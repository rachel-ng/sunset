package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfmv implements zzhbt {
    zzfmv() {
    }

    public final /* synthetic */ zzhbs zza(int i) {
        zzfmw zzfmw = zzfmw.FORMAT_UNKNOWN;
        switch (i) {
            case 0:
                return zzfmw.FORMAT_UNKNOWN;
            case 1:
                return zzfmw.FORMAT_BANNER;
            case 2:
                return zzfmw.FORMAT_INTERSTITIAL;
            case 3:
                return zzfmw.FORMAT_REWARDED;
            case 4:
                return zzfmw.FORMAT_REWARDED_INTERSTITIAL;
            case 5:
                return zzfmw.FORMAT_APP_OPEN;
            case 6:
                return zzfmw.FORMAT_NATIVE;
            default:
                return null;
        }
    }
}
