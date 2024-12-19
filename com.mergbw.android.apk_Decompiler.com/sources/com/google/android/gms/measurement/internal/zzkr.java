package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkr implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzkp zzb;
    private final /* synthetic */ zzkp zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzks zze;

    zzkr(zzks zzks, Bundle bundle, zzkp zzkp, zzkp zzkp2, long j) {
        this.zza = bundle;
        this.zzb = zzkp;
        this.zzc = zzkp2;
        this.zzd = j;
        this.zze = zzks;
    }

    public final void run() {
        zzks.zza(this.zze, this.zza, this.zzb, this.zzc, this.zzd);
    }
}
