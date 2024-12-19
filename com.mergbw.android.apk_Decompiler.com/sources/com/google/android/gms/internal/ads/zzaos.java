package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaos implements zzapb {
    private zzan zza;
    private zzgb zzb;
    private zzafa zzc;

    public zzaos(String str) {
        zzal zzal = new zzal();
        zzal.zzX(str);
        this.zza = zzal.zzad();
    }

    public final void zza(zzfu zzfu) {
        zzeq.zzb(this.zzb);
        int i = zzgd.zza;
        long zze = this.zzb.zze();
        long zzf = this.zzb.zzf();
        if (zze != C.TIME_UNSET && zzf != C.TIME_UNSET) {
            zzan zzan = this.zza;
            if (zzf != zzan.zzr) {
                zzal zzb2 = zzan.zzb();
                zzb2.zzab(zzf);
                zzan zzad = zzb2.zzad();
                this.zza = zzad;
                this.zzc.zzl(zzad);
            }
            int zzb3 = zzfu.zzb();
            this.zzc.zzq(zzfu, zzb3);
            this.zzc.zzs(zze, 1, zzb3, 0, (zzaez) null);
        }
    }

    public final void zzb(zzgb zzgb, zzadx zzadx, zzapo zzapo) {
        this.zzb = zzgb;
        zzapo.zzc();
        zzafa zzw = zzadx.zzw(zzapo.zza(), 5);
        this.zzc = zzw;
        zzw.zzl(this.zza);
    }
}
