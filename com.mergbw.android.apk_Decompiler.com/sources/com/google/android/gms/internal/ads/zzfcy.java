package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfcy implements zzepr {
    final /* synthetic */ zzfcz zza;

    zzfcy(zzfcz zzfcz) {
        this.zza = zzfcz;
    }

    public final void zza() {
        synchronized (this.zza) {
            this.zza.zza = null;
        }
    }

    public final /* bridge */ /* synthetic */ void zzb(Object obj) {
        zzcrz zzcrz = (zzcrz) obj;
        synchronized (this.zza) {
            zzcrz zzcrz2 = this.zza.zza;
            if (zzcrz2 != null) {
                zzcrz2.zzb();
            }
            zzfcz zzfcz = this.zza;
            zzfcz.zza = zzcrz;
            zzcrz.zzc(zzfcz);
            zzfcz zzfcz2 = this.zza;
            zzfcz2.zzg.zzk(new zzcsa(zzcrz, zzfcz2, zzfcz2.zzg, zzfcz2.zzi));
            zzcrz.zzj();
        }
    }
}
