package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

public class TricubicInterpolator implements TrivariateGridInterpolator {
    public TricubicInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        double[] dArr5 = dArr;
        double[] dArr6 = dArr2;
        double[] dArr7 = dArr3;
        double[][][] dArr8 = dArr4;
        if (dArr5.length == 0 || dArr6.length == 0 || dArr7.length == 0 || dArr8.length == 0) {
            throw new NoDataException();
        } else if (dArr5.length == dArr8.length) {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            MathArrays.checkOrder(dArr3);
            int length = dArr5.length;
            int length2 = dArr6.length;
            int length3 = dArr7.length;
            int[] iArr = new int[3];
            iArr[2] = length3;
            int i = 1;
            iArr[1] = length2;
            iArr[0] = length;
            double[][][] dArr9 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[3];
            iArr2[2] = length3;
            iArr2[1] = length2;
            iArr2[0] = length;
            double[][][] dArr10 = (double[][][]) Array.newInstance(Double.TYPE, iArr2);
            int[] iArr3 = new int[3];
            iArr3[2] = length3;
            iArr3[1] = length2;
            iArr3[0] = length;
            double[][][] dArr11 = (double[][][]) Array.newInstance(Double.TYPE, iArr3);
            int[] iArr4 = new int[3];
            iArr4[2] = length3;
            iArr4[1] = length2;
            iArr4[0] = length;
            double[][][] dArr12 = (double[][][]) Array.newInstance(Double.TYPE, iArr4);
            int[] iArr5 = new int[3];
            iArr5[2] = length3;
            iArr5[1] = length2;
            iArr5[0] = length;
            double[][][] dArr13 = (double[][][]) Array.newInstance(Double.TYPE, iArr5);
            int[] iArr6 = new int[3];
            iArr6[2] = length3;
            iArr6[1] = length2;
            iArr6[0] = length;
            double[][][] dArr14 = (double[][][]) Array.newInstance(Double.TYPE, iArr6);
            int[] iArr7 = new int[3];
            iArr7[2] = length3;
            iArr7[1] = length2;
            iArr7[0] = length;
            double[][][] dArr15 = (double[][][]) Array.newInstance(Double.TYPE, iArr7);
            int i2 = 1;
            while (i2 < length - 1) {
                if (dArr6.length == dArr8[i2].length) {
                    int i3 = i2 + 1;
                    int i4 = i2 - 1;
                    double d = dArr5[i3] - dArr5[i4];
                    int i5 = length;
                    int i6 = i;
                    while (i6 < length2 - 1) {
                        if (dArr7.length == dArr8[i2][i6].length) {
                            int i7 = i6 + 1;
                            int i8 = i6 - 1;
                            double d2 = dArr6[i7] - dArr6[i8];
                            double d3 = d * d2;
                            int i9 = length2;
                            int i10 = 1;
                            while (i10 < length3 - 1) {
                                int i11 = i10 + 1;
                                int i12 = i10 - 1;
                                double d4 = dArr7[i11] - dArr7[i12];
                                double[] dArr16 = dArr9[i2][i6];
                                double[][] dArr17 = dArr8[i3];
                                double[] dArr18 = dArr17[i6];
                                double d5 = dArr18[i10];
                                double[][] dArr19 = dArr8[i4];
                                double[] dArr20 = dArr19[i6];
                                dArr16[i10] = (d5 - dArr20[i10]) / d;
                                double[] dArr21 = dArr10[i2][i6];
                                double[][] dArr22 = dArr8[i2];
                                double[] dArr23 = dArr22[i7];
                                double d6 = dArr23[i10];
                                double[] dArr24 = dArr22[i8];
                                dArr21[i10] = (d6 - dArr24[i10]) / d2;
                                double[] dArr25 = dArr11[i2][i6];
                                double[] dArr26 = dArr22[i6];
                                dArr25[i10] = (dArr26[i11] - dArr26[i12]) / d4;
                                double[] dArr27 = dArr12[i2][i6];
                                double[] dArr28 = dArr17[i7];
                                double d7 = dArr28[i10];
                                double[] dArr29 = dArr17[i8];
                                double d8 = d7 - dArr29[i10];
                                double[] dArr30 = dArr19[i7];
                                double[] dArr31 = dArr19[i8];
                                dArr27[i10] = ((d8 - dArr30[i10]) + dArr31[i10]) / d3;
                                dArr13[i2][i6][i10] = (((dArr18[i11] - dArr18[i12]) - dArr20[i11]) + dArr20[i12]) / (d * d4);
                                dArr14[i2][i6][i10] = (((dArr23[i11] - dArr23[i12]) - dArr24[i11]) + dArr24[i12]) / (d2 * d4);
                                dArr15[i2][i6][i10] = (((((((dArr28[i11] - dArr29[i11]) - dArr30[i11]) + dArr31[i11]) - dArr28[i12]) + dArr29[i12]) + dArr30[i12]) - dArr31[i12]) / (d4 * d3);
                                i10 = i11;
                            }
                            double[] dArr32 = dArr;
                            i6 = i7;
                            length2 = i9;
                            i = 1;
                        } else {
                            throw new DimensionMismatchException(dArr7.length, dArr8[i2][i6].length);
                        }
                    }
                    dArr5 = dArr;
                    i2 = i3;
                    length = i5;
                } else {
                    throw new DimensionMismatchException(dArr6.length, dArr8[i2].length);
                }
            }
            final double[] dArr33 = dArr;
            final double[] dArr34 = dArr2;
            final double[] dArr35 = dArr3;
            return new TricubicInterpolatingFunction(this, dArr, dArr2, dArr3, dArr4, dArr9, dArr10, dArr11, dArr12, dArr13, dArr14, dArr15) {
                final /* synthetic */ TricubicInterpolator this$0;

                {
                    this.this$0 = r14;
                }

                public boolean isValidPoint(double d, double d2, double d3) {
                    double[] dArr = dArr33;
                    if (d < dArr[1] || d > dArr[dArr.length - 2]) {
                        return false;
                    }
                    double[] dArr2 = dArr34;
                    if (d2 < dArr2[1] || d2 > dArr2[dArr2.length - 2]) {
                        return false;
                    }
                    double[] dArr3 = dArr35;
                    return d3 >= dArr3[1] && d3 <= dArr3[dArr3.length + -2];
                }
            };
        } else {
            throw new DimensionMismatchException(dArr.length, dArr8.length);
        }
    }
}
