package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhfl implements zzhbs {
    USER_POPULATION_UNSPECIFIED(0),
    CARTER_SB_CHROME_INTERSTITIAL(1),
    GMAIL_PHISHY_JOURNEY(2),
    DOWNLOAD_RELATED_POPULATION_MIN(1000),
    RISKY_DOWNLOADER(1001),
    INFREQUENT_DOWNLOADER(1002),
    REGULAR_DOWNLOADER(1003),
    BOTLIKE_DOWNLOADER(1004),
    DOCUMENT_DOWNLOADER(1005),
    HIGHLY_TECHNICAL_DOWNLOADER(1006),
    LOW_DOWNLOAD_WARNING_CLICK_THROUGH_RATE(1007),
    HIGH_DOWNLOAD_WARNING_CLICK_THROUGH_RATE(1008),
    SPAM_PING_SENDER(1009),
    RFA_TRUSTED(1010),
    DOWNLOAD_RELATED_POPULATION_MAX(1999);
    
    private static final zzhbt zzp = null;
    private final int zzr;

    static {
        zzp = new zzhfj();
    }

    private zzhfl(int i) {
        this.zzr = i;
    }

    public static zzhbu zzb() {
        return zzhfk.zza;
    }

    public static zzhfl zzc(int i) {
        if (i == 0) {
            return USER_POPULATION_UNSPECIFIED;
        }
        if (i == 1) {
            return CARTER_SB_CHROME_INTERSTITIAL;
        }
        if (i == 2) {
            return GMAIL_PHISHY_JOURNEY;
        }
        if (i == 1999) {
            return DOWNLOAD_RELATED_POPULATION_MAX;
        }
        switch (i) {
            case 1000:
                return DOWNLOAD_RELATED_POPULATION_MIN;
            case 1001:
                return RISKY_DOWNLOADER;
            case 1002:
                return INFREQUENT_DOWNLOADER;
            case 1003:
                return REGULAR_DOWNLOADER;
            case 1004:
                return BOTLIKE_DOWNLOADER;
            case 1005:
                return DOCUMENT_DOWNLOADER;
            case 1006:
                return HIGHLY_TECHNICAL_DOWNLOADER;
            case 1007:
                return LOW_DOWNLOAD_WARNING_CLICK_THROUGH_RATE;
            case 1008:
                return HIGH_DOWNLOAD_WARNING_CLICK_THROUGH_RATE;
            case 1009:
                return SPAM_PING_SENDER;
            case 1010:
                return RFA_TRUSTED;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzr);
    }

    public final int zza() {
        return this.zzr;
    }
}
