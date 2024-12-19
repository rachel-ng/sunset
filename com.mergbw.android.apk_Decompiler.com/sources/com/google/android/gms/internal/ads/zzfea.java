package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfea implements zzfek {
    private final zzfek zza;
    private zzcys zzb;

    public zzfea(zzfek zzfek) {
        this.zza = zzfek;
    }

    /* renamed from: zza */
    public final synchronized zzcys zzd() {
        return this.zzb;
    }

    public final synchronized ListenableFuture zzb(zzfel zzfel, zzfej zzfej, zzcys zzcys) {
        this.zzb = zzcys;
        if (zzfel.zza != null) {
            zzcvx zzb2 = this.zzb.zzb();
            return zzb2.zzi(zzb2.zzk(zzgft.zzh(zzfel.zza)));
        }
        return ((zzfdz) this.zza).zzb(zzfel, zzfej, zzcys);
    }

    public final /* bridge */ /* synthetic */ ListenableFuture zzc(zzfel zzfel, zzfej zzfej, Object obj) {
        return zzb(zzfel, zzfej, (zzcys) null);
    }
}
