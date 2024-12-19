package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzfmw implements zzhbs {
    FORMAT_UNKNOWN(0),
    FORMAT_BANNER(1),
    FORMAT_INTERSTITIAL(2),
    FORMAT_REWARDED(3),
    FORMAT_REWARDED_INTERSTITIAL(4),
    FORMAT_APP_OPEN(5),
    FORMAT_NATIVE(6),
    UNRECOGNIZED(-1);
    
    private static final zzhbt zzi = null;
    private final int zzk;

    static {
        zzi = new zzfmv();
    }

    private zzfmw(int i) {
        this.zzk = i;
    }

    public final String toString() {
        return Integer.toString(zza());
    }

    public final int zza() {
        if (this != UNRECOGNIZED) {
            return this.zzk;
        }
        throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
    }
}
