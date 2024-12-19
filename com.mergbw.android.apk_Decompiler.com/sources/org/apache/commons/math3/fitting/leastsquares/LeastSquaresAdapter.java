package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;

public class LeastSquaresAdapter implements LeastSquaresProblem {
    private final LeastSquaresProblem problem;

    public LeastSquaresAdapter(LeastSquaresProblem leastSquaresProblem) {
        this.problem = leastSquaresProblem;
    }

    public RealVector getStart() {
        return this.problem.getStart();
    }

    public int getObservationSize() {
        return this.problem.getObservationSize();
    }

    public int getParameterSize() {
        return this.problem.getParameterSize();
    }

    public LeastSquaresProblem.Evaluation evaluate(RealVector realVector) {
        return this.problem.evaluate(realVector);
    }

    public Incrementor getEvaluationCounter() {
        return this.problem.getEvaluationCounter();
    }

    public Incrementor getIterationCounter() {
        return this.problem.getIterationCounter();
    }

    public ConvergenceChecker<LeastSquaresProblem.Evaluation> getConvergenceChecker() {
        return this.problem.getConvergenceChecker();
    }
}
