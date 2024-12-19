package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfmx implements zzhbt {
    zzfmx() {
    }

    public final /* synthetic */ zzhbs zza(int i) {
        zzfmy zzfmy = zzfmy.ORIENTATION_UNKNOWN;
        if (i == 0) {
            return zzfmy.ORIENTATION_UNKNOWN;
        }
        if (i == 1) {
            return zzfmy.ORIENTATION_PORTRAIT;
        }
        if (i != 2) {
            return null;
        }
        return zzfmy.ORIENTATION_LANDSCAPE;
    }
}
