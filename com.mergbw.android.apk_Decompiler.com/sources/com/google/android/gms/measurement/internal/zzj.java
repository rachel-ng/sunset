package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.AppMeasurementDynamiteService;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
final class zzj implements Runnable {
    private final /* synthetic */ AppMeasurementDynamiteService.zzb zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzj(AppMeasurementDynamiteService appMeasurementDynamiteService, AppMeasurementDynamiteService.zzb zzb2) {
        this.zza = zzb2;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzp().zza((zzir) this.zza);
    }
}
