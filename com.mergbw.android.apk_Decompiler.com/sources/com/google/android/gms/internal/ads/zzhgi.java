package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhgi implements zzhbs {
    UNKNOWN_USER_POPULATION(0),
    SAFE_BROWSING(1),
    EXTENDED_REPORTING(2),
    ENHANCED_PROTECTION(3);
    
    private static final zzhbt zze = null;
    private final int zzg;

    static {
        zze = new zzhgg();
    }

    private zzhgi(int i) {
        this.zzg = i;
    }

    public static zzhgi zzb(int i) {
        if (i == 0) {
            return UNKNOWN_USER_POPULATION;
        }
        if (i == 1) {
            return SAFE_BROWSING;
        }
        if (i == 2) {
            return EXTENDED_REPORTING;
        }
        if (i != 3) {
            return null;
        }
        return ENHANCED_PROTECTION;
    }

    public final String toString() {
        return Integer.toString(this.zzg);
    }

    public final int zza() {
        return this.zzg;
    }
}
