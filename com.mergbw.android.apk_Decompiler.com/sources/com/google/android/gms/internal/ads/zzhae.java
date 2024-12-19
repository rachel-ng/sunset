package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhae extends zzham {
    private final byte[] zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private final int zzi;
    private int zzj;
    private int zzk = Integer.MAX_VALUE;

    /* synthetic */ zzhae(byte[] bArr, int i, int i2, boolean z, zzhad zzhad) {
        super((zzhal) null);
        this.zze = bArr;
        this.zzf = i2 + i;
        this.zzh = i;
        this.zzi = i;
    }

    private final void zzK() {
        int i = this.zzf + this.zzg;
        this.zzf = i;
        int i2 = i - this.zzi;
        int i3 = this.zzk;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzg = i4;
            this.zzf = i - i4;
            return;
        }
        this.zzg = 0;
    }

    public final void zzA(int i) {
        this.zzk = i;
        zzK();
    }

    public final void zzB(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zzf;
            int i3 = this.zzh;
            if (i <= i2 - i3) {
                this.zzh = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzhcd.zzf();
        }
        throw zzhcd.zzj();
    }

    public final boolean zzC() throws IOException {
        return this.zzh == this.zzf;
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        int i = this.zzh;
        if (i != this.zzf) {
            byte[] bArr = this.zze;
            this.zzh = i + 1;
            return bArr[i];
        }
        throw zzhcd.zzj();
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public final int zzd() {
        return this.zzh - this.zzi;
    }

    public final int zze(int i) throws zzhcd {
        if (i >= 0) {
            int i2 = i + (this.zzh - this.zzi);
            if (i2 >= 0) {
                int i3 = this.zzk;
                if (i2 <= i3) {
                    this.zzk = i2;
                    zzK();
                    return i3;
                }
                throw zzhcd.zzj();
            }
            throw zzhcd.zzg();
        }
        throw zzhcd.zzf();
    }

    public final int zzf() throws IOException {
        return zzj();
    }

    public final int zzg() throws IOException {
        return zzi();
    }

    public final int zzh() throws IOException {
        return zzj();
    }

    public final int zzi() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 4) {
            byte[] bArr = this.zze;
            this.zzh = i + 4;
            int i2 = (bArr[i + 1] & 255) << 8;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzhcd.zzj();
    }

    public final int zzk() throws IOException {
        return zzi();
    }

    public final int zzl() throws IOException {
        return zzF(zzj());
    }

    public final int zzm() throws IOException {
        if (zzC()) {
            this.zzj = 0;
            return 0;
        }
        int zzj2 = zzj();
        this.zzj = zzj2;
        if ((zzj2 >>> 3) != 0) {
            return zzj2;
        }
        throw zzhcd.zzc();
    }

    public final int zzn() throws IOException {
        return zzj();
    }

    public final long zzo() throws IOException {
        return zzq();
    }

    public final long zzp() throws IOException {
        return zzr();
    }

    public final long zzq() throws IOException {
        int i = this.zzh;
        if (this.zzf - i >= 8) {
            byte[] bArr = this.zze;
            this.zzh = i + 8;
            long j = (long) bArr[i + 2];
            long j2 = (long) bArr[i + 3];
            long j3 = (long) bArr[i + 4];
            long j4 = (long) bArr[i + 5];
            long j5 = (long) bArr[i + 6];
            long j6 = (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((j & 255) << 16) | ((j2 & 255) << 24);
            return ((((long) bArr[i + 7]) & 255) << 56) | j6 | ((j3 & 255) << 32) | ((j4 & 255) << 40) | ((j5 & 255) << 48);
        }
        throw zzhcd.zzj();
    }

    /* access modifiers changed from: package-private */
    public final long zzs() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zza = zza();
            j |= ((long) (zza & Byte.MAX_VALUE)) << i;
            if ((zza & 128) == 0) {
                return j;
            }
        }
        throw zzhcd.zze();
    }

    public final long zzt() throws IOException {
        return zzq();
    }

    public final long zzu() throws IOException {
        return zzH(zzr());
    }

    public final long zzv() throws IOException {
        return zzr();
    }

    public final zzhac zzw() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zzj2 <= i - i2) {
                zzhac zzv = zzhac.zzv(this.zze, i2, zzj2);
                this.zzh += zzj2;
                return zzv;
            }
        }
        if (zzj2 == 0) {
            return zzhac.zzb;
        }
        if (zzj2 > 0) {
            int i3 = this.zzf;
            int i4 = this.zzh;
            if (zzj2 <= i3 - i4) {
                int i5 = zzj2 + i4;
                this.zzh = i5;
                return new zzgzy(Arrays.copyOfRange(this.zze, i4, i5));
            }
        }
        if (zzj2 <= 0) {
            throw zzhcd.zzf();
        }
        throw zzhcd.zzj();
    }

    public final String zzx() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zzj2 <= i - i2) {
                String str = new String(this.zze, i2, zzj2, zzhcb.zzb);
                this.zzh += zzj2;
                return str;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 < 0) {
            throw zzhcd.zzf();
        }
        throw zzhcd.zzj();
    }

    public final String zzy() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i = this.zzf;
            int i2 = this.zzh;
            if (zzj2 <= i - i2) {
                String zzh2 = zzhff.zzh(this.zze, i2, zzj2);
                this.zzh += zzj2;
                return zzh2;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 <= 0) {
            throw zzhcd.zzf();
        }
        throw zzhcd.zzj();
    }

    public final void zzz(int i) throws zzhcd {
        if (this.zzj != i) {
            throw zzhcd.zzb();
        }
    }

    public final int zzj() throws IOException {
        byte b2;
        byte b3;
        int i = this.zzh;
        int i2 = this.zzf;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b4 = bArr[i];
            if (b4 >= 0) {
                this.zzh = i3;
                return b4;
            } else if (i2 - i3 >= 9) {
                int i4 = i + 2;
                byte b5 = (bArr[i3] << 7) ^ b4;
                if (b5 < 0) {
                    b2 = b5 ^ Byte.MIN_VALUE;
                } else {
                    int i5 = i + 3;
                    byte b6 = (bArr[i4] << 14) ^ b5;
                    if (b6 >= 0) {
                        b3 = b6 ^ 16256;
                    } else {
                        int i6 = i + 4;
                        byte b7 = b6 ^ (bArr[i5] << Ascii.NAK);
                        if (b7 < 0) {
                            b2 = -2080896 ^ b7;
                        } else {
                            i5 = i + 5;
                            byte b8 = bArr[i6];
                            byte b9 = (b7 ^ (b8 << Ascii.FS)) ^ 266354560;
                            if (b8 < 0) {
                                i6 = i + 6;
                                if (bArr[i5] < 0) {
                                    i5 = i + 7;
                                    if (bArr[i6] < 0) {
                                        i6 = i + 8;
                                        if (bArr[i5] < 0) {
                                            i5 = i + 9;
                                            if (bArr[i6] < 0) {
                                                int i7 = i + 10;
                                                if (bArr[i5] >= 0) {
                                                    byte b10 = b9;
                                                    i4 = i7;
                                                    b2 = b10;
                                                }
                                            }
                                        }
                                    }
                                }
                                b2 = b9;
                            }
                            b3 = b9;
                        }
                        i4 = i6;
                    }
                    i4 = i5;
                }
                this.zzh = i4;
                return b2;
            }
        }
        return (int) zzs();
    }

    public final boolean zzE(int i) throws IOException {
        int zzm;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zzf - this.zzh >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zze;
                    int i4 = this.zzh;
                    this.zzh = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzhcd.zze();
            }
            while (i3 < 10) {
                if (zza() < 0) {
                    i3++;
                }
            }
            throw zzhcd.zze();
            return true;
        } else if (i2 == 1) {
            zzB(8);
            return true;
        } else if (i2 == 2) {
            zzB(zzj());
            return true;
        } else if (i2 == 3) {
            do {
                zzm = zzm();
                if (zzm == 0 || !zzE(zzm)) {
                    zzz(((i >>> 3) << 3) | 4);
                }
                zzm = zzm();
                break;
            } while (!zzE(zzm));
            zzz(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzB(4);
                return true;
            }
            throw zzhcd.zza();
        }
    }

    public final long zzr() throws IOException {
        long j;
        long j2;
        long j3;
        int i = this.zzh;
        int i2 = this.zzf;
        if (i2 != i) {
            byte[] bArr = this.zze;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.zzh = i3;
                return (long) b2;
            } else if (i2 - i3 >= 9) {
                int i4 = i + 2;
                byte b3 = (bArr[i3] << 7) ^ b2;
                if (b3 < 0) {
                    j = (long) (b3 ^ Byte.MIN_VALUE);
                } else {
                    int i5 = i + 3;
                    byte b4 = (bArr[i4] << 14) ^ b3;
                    if (b4 >= 0) {
                        j = (long) (b4 ^ 16256);
                    } else {
                        int i6 = i + 4;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            i4 = i6;
                            j = (long) (-2080896 ^ b5);
                        } else {
                            i5 = i + 5;
                            long j4 = (((long) bArr[i6]) << 28) ^ ((long) b5);
                            if (j4 >= 0) {
                                j = j4 ^ 266354560;
                            } else {
                                i4 = i + 6;
                                long j5 = (((long) bArr[i5]) << 35) ^ j4;
                                if (j5 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    int i7 = i + 7;
                                    long j6 = j5 ^ (((long) bArr[i4]) << 42);
                                    if (j6 >= 0) {
                                        j2 = j6 ^ 4363953127296L;
                                    } else {
                                        i4 = i + 8;
                                        j5 = j6 ^ (((long) bArr[i7]) << 49);
                                        if (j5 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            i7 = i + 9;
                                            long j7 = (j5 ^ (((long) bArr[i4]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                i4 = i + 10;
                                                if (((long) bArr[i7]) >= 0) {
                                                    j = j7;
                                                }
                                            } else {
                                                j2 = j7;
                                            }
                                        }
                                    }
                                    i4 = i7;
                                }
                                j = j5 ^ j3;
                            }
                        }
                    }
                    i4 = i5;
                }
                this.zzh = i4;
                return j;
            }
        }
        return zzs();
    }
}
