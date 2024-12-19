package com.google.android.gms.internal.ads;

import android.view.View;
import android.webkit.WebView;
import androidx.webkit.ProxyConfig;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Timer;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfou {
    private static final zzfqf zza = new zzfqf();
    private final zzfow zzb;
    private final WebView zzc;
    private final HashMap zzd = new HashMap();
    private final zzfpi zze = new zzfpi();

    private zzfou(zzfow zzfow, WebView webView, boolean z) {
        zzfqd.zza();
        this.zzb = zzfow;
        this.zzc = webView;
        if (WebViewFeature.isFeatureSupported("WEB_MESSAGE_LISTENER")) {
            zzg();
            WebViewCompat.addWebMessageListener(webView, "omidJsSessionService", new HashSet(Arrays.asList(new String[]{ProxyConfig.MATCH_ALL_SCHEMES})), new zzfot(this));
            return;
        }
        throw new UnsupportedOperationException("The JavaScriptSessionService cannot be supported in this WebView version.");
    }

    public static zzfou zza(zzfow zzfow, WebView webView, boolean z) {
        return new zzfou(zzfow, webView, true);
    }

    static /* bridge */ /* synthetic */ void zzb(zzfou zzfou, String str) {
        zzfoj zzfoj = (zzfoj) zzfou.zzd.get(str);
        if (zzfoj != null) {
            zzfoj.zzc();
            zzfou.zzd.remove(str);
        }
    }

    static /* bridge */ /* synthetic */ void zzd(zzfou zzfou, String str) {
        zzfon zzfon = new zzfon(zzfok.zza(zzfoo.DEFINED_BY_JAVASCRIPT, zzfor.DEFINED_BY_JAVASCRIPT, zzfov.JAVASCRIPT, zzfov.JAVASCRIPT, false), zzfol.zzb(zzfou.zzb, zzfou.zzc, (String) null, (String) null), str);
        zzfou.zzd.put(str, zzfon);
        zzfon.zzd(zzfou.zzc);
        for (zzfph zzfph : zzfou.zze.zza()) {
            zzfon.zzb((View) zzfph.zzb().get(), zzfph.zza(), zzfph.zzc());
        }
        zzfon.zze();
    }

    /* access modifiers changed from: private */
    public final void zzg() {
        WebViewCompat.removeWebMessageListener(this.zzc, "omidJsSessionService");
    }

    public final void zze(View view, zzfoq zzfoq, String str) {
        for (zzfoj zzb2 : this.zzd.values()) {
            zzb2.zzb(view, zzfoq, "Ad overlay");
        }
        this.zze.zzb(view, zzfoq, "Ad overlay");
    }

    public final void zzf(zzchs zzchs) {
        for (zzfoj zzc2 : this.zzd.values()) {
            zzc2.zzc();
        }
        Timer timer = new Timer();
        timer.schedule(new zzfos(this, zzchs, timer), 1000);
    }
}
