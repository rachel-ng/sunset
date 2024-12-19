package com.google.android.gms.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public enum AdFormat {
    BANNER(0),
    INTERSTITIAL(1),
    REWARDED(2),
    REWARDED_INTERSTITIAL(3),
    NATIVE(4),
    APP_OPEN_AD(6);
    
    private final int zzb;

    private AdFormat(int i) {
        this.zzb = i;
    }

    public int getValue() {
        return this.zzb;
    }
}