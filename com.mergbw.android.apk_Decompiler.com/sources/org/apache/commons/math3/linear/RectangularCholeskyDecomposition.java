package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

public class RectangularCholeskyDecomposition {
    private int rank;
    private final RealMatrix root;

    public RectangularCholeskyDecomposition(RealMatrix realMatrix) throws NonPositiveDefiniteMatrixException {
        this(realMatrix, 0.0d);
    }

    public RectangularCholeskyDecomposition(RealMatrix realMatrix, double d) throws NonPositiveDefiniteMatrixException {
        double d2 = d;
        int rowDimension = realMatrix.getRowDimension();
        double[][] data = realMatrix.getData();
        int[] iArr = new int[2];
        iArr[1] = rowDimension;
        boolean z = false;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int[] iArr2 = new int[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            iArr2[i] = i;
        }
        boolean z2 = true;
        int i2 = 0;
        while (z2) {
            int i3 = i2 + 1;
            int i4 = i2;
            for (int i5 = i3; i5 < rowDimension; i5++) {
                int i6 = iArr2[i5];
                int i7 = iArr2[i4];
                if (data[i6][i6] > data[i7][i7]) {
                    i4 = i5;
                }
            }
            if (i4 != i2) {
                int i8 = iArr2[i2];
                iArr2[i2] = iArr2[i4];
                iArr2[i4] = i8;
                double[] dArr2 = dArr[i2];
                dArr[i2] = dArr[i4];
                dArr[i4] = dArr2;
            }
            int i9 = iArr2[i2];
            double d3 = data[i9][i9];
            if (d3 > d2) {
                double sqrt = FastMath.sqrt(d3);
                dArr[i2][i2] = sqrt;
                double d4 = 1.0d / sqrt;
                double d5 = 1.0d / data[i9][i9];
                for (int i10 = i3; i10 < rowDimension; i10++) {
                    int i11 = iArr2[i10];
                    double[] dArr3 = data[i11];
                    double d6 = dArr3[i9] * d4;
                    dArr[i10][i2] = d6;
                    double d7 = dArr3[i11];
                    double d8 = dArr3[i9];
                    dArr3[i11] = d7 - ((d8 * d8) * d5);
                    for (int i12 = i3; i12 < i10; i12++) {
                        int i13 = iArr2[i12];
                        double[] dArr4 = data[i11];
                        double d9 = dArr4[i13] - (dArr[i12][i2] * d6);
                        dArr4[i13] = d9;
                        data[i13][i11] = d9;
                    }
                }
                i2 = i3;
                z2 = i3 < rowDimension;
            } else if (i2 != 0) {
                int i14 = i2;
                while (i14 < rowDimension) {
                    int i15 = iArr2[i14];
                    if (data[i15][i15] >= (-d2)) {
                        i14++;
                    } else {
                        int i16 = iArr2[i14];
                        throw new NonPositiveDefiniteMatrixException(data[i16][i16], i14, d);
                    }
                }
                z2 = z;
            } else {
                throw new NonPositiveDefiniteMatrixException(data[i9][i9], i9, d);
            }
            z = false;
        }
        this.rank = i2;
        this.root = MatrixUtils.createRealMatrix(rowDimension, i2);
        for (int i17 = 0; i17 < rowDimension; i17++) {
            for (int i18 = 0; i18 < i2; i18++) {
                this.root.setEntry(iArr2[i17], i18, dArr[i17][i18]);
            }
        }
    }

    public RealMatrix getRootMatrix() {
        return this.root;
    }

    public int getRank() {
        return this.rank;
    }
}
