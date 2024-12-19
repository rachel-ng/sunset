package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzm;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzgu implements Callable {
    private /* synthetic */ zzgt zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzgu(zzgt zzgt, String str) {
        this.zza = zzgt;
        this.zzb = str;
    }

    public final Object call() {
        return new zzm("internal.remoteConfig", new zzhb(this.zza, this.zzb));
    }
}
