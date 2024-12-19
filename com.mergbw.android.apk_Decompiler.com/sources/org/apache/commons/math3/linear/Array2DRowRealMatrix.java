package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

public class Array2DRowRealMatrix extends AbstractRealMatrix implements Serializable {
    private static final long serialVersionUID = -1067294169172445528L;
    private double[][] data;

    public Array2DRowRealMatrix() {
    }

    public Array2DRowRealMatrix(int i, int i2) throws NotStrictlyPositiveException {
        super(i, i2);
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i;
        this.data = (double[][]) Array.newInstance(Double.TYPE, iArr);
    }

    public Array2DRowRealMatrix(double[][] dArr) throws DimensionMismatchException, NoDataException, NullArgumentException {
        copyIn(dArr);
    }

    public Array2DRowRealMatrix(double[][] dArr, boolean z) throws DimensionMismatchException, NoDataException, NullArgumentException {
        if (z) {
            copyIn(dArr);
        } else if (dArr != null) {
            int length = dArr.length;
            if (length != 0) {
                int length2 = dArr[0].length;
                if (length2 != 0) {
                    int i = 1;
                    while (i < length) {
                        if (dArr[i].length == length2) {
                            i++;
                        } else {
                            throw new DimensionMismatchException(dArr[i].length, length2);
                        }
                    }
                    this.data = dArr;
                    return;
                }
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        } else {
            throw new NullArgumentException();
        }
    }

    public Array2DRowRealMatrix(double[] dArr) {
        int length = dArr.length;
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = length;
        this.data = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < length; i++) {
            this.data[i][0] = dArr[i];
        }
    }

    public RealMatrix createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new Array2DRowRealMatrix(i, i2);
    }

    public RealMatrix copy() {
        return new Array2DRowRealMatrix(copyOut(), false);
    }

    public Array2DRowRealMatrix add(Array2DRowRealMatrix array2DRowRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, array2DRowRealMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < rowDimension; i++) {
            double[] dArr2 = this.data[i];
            double[] dArr3 = array2DRowRealMatrix.data[i];
            double[] dArr4 = dArr[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                dArr4[i2] = dArr2[i2] + dArr3[i2];
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    public Array2DRowRealMatrix subtract(Array2DRowRealMatrix array2DRowRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, array2DRowRealMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < rowDimension; i++) {
            double[] dArr2 = this.data[i];
            double[] dArr3 = array2DRowRealMatrix.data[i];
            double[] dArr4 = dArr[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                dArr4[i2] = dArr2[i2] - dArr3[i2];
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    public Array2DRowRealMatrix multiply(Array2DRowRealMatrix array2DRowRealMatrix) throws DimensionMismatchException {
        MatrixUtils.checkMultiplicationCompatible(this, array2DRowRealMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = array2DRowRealMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        int[] iArr = new int[2];
        iArr[1] = columnDimension;
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        double[] dArr2 = new double[columnDimension2];
        double[][] dArr3 = array2DRowRealMatrix.data;
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < columnDimension2; i2++) {
                dArr2[i2] = dArr3[i2][i];
            }
            for (int i3 = 0; i3 < rowDimension; i3++) {
                double[] dArr4 = this.data[i3];
                double d = 0.0d;
                for (int i4 = 0; i4 < columnDimension2; i4++) {
                    d += dArr4[i4] * dArr2[i4];
                }
                dArr[i3][i] = d;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    public double[][] getData() {
        return copyOut();
    }

    public double[][] getDataRef() {
        return this.data;
    }

    public void setSubMatrix(double[][] dArr, int i, int i2) throws NoDataException, OutOfRangeException, DimensionMismatchException, NullArgumentException {
        if (this.data != null) {
            super.setSubMatrix(dArr, i, i2);
        } else if (i > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, Integer.valueOf(i));
        } else if (i2 <= 0) {
            MathUtils.checkNotNull(dArr);
            if (dArr.length != 0) {
                int length = dArr[0].length;
                if (length != 0) {
                    int length2 = dArr.length;
                    int[] iArr = new int[2];
                    iArr[1] = length;
                    iArr[0] = length2;
                    this.data = (double[][]) Array.newInstance(Double.TYPE, iArr);
                    int i3 = 0;
                    while (true) {
                        double[][] dArr2 = this.data;
                        if (i3 < dArr2.length) {
                            double[] dArr3 = dArr[i3];
                            if (dArr3.length == length) {
                                System.arraycopy(dArr3, 0, dArr2[i3 + i], i2, length);
                                i3++;
                            } else {
                                throw new DimensionMismatchException(dArr[i3].length, length);
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
                }
            } else {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
            }
        } else {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, Integer.valueOf(i2));
        }
    }

    public double getEntry(int i, int i2) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        return this.data[i][i2];
    }

    public void setEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        this.data[i][i2] = d;
    }

    public void addToEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        double[] dArr = this.data[i];
        dArr[i2] = dArr[i2] + d;
    }

    public void multiplyEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        double[] dArr = this.data[i];
        dArr[i2] = dArr[i2] * d;
    }

    public int getRowDimension() {
        double[][] dArr = this.data;
        if (dArr == null) {
            return 0;
        }
        return dArr.length;
    }

    public int getColumnDimension() {
        double[] dArr;
        double[][] dArr2 = this.data;
        if (dArr2 == null || (dArr = dArr2[0]) == null) {
            return 0;
        }
        return dArr.length;
    }

    public double[] operate(double[] dArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (dArr.length == columnDimension) {
            double[] dArr2 = new double[rowDimension];
            for (int i = 0; i < rowDimension; i++) {
                double[] dArr3 = this.data[i];
                double d = 0.0d;
                for (int i2 = 0; i2 < columnDimension; i2++) {
                    d += dArr3[i2] * dArr[i2];
                }
                dArr2[i] = d;
            }
            return dArr2;
        }
        throw new DimensionMismatchException(dArr.length, columnDimension);
    }

    public double[] preMultiply(double[] dArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (dArr.length == rowDimension) {
            double[] dArr2 = new double[columnDimension];
            for (int i = 0; i < columnDimension; i++) {
                double d = 0.0d;
                for (int i2 = 0; i2 < rowDimension; i2++) {
                    d += this.data[i2][i] * dArr[i2];
                }
                dArr2[i] = d;
            }
            return dArr2;
        }
        throw new DimensionMismatchException(dArr.length, rowDimension);
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            double[] dArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                dArr[i2] = realMatrixChangingVisitor.visit(i, i2, dArr[i2]);
            }
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            double[] dArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                realMatrixPreservingVisitor.visit(i, i2, dArr[i2]);
            }
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            double[] dArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                dArr[i5] = realMatrixChangingVisitor.visit(i, i5, dArr[i5]);
            }
            i++;
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            double[] dArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                realMatrixPreservingVisitor.visit(i, i5, dArr[i5]);
            }
            i++;
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                double[] dArr = this.data[i2];
                dArr[i] = realMatrixChangingVisitor.visit(i2, i, dArr[i]);
            }
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        realMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                realMatrixPreservingVisitor.visit(i2, i, this.data[i2][i]);
            }
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                double[] dArr = this.data[i5];
                dArr[i3] = realMatrixChangingVisitor.visit(i5, i3, dArr[i3]);
            }
            i3++;
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInColumnOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        realMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                realMatrixPreservingVisitor.visit(i5, i3, this.data[i5][i3]);
            }
            i3++;
        }
        return realMatrixPreservingVisitor.end();
    }

    private double[][] copyOut() {
        int rowDimension = getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < rowDimension; i++) {
            double[] dArr2 = this.data[i];
            System.arraycopy(dArr2, 0, dArr[i], 0, dArr2.length);
        }
        return dArr;
    }

    private void copyIn(double[][] dArr) throws DimensionMismatchException, NoDataException, NullArgumentException {
        setSubMatrix(dArr, 0, 0);
    }
}
