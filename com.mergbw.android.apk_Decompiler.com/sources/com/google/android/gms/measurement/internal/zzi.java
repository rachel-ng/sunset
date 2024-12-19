package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdg;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
final class zzi implements Runnable {
    private final /* synthetic */ zzdg zza;
    private final /* synthetic */ AppMeasurementDynamiteService zzb;

    zzi(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdg zzdg) {
        this.zza = zzdg;
        this.zzb = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zzb.zza.zzr().zza(this.zza);
    }
}
