package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzflk {
    final /* synthetic */ zzfll zza;
    private final Object zzb;
    private final String zzc;
    private final ListenableFuture zzd;
    private final List zze;
    private final ListenableFuture zzf;

    private zzflk(zzfll zzfll, Object obj, String str, ListenableFuture listenableFuture, List list, ListenableFuture listenableFuture2) {
        this.zza = zzfll;
        this.zzb = obj;
        this.zzc = str;
        this.zzd = listenableFuture;
        this.zze = list;
        this.zzf = listenableFuture2;
    }

    /* synthetic */ zzflk(zzfll zzfll, Object obj, String str, ListenableFuture listenableFuture, List list, ListenableFuture listenableFuture2, zzflj zzflj) {
        this(zzfll, obj, (String) null, listenableFuture, list, listenableFuture2);
    }

    public final zzfky zza() {
        Object obj = this.zzb;
        String str = this.zzc;
        if (str == null) {
            str = this.zza.zzf(obj);
        }
        zzfky zzfky = new zzfky(obj, str, this.zzf);
        this.zza.zzd.zza(zzfky);
        this.zzd.addListener(new zzflh(this, zzfky), zzcci.zzf);
        zzgft.zzr(zzfky, new zzfli(this, zzfky), zzcci.zzf);
        return zzfky;
    }

    public final zzflk zzb(Object obj) {
        return this.zza.zzb(obj, zza());
    }

    public final zzflk zzc(Class cls, zzgfa zzgfa) {
        return new zzflk(this.zza, this.zzb, this.zzc, this.zzd, this.zze, zzgft.zzf(this.zzf, cls, zzgfa, this.zza.zzb));
    }

    public final zzflk zzd(ListenableFuture listenableFuture) {
        return zzg(new zzflg(listenableFuture), zzcci.zzf);
    }

    public final zzflk zze(zzfkw zzfkw) {
        return zzf(new zzflf(zzfkw));
    }

    public final zzflk zzf(zzgfa zzgfa) {
        return zzg(zzgfa, this.zza.zzb);
    }

    public final zzflk zzg(zzgfa zzgfa, Executor executor) {
        return new zzflk(this.zza, this.zzb, this.zzc, this.zzd, this.zze, zzgft.zzn(this.zzf, zzgfa, executor));
    }

    public final zzflk zzh(String str) {
        return new zzflk(this.zza, this.zzb, str, this.zzd, this.zze, this.zzf);
    }

    public final zzflk zzi(long j, TimeUnit timeUnit) {
        return new zzflk(this.zza, this.zzb, this.zzc, this.zzd, this.zze, zzgft.zzo(this.zzf, j, timeUnit, this.zza.zzc));
    }
}
