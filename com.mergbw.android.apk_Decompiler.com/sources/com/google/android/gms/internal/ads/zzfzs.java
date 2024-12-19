package com.google.android.gms.internal.ads;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzs extends AbstractCollection {
    final Object zza;
    Collection zzb;
    @CheckForNull
    final zzfzs zzc;
    @CheckForNull
    final Collection zzd;
    final /* synthetic */ zzfzv zze;

    zzfzs(zzfzv zzfzv, Object obj, @CheckForNull Collection collection, zzfzs zzfzs) {
        this.zze = zzfzv;
        this.zza = obj;
        this.zzb = collection;
        this.zzc = zzfzs;
        this.zzd = zzfzs == null ? null : zzfzs.zzb;
    }

    public final boolean add(Object obj) {
        zzb();
        boolean isEmpty = this.zzb.isEmpty();
        boolean add = this.zzb.add(obj);
        if (add) {
            zzfzv zzfzv = this.zze;
            zzfzv.zzb = zzfzv.zzb + 1;
            if (isEmpty) {
                zza();
                return true;
            }
        }
        return add;
    }

    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = this.zzb.addAll(collection);
        if (!addAll) {
            return addAll;
        }
        int size2 = this.zzb.size();
        zzfzv zzfzv = this.zze;
        zzfzv.zzb = zzfzv.zzb + (size2 - size);
        if (size != 0) {
            return addAll;
        }
        zza();
        return true;
    }

    public final void clear() {
        int size = size();
        if (size != 0) {
            this.zzb.clear();
            zzfzv zzfzv = this.zze;
            zzfzv.zzb = zzfzv.zzb - size;
            zzc();
        }
    }

    public final boolean contains(@CheckForNull Object obj) {
        zzb();
        return this.zzb.contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        zzb();
        return this.zzb.containsAll(collection);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        zzb();
        return this.zzb.equals(obj);
    }

    public final int hashCode() {
        zzb();
        return this.zzb.hashCode();
    }

    public final Iterator iterator() {
        zzb();
        return new zzfzr(this);
    }

    public final boolean remove(@CheckForNull Object obj) {
        zzb();
        boolean remove = this.zzb.remove(obj);
        if (remove) {
            zzfzv zzfzv = this.zze;
            zzfzv.zzb = zzfzv.zzb - 1;
            zzc();
        }
        return remove;
    }

    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean removeAll = this.zzb.removeAll(collection);
        if (removeAll) {
            int size2 = this.zzb.size();
            zzfzv zzfzv = this.zze;
            zzfzv.zzb = zzfzv.zzb + (size2 - size);
            zzc();
        }
        return removeAll;
    }

    public final int size() {
        zzb();
        return this.zzb.size();
    }

    public final String toString() {
        zzb();
        return this.zzb.toString();
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        zzfzs zzfzs = this.zzc;
        if (zzfzs != null) {
            zzfzs.zza();
            return;
        }
        zzfzv zzfzv = this.zze;
        zzfzv.zza.put(this.zza, this.zzb);
    }

    /* access modifiers changed from: package-private */
    public final void zzb() {
        zzfzs zzfzs = this.zzc;
        if (zzfzs != null) {
            zzfzs.zzb();
            zzfzs zzfzs2 = this.zzc;
            if (zzfzs2.zzb != this.zzd) {
                throw new ConcurrentModificationException();
            }
        } else if (this.zzb.isEmpty()) {
            zzfzv zzfzv = this.zze;
            Collection collection = (Collection) zzfzv.zza.get(this.zza);
            if (collection != null) {
                this.zzb = collection;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        zzfzs zzfzs = this.zzc;
        if (zzfzs != null) {
            zzfzs.zzc();
        } else if (this.zzb.isEmpty()) {
            zzfzv zzfzv = this.zze;
            zzfzv.zza.remove(this.zza);
        }
    }

    public final boolean retainAll(Collection collection) {
        collection.getClass();
        int size = size();
        boolean retainAll = this.zzb.retainAll(collection);
        if (retainAll) {
            int size2 = this.zzb.size();
            zzfzv zzfzv = this.zze;
            zzfzv.zzb = zzfzv.zzb + (size2 - size);
            zzc();
        }
        return retainAll;
    }
}
