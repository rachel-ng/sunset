package org.apache.commons.math3.filter;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.MatrixDimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonSquareMatrixException;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.util.MathUtils;

public class KalmanFilter {
    private RealMatrix controlMatrix;
    private RealMatrix errorCovariance;
    private RealMatrix measurementMatrix;
    private RealMatrix measurementMatrixT;
    private final MeasurementModel measurementModel;
    private final ProcessModel processModel;
    private RealVector stateEstimation;
    private RealMatrix transitionMatrix;
    private RealMatrix transitionMatrixT = this.transitionMatrix.transpose();

    public KalmanFilter(ProcessModel processModel2, MeasurementModel measurementModel2) throws NullArgumentException, NonSquareMatrixException, DimensionMismatchException, MatrixDimensionMismatchException {
        MathUtils.checkNotNull(processModel2);
        MathUtils.checkNotNull(measurementModel2);
        this.processModel = processModel2;
        this.measurementModel = measurementModel2;
        RealMatrix stateTransitionMatrix = processModel2.getStateTransitionMatrix();
        this.transitionMatrix = stateTransitionMatrix;
        MathUtils.checkNotNull(stateTransitionMatrix);
        if (processModel2.getControlMatrix() == null) {
            this.controlMatrix = new Array2DRowRealMatrix();
        } else {
            this.controlMatrix = processModel2.getControlMatrix();
        }
        RealMatrix measurementMatrix2 = measurementModel2.getMeasurementMatrix();
        this.measurementMatrix = measurementMatrix2;
        MathUtils.checkNotNull(measurementMatrix2);
        this.measurementMatrixT = this.measurementMatrix.transpose();
        RealMatrix processNoise = processModel2.getProcessNoise();
        MathUtils.checkNotNull(processNoise);
        RealMatrix measurementNoise = measurementModel2.getMeasurementNoise();
        MathUtils.checkNotNull(measurementNoise);
        if (processModel2.getInitialStateEstimate() == null) {
            this.stateEstimation = new ArrayRealVector(this.transitionMatrix.getColumnDimension());
        } else {
            this.stateEstimation = processModel2.getInitialStateEstimate();
        }
        if (this.transitionMatrix.getColumnDimension() == this.stateEstimation.getDimension()) {
            if (processModel2.getInitialErrorCovariance() == null) {
                this.errorCovariance = processNoise.copy();
            } else {
                this.errorCovariance = processModel2.getInitialErrorCovariance();
            }
            if (this.transitionMatrix.isSquare()) {
                RealMatrix realMatrix = this.controlMatrix;
                if (realMatrix == null || realMatrix.getRowDimension() <= 0 || this.controlMatrix.getColumnDimension() <= 0 || this.controlMatrix.getRowDimension() == this.transitionMatrix.getRowDimension()) {
                    MatrixUtils.checkAdditionCompatible(this.transitionMatrix, processNoise);
                    if (this.measurementMatrix.getColumnDimension() != this.transitionMatrix.getRowDimension()) {
                        throw new MatrixDimensionMismatchException(this.measurementMatrix.getRowDimension(), this.measurementMatrix.getColumnDimension(), this.measurementMatrix.getRowDimension(), this.transitionMatrix.getRowDimension());
                    } else if (measurementNoise.getRowDimension() != this.measurementMatrix.getRowDimension()) {
                        throw new MatrixDimensionMismatchException(measurementNoise.getRowDimension(), measurementNoise.getColumnDimension(), this.measurementMatrix.getRowDimension(), measurementNoise.getColumnDimension());
                    }
                } else {
                    throw new MatrixDimensionMismatchException(this.controlMatrix.getRowDimension(), this.controlMatrix.getColumnDimension(), this.transitionMatrix.getRowDimension(), this.controlMatrix.getColumnDimension());
                }
            } else {
                throw new NonSquareMatrixException(this.transitionMatrix.getRowDimension(), this.transitionMatrix.getColumnDimension());
            }
        } else {
            throw new DimensionMismatchException(this.transitionMatrix.getColumnDimension(), this.stateEstimation.getDimension());
        }
    }

    public int getStateDimension() {
        return this.stateEstimation.getDimension();
    }

    public int getMeasurementDimension() {
        return this.measurementMatrix.getRowDimension();
    }

    public double[] getStateEstimation() {
        return this.stateEstimation.toArray();
    }

    public RealVector getStateEstimationVector() {
        return this.stateEstimation.copy();
    }

    public double[][] getErrorCovariance() {
        return this.errorCovariance.getData();
    }

    public RealMatrix getErrorCovarianceMatrix() {
        return this.errorCovariance.copy();
    }

    public void predict() {
        RealVector realVector = null;
        predict((RealVector) null);
    }

    public void predict(double[] dArr) throws DimensionMismatchException {
        predict((RealVector) new ArrayRealVector(dArr, false));
    }

    public void predict(RealVector realVector) throws DimensionMismatchException {
        if (realVector == null || realVector.getDimension() == this.controlMatrix.getColumnDimension()) {
            RealVector operate = this.transitionMatrix.operate(this.stateEstimation);
            this.stateEstimation = operate;
            if (realVector != null) {
                this.stateEstimation = operate.add(this.controlMatrix.operate(realVector));
            }
            this.errorCovariance = this.transitionMatrix.multiply(this.errorCovariance).multiply(this.transitionMatrixT).add(this.processModel.getProcessNoise());
            return;
        }
        throw new DimensionMismatchException(realVector.getDimension(), this.controlMatrix.getColumnDimension());
    }

    public void correct(double[] dArr) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        correct((RealVector) new ArrayRealVector(dArr, false));
    }

    public void correct(RealVector realVector) throws NullArgumentException, DimensionMismatchException, SingularMatrixException {
        MathUtils.checkNotNull(realVector);
        if (realVector.getDimension() == this.measurementMatrix.getRowDimension()) {
            RealMatrix add = this.measurementMatrix.multiply(this.errorCovariance).multiply(this.measurementMatrixT).add(this.measurementModel.getMeasurementNoise());
            RealVector subtract = realVector.subtract(this.measurementMatrix.operate(this.stateEstimation));
            RealMatrix transpose = new CholeskyDecomposition(add).getSolver().solve(this.measurementMatrix.multiply(this.errorCovariance.transpose())).transpose();
            this.stateEstimation = this.stateEstimation.add(transpose.operate(subtract));
            this.errorCovariance = MatrixUtils.createRealIdentityMatrix(transpose.getRowDimension()).subtract(transpose.multiply(this.measurementMatrix)).multiply(this.errorCovariance);
            return;
        }
        throw new DimensionMismatchException(realVector.getDimension(), this.measurementMatrix.getRowDimension());
    }
}
