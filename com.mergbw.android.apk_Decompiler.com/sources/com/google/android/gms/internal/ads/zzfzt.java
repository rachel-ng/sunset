package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.ListIterator;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfzt extends zzfzr implements ListIterator {
    final /* synthetic */ zzfzu zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfzt(zzfzu zzfzu) {
        super(zzfzu);
        this.zzd = zzfzu;
    }

    public final void add(Object obj) {
        boolean isEmpty = this.zzd.isEmpty();
        zza();
        ((ListIterator) this.zza).add(obj);
        zzfzv zzfzv = this.zzd.zzf;
        zzfzv.zzb = zzfzv.zzb + 1;
        if (isEmpty) {
            this.zzd.zza();
        }
    }

    public final boolean hasPrevious() {
        zza();
        return ((ListIterator) this.zza).hasPrevious();
    }

    public final int nextIndex() {
        zza();
        return ((ListIterator) this.zza).nextIndex();
    }

    public final Object previous() {
        zza();
        return ((ListIterator) this.zza).previous();
    }

    public final int previousIndex() {
        zza();
        return ((ListIterator) this.zza).previousIndex();
    }

    public final void set(Object obj) {
        zza();
        ((ListIterator) this.zza).set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzfzt(zzfzu zzfzu, int i) {
        super(zzfzu, ((List) zzfzu.zzb).listIterator(i));
        this.zzd = zzfzu;
    }
}
