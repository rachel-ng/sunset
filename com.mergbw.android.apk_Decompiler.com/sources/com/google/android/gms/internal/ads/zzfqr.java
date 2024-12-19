package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqr {
    private final BlockingQueue zza;
    private final ThreadPoolExecutor zzb;
    private final ArrayDeque zzc = new ArrayDeque();
    private zzfqq zzd = null;

    public zzfqr() {
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        this.zza = linkedBlockingQueue;
        this.zzb = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, linkedBlockingQueue);
    }

    private final void zzc() {
        zzfqq zzfqq = (zzfqq) this.zzc.poll();
        this.zzd = zzfqq;
        if (zzfqq != null) {
            zzfqq.executeOnExecutor(this.zzb, new Object[0]);
        }
    }

    public final void zza(zzfqq zzfqq) {
        this.zzd = null;
        zzc();
    }

    public final void zzb(zzfqq zzfqq) {
        zzfqq.zzb(this);
        this.zzc.add(zzfqq);
        if (this.zzd == null) {
            zzc();
        }
    }
}
