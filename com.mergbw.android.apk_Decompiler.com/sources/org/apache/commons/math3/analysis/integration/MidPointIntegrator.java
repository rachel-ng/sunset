package org.apache.commons.math3.analysis.integration;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MidPointIntegrator extends BaseAbstractUnivariateIntegrator {
    public static final int MIDPOINT_MAX_ITERATIONS_COUNT = 64;

    public MidPointIntegrator(double d, double d2, int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(d, d2, i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public MidPointIntegrator(int i, int i2) throws NotStrictlyPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        super(i, i2);
        if (i2 > 64) {
            throw new NumberIsTooLargeException(Integer.valueOf(i2), 64, false);
        }
    }

    public MidPointIntegrator() {
        super(3, 64);
    }

    private double stage(int i, double d, double d2, double d3) throws TooManyEvaluationsException {
        long j = 1 << (i - 1);
        double d4 = d3 / ((double) j);
        double d5 = d2 + (d4 * 0.5d);
        double d6 = 0.0d;
        for (long j2 = 0; j2 < j; j2++) {
            d6 += computeObjectiveValue(d5);
            d5 += d4;
        }
        return (d + (d6 * d4)) * 0.5d;
    }

    /* access modifiers changed from: protected */
    public double doIntegrate() throws MathIllegalArgumentException, TooManyEvaluationsException, MaxCountExceededException {
        double stage;
        long j;
        double min = getMin();
        double max = getMax() - min;
        double computeObjectiveValue = computeObjectiveValue((max * 0.5d) + min) * max;
        while (true) {
            this.iterations.incrementCount();
            int count = this.iterations.getCount();
            int i = count;
            stage = stage(count, computeObjectiveValue, min, max);
            if (i >= getMinimalIterationCount()) {
                double abs = FastMath.abs(stage - computeObjectiveValue);
                j = 4602678819172646912L;
                if (abs <= getRelativeAccuracy() * (FastMath.abs(computeObjectiveValue) + FastMath.abs(stage)) * 0.5d || abs <= getAbsoluteAccuracy()) {
                    return stage;
                }
            } else {
                j = 4602678819172646912L;
            }
            computeObjectiveValue = stage;
            long j2 = j;
        }
        return stage;
    }
}
