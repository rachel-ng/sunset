package com.google.android.gms.internal.ads;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzhlc {
    private final List zza;
    private final List zzb;

    /* synthetic */ zzhlc(int i, int i2, zzhlb zzhlb) {
        this.zza = zzhkm.zzc(i);
        this.zzb = zzhkm.zzc(i2);
    }

    public final zzhlc zza(zzhky zzhky) {
        this.zzb.add(zzhky);
        return this;
    }

    public final zzhlc zzb(zzhky zzhky) {
        this.zza.add(zzhky);
        return this;
    }

    public final zzhld zzc() {
        return new zzhld(this.zza, this.zzb, (zzhlb) null);
    }
}
