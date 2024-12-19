package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractEvaluation implements LeastSquaresProblem.Evaluation {
    private final int observationSize;

    AbstractEvaluation(int i) {
        this.observationSize = i;
    }

    public RealMatrix getCovariances(double d) {
        RealMatrix jacobian = getJacobian();
        return new QRDecomposition(jacobian.transpose().multiply(jacobian), d).getSolver().getInverse();
    }

    public RealVector getSigma(double d) {
        RealMatrix covariances = getCovariances(d);
        int columnDimension = covariances.getColumnDimension();
        ArrayRealVector arrayRealVector = new ArrayRealVector(columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            arrayRealVector.setEntry(i, FastMath.sqrt(covariances.getEntry(i, i)));
        }
        return arrayRealVector;
    }

    public double getRMS() {
        double cost = getCost();
        return FastMath.sqrt((cost * cost) / ((double) this.observationSize));
    }

    public double getCost() {
        ArrayRealVector arrayRealVector = new ArrayRealVector(getResiduals());
        return FastMath.sqrt(arrayRealVector.dotProduct(arrayRealVector));
    }
}
