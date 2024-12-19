package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

public class PolynomialFunctionNewtonForm implements UnivariateDifferentiableFunction {

    /* renamed from: a  reason: collision with root package name */
    private final double[] f66a;

    /* renamed from: c  reason: collision with root package name */
    private final double[] f67c;
    private double[] coefficients;
    private boolean coefficientsComputed = false;

    public PolynomialFunctionNewtonForm(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        verifyInputArray(dArr, dArr2);
        double[] dArr3 = new double[dArr.length];
        this.f66a = dArr3;
        double[] dArr4 = new double[dArr2.length];
        this.f67c = dArr4;
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
    }

    public double value(double d) {
        return evaluate(this.f66a, this.f67c, d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        verifyInputArray(this.f66a, this.f67c);
        int length = this.f67c.length;
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.f66a[length]);
        for (int i = length - 1; i >= 0; i--) {
            derivativeStructure2 = derivativeStructure.subtract(this.f67c[i]).multiply(derivativeStructure2).add(this.f66a[i]);
        }
        return derivativeStructure2;
    }

    public int degree() {
        return this.f67c.length;
    }

    public double[] getNewtonCoefficients() {
        double[] dArr = this.f66a;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getCenters() {
        double[] dArr = this.f67c;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] dArr = this.coefficients;
        double[] dArr2 = new double[dArr.length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        return dArr2;
    }

    public static double evaluate(double[] dArr, double[] dArr2, double d) throws NullArgumentException, DimensionMismatchException, NoDataException {
        verifyInputArray(dArr, dArr2);
        int length = dArr2.length;
        double d2 = dArr[length];
        for (int i = length - 1; i >= 0; i--) {
            d2 = dArr[i] + ((d - dArr2[i]) * d2);
        }
        return d2;
    }

    /* access modifiers changed from: protected */
    public void computeCoefficients() {
        int degree = degree();
        this.coefficients = new double[(degree + 1)];
        for (int i = 0; i <= degree; i++) {
            this.coefficients[i] = 0.0d;
        }
        this.coefficients[0] = this.f66a[degree];
        for (int i2 = degree - 1; i2 >= 0; i2--) {
            for (int i3 = degree - i2; i3 > 0; i3--) {
                double[] dArr = this.coefficients;
                dArr[i3] = dArr[i3 - 1] - (this.f67c[i2] * dArr[i3]);
            }
            double[] dArr2 = this.coefficients;
            dArr2[0] = this.f66a[i2] - (this.f67c[i2] * dArr2[0]);
        }
        this.coefficientsComputed = true;
    }

    protected static void verifyInputArray(double[] dArr, double[] dArr2) throws NullArgumentException, NoDataException, DimensionMismatchException {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        } else if (dArr.length != dArr2.length + 1) {
            throw new DimensionMismatchException(LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1, dArr.length, dArr2.length);
        }
    }
}
