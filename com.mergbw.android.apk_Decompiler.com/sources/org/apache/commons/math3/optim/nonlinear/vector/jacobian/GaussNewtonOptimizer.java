package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.BlockRealMatrix;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;

@Deprecated
public class GaussNewtonOptimizer extends AbstractLeastSquaresOptimizer {
    private final boolean useLU;

    public GaussNewtonOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this(true, convergenceChecker);
    }

    public GaussNewtonOptimizer(boolean z, ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
        this.useLU = z;
    }

    public PointVectorValuePair doOptimize() {
        checkParameters();
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        if (convergenceChecker != null) {
            int length = getTarget().length;
            RealMatrix weight = getWeight();
            double[] dArr = new double[length];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                dArr[i2] = weight.getEntry(i2, i2);
            }
            double[] startPoint = getStartPoint();
            int length2 = startPoint.length;
            PointVectorValuePair pointVectorValuePair = null;
            boolean z = false;
            while (!z) {
                incrementIterationCount();
                double[] computeObjectiveValue = computeObjectiveValue(startPoint);
                double[] computeResiduals = computeResiduals(computeObjectiveValue);
                RealMatrix computeWeightedJacobian = computeWeightedJacobian(startPoint);
                PointVectorValuePair pointVectorValuePair2 = new PointVectorValuePair(startPoint, computeObjectiveValue);
                double[] dArr2 = new double[length2];
                int[] iArr = new int[2];
                iArr[1] = length2;
                iArr[i] = length2;
                double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                int i3 = i;
                while (i3 < length) {
                    double[] row = computeWeightedJacobian.getRow(i3);
                    double d = dArr[i3];
                    double d2 = computeResiduals[i3] * d;
                    while (i < length2) {
                        dArr2[i] = dArr2[i] + (row[i] * d2);
                        i++;
                    }
                    int i4 = 0;
                    while (i4 < length2) {
                        double[] dArr4 = dArr3[i4];
                        double d3 = row[i4] * d;
                        int i5 = length;
                        for (int i6 = 0; i6 < length2; i6++) {
                            dArr4[i6] = dArr4[i6] + (row[i6] * d3);
                        }
                        i4++;
                        length = i5;
                    }
                    int i7 = length;
                    i3++;
                    i = 0;
                }
                int i8 = length;
                if (pointVectorValuePair == null || !(z = convergenceChecker.converged(getIterations(), pointVectorValuePair, pointVectorValuePair2))) {
                    try {
                        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(dArr3);
                        double[] array = (this.useLU ? new LUDecomposition(blockRealMatrix).getSolver() : new QRDecomposition(blockRealMatrix).getSolver()).solve((RealVector) new ArrayRealVector(dArr2, false)).toArray();
                        for (int i9 = 0; i9 < length2; i9++) {
                            startPoint[i9] = startPoint[i9] + array[i9];
                        }
                        pointVectorValuePair = pointVectorValuePair2;
                        length = i8;
                        i = 0;
                    } catch (SingularMatrixException unused) {
                        throw new ConvergenceException(LocalizedFormats.UNABLE_TO_SOLVE_SINGULAR_PROBLEM, new Object[0]);
                    }
                } else {
                    setCost(computeCost(computeResiduals));
                    return pointVectorValuePair2;
                }
            }
            throw new MathInternalError();
        }
        throw new NullArgumentException();
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
