package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class Sinc implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    private static final double SHORTCUT = 0.006d;
    private final boolean normalized;

    public Sinc() {
        this(false);
    }

    public Sinc(boolean z) {
        this.normalized = z;
    }

    public double value(double d) {
        double sin;
        if (this.normalized) {
            d *= 3.141592653589793d;
        }
        if (FastMath.abs(d) <= SHORTCUT) {
            double d2 = d * d;
            double d3 = (d2 - 20.0d) * d2;
            d = 120.0d;
            sin = d3 + 120.0d;
        } else {
            sin = FastMath.sin(d);
        }
        return sin / d;
    }

    @Deprecated
    public UnivariateFunction derivative() {
        return FunctionUtils.toDifferentiableUnivariateFunction(this).derivative();
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) throws DimensionMismatchException {
        int i;
        double[] dArr;
        double d;
        int i2;
        double[] dArr2;
        Sinc sinc = this;
        double d2 = 1.0d;
        double value = (sinc.normalized ? 3.141592653589793d : 1.0d) * derivativeStructure.getValue();
        double d3 = value * value;
        int i3 = 1;
        int order = derivativeStructure.getOrder() + 1;
        double[] dArr3 = new double[order];
        int i4 = 0;
        if (FastMath.abs(value) <= SHORTCUT) {
            while (i4 < order) {
                int i5 = i4 / 2;
                if ((i4 & 1) == 0) {
                    dArr2 = dArr3;
                    dArr2[i4] = ((double) ((i5 & 1) == 0 ? i3 : -1)) * ((d2 / ((double) (i4 + 1))) - (((d2 / ((double) ((i4 * 2) + 6))) - (d3 / ((double) ((i4 * 24) + 120)))) * d3));
                } else {
                    dArr2 = dArr3;
                    dArr2[i4] = ((i5 & 1) == 0 ? -value : value) * ((1.0d / ((double) (i4 + 2))) - (((1.0d / ((double) ((i4 * 6) + 24))) - (d3 / ((double) ((i4 * 120) + 720)))) * d3));
                }
                i4++;
                dArr3 = dArr2;
                d2 = 1.0d;
                i3 = 1;
            }
            dArr = dArr3;
            i = order;
        } else {
            dArr = dArr3;
            double d4 = 1.0d / value;
            double cos = FastMath.cos(value);
            double sin = FastMath.sin(value);
            dArr[0] = d4 * sin;
            double[] dArr4 = new double[order];
            dArr4[0] = 1.0d;
            double d5 = d4;
            int i6 = 1;
            while (i6 < order) {
                double d6 = 0.0d;
                if ((i6 & 1) == 0) {
                    dArr4[i6] = 0.0d;
                    i2 = i6;
                    d = 0.0d;
                } else {
                    i2 = i6 - 1;
                    d = dArr4[i2];
                    dArr4[i6] = d;
                }
                while (i2 > 1) {
                    int i7 = i2 - 1;
                    double d7 = (((double) (i2 - i6)) * dArr4[i2]) - dArr4[i7];
                    dArr4[i2] = d7;
                    d6 = (d6 * d3) + d7;
                    double d8 = (((double) (i7 - i6)) * dArr4[i7]) + dArr4[i2 - 2];
                    dArr4[i7] = d8;
                    d = (d * d3) + d8;
                    i2 -= 2;
                    order = order;
                }
                int i8 = i6;
                double d9 = dArr4[0] * ((double) (-i6));
                dArr4[0] = d9;
                d5 *= d4;
                dArr[i8] = ((((d6 * d3) + d9) * sin) + (d * value * cos)) * d5;
                i6 = i8 + 1;
                order = order;
            }
            i = order;
            sinc = this;
        }
        if (sinc.normalized) {
            int i9 = i;
            double d10 = 3.141592653589793d;
            for (int i10 = 1; i10 < i9; i10++) {
                dArr[i10] = dArr[i10] * d10;
                d10 *= 3.141592653589793d;
            }
        }
        return derivativeStructure.compose(dArr);
    }
}
