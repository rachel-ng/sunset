package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhak extends zzham {
    private final ByteBuffer zze;
    private final long zzf;
    private long zzg;
    private long zzh;
    private final long zzi;
    private int zzj;
    private int zzk;
    private int zzl = Integer.MAX_VALUE;

    /* synthetic */ zzhak(ByteBuffer byteBuffer, boolean z, zzhaj zzhaj) {
        super((zzhal) null);
        this.zze = byteBuffer;
        long zze2 = zzhfa.zze(byteBuffer);
        this.zzf = zze2;
        this.zzg = ((long) byteBuffer.limit()) + zze2;
        long position = zze2 + ((long) byteBuffer.position());
        this.zzh = position;
        this.zzi = position;
    }

    private final int zzK() {
        return (int) (this.zzg - this.zzh);
    }

    private final void zzL() {
        long j = this.zzg + ((long) this.zzj);
        this.zzg = j;
        int i = (int) (j - this.zzi);
        int i2 = this.zzl;
        if (i > i2) {
            int i3 = i - i2;
            this.zzj = i3;
            this.zzg = j - ((long) i3);
            return;
        }
        this.zzj = 0;
    }

    public final void zzA(int i) {
        this.zzl = i;
        zzL();
    }

    public final void zzB(int i) throws IOException {
        if (i >= 0 && i <= zzK()) {
            this.zzh += (long) i;
        } else if (i < 0) {
            throw zzhcd.zzf();
        } else {
            throw zzhcd.zzj();
        }
    }

    public final boolean zzC() throws IOException {
        return this.zzh == this.zzg;
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        long j = this.zzh;
        if (j != this.zzg) {
            this.zzh = 1 + j;
            return zzhfa.zza(j);
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
        return (int) (this.zzh - this.zzi);
    }

    public final int zze(int i) throws zzhcd {
        if (i >= 0) {
            int zzd = i + zzd();
            int i2 = this.zzl;
            if (zzd <= i2) {
                this.zzl = zzd;
                zzL();
                return i2;
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
        long j = this.zzh;
        if (this.zzg - j >= 4) {
            this.zzh = 4 + j;
            int zza = (zzhfa.zza(1 + j) & 255) << 8;
            return ((zzhfa.zza(j + 3) & 255) << Ascii.CAN) | zza | (zzhfa.zza(j) & 255) | ((zzhfa.zza(2 + j) & 255) << 16);
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
            this.zzk = 0;
            return 0;
        }
        int zzj2 = zzj();
        this.zzk = zzj2;
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
        long j = this.zzh;
        if (this.zzg - j >= 8) {
            this.zzh = 8 + j;
            long zza = (long) zzhfa.zza(5 + j);
            long zza2 = (((long) zzhfa.zza(j)) & 255) | ((((long) zzhfa.zza(1 + j)) & 255) << 8) | ((((long) zzhfa.zza(2 + j)) & 255) << 16) | ((((long) zzhfa.zza(3 + j)) & 255) << 24) | ((((long) zzhfa.zza(4 + j)) & 255) << 32);
            return ((((long) zzhfa.zza(j + 7)) & 255) << 56) | zza2 | ((zza & 255) << 40) | ((((long) zzhfa.zza(6 + j)) & 255) << 48);
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
        if (zzj2 > 0 && zzj2 <= zzK()) {
            byte[] bArr = new byte[zzj2];
            long j = (long) zzj2;
            zzhfa.zzo(this.zzh, bArr, 0, j);
            this.zzh += j;
            return new zzgzy(bArr);
        } else if (zzj2 == 0) {
            return zzhac.zzb;
        } else {
            if (zzj2 < 0) {
                throw zzhcd.zzf();
            }
            throw zzhcd.zzj();
        }
    }

    public final String zzx() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0 && zzj2 <= zzK()) {
            byte[] bArr = new byte[zzj2];
            long j = (long) zzj2;
            zzhfa.zzo(this.zzh, bArr, 0, j);
            String str = new String(bArr, zzhcb.zzb);
            this.zzh += j;
            return str;
        } else if (zzj2 == 0) {
            return "";
        } else {
            if (zzj2 < 0) {
                throw zzhcd.zzf();
            }
            throw zzhcd.zzj();
        }
    }

    public final String zzy() throws IOException {
        int zzj2 = zzj();
        if (zzj2 > 0 && zzj2 <= zzK()) {
            String zzg2 = zzhff.zzg(this.zze, (int) (this.zzh - this.zzf), zzj2);
            this.zzh += (long) zzj2;
            return zzg2;
        } else if (zzj2 == 0) {
            return "";
        } else {
            if (zzj2 <= 0) {
                throw zzhcd.zzf();
            }
            throw zzhcd.zzj();
        }
    }

    public final void zzz(int i) throws zzhcd {
        if (this.zzk != i) {
            throw zzhcd.zzb();
        }
    }

    public final boolean zzE(int i) throws IOException {
        int zzm;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (zzK() >= 10) {
                while (i3 < 10) {
                    long j = this.zzh;
                    this.zzh = 1 + j;
                    if (zzhfa.zza(j) < 0) {
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

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0089, code lost:
        if (com.google.android.gms.internal.ads.zzhfa.zza(r3) >= 0) goto L_0x008b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzj() throws java.io.IOException {
        /*
            r9 = this;
            long r0 = r9.zzh
            long r2 = r9.zzg
            int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x000a
            goto L_0x0092
        L_0x000a:
            r2 = 1
            long r2 = r2 + r0
            byte r4 = com.google.android.gms.internal.ads.zzhfa.zza(r0)
            if (r4 < 0) goto L_0x0016
            r9.zzh = r2
            return r4
        L_0x0016:
            long r5 = r9.zzg
            long r5 = r5 - r2
            r7 = 9
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x0092
            r5 = 2
            long r5 = r5 + r0
            byte r2 = com.google.android.gms.internal.ads.zzhfa.zza(r2)
            int r2 = r2 << 7
            r2 = r2 ^ r4
            if (r2 >= 0) goto L_0x002e
            r0 = r2 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x008f
        L_0x002e:
            r3 = 3
            long r3 = r3 + r0
            byte r5 = com.google.android.gms.internal.ads.zzhfa.zza(r5)
            int r5 = r5 << 14
            r2 = r2 ^ r5
            if (r2 < 0) goto L_0x003e
            r0 = r2 ^ 16256(0x3f80, float:2.278E-41)
        L_0x003c:
            r5 = r3
            goto L_0x008f
        L_0x003e:
            r5 = 4
            long r5 = r5 + r0
            byte r3 = com.google.android.gms.internal.ads.zzhfa.zza(r3)
            int r3 = r3 << 21
            r2 = r2 ^ r3
            if (r2 >= 0) goto L_0x004f
            r0 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r2
            goto L_0x008f
        L_0x004f:
            r3 = 5
            long r3 = r3 + r0
            byte r5 = com.google.android.gms.internal.ads.zzhfa.zza(r5)
            int r6 = r5 << 28
            r2 = r2 ^ r6
            r6 = 266354560(0xfe03f80, float:2.2112565E-29)
            r2 = r2 ^ r6
            if (r5 >= 0) goto L_0x008d
            r5 = 6
            long r5 = r5 + r0
            byte r3 = com.google.android.gms.internal.ads.zzhfa.zza(r3)
            if (r3 >= 0) goto L_0x008b
            r3 = 7
            long r3 = r3 + r0
            byte r5 = com.google.android.gms.internal.ads.zzhfa.zza(r5)
            if (r5 >= 0) goto L_0x008d
            r5 = 8
            long r5 = r5 + r0
            byte r3 = com.google.android.gms.internal.ads.zzhfa.zza(r3)
            if (r3 >= 0) goto L_0x008b
            long r3 = r0 + r7
            byte r5 = com.google.android.gms.internal.ads.zzhfa.zza(r5)
            if (r5 >= 0) goto L_0x008d
            r5 = 10
            long r5 = r5 + r0
            byte r0 = com.google.android.gms.internal.ads.zzhfa.zza(r3)
            if (r0 < 0) goto L_0x0092
        L_0x008b:
            r0 = r2
            goto L_0x008f
        L_0x008d:
            r0 = r2
            goto L_0x003c
        L_0x008f:
            r9.zzh = r5
            return r0
        L_0x0092:
            long r0 = r9.zzs()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhak.zzj():int");
    }

    public final long zzr() throws IOException {
        long j;
        long j2;
        long j3;
        byte b2;
        long j4 = this.zzh;
        if (this.zzg != j4) {
            long j5 = 1 + j4;
            byte zza = zzhfa.zza(j4);
            if (zza >= 0) {
                this.zzh = j5;
                return (long) zza;
            } else if (this.zzg - j5 >= 9) {
                long j6 = 2 + j4;
                byte zza2 = (zzhfa.zza(j5) << 7) ^ zza;
                if (zza2 < 0) {
                    b2 = zza2 ^ Byte.MIN_VALUE;
                } else {
                    long j7 = 3 + j4;
                    byte zza3 = zza2 ^ (zzhfa.zza(j6) << 14);
                    if (zza3 >= 0) {
                        j2 = (long) (zza3 ^ 16256);
                    } else {
                        j6 = 4 + j4;
                        byte zza4 = zza3 ^ (zzhfa.zza(j7) << Ascii.NAK);
                        if (zza4 < 0) {
                            b2 = -2080896 ^ zza4;
                        } else {
                            j7 = 5 + j4;
                            long zza5 = (((long) zzhfa.zza(j6)) << 28) ^ ((long) zza4);
                            if (zza5 >= 0) {
                                j2 = 266354560 ^ zza5;
                            } else {
                                long j8 = 6 + j4;
                                long zza6 = (((long) zzhfa.zza(j7)) << 35) ^ zza5;
                                if (zza6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    j = j4 + 7;
                                    long zza7 = zza6 ^ (((long) zzhfa.zza(j8)) << 42);
                                    if (zza7 >= 0) {
                                        j2 = 4363953127296L ^ zza7;
                                    } else {
                                        j8 = 8 + j4;
                                        zza6 = zza7 ^ (((long) zzhfa.zza(j)) << 49);
                                        if (zza6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            j = j4 + 9;
                                            long zza8 = (zza6 ^ (((long) zzhfa.zza(j8)) << 56)) ^ 71499008037633920L;
                                            if (zza8 < 0) {
                                                long j9 = j4 + 10;
                                                if (((long) zzhfa.zza(j)) >= 0) {
                                                    j = j9;
                                                }
                                            }
                                            j2 = zza8;
                                        }
                                    }
                                    this.zzh = j;
                                    return j2;
                                }
                                j2 = j3 ^ zza6;
                                j = j8;
                                this.zzh = j;
                                return j2;
                            }
                        }
                    }
                    j = j7;
                    this.zzh = j;
                    return j2;
                }
                j2 = (long) b2;
                this.zzh = j;
                return j2;
            }
        }
        return zzs();
    }
}
