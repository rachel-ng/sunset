package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzggj extends zzggg implements ScheduledExecutorService, zzgge {
    final ScheduledExecutorService zza;

    zzggj(ScheduledExecutorService scheduledExecutorService) {
        super(scheduledExecutorService);
        scheduledExecutorService.getClass();
        this.zza = scheduledExecutorService;
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        ScheduledExecutorService scheduledExecutorService = this.zza;
        zzggt zze = zzggt.zze(runnable, (Object) null);
        return new zzggh(zze, scheduledExecutorService.schedule(zze, j, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzggi zzggi = new zzggi(runnable);
        return new zzggh(zzggi, this.zza.scheduleAtFixedRate(zzggi, j, j2, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        zzggi zzggi = new zzggi(runnable);
        return new zzggh(zzggi, this.zza.scheduleWithFixedDelay(zzggi, j, j2, timeUnit));
    }

    public final /* bridge */ /* synthetic */ ScheduledFuture schedule(Callable callable, long j, TimeUnit timeUnit) {
        zzggt zzggt = new zzggt(callable);
        return new zzggh(zzggt, this.zza.schedule(zzggt, j, timeUnit));
    }
}
