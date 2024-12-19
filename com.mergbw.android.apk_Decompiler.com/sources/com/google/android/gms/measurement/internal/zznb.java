package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zznb implements Runnable {
    private final /* synthetic */ zznm zza;
    private final /* synthetic */ zznc zzb;

    zznb(zznc zznc, zznm zznm) {
        this.zza = zznm;
        this.zzb = zznc;
    }

    public final void run() {
        zznc.zza(this.zzb, this.zza);
        this.zzb.zzv();
    }
}
