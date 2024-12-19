package com.google.android.gms.internal.ads;

import android.webkit.WebView;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfpv implements Runnable {
    final /* synthetic */ zzfpw zza;
    private final WebView zzb;

    zzfpv(zzfpw zzfpw) {
        this.zza = zzfpw;
        this.zzb = zzfpw.zza;
    }

    public final void run() {
        this.zzb.destroy();
    }
}
