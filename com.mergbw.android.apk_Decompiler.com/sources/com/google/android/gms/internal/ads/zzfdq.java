package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.internal.ads.zzbdv;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfdq implements zzfek {
    private final zzfek zza;
    private final zzfek zzb;
    private final zzfjy zzc;
    private final String zzd;
    private zzcys zze;
    private final Executor zzf;

    public zzfdq(zzfek zzfek, zzfek zzfek2, zzfjy zzfjy, String str, Executor executor) {
        this.zza = zzfek;
        this.zzb = zzfek2;
        this.zzc = zzfjy;
        this.zzd = str;
        this.zzf = executor;
    }

    private final ListenableFuture zzg(zzfjl zzfjl, zzfel zzfel) {
        zzcys zzcys = zzfjl.zza;
        this.zze = zzcys;
        if (zzfjl.zzc != null) {
            if (zzcys.zzf() != null) {
                zzfjl.zzc.zzo().zzl(zzfjl.zza.zzf());
            }
            return zzgft.zzh(zzfjl.zzc);
        }
        zzcys.zzb().zzl(zzfjl.zzb);
        return ((zzfea) this.zza).zzb(zzfel, (zzfej) null, zzfjl.zza);
    }

    /* renamed from: zza */
    public final synchronized zzcys zzd() {
        return this.zze;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ListenableFuture zzb(zzfel zzfel, zzfdp zzfdp, zzfej zzfej, zzcys zzcys, zzfdv zzfdv) throws Exception {
        if (zzfdv != null) {
            zzfdp zzfdp2 = new zzfdp(zzfdp.zza, zzfdp.zzb, zzfdp.zzc, zzfdp.zzd, zzfdp.zze, zzfdp.zzf, zzfdv.zza);
            if (zzfdv.zzc != null) {
                this.zze = null;
                this.zzc.zze(zzfdp2);
                return zzg(zzfdv.zzc, zzfel);
            }
            ListenableFuture zza2 = this.zzc.zza(zzfdp2);
            if (zza2 != null) {
                this.zze = null;
                return zzgft.zzn(zza2, new zzfdm(this), this.zzf);
            }
            this.zzc.zze(zzfdp2);
            zzfel = new zzfel(zzfel.zzb, zzfdv.zzb);
        }
        ListenableFuture zzb2 = ((zzfea) this.zza).zzb(zzfel, zzfej, zzcys);
        this.zze = zzcys;
        return zzb2;
    }

    public final /* bridge */ /* synthetic */ ListenableFuture zzc(zzfel zzfel, zzfej zzfej, Object obj) {
        return zzf(zzfel, zzfej, (zzcys) null);
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ListenableFuture zze(zzfjv zzfjv) throws Exception {
        zzfjx zzfjx;
        if (zzfjv == null || zzfjv.zza == null || (zzfjx = zzfjv.zzb) == null) {
            throw new zzdzd(1, "Empty prefetch");
        }
        zzbdv.zzb.zzc zzd2 = zzbdv.zzb.zzd();
        zzbdv.zzb.zza.C0004zza zza2 = zzbdv.zzb.zza.zza();
        zza2.zzf(zzbdv.zzb.zzd.IN_MEMORY);
        zza2.zzh(zzbdv.zzb.zze.zzi());
        zzd2.zzd(zza2);
        zzfjv.zza.zza.zzb().zzc().zzm((zzbdv.zzb) zzd2.zzbr());
        return zzg(zzfjv.zza, ((zzfdp) zzfjx).zzb);
    }

    public final synchronized ListenableFuture zzf(zzfel zzfel, zzfej zzfej, zzcys zzcys) {
        zzfel zzfel2 = zzfel;
        zzfej zzfej2 = zzfej;
        synchronized (this) {
            zzcyr zza2 = zzfej2.zza(zzfel2.zzb);
            zza2.zza(new zzfdr(this.zzd));
            zzcys zzcys2 = (zzcys) zza2.zzh();
            zzcys2.zzg();
            zzcys2.zzg();
            zzl zzl = zzcys2.zzg().zzd;
            if (zzl.zzs == null) {
                if (zzl.zzx == null) {
                    zzfho zzg = zzcys2.zzg();
                    zzfej zzfej3 = zzfej;
                    zzfel zzfel3 = zzfel;
                    ListenableFuture zzn = zzgft.zzn(zzgfk.zzu(((zzfdw) this.zzb).zzb(zzfel2, zzfej2, zzcys2)), new zzfdn(this, zzfel, new zzfdp(zzfej3, zzfel3, zzg.zzd, zzg.zzf, this.zzf, zzg.zzj, (zzfjm) null), zzfej, zzcys2), this.zzf);
                    return zzn;
                }
            }
            this.zze = zzcys2;
            ListenableFuture zzb2 = ((zzfea) this.zza).zzb(zzfel2, zzfej2, zzcys2);
            return zzb2;
        }
    }
}
