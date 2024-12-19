package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaq {
    public final zzt zza;

    /* synthetic */ zzaq(zzt zzt, int i, int i2, float f, long j, zzap zzap) {
        boolean z = true;
        zzeq.zze(i > 0, "width must be positive, but is: " + i);
        zzeq.zze(i2 <= 0 ? false : z, "height must be positive, but is: " + i2);
        this.zza = zzt;
    }
}
