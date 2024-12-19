package org.apache.commons.math3.util;

import java.math.BigInteger;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public final class ArithmeticUtils {
    public static boolean isPowerOfTwo(long j) {
        return j > 0 && (j & (j - 1)) == 0;
    }

    private ArithmeticUtils() {
    }

    public static int addAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) + ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long addAndCheck(long j, long j2) throws MathArithmeticException {
        return addAndCheck(j, j2, LocalizedFormats.OVERFLOW_IN_ADDITION);
    }

    @Deprecated
    public static long binomialCoefficient(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficient(i, i2);
    }

    @Deprecated
    public static double binomialCoefficientDouble(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientDouble(i, i2);
    }

    @Deprecated
    public static double binomialCoefficientLog(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.binomialCoefficientLog(i, i2);
    }

    @Deprecated
    public static long factorial(int i) throws NotPositiveException, MathArithmeticException {
        return CombinatoricsUtils.factorial(i);
    }

    @Deprecated
    public static double factorialDouble(int i) throws NotPositiveException {
        return CombinatoricsUtils.factorialDouble(i);
    }

    @Deprecated
    public static double factorialLog(int i) throws NotPositiveException {
        return CombinatoricsUtils.factorialLog(i);
    }

    public static int gcd(int i, int i2) throws MathArithmeticException {
        boolean z;
        int i3;
        int i4;
        if (i != 0 && i2 != 0) {
            long j = (long) i;
            long j2 = (long) i2;
            if (i < 0) {
                if (Integer.MIN_VALUE == i) {
                    i3 = i;
                    z = true;
                } else {
                    i3 = -i;
                    z = false;
                }
                j = -j;
            } else {
                i3 = i;
                z = false;
            }
            if (i2 < 0) {
                if (Integer.MIN_VALUE == i2) {
                    i4 = i2;
                    z = true;
                } else {
                    i4 = -i2;
                }
                j2 = -j2;
            } else {
                i4 = i2;
            }
            if (z) {
                if (j != j2) {
                    long j3 = j2 % j;
                    if (j3 != 0) {
                        i4 = (int) j3;
                        i3 = (int) (j % j3);
                    } else if (j <= 2147483647L) {
                        return (int) j;
                    } else {
                        throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
                    }
                } else {
                    throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
                }
            }
            return gcdPositive(i3, i4);
        } else if (i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE) {
            return FastMath.abs(i + i2);
        } else {
            throw new MathArithmeticException(LocalizedFormats.GCD_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
        }
    }

    private static int gcdPositive(int i, int i2) {
        if (i == 0) {
            return i2;
        }
        if (i2 == 0) {
            return i;
        }
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
        int i3 = i >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Integer.numberOfTrailingZeros(i2);
        int i4 = i2 >> numberOfTrailingZeros2;
        int min = FastMath.min(numberOfTrailingZeros, numberOfTrailingZeros2);
        while (i3 != i4) {
            int i5 = i3 - i4;
            i4 = Math.min(i3, i4);
            int abs = Math.abs(i5);
            i3 = abs >> Integer.numberOfTrailingZeros(abs);
        }
        return i3 << min;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static long gcd(long r23, long r25) throws org.apache.commons.math3.exception.MathArithmeticException {
        /*
            r0 = r23
            r2 = r25
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            r7 = 2
            r9 = 0
            if (r6 == 0) goto L_0x0078
            int r10 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0012
            goto L_0x0078
        L_0x0012:
            if (r6 <= 0) goto L_0x0016
            long r11 = -r0
            goto L_0x0017
        L_0x0016:
            r11 = r0
        L_0x0017:
            if (r10 <= 0) goto L_0x001b
            long r13 = -r2
            goto L_0x001c
        L_0x001b:
            r13 = r2
        L_0x001c:
            r6 = r9
        L_0x001d:
            r15 = 1
            long r17 = r11 & r15
            int r10 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            r8 = 63
            r19 = 2
            if (r10 != 0) goto L_0x0038
            long r21 = r13 & r15
            int r10 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r10 != 0) goto L_0x0038
            if (r6 >= r8) goto L_0x0038
            long r11 = r11 / r19
            long r13 = r13 / r19
            int r6 = r6 + 1
            goto L_0x001d
        L_0x0038:
            if (r6 == r8) goto L_0x0061
            int r0 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
            if (r0 != 0) goto L_0x0040
            r0 = r13
            goto L_0x0043
        L_0x0040:
            long r0 = r11 / r19
            long r0 = -r0
        L_0x0043:
            long r2 = r0 & r15
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x004c
            long r0 = r0 / r19
            goto L_0x0043
        L_0x004c:
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 <= 0) goto L_0x0053
            long r0 = -r0
            r11 = r0
            goto L_0x0054
        L_0x0053:
            r13 = r0
        L_0x0054:
            long r0 = r13 - r11
            long r0 = r0 / r19
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x0043
            long r0 = -r11
            long r2 = r15 << r6
            long r0 = r0 * r2
            return r0
        L_0x0061:
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Long r0 = java.lang.Long.valueOf(r23)
            java.lang.Long r1 = java.lang.Long.valueOf(r25)
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r9] = r0
            r0 = 1
            r2[r0] = r1
            r4.<init>(r5, r2)
            throw r4
        L_0x0078:
            r4 = -9223372036854775808
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x008c
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 == 0) goto L_0x008c
            long r0 = org.apache.commons.math3.util.FastMath.abs((long) r23)
            long r2 = org.apache.commons.math3.util.FastMath.abs((long) r25)
            long r0 = r0 + r2
            return r0
        L_0x008c:
            org.apache.commons.math3.exception.MathArithmeticException r4 = new org.apache.commons.math3.exception.MathArithmeticException
            org.apache.commons.math3.exception.util.LocalizedFormats r5 = org.apache.commons.math3.exception.util.LocalizedFormats.GCD_OVERFLOW_64_BITS
            java.lang.Long r0 = java.lang.Long.valueOf(r23)
            java.lang.Long r1 = java.lang.Long.valueOf(r25)
            java.lang.Object[] r2 = new java.lang.Object[r7]
            r2[r9] = r0
            r0 = 1
            r2[r0] = r1
            r4.<init>(r5, r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ArithmeticUtils.gcd(long, long):long");
    }

    public static int lcm(int i, int i2) throws MathArithmeticException {
        if (i == 0 || i2 == 0) {
            return 0;
        }
        int abs = FastMath.abs(mulAndCheck(i / gcd(i, i2), i2));
        if (abs != Integer.MIN_VALUE) {
            return abs;
        }
        throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_32_BITS, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long lcm(long j, long j2) throws MathArithmeticException {
        if (j == 0 || j2 == 0) {
            return 0;
        }
        long abs = FastMath.abs(mulAndCheck(j / gcd(j, j2), j2));
        if (abs != Long.MIN_VALUE) {
            return abs;
        }
        throw new MathArithmeticException(LocalizedFormats.LCM_OVERFLOW_64_BITS, Long.valueOf(j), Long.valueOf(j2));
    }

    public static int mulAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) * ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException();
    }

    public static long mulAndCheck(long j, long j2) throws MathArithmeticException {
        if (j > j2) {
            return mulAndCheck(j2, j);
        }
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        if (i < 0) {
            int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
            if (i2 >= 0) {
                if (i2 > 0) {
                    if (Long.MIN_VALUE / j2 > j) {
                        throw new MathArithmeticException();
                    }
                }
                return 0;
            } else if (j < Long.MAX_VALUE / j2) {
                throw new MathArithmeticException();
            }
        } else {
            if (i > 0) {
                if (j > Long.MAX_VALUE / j2) {
                    throw new MathArithmeticException();
                }
            }
            return 0;
        }
        return j * j2;
    }

    public static int subAndCheck(int i, int i2) throws MathArithmeticException {
        long j = ((long) i) - ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_SUBTRACTION, Integer.valueOf(i), Integer.valueOf(i2));
    }

    public static long subAndCheck(long j, long j2) throws MathArithmeticException {
        if (j2 != Long.MIN_VALUE) {
            return addAndCheck(j, -j2, LocalizedFormats.OVERFLOW_IN_ADDITION);
        }
        if (j < 0) {
            return j - j2;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_ADDITION, Long.valueOf(j), Long.valueOf(-j2));
    }

    public static int pow(int i, int i2) throws NotPositiveException, MathArithmeticException {
        if (i2 >= 0) {
            int i3 = i;
            int i4 = i2;
            int i5 = 1;
            while (true) {
                if ((i4 & 1) != 0) {
                    try {
                        i5 = mulAndCheck(i5, i3);
                    } catch (MathArithmeticException e) {
                        e.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
                        e.getContext().addMessage(LocalizedFormats.BASE, Integer.valueOf(i));
                        e.getContext().addMessage(LocalizedFormats.EXPONENT, Integer.valueOf(i2));
                        throw e;
                    }
                }
                i4 >>= 1;
                if (i4 == 0) {
                    return i5;
                }
                i3 = mulAndCheck(i3, i3);
            }
        } else {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i2));
        }
    }

    @Deprecated
    public static int pow(int i, long j) throws NotPositiveException {
        if (j >= 0) {
            int i2 = 1;
            while (j != 0) {
                if ((1 & j) != 0) {
                    i2 *= i;
                }
                i *= i;
                j >>= 1;
            }
            return i2;
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j));
    }

    public static long pow(long j, int i) throws NotPositiveException, MathArithmeticException {
        if (i >= 0) {
            long j2 = 1;
            long j3 = j;
            int i2 = i;
            while (true) {
                if ((i2 & 1) != 0) {
                    try {
                        j2 = mulAndCheck(j2, j3);
                    } catch (MathArithmeticException e) {
                        e.getContext().addMessage(LocalizedFormats.OVERFLOW, new Object[0]);
                        e.getContext().addMessage(LocalizedFormats.BASE, Long.valueOf(j));
                        e.getContext().addMessage(LocalizedFormats.EXPONENT, Integer.valueOf(i));
                        throw e;
                    }
                }
                i2 >>= 1;
                if (i2 == 0) {
                    return j2;
                }
                j3 = mulAndCheck(j3, j3);
            }
        } else {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i));
        }
    }

    @Deprecated
    public static long pow(long j, long j2) throws NotPositiveException {
        if (j2 >= 0) {
            long j3 = 1;
            while (j2 != 0) {
                if ((j2 & 1) != 0) {
                    j3 *= j;
                }
                j *= j;
                j2 >>= 1;
            }
            return j3;
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j2));
    }

    public static BigInteger pow(BigInteger bigInteger, int i) throws NotPositiveException {
        if (i >= 0) {
            return bigInteger.pow(i);
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, Integer.valueOf(i));
    }

    public static BigInteger pow(BigInteger bigInteger, long j) throws NotPositiveException {
        if (j >= 0) {
            BigInteger bigInteger2 = BigInteger.ONE;
            while (j != 0) {
                if ((1 & j) != 0) {
                    bigInteger2 = bigInteger2.multiply(bigInteger);
                }
                bigInteger = bigInteger.multiply(bigInteger);
                j >>= 1;
            }
            return bigInteger2;
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, Long.valueOf(j));
    }

    public static BigInteger pow(BigInteger bigInteger, BigInteger bigInteger2) throws NotPositiveException {
        if (bigInteger2.compareTo(BigInteger.ZERO) >= 0) {
            BigInteger bigInteger3 = BigInteger.ONE;
            while (!BigInteger.ZERO.equals(bigInteger2)) {
                if (bigInteger2.testBit(0)) {
                    bigInteger3 = bigInteger3.multiply(bigInteger);
                }
                bigInteger = bigInteger.multiply(bigInteger);
                bigInteger2 = bigInteger2.shiftRight(1);
            }
            return bigInteger3;
        }
        throw new NotPositiveException(LocalizedFormats.EXPONENT, bigInteger2);
    }

    @Deprecated
    public static long stirlingS2(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        return CombinatoricsUtils.stirlingS2(i, i2);
    }

    private static long addAndCheck(long j, long j2, Localizable localizable) throws MathArithmeticException {
        long j3 = j + j2;
        if (((j ^ j2) < 0) || ((j ^ j3) >= 0)) {
            return j3;
        }
        throw new MathArithmeticException(localizable, Long.valueOf(j), Long.valueOf(j2));
    }
}
