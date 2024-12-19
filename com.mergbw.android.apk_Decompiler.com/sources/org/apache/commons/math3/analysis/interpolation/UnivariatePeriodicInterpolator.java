package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class UnivariatePeriodicInterpolator implements UnivariateInterpolator {
    public static final int DEFAULT_EXTEND = 5;
    private final int extend;
    private final UnivariateInterpolator interpolator;
    /* access modifiers changed from: private */
    public final double period;

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d, int i) {
        this.interpolator = univariateInterpolator;
        this.period = d;
        this.extend = i;
    }

    public UnivariatePeriodicInterpolator(UnivariateInterpolator univariateInterpolator, double d) {
        this(univariateInterpolator, d, 5);
    }

    public UnivariateFunction interpolate(double[] dArr, double[] dArr2) throws NumberIsTooSmallException, NonMonotonicSequenceException {
        double[] dArr3 = dArr;
        if (dArr3.length >= this.extend) {
            MathArrays.checkOrder(dArr);
            final double d = dArr3[0];
            int length = dArr3.length + (this.extend * 2);
            double[] dArr4 = new double[length];
            double[] dArr5 = new double[length];
            for (int i = 0; i < dArr3.length; i++) {
                int i2 = i + this.extend;
                dArr4[i2] = MathUtils.reduce(dArr3[i], this.period, d);
                dArr5[i2] = dArr2[i];
            }
            int i3 = 0;
            while (true) {
                int i4 = this.extend;
                if (i3 < i4) {
                    int length2 = (dArr3.length - i4) + i3;
                    double d2 = d;
                    double reduce = MathUtils.reduce(dArr3[length2], this.period, d2);
                    double d3 = this.period;
                    dArr4[i3] = reduce - d3;
                    dArr5[i3] = dArr2[length2];
                    int i5 = (length - this.extend) + i3;
                    dArr4[i5] = MathUtils.reduce(dArr3[i3], d3, d2) + this.period;
                    dArr5[i5] = dArr2[i3];
                    i3++;
                } else {
                    MathArrays.sortInPlace(dArr4, dArr5);
                    final UnivariateFunction interpolate = this.interpolator.interpolate(dArr4, dArr5);
                    return new UnivariateFunction() {
                        public double value(double d) throws MathIllegalArgumentException {
                            return interpolate.value(MathUtils.reduce(d, UnivariatePeriodicInterpolator.this.period, d));
                        }
                    };
                }
            }
        } else {
            throw new NumberIsTooSmallException(Integer.valueOf(dArr3.length), Integer.valueOf(this.extend), true);
        }
    }
}
