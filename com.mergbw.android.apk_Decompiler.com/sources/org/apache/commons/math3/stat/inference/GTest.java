package org.apache.commons.math3.stat.inference;

import java.lang.reflect.Array;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class GTest {
    public double g(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
        double d;
        boolean z;
        double d2;
        double d3;
        if (dArr.length < 2) {
            throw new DimensionMismatchException(dArr.length, 2);
        } else if (dArr.length == jArr.length) {
            MathArrays.checkPositive(dArr);
            MathArrays.checkNonNegative(jArr);
            double d4 = 0.0d;
            double d5 = 0.0d;
            double d6 = 0.0d;
            for (int i = 0; i < jArr.length; i++) {
                d5 += dArr[i];
                d6 += (double) jArr[i];
            }
            if (FastMath.abs(d5 - d6) > 1.0E-5d) {
                d = d6 / d5;
                z = true;
            } else {
                d = 1.0d;
                z = false;
            }
            for (int i2 = 0; i2 < jArr.length; i2++) {
                if (z) {
                    d3 = (double) jArr[i2];
                    d2 = dArr[i2] * d;
                } else {
                    d3 = (double) jArr[i2];
                    d2 = dArr[i2];
                }
                d4 += ((double) jArr[i2]) * FastMath.log(d3 / d2);
            }
            return d4 * 2.0d;
        } else {
            throw new DimensionMismatchException(dArr.length, jArr.length);
        }
    }

    public double gTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, ((double) dArr.length) - 1.0d).cumulativeProbability(g(dArr, jArr));
    }

    public double gTestIntrinsic(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, ((double) dArr.length) - 2.0d).cumulativeProbability(g(dArr, jArr));
    }

    public boolean gTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return gTest(dArr, jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    private double entropy(long[][] jArr) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (long[] jArr2 : jArr) {
            int i = 0;
            while (true) {
                if (i >= jArr2.length) {
                    break;
                }
                d2 += (double) jArr2[i];
                i++;
            }
        }
        for (long[] jArr3 : jArr) {
            int i2 = 0;
            while (true) {
                if (i2 >= jArr3.length) {
                    break;
                }
                long j = jArr3[i2];
                if (j != 0) {
                    double d3 = ((double) j) / d2;
                    d += d3 * FastMath.log(d3);
                }
                i2++;
            }
        }
        return -d;
    }

    private double entropy(long[] jArr) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (long j : jArr) {
            d2 += (double) j;
        }
        for (long j2 : jArr) {
            if (j2 != 0) {
                double d3 = ((double) j2) / d2;
                d += d3 * FastMath.log(d3);
            }
        }
        return -d;
    }

    public double gDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (jArr3.length < 2) {
            throw new DimensionMismatchException(jArr3.length, 2);
        } else if (jArr3.length == jArr4.length) {
            MathArrays.checkNonNegative(jArr);
            MathArrays.checkNonNegative(jArr2);
            long[] jArr5 = new long[jArr3.length];
            int[] iArr = new int[2];
            iArr[1] = jArr3.length;
            iArr[0] = 2;
            long[][] jArr6 = (long[][]) Array.newInstance(Long.TYPE, iArr);
            long j = 0;
            long j2 = 0;
            for (int i = 0; i < jArr3.length; i++) {
                long j3 = jArr3[i];
                if (j3 == 0 && jArr4[i] == 0) {
                    throw new ZeroException(LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY, Integer.valueOf(i));
                }
                j += j3;
                long j4 = jArr4[i];
                j2 += j4;
                jArr5[i] = j3 + j4;
                jArr6[0][i] = jArr3[i];
                jArr6[1][i] = jArr4[i];
            }
            if (j == 0 || j2 == 0) {
                throw new ZeroException();
            }
            return (((double) j) + ((double) j2)) * 2.0d * ((entropy(new long[]{j, j2}) + entropy(jArr5)) - entropy(jArr6));
        } else {
            throw new DimensionMismatchException(jArr3.length, jArr4.length);
        }
    }

    public double rootLogLikelihoodRatio(long j, long j2, long j3, long j4) {
        double sqrt = FastMath.sqrt(gDataSetsComparison(new long[]{j, j2}, new long[]{j3, j4}));
        return ((double) j) / ((double) (j + j2)) < ((double) j3) / ((double) (j3 + j4)) ? -sqrt : sqrt;
    }

    public double gTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, ((double) jArr.length) - 1.0d).cumulativeProbability(gDataSetsComparison(jArr, jArr2));
    }

    public boolean gTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return gTestDataSetsComparison(jArr, jArr2) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }
}
