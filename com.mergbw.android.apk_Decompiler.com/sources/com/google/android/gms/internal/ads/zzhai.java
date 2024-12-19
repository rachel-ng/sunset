package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.C;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhai extends zzham {
    private final InputStream zze;
    private final byte[] zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl = Integer.MAX_VALUE;

    /* synthetic */ zzhai(InputStream inputStream, int i, zzhah zzhah) {
        super((zzhal) null);
        byte[] bArr = zzhcb.zzd;
        this.zze = inputStream;
        this.zzf = new byte[4096];
        this.zzg = 0;
        this.zzi = 0;
        this.zzk = 0;
    }

    private final List zzK(int i) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (i > 0) {
            int min = Math.min(i, 4096);
            byte[] bArr = new byte[min];
            int i2 = 0;
            while (i2 < min) {
                int read = this.zze.read(bArr, i2, min - i2);
                if (read != -1) {
                    this.zzk += read;
                    i2 += read;
                } else {
                    throw zzhcd.zzj();
                }
            }
            i -= min;
            arrayList.add(bArr);
        }
        return arrayList;
    }

    private final void zzL() {
        int i = this.zzg + this.zzh;
        this.zzg = i;
        int i2 = this.zzk + i;
        int i3 = this.zzl;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzh = i4;
            this.zzg = i - i4;
            return;
        }
        this.zzh = 0;
    }

    private final void zzM(int i) throws IOException {
        if (zzN(i)) {
            return;
        }
        if (i > (Integer.MAX_VALUE - this.zzk) - this.zzi) {
            throw zzhcd.zzi();
        }
        throw zzhcd.zzj();
    }

    private final boolean zzN(int i) throws IOException {
        int i2 = this.zzi;
        int i3 = i2 + i;
        int i4 = this.zzg;
        if (i3 > i4) {
            int i5 = this.zzk;
            if (i > (Integer.MAX_VALUE - i5) - i2 || i5 + i2 + i > this.zzl) {
                return false;
            }
            if (i2 > 0) {
                if (i4 > i2) {
                    byte[] bArr = this.zzf;
                    System.arraycopy(bArr, i2, bArr, 0, i4 - i2);
                }
                i5 = this.zzk + i2;
                this.zzk = i5;
                i4 = this.zzg - i2;
                this.zzg = i4;
                this.zzi = 0;
            }
            try {
                int read = this.zze.read(this.zzf, i4, Math.min(4096 - i4, (Integer.MAX_VALUE - i5) - i4));
                if (read == 0 || read < -1 || read > 4096) {
                    throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read <= 0) {
                    return false;
                } else {
                    this.zzg += read;
                    zzL();
                    if (this.zzg >= i) {
                        return true;
                    }
                    return zzN(i);
                }
            } catch (zzhcd e) {
                e.zzk();
                throw e;
            }
        } else {
            throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
        }
    }

    private final byte[] zzO(int i, boolean z) throws IOException {
        byte[] zzP = zzP(i);
        if (zzP != null) {
            return zzP;
        }
        int i2 = this.zzi;
        int i3 = this.zzg;
        int i4 = i3 - i2;
        this.zzk += i3;
        this.zzi = 0;
        this.zzg = 0;
        List<byte[]> zzK = zzK(i - i4);
        byte[] bArr = new byte[i];
        System.arraycopy(this.zzf, i2, bArr, 0, i4);
        for (byte[] bArr2 : zzK) {
            int length = bArr2.length;
            System.arraycopy(bArr2, 0, bArr, i4, length);
            i4 += length;
        }
        return bArr;
    }

    private final byte[] zzP(int i) throws IOException {
        if (i == 0) {
            return zzhcb.zzd;
        }
        int i2 = this.zzk;
        int i3 = this.zzi;
        int i4 = i2 + i3 + i;
        if (C.RATE_UNSET_INT + i4 <= 0) {
            int i5 = this.zzl;
            if (i4 <= i5) {
                int i6 = this.zzg - i3;
                int i7 = i - i6;
                if (i7 >= 4096) {
                    try {
                        if (i7 > this.zze.available()) {
                            return null;
                        }
                    } catch (zzhcd e) {
                        e.zzk();
                        throw e;
                    }
                }
                byte[] bArr = new byte[i];
                System.arraycopy(this.zzf, this.zzi, bArr, 0, i6);
                this.zzk += this.zzg;
                this.zzi = 0;
                this.zzg = 0;
                while (i6 < i) {
                    try {
                        int read = this.zze.read(bArr, i6, i - i6);
                        if (read != -1) {
                            this.zzk += read;
                            i6 += read;
                        } else {
                            throw zzhcd.zzj();
                        }
                    } catch (zzhcd e2) {
                        e2.zzk();
                        throw e2;
                    }
                }
                return bArr;
            }
            zzB((i5 - i2) - i3);
            throw zzhcd.zzj();
        }
        throw zzhcd.zzi();
    }

    public final void zzA(int i) {
        this.zzl = i;
        zzL();
    }

    public final void zzB(int i) throws IOException {
        int i2 = this.zzg;
        int i3 = this.zzi;
        int i4 = i2 - i3;
        if (i <= i4 && i >= 0) {
            this.zzi = i3 + i;
        } else if (i >= 0) {
            int i5 = this.zzk;
            int i6 = i5 + i3;
            int i7 = this.zzl;
            if (i6 + i <= i7) {
                this.zzk = i6;
                this.zzg = 0;
                this.zzi = 0;
                while (i4 < i) {
                    try {
                        long j = (long) (i - i4);
                        long skip = this.zze.skip(j);
                        int i8 = (skip > 0 ? 1 : (skip == 0 ? 0 : -1));
                        if (i8 < 0 || skip > j) {
                            throw new IllegalStateException(String.valueOf(this.zze.getClass()) + "#skip returned invalid result: " + skip + "\nThe InputStream implementation is buggy.");
                        } else if (i8 == 0) {
                            break;
                        } else {
                            i4 += (int) skip;
                        }
                    } catch (zzhcd e) {
                        e.zzk();
                        throw e;
                    } catch (Throwable th) {
                        this.zzk += i4;
                        zzL();
                        throw th;
                    }
                }
                this.zzk += i4;
                zzL();
                if (i4 < i) {
                    int i9 = this.zzg;
                    int i10 = i9 - this.zzi;
                    this.zzi = i9;
                    zzM(1);
                    while (true) {
                        int i11 = i - i10;
                        int i12 = this.zzg;
                        if (i11 > i12) {
                            i10 += i12;
                            this.zzi = i12;
                            zzM(1);
                        } else {
                            this.zzi = i11;
                            return;
                        }
                    }
                }
            } else {
                zzB((i7 - i5) - i3);
                throw zzhcd.zzj();
            }
        } else {
            throw zzhcd.zzf();
        }
    }

    public final boolean zzC() throws IOException {
        return this.zzi == this.zzg && !zzN(1);
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        if (this.zzi == this.zzg) {
            zzM(1);
        }
        byte[] bArr = this.zzf;
        int i = this.zzi;
        this.zzi = i + 1;
        return bArr[i];
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public final int zzd() {
        return this.zzk + this.zzi;
    }

    public final int zze(int i) throws zzhcd {
        if (i >= 0) {
            int i2 = this.zzk + this.zzi;
            int i3 = this.zzl;
            int i4 = i + i2;
            if (i4 <= i3) {
                this.zzl = i4;
                zzL();
                return i3;
            }
            throw zzhcd.zzj();
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
        int i = this.zzi;
        if (this.zzg - i < 4) {
            zzM(4);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 4;
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
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
        int i = this.zzi;
        if (this.zzg - i < 8) {
            zzM(8);
            i = this.zzi;
        }
        byte[] bArr = this.zzf;
        this.zzi = i + 8;
        long j = (long) bArr[i + 2];
        long j2 = (long) bArr[i + 3];
        long j3 = (long) bArr[i + 4];
        long j4 = (long) bArr[i + 5];
        long j5 = (long) bArr[i + 6];
        long j6 = (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((j & 255) << 16) | ((j2 & 255) << 24);
        return ((((long) bArr[i + 7]) & 255) << 56) | j6 | ((j3 & 255) << 32) | ((j4 & 255) << 40) | ((j5 & 255) << 48);
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
        int i = this.zzg;
        int i2 = this.zzi;
        if (zzj2 <= i - i2 && zzj2 > 0) {
            zzhac zzv = zzhac.zzv(this.zzf, i2, zzj2);
            this.zzi += zzj2;
            return zzv;
        } else if (zzj2 == 0) {
            return zzhac.zzb;
        } else {
            if (zzj2 >= 0) {
                byte[] zzP = zzP(zzj2);
                if (zzP != null) {
                    return zzhac.zzv(zzP, 0, zzP.length);
                }
                int i3 = this.zzi;
                int i4 = this.zzg;
                int i5 = i4 - i3;
                this.zzk += i4;
                this.zzi = 0;
                this.zzg = 0;
                List<byte[]> zzK = zzK(zzj2 - i5);
                byte[] bArr = new byte[zzj2];
                System.arraycopy(this.zzf, i3, bArr, 0, i5);
                for (byte[] bArr2 : zzK) {
                    int length = bArr2.length;
                    System.arraycopy(bArr2, 0, bArr, i5, length);
                    i5 += length;
                }
                return new zzgzy(bArr);
            }
            throw zzhcd.zzf();
        }
    }

    public final String zzx() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0) {
            int i = this.zzg;
            int i2 = this.zzi;
            if (zzj2 <= i - i2) {
                String str = new String(this.zzf, i2, zzj2, zzhcb.zzb);
                this.zzi += zzj2;
                return str;
            }
        }
        if (zzj2 == 0) {
            return "";
        }
        if (zzj2 < 0) {
            throw zzhcd.zzf();
        } else if (zzj2 > this.zzg) {
            return new String(zzO(zzj2, false), zzhcb.zzb);
        } else {
            zzM(zzj2);
            String str2 = new String(this.zzf, this.zzi, zzj2, zzhcb.zzb);
            this.zzi += zzj2;
            return str2;
        }
    }

    public final String zzy() throws IOException {
        byte[] bArr;
        int zzj2 = zzj();
        int i = this.zzi;
        int i2 = this.zzg;
        if (zzj2 <= i2 - i && zzj2 > 0) {
            bArr = this.zzf;
            this.zzi = i + zzj2;
        } else if (zzj2 == 0) {
            return "";
        } else {
            if (zzj2 >= 0) {
                i = 0;
                if (zzj2 <= i2) {
                    zzM(zzj2);
                    bArr = this.zzf;
                    this.zzi = zzj2;
                } else {
                    bArr = zzO(zzj2, false);
                }
            } else {
                throw zzhcd.zzf();
            }
        }
        return zzhff.zzh(bArr, i, zzj2);
    }

    public final void zzz(int i) throws zzhcd {
        if (this.zzj != i) {
            throw zzhcd.zzb();
        }
    }

    public final int zzj() throws IOException {
        byte b2;
        byte b3;
        int i = this.zzi;
        int i2 = this.zzg;
        if (i2 != i) {
            byte[] bArr = this.zzf;
            int i3 = i + 1;
            byte b4 = bArr[i];
            if (b4 >= 0) {
                this.zzi = i3;
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
                this.zzi = i4;
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
            if (this.zzg - this.zzi >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzf;
                    int i4 = this.zzi;
                    this.zzi = i4 + 1;
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
        int i = this.zzi;
        int i2 = this.zzg;
        if (i2 != i) {
            byte[] bArr = this.zzf;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.zzi = i3;
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
                this.zzi = i4;
                return j;
            }
        }
        return zzs();
    }
}
