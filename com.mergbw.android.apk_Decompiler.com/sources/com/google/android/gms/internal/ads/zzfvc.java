package com.google.android.gms.internal.ads;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfvc implements SensorEventListener {
    @Nullable
    final zzfuw zza = zzfuy.zza;

    protected zzfvc(String str, String str2) {
        zzfvb.zza();
        zzfqz zzfqz = zzfqz.UNKNOWN;
        zzfuy.zza();
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent sensorEvent) {
        zza(sensorEvent);
    }

    public abstract void zza(SensorEvent sensorEvent);
}
