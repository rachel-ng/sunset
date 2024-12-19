package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmj implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzmh zzb;

    zzmj(zzmh zzmh, long j) {
        this.zza = j;
        this.zzb = zzmh;
    }

    public final void run() {
        zzmh.zza(this.zzb, this.zza);
    }
}
