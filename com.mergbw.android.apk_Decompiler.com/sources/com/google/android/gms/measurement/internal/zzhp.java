package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzhp implements Runnable {
    private /* synthetic */ zzhn zza;
    private /* synthetic */ zzo zzb;

    public /* synthetic */ zzhp(zzhn zzhn, zzo zzo) {
        this.zza = zzhn;
        this.zzb = zzo;
    }

    public final void run() {
        this.zza.zzi(this.zzb);
    }
}
