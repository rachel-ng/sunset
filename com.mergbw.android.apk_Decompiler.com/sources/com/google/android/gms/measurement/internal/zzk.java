package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzdg;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@22.0.2 */
final class zzk implements Runnable {
    private final /* synthetic */ zzdg zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ AppMeasurementDynamiteService zze;

    zzk(AppMeasurementDynamiteService appMeasurementDynamiteService, zzdg zzdg, String str, String str2, boolean z) {
        this.zza = zzdg;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = z;
        this.zze = appMeasurementDynamiteService;
    }

    public final void run() {
        this.zze.zza.zzr().zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
