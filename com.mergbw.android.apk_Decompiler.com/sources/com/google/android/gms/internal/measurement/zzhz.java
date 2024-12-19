package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzhz extends zzib {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzia zzc;

    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }

    zzhz(zzia zzia) {
        this.zzc = zzia;
        this.zzb = zzia.zzb();
    }

    public final boolean hasNext() {
        return this.zza < this.zzb;
    }
}
