package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkw implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzks zzb;

    zzkw(zzks zzks, long j) {
        this.zza = j;
        this.zzb = zzks;
    }

    public final void run() {
        this.zzb.zzc().zza(this.zza);
        this.zzb.zza = null;
    }
}
