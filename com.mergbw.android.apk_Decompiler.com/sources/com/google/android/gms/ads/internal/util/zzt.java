package com.google.android.gms.ads.internal.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import androidx.browser.customtabs.CustomTabsIntent;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.google.android.gms.ads.formats.zzj;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzl;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.ads.zzbeg;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzbfm;
import com.google.android.gms.internal.ads.zzbgt;
import com.google.android.gms.internal.ads.zzbyf;
import com.google.android.gms.internal.ads.zzcgu;
import com.google.android.gms.internal.ads.zzcig;
import com.google.android.gms.internal.ads.zzdsg;
import com.google.android.gms.internal.ads.zzfgt;
import com.google.android.gms.internal.ads.zzfgw;
import com.google.android.gms.internal.ads.zzfuv;
import com.google.android.gms.internal.ads.zzfxr;
import com.google.android.gms.internal.ads.zzfyt;
import com.google.android.gms.internal.ads.zzgft;
import com.google.android.gms.internal.ads.zzhlh;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzt {
    public static final zzfuv zza = new zzf(Looper.getMainLooper());
    private final AtomicReference zzb = new AtomicReference((Object) null);
    private final AtomicReference zzc = new AtomicReference((Object) null);
    private final AtomicReference zzd = new AtomicReference(new Bundle());
    private final AtomicBoolean zze = new AtomicBoolean();
    /* access modifiers changed from: private */
    public boolean zzf = true;
    private final Object zzg = new Object();
    private String zzh;
    private volatile String zzi;
    private boolean zzj = false;
    private boolean zzk = false;
    private final Executor zzl = Executors.newSingleThreadExecutor();

    public static final boolean zzA(Context context, String str) {
        Context zza2 = zzbyf.zza(context);
        return Wrappers.packageManager(zza2).checkPermission(str, zza2.getPackageName()) == 0;
    }

    public static final boolean zzB(Context context) {
        try {
            return DeviceProperties.isBstar(context);
        } catch (NoSuchMethodError unused) {
            return false;
        }
    }

    public static final boolean zzC(String str) {
        if (!zzl.zzk()) {
            return false;
        }
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeV)).booleanValue()) {
            return false;
        }
        String str2 = (String) zzba.zzc().zza(zzbep.zzeX);
        if (!str2.isEmpty()) {
            for (String equals : str2.split(";")) {
                if (equals.equals(str)) {
                    return false;
                }
            }
        }
        String str3 = (String) zzba.zzc().zza(zzbep.zzeW);
        if (str3.isEmpty()) {
            return true;
        }
        for (String equals2 : str3.split(";")) {
            if (equals2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean zzD(Context context) {
        KeyguardManager zzX;
        if (context == null || (zzX = zzX(context)) == null || !zzX.isKeyguardLocked()) {
            return false;
        }
        return true;
    }

    public static final boolean zzE(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException unused) {
            return true;
        } catch (Throwable th) {
            zzm.zzh("Error loading class.", th);
            zzu.zzo().zzw(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static final boolean zzF() {
        int myUid = Process.myUid();
        return myUid == 0 || myUid == 1000;
    }

    public static final boolean zzG(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager != null) {
                if (keyguardManager != null) {
                    List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses == null) {
                        return false;
                    }
                    for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                        if (Process.myPid() == next.pid) {
                            if (next.importance != 100 || keyguardManager.inKeyguardRestrictedInputMode()) {
                                return true;
                            }
                            PowerManager powerManager = (PowerManager) context.getSystemService("power");
                            if (powerManager == null) {
                                return true;
                            }
                            if (powerManager.isScreenOn()) {
                                return false;
                            }
                            return true;
                        }
                    }
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static final boolean zzH(Context context) {
        try {
            Bundle zzY = zzY(context);
            String string = zzY.getString("com.google.android.gms.ads.INTEGRATION_MANAGER");
            if (!TextUtils.isEmpty(zzZ(zzY)) || TextUtils.isEmpty(string)) {
                return false;
            }
            return true;
        } catch (RemoteException unused) {
        }
    }

    public static final boolean zzI(Context context) {
        Window window;
        if (!(!(context instanceof Activity) || (window = ((Activity) context).getWindow()) == null || window.getDecorView() == null)) {
            Rect rect = new Rect();
            Rect rect2 = new Rect();
            window.getDecorView().getGlobalVisibleRect(rect, (Point) null);
            window.getDecorView().getWindowVisibleDisplayFrame(rect2);
            if (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static final void zzJ(View view, int i, MotionEvent motionEvent) {
        int i2;
        String str;
        int i3;
        int i4;
        String str2;
        zzfgt zzD;
        View view2 = view;
        int[] iArr = new int[2];
        Rect rect = new Rect();
        try {
            String packageName = view.getContext().getPackageName();
            if (view2 instanceof zzdsg) {
                view2 = ((zzdsg) view2).getChildAt(0);
            }
            if ((view2 instanceof zzj) || (view2 instanceof NativeAdView)) {
                str = "NATIVE";
                i2 = 1;
            } else {
                str = "UNKNOWN";
                i2 = 0;
            }
            if (view2.getLocalVisibleRect(rect)) {
                i3 = rect.width();
                i4 = rect.height();
            } else {
                i4 = 0;
                i3 = 0;
            }
            zzu.zzp();
            long zzw = zzw(view2);
            view2.getLocationOnScreen(iArr);
            int i5 = iArr[0];
            int i6 = iArr[1];
            String str3 = "none";
            if (view2 instanceof zzcig) {
                zzfgw zzR = ((zzcig) view2).zzR();
                if (zzR != null) {
                    str2 = zzR.zzb;
                    view2.setContentDescription(str2 + ":" + view2.hashCode());
                    if ((view2 instanceof zzcgu) && (zzD = ((zzcgu) view2).zzD()) != null) {
                        str = zzfgt.zza(zzD.zzb);
                        i2 = zzD.zzf;
                        str3 = zzD.zzF;
                    }
                    zzm.zzi(String.format(Locale.US, "<Ad hashCode=%d, package=%s, adNetCls=%s, gwsQueryId=%s, format=%s, impType=%d, class=%s, x=%d, y=%d, width=%d, height=%d, vWidth=%d, vHeight=%d, alpha=%d, state=%s>", new Object[]{Integer.valueOf(view2.hashCode()), packageName, str3, str2, str, Integer.valueOf(i2), view2.getClass().getName(), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(view2.getWidth()), Integer.valueOf(view2.getHeight()), Integer.valueOf(i3), Integer.valueOf(i4), Long.valueOf(zzw), Integer.toString(i, 2)}));
                }
            }
            str2 = str3;
            str = zzfgt.zza(zzD.zzb);
            i2 = zzD.zzf;
            str3 = zzD.zzF;
            zzm.zzi(String.format(Locale.US, "<Ad hashCode=%d, package=%s, adNetCls=%s, gwsQueryId=%s, format=%s, impType=%d, class=%s, x=%d, y=%d, width=%d, height=%d, vWidth=%d, vHeight=%d, alpha=%d, state=%s>", new Object[]{Integer.valueOf(view2.hashCode()), packageName, str3, str2, str, Integer.valueOf(i2), view2.getClass().getName(), Integer.valueOf(i5), Integer.valueOf(i6), Integer.valueOf(view2.getWidth()), Integer.valueOf(view2.getHeight()), Integer.valueOf(i3), Integer.valueOf(i4), Long.valueOf(zzw), Integer.toString(i, 2)}));
        } catch (Exception e) {
            zzm.zzh("Failure getting view location.", e);
        }
    }

    public static final AlertDialog.Builder zzK(Context context) {
        zzu.zzq();
        return new AlertDialog.Builder(context, 16974374);
    }

    public static final void zzL(Context context, String str, String str2) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(str2);
        for (String zzca : arrayList) {
            new zzca(context, str, zzca).zzb();
        }
    }

    public static final void zzM(Context context, Throwable th) {
        if (context != null) {
            try {
                if (((Boolean) zzbgt.zzb.zze()).booleanValue()) {
                    CrashUtils.addDynamiteErrorToDropBox(context, th);
                }
            } catch (IllegalStateException unused) {
            }
        }
    }

    public static final String zzN(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder sb = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }

    public static final int zzO(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            zzm.zzj("Could not parse value:".concat(e.toString()));
            return 0;
        }
    }

    public static final Map zzP(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : uri.getQueryParameterNames()) {
            if (!TextUtils.isEmpty(next)) {
                hashMap.put(next, uri.getQueryParameter(next));
            }
        }
        return hashMap;
    }

    public static final int[] zzQ(Activity activity) {
        View findViewById;
        Window window = activity.getWindow();
        if (window == null || (findViewById = window.findViewById(16908290)) == null) {
            return zzu();
        }
        return new int[]{findViewById.getWidth(), findViewById.getHeight()};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.findViewById(16908290);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int[] zzR(android.app.Activity r5) {
        /*
            android.view.Window r0 = r5.getWindow()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0021
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            android.view.View r0 = r0.findViewById(r3)
            if (r0 == 0) goto L_0x0021
            r3 = 2
            int[] r3 = new int[r3]
            int r4 = r0.getTop()
            r3[r2] = r4
            int r0 = r0.getBottom()
            r3[r1] = r0
            goto L_0x0025
        L_0x0021:
            int[] r3 = zzu()
        L_0x0025:
            com.google.android.gms.ads.internal.util.client.zzf r0 = com.google.android.gms.ads.internal.client.zzay.zzb()
            r2 = r3[r2]
            int r0 = r0.zzb(r5, r2)
            com.google.android.gms.ads.internal.util.client.zzf r2 = com.google.android.gms.ads.internal.client.zzay.zzb()
            r1 = r3[r1]
            int r5 = r2.zzb(r5, r1)
            int[] r5 = new int[]{r0, r5}
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzt.zzR(android.app.Activity):int[]");
    }

    public static final boolean zzS(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z = zzu.zzp().zzf || keyguardManager == null || !keyguardManager.inKeyguardRestrictedInputMode() || zzn(view);
        long zzw = zzw(view);
        if (view.getVisibility() == 0 && view.isShown() && ((powerManager == null || powerManager.isScreenOn()) && z)) {
            if (!((Boolean) zzba.zzc().zza(zzbep.zzbm)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                if (!((Boolean) zzba.zzc().zza(zzbep.zzkD)).booleanValue()) {
                    return true;
                }
                if (zzw >= ((long) ((Integer) zzba.zzc().zza(zzbep.zzkF)).intValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final void zzT(Context context, Intent intent) {
        if (((Boolean) zzba.zzc().zza(zzbep.zzkX)).booleanValue()) {
            try {
                zzab(context, intent);
            } catch (SecurityException e) {
                zzm.zzk("", e);
                zzu.zzo().zzw(e, "AdUtil.startActivityWithUnknownContext");
            }
        } else {
            zzab(context, intent);
        }
    }

    public static final void zzU(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            zzo(context, intent);
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            zzm.zze("Opening " + uri2 + " in a new browser.");
        } catch (ActivityNotFoundException e) {
            zzm.zzh("No browser is found.", e);
        }
    }

    public static final int[] zzV(Activity activity) {
        int[] zzQ = zzQ(activity);
        return new int[]{zzay.zzb().zzb(activity, zzQ[0]), zzay.zzb().zzb(activity, zzQ[1])};
    }

    public static final boolean zzW(View view, Context context) {
        Context applicationContext = context.getApplicationContext();
        return zzS(view, applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null, zzX(context));
    }

    private static KeyguardManager zzX(Context context) {
        Object systemService = context.getSystemService("keyguard");
        if (systemService == null || !(systemService instanceof KeyguardManager)) {
            return null;
        }
        return (KeyguardManager) systemService;
    }

    private static Bundle zzY(Context context) throws RemoteException {
        try {
            return Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            zze.zzb("Error getting metadata", e);
            return null;
        }
    }

    private static String zzZ(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        String string = bundle.getString("com.google.android.gms.ads.APPLICATION_ID");
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        if (string.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$") || string.matches("^/\\d+~.+$")) {
            return string;
        }
        return "";
    }

    public static int zza(int i) {
        if (i >= 5000) {
            return i;
        }
        if (i <= 0) {
            return 60000;
        }
        zzm.zzj("HTTP timeout too low: " + i + " milliseconds. Reverting to default timeout: 60000 milliseconds.");
        return 60000;
    }

    private static boolean zzaa(String str, AtomicReference atomicReference, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Pattern pattern = (Pattern) atomicReference.get();
            if (pattern == null || !str2.equals(pattern.pattern())) {
                pattern = Pattern.compile(str2);
                atomicReference.set(pattern);
            }
            return pattern.matcher(str).matches();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    private static final void zzab(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable unused) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    private static final String zzac(Context context, String str) {
        String str2;
        String str3;
        if (str == null) {
            return zzq();
        }
        try {
            zzck zza2 = zzck.zza();
            if (TextUtils.isEmpty(zza2.zza)) {
                if (ClientLibraryUtils.isPackageSide()) {
                    str3 = (String) zzch.zza(context, new zzci(context));
                } else {
                    str3 = (String) zzch.zza(context, new zzcj(GooglePlayServicesUtilLight.getRemoteContext(context), context));
                }
                zza2.zza = str3;
            }
            str2 = zza2.zza;
        } catch (Exception unused) {
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = WebSettings.getDefaultUserAgent(context);
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = zzq();
        }
        String str4 = str2 + " (Mobile; " + str;
        try {
            if (Wrappers.packageManager(context).isCallerInstantApp()) {
                str4 = str4 + ";aia";
            }
        } catch (Exception e) {
            zzu.zzo().zzw(e, "AdUtil.getUserAgent");
        }
        return str4.concat(")");
    }

    public static List zzd() {
        zzbeg zzbeg = zzbep.zza;
        List<String> zzb2 = zzba.zza().zzb();
        ArrayList arrayList = new ArrayList();
        for (String zzd2 : zzb2) {
            for (String valueOf : zzfyt.zzc(zzfxr.zzc(',')).zzd(zzd2)) {
                try {
                    arrayList.add(Long.valueOf(valueOf));
                } catch (NumberFormatException unused) {
                    zze.zza("Experiment ID is not a number");
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:8:0x0016 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean zzn(android.view.View r2) {
        /*
            android.view.View r2 = r2.getRootView()
            r0 = 0
            if (r2 != 0) goto L_0x0009
        L_0x0007:
            r2 = r0
            goto L_0x0013
        L_0x0009:
            android.content.Context r2 = r2.getContext()
            boolean r1 = r2 instanceof android.app.Activity
            if (r1 == 0) goto L_0x0007
            android.app.Activity r2 = (android.app.Activity) r2
        L_0x0013:
            r1 = 0
            if (r2 != 0) goto L_0x0017
            return r1
        L_0x0017:
            android.view.Window r2 = r2.getWindow()
            if (r2 != 0) goto L_0x001e
            goto L_0x0022
        L_0x001e:
            android.view.WindowManager$LayoutParams r0 = r2.getAttributes()
        L_0x0022:
            if (r0 == 0) goto L_0x002d
            int r2 = r0.flags
            r0 = 524288(0x80000, float:7.34684E-40)
            r2 = r2 & r0
            if (r2 == 0) goto L_0x002d
            r2 = 1
            return r2
        L_0x002d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzt.zzn(android.view.View):boolean");
    }

    public static final void zzo(Context context, Intent intent) {
        if (intent != null) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder(CustomTabsIntent.EXTRA_SESSION, (IBinder) null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    public static final String zzp(Context context) throws RemoteException {
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        return zzZ(zzY(context));
    }

    static final String zzq() {
        StringBuilder sb = new StringBuilder(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build.VERSION.RELEASE != null) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append(VectorFormat.DEFAULT_SEPARATOR);
        sb.append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append(VectorFormat.DEFAULT_SEPARATOR);
            sb.append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/");
                sb.append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }

    public static final String zzr() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        return str + " " + str2;
    }

    public static final Integer zzs(Context context) {
        Object systemService = context.getSystemService("display");
        if (systemService instanceof DisplayManager) {
            return Integer.valueOf(((DisplayManager) systemService).getDisplays().length);
        }
        return null;
    }

    public static final DisplayMetrics zzt(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    protected static final int[] zzu() {
        return new int[]{0, 0};
    }

    public static final Map zzv(String str) {
        HashMap hashMap = new HashMap();
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                HashSet hashSet = new HashSet();
                JSONArray optJSONArray = jSONObject.optJSONArray(next);
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        String optString = optJSONArray.optString(i);
                        if (optString != null) {
                            hashSet.add(optString);
                        }
                    }
                    hashMap.put(next, hashSet);
                }
            }
            return hashMap;
        } catch (JSONException e) {
            zzu.zzo().zzw(e, "AdUtil.getMapOfFileNamesToKeysFromJsonString");
            return hashMap;
        }
    }

    public static final long zzw(View view) {
        float f;
        int i;
        float f2 = Float.MAX_VALUE;
        Object obj = view;
        do {
            f = 0.0f;
            if (!(obj instanceof View)) {
                break;
            }
            View view2 = (View) obj;
            f2 = Math.min(f2, view2.getAlpha());
            i = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
            obj = view2.getParent();
        } while (i > 0);
        if (f2 >= 0.0f) {
            f = f2;
        }
        return (long) Math.round(f * 100.0f);
    }

    public static final WebResourceResponse zzx(Context context, String str, String str2) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", zzu.zzp().zzc(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new zzbq(context).zzb(0, str2, hashMap, (byte[]) null).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse(FastJsonJsonView.DEFAULT_JSONP_CONTENT_TYPE, "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException | InterruptedException | ExecutionException | TimeoutException e) {
            zzm.zzk("Could not fetch MRAID JS.", e);
        }
        return null;
    }

    public static final String zzy() {
        Resources zze2 = zzu.zzo().zze();
        return zze2 != null ? zze2.getString(R.string.s7) : "Test Ad";
    }

    public static final zzbt zzz(Context context) {
        try {
            Object newInstance = context.getClassLoader().loadClass("com.google.android.gms.ads.internal.util.WorkManagerUtil").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            if (!(newInstance instanceof IBinder)) {
                zzm.zzg("Instantiated WorkManagerUtil not instance of IBinder.");
                return null;
            }
            IBinder iBinder = (IBinder) newInstance;
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.util.IWorkManagerUtil");
            if (queryLocalInterface instanceof zzbt) {
                return (zzbt) queryLocalInterface;
            }
            return new zzbr(iBinder);
        } catch (Exception e) {
            zzu.zzo().zzw(e, "Failed to instantiate WorkManagerUtil");
            return null;
        }
    }

    public final ListenableFuture zzb(Uri uri) {
        return zzgft.zzj(new zzn(uri), this.zzl);
    }

    public final String zzc(Context context, String str) {
        if (!((Boolean) zzba.zzc().zza(zzbep.zzlk)).booleanValue()) {
            synchronized (this.zzg) {
                String str2 = this.zzh;
                if (str2 != null) {
                    return str2;
                }
                String zzac = zzac(context, str);
                this.zzh = zzac;
                return zzac;
            }
        } else if (this.zzi != null) {
            return this.zzi;
        } else {
            this.zzi = zzac(context, str);
            return this.zzi;
        }
    }

    public final void zzf(Context context, String str, boolean z, HttpURLConnection httpURLConnection, boolean z2, int i) {
        int zza2 = zza(i);
        zzm.zzi("HTTP timeout: " + zza2 + " milliseconds.");
        httpURLConnection.setConnectTimeout(zza2);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(zza2);
        if (TextUtils.isEmpty(httpURLConnection.getRequestProperty("User-Agent"))) {
            httpURLConnection.setRequestProperty("User-Agent", zzc(context, str));
        }
        httpURLConnection.setUseCaches(false);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzg(Context context, String str, SharedPreferences sharedPreferences, String str2) {
        this.zzd.set(zzad.zzb(context, str));
    }

    public final void zzh(Context context, String str, String str2, Bundle bundle, boolean z) {
        zzu.zzp();
        bundle.putString("device", zzr());
        zzbeg zzbeg = zzbep.zza;
        bundle.putString("eids", TextUtils.join(",", zzba.zza().zza()));
        if (bundle.isEmpty()) {
            zzm.zze("Empty or null bundle.");
        } else {
            String str3 = (String) zzba.zzc().zza(zzbep.zzkA);
            if (!this.zze.getAndSet(true)) {
                this.zzd.set(zzad.zza(context, str3, new zzm(this, context, str3)));
            }
            bundle.putAll((Bundle) this.zzd.get());
        }
        zzay.zzb();
        zzf.zzx(context, str, "gmob-apps", bundle, true, new zzl(context, str));
    }

    public final boolean zzi(String str) {
        zzbeg zzbeg = zzbep.zzab;
        return zzaa(str, this.zzb, (String) zzba.zzc().zza(zzbeg));
    }

    public final boolean zzj(String str) {
        zzbeg zzbeg = zzbep.zzac;
        return zzaa(str, this.zzc, (String) zzba.zzc().zza(zzbeg));
    }

    public final boolean zzk(Context context) {
        if (this.zzk) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        zzbep.zza(context);
        if (!((Boolean) zzba.zzc().zza(zzbep.zzkW)).booleanValue() || Build.VERSION.SDK_INT < 33) {
            context.getApplicationContext().registerReceiver(new zzq(this, (zzp) null), intentFilter);
        } else {
            Intent unused = context.getApplicationContext().registerReceiver(new zzq(this, (zzp) null), intentFilter, 4);
        }
        this.zzk = true;
        return true;
    }

    public final boolean zzl(Context context) {
        if (this.zzj) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        zzbep.zza(context);
        if (!((Boolean) zzba.zzc().zza(zzbep.zzkW)).booleanValue() || Build.VERSION.SDK_INT < 33) {
            context.getApplicationContext().registerReceiver(new zzs(this, (zzr) null), intentFilter);
        } else {
            Intent unused = context.getApplicationContext().registerReceiver(new zzs(this, (zzr) null), intentFilter, 4);
        }
        this.zzj = true;
        return true;
    }

    public final int zzm(Context context, Uri uri) {
        int i;
        if (context == null) {
            zze.zza("Trying to open chrome custom tab on a null context");
            return 3;
        }
        if (!(context instanceof Activity)) {
            zze.zza("Chrome Custom Tabs can only work with Activity context.");
            i = 2;
        } else {
            i = 0;
        }
        if (true == ((Boolean) zzba.zzc().zza(zzbep.zzeC)).equals(zzba.zzc().zza(zzbep.zzeD))) {
            i = 9;
        }
        if (i != 0) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(268435456);
            context.startActivity(intent);
            return i;
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzeC)).booleanValue()) {
            zzbfm zzbfm = new zzbfm();
            zzbfm.zze(new zzo(this, zzbfm, context, uri));
            zzbfm.zzb((Activity) context);
        }
        if (!((Boolean) zzba.zzc().zza(zzbep.zzeD)).booleanValue()) {
            return 5;
        }
        CustomTabsIntent build = new CustomTabsIntent.Builder().build();
        build.intent.setPackage(zzhlh.zza(context));
        build.launchUrl(context, uri);
        return 5;
    }
}
