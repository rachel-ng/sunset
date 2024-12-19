package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaga implements zzadu {
    public static final zzaea zza = new zzafz();
    private final byte[] zzb;
    private final zzfu zzc;
    private final zzaeb zzd;
    private zzadx zze;
    private zzafa zzf;
    private int zzg;
    private zzcd zzh;
    private zzaeg zzi;
    private int zzj;
    private int zzk;
    private zzafy zzl;
    private int zzm;
    private long zzn;

    public zzaga() {
        throw null;
    }

    public zzaga(int i) {
        this.zzb = new byte[42];
        this.zzc = new zzfu(new byte[32768], 0);
        this.zzd = new zzaeb();
        this.zzg = 0;
    }

    private final long zza(zzfu zzfu, boolean z) {
        boolean z2;
        this.zzi.getClass();
        int zzd2 = zzfu.zzd();
        while (zzd2 <= zzfu.zze() - 16) {
            zzfu.zzK(zzd2);
            if (zzaec.zzc(zzfu, this.zzi, this.zzk, this.zzd)) {
                zzfu.zzK(zzd2);
                return this.zzd.zza;
            }
            zzd2++;
        }
        if (z) {
            while (zzd2 <= zzfu.zze() - this.zzj) {
                zzfu.zzK(zzd2);
                try {
                    z2 = zzaec.zzc(zzfu, this.zzi, this.zzk, this.zzd);
                } catch (IndexOutOfBoundsException unused) {
                    z2 = false;
                }
                if (zzfu.zzd() <= zzfu.zze() && z2) {
                    zzfu.zzK(zzd2);
                    return this.zzd.zza;
                }
                zzd2++;
            }
            zzfu.zzK(zzfu.zze());
            return -1;
        }
        zzfu.zzK(zzd2);
        return -1;
    }

    private final void zzg() {
        zzaeg zzaeg = this.zzi;
        int i = zzgd.zza;
        this.zzf.zzs((this.zzn * 1000000) / ((long) zzaeg.zze), 1, this.zzm, 0, (zzaez) null);
    }

    public final /* synthetic */ List zzc() {
        return zzgbc.zzm();
    }

    public final void zzd(zzadx zzadx) {
        this.zze = zzadx;
        this.zzf = zzadx.zzw(0, 1);
        zzadx.zzD();
    }

    public final boolean zzf(zzadv zzadv) throws IOException {
        zzaed.zza(zzadv, false);
        zzfu zzfu = new zzfu(4);
        ((zzadi) zzadv).zzm(zzfu.zzM(), 0, 4, false);
        if (zzfu.zzu() == 1716281667) {
            return true;
        }
        return false;
    }

    public final void zze(long j, long j2) {
        long j3 = 0;
        if (j == 0) {
            this.zzg = 0;
        } else {
            zzafy zzafy = this.zzl;
            if (zzafy != null) {
                zzafy.zzd(j2);
            }
        }
        if (j2 != 0) {
            j3 = -1;
        }
        this.zzn = j3;
        this.zzm = 0;
        this.zzc.zzH(0);
    }

    public final int zzb(zzadv zzadv, zzaeq zzaeq) throws IOException {
        boolean zzo;
        zzaet zzaet;
        zzadv zzadv2 = zzadv;
        int i = this.zzg;
        boolean z = true;
        if (i == 0) {
            zzadv.zzj();
            long zze2 = zzadv.zze();
            zzcd zza2 = zzaed.zza(zzadv2, true);
            ((zzadi) zzadv2).zzo((int) (zzadv.zze() - zze2), false);
            this.zzh = zza2;
            this.zzg = 1;
            return 0;
        } else if (i == 1) {
            ((zzadi) zzadv2).zzm(this.zzb, 0, 42, false);
            zzadv.zzj();
            this.zzg = 2;
            return 0;
        } else if (i == 2) {
            zzfu zzfu = new zzfu(4);
            ((zzadi) zzadv2).zzn(zzfu.zzM(), 0, 4, false);
            if (zzfu.zzu() == 1716281667) {
                this.zzg = 3;
                return 0;
            }
            throw zzch.zza("Failed to read FLAC stream marker.", (Throwable) null);
        } else if (i == 3) {
            zzaeg zzaeg = this.zzi;
            do {
                zzadv.zzj();
                zzft zzft = new zzft(new byte[4], 4);
                zzadi zzadi = (zzadi) zzadv2;
                zzadi.zzm(zzft.zza, 0, 4, false);
                zzo = zzft.zzo();
                int zzd2 = zzft.zzd(7);
                int zzd3 = zzft.zzd(24) + 4;
                if (zzd2 == 0) {
                    byte[] bArr = new byte[38];
                    zzadi.zzn(bArr, 0, 38, false);
                    zzaeg = new zzaeg(bArr, 4);
                } else if (zzaeg == null) {
                    throw new IllegalArgumentException();
                } else if (zzd2 == 3) {
                    zzfu zzfu2 = new zzfu(zzd3);
                    zzadi.zzn(zzfu2.zzM(), 0, zzd3, false);
                    zzaeg = zzaeg.zzf(zzaed.zzb(zzfu2));
                } else if (zzd2 == 4) {
                    zzfu zzfu3 = new zzfu(zzd3);
                    zzadi.zzn(zzfu3.zzM(), 0, zzd3, false);
                    zzfu3.zzL(4);
                    zzaeg = zzaeg.zzg(Arrays.asList(zzafg.zzc(zzfu3, false, false).zzb));
                } else if (zzd2 == 6) {
                    zzfu zzfu4 = new zzfu(zzd3);
                    zzadi.zzn(zzfu4.zzM(), 0, zzd3, false);
                    zzfu4.zzL(4);
                    zzaeg = zzaeg.zze(zzgbc.zzn(zzagw.zzb(zzfu4)));
                } else {
                    zzadi.zzo(zzd3, false);
                }
                int i2 = zzgd.zza;
                this.zzi = zzaeg;
            } while (!zzo);
            zzaeg.getClass();
            this.zzj = Math.max(zzaeg.zzc, 6);
            this.zzf.zzl(this.zzi.zzc(this.zzb, this.zzh));
            this.zzg = 4;
            return 0;
        } else if (i != 4) {
            this.zzf.getClass();
            zzaeg zzaeg2 = this.zzi;
            zzaeg2.getClass();
            zzafy zzafy = this.zzl;
            if (zzafy != null && zzafy.zze()) {
                return zzafy.zza(zzadv2, zzaeq);
            }
            if (this.zzn == -1) {
                this.zzn = zzaec.zzb(zzadv2, zzaeg2);
                return 0;
            }
            zzfu zzfu5 = this.zzc;
            int zze3 = zzfu5.zze();
            if (zze3 < 32768) {
                int zza3 = zzadv2.zza(zzfu5.zzM(), zze3, 32768 - zze3);
                if (zza3 != -1) {
                    z = false;
                }
                if (!z) {
                    this.zzc.zzJ(zze3 + zza3);
                } else if (this.zzc.zzb() == 0) {
                    zzg();
                    return -1;
                }
            } else {
                z = false;
            }
            zzfu zzfu6 = this.zzc;
            int zzd4 = zzfu6.zzd();
            int i3 = this.zzm;
            int i4 = this.zzj;
            if (i3 < i4) {
                zzfu6.zzL(Math.min(i4 - i3, zzfu6.zzb()));
            }
            long zza4 = zza(this.zzc, z);
            zzfu zzfu7 = this.zzc;
            int zzd5 = zzfu7.zzd() - zzd4;
            zzfu7.zzK(zzd4);
            zzaey.zzb(this.zzf, this.zzc, zzd5);
            this.zzm += zzd5;
            if (zza4 != -1) {
                zzg();
                this.zzm = 0;
                this.zzn = zza4;
            }
            zzfu zzfu8 = this.zzc;
            if (zzfu8.zzb() >= 16) {
                return 0;
            }
            int zzb2 = zzfu8.zzb();
            System.arraycopy(zzfu8.zzM(), zzfu8.zzd(), zzfu8.zzM(), 0, zzb2);
            this.zzc.zzK(0);
            this.zzc.zzJ(zzb2);
            return 0;
        } else {
            zzadv.zzj();
            zzfu zzfu9 = new zzfu(2);
            ((zzadi) zzadv2).zzm(zzfu9.zzM(), 0, 2, false);
            int zzq = zzfu9.zzq();
            if ((zzq >> 2) == 16382) {
                zzadv.zzj();
                this.zzk = zzq;
                zzadx zzadx = this.zze;
                int i5 = zzgd.zza;
                long zzf2 = zzadv.zzf();
                long zzd6 = zzadv.zzd();
                zzaeg zzaeg3 = this.zzi;
                zzaeg3.getClass();
                if (zzaeg3.zzk != null) {
                    zzaet = new zzaee(zzaeg3, zzf2);
                } else if (zzd6 == -1 || zzaeg3.zzj <= 0) {
                    zzaet = new zzaes(zzaeg3.zza(), 0);
                } else {
                    zzafy zzafy2 = new zzafy(zzaeg3, this.zzk, zzf2, zzd6);
                    this.zzl = zzafy2;
                    zzaet = zzafy2.zzb();
                }
                zzadx.zzO(zzaet);
                this.zzg = 5;
                return 0;
            }
            zzadv.zzj();
            throw zzch.zza("First frame does not start with sync code.", (Throwable) null);
        }
    }
}
