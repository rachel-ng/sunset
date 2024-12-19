package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzjg implements Runnable {
    private /* synthetic */ zziv zza;
    private /* synthetic */ String zzb;

    public /* synthetic */ zzjg(zziv zziv, String str) {
        this.zza = zziv;
        this.zzb = str;
    }

    public final void run() {
        this.zza.zzb(this.zzb);
    }
}
