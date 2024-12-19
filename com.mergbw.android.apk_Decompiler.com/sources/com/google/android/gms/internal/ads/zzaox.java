package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzaox {
    private final zzgb zza = new zzgb(0);
    private final zzfu zzb = new zzfu();
    private boolean zzc;
    private boolean zzd;
    private boolean zze;
    private long zzf = C.TIME_UNSET;
    private long zzg = C.TIME_UNSET;
    private long zzh = C.TIME_UNSET;

    zzaox() {
    }

    public static long zzc(zzfu zzfu) {
        zzfu zzfu2 = zzfu;
        int zzd2 = zzfu.zzd();
        if (zzfu.zzb() < 9) {
            return C.TIME_UNSET;
        }
        byte[] bArr = new byte[9];
        zzfu2.zzG(bArr, 0, 9);
        zzfu2.zzK(zzd2);
        byte b2 = bArr[0];
        if ((b2 & 196) != 68) {
            return C.TIME_UNSET;
        }
        byte b3 = bArr[2];
        if ((b3 & 4) != 4) {
            return C.TIME_UNSET;
        }
        byte b4 = bArr[4];
        if ((b4 & 4) != 4 || (bArr[5] & 1) != 1 || (bArr[8] & 3) != 3) {
            return C.TIME_UNSET;
        }
        long j = (long) b2;
        long j2 = (long) bArr[1];
        long j3 = (long) b3;
        long j4 = (j2 & 255) << 20;
        long j5 = (j3 & 3) << 13;
        long j6 = j5 | j4 | ((j & 3) << 28) | (((j & 56) >> 3) << 30) | (((248 & j3) >> 3) << 15);
        return j6 | ((((long) bArr[3]) & 255) << 5) | ((((long) b4) & 248) >> 3);
    }

    private final int zzf(zzadv zzadv) {
        byte[] bArr = zzgd.zzf;
        int length = bArr.length;
        this.zzb.zzI(bArr, 0);
        this.zzc = true;
        zzadv.zzj();
        return 0;
    }

    private static final int zzg(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    public final int zza(zzadv zzadv, zzaeq zzaeq) throws IOException {
        boolean z = this.zze;
        long j = C.TIME_UNSET;
        if (!z) {
            long zzd2 = zzadv.zzd();
            int min = (int) Math.min(20000, zzd2);
            long j2 = zzd2 - ((long) min);
            if (zzadv.zzf() != j2) {
                zzaeq.zza = j2;
                return 1;
            }
            this.zzb.zzH(min);
            zzadv.zzj();
            ((zzadi) zzadv).zzm(this.zzb.zzM(), 0, min, false);
            zzfu zzfu = this.zzb;
            int zzd3 = zzfu.zzd();
            int zze2 = zzfu.zze() - 4;
            while (true) {
                if (zze2 < zzd3) {
                    break;
                }
                if (zzg(zzfu.zzM(), zze2) == 442) {
                    zzfu.zzK(zze2 + 4);
                    long zzc2 = zzc(zzfu);
                    if (zzc2 != C.TIME_UNSET) {
                        j = zzc2;
                        break;
                    }
                }
                zze2--;
            }
            this.zzg = j;
            this.zze = true;
        } else if (this.zzg == C.TIME_UNSET) {
            zzf(zzadv);
            return 0;
        } else if (!this.zzd) {
            int min2 = (int) Math.min(20000, zzadv.zzd());
            if (zzadv.zzf() != 0) {
                zzaeq.zza = 0;
                return 1;
            }
            this.zzb.zzH(min2);
            zzadv.zzj();
            ((zzadi) zzadv).zzm(this.zzb.zzM(), 0, min2, false);
            zzfu zzfu2 = this.zzb;
            int zzd4 = zzfu2.zzd();
            int zze3 = zzfu2.zze();
            while (true) {
                if (zzd4 >= zze3 - 3) {
                    break;
                }
                if (zzg(zzfu2.zzM(), zzd4) == 442) {
                    zzfu2.zzK(zzd4 + 4);
                    long zzc3 = zzc(zzfu2);
                    if (zzc3 != C.TIME_UNSET) {
                        j = zzc3;
                        break;
                    }
                }
                zzd4++;
            }
            this.zzf = j;
            this.zzd = true;
        } else {
            long j3 = this.zzf;
            if (j3 == C.TIME_UNSET) {
                zzf(zzadv);
                return 0;
            }
            zzgb zzgb = this.zza;
            this.zzh = zzgb.zzc(this.zzg) - zzgb.zzb(j3);
            zzf(zzadv);
            return 0;
        }
        return 0;
    }

    public final long zzb() {
        return this.zzh;
    }

    public final zzgb zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzc;
    }
}
