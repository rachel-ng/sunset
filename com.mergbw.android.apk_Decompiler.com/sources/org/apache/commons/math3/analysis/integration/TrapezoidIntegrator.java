package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class TrapezoidIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int TRAPEZOID_MAX_ITERATIONS_COUNT = 64;
    private double s;

    public TrapezoidIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public TrapezoidIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public TrapezoidIntegrator() {
        super(3, 64);
    }

    /* access modifiers changed from: package-private */
    public double stage(BaseAbstractUnivariateIntegrator baseAbstractUnivariateIntegrator, int i) throws TooManyEvaluationsException {
        BaseAbstractUnivariateIntegrator baseAbstractUnivariateIntegrator2 = baseAbstractUnivariateIntegrator;
        if (i == 0) {
            double max = baseAbstractUnivariateIntegrator.getMax();
            double min = baseAbstractUnivariateIntegrator.getMin();
            double computeObjectiveValue = (max - min) * 0.5d * (baseAbstractUnivariateIntegrator2.computeObjectiveValue(min) + baseAbstractUnivariateIntegrator2.computeObjectiveValue(max));
            this.s = computeObjectiveValue;
            return computeObjectiveValue;
        }
        long j = 1 << (i - 1);
        double max2 = baseAbstractUnivariateIntegrator.getMax();
        double min2 = baseAbstractUnivariateIntegrator.getMin();
        double d = (max2 - min2) / ((double) j);
        double d2 = min2 + (d * 0.5d);
        double d3 = 0.0d;
        for (long j2 = 0; j2 < j; j2++) {
            d3 += baseAbstractUnivariateIntegrator2.computeObjectiveValue(d2);
            d2 += d;
        }
        double d4 = (this.s + (d3 * d)) * 0.5d;
        this.s = d4;
        return d4;
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double stage;
        double stage2 = stage(this, 0);
        this.iterations.incrementCount();
        while (true) {
            int count = this.iterations.getCount();
            stage = stage(this, count);
            if (count >= getMinimalIterationCount()) {
                double abs = FastMath.abs(stage - stage2);
                if (abs <= getRelativeAccuracy() * (FastMath.abs(stage2) + FastMath.abs(stage)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return stage;
                }
            }
            this.iterations.incrementCount();
            stage2 = stage;
        }
        return stage;
    }
}
