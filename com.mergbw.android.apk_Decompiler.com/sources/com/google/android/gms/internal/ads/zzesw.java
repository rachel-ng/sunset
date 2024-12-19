package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzesw implements zzexw {
    private final zzgge zza;

    zzesw(zzgge zzgge) {
        this.zza = zzgge;
    }

    public final int zza() {
        return 55;
    }

    public final ListenableFuture zzb() {
        return this.zza.zzb(new zzesv(this));
    }
}