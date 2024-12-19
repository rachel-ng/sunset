package com.alibaba.fastjson.util;

import java.lang.reflect.Array;
import java.math.BigInteger;

public final class RyuDouble {
    private static final int[][] POW5_INV_SPLIT;
    private static final int[][] POW5_SPLIT;

    static {
        int i;
        int[] iArr = new int[2];
        iArr[1] = 4;
        iArr[0] = 326;
        POW5_SPLIT = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int[] iArr2 = new int[2];
        iArr2[1] = 4;
        iArr2[0] = 291;
        POW5_INV_SPLIT = (int[][]) Array.newInstance(Integer.TYPE, iArr2);
        BigInteger subtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger subtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i2 = 0;
        while (i2 < 326) {
            BigInteger pow = BigInteger.valueOf(5).pow(i2);
            int bitLength = pow.bitLength();
            if (i2 == 0) {
                i = 1;
            } else {
                i = (int) (((((long) i2) * 23219280) + 9999999) / 10000000);
            }
            if (i == bitLength) {
                if (i2 < POW5_SPLIT.length) {
                    for (int i3 = 0; i3 < 4; i3++) {
                        POW5_SPLIT[i2][i3] = pow.shiftRight((bitLength - 121) + ((3 - i3) * 31)).and(subtract).intValue();
                    }
                }
                if (i2 < POW5_INV_SPLIT.length) {
                    BigInteger add = BigInteger.ONE.shiftLeft(bitLength + 121).divide(pow).add(BigInteger.ONE);
                    for (int i4 = 0; i4 < 4; i4++) {
                        if (i4 == 0) {
                            POW5_INV_SPLIT[i2][i4] = add.shiftRight((3 - i4) * 31).intValue();
                        } else {
                            POW5_INV_SPLIT[i2][i4] = add.shiftRight((3 - i4) * 31).and(subtract2).intValue();
                        }
                    }
                }
                i2++;
            } else {
                throw new IllegalStateException(bitLength + " != " + i);
            }
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    /* JADX WARNING: Removed duplicated region for block: B:237:0x0562  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0564  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int toString(double r41, char[] r43, int r44) {
        /*
            boolean r0 = java.lang.Double.isNaN(r41)
            if (r0 == 0) goto L_0x001b
            int r0 = r44 + 1
            r1 = 78
            r43[r44] = r1
            int r1 = r44 + 2
            r2 = 97
            r43[r0] = r2
            int r0 = r44 + 3
            r2 = 78
            r43[r1] = r2
        L_0x0018:
            int r0 = r0 - r44
            return r0
        L_0x001b:
            r0 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            int r0 = (r41 > r0 ? 1 : (r41 == r0 ? 0 : -1))
            r1 = 121(0x79, float:1.7E-43)
            r2 = 105(0x69, float:1.47E-43)
            r3 = 110(0x6e, float:1.54E-43)
            if (r0 != 0) goto L_0x0050
            int r0 = r44 + 1
            r4 = 73
            r43[r44] = r4
            int r4 = r44 + 2
            r43[r0] = r3
            int r0 = r44 + 3
            r5 = 102(0x66, float:1.43E-43)
            r43[r4] = r5
            int r4 = r44 + 4
            r43[r0] = r2
            int r0 = r44 + 5
            r43[r4] = r3
            int r3 = r44 + 6
            r43[r0] = r2
            int r0 = r44 + 7
            r2 = 116(0x74, float:1.63E-43)
            r43[r3] = r2
            int r2 = r44 + 8
            r43[r0] = r1
            int r2 = r2 - r44
            return r2
        L_0x0050:
            r4 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            int r0 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0083
            int r0 = r44 + 1
            r4 = 45
            r43[r44] = r4
            int r4 = r44 + 2
            r5 = 73
            r43[r0] = r5
            int r0 = r44 + 3
            r43[r4] = r3
            int r4 = r44 + 4
            r5 = 102(0x66, float:1.43E-43)
            r43[r0] = r5
            int r0 = r44 + 5
            r43[r4] = r2
            int r4 = r44 + 6
            r43[r0] = r3
            int r0 = r44 + 7
            r43[r4] = r2
            int r2 = r44 + 8
            r3 = 116(0x74, float:1.63E-43)
            r43[r0] = r3
            int r0 = r44 + 9
            r43[r2] = r1
            goto L_0x0018
        L_0x0083:
            long r2 = java.lang.Double.doubleToLongBits(r41)
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r6 = 46
            r7 = 48
            if (r0 != 0) goto L_0x009f
            int r0 = r44 + 1
            r43[r44] = r7
            int r1 = r44 + 2
            r43[r0] = r6
            int r0 = r44 + 3
            r43[r1] = r7
            goto L_0x0018
        L_0x009f:
            r8 = -9223372036854775808
            int r8 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x00ba
            int r0 = r44 + 1
            r1 = 45
            r43[r44] = r1
            int r1 = r44 + 2
            r43[r0] = r7
            int r0 = r44 + 3
            r43[r1] = r6
            int r1 = r44 + 4
            r43[r0] = r7
        L_0x00b7:
            int r1 = r1 - r44
            return r1
        L_0x00ba:
            r8 = 52
            long r8 = r2 >>> r8
            r10 = 2047(0x7ff, double:1.0114E-320)
            long r8 = r8 & r10
            int r8 = (int) r8
            r9 = 4503599627370495(0xfffffffffffff, double:2.225073858507201E-308)
            long r2 = r2 & r9
            if (r8 != 0) goto L_0x00cd
            r9 = -1074(0xfffffffffffffbce, float:NaN)
            goto L_0x00d2
        L_0x00cd:
            int r9 = r8 + -1075
            r10 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r2 = r2 | r10
        L_0x00d2:
            r10 = 0
            r11 = 1
            if (r0 >= 0) goto L_0x00d8
            r0 = r11
            goto L_0x00d9
        L_0x00d8:
            r0 = r10
        L_0x00d9:
            r12 = 1
            long r14 = r2 & r12
            int r14 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r14 != 0) goto L_0x00e3
            r14 = r11
            goto L_0x00e4
        L_0x00e3:
            r14 = r10
        L_0x00e4:
            r15 = 4
            long r15 = r15 * r2
            r17 = 2
            long r17 = r15 + r17
            r19 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            int r2 = (r2 > r19 ? 1 : (r2 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x00f6
            if (r8 > r11) goto L_0x00f4
            goto L_0x00f6
        L_0x00f4:
            r2 = r10
            goto L_0x00f7
        L_0x00f6:
            r2 = r11
        L_0x00f7:
            long r19 = r15 - r12
            long r6 = (long) r2
            long r19 = r19 - r6
            int r9 = r9 + -2
            r6 = 10000000(0x989680, double:4.9406565E-317)
            r21 = 2147483647(0x7fffffff, double:1.060997895E-314)
            r23 = 3
            r24 = 2
            r25 = 31
            if (r9 < 0) goto L_0x02e8
            long r12 = (long) r9
            r26 = 3010299(0x2deefb, double:1.4872853E-317)
            long r12 = r12 * r26
            long r12 = r12 / r6
            int r2 = (int) r12
            int r2 = r2 - r11
            int r2 = java.lang.Math.max(r10, r2)
            if (r2 != 0) goto L_0x011d
            r12 = r11
            goto L_0x012a
        L_0x011d:
            long r12 = (long) r2
            r26 = 23219280(0x1624c50, double:1.14718486E-316)
            long r12 = r12 * r26
            r26 = 9999999(0x98967f, double:4.940656E-317)
            long r12 = r12 + r26
            long r12 = r12 / r6
            int r12 = (int) r12
        L_0x012a:
            int r12 = r12 + r1
            int r1 = -r9
            int r1 = r1 + r2
            int r1 = r1 + r12
            int r1 = r1 + -114
            if (r1 < 0) goto L_0x02d0
            int[][] r9 = POW5_INV_SPLIT
            r9 = r9[r2]
            long r12 = r15 >>> r25
            long r26 = r15 & r21
            r6 = r9[r10]
            long r4 = (long) r6
            long r4 = r4 * r12
            r28 = r4
            long r3 = (long) r6
            long r3 = r3 * r26
            r5 = r9[r11]
            long r10 = (long) r5
            long r10 = r10 * r12
            long r7 = (long) r5
            long r7 = r7 * r26
            r31 = r0
            r0 = r9[r24]
            r32 = r14
            r33 = r15
            long r14 = (long) r0
            long r14 = r14 * r12
            r35 = r5
            r16 = r6
            long r5 = (long) r0
            long r5 = r5 * r26
            r9 = r9[r23]
            r37 = r0
            r36 = r1
            long r0 = (long) r9
            long r12 = r12 * r0
            long r0 = (long) r9
            long r26 = r26 * r0
            long r0 = r26 >>> r25
            long r0 = r0 + r5
            long r0 = r0 + r12
            long r0 = r0 >>> r25
            long r0 = r0 + r7
            long r0 = r0 + r14
            long r0 = r0 >>> r25
            long r0 = r0 + r3
            long r0 = r0 + r10
            r3 = 21
            long r0 = r0 >>> r3
            r3 = 10
            long r4 = r28 << r3
            long r0 = r0 + r4
            long r0 = r0 >>> r36
            long r3 = r17 >>> r25
            long r5 = r17 & r21
            r8 = r16
            long r10 = (long) r8
            long r10 = r10 * r3
            long r12 = (long) r8
            long r12 = r12 * r5
            r14 = r35
            long r7 = (long) r14
            long r7 = r7 * r3
            r26 = r0
            long r0 = (long) r14
            long r0 = r0 * r5
            r28 = r10
            r15 = r37
            long r10 = (long) r15
            long r10 = r10 * r3
            r37 = r7
            long r7 = (long) r15
            long r7 = r7 * r5
            r39 = r15
            long r14 = (long) r9
            long r3 = r3 * r14
            long r14 = (long) r9
            long r5 = r5 * r14
            long r5 = r5 >>> r25
            long r5 = r5 + r7
            long r5 = r5 + r3
            long r3 = r5 >>> r25
            long r3 = r3 + r0
            long r3 = r3 + r10
            long r0 = r3 >>> r25
            long r0 = r0 + r12
            long r0 = r0 + r37
            r3 = 21
            long r0 = r0 >>> r3
            r3 = 10
            long r4 = r28 << r3
            long r0 = r0 + r4
            long r0 = r0 >>> r36
            long r3 = r19 >>> r25
            long r5 = r19 & r21
            r8 = r16
            long r10 = (long) r8
            long r10 = r10 * r3
            long r12 = (long) r8
            long r12 = r12 * r5
            r8 = r35
            long r14 = (long) r8
            long r14 = r14 * r3
            long r7 = (long) r8
            long r7 = r7 * r5
            r28 = r0
            r16 = r2
            r0 = r39
            long r1 = (long) r0
            long r1 = r1 * r3
            r21 = r10
            long r10 = (long) r0
            long r10 = r10 * r5
            r37 = r14
            long r14 = (long) r9
            long r3 = r3 * r14
            long r14 = (long) r9
            long r5 = r5 * r14
            long r5 = r5 >>> r25
            long r5 = r5 + r10
            long r5 = r5 + r3
            long r3 = r5 >>> r25
            long r3 = r3 + r7
            long r3 = r3 + r1
            long r0 = r3 >>> r25
            long r0 = r0 + r12
            long r0 = r0 + r37
            r2 = 21
            long r0 = r0 >>> r2
            r3 = 10
            long r4 = r21 << r3
            long r0 = r0 + r4
            long r0 = r0 >>> r36
            r3 = r16
            if (r3 > r2) goto L_0x02c5
            r4 = 5
            long r15 = r33 % r4
            r6 = 0
            int r2 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            r8 = 625(0x271, double:3.09E-321)
            if (r2 != 0) goto L_0x023d
            if (r2 == 0) goto L_0x0202
            r2 = 0
            goto L_0x0234
        L_0x0202:
            r10 = 25
            long r15 = r33 % r10
            int r2 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x020c
            r2 = 1
            goto L_0x0234
        L_0x020c:
            r10 = 125(0x7d, double:6.2E-322)
            long r15 = r33 % r10
            int r2 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0217
            r2 = r24
            goto L_0x0234
        L_0x0217:
            long r15 = r33 % r8
            int r2 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r2 == 0) goto L_0x0220
            r2 = r23
            goto L_0x0234
        L_0x0220:
            long r15 = r33 / r8
            r2 = 4
        L_0x0223:
            int r8 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x0234
            long r8 = r15 % r4
            int r8 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x022e
            goto L_0x0234
        L_0x022e:
            long r15 = r15 / r4
            int r2 = r2 + 1
            r6 = 0
            goto L_0x0223
        L_0x0234:
            if (r2 < r3) goto L_0x0238
            r2 = 1
            goto L_0x0239
        L_0x0238:
            r2 = 0
        L_0x0239:
            r4 = r2
            r2 = 0
            goto L_0x02c7
        L_0x023d:
            if (r32 == 0) goto L_0x0280
            long r6 = r19 % r4
            r10 = 0
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0249
            r2 = 0
            goto L_0x027c
        L_0x0249:
            r6 = 25
            long r6 = r19 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0253
            r2 = 1
            goto L_0x027c
        L_0x0253:
            r6 = 125(0x7d, double:6.2E-322)
            long r6 = r19 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x025e
            r2 = r24
            goto L_0x027c
        L_0x025e:
            long r6 = r19 % r8
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0267
            r2 = r23
            goto L_0x027c
        L_0x0267:
            long r19 = r19 / r8
            r2 = 4
        L_0x026a:
            int r6 = (r19 > r10 ? 1 : (r19 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x027c
            long r6 = r19 % r4
            int r6 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x0275
            goto L_0x027c
        L_0x0275:
            long r19 = r19 / r4
            int r2 = r2 + 1
            r10 = 0
            goto L_0x026a
        L_0x027c:
            if (r2 < r3) goto L_0x02c5
            r2 = 1
            goto L_0x02c6
        L_0x0280:
            long r6 = r17 % r4
            r10 = 0
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x028a
            r2 = 0
            goto L_0x02bd
        L_0x028a:
            r6 = 25
            long r6 = r17 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x0294
            r2 = 1
            goto L_0x02bd
        L_0x0294:
            r6 = 125(0x7d, double:6.2E-322)
            long r6 = r17 % r6
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x029f
            r2 = r24
            goto L_0x02bd
        L_0x029f:
            long r6 = r17 % r8
            int r2 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r2 == 0) goto L_0x02a8
            r2 = r23
            goto L_0x02bd
        L_0x02a8:
            long r17 = r17 / r8
            r2 = 4
        L_0x02ab:
            int r6 = (r17 > r10 ? 1 : (r17 == r10 ? 0 : -1))
            if (r6 <= 0) goto L_0x02bd
            long r6 = r17 % r4
            int r6 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x02b6
            goto L_0x02bd
        L_0x02b6:
            long r17 = r17 / r4
            int r2 = r2 + 1
            r10 = 0
            goto L_0x02ab
        L_0x02bd:
            if (r2 < r3) goto L_0x02c5
            r4 = 1
            long r4 = r28 - r4
            r28 = r4
        L_0x02c5:
            r2 = 0
        L_0x02c6:
            r4 = 0
        L_0x02c7:
            r17 = r26
            r40 = r3
            r3 = r2
            r2 = r40
            goto L_0x0411
        L_0x02d0:
            r36 = r1
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = ""
            r1.<init>(r2)
            r2 = r36
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02e8:
            r31 = r0
            r32 = r14
            r33 = r15
            int r0 = -r9
            long r3 = (long) r0
            r5 = 6989700(0x6aa784, double:3.4533706E-317)
            long r3 = r3 * r5
            r5 = 10000000(0x989680, double:4.9406565E-317)
            long r3 = r3 / r5
            int r3 = (int) r3
            r4 = 1
            int r3 = r3 - r4
            r4 = 0
            int r3 = java.lang.Math.max(r4, r3)
            int r0 = r0 - r3
            if (r0 != 0) goto L_0x0305
            r4 = 1
            goto L_0x0313
        L_0x0305:
            long r4 = (long) r0
            r10 = 23219280(0x1624c50, double:1.14718486E-316)
            long r4 = r4 * r10
            r10 = 9999999(0x98967f, double:4.940656E-317)
            long r4 = r4 + r10
            r10 = 10000000(0x989680, double:4.9406565E-317)
            long r4 = r4 / r10
            int r4 = (int) r4
        L_0x0313:
            int r4 = r4 - r1
            int r1 = r3 - r4
            int r1 = r1 + -114
            if (r1 < 0) goto L_0x06ad
            int[][] r4 = POW5_SPLIT
            r0 = r4[r0]
            long r4 = r33 >>> r25
            long r10 = r33 & r21
            r6 = 0
            r8 = r0[r6]
            long r12 = (long) r8
            long r12 = r12 * r4
            long r14 = (long) r8
            long r14 = r14 * r10
            r16 = 1
            r6 = r0[r16]
            r16 = r8
            long r7 = (long) r6
            long r7 = r7 * r4
            r26 = r2
            r27 = r3
            long r2 = (long) r6
            long r2 = r2 * r10
            r28 = r9
            r9 = r0[r24]
            r35 = r12
            long r12 = (long) r9
            long r12 = r12 * r4
            r29 = r6
            r37 = r7
            long r6 = (long) r9
            long r6 = r6 * r10
            r0 = r0[r23]
            r39 = r9
            long r8 = (long) r0
            long r4 = r4 * r8
            long r8 = (long) r0
            long r10 = r10 * r8
            long r8 = r10 >>> r25
            long r8 = r8 + r6
            long r8 = r8 + r4
            long r4 = r8 >>> r25
            long r4 = r4 + r2
            long r4 = r4 + r12
            long r2 = r4 >>> r25
            long r2 = r2 + r14
            long r2 = r2 + r37
            r4 = 21
            long r2 = r2 >>> r4
            r4 = 10
            long r5 = r35 << r4
            long r2 = r2 + r5
            long r2 = r2 >>> r1
            long r4 = r17 >>> r25
            long r8 = r17 & r21
            r6 = r16
            long r10 = (long) r6
            long r10 = r10 * r4
            long r12 = (long) r6
            long r12 = r12 * r8
            r15 = r8
            r14 = r29
            long r7 = (long) r14
            long r7 = r7 * r4
            r17 = r2
            long r2 = (long) r14
            long r2 = r2 * r15
            r35 = r10
            r9 = r39
            long r10 = (long) r9
            long r10 = r10 * r4
            r29 = r6
            r37 = r7
            long r6 = (long) r9
            long r6 = r6 * r15
            long r8 = (long) r0
            long r4 = r4 * r8
            long r8 = (long) r0
            long r8 = r8 * r15
            long r8 = r8 >>> r25
            long r8 = r8 + r6
            long r8 = r8 + r4
            long r4 = r8 >>> r25
            long r4 = r4 + r2
            long r4 = r4 + r10
            long r2 = r4 >>> r25
            long r2 = r2 + r12
            long r2 = r2 + r37
            r4 = 21
            long r2 = r2 >>> r4
            r4 = 10
            long r5 = r35 << r4
            long r2 = r2 + r5
            long r2 = r2 >>> r1
            long r4 = r19 >>> r25
            long r8 = r19 & r21
            r6 = r29
            long r10 = (long) r6
            long r10 = r10 * r4
            long r12 = (long) r6
            long r12 = r12 * r8
            r15 = r8
            long r7 = (long) r14
            long r7 = r7 * r4
            r19 = r2
            long r2 = (long) r14
            long r2 = r2 * r15
            r21 = r10
            r6 = r39
            long r9 = (long) r6
            long r9 = r9 * r4
            r35 = r7
            long r6 = (long) r6
            long r6 = r6 * r15
            r37 = r12
            long r11 = (long) r0
            long r4 = r4 * r11
            long r11 = (long) r0
            long r11 = r11 * r15
            long r11 = r11 >>> r25
            long r11 = r11 + r6
            long r11 = r11 + r4
            long r4 = r11 >>> r25
            long r4 = r4 + r2
            long r4 = r4 + r9
            long r2 = r4 >>> r25
            long r2 = r2 + r37
            long r2 = r2 + r35
            r0 = 21
            long r2 = r2 >>> r0
            r0 = 10
            long r4 = r21 << r0
            long r2 = r2 + r4
            long r0 = r2 >>> r1
            int r2 = r27 + r28
            r4 = r27
            r3 = 1
            if (r4 > r3) goto L_0x03f4
            if (r32 == 0) goto L_0x03ed
            r10 = r26
            if (r10 != r3) goto L_0x03e5
            r30 = r3
            goto L_0x03e7
        L_0x03e5:
            r30 = 0
        L_0x03e7:
            r4 = r3
            r28 = r19
            r3 = r30
            goto L_0x0411
        L_0x03ed:
            r5 = 1
            long r28 = r19 - r5
            r4 = r3
        L_0x03f2:
            r3 = 0
            goto L_0x0411
        L_0x03f4:
            r5 = 1
            r7 = 63
            if (r4 >= r7) goto L_0x040d
            int r4 = r4 - r3
            long r3 = r5 << r4
            long r3 = r3 - r5
            long r3 = r33 & r3
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0408
            r3 = 1
            goto L_0x0409
        L_0x0408:
            r3 = 0
        L_0x0409:
            r4 = r3
            r28 = r19
            goto L_0x03f2
        L_0x040d:
            r28 = r19
            r3 = 0
            r4 = 0
        L_0x0411:
            r5 = 1000000000000000000(0xde0b6b3a7640000, double:7.832953389245686E-242)
            int r5 = (r28 > r5 ? 1 : (r28 == r5 ? 0 : -1))
            r6 = 7
            r7 = 5
            r8 = 10
            if (r5 < 0) goto L_0x0422
            r23 = 19
            goto L_0x04dd
        L_0x0422:
            r10 = 100000000000000000(0x16345785d8a0000, double:5.620395787888205E-302)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x042f
            r23 = 18
            goto L_0x04dd
        L_0x042f:
            r10 = 10000000000000000(0x2386f26fc10000, double:5.431165199810528E-308)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x043c
            r23 = 17
            goto L_0x04dd
        L_0x043c:
            r10 = 1000000000000000(0x38d7ea4c68000, double:4.940656458412465E-309)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0449
            r23 = 16
            goto L_0x04dd
        L_0x0449:
            r10 = 100000000000000(0x5af3107a4000, double:4.94065645841247E-310)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0456
            r23 = 15
            goto L_0x04dd
        L_0x0456:
            r10 = 10000000000000(0x9184e72a000, double:4.9406564584125E-311)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0463
            r23 = 14
            goto L_0x04dd
        L_0x0463:
            r10 = 1000000000000(0xe8d4a51000, double:4.94065645841E-312)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0470
            r23 = 13
            goto L_0x04dd
        L_0x0470:
            r10 = 100000000000(0x174876e800, double:4.9406564584E-313)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x047d
            r23 = 12
            goto L_0x04dd
        L_0x047d:
            r10 = 10000000000(0x2540be400, double:4.9406564584E-314)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0489
            r23 = 11
            goto L_0x04dd
        L_0x0489:
            r10 = 1000000000(0x3b9aca00, double:4.94065646E-315)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x0493
            r23 = 10
            goto L_0x04dd
        L_0x0493:
            r10 = 100000000(0x5f5e100, double:4.94065646E-316)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x049d
            r23 = 9
            goto L_0x04dd
        L_0x049d:
            r10 = 10000000(0x989680, double:4.9406565E-317)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04a7
            r23 = 8
            goto L_0x04dd
        L_0x04a7:
            r10 = 1000000(0xf4240, double:4.940656E-318)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04b1
            r23 = r6
            goto L_0x04dd
        L_0x04b1:
            r10 = 100000(0x186a0, double:4.94066E-319)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04bb
            r23 = 6
            goto L_0x04dd
        L_0x04bb:
            r10 = 10000(0x2710, double:4.9407E-320)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04c4
            r23 = r7
            goto L_0x04dd
        L_0x04c4:
            r10 = 1000(0x3e8, double:4.94E-321)
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04cd
            r23 = 4
            goto L_0x04dd
        L_0x04cd:
            r10 = 100
            int r5 = (r28 > r10 ? 1 : (r28 == r10 ? 0 : -1))
            if (r5 < 0) goto L_0x04d4
            goto L_0x04dd
        L_0x04d4:
            int r5 = (r28 > r8 ? 1 : (r28 == r8 ? 0 : -1))
            if (r5 < 0) goto L_0x04db
            r23 = r24
            goto L_0x04dd
        L_0x04db:
            r23 = 1
        L_0x04dd:
            int r2 = r2 + r23
            int r5 = r2 + -1
            r10 = -3
            if (r5 < r10) goto L_0x04e9
            if (r5 < r6) goto L_0x04e7
            goto L_0x04e9
        L_0x04e7:
            r6 = 0
            goto L_0x04ea
        L_0x04e9:
            r6 = 1
        L_0x04ea:
            if (r3 != 0) goto L_0x051c
            if (r4 == 0) goto L_0x04ef
            goto L_0x051c
        L_0x04ef:
            r3 = 0
            r4 = 0
        L_0x04f1:
            long r10 = r28 / r8
            long r12 = r0 / r8
            int r14 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r14 <= 0) goto L_0x050d
            r14 = 100
            int r14 = (r28 > r14 ? 1 : (r28 == r14 ? 0 : -1))
            if (r14 >= 0) goto L_0x0502
            if (r6 == 0) goto L_0x0502
            goto L_0x050d
        L_0x0502:
            long r0 = r17 % r8
            int r3 = (int) r0
            long r17 = r17 / r8
            int r4 = r4 + 1
            r28 = r10
            r0 = r12
            goto L_0x04f1
        L_0x050d:
            int r0 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x0516
            if (r3 < r7) goto L_0x0514
            goto L_0x0516
        L_0x0514:
            r0 = 0
            goto L_0x0517
        L_0x0516:
            r0 = 1
        L_0x0517:
            long r0 = (long) r0
            long r17 = r17 + r0
            goto L_0x0591
        L_0x051c:
            r10 = 0
            r11 = 0
        L_0x051e:
            long r12 = r28 / r8
            long r14 = r0 / r8
            int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r16 <= 0) goto L_0x054b
            r19 = 100
            int r16 = (r28 > r19 ? 1 : (r28 == r19 ? 0 : -1))
            if (r16 >= 0) goto L_0x052f
            if (r6 == 0) goto L_0x052f
            goto L_0x054b
        L_0x052f:
            long r0 = r0 % r8
            r19 = 0
            int r0 = (r0 > r19 ? 1 : (r0 == r19 ? 0 : -1))
            if (r0 != 0) goto L_0x0538
            r0 = 1
            goto L_0x0539
        L_0x0538:
            r0 = 0
        L_0x0539:
            r3 = r3 & r0
            if (r10 != 0) goto L_0x053e
            r0 = 1
            goto L_0x053f
        L_0x053e:
            r0 = 0
        L_0x053f:
            r4 = r4 & r0
            long r0 = r17 % r8
            int r10 = (int) r0
            long r17 = r17 / r8
            int r11 = r11 + 1
            r28 = r12
            r0 = r14
            goto L_0x051e
        L_0x054b:
            if (r3 == 0) goto L_0x0571
            if (r32 == 0) goto L_0x0571
        L_0x054f:
            long r12 = r0 % r8
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 != 0) goto L_0x0571
            r12 = 100
            int r12 = (r28 > r12 ? 1 : (r28 == r12 ? 0 : -1))
            if (r12 >= 0) goto L_0x0560
            if (r6 == 0) goto L_0x0560
            goto L_0x0571
        L_0x0560:
            if (r10 != 0) goto L_0x0564
            r10 = 1
            goto L_0x0565
        L_0x0564:
            r10 = 0
        L_0x0565:
            r4 = r4 & r10
            long r12 = r17 % r8
            int r10 = (int) r12
            long r28 = r28 / r8
            long r17 = r17 / r8
            long r0 = r0 / r8
            int r11 = r11 + 1
            goto L_0x054f
        L_0x0571:
            if (r4 == 0) goto L_0x0580
            if (r10 != r7) goto L_0x0580
            r12 = 2
            long r12 = r17 % r12
            r14 = 0
            int r4 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r4 != 0) goto L_0x0580
            r10 = 4
        L_0x0580:
            int r0 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x0588
            if (r3 == 0) goto L_0x058a
            if (r32 == 0) goto L_0x058a
        L_0x0588:
            if (r10 < r7) goto L_0x058c
        L_0x058a:
            r0 = 1
            goto L_0x058d
        L_0x058c:
            r0 = 0
        L_0x058d:
            long r0 = (long) r0
            long r17 = r17 + r0
            r4 = r11
        L_0x0591:
            int r0 = r23 - r4
            if (r31 == 0) goto L_0x059c
            int r1 = r44 + 1
            r3 = 45
            r43[r44] = r3
            goto L_0x059e
        L_0x059c:
            r1 = r44
        L_0x059e:
            if (r6 == 0) goto L_0x0616
            r10 = 0
        L_0x05a1:
            int r2 = r0 + -1
            if (r10 >= r2) goto L_0x05b6
            long r2 = r17 % r8
            int r2 = (int) r2
            long r17 = r17 / r8
            int r3 = r1 + r0
            int r3 = r3 - r10
            r4 = 48
            int r2 = r2 + r4
            char r2 = (char) r2
            r43[r3] = r2
            int r10 = r10 + 1
            goto L_0x05a1
        L_0x05b6:
            r2 = 48
            long r17 = r17 % r8
            long r2 = r17 + r2
            int r2 = (int) r2
            char r2 = (char) r2
            r43[r1] = r2
            int r2 = r1 + 1
            r3 = 46
            r43[r2] = r3
            int r2 = r0 + 1
            int r1 = r1 + r2
            r2 = 1
            if (r0 != r2) goto L_0x05d3
            int r0 = r1 + 1
            r2 = 48
            r43[r1] = r2
            r1 = r0
        L_0x05d3:
            int r0 = r1 + 1
            r2 = 69
            r43[r1] = r2
            if (r5 >= 0) goto L_0x05e3
            int r1 = r1 + 2
            r2 = 45
            r43[r0] = r2
            int r5 = -r5
            r0 = r1
        L_0x05e3:
            r1 = 100
            if (r5 < r1) goto L_0x05fc
            int r1 = r0 + 1
            int r2 = r5 / 100
            r3 = 48
            int r2 = r2 + r3
            char r2 = (char) r2
            r43[r0] = r2
            int r5 = r5 % 100
            int r0 = r0 + 2
            int r2 = r5 / 10
            int r2 = r2 + r3
            char r2 = (char) r2
            r43[r1] = r2
            goto L_0x060b
        L_0x05fc:
            r1 = 10
            r3 = 48
            if (r5 < r1) goto L_0x060b
            int r1 = r0 + 1
            int r2 = r5 / 10
            int r2 = r2 + r3
            char r2 = (char) r2
            r43[r0] = r2
            r0 = r1
        L_0x060b:
            int r1 = r0 + 1
            r2 = 10
            int r5 = r5 % r2
            int r5 = r5 + r3
            char r2 = (char) r5
            r43[r0] = r2
            goto L_0x00b7
        L_0x0616:
            r3 = 48
            if (r5 >= 0) goto L_0x064a
            int r2 = r1 + 1
            r43[r1] = r3
            int r1 = r1 + 2
            r4 = 46
            r43[r2] = r4
            r2 = -1
        L_0x0625:
            if (r2 <= r5) goto L_0x0631
            int r4 = r1 + 1
            r43[r1] = r3
            int r2 = r2 + -1
            r1 = r4
            r3 = 48
            goto L_0x0625
        L_0x0631:
            r2 = r1
            r10 = 0
        L_0x0633:
            if (r10 >= r0) goto L_0x06aa
            int r3 = r1 + r0
            int r3 = r3 - r10
            r4 = 1
            int r3 = r3 - r4
            r4 = 48
            long r6 = r17 % r8
            long r6 = r6 + r4
            int r4 = (int) r6
            char r4 = (char) r4
            r43[r3] = r4
            long r17 = r17 / r8
            int r2 = r2 + 1
            int r10 = r10 + 1
            goto L_0x0633
        L_0x064a:
            if (r2 < r0) goto L_0x067c
            r10 = 0
        L_0x064d:
            if (r10 >= r0) goto L_0x0662
            int r3 = r1 + r0
            int r3 = r3 - r10
            r4 = 1
            int r3 = r3 - r4
            r4 = 48
            long r6 = r17 % r8
            long r6 = r6 + r4
            int r4 = (int) r6
            char r4 = (char) r4
            r43[r3] = r4
            long r17 = r17 / r8
            int r10 = r10 + 1
            goto L_0x064d
        L_0x0662:
            int r1 = r1 + r0
        L_0x0663:
            if (r0 >= r2) goto L_0x066f
            int r3 = r1 + 1
            r4 = 48
            r43[r1] = r4
            int r0 = r0 + 1
            r1 = r3
            goto L_0x0663
        L_0x066f:
            r4 = 48
            int r0 = r1 + 1
            r2 = 46
            r43[r1] = r2
            int r2 = r1 + 2
            r43[r0] = r4
            goto L_0x06aa
        L_0x067c:
            int r2 = r1 + 1
            r10 = 0
        L_0x067f:
            if (r10 >= r0) goto L_0x06a6
            int r3 = r0 - r10
            r4 = 1
            int r3 = r3 - r4
            if (r3 != r5) goto L_0x0692
            int r3 = r2 + r0
            int r3 = r3 - r10
            int r3 = r3 - r4
            r6 = 46
            r43[r3] = r6
            int r2 = r2 + -1
            goto L_0x0694
        L_0x0692:
            r6 = 46
        L_0x0694:
            int r3 = r2 + r0
            int r3 = r3 - r10
            int r3 = r3 - r4
            r11 = 48
            long r13 = r17 % r8
            long r13 = r13 + r11
            int r7 = (int) r13
            char r7 = (char) r7
            r43[r3] = r7
            long r17 = r17 / r8
            int r10 = r10 + 1
            goto L_0x067f
        L_0x06a6:
            r4 = 1
            int r0 = r0 + r4
            int r2 = r1 + r0
        L_0x06aa:
            int r2 = r2 - r44
            return r2
        L_0x06ad:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = ""
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.util.RyuDouble.toString(double, char[], int):int");
    }
}
