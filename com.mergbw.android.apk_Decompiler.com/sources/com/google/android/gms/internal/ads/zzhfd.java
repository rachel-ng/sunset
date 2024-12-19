package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
final class zzhfd extends zzhfc {
    zzhfd() {
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
        if (r13[r14] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0080, code lost:
        if (r13[r14] <= -65) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r13[r14] <= -65) goto L_0x001e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(int r12, byte[] r13, int r14, int r15) {
        /*
            r11 = this;
            r0 = -19
            r1 = -62
            r2 = -16
            r3 = 0
            r4 = -96
            r5 = -32
            r6 = -65
            r7 = -1
            if (r12 == 0) goto L_0x0084
            if (r14 < r15) goto L_0x0013
            return r12
        L_0x0013:
            byte r8 = (byte) r12
            if (r8 >= r5) goto L_0x0022
            if (r8 < r1) goto L_0x0021
            int r12 = r14 + 1
            byte r14 = r13[r14]
            if (r14 > r6) goto L_0x0021
        L_0x001e:
            r14 = r12
            goto L_0x0084
        L_0x0021:
            return r7
        L_0x0022:
            int r9 = r12 >> 8
            int r9 = ~r9
            if (r8 >= r2) goto L_0x004b
            byte r12 = (byte) r9
            if (r12 != 0) goto L_0x0039
            int r12 = r14 + 1
            byte r14 = r13[r14]
            if (r12 >= r15) goto L_0x0034
            r10 = r14
            r14 = r12
            r12 = r10
            goto L_0x0039
        L_0x0034:
            int r12 = com.google.android.gms.internal.ads.zzhff.zzk(r8, r14)
            return r12
        L_0x0039:
            if (r12 > r6) goto L_0x004a
            if (r8 != r5) goto L_0x003f
            if (r12 < r4) goto L_0x004a
        L_0x003f:
            if (r8 != r0) goto L_0x0043
            if (r12 >= r4) goto L_0x004a
        L_0x0043:
            int r12 = r14 + 1
            byte r14 = r13[r14]
            if (r14 > r6) goto L_0x004a
            goto L_0x001e
        L_0x004a:
            return r7
        L_0x004b:
            byte r9 = (byte) r9
            if (r9 != 0) goto L_0x005c
            int r12 = r14 + 1
            byte r9 = r13[r14]
            if (r12 >= r15) goto L_0x0057
            r14 = r12
            r12 = r3
            goto L_0x005e
        L_0x0057:
            int r12 = com.google.android.gms.internal.ads.zzhff.zzk(r8, r9)
            return r12
        L_0x005c:
            int r12 = r12 >> 16
        L_0x005e:
            if (r12 != 0) goto L_0x006f
            int r12 = r14 + 1
            byte r14 = r13[r14]
            if (r12 >= r15) goto L_0x006a
            r10 = r14
            r14 = r12
            r12 = r10
            goto L_0x006f
        L_0x006a:
            int r12 = com.google.android.gms.internal.ads.zzhff.zzl(r8, r9, r14)
            return r12
        L_0x006f:
            if (r9 > r6) goto L_0x0083
            int r8 = r8 << 28
            int r9 = r9 + 112
            int r8 = r8 + r9
            int r8 = r8 >> 30
            if (r8 != 0) goto L_0x0083
            if (r12 > r6) goto L_0x0083
            int r12 = r14 + 1
            byte r14 = r13[r14]
            if (r14 > r6) goto L_0x0083
            goto L_0x001e
        L_0x0083:
            return r7
        L_0x0084:
            if (r14 >= r15) goto L_0x008d
            byte r12 = r13[r14]
            if (r12 < 0) goto L_0x008d
            int r14 = r14 + 1
            goto L_0x0084
        L_0x008d:
            if (r14 < r15) goto L_0x0091
            goto L_0x00f0
        L_0x0091:
            if (r14 < r15) goto L_0x0095
            goto L_0x00f0
        L_0x0095:
            int r12 = r14 + 1
            byte r8 = r13[r14]
            if (r8 >= 0) goto L_0x00f1
            if (r8 >= r5) goto L_0x00ab
            if (r12 < r15) goto L_0x00a1
            r3 = r8
            goto L_0x00f0
        L_0x00a1:
            if (r8 < r1) goto L_0x00a9
            int r14 = r14 + 2
            byte r12 = r13[r12]
            if (r12 <= r6) goto L_0x0091
        L_0x00a9:
            r3 = r7
            goto L_0x00f0
        L_0x00ab:
            if (r8 >= r2) goto L_0x00cb
            int r9 = r15 + -1
            if (r12 < r9) goto L_0x00b6
            int r3 = com.google.android.gms.internal.ads.zzhff.zzc(r13, r12, r15)
            goto L_0x00f0
        L_0x00b6:
            int r9 = r14 + 2
            byte r12 = r13[r12]
            if (r12 > r6) goto L_0x00a9
            if (r8 != r5) goto L_0x00c0
            if (r12 < r4) goto L_0x00a9
        L_0x00c0:
            if (r8 != r0) goto L_0x00c4
            if (r12 >= r4) goto L_0x00a9
        L_0x00c4:
            int r14 = r14 + 3
            byte r12 = r13[r9]
            if (r12 <= r6) goto L_0x0091
            goto L_0x00a9
        L_0x00cb:
            int r9 = r15 + -2
            if (r12 < r9) goto L_0x00d4
            int r3 = com.google.android.gms.internal.ads.zzhff.zzc(r13, r12, r15)
            goto L_0x00f0
        L_0x00d4:
            int r9 = r14 + 2
            byte r12 = r13[r12]
            if (r12 > r6) goto L_0x00a9
            int r8 = r8 << 28
            int r12 = r12 + 112
            int r8 = r8 + r12
            int r12 = r8 >> 30
            if (r12 != 0) goto L_0x00a9
            int r12 = r14 + 3
            byte r8 = r13[r9]
            if (r8 > r6) goto L_0x00a9
            int r14 = r14 + 4
            byte r12 = r13[r12]
            if (r12 <= r6) goto L_0x0091
            goto L_0x00a9
        L_0x00f0:
            return r3
        L_0x00f1:
            r14 = r12
            goto L_0x0091
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhfd.zza(int, byte[], int, int):int");
    }

    /* access modifiers changed from: package-private */
    public final String zzb(byte[] bArr, int i, int i2) throws zzhcd {
        int i3;
        int i4;
        int length = bArr.length;
        if ((((length - i) - i2) | i | i2) >= 0) {
            int i5 = i + i2;
            char[] cArr = new char[i2];
            int i6 = 0;
            while (r11 < i5) {
                byte b2 = bArr[r11];
                if (!zzhfb.zzd(b2)) {
                    break;
                }
                i = r11 + 1;
                cArr[i3] = (char) b2;
                i6 = i3 + 1;
            }
            while (r11 < i5) {
                int i7 = r11 + 1;
                byte b3 = bArr[r11];
                if (zzhfb.zzd(b3)) {
                    cArr[i3] = (char) b3;
                    i3++;
                    r11 = i7;
                    while (r11 < i5) {
                        byte b4 = bArr[r11];
                        if (!zzhfb.zzd(b4)) {
                            break;
                        }
                        r11++;
                        cArr[i3] = (char) b4;
                        i3++;
                    }
                } else {
                    if (zzhfb.zzf(b3)) {
                        if (i7 < i5) {
                            i4 = i3 + 1;
                            r11 = r11 + 2;
                            zzhfb.zzc(b3, bArr[i7], cArr, i3);
                        } else {
                            throw zzhcd.zzd();
                        }
                    } else if (zzhfb.zze(b3)) {
                        if (i7 < i5 - 1) {
                            i4 = i3 + 1;
                            int i8 = r11 + 2;
                            r11 = r11 + 3;
                            zzhfb.zzb(b3, bArr[i7], bArr[i8], cArr, i3);
                        } else {
                            throw zzhcd.zzd();
                        }
                    } else if (i7 < i5 - 2) {
                        byte b5 = bArr[i7];
                        int i9 = r11 + 3;
                        byte b6 = bArr[r11 + 2];
                        r11 += 4;
                        zzhfb.zza(b3, b5, b6, bArr[i9], cArr, i3);
                        i3 += 2;
                    } else {
                        throw zzhcd.zzd();
                    }
                    i3 = i4;
                }
            }
            return new String(cArr, 0, i3);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[]{Integer.valueOf(length), Integer.valueOf(i), Integer.valueOf(i2)}));
    }
}
