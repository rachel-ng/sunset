package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjt implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zziv zzb;

    zzjt(zziv zziv, Bundle bundle) {
        this.zza = bundle;
        this.zzb = zziv;
    }

    public final void run() {
        zziv.zzb(this.zzb, this.zza);
    }
}
