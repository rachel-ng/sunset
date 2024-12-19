package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhjf implements zzhbs {
    UNKNOWN(0),
    PHISHING_INTERSTITIAL(1),
    CLIENT_SIDE_PHISHING_INTERSTITIAL(2),
    MALWARE_INTERSTITIAL(3),
    UWS_INTERSTITIAL(4),
    BILLING_INTERSTITIAL(5),
    BINARY_MALWARE_DOWNLOAD_WARNING(6);
    
    private static final zzhbt zzh = null;
    private final int zzj;

    static {
        zzh = new zzhjd();
    }

    private zzhjf(int i) {
        this.zzj = i;
    }

    public static zzhjf zzb(int i) {
        switch (i) {
            case 0:
                return UNKNOWN;
            case 1:
                return PHISHING_INTERSTITIAL;
            case 2:
                return CLIENT_SIDE_PHISHING_INTERSTITIAL;
            case 3:
                return MALWARE_INTERSTITIAL;
            case 4:
                return UWS_INTERSTITIAL;
            case 5:
                return BILLING_INTERSTITIAL;
            case 6:
                return BINARY_MALWARE_DOWNLOAD_WARNING;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzj);
    }

    public final int zza() {
        return this.zzj;
    }
}
