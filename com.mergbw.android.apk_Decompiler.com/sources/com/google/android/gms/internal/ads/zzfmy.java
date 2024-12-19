package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfmy implements zzhbs {
    ORIENTATION_UNKNOWN(0),
    ORIENTATION_PORTRAIT(1),
    ORIENTATION_LANDSCAPE(2),
    UNRECOGNIZED(-1);
    
    private static final zzhbt zze = null;
    private final int zzg;

    static {
        zze = new zzfmx();
    }

    private zzfmy(int i) {
        this.zzg = i;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzg;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
