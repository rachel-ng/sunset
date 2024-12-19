package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgof {
    final Map zza = new HashMap();
    final Map zzb = new HashMap();

    private zzgof() {
    }

    public final zzgof zza(Enum enumR, Object obj) {
        this.zza.put(enumR, obj);
        this.zzb.put(obj, enumR);
        return this;
    }

    public final zzgoh zzb() {
        return new zzgoh(Collections.unmodifiableMap(this.zza), Collections.unmodifiableMap(this.zzb), (zzgog) null);
    }

    /* synthetic */ zzgof(zzgoe zzgoe) {
    }
}
