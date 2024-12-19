package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.client.zzay;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.util.zzch;
import com.google.android.gms.ads.internal.util.zzck;
import com.google.android.gms.ads.internal.util.zzco;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzk;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcic extends WebView implements DownloadListener, ViewTreeObserver.OnGlobalLayoutListener, zzchd {
    public static final /* synthetic */ int zza = 0;
    private final String zzA = "";
    private zzcif zzB;
    private boolean zzC;
    private boolean zzD;
    private zzbhj zzE;
    private zzbhh zzF;
    private zzbca zzG;
    private int zzH;
    /* access modifiers changed from: private */
    public int zzI;
    private zzbfb zzJ;
    private final zzbfb zzK;
    private zzbfb zzL;
    private final zzbfc zzM;
    private int zzN;
    private zzm zzO;
    private boolean zzP;
    private final zzco zzQ;
    private int zzR = -1;
    private int zzS = -1;
    private int zzT = -1;
    private int zzU = -1;
    private Map zzV;
    private final WindowManager zzW;
    private final zzbdm zzX;
    private boolean zzY;
    private final zzciw zzb;
    private final zzaxd zzc;
    private final zzfhs zzd;
    private final zzbfs zze;
    private final VersionInfoParcel zzf;
    private com.google.android.gms.ads.internal.zzm zzg;
    private final zza zzh;
    private final DisplayMetrics zzi;
    private final float zzj;
    private zzfgt zzk;
    private zzfgw zzl;
    private boolean zzm = false;
    private boolean zzn = false;
    private zzchl zzo;
    private zzm zzp;
    private zzehg zzq;
    private zzehe zzr;
    private zzcix zzs;
    private final String zzt;
    private boolean zzu;
    private boolean zzv;
    private boolean zzw;
    private boolean zzx;
    private Boolean zzy;
    private boolean zzz = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected zzcic(zzciw zzciw, zzcix zzcix, String str, boolean z, boolean z2, zzaxd zzaxd, zzbfs zzbfs, VersionInfoParcel versionInfoParcel, zzbfe zzbfe, com.google.android.gms.ads.internal.zzm zzm2, zza zza2, zzbdm zzbdm, zzfgt zzfgt, zzfgw zzfgw, zzfhs zzfhs) {
        super(zzciw);
        zzfgw zzfgw2;
        VersionInfoParcel versionInfoParcel2 = versionInfoParcel;
        this.zzb = zzciw;
        this.zzs = zzcix;
        this.zzt = str;
        this.zzw = z;
        this.zzc = zzaxd;
        this.zzd = zzfhs;
        this.zze = zzbfs;
        this.zzf = versionInfoParcel2;
        this.zzg = zzm2;
        this.zzh = zza2;
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        this.zzW = windowManager;
        zzu.zzp();
        DisplayMetrics zzt2 = zzt.zzt(windowManager);
        this.zzi = zzt2;
        this.zzj = zzt2.density;
        this.zzX = zzbdm;
        this.zzk = zzfgt;
        this.zzl = zzfgw;
        this.zzQ = new zzco(zzciw.zza(), this, this, (ViewTreeObserver.OnScrollChangedListener) null);
        this.zzY = false;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            com.google.android.gms.ads.internal.util.client.zzm.zzh("Unable to enable Javascript.", e);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (((Boolean) zzba.zzc().zza(zzbep.zzlF)).booleanValue()) {
            settings.setMixedContentMode(1);
        } else {
            settings.setMixedContentMode(2);
        }
        settings.setUserAgentString(zzu.zzp().zzc(zzciw, versionInfoParcel2.afmaVersion));
        zzu.zzp();
        Context context = getContext();
        zzch.zza(context, new zzk(settings, context));
        settings.setAllowFileAccessFromFileURLs(false);
        settings.setAllowUniversalAccessFromFileURLs(false);
        settings.setMediaPlaybackRequiresUserGesture(false);
        setDownloadListener(this);
        zzba();
        addJavascriptInterface(new zzcij(this, new zzcii(this)), "googleAdsJsInterface");
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        zzbi();
        zzbfc zzbfc = new zzbfc(new zzbfe(true, "make_wv", this.zzt));
        this.zzM = zzbfc;
        zzbfc.zza().zzc((zzbfe) null);
        if (!(!((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() || (zzfgw2 = this.zzl) == null || zzfgw2.zzb == null)) {
            zzbfc.zza().zzd("gqi", this.zzl.zzb);
        }
        zzbfc.zza();
        zzbfb zzf2 = zzbfe.zzf();
        this.zzK = zzf2;
        zzbfc.zzb("native:view_create", zzf2);
        this.zzL = null;
        this.zzJ = null;
        zzck.zza().zzb(zzciw);
        zzu.zzo().zzt();
    }

    private final synchronized void zzba() {
        zzfgt zzfgt = this.zzk;
        if (zzfgt != null) {
            if (zzfgt.zzan) {
                com.google.android.gms.ads.internal.util.client.zzm.zze("Disabling hardware acceleration on an overlay.");
                zzbc();
                return;
            }
        }
        if (!this.zzw) {
            if (!this.zzs.zzi()) {
                com.google.android.gms.ads.internal.util.client.zzm.zze("Enabling hardware acceleration on an AdView.");
                zzbe();
                return;
            }
        }
        com.google.android.gms.ads.internal.util.client.zzm.zze("Enabling hardware acceleration on an overlay.");
        zzbe();
    }

    private final synchronized void zzbb() {
        if (!this.zzP) {
            this.zzP = true;
            zzu.zzo().zzr();
        }
    }

    private final synchronized void zzbc() {
        if (!this.zzx) {
            setLayerType(1, (Paint) null);
        }
        this.zzx = true;
    }

    private final void zzbd(boolean z) {
        String str;
        HashMap hashMap = new HashMap();
        if (true != z) {
            str = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        hashMap.put("isVisible", str);
        zzd("onAdVisibilityChanged", hashMap);
    }

    private final synchronized void zzbe() {
        if (this.zzx) {
            setLayerType(0, (Paint) null);
        }
        this.zzx = false;
    }

    private final synchronized void zzbf(String str) {
        try {
            if (((Boolean) zzba.zzc().zza(zzbep.zzll)).booleanValue()) {
                zzt.zza.post(new zzchx(this, "about:blank"));
            } else {
                super.loadUrl("about:blank");
            }
        } catch (Throwable th) {
            zzu.zzo().zzw(th, "AdWebViewImpl.loadUrlUnsafe");
            com.google.android.gms.ads.internal.util.client.zzm.zzk("Could not call loadUrl in destroy(). ", th);
        }
    }

    private final void zzbg() {
        zzbew.zza(this.zzM.zza(), this.zzK, "aeh2");
    }

    private final synchronized void zzbh() {
        Map map = this.zzV;
        if (map != null) {
            for (zzcfp release : map.values()) {
                release.release();
            }
        }
        this.zzV = null;
    }

    private final void zzbi() {
        zzbfc zzbfc = this.zzM;
        if (zzbfc != null) {
            zzbfe zza2 = zzbfc.zza();
            zzbeu zzg2 = zzu.zzo().zzg();
            if (zzg2 != null) {
                zzg2.zzf(zza2);
            }
        }
    }

    private final synchronized void zzbj() {
        Boolean zzl2 = zzu.zzo().zzl();
        this.zzy = zzl2;
        if (zzl2 == null) {
            try {
                evaluateJavascript("(function(){})()", (ValueCallback) null);
                zzaY(true);
            } catch (IllegalStateException unused) {
                zzaY(false);
            }
        }
    }

    public final synchronized void destroy() {
        zzbi();
        this.zzQ.zza();
        zzm zzm2 = this.zzp;
        if (zzm2 != null) {
            zzm2.zzb();
            this.zzp.zzm();
            this.zzp = null;
        }
        this.zzq = null;
        this.zzr = null;
        this.zzo.zzh();
        this.zzG = null;
        this.zzg = null;
        setOnClickListener((View.OnClickListener) null);
        setOnTouchListener((View.OnTouchListener) null);
        if (!this.zzv) {
            zzu.zzy().zzd(this);
            zzbh();
            this.zzv = true;
            if (((Boolean) zzba.zzc().zza(zzbep.zzkN)).booleanValue()) {
                zze.zza("Initiating WebView self destruct sequence in 3...");
                zze.zza("Loading blank page in WebView, 2...");
                zzbf("about:blank");
                return;
            }
            zze.zza("Destroying the WebView immediately...");
            zzX();
        }
    }

    public final synchronized void evaluateJavascript(String str, ValueCallback valueCallback) {
        if (zzaE()) {
            com.google.android.gms.ads.internal.util.client.zzm.zzl("#004 The webview is destroyed. Ignoring action.", (Throwable) null);
            if (valueCallback != null) {
                valueCallback.onReceiveValue((Object) null);
                return;
            }
            return;
        }
        if (!((Boolean) zzba.zzc().zza(zzbep.zzkO)).booleanValue() || Looper.getMainLooper().getThread() == Thread.currentThread()) {
            super.evaluateJavascript(str, valueCallback);
        } else {
            zzcci.zze.zza(new zzchw(this, str, valueCallback));
        }
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.zzv) {
                    this.zzo.zzh();
                    zzu.zzy().zzd(this);
                    zzbh();
                    zzbb();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!zzaE()) {
            super.loadData(str, str2, str3);
        } else {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!zzaE()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!zzaE()) {
            try {
                if (((Boolean) zzba.zzc().zza(zzbep.zzll)).booleanValue()) {
                    zzt.zza.post(new zzchz(this, str));
                } else {
                    super.loadUrl(str);
                }
            } catch (Throwable th) {
                zzu.zzo().zzw(th, "AdWebViewImpl.loadUrl");
                com.google.android.gms.ads.internal.util.client.zzm.zzk("Could not call loadUrl. ", th);
            }
        } else {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final void onAdClicked() {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.onAdClicked();
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!zzaE()) {
            this.zzQ.zzc();
        }
        if (this.zzY) {
            onResume();
            this.zzY = false;
        }
        boolean z = this.zzC;
        zzchl zzchl = this.zzo;
        if (zzchl != null && zzchl.zzQ()) {
            if (!this.zzD) {
                this.zzo.zza();
                this.zzo.zzb();
                this.zzD = true;
            }
            zzaZ();
            z = true;
        }
        zzbd(z);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        zzchl zzchl;
        synchronized (this) {
            if (!zzaE()) {
                this.zzQ.zzd();
            }
            super.onDetachedFromWindow();
            if (this.zzD && (zzchl = this.zzo) != null && zzchl.zzQ() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                this.zzo.zza();
                this.zzo.zzb();
                this.zzD = false;
            }
        }
        zzbd(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            if (((Boolean) zzba.zzc().zza(zzbep.zzkZ)).booleanValue() && getContext() != null) {
                intent.setPackage(getContext().getPackageName());
            }
            zzu.zzp();
            zzt.zzT(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            com.google.android.gms.ads.internal.util.client.zzm.zze("Couldn't find an Activity to view url/mimetype: " + str + " / " + str4);
            zzu.zzo().zzw(e, "AdWebViewImpl.onDownloadStart: ".concat(String.valueOf(str)));
        }
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        if (!zzaE()) {
            super.onDraw(canvas);
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (motionEvent.getActionMasked() == 8) {
            if (axisValue > 0.0f && !canScrollVertically(-1)) {
                return false;
            }
            if (axisValue < 0.0f && !canScrollVertically(1)) {
                return false;
            }
            if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                return false;
            }
            if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean zzaZ = zzaZ();
        zzm zzL2 = zzL();
        if (zzL2 != null && zzaZ) {
            zzL2.zzn();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01b7 A[SYNTHETIC, Splitter:B:114:0x01b7] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r10, int r11) {
        /*
            r9 = this;
            java.lang.String r0 = "Not enough space to show ad. Needs "
            monitor-enter(r9)
            boolean r1 = r9.zzaE()     // Catch:{ all -> 0x01dd }
            r2 = 0
            if (r1 == 0) goto L_0x000f
            r9.setMeasuredDimension(r2, r2)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x000f:
            boolean r1 = r9.isInEditMode()     // Catch:{ all -> 0x01dd }
            if (r1 != 0) goto L_0x01d8
            boolean r1 = r9.zzw     // Catch:{ all -> 0x01dd }
            if (r1 != 0) goto L_0x01d8
            com.google.android.gms.internal.ads.zzcix r1 = r9.zzs     // Catch:{ all -> 0x01dd }
            boolean r1 = r1.zzf()     // Catch:{ all -> 0x01dd }
            if (r1 == 0) goto L_0x0023
            goto L_0x01d8
        L_0x0023:
            com.google.android.gms.internal.ads.zzcix r1 = r9.zzs     // Catch:{ all -> 0x01dd }
            boolean r1 = r1.zzh()     // Catch:{ all -> 0x01dd }
            if (r1 == 0) goto L_0x0030
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x0030:
            com.google.android.gms.internal.ads.zzcix r1 = r9.zzs     // Catch:{ all -> 0x01dd }
            boolean r1 = r1.zzj()     // Catch:{ all -> 0x01dd }
            if (r1 == 0) goto L_0x0099
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzdQ     // Catch:{ all -> 0x01dd }
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x01dd }
            java.lang.Object r0 = r1.zza(r0)     // Catch:{ all -> 0x01dd }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01dd }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01dd }
            if (r0 == 0) goto L_0x004f
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x004f:
            com.google.android.gms.internal.ads.zzcif r0 = r9.zzq()     // Catch:{ all -> 0x01dd }
            r1 = 0
            if (r0 == 0) goto L_0x005b
            float r0 = r0.zze()     // Catch:{ all -> 0x01dd }
            goto L_0x005c
        L_0x005b:
            r0 = r1
        L_0x005c:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x0065
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x0065:
            int r10 = android.view.View.MeasureSpec.getSize(r10)     // Catch:{ all -> 0x01dd }
            int r11 = android.view.View.MeasureSpec.getSize(r11)     // Catch:{ all -> 0x01dd }
            float r1 = (float) r11     // Catch:{ all -> 0x01dd }
            float r1 = r1 * r0
            float r3 = (float) r10     // Catch:{ all -> 0x01dd }
            float r3 = r3 / r0
            int r3 = (int) r3     // Catch:{ all -> 0x01dd }
            if (r11 != 0) goto L_0x007d
            if (r3 == 0) goto L_0x007c
            float r11 = (float) r3     // Catch:{ all -> 0x01dd }
            float r11 = r11 * r0
            int r11 = (int) r11     // Catch:{ all -> 0x01dd }
            r2 = r10
            r10 = r3
            goto L_0x008c
        L_0x007c:
            r11 = r2
        L_0x007d:
            int r1 = (int) r1     // Catch:{ all -> 0x01dd }
            if (r10 != 0) goto L_0x0089
            if (r1 == 0) goto L_0x008a
            float r10 = (float) r1     // Catch:{ all -> 0x01dd }
            float r10 = r10 / r0
            int r3 = (int) r10     // Catch:{ all -> 0x01dd }
            r10 = r11
            r11 = r1
            r2 = r11
            goto L_0x008c
        L_0x0089:
            r2 = r10
        L_0x008a:
            r10 = r11
            r11 = r1
        L_0x008c:
            int r11 = java.lang.Math.min(r11, r2)     // Catch:{ all -> 0x01dd }
            int r10 = java.lang.Math.min(r3, r10)     // Catch:{ all -> 0x01dd }
            r9.setMeasuredDimension(r11, r10)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x0099:
            com.google.android.gms.internal.ads.zzcix r1 = r9.zzs     // Catch:{ all -> 0x01dd }
            boolean r1 = r1.zzg()     // Catch:{ all -> 0x01dd }
            if (r1 == 0) goto L_0x00e1
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzdV     // Catch:{ all -> 0x01dd }
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x01dd }
            java.lang.Object r0 = r1.zza(r0)     // Catch:{ all -> 0x01dd }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01dd }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01dd }
            if (r0 == 0) goto L_0x00b8
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x00b8:
            com.google.android.gms.internal.ads.zzcia r0 = new com.google.android.gms.internal.ads.zzcia     // Catch:{ all -> 0x01dd }
            r0.<init>(r9)     // Catch:{ all -> 0x01dd }
            java.lang.String r1 = "/contentHeight"
            r9.zzag(r1, r0)     // Catch:{ all -> 0x01dd }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r9.zzaT(r0)     // Catch:{ all -> 0x01dd }
            android.util.DisplayMetrics r0 = r9.zzi     // Catch:{ all -> 0x01dd }
            float r0 = r0.density     // Catch:{ all -> 0x01dd }
            int r10 = android.view.View.MeasureSpec.getSize(r10)     // Catch:{ all -> 0x01dd }
            int r1 = r9.zzI     // Catch:{ all -> 0x01dd }
            r2 = -1
            if (r1 == r2) goto L_0x00d8
            float r11 = (float) r1     // Catch:{ all -> 0x01dd }
            float r11 = r11 * r0
            int r11 = (int) r11     // Catch:{ all -> 0x01dd }
            goto L_0x00dc
        L_0x00d8:
            int r11 = android.view.View.MeasureSpec.getSize(r11)     // Catch:{ all -> 0x01dd }
        L_0x00dc:
            r9.setMeasuredDimension(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x00e1:
            com.google.android.gms.internal.ads.zzcix r1 = r9.zzs     // Catch:{ all -> 0x01dd }
            boolean r1 = r1.zzi()     // Catch:{ all -> 0x01dd }
            if (r1 == 0) goto L_0x00f4
            android.util.DisplayMetrics r10 = r9.zzi     // Catch:{ all -> 0x01dd }
            int r11 = r10.widthPixels     // Catch:{ all -> 0x01dd }
            int r10 = r10.heightPixels     // Catch:{ all -> 0x01dd }
            r9.setMeasuredDimension(r11, r10)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x00f4:
            int r1 = android.view.View.MeasureSpec.getMode(r10)     // Catch:{ all -> 0x01dd }
            int r10 = android.view.View.MeasureSpec.getSize(r10)     // Catch:{ all -> 0x01dd }
            int r3 = android.view.View.MeasureSpec.getMode(r11)     // Catch:{ all -> 0x01dd }
            int r11 = android.view.View.MeasureSpec.getSize(r11)     // Catch:{ all -> 0x01dd }
            r4 = 2147483647(0x7fffffff, float:NaN)
            r5 = 1073741824(0x40000000, float:2.0)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r1 == r6) goto L_0x0112
            if (r1 != r5) goto L_0x0110
            goto L_0x0112
        L_0x0110:
            r1 = r4
            goto L_0x0113
        L_0x0112:
            r1 = r10
        L_0x0113:
            if (r3 == r6) goto L_0x0117
            if (r3 != r5) goto L_0x0118
        L_0x0117:
            r4 = r11
        L_0x0118:
            com.google.android.gms.internal.ads.zzcix r3 = r9.zzs     // Catch:{ all -> 0x01dd }
            int r5 = r3.zzb     // Catch:{ all -> 0x01dd }
            r6 = 1
            if (r5 > r1) goto L_0x0126
            int r3 = r3.zza     // Catch:{ all -> 0x01dd }
            if (r3 <= r4) goto L_0x0124
            goto L_0x0126
        L_0x0124:
            r3 = r2
            goto L_0x0127
        L_0x0126:
            r3 = r6
        L_0x0127:
            com.google.android.gms.internal.ads.zzbeg r5 = com.google.android.gms.internal.ads.zzbep.zzfy     // Catch:{ all -> 0x01dd }
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x01dd }
            java.lang.Object r5 = r7.zza(r5)     // Catch:{ all -> 0x01dd }
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch:{ all -> 0x01dd }
            boolean r5 = r5.booleanValue()     // Catch:{ all -> 0x01dd }
            if (r5 == 0) goto L_0x0155
            com.google.android.gms.internal.ads.zzcix r5 = r9.zzs     // Catch:{ all -> 0x01dd }
            int r7 = r5.zzb     // Catch:{ all -> 0x01dd }
            float r7 = (float) r7     // Catch:{ all -> 0x01dd }
            float r8 = r9.zzj     // Catch:{ all -> 0x01dd }
            float r1 = (float) r1     // Catch:{ all -> 0x01dd }
            float r7 = r7 / r8
            float r1 = r1 / r8
            int r1 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0153
            int r1 = r5.zza     // Catch:{ all -> 0x01dd }
            float r1 = (float) r1     // Catch:{ all -> 0x01dd }
            float r1 = r1 / r8
            float r4 = (float) r4     // Catch:{ all -> 0x01dd }
            float r4 = r4 / r8
            int r1 = (r1 > r4 ? 1 : (r1 == r4 ? 0 : -1))
            if (r1 > 0) goto L_0x0153
            r1 = r6
            goto L_0x0154
        L_0x0153:
            r1 = r2
        L_0x0154:
            r3 = r3 & r1
        L_0x0155:
            r1 = 8
            if (r3 == 0) goto L_0x01b7
            com.google.android.gms.internal.ads.zzcix r3 = r9.zzs     // Catch:{ all -> 0x01dd }
            int r4 = r3.zzb     // Catch:{ all -> 0x01dd }
            float r4 = (float) r4     // Catch:{ all -> 0x01dd }
            float r5 = r9.zzj     // Catch:{ all -> 0x01dd }
            int r3 = r3.zza     // Catch:{ all -> 0x01dd }
            float r3 = (float) r3     // Catch:{ all -> 0x01dd }
            float r10 = (float) r10     // Catch:{ all -> 0x01dd }
            float r11 = (float) r11     // Catch:{ all -> 0x01dd }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x01dd }
            r7.<init>(r0)     // Catch:{ all -> 0x01dd }
            float r4 = r4 / r5
            int r0 = (int) r4     // Catch:{ all -> 0x01dd }
            r7.append(r0)     // Catch:{ all -> 0x01dd }
            java.lang.String r0 = "x"
            r7.append(r0)     // Catch:{ all -> 0x01dd }
            float r3 = r3 / r5
            int r0 = (int) r3     // Catch:{ all -> 0x01dd }
            r7.append(r0)     // Catch:{ all -> 0x01dd }
            java.lang.String r0 = " dp, but only has "
            r7.append(r0)     // Catch:{ all -> 0x01dd }
            float r10 = r10 / r5
            int r10 = (int) r10     // Catch:{ all -> 0x01dd }
            r7.append(r10)     // Catch:{ all -> 0x01dd }
            java.lang.String r10 = "x"
            r7.append(r10)     // Catch:{ all -> 0x01dd }
            float r11 = r11 / r5
            int r10 = (int) r11     // Catch:{ all -> 0x01dd }
            r7.append(r10)     // Catch:{ all -> 0x01dd }
            java.lang.String r10 = " dp."
            r7.append(r10)     // Catch:{ all -> 0x01dd }
            java.lang.String r10 = r7.toString()     // Catch:{ all -> 0x01dd }
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r10)     // Catch:{ all -> 0x01dd }
            int r10 = r9.getVisibility()     // Catch:{ all -> 0x01dd }
            if (r10 == r1) goto L_0x01a3
            r10 = 4
            r9.setVisibility(r10)     // Catch:{ all -> 0x01dd }
        L_0x01a3:
            r9.setMeasuredDimension(r2, r2)     // Catch:{ all -> 0x01dd }
            boolean r10 = r9.zzm     // Catch:{ all -> 0x01dd }
            if (r10 != 0) goto L_0x01b5
            com.google.android.gms.internal.ads.zzbdm r10 = r9.zzX     // Catch:{ all -> 0x01dd }
            com.google.android.gms.internal.ads.zzbdo r11 = com.google.android.gms.internal.ads.zzbdo.BANNER_SIZE_INVALID     // Catch:{ all -> 0x01dd }
            r10.zzb(r11)     // Catch:{ all -> 0x01dd }
            r9.zzm = r6     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x01b5:
            monitor-exit(r9)
            return
        L_0x01b7:
            int r10 = r9.getVisibility()     // Catch:{ all -> 0x01dd }
            if (r10 == r1) goto L_0x01c0
            r9.setVisibility(r2)     // Catch:{ all -> 0x01dd }
        L_0x01c0:
            boolean r10 = r9.zzn     // Catch:{ all -> 0x01dd }
            if (r10 != 0) goto L_0x01cd
            com.google.android.gms.internal.ads.zzbdm r10 = r9.zzX     // Catch:{ all -> 0x01dd }
            com.google.android.gms.internal.ads.zzbdo r11 = com.google.android.gms.internal.ads.zzbdo.BANNER_SIZE_VALID     // Catch:{ all -> 0x01dd }
            r10.zzb(r11)     // Catch:{ all -> 0x01dd }
            r9.zzn = r6     // Catch:{ all -> 0x01dd }
        L_0x01cd:
            com.google.android.gms.internal.ads.zzcix r10 = r9.zzs     // Catch:{ all -> 0x01dd }
            int r11 = r10.zzb     // Catch:{ all -> 0x01dd }
            int r10 = r10.zza     // Catch:{ all -> 0x01dd }
            r9.setMeasuredDimension(r11, r10)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x01d8:
            super.onMeasure(r10, r11)     // Catch:{ all -> 0x01dd }
            monitor-exit(r9)
            return
        L_0x01dd:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x01dd }
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcic.onMeasure(int, int):void");
    }

    public final void onPause() {
        if (!zzaE()) {
            try {
                super.onPause();
                if (((Boolean) zzba.zzc().zza(zzbep.zzmz)).booleanValue() && WebViewFeature.isFeatureSupported("MUTE_AUDIO")) {
                    com.google.android.gms.ads.internal.util.client.zzm.zze("Muting webview");
                    WebViewCompat.setAudioMuted(this, true);
                }
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.client.zzm.zzh("Could not pause webview.", e);
                if (((Boolean) zzba.zzc().zza(zzbep.zzmC)).booleanValue()) {
                    zzu.zzo().zzw(e, "AdWebViewImpl.onPause");
                }
            }
        }
    }

    public final void onResume() {
        if (!zzaE()) {
            try {
                super.onResume();
                if (((Boolean) zzba.zzc().zza(zzbep.zzmz)).booleanValue() && WebViewFeature.isFeatureSupported("MUTE_AUDIO")) {
                    com.google.android.gms.ads.internal.util.client.zzm.zze("Unmuting webview");
                    WebViewCompat.setAudioMuted(this, false);
                }
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.client.zzm.zzh("Could not resume webview.", e);
                if (((Boolean) zzba.zzc().zza(zzbep.zzmC)).booleanValue()) {
                    zzu.zzo().zzw(e, "AdWebViewImpl.onResume");
                }
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.zzo.zzQ() || this.zzo.zzO()) {
            zzaxd zzaxd = this.zzc;
            if (zzaxd != null) {
                zzaxd.zzd(motionEvent);
            }
            zzbfs zzbfs = this.zze;
            if (zzbfs != null) {
                zzbfs.zzb(motionEvent);
            }
        } else {
            synchronized (this) {
                zzbhj zzbhj = this.zzE;
                if (zzbhj != null) {
                    zzbhj.zzd(motionEvent);
                }
            }
        }
        if (zzaE()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzchl) {
            this.zzo = (zzchl) webViewClient;
        }
    }

    public final void stopLoading() {
        if (!zzaE()) {
            try {
                super.stopLoading();
            } catch (Exception e) {
                com.google.android.gms.ads.internal.util.client.zzm.zzh("Could not stop loading webview.", e);
            }
        }
    }

    public final synchronized void zzA(int i) {
        this.zzN = i;
    }

    public final void zzB(int i) {
    }

    public final synchronized void zzC(zzcif zzcif) {
        if (this.zzB != null) {
            com.google.android.gms.ads.internal.util.client.zzm.zzg("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzB = zzcif;
        }
    }

    public final zzfgt zzD() {
        return this.zzk;
    }

    public final Context zzE() {
        return this.zzb.zzb();
    }

    public final View zzF() {
        return this;
    }

    public final WebView zzG() {
        return this;
    }

    public final WebViewClient zzH() {
        return this.zzo;
    }

    public final zzaxd zzI() {
        return this.zzc;
    }

    public final synchronized zzbca zzJ() {
        return this.zzG;
    }

    public final synchronized zzbhj zzK() {
        return this.zzE;
    }

    public final synchronized zzm zzL() {
        return this.zzp;
    }

    public final synchronized zzm zzM() {
        return this.zzO;
    }

    public final /* synthetic */ zzciv zzN() {
        return this.zzo;
    }

    public final synchronized zzcix zzO() {
        return this.zzs;
    }

    public final synchronized zzehe zzP() {
        return this.zzr;
    }

    public final synchronized zzehg zzQ() {
        return this.zzq;
    }

    public final zzfgw zzR() {
        return this.zzl;
    }

    public final zzfhs zzS() {
        return this.zzd;
    }

    public final ListenableFuture zzT() {
        zzbfs zzbfs = this.zze;
        if (zzbfs == null) {
            return zzgft.zzh((Object) null);
        }
        return zzbfs.zza();
    }

    public final synchronized String zzU() {
        return this.zzt;
    }

    public final List zzV() {
        return new ArrayList();
    }

    public final void zzW(zzfgt zzfgt, zzfgw zzfgw) {
        this.zzk = zzfgt;
        this.zzl = zzfgw;
    }

    public final synchronized void zzX() {
        zze.zza("Destroying WebView!");
        zzbb();
        zzt.zza.post(new zzcib(this));
    }

    public final void zzY() {
        zzbg();
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzf.afmaVersion);
        zzd("onhide", hashMap);
    }

    public final void zzZ(int i) {
        if (i == 0) {
            zzbfc zzbfc = this.zzM;
            zzbew.zza(zzbfc.zza(), this.zzK, "aebb2");
        }
        zzbg();
        this.zzM.zza();
        this.zzM.zza().zzd("close_type", String.valueOf(i));
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzf.afmaVersion);
        zzd("onhide", hashMap);
    }

    public final void zza(String str) {
        throw null;
    }

    public final void zzaA(String str, Predicate predicate) {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.zzN(str, predicate);
        }
    }

    public final synchronized boolean zzaB() {
        return this.zzu;
    }

    public final synchronized boolean zzaC() {
        return this.zzH > 0;
    }

    public final boolean zzaD(boolean z, int i) {
        destroy();
        this.zzX.zzc(new zzchy(z, i));
        this.zzX.zzb(zzbdo.ANDROID_WEBVIEW_CRASH);
        return true;
    }

    public final synchronized boolean zzaE() {
        return this.zzv;
    }

    public final synchronized boolean zzaF() {
        return this.zzw;
    }

    public final boolean zzaG() {
        return false;
    }

    public final synchronized boolean zzaH() {
        return this.zzz;
    }

    public final void zzaJ(zzc zzc2, boolean z, boolean z2) {
        this.zzo.zzu(zzc2, z, z2);
    }

    public final void zzaK(String str, String str2, int i) {
        this.zzo.zzv(str, str2, 14);
    }

    public final void zzaL(boolean z, int i, boolean z2) {
        this.zzo.zzw(z, i, z2);
    }

    public final void zzaM(boolean z, int i, String str, String str2, boolean z2) {
        this.zzo.zzy(z, i, str, str2, z2);
    }

    public final void zzaN(boolean z, int i, String str, boolean z2, boolean z3) {
        this.zzo.zzz(z, i, str, z2, z3);
    }

    public final zzchl zzaO() {
        return this.zzo;
    }

    /* access modifiers changed from: package-private */
    public final synchronized Boolean zzaP() {
        return this.zzy;
    }

    /* access modifiers changed from: protected */
    public final synchronized void zzaS(String str, ValueCallback valueCallback) {
        if (!zzaE()) {
            evaluateJavascript(str, (ValueCallback) null);
        } else {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzaT(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (zzaP() == null) {
                zzbj();
            }
            if (zzaP().booleanValue()) {
                zzaS(str, (ValueCallback) null);
            } else {
                zzaX("javascript:".concat(str));
            }
        } else {
            zzaX("javascript:".concat(str));
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaU(String str, ValueCallback valueCallback) {
        super.evaluateJavascript(str, valueCallback);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaV(String str) {
        super.loadUrl(str);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzaW(String str) {
        super.loadUrl("about:blank");
    }

    /* access modifiers changed from: protected */
    public final synchronized void zzaX(String str) {
        if (!zzaE()) {
            loadUrl(str);
        } else {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzaY(Boolean bool) {
        synchronized (this) {
            this.zzy = bool;
        }
        zzu.zzo().zzy(bool);
    }

    public final boolean zzaZ() {
        int i;
        int i2;
        boolean z = false;
        if (this.zzo.zzP() || this.zzo.zzQ()) {
            zzay.zzb();
            DisplayMetrics displayMetrics = this.zzi;
            int zzw2 = zzf.zzw(displayMetrics, displayMetrics.widthPixels);
            zzay.zzb();
            DisplayMetrics displayMetrics2 = this.zzi;
            int zzw3 = zzf.zzw(displayMetrics2, displayMetrics2.heightPixels);
            Activity zza2 = this.zzb.zza();
            if (zza2 == null || zza2.getWindow() == null) {
                i2 = zzw2;
                i = zzw3;
            } else {
                zzu.zzp();
                int[] zzQ2 = zzt.zzQ(zza2);
                zzay.zzb();
                int zzw4 = zzf.zzw(this.zzi, zzQ2[0]);
                zzay.zzb();
                i = zzf.zzw(this.zzi, zzQ2[1]);
                i2 = zzw4;
            }
            int i3 = this.zzS;
            if (!(i3 == zzw2 && this.zzR == zzw3 && this.zzT == i2 && this.zzU == i)) {
                if (!(i3 == zzw2 && this.zzR == zzw3)) {
                    z = true;
                }
                this.zzS = zzw2;
                this.zzR = zzw3;
                this.zzT = i2;
                this.zzU = i;
                new zzbuj(this, "").zzj(zzw2, zzw3, i2, i, this.zzi.density, this.zzW.getDefaultDisplay().getRotation());
                return z;
            }
        }
        return false;
    }

    public final void zzaa() {
        if (this.zzJ == null) {
            zzbfc zzbfc = this.zzM;
            zzbew.zza(zzbfc.zza(), this.zzK, "aes2");
            this.zzM.zza();
            zzbfb zzf2 = zzbfe.zzf();
            this.zzJ = zzf2;
            this.zzM.zzb("native:view_show", zzf2);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("version", this.zzf.afmaVersion);
        zzd("onshow", hashMap);
    }

    public final void zzab() {
        throw null;
    }

    public final void zzac(boolean z) {
        this.zzo.zzi(z);
    }

    public final void zzad() {
        this.zzQ.zzb();
    }

    public final synchronized void zzae(String str, String str2, String str3) {
        String str4;
        if (!zzaE()) {
            String str5 = (String) zzba.zzc().zza(zzbep.zzO);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("version", str5);
                jSONObject.put("sdk", "Google Mobile Ads");
                jSONObject.put(RemoteConfigConstants.RequestFieldKey.SDK_VERSION, "12.4.51-000");
                str4 = "<script>Object.defineProperty(window,'MRAID_ENV',{get:function(){return " + jSONObject.toString() + "}});</script>";
            } catch (JSONException e) {
                com.google.android.gms.ads.internal.util.client.zzm.zzk("Unable to build MRAID_ENV", e);
                str4 = null;
            }
            super.loadDataWithBaseURL(str, zzcio.zzb(str2, str4), "text/html", "UTF-8", (String) null);
            return;
        }
        com.google.android.gms.ads.internal.util.client.zzm.zzj("#004 The webview is destroyed. Ignoring action.");
    }

    public final void zzaf() {
        if (this.zzL == null) {
            this.zzM.zza();
            zzbfb zzf2 = zzbfe.zzf();
            this.zzL = zzf2;
            this.zzM.zzb("native:view_load", zzf2);
        }
    }

    public final void zzag(String str, zzblp zzblp) {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.zzA(str, zzblp);
        }
    }

    public final void zzah() {
        throw null;
    }

    public final synchronized void zzai(zzm zzm2) {
        this.zzp = zzm2;
    }

    public final synchronized void zzaj(zzcix zzcix) {
        this.zzs = zzcix;
        requestLayout();
    }

    public final synchronized void zzak(zzbca zzbca) {
        this.zzG = zzbca;
    }

    public final synchronized void zzal(boolean z) {
        this.zzz = z;
    }

    public final void zzam() {
        setBackgroundColor(0);
    }

    public final void zzan(Context context) {
        this.zzb.setBaseContext(context);
        this.zzQ.zze(this.zzb.zza());
    }

    public final synchronized void zzao(boolean z) {
        zzm zzm2 = this.zzp;
        if (zzm2 != null) {
            zzm2.zzy(this.zzo.zzP(), z);
        } else {
            this.zzu = z;
        }
    }

    public final synchronized void zzap(zzbhh zzbhh) {
        this.zzF = zzbhh;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzaq(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.zzw     // Catch:{ all -> 0x003a }
            r2.zzw = r3     // Catch:{ all -> 0x003a }
            r2.zzba()     // Catch:{ all -> 0x003a }
            if (r3 == r0) goto L_0x0038
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzP     // Catch:{ all -> 0x003a }
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x003a }
            java.lang.Object r0 = r1.zza(r0)     // Catch:{ all -> 0x003a }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x003a }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0024
            com.google.android.gms.internal.ads.zzcix r0 = r2.zzs     // Catch:{ all -> 0x003a }
            boolean r0 = r0.zzi()     // Catch:{ all -> 0x003a }
            if (r0 != 0) goto L_0x0038
        L_0x0024:
            java.lang.String r0 = ""
            com.google.android.gms.internal.ads.zzbuj r1 = new com.google.android.gms.internal.ads.zzbuj     // Catch:{ all -> 0x003a }
            r1.<init>(r2, r0)     // Catch:{ all -> 0x003a }
            r0 = 1
            if (r0 == r3) goto L_0x0031
            java.lang.String r3 = "default"
            goto L_0x0033
        L_0x0031:
            java.lang.String r3 = "expanded"
        L_0x0033:
            r1.zzl(r3)     // Catch:{ all -> 0x003a }
            monitor-exit(r2)
            return
        L_0x0038:
            monitor-exit(r2)
            return
        L_0x003a:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcic.zzaq(boolean):void");
    }

    public final synchronized void zzar(zzbhj zzbhj) {
        this.zzE = zzbhj;
    }

    public final synchronized void zzas(zzehe zzehe) {
        this.zzr = zzehe;
    }

    public final synchronized void zzat(zzehg zzehg) {
        this.zzq = zzehg;
    }

    public final synchronized void zzau(int i) {
        zzm zzm2 = this.zzp;
        if (zzm2 != null) {
            zzm2.zzA(i);
        }
    }

    public final void zzav(boolean z) {
        this.zzY = true;
    }

    public final synchronized void zzaw(zzm zzm2) {
        this.zzO = zzm2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0016, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzax(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            int r0 = r2.zzH     // Catch:{ all -> 0x0017 }
            r1 = 1
            if (r1 == r3) goto L_0x0007
            r1 = -1
        L_0x0007:
            int r0 = r0 + r1
            r2.zzH = r0     // Catch:{ all -> 0x0017 }
            if (r0 > 0) goto L_0x0015
            com.google.android.gms.ads.internal.overlay.zzm r3 = r2.zzp     // Catch:{ all -> 0x0017 }
            if (r3 == 0) goto L_0x0015
            r3.zzE()     // Catch:{ all -> 0x0017 }
            monitor-exit(r2)
            return
        L_0x0015:
            monitor-exit(r2)
            return
        L_0x0017:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0017 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcic.zzax(boolean):void");
    }

    public final synchronized void zzay(boolean z) {
        if (z) {
            setBackgroundColor(0);
        }
        zzm zzm2 = this.zzp;
        if (zzm2 != null) {
            zzm2.zzB(z);
        }
    }

    public final void zzaz(String str, zzblp zzblp) {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.zzM(str, zzblp);
        }
    }

    public final void zzb(String str, String str2) {
        zzaT(str + "(" + str2 + ");");
    }

    public final void zzd(String str, Map map) {
        try {
            zze(str, zzay.zzb().zzj(map));
        } catch (JSONException unused) {
            com.google.android.gms.ads.internal.util.client.zzm.zzj("Could not convert parameters to JSON.");
        }
    }

    public final void zzdG() {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.zzdG();
        }
    }

    public final void zzdf() {
        zzchl zzchl = this.zzo;
        if (zzchl != null) {
            zzchl.zzdf();
        }
    }

    public final synchronized void zzdg() {
        com.google.android.gms.ads.internal.zzm zzm2 = this.zzg;
        if (zzm2 != null) {
            zzm2.zzdg();
        }
    }

    public final synchronized void zzdh() {
        com.google.android.gms.ads.internal.zzm zzm2 = this.zzg;
        if (zzm2 != null) {
            zzm2.zzdh();
        }
    }

    public final synchronized String zzdi() {
        return this.zzA;
    }

    public final void zzdp(zzbam zzbam) {
        synchronized (this) {
            this.zzC = zzbam.zzj;
        }
        zzbd(zzbam.zzj);
    }

    public final void zze(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("',");
        sb.append(jSONObject2);
        sb.append(");");
        com.google.android.gms.ads.internal.util.client.zzm.zze("Dispatching AFMA event: ".concat(sb.toString()));
        zzaT(sb.toString());
    }

    public final synchronized int zzf() {
        return this.zzN;
    }

    public final int zzg() {
        return getMeasuredHeight();
    }

    public final int zzh() {
        return getMeasuredWidth();
    }

    public final Activity zzi() {
        return this.zzb.zza();
    }

    public final zza zzj() {
        return this.zzh;
    }

    public final zzbfb zzk() {
        return this.zzK;
    }

    public final void zzl(String str, JSONObject jSONObject) {
        zzb(str, jSONObject.toString());
    }

    public final zzbfc zzm() {
        return this.zzM;
    }

    public final VersionInfoParcel zzn() {
        return this.zzf;
    }

    public final zzcdt zzo() {
        return null;
    }

    public final synchronized zzcfp zzp(String str) {
        Map map = this.zzV;
        if (map == null) {
            return null;
        }
        return (zzcfp) map.get(str);
    }

    public final synchronized zzcif zzq() {
        return this.zzB;
    }

    public final synchronized String zzr() {
        zzfgw zzfgw = this.zzl;
        if (zzfgw == null) {
            return null;
        }
        return zzfgw.zzb;
    }

    public final synchronized void zzt(String str, zzcfp zzcfp) {
        if (this.zzV == null) {
            this.zzV = new HashMap();
        }
        this.zzV.put(str, zzcfp);
    }

    public final void zzu() {
        zzm zzL2 = zzL();
        if (zzL2 != null) {
            zzL2.zzd();
        }
    }

    public final void zzv(boolean z, long j) {
        String str;
        HashMap hashMap = new HashMap(2);
        if (true != z) {
            str = SessionDescription.SUPPORTED_SDP_VERSION;
        } else {
            str = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        hashMap.put(FirebaseAnalytics.Param.SUCCESS, str);
        hashMap.put(TypedValues.TransitionType.S_DURATION, Long.toString(j));
        zzd("onCacheAccessComplete", hashMap);
    }

    public final synchronized void zzw() {
        zzbhh zzbhh = this.zzF;
        if (zzbhh != null) {
            zzt.zza.post(new zzdpr((zzdpt) zzbhh));
        }
    }

    public final void zzx(int i) {
    }

    public final void zzy(int i) {
    }

    public final void zzz(boolean z) {
        this.zzo.zzD(false);
    }
}
