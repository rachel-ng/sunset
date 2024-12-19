package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: BicubicSplineInterpolatingFunction */
class BicubicSplineFunction implements BivariateFunction {
    private static final short N = 4;

    /* renamed from: a  reason: collision with root package name */
    private final double[][] f63a;
    private final BivariateFunction partialDerivativeX;
    private final BivariateFunction partialDerivativeXX;
    private final BivariateFunction partialDerivativeXY;
    private final BivariateFunction partialDerivativeY;
    private final BivariateFunction partialDerivativeYY;

    public BicubicSplineFunction(double[] dArr) {
        this(dArr, false);
    }

    public BicubicSplineFunction(double[] dArr, boolean z) {
        int[] iArr = new int[2];
        int i = 4;
        iArr[1] = 4;
        int i2 = 0;
        iArr[0] = 4;
        this.f63a = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i3 = 0; i3 < 4; i3++) {
            for (int i4 = 0; i4 < 4; i4++) {
                this.f63a[i3][i4] = dArr[(i3 * 4) + i4];
            }
        }
        if (z) {
            int[] iArr2 = new int[2];
            iArr2[1] = 4;
            iArr2[0] = 4;
            final double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
            int[] iArr3 = new int[2];
            iArr3[1] = 4;
            iArr3[0] = 4;
            final double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr3);
            int[] iArr4 = new int[2];
            iArr4[1] = 4;
            iArr4[0] = 4;
            final double[][] dArr4 = (double[][]) Array.newInstance(Double.TYPE, iArr4);
            int[] iArr5 = new int[2];
            iArr5[1] = 4;
            iArr5[0] = 4;
            final double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, iArr5);
            int[] iArr6 = new int[2];
            iArr6[1] = 4;
            iArr6[0] = 4;
            final double[][] dArr6 = (double[][]) Array.newInstance(Double.TYPE, iArr6);
            int i5 = 0;
            while (i5 < i) {
                int i6 = i2;
                while (i6 < i) {
                    double d = this.f63a[i5][i6];
                    double[] dArr7 = dArr2[i5];
                    dArr7[i6] = ((double) i5) * d;
                    double[] dArr8 = dArr3[i5];
                    double d2 = (double) i6;
                    dArr8[i6] = d * d2;
                    dArr4[i5][i6] = ((double) (i5 - 1)) * dArr7[i6];
                    dArr5[i5][i6] = ((double) (i6 - 1)) * dArr8[i6];
                    dArr6[i5][i6] = d2 * dArr7[i6];
                    i6++;
                    i = 4;
                }
                i5++;
                i = 4;
                i2 = 0;
            }
            this.partialDerivativeX = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d2 * d2;
                    return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{1.0d, d2, d3, d3 * d2}, dArr2);
                }
            };
            this.partialDerivativeY = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d * d;
                    return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr3);
                }
            };
            this.partialDerivativeXX = new BivariateFunction() {
                public double value(double d, double d2) {
                    double[] dArr = {0.0d, 0.0d, 1.0d, d};
                    double d3 = d2 * d2;
                    return BicubicSplineFunction.this.apply(dArr, new double[]{1.0d, d2, d3, d3 * d2}, dArr4);
                }
            };
            this.partialDerivativeYY = new BivariateFunction() {
                public double value(double d, double d2) {
                    double d3 = d * d;
                    return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 0.0d, 1.0d, d2}, dArr5);
                }
            };
            this.partialDerivativeXY = new BivariateFunction() {
                public double value(double d, double d2) {
                    return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr6);
                }
            };
            return;
        }
        this.partialDerivativeX = null;
        this.partialDerivativeY = null;
        this.partialDerivativeXX = null;
        this.partialDerivativeYY = null;
        this.partialDerivativeXY = null;
    }

    public double value(double d, double d2) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), 0, 1);
        } else {
            double d3 = d * d;
            double[] dArr = {1.0d, d, d3, d3 * d};
            double d4 = d2 * d2;
            return apply(dArr, new double[]{1.0d, d2, d4, d4 * d2}, this.f63a);
        }
    }

    /* access modifiers changed from: private */
    public double apply(double[] dArr, double[] dArr2, double[][] dArr3) {
        double d = 0.0d;
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                d += dArr3[i][i2] * dArr[i] * dArr2[i2];
            }
        }
        return d;
    }

    public BivariateFunction partialDerivativeX() {
        return this.partialDerivativeX;
    }

    public BivariateFunction partialDerivativeY() {
        return this.partialDerivativeY;
    }

    public BivariateFunction partialDerivativeXX() {
        return this.partialDerivativeXX;
    }

    public BivariateFunction partialDerivativeYY() {
        return this.partialDerivativeYY;
    }

    public BivariateFunction partialDerivativeXY() {
        return this.partialDerivativeXY;
    }
}
