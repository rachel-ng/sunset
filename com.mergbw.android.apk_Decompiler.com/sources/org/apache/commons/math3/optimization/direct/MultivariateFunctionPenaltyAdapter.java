package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

@Deprecated
public class MultivariateFunctionPenaltyAdapter implements MultivariateFunction {
    private final MultivariateFunction bounded;
    private final double[] lower;
    private final double offset;
    private final double[] scale;
    private final double[] upper;

    public MultivariateFunctionPenaltyAdapter(MultivariateFunction multivariateFunction, double[] dArr, double[] dArr2, double d, double[] dArr3) {
        MathUtils.checkNotNull(dArr);
        MathUtils.checkNotNull(dArr2);
        MathUtils.checkNotNull(dArr3);
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length == dArr3.length) {
            int i = 0;
            while (i < dArr.length) {
                if (dArr2[i] >= dArr[i]) {
                    i++;
                } else {
                    throw new NumberIsTooSmallException(Double.valueOf(dArr2[i]), Double.valueOf(dArr[i]), true);
                }
            }
            this.bounded = multivariateFunction;
            this.lower = (double[]) dArr.clone();
            this.upper = (double[]) dArr2.clone();
            this.offset = d;
            this.scale = (double[]) dArr3.clone();
        } else {
            throw new DimensionMismatchException(dArr.length, dArr3.length);
        }
    }

    public double value(double[] dArr) {
        double d;
        int i = 0;
        while (i < this.scale.length) {
            double d2 = dArr[i];
            if (d2 < this.lower[i] || d2 > this.upper[i]) {
                double d3 = 0.0d;
                while (true) {
                    double[] dArr2 = this.scale;
                    if (i >= dArr2.length) {
                        return this.offset + d3;
                    }
                    double d4 = dArr[i];
                    double d5 = this.lower[i];
                    if (d4 < d5) {
                        d = dArr2[i] * (d5 - d4);
                    } else {
                        double d6 = this.upper[i];
                        d = d4 > d6 ? dArr2[i] * (d4 - d6) : 0.0d;
                    }
                    d3 += FastMath.sqrt(d);
                    i++;
                }
            } else {
                i++;
            }
        }
        return this.bounded.value(dArr);
    }
}
