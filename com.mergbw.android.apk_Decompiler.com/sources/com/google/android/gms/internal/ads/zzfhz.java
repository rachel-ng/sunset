package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzu;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfhz implements zzgfp {
    final /* synthetic */ zzfia zza;
    final /* synthetic */ int zzb;

    zzfhz(zzfia zzfia, int i) {
        this.zzb = i;
        this.zza = zzfia;
    }

    public final void zza(Throwable th) {
        zzu.zzo().zzw(th, "BufferingUrlPinger.attributionReportingManager");
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        int i = this.zzb;
        this.zza.zzb((String) obj, i);
    }
}
