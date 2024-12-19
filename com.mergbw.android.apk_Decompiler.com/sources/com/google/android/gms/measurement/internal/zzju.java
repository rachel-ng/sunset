package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzju implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zziv zzb;

    zzju(zziv zziv, long j) {
        this.zza = j;
        this.zzb = zziv;
    }

    public final void run() {
        this.zzb.zzb(this.zza);
        this.zzb.zzo().zza((AtomicReference<String>) new AtomicReference());
    }
}
