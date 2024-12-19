package com.google.android.gms.internal.ads;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
class zzfzj implements Iterator {
    final Iterator zza;
    @CheckForNull
    Object zzb = null;
    @CheckForNull
    Collection zzc = null;
    Iterator zzd = zzgbk.INSTANCE;
    final /* synthetic */ zzfzv zze;

    zzfzj(zzfzv zzfzv) {
        this.zze = zzfzv;
        this.zza = zzfzv.zza.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.zza.hasNext() || this.zzd.hasNext();
    }

    public final Object next() {
        if (!this.zzd.hasNext()) {
            Map.Entry entry = (Map.Entry) this.zza.next();
            this.zzb = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.zzc = collection;
            this.zzd = collection.iterator();
        }
        return this.zzd.next();
    }

    public final void remove() {
        this.zzd.remove();
        if (((Collection) Objects.requireNonNull(this.zzc)).isEmpty()) {
            this.zza.remove();
        }
        zzfzv zzfzv = this.zze;
        zzfzv.zzb = zzfzv.zzb - 1;
    }
}
