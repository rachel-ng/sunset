package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.exception.TooManyIterationsException;
import org.apache.commons.math3.util.Incrementor;

public abstract class AbstractOptimizationProblem<PAIR> implements OptimizationProblem<PAIR> {
    private static final MaxEvalCallback MAX_EVAL_CALLBACK = new MaxEvalCallback();
    private static final MaxIterCallback MAX_ITER_CALLBACK = new MaxIterCallback();
    private final ConvergenceChecker<PAIR> checker;
    private final int maxEvaluations;
    private final int maxIterations;

    protected AbstractOptimizationProblem(int i, int i2, ConvergenceChecker<PAIR> convergenceChecker) {
        this.maxEvaluations = i;
        this.maxIterations = i2;
        this.checker = convergenceChecker;
    }

    public Incrementor getEvaluationCounter() {
        return new Incrementor(this.maxEvaluations, MAX_EVAL_CALLBACK);
    }

    public Incrementor getIterationCounter() {
        return new Incrementor(this.maxIterations, MAX_ITER_CALLBACK);
    }

    public ConvergenceChecker<PAIR> getConvergenceChecker() {
        return this.checker;
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
