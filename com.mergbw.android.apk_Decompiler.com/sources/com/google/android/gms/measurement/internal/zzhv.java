package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
final class zzhv implements Runnable {
    private final /* synthetic */ zzae zza;
    private final /* synthetic */ zzhn zzb;

    zzhv(zzhn zzhn, zzae zzae) {
        this.zza = zzae;
        this.zzb = zzhn;
    }

    public final void run() {
        this.zzb.zza.zzr();
        if (this.zza.zzc.zza() == null) {
            this.zzb.zza.zza(this.zza);
        } else {
            this.zzb.zza.zzb(this.zza);
        }
    }
}
