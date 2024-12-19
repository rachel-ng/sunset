package org.apache.commons.math3.util;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public final class CombinatoricsUtils {
    static final long[] FACTORIALS = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    static final AtomicReference<long[][]> STIRLING_S2 = new AtomicReference<>((Object) null);

    private CombinatoricsUtils() {
    }

    public static long binomialCoefficient(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        long j = 1;
        if (i == i2 || i2 == 0) {
            return 1;
        }
        if (i2 == 1 || i2 == i - 1) {
            return (long) i;
        }
        if (i2 > i / 2) {
            return binomialCoefficient(i, i - i2);
        }
        if (i <= 61) {
            int i3 = (i - i2) + 1;
            for (int i4 = 1; i4 <= i2; i4++) {
                j = (j * ((long) i3)) / ((long) i4);
                i3++;
            }
        } else if (i <= 66) {
            int i5 = (i - i2) + 1;
            for (int i6 = 1; i6 <= i2; i6++) {
                long gcd = (long) ArithmeticUtils.gcd(i5, i6);
                j = (j / (((long) i6) / gcd)) * (((long) i5) / gcd);
                i5++;
            }
        } else {
            int i7 = (i - i2) + 1;
            for (int i8 = 1; i8 <= i2; i8++) {
                long gcd2 = (long) ArithmeticUtils.gcd(i7, i8);
                j = ArithmeticUtils.mulAndCheck(j / (((long) i8) / gcd2), ((long) i7) / gcd2);
                i7++;
            }
        }
        return j;
    }

    public static double binomialCoefficientDouble(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double d = 1.0d;
        if (i == i2 || i2 == 0) {
            return 1.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return (double) i;
        }
        if (i2 > i / 2) {
            return binomialCoefficientDouble(i, i - i2);
        }
        if (i < 67) {
            return (double) binomialCoefficient(i, i2);
        }
        for (int i3 = 1; i3 <= i2; i3++) {
            d *= ((double) ((i - i2) + i3)) / ((double) i3);
        }
        return FastMath.floor(d + 0.5d);
    }

    public static double binomialCoefficientLog(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        checkBinomial(i, i2);
        double d = 0.0d;
        if (i == i2 || i2 == 0) {
            return 0.0d;
        }
        if (i2 == 1 || i2 == i - 1) {
            return FastMath.log((double) i);
        }
        if (i < 67) {
            return FastMath.log((double) binomialCoefficient(i, i2));
        }
        if (i < 1030) {
            return FastMath.log(binomialCoefficientDouble(i, i2));
        }
        if (i2 > i / 2) {
            return binomialCoefficientLog(i, i - i2);
        }
        for (int i3 = (i - i2) + 1; i3 <= i; i3++) {
            d += FastMath.log((double) i3);
        }
        for (int i4 = 2; i4 <= i2; i4++) {
            d -= FastMath.log((double) i4);
        }
        return d;
    }

    public static long factorial(int i) throws NotPositiveException, MathArithmeticException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i <= 20) {
            return FACTORIALS[i];
        } else {
            throw new MathArithmeticException();
        }
    }

    public static double factorialDouble(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i < 21) {
            return (double) FACTORIALS[i];
        } else {
            return FastMath.floor(FastMath.exp(factorialLog(i)) + 0.5d);
        }
    }

    public static double factorialLog(int i) throws NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.FACTORIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        } else if (i < 21) {
            return FastMath.log((double) FACTORIALS[i]);
        } else {
            double d = 0.0d;
            for (int i2 = 2; i2 <= i; i2++) {
                d += FastMath.log((double) i2);
            }
            return d;
        }
    }

    public static long stirlingS2(int i, int i2) throws NotPositiveException, NumberIsTooLargeException, MathArithmeticException {
        int i3 = i;
        int i4 = i2;
        if (i4 < 0) {
            throw new NotPositiveException(Integer.valueOf(i2));
        } else if (i4 <= i3) {
            long[][] jArr = STIRLING_S2.get();
            char c2 = 0;
            long j = 1;
            if (jArr == null) {
                long[][] jArr2 = new long[26][];
                jArr2[0] = new long[]{1};
                int i5 = 1;
                while (i5 < 26) {
                    int i6 = i5 + 1;
                    long[] jArr3 = new long[i6];
                    jArr2[i5] = jArr3;
                    jArr3[c2] = 0;
                    jArr3[1] = j;
                    jArr3[i5] = j;
                    int i7 = 2;
                    while (i7 < i5) {
                        long[] jArr4 = jArr2[i5];
                        long[] jArr5 = jArr2[i5 - 1];
                        jArr4[i7] = (((long) i7) * jArr5[i7]) + jArr5[i7 - 1];
                        i7++;
                        c2 = 0;
                        j = 1;
                    }
                    i5 = i6;
                }
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(STIRLING_S2, (Object) null, jArr2);
                jArr = jArr2;
            }
            if (i3 < jArr.length) {
                return jArr[i3][i4];
            }
            if (i4 == 0) {
                return 0;
            }
            if (i4 == 1 || i4 == i3) {
                return 1;
            }
            if (i4 == 2) {
                return (1 << (i3 - 1)) - 1;
            }
            if (i4 == i3 - 1) {
                return binomialCoefficient(i3, 2);
            }
            long j2 = (i4 & 1) == 0 ? 1 : -1;
            int i8 = 1;
            long j3 = 0;
            while (i8 <= i4) {
                j2 = -j2;
                long[][] jArr6 = jArr;
                j3 += binomialCoefficient(i4, i8) * j2 * ((long) ArithmeticUtils.pow(i8, i3));
                if (j3 >= 0) {
                    i8++;
                    jArr = jArr6;
                } else {
                    throw new MathArithmeticException(LocalizedFormats.ARGUMENT_OUTSIDE_DOMAIN, Integer.valueOf(i), 0, Integer.valueOf(jArr6.length - 1));
                }
            }
            return j3 / factorial(i2);
        } else {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), Integer.valueOf(i), true);
        }
    }

    public static Iterator<int[]> combinationsIterator(int i, int i2) {
        return new Combinations(i, i2).iterator();
    }

    public static void checkBinomial(int i, int i2) throws NumberIsTooLargeException, NotPositiveException {
        if (i < i2) {
            throw new NumberIsTooLargeException(LocalizedFormats.BINOMIAL_INVALID_PARAMETERS_ORDER, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.BINOMIAL_NEGATIVE_PARAMETER, Integer.valueOf(i));
        }
    }
}
