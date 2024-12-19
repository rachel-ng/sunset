package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver() {
        this(1.0E-6d);
    }

    public MullerSolver(double d) {
        super(d);
    }

    public MullerSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue) < functionValueAccuracy) {
            return min;
        }
        double computeObjectiveValue2 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue2) < functionValueAccuracy) {
            return max;
        }
        double computeObjectiveValue3 = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue3) < functionValueAccuracy) {
            return startValue;
        }
        verifyBracketing(min, max);
        if (isBracketing(min, startValue)) {
            return solve(min, startValue, computeObjectiveValue, computeObjectiveValue3);
        }
        return solve(startValue, max, computeObjectiveValue3, computeObjectiveValue2);
    }

    private double solve(double d, double d2, double d3, double d4) throws TooManyEvaluationsException {
        double sqrt;
        long j;
        int i;
        double relativeAccuracy = getRelativeAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double d5 = (d + d2) * 0.5d;
        double d6 = d3;
        double d7 = d4;
        double d8 = d5;
        double computeObjectiveValue = computeObjectiveValue(d5);
        double d9 = Double.POSITIVE_INFINITY;
        double d10 = d;
        double d11 = d2;
        while (true) {
            double d12 = d8 - d10;
            double d13 = (computeObjectiveValue - d6) / d12;
            double d14 = d11 - d8;
            double d15 = d11 - d10;
            double d16 = (((d7 - computeObjectiveValue) / d14) - d13) / d15;
            double d17 = d13 + (d12 * d16);
            double d18 = (d17 * d17) - ((4.0d * computeObjectiveValue) * d16);
            double d19 = -2.0d * computeObjectiveValue;
            double sqrt2 = d8 + (d19 / (d17 + FastMath.sqrt(d18)));
            sqrt = isSequence(d10, sqrt2, d11) ? sqrt2 : d8 + (d19 / (d17 - FastMath.sqrt(d18)));
            double computeObjectiveValue2 = computeObjectiveValue(sqrt);
            if (FastMath.abs(sqrt - d9) <= FastMath.max(relativeAccuracy * FastMath.abs(sqrt), absoluteAccuracy) || FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
                return sqrt;
            }
            int i2 = (sqrt > d8 ? 1 : (sqrt == d8 ? 0 : -1));
            if ((i2 >= 0 || d12 <= d15 * 0.95d) && ((sqrt <= d8 || d14 <= d15 * 0.95d) && i != 0)) {
                j = 4602678819172646912L;
                if (i2 >= 0) {
                    d10 = d8;
                }
                if (i2 >= 0) {
                    d6 = computeObjectiveValue;
                }
                int i3 = (sqrt > d8 ? 1 : (sqrt == d8 ? 0 : -1));
                if (i3 <= 0) {
                    d11 = d8;
                }
                if (i3 <= 0) {
                    d7 = computeObjectiveValue;
                }
                d8 = sqrt;
                computeObjectiveValue = computeObjectiveValue2;
            } else {
                j = 4602678819172646912L;
                double d20 = (d10 + d11) * 0.5d;
                double computeObjectiveValue3 = computeObjectiveValue(d20);
                if (FastMath.signum(d6) + FastMath.signum(computeObjectiveValue3) == 0.0d) {
                    d11 = d20;
                    d7 = computeObjectiveValue3;
                } else {
                    d10 = d20;
                    d6 = computeObjectiveValue3;
                }
                double d21 = (d10 + d11) * 0.5d;
                d8 = d21;
                computeObjectiveValue = computeObjectiveValue(d21);
                sqrt = Double.POSITIVE_INFINITY;
            }
            long j2 = j;
            d9 = sqrt;
        }
        return sqrt;
    }
}
