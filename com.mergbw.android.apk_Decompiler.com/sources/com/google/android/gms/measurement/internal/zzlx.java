package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlx implements Runnable {
    private final /* synthetic */ zzfl zza;
    private final /* synthetic */ zzlw zzb;

    zzlx(zzlw zzlw, zzfl zzfl) {
        this.zza = zzfl;
        this.zzb = zzlw;
    }

    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzak()) {
                this.zzb.zza.zzj().zzc().zza("Connected to remote service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}
