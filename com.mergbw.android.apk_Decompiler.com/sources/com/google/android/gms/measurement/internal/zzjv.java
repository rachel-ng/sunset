package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjv implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb = null;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zziv zze;

    zzjv(zziv zziv, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zza = atomicReference;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = zziv;
    }

    public final void run() {
        this.zze.zzu.zzr().zza((AtomicReference<List<zzae>>) this.zza, (String) null, this.zzc, this.zzd);
    }
}
