package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfkd implements zzgfa {
    public final /* synthetic */ zzfke zza;
    public final /* synthetic */ zzfjw zzb;

    public /* synthetic */ zzfkd(zzfke zzfke, zzfjw zzfjw) {
        this.zza = zzfke;
        this.zzb = zzfjw;
    }

    public final ListenableFuture zza(Object obj) {
        return this.zza.zzc(this.zzb, (Exception) obj);
    }
}
