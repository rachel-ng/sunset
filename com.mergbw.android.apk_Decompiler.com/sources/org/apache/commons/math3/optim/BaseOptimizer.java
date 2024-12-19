package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;

public abstract class BaseOptimizer<PAIR> {
    private final ConvergenceChecker<PAIR> checker;
    protected final Incrementor evaluations;
    protected final Incrementor iterations;

    /* access modifiers changed from: protected */
    public abstract PAIR doOptimize();

    protected BaseOptimizer(ConvergenceChecker<PAIR> convergenceChecker) {
        this(convergenceChecker, 0, Integer.MAX_VALUE);
    }

    protected BaseOptimizer(ConvergenceChecker<PAIR> convergenceChecker, int i, int i2) {
        this.checker = convergenceChecker;
        this.evaluations = new Incrementor(i, new MaxEvalCallback());
        this.iterations = new Incrementor(i2, new MaxIterCallback());
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public int getMaxIterations() {
        return this.iterations.getMaximalCount();
    }

    public int getIterations() {
        return this.iterations.getCount();
    }

    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, TooManyIterationsException {
        parseOptimizationData(optimizationDataArr);
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return doOptimize();
    }

    public PAIR optimize() throws TooManyEvaluationsException, TooManyIterationsException {
        this.evaluations.resetCount();
        this.iterations.resetCount();
        return doOptimize();
    }

    /* access modifiers changed from: protected */
    public void incrementEvaluationCount() throws TooManyEvaluationsException {
        this.evaluations.incrementCount();
    }

    /* access modifiers changed from: protected */
    public void incrementIterationCount() throws TooManyIterationsException {
        this.iterations.incrementCount();
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        for (MaxEval maxEval : optimizationDataArr) {
            if (maxEval instanceof MaxEval) {
                this.evaluations.setMaximalCount(maxEval.getMaxEval());
            } else if (maxEval instanceof MaxIter) {
                this.iterations.setMaximalCount(((MaxIter) maxEval).getMaxIter());
            }
        }
    }

    private static class MaxEvalCallback implements Incrementor.MaxCountExceededCallback {
        private MaxEvalCallback() {
        }

        public void trigger(int i) {
            throw new TooManyEvaluationsException(Integer.valueOf(i));
        }
    }

    private static class MaxIterCallback implements Incrementor.MaxCountExceededCallback {
        private MaxIterCallback() {
        }

        public void trigger(int i) {
            throw new TooManyIterationsException(Integer.valueOf(i));
        }
    }
}
