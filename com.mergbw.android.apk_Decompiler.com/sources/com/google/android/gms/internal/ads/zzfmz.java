package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfmz implements zzhbt {
    zzfmz() {
    }

    public final /* synthetic */ zzhbs zza(int i) {
        zzfna zzfna = zzfna.OS_UNKNOWN;
        if (i == 0) {
            return zzfna.OS_UNKNOWN;
        }
        if (i == 1) {
            return zzfna.OS_ANDROID;
        }
        if (i != 2) {
            return null;
        }
        return zzfna.OS_IOS;
    }
}
