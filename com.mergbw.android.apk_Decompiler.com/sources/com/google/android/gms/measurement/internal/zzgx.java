package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzx;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzgx implements Callable {
    private /* synthetic */ zzgt zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzgx(zzgt zzgt, String str) {
        this.zza = zzgt;
        this.zzb = str;
    }

    public final Object call() {
        return new zzx("internal.appMetadata", new zzgv(this.zza, this.zzb));
    }
}
