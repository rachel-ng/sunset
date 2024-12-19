package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgfx implements ListenableFuture {
    static final ListenableFuture zza = new zzgfx((Object) null);
    private static final zzggd zzb = new zzggd(zzgfx.class);
    private final Object zzc;

    zzgfx(Object obj) {
        this.zzc = obj;
    }

    public final void addListener(Runnable runnable, Executor executor) {
        zzfyg.zzc(runnable, "Runnable was null.");
        zzfyg.zzc(executor, "Executor was null.");
        try {
            executor.execute(runnable);
        } catch (Exception e) {
            Logger zza2 = zzb.zza();
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(runnable);
            String valueOf2 = String.valueOf(executor);
            zza2.logp(level, "com.google.common.util.concurrent.ImmediateFuture", "addListener", "RuntimeException while executing runnable " + valueOf + " with executor " + valueOf2, e);
        }
    }

    public final boolean cancel(boolean z) {
        return false;
    }

    public final Object get() {
        return this.zzc;
    }

    public final Object get(long j, TimeUnit timeUnit) throws ExecutionException {
        timeUnit.getClass();
        return this.zzc;
    }

    public final boolean isCancelled() {
        return false;
    }

    public final boolean isDone() {
        return true;
    }

    public final String toString() {
        Object obj = this.zzc;
        String obj2 = super.toString();
        String valueOf = String.valueOf(obj);
        return obj2 + "[status=SUCCESS, result=[" + valueOf + "]]";
    }
}
