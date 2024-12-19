package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzi extends zzgby {
    final transient Map zza;
    final /* synthetic */ zzfzv zzb;

    zzfzi(zzfzv zzfzv, Map map) {
        this.zzb = zzfzv;
        this.zza = map;
    }

    public final void clear() {
        zzfzv zzfzv = this.zzb;
        if (this.zza == zzfzv.zza) {
            zzfzv.zzp();
        } else {
            zzgbm.zzb(new zzfzh(this));
        }
    }

    public final boolean containsKey(@CheckForNull Object obj) {
        Map map = this.zza;
        map.getClass();
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public final boolean equals(@CheckForNull Object obj) {
        return this == obj || this.zza.equals(obj);
    }

    @CheckForNull
    public final /* bridge */ /* synthetic */ Object get(@CheckForNull Object obj) {
        Collection collection = (Collection) zzgbz.zza(this.zza, obj);
        if (collection == null) {
            return null;
        }
        return this.zzb.zzc(obj, collection);
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public Set keySet() {
        return this.zzb.zzt();
    }

    @CheckForNull
    public final /* bridge */ /* synthetic */ Object remove(@CheckForNull Object obj) {
        Collection collection = (Collection) this.zza.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection zza2 = this.zzb.zza();
        zza2.addAll(collection);
        zzfzv zzfzv = this.zzb;
        zzfzv.zzb = zzfzv.zzb - collection.size();
        collection.clear();
        return zza2;
    }

    public final int size() {
        return this.zza.size();
    }

    public final String toString() {
        return this.zza.toString();
    }

    /* access modifiers changed from: package-private */
    public final Map.Entry zza(Map.Entry entry) {
        Object key = entry.getKey();
        return new zzgay(key, this.zzb.zzc(key, (Collection) entry.getValue()));
    }

    /* access modifiers changed from: protected */
    public final Set zzb() {
        return new zzfzg(this);
    }
}
