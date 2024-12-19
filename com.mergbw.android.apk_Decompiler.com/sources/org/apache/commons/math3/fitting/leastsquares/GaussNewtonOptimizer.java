package org.apache.commons.math3.fitting.leastsquares;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.CholeskyDecomposition;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.NonPositiveDefiniteMatrixException;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.linear.SingularValueDecomposition;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.Incrementor;
import org.apache.commons.math3.util.Pair;

public class GaussNewtonOptimizer implements LeastSquaresOptimizer {
    private static final double SINGULARITY_THRESHOLD = 1.0E-11d;
    private final Decomposition decomposition;

    public enum Decomposition {
        LU {
            /* access modifiers changed from: protected */
            public RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                try {
                    Pair access$100 = GaussNewtonOptimizer.computeNormalMatrix(realMatrix, realVector);
                    return new LUDecomposition((RealMatrix) access$100.getFirst(), GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve((RealVector) access$100.getSecond());
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        QR {
            /* access modifiers changed from: protected */
            public RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                try {
                    return new QRDecomposition(realMatrix, GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve(realVector);
                } catch (SingularMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        CHOLESKY {
            /* access modifiers changed from: protected */
            public RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                try {
                    Pair access$100 = GaussNewtonOptimizer.computeNormalMatrix(realMatrix, realVector);
                    return new CholeskyDecomposition((RealMatrix) access$100.getFirst(), GaussNewtonOptimizer.SINGULARITY_THRESHOLD, GaussNewtonOptimizer.SINGULARITY_THRESHOLD).getSolver().solve((RealVector) access$100.getSecond());
                } catch (NonPositiveDefiniteMatrixException e) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, e);
                }
            }
        },
        SVD {
            /* access modifiers changed from: protected */
            public RealVector solve(RealMatrix realMatrix, RealVector realVector) {
                return new SingularValueDecomposition(realMatrix).getSolver().solve(realVector);
            }
        };

        /* access modifiers changed from: protected */
        public abstract RealVector solve(RealMatrix realMatrix, RealVector realVector);
    }

    public GaussNewtonOptimizer() {
        this(Decomposition.QR);
    }

    public GaussNewtonOptimizer(Decomposition decomposition2) {
        this.decomposition = decomposition2;
    }

    public Decomposition getDecomposition() {
        return this.decomposition;
    }

    public GaussNewtonOptimizer withDecomposition(Decomposition decomposition2) {
        return new GaussNewtonOptimizer(decomposition2);
    }

    public LeastSquaresOptimizer.Optimum optimize(LeastSquaresProblem leastSquaresProblem) {
        Incrementor evaluationCounter = leastSquaresProblem.getEvaluationCounter();
        Incrementor iterationCounter = leastSquaresProblem.getIterationCounter();
        ConvergenceChecker convergenceChecker = leastSquaresProblem.getConvergenceChecker();
        if (convergenceChecker != null) {
            RealVector start = leastSquaresProblem.getStart();
            LeastSquaresProblem.Evaluation evaluation = null;
            while (true) {
                iterationCounter.incrementCount();
                evaluationCounter.incrementCount();
                LeastSquaresProblem.Evaluation evaluate = leastSquaresProblem.evaluate(start);
                RealVector residuals = evaluate.getResiduals();
                RealMatrix jacobian = evaluate.getJacobian();
                RealVector point = evaluate.getPoint();
                if (evaluation != null && convergenceChecker.converged(iterationCounter.getCount(), evaluation, evaluate)) {
                    return new OptimumImpl(evaluate, evaluationCounter.getCount(), iterationCounter.getCount());
                }
                evaluation = evaluate;
                start = point.add(this.decomposition.solve(jacobian, residuals));
            }
        } else {
            throw new NullArgumentException();
        }
    }

    public String toString() {
        return "GaussNewtonOptimizer{decomposition=" + this.decomposition + '}';
    }

    /* access modifiers changed from: private */
    public static Pair<RealMatrix, RealVector> computeNormalMatrix(RealMatrix realMatrix, RealVector realVector) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        RealMatrix createRealMatrix = MatrixUtils.createRealMatrix(columnDimension, columnDimension);
        ArrayRealVector arrayRealVector = new ArrayRealVector(columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                arrayRealVector.setEntry(i2, arrayRealVector.getEntry(i2) + (realVector.getEntry(i) * realMatrix.getEntry(i, i2)));
            }
            for (int i3 = 0; i3 < columnDimension; i3++) {
                for (int i4 = i3; i4 < columnDimension; i4++) {
                    createRealMatrix.setEntry(i3, i4, createRealMatrix.getEntry(i3, i4) + (realMatrix.getEntry(i, i3) * realMatrix.getEntry(i, i4)));
                }
            }
        }
        for (int i5 = 0; i5 < columnDimension; i5++) {
            for (int i6 = 0; i6 < i5; i6++) {
                createRealMatrix.setEntry(i5, i6, createRealMatrix.getEntry(i6, i5));
            }
        }
        return new Pair<>(createRealMatrix, arrayRealVector);
    }
}
