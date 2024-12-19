package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzif implements Callable<byte[]> {
    private final /* synthetic */ zzbd zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzhn zzc;

    public final /* synthetic */ Object call() throws Exception {
        this.zzc.zza.zzr();
        return this.zzc.zza.zzm().zza(this.zza, this.zzb);
    }

    zzif(zzhn zzhn, zzbd zzbd, String str) {
        this.zza = zzbd;
        this.zzb = str;
        this.zzc = zzhn;
    }
}
