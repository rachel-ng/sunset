package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmk implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzmh zzb;

    zzmk(zzmh zzmh, long j) {
        this.zza = j;
        this.zzb = zzmh;
    }

    public final void run() {
        zzmh.zzb(this.zzb, this.zza);
    }
}
