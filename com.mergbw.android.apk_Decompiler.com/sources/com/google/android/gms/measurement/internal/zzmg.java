package com.google.android.gms.measurement.internal;

import android.content.Intent;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzmg implements Runnable {
    private /* synthetic */ zzme zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ zzfw zzc;
    private /* synthetic */ Intent zzd;

    public /* synthetic */ zzmg(zzme zzme, int i, zzfw zzfw, Intent intent) {
        this.zza = zzme;
        this.zzb = i;
        this.zzc = zzfw;
        this.zzd = intent;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd);
    }
}
