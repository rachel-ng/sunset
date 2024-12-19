package com.google.android.gms.internal.measurement;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzhw {
    static double zza(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzd(bArr, i));
    }

    static float zzb(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzc(bArr, i));
    }

    static int zza(byte[] bArr, int i, zzhv zzhv) throws zzjs {
        int zzc = zzc(bArr, i, zzhv);
        int i2 = zzhv.zza;
        if (i2 < 0) {
            throw zzjs.zzf();
        } else if (i2 > bArr.length - zzc) {
            throw zzjs.zzh();
        } else if (i2 == 0) {
            zzhv.zzc = zzia.zza;
            return zzc;
        } else {
            zzhv.zzc = zzia.zza(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zzc(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << Ascii.CAN) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int zza(zzll zzll, byte[] bArr, int i, int i2, int i3, zzhv zzhv) throws IOException {
        Object zza = zzll.zza();
        int zza2 = zza(zza, zzll, bArr, i, i2, i3, zzhv);
        zzll.zzc(zza);
        zzhv.zzc = zza;
        return zza2;
    }

    static int zza(zzll zzll, byte[] bArr, int i, int i2, zzhv zzhv) throws IOException {
        Object zza = zzll.zza();
        int zza2 = zza(zza, zzll, bArr, i, i2, zzhv);
        zzll.zzc(zza);
        zzhv.zzc = zza;
        return zza2;
    }

    static int zza(zzll<?> zzll, int i, byte[] bArr, int i2, int i3, zzjt<?> zzjt, zzhv zzhv) throws IOException {
        int zza = zza((zzll) zzll, bArr, i2, i3, zzhv);
        zzjt.add(zzhv.zzc);
        while (zza < i3) {
            int zzc = zzc(bArr, zza, zzhv);
            if (i != zzhv.zza) {
                break;
            }
            zza = zza((zzll) zzll, bArr, zzc, i3, zzhv);
            zzjt.add(zzhv.zzc);
        }
        return zza;
    }

    static int zza(byte[] bArr, int i, zzjt<?> zzjt, zzhv zzhv) throws IOException {
        zzjn zzjn = (zzjn) zzjt;
        int zzc = zzc(bArr, i, zzhv);
        int i2 = zzhv.zza + zzc;
        while (zzc < i2) {
            zzc = zzc(bArr, zzc, zzhv);
            zzjn.zzd(zzhv.zza);
        }
        if (zzc == i2) {
            return zzc;
        }
        throw zzjs.zzh();
    }

    static int zzb(byte[] bArr, int i, zzhv zzhv) throws zzjs {
        int zzc = zzc(bArr, i, zzhv);
        int i2 = zzhv.zza;
        if (i2 < 0) {
            throw zzjs.zzf();
        } else if (i2 == 0) {
            zzhv.zzc = "";
            return zzc;
        } else {
            zzhv.zzc = zzmk.zzb(bArr, zzc, i2);
            return zzc + i2;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzme zzme, zzhv zzhv) throws zzjs {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzd = zzd(bArr, i2, zzhv);
                zzme.zza(i, (Object) Long.valueOf(zzhv.zzb));
                return zzd;
            } else if (i4 == 1) {
                zzme.zza(i, (Object) Long.valueOf(zzd(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzc = zzc(bArr, i2, zzhv);
                int i5 = zzhv.zza;
                if (i5 < 0) {
                    throw zzjs.zzf();
                } else if (i5 <= bArr.length - zzc) {
                    if (i5 == 0) {
                        zzme.zza(i, (Object) zzia.zza);
                    } else {
                        zzme.zza(i, (Object) zzia.zza(bArr, zzc, i5));
                    }
                    return zzc + i5;
                } else {
                    throw zzjs.zzh();
                }
            } else if (i4 == 3) {
                zzme zzd2 = zzme.zzd();
                int i6 = (i & -8) | 4;
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzc2 = zzc(bArr, i2, zzhv);
                    int i8 = zzhv.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzc2;
                        break;
                    }
                    int zza = zza(i7, bArr, zzc2, i3, zzd2, zzhv);
                    i7 = i8;
                    i2 = zza;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzjs.zzg();
                }
                zzme.zza(i, (Object) zzd2);
                return i2;
            } else if (i4 == 5) {
                zzme.zza(i, (Object) Integer.valueOf(zzc(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzjs.zzc();
            }
        } else {
            throw zzjs.zzc();
        }
    }

    static int zzc(byte[] bArr, int i, zzhv zzhv) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return zza((int) b2, bArr, i2, zzhv);
        }
        zzhv.zza = b2;
        return i2;
    }

    static int zza(int i, byte[] bArr, int i2, zzhv zzhv) {
        int i3 = i & 127;
        int i4 = i2 + 1;
        byte b2 = bArr[i2];
        if (b2 >= 0) {
            zzhv.zza = i3 | (b2 << 7);
            return i4;
        }
        int i5 = i3 | ((b2 & Byte.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b3 = bArr[i4];
        if (b3 >= 0) {
            zzhv.zza = i5 | (b3 << 14);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzhv.zza = i7 | (b4 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i2 + 4;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzhv.zza = i9 | (b5 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzhv.zza = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzjt<?> zzjt, zzhv zzhv) {
        zzjn zzjn = (zzjn) zzjt;
        int zzc = zzc(bArr, i2, zzhv);
        zzjn.zzd(zzhv.zza);
        while (zzc < i3) {
            int zzc2 = zzc(bArr, zzc, zzhv);
            if (i != zzhv.zza) {
                break;
            }
            zzc = zzc(bArr, zzc2, zzhv);
            zzjn.zzd(zzhv.zza);
        }
        return zzc;
    }

    static int zzd(byte[] bArr, int i, zzhv zzhv) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            zzhv.zzb = j;
            return i2;
        }
        int i3 = i + 2;
        byte b2 = bArr[i2];
        long j2 = (j & 127) | (((long) (b2 & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b2 < 0) {
            int i5 = i3 + 1;
            byte b3 = bArr[i3];
            i4 += 7;
            j2 |= ((long) (b3 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b2 = b3;
            i3 = i6;
        }
        zzhv.zzb = j2;
        return i3;
    }

    static int zza(Object obj, zzll zzll, byte[] bArr, int i, int i2, int i3, zzhv zzhv) throws IOException {
        int zza = ((zzkx) zzll).zza(obj, bArr, i, i2, i3, zzhv);
        zzhv.zzc = obj;
        return zza;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zza(java.lang.Object r6, com.google.android.gms.internal.measurement.zzll r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.measurement.zzhv r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zza((int) r9, (byte[]) r8, (int) r0, (com.google.android.gms.internal.measurement.zzhv) r11)
            int r9 = r11.zza
        L_0x000c:
            r3 = r0
            if (r9 < 0) goto L_0x001e
            int r10 = r10 - r3
            if (r9 > r10) goto L_0x001e
            int r9 = r9 + r3
            r0 = r7
            r1 = r6
            r2 = r8
            r4 = r9
            r5 = r11
            r0.zza(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.measurement.zzjs r6 = com.google.android.gms.internal.measurement.zzjs.zzh()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhw.zza(java.lang.Object, com.google.android.gms.internal.measurement.zzll, byte[], int, int, com.google.android.gms.internal.measurement.zzhv):int");
    }

    static int zza(int i, byte[] bArr, int i2, int i3, zzhv zzhv) throws zzjs {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                return zzd(bArr, i2, zzhv);
            }
            if (i4 == 1) {
                return i2 + 8;
            }
            if (i4 == 2) {
                return zzc(bArr, i2, zzhv) + zzhv.zza;
            }
            if (i4 == 3) {
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = zzc(bArr, i2, zzhv);
                    i6 = zzhv.zza;
                    if (i6 == i5) {
                        break;
                    }
                    i2 = zza(i6, bArr, i2, i3, zzhv);
                }
                if (i2 <= i3 && i6 == i5) {
                    return i2;
                }
                throw zzjs.zzg();
            } else if (i4 == 5) {
                return i2 + 4;
            } else {
                throw zzjs.zzc();
            }
        } else {
            throw zzjs.zzc();
        }
    }

    static long zzd(byte[] bArr, int i) {
        return ((((long) bArr[i + 7]) & 255) << 56) | (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48);
    }
}
