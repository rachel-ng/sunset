package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfzk implements Iterator {
    @CheckForNull
    Map.Entry zza;
    final /* synthetic */ Iterator zzb;
    final /* synthetic */ zzfzl zzc;

    zzfzk(zzfzl zzfzl, Iterator it) {
        this.zzb = it;
        this.zzc = zzfzl;
    }

    public final boolean hasNext() {
        return this.zzb.hasNext();
    }

    public final Object next() {
        Map.Entry entry = (Map.Entry) this.zzb.next();
        this.zza = entry;
        return entry.getKey();
    }

    public final void remove() {
        zzfyg.zzk(this.zza != null, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.zza.getValue();
        this.zzb.remove();
        zzfzv zzfzv = this.zzc.zza;
        zzfzv.zzb = zzfzv.zzb - collection.size();
        collection.clear();
        this.zza = null;
    }
}
