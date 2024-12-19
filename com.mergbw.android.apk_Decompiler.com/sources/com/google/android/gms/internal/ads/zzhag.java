package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhag extends zzham {
    private final Iterable zze;
    private final Iterator zzf;
    private ByteBuffer zzg;
    private int zzh;
    private int zzi;
    private int zzj = Integer.MAX_VALUE;
    private int zzk;
    private int zzl;
    private long zzm;
    private long zzn;
    private long zzo;

    /* synthetic */ zzhag(Iterable iterable, int i, boolean z, zzhaf zzhaf) {
        super((zzhal) null);
        this.zzh = i;
        this.zze = iterable;
        this.zzf = iterable.iterator();
        this.zzl = 0;
        if (i == 0) {
            this.zzg = zzhcb.zze;
            this.zzm = 0;
            this.zzn = 0;
            this.zzo = 0;
            return;
        }
        zzO();
    }

    private final int zzK() {
        return (int) ((((long) (this.zzh - this.zzl)) - this.zzm) + this.zzn);
    }

    private final void zzL() throws zzhcd {
        if (this.zzf.hasNext()) {
            zzO();
            return;
        }
        throw zzhcd.zzj();
    }

    private final void zzM(byte[] bArr, int i, int i2) throws IOException {
        if (i2 <= zzK()) {
            int i3 = i2;
            while (i3 > 0) {
                if (this.zzo - this.zzm == 0) {
                    zzL();
                }
                int min = Math.min(i3, (int) (this.zzo - this.zzm));
                long j = (long) min;
                zzhfa.zzo(this.zzm, bArr, (long) (i2 - i3), j);
                i3 -= min;
                this.zzm += j;
            }
        } else if (i2 > 0) {
            throw zzhcd.zzj();
        }
    }

    private final void zzN() {
        int i = this.zzh + this.zzi;
        this.zzh = i;
        int i2 = this.zzj;
        if (i > i2) {
            int i3 = i - i2;
            this.zzi = i3;
            this.zzh = i - i3;
            return;
        }
        this.zzi = 0;
    }

    private final void zzO() {
        ByteBuffer byteBuffer = (ByteBuffer) this.zzf.next();
        this.zzg = byteBuffer;
        this.zzl += (int) (this.zzm - this.zzn);
        long position = (long) byteBuffer.position();
        this.zzm = position;
        this.zzn = position;
        this.zzo = (long) this.zzg.limit();
        long zze2 = zzhfa.zze(this.zzg);
        this.zzm += zze2;
        this.zzn += zze2;
        this.zzo += zze2;
    }

    public final void zzA(int i) {
        this.zzj = i;
        zzN();
    }

    public final boolean zzC() throws IOException {
        return (((long) this.zzl) + this.zzm) - this.zzn == ((long) this.zzh);
    }

    public final boolean zzD() throws IOException {
        return zzr() != 0;
    }

    public final byte zza() throws IOException {
        if (this.zzo - this.zzm == 0) {
            zzL();
        }
        long j = this.zzm;
        this.zzm = 1 + j;
        return zzhfa.zza(j);
    }

    public final double zzb() throws IOException {
        return Double.longBitsToDouble(zzq());
    }

    public final float zzc() throws IOException {
        return Float.intBitsToFloat(zzi());
    }

    public final int zzd() {
        return (int) ((((long) this.zzl) + this.zzm) - this.zzn);
    }

    public final int zze(int i) throws zzhcd {
        if (i >= 0) {
            int zzd = i + zzd();
            int i2 = this.zzj;
            if (zzd <= i2) {
                this.zzj = zzd;
                zzN();
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
        long j = this.zzo;
        long j2 = this.zzm;
        if (j - j2 >= 4) {
            this.zzm = 4 + j2;
            return (zzhfa.zza(j2) & 255) | ((zzhfa.zza(1 + j2) & 255) << 8) | ((zzhfa.zza(2 + j2) & 255) << 16) | ((zzhfa.zza(j2 + 3) & 255) << Ascii.CAN);
        }
        return (zza() & 255) | ((zza() & 255) << 8) | ((zza() & 255) << 16) | ((zza() & 255) << Ascii.CAN);
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
        long j = this.zzo;
        long j2 = this.zzm;
        if (j - j2 >= 8) {
            this.zzm = 8 + j2;
            byte zza = zzhfa.zza(j2 + 4);
            long j3 = j2;
            byte zza2 = zzhfa.zza(j3 + 5);
            long zza3 = ((long) zzhfa.zza(j2)) & 255;
            byte zza4 = zzhfa.zza(j3 + 6);
            long zza5 = (((long) zzhfa.zza(1 + j2)) & 255) << 8;
            byte zza6 = zzhfa.zza(j3 + 7);
            return ((((long) zza) & 255) << 32) | zza3 | zza5 | ((((long) zzhfa.zza(j2 + 2)) & 255) << 16) | ((((long) zzhfa.zza(3 + j2)) & 255) << 24) | ((((long) zza2) & 255) << 40) | ((((long) zza4) & 255) << 48) | ((((long) zza6) & 255) << 56);
        }
        long zza7 = (((long) zza()) & 255) << 8;
        return (((long) zza()) & 255) | zza7 | ((((long) zza()) & 255) << 16) | ((((long) zza()) & 255) << 24) | ((((long) zza()) & 255) << 32) | ((((long) zza()) & 255) << 40) | ((((long) zza()) & 255) << 48) | ((((long) zza()) & 255) << 56);
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
            long j = this.zzo;
            long j2 = this.zzm;
            long j3 = (long) zzj2;
            if (j3 <= j - j2) {
                byte[] bArr = new byte[zzj2];
                zzhfa.zzo(j2, bArr, 0, j3);
                this.zzm += j3;
                return new zzgzy(bArr);
            }
        }
        if (zzj2 > 0 && zzj2 <= zzK()) {
            byte[] bArr2 = new byte[zzj2];
            zzM(bArr2, 0, zzj2);
            return new zzgzy(bArr2);
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
        if (zzj2 > 0) {
            long j = this.zzo;
            long j2 = this.zzm;
            long j3 = (long) zzj2;
            if (j3 <= j - j2) {
                byte[] bArr = new byte[zzj2];
                zzhfa.zzo(j2, bArr, 0, j3);
                String str = new String(bArr, zzhcb.zzb);
                this.zzm += j3;
                return str;
            }
        }
        if (zzj2 > 0 && zzj2 <= zzK()) {
            byte[] bArr2 = new byte[zzj2];
            zzM(bArr2, 0, zzj2);
            return new String(bArr2, zzhcb.zzb);
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
        if (zzj2 > 0) {
            long j = this.zzo;
            long j2 = this.zzm;
            long j3 = (long) zzj2;
            if (j3 <= j - j2) {
                String zzg2 = zzhff.zzg(this.zzg, (int) (j2 - this.zzn), zzj2);
                this.zzm += j3;
                return zzg2;
            }
        }
        if (zzj2 >= 0 && zzj2 <= zzK()) {
            byte[] bArr = new byte[zzj2];
            zzM(bArr, 0, zzj2);
            return zzhff.zzh(bArr, 0, zzj2);
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

    public final void zzB(int i) throws IOException {
        if (i >= 0) {
            if (((long) i) <= (((long) (this.zzh - this.zzl)) - this.zzm) + this.zzn) {
                while (i > 0) {
                    if (this.zzo - this.zzm == 0) {
                        zzL();
                    }
                    int min = Math.min(i, (int) (this.zzo - this.zzm));
                    i -= min;
                    this.zzm += (long) min;
                }
                return;
            }
        }
        if (i < 0) {
            throw zzhcd.zzf();
        }
        throw zzhcd.zzj();
    }

    public final boolean zzE(int i) throws IOException {
        int zzm2;
        int i2 = i & 7;
        if (i2 == 0) {
            for (int i3 = 0; i3 < 10; i3++) {
                if (zza() >= 0) {
                    return true;
                }
            }
            throw zzhcd.zze();
        } else if (i2 == 1) {
            zzB(8);
            return true;
        } else if (i2 == 2) {
            zzB(zzj());
            return true;
        } else if (i2 == 3) {
            do {
                zzm2 = zzm();
                if (zzm2 == 0 || !zzE(zzm2)) {
                    zzz(((i >>> 3) << 3) | 4);
                }
                zzm2 = zzm();
                break;
            } while (!zzE(zzm2));
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

    public final int zzj() throws IOException {
        byte b2;
        byte b3;
        long j = this.zzm;
        if (this.zzo != j) {
            long j2 = j + 1;
            byte zza = zzhfa.zza(j);
            if (zza >= 0) {
                this.zzm++;
                return zza;
            } else if (this.zzo - this.zzm >= 10) {
                long j3 = 2 + j;
                byte zza2 = (zzhfa.zza(j2) << 7) ^ zza;
                if (zza2 < 0) {
                    b2 = zza2 ^ Byte.MIN_VALUE;
                } else {
                    long j4 = 3 + j;
                    byte zza3 = (zzhfa.zza(j3) << 14) ^ zza2;
                    if (zza3 >= 0) {
                        b3 = zza3 ^ 16256;
                    } else {
                        long j5 = 4 + j;
                        byte zza4 = zza3 ^ (zzhfa.zza(j4) << Ascii.NAK);
                        if (zza4 < 0) {
                            b2 = -2080896 ^ zza4;
                        } else {
                            j4 = 5 + j;
                            byte zza5 = zzhfa.zza(j5);
                            byte b4 = (zza4 ^ (zza5 << Ascii.FS)) ^ 266354560;
                            if (zza5 < 0) {
                                j5 = 6 + j;
                                if (zzhfa.zza(j4) < 0) {
                                    j4 = 7 + j;
                                    if (zzhfa.zza(j5) < 0) {
                                        j5 = 8 + j;
                                        if (zzhfa.zza(j4) < 0) {
                                            j4 = 9 + j;
                                            if (zzhfa.zza(j5) < 0) {
                                                long j6 = j + 10;
                                                if (zzhfa.zza(j4) >= 0) {
                                                    long j7 = j6;
                                                    b2 = b4;
                                                    j3 = j7;
                                                }
                                            }
                                        }
                                    }
                                }
                                b2 = b4;
                            }
                            b3 = b4;
                        }
                        j3 = j5;
                    }
                    j3 = j4;
                }
                this.zzm = j3;
                return b2;
            }
        }
        return (int) zzs();
    }

    public final long zzr() throws IOException {
        long j;
        long j2;
        long j3;
        long j4 = this.zzm;
        if (this.zzo != j4) {
            long j5 = j4 + 1;
            byte zza = zzhfa.zza(j4);
            if (zza >= 0) {
                this.zzm++;
                return (long) zza;
            } else if (this.zzo - this.zzm >= 10) {
                long j6 = 2 + j4;
                byte zza2 = (zzhfa.zza(j5) << 7) ^ zza;
                if (zza2 < 0) {
                    j = (long) (zza2 ^ Byte.MIN_VALUE);
                } else {
                    long j7 = 3 + j4;
                    byte zza3 = (zzhfa.zza(j6) << 14) ^ zza2;
                    if (zza3 >= 0) {
                        j = (long) (zza3 ^ 16256);
                    } else {
                        long j8 = 4 + j4;
                        byte zza4 = zza3 ^ (zzhfa.zza(j7) << Ascii.NAK);
                        if (zza4 < 0) {
                            j = (long) (-2080896 ^ zza4);
                            j6 = j8;
                        } else {
                            j7 = 5 + j4;
                            long zza5 = (((long) zzhfa.zza(j8)) << 28) ^ ((long) zza4);
                            if (zza5 >= 0) {
                                j = 266354560 ^ zza5;
                            } else {
                                long j9 = 6 + j4;
                                long zza6 = zza5 ^ (((long) zzhfa.zza(j7)) << 35);
                                if (zza6 < 0) {
                                    j3 = -34093383808L;
                                } else {
                                    long j10 = 7 + j4;
                                    long zza7 = zza6 ^ (((long) zzhfa.zza(j9)) << 42);
                                    if (zza7 >= 0) {
                                        j2 = 4363953127296L ^ zza7;
                                    } else {
                                        j9 = 8 + j4;
                                        zza6 = zza7 ^ (((long) zzhfa.zza(j10)) << 49);
                                        if (zza6 < 0) {
                                            j3 = -558586000294016L;
                                        } else {
                                            j10 = 9 + j4;
                                            long zza8 = (zza6 ^ (((long) zzhfa.zza(j9)) << 56)) ^ 71499008037633920L;
                                            if (zza8 < 0) {
                                                long j11 = j4 + 10;
                                                if (((long) zzhfa.zza(j10)) >= 0) {
                                                    long j12 = j11;
                                                    j = zza8;
                                                    j6 = j12;
                                                }
                                            } else {
                                                j2 = zza8;
                                            }
                                        }
                                    }
                                    j6 = j10;
                                }
                                j = j3 ^ zza6;
                                j6 = j9;
                            }
                        }
                    }
                    j6 = j7;
                }
                this.zzm = j6;
                return j;
            }
        }
        return zzs();
    }
}
