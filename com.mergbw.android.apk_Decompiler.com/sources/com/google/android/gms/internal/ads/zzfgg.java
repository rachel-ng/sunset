package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
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
public final class zzfgg implements zzeps {
    private final Context zza;
    /* access modifiers changed from: private */
    public final Executor zzb;
    private final zzcjd zzc;
    /* access modifiers changed from: private */
    public final zzffw zzd;
    /* access modifiers changed from: private */
    public final zzfek zze;
    private final zzfhg zzf;
    /* access modifiers changed from: private */
    public final zzfmq zzg;
    private final zzfhm zzh;
    private ListenableFuture zzi;

    public zzfgg(Context context, Executor executor, zzcjd zzcjd, zzfek zzfek, zzffw zzffw, zzfhm zzfhm, zzfhg zzfhg) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcjd;
        this.zze = zzfek;
        this.zzd = zzffw;
        this.zzh = zzfhm;
        this.zzf = zzfhg;
        this.zzg = zzcjd.zzz();
    }

    /* access modifiers changed from: private */
    public final zzdrl zzk(zzfei zzfei) {
        zzdrl zzi2 = this.zzc.zzi();
        zzcyt zzcyt = new zzcyt();
        zzcyt.zze(this.zza);
        zzcyt.zzi(((zzfgf) zzfei).zza);
        zzcyt.zzh(this.zzf);
        zzi2.zzd(zzcyt.zzj());
        zzi2.zzc(new zzdfa().zzn());
        return zzi2;
    }

    public final boolean zza() {
        throw null;
    }

    public final boolean zzb(zzl zzl, String str, zzepq zzepq, zzepr zzepr) throws RemoteException {
        zzfmn zzfmn;
        zzbyo zzbyo = new zzbyo(zzl, str);
        if (zzbyo.zzb == null) {
            zzm.zzg("Ad unit ID should not be null for rewarded video ad.");
            this.zzb.execute(new zzffz(this));
            return false;
        }
        ListenableFuture listenableFuture = this.zzi;
        if (listenableFuture != null && !listenableFuture.isDone()) {
            return false;
        }
        if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
            zzfek zzfek = this.zze;
            if (zzfek.zzd() != null) {
                zzfmn zzh2 = ((zzdrm) zzfek.zzd()).zzh();
                zzh2.zzd(zzfmw.FORMAT_REWARDED);
                zzh2.zzb(zzbyo.zza.zzp);
                zzh2.zzg(zzbyo.zza.zzm);
                zzfmn = zzh2;
                zzfil.zza(this.zza, zzbyo.zza.zzf);
                if (((Boolean) zzba.zzc().zza(zzbep.zziU)).booleanValue() && zzbyo.zza.zzf) {
                    this.zzc.zzl().zzo(true);
                }
                Bundle zza2 = zzdun.zza(new Pair(zzdul.PUBLIC_API_CALL.zza(), Long.valueOf(zzbyo.zza.zzz)), new Pair(zzdul.DYNAMITE_ENTER.zza(), Long.valueOf(zzu.zzB().currentTimeMillis())));
                zzfhm zzfhm = this.zzh;
                zzfhm.zzt(zzbyo.zzb);
                zzfhm.zzs(zzq.zzd());
                zzfhm.zzH(zzbyo.zza);
                zzfhm.zzA(zza2);
                Context context = this.zza;
                zzfho zzJ = zzfhm.zzJ();
                zzfmc zzb2 = zzfmb.zzb(context, zzfmm.zza(zzJ), zzfmw.FORMAT_REWARDED, zzbyo.zza);
                zzfgf zzfgf = new zzfgf((zzfge) null);
                zzfgf.zza = zzJ;
                ListenableFuture zzc2 = this.zze.zzc(new zzfel(zzfgf, (zzbxu) null), new zzfga(this), (Object) null);
                this.zzi = zzc2;
                zzgft.zzr(zzc2, new zzfgd(this, zzepr, zzfmn, zzb2, zzfgf), this.zzb);
                return true;
            }
        }
        zzfmn = null;
        zzfil.zza(this.zza, zzbyo.zza.zzf);
        this.zzc.zzl().zzo(true);
        Bundle zza22 = zzdun.zza(new Pair(zzdul.PUBLIC_API_CALL.zza(), Long.valueOf(zzbyo.zza.zzz)), new Pair(zzdul.DYNAMITE_ENTER.zza(), Long.valueOf(zzu.zzB().currentTimeMillis())));
        zzfhm zzfhm2 = this.zzh;
        zzfhm2.zzt(zzbyo.zzb);
        zzfhm2.zzs(zzq.zzd());
        zzfhm2.zzH(zzbyo.zza);
        zzfhm2.zzA(zza22);
        Context context2 = this.zza;
        zzfho zzJ2 = zzfhm2.zzJ();
        zzfmc zzb22 = zzfmb.zzb(context2, zzfmm.zza(zzJ2), zzfmw.FORMAT_REWARDED, zzbyo.zza);
        zzfgf zzfgf2 = new zzfgf((zzfge) null);
        zzfgf2.zza = zzJ2;
        ListenableFuture zzc22 = this.zze.zzc(new zzfel(zzfgf2, (zzbxu) null), new zzfga(this), (Object) null);
        this.zzi = zzc22;
        zzgft.zzr(zzc22, new zzfgd(this, zzepr, zzfmn, zzb22, zzfgf2), this.zzb);
        return true;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzi() {
        this.zzd.zzdB(zzfiq.zzd(6, (String) null, (zze) null));
    }

    /* access modifiers changed from: package-private */
    public final void zzj(int i) {
        this.zzh.zzp().zza(i);
    }
}
