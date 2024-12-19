package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhle implements zzhky {
    private static final Object zza = new Object();
    private volatile zzhky zzb;
    private volatile Object zzc = zza;

    private zzhle(zzhky zzhky) {
        this.zzb = zzhky;
    }

    public static zzhky zza(zzhky zzhky) {
        return ((zzhky instanceof zzhle) || (zzhky instanceof zzhko)) ? zzhky : new zzhle(zzhky);
    }

    public final Object zzb() {
        Object obj = this.zzc;
        if (obj != zza) {
            return obj;
        }
        zzhky zzhky = this.zzb;
        if (zzhky == null) {
            return this.zzc;
        }
        Object zzb2 = zzhky.zzb();
        this.zzc = zzb2;
        this.zzb = null;
        return zzb2;
    }
}
