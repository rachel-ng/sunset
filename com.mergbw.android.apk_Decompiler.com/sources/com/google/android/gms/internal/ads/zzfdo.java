package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfdo implements zzfjw {
    private final zzfek zza;

    public zzfdo(zzfek zzfek) {
        this.zza = zzfek;
    }

    public final ListenableFuture zza(zzfjx zzfjx) {
        zzfdp zzfdp = (zzfdp) zzfjx;
        return ((zzfdl) this.zza).zzb(zzfdp.zzb, zzfdp.zza, (zzcys) null);
    }

    public final void zzb(zzfjl zzfjl) {
        zzfjl.zza = ((zzfdl) this.zza).zza();
    }
}
