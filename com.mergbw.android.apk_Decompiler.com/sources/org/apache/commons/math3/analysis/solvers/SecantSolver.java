package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class SecantSolver extends AbstractUnivariateSolver {
    protected static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public SecantSolver() {
        super(1.0E-6d);
    }

    public SecantSolver(double d) {
        super(d);
    }

    public SecantSolver(double d, double d2) {
        super(d, d2);
    }

    /* access modifiers changed from: protected */
    public final double doSolve() throws TooManyEvaluationsException, NoBracketingException {
        double min = getMin();
        double max = getMax();
        double computeObjectiveValue = computeObjectiveValue(min);
        double computeObjectiveValue2 = computeObjectiveValue(max);
        double d = 0.0d;
        if (computeObjectiveValue == 0.0d) {
            return min;
        }
        if (computeObjectiveValue2 == 0.0d) {
            return max;
        }
        verifyBracketing(min, max);
        double functionValueAccuracy = getFunctionValueAccuracy();
        double absoluteAccuracy = getAbsoluteAccuracy();
        double relativeAccuracy = getRelativeAccuracy();
        while (true) {
            double d2 = computeObjectiveValue;
            computeObjectiveValue = computeObjectiveValue2;
            double d3 = min;
            min = max;
            max = min - (((min - d3) * computeObjectiveValue) / (computeObjectiveValue - d2));
            computeObjectiveValue2 = computeObjectiveValue(max);
            if (computeObjectiveValue2 == d || FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy || FastMath.abs(max - min) < FastMath.max(relativeAccuracy * FastMath.abs(max), absoluteAccuracy)) {
                return max;
            }
            d = 0.0d;
        }
    }
}
