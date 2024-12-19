package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.SimpleValueChecker;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;

public class SimplexOptimizer extends MultivariateOptimizer {
    private AbstractSimplex simplex;

    public SimplexOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public SimplexOptimizer(double d, double d2) {
        this(new SimpleValueChecker(d, d2));
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        checkParameters();
        AnonymousClass1 r0 = new MultivariateFunction() {
            public double value(double[] dArr) {
                return SimplexOptimizer.this.computeObjectiveValue(dArr);
            }
        };
        final boolean z = getGoalType() == GoalType.MINIMIZE;
        AnonymousClass2 r2 = new Comparator<PointValuePair>() {
            public int compare(PointValuePair pointValuePair, PointValuePair pointValuePair2) {
                double doubleValue = ((Double) pointValuePair.getValue()).doubleValue();
                double doubleValue2 = ((Double) pointValuePair2.getValue()).doubleValue();
                return z ? Double.compare(doubleValue, doubleValue2) : Double.compare(doubleValue2, doubleValue);
            }
        };
        this.simplex.build(getStartPoint());
        this.simplex.evaluate(r0, r2);
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        PointValuePair[] pointValuePairArr = null;
        while (true) {
            if (getIterations() > 0) {
                boolean z2 = true;
                for (int i = 0; i < this.simplex.getSize(); i++) {
                    z2 = z2 && convergenceChecker.converged(0, pointValuePairArr[i], this.simplex.getPoint(i));
                }
                if (z2) {
                    return this.simplex.getPoint(0);
                }
            }
            pointValuePairArr = this.simplex.getPoints();
            this.simplex.iterate(r0, r2);
            incrementIterationCount();
        }
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (AbstractSimplex abstractSimplex : optimizationDataArr) {
            if (abstractSimplex instanceof AbstractSimplex) {
                this.simplex = abstractSimplex;
                return;
            }
        }
    }

    private void checkParameters() {
        if (this.simplex == null) {
            throw new NullArgumentException();
        } else if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
