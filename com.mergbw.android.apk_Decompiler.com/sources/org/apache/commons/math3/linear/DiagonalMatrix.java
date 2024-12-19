package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public class DiagonalMatrix extends AbstractRealMatrix implements Serializable {
    private static final long serialVersionUID = 20121229;
    private final double[] data;

    public DiagonalMatrix(int i) throws NotStrictlyPositiveException {
        super(i, i);
        this.data = new double[i];
    }

    public DiagonalMatrix(double[] dArr) {
        this(dArr, true);
    }

    public DiagonalMatrix(double[] dArr, boolean z) throws NullArgumentException {
        MathUtils.checkNotNull(dArr);
        this.data = z ? (double[]) dArr.clone() : dArr;
    }

    public RealMatrix createMatrix(int i, int i2) throws NotStrictlyPositiveException, DimensionMismatchException {
        if (i == i2) {
            return new DiagonalMatrix(i);
        }
        throw new DimensionMismatchException(i, i2);
    }

    public RealMatrix copy() {
        return new DiagonalMatrix(this.data);
    }

    public DiagonalMatrix add(DiagonalMatrix diagonalMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, diagonalMatrix);
        int rowDimension = getRowDimension();
        double[] dArr = new double[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            dArr[i] = this.data[i] + diagonalMatrix.data[i];
        }
        return new DiagonalMatrix(dArr, false);
    }

    public DiagonalMatrix subtract(DiagonalMatrix diagonalMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, diagonalMatrix);
        int rowDimension = getRowDimension();
        double[] dArr = new double[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            dArr[i] = this.data[i] - diagonalMatrix.data[i];
        }
        return new DiagonalMatrix(dArr, false);
    }

    public DiagonalMatrix multiply(DiagonalMatrix diagonalMatrix) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, diagonalMatrix);
        int rowDimension = getRowDimension();
        double[] dArr = new double[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            dArr[i] = this.data[i] * diagonalMatrix.data[i];
        }
        return new DiagonalMatrix(dArr, false);
    }

    public RealMatrix multiply(RealMatrix realMatrix) throws DimensionMismatchException {
        if (realMatrix instanceof DiagonalMatrix) {
            return multiply((DiagonalMatrix) realMatrix);
        }
        MatrixUtils.checkMultiplicationCompatible(this, realMatrix);
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                dArr[i][i2] = this.data[i] * realMatrix.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    public double[][] getData() {
        int rowDimension = getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = rowDimension;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < rowDimension; i++) {
            dArr[i][i] = this.data[i];
        }
        return dArr;
    }

    public double[] getDataRef() {
        return this.data;
    }

    public double getEntry(int i, int i2) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        if (i == i2) {
            return this.data[i];
        }
        return 0.0d;
    }

    public void setEntry(int i, int i2, double d) throws OutOfRangeException, NumberIsTooLargeException {
        if (i == i2) {
            MatrixUtils.checkRowIndex(this, i);
            this.data[i] = d;
            return;
        }
        ensureZero(d);
    }

    public void addToEntry(int i, int i2, double d) throws OutOfRangeException, NumberIsTooLargeException {
        if (i == i2) {
            MatrixUtils.checkRowIndex(this, i);
            double[] dArr = this.data;
            dArr[i] = dArr[i] + d;
            return;
        }
        ensureZero(d);
    }

    public void multiplyEntry(int i, int i2, double d) throws OutOfRangeException {
        if (i == i2) {
            MatrixUtils.checkRowIndex(this, i);
            double[] dArr = this.data;
            dArr[i] = dArr[i] * d;
        }
    }

    public int getRowDimension() {
        return this.data.length;
    }

    public int getColumnDimension() {
        return this.data.length;
    }

    public double[] operate(double[] dArr) throws DimensionMismatchException {
        return multiply(new DiagonalMatrix(dArr, false)).getDataRef();
    }

    public double[] preMultiply(double[] dArr) throws DimensionMismatchException {
        return operate(dArr);
    }

    public RealVector preMultiply(RealVector realVector) throws DimensionMismatchException {
        double[] dArr;
        if (realVector instanceof ArrayRealVector) {
            dArr = ((ArrayRealVector) realVector).getDataRef();
        } else {
            dArr = realVector.toArray();
        }
        return MatrixUtils.createRealVector(preMultiply(dArr));
    }

    private void ensureZero(double d) throws NumberIsTooLargeException {
        if (!Precision.equals(0.0d, d, 1)) {
            throw new NumberIsTooLargeException(Double.valueOf(FastMath.abs(d)), 0, true);
        }
    }

    public DiagonalMatrix inverse() throws SingularMatrixException {
        return inverse(0.0d);
    }

    public DiagonalMatrix inverse(double d) throws SingularMatrixException {
        if (!isSingular(d)) {
            double[] dArr = new double[this.data.length];
            int i = 0;
            while (true) {
                double[] dArr2 = this.data;
                if (i >= dArr2.length) {
                    return new DiagonalMatrix(dArr, false);
                }
                dArr[i] = 1.0d / dArr2[i];
                i++;
            }
        } else {
            throw new SingularMatrixException();
        }
    }

    public boolean isSingular(double d) {
        int i = 0;
        while (true) {
            double[] dArr = this.data;
            if (i >= dArr.length) {
                return false;
            }
            if (Precision.equals(dArr[i], 0.0d, d)) {
                return true;
            }
            i++;
        }
    }
}
