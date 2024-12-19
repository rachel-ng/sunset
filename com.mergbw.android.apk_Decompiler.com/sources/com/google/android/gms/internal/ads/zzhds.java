package com.google.android.gms.internal.ads;

import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhds extends zzgzt {
    final zzhdw zza;
    zzgzv zzb = zzb();
    final /* synthetic */ zzhdy zzc;

    zzhds(zzhdy zzhdy) {
        this.zzc = zzhdy;
        this.zza = new zzhdw(zzhdy, (zzhdv) null);
    }

    private final zzgzv zzb() {
        zzhdw zzhdw = this.zza;
        if (zzhdw.hasNext()) {
            return zzhdw.next().iterator();
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final byte zza() {
        zzgzv zzgzv = this.zzb;
        if (zzgzv != null) {
            byte zza2 = zzgzv.zza();
            if (!this.zzb.hasNext()) {
                this.zzb = zzb();
            }
            return zza2;
        }
        throw new NoSuchElementException();
    }
}
