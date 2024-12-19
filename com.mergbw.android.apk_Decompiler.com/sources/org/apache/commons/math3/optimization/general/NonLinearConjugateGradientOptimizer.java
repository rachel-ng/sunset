package org.apache.commons.math3.optimization.general;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class NonLinearConjugateGradientOptimizer extends AbstractScalarDifferentiableOptimizer {
    private double initialStep;
    /* access modifiers changed from: private */
    public double[] point;
    private final Preconditioner preconditioner;
    private final UnivariateSolver solver;
    private final ConjugateGradientFormula updateFormula;

    @Deprecated
    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula) {
        this(conjugateGradientFormula, new SimpleValueChecker());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(conjugateGradientFormula, convergenceChecker, new BrentSolver(), new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(conjugateGradientFormula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(ConjugateGradientFormula conjugateGradientFormula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner2) {
        super(convergenceChecker);
        this.updateFormula = conjugateGradientFormula;
        this.solver = univariateSolver;
        this.preconditioner = preconditioner2;
        this.initialStep = 1.0d;
    }

    public void setInitialStep(double d) {
        if (d <= 0.0d) {
            this.initialStep = 1.0d;
        } else {
            this.initialStep = d;
        }
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double[] dArr;
        double d;
        ConvergenceChecker<PointValuePair> convergenceChecker = getConvergenceChecker();
        this.point = getStartPoint();
        GoalType goalType = getGoalType();
        double[] dArr2 = this.point;
        int length = dArr2.length;
        double[] computeObjectiveGradient = computeObjectiveGradient(dArr2);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                computeObjectiveGradient[i] = -computeObjectiveGradient[i];
            }
        }
        double[] precondition = this.preconditioner.precondition(this.point, computeObjectiveGradient);
        double[] dArr3 = (double[]) precondition.clone();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            d2 += computeObjectiveGradient[i2] * dArr3[i2];
        }
        PointValuePair pointValuePair = null;
        int maxEvaluations = getMaxEvaluations();
        double[] dArr4 = precondition;
        double[] dArr5 = dArr3;
        double d3 = d2;
        int i3 = 0;
        while (true) {
            int i4 = i3 + 1;
            PointValuePair pointValuePair2 = new PointValuePair(this.point, computeObjectiveValue(this.point));
            if (pointValuePair != null && convergenceChecker.converged(i4, pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            LineSearchFunction lineSearchFunction = new LineSearchFunction(dArr5);
            LineSearchFunction lineSearchFunction2 = lineSearchFunction;
            double findUpperBound = findUpperBound(lineSearchFunction, 0.0d, this.initialStep);
            pointValuePair = pointValuePair2;
            int i5 = i4;
            double[] dArr6 = dArr5;
            double solve = this.solver.solve(maxEvaluations, lineSearchFunction2, 0.0d, findUpperBound, 1.0E-15d);
            maxEvaluations -= this.solver.getEvaluations();
            int i6 = 0;
            while (true) {
                dArr = this.point;
                if (i6 >= dArr.length) {
                    break;
                }
                dArr[i6] = dArr[i6] + (dArr6[i6] * solve);
                i6++;
            }
            double[] computeObjectiveGradient2 = computeObjectiveGradient(dArr);
            if (goalType == GoalType.MINIMIZE) {
                for (int i7 = 0; i7 < length; i7++) {
                    computeObjectiveGradient2[i7] = -computeObjectiveGradient2[i7];
                }
            }
            double[] precondition2 = this.preconditioner.precondition(this.point, computeObjectiveGradient2);
            double d4 = 0.0d;
            for (int i8 = 0; i8 < length; i8++) {
                d4 += computeObjectiveGradient2[i8] * precondition2[i8];
            }
            if (this.updateFormula == ConjugateGradientFormula.FLETCHER_REEVES) {
                d = d4 / d3;
            } else {
                double d5 = 0.0d;
                for (int i9 = 0; i9 < computeObjectiveGradient2.length; i9++) {
                    d5 += computeObjectiveGradient2[i9] * dArr4[i9];
                }
                d = (d4 - d5) / d3;
            }
            if (i5 % length == 0 || d < 0.0d) {
                dArr5 = (double[]) precondition2.clone();
            } else {
                for (int i10 = 0; i10 < length; i10++) {
                    dArr6[i10] = precondition2[i10] + (dArr6[i10] * d);
                }
                dArr5 = dArr6;
            }
            dArr4 = precondition2;
            i3 = i5;
            d3 = d4;
        }
    }

    private double findUpperBound(UnivariateFunction univariateFunction, double d, double d2) {
        double value = univariateFunction.value(d);
        while (d2 < Double.MAX_VALUE) {
            double d3 = d + d2;
            double value2 = univariateFunction.value(d3);
            if (value * value2 <= 0.0d) {
                return d3;
            }
            d2 *= FastMath.max(2.0d, value / value2);
        }
        throw new MathIllegalStateException(LocalizedFormats.UNABLE_TO_BRACKET_OPTIMUM_IN_LINE_SEARCH, new Object[0]);
    }

    public static class IdentityPreconditioner implements Preconditioner {
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }

    private class LineSearchFunction implements UnivariateFunction {
        private final double[] searchDirection;

        public LineSearchFunction(double[] dArr) {
            this.searchDirection = dArr;
        }

        public double value(double d) {
            double[] dArr = (double[]) NonLinearConjugateGradientOptimizer.this.point.clone();
            for (int i = 0; i < dArr.length; i++) {
                dArr[i] = dArr[i] + (this.searchDirection[i] * d);
            }
            double[] computeObjectiveGradient = NonLinearConjugateGradientOptimizer.this.computeObjectiveGradient(dArr);
            double d2 = 0.0d;
            for (int i2 = 0; i2 < computeObjectiveGradient.length; i2++) {
                d2 += computeObjectiveGradient[i2] * this.searchDirection[i2];
            }
            return d2;
        }
    }
}
