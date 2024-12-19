package org.apache.commons.math3.stat.inference;

import androidx.work.WorkRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fraction.BigFraction;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class KolmogorovSmirnovTest {
    protected static final double KS_SUM_CAUCHY_CRITERION = 1.0E-20d;
    protected static final int LARGE_SAMPLE_PRODUCT = 10000;
    protected static final int MAXIMUM_PARTIAL_SUM_COUNT = 100000;
    protected static final int MONTE_CARLO_ITERATIONS = 1000000;
    protected static final double PG_SUM_RELATIVE_ERROR = 1.0E-10d;
    protected static final int SMALL_SAMPLE_PRODUCT = 200;
    private final RandomGenerator rng;

    public KolmogorovSmirnovTest() {
        this.rng = new Well19937c();
    }

    public KolmogorovSmirnovTest(RandomGenerator randomGenerator) {
        this.rng = randomGenerator;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, boolean z) {
        return 1.0d - cdf(kolmogorovSmirnovStatistic(realDistribution, dArr), dArr.length, z);
    }

    public double kolmogorovSmirnovStatistic(RealDistribution realDistribution, double[] dArr) {
        checkArray(dArr);
        int length = dArr.length;
        double d = (double) length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, length);
        Arrays.sort(dArr2);
        double d2 = 0.0d;
        for (int i = 1; i <= length; i++) {
            int i2 = i - 1;
            double cumulativeProbability = realDistribution.cumulativeProbability(dArr2[i2]);
            double max = FastMath.max(cumulativeProbability - (((double) i2) / d), (((double) i) / d) - cumulativeProbability);
            if (max > d2) {
                d2 = max;
            }
        }
        return d2;
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2, boolean z) {
        long length = ((long) dArr.length) * ((long) dArr2.length);
        if (length < 200) {
            return exactP(kolmogorovSmirnovStatistic(dArr, dArr2), dArr.length, dArr2.length, z);
        } else if (length >= WorkRequest.MIN_BACKOFF_MILLIS) {
            return approximateP(kolmogorovSmirnovStatistic(dArr, dArr2), dArr.length, dArr2.length);
        } else {
            return monteCarloP(kolmogorovSmirnovStatistic(dArr, dArr2), dArr.length, dArr2.length, z, 1000000);
        }
    }

    public double kolmogorovSmirnovTest(double[] dArr, double[] dArr2) {
        return kolmogorovSmirnovTest(dArr, dArr2, true);
    }

    public double kolmogorovSmirnovStatistic(double[] dArr, double[] dArr2) {
        double d;
        checkArray(dArr);
        checkArray(dArr2);
        double[] copyOf = MathArrays.copyOf(dArr);
        double[] copyOf2 = MathArrays.copyOf(dArr2);
        Arrays.sort(copyOf);
        Arrays.sort(copyOf2);
        int length = copyOf.length;
        int length2 = copyOf2.length;
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        while (true) {
            d = 1.0d;
            if (i2 >= length) {
                break;
            }
            double d3 = (((double) i2) + 1.0d) / ((double) length);
            int binarySearch = Arrays.binarySearch(copyOf2, copyOf[i2]);
            double abs = FastMath.abs(d3 - ((binarySearch >= 0 ? ((double) binarySearch) + 1.0d : ((double) (-binarySearch)) - 1.0d) / ((double) length2)));
            if (abs > d2) {
                d2 = abs;
            }
            i2++;
        }
        while (i < length2) {
            double d4 = (((double) i) + d) / ((double) length2);
            int binarySearch2 = Arrays.binarySearch(copyOf, copyOf2[i]);
            double abs2 = FastMath.abs((binarySearch2 >= 0 ? (((double) binarySearch2) + d) / ((double) length) : (((double) (-binarySearch2)) - 1.0d) / ((double) length)) - d4);
            if (abs2 > d2) {
                d2 = abs2;
            }
            i++;
            d = 1.0d;
        }
        return d2;
    }

    public double kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr) {
        return kolmogorovSmirnovTest(realDistribution, dArr, false);
    }

    public boolean kolmogorovSmirnovTest(RealDistribution realDistribution, double[] dArr, double d) {
        if (d > 0.0d && d <= 0.5d) {
            return kolmogorovSmirnovTest(realDistribution, dArr) < d;
        }
        throw new OutOfRangeException(LocalizedFormats.OUT_OF_BOUND_SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, Double.valueOf(0.5d));
    }

    public double cdf(double d, int i) throws MathArithmeticException {
        return cdf(d, i, false);
    }

    public double cdfExact(double d, int i) throws MathArithmeticException {
        return cdf(d, i, true);
    }

    public double cdf(double d, int i, boolean z) throws MathArithmeticException {
        double d2 = (double) i;
        double d3 = 1.0d;
        double d4 = 1.0d / d2;
        double d5 = 0.5d * d4;
        if (d <= d5) {
            return 0.0d;
        }
        if (d5 < d && d <= d4) {
            double d6 = (d * 2.0d) - d4;
            for (int i2 = 1; i2 <= i; i2++) {
                d3 *= ((double) i2) * d6;
            }
            return d3;
        } else if (1.0d - d4 <= d && d < 1.0d) {
            return 1.0d - (Math.pow(1.0d - d, d2) * 2.0d);
        } else {
            if (1.0d <= d) {
                return 1.0d;
            }
            if (z) {
                return exactK(d, i);
            }
            if (i <= 140) {
                return roundedK(d, i);
            }
            return pelzGood(d, i);
        }
    }

    private double exactK(double d, int i) throws MathArithmeticException {
        int ceil = ((int) Math.ceil(((double) i) * d)) - 1;
        BigFraction entry = createExactH(d, i).power(i).getEntry(ceil, ceil);
        for (int i2 = 1; i2 <= i; i2++) {
            entry = entry.multiply(i2).divide(i);
        }
        return entry.bigDecimalValue(20, 4).doubleValue();
    }

    private double roundedK(double d, int i) {
        double d2 = (double) i;
        int ceil = ((int) Math.ceil(d2 * d)) - 1;
        double entry = createRoundedH(d, i).power(i).getEntry(ceil, ceil);
        for (int i2 = 1; i2 <= i; i2++) {
            entry *= ((double) i2) / d2;
        }
        return entry;
    }

    public double pelzGood(double d, int i) {
        double d2;
        double d3;
        int i2;
        int i3;
        double d4;
        double d5 = (double) i;
        double sqrt = FastMath.sqrt(d5);
        double d6 = d * sqrt;
        double d7 = d * d * d5;
        double d8 = d7 * d7;
        double d9 = d8 * d7;
        double d10 = 9.869604401089358d / (8.0d * d7);
        double d11 = 0.0d;
        double d12 = d8 * d8;
        double d13 = 0.0d;
        int i4 = 1;
        while (true) {
            if (i4 >= 100000) {
                d2 = d5;
                d3 = d9;
                i2 = 100000;
                break;
            }
            d2 = d5;
            double d14 = (double) ((i4 * 2) - 1);
            d3 = d9;
            double exp = FastMath.exp((-d10) * d14 * d14);
            d13 += exp;
            if (exp <= d13 * 1.0E-10d) {
                i2 = 100000;
                break;
            }
            i4++;
            d5 = d2;
            d9 = d3;
        }
        if (i4 != i2) {
            double sqrt2 = (d13 * FastMath.sqrt(6.283185307179586d)) / d6;
            double d15 = d7 * 2.0d;
            double d16 = 0.0d;
            int i5 = 0;
            while (true) {
                if (i5 >= i2) {
                    break;
                }
                double d17 = ((double) i5) + 0.5d;
                double d18 = d17 * d17;
                double exp2 = ((d18 * 9.869604401089358d) - d7) * FastMath.exp((d18 * -9.869604401089358d) / d15);
                d16 += exp2;
                if (FastMath.abs(exp2) < FastMath.abs(d16) * 1.0E-10d) {
                    i2 = 100000;
                    break;
                }
                i5++;
                i2 = 100000;
            }
            if (i5 != i2) {
                double sqrt3 = FastMath.sqrt(1.5707963267948966d);
                double d19 = sqrt2 + ((d16 * sqrt3) / ((3.0d * d8) * sqrt));
                double d20 = 2.0d * d8;
                double d21 = 6.0d * d3;
                double d22 = d7 * 5.0d;
                double d23 = 0.0d;
                int i6 = 0;
                while (true) {
                    i3 = 100000;
                    d4 = sqrt;
                    if (i6 >= 100000) {
                        break;
                    }
                    double d24 = ((double) i6) + 0.5d;
                    double d25 = d24 * d24;
                    double exp3 = (d21 + d20 + ((d20 - d22) * 9.869604401089358d * d25) + ((1.0d - d15) * 97.40909103400243d * d25 * d25)) * FastMath.exp((d25 * -9.869604401089358d) / d15);
                    d23 += exp3;
                    if (FastMath.abs(exp3) < FastMath.abs(d23) * 1.0E-10d) {
                        i3 = 100000;
                        break;
                    }
                    i6++;
                    sqrt = d4;
                }
                if (i6 != i3) {
                    double d26 = 0.0d;
                    int i7 = 1;
                    while (true) {
                        if (i7 >= i3) {
                            break;
                        }
                        double d27 = (double) (i7 * i7);
                        double exp4 = d27 * 9.869604401089358d * FastMath.exp((d27 * -9.869604401089358d) / d15);
                        d26 += exp4;
                        if (FastMath.abs(exp4) < FastMath.abs(d26) * 1.0E-10d) {
                            i3 = 100000;
                            break;
                        }
                        i7++;
                        i3 = 100000;
                    }
                    if (i7 != i3) {
                        double d28 = d19 + ((sqrt3 / d2) * ((d23 / ((((36.0d * d7) * d7) * d7) * d6)) - (d26 / ((18.0d * d7) * d6))));
                        double d29 = 0.0d;
                        int i8 = 100000;
                        int i9 = 0;
                        while (true) {
                            if (i9 >= i8) {
                                break;
                            }
                            double d30 = ((double) i9) + 0.5d;
                            double d31 = d30 * d30;
                            double d32 = d31 * d31;
                            double d33 = d12;
                            double exp5 = (((((((d32 * d31) * 961.3891935753043d) * (5.0d - (d7 * 30.0d))) + ((d32 * 97.40909103400243d) * ((-60.0d * d7) + (212.0d * d8)))) + ((d31 * 9.869604401089358d) * ((135.0d * d8) - (96.0d * d3)))) - (d3 * 30.0d)) - (90.0d * d33)) * FastMath.exp((d31 * -9.869604401089358d) / d15);
                            d29 += exp5;
                            if (FastMath.abs(exp5) < FastMath.abs(d29) * 1.0E-10d) {
                                i8 = 100000;
                                break;
                            }
                            i9++;
                            d12 = d33;
                            i8 = 100000;
                        }
                        if (i9 != i8) {
                            int i10 = 1;
                            while (true) {
                                if (i10 >= i8) {
                                    break;
                                }
                                double d34 = (double) (i10 * i10);
                                double exp6 = ((d34 * d34 * -97.40909103400243d) + (29.608813203268074d * d34 * d7)) * FastMath.exp((d34 * -9.869604401089358d) / d15);
                                d11 += exp6;
                                if (FastMath.abs(exp6) < FastMath.abs(d11) * 1.0E-10d) {
                                    i8 = 100000;
                                    break;
                                }
                                i10++;
                                i8 = 100000;
                            }
                            if (i10 != i8) {
                                return d28 + ((sqrt3 / (d4 * d2)) * ((d29 / ((3240.0d * d3) * d8)) + (d11 / (108.0d * d3))));
                            }
                            throw new TooManyIterationsException(100000);
                        }
                        throw new TooManyIterationsException(Integer.valueOf(i8));
                    }
                    throw new TooManyIterationsException(Integer.valueOf(i3));
                }
                throw new TooManyIterationsException(Integer.valueOf(i3));
            }
            throw new TooManyIterationsException(Integer.valueOf(i2));
        }
        throw new TooManyIterationsException(Integer.valueOf(i2));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(20:2|3|4|5|8|(4:10|(3:12|(2:14|46)(2:15|45)|16)|44|17)|43|18|(1:20)|47|21|(1:23)|48|24|(1:26)|27|(3:29|(2:30|(3:32|(3:34|(1:36)|52)(1:51)|37)(1:50))|38)|49|39|40) */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0033, code lost:
        r4 = new org.apache.commons.math3.fraction.BigFraction(r0, 1.0E-5d, 10000);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.apache.commons.math3.linear.FieldMatrix<org.apache.commons.math3.fraction.BigFraction> createExactH(double r12, int r14) throws org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.fraction.FractionConversionException {
        /*
            r11 = this;
            double r0 = (double) r14
            double r0 = r0 * r12
            double r12 = java.lang.Math.ceil(r0)
            int r12 = (int) r12
            int r13 = r12 * 2
            int r14 = r13 + -1
            double r2 = (double) r12
            double r0 = r2 - r0
            r2 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r10 = 0
            if (r12 >= 0) goto L_0x00f1
            org.apache.commons.math3.fraction.BigFraction r12 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x0024 }
            r7 = 4307583784117748259(0x3bc79ca10c924223, double:1.0E-20)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r12
            r5 = r0
            r4.<init>(r5, r7, r9)     // Catch:{ FractionConversionException -> 0x0024 }
            goto L_0x0041
        L_0x0024:
            org.apache.commons.math3.fraction.BigFraction r12 = new org.apache.commons.math3.fraction.BigFraction     // Catch:{ FractionConversionException -> 0x0033 }
            r7 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r12
            r5 = r0
            r4.<init>(r5, r7, r9)     // Catch:{ FractionConversionException -> 0x0033 }
            goto L_0x0041
        L_0x0033:
            org.apache.commons.math3.fraction.BigFraction r12 = new org.apache.commons.math3.fraction.BigFraction
            r7 = 4532020583610935537(0x3ee4f8b588e368f1, double:1.0E-5)
            r9 = 10000(0x2710, float:1.4013E-41)
            r4 = r12
            r5 = r0
            r4.<init>(r5, r7, r9)
        L_0x0041:
            r0 = 2
            int[] r1 = new int[r0]
            r2 = 1
            r1[r2] = r14
            r1[r10] = r14
            java.lang.Class<org.apache.commons.math3.fraction.BigFraction> r3 = org.apache.commons.math3.fraction.BigFraction.class
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r3, r1)
            org.apache.commons.math3.fraction.BigFraction[][] r1 = (org.apache.commons.math3.fraction.BigFraction[][]) r1
            r3 = r10
        L_0x0052:
            if (r3 >= r14) goto L_0x006f
            r4 = r10
        L_0x0055:
            if (r4 >= r14) goto L_0x006c
            int r5 = r3 - r4
            int r5 = r5 + r2
            if (r5 >= 0) goto L_0x0063
            r5 = r1[r3]
            org.apache.commons.math3.fraction.BigFraction r6 = org.apache.commons.math3.fraction.BigFraction.ZERO
            r5[r4] = r6
            goto L_0x0069
        L_0x0063:
            r5 = r1[r3]
            org.apache.commons.math3.fraction.BigFraction r6 = org.apache.commons.math3.fraction.BigFraction.ONE
            r5[r4] = r6
        L_0x0069:
            int r4 = r4 + 1
            goto L_0x0055
        L_0x006c:
            int r3 = r3 + 1
            goto L_0x0052
        L_0x006f:
            org.apache.commons.math3.fraction.BigFraction[] r3 = new org.apache.commons.math3.fraction.BigFraction[r14]
            r3[r10] = r12
            r4 = r2
        L_0x0074:
            if (r4 >= r14) goto L_0x0083
            int r5 = r4 + -1
            r5 = r3[r5]
            org.apache.commons.math3.fraction.BigFraction r5 = r12.multiply((org.apache.commons.math3.fraction.BigFraction) r5)
            r3[r4] = r5
            int r4 = r4 + 1
            goto L_0x0074
        L_0x0083:
            r4 = r10
        L_0x0084:
            if (r4 >= r14) goto L_0x00a6
            r5 = r1[r4]
            r6 = r5[r10]
            r7 = r3[r4]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.subtract((org.apache.commons.math3.fraction.BigFraction) r7)
            r5[r10] = r6
            int r5 = r13 + -2
            r5 = r1[r5]
            r6 = r5[r4]
            int r7 = r14 - r4
            int r7 = r7 - r2
            r7 = r3[r7]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.subtract((org.apache.commons.math3.fraction.BigFraction) r7)
            r5[r4] = r6
            int r4 = r4 + 1
            goto L_0x0084
        L_0x00a6:
            org.apache.commons.math3.fraction.BigFraction r3 = org.apache.commons.math3.fraction.BigFraction.ONE_HALF
            int r3 = r12.compareTo((org.apache.commons.math3.fraction.BigFraction) r3)
            if (r3 != r2) goto L_0x00c5
            int r13 = r13 - r0
            r13 = r1[r13]
            r3 = r13[r10]
            org.apache.commons.math3.fraction.BigFraction r12 = r12.multiply((int) r0)
            org.apache.commons.math3.fraction.BigFraction r12 = r12.subtract((int) r2)
            org.apache.commons.math3.fraction.BigFraction r12 = r12.pow((int) r14)
            org.apache.commons.math3.fraction.BigFraction r12 = r3.add((org.apache.commons.math3.fraction.BigFraction) r12)
            r13[r10] = r12
        L_0x00c5:
            r12 = r10
        L_0x00c6:
            if (r12 >= r14) goto L_0x00e7
            r13 = r10
        L_0x00c9:
            int r3 = r12 + 1
            if (r13 >= r3) goto L_0x00e5
            int r3 = r12 - r13
            int r3 = r3 + r2
            if (r3 <= 0) goto L_0x00e2
            r4 = r0
        L_0x00d3:
            if (r4 > r3) goto L_0x00e2
            r5 = r1[r12]
            r6 = r5[r13]
            org.apache.commons.math3.fraction.BigFraction r6 = r6.divide((int) r4)
            r5[r13] = r6
            int r4 = r4 + 1
            goto L_0x00d3
        L_0x00e2:
            int r13 = r13 + 1
            goto L_0x00c9
        L_0x00e5:
            r12 = r3
            goto L_0x00c6
        L_0x00e7:
            org.apache.commons.math3.linear.Array2DRowFieldMatrix r12 = new org.apache.commons.math3.linear.Array2DRowFieldMatrix
            org.apache.commons.math3.fraction.BigFractionField r13 = org.apache.commons.math3.fraction.BigFractionField.getInstance()
            r12.<init>(r13, (T[][]) r1)
            return r12
        L_0x00f1:
            org.apache.commons.math3.exception.NumberIsTooLargeException r12 = new org.apache.commons.math3.exception.NumberIsTooLargeException
            java.lang.Double r13 = java.lang.Double.valueOf(r0)
            java.lang.Double r14 = java.lang.Double.valueOf(r2)
            r12.<init>(r13, r14, r10)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.inference.KolmogorovSmirnovTest.createExactH(double, int):org.apache.commons.math3.linear.FieldMatrix");
    }

    private RealMatrix createRoundedH(double d, int i) throws NumberIsTooLargeException {
        int i2;
        double d2 = ((double) i) * d;
        int ceil = (int) Math.ceil(d2);
        int i3 = ceil * 2;
        int i4 = i3 - 1;
        double d3 = ((double) ceil) - d2;
        if (d3 < 1.0d) {
            int[] iArr = new int[2];
            iArr[1] = i4;
            iArr[0] = i4;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i5 = 0; i5 < i4; i5++) {
                for (int i6 = 0; i6 < i4; i6++) {
                    if ((i5 - i6) + 1 < 0) {
                        dArr[i5][i6] = 0.0d;
                    } else {
                        dArr[i5][i6] = 1.0d;
                    }
                }
            }
            double[] dArr2 = new double[i4];
            dArr2[0] = d3;
            for (int i7 = 1; i7 < i4; i7++) {
                dArr2[i7] = dArr2[i7 - 1] * d3;
            }
            for (int i8 = 0; i8 < i4; i8++) {
                double[] dArr3 = dArr[i8];
                dArr3[0] = dArr3[0] - dArr2[i8];
                double[] dArr4 = dArr[i3 - 2];
                dArr4[i8] = dArr4[i8] - dArr2[(i4 - i8) - 1];
            }
            if (Double.compare(d3, 0.5d) > 0) {
                double[] dArr5 = dArr[i3 - 2];
                dArr5[0] = dArr5[0] + FastMath.pow((d3 * 2.0d) - 1.0d, i4);
            }
            int i9 = 0;
            while (i9 < i4) {
                int i10 = 0;
                while (true) {
                    i2 = i9 + 1;
                    if (i10 >= i2) {
                        break;
                    }
                    int i11 = (i9 - i10) + 1;
                    if (i11 > 0) {
                        for (int i12 = 2; i12 <= i11; i12++) {
                            double[] dArr6 = dArr[i9];
                            dArr6[i10] = dArr6[i10] / ((double) i12);
                        }
                    }
                    i10++;
                }
                i9 = i2;
            }
            return MatrixUtils.createRealMatrix(dArr);
        }
        throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(1.0d), false);
    }

    private void checkArray(double[] dArr) {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
        } else if (dArr.length < 2) {
            throw new InsufficientDataException(LocalizedFormats.INSUFFICIENT_OBSERVED_POINTS_IN_SAMPLE, Integer.valueOf(dArr.length), 2);
        }
    }

    public double ksSum(double d, double d2, int i) {
        int i2 = i;
        double d3 = -2.0d * d * d;
        double d4 = 0.5d;
        double d5 = 1.0d;
        int i3 = -1;
        long j = 1;
        while (d5 > d2 && j < ((long) i2)) {
            double d6 = (double) j;
            d5 = FastMath.exp(d3 * d6 * d6);
            d4 += ((double) i3) * d5;
            i3 *= -1;
            j++;
        }
        if (j != ((long) i2)) {
            return d4 * 2.0d;
        }
        throw new TooManyIterationsException(Integer.valueOf(i));
    }

    public double exactP(double d, int i, int i2, boolean z) {
        int i3 = i;
        int i4 = i2;
        int i5 = i3 + i4;
        Iterator<int[]> combinationsIterator = CombinatoricsUtils.combinationsIterator(i5, i3);
        double[] dArr = new double[i3];
        double[] dArr2 = new double[i4];
        long j = 0;
        while (combinationsIterator.hasNext()) {
            int[] next = combinationsIterator.next();
            int i6 = 0;
            int i7 = 0;
            for (int i8 = 0; i8 < i5; i8++) {
                if (i6 >= i3 || next[i6] != i8) {
                    dArr2[i7] = (double) i8;
                    i7++;
                } else {
                    dArr[i6] = (double) i8;
                    i6++;
                }
            }
            int i9 = (kolmogorovSmirnovStatistic(dArr, dArr2) > d ? 1 : (kolmogorovSmirnovStatistic(dArr, dArr2) == d ? 0 : -1));
            if (i9 > 0 || (i9 == 0 && !z)) {
                j++;
            }
        }
        return ((double) j) / ((double) CombinatoricsUtils.binomialCoefficient(i5, i3));
    }

    public double approximateP(double d, int i, int i2) {
        double d2 = (double) i2;
        double d3 = (double) i;
        return 1.0d - ksSum(d * FastMath.sqrt((d2 * d3) / (d2 + d3)), KS_SUM_CAUCHY_CRITERION, 100000);
    }

    public double monteCarloP(double d, int i, int i2, boolean z, int i3) {
        int i4 = i;
        int i5 = i2;
        int i6 = i3;
        int[] natural = MathArrays.natural(i5 + i4);
        double[] dArr = new double[i4];
        double[] dArr2 = new double[i5];
        int i7 = 0;
        for (int i8 = 0; i8 < i6; i8++) {
            copyPartition(dArr, dArr2, natural, i, i2);
            int i9 = (kolmogorovSmirnovStatistic(dArr, dArr2) > d ? 1 : (kolmogorovSmirnovStatistic(dArr, dArr2) == d ? 0 : -1));
            if (i9 > 0 || (i9 == 0 && !z)) {
                i7++;
            }
            MathArrays.shuffle(natural, this.rng);
            Arrays.sort(natural, 0, i4);
        }
        return ((double) i7) / ((double) i6);
    }

    private void copyPartition(double[] dArr, double[] dArr2, int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i + i2; i5++) {
            if (i3 >= i || iArr[i3] != i5) {
                dArr2[i4] = (double) i5;
                i4++;
            } else {
                dArr[i3] = (double) i5;
                i3++;
            }
        }
    }
}
