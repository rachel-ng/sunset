package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzabw {
    private final zzabv zza;
    private final zzabq zzb;
    private final zzabo zzc = new zzabo();
    private final zzga zzd = new zzga(10);
    private final zzga zze = new zzga(10);
    private final zzfm zzf = new zzfm(16);
    private zzdv zzg;
    private zzdv zzh = zzdv.zza;
    private long zzi;
    private long zzj = C.TIME_UNSET;

    public zzabw(zzabv zzabv, zzabq zzabq) {
        this.zza = zzabv;
        this.zzb = zzabq;
    }

    private static Object zzf(zzga zzga) {
        zzeq.zzd(zzga.zza() > 0);
        while (zzga.zza() > 1) {
            zzga.zzb();
        }
        Object zzb2 = zzga.zzb();
        zzb2.getClass();
        return zzb2;
    }

    public final void zza() {
        this.zzf.zzc();
        this.zzj = C.TIME_UNSET;
        zzga zzga = this.zze;
        if (zzga.zza() > 0) {
            Long l = (Long) zzf(zzga);
            l.longValue();
            this.zze.zzd(0, l);
        }
        if (this.zzg == null) {
            zzga zzga2 = this.zzd;
            if (zzga2.zza() > 0) {
                this.zzg = (zzdv) zzf(zzga2);
                return;
            }
            return;
        }
        this.zzd.zze();
    }

    public final void zzb(long j, long j2) {
        this.zze.zzd(j, Long.valueOf(j2));
    }

    public final void zzc(long j, long j2) throws zzjh {
        long j3;
        while (true) {
            zzfm zzfm = this.zzf;
            if (!zzfm.zzd()) {
                zzga zzga = this.zze;
                long zza2 = zzfm.zza();
                Long l = (Long) zzga.zzc(zza2);
                if (!(l == null || l.longValue() == this.zzi)) {
                    this.zzi = l.longValue();
                    this.zzb.zzf();
                }
                int zza3 = this.zzb.zza(zza2, j, j2, this.zzi, false, this.zzc);
                if (zza3 == 0 || zza3 == 1) {
                    this.zzj = zza2;
                    long zzb2 = this.zzf.zzb();
                    Long.valueOf(zzb2).getClass();
                    zzdv zzdv = (zzdv) this.zzd.zzc(zzb2);
                    if (zzdv != null && !zzdv.equals(zzdv.zza) && !zzdv.equals(this.zzh)) {
                        this.zzh = zzdv;
                        this.zza.zzm(zzdv);
                    }
                    if (zza3 == 0) {
                        j3 = -1;
                    } else {
                        j3 = this.zzc.zzd();
                    }
                    this.zza.zzp(j3, zzb2, this.zzi, this.zzb.zzp());
                } else if (zza3 == 2 || zza3 == 3 || zza3 == 4) {
                    this.zzj = zza2;
                    this.zzf.zzb();
                    this.zza.zzl();
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final void zzd(float f) {
        zzeq.zzd(f > 0.0f);
        this.zzb.zzn(f);
    }

    public final boolean zze(long j) {
        long j2 = this.zzj;
        return j2 != C.TIME_UNSET && j2 >= j;
    }
}
