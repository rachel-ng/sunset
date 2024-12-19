package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzin extends zzio {
    private final byte[] zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;

    private final byte zzv() throws IOException {
        int i = this.zzg;
        if (i != this.zze) {
            byte[] bArr = this.zzd;
            this.zzg = i + 1;
            return bArr[i];
        }
        throw zzjs.zzh();
    }

    public final double zza() throws IOException {
        return Double.longBitsToDouble(zzy());
    }

    public final float zzb() throws IOException {
        return Float.intBitsToFloat(zzw());
    }

    public final int zzc() {
        return this.zzg - this.zzh;
    }

    public final int zza(int i) throws zzjs {
        if (i >= 0) {
            int zzc = i + zzc();
            if (zzc >= 0) {
                int i2 = this.zzj;
                if (zzc <= i2) {
                    this.zzj = zzc;
                    zzaa();
                    return i2;
                }
                throw zzjs.zzh();
            }
            throw zzjs.zzg();
        }
        throw zzjs.zzf();
    }

    public final int zzd() throws IOException {
        return zzx();
    }

    public final int zze() throws IOException {
        return zzw();
    }

    public final int zzf() throws IOException {
        return zzx();
    }

    private final int zzw() throws IOException {
        int i = this.zzg;
        if (this.zze - i >= 4) {
            byte[] bArr = this.zzd;
            this.zzg = i + 4;
            return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }
        throw zzjs.zzh();
    }

    private final int zzx() throws IOException {
        byte b2;
        byte b3;
        int i = this.zzg;
        int i2 = this.zze;
        if (i2 != i) {
            byte[] bArr = this.zzd;
            int i3 = i + 1;
            byte b4 = bArr[i];
            if (b4 >= 0) {
                this.zzg = i3;
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
                this.zzg = i4;
                return b2;
            }
        }
        return (int) zzm();
    }

    public final int zzg() throws IOException {
        return zzw();
    }

    public final int zzh() throws IOException {
        return zze(zzx());
    }

    public final int zzi() throws IOException {
        if (zzt()) {
            this.zzi = 0;
            return 0;
        }
        int zzx = zzx();
        this.zzi = zzx;
        if ((zzx >>> 3) != 0) {
            return zzx;
        }
        throw zzjs.zzc();
    }

    public final int zzj() throws IOException {
        return zzx();
    }

    public final long zzk() throws IOException {
        return zzy();
    }

    public final long zzl() throws IOException {
        return zzz();
    }

    private final long zzy() throws IOException {
        int i = this.zzg;
        if (this.zze - i >= 8) {
            byte[] bArr = this.zzd;
            this.zzg = i + 8;
            return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
        }
        throw zzjs.zzh();
    }

    private final long zzz() throws IOException {
        long j;
        long j2;
        long j3;
        int i = this.zzg;
        int i2 = this.zze;
        if (i2 != i) {
            byte[] bArr = this.zzd;
            int i3 = i + 1;
            byte b2 = bArr[i];
            if (b2 >= 0) {
                this.zzg = i3;
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
                        i4 = i5;
                    } else {
                        int i6 = i + 4;
                        byte b5 = b4 ^ (bArr[i5] << Ascii.NAK);
                        if (b5 < 0) {
                            i4 = i6;
                            j = (long) (-2080896 ^ b5);
                        } else {
                            long j4 = (long) b5;
                            int i7 = i + 5;
                            long j5 = j4 ^ (((long) bArr[i6]) << 28);
                            if (j5 >= 0) {
                                j3 = 266354560;
                            } else {
                                int i8 = i + 6;
                                long j6 = j5 ^ (((long) bArr[i7]) << 35);
                                if (j6 < 0) {
                                    j2 = -34093383808L;
                                } else {
                                    i7 = i + 7;
                                    j5 = j6 ^ (((long) bArr[i8]) << 42);
                                    if (j5 >= 0) {
                                        j3 = 4363953127296L;
                                    } else {
                                        i8 = i + 8;
                                        j6 = j5 ^ (((long) bArr[i7]) << 49);
                                        if (j6 < 0) {
                                            j2 = -558586000294016L;
                                        } else {
                                            i4 = i + 9;
                                            long j7 = (j6 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                            if (j7 < 0) {
                                                int i9 = i + 10;
                                                if (((long) bArr[i4]) >= 0) {
                                                    i4 = i9;
                                                }
                                            }
                                            j = j7;
                                        }
                                    }
                                }
                                j = j6 ^ j2;
                                i4 = i8;
                            }
                            j = j5 ^ j3;
                        }
                    }
                }
                this.zzg = i4;
                return j;
            }
        }
        return zzm();
    }

    /* access modifiers changed from: package-private */
    public final long zzm() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzv = zzv();
            j |= ((long) (zzv & Byte.MAX_VALUE)) << i;
            if ((zzv & 128) == 0) {
                return j;
            }
        }
        throw zzjs.zze();
    }

    public final long zzn() throws IOException {
        return zzy();
    }

    public final long zzo() throws IOException {
        return zza(zzz());
    }

    public final long zzp() throws IOException {
        return zzz();
    }

    public final zzia zzq() throws IOException {
        byte[] bArr;
        int zzx = zzx();
        if (zzx > 0) {
            int i = this.zze;
            int i2 = this.zzg;
            if (zzx <= i - i2) {
                zzia zza = zzia.zza(this.zzd, i2, zzx);
                this.zzg += zzx;
                return zza;
            }
        }
        if (zzx == 0) {
            return zzia.zza;
        }
        if (zzx > 0) {
            int i3 = this.zze;
            int i4 = this.zzg;
            if (zzx <= i3 - i4) {
                int i5 = zzx + i4;
                this.zzg = i5;
                bArr = Arrays.copyOfRange(this.zzd, i4, i5);
                return zzia.zzb(bArr);
            }
        }
        if (zzx > 0) {
            throw zzjs.zzh();
        } else if (zzx == 0) {
            bArr = zzjm.zzb;
            return zzia.zzb(bArr);
        } else {
            throw zzjs.zzf();
        }
    }

    public final String zzr() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i = this.zze;
            int i2 = this.zzg;
            if (zzx <= i - i2) {
                String str = new String(this.zzd, i2, zzx, zzjm.zza);
                this.zzg += zzx;
                return str;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx < 0) {
            throw zzjs.zzf();
        }
        throw zzjs.zzh();
    }

    public final String zzs() throws IOException {
        int zzx = zzx();
        if (zzx > 0) {
            int i = this.zze;
            int i2 = this.zzg;
            if (zzx <= i - i2) {
                String zzb = zzmk.zzb(this.zzd, i2, zzx);
                this.zzg += zzx;
                return zzb;
            }
        }
        if (zzx == 0) {
            return "";
        }
        if (zzx <= 0) {
            throw zzjs.zzf();
        }
        throw zzjs.zzh();
    }

    private zzin(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzj = Integer.MAX_VALUE;
        this.zzd = bArr;
        this.zze = i2 + i;
        this.zzg = i;
        this.zzh = i;
    }

    public final void zzb(int i) throws zzjs {
        if (this.zzi != i) {
            throw zzjs.zzb();
        }
    }

    public final void zzc(int i) {
        this.zzj = i;
        zzaa();
    }

    private final void zzaa() {
        int i = this.zze + this.zzf;
        this.zze = i;
        int i2 = i - this.zzh;
        int i3 = this.zzj;
        if (i2 > i3) {
            int i4 = i2 - i3;
            this.zzf = i4;
            this.zze = i - i4;
            return;
        }
        this.zzf = 0;
    }

    private final void zzf(int i) throws IOException {
        if (i >= 0) {
            int i2 = this.zze;
            int i3 = this.zzg;
            if (i <= i2 - i3) {
                this.zzg = i3 + i;
                return;
            }
        }
        if (i < 0) {
            throw zzjs.zzf();
        }
        throw zzjs.zzh();
    }

    public final boolean zzt() throws IOException {
        return this.zzg == this.zze;
    }

    public final boolean zzu() throws IOException {
        return zzz() != 0;
    }

    public final boolean zzd(int i) throws IOException {
        int zzi2;
        int i2 = i & 7;
        int i3 = 0;
        if (i2 == 0) {
            if (this.zze - this.zzg >= 10) {
                while (i3 < 10) {
                    byte[] bArr = this.zzd;
                    int i4 = this.zzg;
                    this.zzg = i4 + 1;
                    if (bArr[i4] < 0) {
                        i3++;
                    }
                }
                throw zzjs.zze();
            }
            while (i3 < 10) {
                if (zzv() < 0) {
                    i3++;
                }
            }
            throw zzjs.zze();
            return true;
        } else if (i2 == 1) {
            zzf(8);
            return true;
        } else if (i2 == 2) {
            zzf(zzx());
            return true;
        } else if (i2 == 3) {
            do {
                zzi2 = zzi();
                if (zzi2 == 0 || !zzd(zzi2)) {
                    zzb(((i >>> 3) << 3) | 4);
                }
                zzi2 = zzi();
                break;
            } while (!zzd(zzi2));
            zzb(((i >>> 3) << 3) | 4);
            return true;
        } else if (i2 == 4) {
            return false;
        } else {
            if (i2 == 5) {
                zzf(4);
                return true;
            }
            throw zzjs.zza();
        }
    }
}
