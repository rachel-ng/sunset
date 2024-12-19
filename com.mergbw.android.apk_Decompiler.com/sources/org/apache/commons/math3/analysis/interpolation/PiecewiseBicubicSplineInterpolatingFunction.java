package org.apache.commons.math3.analysis.interpolation;

import java.util.Arrays;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.InsufficientDataException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

public class PiecewiseBicubicSplineInterpolatingFunction implements BivariateFunction {
    private static final int MIN_NUM_POINTS = 5;
    private final double[][] fval;
    private final double[] xval;
    private final double[] yval;

    public PiecewiseBicubicSplineInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3) throws DimensionMismatchException, NullArgumentException, NoDataException, NonMonotonicSequenceException {
        double[] dArr4;
        if (dArr == null || dArr2 == null || dArr3 == null || (dArr4 = dArr3[0]) == null) {
            throw new NullArgumentException();
        }
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length == 0 || length2 == 0 || dArr3.length == 0 || dArr4.length == 0) {
            throw new NoDataException();
        } else if (length < 5 || length2 < 5 || dArr3.length < 5 || dArr4.length < 5) {
            throw new InsufficientDataException();
        } else if (length != dArr3.length) {
            throw new DimensionMismatchException(length, dArr3.length);
        } else if (length2 == dArr4.length) {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            this.xval = (double[]) dArr.clone();
            this.yval = (double[]) dArr2.clone();
            this.fval = (double[][]) dArr3.clone();
        } else {
            throw new DimensionMismatchException(length2, dArr3[0].length);
        }
    }

    public double value(double d, double d2) throws OutOfRangeException {
        AkimaSplineInterpolator akimaSplineInterpolator = new AkimaSplineInterpolator();
        int searchIndex = searchIndex(d, this.xval, 2, 5);
        int searchIndex2 = searchIndex(d2, this.yval, 2, 5);
        double[] dArr = new double[5];
        double[] dArr2 = new double[5];
        double[] dArr3 = new double[5];
        double[] dArr4 = new double[5];
        for (int i = 0; i < 5; i++) {
            dArr[i] = this.xval[searchIndex + i];
            dArr2[i] = this.yval[searchIndex2 + i];
        }
        for (int i2 = 0; i2 < 5; i2++) {
            for (int i3 = 0; i3 < 5; i3++) {
                dArr3[i3] = this.fval[searchIndex + i3][searchIndex2 + i2];
            }
            dArr4[i2] = akimaSplineInterpolator.interpolate(dArr, dArr3).value(d);
        }
        return akimaSplineInterpolator.interpolate(dArr2, dArr4).value(d2);
    }

    public boolean isValidPoint(double d, double d2) {
        double[] dArr = this.xval;
        if (d >= dArr[0] && d <= dArr[dArr.length - 1]) {
            double[] dArr2 = this.yval;
            return d2 >= dArr2[0] && d2 <= dArr2[dArr2.length - 1];
        }
    }

    private int searchIndex(double d, double[] dArr, int i, int i2) {
        int binarySearch = Arrays.binarySearch(dArr, d);
        int i3 = 0;
        if (binarySearch == -1 || binarySearch == (-dArr.length) - 1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(dArr[0]), Double.valueOf(dArr[dArr.length - 1]));
        }
        int i4 = binarySearch < 0 ? ((-binarySearch) - i) - 1 : binarySearch - i;
        if (i4 >= 0) {
            i3 = i4;
        }
        return i3 + i2 >= dArr.length ? dArr.length - i2 : i3;
    }
}
