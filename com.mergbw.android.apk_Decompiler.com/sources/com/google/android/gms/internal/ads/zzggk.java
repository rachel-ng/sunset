package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzggk {
    public static zzgge zza(ExecutorService executorService) {
        zzgge zzgge;
        if (executorService instanceof zzgge) {
            return (zzgge) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            zzgge = new zzggj((ScheduledExecutorService) executorService);
        } else {
            zzgge = new zzggg(executorService);
        }
        return zzgge;
    }

    public static Executor zzb() {
        return zzgfh.INSTANCE;
    }

    static Executor zzc(Executor executor, zzgeh zzgeh) {
        executor.getClass();
        if (executor == zzgfh.INSTANCE) {
            return executor;
        }
        return new zzggf(executor, zzgeh);
    }
}
