package org.apache.commons.math3.stat.inference;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class ChiSquareTest {
    public double chiSquare(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException {
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
                    double d7 = dArr[i2];
                    double d8 = ((double) jArr[i2]) - (d * d7);
                    d3 = d8 * d8;
                    d2 = d7 * d;
                } else {
                    d2 = dArr[i2];
                    double d9 = ((double) jArr[i2]) - d2;
                    d3 = d9 * d9;
                }
                d4 += d3 / d2;
            }
            return d4;
        } else {
            throw new DimensionMismatchException(dArr.length, jArr.length);
        }
    }

    public double chiSquareTest(double[] dArr, long[] jArr) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, ((double) dArr.length) - 1.0d).cumulativeProbability(chiSquare(dArr, jArr));
    }

    public boolean chiSquareTest(double[] dArr, long[] jArr, double d) throws NotPositiveException, NotStrictlyPositiveException, DimensionMismatchException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTest(dArr, jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    public double chiSquare(long[][] jArr) throws NullArgumentException, NotPositiveException, DimensionMismatchException {
        long[][] jArr2 = jArr;
        checkArray(jArr);
        int length = jArr2.length;
        int i = 0;
        int length2 = jArr2[0].length;
        double[] dArr = new double[length];
        double[] dArr2 = new double[length2];
        int i2 = 0;
        double d = 0.0d;
        while (i2 < length) {
            int i3 = i;
            while (i3 < length2) {
                double d2 = dArr[i2];
                long j = jArr2[i2][i3];
                dArr[i2] = d2 + ((double) j);
                dArr2[i3] = dArr2[i3] + ((double) j);
                d += (double) j;
                i3++;
                length2 = length2;
            }
            int i4 = length2;
            i2++;
            i = 0;
        }
        int i5 = length2;
        int i6 = 0;
        double d3 = 0.0d;
        while (i6 < length) {
            int i7 = i5;
            int i8 = 0;
            while (i8 < i7) {
                double d4 = (dArr[i6] * dArr2[i8]) / d;
                long j2 = jArr2[i6][i8];
                d3 += ((((double) j2) - d4) * (((double) j2) - d4)) / d4;
                i8++;
                jArr2 = jArr;
                length = length;
            }
            int i9 = length;
            i6++;
            jArr2 = jArr;
            i5 = i7;
        }
        return d3;
    }

    public double chiSquareTest(long[][] jArr) throws NullArgumentException, DimensionMismatchException, NotPositiveException, MaxCountExceededException {
        checkArray(jArr);
        return 1.0d - new ChiSquaredDistribution((((double) jArr.length) - 1.0d) * (((double) jArr[0].length) - 1.0d)).cumulativeProbability(chiSquare(jArr));
    }

    public boolean chiSquareTest(long[][] jArr, double d) throws NullArgumentException, DimensionMismatchException, NotPositiveException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTest(jArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    public double chiSquareDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException {
        long[] jArr3 = jArr;
        long[] jArr4 = jArr2;
        if (jArr3.length < 2) {
            throw new DimensionMismatchException(jArr3.length, 2);
        } else if (jArr3.length == jArr4.length) {
            MathArrays.checkNonNegative(jArr);
            MathArrays.checkNonNegative(jArr2);
            char c2 = 0;
            long j = 0;
            long j2 = 0;
            long j3 = 0;
            for (int i = 0; i < jArr3.length; i++) {
                j2 += jArr3[i];
                j3 += jArr4[i];
            }
            if (j2 == 0 || j3 == 0) {
                throw new ZeroException();
            }
            boolean z = j2 != j3;
            double d = 0.0d;
            double sqrt = z ? FastMath.sqrt(((double) j2) / ((double) j3)) : 0.0d;
            int i2 = 0;
            while (i2 < jArr3.length) {
                long j4 = jArr3[i2];
                if (j4 == j && jArr4[i2] == j) {
                    LocalizedFormats localizedFormats = LocalizedFormats.OBSERVED_COUNTS_BOTTH_ZERO_FOR_ENTRY;
                    Object[] objArr = new Object[1];
                    objArr[c2] = Integer.valueOf(i2);
                    throw new ZeroException(localizedFormats, objArr);
                }
                double d2 = (double) j4;
                double d3 = (double) jArr4[i2];
                double d4 = z ? (d2 / sqrt) - (d3 * sqrt) : d2 - d3;
                d += (d4 * d4) / (d2 + d3);
                i2++;
                c2 = 0;
                j = 0;
            }
            return d;
        } else {
            throw new DimensionMismatchException(jArr3.length, jArr4.length);
        }
    }

    public double chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2) throws DimensionMismatchException, NotPositiveException, ZeroException, MaxCountExceededException {
        return 1.0d - new ChiSquaredDistribution((RandomGenerator) null, ((double) jArr.length) - 1.0d).cumulativeProbability(chiSquareDataSetsComparison(jArr, jArr2));
    }

    public boolean chiSquareTestDataSetsComparison(long[] jArr, long[] jArr2, double d) throws DimensionMismatchException, NotPositiveException, ZeroException, OutOfRangeException, MaxCountExceededException {
        if (d > 0.0d && d <= 0.5d) {
            return chiSquareTestDataSetsComparison(jArr, jArr2) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    private void checkArray(long[][] jArr) throws NullArgumentException, DimensionMismatchException, NotPositiveException {
        if (jArr.length < 2) {
            throw new DimensionMismatchException(jArr.length, 2);
        } else if (jArr[0].length >= 2) {
            MathArrays.checkRectangular(jArr);
            MathArrays.checkNonNegative(jArr);
        } else {
            throw new DimensionMismatchException(jArr[0].length, 2);
        }
    }
}
