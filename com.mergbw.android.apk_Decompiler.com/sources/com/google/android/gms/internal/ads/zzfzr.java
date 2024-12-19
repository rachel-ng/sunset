package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzr implements Iterator {
    final Iterator zza;
    final Collection zzb;
    final /* synthetic */ zzfzs zzc;

    zzfzr(zzfzs zzfzs) {
        Iterator it;
        this.zzc = zzfzs;
        this.zzb = zzfzs.zzb;
        Collection collection = zzfzs.zzb;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.zza = it;
    }

    zzfzr(zzfzs zzfzs, Iterator it) {
        this.zzc = zzfzs;
        this.zzb = zzfzs.zzb;
        this.zza = it;
    }

    public final boolean hasNext() {
        zza();
        return this.zza.hasNext();
    }

    public final Object next() {
        zza();
        return this.zza.next();
    }

    public final void remove() {
        this.zza.remove();
        zzfzv zzfzv = this.zzc.zze;
        zzfzv.zzb = zzfzv.zzb - 1;
        this.zzc.zzc();
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzc.zzb();
        if (this.zzc.zzb != this.zzb) {
            throw new ConcurrentModificationException();
        }
    }
}
