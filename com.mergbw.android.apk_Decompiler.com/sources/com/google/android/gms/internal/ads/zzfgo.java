package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfgo implements zzepr {
    final /* synthetic */ zzfgq zza;

    zzfgo(zzfgq zzfgq) {
        this.zza = zzfgq;
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzd = null;
        }
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        synchronized (this.zza) {
            this.zza.zzd = (zzdrh) obj;
            if (((Boolean) zzba.zzc().zza(zzbep.zzdy)).booleanValue()) {
                ((zzdrh) obj).zzd().zza = this.zza.zzc;
            }
            this.zza.zzd.zzj();
        }
    }
}
