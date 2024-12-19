package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzgwg implements zzhbs {
    UNKNOWN_KEYMATERIAL(0),
    SYMMETRIC(1),
    ASYMMETRIC_PRIVATE(2),
    ASYMMETRIC_PUBLIC(3),
    REMOTE(4),
    UNRECOGNIZED(-1);
    
    private static final zzhbt zzg = null;
    private final int zzi;

    static {
        zzg = new zzgwf();
    }

    private zzgwg(int i) {
        this.zzi = i;
    }

    public static zzgwg zzb(int i) {
        if (i == 0) {
            return UNKNOWN_KEYMATERIAL;
        }
        if (i == 1) {
            return SYMMETRIC;
        }
        if (i == 2) {
            return ASYMMETRIC_PRIVATE;
        }
        if (i == 3) {
            return ASYMMETRIC_PUBLIC;
        }
        if (i != 4) {
            return null;
        }
        return REMOTE;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzi;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
