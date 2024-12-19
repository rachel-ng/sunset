package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfxa extends zzfww {
    final /* synthetic */ zzfxg zza;

    zzfxa(zzfxg zzfxg) {
        this.zza = zzfxg;
    }

    public final void zza() {
        synchronized (this.zza.zzg) {
            if (this.zza.zzl.get() <= 0 || this.zza.zzl.decrementAndGet() <= 0) {
                zzfxg zzfxg = this.zza;
                if (zzfxg.zzn != null) {
                    zzfxg.zzc.zzc("Unbind from service.", new Object[0]);
                    zzfxg zzfxg2 = this.zza;
                    zzfxg2.zzb.unbindService(zzfxg2.zzm);
                    this.zza.zzh = false;
                    this.zza.zzn = null;
                    this.zza.zzm = null;
                }
                this.zza.zzw();
                return;
            }
            this.zza.zzc.zzc("Leaving the connection open for other ongoing calls.", new Object[0]);
        }
    }
}
