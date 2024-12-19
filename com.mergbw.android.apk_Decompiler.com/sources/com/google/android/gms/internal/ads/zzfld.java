package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfld {
    public static final zzflk zza(Callable callable, Object obj, zzfll zzfll) {
        return zzb(callable, zzfll.zzb, obj, zzfll);
    }

    public static final zzflk zzb(Callable callable, zzgge zzgge, Object obj, zzfll zzfll) {
        return new zzflk(zzfll, obj, (String) null, zzfll.zza, Collections.emptyList(), zzgge.zzb(callable), (zzflj) null);
    }

    public static final zzflk zzc(ListenableFuture listenableFuture, Object obj, zzfll zzfll) {
        return new zzflk(zzfll, obj, (String) null, zzfll.zza, Collections.emptyList(), listenableFuture, (zzflj) null);
    }

    public static final zzflk zzd(zzfkx zzfkx, zzgge zzgge, Object obj, zzfll zzfll) {
        return zzb(new zzflc(zzfkx), zzgge, obj, zzfll);
    }
}
