package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzu extends zzfzs implements List {
    final /* synthetic */ zzfzv zzf;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfzu(zzfzv zzfzv, Object obj, @CheckForNull List list, zzfzs zzfzs) {
        super(zzfzv, obj, list, zzfzs);
        this.zzf = zzfzv;
    }

    public final void add(int i, Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        ((List) this.zzb).add(i, obj);
        zzfzv zzfzv = this.zzf;
        zzfzv.zzb = zzfzv.zzb + 1;
        if (isEmpty) {
            zza();
        }
    }

    public final boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.zzb).addAll(i, collection);
        if (!addAll) {
            return addAll;
        }
        int size2 = this.zzb.size();
        zzfzv zzfzv = this.zzf;
        zzfzv.zzb = zzfzv.zzb + (size2 - size);
        if (size != 0) {
            return addAll;
        }
        zza();
        return true;
    }

    public final Object get(int i) {
        zzb();
        return ((List) this.zzb).get(i);
    }

    public final int indexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).indexOf(obj);
    }

    public final int lastIndexOf(@CheckForNull Object obj) {
        zzb();
        return ((List) this.zzb).lastIndexOf(obj);
    }

    public final ListIterator listIterator() {
        zzb();
        return new zzfzt(this);
    }

    public final Object remove(int i) {
        zzb();
        Object remove = ((List) this.zzb).remove(i);
        zzfzv zzfzv = this.zzf;
        zzfzv.zzb = zzfzv.zzb - 1;
        zzc();
        return remove;
    }

    public final Object set(int i, Object obj) {
        zzb();
        return ((List) this.zzb).set(i, obj);
    }

    public final List subList(int i, int i2) {
        zzb();
        List subList = ((List) this.zzb).subList(i, i2);
        zzfzs zzfzs = this.zzc;
        if (zzfzs == null) {
            zzfzs = this;
        }
        return this.zzf.zzh(this.zza, subList, zzfzs);
    }

    public final ListIterator listIterator(int i) {
        zzb();
        return new zzfzt(this, i);
    }
}
