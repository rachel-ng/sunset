package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzia implements Callable<zzaj> {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzhn zzb;

    public final /* synthetic */ Object call() throws Exception {
        this.zzb.zza.zzr();
        return new zzaj(this.zzb.zza.zza(this.zza.zza));
    }

    zzia(zzhn zzhn, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzhn;
    }
}
