package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

class DenseWeightedEvaluation extends AbstractEvaluation {
    private final LeastSquaresProblem.Evaluation unweighted;
    private final RealMatrix weightSqrt;

    DenseWeightedEvaluation(LeastSquaresProblem.Evaluation evaluation, RealMatrix realMatrix) {
        super(realMatrix.getColumnDimension());
        this.unweighted = evaluation;
        this.weightSqrt = realMatrix;
    }

    public RealMatrix getJacobian() {
        return this.weightSqrt.multiply(this.unweighted.getJacobian());
    }

    public RealVector getResiduals() {
        return this.weightSqrt.operate(this.unweighted.getResiduals());
    }

    public RealVector getPoint() {
        return this.unweighted.getPoint();
    }
}
