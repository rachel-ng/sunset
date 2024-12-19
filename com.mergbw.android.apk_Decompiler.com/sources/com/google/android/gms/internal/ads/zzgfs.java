package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgfs {
    private final boolean zza;
    private final zzgbc zzb;

    /* synthetic */ zzgfs(boolean z, zzgbc zzgbc, zzgfr zzgfr) {
        this.zza = z;
        this.zzb = zzgbc;
    }

    public final ListenableFuture zza(Callable callable, Executor executor) {
        return new zzgfg(this.zzb, this.zza, executor, callable);
    }
}
