package org.apache.commons.math3.optim.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.MaxEval;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

public class MultiStartUnivariateOptimizer extends UnivariateOptimizer {
    private RandomGenerator generator;
    private int maxEvalIndex = -1;
    private OptimizationData[] optimData;
    private UnivariatePointValuePair[] optima;
    private final UnivariateOptimizer optimizer;
    private int searchIntervalIndex = -1;
    private int starts;
    private int totalEvaluations;

    public MultiStartUnivariateOptimizer(UnivariateOptimizer univariateOptimizer, int i, RandomGenerator randomGenerator) {
        super(univariateOptimizer.getConvergenceChecker());
        if (i >= 1) {
            this.optimizer = univariateOptimizer;
            this.starts = i;
            this.generator = randomGenerator;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public UnivariatePointValuePair[] getOptima() {
        UnivariatePointValuePair[] univariatePointValuePairArr = this.optima;
        if (univariatePointValuePairArr != null) {
            return (UnivariatePointValuePair[]) univariatePointValuePairArr.clone();
        }
        throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
    }

    public UnivariatePointValuePair optimize(OptimizationData... optimizationDataArr) {
        this.optimData = optimizationDataArr;
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public UnivariatePointValuePair doOptimize() {
        double d;
        int i = 0;
        while (true) {
            OptimizationData[] optimizationDataArr = this.optimData;
            if (i >= optimizationDataArr.length) {
                break;
            }
            OptimizationData optimizationData = optimizationDataArr[i];
            if (optimizationData instanceof MaxEval) {
                optimizationDataArr[i] = null;
                this.maxEvalIndex = i;
            } else if (optimizationData instanceof SearchInterval) {
                optimizationDataArr[i] = null;
                this.searchIntervalIndex = i;
            }
            i++;
        }
        if (this.maxEvalIndex == -1) {
            throw new MathIllegalStateException();
        } else if (this.searchIntervalIndex != -1) {
            this.optima = new UnivariatePointValuePair[this.starts];
            this.totalEvaluations = 0;
            int maxEvaluations = getMaxEvaluations();
            double min = getMin();
            double max = getMax();
            double startValue = getStartValue();
            RuntimeException e = null;
            for (int i2 = 0; i2 < this.starts; i2++) {
                try {
                    this.optimData[this.maxEvalIndex] = new MaxEval(maxEvaluations - this.totalEvaluations);
                    if (i2 == 0) {
                        d = startValue;
                    } else {
                        d = (this.generator.nextDouble() * (max - min)) + min;
                    }
                    this.optimData[this.searchIntervalIndex] = new SearchInterval(min, max, d);
                    this.optima[i2] = this.optimizer.optimize(this.optimData);
                } catch (RuntimeException e2) {
                    e = e2;
                    this.optima[i2] = null;
                }
                this.totalEvaluations += this.optimizer.getEvaluations();
            }
            sortPairs(getGoalType());
            UnivariatePointValuePair univariatePointValuePair = this.optima[0];
            if (univariatePointValuePair != null) {
                return univariatePointValuePair;
            }
            throw e;
        } else {
            throw new MathIllegalStateException();
        }
    }

    private void sortPairs(final GoalType goalType) {
        Arrays.sort(this.optima, new Comparator<UnivariatePointValuePair>() {
            public int compare(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2) {
                if (univariatePointValuePair == null) {
                    return univariatePointValuePair2 == null ? 0 : 1;
                }
                if (univariatePointValuePair2 == null) {
                    return -1;
                }
                double value = univariatePointValuePair.getValue();
                double value2 = univariatePointValuePair2.getValue();
                return goalType == GoalType.MINIMIZE ? Double.compare(value, value2) : Double.compare(value2, value);
            }
        });
    }
}
