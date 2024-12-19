package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class PolynomialFunctionLagrangeForm implements UnivariateFunction {
    private double[] coefficients;
    private boolean coefficientsComputed = false;
    private final double[] x;
    private final double[] y;

    public PolynomialFunctionLagrangeForm(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        double[] dArr3 = new double[dArr.length];
        this.x = dArr3;
        double[] dArr4 = new double[dArr2.length];
        this.y = dArr4;
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
        if (!verifyInterpolationArray(dArr, dArr2, false)) {
            MathArrays.sortInPlace(dArr3, dArr4);
            verifyInterpolationArray(dArr3, dArr4, true);
        }
    }

    public double value(double d) {
        return evaluateInternal(this.x, this.y, d);
    }

    public int degree() {
        return this.x.length - 1;
    }

    public double[] getInterpolatingPoints() {
        double[] dArr = this.x;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getInterpolatingValues() {
        double[] dArr = this.y;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] dArr = this.coefficients;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public static double evaluate(double[] dArr, double[] dArr2, double d) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (verifyInterpolationArray(dArr, dArr2, false)) {
            return evaluateInternal(dArr, dArr2, d);
        }
        double[] dArr3 = new double[dArr.length];
        double[] dArr4 = new double[dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
        MathArrays.sortInPlace(dArr3, dArr4);
        verifyInterpolationArray(dArr3, dArr4, true);
        return evaluateInternal(dArr3, dArr4, d);
    }

    private static double evaluateInternal(double[] dArr, double[] dArr2, double d) {
        int i;
        double d2;
        double[] dArr3 = dArr;
        int length = dArr3.length;
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        double d3 = Double.POSITIVE_INFINITY;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            dArr4[i3] = dArr2[i3];
            dArr5[i3] = dArr2[i3];
            double abs = FastMath.abs(d - dArr3[i3]);
            if (abs < d3) {
                i2 = i3;
                d3 = abs;
            }
        }
        double d4 = dArr2[i2];
        for (int i4 = 1; i4 < length; i4++) {
            int i5 = 0;
            while (true) {
                i = length - i4;
                if (i5 >= i) {
                    break;
                }
                double d5 = dArr3[i5];
                double d6 = d5 - d;
                double d7 = dArr3[i4 + i5];
                double d8 = d7 - d;
                double d9 = d5 - d7;
                int i6 = i5 + 1;
                double d10 = (dArr4[i6] - dArr5[i5]) / d9;
                dArr4[i5] = d6 * d10;
                dArr5[i5] = d8 * d10;
                i5 = i6;
            }
            if (((double) i2) < ((double) (i + 1)) * 0.5d) {
                d2 = dArr4[i2];
            } else {
                i2--;
                d2 = dArr5[i2];
            }
            d4 += d2;
        }
        return d4;
    }

    /* access modifiers changed from: protected */
    public void computeCoefficients() {
        int degree = degree();
        int i = degree + 1;
        this.coefficients = new double[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            this.coefficients[i3] = 0.0d;
        }
        double[] dArr = new double[(degree + 2)];
        dArr[0] = 1.0d;
        int i4 = 0;
        while (i4 < i) {
            for (int i5 = i4; i5 > 0; i5--) {
                dArr[i5] = dArr[i5 - 1] - (dArr[i5] * this.x[i4]);
            }
            dArr[0] = dArr[0] * (-this.x[i4]);
            i4++;
            dArr[i4] = 1.0d;
        }
        double[] dArr2 = new double[i];
        int i6 = 0;
        while (i6 < i) {
            double d = 1.0d;
            for (int i7 = i2; i7 < i; i7++) {
                if (i6 != i7) {
                    double[] dArr3 = this.x;
                    d *= dArr3[i6] - dArr3[i7];
                }
            }
            double d2 = this.y[i6] / d;
            double d3 = dArr[i];
            dArr2[degree] = d3;
            double[] dArr4 = this.coefficients;
            dArr4[degree] = dArr4[degree] + (d3 * d2);
            for (int i8 = degree - 1; i8 >= 0; i8--) {
                int i9 = i8 + 1;
                double d4 = dArr[i9] + (dArr2[i9] * this.x[i6]);
                dArr2[i8] = d4;
                double[] dArr5 = this.coefficients;
                dArr5[i8] = dArr5[i8] + (d4 * d2);
            }
            i6++;
            i2 = 0;
        }
        this.coefficientsComputed = true;
    }

    public static boolean verifyInterpolationArray(double[] dArr, double[] dArr2, boolean z) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length >= 2) {
            return MathArrays.checkOrder(dArr, MathArrays.OrderDirection.INCREASING, true, z);
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, 2, Integer.valueOf(dArr.length), true);
        }
    }
}
