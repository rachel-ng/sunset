package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzml implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zzmm zzc;

    zzml(zzmm zzmm, long j, long j2) {
        this.zzc = zzmm;
        this.zza = j;
        this.zzb = j2;
    }

    public final void run() {
        this.zzc.zza.zzl().zzb((Runnable) new zzmo(this));
    }
}
