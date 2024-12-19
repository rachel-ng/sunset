package org.apache.commons.math3.analysis.function;

import java.util.Arrays;
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
import org.apache.commons.math3.util.Precision;

public class Gaussian implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private final double i2s2;
    private final double is;
    private final double mean;
    private final double norm;

    public Gaussian(double d, double d2, double d3) throws NotStrictlyPositiveException {
        if (d3 > 0.0d) {
            this.norm = d;
            this.mean = d2;
            double d4 = 1.0d / d3;
            this.is = d4;
            this.i2s2 = 0.5d * d4 * d4;
            return;
        }
        throw new NotStrictlyPositiveException(Double.valueOf(d3));
    }

    public Gaussian(double d, double d2) throws NotStrictlyPositiveException {
        this(1.0d / (FastMath.sqrt(6.283185307179586d) * d2), d, d2);
    }

    public Gaussian() {
        this(0.0d, 1.0d);
    }

    public double value(double d) {
        return value(d - this.mean, this.norm, this.i2s2);
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public static class Parametric implements ParametricUnivariateFunction {
        public double value(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            validateParameters(dArr);
            double d2 = d - dArr[1];
            double d3 = dArr[2];
            return Gaussian.value(d2, dArr[0], 1.0d / ((2.0d * d3) * d3));
        }

        public double[] gradient(double d, double... dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            double[] dArr2 = dArr;
            validateParameters(dArr2);
            double d2 = dArr2[0];
            double d3 = d - dArr2[1];
            double d4 = dArr2[2];
            double d5 = 1.0d / ((d4 * 2.0d) * d4);
            double access$000 = Gaussian.value(d3, 1.0d, d5);
            double d6 = d2 * access$000 * 2.0d * d5 * d3;
            return new double[]{access$000, d6, (d3 * d6) / d4};
        }

        private void validateParameters(double[] dArr) throws NullArgumentException, DimensionMismatchException, NotStrictlyPositiveException {
            if (dArr == null) {
                throw new NullArgumentException();
            } else if (dArr.length != 3) {
                throw new DimensionMismatchException(dArr.length, 3);
            } else if (dArr[2] <= 0.0d) {
                throw new NotStrictlyPositiveException(Double.valueOf(dArr[2]));
            }
        }
    }

    /* access modifiers changed from: private */
    public static double value(double d, double d2, double d3) {
        return d2 * FastMath.exp((-d) * d * d3);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        double d;
        double value = this.is * (derivativeStructure.getValue() - this.mean);
        int i = 1;
        int order = derivativeStructure.getOrder() + 1;
        double[] dArr = new double[order];
        double[] dArr2 = new double[order];
        dArr2[0] = 1.0d;
        double d2 = value * value;
        double exp = this.norm * FastMath.exp(-0.5d * d2);
        if (exp <= Precision.SAFE_MIN) {
            Arrays.fill(dArr, 0.0d);
        } else {
            dArr[0] = exp;
            int i2 = 1;
            while (i2 < order) {
                dArr2[i2] = -dArr2[i2 - 1];
                int i3 = i2;
                double d3 = 0.0d;
                while (i3 >= 0) {
                    d3 = (d3 * d2) + dArr2[i3];
                    if (i3 > 2) {
                        int i4 = i3 - 1;
                        d = d2;
                        dArr2[i3 - 2] = (((double) i4) * dArr2[i4]) - dArr2[i3 - 3];
                        i = 1;
                    } else {
                        d = d2;
                        i = 1;
                        if (i3 == 2) {
                            dArr2[0] = dArr2[1];
                            i3 -= 2;
                            d2 = d;
                        }
                    }
                    i3 -= 2;
                    d2 = d;
                }
                double d4 = d2;
                if ((i2 & 1) == i) {
                    d3 *= value;
                }
                exp *= this.is;
                dArr[i2] = d3 * exp;
                i2++;
                d2 = d4;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
