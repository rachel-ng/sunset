package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfdw implements zzfek {
    private final zzfjc zza;
    private final Executor zzb;
    private final zzgfp zzc = new zzfdu(this);

    public zzfdw(zzfjc zzfjc, Executor executor) {
        this.zza = zzfjc;
        this.zzb = executor;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ListenableFuture zza(zzcys zzcys, zzfef zzfef) throws Exception {
        zzfjc zzfjc = this.zza;
        zzfjm zzfjm = zzfef.zzb;
        zzbxu zzbxu = zzfef.zza;
        zzfjl zzb2 = zzfjc.zzb(zzfjm);
        if (!(zzb2 == null || zzbxu == null)) {
            zzgft.zzr(zzcys.zzb().zzh(zzbxu), this.zzc, this.zzb);
        }
        return zzgft.zzh(new zzfdv(zzfjm, zzbxu, zzb2));
    }

    public final ListenableFuture zzb(zzfel zzfel, zzfej zzfej, zzcys zzcys) {
        return zzgft.zze(zzgft.zzn(zzgfk.zzu(new zzfeg(this.zza, zzcys, this.zzb).zzc()), new zzfds(this, zzcys), this.zzb), Exception.class, new zzfdt(this), this.zzb);
    }

    public final /* bridge */ /* synthetic */ ListenableFuture zzc(zzfel zzfel, zzfej zzfej, Object obj) {
        return zzb(zzfel, zzfej, (zzcys) null);
    }

    public final /* bridge */ /* synthetic */ Object zzd() {
        return null;
    }
}
