package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzmo implements Runnable {
    private /* synthetic */ zzml zza;

    public /* synthetic */ zzmo(zzml zzml) {
        this.zza = zzml;
    }

    public final void run() {
        zzml zzml = this.zza;
        zzmm zzmm = zzml.zzc;
        long j = zzml.zza;
        long j2 = zzml.zzb;
        zzmm.zza.zzt();
        zzmm.zza.zzj().zzc().zza("Application going to the background");
        zzmm.zza.zzk().zzn.zza(true);
        zzmm.zza.zza(true);
        if (!zzmm.zza.zze().zzv()) {
            if (zzmm.zza.zze().zza(zzbf.zzch)) {
                zzmm.zza.zza(false, false, j2);
                zzmm.zza.zzb.zzb(j2);
            } else {
                zzmm.zza.zzb.zzb(j2);
                zzmm.zza.zza(false, false, j2);
            }
        }
        zzmm.zza.zzj().zzn().zza("Application backgrounded at: timestamp_millis", Long.valueOf(j));
    }
}
