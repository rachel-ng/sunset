package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfsb implements zzhbs {
    BLOCKED_REASON_UNKNOWN(1),
    BLOCKED_REASON_BACKGROUND(2);
    
    private static final zzhbt zzc = null;
    private final int zze;

    static {
        zzc = new zzfrz();
    }

    private zzfsb(int i) {
        this.zze = i;
    }

    public static zzfsb zzb(int i) {
        if (i == 1) {
            return BLOCKED_REASON_UNKNOWN;
        }
        if (i != 2) {
            return null;
        }
        return BLOCKED_REASON_BACKGROUND;
    }

    public final String toString() {
        return Integer.toString(this.zze);
    }

    public final int zza() {
        return this.zze;
    }
}
