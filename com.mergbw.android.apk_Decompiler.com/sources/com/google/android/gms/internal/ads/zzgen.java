package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgen extends zzgep {
    zzgen(ListenableFuture listenableFuture, zzgfa zzgfa) {
        super(listenableFuture, zzgfa);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Object obj2) throws Exception {
        zzgfa zzgfa = (zzgfa) obj;
        ListenableFuture zza = zzgfa.zza(obj2);
        zzfyg.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzgfa);
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Object obj) {
        zzs((ListenableFuture) obj);
    }
}
