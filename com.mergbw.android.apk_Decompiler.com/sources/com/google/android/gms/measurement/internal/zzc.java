package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzc implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzb zzb;

    zzc(zzb zzb2, long j) {
        this.zza = j;
        this.zzb = zzb2;
    }

    public final void run() {
        this.zzb.zzb(this.zza);
    }
}
