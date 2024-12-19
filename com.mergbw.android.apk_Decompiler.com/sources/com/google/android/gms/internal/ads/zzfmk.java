package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfmk implements zzgfp {
    final /* synthetic */ zzfmn zza;
    final /* synthetic */ zzfmc zzb;
    final /* synthetic */ boolean zzc;

    zzfmk(zzfmn zzfmn, zzfmc zzfmc, boolean z) {
        this.zza = zzfmn;
        this.zzb = zzfmc;
        this.zzc = z;
    }

    public final void zza(Throwable th) {
        zzfmc zzfmc = this.zzb;
        if (zzfmc.zzl()) {
            zzfmn zzfmn = this.zza;
            zzfmc.zzi(th);
            zzfmc.zzh(false);
            zzfmn.zza(zzfmc);
            if (this.zzc) {
                this.zza.zzi();
            }
        }
    }

    public final void zzb(Object obj) {
        zzfmc zzfmc = this.zzb;
        zzfmc.zzh(true);
        this.zza.zza(zzfmc);
        if (this.zzc) {
            this.zza.zzi();
        }
    }
}
