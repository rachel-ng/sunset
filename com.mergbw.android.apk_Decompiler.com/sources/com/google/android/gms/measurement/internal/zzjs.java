package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjs implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zziv zzb;

    zzjs(zziv zziv, AtomicReference atomicReference) {
        this.zza = atomicReference;
        this.zzb = zziv;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                this.zza.set(this.zzb.zze().zzf(this.zzb.zzg().zzad()));
                this.zza.notify();
            } catch (Throwable th) {
                this.zza.notify();
                throw th;
            }
        }
    }
}
