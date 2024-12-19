package com.google.android.gms.measurement.internal;

import android.content.ComponentName;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzly implements Runnable {
    private final /* synthetic */ ComponentName zza;
    private final /* synthetic */ zzlw zzb;

    zzly(zzlw zzlw, ComponentName componentName) {
        this.zza = componentName;
        this.zzb = zzlw;
    }

    public final void run() {
        zzkx.zza(this.zzb.zza, this.zza);
    }
}
