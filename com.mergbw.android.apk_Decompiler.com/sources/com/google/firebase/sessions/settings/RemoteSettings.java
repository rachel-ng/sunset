package com.google.firebase.sessions.settings;

import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.sessions.ApplicationInfo;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0000\u0018\u0000 +2\u00020\u0001:\u0001+B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\u0010\rJ\r\u0010\"\u001a\u00020#H\u0001¢\u0006\u0002\b$J\b\u0010%\u001a\u00020\u0015H\u0016J\u0010\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'H\u0002J\u0011\u0010)\u001a\u00020#H@ø\u0001\u0000¢\u0006\u0002\u0010*R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\u0004\u0018\u00010\u00118VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u00158VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u00198VX\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001b\u0010\u001c\u001a\u00020\u001d8BX\u0002¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\u001e\u0010\u001f\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006,"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings;", "Lcom/google/firebase/sessions/settings/SettingsProvider;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "firebaseInstallationsApi", "Lcom/google/firebase/installations/FirebaseInstallationsApi;", "appInfo", "Lcom/google/firebase/sessions/ApplicationInfo;", "configsFetcher", "Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;", "dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "(Lkotlin/coroutines/CoroutineContext;Lcom/google/firebase/installations/FirebaseInstallationsApi;Lcom/google/firebase/sessions/ApplicationInfo;Lcom/google/firebase/sessions/settings/CrashlyticsSettingsFetcher;Landroidx/datastore/core/DataStore;)V", "fetchInProgress", "Lkotlinx/coroutines/sync/Mutex;", "samplingRate", "", "getSamplingRate", "()Ljava/lang/Double;", "sessionEnabled", "", "getSessionEnabled", "()Ljava/lang/Boolean;", "sessionRestartTimeout", "Lkotlin/time/Duration;", "getSessionRestartTimeout-FghU774", "()Lkotlin/time/Duration;", "settingsCache", "Lcom/google/firebase/sessions/settings/SettingsCache;", "getSettingsCache", "()Lcom/google/firebase/sessions/settings/SettingsCache;", "settingsCache$delegate", "Lkotlin/Lazy;", "clearCachedSettings", "", "clearCachedSettings$com_google_firebase_firebase_sessions", "isSettingsStale", "removeForwardSlashesIn", "", "s", "updateSettings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RemoteSettings.kt */
public final class RemoteSettings implements SettingsProvider {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @Deprecated
    public static final String FORWARD_SLASH_STRING = "/";
    @Deprecated
    public static final String TAG = "SessionConfigFetcher";
    private final ApplicationInfo appInfo;
    private final CoroutineContext backgroundDispatcher;
    private final CrashlyticsSettingsFetcher configsFetcher;
    private final Mutex fetchInProgress = MutexKt.Mutex$default(false, 1, (Object) null);
    private final FirebaseInstallationsApi firebaseInstallationsApi;
    private final Lazy settingsCache$delegate;

    public RemoteSettings(CoroutineContext coroutineContext, FirebaseInstallationsApi firebaseInstallationsApi2, ApplicationInfo applicationInfo, CrashlyticsSettingsFetcher crashlyticsSettingsFetcher, DataStore<Preferences> dataStore) {
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(firebaseInstallationsApi2, "firebaseInstallationsApi");
        Intrinsics.checkNotNullParameter(applicationInfo, "appInfo");
        Intrinsics.checkNotNullParameter(crashlyticsSettingsFetcher, "configsFetcher");
        Intrinsics.checkNotNullParameter(dataStore, "dataStore");
        this.backgroundDispatcher = coroutineContext;
        this.firebaseInstallationsApi = firebaseInstallationsApi2;
        this.appInfo = applicationInfo;
        this.configsFetcher = crashlyticsSettingsFetcher;
        this.settingsCache$delegate = LazyKt.lazy(new RemoteSettings$settingsCache$2(dataStore));
    }

    /* access modifiers changed from: private */
    public final SettingsCache getSettingsCache() {
        return (SettingsCache) this.settingsCache$delegate.getValue();
    }

    public Boolean getSessionEnabled() {
        return getSettingsCache().sessionsEnabled();
    }

    /* renamed from: getSessionRestartTimeout-FghU774  reason: not valid java name */
    public Duration m685getSessionRestartTimeoutFghU774() {
        Integer sessionRestartTimeout = getSettingsCache().sessionRestartTimeout();
        if (sessionRestartTimeout == null) {
            return null;
        }
        Duration.Companion companion = Duration.Companion;
        return Duration.m150boximpl(DurationKt.toDuration(sessionRestartTimeout.intValue(), DurationUnit.SECONDS));
    }

    public Double getSamplingRate() {
        return getSettingsCache().sessionSamplingRate();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0096 A[Catch:{ all -> 0x0053 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a1 A[SYNTHETIC, Splitter:B:39:0x00a1] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c0 A[Catch:{ all -> 0x0053 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object updateSettings(kotlin.coroutines.Continuation<? super kotlin.Unit> r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            boolean r2 = r0 instanceof com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1
            if (r2 == 0) goto L_0x0018
            r2 = r0
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1 r2 = (com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r2.label
            int r0 = r0 - r4
            r2.label = r0
            goto L_0x001d
        L_0x0018:
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1 r2 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$1
            r2.<init>(r1, r0)
        L_0x001d:
            java.lang.Object r0 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            java.lang.String r5 = "SessionConfigFetcher"
            r6 = 3
            r7 = 1
            r8 = 2
            r9 = 0
            if (r4 == 0) goto L_0x0063
            if (r4 == r7) goto L_0x0057
            if (r4 == r8) goto L_0x0047
            if (r4 != r6) goto L_0x003f
            java.lang.Object r2 = r2.L$0
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x003c }
            goto L_0x015a
        L_0x003c:
            r0 = move-exception
            goto L_0x0162
        L_0x003f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0047:
            java.lang.Object r4 = r2.L$1
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r10 = r2.L$0
            com.google.firebase.sessions.settings.RemoteSettings r10 = (com.google.firebase.sessions.settings.RemoteSettings) r10
            kotlin.ResultKt.throwOnFailure(r0)     // Catch:{ all -> 0x0053 }
            goto L_0x00b2
        L_0x0053:
            r0 = move-exception
            r2 = r4
            goto L_0x0162
        L_0x0057:
            java.lang.Object r4 = r2.L$1
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r10 = r2.L$0
            com.google.firebase.sessions.settings.RemoteSettings r10 = (com.google.firebase.sessions.settings.RemoteSettings) r10
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x008c
        L_0x0063:
            kotlin.ResultKt.throwOnFailure(r0)
            kotlinx.coroutines.sync.Mutex r0 = r1.fetchInProgress
            boolean r0 = r0.isLocked()
            if (r0 != 0) goto L_0x007b
            com.google.firebase.sessions.settings.SettingsCache r0 = r16.getSettingsCache()
            boolean r0 = r0.hasCacheExpired$com_google_firebase_firebase_sessions()
            if (r0 != 0) goto L_0x007b
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x007b:
            kotlinx.coroutines.sync.Mutex r0 = r1.fetchInProgress
            r2.L$0 = r1
            r2.L$1 = r0
            r2.label = r7
            java.lang.Object r4 = r0.lock(r9, r2)
            if (r4 != r3) goto L_0x008a
            return r3
        L_0x008a:
            r4 = r0
            r10 = r1
        L_0x008c:
            com.google.firebase.sessions.settings.SettingsCache r0 = r10.getSettingsCache()     // Catch:{ all -> 0x0053 }
            boolean r0 = r0.hasCacheExpired$com_google_firebase_firebase_sessions()     // Catch:{ all -> 0x0053 }
            if (r0 != 0) goto L_0x00a1
            java.lang.String r0 = "Remote settings cache not expired. Using cached values."
            android.util.Log.d(r5, r0)     // Catch:{ all -> 0x0053 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0053 }
            r4.unlock(r9)
            return r0
        L_0x00a1:
            com.google.firebase.sessions.InstallationId$Companion r0 = com.google.firebase.sessions.InstallationId.Companion     // Catch:{ all -> 0x0053 }
            com.google.firebase.installations.FirebaseInstallationsApi r11 = r10.firebaseInstallationsApi     // Catch:{ all -> 0x0053 }
            r2.L$0 = r10     // Catch:{ all -> 0x0053 }
            r2.L$1 = r4     // Catch:{ all -> 0x0053 }
            r2.label = r8     // Catch:{ all -> 0x0053 }
            java.lang.Object r0 = r0.create(r11, r2)     // Catch:{ all -> 0x0053 }
            if (r0 != r3) goto L_0x00b2
            return r3
        L_0x00b2:
            com.google.firebase.sessions.InstallationId r0 = (com.google.firebase.sessions.InstallationId) r0     // Catch:{ all -> 0x0053 }
            java.lang.String r0 = r0.getFid()     // Catch:{ all -> 0x0053 }
            java.lang.String r11 = ""
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r11)     // Catch:{ all -> 0x0053 }
            if (r11 == 0) goto L_0x00cb
            java.lang.String r0 = "Error getting Firebase Installation ID. Skipping this Session Event."
            android.util.Log.w(r5, r0)     // Catch:{ all -> 0x0053 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0053 }
            r4.unlock(r9)
            return r0
        L_0x00cb:
            r11 = 5
            kotlin.Pair[] r11 = new kotlin.Pair[r11]     // Catch:{ all -> 0x0053 }
            java.lang.String r12 = "X-Crashlytics-Installation-ID"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r12, r0)     // Catch:{ all -> 0x0053 }
            r12 = 0
            r11[r12] = r0     // Catch:{ all -> 0x0053 }
            java.lang.String r0 = "X-Crashlytics-Device-Model"
            kotlin.jvm.internal.StringCompanionObject r13 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ all -> 0x0053 }
            java.lang.String r13 = "%s/%s"
            java.lang.Object[] r14 = new java.lang.Object[r8]     // Catch:{ all -> 0x0053 }
            java.lang.String r15 = android.os.Build.MANUFACTURER     // Catch:{ all -> 0x0053 }
            r14[r12] = r15     // Catch:{ all -> 0x0053 }
            java.lang.String r12 = android.os.Build.MODEL     // Catch:{ all -> 0x0053 }
            r14[r7] = r12     // Catch:{ all -> 0x0053 }
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r14, r8)     // Catch:{ all -> 0x0053 }
            java.lang.String r12 = java.lang.String.format(r13, r12)     // Catch:{ all -> 0x0053 }
            java.lang.String r13 = "format(format, *args)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r13)     // Catch:{ all -> 0x0053 }
            java.lang.String r12 = r10.removeForwardSlashesIn(r12)     // Catch:{ all -> 0x0053 }
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r12)     // Catch:{ all -> 0x0053 }
            r11[r7] = r0     // Catch:{ all -> 0x0053 }
            java.lang.String r0 = "X-Crashlytics-OS-Build-Version"
            java.lang.String r7 = android.os.Build.VERSION.INCREMENTAL     // Catch:{ all -> 0x0053 }
            java.lang.String r12 = "INCREMENTAL"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r12)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = r10.removeForwardSlashesIn(r7)     // Catch:{ all -> 0x0053 }
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r7)     // Catch:{ all -> 0x0053 }
            r11[r8] = r0     // Catch:{ all -> 0x0053 }
            java.lang.String r0 = "X-Crashlytics-OS-Display-Version"
            java.lang.String r7 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x0053 }
            java.lang.String r8 = "RELEASE"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = r10.removeForwardSlashesIn(r7)     // Catch:{ all -> 0x0053 }
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r7)     // Catch:{ all -> 0x0053 }
            r11[r6] = r0     // Catch:{ all -> 0x0053 }
            java.lang.String r0 = "X-Crashlytics-API-Client-Version"
            com.google.firebase.sessions.ApplicationInfo r7 = r10.appInfo     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = r7.getSessionSdkVersion()     // Catch:{ all -> 0x0053 }
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r7)     // Catch:{ all -> 0x0053 }
            r7 = 4
            r11[r7] = r0     // Catch:{ all -> 0x0053 }
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r11)     // Catch:{ all -> 0x0053 }
            java.lang.String r7 = "Fetching settings from server."
            android.util.Log.d(r5, r7)     // Catch:{ all -> 0x0053 }
            com.google.firebase.sessions.settings.CrashlyticsSettingsFetcher r5 = r10.configsFetcher     // Catch:{ all -> 0x0053 }
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1 r7 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$1     // Catch:{ all -> 0x0053 }
            r7.<init>(r10, r9)     // Catch:{ all -> 0x0053 }
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7     // Catch:{ all -> 0x0053 }
            com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$2 r8 = new com.google.firebase.sessions.settings.RemoteSettings$updateSettings$2$2     // Catch:{ all -> 0x0053 }
            r8.<init>(r9)     // Catch:{ all -> 0x0053 }
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8     // Catch:{ all -> 0x0053 }
            r2.L$0 = r4     // Catch:{ all -> 0x0053 }
            r2.L$1 = r9     // Catch:{ all -> 0x0053 }
            r2.label = r6     // Catch:{ all -> 0x0053 }
            java.lang.Object r0 = r5.doConfigFetch(r0, r7, r8, r2)     // Catch:{ all -> 0x0053 }
            if (r0 != r3) goto L_0x0159
            return r3
        L_0x0159:
            r2 = r4
        L_0x015a:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003c }
            r2.unlock(r9)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0162:
            r2.unlock(r9)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.settings.RemoteSettings.updateSettings(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public boolean isSettingsStale() {
        return getSettingsCache().hasCacheExpired$com_google_firebase_firebase_sessions();
    }

    public final void clearCachedSettings$com_google_firebase_firebase_sessions() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new RemoteSettings$clearCachedSettings$1(this, (Continuation<? super RemoteSettings$clearCachedSettings$1>) null), 3, (Object) null);
    }

    private final String removeForwardSlashesIn(String str) {
        return new Regex(FORWARD_SLASH_STRING).replace((CharSequence) str, "");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/google/firebase/sessions/settings/RemoteSettings$Companion;", "", "()V", "FORWARD_SLASH_STRING", "", "TAG", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RemoteSettings.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
