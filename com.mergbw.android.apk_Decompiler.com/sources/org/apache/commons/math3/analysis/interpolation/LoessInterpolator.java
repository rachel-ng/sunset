package org.apache.commons.math3.analysis.interpolation;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class LoessInterpolator implements UnivariateInterpolator, Serializable {
    public static final double DEFAULT_ACCURACY = 1.0E-12d;
    public static final double DEFAULT_BANDWIDTH = 0.3d;
    public static final int DEFAULT_ROBUSTNESS_ITERS = 2;
    private static final long serialVersionUID = 5204927143605193821L;
    private final double accuracy;
    private final double bandwidth;
    private final int robustnessIters;

    public LoessInterpolator() {
        this.bandwidth = 0.3d;
        this.robustnessIters = 2;
        this.accuracy = 1.0E-12d;
    }

    public LoessInterpolator(double d, int i) {
        this(d, i, 1.0E-12d);
    }

    public LoessInterpolator(double d, int i, double d2) throws OutOfRangeException, NotPositiveException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.BANDWIDTH, Double.valueOf(d), 0, 1);
        }
        this.bandwidth = d;
        if (i >= 0) {
            this.robustnessIters = i;
            this.accuracy = d2;
            return;
        }
        throw new NotPositiveException(LocalizedFormats.ROBUSTNESS_ITERATIONS, Integer.valueOf(i));
    }

    public final PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        return new SplineInterpolator().interpolate(dArr, smooth(dArr, dArr2));
    }

    public final double[] smooth(double[] dArr, double[] dArr2, double[] dArr3) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[] dArr6 = dArr3;
        if (dArr4.length == dArr5.length) {
            int length = dArr4.length;
            if (length != 0) {
                checkAllFiniteReal(dArr);
                checkAllFiniteReal(dArr2);
                checkAllFiniteReal(dArr3);
                MathArrays.checkOrder(dArr);
                int i = 0;
                char c2 = 1;
                if (length == 1) {
                    return new double[]{dArr5[0]};
                } else if (length == 2) {
                    return new double[]{dArr5[0], dArr5[1]};
                } else {
                    int i2 = (int) (this.bandwidth * ((double) length));
                    if (i2 >= 2) {
                        double[] dArr7 = new double[length];
                        double[] dArr8 = new double[length];
                        double[] dArr9 = new double[length];
                        double[] dArr10 = new double[length];
                        Arrays.fill(dArr10, 1.0d);
                        int i3 = 0;
                        while (i3 <= this.robustnessIters) {
                            int[] iArr = {i, i2 - 1};
                            int i4 = i;
                            while (true) {
                                double d = 0.0d;
                                if (i4 >= length) {
                                    break;
                                }
                                double d2 = dArr4[i4];
                                if (i4 > 0) {
                                    updateBandwidthInterval(dArr4, dArr6, i4, iArr);
                                }
                                int i5 = iArr[i];
                                int i6 = iArr[c2];
                                double d3 = dArr4[i4];
                                double abs = FastMath.abs(1.0d / (dArr4[d3 - dArr4[i5] > dArr4[i6] - d3 ? i5 : i6] - d2));
                                double d4 = 0.0d;
                                double d5 = 0.0d;
                                double d6 = 0.0d;
                                double d7 = 0.0d;
                                double d8 = 0.0d;
                                while (i5 <= i6) {
                                    double d9 = dArr4[i5];
                                    double d10 = dArr5[i5];
                                    double tricube = tricube((i5 < i4 ? d2 - d9 : d9 - d2) * abs) * dArr10[i5] * dArr6[i5];
                                    double d11 = d9 * tricube;
                                    d5 += tricube;
                                    d4 += d11;
                                    d8 += d9 * d11;
                                    d6 += tricube * d10;
                                    d7 += d10 * d11;
                                    i5++;
                                }
                                double d12 = d4 / d5;
                                double d13 = d6 / d5;
                                double d14 = d7 / d5;
                                double d15 = (d8 / d5) - (d12 * d12);
                                double[] dArr11 = dArr7;
                                if (FastMath.sqrt(FastMath.abs(d15)) >= this.accuracy) {
                                    d = (d14 - (d12 * d13)) / d15;
                                }
                                double d16 = (d * d2) + (d13 - (d12 * d));
                                dArr11[i4] = d16;
                                dArr8[i4] = FastMath.abs(dArr5[i4] - d16);
                                i4++;
                                dArr7 = dArr11;
                                i = 0;
                                c2 = 1;
                            }
                            double[] dArr12 = dArr7;
                            if (i3 == this.robustnessIters) {
                                return dArr12;
                            }
                            System.arraycopy(dArr8, 0, dArr9, 0, length);
                            Arrays.sort(dArr9);
                            double d17 = dArr9[length / 2];
                            if (FastMath.abs(d17) < this.accuracy) {
                                return dArr12;
                            }
                            for (int i7 = 0; i7 < length; i7++) {
                                double d18 = dArr8[i7] / (6.0d * d17);
                                if (d18 >= 1.0d) {
                                    dArr10[i7] = 0.0d;
                                } else {
                                    double d19 = 1.0d - (d18 * d18);
                                    dArr10[i7] = d19 * d19;
                                }
                            }
                            i3++;
                            dArr7 = dArr12;
                            i = 0;
                            c2 = 1;
                        }
                        return dArr7;
                    }
                    throw new NumberIsTooSmallException(LocalizedFormats.BANDWIDTH, Integer.valueOf(i2), 2, true);
                }
            } else {
                throw new NoDataException();
            }
        } else {
            throw new DimensionMismatchException(dArr4.length, dArr5.length);
        }
    }

    public final double[] smooth(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException, DimensionMismatchException, NoDataException, NotFiniteNumberException, NumberIsTooSmallException {
        if (dArr.length == dArr2.length) {
            double[] dArr3 = new double[dArr.length];
            Arrays.fill(dArr3, 1.0d);
            return smooth(dArr, dArr2, dArr3);
        }
        throw new DimensionMismatchException(dArr.length, dArr2.length);
    }

    private static void updateBandwidthInterval(double[] dArr, double[] dArr2, int i, int[] iArr) {
        int i2 = iArr[0];
        int nextNonzero = nextNonzero(dArr2, iArr[1]);
        if (nextNonzero < dArr.length) {
            double d = dArr[nextNonzero];
            double d2 = dArr[i];
            if (d - d2 < d2 - dArr[i2]) {
                iArr[0] = nextNonzero(dArr2, iArr[0]);
                iArr[1] = nextNonzero;
            }
        }
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private static int nextNonzero(double[] r4, int r5) {
        /*
        L_0x0000:
            int r5 = r5 + 1
            int r0 = r4.length
            if (r5 >= r0) goto L_0x000e
            r0 = r4[r5]
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x000e
            goto L_0x0000
        L_0x000e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.interpolation.LoessInterpolator.nextNonzero(double[], int):int");
    }

    private static double tricube(double d) {
        double abs = FastMath.abs(d);
        if (abs >= 1.0d) {
            return 0.0d;
        }
        double d2 = 1.0d - ((abs * abs) * abs);
        return d2 * d2 * d2;
    }

    private static void checkAllFiniteReal(double[] dArr) {
        for (double checkFinite : dArr) {
            MathUtils.checkFinite(checkFinite);
        }
    }
}
