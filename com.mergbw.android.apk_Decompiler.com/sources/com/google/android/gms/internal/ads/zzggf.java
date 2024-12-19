package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzggf implements Executor {
    final /* synthetic */ Executor zza;
    final /* synthetic */ zzgeh zzb;

    zzggf(Executor executor, zzgeh zzgeh) {
        this.zza = executor;
        this.zzb = zzgeh;
    }

    public final void execute(Runnable runnable) {
        try {
            this.zza.execute(runnable);
        } catch (RejectedExecutionException e) {
            this.zzb.zzd(e);
        }
    }
}
