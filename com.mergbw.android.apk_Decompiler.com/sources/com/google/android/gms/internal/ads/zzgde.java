package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgde {
    static int zza(Set set) {
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i += next != null ? next.hashCode() : 0;
        }
        return i;
    }

    public static zzgdd zzb(Set set, Set set2) {
        zzfyg.zzc(set, "set1");
        zzfyg.zzc(set2, "set2");
        return new zzgcy(set, set2);
    }

    /* JADX WARNING: type inference failed for: r1v2, types: [java.util.Collection, java.util.Set] */
    public static Set zzc(Set set, zzfyh zzfyh) {
        if (set instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) set;
            if (sortedSet instanceof zzgcz) {
                zzgcz zzgcz = (zzgcz) sortedSet;
                return new zzgda((SortedSet) zzgcz.zza, zzfyk.zza(zzgcz.zzb, zzfyh));
            }
            sortedSet.getClass();
            return new zzgda(sortedSet, zzfyh);
        } else if (set instanceof zzgcz) {
            zzgcz zzgcz2 = (zzgcz) set;
            return new zzgcz(zzgcz2.zza, zzfyk.zza(zzgcz2.zzb, zzfyh));
        } else {
            set.getClass();
            return new zzgcz(set, zzfyh);
        }
    }

    static boolean zzd(Set set, @CheckForNull Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                return set.size() == set2.size() && set.containsAll(set2);
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
    }

    static boolean zzf(Set set, Iterator it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    static boolean zze(Set set, Collection collection) {
        collection.getClass();
        if (collection instanceof zzgck) {
            collection = ((zzgck) collection).zza();
        }
        if (!(collection instanceof Set) || collection.size() <= set.size()) {
            return zzf(set, collection.iterator());
        }
        Iterator it = set.iterator();
        collection.getClass();
        boolean z = false;
        while (it.hasNext()) {
            if (collection.contains(it.next())) {
                it.remove();
                z = true;
            }
        }
        return z;
    }
}
