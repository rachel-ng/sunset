package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointVectorValuePair;

@Deprecated
public abstract class JacobianMultivariateVectorOptimizer extends MultivariateVectorOptimizer {
    private MultivariateMatrixFunction jacobian;

    protected JacobianMultivariateVectorOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    /* access modifiers changed from: protected */
    public double[][] computeJacobian(double[] dArr) {
        return this.jacobian.value(dArr);
    }

    public PointVectorValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (ModelFunctionJacobian modelFunctionJacobian : optimizationDataArr) {
            if (modelFunctionJacobian instanceof ModelFunctionJacobian) {
                this.jacobian = modelFunctionJacobian.getModelFunctionJacobian();
                return;
            }
        }
    }
}
