package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzmf implements Runnable {
    private final /* synthetic */ zznc zza;
    private final /* synthetic */ Runnable zzb;

    zzmf(zzme zzme, zznc zznc, Runnable runnable) {
        this.zza = zznc;
        this.zzb = runnable;
    }

    public final void run() {
        this.zza.zzr();
        this.zza.zza(this.zzb);
        this.zza.zzw();
    }
}
