package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.primitives.SignedBytes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzanv implements zzaoc {
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

    public zzanv() {
        throw null;
    }

    public zzanv(String str, int i) {
        zzft zzft = new zzft(new byte[16], 16);
        this.zza = zzft;
        this.zzb = new zzfu(zzft.zza);
        this.zzg = 0;
        this.zzh = 0;
        this.zzi = false;
        this.zzm = C.TIME_UNSET;
        this.zzc = str;
        this.zzd = i;
    }

    public final void zza(zzfu zzfu) {
        int zzm2;
        byte b2;
        zzeq.zzb(this.zzf);
        while (zzfu.zzb() > 0) {
            int i = this.zzg;
            boolean z = true;
            if (i == 0) {
                while (true) {
                    if (zzfu.zzb() <= 0) {
                        break;
                    } else if (!this.zzi) {
                        this.zzi = zzfu.zzm() == 172;
                    } else {
                        zzm2 = zzfu.zzm();
                        this.zzi = zzm2 == 172;
                        b2 = SignedBytes.MAX_POWER_OF_TWO;
                        if (zzm2 == 64) {
                            break;
                        } else if (zzm2 == 65) {
                            zzm2 = 65;
                            break;
                        }
                    }
                }
                this.zzg = 1;
                zzfu zzfu2 = this.zzb;
                zzfu2.zzM()[0] = -84;
                if (zzm2 == 65) {
                    b2 = 65;
                }
                zzfu2.zzM()[1] = b2;
                this.zzh = 2;
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
                int min2 = Math.min(zzfu.zzb(), 16 - this.zzh);
                zzfu.zzG(zzM, this.zzh, min2);
                int i3 = this.zzh + min2;
                this.zzh = i3;
                if (i3 == 16) {
                    this.zza.zzk(0);
                    zzacv zza2 = zzacw.zza(this.zza);
                    zzan zzan = this.zzk;
                    if (zzan == null || zzan.zzA != 2 || zza2.zza != zzan.zzB || !MimeTypes.AUDIO_AC4.equals(zzan.zzn)) {
                        zzal zzal = new zzal();
                        zzal.zzK(this.zze);
                        zzal.zzX(MimeTypes.AUDIO_AC4);
                        zzal.zzy(2);
                        zzal.zzY(zza2.zza);
                        zzal.zzO(this.zzc);
                        zzal.zzV(this.zzd);
                        zzan zzad = zzal.zzad();
                        this.zzk = zzad;
                        this.zzf.zzl(zzad);
                    }
                    this.zzl = zza2.zzb;
                    this.zzj = (((long) zza2.zzc) * 1000000) / ((long) this.zzk.zzB);
                    this.zzb.zzK(0);
                    this.zzf.zzq(this.zzb, 16);
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
