package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfne implements zzhbs {
    TAG_UNKNOWN(0),
    UNRECOGNIZED(-1);
    
    private static final zzhbt zzc = null;
    private final int zze;

    static {
        zzc = new zzfnd();
    }

    private zzfne(int i) {
        this.zze = i;
    }

    public static zzfne zzb(int i) {
        if (i != 0) {
            return null;
        }
        return TAG_UNKNOWN;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zze;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
