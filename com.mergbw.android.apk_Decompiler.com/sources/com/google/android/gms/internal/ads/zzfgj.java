package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfgj implements zzepr {
    final /* synthetic */ zzfgk zza;

    zzfgj(zzfgk zzfgk) {
        this.zza = zzfgk;
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.zzi = null;
        }
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        synchronized (this.zza) {
            this.zza.zzi = (zzdrh) obj;
            if (((Boolean) zzba.zzc().zza(zzbep.zzdy)).booleanValue()) {
                ((zzdrh) obj).zzd().zza = this.zza.zzd;
            }
            this.zza.zzi.zzj();
        }
    }
}
