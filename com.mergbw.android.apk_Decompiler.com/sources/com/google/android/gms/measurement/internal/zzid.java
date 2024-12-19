package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzid implements Runnable {
    private final /* synthetic */ zzbd zza;
    private final /* synthetic */ zzo zzb;
    private final /* synthetic */ zzhn zzc;

    zzid(zzhn zzhn, zzbd zzbd, zzo zzo) {
        this.zza = zzbd;
        this.zzb = zzo;
        this.zzc = zzhn;
    }

    public final void run() {
        this.zzc.zzc(this.zzc.zzb(this.zza, this.zzb), this.zzb);
    }
}
