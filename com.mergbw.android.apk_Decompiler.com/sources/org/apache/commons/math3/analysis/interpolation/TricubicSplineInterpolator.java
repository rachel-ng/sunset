package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
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

    public TricubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
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
            int i = 3;
            int[] iArr = new int[3];
            iArr[2] = length2;
            iArr[1] = length;
            int i2 = 0;
            iArr[0] = length3;
            double[][][] dArr9 = (double[][][]) Array.newInstance(Double.TYPE, iArr);
            int[] iArr2 = new int[3];
            iArr2[2] = length;
            iArr2[1] = length3;
            iArr2[0] = length2;
            double[][][] dArr10 = (double[][][]) Array.newInstance(Double.TYPE, iArr2);
            int i3 = 0;
            while (i3 < length) {
                if (dArr8[i3].length == length2) {
                    int i4 = i2;
                    while (i4 < length2) {
                        if (dArr8[i3][i4].length == length3) {
                            for (int i5 = 0; i5 < length3; i5++) {
                                double d = dArr8[i3][i4][i5];
                                dArr9[i5][i3][i4] = d;
                                dArr10[i4][i5][i3] = d;
                            }
                            i4++;
                        } else {
                            throw new DimensionMismatchException(dArr8[i3][i4].length, length3);
                        }
                    }
                    i3++;
                    i2 = 0;
                } else {
                    throw new DimensionMismatchException(dArr8[i3].length, length2);
                }
            }
            BicubicSplineInterpolator bicubicSplineInterpolator = new BicubicSplineInterpolator(true);
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr = new BicubicSplineInterpolatingFunction[length];
            for (int i6 = 0; i6 < length; i6++) {
                bicubicSplineInterpolatingFunctionArr[i6] = bicubicSplineInterpolator.interpolate(dArr6, dArr7, dArr8[i6]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr2 = new BicubicSplineInterpolatingFunction[length2];
            for (int i7 = 0; i7 < length2; i7++) {
                bicubicSplineInterpolatingFunctionArr2[i7] = bicubicSplineInterpolator.interpolate(dArr7, dArr5, dArr10[i7]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr3 = new BicubicSplineInterpolatingFunction[length3];
            for (int i8 = 0; i8 < length3; i8++) {
                bicubicSplineInterpolatingFunctionArr3[i8] = bicubicSplineInterpolator.interpolate(dArr5, dArr6, dArr9[i8]);
            }
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
            int i9 = 0;
            while (i9 < length3) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = bicubicSplineInterpolatingFunctionArr3[i9];
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr4 = bicubicSplineInterpolatingFunctionArr3;
                int i10 = 0;
                while (i10 < length) {
                    double d2 = dArr5[i10];
                    int i11 = 0;
                    while (i11 < length2) {
                        int i12 = length;
                        double d3 = dArr6[i11];
                        dArr11[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeX(d2, d3);
                        dArr12[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeY(d2, d3);
                        dArr13[i10][i11][i9] = bicubicSplineInterpolatingFunction.partialDerivativeXY(d2, d3);
                        i11++;
                        double[] dArr14 = dArr;
                        length = i12;
                    }
                    int i13 = length;
                    i10++;
                    dArr5 = dArr;
                    double[] dArr15 = dArr3;
                    double[][][] dArr16 = dArr4;
                }
                int i14 = length;
                i9++;
                dArr5 = dArr;
                double[] dArr17 = dArr3;
                double[][][] dArr18 = dArr4;
                bicubicSplineInterpolatingFunctionArr3 = bicubicSplineInterpolatingFunctionArr4;
                i = 3;
            }
            int i15 = length;
            int i16 = i;
            int[] iArr6 = new int[i16];
            iArr6[2] = length3;
            iArr6[1] = length2;
            iArr6[0] = i15;
            double[][][] dArr19 = (double[][][]) Array.newInstance(Double.TYPE, iArr6);
            int[] iArr7 = new int[i16];
            iArr7[2] = length3;
            iArr7[1] = length2;
            iArr7[0] = i15;
            double[][][] dArr20 = (double[][][]) Array.newInstance(Double.TYPE, iArr7);
            int i17 = i15;
            for (int i18 = 0; i18 < i17; i18++) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction2 = bicubicSplineInterpolatingFunctionArr[i18];
                int i19 = 0;
                while (i19 < length2) {
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr5 = bicubicSplineInterpolatingFunctionArr;
                    double d4 = dArr6[i19];
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr6 = bicubicSplineInterpolatingFunctionArr5;
                    int i20 = 0;
                    while (i20 < length3) {
                        double[][][] dArr21 = dArr13;
                        double d5 = dArr3[i20];
                        dArr19[i18][i19][i20] = bicubicSplineInterpolatingFunction2.partialDerivativeY(d4, d5);
                        dArr20[i18][i19][i20] = bicubicSplineInterpolatingFunction2.partialDerivativeXY(d4, d5);
                        i20++;
                        dArr12 = dArr12;
                        dArr13 = dArr21;
                    }
                    double[][][] dArr22 = dArr13;
                    double[][][] dArr23 = dArr12;
                    i19++;
                    bicubicSplineInterpolatingFunctionArr = bicubicSplineInterpolatingFunctionArr6;
                }
                double[][][] dArr24 = dArr13;
                double[][][] dArr25 = dArr12;
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr7 = bicubicSplineInterpolatingFunctionArr;
            }
            double[][][] dArr26 = dArr13;
            double[][][] dArr27 = dArr12;
            int[] iArr8 = new int[3];
            iArr8[2] = length3;
            iArr8[1] = length2;
            iArr8[0] = i17;
            double[][][] dArr28 = (double[][][]) Array.newInstance(Double.TYPE, iArr8);
            for (int i21 = 0; i21 < length2; i21++) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction3 = bicubicSplineInterpolatingFunctionArr2[i21];
                for (int i22 = 0; i22 < length3; i22++) {
                    double d6 = dArr3[i22];
                    int i23 = 0;
                    while (i23 < i17) {
                        dArr28[i23][i21][i22] = bicubicSplineInterpolatingFunction3.partialDerivativeXY(d6, dArr[i23]);
                        i23++;
                        dArr11 = dArr11;
                        dArr19 = dArr19;
                    }
                    double[] dArr29 = dArr;
                    double[][][] dArr30 = dArr19;
                    double[][][] dArr31 = dArr11;
                }
                double[] dArr32 = dArr;
                double[][][] dArr33 = dArr19;
                double[][][] dArr34 = dArr11;
            }
            double[] dArr35 = dArr;
            double[][][] dArr36 = dArr19;
            double[][][] dArr37 = dArr11;
            int[] iArr9 = new int[3];
            iArr9[2] = length3;
            iArr9[1] = length2;
            int i24 = 0;
            iArr9[0] = i17;
            double[][][] dArr38 = (double[][][]) Array.newInstance(Double.TYPE, iArr9);
            int i25 = 0;
            while (i25 < i17) {
                int nextIndex = nextIndex(i25, i17);
                int previousIndex = previousIndex(i25);
                int i26 = i24;
                while (i26 < length2) {
                    int nextIndex2 = nextIndex(i26, length2);
                    int previousIndex2 = previousIndex(i26);
                    while (i24 < length3) {
                        int nextIndex3 = nextIndex(i24, length3);
                        int previousIndex3 = previousIndex(i24);
                        double[] dArr39 = dArr38[i25][i26];
                        int i27 = length2;
                        double[][][] dArr40 = dArr4;
                        double[][] dArr41 = dArr40[nextIndex];
                        double[] dArr42 = dArr41[nextIndex2];
                        double d7 = dArr42[nextIndex3];
                        double[] dArr43 = dArr41[previousIndex2];
                        double d8 = d7 - dArr43[nextIndex3];
                        double[][] dArr44 = dArr40[previousIndex];
                        double[] dArr45 = dArr44[nextIndex2];
                        double[] dArr46 = dArr44[previousIndex2];
                        dArr39[i24] = ((((((d8 - dArr45[nextIndex3]) + dArr46[nextIndex3]) - dArr42[previousIndex3]) + dArr43[previousIndex3]) + dArr45[previousIndex3]) - dArr46[previousIndex3]) / (((dArr35[nextIndex] - dArr35[previousIndex]) * (dArr6[nextIndex2] - dArr6[previousIndex2])) * (dArr3[nextIndex3] - dArr3[previousIndex3]));
                        i24++;
                        length2 = i27;
                    }
                    int i28 = length2;
                    double[][][] dArr47 = dArr4;
                    i26++;
                    length2 = i28;
                    i24 = 0;
                }
                int i29 = length2;
                double[][][] dArr48 = dArr4;
                i25++;
                length2 = i29;
                i24 = 0;
            }
            double[][][] dArr49 = dArr4;
            return new TricubicSplineInterpolatingFunction(dArr, dArr2, dArr3, dArr4, dArr37, dArr27, dArr36, dArr26, dArr28, dArr20, dArr38);
        } else {
            throw new DimensionMismatchException(dArr5.length, dArr8.length);
        }
    }
}
