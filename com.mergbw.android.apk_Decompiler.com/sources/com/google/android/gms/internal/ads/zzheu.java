package com.google.android.gms.internal.ads;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzheu implements Iterator {
    final Iterator zza;
    final /* synthetic */ zzhev zzb;

    zzheu(zzhev zzhev) {
        this.zzb = zzhev;
        this.zza = zzhev.zza.iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.zza.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
