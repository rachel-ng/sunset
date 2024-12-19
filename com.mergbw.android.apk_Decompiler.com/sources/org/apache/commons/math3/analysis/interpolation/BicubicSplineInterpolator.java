package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class BicubicSplineInterpolator implements BivariateGridInterpolator {
    private final boolean initializeDerivatives;

    private int nextIndex(int i, int i2) {
        int i3 = i + 1;
        return i3 < i2 ? i3 : i;
    }

    private int previousIndex(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public BicubicSplineInterpolator() {
        this(false);
    }

    public BicubicSplineInterpolator(boolean z) {
        this.initializeDerivatives = z;
    }

    public BicubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[][] dArr6 = dArr3;
        if (dArr4.length == 0 || dArr5.length == 0 || dArr6.length == 0) {
            throw new NoDataException();
        } else if (dArr4.length == dArr6.length) {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            int length = dArr4.length;
            int length2 = dArr5.length;
            int[] iArr = new int[2];
            iArr[1] = length;
            int i = 0;
            iArr[0] = length2;
            double[][] dArr7 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i2 = 0;
            while (i2 < length) {
                if (dArr6[i2].length == length2) {
                    for (int i3 = 0; i3 < length2; i3++) {
                        dArr7[i3][i2] = dArr6[i2][i3];
                    }
                    i2++;
                } else {
                    throw new DimensionMismatchException(dArr6[i2].length, length2);
                }
            }
            SplineInterpolator splineInterpolator = new SplineInterpolator();
            PolynomialSplineFunction[] polynomialSplineFunctionArr = new PolynomialSplineFunction[length2];
            for (int i4 = 0; i4 < length2; i4++) {
                polynomialSplineFunctionArr[i4] = splineInterpolator.interpolate(dArr4, dArr7[i4]);
            }
            PolynomialSplineFunction[] polynomialSplineFunctionArr2 = new PolynomialSplineFunction[length];
            for (int i5 = 0; i5 < length; i5++) {
                polynomialSplineFunctionArr2[i5] = splineInterpolator.interpolate(dArr5, dArr6[i5]);
            }
            int[] iArr2 = new int[2];
            iArr2[1] = length2;
            iArr2[0] = length;
            double[][] dArr8 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
            int i6 = 0;
            while (i6 < length2) {
                UnivariateFunction derivative = polynomialSplineFunctionArr[i6].derivative();
                int i7 = i;
                while (i7 < length) {
                    dArr8[i7][i6] = derivative.value(dArr4[i7]);
                    i7++;
                    dArr8 = dArr8;
                }
                double[][] dArr9 = dArr8;
                i6++;
                i = 0;
            }
            double[][] dArr10 = dArr8;
            int[] iArr3 = new int[2];
            iArr3[1] = length2;
            iArr3[0] = length;
            double[][] dArr11 = (double[][]) Array.newInstance(Double.TYPE, iArr3);
            for (int i8 = 0; i8 < length; i8++) {
                UnivariateFunction derivative2 = polynomialSplineFunctionArr2[i8].derivative();
                for (int i9 = 0; i9 < length2; i9++) {
                    dArr11[i8][i9] = derivative2.value(dArr5[i9]);
                }
            }
            int[] iArr4 = new int[2];
            iArr4[1] = length2;
            iArr4[0] = length;
            double[][] dArr12 = (double[][]) Array.newInstance(Double.TYPE, iArr4);
            for (int i10 = 0; i10 < length; i10++) {
                int nextIndex = nextIndex(i10, length);
                int previousIndex = previousIndex(i10);
                for (int i11 = 0; i11 < length2; i11++) {
                    int nextIndex2 = nextIndex(i11, length2);
                    int previousIndex2 = previousIndex(i11);
                    double[] dArr13 = dArr12[i10];
                    double[] dArr14 = dArr6[nextIndex];
                    double[] dArr15 = dArr6[previousIndex];
                    dArr13[i11] = (((dArr14[nextIndex2] - dArr14[previousIndex2]) - dArr15[nextIndex2]) + dArr15[previousIndex2]) / ((dArr4[nextIndex] - dArr4[previousIndex]) * (dArr5[nextIndex2] - dArr5[previousIndex2]));
                }
            }
            return new BicubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr10, dArr11, dArr12, this.initializeDerivatives);
        } else {
            throw new DimensionMismatchException(dArr4.length, dArr6.length);
        }
    }
}
