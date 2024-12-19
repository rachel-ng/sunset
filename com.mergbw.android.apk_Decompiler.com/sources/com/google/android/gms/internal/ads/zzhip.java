package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhip implements zzhbs {
    AD_RESOURCE_UNKNOWN(0),
    AD_RESOURCE_CREATIVE(1),
    AD_RESOURCE_POST_CLICK(2),
    AD_RESOURCE_AUTO_CLICK_DESTINATION(3);
    
    private static final zzhbt zze = null;
    private final int zzg;

    static {
        zze = new zzhin();
    }

    private zzhip(int i) {
        this.zzg = i;
    }

    public static zzhip zzb(int i) {
        if (i == 0) {
            return AD_RESOURCE_UNKNOWN;
        }
        if (i == 1) {
            return AD_RESOURCE_CREATIVE;
        }
        if (i == 2) {
            return AD_RESOURCE_POST_CLICK;
        }
        if (i != 3) {
            return null;
        }
        return AD_RESOURCE_AUTO_CLICK_DESTINATION;
    }

    public final String toString() {
        return Integer.toString(this.zzg);
    }

    public final int zza() {
        return this.zzg;
    }
}
