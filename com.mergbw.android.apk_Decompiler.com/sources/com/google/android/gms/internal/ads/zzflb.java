package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzflb {
    final /* synthetic */ zzfll zza;
    private final Object zzb;
    private final List zzc;

    /* synthetic */ zzflb(zzfll zzfll, Object obj, List list, zzfla zzfla) {
        this.zza = zzfll;
        this.zzb = obj;
        this.zzc = list;
    }

    public final zzflk zza(Callable callable) {
        zzgfs zzb2 = zzgft.zzb(this.zzc);
        ListenableFuture zza2 = zzb2.zza(new zzfkz(), zzcci.zzf);
        ListenableFuture zza3 = zzb2.zza(callable, this.zza.zzb);
        return new zzflk(this.zza, this.zzb, (String) null, zza2, this.zzc, zza3, (zzflj) null);
    }
}
