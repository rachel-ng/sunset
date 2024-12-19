package com.google.android.gms.internal.ads;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhdw implements Iterator {
    private final ArrayDeque zza;
    private zzgzx zzb;

    /* synthetic */ zzhdw(zzhac zzhac, zzhdv zzhdv) {
        if (zzhac instanceof zzhdy) {
            zzhdy zzhdy = (zzhdy) zzhac;
            ArrayDeque arrayDeque = new ArrayDeque(zzhdy.zzf());
            this.zza = arrayDeque;
            arrayDeque.push(zzhdy);
            this.zzb = zzb(zzhdy.zzd);
            return;
        }
        this.zza = null;
        this.zzb = (zzgzx) zzhac;
    }

    private final zzgzx zzb(zzhac zzhac) {
        while (zzhac instanceof zzhdy) {
            zzhdy zzhdy = (zzhdy) zzhac;
            this.zza.push(zzhdy);
            zzhac = zzhdy.zzd;
        }
        return (zzgzx) zzhac;
    }

    public final boolean hasNext() {
        return this.zzb != null;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    /* renamed from: zza */
    public final zzgzx next() {
        zzgzx zzgzx;
        zzgzx zzgzx2 = this.zzb;
        if (zzgzx2 != null) {
            do {
                ArrayDeque arrayDeque = this.zza;
                zzgzx = null;
                if (arrayDeque == null || arrayDeque.isEmpty()) {
                    this.zzb = zzgzx;
                } else {
                    zzgzx = zzb(((zzhdy) this.zza.pop()).zze);
                }
            } while (zzgzx.zzd() == 0);
            this.zzb = zzgzx;
            return zzgzx2;
        }
        throw new NoSuchElementException();
    }
}
