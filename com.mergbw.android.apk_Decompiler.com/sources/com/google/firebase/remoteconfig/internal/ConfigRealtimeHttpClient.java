package com.google.firebase.remoteconfig.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.installations.InstallationTokenResult;
import com.google.firebase.remoteconfig.BuildConfig;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class ConfigRealtimeHttpClient {
    private static final String API_KEY_HEADER = "X-Goog-Api-Key";
    static final int[] BACKOFF_TIME_DURATIONS_IN_MINUTES = {2, 4, 8, 16, 32, 64, 128, 256};
    private static final Pattern GMP_APP_ID_PATTERN = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private static final String INSTALLATIONS_AUTH_TOKEN_HEADER = "X-Goog-Firebase-Installations-Auth";
    private static final String X_ACCEPT_RESPONSE_STREAMING = "X-Accept-Response-Streaming";
    private static final String X_ANDROID_CERT_HEADER = "X-Android-Cert";
    private static final String X_ANDROID_PACKAGE_HEADER = "X-Android-Package";
    private static final String X_GOOGLE_GFE_CAN_RETRY = "X-Google-GFE-Can-Retry";
    private final int ORIGINAL_RETRIES = 8;
    ConfigCacheClient activatedCache;
    private final Clock clock;
    private final ConfigFetchHandler configFetchHandler;
    private final Context context;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private int httpRetriesRemaining;
    private boolean isHttpConnectionRunning;
    private boolean isInBackground;
    private boolean isRealtimeDisabled;
    private final Set<ConfigUpdateListener> listeners;
    private final ConfigMetadataClient metadataClient;
    private final String namespace;
    private final Random random;
    private final ScheduledExecutorService scheduledExecutorService;

    private boolean isStatusCodeRetryable(int i) {
        return i == 408 || i == 429 || i == 502 || i == 503 || i == 504;
    }

    public ConfigRealtimeHttpClient(FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, ConfigFetchHandler configFetchHandler2, ConfigCacheClient configCacheClient, Context context2, String str, Set<ConfigUpdateListener> set, ConfigMetadataClient configMetadataClient, ScheduledExecutorService scheduledExecutorService2) {
        this.listeners = set;
        this.isHttpConnectionRunning = false;
        this.scheduledExecutorService = scheduledExecutorService2;
        this.random = new Random();
        this.httpRetriesRemaining = Math.max(8 - configMetadataClient.getRealtimeBackoffMetadata().getNumFailedStreams(), 1);
        this.clock = DefaultClock.getInstance();
        this.firebaseApp = firebaseApp2;
        this.configFetchHandler = configFetchHandler2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.activatedCache = configCacheClient;
        this.context = context2;
        this.namespace = str;
        this.metadataClient = configMetadataClient;
        this.isRealtimeDisabled = false;
        this.isInBackground = false;
    }

    private static String extractProjectNumberFromAppId(String str) {
        Matcher matcher = GMP_APP_ID_PATTERN.matcher(str);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }

    private String getFingerprintHashForPackage() {
        try {
            Context context2 = this.context;
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context2, context2.getPackageName());
            if (packageCertificateHashBytes != null) {
                return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
            }
            Log.e(FirebaseRemoteConfig.TAG, "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.i(FirebaseRemoteConfig.TAG, "No such package: " + this.context.getPackageName());
            return null;
        }
    }

    private void setCommonRequestHeaders(HttpURLConnection httpURLConnection, String str) {
        httpURLConnection.setRequestProperty(INSTALLATIONS_AUTH_TOKEN_HEADER, str);
        httpURLConnection.setRequestProperty(API_KEY_HEADER, this.firebaseApp.getOptions().getApiKey());
        httpURLConnection.setRequestProperty(X_ANDROID_PACKAGE_HEADER, this.context.getPackageName());
        httpURLConnection.setRequestProperty(X_ANDROID_CERT_HEADER, getFingerprintHashForPackage());
        httpURLConnection.setRequestProperty(X_GOOGLE_GFE_CAN_RETRY, "yes");
        httpURLConnection.setRequestProperty(X_ACCEPT_RESPONSE_STREAMING, "true");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Accept", "application/json");
    }

    private JSONObject createRequestBody(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("project", extractProjectNumberFromAppId(this.firebaseApp.getOptions().getApplicationId()));
        hashMap.put("namespace", this.namespace);
        hashMap.put("lastKnownVersionNumber", Long.toString(this.configFetchHandler.getTemplateVersionNumber()));
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_ID, this.firebaseApp.getOptions().getApplicationId());
        hashMap.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, BuildConfig.VERSION_NAME);
        hashMap.put(RemoteConfigConstants.RequestFieldKey.INSTANCE_ID, str);
        return new JSONObject(hashMap);
    }

    public void setRequestParams(HttpURLConnection httpURLConnection, String str, String str2) throws IOException {
        httpURLConnection.setRequestMethod(ShareTarget.METHOD_POST);
        setCommonRequestHeaders(httpURLConnection, str2);
        byte[] bytes = createRequestBody(str).toString().getBytes("utf-8");
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /* access modifiers changed from: private */
    public synchronized void propagateErrors(FirebaseRemoteConfigException firebaseRemoteConfigException) {
        for (ConfigUpdateListener onError : this.listeners) {
            onError.onError(firebaseRemoteConfigException);
        }
    }

    public int getNumberOfFailedStreams() {
        return this.metadataClient.getRealtimeBackoffMetadata().getNumFailedStreams();
    }

    public Date getBackoffEndTime() {
        return this.metadataClient.getRealtimeBackoffMetadata().getBackoffEndTime();
    }

    private void updateBackoffMetadataWithLastFailedStreamConnectionTime(Date date) {
        int numFailedStreams = this.metadataClient.getRealtimeBackoffMetadata().getNumFailedStreams() + 1;
        this.metadataClient.setRealtimeBackoffMetadata(numFailedStreams, new Date(date.getTime() + getRandomizedBackoffDurationInMillis(numFailedStreams)));
    }

    private long getRandomizedBackoffDurationInMillis(int i) {
        int[] iArr = BACKOFF_TIME_DURATIONS_IN_MINUTES;
        int length = iArr.length;
        if (i >= length) {
            i = length;
        }
        long millis = TimeUnit.MINUTES.toMillis((long) iArr[i - 1]);
        return (millis / 2) + ((long) this.random.nextInt((int) millis));
    }

    /* access modifiers changed from: private */
    public synchronized void enableBackoff() {
        this.isRealtimeDisabled = true;
    }

    private synchronized boolean canMakeHttpStreamConnection() {
        return !this.listeners.isEmpty() && !this.isHttpConnectionRunning && !this.isRealtimeDisabled && !this.isInBackground;
    }

    private String getRealtimeURL(String str) {
        return String.format(RemoteConfigConstants.REALTIME_REGEX_URL, new Object[]{extractProjectNumberFromAppId(this.firebaseApp.getOptions().getApplicationId()), str});
    }

    private URL getUrl() {
        try {
            return new URL(getRealtimeURL(this.namespace));
        } catch (MalformedURLException unused) {
            Log.e(FirebaseRemoteConfig.TAG, "URL is malformed");
            return null;
        }
    }

    public Task<HttpURLConnection> createRealtimeConnection() {
        Task<InstallationTokenResult> token = this.firebaseInstallations.getToken(false);
        Task<String> id = this.firebaseInstallations.getId();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{token, id}).continueWithTask(this.scheduledExecutorService, new ConfigRealtimeHttpClient$$ExternalSyntheticLambda0(this, token, id));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$createRealtimeConnection$0$com-google-firebase-remoteconfig-internal-ConfigRealtimeHttpClient  reason: not valid java name */
    public /* synthetic */ Task m677lambda$createRealtimeConnection$0$comgooglefirebaseremoteconfiginternalConfigRealtimeHttpClient(Task task, Task task2, Task task3) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation auth token for config update listener connection.", (Throwable) task.getException()));
        }
        if (!task2.isSuccessful()) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Firebase Installations failed to get installation ID for config update listener connection.", (Throwable) task2.getException()));
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) getUrl().openConnection();
            setRequestParams(httpURLConnection, (String) task2.getResult(), ((InstallationTokenResult) task.getResult()).getToken());
            return Tasks.forResult(httpURLConnection);
        } catch (IOException e) {
            return Tasks.forException(new FirebaseRemoteConfigClientException("Failed to open HTTP stream connection", (Throwable) e));
        }
    }

    public void startHttpConnection() {
        makeRealtimeHttpConnection(0);
    }

    public synchronized void retryHttpConnectionWhenBackoffEnds() {
        makeRealtimeHttpConnection(Math.max(0, this.metadataClient.getRealtimeBackoffMetadata().getBackoffEndTime().getTime() - new Date(this.clock.currentTimeMillis()).getTime()));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void makeRealtimeHttpConnection(long r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.canMakeHttpStreamConnection()     // Catch:{ all -> 0x0030 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r3)
            return
        L_0x0009:
            int r0 = r3.httpRetriesRemaining     // Catch:{ all -> 0x0030 }
            if (r0 <= 0) goto L_0x001e
            int r0 = r0 + -1
            r3.httpRetriesRemaining = r0     // Catch:{ all -> 0x0030 }
            java.util.concurrent.ScheduledExecutorService r0 = r3.scheduledExecutorService     // Catch:{ all -> 0x0030 }
            com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient$1 r1 = new com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient$1     // Catch:{ all -> 0x0030 }
            r1.<init>()     // Catch:{ all -> 0x0030 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0030 }
            r0.schedule(r1, r4, r2)     // Catch:{ all -> 0x0030 }
            goto L_0x002e
        L_0x001e:
            boolean r4 = r3.isInBackground     // Catch:{ all -> 0x0030 }
            if (r4 != 0) goto L_0x002e
            com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException r4 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException     // Catch:{ all -> 0x0030 }
            java.lang.String r5 = "Unable to connect to the server. Check your connection and try again."
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r0 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR     // Catch:{ all -> 0x0030 }
            r4.<init>((java.lang.String) r5, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r0)     // Catch:{ all -> 0x0030 }
            r3.propagateErrors(r4)     // Catch:{ all -> 0x0030 }
        L_0x002e:
            monitor-exit(r3)
            return
        L_0x0030:
            r4 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0030 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient.makeRealtimeHttpConnection(long):void");
    }

    /* access modifiers changed from: package-private */
    public void setRealtimeBackgroundState(boolean z) {
        this.isInBackground = z;
    }

    private synchronized void resetRetryCount() {
        this.httpRetriesRemaining = 8;
    }

    private synchronized void setIsHttpConnectionRunning(boolean z) {
        this.isHttpConnectionRunning = z;
    }

    public synchronized ConfigAutoFetch startAutoFetch(HttpURLConnection httpURLConnection) {
        HttpURLConnection httpURLConnection2;
        httpURLConnection2 = httpURLConnection;
        return new ConfigAutoFetch(httpURLConnection2, this.configFetchHandler, this.activatedCache, this.listeners, new ConfigUpdateListener() {
            public void onUpdate(ConfigUpdate configUpdate) {
            }

            public void onError(FirebaseRemoteConfigException firebaseRemoteConfigException) {
                ConfigRealtimeHttpClient.this.enableBackoff();
                ConfigRealtimeHttpClient.this.propagateErrors(firebaseRemoteConfigException);
            }
        }, this.scheduledExecutorService);
    }

    private String parseForbiddenErrorResponseMessage(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException unused) {
            if (sb.length() == 0) {
                return "Unable to connect to the server, access is forbidden. HTTP status code: 403";
            }
        }
        return sb.toString();
    }

    public void beginRealtimeHttpStream() {
        if (canMakeHttpStreamConnection()) {
            if (new Date(this.clock.currentTimeMillis()).before(this.metadataClient.getRealtimeBackoffMetadata().getBackoffEndTime())) {
                retryHttpConnectionWhenBackoffEnds();
                return;
            }
            Task<HttpURLConnection> createRealtimeConnection = createRealtimeConnection();
            Tasks.whenAllComplete((Task<?>[]) new Task[]{createRealtimeConnection}).continueWith(this.scheduledExecutorService, new ConfigRealtimeHttpClient$$ExternalSyntheticLambda1(this, createRealtimeConnection));
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0157  */
    /* renamed from: lambda$beginRealtimeHttpStream$1$com-google-firebase-remoteconfig-internal-ConfigRealtimeHttpClient  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ com.google.android.gms.tasks.Task m676lambda$beginRealtimeHttpStream$1$comgooglefirebaseremoteconfiginternalConfigRealtimeHttpClient(com.google.android.gms.tasks.Task r12, com.google.android.gms.tasks.Task r13) throws java.lang.Exception {
        /*
            r11 = this;
            java.lang.String r13 = "Unable to connect to the server. Try again in a few minutes. HTTP status code: %d"
            r0 = 403(0x193, float:5.65E-43)
            r1 = 200(0xc8, float:2.8E-43)
            r2 = 1
            r3 = 0
            r4 = 0
            boolean r5 = r12.isSuccessful()     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            if (r5 == 0) goto L_0x0094
            r11.setIsHttpConnectionRunning(r2)     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            java.lang.Object r12 = r12.getResult()     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            java.net.HttpURLConnection r12 = (java.net.HttpURLConnection) r12     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            int r5 = r12.getResponseCode()     // Catch:{ IOException -> 0x0091, all -> 0x008c }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ IOException -> 0x0091, all -> 0x008c }
            r6.getClass()     // Catch:{ IOException -> 0x008a }
            if (r5 != r1) goto L_0x0034
            r11.resetRetryCount()     // Catch:{ IOException -> 0x008a }
            com.google.firebase.remoteconfig.internal.ConfigMetadataClient r7 = r11.metadataClient     // Catch:{ IOException -> 0x008a }
            r7.resetRealtimeBackoff()     // Catch:{ IOException -> 0x008a }
            com.google.firebase.remoteconfig.internal.ConfigAutoFetch r7 = r11.startAutoFetch(r12)     // Catch:{ IOException -> 0x008a }
            r7.listenForNotifications()     // Catch:{ IOException -> 0x008a }
        L_0x0034:
            r11.closeRealtimeHttpStream(r12)
            r11.setIsHttpConnectionRunning(r3)
            if (r6 == 0) goto L_0x0048
            r6.getClass()
            boolean r7 = r11.isStatusCodeRetryable(r5)
            if (r7 == 0) goto L_0x0046
            goto L_0x0048
        L_0x0046:
            r7 = r3
            goto L_0x0049
        L_0x0048:
            r7 = r2
        L_0x0049:
            if (r7 == 0) goto L_0x0059
            java.util.Date r8 = new java.util.Date
            com.google.android.gms.common.util.Clock r9 = r11.clock
            long r9 = r9.currentTimeMillis()
            r8.<init>(r9)
            r11.updateBackoffMetadataWithLastFailedStreamConnectionTime(r8)
        L_0x0059:
            if (r7 != 0) goto L_0x0085
            r6.getClass()
            if (r5 != r1) goto L_0x0061
            goto L_0x0085
        L_0x0061:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r1[r3] = r6
            java.lang.String r13 = java.lang.String.format(r13, r1)
            r6.getClass()
            if (r5 != r0) goto L_0x0076
            java.io.InputStream r12 = r12.getErrorStream()
            java.lang.String r13 = r11.parseForbiddenErrorResponseMessage(r12)
        L_0x0076:
            com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException r12 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException
            r6.getClass()
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r0 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR
            r12.<init>((int) r5, (java.lang.String) r13, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r0)
        L_0x0080:
            r11.propagateErrors(r12)
            goto L_0x00fe
        L_0x0085:
            r11.retryHttpConnectionWhenBackoffEnds()
            goto L_0x00fe
        L_0x008a:
            r5 = move-exception
            goto L_0x00a6
        L_0x008c:
            r5 = move-exception
            r6 = r4
            r4 = r5
            goto L_0x0104
        L_0x0091:
            r5 = move-exception
            r6 = r4
            goto L_0x00a6
        L_0x0094:
            java.io.IOException r5 = new java.io.IOException     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            java.lang.Exception r12 = r12.getException()     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            r5.<init>(r12)     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
            throw r5     // Catch:{ IOException -> 0x00a3, all -> 0x009e }
        L_0x009e:
            r12 = move-exception
            r6 = r4
            r4 = r12
            r12 = r6
            goto L_0x0104
        L_0x00a3:
            r5 = move-exception
            r12 = r4
            r6 = r12
        L_0x00a6:
            java.lang.String r7 = "FirebaseRemoteConfig"
            java.lang.String r8 = "Exception connecting to real-time RC backend. Retrying the connection..."
            android.util.Log.d(r7, r8, r5)     // Catch:{ all -> 0x0103 }
            r11.closeRealtimeHttpStream(r12)
            r11.setIsHttpConnectionRunning(r3)
            if (r6 == 0) goto L_0x00c2
            int r5 = r6.intValue()
            boolean r5 = r11.isStatusCodeRetryable(r5)
            if (r5 == 0) goto L_0x00c0
            goto L_0x00c2
        L_0x00c0:
            r5 = r3
            goto L_0x00c3
        L_0x00c2:
            r5 = r2
        L_0x00c3:
            if (r5 == 0) goto L_0x00d3
            java.util.Date r7 = new java.util.Date
            com.google.android.gms.common.util.Clock r8 = r11.clock
            long r8 = r8.currentTimeMillis()
            r7.<init>(r8)
            r11.updateBackoffMetadataWithLastFailedStreamConnectionTime(r7)
        L_0x00d3:
            if (r5 != 0) goto L_0x0085
            int r5 = r6.intValue()
            if (r5 != r1) goto L_0x00dc
            goto L_0x0085
        L_0x00dc:
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r1[r3] = r6
            java.lang.String r13 = java.lang.String.format(r13, r1)
            int r1 = r6.intValue()
            if (r1 != r0) goto L_0x00f2
            java.io.InputStream r12 = r12.getErrorStream()
            java.lang.String r13 = r11.parseForbiddenErrorResponseMessage(r12)
        L_0x00f2:
            com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException r12 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException
            int r0 = r6.intValue()
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR
            r12.<init>((int) r0, (java.lang.String) r13, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r1)
            goto L_0x0080
        L_0x00fe:
            com.google.android.gms.tasks.Task r12 = com.google.android.gms.tasks.Tasks.forResult(r4)
            return r12
        L_0x0103:
            r4 = move-exception
        L_0x0104:
            r11.closeRealtimeHttpStream(r12)
            r11.setIsHttpConnectionRunning(r3)
            if (r6 == 0) goto L_0x0119
            int r5 = r6.intValue()
            boolean r5 = r11.isStatusCodeRetryable(r5)
            if (r5 == 0) goto L_0x0117
            goto L_0x0119
        L_0x0117:
            r5 = r3
            goto L_0x011a
        L_0x0119:
            r5 = r2
        L_0x011a:
            if (r5 == 0) goto L_0x012a
            java.util.Date r7 = new java.util.Date
            com.google.android.gms.common.util.Clock r8 = r11.clock
            long r8 = r8.currentTimeMillis()
            r7.<init>(r8)
            r11.updateBackoffMetadataWithLastFailedStreamConnectionTime(r7)
        L_0x012a:
            if (r5 != 0) goto L_0x0157
            int r5 = r6.intValue()
            if (r5 == r1) goto L_0x0157
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r1[r3] = r6
            java.lang.String r13 = java.lang.String.format(r13, r1)
            int r1 = r6.intValue()
            if (r1 != r0) goto L_0x0148
            java.io.InputStream r12 = r12.getErrorStream()
            java.lang.String r13 = r11.parseForbiddenErrorResponseMessage(r12)
        L_0x0148:
            com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException r12 = new com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException
            int r0 = r6.intValue()
            com.google.firebase.remoteconfig.FirebaseRemoteConfigException$Code r1 = com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code.CONFIG_UPDATE_STREAM_ERROR
            r12.<init>((int) r0, (java.lang.String) r13, (com.google.firebase.remoteconfig.FirebaseRemoteConfigException.Code) r1)
            r11.propagateErrors(r12)
            goto L_0x015a
        L_0x0157:
            r11.retryHttpConnectionWhenBackoffEnds()
        L_0x015a:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigRealtimeHttpClient.m676lambda$beginRealtimeHttpStream$1$comgooglefirebaseremoteconfiginternalConfigRealtimeHttpClient(com.google.android.gms.tasks.Task, com.google.android.gms.tasks.Task):com.google.android.gms.tasks.Task");
    }

    public void closeRealtimeHttpStream(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
            try {
                httpURLConnection.getInputStream().close();
                if (httpURLConnection.getErrorStream() != null) {
                    httpURLConnection.getErrorStream().close();
                }
            } catch (IOException unused) {
            }
        }
    }
}
