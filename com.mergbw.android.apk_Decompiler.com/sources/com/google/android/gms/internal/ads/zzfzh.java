package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfzh implements Iterator {
    final Iterator zza;
    @CheckForNull
    Collection zzb;
    final /* synthetic */ zzfzi zzc;

    zzfzh(zzfzi zzfzi) {
        this.zzc = zzfzi;
        this.zza = zzfzi.zza.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.zza.next();
        this.zzb = (Collection) entry.getValue();
        return this.zzc.zza(entry);
    }

    public final void remove() {
        zzfyg.zzk(this.zzb != null, "no calls to next() since the last call to remove()");
        this.zza.remove();
        zzfzv zzfzv = this.zzc.zzb;
        zzfzv.zzb = zzfzv.zzb - this.zzb.size();
        this.zzb.clear();
        this.zzb = null;
    }
}
