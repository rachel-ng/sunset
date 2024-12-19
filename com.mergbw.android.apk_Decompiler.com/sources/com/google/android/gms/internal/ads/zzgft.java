package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgft extends zzgfv {
    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzgfs zza(java.lang.Iterable r3) {
        /*
            com.google.android.gms.internal.ads.zzgfs r0 = new com.google.android.gms.internal.ads.zzgfs
            com.google.android.gms.internal.ads.zzgbc r3 = com.google.android.gms.internal.ads.zzgbc.zzk(r3)
            r1 = 0
            r2 = 0
            r0.<init>(r2, r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgft.zza(java.lang.Iterable):com.google.android.gms.internal.ads.zzgfs");
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Collection, java.lang.Object, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzgfs zzb(java.lang.Iterable r3) {
        /*
            com.google.android.gms.internal.ads.zzgfs r0 = new com.google.android.gms.internal.ads.zzgfs
            int r1 = com.google.android.gms.internal.ads.zzgbc.zzd
            r3.getClass()
            com.google.android.gms.internal.ads.zzgbc r3 = com.google.android.gms.internal.ads.zzgbc.zzk(r3)
            r1 = 0
            r2 = 1
            r0.<init>(r2, r3, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgft.zzb(java.lang.Iterable):com.google.android.gms.internal.ads.zzgfs");
    }

    @SafeVarargs
    public static zzgfs zzc(ListenableFuture... listenableFutureArr) {
        return new zzgfs(true, zzgbc.zzl(listenableFutureArr), (zzgfr) null);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.Collection, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.common.util.concurrent.ListenableFuture zzd(java.lang.Iterable r2) {
        /*
            com.google.android.gms.internal.ads.zzgfb r0 = new com.google.android.gms.internal.ads.zzgfb
            com.google.android.gms.internal.ads.zzgbc r2 = com.google.android.gms.internal.ads.zzgbc.zzk(r2)
            r1 = 1
            r0.<init>(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgft.zzd(java.lang.Iterable):com.google.common.util.concurrent.ListenableFuture");
    }

    public static ListenableFuture zze(ListenableFuture listenableFuture, Class cls, zzfxu zzfxu, Executor executor) {
        zzgee zzgee = new zzgee(listenableFuture, cls, zzfxu);
        listenableFuture.addListener(zzgee, zzggk.zzc(executor, zzgee));
        return zzgee;
    }

    public static ListenableFuture zzf(ListenableFuture listenableFuture, Class cls, zzgfa zzgfa, Executor executor) {
        zzged zzged = new zzged(listenableFuture, cls, zzgfa);
        listenableFuture.addListener(zzged, zzggk.zzc(executor, zzged));
        return zzged;
    }

    public static ListenableFuture zzh(Object obj) {
        if (obj == null) {
            return zzgfx.zza;
        }
        return new zzgfx(obj);
    }

    public static ListenableFuture zzi() {
        return zzgfx.zza;
    }

    public static ListenableFuture zzj(Callable callable, Executor executor) {
        zzggt zzggt = new zzggt(callable);
        executor.execute(zzggt);
        return zzggt;
    }

    public static ListenableFuture zzk(zzgez zzgez, Executor executor) {
        zzggt zzggt = new zzggt(zzgez);
        executor.execute(zzggt);
        return zzggt;
    }

    @SafeVarargs
    public static ListenableFuture zzl(ListenableFuture... listenableFutureArr) {
        return new zzgfb(zzgbc.zzl(listenableFutureArr), false);
    }

    public static ListenableFuture zzm(ListenableFuture listenableFuture, zzfxu zzfxu, Executor executor) {
        zzgeo zzgeo = new zzgeo(listenableFuture, zzfxu);
        listenableFuture.addListener(zzgeo, zzggk.zzc(executor, zzgeo));
        return zzgeo;
    }

    public static ListenableFuture zzn(ListenableFuture listenableFuture, zzgfa zzgfa, Executor executor) {
        int i = zzgep.zzc;
        executor.getClass();
        zzgen zzgen = new zzgen(listenableFuture, zzgfa);
        listenableFuture.addListener(zzgen, zzggk.zzc(executor, zzgen));
        return zzgen;
    }

    public static ListenableFuture zzo(ListenableFuture listenableFuture, long j, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        if (listenableFuture.isDone()) {
            return listenableFuture;
        }
        return zzggq.zzf(listenableFuture, j, timeUnit, scheduledExecutorService);
    }

    public static Object zzp(Future future) throws ExecutionException {
        if (future.isDone()) {
            return zzggv.zza(future);
        }
        throw new IllegalStateException(zzfyv.zzb("Future was expected to be done: %s", future));
    }

    public static Object zzq(Future future) {
        try {
            return zzggv.zza(future);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof Error) {
                throw new zzgfi((Error) cause);
            }
            throw new zzggu(cause);
        }
    }

    public static ListenableFuture zzg(Throwable th) {
        th.getClass();
        return new zzgfw(th);
    }

    public static void zzr(ListenableFuture listenableFuture, zzgfp zzgfp, Executor executor) {
        zzgfp.getClass();
        listenableFuture.addListener(new zzgfq(listenableFuture, zzgfp), executor);
    }
}
