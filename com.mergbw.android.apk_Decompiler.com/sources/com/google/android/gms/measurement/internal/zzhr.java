package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzhr implements Runnable {
    private final /* synthetic */ zzo zza;
    private final /* synthetic */ zzhn zzb;

    zzhr(zzhn zzhn, zzo zzo) {
        this.zza = zzo;
        this.zzb = zzhn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        zznc zza2 = this.zzb.zza;
        zzo zzo = this.zza;
        zza2.zzl().zzt();
        zza2.zzs();
        Preconditions.checkNotEmpty(zzo.zza);
        zza2.zza(zzo);
    }
}
