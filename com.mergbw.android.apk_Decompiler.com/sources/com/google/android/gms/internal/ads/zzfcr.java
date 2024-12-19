package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzdg;
import com.google.android.gms.ads.internal.client.zze;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.overlay.zzp;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfcr implements zzczo, zzdbk, zzfeh, zzp, zzdbw, zzdab, zzdhi {
    private final zzfiy zza;
    private final AtomicReference zzb = new AtomicReference();
    private final AtomicReference zzc = new AtomicReference();
    private final AtomicReference zzd = new AtomicReference();
    private final AtomicReference zze = new AtomicReference();
    private final AtomicReference zzf = new AtomicReference();
    private final AtomicReference zzg = new AtomicReference();
    private zzfcr zzh = null;

    public zzfcr(zzfiy zzfiy) {
        this.zza = zzfiy;
    }

    public static zzfcr zzi(zzfcr zzfcr) {
        zzfcr zzfcr2 = new zzfcr(zzfcr.zza);
        zzfcr2.zzh = zzfcr;
        return zzfcr2;
    }

    public final void zzdG() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdG();
        } else {
            zzfdy.zza(this.zzd, new zzfci());
        }
    }

    public final void zzdH() {
    }

    public final void zzdf() {
    }

    public final void zzdk() {
    }

    public final void zzdq() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdq();
        } else {
            zzfdy.zza(this.zzf, new zzfcf());
        }
    }

    public final void zzdr() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdr();
            return;
        }
        zzfdy.zza(this.zzf, new zzfcq());
        zzfdy.zza(this.zzd, new zzfcd());
        zzfdy.zza(this.zzd, new zzfce());
    }

    public final void zzdt() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdt();
        } else {
            zzfdy.zza(this.zzf, new zzfcp());
        }
    }

    public final void zzdu(int i) {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdu(i);
        } else {
            zzfdy.zza(this.zzf, new zzfcl(i));
        }
    }

    public final void zzg() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzg();
        } else {
            zzfdy.zza(this.zze, new zzfco());
        }
    }

    public final void zzh(zzs zzs) {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzh(zzs);
        } else {
            zzfdy.zza(this.zzg, new zzfcc(zzs));
        }
    }

    public final void zzj() {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzj();
            return;
        }
        this.zza.zza();
        zzfdy.zza(this.zzc, new zzfcj());
        zzfdy.zza(this.zzd, new zzfck());
    }

    public final void zzl(zzfeh zzfeh) {
        this.zzh = (zzfcr) zzfeh;
    }

    public final void zzm(zzp zzp) {
        this.zzf.set(zzp);
    }

    public final void zzn(zzdg zzdg) {
        this.zzg.set(zzdg);
    }

    public final void zzo(zzbcj zzbcj) {
        this.zzb.set(zzbcj);
    }

    public final void zzp(zzbcn zzbcn) {
        this.zzd.set(zzbcn);
    }

    public final void zzq(zze zze2) {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzq(zze2);
        } else {
            zzfdy.zza(this.zzd, new zzfcg(zze2));
        }
    }

    public final void zzk(zzbcg zzbcg) {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzk(zzbcg);
        } else {
            zzfdy.zza(this.zzb, new zzfch(zzbcg));
        }
    }

    public final void zzdB(zze zze2) {
        zzfcr zzfcr = this.zzh;
        if (zzfcr != null) {
            zzfcr.zzdB(zze2);
            return;
        }
        zzfdy.zza(this.zzb, new zzfcm(zze2));
        zzfdy.zza(this.zzb, new zzfcn(zze2));
    }
}
