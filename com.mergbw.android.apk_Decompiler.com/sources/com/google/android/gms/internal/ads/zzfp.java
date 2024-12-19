package com.google.android.gms.internal.ads;

import android.telephony.TelephonyCallback;
import android.telephony.TelephonyDisplayInfo;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfp extends TelephonyCallback implements TelephonyCallback.DisplayInfoListener {
    private final zzfs zza;

    public zzfp(zzfs zzfs) {
        this.zza = zzfs;
    }

    public final void onDisplayInfoChanged(TelephonyDisplayInfo telephonyDisplayInfo) {
        int m = telephonyDisplayInfo.getOverrideNetworkType();
        int i = 5;
        boolean z = m == 3 || m == 4 || m == 5;
        zzfs zzfs = this.zza;
        if (true == z) {
            i = 10;
        }
        zzfs.zzc(zzfs, i);
    }
}
