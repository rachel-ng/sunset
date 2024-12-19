package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

@Deprecated
public class BicubicSplineInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private static final int NUM_COEFF = 16;
    private final BivariateFunction[][][] partialDerivatives;
    private final BicubicSplineFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicSplineInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        this(dArr, dArr2, dArr3, dArr4, dArr5, dArr6, false);
    }

    public BicubicSplineInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6, boolean z) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        double[][] dArr7 = dArr3;
        double[][] dArr8 = dArr4;
        double[][] dArr9 = dArr5;
        double[][] dArr10 = dArr6;
        boolean z2 = z;
        int length = dArr.length;
        int length2 = dArr2.length;
        if (!(length == 0 || length2 == 0 || dArr7.length == 0)) {
            int i = 0;
            if (dArr7[0].length != 0) {
                if (length != dArr7.length) {
                    throw new DimensionMismatchException(length, dArr7.length);
                } else if (length != dArr8.length) {
                    throw new DimensionMismatchException(length, dArr8.length);
                } else if (length != dArr9.length) {
                    throw new DimensionMismatchException(length, dArr9.length);
                } else if (length == dArr10.length) {
                    MathArrays.checkOrder(dArr);
                    MathArrays.checkOrder(dArr2);
                    this.xval = (double[]) dArr.clone();
                    this.yval = (double[]) dArr2.clone();
                    char c2 = 1;
                    int i2 = length - 1;
                    int i3 = length2 - 1;
                    int[] iArr = new int[2];
                    iArr[1] = i3;
                    iArr[0] = i2;
                    this.splines = (BicubicSplineFunction[][]) Array.newInstance(BicubicSplineFunction.class, iArr);
                    int i4 = 0;
                    while (i4 < i2) {
                        if (dArr7[i4].length != length2) {
                            throw new DimensionMismatchException(dArr7[i4].length, length2);
                        } else if (dArr8[i4].length != length2) {
                            throw new DimensionMismatchException(dArr8[i4].length, length2);
                        } else if (dArr9[i4].length != length2) {
                            throw new DimensionMismatchException(dArr9[i4].length, length2);
                        } else if (dArr10[i4].length == length2) {
                            int i5 = i4 + 1;
                            int i6 = i;
                            while (i6 < i3) {
                                int i7 = i6 + 1;
                                double[] dArr11 = dArr7[i4];
                                double d = dArr11[i6];
                                double[] dArr12 = dArr7[i5];
                                double d2 = dArr12[i6];
                                double d3 = dArr11[i7];
                                double d4 = dArr12[i7];
                                double[] dArr13 = dArr8[i4];
                                double d5 = dArr13[i6];
                                double[] dArr14 = dArr8[i5];
                                double d6 = dArr14[i6];
                                double d7 = dArr13[i7];
                                double d8 = dArr14[i7];
                                double[] dArr15 = dArr9[i4];
                                double d9 = dArr15[i6];
                                double[] dArr16 = dArr9[i5];
                                double d10 = dArr16[i6];
                                double d11 = dArr15[i7];
                                double d12 = dArr16[i7];
                                double[] dArr17 = dArr10[i4];
                                double d13 = dArr17[i6];
                                double[] dArr18 = dArr10[i5];
                                double d14 = dArr18[i6];
                                double d15 = dArr17[i7];
                                double d16 = dArr18[i7];
                                double[] dArr19 = new double[16];
                                dArr19[i] = d;
                                dArr19[c2] = d2;
                                dArr19[2] = d3;
                                dArr19[3] = d4;
                                dArr19[4] = d5;
                                dArr19[5] = d6;
                                dArr19[6] = d7;
                                dArr19[7] = d8;
                                dArr19[8] = d9;
                                dArr19[9] = d10;
                                dArr19[10] = d11;
                                dArr19[11] = d12;
                                dArr19[12] = d13;
                                dArr19[13] = d14;
                                dArr19[14] = d15;
                                dArr19[15] = d16;
                                this.splines[i4][i6] = new BicubicSplineFunction(computeSplineCoefficients(dArr19), z2);
                                i6 = i7;
                                c2 = 1;
                                i = 0;
                            }
                            i4 = i5;
                        } else {
                            throw new DimensionMismatchException(dArr10[i4].length, length2);
                        }
                    }
                    if (z2) {
                        int[] iArr2 = new int[3];
                        iArr2[2] = i3;
                        iArr2[1] = i2;
                        iArr2[0] = 5;
                        this.partialDerivatives = (BivariateFunction[][][]) Array.newInstance(BivariateFunction.class, iArr2);
                        for (int i8 = 0; i8 < i2; i8++) {
                            for (int i9 = 0; i9 < i3; i9++) {
                                BicubicSplineFunction bicubicSplineFunction = this.splines[i8][i9];
                                this.partialDerivatives[0][i8][i9] = bicubicSplineFunction.partialDerivativeX();
                                this.partialDerivatives[1][i8][i9] = bicubicSplineFunction.partialDerivativeY();
                                this.partialDerivatives[2][i8][i9] = bicubicSplineFunction.partialDerivativeXX();
                                this.partialDerivatives[3][i8][i9] = bicubicSplineFunction.partialDerivativeYY();
                                this.partialDerivatives[4][i8][i9] = bicubicSplineFunction.partialDerivativeXY();
                            }
                        }
                        return;
                    }
                    BivariateFunction[][][] bivariateFunctionArr = null;
                    this.partialDerivatives = null;
                    return;
                } else {
                    throw new DimensionMismatchException(length, dArr10.length);
                }
            }
        }
        throw new NoDataException();
    }

    public double value(double d, double d2) throws OutOfRangeException {
        int searchIndex = searchIndex(d, this.xval);
        int searchIndex2 = searchIndex(d2, this.yval);
        double[] dArr = this.xval;
        double d3 = dArr[searchIndex];
        double[] dArr2 = this.yval;
        double d4 = dArr2[searchIndex2];
        return this.splines[searchIndex][searchIndex2].value((d - d3) / (dArr[searchIndex + 1] - d3), (d2 - d4) / (dArr2[searchIndex2 + 1] - d4));
    }

    public boolean isValidPoint(double d, double d2) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            return d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1];
        }
    }

    public double partialDerivativeX(double d, double d2) throws OutOfRangeException {
        return partialDerivative(0, d, d2);
    }

    public double partialDerivativeY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(1, d, d2);
    }

    public double partialDerivativeXX(double d, double d2) throws OutOfRangeException {
        return partialDerivative(2, d, d2);
    }

    public double partialDerivativeYY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(3, d, d2);
    }

    public double partialDerivativeXY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(4, d, d2);
    }

    private double partialDerivative(int i, double d, double d2) throws OutOfRangeException {
        int searchIndex = searchIndex(d, this.xval);
        int searchIndex2 = searchIndex(d2, this.yval);
        double[] dArr = this.xval;
        double d3 = dArr[searchIndex];
        double[] dArr2 = this.yval;
        double d4 = dArr2[searchIndex2];
        return this.partialDerivatives[i][searchIndex][searchIndex2].value((d - d3) / (dArr[searchIndex + 1] - d3), (d2 - d4) / (dArr2[searchIndex2 + 1] - d4));
    }

    private int searchIndex(double d, double[] dArr) {
        int binarySearch = Arrays.binarySearch(dArr, d);
        if (binarySearch == -1 || binarySearch == (-dArr.length) - 1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(dArr[0]), Double.valueOf(dArr[dArr.length - 1]));
        } else if (binarySearch < 0) {
            return (-binarySearch) - 2;
        } else {
            int length = dArr.length;
            return binarySearch == length + -1 ? length - 2 : binarySearch;
        }
    }

    private double[] computeSplineCoefficients(double[] dArr) {
        double[] dArr2 = new double[16];
        for (int i = 0; i < 16; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 16; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
