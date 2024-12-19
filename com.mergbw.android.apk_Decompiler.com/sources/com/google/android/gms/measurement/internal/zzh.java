package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdg;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
final class zzh implements Runnable {
    private final /* synthetic */ zzdg zza;
    private final /* synthetic */ zzbd zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    zzh(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdg zzdg, zzbd zzbd, String str) {
        this.zza = zzdg;
        this.zzb = zzbd;
        this.zzc = str;
        this.zzd = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzd.zza.zzr().zza(this.zza, this.zzb, this.zzc);
    }
}
