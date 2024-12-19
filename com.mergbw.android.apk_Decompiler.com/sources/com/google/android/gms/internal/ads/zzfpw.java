package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.webkit.WebView;
import com.google.android.exoplayer2.ExoPlayer;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpw extends zzfps {
    /* access modifiers changed from: private */
    public WebView zza;
    private Long zzb = null;
    private final Map zzc;

    public zzfpw(String str, Map map, String str2) {
        super(str);
        this.zzc = map;
    }

    public final void zzc() {
        long j;
        super.zzc();
        if (this.zzb == null) {
            j = 4000;
        } else {
            j = TimeUnit.MILLISECONDS.convert(System.nanoTime() - this.zzb.longValue(), TimeUnit.NANOSECONDS);
        }
        new Handler().postDelayed(new zzfpv(this), Math.max(4000 - j, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS));
        this.zza = null;
    }

    public final void zzi(zzfon zzfon, zzfol zzfol) {
        JSONObject jSONObject = new JSONObject();
        Map zzi = zzfol.zzi();
        Iterator it = zzi.keySet().iterator();
        if (!it.hasNext()) {
            zzj(zzfon, zzfol, jSONObject);
        } else {
            zzfox zzfox = (zzfox) zzi.get((String) it.next());
            throw null;
        }
    }

    public final void zzn() {
        WebView webView = new WebView(zzfpj.zzb().zza());
        this.zza = webView;
        webView.getSettings().setJavaScriptEnabled(true);
        this.zza.getSettings().setAllowContentAccess(false);
        this.zza.getSettings().setAllowFileAccess(false);
        this.zza.setWebViewClient(new zzfpu(this));
        zzm(this.zza);
        zzfpl.zzj(this.zza, (String) null);
        Iterator it = this.zzc.keySet().iterator();
        if (!it.hasNext()) {
            this.zzb = Long.valueOf(System.nanoTime());
            return;
        }
        zzfox zzfox = (zzfox) this.zzc.get((String) it.next());
        throw null;
    }
}
