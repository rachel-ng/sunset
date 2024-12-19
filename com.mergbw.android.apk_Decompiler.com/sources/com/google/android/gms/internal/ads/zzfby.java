package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfby implements zzgfp {
    final /* synthetic */ zzepr zza;
    final /* synthetic */ zzfmn zzb;
    final /* synthetic */ zzfmc zzc;
    final /* synthetic */ zzfca zzd;
    final /* synthetic */ zzfcb zze;

    zzfby(zzfcb zzfcb, zzepr zzepr, zzfmn zzfmn, zzfmc zzfmc, zzfca zzfca) {
        this.zza = zzepr;
        this.zzb = zzfmn;
        this.zzc = zzfmc;
        this.zzd = zzfca;
        this.zze = zzfcb;
    }

    /* JADX WARNING: type inference failed for: r0v17, types: [com.google.android.gms.internal.ads.zzcrp, java.lang.Object] */
    public final void zza(Throwable th) {
        zze zze2;
        zzfmn zzfmn;
        zzcrp zzcrp = (zzcrp) this.zze.zze.zzd();
        if (zzcrp == null) {
            zze2 = zzfiq.zzb(th, (zzehq) null);
        } else {
            zze2 = zzcrp.zzb().zza(th);
        }
        synchronized (this.zze) {
            this.zze.zzj = null;
            if (zzcrp != null) {
                zzcrp.zzc().zzdB(zze2);
                if (((Boolean) zzba.zzc().zza(zzbep.zzil)).booleanValue()) {
                    this.zze.zzc.execute(new zzfbx(this, zze2));
                }
            } else {
                this.zze.zzd.zzdB(zze2);
                this.zze.zzm(this.zzd).zzh().zzb().zzc().zzh();
            }
            zzfil.zzb(zze2.zza, th, "AppOpenAdLoader.onFailure");
            this.zza.zza();
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzh = this.zze.zzh;
                zzfmc zzfmc = this.zzc;
                zzfmc.zza(zze2);
                zzfmc.zzi(th);
                zzfmc.zzh(false);
                zzh.zzb(zzfmc.zzn());
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
        zzcup zzcup = (zzcup) obj;
        synchronized (this.zze) {
            this.zze.zzj = null;
            if (((Boolean) zzba.zzc().zza(zzbep.zzil)).booleanValue()) {
                zzcup.zzn().zzb(this.zze.zzd);
            }
            this.zza.zzb(zzcup);
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzh = this.zze.zzh;
                zzfmc zzfmc = this.zzc;
                zzfmc.zzb(zzcup.zzp().zzb);
                zzfmc.zze(zzcup.zzl().zzg());
                zzfmc.zzh(true);
                zzh.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzh(zzcup.zzp().zzb);
                zzfmn.zzf(zzcup.zzl().zzg());
                zzfmc zzfmc2 = this.zzc;
                zzfmc2.zzh(true);
                zzfmn.zza(zzfmc2);
                zzfmn.zzi();
            }
        }
    }
}
