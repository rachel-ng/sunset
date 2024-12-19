package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjl implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zziv zzb;

    zzjl(zziv zziv, long j) {
        this.zza = j;
        this.zzb = zziv;
    }

    public final void run() {
        this.zzb.zzk().zzf.zza(this.zza);
        this.zzb.zzj().zzc().zza("Session timeout duration set", Long.valueOf(this.zza));
    }
}
