package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzhk implements Runnable {
    private final /* synthetic */ zzit zza;
    private final /* synthetic */ zzhj zzb;

    zzhk(zzhj zzhj, zzit zzit) {
        this.zza = zzit;
        this.zzb = zzhj;
    }

    public final void run() {
        zzhj.zza(this.zzb, this.zza);
        this.zzb.zza(this.zza.zzg);
    }
}
