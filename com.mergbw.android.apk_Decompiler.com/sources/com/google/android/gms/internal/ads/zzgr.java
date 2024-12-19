package com.google.android.gms.internal.ads;

import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgr {
    public static final byte[] zza = {0, 0, 0, 1};
    public static final float[] zzb = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object zzc = new Object();
    private static int[] zzd = new int[10];

    public static int zza(byte[] bArr, int i, int i2, boolean[] zArr) {
        boolean z;
        int i3 = i2 - i;
        boolean z2 = false;
        zzeq.zzf(i3 >= 0);
        if (i3 == 0) {
            return i2;
        }
        if (zArr[0]) {
            zzf(zArr);
            return i - 3;
        } else if (i3 > 1 && zArr[1] && bArr[i] == 1) {
            zzf(zArr);
            return i - 2;
        } else if (i3 <= 2 || !zArr[2] || bArr[i] != 0 || bArr[i + 1] != 1) {
            int i4 = i2 - 1;
            int i5 = i + 2;
            while (i5 < i4) {
                byte b2 = bArr[i5];
                if ((b2 & 254) == 0) {
                    int i6 = i5 - 2;
                    if (bArr[i6] == 0 && bArr[i5 - 1] == 0 && b2 == 1) {
                        zzf(zArr);
                        return i6;
                    }
                    i5 = i6;
                }
                i5 += 3;
            }
            if (i3 <= 2 ? i3 != 2 ? !zArr[1] || bArr[i4] != 1 : !(zArr[2] && bArr[i2 - 2] == 0 && bArr[i4] == 1) : !(bArr[i2 - 3] == 0 && bArr[i2 - 2] == 0 && bArr[i4] == 1)) {
                z = false;
            } else {
                z = true;
            }
            zArr[0] = z;
            zArr[1] = i3 <= 1 ? !(!zArr[2] || bArr[i4] != 0) : bArr[i2 + -2] == 0 && bArr[i4] == 0;
            if (bArr[i4] == 0) {
                z2 = true;
            }
            zArr[2] = z2;
            return i2;
        } else {
            zzf(zArr);
            return i - 1;
        }
    }

    public static zzgo zzc(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int[] iArr;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        zzgs zzgs = new zzgs(bArr, i, i2);
        int i12 = 4;
        zzgs.zze(4);
        int zza2 = zzgs.zza(3);
        zzgs.zzd();
        int zza3 = zzgs.zza(2);
        boolean zzf = zzgs.zzf();
        int zza4 = zzgs.zza(5);
        int i13 = 0;
        for (int i14 = 0; i14 < 32; i14++) {
            if (zzgs.zzf()) {
                i13 |= 1 << i14;
            }
        }
        int i15 = 6;
        int[] iArr2 = new int[6];
        for (int i16 = 0; i16 < 6; i16++) {
            iArr2[i16] = zzgs.zza(8);
        }
        int zza5 = zzgs.zza(8);
        int i17 = 0;
        for (int i18 = 0; i18 < zza2; i18++) {
            if (zzgs.zzf()) {
                i17 += 89;
            }
            if (zzgs.zzf()) {
                i17 += 8;
            }
        }
        zzgs.zze(i17);
        if (zza2 > 0) {
            int i19 = 8 - zza2;
            zzgs.zze(i19 + i19);
        }
        int zzc2 = zzgs.zzc();
        int zzc3 = zzgs.zzc();
        if (zzc3 == 3) {
            zzgs.zzd();
            zzc3 = 3;
        }
        int zzc4 = zzgs.zzc();
        int zzc5 = zzgs.zzc();
        if (zzgs.zzf()) {
            int zzc6 = zzgs.zzc();
            int zzc7 = zzgs.zzc();
            int zzc8 = zzgs.zzc();
            int zzc9 = zzgs.zzc();
            if (zzc3 == 1) {
                i11 = 2;
            } else if (zzc3 == 2) {
                zzc3 = 2;
                i11 = 2;
            } else {
                i11 = 1;
            }
            zzc4 -= i11 * (zzc6 + zzc7);
            zzc5 -= (zzc3 == 1 ? 2 : 1) * (zzc8 + zzc9);
        }
        int i20 = zzc4;
        int i21 = zzc3;
        int i22 = zzc5;
        int i23 = i20;
        int zzc10 = zzgs.zzc();
        int zzc11 = zzgs.zzc();
        int zzc12 = zzgs.zzc();
        for (int i24 = true != zzgs.zzf() ? zza2 : 0; i24 <= zza2; i24++) {
            zzgs.zzc();
            zzgs.zzc();
            zzgs.zzc();
        }
        zzgs.zzc();
        zzgs.zzc();
        zzgs.zzc();
        zzgs.zzc();
        zzgs.zzc();
        zzgs.zzc();
        if (zzgs.zzf() && zzgs.zzf()) {
            int i25 = 0;
            while (i25 < i12) {
                int i26 = 0;
                while (i26 < i15) {
                    if (!zzgs.zzf()) {
                        zzgs.zzc();
                    } else {
                        int min = Math.min(64, 1 << ((i25 + i25) + 4));
                        if (i25 > 1) {
                            zzgs.zzb();
                        }
                        for (int i27 = 0; i27 < min; i27++) {
                            zzgs.zzb();
                        }
                    }
                    i26 += i25 == 3 ? 3 : 1;
                    i15 = 6;
                }
                i25++;
                i12 = 4;
                i15 = 6;
            }
        }
        zzgs.zze(2);
        if (zzgs.zzf()) {
            zzgs.zze(8);
            zzgs.zzc();
            zzgs.zzc();
            zzgs.zzd();
        }
        int zzc13 = zzgs.zzc();
        int i28 = 0;
        int[] iArr3 = new int[0];
        int[] iArr4 = new int[0];
        int i29 = -1;
        int i30 = -1;
        int i31 = -1;
        while (i28 < zzc13) {
            if (i28 == 0 || !zzgs.zzf()) {
                i7 = zzc13;
                i8 = i13;
                i9 = i21;
                iArr = iArr2;
                int zzc14 = zzgs.zzc();
                int zzc15 = zzgs.zzc();
                int[] iArr5 = new int[zzc14];
                int i32 = 0;
                while (i32 < zzc14) {
                    iArr5[i32] = (i32 > 0 ? iArr5[i32 - 1] : 0) - (zzgs.zzc() + 1);
                    zzgs.zzd();
                    i32++;
                }
                int[] iArr6 = new int[zzc15];
                int i33 = 0;
                while (i33 < zzc15) {
                    iArr6[i33] = (i33 > 0 ? iArr6[i33 - 1] : 0) + zzgs.zzc() + 1;
                    zzgs.zzd();
                    i33++;
                }
                i31 = zzc15;
                iArr4 = iArr6;
                i30 = zzc14;
                iArr3 = iArr5;
            } else {
                int i34 = i30 + i31;
                boolean zzf2 = zzgs.zzf();
                int zzc16 = zzgs.zzc() + 1;
                int i35 = 1 - ((zzf2 ? 1 : 0) + zzf2);
                int i36 = i34 + 1;
                i7 = zzc13;
                boolean[] zArr = new boolean[i36];
                iArr = iArr2;
                for (int i37 = 0; i37 <= i34; i37++) {
                    if (!zzgs.zzf()) {
                        zArr[i37] = zzgs.zzf();
                    } else {
                        zArr[i37] = true;
                    }
                }
                int i38 = i31 - 1;
                int[] iArr7 = new int[i36];
                int[] iArr8 = new int[i36];
                int i39 = 0;
                while (true) {
                    i10 = i35 * zzc16;
                    if (i38 < 0) {
                        break;
                    }
                    int i40 = iArr4[i38] + i10;
                    if (i40 < 0 && zArr[i30 + i38]) {
                        iArr7[i39] = i40;
                        i39++;
                    }
                    i38--;
                }
                if (i10 < 0 && zArr[i34]) {
                    iArr7[i39] = i10;
                    i39++;
                }
                i8 = i13;
                i9 = i21;
                int i41 = i39;
                for (int i42 = 0; i42 < i30; i42++) {
                    int i43 = iArr3[i42] + i10;
                    if (i43 < 0 && zArr[i42]) {
                        iArr7[i41] = i43;
                        i41++;
                    }
                }
                int[] copyOf = Arrays.copyOf(iArr7, i41);
                int i44 = 0;
                for (int i45 = i30 - 1; i45 >= 0; i45--) {
                    int i46 = iArr3[i45] + i10;
                    if (i46 > 0 && zArr[i45]) {
                        iArr8[i44] = i46;
                        i44++;
                    }
                }
                if (i10 > 0 && zArr[i34]) {
                    iArr8[i44] = i10;
                    i44++;
                }
                int i47 = i44;
                for (int i48 = 0; i48 < i31; i48++) {
                    int i49 = iArr4[i48] + i10;
                    if (i49 > 0 && zArr[i30 + i48]) {
                        iArr8[i47] = i49;
                        i47++;
                    }
                }
                iArr4 = Arrays.copyOf(iArr8, i47);
                i31 = i47;
                iArr3 = copyOf;
                i30 = i41;
            }
            i28++;
            i21 = i9;
            i13 = i8;
            zzc13 = i7;
            iArr2 = iArr;
        }
        int i50 = i13;
        int i51 = i21;
        int[] iArr9 = iArr2;
        if (zzgs.zzf()) {
            int zzc17 = zzgs.zzc();
            for (int i52 = 0; i52 < zzc17; i52++) {
                zzgs.zze(zzc12 + 5);
            }
        }
        zzgs.zze(2);
        float f = 1.0f;
        if (zzgs.zzf()) {
            if (zzgs.zzf()) {
                int zza6 = zzgs.zza(8);
                if (zza6 == 255) {
                    int zza7 = zzgs.zza(16);
                    int zza8 = zzgs.zza(16);
                    if (!(zza7 == 0 || zza8 == 0)) {
                        f = ((float) zza7) / ((float) zza8);
                    }
                } else if (zza6 < 17) {
                    f = zzb[zza6];
                } else {
                    zzfk.zzf("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + zza6);
                }
            }
            if (zzgs.zzf()) {
                zzgs.zzd();
            }
            if (zzgs.zzf()) {
                zzgs.zze(3);
                int i53 = 1;
                if (true != zzgs.zzf()) {
                    i53 = 2;
                }
                if (zzgs.zzf()) {
                    int zza9 = zzgs.zza(8);
                    int zza10 = zzgs.zza(8);
                    zzgs.zze(8);
                    int zza11 = zzt.zza(zza9);
                    i6 = zzt.zzb(zza10);
                    i4 = zza11;
                } else {
                    i4 = -1;
                    i6 = -1;
                }
                i29 = i53;
            } else {
                i4 = -1;
                i6 = -1;
            }
            if (zzgs.zzf()) {
                zzgs.zzc();
                zzgs.zzc();
            }
            zzgs.zzd();
            if (zzgs.zzf()) {
                i22 += i22;
            }
            i3 = i6;
            i5 = i22;
        } else {
            i5 = i22;
            i4 = -1;
            i3 = -1;
        }
        return new zzgo(zza3, zzf, zza4, i50, i51, zzc10, zzc11, iArr9, zza5, zzc2, i23, i5, f, i4, i29, i3);
    }

    public static zzgp zzd(byte[] bArr, int i, int i2) {
        zzgs zzgs = new zzgs(bArr, 4, i2);
        int zzc2 = zzgs.zzc();
        int zzc3 = zzgs.zzc();
        zzgs.zzd();
        return new zzgp(zzc2, zzc3, zzgs.zzf());
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x01d1  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0117  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x019f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzgq zze(byte[] r26, int r27, int r28) {
        /*
            com.google.android.gms.internal.ads.zzgs r0 = new com.google.android.gms.internal.ads.zzgs
            r1 = r26
            r2 = r27
            r3 = r28
            r0.<init>(r1, r2, r3)
            r1 = 8
            int r2 = r0.zza(r1)
            int r5 = r0.zza(r1)
            int r6 = r0.zza(r1)
            int r7 = r0.zzc()
            r3 = 100
            r8 = 3
            r10 = 1
            if (r2 == r3) goto L_0x004f
            r3 = 110(0x6e, float:1.54E-43)
            if (r2 == r3) goto L_0x004f
            r3 = 122(0x7a, float:1.71E-43)
            if (r2 == r3) goto L_0x004f
            r3 = 244(0xf4, float:3.42E-43)
            if (r2 == r3) goto L_0x004f
            r3 = 44
            if (r2 == r3) goto L_0x004f
            r3 = 83
            if (r2 == r3) goto L_0x004f
            r3 = 86
            if (r2 == r3) goto L_0x004f
            r3 = 118(0x76, float:1.65E-43)
            if (r2 == r3) goto L_0x004f
            r3 = 128(0x80, float:1.794E-43)
            if (r2 == r3) goto L_0x004f
            r3 = 138(0x8a, float:1.93E-43)
            if (r2 != r3) goto L_0x0049
            r2 = r3
            goto L_0x004f
        L_0x0049:
            r3 = r10
            r12 = 0
            r13 = 0
            r14 = 0
            goto L_0x00ab
        L_0x004f:
            int r3 = r0.zzc()
            if (r3 != r8) goto L_0x005b
            boolean r11 = r0.zzf()
            r12 = r8
            goto L_0x005d
        L_0x005b:
            r12 = r3
            r11 = 0
        L_0x005d:
            int r13 = r0.zzc()
            int r14 = r0.zzc()
            r0.zzd()
            boolean r15 = r0.zzf()
            if (r15 == 0) goto L_0x00a8
            if (r12 == r8) goto L_0x0072
            r12 = r1
            goto L_0x0074
        L_0x0072:
            r12 = 12
        L_0x0074:
            r15 = 0
        L_0x0075:
            if (r15 >= r12) goto L_0x00a8
            boolean r16 = r0.zzf()
            if (r16 == 0) goto L_0x00a2
            r9 = 6
            if (r15 >= r9) goto L_0x0083
            r9 = 16
            goto L_0x0085
        L_0x0083:
            r9 = 64
        L_0x0085:
            r16 = r1
            r17 = r16
            r1 = 0
        L_0x008a:
            if (r1 >= r9) goto L_0x00a2
            if (r16 == 0) goto L_0x009a
            int r16 = r0.zzb()
            int r8 = r17 + r16
            int r8 = r8 + 256
            int r8 = r8 % 256
            r16 = r8
        L_0x009a:
            if (r16 == 0) goto L_0x009e
            r17 = r16
        L_0x009e:
            int r1 = r1 + 1
            r8 = 3
            goto L_0x008a
        L_0x00a2:
            int r15 = r15 + 1
            r1 = 8
            r8 = 3
            goto L_0x0075
        L_0x00a8:
            r12 = r13
            r13 = r14
            r14 = r11
        L_0x00ab:
            int r1 = r0.zzc()
            int r16 = r1 + 4
            int r1 = r0.zzc()
            if (r1 != 0) goto L_0x00c3
            int r8 = r0.zzc()
            int r8 = r8 + 4
            r15 = r5
            r18 = r8
        L_0x00c0:
            r19 = 0
            goto L_0x00eb
        L_0x00c3:
            if (r1 != r10) goto L_0x00e7
            boolean r1 = r0.zzf()
            r0.zzb()
            r0.zzb()
            int r8 = r0.zzc()
            long r8 = (long) r8
            r15 = r5
            r11 = 0
        L_0x00d6:
            long r4 = (long) r11
            int r4 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r4 >= 0) goto L_0x00e1
            r0.zzc()
            int r11 = r11 + 1
            goto L_0x00d6
        L_0x00e1:
            r19 = r1
            r1 = r10
            r18 = 0
            goto L_0x00eb
        L_0x00e7:
            r15 = r5
            r18 = 0
            goto L_0x00c0
        L_0x00eb:
            int r8 = r0.zzc()
            r0.zzd()
            int r4 = r0.zzc()
            int r4 = r4 + r10
            int r5 = r0.zzc()
            int r5 = r5 + r10
            boolean r20 = r0.zzf()
            int r9 = 2 - r20
            if (r20 != 0) goto L_0x0107
            r0.zzd()
        L_0x0107:
            int r5 = r5 * r9
            r0.zzd()
            r11 = 16
            int r4 = r4 * r11
            int r5 = r5 * r11
            boolean r11 = r0.zzf()
            r21 = 2
            if (r11 == 0) goto L_0x0147
            int r11 = r0.zzc()
            int r22 = r0.zzc()
            int r23 = r0.zzc()
            int r24 = r0.zzc()
            if (r3 != 0) goto L_0x012a
            goto L_0x013d
        L_0x012a:
            r10 = 3
            if (r3 != r10) goto L_0x0131
            r10 = 1
            r25 = 1
            goto L_0x0134
        L_0x0131:
            r25 = r21
            r10 = 1
        L_0x0134:
            if (r3 != r10) goto L_0x0139
            r10 = r21
            goto L_0x013a
        L_0x0139:
            r10 = 1
        L_0x013a:
            int r9 = r9 * r10
            r10 = r25
        L_0x013d:
            int r11 = r11 + r22
            int r11 = r11 * r10
            int r4 = r4 - r11
            int r23 = r23 + r24
            int r23 = r23 * r9
            int r5 = r5 - r23
        L_0x0147:
            r9 = r4
            r10 = r5
            boolean r3 = r0.zzf()
            r5 = -1
            if (r3 == 0) goto L_0x01d8
            boolean r3 = r0.zzf()
            if (r3 == 0) goto L_0x018e
            r3 = 8
            int r11 = r0.zza(r3)
            r3 = 255(0xff, float:3.57E-43)
            if (r11 != r3) goto L_0x0172
            r3 = 16
            int r11 = r0.zza(r3)
            int r3 = r0.zza(r3)
            if (r11 == 0) goto L_0x018e
            if (r3 == 0) goto L_0x018e
            float r4 = (float) r11
            float r3 = (float) r3
            float r4 = r4 / r3
            goto L_0x0190
        L_0x0172:
            r3 = 17
            if (r11 >= r3) goto L_0x017b
            float[] r3 = zzb
            r4 = r3[r11]
            goto L_0x0190
        L_0x017b:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unexpected aspect_ratio_idc value: "
            r3.<init>(r4)
            r3.append(r11)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "NalUnitUtil"
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r3)
        L_0x018e:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x0190:
            boolean r3 = r0.zzf()
            if (r3 == 0) goto L_0x0199
            r0.zzd()
        L_0x0199:
            boolean r3 = r0.zzf()
            if (r3 == 0) goto L_0x01d1
            r3 = 3
            r0.zze(r3)
            boolean r3 = r0.zzf()
            r11 = 1
            if (r11 == r3) goto L_0x01ab
            goto L_0x01ad
        L_0x01ab:
            r21 = r11
        L_0x01ad:
            boolean r3 = r0.zzf()
            if (r3 == 0) goto L_0x01cc
            r3 = 8
            int r5 = r0.zza(r3)
            int r11 = r0.zza(r3)
            r0.zze(r3)
            int r0 = com.google.android.gms.internal.ads.zzt.zza(r5)
            int r3 = com.google.android.gms.internal.ads.zzt.zzb(r11)
            r22 = r3
            r11 = r4
            goto L_0x01df
        L_0x01cc:
            r11 = r4
            r0 = r5
            r22 = r0
            goto L_0x01df
        L_0x01d1:
            r11 = r4
            r0 = r5
            r21 = r0
            r22 = r21
            goto L_0x01df
        L_0x01d8:
            r0 = r5
            r21 = r0
            r22 = r21
            r11 = 1065353216(0x3f800000, float:1.0)
        L_0x01df:
            com.google.android.gms.internal.ads.zzgq r23 = new com.google.android.gms.internal.ads.zzgq
            r3 = r23
            r4 = r2
            r5 = r15
            r15 = r20
            r17 = r1
            r20 = r0
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
            return r23
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgr.zze(byte[], int, int):com.google.android.gms.internal.ads.zzgq");
    }

    public static void zzf(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    public static int zzb(byte[] bArr, int i) {
        int i2;
        synchronized (zzc) {
            int i3 = 0;
            int i4 = 0;
            while (i3 < i) {
                while (true) {
                    if (i3 >= i - 2) {
                        i3 = i;
                        break;
                    }
                    int i5 = i3 + 1;
                    if (bArr[i3] == 0 && bArr[i5] == 0 && bArr[i3 + 2] == 3) {
                        break;
                    }
                    i3 = i5;
                }
                if (i3 < i) {
                    int[] iArr = zzd;
                    int length = iArr.length;
                    if (length <= i4) {
                        zzd = Arrays.copyOf(iArr, length + length);
                    }
                    zzd[i4] = i3;
                    i3 += 3;
                    i4++;
                }
            }
            i2 = i - i4;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            while (i6 < i4) {
                try {
                    int i9 = zzd[i6] - i7;
                    System.arraycopy(bArr, i7, bArr, i8, i9);
                    int i10 = i8 + i9;
                    int i11 = i10 + 1;
                    bArr[i10] = 0;
                    i8 = i10 + 2;
                    bArr[i11] = 0;
                    i7 += i9 + 3;
                    i6++;
                } catch (Throwable th) {
                    throw th;
                }
            }
            System.arraycopy(bArr, i7, bArr, i8, i2 - i8);
        }
        return i2;
    }
}
