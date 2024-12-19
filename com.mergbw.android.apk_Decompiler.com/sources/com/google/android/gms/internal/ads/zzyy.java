package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzyy extends zzza implements Comparable {
    private final int zze;
    private final boolean zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final boolean zzm;

    public zzyy(int i, zzde zzde, int i2, zzys zzys, int i3, String str) {
        super(i, zzde, i2);
        zzgbc zzgbc;
        int i4;
        int i5 = 0;
        this.zzf = zzze.zzo(i3, false);
        int i6 = this.zzd.zzf;
        int i7 = zzys.zzy;
        this.zzg = 1 == (i6 & 1);
        this.zzh = (i6 & 2) != 0;
        if (zzys.zzw.isEmpty()) {
            zzgbc = zzgbc.zzn("");
        } else {
            zzgbc = zzys.zzw;
        }
        int i8 = 0;
        while (true) {
            if (i8 >= zzgbc.size()) {
                i8 = Integer.MAX_VALUE;
                i4 = 0;
                break;
            }
            boolean z = zzys.zzz;
            i4 = zzze.zzc(this.zzd, (String) zzgbc.get(i8), false);
            if (i4 > 0) {
                break;
            }
            i8++;
        }
        this.zzi = i8;
        this.zzj = i4;
        int zzb = zzze.zzb(this.zzd.zzg, zzys.zzx);
        this.zzk = zzb;
        this.zzm = (this.zzd.zzg & 1088) != 0;
        int zzc = zzze.zzc(this.zzd, str, zzze.zzh(str) == null);
        this.zzl = zzc;
        boolean z2 = i4 > 0 || (zzys.zzw.isEmpty() && zzb > 0) || this.zzg || (this.zzh && zzc > 0);
        if (zzze.zzo(i3, zzys.zzT) && z2) {
            i5 = 1;
        }
        this.zze = i5;
    }

    /* renamed from: zza */
    public final int compareTo(zzyy zzyy) {
        zzgcn zzgcn;
        zzgar zze2 = zzgar.zzk().zze(this.zzf, zzyy.zzf).zzd(Integer.valueOf(this.zzi), Integer.valueOf(zzyy.zzi), zzgcn.zzc().zza()).zzb(this.zzj, zzyy.zzj).zzb(this.zzk, zzyy.zzk).zze(this.zzg, zzyy.zzg);
        Boolean valueOf = Boolean.valueOf(this.zzh);
        Boolean valueOf2 = Boolean.valueOf(zzyy.zzh);
        if (this.zzj == 0) {
            zzgcn = zzgcn.zzc();
        } else {
            zzgcn = zzgcn.zzc().zza();
        }
        zzgar zzb = zze2.zzd(valueOf, valueOf2, zzgcn).zzb(this.zzl, zzyy.zzl);
        if (this.zzk == 0) {
            zzb = zzb.zzf(this.zzm, zzyy.zzm);
        }
        return zzb.zza();
    }

    public final int zzb() {
        return this.zze;
    }

    public final /* bridge */ /* synthetic */ boolean zzc(zzza zzza) {
        zzyy zzyy = (zzyy) zzza;
        return false;
    }
}
