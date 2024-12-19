package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzpc implements zzoz {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Long> zzb;
    private static final zzgz<Double> zzc;
    private static final zzgz<Long> zzd;
    private static final zzgz<Long> zze;
    private static final zzgz<String> zzf;

    public final double zza() {
        return zzc.zza().doubleValue();
    }

    public final long zzb() {
        return zzb.zza().longValue();
    }

    public final long zzc() {
        return zzd.zza().longValue();
    }

    public final long zzd() {
        return zze.zza().longValue();
    }

    public final String zze() {
        return zzf.zza();
    }

    static {
        zzhh zza2 = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.test.boolean_flag", false);
        zzb = zza2.zza("measurement.test.cached_long_flag", -1);
        zzc = zza2.zza("measurement.test.double_flag", -3.0d);
        zzd = zza2.zza("measurement.test.int_flag", -2);
        zze = zza2.zza("measurement.test.long_flag", -1);
        zzf = zza2.zza("measurement.test.string_flag", "---");
    }

    public final boolean zzf() {
        return zza.zza().booleanValue();
    }
}
