package com.google.android.gms.measurement.internal;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzji implements Executor {
    private final /* synthetic */ zziv zza;

    zzji(zziv zziv) {
        this.zza = zziv;
    }

    public final void execute(Runnable runnable) {
        this.zza.zzl().zzb(runnable);
    }
}
