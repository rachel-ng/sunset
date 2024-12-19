package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzcmw implements zzfgn {
    private final zzcla zza;
    private final zzcmw zzb = this;
    private final zzhky zzc;
    private final zzhky zzd;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;
    private final zzhky zzi;
    private final zzhky zzj;

    /* synthetic */ zzcmw(zzcla zzcla, Context context, String str, zzcmv zzcmv) {
        this.zza = zzcla;
        zzhkp zza2 = zzhkq.zza(context);
        this.zzc = zza2;
        zzfen zzfen = new zzfen(zza2, zzcla.zzaE, zzcla.zzaF);
        this.zzd = zzfen;
        zzhky zzc2 = zzhko.zzc(new zzffx(zzcla.zzaE));
        this.zze = zzc2;
        zzhky zzc3 = zzhko.zzc(zzfhl.zza());
        this.zzf = zzc3;
        zzhky zzc4 = zzhko.zzc(new zzfgh(zza2, zzcla.zzc, zzcla.zzO, zzfen, zzc2, zzfhq.zza(), zzc3));
        this.zzg = zzc4;
        this.zzh = zzhko.zzc(new zzfgr(zzc4, zzc2, zzc3));
        zzhkp zzc5 = zzhkq.zzc(str);
        this.zzi = zzc5;
        this.zzj = zzhko.zzc(new zzfgl(zzc5, zzc4, zza2, zzc2, zzc3, zzcla.zzl, zzcla.zzQ, zzcla.zzM));
    }

    public final zzfgk zza() {
        return (zzfgk) this.zzj.zzb();
    }

    public final zzfgq zzb() {
        return (zzfgq) this.zzh.zzb();
    }
}
