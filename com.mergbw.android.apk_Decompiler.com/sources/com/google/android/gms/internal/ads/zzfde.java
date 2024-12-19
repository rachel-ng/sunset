package com.google.android.gms.internal.ads;

import android.view.ViewGroup;
import android.view.ViewParent;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.util.client.zzm;
import java.util.Objects;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfde implements zzgfp {
    final /* synthetic */ zzepr zza;
    final /* synthetic */ zzfmn zzb;
    final /* synthetic */ zzfmc zzc;
    final /* synthetic */ zzctg zzd;
    final /* synthetic */ zzfdf zze;

    zzfde(zzfdf zzfdf, zzepr zzepr, zzfmn zzfmn, zzfmc zzfmc, zzctg zzctg) {
        this.zza = zzepr;
        this.zzb = zzfmn;
        this.zzc = zzfmc;
        this.zzd = zzctg;
        this.zze = zzfdf;
    }

    public final void zza(Throwable th) {
        zzfmn zzfmn;
        zze zza2 = this.zzd.zzd().zza(th);
        synchronized (this.zze) {
            this.zze.zzl = null;
            this.zzd.zzf().zzdB(zza2);
            if (((Boolean) zzba.zzc().zza(zzbep.zzik)).booleanValue()) {
                this.zze.zzb.execute(new zzfdc(this, zza2));
            }
            zzfdf zzfdf = this.zze;
            zzfdf.zzh.zzd(zzfdf.zzj.zzc());
            zzfil.zzb(zza2.zza, th, "BannerAdLoader.onFailure");
            this.zza.zza();
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzj = this.zze.zzi;
                zzfmc zzfmc = this.zzc;
                zzfmc.zza(zza2);
                zzfmc.zzi(th);
                zzfmc.zzh(false);
                zzj.zzb(zzfmc.zzn());
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
        zzcsf zzcsf = (zzcsf) obj;
        synchronized (this.zze) {
            this.zze.zzl = null;
            this.zze.zzf.removeAllViews();
            if (zzcsf.zzc() != null) {
                ViewParent parent = zzcsf.zzc().getParent();
                if (parent instanceof ViewGroup) {
                    String str = "";
                    if (zzcsf.zzl() != null) {
                        str = zzcsf.zzl().zzg();
                    }
                    zzm.zzj("Banner view provided from " + str + " already has a parent view. Removing its old parent.");
                    ((ViewGroup) parent).removeView(zzcsf.zzc());
                }
            }
            if (((Boolean) zzba.zzc().zza(zzbep.zzik)).booleanValue()) {
                zzddq zzn = zzcsf.zzn();
                zzn.zza(this.zze.zzd);
                zzn.zzc(this.zze.zze);
            }
            this.zze.zzf.addView(zzcsf.zzc());
            this.zza.zzb(zzcsf);
            if (((Boolean) zzba.zzc().zza(zzbep.zzik)).booleanValue()) {
                zzfdf zzfdf = this.zze;
                Executor zzk = zzfdf.zzb;
                zzepc zzg = zzfdf.zzd;
                Objects.requireNonNull(zzg);
                zzk.execute(new zzfdd(zzg));
            }
            this.zze.zzh.zzd(zzcsf.zza());
            if (!((Boolean) zzbgd.zzc.zze()).booleanValue() || (zzfmn = this.zzb) == null) {
                zzfmq zzj = this.zze.zzi;
                zzfmc zzfmc = this.zzc;
                zzfmc.zzb(zzcsf.zzp().zzb);
                zzfmc.zze(zzcsf.zzl().zzg());
                zzfmc.zzh(true);
                zzj.zzb(zzfmc.zzn());
            } else {
                zzfmn.zzh(zzcsf.zzp().zzb);
                zzfmn.zzf(zzcsf.zzl().zzg());
                zzfmc zzfmc2 = this.zzc;
                zzfmc2.zzh(true);
                zzfmn.zza(zzfmc2);
                zzfmn.zzi();
            }
        }
    }
}
