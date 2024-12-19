package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzbdv;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzdrp implements zzhkp {
    private final zzhlg zza;

    public zzdrp(zzhlg zzhlg) {
        this.zza = zzhlg;
    }

    public final /* bridge */ /* synthetic */ Object zzb() {
        zzbdv.zza.C0001zza zza2;
        if (((zzczc) this.zza).zza().zzo.zza == 3) {
            zza2 = zzbdv.zza.C0001zza.REWARDED_INTERSTITIAL;
        } else {
            zza2 = zzbdv.zza.C0001zza.REWARD_BASED_VIDEO_AD;
        }
        zzhkx.zzb(zza2);
        return zza2;
    }
}
