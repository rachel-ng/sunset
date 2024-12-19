package org.apache.commons.math3.fitting;

import java.util.Collection;
import org.apache.commons.math3.analysis.MultivariateMatrixFunction;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;

public abstract class AbstractCurveFitter {
    /* access modifiers changed from: protected */
    public abstract LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> collection);

    public double[] fit(Collection<WeightedObservedPoint> collection) {
        return getOptimizer().optimize(getProblem(collection)).getPoint().toArray();
    }

    /* access modifiers changed from: protected */
    public LeastSquaresOptimizer getOptimizer() {
        return new LevenbergMarquardtOptimizer();
    }

    protected static class TheoreticalValuesFunction {
        /* access modifiers changed from: private */
        public final ParametricUnivariateFunction f;
        /* access modifiers changed from: private */
        public final double[] points;

        public TheoreticalValuesFunction(ParametricUnivariateFunction parametricUnivariateFunction, Collection<WeightedObservedPoint> collection) {
            this.f = parametricUnivariateFunction;
            this.points = new double[collection.size()];
            int i = 0;
            for (WeightedObservedPoint x : collection) {
                this.points[i] = x.getX();
                i++;
            }
        }

        public MultivariateVectorFunction getModelFunction() {
            return new MultivariateVectorFunction() {
                public double[] value(double[] dArr) {
                    int length = TheoreticalValuesFunction.this.points.length;
                    double[] dArr2 = new double[length];
                    for (int i = 0; i < length; i++) {
                        dArr2[i] = TheoreticalValuesFunction.this.f.value(TheoreticalValuesFunction.this.points[i], dArr);
                    }
                    return dArr2;
                }
            };
        }

        public MultivariateMatrixFunction getModelFunctionJacobian() {
            return new MultivariateMatrixFunction() {
                public double[][] value(double[] dArr) {
                    int length = TheoreticalValuesFunction.this.points.length;
                    double[][] dArr2 = new double[length][];
                    for (int i = 0; i < length; i++) {
                        dArr2[i] = TheoreticalValuesFunction.this.f.gradient(TheoreticalValuesFunction.this.points[i], dArr);
                    }
                    return dArr2;
                }
            };
        }
    }
}
