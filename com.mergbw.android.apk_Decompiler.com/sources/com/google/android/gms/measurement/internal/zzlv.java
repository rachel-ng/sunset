package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlv implements Runnable {
    private final /* synthetic */ zzfl zza;
    private final /* synthetic */ zzlw zzb;

    zzlv(zzlw zzlw, zzfl zzfl) {
        this.zza = zzfl;
        this.zzb = zzlw;
    }

    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzak()) {
                this.zzb.zza.zzj().zzp().zza("Connected to service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}
