package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzhy implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzhn zzb;

    zzhy(zzhn zzhn, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzhn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        this.zzb.zza.zzd(this.zza);
    }
}
