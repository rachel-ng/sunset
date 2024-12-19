package com.google.android.gms.internal.ads;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgak extends AbstractCollection {
    final /* synthetic */ zzgal zza;

    zzgak(zzgal zzgal) {
        this.zza = zzgal;
    }

    public final void clear() {
        this.zza.clear();
    }

    public final Iterator iterator() {
        zzgal zzgal = this.zza;
        Map zzl = zzgal.zzl();
        if (zzl != null) {
            return zzl.values().iterator();
        }
        return new zzgae(zzgal);
    }

    public final int size() {
        return this.zza.size();
    }
}
