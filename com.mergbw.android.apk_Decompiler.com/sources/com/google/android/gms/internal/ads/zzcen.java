package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzcen implements Runnable {
    public final /* synthetic */ zzcew zza;
    public final /* synthetic */ int zzb;
    public final /* synthetic */ int zzc;

    public /* synthetic */ zzcen(zzcew zzcew, int i, int i2) {
        this.zza = zzcew;
        this.zzb = i;
        this.zzc = i2;
    }

    public final void run() {
        this.zza.zzO(this.zzb, this.zzc);
    }
}