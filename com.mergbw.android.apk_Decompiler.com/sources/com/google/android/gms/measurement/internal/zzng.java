package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzng implements Callable<String> {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zznc zzb;

    public final /* synthetic */ Object call() throws Exception {
        if (!this.zzb.zzb((String) Preconditions.checkNotNull(this.zza.zza)).zzj() || !zzin.zzb(this.zza.zzt).zzj()) {
            this.zzb.zzj().zzp().zza("Analytics storage consent denied. Returning null app instance id");
            return null;
        }
        zzg zza2 = this.zzb.zza(this.zza);
        if (zza2 != null) {
            return zza2.zzad();
        }
        this.zzb.zzj().zzu().zza("App info was null when attempting to get app instance id");
        return null;
    }

    zzng(zznc zznc, zzo zzo) {
        this.zza = zzo;
        this.zzb = zznc;
    }
}
