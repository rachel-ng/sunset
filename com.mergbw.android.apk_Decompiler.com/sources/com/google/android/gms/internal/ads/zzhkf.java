package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzhkf implements Iterator {
    int zza = 0;
    final /* synthetic */ zzhkg zzb;

    zzhkf(zzhkg zzhkg) {
        this.zzb = zzhkg;
    }

    public final boolean hasNext() {
        return this.zza < this.zzb.zza.size() || this.zzb.zzb.hasNext();
    }

    public final Object next() {
        if (this.zza < this.zzb.zza.size()) {
            zzhkg zzhkg = this.zzb;
            int i = this.zza;
            this.zza = i + 1;
            return zzhkg.zza.get(i);
        }
        zzhkg zzhkg2 = this.zzb;
        zzhkg2.zza.add(zzhkg2.zzb.next());
        return next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
