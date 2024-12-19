package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhju implements zzhbs {
    EVENT_URL(1),
    LANDING_PAGE(2),
    LANDING_REFERRER(3),
    CLIENT_REDIRECT(4),
    SERVER_REDIRECT(5),
    RECENT_NAVIGATION(6),
    REFERRER(7);
    
    private static final zzhbt zzh = null;
    private final int zzj;

    static {
        zzh = new zzhjs();
    }

    private zzhju(int i) {
        this.zzj = i;
    }

    public static zzhju zzb(int i) {
        switch (i) {
            case 1:
                return EVENT_URL;
            case 2:
                return LANDING_PAGE;
            case 3:
                return LANDING_REFERRER;
            case 4:
                return CLIENT_REDIRECT;
            case 5:
                return SERVER_REDIRECT;
            case 6:
                return RECENT_NAVIGATION;
            case 7:
                return REFERRER;
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
