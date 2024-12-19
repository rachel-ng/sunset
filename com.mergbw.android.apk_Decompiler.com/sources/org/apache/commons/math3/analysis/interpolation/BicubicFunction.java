package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

/* compiled from: BicubicInterpolatingFunction */
class BicubicFunction implements BivariateFunction {
    private static final short N = 4;

    /* renamed from: a  reason: collision with root package name */
    private final double[][] f62a;

    public BicubicFunction(double[] dArr) {
        int[] iArr = new int[2];
        iArr[1] = 4;
        iArr[0] = 4;
        this.f62a = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < 4; i++) {
            double[] dArr2 = this.f62a[i];
            for (int i2 = 0; i2 < 4; i2++) {
                dArr2[i2] = dArr[(i2 * 4) + i];
            }
        }
    }

    public double value(double d, double d2) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), 0, 1);
        } else {
            double d3 = d * d;
            double[] dArr = {1.0d, d, d3, d3 * d};
            double d4 = d2 * d2;
            return apply(dArr, new double[]{1.0d, d2, d4, d4 * d2}, this.f62a);
        }
    }

    private double apply(double[] dArr, double[] dArr2, double[][] dArr3) {
        double d = 0.0d;
        for (int i = 0; i < 4; i++) {
            d += MathArrays.linearCombination(dArr3[i], dArr2) * dArr[i];
        }
        return d;
    }
}
