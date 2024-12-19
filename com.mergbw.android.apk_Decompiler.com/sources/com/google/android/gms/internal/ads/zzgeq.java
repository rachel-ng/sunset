package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzgeq implements Runnable {
    public final /* synthetic */ zzges zza;
    public final /* synthetic */ ListenableFuture zzb;
    public final /* synthetic */ int zzc;

    public /* synthetic */ zzgeq(zzges zzges, ListenableFuture listenableFuture, int i) {
        this.zza = zzges;
        this.zzb = listenableFuture;
        this.zzc = i;
    }

    public final void run() {
        this.zza.zzw(this.zzb, this.zzc);
    }
}
