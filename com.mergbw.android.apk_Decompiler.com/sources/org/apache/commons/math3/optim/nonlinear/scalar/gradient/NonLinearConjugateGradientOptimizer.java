package org.apache.commons.math3.optim.nonlinear.scalar.gradient;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.GradientMultivariateOptimizer;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;

public class NonLinearConjugateGradientOptimizer extends GradientMultivariateOptimizer {
    private final LineSearch line;
    private final Preconditioner preconditioner;
    private final Formula updateFormula;

    public enum Formula {
        FLETCHER_REEVES,
        POLAK_RIBIERE
    }

    @Deprecated
    public static class BracketingStep implements OptimizationData {
        private final double initialStep;

        public BracketingStep(double d) {
            this.initialStep = d;
        }

        public double getBracketingStep() {
            return this.initialStep;
        }
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(formula, convergenceChecker, 1.0E-8d, 1.0E-8d, 1.0E-8d, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver) {
        this(formula, convergenceChecker, univariateSolver, new IdentityPreconditioner());
    }

    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, double d, double d2, double d3) {
        this(formula, convergenceChecker, d, d2, d3, new IdentityPreconditioner());
    }

    @Deprecated
    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, UnivariateSolver univariateSolver, Preconditioner preconditioner2) {
        this(formula, convergenceChecker, univariateSolver.getRelativeAccuracy(), univariateSolver.getAbsoluteAccuracy(), univariateSolver.getAbsoluteAccuracy(), preconditioner2);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NonLinearConjugateGradientOptimizer(Formula formula, ConvergenceChecker<PointValuePair> convergenceChecker, double d, double d2, double d3, Preconditioner preconditioner2) {
        super(convergenceChecker);
        ConvergenceChecker<PointValuePair> convergenceChecker2 = convergenceChecker;
        this.updateFormula = formula;
        this.preconditioner = preconditioner2;
        this.line = new LineSearch(this, d, d2, d3);
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double d;
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        double[] startPoint = getStartPoint();
        GoalType goalType = getGoalType();
        int length = startPoint.length;
        double[] computeObjectiveGradient = computeObjectiveGradient(startPoint);
        if (goalType == GoalType.MINIMIZE) {
            for (int i = 0; i < length; i++) {
                computeObjectiveGradient[i] = -computeObjectiveGradient[i];
            }
        }
        double[] precondition = this.preconditioner.precondition(startPoint, computeObjectiveGradient);
        double[] dArr = (double[]) precondition.clone();
        double d2 = 0.0d;
        for (int i2 = 0; i2 < length; i2++) {
            d2 += computeObjectiveGradient[i2] * dArr[i2];
        }
        PointValuePair pointValuePair = null;
        while (true) {
            incrementIterationCount();
            PointValuePair pointValuePair2 = new PointValuePair(startPoint, computeObjectiveValue(startPoint));
            if (pointValuePair != null && convergenceChecker.converged(getIterations(), pointValuePair, pointValuePair2)) {
                return pointValuePair2;
            }
            double point = this.line.search(startPoint, dArr).getPoint();
            for (int i3 = 0; i3 < startPoint.length; i3++) {
                startPoint[i3] = startPoint[i3] + (dArr[i3] * point);
            }
            double[] computeObjectiveGradient2 = computeObjectiveGradient(startPoint);
            if (goalType == GoalType.MINIMIZE) {
                for (int i4 = 0; i4 < length; i4++) {
                    computeObjectiveGradient2[i4] = -computeObjectiveGradient2[i4];
                }
            }
            double[] precondition2 = this.preconditioner.precondition(startPoint, computeObjectiveGradient2);
            double d3 = 0.0d;
            for (int i5 = 0; i5 < length; i5++) {
                d3 += computeObjectiveGradient2[i5] * precondition2[i5];
            }
            int i6 = AnonymousClass1.$SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula[this.updateFormula.ordinal()];
            if (i6 == 1) {
                d = d3 / d2;
            } else if (i6 == 2) {
                double d4 = 0.0d;
                for (int i7 = 0; i7 < computeObjectiveGradient2.length; i7++) {
                    d4 += computeObjectiveGradient2[i7] * precondition[i7];
                }
                d = (d3 - d4) / d2;
            } else {
                throw new MathInternalError();
            }
            if (getIterations() % length != 0) {
                if (d >= 0.0d) {
                    for (int i8 = 0; i8 < length; i8++) {
                        dArr[i8] = precondition2[i8] + (dArr[i8] * d);
                    }
                    precondition = precondition2;
                    pointValuePair = pointValuePair2;
                    d2 = d3;
                }
            }
            dArr = (double[]) precondition2.clone();
            precondition = precondition2;
            pointValuePair = pointValuePair2;
            d2 = d3;
        }
    }

    /* renamed from: org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer$Formula[] r0 = org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula = r0
                org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer$Formula r1 = org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula.FLETCHER_REEVES     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$optim$nonlinear$scalar$gradient$NonLinearConjugateGradientOptimizer$Formula     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer$Formula r1 = org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.Formula.POLAK_RIBIERE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.gradient.NonLinearConjugateGradientOptimizer.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        checkParameters();
    }

    public static class IdentityPreconditioner implements Preconditioner {
        public double[] precondition(double[] dArr, double[] dArr2) {
            return (double[]) dArr2.clone();
        }
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
