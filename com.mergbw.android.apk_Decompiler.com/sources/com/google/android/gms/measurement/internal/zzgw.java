package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzr;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzgw implements Callable {
    private /* synthetic */ zzgt zza;

    public /* synthetic */ zzgw(zzgt zzgt) {
        this.zza = zzgt;
    }

    public final Object call() {
        return new zzr(this.zza.zzb);
    }
}
