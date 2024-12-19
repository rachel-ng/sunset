package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzkd implements Runnable {
    private final /* synthetic */ zzav zza;
    private final /* synthetic */ zziv zzb;

    zzkd(zziv zziv, zzav zzav) {
        this.zza = zzav;
        this.zzb = zziv;
    }

    public final void run() {
        if (this.zzb.zzk().zza(this.zza)) {
            this.zzb.zzj().zzp().zza("Setting DMA consent(FE)", this.zza);
            if (this.zzb.zzo().zzan()) {
                this.zzb.zzo().zzai();
            } else {
                this.zzb.zzo().zza(false);
            }
        } else {
            this.zzb.zzj().zzn().zza("Lower precedence consent source ignored, proposed source", Integer.valueOf(this.zza.zza()));
        }
    }
}
