package com.google.android.gms.internal.ads;

import com.google.common.base.Ascii;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzgzo {
    static int zza(byte[] bArr, int i, zzgzn zzgzn) throws zzhcd {
        int zzh = zzh(bArr, i, zzgzn);
        int i2 = zzgzn.zza;
        if (i2 < 0) {
            throw zzhcd.zzf();
        } else if (i2 > bArr.length - zzh) {
            throw zzhcd.zzj();
        } else if (i2 == 0) {
            zzgzn.zzc = zzhac.zzb;
            return zzh;
        } else {
            zzgzn.zzc = zzhac.zzv(bArr, zzh, i2);
            return zzh + i2;
        }
    }

    static int zzb(byte[] bArr, int i) {
        int i2 = (bArr[i + 1] & 255) << 8;
        return ((bArr[i + 3] & 255) << Ascii.CAN) | i2 | (bArr[i] & 255) | ((bArr[i + 2] & 255) << 16);
    }

    static int zzc(zzhdz zzhdz, byte[] bArr, int i, int i2, int i3, zzgzn zzgzn) throws IOException {
        Object zze = zzhdz.zze();
        int zzl = zzl(zze, zzhdz, bArr, i, i2, i3, zzgzn);
        zzhdz.zzf(zze);
        zzgzn.zzc = zze;
        return zzl;
    }

    static int zzd(zzhdz zzhdz, byte[] bArr, int i, int i2, zzgzn zzgzn) throws IOException {
        Object zze = zzhdz.zze();
        int zzm = zzm(zze, zzhdz, bArr, i, i2, zzgzn);
        zzhdz.zzf(zze);
        zzgzn.zzc = zze;
        return zzm;
    }

    static int zze(zzhdz zzhdz, int i, byte[] bArr, int i2, int i3, zzhca zzhca, zzgzn zzgzn) throws IOException {
        int zzd = zzd(zzhdz, bArr, i2, i3, zzgzn);
        zzhca.add(zzgzn.zzc);
        while (zzd < i3) {
            int zzh = zzh(bArr, zzd, zzgzn);
            if (i != zzgzn.zza) {
                break;
            }
            zzd = zzd(zzhdz, bArr, zzh, i3, zzgzn);
            zzhca.add(zzgzn.zzc);
        }
        return zzd;
    }

    static int zzf(byte[] bArr, int i, zzhca zzhca, zzgzn zzgzn) throws IOException {
        zzhbp zzhbp = (zzhbp) zzhca;
        int zzh = zzh(bArr, i, zzgzn);
        int i2 = zzgzn.zza + zzh;
        while (zzh < i2) {
            zzh = zzh(bArr, zzh, zzgzn);
            zzhbp.zzi(zzgzn.zza);
        }
        if (zzh == i2) {
            return zzh;
        }
        throw zzhcd.zzj();
    }

    static int zzg(int i, byte[] bArr, int i2, int i3, zzher zzher, zzgzn zzgzn) throws zzhcd {
        if ((i >>> 3) != 0) {
            int i4 = i & 7;
            if (i4 == 0) {
                int zzk = zzk(bArr, i2, zzgzn);
                zzher.zzj(i, Long.valueOf(zzgzn.zzb));
                return zzk;
            } else if (i4 == 1) {
                zzher.zzj(i, Long.valueOf(zzn(bArr, i2)));
                return i2 + 8;
            } else if (i4 == 2) {
                int zzh = zzh(bArr, i2, zzgzn);
                int i5 = zzgzn.zza;
                if (i5 < 0) {
                    throw zzhcd.zzf();
                } else if (i5 <= bArr.length - zzh) {
                    if (i5 == 0) {
                        zzher.zzj(i, zzhac.zzb);
                    } else {
                        zzher.zzj(i, zzhac.zzv(bArr, zzh, i5));
                    }
                    return zzh + i5;
                } else {
                    throw zzhcd.zzj();
                }
            } else if (i4 == 3) {
                int i6 = (i & -8) | 4;
                zzher zzf = zzher.zzf();
                int i7 = 0;
                while (true) {
                    if (i2 >= i3) {
                        break;
                    }
                    int zzh2 = zzh(bArr, i2, zzgzn);
                    int i8 = zzgzn.zza;
                    i7 = i8;
                    if (i8 == i6) {
                        i2 = zzh2;
                        break;
                    }
                    int zzg = zzg(i7, bArr, zzh2, i3, zzf, zzgzn);
                    i7 = i8;
                    i2 = zzg;
                }
                if (i2 > i3 || i7 != i6) {
                    throw zzhcd.zzg();
                }
                zzher.zzj(i, zzf);
                return i2;
            } else if (i4 == 5) {
                zzher.zzj(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            } else {
                throw zzhcd.zzc();
            }
        } else {
            throw zzhcd.zzc();
        }
    }

    static int zzh(byte[] bArr, int i, zzgzn zzgzn) {
        int i2 = i + 1;
        byte b2 = bArr[i];
        if (b2 < 0) {
            return zzi(b2, bArr, i2, zzgzn);
        }
        zzgzn.zza = b2;
        return i2;
    }

    static int zzi(int i, byte[] bArr, int i2, zzgzn zzgzn) {
        byte b2 = bArr[i2];
        int i3 = i2 + 1;
        int i4 = i & 127;
        if (b2 >= 0) {
            zzgzn.zza = i4 | (b2 << 7);
            return i3;
        }
        int i5 = i4 | ((b2 & Byte.MAX_VALUE) << 7);
        int i6 = i2 + 2;
        byte b3 = bArr[i3];
        if (b3 >= 0) {
            zzgzn.zza = i5 | (b3 << 14);
            return i6;
        }
        int i7 = i5 | ((b3 & Byte.MAX_VALUE) << 14);
        int i8 = i2 + 3;
        byte b4 = bArr[i6];
        if (b4 >= 0) {
            zzgzn.zza = i7 | (b4 << Ascii.NAK);
            return i8;
        }
        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << Ascii.NAK);
        int i10 = i2 + 4;
        byte b5 = bArr[i8];
        if (b5 >= 0) {
            zzgzn.zza = i9 | (b5 << Ascii.FS);
            return i10;
        }
        int i11 = i9 | ((b5 & Byte.MAX_VALUE) << Ascii.FS);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] < 0) {
                i10 = i12;
            } else {
                zzgzn.zza = i11;
                return i12;
            }
        }
    }

    static int zzj(int i, byte[] bArr, int i2, int i3, zzhca zzhca, zzgzn zzgzn) {
        zzhbp zzhbp = (zzhbp) zzhca;
        int zzh = zzh(bArr, i2, zzgzn);
        zzhbp.zzi(zzgzn.zza);
        while (zzh < i3) {
            int zzh2 = zzh(bArr, zzh, zzgzn);
            if (i != zzgzn.zza) {
                break;
            }
            zzh = zzh(bArr, zzh2, zzgzn);
            zzhbp.zzi(zzgzn.zza);
        }
        return zzh;
    }

    static int zzk(byte[] bArr, int i, zzgzn zzgzn) {
        long j = (long) bArr[i];
        int i2 = i + 1;
        if (j >= 0) {
            zzgzn.zzb = j;
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
        zzgzn.zzb = j2;
        return i3;
    }

    static int zzl(Object obj, zzhdz zzhdz, byte[] bArr, int i, int i2, int i3, zzgzn zzgzn) throws IOException {
        int zzc = ((zzhdh) zzhdz).zzc(obj, bArr, i, i2, i3, zzgzn);
        zzgzn.zzc = obj;
        return zzc;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: byte} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int zzm(java.lang.Object r6, com.google.android.gms.internal.ads.zzhdz r7, byte[] r8, int r9, int r10, com.google.android.gms.internal.ads.zzgzn r11) throws java.io.IOException {
        /*
            int r0 = r9 + 1
            byte r9 = r8[r9]
            if (r9 >= 0) goto L_0x000c
            int r0 = zzi(r9, r8, r0, r11)
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
            r0.zzi(r1, r2, r3, r4, r5)
            r11.zzc = r6
            return r9
        L_0x001e:
            com.google.android.gms.internal.ads.zzhcd r6 = com.google.android.gms.internal.ads.zzhcd.zzj()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgzo.zzm(java.lang.Object, com.google.android.gms.internal.ads.zzhdz, byte[], int, int, com.google.android.gms.internal.ads.zzgzn):int");
    }

    static long zzn(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }
}
