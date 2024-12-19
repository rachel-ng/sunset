package org.apache.commons.math3.analysis.polynomials;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class PolynomialSplineFunction implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double[] knots;
    private final int n;
    private final PolynomialFunction[] polynomials;

    public PolynomialSplineFunction(double[] dArr, PolynomialFunction[] polynomialFunctionArr) throws NullArgumentException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        if (dArr == null || polynomialFunctionArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NOT_ENOUGH_POINTS_IN_SPLINE_PARTITION, 2, Integer.valueOf(dArr.length), false);
        } else if (dArr.length - 1 == polynomialFunctionArr.length) {
            MathArrays.checkOrder(dArr);
            int length = dArr.length;
            int i = length - 1;
            this.n = i;
            double[] dArr2 = new double[length];
            this.knots = dArr2;
            System.arraycopy(dArr, 0, dArr2, 0, length);
            PolynomialFunction[] polynomialFunctionArr2 = new PolynomialFunction[i];
            this.polynomials = polynomialFunctionArr2;
            System.arraycopy(polynomialFunctionArr, 0, polynomialFunctionArr2, 0, i);
        } else {
            throw new DimensionMismatchException(polynomialFunctionArr.length, dArr.length);
        }
    }

    public double value(double d) {
        double[] dArr = this.knots;
        if (d < dArr[0] || d > dArr[this.n]) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.n]));
        }
        int binarySearch = Arrays.binarySearch(dArr, d);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        PolynomialFunction[] polynomialFunctionArr = this.polynomials;
        if (binarySearch >= polynomialFunctionArr.length) {
            binarySearch--;
        }
        return polynomialFunctionArr[binarySearch].value(d - this.knots[binarySearch]);
    }

    public UnivariateFunction derivative() {
        return polynomialSplineDerivative();
    }

    public PolynomialSplineFunction polynomialSplineDerivative() {
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[this.n];
        for (int i = 0; i < this.n; i++) {
            polynomialFunctionArr[i] = this.polynomials[i].polynomialDerivative();
        }
        return new PolynomialSplineFunction(this.knots, polynomialFunctionArr);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        double value = derivativeStructure.getValue();
        double[] dArr = this.knots;
        if (value < dArr[0] || value > dArr[this.n]) {
            throw new OutOfRangeException(Double.valueOf(value), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.n]));
        }
        int binarySearch = Arrays.binarySearch(dArr, value);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        PolynomialFunction[] polynomialFunctionArr = this.polynomials;
        if (binarySearch >= polynomialFunctionArr.length) {
            binarySearch--;
        }
        return polynomialFunctionArr[binarySearch].value(derivativeStructure.subtract(this.knots[binarySearch]));
    }

    public int getN() {
        return this.n;
    }

    public PolynomialFunction[] getPolynomials() {
        int i = this.n;
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[i];
        System.arraycopy(this.polynomials, 0, polynomialFunctionArr, 0, i);
        return polynomialFunctionArr;
    }

    public double[] getKnots() {
        int i = this.n;
        double[] dArr = new double[(i + 1)];
        System.arraycopy(this.knots, 0, dArr, 0, i + 1);
        return dArr;
    }

    public boolean isValidPoint(double d) {
        double[] dArr = this.knots;
        return d >= dArr[0] && d <= dArr[this.n];
    }
}
