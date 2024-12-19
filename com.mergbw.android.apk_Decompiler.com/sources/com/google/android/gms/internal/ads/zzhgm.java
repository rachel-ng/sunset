package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhgm implements zzhbs {
    SAFE(0),
    DANGEROUS(1),
    UNCOMMON(2),
    POTENTIALLY_UNWANTED(3),
    DANGEROUS_HOST(4),
    UNKNOWN(5),
    PLAY_POLICY_VIOLATION_SEVERE(6),
    PLAY_POLICY_VIOLATION_OTHER(7),
    DANGEROUS_ACCOUNT_COMPROMISE(8),
    PENDING(9),
    PLAY_POLICY_VIOLATION_TREATMENT_ON_DEVICE(10),
    HIGH_RISK_BLOCK(11),
    HIGH_RISK_WARN(12);
    
    private static final zzhbt zzn = null;
    private final int zzp;

    static {
        zzn = new zzhgk();
    }

    private zzhgm(int i) {
        this.zzp = i;
    }

    public static zzhgm zzb(int i) {
        switch (i) {
            case 0:
                return SAFE;
            case 1:
                return DANGEROUS;
            case 2:
                return UNCOMMON;
            case 3:
                return POTENTIALLY_UNWANTED;
            case 4:
                return DANGEROUS_HOST;
            case 5:
                return UNKNOWN;
            case 6:
                return PLAY_POLICY_VIOLATION_SEVERE;
            case 7:
                return PLAY_POLICY_VIOLATION_OTHER;
            case 8:
                return DANGEROUS_ACCOUNT_COMPROMISE;
            case 9:
                return PENDING;
            case 10:
                return PLAY_POLICY_VIOLATION_TREATMENT_ON_DEVICE;
            case 11:
                return HIGH_RISK_BLOCK;
            case 12:
                return HIGH_RISK_WARN;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzp);
    }

    public final int zza() {
        return this.zzp;
    }
}
