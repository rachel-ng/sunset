package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhim implements zzhbs {
    UNKNOWN(0),
    URL_PHISHING(1),
    URL_MALWARE(2),
    URL_UNWANTED(3),
    CLIENT_SIDE_PHISHING_URL(4),
    CLIENT_SIDE_MALWARE_URL(5),
    DANGEROUS_DOWNLOAD_RECOVERY(6),
    DANGEROUS_DOWNLOAD_WARNING(7),
    OCTAGON_AD(8),
    OCTAGON_AD_SB_MATCH(9),
    DANGEROUS_DOWNLOAD_BY_API(10),
    OCTAGON_IOS_AD(11),
    PASSWORD_PROTECTION_PHISHING_URL(12),
    DANGEROUS_DOWNLOAD_OPENED(13),
    AD_SAMPLE(14),
    URL_SUSPICIOUS(15),
    BILLING(16),
    APK_DOWNLOAD(17),
    BLOCKED_AD_DRIVE_BY_DOWNLOAD(18),
    BLOCKED_AD_REDIRECT(19),
    BLOCKED_AD_POPUP(20),
    HASH_PREFIX_REAL_TIME_EXPERIMENT(21),
    PHISHY_SITE_INTERACTIONS(22),
    WARNING_SHOWN(23),
    NOTIFICATION_PERMISSION_ACCEPTED(24),
    DANGEROUS_DOWNLOAD_AUTO_DELETED(25),
    DANGEROUS_DOWNLOAD_PROFILE_CLOSED(26);
    
    private static final zzhbt zzB = null;
    private final int zzD;

    static {
        zzB = new zzhik();
    }

    private zzhim(int i) {
        this.zzD = i;
    }

    public static zzhim zzb(int i) {
        switch (i) {
            case 0:
                return UNKNOWN;
            case 1:
                return URL_PHISHING;
            case 2:
                return URL_MALWARE;
            case 3:
                return URL_UNWANTED;
            case 4:
                return CLIENT_SIDE_PHISHING_URL;
            case 5:
                return CLIENT_SIDE_MALWARE_URL;
            case 6:
                return DANGEROUS_DOWNLOAD_RECOVERY;
            case 7:
                return DANGEROUS_DOWNLOAD_WARNING;
            case 8:
                return OCTAGON_AD;
            case 9:
                return OCTAGON_AD_SB_MATCH;
            case 10:
                return DANGEROUS_DOWNLOAD_BY_API;
            case 11:
                return OCTAGON_IOS_AD;
            case 12:
                return PASSWORD_PROTECTION_PHISHING_URL;
            case 13:
                return DANGEROUS_DOWNLOAD_OPENED;
            case 14:
                return AD_SAMPLE;
            case 15:
                return URL_SUSPICIOUS;
            case 16:
                return BILLING;
            case 17:
                return APK_DOWNLOAD;
            case 18:
                return BLOCKED_AD_DRIVE_BY_DOWNLOAD;
            case 19:
                return BLOCKED_AD_REDIRECT;
            case 20:
                return BLOCKED_AD_POPUP;
            case 21:
                return HASH_PREFIX_REAL_TIME_EXPERIMENT;
            case 22:
                return PHISHY_SITE_INTERACTIONS;
            case 23:
                return WARNING_SHOWN;
            case 24:
                return NOTIFICATION_PERMISSION_ACCEPTED;
            case 25:
                return DANGEROUS_DOWNLOAD_AUTO_DELETED;
            case 26:
                return DANGEROUS_DOWNLOAD_PROFILE_CLOSED;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzD);
    }

    public final int zza() {
        return this.zzD;
    }
}
