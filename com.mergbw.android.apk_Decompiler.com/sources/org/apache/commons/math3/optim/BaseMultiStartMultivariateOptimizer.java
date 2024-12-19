package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.random.RandomVectorGenerator;

public abstract class BaseMultiStartMultivariateOptimizer<PAIR> extends BaseMultivariateOptimizer<PAIR> {
    private RandomVectorGenerator generator;
    private int initialGuessIndex = -1;
    private int maxEvalIndex = -1;
    private OptimizationData[] optimData;
    private final BaseMultivariateOptimizer<PAIR> optimizer;
    private int starts;
    private int totalEvaluations;

    /* access modifiers changed from: protected */
    public abstract void clear();

    public abstract PAIR[] getOptima();

    /* access modifiers changed from: protected */
    public abstract void store(PAIR pair);

    public BaseMultiStartMultivariateOptimizer(BaseMultivariateOptimizer<PAIR> baseMultivariateOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        super(baseMultivariateOptimizer.getConvergenceChecker());
        if (i >= 1) {
            this.optimizer = baseMultivariateOptimizer;
            this.starts = i;
            this.generator = randomVectorGenerator;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) {
        this.optimData = optimizationDataArr;
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public PAIR doOptimize() {
        double[] dArr;
        int i = 0;
        while (true) {
            OptimizationData[] optimizationDataArr = this.optimData;
            if (i >= optimizationDataArr.length) {
                break;
            }
            if (optimizationDataArr[i] instanceof MaxEval) {
                optimizationDataArr[i] = null;
                this.maxEvalIndex = i;
            }
            if (optimizationDataArr[i] instanceof InitialGuess) {
                optimizationDataArr[i] = null;
                this.initialGuessIndex = i;
            }
            i++;
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        } else if (this.initialGuessIndex != -1) {
            this.totalEvaluations = 0;
            clear();
            int maxEvaluations = getMaxEvaluations();
            double[] lowerBound = getLowerBound();
            double[] upperBound = getUpperBound();
            double[] startPoint = getStartPoint();
            RuntimeException e = null;
            for (int i2 = 0; i2 < this.starts; i2++) {
                try {
                    this.optimData[this.maxEvalIndex] = new MaxEval(maxEvaluations - this.totalEvaluations);
                    if (i2 == 0) {
                        dArr = startPoint;
                    } else {
                        int i3 = 0;
                        dArr = null;
                        while (dArr == null) {
                            int i4 = i3 + 1;
                            if (i3 < getMaxEvaluations()) {
                                double[] nextVector = this.generator.nextVector();
                                int i5 = 0;
                                while (nextVector != null && i5 < nextVector.length) {
                                    if ((lowerBound != null && nextVector[i5] < lowerBound[i5]) || (upperBound != null && nextVector[i5] > upperBound[i5])) {
                                        nextVector = null;
                                    }
                                    i5++;
                                }
                                double[] dArr2 = nextVector;
                                i3 = i4;
                                dArr = dArr2;
                            } else {
                                throw new TooManyEvaluationsException(Integer.valueOf(getMaxEvaluations()));
                            }
                        }
                    }
                    this.optimData[this.initialGuessIndex] = new InitialGuess(dArr);
                    store(this.optimizer.optimize(this.optimData));
                } catch (RuntimeException e2) {
                    e = e2;
                }
                this.totalEvaluations += this.optimizer.getEvaluations();
            }
            PAIR[] optima = getOptima();
            if (optima.length != 0) {
                return optima[0];
            }
            throw e;
        } else {
            throw new MathIllegalStateException();
        }
    }
}
