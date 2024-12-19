package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class SplineInterpolator implements UnivariateInterpolator {
    public PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (dArr3.length != dArr4.length) {
            throw new DimensionMismatchException(dArr3.length, dArr4.length);
        } else if (dArr3.length >= 3) {
            int length = dArr3.length;
            int i = length - 1;
            MathArrays.checkOrder(dArr);
            double[] dArr5 = new double[i];
            int i2 = 0;
            while (i2 < i) {
                int i3 = i2 + 1;
                dArr5[i2] = dArr3[i3] - dArr3[i2];
                i2 = i3;
            }
            double[] dArr6 = new double[i];
            double[] dArr7 = new double[length];
            dArr6[0] = 0.0d;
            dArr7[0] = 0.0d;
            int i4 = 1;
            while (i4 < i) {
                int i5 = i4 + 1;
                int i6 = i4 - 1;
                double d = ((dArr3[i5] - dArr3[i6]) * 2.0d) - (dArr5[i6] * dArr6[i6]);
                dArr6[i4] = dArr5[i4] / d;
                double d2 = dArr4[i5];
                double d3 = dArr5[i6];
                double d4 = (d2 * d3) - (dArr4[i4] * (dArr3[i5] - dArr3[i6]));
                double d5 = dArr4[i6];
                double d6 = dArr5[i4];
                dArr7[i4] = ((((d4 + (d5 * d6)) * 3.0d) / (d6 * d3)) - (d3 * dArr7[i6])) / d;
                i4 = i5;
            }
            double[] dArr8 = new double[i];
            double[] dArr9 = new double[length];
            double[] dArr10 = new double[i];
            dArr7[i] = 0.0d;
            dArr9[i] = 0.0d;
            for (int i7 = length - 2; i7 >= 0; i7--) {
                int i8 = i7 + 1;
                double d7 = dArr7[i7] - (dArr6[i7] * dArr9[i8]);
                dArr9[i7] = d7;
                double d8 = dArr4[i8] - dArr4[i7];
                double d9 = dArr5[i7];
                dArr8[i7] = (d8 / d9) - ((d9 * (dArr9[i8] + (d7 * 2.0d))) / 3.0d);
                dArr10[i7] = (dArr9[i8] - dArr9[i7]) / (dArr5[i7] * 3.0d);
            }
            PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[i];
            for (int i9 = 0; i9 < i; i9++) {
                polynomialFunctionArr[i9] = new PolynomialFunction(new double[]{dArr4[i9], dArr8[i9], dArr9[i9], dArr10[i9]});
            }
            return new PolynomialSplineFunction(dArr3, polynomialFunctionArr);
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr3.length), 3, true);
        }
    }
}
