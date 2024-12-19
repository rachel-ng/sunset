package com.google.android.gms.measurement.internal;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzko implements Runnable {
    private /* synthetic */ zzkl zza;
    private /* synthetic */ int zzb;
    private /* synthetic */ Exception zzc;
    private /* synthetic */ byte[] zzd;
    private /* synthetic */ Map zze;

    public /* synthetic */ zzko(zzkl zzkl, int i, Exception exc, byte[] bArr, Map map) {
        this.zza = zzkl;
        this.zzb = i;
        this.zzc = exc;
        this.zzd = bArr;
        this.zze = map;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}
