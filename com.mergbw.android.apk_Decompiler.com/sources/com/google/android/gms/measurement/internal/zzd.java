package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzd implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzb zzc;

    zzd(zzb zzb2, String str, long j) {
        this.zza = str;
        this.zzb = j;
        this.zzc = zzb2;
    }

    public final void run() {
        zzb.zzb(this.zzc, this.zza, this.zzb);
    }
}
