package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzaw implements Runnable {
    private final /* synthetic */ zzil zza;
    private final /* synthetic */ zzat zzb;

    zzaw(zzat zzat, zzil zzil) {
        this.zza = zzil;
        this.zzb = zzat;
    }

    public final void run() {
        this.zza.zzd();
        if (zzab.zza()) {
            this.zza.zzl().zzb((Runnable) this);
            return;
        }
        boolean zzc = this.zzb.zzc();
        this.zzb.zzd = 0;
        if (zzc) {
            this.zzb.zzb();
        }
    }
}
