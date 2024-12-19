package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzged extends zzgef {
    zzged(ListenableFuture listenableFuture, Class cls, zzgfa zzgfa) {
        super(listenableFuture, cls, zzgfa);
    }

    /* access modifiers changed from: package-private */
    public final /* bridge */ /* synthetic */ Object zze(Object obj, Throwable th) throws Exception {
        zzgfa zzgfa = (zzgfa) obj;
        ListenableFuture zza = zzgfa.zza(th);
        zzfyg.zzd(zza, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", zzgfa);
        return zza;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzf(Object obj) {
        zzs((ListenableFuture) obj);
    }
}
