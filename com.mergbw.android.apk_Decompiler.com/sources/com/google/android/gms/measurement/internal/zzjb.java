package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzjb implements Runnable {
    private /* synthetic */ zziv zza;
    private /* synthetic */ Bundle zzb;
    private /* synthetic */ long zzc;

    public /* synthetic */ zzjb(zziv zziv, Bundle bundle, long j) {
        this.zza = zziv;
        this.zzb = bundle;
        this.zzc = j;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
