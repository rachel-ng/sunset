package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.client.zzbe;
import com.google.android.gms.ads.internal.client.zzbh;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzl;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfdf implements zzeps {
    private final Context zza;
    /* access modifiers changed from: private */
    public final Executor zzb;
    private final zzcjd zzc;
    /* access modifiers changed from: private */
    public final zzepc zzd;
    /* access modifiers changed from: private */
    public final zzepg zze;
    /* access modifiers changed from: private */
    public final ViewGroup zzf;
    private zzbfk zzg;
    /* access modifiers changed from: private */
    public final zzdca zzh;
    /* access modifiers changed from: private */
    public final zzfmq zzi;
    /* access modifiers changed from: private */
    public final zzdeh zzj;
    private final zzfhm zzk;
    /* access modifiers changed from: private */
    public ListenableFuture zzl;

    public zzfdf(Context context, Executor executor, zzq zzq, zzcjd zzcjd, zzepc zzepc, zzepg zzepg, zzfhm zzfhm, zzdeh zzdeh) {
        this.zza = context;
        this.zzb = executor;
        this.zzc = zzcjd;
        this.zzd = zzepc;
        this.zze = zzepg;
        this.zzk = zzfhm;
        this.zzh = zzcjd.zzf();
        this.zzi = zzcjd.zzz();
        this.zzf = new FrameLayout(context);
        this.zzj = zzdeh;
        zzfhm.zzs(zzq);
    }

    public final boolean zza() {
        ListenableFuture listenableFuture = this.zzl;
        return listenableFuture != null && !listenableFuture.isDone();
    }

    public final boolean zzb(zzl zzl2, String str, zzepq zzepq, zzepr zzepr) throws RemoteException {
        zzctg zzctg;
        zzfmn zzfmn;
        if (str == null) {
            zzm.zzg("Ad unit ID should not be null for banner ad.");
            this.zzb.execute(new zzfdb(this));
            return false;
        }
        if (!zza()) {
            if (((Boolean) zzba.zzc().zza(zzbep.zziU)).booleanValue() && zzl2.zzf) {
                this.zzc.zzl().zzo(true);
            }
            Bundle zza2 = zzdun.zza(new Pair(zzdul.PUBLIC_API_CALL.zza(), Long.valueOf(zzl2.zzz)), new Pair(zzdul.DYNAMITE_ENTER.zza(), Long.valueOf(zzu.zzB().currentTimeMillis())));
            zzfhm zzfhm = this.zzk;
            zzfhm.zzt(str);
            zzfhm.zzH(zzl2);
            zzfhm.zzA(zza2);
            Context context = this.zza;
            zzfho zzJ = zzfhm.zzJ();
            zzfmc zzb2 = zzfmb.zzb(context, zzfmm.zza(zzJ), zzfmw.FORMAT_BANNER, zzl2);
            if (!((Boolean) zzbgq.zze.zze()).booleanValue() || !this.zzk.zzh().zzk) {
                if (((Boolean) zzba.zzc().zza(zzbep.zzik)).booleanValue()) {
                    zzctf zze2 = this.zzc.zze();
                    zzcyt zzcyt = new zzcyt();
                    zzcyt.zze(this.zza);
                    zzcyt.zzi(zzJ);
                    zze2.zzi(zzcyt.zzj());
                    zzdfa zzdfa = new zzdfa();
                    zzdfa.zzj(this.zzd, this.zzb);
                    zzdfa.zzk(this.zzd, this.zzb);
                    zze2.zzf(zzdfa.zzn());
                    zze2.zze(new zzenl(this.zzg));
                    zze2.zzd(new zzdjy(zzdme.zza, (zzbh) null));
                    zze2.zzg(new zzcuh(this.zzh, this.zzj));
                    zze2.zzc(new zzcsc(this.zzf));
                    zzctg = zze2.zzk();
                } else {
                    zzctf zze3 = this.zzc.zze();
                    zzcyt zzcyt2 = new zzcyt();
                    zzcyt2.zze(this.zza);
                    zzcyt2.zzi(zzJ);
                    zze3.zzi(zzcyt2.zzj());
                    zzdfa zzdfa2 = new zzdfa();
                    zzdfa2.zzj(this.zzd, this.zzb);
                    zzdfa2.zza(this.zzd, this.zzb);
                    zzdfa2.zza(this.zze, this.zzb);
                    zzdfa2.zzl(this.zzd, this.zzb);
                    zzdfa2.zzd(this.zzd, this.zzb);
                    zzdfa2.zze(this.zzd, this.zzb);
                    zzdfa2.zzf(this.zzd, this.zzb);
                    zzdfa2.zzb(this.zzd, this.zzb);
                    zzdfa2.zzk(this.zzd, this.zzb);
                    zzdfa2.zzi(this.zzd, this.zzb);
                    zze3.zzf(zzdfa2.zzn());
                    zze3.zze(new zzenl(this.zzg));
                    zze3.zzd(new zzdjy(zzdme.zza, (zzbh) null));
                    zze3.zzg(new zzcuh(this.zzh, this.zzj));
                    zze3.zzc(new zzcsc(this.zzf));
                    zzctg = zze3.zzk();
                }
                zzctg zzctg2 = zzctg;
                if (((Boolean) zzbgd.zzc.zze()).booleanValue()) {
                    zzfmn zzj2 = zzctg2.zzj();
                    zzj2.zzd(zzfmw.FORMAT_BANNER);
                    zzj2.zzb(zzl2.zzp);
                    zzj2.zzg(zzl2.zzm);
                    zzfmn = zzj2;
                } else {
                    zzfmn = null;
                }
                zzcvx zzd2 = zzctg2.zzd();
                ListenableFuture zzi2 = zzd2.zzi(zzd2.zzj());
                this.zzl = zzi2;
                zzgft.zzr(zzi2, new zzfde(this, zzepr, zzfmn, zzb2, zzctg2), this.zzb);
                return true;
            }
            zzepc zzepc = this.zzd;
            if (zzepc != null) {
                zzepc.zzdB(zzfiq.zzd(7, (String) null, (zze) null));
            }
        }
        return false;
    }

    public final ViewGroup zzd() {
        return this.zzf;
    }

    public final zzfhm zzi() {
        return this.zzk;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm() {
        this.zzd.zzdB(zzfiq.zzd(6, (String) null, (zze) null));
    }

    public final void zzn() {
        this.zzh.zzd(this.zzj.zzc());
    }

    public final void zzo(zzbe zzbe) {
        this.zze.zza(zzbe);
    }

    public final void zzp(zzdcb zzdcb) {
        this.zzh.zzo(zzdcb, this.zzb);
    }

    public final void zzq(zzbfk zzbfk) {
        this.zzg = zzbfk;
    }

    public final boolean zzr() {
        ViewParent parent = this.zzf.getParent();
        if (!(parent instanceof View)) {
            return false;
        }
        View view = (View) parent;
        zzu.zzp();
        return zzt.zzW(view, view.getContext());
    }
}
