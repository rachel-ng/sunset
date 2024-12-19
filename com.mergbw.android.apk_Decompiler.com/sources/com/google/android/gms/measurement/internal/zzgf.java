package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzgf implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzgg zzb;

    zzgf(zzgg zzgg, boolean z) {
        this.zza = z;
        this.zzb = zzgg;
    }

    public final void run() {
        this.zzb.zza.zza(this.zza);
    }
}
