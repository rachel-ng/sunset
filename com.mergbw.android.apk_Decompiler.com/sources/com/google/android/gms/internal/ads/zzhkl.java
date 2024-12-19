package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzhkl implements zzhkp {
    private final Map zza;

    zzhkl(Map map) {
        this.zza = Collections.unmodifiableMap(map);
    }

    /* access modifiers changed from: package-private */
    public final Map zza() {
        return this.zza;
    }
}
