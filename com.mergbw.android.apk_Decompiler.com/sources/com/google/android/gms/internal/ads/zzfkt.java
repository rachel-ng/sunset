package com.google.android.gms.internal.ads;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfkt implements zzhkp {
    private final zzhlg zza;

    public zzfkt(zzhlg zzhlg) {
        this.zza = zzhlg;
    }

    public final /* bridge */ /* synthetic */ Object zzb() {
        zzfuu.zza();
        ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, (ThreadFactory) this.zza.zzb()));
        zzhkx.zzb(unconfigurableScheduledExecutorService);
        return unconfigurableScheduledExecutorService;
    }
}
