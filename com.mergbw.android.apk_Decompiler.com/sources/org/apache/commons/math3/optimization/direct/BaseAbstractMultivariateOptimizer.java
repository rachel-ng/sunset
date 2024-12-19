package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optimization.BaseMultivariateOptimizer;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.optimization.SimpleBounds;
import org.apache.commons.math3.optimization.SimpleValueChecker;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
public abstract class BaseAbstractMultivariateOptimizer<FUNC extends MultivariateFunction> implements BaseMultivariateOptimizer<FUNC> {
    private ConvergenceChecker<PointValuePair> checker;
    protected final Incrementor evaluations;
    private MultivariateFunction function;
    private GoalType goal;
    private double[] lowerBound;
    private double[] start;
    private double[] upperBound;

    /* access modifiers changed from: protected */
    public abstract PointValuePair doOptimize();

    @Deprecated
    protected BaseAbstractMultivariateOptimizer() {
        this(new SimpleValueChecker());
    }

    protected BaseAbstractMultivariateOptimizer(ConvergenceChecker<PointValuePair> convergenceChecker) {
        this.evaluations = new Incrementor();
        this.checker = convergenceChecker;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public ConvergenceChecker<PointValuePair> getConvergenceChecker() {
        return this.checker;
    }

    /* access modifiers changed from: protected */
    public double computeObjectiveValue(double[] dArr) {
        try {
            this.evaluations.incrementCount();
            return this.function.value(dArr);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }

    @Deprecated
    public PointValuePair optimize(int i, FUNC func, GoalType goalType, double[] dArr) {
        return optimizeInternal(i, func, goalType, new InitialGuess(dArr));
    }

    public PointValuePair optimize(int i, FUNC func, GoalType goalType, OptimizationData... optimizationDataArr) {
        return optimizeInternal(i, func, goalType, optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PointValuePair optimizeInternal(int i, FUNC func, GoalType goalType, double[] dArr) {
        return optimizeInternal(i, func, goalType, new InitialGuess(dArr));
    }

    /* access modifiers changed from: protected */
    public PointValuePair optimizeInternal(int i, FUNC func, GoalType goalType, OptimizationData... optimizationDataArr) throws TooManyEvaluationsException {
        this.evaluations.setMaximalCount(i);
        this.evaluations.resetCount();
        this.function = func;
        this.goal = goalType;
        parseOptimizationData(optimizationDataArr);
        checkParameters();
        return doOptimize();
    }

    private void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (InitialGuess initialGuess : optimizationDataArr) {
            if (initialGuess instanceof InitialGuess) {
                this.start = initialGuess.getInitialGuess();
            } else if (initialGuess instanceof SimpleBounds) {
                SimpleBounds simpleBounds = (SimpleBounds) initialGuess;
                this.lowerBound = simpleBounds.getLower();
                this.upperBound = simpleBounds.getUpper();
            }
        }
    }

    public GoalType getGoalType() {
        return this.goal;
    }

    public double[] getStartPoint() {
        double[] dArr = this.start;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getLowerBound() {
        double[] dArr = this.lowerBound;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getUpperBound() {
        double[] dArr = this.upperBound;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    private void checkParameters() {
        double[] dArr = this.start;
        if (dArr != null) {
            int length = dArr.length;
            double[] dArr2 = this.lowerBound;
            if (dArr2 != null) {
                if (dArr2.length == length) {
                    int i = 0;
                    while (i < length) {
                        double d = this.start[i];
                        double d2 = this.lowerBound[i];
                        if (d >= d2) {
                            i++;
                        } else {
                            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d2), true);
                        }
                    }
                } else {
                    throw new DimensionMismatchException(this.lowerBound.length, length);
                }
            }
            double[] dArr3 = this.upperBound;
            if (dArr3 != null) {
                if (dArr3.length == length) {
                    int i2 = 0;
                    while (i2 < length) {
                        double d3 = this.start[i2];
                        double d4 = this.upperBound[i2];
                        if (d3 <= d4) {
                            i2++;
                        } else {
                            throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(d4), true);
                        }
                    }
                } else {
                    throw new DimensionMismatchException(this.upperBound.length, length);
                }
            }
            if (this.lowerBound == null) {
                this.lowerBound = new double[length];
                for (int i3 = 0; i3 < length; i3++) {
                    this.lowerBound[i3] = Double.NEGATIVE_INFINITY;
                }
            }
            if (this.upperBound == null) {
                this.upperBound = new double[length];
                for (int i4 = 0; i4 < length; i4++) {
                    this.upperBound[i4] = Double.POSITIVE_INFINITY;
                }
            }
        }
    }
}
