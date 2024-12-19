package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzie implements Runnable {
    private final /* synthetic */ zzno zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzhn zzc;

    zzie(zzhn zzhn, zzno zzno, zzo zzo) {
        this.zza = zzno;
        this.zzb = zzo;
        this.zzc = zzhn;
    }

    public final void run() {
        this.zzc.zza.zzr();
        if (this.zza.zza() == null) {
            this.zzc.zza.zza(this.zza.zza, this.zzb);
        } else {
            this.zzc.zza.zza(this.zza, this.zzb);
        }
    }
}
