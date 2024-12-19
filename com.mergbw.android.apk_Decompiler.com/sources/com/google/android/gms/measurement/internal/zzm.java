package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdg;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
final class zzm implements Runnable {
    private final /* synthetic */ zzdg zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzm(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdg zzdg, String str, String str2) {
        this.zza = zzdg;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
