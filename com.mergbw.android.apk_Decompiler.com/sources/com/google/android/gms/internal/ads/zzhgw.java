package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public enum zzhgw implements zzhbs {
    ACTION_UNSPECIFIED(0),
    PROCEED(1),
    DISCARD(2),
    KEEP(3),
    CLOSE(4),
    CANCEL(5),
    DISMISS(6),
    BACK(7),
    OPEN_SUBPAGE(8),
    PROCEED_DEEP_SCAN(9),
    OPEN_LEARN_MORE_LINK(10);
    
    private static final zzhbt zzl = null;
    private final int zzn;

    static {
        zzl = new zzhgu();
    }

    private zzhgw(int i) {
        this.zzn = i;
    }

    public static zzhgw zzb(int i) {
        switch (i) {
            case 0:
                return ACTION_UNSPECIFIED;
            case 1:
                return PROCEED;
            case 2:
                return DISCARD;
            case 3:
                return KEEP;
            case 4:
                return CLOSE;
            case 5:
                return CANCEL;
            case 6:
                return DISMISS;
            case 7:
                return BACK;
            case 8:
                return OPEN_SUBPAGE;
            case 9:
                return PROCEED_DEEP_SCAN;
            case 10:
                return OPEN_LEARN_MORE_LINK;
            default:
                return null;
        }
    }

    public final String toString() {
        return Integer.toString(this.zzn);
    }

    public final int zza() {
        return this.zzn;
    }
}
