package com.google.android.gms.internal.ads;

import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfpk implements Runnable {
    final /* synthetic */ WebView zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzfpl zzc;

    zzfpk(zzfpl zzfpl, WebView webView, String str) {
        this.zza = webView;
        this.zzb = str;
        this.zzc = zzfpl;
    }

    public final void run() {
        zzfpl.zzj(this.zza, this.zzb);
    }
}
