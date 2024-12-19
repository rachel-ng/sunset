package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class SchurTransformer {
    private static final int MAX_ITERATIONS = 100;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private RealMatrix cachedT;
    private final double epsilon = Precision.EPSILON;
    private final double[][] matrixP;
    private final double[][] matrixT;

    public SchurTransformer(RealMatrix realMatrix) {
        if (realMatrix.isSquare()) {
            HessenbergTransformer hessenbergTransformer = new HessenbergTransformer(realMatrix);
            this.matrixT = hessenbergTransformer.getH().getData();
            this.matrixP = hessenbergTransformer.getP().getData();
            this.cachedT = null;
            this.cachedP = null;
            this.cachedPt = null;
            transform();
            return;
        }
        throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            this.cachedP = MatrixUtils.createRealMatrix(this.matrixP);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getT() {
        if (this.cachedT == null) {
            this.cachedT = MatrixUtils.createRealMatrix(this.matrixT);
        }
        return this.cachedT;
    }

    private void transform() {
        double d;
        int length = this.matrixT.length;
        double norm = getNorm();
        ShiftInfo shiftInfo = new ShiftInfo();
        int i = length - 1;
        int i2 = i;
        int i3 = 0;
        while (i2 >= 0) {
            int findSmallSubDiagonalElement = findSmallSubDiagonalElement(i2, norm);
            if (findSmallSubDiagonalElement == i2) {
                double[] dArr = this.matrixT[i2];
                dArr[i2] = dArr[i2] + shiftInfo.exShift;
                i2--;
                d = norm;
            } else {
                int i4 = i2 - 1;
                if (findSmallSubDiagonalElement == i4) {
                    double[][] dArr2 = this.matrixT;
                    double[] dArr3 = dArr2[i4];
                    double d2 = dArr3[i4];
                    double[] dArr4 = dArr2[i2];
                    double d3 = dArr4[i2];
                    double d4 = (d2 - d3) / 2.0d;
                    double d5 = (d4 * d4) + (dArr4[i4] * dArr3[i2]);
                    d = norm;
                    dArr4[i2] = d3 + shiftInfo.exShift;
                    double[] dArr5 = this.matrixT[i4];
                    dArr5[i4] = dArr5[i4] + shiftInfo.exShift;
                    if (d5 >= 0.0d) {
                        double sqrt = FastMath.sqrt(FastMath.abs(d5));
                        double d6 = d4 >= 0.0d ? d4 + sqrt : d4 - sqrt;
                        double d7 = this.matrixT[i2][i4];
                        double abs = FastMath.abs(d7) + FastMath.abs(d6);
                        double d8 = d7 / abs;
                        double d9 = d6 / abs;
                        double sqrt2 = FastMath.sqrt((d8 * d8) + (d9 * d9));
                        double d10 = d8 / sqrt2;
                        double d11 = d9 / sqrt2;
                        for (int i5 = i4; i5 < length; i5++) {
                            double[][] dArr6 = this.matrixT;
                            double[] dArr7 = dArr6[i4];
                            double d12 = dArr7[i5];
                            double[] dArr8 = dArr6[i2];
                            dArr7[i5] = (d11 * d12) + (dArr8[i5] * d10);
                            dArr8[i5] = (dArr8[i5] * d11) - (d12 * d10);
                        }
                        for (int i6 = 0; i6 <= i2; i6++) {
                            double[] dArr9 = this.matrixT[i6];
                            double d13 = dArr9[i4];
                            dArr9[i4] = (d11 * d13) + (dArr9[i2] * d10);
                            dArr9[i2] = (dArr9[i2] * d11) - (d13 * d10);
                        }
                        for (int i7 = 0; i7 <= i; i7++) {
                            double[] dArr10 = this.matrixP[i7];
                            double d14 = dArr10[i4];
                            dArr10[i4] = (d11 * d14) + (dArr10[i2] * d10);
                            dArr10[i2] = (dArr10[i2] * d11) - (d14 * d10);
                        }
                    }
                    i2 -= 2;
                } else {
                    d = norm;
                    computeShift(findSmallSubDiagonalElement, i2, i3, shiftInfo);
                    int i8 = i3 + 1;
                    if (i8 <= 100) {
                        double[] dArr11 = new double[3];
                        performDoubleQRStep(findSmallSubDiagonalElement, initQRStep(findSmallSubDiagonalElement, i2, shiftInfo, dArr11), i2, shiftInfo, dArr11);
                        i3 = i8;
                        norm = d;
                    } else {
                        throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, 100, new Object[0]);
                    }
                }
            }
            i3 = 0;
            norm = d;
        }
    }

    private double getNorm() {
        double d = 0.0d;
        for (int i = 0; i < this.matrixT.length; i++) {
            int max = FastMath.max(i - 1, 0);
            while (true) {
                double[][] dArr = this.matrixT;
                if (max >= dArr.length) {
                    break;
                }
                d += FastMath.abs(dArr[i][max]);
                max++;
            }
        }
        return d;
    }

    private int findSmallSubDiagonalElement(int i, double d) {
        while (i > 0) {
            int i2 = i - 1;
            double abs = FastMath.abs(this.matrixT[i2][i2]) + FastMath.abs(this.matrixT[i][i]);
            if (abs == 0.0d) {
                abs = d;
            }
            if (FastMath.abs(this.matrixT[i][i2]) < this.epsilon * abs) {
                break;
            }
            i--;
        }
        return i;
    }

    private void computeShift(int i, int i2, int i3, ShiftInfo shiftInfo) {
        int i4 = i2;
        int i5 = i3;
        ShiftInfo shiftInfo2 = shiftInfo;
        shiftInfo2.x = this.matrixT[i4][i4];
        shiftInfo2.w = 0.0d;
        shiftInfo2.y = 0.0d;
        if (i < i4) {
            int i6 = i4 - 1;
            shiftInfo2.y = this.matrixT[i6][i6];
            double[][] dArr = this.matrixT;
            shiftInfo2.w = dArr[i4][i6] * dArr[i6][i4];
        }
        if (i5 == 10) {
            shiftInfo2.exShift += shiftInfo2.x;
            for (int i7 = 0; i7 <= i4; i7++) {
                double[] dArr2 = this.matrixT[i7];
                dArr2[i7] = dArr2[i7] - shiftInfo2.x;
            }
            int i8 = i4 - 1;
            double abs = FastMath.abs(this.matrixT[i4][i8]) + FastMath.abs(this.matrixT[i8][i4 - 2]);
            double d = 0.75d * abs;
            shiftInfo2.x = d;
            shiftInfo2.y = d;
            shiftInfo2.w = -0.4375d * abs * abs;
        }
        if (i5 == 30) {
            double d2 = (shiftInfo2.y - shiftInfo2.x) / 2.0d;
            double d3 = (d2 * d2) + shiftInfo2.w;
            if (d3 > 0.0d) {
                double sqrt = FastMath.sqrt(d3);
                if (shiftInfo2.y < shiftInfo2.x) {
                    sqrt = -sqrt;
                }
                double d4 = shiftInfo2.x - (shiftInfo2.w / (((shiftInfo2.y - shiftInfo2.x) / 2.0d) + sqrt));
                for (int i9 = 0; i9 <= i4; i9++) {
                    double[] dArr3 = this.matrixT[i9];
                    dArr3[i9] = dArr3[i9] - d4;
                }
                shiftInfo2.exShift += d4;
                shiftInfo2.w = 0.964d;
                shiftInfo2.y = 0.964d;
                shiftInfo2.x = 0.964d;
            }
        }
    }

    private int initQRStep(int i, int i2, ShiftInfo shiftInfo, double[] dArr) {
        int i3 = i;
        ShiftInfo shiftInfo2 = shiftInfo;
        int i4 = i2 - 2;
        while (i4 >= i3) {
            double d = this.matrixT[i4][i4];
            double d2 = shiftInfo2.x - d;
            double d3 = shiftInfo2.y - d;
            double d4 = (d2 * d3) - shiftInfo2.w;
            double[][] dArr2 = this.matrixT;
            int i5 = i4 + 1;
            double[] dArr3 = dArr2[i5];
            double d5 = d4 / dArr3[i4];
            double[] dArr4 = dArr2[i4];
            dArr[0] = d5 + dArr4[i5];
            dArr[1] = ((dArr3[i5] - d) - d2) - d3;
            dArr[2] = dArr2[i4 + 2][i5];
            if (i4 == i3) {
                break;
            }
            int i6 = i4 - 1;
            if (FastMath.abs(dArr4[i6]) * (FastMath.abs(dArr[1]) + FastMath.abs(dArr[2])) < this.epsilon * FastMath.abs(dArr[0]) * (FastMath.abs(this.matrixT[i6][i6]) + FastMath.abs(d) + FastMath.abs(this.matrixT[i5][i5]))) {
                break;
            }
            i4--;
        }
        return i4;
    }

    private void performDoubleQRStep(int i, int i2, int i3, ShiftInfo shiftInfo, double[] dArr) {
        int i4;
        double d;
        double d2;
        int i5 = i2;
        int i6 = i3;
        ShiftInfo shiftInfo2 = shiftInfo;
        int length = this.matrixT.length;
        boolean z = false;
        double d3 = dArr[0];
        boolean z2 = true;
        double d4 = dArr[1];
        int i7 = 2;
        double d5 = dArr[2];
        int i8 = i5;
        while (true) {
            int i9 = i6 - 1;
            if (i8 > i9) {
                break;
            }
            boolean z3 = i8 != i9 ? z2 : z;
            if (i8 != i5) {
                double[][] dArr2 = this.matrixT;
                int i10 = i8 - 1;
                double d6 = dArr2[i8][i10];
                double d7 = dArr2[i8 + 1][i10];
                double d8 = z3 ? dArr2[i8 + 2][i10] : 0.0d;
                shiftInfo2.x = FastMath.abs(d6) + FastMath.abs(d7) + FastMath.abs(d8);
                double d9 = d8;
                if (Precision.equals(shiftInfo2.x, 0.0d, this.epsilon)) {
                    i4 = length;
                    d3 = d6;
                    d4 = d7;
                    d5 = d9;
                    i8++;
                    length = i4;
                    z = false;
                    z2 = true;
                    i7 = 2;
                } else {
                    d3 = d6 / shiftInfo2.x;
                    d4 = d7 / shiftInfo2.x;
                    d5 = d9 / shiftInfo2.x;
                }
            }
            double d10 = d5;
            double sqrt = FastMath.sqrt((d3 * d3) + (d4 * d4) + (d5 * d5));
            if (d3 < 0.0d) {
                sqrt = -sqrt;
            }
            if (sqrt != 0.0d) {
                if (i8 != i5) {
                    d2 = d4;
                    d = d3;
                    this.matrixT[i8][i8 - 1] = (-sqrt) * shiftInfo2.x;
                    int i11 = i;
                } else {
                    d = d3;
                    d2 = d4;
                    if (i != i5) {
                        double[] dArr3 = this.matrixT[i8];
                        int i12 = i8 - 1;
                        dArr3[i12] = -dArr3[i12];
                    }
                }
                d3 = d + sqrt;
                shiftInfo2.x = d3 / sqrt;
                shiftInfo2.y = d2 / sqrt;
                double d11 = d10 / sqrt;
                double d12 = d2 / d3;
                double d13 = d10 / d3;
                int i13 = i8;
                while (i13 < length) {
                    double[][] dArr4 = this.matrixT;
                    double[] dArr5 = dArr4[i8];
                    int i14 = i8 + 1;
                    double d14 = dArr5[i13] + (dArr4[i14][i13] * d12);
                    if (z3) {
                        double[] dArr6 = dArr4[i8 + 2];
                        double d15 = dArr6[i13];
                        d14 += d13 * d15;
                        dArr6[i13] = d15 - (d14 * d11);
                    }
                    int i15 = i13;
                    dArr5[i15] = dArr5[i13] - (shiftInfo2.x * d14);
                    double[] dArr7 = this.matrixT[i14];
                    dArr7[i15] = dArr7[i15] - (shiftInfo2.y * d14);
                    i13 = i15 + 1;
                    int i16 = i;
                    d3 = d14;
                    length = length;
                }
                i4 = length;
                int i17 = 0;
                while (i17 <= FastMath.min(i6, i8 + 3)) {
                    double d16 = shiftInfo2.x * this.matrixT[i17][i8];
                    double d17 = shiftInfo2.y;
                    double[] dArr8 = this.matrixT[i17];
                    int i18 = i8 + 1;
                    double d18 = d16 + (d17 * dArr8[i18]);
                    if (z3) {
                        int i19 = i8 + 2;
                        double d19 = dArr8[i19];
                        d18 += d11 * d19;
                        dArr8[i19] = d19 - (d18 * d13);
                    }
                    dArr8[i8] = dArr8[i8] - d18;
                    dArr8[i18] = dArr8[i18] - (d18 * d12);
                    i17++;
                    d3 = d18;
                }
                int length2 = this.matrixT.length - 1;
                int i20 = 0;
                while (i20 <= length2) {
                    double d20 = shiftInfo2.y;
                    double[] dArr9 = this.matrixP[i20];
                    int i21 = i8 + 1;
                    double d21 = (shiftInfo2.x * this.matrixP[i20][i8]) + (d20 * dArr9[i21]);
                    if (z3) {
                        int i22 = i8 + 2;
                        double d22 = dArr9[i22];
                        d21 += d11 * d22;
                        dArr9[i22] = d22 - (d21 * d13);
                    }
                    dArr9[i8] = dArr9[i8] - d21;
                    dArr9[i21] = dArr9[i21] - (d21 * d12);
                    i20++;
                    d3 = d21;
                }
                d4 = d12;
                d5 = d13;
            } else {
                i4 = length;
                double d23 = d3;
                double d24 = d4;
                d5 = d10;
            }
            i8++;
            length = i4;
            z = false;
            z2 = true;
            i7 = 2;
        }
        int i23 = i5 + i7;
        for (int i24 = i23; i24 <= i6; i24++) {
            double[] dArr10 = this.matrixT[i24];
            dArr10[i24 - 2] = 0.0d;
            if (i24 > i23) {
                dArr10[i24 - 3] = 0.0d;
            }
        }
    }

    private static class ShiftInfo {
        double exShift;
        double w;
        double x;
        double y;

        private ShiftInfo() {
        }
    }
}
