package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.nio.charset.Charset;
import java.util.Arrays;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfu {
    private static final char[] zza = {13, 10};
    private static final char[] zzb = {10};
    private static final zzgbh zzc = zzgbh.zzr(zzfxs.zza, zzfxs.zzc, zzfxs.zzf, zzfxs.zzd, zzfxs.zze);
    private byte[] zzd;
    private int zze;
    private int zzf;

    public zzfu() {
        this.zzd = zzgd.zzf;
    }

    public zzfu(byte[] bArr, int i) {
        this.zzd = bArr;
        this.zzf = i;
    }

    private final char zzN(Charset charset, char[] cArr) {
        int zzO = zzO(charset);
        if (zzO != 0) {
            int i = zzO >> 16;
            for (char c2 : cArr) {
                char c3 = (char) i;
                if (c2 == c3) {
                    this.zze += (char) zzO;
                    return c3;
                }
            }
        }
        return 0;
    }

    private final int zzO(Charset charset) {
        byte b2;
        char zzb2;
        int i = 1;
        if (charset.equals(zzfxs.zzc) || charset.equals(zzfxs.zza)) {
            int i2 = this.zzf;
            int i3 = this.zze;
            if (i2 - i3 > 0) {
                b2 = (byte) zzgdx.zza((long) (this.zzd[i3] & 255));
                return (zzgdx.zza((long) b2) << 16) + i;
            }
        }
        if (charset.equals(zzfxs.zzf) || charset.equals(zzfxs.zzd)) {
            int i4 = this.zzf;
            int i5 = this.zze;
            if (i4 - i5 >= 2) {
                byte[] bArr = this.zzd;
                zzb2 = zzgdx.zzb(bArr[i5], bArr[i5 + 1]);
                b2 = (byte) zzb2;
                i = 2;
                return (zzgdx.zza((long) b2) << 16) + i;
            }
        }
        if (!charset.equals(zzfxs.zze)) {
            return 0;
        }
        int i6 = this.zzf;
        int i7 = this.zze;
        if (i6 - i7 < 2) {
            return 0;
        }
        byte[] bArr2 = this.zzd;
        zzb2 = zzgdx.zzb(bArr2[i7 + 1], bArr2[i7]);
        b2 = (byte) zzb2;
        i = 2;
        return (zzgdx.zza((long) b2) << 16) + i;
    }

    public final String zzA(int i, Charset charset) {
        byte[] bArr = this.zzd;
        int i2 = this.zze;
        String str = new String(bArr, i2, i, charset);
        this.zze = i2 + i;
        return str;
    }

    public final Charset zzB() {
        int i = this.zzf;
        int i2 = this.zze;
        int i3 = i - i2;
        if (i3 >= 3) {
            byte[] bArr = this.zzd;
            if (bArr[i2] == -17 && bArr[i2 + 1] == -69 && bArr[i2 + 2] == -65) {
                this.zze = i2 + 3;
                return zzfxs.zzc;
            }
        }
        if (i3 < 2) {
            return null;
        }
        byte[] bArr2 = this.zzd;
        byte b2 = bArr2[i2];
        if (b2 == -2) {
            if (bArr2[i2 + 1] != -1) {
                return null;
            }
            this.zze = i2 + 2;
            return zzfxs.zzd;
        } else if (b2 != -1 || bArr2[i2 + 1] != -2) {
            return null;
        } else {
            this.zze = i2 + 2;
            return zzfxs.zze;
        }
    }

    public final short zzC() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        this.zze = i + 2;
        return (short) (((bArr[i2] & 255) << 8) | (bArr[i] & 255));
    }

    public final short zzD() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        this.zze = i + 2;
        return (short) ((bArr[i2] & 255) | ((bArr[i] & 255) << 8));
    }

    public final void zzE(int i) {
        byte[] bArr = this.zzd;
        if (i > bArr.length) {
            this.zzd = Arrays.copyOf(bArr, i);
        }
    }

    public final void zzF(zzft zzft, int i) {
        zzG(zzft.zza, 0, i);
        zzft.zzk(0);
    }

    public final void zzG(byte[] bArr, int i, int i2) {
        System.arraycopy(this.zzd, this.zze, bArr, i, i2);
        this.zze += i2;
    }

    public final void zzH(int i) {
        byte[] bArr = this.zzd;
        if (bArr.length < i) {
            bArr = new byte[i];
        }
        zzI(bArr, i);
    }

    public final void zzI(byte[] bArr, int i) {
        this.zzd = bArr;
        this.zzf = i;
        this.zze = 0;
    }

    public final void zzJ(int i) {
        boolean z = false;
        if (i >= 0 && i <= this.zzd.length) {
            z = true;
        }
        zzeq.zzd(z);
        this.zzf = i;
    }

    public final void zzK(int i) {
        boolean z = false;
        if (i >= 0 && i <= this.zzf) {
            z = true;
        }
        zzeq.zzd(z);
        this.zze = i;
    }

    public final void zzL(int i) {
        zzK(this.zze + i);
    }

    public final byte[] zzM() {
        return this.zzd;
    }

    public final char zza(Charset charset) {
        zzeq.zze(zzc.contains(charset), "Unsupported charset: ".concat(String.valueOf(String.valueOf(charset))));
        return (char) (zzO(charset) >> 16);
    }

    public final int zzb() {
        return this.zzf - this.zze;
    }

    public final int zzc() {
        return this.zzd.length;
    }

    public final int zzd() {
        return this.zze;
    }

    public final int zze() {
        return this.zzf;
    }

    public final int zzf() {
        return this.zzd[this.zze] & 255;
    }

    public final int zzg() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        int i4 = i + 3;
        this.zze = i4;
        this.zze = i + 4;
        return (bArr[i4] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
    }

    public final int zzh() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        this.zze = i + 3;
        return (bArr[i3] & 255) | (((bArr[i] & 255) << Ascii.CAN) >> 8) | ((bArr[i2] & 255) << 8);
    }

    public final int zzi() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        int i4 = i + 3;
        this.zze = i4;
        this.zze = i + 4;
        int i5 = (bArr[i2] & 255) << 8;
        return ((bArr[i4] & 255) << Ascii.CAN) | i5 | (bArr[i] & 255) | ((bArr[i3] & 255) << 16);
    }

    public final int zzj() {
        int zzi = zzi();
        if (zzi >= 0) {
            return zzi;
        }
        throw new IllegalStateException("Top bit not zero: " + zzi);
    }

    public final int zzk() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        this.zze = i + 2;
        return ((bArr[i2] & 255) << 8) | (bArr[i] & 255);
    }

    public final int zzl() {
        return (zzm() << 21) | (zzm() << 14) | (zzm() << 7) | zzm();
    }

    public final int zzm() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        this.zze = i + 1;
        return bArr[i] & 255;
    }

    public final int zzn() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        this.zze = i + 2;
        this.zze = i + 4;
        return (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
    }

    public final int zzo() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        this.zze = i + 3;
        return (bArr[i3] & 255) | ((bArr[i] & 255) << 16) | ((bArr[i2] & 255) << 8);
    }

    public final int zzp() {
        int zzg = zzg();
        if (zzg >= 0) {
            return zzg;
        }
        throw new IllegalStateException("Top bit not zero: " + zzg);
    }

    public final int zzq() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        this.zze = i + 2;
        return (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
    }

    public final long zzr() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        long j = (long) bArr[i2];
        int i4 = i + 3;
        this.zze = i4;
        long j2 = (long) bArr[i3];
        int i5 = i + 4;
        this.zze = i5;
        long j3 = (long) bArr[i4];
        int i6 = i + 5;
        this.zze = i6;
        long j4 = (long) bArr[i5];
        int i7 = i + 6;
        this.zze = i7;
        int i8 = i + 7;
        this.zze = i8;
        long j5 = (long) bArr[i6];
        this.zze = i + 8;
        long j6 = (j & 255) << 8;
        long j7 = (((long) bArr[i7]) & 255) << 48;
        return ((((long) bArr[i8]) & 255) << 56) | j7 | j6 | (((long) bArr[i]) & 255) | ((j2 & 255) << 16) | ((j3 & 255) << 24) | ((j4 & 255) << 32) | ((j5 & 255) << 40);
    }

    public final long zzs() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        long j = (long) bArr[i2];
        int i4 = i + 3;
        this.zze = i4;
        long j2 = (long) bArr[i3];
        this.zze = i + 4;
        return ((((long) bArr[i4]) & 255) << 24) | (((long) bArr[i]) & 255) | ((j & 255) << 8) | ((j2 & 255) << 16);
    }

    public final long zzt() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        long j = (long) bArr[i2];
        int i4 = i + 3;
        this.zze = i4;
        long j2 = (long) bArr[i3];
        int i5 = i + 4;
        this.zze = i5;
        long j3 = (long) bArr[i4];
        int i6 = i + 5;
        this.zze = i6;
        long j4 = (long) bArr[i5];
        int i7 = i + 6;
        this.zze = i7;
        long j5 = j4;
        long j6 = (long) bArr[i6];
        int i8 = i + 7;
        this.zze = i8;
        long j7 = j6;
        this.zze = i + 8;
        return (((long) bArr[i8]) & 255) | ((((long) bArr[i]) & 255) << 56) | ((j & 255) << 48) | ((j2 & 255) << 40) | ((j3 & 255) << 32) | ((j5 & 255) << 24) | ((j7 & 255) << 16) | ((((long) bArr[i7]) & 255) << 8);
    }

    public final long zzu() {
        byte[] bArr = this.zzd;
        int i = this.zze;
        int i2 = i + 1;
        this.zze = i2;
        int i3 = i + 2;
        this.zze = i3;
        long j = (long) bArr[i2];
        int i4 = i + 3;
        this.zze = i4;
        this.zze = i + 4;
        long j2 = j & 255;
        return (((long) bArr[i4]) & 255) | ((((long) bArr[i]) & 255) << 24) | (j2 << 16) | ((((long) bArr[i3]) & 255) << 8);
    }

    public final long zzv() {
        long zzt = zzt();
        if (zzt >= 0) {
            return zzt;
        }
        throw new IllegalStateException("Top bit not zero: " + zzt);
    }

    public final long zzw() {
        int i;
        int i2;
        long j = (long) this.zzd[this.zze];
        int i3 = 7;
        while (true) {
            i = 0;
            i2 = 1;
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((((long) i4) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= (long) (i4 - 1);
                i = 7 - i3;
            } else if (i3 == 7) {
                i = 1;
            }
        }
        if (i != 0) {
            while (i2 < i) {
                byte b2 = this.zzd[this.zze + i2];
                if ((b2 & 192) == 128) {
                    j = (j << 6) | ((long) (b2 & Utf8.REPLACEMENT_BYTE));
                    i2++;
                } else {
                    throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
                }
            }
            this.zze += i;
            return j;
        }
        throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
    }

    public final String zzx(char c2) {
        int i = this.zzf;
        int i2 = this.zze;
        if (i - i2 == 0) {
            return null;
        }
        while (i2 < this.zzf && this.zzd[i2] != 0) {
            i2++;
        }
        byte[] bArr = this.zzd;
        int i3 = this.zze;
        String zzB = zzgd.zzB(bArr, i3, i2 - i3);
        this.zze = i2;
        if (i2 < this.zzf) {
            this.zze = i2 + 1;
        }
        return zzB;
    }

    public final String zzy(Charset charset) {
        zzeq.zze(zzc.contains(charset), "Unsupported charset: ".concat(String.valueOf(String.valueOf(charset))));
        if (this.zzf - this.zze == 0) {
            return null;
        }
        if (!charset.equals(zzfxs.zza)) {
            zzB();
        }
        int i = 1;
        if (!charset.equals(zzfxs.zzc) && !charset.equals(zzfxs.zza)) {
            i = 2;
            if (!charset.equals(zzfxs.zzf) && !charset.equals(zzfxs.zze) && !charset.equals(zzfxs.zzd)) {
                throw new IllegalArgumentException("Unsupported charset: ".concat(String.valueOf(String.valueOf(charset))));
            }
        }
        int i2 = this.zze;
        while (true) {
            int i3 = this.zzf;
            if (i2 >= i3 - (i - 1)) {
                i2 = i3;
                break;
            } else if ((charset.equals(zzfxs.zzc) || charset.equals(zzfxs.zza)) && zzgd.zzM(this.zzd[i2])) {
                break;
            } else {
                if (charset.equals(zzfxs.zzf) || charset.equals(zzfxs.zzd)) {
                    byte[] bArr = this.zzd;
                    if (bArr[i2] == 0 && zzgd.zzM(bArr[i2 + 1])) {
                        break;
                    }
                }
                if (charset.equals(zzfxs.zze)) {
                    byte[] bArr2 = this.zzd;
                    if (bArr2[i2 + 1] == 0 && zzgd.zzM(bArr2[i2])) {
                        break;
                    }
                }
                i2 += i;
            }
        }
        String zzA = zzA(i2 - this.zze, charset);
        if (this.zze != this.zzf && zzN(charset, zza) == 13) {
            zzN(charset, zzb);
        }
        return zzA;
    }

    public final String zzz(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = this.zze;
        int i3 = (i2 + i) - 1;
        String zzB = zzgd.zzB(this.zzd, i2, (i3 >= this.zzf || this.zzd[i3] != 0) ? i : i - 1);
        this.zze += i;
        return zzB;
    }

    public zzfu(int i) {
        this.zzd = new byte[i];
        this.zzf = i;
    }

    public zzfu(byte[] bArr) {
        this.zzd = bArr;
        this.zzf = bArr.length;
    }
}
