package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Precision;

public class EvaluationRmsChecker implements ConvergenceChecker<LeastSquaresProblem.Evaluation> {
    private final double absTol;
    private final double relTol;

    public EvaluationRmsChecker(double d) {
        this(d, d);
    }

    public EvaluationRmsChecker(double d, double d2) {
        this.relTol = d;
        this.absTol = d2;
    }

    public boolean converged(int i, LeastSquaresProblem.Evaluation evaluation, LeastSquaresProblem.Evaluation evaluation2) {
        double rms = evaluation.getRMS();
        double rms2 = evaluation2.getRMS();
        if (!Precision.equals(rms, rms2, this.absTol)) {
            return Precision.equalsWithRelativeTolerance(rms, rms2, this.relTol);
        }
    }
}
