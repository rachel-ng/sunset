package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzjc implements Runnable {
    private /* synthetic */ zziv zza;
    private /* synthetic */ Bundle zzb;

    public /* synthetic */ zzjc(zziv zziv, Bundle bundle) {
        this.zza = zziv;
        this.zzb = bundle;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
