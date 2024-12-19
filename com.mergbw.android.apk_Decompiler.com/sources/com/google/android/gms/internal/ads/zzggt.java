package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzggt extends zzgfj implements RunnableFuture {
    @CheckForNull
    private volatile zzggc zza;

    zzggt(zzgez zzgez) {
        this.zza = new zzggr(this, zzgez);
    }

    static zzggt zze(Runnable runnable, Object obj) {
        return new zzggt(Executors.callable(runnable, obj));
    }

    public final void run() {
        zzggc zzggc = this.zza;
        if (zzggc != null) {
            zzggc.run();
        }
        this.zza = null;
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        zzggc zzggc = this.zza;
        if (zzggc == null) {
            return super.zza();
        }
        String obj = zzggc.toString();
        return "task=[" + obj + "]";
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzggc zzggc;
        if (zzt() && (zzggc = this.zza) != null) {
            zzggc.zzh();
        }
        this.zza = null;
    }

    zzggt(Callable callable) {
        this.zza = new zzggs(this, callable);
    }
}
