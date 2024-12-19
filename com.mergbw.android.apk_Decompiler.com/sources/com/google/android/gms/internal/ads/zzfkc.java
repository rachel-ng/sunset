package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfkc implements zzgfa {
    public final /* synthetic */ zzfke zza;
    public final /* synthetic */ zzfjw zzb;
    public final /* synthetic */ zzfjc zzc;
    public final /* synthetic */ zzfjx zzd;

    public /* synthetic */ zzfkc(zzfke zzfke, zzfjw zzfjw, zzfjc zzfjc, zzfjx zzfjx) {
        this.zza = zzfke;
        this.zzb = zzfjw;
        this.zzc = zzfjc;
        this.zzd = zzfjx;
    }

    public final ListenableFuture zza(Object obj) {
        return this.zza.zzb(this.zzb, this.zzc, this.zzd, (zzfjl) obj);
    }
}
