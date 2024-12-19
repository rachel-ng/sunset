package com.google.android.gms.internal.ads;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzfzv extends zzfzy implements Serializable {
    /* access modifiers changed from: private */
    public final transient Map zza;
    /* access modifiers changed from: private */
    public transient int zzb;

    protected zzfzv(Map map) {
        zzfyg.zze(map.isEmpty());
        this.zza = map;
    }

    static /* bridge */ /* synthetic */ void zzo(zzfzv zzfzv, Object obj) {
        Object obj2;
        try {
            obj2 = zzfzv.zza.remove(obj);
        } catch (ClassCastException | NullPointerException unused) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            zzfzv.zzb -= size;
        }
    }

    /* access modifiers changed from: package-private */
    public abstract Collection zza();

    /* access modifiers changed from: package-private */
    public Collection zzb(Collection collection) {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public Collection zzc(Object obj, Collection collection) {
        throw null;
    }

    public final int zze() {
        return this.zzb;
    }

    /* access modifiers changed from: package-private */
    public final Collection zzf() {
        return new zzfzx(this);
    }

    /* access modifiers changed from: package-private */
    public final Iterator zzg() {
        return new zzfzf(this);
    }

    /* access modifiers changed from: package-private */
    public final List zzh(Object obj, List list, @CheckForNull zzfzs zzfzs) {
        if (list instanceof RandomAccess) {
            return new zzfzo(this, obj, list, zzfzs);
        }
        return new zzfzu(this, obj, list, zzfzs);
    }

    /* access modifiers changed from: package-private */
    public Map zzj() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final Map zzk() {
        Map map = this.zza;
        if (map instanceof NavigableMap) {
            return new zzfzm(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new zzfzp(this, (SortedMap) map);
        }
        return new zzfzi(this, map);
    }

    /* access modifiers changed from: package-private */
    public Set zzl() {
        throw null;
    }

    /* access modifiers changed from: package-private */
    public final Set zzm() {
        Map map = this.zza;
        if (map instanceof NavigableMap) {
            return new zzfzn(this, (NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new zzfzq(this, (SortedMap) map);
        }
        return new zzfzl(this, map);
    }

    public final void zzp() {
        for (Collection clear : this.zza.values()) {
            clear.clear();
        }
        this.zza.clear();
        this.zzb = 0;
    }

    public final boolean zzq(Object obj, Object obj2) {
        Collection collection = (Collection) this.zza.get(obj);
        if (collection == null) {
            Collection zza2 = zza();
            if (zza2.add(obj2)) {
                this.zzb++;
                this.zza.put(obj, zza2);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(obj2)) {
            return false;
        } else {
            this.zzb++;
            return true;
        }
    }
}
