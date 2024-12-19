package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

@Deprecated
/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzhev extends AbstractList implements RandomAccess, zzhcm {
    /* access modifiers changed from: private */
    public final zzhcm zza;

    public zzhev(zzhcm zzhcm) {
        this.zza = zzhcm;
    }

    public final /* bridge */ /* synthetic */ Object get(int i) {
        return ((zzhcl) this.zza).get(i);
    }

    public final Iterator iterator() {
        return new zzheu(this);
    }

    public final ListIterator listIterator(int i) {
        return new zzhet(this, i);
    }

    public final int size() {
        return this.zza.size();
    }

    public final zzhcm zzd() {
        return this;
    }

    public final Object zze(int i) {
        return this.zza.zze(i);
    }

    public final List zzh() {
        return this.zza.zzh();
    }

    public final void zzi(zzhac zzhac) {
        throw new UnsupportedOperationException();
    }
}
