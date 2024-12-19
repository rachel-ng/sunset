package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfsi implements zzhbs {
    EVENT_TYPE_UNKNOWN(0),
    BLOCKED_IMPRESSION(1);
    
    private static final zzhbt zzc = null;
    private final int zze;

    static {
        zzc = new zzfsg();
    }

    private zzfsi(int i) {
        this.zze = i;
    }

    public static zzfsi zzb(int i) {
        if (i == 0) {
            return EVENT_TYPE_UNKNOWN;
        }
        if (i != 1) {
            return null;
        }
        return BLOCKED_IMPRESSION;
    }

    public final String toString() {
        return Integer.toString(this.zze);
    }

    public final int zza() {
        return this.zze;
    }
}
