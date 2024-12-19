package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

class OptimumImpl implements LeastSquaresOptimizer.Optimum {
    private final int evaluations;
    private final int iterations;
    private final LeastSquaresProblem.Evaluation value;

    OptimumImpl(LeastSquaresProblem.Evaluation evaluation, int i, int i2) {
        this.value = evaluation;
        this.evaluations = i;
        this.iterations = i2;
    }

    public int getEvaluations() {
        return this.evaluations;
    }

    public int getIterations() {
        return this.iterations;
    }

    public RealMatrix getCovariances(double d) {
        return this.value.getCovariances(d);
    }

    public RealVector getSigma(double d) {
        return this.value.getSigma(d);
    }

    public double getRMS() {
        return this.value.getRMS();
    }

    public RealMatrix getJacobian() {
        return this.value.getJacobian();
    }

    public double getCost() {
        return this.value.getCost();
    }

    public RealVector getResiduals() {
        return this.value.getResiduals();
    }

    public RealVector getPoint() {
        return this.value.getPoint();
    }
}
