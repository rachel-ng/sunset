package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zznk;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkg implements Runnable {
    private final /* synthetic */ zzin zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzin zze;
    private final /* synthetic */ zziv zzf;

    zzkg(zziv zziv, zzin zzin, long j, long j2, boolean z, zzin zzin2) {
        this.zza = zzin;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = z;
        this.zze = zzin2;
        this.zzf = zziv;
    }

    public final void run() {
        this.zzf.zza(this.zza);
        if (!zznk.zza() || !this.zzf.zze().zza(zzbf.zzcu)) {
            this.zzf.zza(this.zzb, false);
        }
        zziv.zza(this.zzf, this.zza, this.zzc, true, this.zzd);
        zziv.zza(this.zzf, this.zza, this.zze);
    }
}
