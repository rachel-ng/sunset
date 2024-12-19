package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfju implements zzgfp {
    final /* synthetic */ zzfjx zza;
    final /* synthetic */ zzfjy zzb;

    zzfju(zzfjy zzfjy, zzfjx zzfjx) {
        this.zza = zzfjx;
        this.zzb = zzfjy;
    }

    public final void zza(Throwable th) {
        synchronized (this.zzb) {
            this.zzb.zze = null;
        }
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        Void voidR = (Void) obj;
        synchronized (this.zzb) {
            this.zzb.zze = null;
            this.zzb.zzd.addFirst(this.zza);
            zzfjy zzfjy = this.zzb;
            if (zzfjy.zzf == 1) {
                zzfjy.zzh();
            }
        }
    }
}
