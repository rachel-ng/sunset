package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zzfll {
    /* access modifiers changed from: private */
    public static final ListenableFuture zza = zzgft.zzh((Object) null);
    /* access modifiers changed from: private */
    public final zzgge zzb;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService zzc;
    /* access modifiers changed from: private */
    public final zzflm zzd;

    public zzfll(zzgge zzgge, ScheduledExecutorService scheduledExecutorService, zzflm zzflm) {
        this.zzb = zzgge;
        this.zzc = scheduledExecutorService;
        this.zzd = zzflm;
    }

    public final zzflb zza(Object obj, ListenableFuture... listenableFutureArr) {
        return new zzflb(this, obj, Arrays.asList(listenableFutureArr), (zzfla) null);
    }

    public final zzflk zzb(Object obj, ListenableFuture listenableFuture) {
        return new zzflk(this, obj, (String) null, listenableFuture, Collections.singletonList(listenableFuture), listenableFuture, (zzflj) null);
    }

    /* access modifiers changed from: protected */
    public abstract String zzf(Object obj);
}
