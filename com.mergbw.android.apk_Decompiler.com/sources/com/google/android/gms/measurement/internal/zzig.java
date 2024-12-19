package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzig implements Callable<List<zznq>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzhn zzb;

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzr();
        return this.zzb.zza.zzf().zzk(this.zza);
    }

    zzig(zzhn zzhn, String str) {
        this.zza = str;
        this.zzb = zzhn;
    }
}
