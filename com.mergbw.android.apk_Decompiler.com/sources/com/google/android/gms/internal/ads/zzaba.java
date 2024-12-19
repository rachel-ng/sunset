package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzaba implements zzacm, zzaas {
    final /* synthetic */ zzabc zza;
    private final Context zzb;
    private final int zzc;
    private final ArrayList zzd;
    private zzds zze;
    private zzan zzf;
    private long zzg;
    private boolean zzh;
    private long zzi;
    private boolean zzj;
    private long zzk;
    private zzack zzl;
    private Executor zzm;

    public zzaba(zzabc zzabc, Context context) {
        this.zza = zzabc;
        this.zzb = context;
        this.zzc = true != zzgd.zzL(context) ? 5 : 1;
        this.zzd = new ArrayList();
        this.zzi = C.TIME_UNSET;
        this.zzl = zzack.zzb;
        this.zzm = zzabc.zza;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [com.google.android.gms.internal.ads.zzds, java.lang.Throwable, java.lang.Object] */
    private final void zzm() {
        if (this.zzf != null) {
            new ArrayList().addAll(this.zzd);
            zzan zzan = this.zzf;
            zzan.getClass();
            ? r1 = 0;
            zzeq.zzb(r1);
            int i = zzan.zzt;
            zzao zzao = new zzao(zzabc.zzu(zzan.zzz), zzan.zzs, i);
            zzao.zza(zzan.zzw);
            zzao.zzb();
            r1.zzd();
            throw r1;
        }
    }

    public final void zza(zzabc zzabc) {
        this.zzm.execute(new zzaaz(this, this.zzl));
    }

    public final void zzb(zzabc zzabc) {
        this.zzm.execute(new zzaay(this, this.zzl));
    }

    public final void zzc(zzabc zzabc, zzdv zzdv) {
        this.zzm.execute(new zzaax(this, this.zzl, zzdv));
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [com.google.android.gms.internal.ads.zzds, java.lang.Throwable, java.lang.Object] */
    public final long zzd(long j, boolean z) {
        zzeq.zzf(false);
        long j2 = this.zzk;
        if (j2 != C.TIME_UNSET) {
            if (!zzabc.zzt(this.zza, j2)) {
                return C.TIME_UNSET;
            }
            zzm();
            this.zzk = C.TIME_UNSET;
        }
        ? r3 = 0;
        zzeq.zzb(r3);
        r3.zza();
        throw r3;
    }

    public final void zze() {
        this.zzj = false;
        this.zzi = C.TIME_UNSET;
        zzabc.zzg(this.zza);
    }

    public final void zzf(zzan zzan, zzer zzer) throws zzacl {
        this.zze = zzabc.zzb(this.zza, zzan, zzer);
    }

    public final void zzg(int i, zzan zzan) {
        boolean z = false;
        zzeq.zzf(false);
        int i2 = zzgd.zza;
        this.zzf = zzan;
        if (!this.zzj) {
            zzm();
            this.zzj = true;
            this.zzk = C.TIME_UNSET;
            return;
        }
        if (this.zzi != C.TIME_UNSET) {
            z = true;
        }
        zzeq.zzf(z);
        this.zzk = this.zzi;
    }

    public final void zzh(long j, long j2) throws zzacl {
        zzeq.zzf(false);
        try {
            this.zza.zzo(j, j2);
        } catch (zzjh e) {
            zzan zzan = this.zzf;
            if (zzan == null) {
                zzan = new zzal().zzad();
            }
            throw new zzacl(e, zzan);
        }
    }

    public final void zzi(zzack zzack, Executor executor) {
        this.zzl = zzack;
        this.zzm = executor;
    }

    public final void zzj(long j) {
        this.zzh = this.zzg != j;
        this.zzg = j;
    }

    public final void zzk(List list) {
        this.zzd.clear();
        this.zzd.addAll(list);
        zzm();
    }

    public final boolean zzl() {
        return zzgd.zzL(this.zzb);
    }
}
