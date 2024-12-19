package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgos implements zzghj {
    final String zza;
    final Class zzb;
    final zzgwg zzc;
    final zzhdm zzd;

    zzgos(String str, Class cls, zzgwg zzgwg, zzhdm zzhdm) {
        this.zzd = zzhdm;
        this.zza = str;
        this.zzb = cls;
        this.zzc = zzgwg;
    }

    public static zzghj zzd(String str, Class cls, zzgwg zzgwg, zzhdm zzhdm) {
        return new zzgos(str, cls, zzgwg, zzhdm);
    }

    public final zzgwh zza(zzhac zzhac) throws GeneralSecurityException {
        zzgwl zza2 = zzgwm.zza();
        zza2.zzb(this.zza);
        zza2.zzc(zzhac);
        zza2.zza(zzgxn.RAW);
        zzgqq zzd2 = zzgpl.zzc().zzd(zzgpb.zzb().zza(zzgpl.zzc().zzb(zzgqm.zza((zzgwm) zza2.zzbr())), (Integer) null), zzgql.class, zzghh.zza());
        zzgwe zza3 = zzgwh.zza();
        zzgql zzgql = (zzgql) zzd2;
        zza3.zzb(zzgql.zzg());
        zza3.zzc(zzgql.zze());
        zza3.zza(zzgql.zzb());
        return (zzgwh) zza3.zzbr();
    }

    public final Class zzb() {
        return this.zzb;
    }

    public final Object zzc(zzhac zzhac) throws GeneralSecurityException {
        return zzgpi.zza().zzc(zzgpl.zzc().zza(zzgql.zza(this.zza, zzhac, this.zzc, zzgxn.RAW, (Integer) null), zzghh.zza()), this.zzb);
    }
}
