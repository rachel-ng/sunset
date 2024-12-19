package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfdl implements zzfek {
    private zzcys zza;
    private final Executor zzb = zzggk.zzb();

    public final zzcys zza() {
        return this.zza;
    }

    public final ListenableFuture zzb(zzfel zzfel, zzfej zzfej, zzcys zzcys) {
        zzcyr zza2 = zzfej.zza(zzfel.zzb);
        zza2.zzb(new zzfeo(true));
        zzcys zzcys2 = (zzcys) zza2.zzh();
        this.zza = zzcys2;
        zzcvx zzb2 = zzcys2.zzb();
        zzfjl zzfjl = new zzfjl();
        return zzgft.zzm(zzgft.zzn(zzgfk.zzu(zzb2.zzj()), new zzfdj(this, zzfjl, zzb2), this.zzb), new zzfdk(zzfjl), this.zzb);
    }

    public final /* bridge */ /* synthetic */ ListenableFuture zzc(zzfel zzfel, zzfej zzfej, Object obj) {
        return zzb(zzfel, zzfej, (zzcys) null);
    }

    public final /* synthetic */ Object zzd() {
        return this.zza;
    }
}
