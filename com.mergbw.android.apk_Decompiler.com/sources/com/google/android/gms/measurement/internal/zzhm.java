package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzhm implements Runnable {
    private /* synthetic */ zzhn zza;
    private /* synthetic */ zzo zzb;

    public /* synthetic */ zzhm(zzhn zzhn, zzo zzo) {
        this.zza = zzhn;
        this.zzb = zzo;
    }

    public final void run() {
        this.zza.zzj(this.zzb);
    }
}
