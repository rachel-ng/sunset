package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfis implements zzhkp {
    private final zzfir zza;

    public zzfis(zzfir zzfir) {
        this.zza = zzfir;
    }

    public final /* synthetic */ Object zzb() {
        Clock instance = DefaultClock.getInstance();
        zzhkx.zzb(instance);
        return instance;
    }
}
