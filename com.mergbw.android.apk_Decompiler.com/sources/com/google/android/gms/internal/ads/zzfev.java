package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfev implements zzgfp {
    final /* synthetic */ zzepr zza;
    final /* synthetic */ zzfmn zzb;
    final /* synthetic */ zzfmc zzc;
    final /* synthetic */ zzdjh zzd;
    final /* synthetic */ zzfew zze;

    zzfev(zzfew zzfew, zzepr zzepr, zzfmn zzfmn, zzfmc zzfmc, zzdjh zzdjh) {
        this.zza = zzepr;
        this.zzb = zzfmn;
        this.zzc = zzfmc;
        this.zzd = zzdjh;
        this.zze = zzfew;
    }

    public final void zza(Throwable th) {
        zzfmn zzfmn;
        zze zza2 = this.zzd.zza().zza(th);
        synchronized (this.zze) {
            this.zze.zzi = null;
            this.zzd.zzb().zzdB(zza2);
            if (((Boolean) zzba.zzc().zza(zzbep.zzim)).booleanValue()) {
                this.zze.zzb.execute(new zzfer(this, zza2));
                this.zze.zzb.execute(new zzfes(this, zza2));
            }
            zzfil.zzb(zza2.zza, th, "InterstitialAdLoader.onFailure");
            this.zza.zza();
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zze2 = this.zze.zzg;
                zzfmc zzfmc = this.zzc;
                zzfmc.zza(zza2);
                zzfmc.zzi(th);
                zzfmc.zzh(false);
                zze2.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzc(zza2);
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
        zzdig zzdig = (zzdig) obj;
        synchronized (this.zze) {
            this.zze.zzi = null;
            if (((Boolean) zzba.zzc().zza(zzbep.zzim)).booleanValue()) {
                zzddq zzn = zzdig.zzn();
                zzn.zza(this.zze.zzd);
                zzn.zzd(this.zze.zze);
            }
            this.zza.zzb(zzdig);
            if (((Boolean) zzba.zzc().zza(zzbep.zzim)).booleanValue()) {
                this.zze.zzb.execute(new zzfet(this));
                this.zze.zzb.execute(new zzfeu(this));
            }
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zze2 = this.zze.zzg;
                zzfmc zzfmc = this.zzc;
                zzfmc.zzb(zzdig.zzp().zzb);
                zzfmc.zze(zzdig.zzl().zzg());
                zzfmc.zzh(true);
                zze2.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzh(zzdig.zzp().zzb);
                zzfmn.zzf(zzdig.zzl().zzg());
                zzfmc zzfmc2 = this.zzc;
                zzfmc2.zzh(true);
                zzfmn.zza(zzfmc2);
                zzfmn.zzi();
            }
        }
    }
}
