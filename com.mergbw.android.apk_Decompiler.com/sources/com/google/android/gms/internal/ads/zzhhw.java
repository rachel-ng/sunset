package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhhw implements zzhbs {
    UNSPECIFIED(0),
    CMD_DONT_PROCEED(1),
    CMD_PROCEED(2),
    CMD_SHOW_MORE_SECTION(3),
    CMD_OPEN_HELP_CENTER(4),
    CMD_OPEN_DIAGNOSTIC(5),
    CMD_RELOAD(6),
    CMD_OPEN_DATE_SETTINGS(7),
    CMD_OPEN_LOGIN(8),
    CMD_DO_REPORT(9),
    CMD_DONT_REPORT(10),
    CMD_OPEN_REPORTING_PRIVACY(11),
    CMD_OPEN_WHITEPAPER(12),
    CMD_REPORT_PHISHING_ERROR(13),
    CMD_OPEN_ENHANCED_PROTECTION_SETTINGS(14),
    CMD_CLOSE_INTERSTITIAL_WITHOUT_UI(15);
    
    private static final zzhbt zzq = null;
    private final int zzs;

    static {
        zzq = new zzhhu();
    }

    private zzhhw(int i) {
        this.zzs = i;
    }

    public static zzhhw zzb(int i) {
        switch (i) {
            case 0:
                return UNSPECIFIED;
            case 1:
                return CMD_DONT_PROCEED;
            case 2:
                return CMD_PROCEED;
            case 3:
                return CMD_SHOW_MORE_SECTION;
            case 4:
                return CMD_OPEN_HELP_CENTER;
            case 5:
                return CMD_OPEN_DIAGNOSTIC;
            case 6:
                return CMD_RELOAD;
            case 7:
                return CMD_OPEN_DATE_SETTINGS;
            case 8:
                return CMD_OPEN_LOGIN;
            case 9:
                return CMD_DO_REPORT;
            case 10:
                return CMD_DONT_REPORT;
            case 11:
                return CMD_OPEN_REPORTING_PRIVACY;
            case 12:
                return CMD_OPEN_WHITEPAPER;
            case 13:
                return CMD_REPORT_PHISHING_ERROR;
            case 14:
                return CMD_OPEN_ENHANCED_PROTECTION_SETTINGS;
            case 15:
                return CMD_CLOSE_INTERSTITIAL_WITHOUT_UI;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzs);
    }

    public final int zza() {
        return this.zzs;
    }
}
