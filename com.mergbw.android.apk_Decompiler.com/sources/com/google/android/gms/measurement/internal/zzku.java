package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzku implements Runnable {
    private final /* synthetic */ zzkp zza;
    private final /* synthetic */ zzkp zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzks zze;

    zzku(zzks zzks, zzkp zzkp, zzkp zzkp2, long j, boolean z) {
        this.zza = zzkp;
        this.zzb = zzkp2;
        this.zzc = j;
        this.zzd = z;
        this.zze = zzks;
    }

    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
