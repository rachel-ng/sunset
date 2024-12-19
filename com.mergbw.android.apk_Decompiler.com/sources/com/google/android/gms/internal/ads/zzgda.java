package com.google.android.gms.internal.ads;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgda extends zzgcz implements SortedSet {
    zzgda(SortedSet sortedSet, zzfyh zzfyh) {
        super(sortedSet, zzfyh);
    }

    @CheckForNull
    public final Comparator comparator() {
        return ((SortedSet) this.zza).comparator();
    }

    public final Object first() {
        Iterator it = this.zza.iterator();
        it.getClass();
        zzfyh zzfyh = this.zzb;
        zzfyh.getClass();
        while (it.hasNext()) {
            Object next = it.next();
            if (zzfyh.zza(next)) {
                return next;
            }
        }
        throw new NoSuchElementException();
    }

    public final SortedSet headSet(Object obj) {
        return new zzgda(((SortedSet) this.zza).headSet(obj), this.zzb);
    }

    public final Object last() {
        SortedSet sortedSet = (SortedSet) this.zza;
        while (true) {
            zzfyh zzfyh = this.zzb;
            Object last = sortedSet.last();
            if (zzfyh.zza(last)) {
                return last;
            }
            sortedSet = sortedSet.headSet(last);
        }
    }

    public final SortedSet subSet(Object obj, Object obj2) {
        return new zzgda(((SortedSet) this.zza).subSet(obj, obj2), this.zzb);
    }

    public final SortedSet tailSet(Object obj) {
        return new zzgda(((SortedSet) this.zza).tailSet(obj), this.zzb);
    }
}
