package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzho implements Runnable {
    private /* synthetic */ zzhn zza;
    private /* synthetic */ String zzb;
    private /* synthetic */ Bundle zzc;

    public /* synthetic */ zzho(zzhn zzhn, String str, Bundle bundle) {
        this.zza = zzhn;
        this.zzb = str;
        this.zzc = bundle;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
