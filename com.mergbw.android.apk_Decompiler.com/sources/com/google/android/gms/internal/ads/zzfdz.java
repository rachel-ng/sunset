package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfdz implements zzfek {
    private zzcys zza;

    /* renamed from: zza */
    public final synchronized zzcys zzd() {
        return this.zza;
    }

    public final /* bridge */ /* synthetic */ ListenableFuture zzc(zzfel zzfel, zzfej zzfej, Object obj) {
        return zzb(zzfel, zzfej, (zzcys) null);
    }

    public final synchronized ListenableFuture zzb(zzfel zzfel, zzfej zzfej, zzcys zzcys) {
        zzcvx zzb;
        if (zzcys != null) {
            this.zza = zzcys;
        } else {
            this.zza = (zzcys) zzfej.zza(zzfel.zzb).zzh();
        }
        zzb = this.zza.zzb();
        return zzb.zzi(zzb.zzj());
    }
}
