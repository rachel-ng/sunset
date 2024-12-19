package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkf implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzin zzd;
    private final /* synthetic */ zziv zze;

    zzkf(zziv zziv, zzin zzin, long j, boolean z, zzin zzin2) {
        this.zza = zzin;
        this.zzb = j;
        this.zzc = z;
        this.zzd = zzin2;
        this.zze = zziv;
    }

    public final void run() {
        this.zze.zza(this.zza);
        zziv.zza(this.zze, this.zza, this.zzb, false, this.zzc);
        zziv.zza(this.zze, this.zza, this.zzd);
    }
}
