package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzke implements Runnable {
    private final /* synthetic */ Boolean zza;
    private final /* synthetic */ zziv zzb;

    zzke(zziv zziv, Boolean bool) {
        this.zza = bool;
        this.zzb = zziv;
    }

    public final void run() {
        this.zzb.zza(this.zza, true);
    }
}
