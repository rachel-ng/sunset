package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzja implements Runnable {
    private /* synthetic */ zziv zza;
    private /* synthetic */ AtomicReference zzb;

    public /* synthetic */ zzja(zziv zziv, AtomicReference atomicReference) {
        this.zza = zziv;
        this.zzb = atomicReference;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
