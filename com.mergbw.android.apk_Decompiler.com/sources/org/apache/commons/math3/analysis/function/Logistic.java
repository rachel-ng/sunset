package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.util.FastMath;

public class Logistic implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    /* renamed from: a  reason: collision with root package name */
    private final double f60a;

    /* renamed from: b  reason: collision with root package name */
    private final double f61b;
    private final double k;
    private final double m;
    private final double oneOverN;
    private final double q;

    public Logistic(double d, double d2, double d3, double d4, double d5, double d6) throws NotStrictlyPositiveException {
        if (d6 > 0.0d) {
            this.k = d;
            this.m = d2;
            this.f61b = d3;
            this.q = d4;
            this.f60a = d5;
            this.oneOverN = 1.0d / d6;
            return;
        }
        throw new NotStrictlyPositiveException(Double.valueOf(d6));
    }

    public double value(double d) {
        return value(this.m - d, this.k, this.f61b, this.q, this.f60a, this.oneOverN);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            double[] dArr2 = dArr;
            validateParameters(dArr2);
            return Logistic.value(dArr2[1] - d, dArr2[0], dArr2[2], dArr2[3], dArr2[4], 1.0d / dArr2[5]);
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            double[] dArr2 = dArr;
            validateParameters(dArr2);
            double d2 = dArr2[2];
            double d3 = dArr2[3];
            double d4 = dArr2[1] - d;
            double d5 = 1.0d / dArr2[5];
            double exp = FastMath.exp(d2 * d4);
            double d6 = d3 * exp;
            double d7 = d6 + 1.0d;
            double pow = ((dArr2[0] - dArr2[4]) * d5) / FastMath.pow(d7, d5);
            double d8 = (-pow) / d7;
            double d9 = d2;
            double d10 = pow;
            double d11 = d3;
            return new double[]{Logistic.value(d4, 1.0d, d9, d11, 0.0d, d5), d8 * d2 * d6, d6 * d8 * d4, d8 * exp, Logistic.value(d4, 0.0d, d9, d11, 1.0d, d5), d10 * FastMath.log(d7) * d5};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 6) {
                throw new DimensionMismatchException(dArr.length, 6);
            } else if (dArr[5] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[5]));
            }
        }
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3, double d4, double d5, double d6) {
        return d5 + ((d2 - d5) / FastMath.pow((d4 * FastMath.exp(d3 * d)) + 1.0d, d6));
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return derivativeStructure.negate().add(this.m).multiply(this.f61b).exp().multiply(this.q).add(1.0d).pow(this.oneOverN).reciprocal().multiply(this.k - this.f60a).add(this.f60a);
    }
}
