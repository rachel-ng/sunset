package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzma implements Runnable {
    private final /* synthetic */ zzlw zza;

    zzma(zzlw zzlw) {
        this.zza = zzlw;
    }

    public final void run() {
        zzkx.zza(this.zza.zza, new ComponentName(this.zza.zza.zza(), "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
