package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzans implements zzaoc {
    private final zzft zza;
    private final zzfu zzb;
    private final String zzc;
    private final int zzd;
    private String zze;
    private zzafa zzf;
    private int zzg;
    private int zzh;
    private boolean zzi;
    private long zzj;
    private zzan zzk;
    private int zzl;
    private long zzm;

    public zzans() {
        throw null;
    }

    public zzans(String str, int i) {
        zzft zzft = new zzft(new byte[128], 128);
        this.zza = zzft;
        this.zzb = new zzfu(zzft.zza);
        this.zzg = 0;
        this.zzm = C.TIME_UNSET;
        this.zzc = str;
        this.zzd = i;
    }

    public final void zza(zzfu zzfu) {
        zzeq.zzb(this.zzf);
        while (zzfu.zzb() > 0) {
            int i = this.zzg;
            boolean z = true;
            if (i == 0) {
                while (true) {
                    if (zzfu.zzb() <= 0) {
                        break;
                    } else if (!this.zzi) {
                        this.zzi = zzfu.zzm() == 11;
                    } else {
                        int zzm2 = zzfu.zzm();
                        if (zzm2 == 119) {
                            this.zzi = false;
                            this.zzg = 1;
                            zzfu zzfu2 = this.zzb;
                            zzfu2.zzM()[0] = 11;
                            zzfu2.zzM()[1] = 119;
                            this.zzh = 2;
                            break;
                        }
                        this.zzi = zzm2 == 11;
                    }
                }
            } else if (i != 1) {
                int min = Math.min(zzfu.zzb(), this.zzl - this.zzh);
                this.zzf.zzq(zzfu, min);
                int i2 = this.zzh + min;
                this.zzh = i2;
                if (i2 == this.zzl) {
                    if (this.zzm == C.TIME_UNSET) {
                        z = false;
                    }
                    zzeq.zzf(z);
                    this.zzf.zzs(this.zzm, 1, this.zzl, 0, (zzaez) null);
                    this.zzm += this.zzj;
                    this.zzg = 0;
                }
            } else {
                byte[] zzM = this.zzb.zzM();
                int min2 = Math.min(zzfu.zzb(), 128 - this.zzh);
                zzfu.zzG(zzM, this.zzh, min2);
                int i3 = this.zzh + min2;
                this.zzh = i3;
                if (i3 == 128) {
                    this.zza.zzk(0);
                    zzacs zze2 = zzact.zze(this.zza);
                    zzan zzan = this.zzk;
                    if (zzan == null || zze2.zzc != zzan.zzA || zze2.zzb != zzan.zzB || !zzgd.zzG(zze2.zza, zzan.zzn)) {
                        zzal zzal = new zzal();
                        zzal.zzK(this.zze);
                        zzal.zzX(zze2.zza);
                        zzal.zzy(zze2.zzc);
                        zzal.zzY(zze2.zzb);
                        zzal.zzO(this.zzc);
                        zzal.zzV(this.zzd);
                        zzal.zzS(zze2.zzf);
                        if (MimeTypes.AUDIO_AC3.equals(zze2.zza)) {
                            zzal.zzx(zze2.zzf);
                        }
                        zzan zzad = zzal.zzad();
                        this.zzk = zzad;
                        this.zzf.zzl(zzad);
                    }
                    this.zzl = zze2.zzd;
                    this.zzj = (((long) zze2.zze) * 1000000) / ((long) this.zzk.zzB);
                    this.zzb.zzK(0);
                    this.zzf.zzq(this.zzb, 128);
                    this.zzg = 2;
                }
            }
        }
    }

    public final void zzb(zzadx zzadx, zzapo zzapo) {
        zzapo.zzc();
        this.zze = zzapo.zzb();
        this.zzf = zzadx.zzw(zzapo.zza(), 1);
    }

    public final void zzc() {
    }

    public final void zzd(long j, int i) {
        this.zzm = j;
    }

    public final void zze() {
        this.zzg = 0;
        this.zzh = 0;
        this.zzi = false;
        this.zzm = C.TIME_UNSET;
    }
}
