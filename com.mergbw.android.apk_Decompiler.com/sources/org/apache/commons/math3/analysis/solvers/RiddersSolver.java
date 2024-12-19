package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class RiddersSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public RiddersSolver() {
        this(1.0E-6d);
    }

    public RiddersSolver(double d) {
        super(d);
    }

    public RiddersSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = computeObjectiveValue(min);
        double computeObjectiveValue2 = computeObjectiveValue(max);
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double absoluteAccuracy = getAbsoluteAccuracy();
        double functionValueAccuracy = getFunctionValueAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        double d = Double.POSITIVE_INFINITY;
        while (true) {
            double d2 = (min + max) * 0.5d;
            double computeObjectiveValue3 = computeObjectiveValue(d2);
            if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
                return d2;
            }
            double signum = ((FastMath.signum(computeObjectiveValue2) * FastMath.signum(computeObjectiveValue3)) * (d2 - min)) / FastMath.sqrt(1.0d - ((computeObjectiveValue * computeObjectiveValue2) / (computeObjectiveValue3 * computeObjectiveValue3)));
            double d3 = min;
            double d4 = d2 - signum;
            double computeObjectiveValue4 = computeObjectiveValue(d4);
            double d5 = max;
            if (FastMath.abs(d4 - d) <= FastMath.max(relativeAccuracy * FastMath.abs(d4), absoluteAccuracy) || FastMath.abs(computeObjectiveValue4) <= functionValueAccuracy) {
                return d4;
            }
            if (signum > 0.0d) {
                if (FastMath.signum(computeObjectiveValue) + FastMath.signum(computeObjectiveValue4) == 0.0d) {
                    d5 = d4;
                    d2 = d3;
                } else {
                    d5 = d2;
                    computeObjectiveValue2 = computeObjectiveValue3;
                    computeObjectiveValue = computeObjectiveValue4;
                    d2 = d4;
                    d = d4;
                    min = d2;
                    max = d5;
                }
            } else if (FastMath.signum(computeObjectiveValue2) + FastMath.signum(computeObjectiveValue4) == 0.0d) {
                d2 = d4;
                computeObjectiveValue = computeObjectiveValue4;
                d = d4;
                min = d2;
                max = d5;
            } else {
                d5 = d4;
                computeObjectiveValue = computeObjectiveValue3;
            }
            computeObjectiveValue2 = computeObjectiveValue4;
            d = d4;
            min = d2;
            max = d5;
        }
    }
}
