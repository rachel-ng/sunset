package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhgf implements zzhbs {
    UNSPECIFIED(0),
    TAILORED_WARNING_CT_BASE(1),
    TAILORED_WARNING_CT(2),
    TAILORED_WARNING_CT_ACCOUNT_INFO(3),
    TAILORED_WARNING_SUSPICIOUS_BASE(4),
    TAILORED_WARNING_SUSPICIOUS(5);
    
    private static final zzhbt zzg = null;
    private final int zzi;

    static {
        zzg = new zzhgd();
    }

    private zzhgf(int i) {
        this.zzi = i;
    }

    public static zzhgf zzb(int i) {
        if (i == 0) {
            return UNSPECIFIED;
        }
        if (i == 1) {
            return TAILORED_WARNING_CT_BASE;
        }
        if (i == 2) {
            return TAILORED_WARNING_CT;
        }
        if (i == 3) {
            return TAILORED_WARNING_CT_ACCOUNT_INFO;
        }
        if (i == 4) {
            return TAILORED_WARNING_SUSPICIOUS_BASE;
        }
        if (i != 5) {
            return null;
        }
        return TAILORED_WARNING_SUSPICIOUS;
    }

    public final String toString() {
        return Integer.toString(this.zzi);
    }

    public final int zza() {
        return this.zzi;
    }
}
