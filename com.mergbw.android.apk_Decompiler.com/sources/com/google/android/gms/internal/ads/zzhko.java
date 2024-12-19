package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhko implements zzhky, zzhkj {
    private static final Object zza = new Object();
    private volatile zzhky zzb;
    private volatile Object zzc = zza;

    private zzhko(zzhky zzhky) {
        this.zzb = zzhky;
    }

    public static zzhkj zza(zzhky zzhky) {
        return zzhky instanceof zzhkj ? (zzhkj) zzhky : new zzhko(zzhky);
    }

    public static zzhky zzc(zzhky zzhky) {
        return zzhky instanceof zzhko ? zzhky : new zzhko(zzhky);
    }

    public final Object zzb() {
        Object obj = this.zzc;
        Object obj2 = zza;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.zzc;
                if (obj == obj2) {
                    obj = this.zzb.zzb();
                    Object obj3 = this.zzc;
                    if (obj3 != obj2) {
                        if (obj3 != obj) {
                            throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                        }
                    }
                    this.zzc = obj;
                    this.zzb = null;
                }
            }
        }
        return obj;
    }
}
