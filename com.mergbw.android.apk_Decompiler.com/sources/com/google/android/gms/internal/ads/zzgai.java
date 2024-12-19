package com.google.android.gms.internal.ads;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgai extends AbstractSet {
    final /* synthetic */ zzgal zza;

    zzgai(zzgal zzgal) {
        this.zza = zzgal;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final boolean contains(@CheckForNull Object obj) {
        return this.zza.containsKey(obj);
    }

    public final Iterator iterator() {
        zzgal zzgal = this.zza;
        Map zzl = zzgal.zzl();
        if (zzl != null) {
            return zzl.keySet().iterator();
        }
        return new zzgac(zzgal);
    }

    public final boolean remove(@CheckForNull Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.keySet().remove(obj);
        }
        return this.zza.zzy(obj) != zzgal.zzd;
    }

    public final int size() {
        return this.zza.size();
    }
}
