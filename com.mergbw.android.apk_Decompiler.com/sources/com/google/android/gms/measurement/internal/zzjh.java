package com.google.android.gms.measurement.internal;

import android.util.SparseArray;
import com.google.common.util.concurrent.FutureCallback;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzjh implements FutureCallback<Object> {
    private final /* synthetic */ zzmu zza;
    private final /* synthetic */ zziv zzb;

    zzjh(zziv zziv, zzmu zzmu) {
        this.zza = zzmu;
        this.zzb = zziv;
    }

    public final void onFailure(Throwable th) {
        this.zzb.zzt();
        this.zzb.zzh = false;
        if (!this.zzb.zze().zza(zzbf.zzcf)) {
            this.zzb.zzar();
            this.zzb.zzj().zzg().zza("registerTriggerAsync failed with throwable", th);
            return;
        }
        this.zzb.zzal().add(this.zza);
        if (this.zzb.zzi > 64) {
            this.zzb.zzi = 1;
            this.zzb.zzj().zzu().zza("registerTriggerAsync failed. May try later. App ID, throwable", zzfw.zza(this.zzb.zzg().zzad()), zzfw.zza(th.toString()));
            return;
        }
        this.zzb.zzj().zzu().zza("registerTriggerAsync failed. App ID, delay in seconds, throwable", zzfw.zza(this.zzb.zzg().zzad()), zzfw.zza(String.valueOf(this.zzb.zzi)), zzfw.zza(th.toString()));
        zziv zziv = this.zzb;
        zziv.zzb(zziv, zziv.zzi);
        zziv zziv2 = this.zzb;
        zziv2.zzi = zziv2.zzi << 1;
    }

    public final void onSuccess(Object obj) {
        this.zzb.zzt();
        if (this.zzb.zze().zza(zzbf.zzcf)) {
            SparseArray<Long> zzh = this.zzb.zzk().zzh();
            zzh.put(this.zza.zzc, Long.valueOf(this.zza.zzb));
            this.zzb.zzk().zza(zzh);
            this.zzb.zzh = false;
            this.zzb.zzi = 1;
            this.zzb.zzj().zzc().zza("Successfully registered trigger URI", this.zza.zza);
            this.zzb.zzar();
            return;
        }
        this.zzb.zzh = false;
        this.zzb.zzar();
        this.zzb.zzj().zzc().zza("registerTriggerAsync ran. uri", this.zza.zza);
    }
}
