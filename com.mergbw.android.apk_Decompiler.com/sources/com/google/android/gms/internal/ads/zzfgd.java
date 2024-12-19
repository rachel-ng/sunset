package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zze;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfgd implements zzgfp {
    final /* synthetic */ zzepr zza;
    final /* synthetic */ zzfmn zzb;
    final /* synthetic */ zzfmc zzc;
    final /* synthetic */ zzfgf zzd;
    final /* synthetic */ zzfgg zze;

    zzfgd(zzfgg zzfgg, zzepr zzepr, zzfmn zzfmn, zzfmc zzfmc, zzfgf zzfgf) {
        this.zza = zzepr;
        this.zzb = zzfmn;
        this.zzc = zzfmc;
        this.zzd = zzfgf;
        this.zze = zzfgg;
    }

    public final void zza(Throwable th) {
        zze zze2;
        zzfmn zzfmn;
        zzdrm zzdrm = (zzdrm) this.zze.zze.zzd();
        if (zzdrm == null) {
            zze2 = zzfiq.zzb(th, (zzehq) null);
        } else {
            zze2 = zzdrm.zzb().zza(th);
        }
        synchronized (this.zze) {
            if (zzdrm != null) {
                zzdrm.zza().zzdB(zze2);
                this.zze.zzb.execute(new zzfgb(this, zze2));
            } else {
                this.zze.zzd.zzdB(zze2);
                this.zze.zzk(this.zzd).zze().zzb().zzc().zzh();
            }
            zzfil.zzb(zze2.zza, th, "RewardedAdLoader.onFailure");
            this.zza.zza();
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzg = this.zze.zzg;
                zzfmc zzfmc = this.zzc;
                zzfmc.zza(zze2);
                zzfmc.zzi(th);
                zzfmc.zzh(false);
                zzg.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzc(zze2);
                zzfmc zzfmc2 = this.zzc;
                zzfmc2.zzi(th);
                zzfmc2.zzh(false);
                zzfmn.zza(zzfmc2);
                zzfmn.zzi();
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzfmn zzfmn;
        zzdrh zzdrh = (zzdrh) obj;
        synchronized (this.zze) {
            zzdrh.zzn().zzd(this.zze.zzd);
            this.zza.zzb(zzdrh);
            zzfgg zzfgg = this.zze;
            Executor zzh = zzfgg.zzb;
            zzffw zzf = zzfgg.zzd;
            Objects.requireNonNull(zzf);
            zzh.execute(new zzfgc(zzf));
            this.zze.zzd.onAdMetadataChanged();
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzg = this.zze.zzg;
                zzfmc zzfmc = this.zzc;
                zzfmc.zzb(zzdrh.zzp().zzb);
                zzfmc.zze(zzdrh.zzl().zzg());
                zzfmc.zzh(true);
                zzg.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzh(zzdrh.zzp().zzb);
                zzfmn.zzf(zzdrh.zzl().zzg());
                zzfmc zzfmc2 = this.zzc;
                zzfmc2.zzh(true);
                zzfmn.zza(zzfmc2);
                zzfmn.zzi();
            }
        }
    }
}
