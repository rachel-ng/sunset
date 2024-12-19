package org.apache.commons.math3.fitting;

import java.util.Collection;
import org.apache.commons.math3.analysis.ParametricUnivariateFunction;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresBuilder;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem;
import org.apache.commons.math3.linear.DiagonalMatrix;

public class SimpleCurveFitter extends AbstractCurveFitter {
    private final ParametricUnivariateFunction function;
    private final double[] initialGuess;
    private final int maxIter;

    private SimpleCurveFitter(ParametricUnivariateFunction parametricUnivariateFunction, double[] dArr, int i) {
        this.function = parametricUnivariateFunction;
        this.initialGuess = dArr;
        this.maxIter = i;
    }

    public static SimpleCurveFitter create(ParametricUnivariateFunction parametricUnivariateFunction, double[] dArr) {
        return new SimpleCurveFitter(parametricUnivariateFunction, dArr, Integer.MAX_VALUE);
    }

    public SimpleCurveFitter withStartPoint(double[] dArr) {
        return new SimpleCurveFitter(this.function, (double[]) dArr.clone(), this.maxIter);
    }

    public SimpleCurveFitter withMaxIterations(int i) {
        return new SimpleCurveFitter(this.function, this.initialGuess, i);
    }

    /* access modifiers changed from: protected */
    public LeastSquaresProblem getProblem(Collection<WeightedObservedPoint> collection) {
        int size = collection.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        int i = 0;
        for (WeightedObservedPoint next : collection) {
            dArr[i] = next.getY();
            dArr2[i] = next.getWeight();
            i++;
        }
        AbstractCurveFitter.TheoreticalValuesFunction theoreticalValuesFunction = new AbstractCurveFitter.TheoreticalValuesFunction(this.function, collection);
        return new LeastSquaresBuilder().maxEvaluations(Integer.MAX_VALUE).maxIterations(this.maxIter).start(this.initialGuess).target(dArr).weight(new DiagonalMatrix(dArr2)).model(theoreticalValuesFunction.getModelFunction(), theoreticalValuesFunction.getModelFunctionJacobian()).build();
    }
}
