package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

class BiDiagonalTransformer {
    private RealMatrix cachedB = null;
    private RealMatrix cachedU = null;
    private RealMatrix cachedV = null;
    private final double[][] householderVectors;
    private final double[] main;
    private final double[] secondary;

    public BiDiagonalTransformer(RealMatrix realMatrix) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int min = FastMath.min(rowDimension, columnDimension);
        this.householderVectors = realMatrix.getData();
        this.main = new double[min];
        this.secondary = new double[(min - 1)];
        if (rowDimension >= columnDimension) {
            transformToUpperBiDiagonal();
        } else {
            transformToLowerBiDiagonal();
        }
    }

    public RealMatrix getU() {
        double d;
        if (this.cachedU == null) {
            double[][] dArr = this.householderVectors;
            int length = dArr.length;
            int length2 = dArr[0].length;
            double[] dArr2 = this.main;
            int length3 = dArr2.length;
            int i = length >= length2 ? 0 : 1;
            if (length < length2) {
                dArr2 = this.secondary;
            }
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length;
            double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i2 = length - 1;
            while (true) {
                d = 1.0d;
                if (i2 < length3) {
                    break;
                }
                dArr3[i2][i2] = 1.0d;
                i2--;
            }
            int i3 = length3 - 1;
            while (i3 >= i) {
                double[] dArr4 = this.householderVectors[i3];
                dArr3[i3][i3] = d;
                int i4 = i3 - i;
                double d2 = 0.0d;
                if (dArr4[i4] != 0.0d) {
                    int i5 = i3;
                    while (i5 < length) {
                        double d3 = d2;
                        for (int i6 = i3; i6 < length; i6++) {
                            d3 -= dArr3[i6][i5] * this.householderVectors[i6][i4];
                        }
                        double d4 = d3 / (dArr2[i4] * dArr4[i4]);
                        for (int i7 = i3; i7 < length; i7++) {
                            double[] dArr5 = dArr3[i7];
                            dArr5[i5] = dArr5[i5] + ((-d4) * this.householderVectors[i7][i4]);
                        }
                        i5++;
                        d2 = 0.0d;
                    }
                }
                i3--;
                d = 1.0d;
            }
            if (i > 0) {
                dArr3[0][0] = 1.0d;
            }
            this.cachedU = MatrixUtils.createRealMatrix(dArr3);
        }
        return this.cachedU;
    }

    public RealMatrix getB() {
        if (this.cachedB == null) {
            double[][] dArr = this.householderVectors;
            int length = dArr.length;
            int i = 0;
            int length2 = dArr[0].length;
            int[] iArr = new int[2];
            iArr[1] = length2;
            iArr[0] = length;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            while (true) {
                double[] dArr3 = this.main;
                if (i >= dArr3.length) {
                    break;
                }
                double[] dArr4 = dArr2[i];
                dArr4[i] = dArr3[i];
                if (length < length2) {
                    if (i > 0) {
                        int i2 = i - 1;
                        dArr4[i2] = this.secondary[i2];
                    }
                } else if (i < dArr3.length - 1) {
                    dArr4[i + 1] = this.secondary[i];
                }
                i++;
            }
            this.cachedB = MatrixUtils.createRealMatrix(dArr2);
        }
        return this.cachedB;
    }

    public RealMatrix getV() {
        double d;
        if (this.cachedV == null) {
            double[][] dArr = this.householderVectors;
            int length = dArr.length;
            int length2 = dArr[0].length;
            double[] dArr2 = this.main;
            int length3 = dArr2.length;
            int i = length >= length2 ? 1 : 0;
            if (length >= length2) {
                dArr2 = this.secondary;
            }
            int[] iArr = new int[2];
            iArr[1] = length2;
            iArr[0] = length2;
            double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i2 = length2 - 1;
            while (true) {
                d = 1.0d;
                if (i2 < length3) {
                    break;
                }
                dArr3[i2][i2] = 1.0d;
                i2--;
            }
            int i3 = length3 - 1;
            while (i3 >= i) {
                int i4 = i3 - i;
                double[] dArr4 = this.householderVectors[i4];
                dArr3[i3][i3] = d;
                double d2 = 0.0d;
                if (dArr4[i3] != 0.0d) {
                    int i5 = i3;
                    while (i5 < length2) {
                        double d3 = d2;
                        for (int i6 = i3; i6 < length2; i6++) {
                            d3 -= dArr3[i6][i5] * dArr4[i6];
                        }
                        double d4 = d3 / (dArr2[i4] * dArr4[i3]);
                        for (int i7 = i3; i7 < length2; i7++) {
                            double[] dArr5 = dArr3[i7];
                            dArr5[i5] = dArr5[i5] + ((-d4) * dArr4[i7]);
                        }
                        i5++;
                        d2 = 0.0d;
                    }
                }
                i3--;
                d = 1.0d;
            }
            if (i > 0) {
                dArr3[0][0] = 1.0d;
            }
            this.cachedV = MatrixUtils.createRealMatrix(dArr3);
        }
        return this.cachedV;
    }

    /* access modifiers changed from: package-private */
    public double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    /* access modifiers changed from: package-private */
    public double[] getMainDiagonalRef() {
        return this.main;
    }

    /* access modifiers changed from: package-private */
    public double[] getSecondaryDiagonalRef() {
        return this.secondary;
    }

    /* access modifiers changed from: package-private */
    public boolean isUpperBiDiagonal() {
        double[][] dArr = this.householderVectors;
        return dArr.length >= dArr[0].length;
    }

    private void transformToUpperBiDiagonal() {
        double[][] dArr = this.householderVectors;
        int length = dArr.length;
        int length2 = dArr[0].length;
        for (int i = 0; i < length2; i++) {
            double d = 0.0d;
            for (int i2 = i; i2 < length; i2++) {
                double d2 = this.householderVectors[i2][i];
                d += d2 * d2;
            }
            double[] dArr2 = this.householderVectors[i];
            int i3 = (dArr2[i] > 0.0d ? 1 : (dArr2[i] == 0.0d ? 0 : -1));
            double sqrt = FastMath.sqrt(d);
            if (i3 > 0) {
                sqrt = -sqrt;
            }
            this.main[i] = sqrt;
            if (sqrt != 0.0d) {
                dArr2[i] = dArr2[i] - sqrt;
                for (int i4 = i + 1; i4 < length2; i4++) {
                    double d3 = 0.0d;
                    for (int i5 = i; i5 < length; i5++) {
                        double[] dArr3 = this.householderVectors[i5];
                        d3 -= dArr3[i4] * dArr3[i];
                    }
                    double d4 = d3 / (this.householderVectors[i][i] * sqrt);
                    for (int i6 = i; i6 < length; i6++) {
                        double[] dArr4 = this.householderVectors[i6];
                        dArr4[i4] = dArr4[i4] - (dArr4[i] * d4);
                    }
                }
            }
            if (i < length2 - 1) {
                int i7 = i + 1;
                double d5 = 0.0d;
                for (int i8 = i7; i8 < length2; i8++) {
                    double d6 = dArr2[i8];
                    d5 += d6 * d6;
                }
                double sqrt2 = dArr2[i7] > 0.0d ? -FastMath.sqrt(d5) : FastMath.sqrt(d5);
                this.secondary[i] = sqrt2;
                if (sqrt2 != 0.0d) {
                    dArr2[i7] = dArr2[i7] - sqrt2;
                    for (int i9 = i7; i9 < length; i9++) {
                        double[] dArr5 = this.householderVectors[i9];
                        double d7 = 0.0d;
                        for (int i10 = i7; i10 < length2; i10++) {
                            d7 -= dArr5[i10] * dArr2[i10];
                        }
                        double d8 = d7 / (dArr2[i7] * sqrt2);
                        for (int i11 = i7; i11 < length2; i11++) {
                            dArr5[i11] = dArr5[i11] - (dArr2[i11] * d8);
                        }
                    }
                }
            }
        }
    }

    private void transformToLowerBiDiagonal() {
        double[][] dArr = this.householderVectors;
        int length = dArr.length;
        int length2 = dArr[0].length;
        for (int i = 0; i < length; i++) {
            double[] dArr2 = this.householderVectors[i];
            double d = 0.0d;
            for (int i2 = i; i2 < length2; i2++) {
                double d2 = dArr2[i2];
                d += d2 * d2;
            }
            double sqrt = dArr2[i] > 0.0d ? -FastMath.sqrt(d) : FastMath.sqrt(d);
            this.main[i] = sqrt;
            if (sqrt != 0.0d) {
                dArr2[i] = dArr2[i] - sqrt;
                for (int i3 = i + 1; i3 < length; i3++) {
                    double[] dArr3 = this.householderVectors[i3];
                    double d3 = 0.0d;
                    for (int i4 = i; i4 < length2; i4++) {
                        d3 -= dArr3[i4] * dArr2[i4];
                    }
                    double d4 = d3 / (this.householderVectors[i][i] * sqrt);
                    for (int i5 = i; i5 < length2; i5++) {
                        dArr3[i5] = dArr3[i5] - (dArr2[i5] * d4);
                    }
                }
            }
            if (i < length - 1) {
                int i6 = i + 1;
                double[] dArr4 = this.householderVectors[i6];
                double d5 = 0.0d;
                for (int i7 = i6; i7 < length; i7++) {
                    double d6 = this.householderVectors[i7][i];
                    d5 += d6 * d6;
                }
                double sqrt2 = dArr4[i] > 0.0d ? -FastMath.sqrt(d5) : FastMath.sqrt(d5);
                this.secondary[i] = sqrt2;
                if (sqrt2 != 0.0d) {
                    dArr4[i] = dArr4[i] - sqrt2;
                    for (int i8 = i6; i8 < length2; i8++) {
                        double d7 = 0.0d;
                        for (int i9 = i6; i9 < length; i9++) {
                            double[] dArr5 = this.householderVectors[i9];
                            d7 -= dArr5[i8] * dArr5[i];
                        }
                        double d8 = d7 / (dArr4[i] * sqrt2);
                        for (int i10 = i6; i10 < length; i10++) {
                            double[] dArr6 = this.householderVectors[i10];
                            dArr6[i8] = dArr6[i8] - (dArr6[i] * d8);
                        }
                    }
                }
            }
        }
    }
}
