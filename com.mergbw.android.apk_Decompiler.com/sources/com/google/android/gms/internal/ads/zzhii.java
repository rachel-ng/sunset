package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhii implements zzhbs {
    TYPE_UNKNOWN(0),
    TYPE_CREATIVE(1);
    
    private static final zzhbt zzc = null;
    private final int zze;

    static {
        zzc = new zzhig();
    }

    private zzhii(int i) {
        this.zze = i;
    }

    public static zzhii zzb(int i) {
        if (i == 0) {
            return TYPE_UNKNOWN;
        }
        if (i != 1) {
            return null;
        }
        return TYPE_CREATIVE;
    }

    public final String toString() {
        return Integer.toString(this.zze);
    }

    public final int zza() {
        return this.zze;
    }
}
