package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzow implements zzot {
    private static final zzgz<Boolean> zza;
    private static final zzgz<Boolean> zzb;

    static {
        zzhh zza2 = new zzhh(zzgw.zza("com.google.android.gms.measurement")).zzb().zza();
        zza = zza2.zza("measurement.item_scoped_custom_parameters.client", true);
        zzb = zza2.zza("measurement.item_scoped_custom_parameters.service", true);
    }

    public final boolean zza() {
        return true;
    }

    public final boolean zzb() {
        return zza.zza().booleanValue();
    }

    public final boolean zzc() {
        return zzb.zza().booleanValue();
    }
}
