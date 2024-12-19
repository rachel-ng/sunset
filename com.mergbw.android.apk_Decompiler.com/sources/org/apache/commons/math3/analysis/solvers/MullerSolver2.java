package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class MullerSolver2 extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public MullerSolver2() {
        this(1.0E-6d);
    }

    public MullerSolver2(double d) {
        super(d);
    }

    public MullerSolver2(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        double d;
        double d2;
        MullerSolver2 mullerSolver2 = this;
        double min = getMin();
        double max = getMax();
        mullerSolver2.verifyInterval(min, max);
        double relativeAccuracy = getRelativeAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double computeObjectiveValue = mullerSolver2.computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue) < functionValueAccuracy) {
            return min;
        }
        double computeObjectiveValue2 = mullerSolver2.computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue2) < functionValueAccuracy) {
            return max;
        }
        if (computeObjectiveValue * computeObjectiveValue2 <= 0.0d) {
            double d3 = computeObjectiveValue2;
            double d4 = (min + max) * 0.5d;
            double d5 = min;
            double d6 = max;
            double d7 = Double.POSITIVE_INFINITY;
            double d8 = computeObjectiveValue;
            double d9 = d3;
            double computeObjectiveValue3 = mullerSolver2.computeObjectiveValue(d4);
            double d10 = d4;
            double d11 = d8;
            while (true) {
                double d12 = d10 - d6;
                double d13 = d12 / (d6 - d5);
                double d14 = d13 + 1.0d;
                double d15 = ((computeObjectiveValue3 - (d14 * d9)) + (d13 * d11)) * d13;
                double d16 = ((((d13 * 2.0d) + 1.0d) * computeObjectiveValue3) - ((d14 * d14) * d9)) + (d13 * d13 * d11);
                double d17 = d14 * computeObjectiveValue3;
                double d18 = d16 * d16;
                double d19 = d18 - ((d15 * 4.0d) * d17);
                if (d19 >= 0.0d) {
                    d = d16 + FastMath.sqrt(d19);
                    double sqrt = d16 - FastMath.sqrt(d19);
                    if (FastMath.abs(d) <= FastMath.abs(sqrt)) {
                        d = sqrt;
                    }
                } else {
                    d = FastMath.sqrt(d18 - d19);
                }
                if (d != 0.0d) {
                    d2 = d10 - (((d17 * 2.0d) * d12) / d);
                    while (true) {
                        if (d2 != d6 && d2 != d10) {
                            break;
                        }
                        d2 += absoluteAccuracy;
                    }
                } else {
                    d2 = (FastMath.random() * (max - min)) + min;
                    d7 = Double.POSITIVE_INFINITY;
                }
                double computeObjectiveValue4 = mullerSolver2.computeObjectiveValue(d2);
                if (FastMath.abs(d2 - d7) <= FastMath.max(relativeAccuracy * FastMath.abs(d2), absoluteAccuracy) || FastMath.abs(computeObjectiveValue4) <= functionValueAccuracy) {
                    return d2;
                }
                mullerSolver2 = this;
                d7 = d2;
                d11 = d9;
                d9 = computeObjectiveValue3;
                computeObjectiveValue3 = computeObjectiveValue4;
                d5 = d6;
                d6 = d10;
                d10 = d7;
            }
            return d2;
        }
        throw new NoBracketingException(min, max, computeObjectiveValue, computeObjectiveValue2);
    }
}
