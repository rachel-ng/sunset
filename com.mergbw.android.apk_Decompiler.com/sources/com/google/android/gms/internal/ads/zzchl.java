package com.google.android.gms.internal.ads;

import android.net.TrafficStats;
import android.net.Uri;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.core.view.ViewCompat;
import androidx.webkit.ProxyConfig;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzaa;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzl;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.Predicate;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzchl extends WebViewClient implements zzciv {
    public static final /* synthetic */ int zzb = 0;
    private boolean zzA;
    private int zzB;
    private boolean zzC;
    private final HashSet zzD;
    private final zzegk zzE;
    private View.OnAttachStateChangeListener zzF;
    protected zzcaf zza;
    private final zzchd zzc;
    private final zzbdm zzd;
    private final HashMap zze = new HashMap();
    private final Object zzf = new Object();
    private zza zzg;
    private zzp zzh;
    private zzcit zzi;
    private zzciu zzj;
    private zzbkf zzk;
    private zzbkh zzl;
    private zzdhi zzm;
    private boolean zzn;
    private boolean zzo;
    private int zzp = 0;
    private String zzq = "";
    private String zzr = "";
    private boolean zzs;
    private boolean zzt;
    private boolean zzu;
    private zzaa zzv;
    private zzbui zzw;
    private zzb zzx;
    private zzbud zzy;
    private boolean zzz;

    public zzchl(zzchd zzchd, zzbdm zzbdm, boolean z, zzbui zzbui, zzbud zzbud, zzegk zzegk) {
        this.zzd = zzbdm;
        this.zzc = zzchd;
        this.zzs = z;
        this.zzw = zzbui;
        this.zzy = null;
        this.zzD = new HashSet(Arrays.asList(((String) zzba.zzc().zza(zzbep.zzfM)).split(",")));
        this.zzE = zzegk;
    }

    private static WebResourceResponse zzS() {
        if (((Boolean) zzba.zzc().zza(zzbep.zzaK)).booleanValue()) {
            return new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0]));
        }
        return null;
    }

    private final WebResourceResponse zzT(String str, Map map) throws IOException {
        HttpURLConnection httpURLConnection;
        WebResourceResponse webResourceResponse;
        String str2;
        URL url = new URL(str);
        try {
            TrafficStats.setThreadStatsTag(264);
            int i = 0;
            while (true) {
                i++;
                if (i <= 20) {
                    URLConnection openConnection = url.openConnection();
                    openConnection.setConnectTimeout(10000);
                    openConnection.setReadTimeout(10000);
                    for (Map.Entry entry : map.entrySet()) {
                        openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                    }
                    if (openConnection instanceof HttpURLConnection) {
                        httpURLConnection = (HttpURLConnection) openConnection;
                        zzu.zzp().zzf(this.zzc.getContext(), this.zzc.zzn().afmaVersion, false, httpURLConnection, false, 60000);
                        webResourceResponse = null;
                        zzl zzl2 = new zzl((String) null);
                        zzl2.zzc(httpURLConnection, (byte[]) null);
                        int responseCode = httpURLConnection.getResponseCode();
                        zzl2.zze(httpURLConnection, responseCode);
                        if (responseCode < 300 || responseCode >= 400) {
                            zzu.zzp();
                            zzu.zzp();
                            String contentType = httpURLConnection.getContentType();
                        } else {
                            String headerField = httpURLConnection.getHeaderField("Location");
                            if (headerField != null) {
                                if (!headerField.startsWith("tel:")) {
                                    URL url2 = new URL(url, headerField);
                                    String protocol = url2.getProtocol();
                                    if (protocol != null) {
                                        if (!protocol.equals(ProxyConfig.MATCH_HTTP) && !protocol.equals(ProxyConfig.MATCH_HTTPS)) {
                                            zzm.zzj("Unsupported scheme: " + protocol);
                                            webResourceResponse = zzS();
                                            break;
                                        }
                                        zzm.zze("Redirecting to " + headerField);
                                        httpURLConnection.disconnect();
                                        url = url2;
                                    } else {
                                        zzm.zzj("Protocol is null");
                                        webResourceResponse = zzS();
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            } else {
                                throw new IOException("Missing Location header in redirect");
                            }
                        }
                    } else {
                        throw new IOException("Invalid protocol.");
                    }
                } else {
                    TrafficStats.clearThreadStatsTag();
                    throw new IOException("Too many redirects (20)");
                }
            }
            zzu.zzp();
            zzu.zzp();
            String contentType2 = httpURLConnection.getContentType();
            String str3 = "";
            if (TextUtils.isEmpty(contentType2)) {
                str2 = str3;
            } else {
                str2 = contentType2.split(";")[0].trim();
            }
            zzu.zzp();
            String contentType3 = httpURLConnection.getContentType();
            if (!TextUtils.isEmpty(contentType3)) {
                String[] split = contentType3.split(";");
                if (split.length != 1) {
                    int i2 = 1;
                    while (true) {
                        if (i2 >= split.length) {
                            break;
                        }
                        if (split[i2].trim().startsWith("charset")) {
                            String[] split2 = split[i2].trim().split("=");
                            if (split2.length > 1) {
                                str3 = split2[1].trim();
                                break;
                            }
                        }
                        i2++;
                    }
                }
            }
            String str4 = str3;
            Map headerFields = httpURLConnection.getHeaderFields();
            HashMap hashMap = new HashMap(headerFields.size());
            for (Map.Entry entry2 : headerFields.entrySet()) {
                if (!(entry2.getKey() == null || entry2.getValue() == null || ((List) entry2.getValue()).isEmpty())) {
                    hashMap.put((String) entry2.getKey(), (String) ((List) entry2.getValue()).get(0));
                }
            }
            webResourceResponse = zzu.zzq().zzb(str2, str4, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
            return webResourceResponse;
        } finally {
            TrafficStats.clearThreadStatsTag();
        }
    }

    /* access modifiers changed from: private */
    public final void zzU(Map map, List list, String str) {
        if (zze.zzc()) {
            zze.zza("Received GMSG: ".concat(str));
            for (String str2 : map.keySet()) {
                zze.zza("  " + str2 + ": " + ((String) map.get(str2)));
            }
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((zzblp) it.next()).zza(this.zzc, map);
        }
    }

    private final void zzV() {
        View.OnAttachStateChangeListener onAttachStateChangeListener = this.zzF;
        if (onAttachStateChangeListener != null) {
            ((View) this.zzc).removeOnAttachStateChangeListener(onAttachStateChangeListener);
        }
    }

    /* access modifiers changed from: private */
    public final void zzW(View view, zzcaf zzcaf, int i) {
        if (zzcaf.zzi() && i > 0) {
            zzcaf.zzg(view);
            if (zzcaf.zzi()) {
                zzt.zza.postDelayed(new zzche(this, view, zzcaf, i), 100);
            }
        }
    }

    private static final boolean zzX(zzchd zzchd) {
        if (zzchd.zzD() != null) {
            return zzchd.zzD().zzaj;
        }
        return false;
    }

    private static final boolean zzY(boolean z, zzchd zzchd) {
        return z && !zzchd.zzO().zzi() && !zzchd.zzU().equals("interstitial_mb");
    }

    public final void onAdClicked() {
        zza zza2 = this.zzg;
        if (zza2 != null) {
            zza2.onAdClicked();
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        zze.zza("Loading resource: ".concat(String.valueOf(str)));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzj(parse);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r2 == null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r2.zza();
        r1.zzj = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        zzg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r1.zzc.zzL() == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (((java.lang.Boolean) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzlR)).booleanValue() == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        r1.zzc.zzL().zzG(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r1.zzz = true;
        r2 = r1.zzj;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r2, java.lang.String r3) {
        /*
            r1 = this;
            java.lang.Object r2 = r1.zzf
            monitor-enter(r2)
            com.google.android.gms.internal.ads.zzchd r0 = r1.zzc     // Catch:{ all -> 0x004c }
            boolean r0 = r0.zzaE()     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x0017
            java.lang.String r3 = "Blank page loaded, 1..."
            com.google.android.gms.ads.internal.util.zze.zza(r3)     // Catch:{ all -> 0x004c }
            com.google.android.gms.internal.ads.zzchd r3 = r1.zzc     // Catch:{ all -> 0x004c }
            r3.zzX()     // Catch:{ all -> 0x004c }
            monitor-exit(r2)     // Catch:{ all -> 0x004c }
            return
        L_0x0017:
            monitor-exit(r2)     // Catch:{ all -> 0x004c }
            r2 = 1
            r1.zzz = r2
            com.google.android.gms.internal.ads.zzciu r2 = r1.zzj
            if (r2 == 0) goto L_0x0025
            r2.zza()
            r2 = 0
            r1.zzj = r2
        L_0x0025:
            r1.zzg()
            com.google.android.gms.internal.ads.zzchd r2 = r1.zzc
            com.google.android.gms.ads.internal.overlay.zzm r2 = r2.zzL()
            if (r2 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzbeg r2 = com.google.android.gms.internal.ads.zzbep.zzlR
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r2 = r0.zza(r2)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.zzchd r2 = r1.zzc
            com.google.android.gms.ads.internal.overlay.zzm r2 = r2.zzL()
            r2.zzG(r3)
        L_0x004b:
            return
        L_0x004c:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x004c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzchl.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.zzo = true;
        this.zzp = i;
        this.zzq = str;
        this.zzr = str2;
    }

    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzc.zzaD(Chip$$ExternalSyntheticApiModelOutline0.m(renderProcessGoneDetail), Chip$$ExternalSyntheticApiModelOutline0.m(renderProcessGoneDetail));
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzc(str, Collections.emptyMap());
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case Opcodes.POP:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case 127:
                    case 128:
                    case TsExtractor.TS_STREAM_TYPE_AC3:
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS:
                        return true;
                    default:
                        return false;
                }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean shouldOverrideUrlLoading(android.webkit.WebView r13, java.lang.String r14) {
        /*
            r12 = this;
            java.lang.String r0 = "AdWebView shouldOverrideUrlLoading: "
            java.lang.String r1 = java.lang.String.valueOf(r14)
            java.lang.String r0 = r0.concat(r1)
            com.google.android.gms.ads.internal.util.zze.zza(r0)
            android.net.Uri r0 = android.net.Uri.parse(r14)
            java.lang.String r1 = r0.getScheme()
            java.lang.String r2 = "gmsg"
            boolean r1 = r2.equalsIgnoreCase(r1)
            r2 = 1
            if (r1 == 0) goto L_0x002f
            java.lang.String r1 = r0.getHost()
            java.lang.String r3 = "mobileads.google.com"
            boolean r1 = r3.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x002f
            r12.zzj(r0)
            goto L_0x0112
        L_0x002f:
            boolean r1 = r12.zzn
            if (r1 == 0) goto L_0x006e
            com.google.android.gms.internal.ads.zzchd r1 = r12.zzc
            android.webkit.WebView r1 = r1.zzG()
            if (r13 != r1) goto L_0x006e
            java.lang.String r1 = r0.getScheme()
            java.lang.String r3 = "http"
            boolean r3 = r3.equalsIgnoreCase(r1)
            if (r3 != 0) goto L_0x004f
            java.lang.String r3 = "https"
            boolean r1 = r3.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x006e
        L_0x004f:
            com.google.android.gms.ads.internal.client.zza r0 = r12.zzg
            r1 = 0
            if (r0 == 0) goto L_0x0060
            r0.onAdClicked()
            com.google.android.gms.internal.ads.zzcaf r0 = r12.zza
            if (r0 == 0) goto L_0x005e
            r0.zzh(r14)
        L_0x005e:
            r12.zzg = r1
        L_0x0060:
            com.google.android.gms.internal.ads.zzdhi r0 = r12.zzm
            if (r0 == 0) goto L_0x0069
            r0.zzdG()
            r12.zzm = r1
        L_0x0069:
            boolean r13 = super.shouldOverrideUrlLoading(r13, r14)
            return r13
        L_0x006e:
            com.google.android.gms.internal.ads.zzchd r13 = r12.zzc
            android.webkit.WebView r13 = r13.zzG()
            boolean r13 = r13.willNotDraw()
            if (r13 != 0) goto L_0x0105
            com.google.android.gms.internal.ads.zzchd r13 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzaxd r13 = r13.zzI()     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzchd r1 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzfhs r1 = r1.zzS()     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzlW     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ zzaxe -> 0x00d0 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ zzaxe -> 0x00d0 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ zzaxe -> 0x00d0 }
            boolean r3 = r3.booleanValue()     // Catch:{ zzaxe -> 0x00d0 }
            if (r3 == 0) goto L_0x00b5
            if (r1 == 0) goto L_0x00b5
            if (r13 == 0) goto L_0x00dd
            boolean r13 = r13.zzf(r0)     // Catch:{ zzaxe -> 0x00d0 }
            if (r13 == 0) goto L_0x00dd
            com.google.android.gms.internal.ads.zzchd r13 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            android.content.Context r13 = r13.getContext()     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzchd r3 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            android.app.Activity r4 = r3.zzi()     // Catch:{ zzaxe -> 0x00d0 }
            android.view.View r3 = (android.view.View) r3     // Catch:{ zzaxe -> 0x00d0 }
            android.net.Uri r0 = r1.zza(r0, r13, r3, r4)     // Catch:{ zzaxe -> 0x00d0 }
            goto L_0x00dd
        L_0x00b5:
            if (r13 == 0) goto L_0x00dd
            boolean r1 = r13.zzf(r0)     // Catch:{ zzaxe -> 0x00d0 }
            if (r1 == 0) goto L_0x00dd
            com.google.android.gms.internal.ads.zzchd r1 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            android.content.Context r1 = r1.getContext()     // Catch:{ zzaxe -> 0x00d0 }
            com.google.android.gms.internal.ads.zzchd r3 = r12.zzc     // Catch:{ zzaxe -> 0x00d0 }
            android.app.Activity r4 = r3.zzi()     // Catch:{ zzaxe -> 0x00d0 }
            android.view.View r3 = (android.view.View) r3     // Catch:{ zzaxe -> 0x00d0 }
            android.net.Uri r0 = r13.zza(r0, r1, r3, r4)     // Catch:{ zzaxe -> 0x00d0 }
            goto L_0x00dd
        L_0x00d0:
            java.lang.String r13 = java.lang.String.valueOf(r14)
            java.lang.String r1 = "Unable to append parameter to URL: "
            java.lang.String r13 = r1.concat(r13)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r13)
        L_0x00dd:
            com.google.android.gms.ads.internal.zzb r13 = r12.zzx
            if (r13 == 0) goto L_0x00ee
            boolean r13 = r13.zzc()
            if (r13 == 0) goto L_0x00e8
            goto L_0x00ee
        L_0x00e8:
            com.google.android.gms.ads.internal.zzb r13 = r12.zzx
            r13.zzb(r14)
            goto L_0x0112
        L_0x00ee:
            com.google.android.gms.ads.internal.overlay.zzc r13 = new com.google.android.gms.ads.internal.overlay.zzc
            java.lang.String r5 = r0.toString()
            r10 = 0
            r11 = 0
            java.lang.String r4 = "android.intent.action.VIEW"
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r3 = r13
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            r14 = 0
            r12.zzu(r13, r2, r14)
            goto L_0x0112
        L_0x0105:
            java.lang.String r13 = java.lang.String.valueOf(r14)
            java.lang.String r14 = "AdWebView unable to handle URL: "
            java.lang.String r13 = r14.concat(r13)
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r13)
        L_0x0112:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzchl.shouldOverrideUrlLoading(android.webkit.WebView, java.lang.String):boolean");
    }

    public final void zzA(String str, zzblp zzblp) {
        synchronized (this.zzf) {
            List list = (List) this.zze.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zze.put(str, list);
            }
            list.add(zzblp);
        }
    }

    public final void zzB(zzcit zzcit) {
        this.zzi = zzcit;
    }

    public final void zzC(int i, int i2) {
        zzbud zzbud = this.zzy;
        if (zzbud != null) {
            zzbud.zze(i, i2);
        }
    }

    public final void zzD(boolean z) {
        this.zzn = false;
    }

    public final void zzE(boolean z) {
        synchronized (this.zzf) {
            this.zzu = z;
        }
    }

    public final void zzF() {
        synchronized (this.zzf) {
            this.zzn = false;
            this.zzs = true;
            zzcci.zze.execute(new zzchf(this));
        }
    }

    public final void zzG(boolean z) {
        synchronized (this.zzf) {
            this.zzt = true;
        }
    }

    public final void zzH(zzciu zzciu) {
        this.zzj = zzciu;
    }

    public final void zzI(zzcqd zzcqd, zzefz zzefz, zzfoe zzfoe) {
        zzL("/click");
        if (zzefz == null || zzfoe == null) {
            zzA("/click", new zzbkn(this.zzm, zzcqd));
        } else {
            zzA("/click", new zzfhw(this.zzm, zzcqd, zzfoe, zzefz));
        }
    }

    public final void zzJ(zzcqd zzcqd) {
        zzL("/click");
        zzA("/click", new zzbkn(this.zzm, zzcqd));
    }

    public final void zzK(zzcqd zzcqd, zzefz zzefz, zzdvc zzdvc) {
        zzL("/open");
        zzA("/open", new zzbmb(this.zzx, this.zzy, zzefz, zzdvc, zzcqd));
    }

    public final void zzL(String str) {
        synchronized (this.zzf) {
            List list = (List) this.zze.get(str);
            if (list != null) {
                list.clear();
            }
        }
    }

    public final void zzM(String str, zzblp zzblp) {
        synchronized (this.zzf) {
            List list = (List) this.zze.get(str);
            if (list != null) {
                list.remove(zzblp);
            }
        }
    }

    public final void zzN(String str, Predicate predicate) {
        synchronized (this.zzf) {
            List<zzblp> list = (List) this.zze.get(str);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (zzblp zzblp : list) {
                    if (predicate.apply(zzblp)) {
                        arrayList.add(zzblp);
                    }
                }
                list.removeAll(arrayList);
            }
        }
    }

    public final boolean zzO() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzu;
        }
        return z;
    }

    public final boolean zzP() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzs;
        }
        return z;
    }

    public final boolean zzQ() {
        boolean z;
        synchronized (this.zzf) {
            z = this.zzt;
        }
        return z;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x01c6: MOVE  (r1v47 com.google.android.gms.internal.ads.zzblq) = (r35v0 com.google.android.gms.internal.ads.zzblq)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    public final void zzR(com.google.android.gms.ads.internal.client.zza r18, com.google.android.gms.internal.ads.zzbkf r19, com.google.android.gms.ads.internal.overlay.zzp r20, com.google.android.gms.internal.ads.zzbkh r21, com.google.android.gms.ads.internal.overlay.zzaa r22, boolean r23, com.google.android.gms.internal.ads.zzbls r24, com.google.android.gms.ads.internal.zzb r25, com.google.android.gms.internal.ads.zzbuk r26, com.google.android.gms.internal.ads.zzcaf r27, com.google.android.gms.internal.ads.zzefz r28, com.google.android.gms.internal.ads.zzfoe r29, com.google.android.gms.internal.ads.zzdvc r30, com.google.android.gms.internal.ads.zzbmj r31, com.google.android.gms.internal.ads.zzdhi r32, com.google.android.gms.internal.ads.zzbmi r33, com.google.android.gms.internal.ads.zzbmc r34, com.google.android.gms.internal.ads.zzblq r35, com.google.android.gms.internal.ads.zzcqd r36) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            r2 = r21
            r3 = r24
            r4 = r26
            r5 = r27
            r10 = r28
            r11 = r29
            r12 = r31
            r13 = r32
            r14 = r33
            r15 = r34
            r9 = r35
            r8 = r36
            if (r25 != 0) goto L_0x002c
            com.google.android.gms.internal.ads.zzchd r6 = r0.zzc
            com.google.android.gms.ads.internal.zzb r7 = new com.google.android.gms.ads.internal.zzb
            android.content.Context r6 = r6.getContext()
            r8 = 0
            r7.<init>(r6, r5, r8)
            r8 = r7
            goto L_0x002e
        L_0x002c:
            r8 = r25
        L_0x002e:
            com.google.android.gms.internal.ads.zzchd r6 = r0.zzc
            com.google.android.gms.internal.ads.zzbud r7 = new com.google.android.gms.internal.ads.zzbud
            r7.<init>(r6, r4)
            r0.zzy = r7
            r0.zza = r5
            com.google.android.gms.internal.ads.zzbeg r5 = com.google.android.gms.internal.ads.zzbep.zzaS
            com.google.android.gms.internal.ads.zzben r6 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r5 = r6.zza(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0055
            com.google.android.gms.internal.ads.zzbke r5 = new com.google.android.gms.internal.ads.zzbke
            r5.<init>(r1)
            java.lang.String r6 = "/adMetadata"
            r0.zzA(r6, r5)
        L_0x0055:
            if (r2 == 0) goto L_0x0061
            com.google.android.gms.internal.ads.zzbkg r5 = new com.google.android.gms.internal.ads.zzbkg
            r5.<init>(r2)
            java.lang.String r6 = "/appEvent"
            r0.zzA(r6, r5)
        L_0x0061:
            java.lang.String r5 = "/backButton"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzj
            r0.zzA(r5, r6)
            java.lang.String r5 = "/refresh"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzk
            r0.zzA(r5, r6)
            java.lang.String r5 = "/canOpenApp"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzb
            r0.zzA(r5, r6)
            java.lang.String r5 = "/canOpenURLs"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zza
            r0.zzA(r5, r6)
            java.lang.String r5 = "/canOpenIntents"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzc
            r0.zzA(r5, r6)
            java.lang.String r5 = "/close"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzd
            r0.zzA(r5, r6)
            java.lang.String r5 = "/customClose"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zze
            r0.zzA(r5, r6)
            java.lang.String r5 = "/instrument"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzn
            r0.zzA(r5, r6)
            java.lang.String r5 = "/delayPageLoaded"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzp
            r0.zzA(r5, r6)
            java.lang.String r5 = "/delayPageClosed"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzq
            r0.zzA(r5, r6)
            java.lang.String r5 = "/getLocationInfo"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzr
            r0.zzA(r5, r6)
            java.lang.String r5 = "/log"
            com.google.android.gms.internal.ads.zzblp r6 = com.google.android.gms.internal.ads.zzblo.zzg
            r0.zzA(r5, r6)
            com.google.android.gms.internal.ads.zzblw r5 = new com.google.android.gms.internal.ads.zzblw
            com.google.android.gms.internal.ads.zzbud r6 = r0.zzy
            r5.<init>(r8, r6, r4)
            java.lang.String r4 = "/mraid"
            r0.zzA(r4, r5)
            com.google.android.gms.internal.ads.zzbui r4 = r0.zzw
            if (r4 == 0) goto L_0x00ca
            java.lang.String r5 = "/mraidLoaded"
            r0.zzA(r5, r4)
        L_0x00ca:
            com.google.android.gms.internal.ads.zzbmb r7 = new com.google.android.gms.internal.ads.zzbmb
            com.google.android.gms.internal.ads.zzbud r6 = r0.zzy
            r4 = r7
            r5 = r8
            r2 = r7
            r7 = r28
            r1 = r36
            r16 = r8
            r8 = r30
            r9 = r36
            r4.<init>(r5, r6, r7, r8, r9)
            java.lang.String r4 = "/open"
            r0.zzA(r4, r2)
            com.google.android.gms.internal.ads.zzcfq r2 = new com.google.android.gms.internal.ads.zzcfq
            r2.<init>()
            java.lang.String r4 = "/precache"
            r0.zzA(r4, r2)
            java.lang.String r2 = "/touch"
            com.google.android.gms.internal.ads.zzblp r4 = com.google.android.gms.internal.ads.zzblo.zzi
            r0.zzA(r2, r4)
            java.lang.String r2 = "/video"
            com.google.android.gms.internal.ads.zzblp r4 = com.google.android.gms.internal.ads.zzblo.zzl
            r0.zzA(r2, r4)
            java.lang.String r2 = "/videoMeta"
            com.google.android.gms.internal.ads.zzblp r4 = com.google.android.gms.internal.ads.zzblo.zzm
            r0.zzA(r2, r4)
            java.lang.String r2 = "/httpTrack"
            java.lang.String r4 = "/click"
            if (r10 == 0) goto L_0x011b
            if (r11 == 0) goto L_0x011b
            com.google.android.gms.internal.ads.zzfhw r5 = new com.google.android.gms.internal.ads.zzfhw
            r5.<init>(r13, r1, r11, r10)
            r0.zzA(r4, r5)
            com.google.android.gms.internal.ads.zzfhx r1 = new com.google.android.gms.internal.ads.zzfhx
            r1.<init>(r11, r10)
            r0.zzA(r2, r1)
            goto L_0x0128
        L_0x011b:
            com.google.android.gms.internal.ads.zzbkn r5 = new com.google.android.gms.internal.ads.zzbkn
            r5.<init>(r13, r1)
            r0.zzA(r4, r5)
            com.google.android.gms.internal.ads.zzblp r1 = com.google.android.gms.internal.ads.zzblo.zzf
            r0.zzA(r2, r1)
        L_0x0128:
            com.google.android.gms.internal.ads.zzchd r1 = r0.zzc
            com.google.android.gms.internal.ads.zzcau r2 = com.google.android.gms.ads.internal.zzu.zzn()
            android.content.Context r1 = r1.getContext()
            boolean r1 = r2.zzp(r1)
            if (r1 == 0) goto L_0x015d
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            com.google.android.gms.internal.ads.zzchd r2 = r0.zzc
            com.google.android.gms.internal.ads.zzfgt r2 = r2.zzD()
            if (r2 == 0) goto L_0x014d
            com.google.android.gms.internal.ads.zzchd r1 = r0.zzc
            com.google.android.gms.internal.ads.zzfgt r1 = r1.zzD()
            java.util.Map r1 = r1.zzax
        L_0x014d:
            com.google.android.gms.internal.ads.zzchd r2 = r0.zzc
            com.google.android.gms.internal.ads.zzblv r4 = new com.google.android.gms.internal.ads.zzblv
            android.content.Context r2 = r2.getContext()
            r4.<init>(r2, r1)
            java.lang.String r1 = "/logScionEvent"
            r0.zzA(r1, r4)
        L_0x015d:
            if (r3 == 0) goto L_0x0169
            com.google.android.gms.internal.ads.zzblr r1 = new com.google.android.gms.internal.ads.zzblr
            r1.<init>(r3)
            java.lang.String r2 = "/setInterstitialProperties"
            r0.zzA(r2, r1)
        L_0x0169:
            if (r12 == 0) goto L_0x0182
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zziU
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0182
            java.lang.String r1 = "/inspectorNetworkExtras"
            r0.zzA(r1, r12)
        L_0x0182:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzjn
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x019b
            if (r14 == 0) goto L_0x019b
            java.lang.String r1 = "/shareSheet"
            r0.zzA(r1, r14)
        L_0x019b:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzjs
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x01b4
            if (r15 == 0) goto L_0x01b4
            java.lang.String r1 = "/inspectorOutOfContextTest"
            r0.zzA(r1, r15)
        L_0x01b4:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzjw
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x01cf
            r1 = r35
            if (r1 == 0) goto L_0x01cf
            java.lang.String r2 = "/inspectorStorage"
            r0.zzA(r2, r1)
        L_0x01cf:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzlz
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0204
            java.lang.String r1 = "/bindPlayStoreOverlay"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzu
            r0.zzA(r1, r2)
            java.lang.String r1 = "/presentPlayStoreOverlay"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzv
            r0.zzA(r1, r2)
            java.lang.String r1 = "/expandPlayStoreOverlay"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzw
            r0.zzA(r1, r2)
            java.lang.String r1 = "/collapsePlayStoreOverlay"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzx
            r0.zzA(r1, r2)
            java.lang.String r1 = "/closePlayStoreOverlay"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzy
            r0.zzA(r1, r2)
        L_0x0204:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzdi
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0224
            java.lang.String r1 = "/setPAIDPersonalizationEnabled"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzA
            r0.zzA(r1, r2)
            java.lang.String r1 = "/resetPAID"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzz
            r0.zzA(r1, r2)
        L_0x0224:
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzlQ
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r1 = r2.zza(r1)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x0254
            com.google.android.gms.internal.ads.zzchd r1 = r0.zzc
            com.google.android.gms.internal.ads.zzfgt r2 = r1.zzD()
            if (r2 == 0) goto L_0x0254
            com.google.android.gms.internal.ads.zzfgt r1 = r1.zzD()
            boolean r1 = r1.zzas
            if (r1 == 0) goto L_0x0254
            java.lang.String r1 = "/writeToLocalStorage"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzB
            r0.zzA(r1, r2)
            java.lang.String r1 = "/clearLocalStorageKeys"
            com.google.android.gms.internal.ads.zzblp r2 = com.google.android.gms.internal.ads.zzblo.zzC
            r0.zzA(r1, r2)
        L_0x0254:
            r1 = r18
            r0.zzg = r1
            r1 = r20
            r0.zzh = r1
            r1 = r19
            r0.zzk = r1
            r1 = r21
            r0.zzl = r1
            r1 = r22
            r0.zzv = r1
            r7 = r16
            r0.zzx = r7
            r0.zzm = r13
            r1 = r23
            r0.zzn = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzchl.zzR(com.google.android.gms.ads.internal.client.zza, com.google.android.gms.internal.ads.zzbkf, com.google.android.gms.ads.internal.overlay.zzp, com.google.android.gms.internal.ads.zzbkh, com.google.android.gms.ads.internal.overlay.zzaa, boolean, com.google.android.gms.internal.ads.zzbls, com.google.android.gms.ads.internal.zzb, com.google.android.gms.internal.ads.zzbuk, com.google.android.gms.internal.ads.zzcaf, com.google.android.gms.internal.ads.zzefz, com.google.android.gms.internal.ads.zzfoe, com.google.android.gms.internal.ads.zzdvc, com.google.android.gms.internal.ads.zzbmj, com.google.android.gms.internal.ads.zzdhi, com.google.android.gms.internal.ads.zzbmi, com.google.android.gms.internal.ads.zzbmc, com.google.android.gms.internal.ads.zzblq, com.google.android.gms.internal.ads.zzcqd):void");
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zza() {
        synchronized (this.zzf) {
        }
        return null;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzb() {
        synchronized (this.zzf) {
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0164, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0165, code lost:
        r10 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0168, code lost:
        r2 = true;
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x016c, code lost:
        r2 = true;
        r7 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00b1 A[SYNTHETIC, Splitter:B:23:0x00b1] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0164 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:31:0x0103] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x01fd A[Catch:{ Exception | NoClassDefFoundError -> 0x0275 }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x024b A[Catch:{ Exception | NoClassDefFoundError -> 0x0275 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x026e A[Catch:{ Exception | NoClassDefFoundError -> 0x0275 }] */
    /* JADX WARNING: Removed duplicated region for block: B:92:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:55:0x0176=Splitter:B:55:0x0176, B:63:0x01ac=Splitter:B:63:0x01ac} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.webkit.WebResourceResponse zzc(java.lang.String r19, java.util.Map r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            java.lang.String r2 = "range"
            java.lang.String r3 = "ms"
            java.lang.String r4 = "Cache connection took "
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.<init>()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchd r6 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzfgt r6 = r6.zzD()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r6 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zzchd r5 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzfgt r5 = r5.zzD()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.util.Map r5 = r5.zzax     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
        L_0x001f:
            com.google.android.gms.internal.ads.zzchd r6 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            android.content.Context r6 = r6.getContext()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r7 = r1.zzC     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r5 = com.google.android.gms.internal.ads.zzcaw.zzc(r0, r6, r7, r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r6 = r5.equals(r0)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r6 != 0) goto L_0x0038
            r6 = r20
            android.webkit.WebResourceResponse r0 = r1.zzT(r5, r6)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            return r0
        L_0x0038:
            r6 = r20
            android.net.Uri r5 = android.net.Uri.parse(r19)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzbcy r5 = com.google.android.gms.internal.ads.zzbcy.zza(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r5 == 0) goto L_0x025a
            java.util.HashMap r13 = new java.util.HashMap     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r13.<init>()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r8 = "Access-Control-Allow-Origin"
            java.lang.String r9 = "*"
            r13.put(r8, r9)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            android.net.Uri r8 = android.net.Uri.parse(r19)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.util.Set r9 = r8.getQueryParameterNames()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r9 = r9.contains(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r10 = 0
            r11 = -1
            r12 = 1
            if (r9 == 0) goto L_0x0096
            r9 = 45
            com.google.android.gms.internal.ads.zzfxr r9 = com.google.android.gms.internal.ads.zzfxr.zzc(r9)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzfyt r9 = com.google.android.gms.internal.ads.zzfyt.zzc(r9)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = r8.getQueryParameter(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.util.List r2 = r9.zzf(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            int r8 = r2.size()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r9 = 2
            if (r8 != r9) goto L_0x0096
            java.lang.Object r8 = r2.get(r10)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r8 = (java.lang.String) r8     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            int r8 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Object r2 = r2.get(r12)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            int r2 = r2 + r12
            if (r8 <= 0) goto L_0x0094
            long r14 = (long) r8     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.zzh = r14     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
        L_0x0094:
            int r2 = r2 - r8
            goto L_0x0097
        L_0x0096:
            r2 = r11
        L_0x0097:
            com.google.android.gms.internal.ads.zzbeg r8 = com.google.android.gms.internal.ads.zzbep.zzeo     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzben r9 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Object r8 = r9.zza(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r8 = r8.booleanValue()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r9 = "X-Afma-Gcache-CachedBytes"
            java.lang.String r14 = "X-Afma-Gcache-IsDownloaded"
            java.lang.String r15 = "X-Afma-Gcache-IsGcacheHit"
            java.lang.String r10 = "X-Afma-Gcache-HasAdditionalMetadataFromReadV2"
            if (r8 == 0) goto L_0x01fd
            com.google.android.gms.internal.ads.zzchd r8 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r8 = r8.zzr()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r8 = com.google.android.gms.internal.ads.zzfyv.zzc(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.zzi = r8     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchd r8 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            int r8 = r8.zzf()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.zzj = r8     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r8 = r5.zzg     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r8 == 0) goto L_0x00d6
            com.google.android.gms.internal.ads.zzbeg r8 = com.google.android.gms.internal.ads.zzbep.zzeq     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Object r7 = r7.zza(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            goto L_0x00e2
        L_0x00d6:
            com.google.android.gms.internal.ads.zzbeg r7 = com.google.android.gms.internal.ads.zzbep.zzep     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzben r8 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Object r7 = r8.zza(r7)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
        L_0x00e2:
            long r7 = r7.longValue()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.common.util.Clock r16 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r16 = r16.elapsedRealtime()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.ads.internal.zzu.zzd()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchd r12 = r1.zzc     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            android.content.Context r12 = r12.getContext()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.util.concurrent.Future r5 = com.google.android.gms.internal.ads.zzbdj.zza(r12, r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.util.concurrent.TimeUnit r12 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ExecutionException | TimeoutException -> 0x01a9, InterruptedException -> 0x0173, all -> 0x0170 }
            java.lang.Object r7 = r5.get(r7, r12)     // Catch:{ ExecutionException | TimeoutException -> 0x01a9, InterruptedException -> 0x0173, all -> 0x0170 }
            com.google.android.gms.internal.ads.zzbdk r7 = (com.google.android.gms.internal.ads.zzbdk) r7     // Catch:{ ExecutionException | TimeoutException -> 0x01a9, InterruptedException -> 0x0173, all -> 0x0170 }
            boolean r8 = r7.zzd()     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            java.lang.String r8 = java.lang.Boolean.toString(r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            r13.put(r10, r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            boolean r8 = r7.zzf()     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            java.lang.String r8 = java.lang.Boolean.toString(r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            r13.put(r15, r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            boolean r8 = r7.zze()     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            java.lang.String r8 = java.lang.Boolean.toString(r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            r13.put(r14, r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            long r14 = r7.zza()     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            r13.put(r9, r8)     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            java.io.InputStream r7 = r7.zzc()     // Catch:{ ExecutionException | TimeoutException -> 0x016c, InterruptedException -> 0x0168, all -> 0x0164 }
            if (r2 == r11) goto L_0x013f
            long r8 = (long) r2
            java.io.InputStream r7 = com.google.android.gms.internal.ads.zzgdm.zza(r7, r8)     // Catch:{ ExecutionException | TimeoutException -> 0x013d, InterruptedException -> 0x013b, all -> 0x0164 }
            goto L_0x013f
        L_0x013b:
            r2 = 1
            goto L_0x016a
        L_0x013d:
            r2 = 1
            goto L_0x016e
        L_0x013f:
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r2.elapsedRealtime()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r8 - r16
            com.google.android.gms.internal.ads.zzfuv r2 = com.google.android.gms.ads.internal.util.zzt.zza     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchh r5 = new com.google.android.gms.internal.ads.zzchh     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r10 = 1
            r5.<init>(r1, r10, r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.post(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r3)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            goto L_0x01a3
        L_0x0164:
            r0 = move-exception
            r10 = 1
            goto L_0x01d6
        L_0x0168:
            r2 = 1
            r7 = 0
        L_0x016a:
            r10 = 1
            goto L_0x0176
        L_0x016c:
            r2 = 1
            r7 = 0
        L_0x016e:
            r10 = 1
            goto L_0x01ac
        L_0x0170:
            r0 = move-exception
            r10 = 0
            goto L_0x01d6
        L_0x0173:
            r2 = 1
            r7 = 0
            r10 = 0
        L_0x0176:
            r5.cancel(r2)     // Catch:{ all -> 0x01a7 }
            java.lang.Thread r2 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x01a7 }
            r2.interrupt()     // Catch:{ all -> 0x01a7 }
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r2.elapsedRealtime()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r8 - r16
            com.google.android.gms.internal.ads.zzfuv r2 = com.google.android.gms.ads.internal.util.zzt.zza     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchh r5 = new com.google.android.gms.internal.ads.zzchh     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.<init>(r1, r10, r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.post(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r3)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
        L_0x01a3:
            com.google.android.gms.ads.internal.util.zze.zza(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            goto L_0x01d3
        L_0x01a7:
            r0 = move-exception
            goto L_0x01d6
        L_0x01a9:
            r2 = 1
            r7 = 0
            r10 = 0
        L_0x01ac:
            r5.cancel(r2)     // Catch:{ all -> 0x01a7 }
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r2.elapsedRealtime()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r8 = r8 - r16
            com.google.android.gms.internal.ads.zzfuv r2 = com.google.android.gms.ads.internal.util.zzt.zza     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchh r5 = new com.google.android.gms.internal.ads.zzchh     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r5.<init>(r1, r10, r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.post(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r8)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r3)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            goto L_0x01a3
        L_0x01d3:
            r14 = r7
            goto L_0x0249
        L_0x01d6:
            com.google.android.gms.common.util.Clock r2 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r5 = r2.elapsedRealtime()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r5 = r5 - r16
            com.google.android.gms.internal.ads.zzfuv r2 = com.google.android.gms.ads.internal.util.zzt.zza     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzchh r7 = new com.google.android.gms.internal.ads.zzchh     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r7.<init>(r1, r10, r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.post(r7)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r2.append(r3)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.ads.internal.util.zze.zza(r2)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            throw r0     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
        L_0x01fd:
            com.google.android.gms.internal.ads.zzbcu r3 = com.google.android.gms.ads.internal.zzu.zzc()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            com.google.android.gms.internal.ads.zzbcv r3 = r3.zzb(r5)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r3 == 0) goto L_0x0248
            boolean r4 = r3.zze()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r4 == 0) goto L_0x0248
            boolean r4 = r3.zzd()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r4 = java.lang.Boolean.toString(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r13.put(r10, r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r4 = r3.zzg()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r4 = java.lang.Boolean.toString(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r13.put(r15, r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r4 = r3.zzf()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r4 = java.lang.Boolean.toString(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r13.put(r14, r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            long r4 = r3.zza()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r4 = java.lang.Long.toString(r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r13.put(r9, r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.io.InputStream r3 = r3.zzc()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r2 == r11) goto L_0x0246
            long r4 = (long) r2     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.io.InputStream r2 = com.google.android.gms.internal.ads.zzgdm.zza(r3, r4)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            r14 = r2
            goto L_0x0249
        L_0x0246:
            r14 = r3
            goto L_0x0249
        L_0x0248:
            r14 = 0
        L_0x0249:
            if (r14 == 0) goto L_0x025a
            android.webkit.WebResourceResponse r0 = new android.webkit.WebResourceResponse     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.String r9 = ""
            java.lang.String r10 = ""
            java.lang.String r12 = "OK"
            r11 = 200(0xc8, float:2.8E-43)
            r8 = r0
            r8.<init>(r9, r10, r11, r12, r13, r14)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            return r0
        L_0x025a:
            boolean r2 = com.google.android.gms.ads.internal.util.client.zzl.zzk()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r2 == 0) goto L_0x0273
            com.google.android.gms.internal.ads.zzbfv r2 = com.google.android.gms.internal.ads.zzbgg.zzb     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Object r2 = r2.zze()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            boolean r2 = r2.booleanValue()     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            if (r2 == 0) goto L_0x0273
            android.webkit.WebResourceResponse r0 = r18.zzT(r19, r20)     // Catch:{ Exception -> 0x0277, NoClassDefFoundError -> 0x0275 }
            return r0
        L_0x0273:
            r0 = 0
            return r0
        L_0x0275:
            r0 = move-exception
            goto L_0x0278
        L_0x0277:
            r0 = move-exception
        L_0x0278:
            java.lang.String r2 = "AdWebViewClient.interceptRequest"
            com.google.android.gms.internal.ads.zzcby r3 = com.google.android.gms.ads.internal.zzu.zzo()
            r3.zzw(r0, r2)
            android.webkit.WebResourceResponse r0 = zzS()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzchl.zzc(java.lang.String, java.util.Map):android.webkit.WebResourceResponse");
    }

    public final zzb zzd() {
        return this.zzx;
    }

    public final void zzdG() {
        zzdhi zzdhi = this.zzm;
        if (zzdhi != null) {
            zzdhi.zzdG();
        }
    }

    public final void zzdf() {
        zzdhi zzdhi = this.zzm;
        if (zzdhi != null) {
            zzdhi.zzdf();
        }
    }

    public final void zzg() {
        if (this.zzi != null && ((this.zzz && this.zzB <= 0) || this.zzA || this.zzo)) {
            if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() && this.zzc.zzm() != null) {
                zzbew.zza(this.zzc.zzm().zza(), this.zzc.zzk(), "awfllc");
            }
            zzcit zzcit = this.zzi;
            boolean z = false;
            if (!this.zzA && !this.zzo) {
                z = true;
            }
            zzcit.zza(z, this.zzp, this.zzq, this.zzr);
            this.zzi = null;
        }
        this.zzc.zzaf();
    }

    public final void zzh() {
        zzcaf zzcaf = this.zza;
        if (zzcaf != null) {
            zzcaf.zze();
            this.zza = null;
        }
        zzV();
        synchronized (this.zzf) {
            this.zze.clear();
            this.zzg = null;
            this.zzh = null;
            this.zzi = null;
            this.zzj = null;
            this.zzk = null;
            this.zzl = null;
            this.zzn = false;
            this.zzs = false;
            this.zzt = false;
            this.zzv = null;
            this.zzx = null;
            this.zzw = null;
            zzbud zzbud = this.zzy;
            if (zzbud != null) {
                zzbud.zza(true);
                this.zzy = null;
            }
        }
    }

    public final void zzi(boolean z) {
        this.zzC = z;
    }

    public final void zzj(Uri uri) {
        String str;
        zze.zza("Received GMSG: ".concat(String.valueOf(String.valueOf(uri))));
        HashMap hashMap = this.zze;
        String path = uri.getPath();
        List list = (List) hashMap.get(path);
        if (path == null || list == null) {
            zze.zza("No GMSG handler found for GMSG: ".concat(String.valueOf(String.valueOf(uri))));
            if (((Boolean) zzba.zzc().zza(zzbep.zzgV)).booleanValue() && zzu.zzo().zzg() != null) {
                if (path == null || path.length() < 2) {
                    str = "null";
                } else {
                    str = path.substring(1);
                }
                zzcci.zza.execute(new zzchg(str));
                return;
            }
            return;
        }
        String encodedQuery = uri.getEncodedQuery();
        if (((Boolean) zzba.zzc().zza(zzbep.zzfL)).booleanValue() && this.zzD.contains(path) && encodedQuery != null) {
            if (encodedQuery.length() >= ((Integer) zzba.zzc().zza(zzbep.zzfN)).intValue()) {
                zze.zza("Parsing gmsg query params on BG thread: ".concat(path));
                zzgft.zzr(zzu.zzp().zzb(uri), new zzchj(this, list, path, uri), zzcci.zze);
                return;
            }
        }
        zzu.zzp();
        zzU(zzt.zzP(uri), list, path);
    }

    public final void zzk() {
        zzbdm zzbdm = this.zzd;
        if (zzbdm != null) {
            zzbdm.zzb(zzbdo.DELAY_PAGE_LOAD_CANCELLED_AD);
        }
        this.zzA = true;
        this.zzp = zzbdo.DELAY_PAGE_LOAD_CANCELLED_AD.zza();
        this.zzq = "Page loaded delay cancel.";
        zzg();
        this.zzc.destroy();
    }

    public final void zzl() {
        synchronized (this.zzf) {
        }
        this.zzB++;
        zzg();
    }

    public final void zzm() {
        this.zzB--;
        zzg();
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzn() {
        this.zzc.zzad();
        com.google.android.gms.ads.internal.overlay.zzm zzL = this.zzc.zzL();
        if (zzL != null) {
            zzL.zzz();
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzo(boolean z, long j) {
        this.zzc.zzv(z, j);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzp(View view, zzcaf zzcaf, int i) {
        zzW(view, zzcaf, i - 1);
    }

    public final void zzq(int i, int i2, boolean z) {
        zzbui zzbui = this.zzw;
        if (zzbui != null) {
            zzbui.zzb(i, i2);
        }
        zzbud zzbud = this.zzy;
        if (zzbud != null) {
            zzbud.zzd(i, i2, false);
        }
    }

    public final void zzr() {
        zzcaf zzcaf = this.zza;
        if (zzcaf != null) {
            WebView zzG = this.zzc.zzG();
            if (ViewCompat.isAttachedToWindow(zzG)) {
                zzW(zzG, zzcaf, 10);
                return;
            }
            zzV();
            zzchi zzchi = new zzchi(this, zzcaf);
            this.zzF = zzchi;
            ((View) this.zzc).addOnAttachStateChangeListener(zzchi);
        }
    }

    public final void zzu(zzc zzc2, boolean z, boolean z2) {
        zzdhi zzdhi;
        zzchd zzchd = this.zzc;
        boolean zzaF = zzchd.zzaF();
        boolean z3 = false;
        boolean z4 = zzY(zzaF, zzchd) || z2;
        if (z4 || !z) {
            z3 = true;
        }
        zza zza2 = z4 ? null : this.zzg;
        zzp zzp2 = zzaF ? null : this.zzh;
        zzaa zzaa = this.zzv;
        zzchd zzchd2 = this.zzc;
        VersionInfoParcel zzn2 = zzchd2.zzn();
        if (z3) {
            zzdhi = null;
        } else {
            zzdhi = this.zzm;
        }
        zzx(new AdOverlayInfoParcel(zzc2, zza2, zzp2, zzaa, zzn2, zzchd2, zzdhi));
    }

    public final void zzv(String str, String str2, int i) {
        zzegk zzegk = this.zzE;
        zzchd zzchd = this.zzc;
        zzx(new AdOverlayInfoParcel(zzchd, zzchd.zzn(), str, str2, 14, zzegk));
    }

    public final void zzw(boolean z, int i, boolean z2) {
        zzdhi zzdhi;
        zzchd zzchd = this.zzc;
        boolean zzY = zzY(zzchd.zzaF(), zzchd);
        boolean z3 = true;
        if (!zzY && z2) {
            z3 = false;
        }
        zza zza2 = zzY ? null : this.zzg;
        zzp zzp2 = this.zzh;
        zzaa zzaa = this.zzv;
        zzchd zzchd2 = this.zzc;
        VersionInfoParcel zzn2 = zzchd2.zzn();
        if (z3) {
            zzdhi = null;
        } else {
            zzdhi = this.zzm;
        }
        zzx(new AdOverlayInfoParcel(zza2, zzp2, zzaa, zzchd2, z, i, zzn2, zzdhi, zzX(this.zzc) ? this.zzE : null));
    }

    public final void zzx(AdOverlayInfoParcel adOverlayInfoParcel) {
        zzc zzc2;
        zzbud zzbud = this.zzy;
        boolean zzf2 = zzbud != null ? zzbud.zzf() : false;
        zzu.zzi();
        zzn.zza(this.zzc.getContext(), adOverlayInfoParcel, !zzf2);
        zzcaf zzcaf = this.zza;
        if (zzcaf != null) {
            String str = adOverlayInfoParcel.zzl;
            if (str == null && (zzc2 = adOverlayInfoParcel.zza) != null) {
                str = zzc2.zzb;
            }
            zzcaf.zzh(str);
        }
    }

    public final void zzy(boolean z, int i, String str, String str2, boolean z2) {
        zza zza2;
        zzdhi zzdhi;
        zzchd zzchd = this.zzc;
        boolean zzaF = zzchd.zzaF();
        boolean zzY = zzY(zzaF, zzchd);
        boolean z3 = true;
        if (!zzY && z2) {
            z3 = false;
        }
        if (zzY) {
            zza2 = null;
        } else {
            zza2 = this.zzg;
        }
        zzchk zzchk = zzaF ? null : new zzchk(this.zzc, this.zzh);
        zzbkf zzbkf = this.zzk;
        zzbkh zzbkh = this.zzl;
        zzaa zzaa = this.zzv;
        zzchd zzchd2 = this.zzc;
        VersionInfoParcel zzn2 = zzchd2.zzn();
        if (z3) {
            zzdhi = null;
        } else {
            zzdhi = this.zzm;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = r4;
        AdOverlayInfoParcel adOverlayInfoParcel2 = new AdOverlayInfoParcel(zza2, (zzp) zzchk, zzbkf, zzbkh, zzaa, zzchd2, z, i, str, str2, zzn2, zzdhi, (zzbuz) zzX(this.zzc) ? this.zzE : null);
        zzx(adOverlayInfoParcel);
    }

    public final void zzz(boolean z, int i, String str, boolean z2, boolean z3) {
        zza zza2;
        zzdhi zzdhi;
        zzchd zzchd = this.zzc;
        boolean zzaF = zzchd.zzaF();
        boolean zzY = zzY(zzaF, zzchd);
        boolean z4 = true;
        if (!zzY && z2) {
            z4 = false;
        }
        if (zzY) {
            zza2 = null;
        } else {
            zza2 = this.zzg;
        }
        zzchk zzchk = zzaF ? null : new zzchk(this.zzc, this.zzh);
        zzbkf zzbkf = this.zzk;
        zzbkh zzbkh = this.zzl;
        zzaa zzaa = this.zzv;
        zzchd zzchd2 = this.zzc;
        VersionInfoParcel zzn2 = zzchd2.zzn();
        if (z4) {
            zzdhi = null;
        } else {
            zzdhi = this.zzm;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = r4;
        AdOverlayInfoParcel adOverlayInfoParcel2 = new AdOverlayInfoParcel(zza2, (zzp) zzchk, zzbkf, zzbkh, zzaa, zzchd2, z, i, str, zzn2, zzdhi, (zzbuz) zzX(this.zzc) ? this.zzE : null, z3);
        zzx(adOverlayInfoParcel);
    }
}
