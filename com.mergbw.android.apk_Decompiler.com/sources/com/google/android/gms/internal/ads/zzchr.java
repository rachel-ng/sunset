package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzchr implements Runnable {
    public final /* synthetic */ zzchd zza;

    public /* synthetic */ zzchr(zzchd zzchd) {
        this.zza = zzchd;
    }

    public final void run() {
        this.zza.destroy();
    }
}