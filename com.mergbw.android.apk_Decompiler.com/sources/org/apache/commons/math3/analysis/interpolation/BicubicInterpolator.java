package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicInterpolator implements BivariateGridInterpolator {
    public BicubicInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException, NumberIsTooSmallException {
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
            iArr[1] = length2;
            iArr[0] = length;
            double[][] dArr7 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[2];
            iArr2[1] = length2;
            iArr2[0] = length;
            double[][] dArr8 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
            int[] iArr3 = new int[2];
            iArr3[1] = length2;
            iArr3[0] = length;
            double[][] dArr9 = (double[][]) Array.newInstance(Double.TYPE, iArr3);
            int i = 1;
            while (i < length - 1) {
                int i2 = i + 1;
                int i3 = i - 1;
                double d = dArr4[i2] - dArr4[i3];
                int i4 = 1;
                while (i4 < length2 - 1) {
                    int i5 = i4 + 1;
                    int i6 = i4 - 1;
                    double d2 = dArr5[i5] - dArr5[i6];
                    double[] dArr10 = dArr7[i];
                    double[] dArr11 = dArr6[i2];
                    double d3 = dArr11[i4];
                    double[] dArr12 = dArr6[i3];
                    dArr10[i4] = (d3 - dArr12[i4]) / d;
                    double[] dArr13 = dArr8[i];
                    double[] dArr14 = dArr6[i];
                    dArr13[i4] = (dArr14[i5] - dArr14[i6]) / d2;
                    dArr9[i][i4] = (((dArr11[i5] - dArr11[i6]) - dArr12[i5]) + dArr12[i6]) / (d2 * d);
                    i4 = i5;
                }
                i = i2;
            }
            final double[] dArr15 = dArr;
            final double[] dArr16 = dArr2;
            return new BicubicInterpolatingFunction(this, dArr, dArr2, dArr3, dArr7, dArr8, dArr9) {
                final /* synthetic */ BicubicInterpolator this$0;

                {
                    this.this$0 = r9;
                }

                public boolean isValidPoint(double d, double d2) {
                    double[] dArr = dArr15;
                    if (d < dArr[1] || d > dArr[dArr.length - 2]) {
                        return false;
                    }
                    double[] dArr2 = dArr16;
                    return d2 >= dArr2[1] && d2 <= dArr2[dArr2.length + -2];
                }
            };
        } else {
            throw new DimensionMismatchException(dArr4.length, dArr6.length);
        }
    }
}
