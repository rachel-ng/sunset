package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfew implements zzeps {
    private final Context zza;
    /* access modifiers changed from: private */
    public final Executor zzb;
    private final zzcjd zzc;
    /* access modifiers changed from: private */
    public final zzepc zzd;
    /* access modifiers changed from: private */
    public final zzffw zze;
    private zzbfk zzf;
    /* access modifiers changed from: private */
    public final zzfmq zzg;
    private final zzfhm zzh;
    /* access modifiers changed from: private */
    public ListenableFuture zzi;

    public zzfew(Context context, Executor executor, zzcjd zzcjd, zzepc zzepc, zzffw zzffw, zzfhm zzfhm) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcjd;
        this.zzd = zzepc;
        this.zzh = zzfhm;
        this.zze = zzffw;
        this.zzg = zzcjd.zzz();
    }

    public final boolean zza() {
        ListenableFuture listenableFuture = this.zzi;
        return listenableFuture != null && !listenableFuture.isDone();
    }

    public final boolean zzb(zzl zzl, String str, zzepq zzepq, zzepr zzepr) {
        zzdjh zzf2;
        zzfmn zzfmn;
        if (str == null) {
            zzm.zzg("Ad unit ID should not be null for interstitial ad.");
            this.zzb.execute(new zzfeq(this));
            return false;
        } else if (zza()) {
            return false;
        } else {
            if (((Boolean) zzba.zzc().zza(zzbep.zziU)).booleanValue() && zzl.zzf) {
                this.zzc.zzl().zzo(true);
            }
            zzq zzq = ((zzfep) zzepq).zza;
            Bundle zza2 = zzdun.zza(new Pair(zzdul.PUBLIC_API_CALL.zza(), Long.valueOf(zzl.zzz)), new Pair(zzdul.DYNAMITE_ENTER.zza(), Long.valueOf(zzu.zzB().currentTimeMillis())));
            zzfhm zzfhm = this.zzh;
            zzfhm.zzt(str);
            zzfhm.zzs(zzq);
            zzfhm.zzH(zzl);
            zzfhm.zzA(zza2);
            Context context = this.zza;
            zzfho zzJ = zzfhm.zzJ();
            zzfmc zzb2 = zzfmb.zzb(context, zzfmm.zza(zzJ), zzfmw.FORMAT_INTERSTITIAL, zzl);
            if (((Boolean) zzba.zzc().zza(zzbep.zzim)).booleanValue()) {
                zzdjg zzg2 = this.zzc.zzg();
                zzcyt zzcyt = new zzcyt();
                zzcyt.zze(this.zza);
                zzcyt.zzi(zzJ);
                zzg2.zze(zzcyt.zzj());
                zzdfa zzdfa = new zzdfa();
                zzdfa.zzj(this.zzd, this.zzb);
                zzdfa.zzk(this.zzd, this.zzb);
                zzg2.zzd(zzdfa.zzn());
                zzg2.zzc(new zzenl(this.zzf));
                zzf2 = zzg2.zzf();
            } else {
                zzdfa zzdfa2 = new zzdfa();
                zzffw zzffw = this.zze;
                if (zzffw != null) {
                    zzdfa2.zze(zzffw, this.zzb);
                    zzdfa2.zzf(this.zze, this.zzb);
                    zzdfa2.zzb(this.zze, this.zzb);
                }
                zzdjg zzg3 = this.zzc.zzg();
                zzcyt zzcyt2 = new zzcyt();
                zzcyt2.zze(this.zza);
                zzcyt2.zzi(zzJ);
                zzg3.zze(zzcyt2.zzj());
                zzdfa2.zzj(this.zzd, this.zzb);
                zzdfa2.zze(this.zzd, this.zzb);
                zzdfa2.zzf(this.zzd, this.zzb);
                zzdfa2.zzb(this.zzd, this.zzb);
                zzdfa2.zza(this.zzd, this.zzb);
                zzdfa2.zzl(this.zzd, this.zzb);
                zzdfa2.zzk(this.zzd, this.zzb);
                zzdfa2.zzi(this.zzd, this.zzb);
                zzdfa2.zzc(this.zzd, this.zzb);
                zzg3.zzd(zzdfa2.zzn());
                zzg3.zzc(new zzenl(this.zzf));
                zzf2 = zzg3.zzf();
            }
            zzdjh zzdjh = zzf2;
            if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
                zzfmn zzf3 = zzdjh.zzf();
                zzf3.zzd(zzfmw.FORMAT_INTERSTITIAL);
                zzf3.zzb(zzl.zzp);
                zzf3.zzg(zzl.zzm);
                zzfmn = zzf3;
            } else {
                zzfmn = null;
            }
            zzcvx zza3 = zzdjh.zza();
            ListenableFuture zzi2 = zza3.zzi(zza3.zzj());
            this.zzi = zzi2;
            zzgft.zzr(zzi2, new zzfev(this, zzepr, zzfmn, zzb2, zzdjh), this.zzb);
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzh() {
        this.zzd.zzdB(zzfiq.zzd(6, (String) null, (zze) null));
    }

    public final void zzi(zzbfk zzbfk) {
        this.zzf = zzbfk;
    }
}
