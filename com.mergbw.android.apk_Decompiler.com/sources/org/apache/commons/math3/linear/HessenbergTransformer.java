package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class HessenbergTransformer {
    private RealMatrix cachedH;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private final double[][] householderVectors;
    private final double[] ort;

    public HessenbergTransformer(RealMatrix realMatrix) {
        if (realMatrix.isSquare()) {
            int rowDimension = realMatrix.getRowDimension();
            this.householderVectors = realMatrix.getData();
            this.ort = new double[rowDimension];
            this.cachedP = null;
            this.cachedPt = null;
            this.cachedH = null;
            transform();
            return;
        }
        throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            int length = this.householderVectors.length;
            int i = length - 1;
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i2 = 0;
            while (i2 < length) {
                int i3 = 0;
                while (i3 < length) {
                    dArr[i2][i3] = i2 == i3 ? 1.0d : 0.0d;
                    i3++;
                }
                i2++;
            }
            for (int i4 = length - 2; i4 >= 1; i4--) {
                int i5 = i4 - 1;
                if (this.householderVectors[i4][i5] != 0.0d) {
                    for (int i6 = i4 + 1; i6 <= i; i6++) {
                        this.ort[i6] = this.householderVectors[i6][i5];
                    }
                    for (int i7 = i4; i7 <= i; i7++) {
                        double d = 0.0d;
                        for (int i8 = i4; i8 <= i; i8++) {
                            d += this.ort[i8] * dArr[i8][i7];
                        }
                        double d2 = (d / this.ort[i4]) / this.householderVectors[i4][i5];
                        for (int i9 = i4; i9 <= i; i9++) {
                            double[] dArr2 = dArr[i9];
                            dArr2[i7] = dArr2[i7] + (this.ort[i9] * d2);
                        }
                    }
                }
            }
            this.cachedP = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getH() {
        if (this.cachedH == null) {
            int length = this.householderVectors.length;
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    int i2 = i - 1;
                    dArr[i][i2] = this.householderVectors[i][i2];
                }
                for (int i3 = i; i3 < length; i3++) {
                    dArr[i][i3] = this.householderVectors[i][i3];
                }
            }
            this.cachedH = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedH;
    }

    /* access modifiers changed from: package-private */
    public double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    private void transform() {
        int length = this.householderVectors.length;
        int i = length - 1;
        for (int i2 = 1; i2 <= length - 2; i2++) {
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i3 = i2; i3 <= i; i3++) {
                d2 += FastMath.abs(this.householderVectors[i3][i2 - 1]);
            }
            if (!Precision.equals(d2, 0.0d)) {
                double d3 = 0.0d;
                for (int i4 = i; i4 >= i2; i4--) {
                    double[] dArr = this.ort;
                    double d4 = this.householderVectors[i4][i2 - 1] / d2;
                    dArr[i4] = d4;
                    d3 += d4 * d4;
                }
                int i5 = (this.ort[i2] > 0.0d ? 1 : (this.ort[i2] == 0.0d ? 0 : -1));
                double sqrt = FastMath.sqrt(d3);
                if (i5 > 0) {
                    sqrt = -sqrt;
                }
                double[] dArr2 = this.ort;
                double d5 = dArr2[i2];
                double d6 = d3 - (d5 * sqrt);
                dArr2[i2] = d5 - sqrt;
                int i6 = i2;
                while (i6 < length) {
                    double d7 = d;
                    for (int i7 = i; i7 >= i2; i7--) {
                        d7 += this.ort[i7] * this.householderVectors[i7][i6];
                    }
                    double d8 = d7 / d6;
                    for (int i8 = i2; i8 <= i; i8++) {
                        double[] dArr3 = this.householderVectors[i8];
                        dArr3[i6] = dArr3[i6] - (this.ort[i8] * d8);
                    }
                    i6++;
                    d = 0.0d;
                }
                for (int i9 = 0; i9 <= i; i9++) {
                    double d9 = 0.0d;
                    for (int i10 = i; i10 >= i2; i10--) {
                        d9 += this.ort[i10] * this.householderVectors[i9][i10];
                    }
                    double d10 = d9 / d6;
                    for (int i11 = i2; i11 <= i; i11++) {
                        double[] dArr4 = this.householderVectors[i9];
                        dArr4[i11] = dArr4[i11] - (this.ort[i11] * d10);
                    }
                }
                double[] dArr5 = this.ort;
                dArr5[i2] = dArr5[i2] * d2;
                this.householderVectors[i2][i2 - 1] = d2 * sqrt;
            }
        }
    }
}
