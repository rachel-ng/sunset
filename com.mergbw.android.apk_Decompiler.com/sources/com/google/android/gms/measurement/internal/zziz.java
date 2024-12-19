package com.google.android.gms.measurement.internal;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zziz implements Runnable {
    private /* synthetic */ zziv zza;
    private /* synthetic */ List zzb;

    public /* synthetic */ zziz(zziv zziv, List list) {
        this.zza = zziv;
        this.zzb = list;
    }

    public final void run() {
        this.zza.zza(this.zzb);
    }
}
