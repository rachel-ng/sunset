package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhkn implements zzhkp {
    private zzhky zza;

    public static void zza(zzhky zzhky, zzhky zzhky2) {
        zzhkn zzhkn = (zzhkn) zzhky;
        if (zzhkn.zza == null) {
            zzhkn.zza = zzhky2;
            return;
        }
        throw new IllegalStateException();
    }

    public final Object zzb() {
        zzhky zzhky = this.zza;
        if (zzhky != null) {
            return zzhky.zzb();
        }
        throw new IllegalStateException();
    }
}
