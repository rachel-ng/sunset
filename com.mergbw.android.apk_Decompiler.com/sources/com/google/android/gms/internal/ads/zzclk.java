package com.google.android.gms.internal.ads;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzclk implements zzfbu {
    private final zzcla zza;
    private final zzclk zzb = this;
    private final zzhky zzc;
    private final zzhky zzd;
    private final zzhky zze;
    private final zzhky zzf;
    private final zzhky zzg;
    private final zzhky zzh;

    /* synthetic */ zzclk(zzcla zzcla, Context context, String str, zzclj zzclj) {
        this.zza = zzcla;
        zzhkp zza2 = zzhkq.zza(context);
        this.zzc = zza2;
        zzhkp zza3 = zzhkq.zza(str);
        this.zzd = zza3;
        zzfem zzfem = new zzfem(zza2, zzcla.zzaE, zzcla.zzaF);
        this.zze = zzfem;
        zzhky zzc2 = zzhko.zzc(new zzfcs(zzcla.zzaE));
        this.zzf = zzc2;
        zzhky zzhky = zzc2;
        zzhky zzc3 = zzhko.zzc(new zzfcu(zza2, zzcla.zzc, zzcla.zzO, zzfem, zzhky, zzfhq.zza(), zzcla.zzl));
        this.zzg = zzc3;
        this.zzh = zzhko.zzc(new zzfda(zzcla.zzO, zza2, zza3, zzc3, zzhky, zzcla.zzl, zzcla.zzM));
    }

    public final zzfcz zza() {
        return (zzfcz) this.zzh.zzb();
    }
}
