package org.apache.commons.math3.optimization.univariate;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.random.RandomGenerator;

@Deprecated
public class UnivariateMultiStartOptimizer<FUNC extends UnivariateFunction> implements BaseUnivariateOptimizer<FUNC> {
    private RandomGenerator generator;
    private int maxEvaluations;
    private UnivariatePointValuePair[] optima;
    private final BaseUnivariateOptimizer<FUNC> optimizer;
    private int starts;
    private int totalEvaluations;

    public UnivariateMultiStartOptimizer(BaseUnivariateOptimizer<FUNC> baseUnivariateOptimizer, int i, RandomGenerator randomGenerator) {
        if (baseUnivariateOptimizer == null || randomGenerator == null) {
            throw new NullArgumentException();
        } else if (i >= 1) {
            this.optimizer = baseUnivariateOptimizer;
            this.starts = i;
            this.generator = randomGenerator;
        } else {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
    }

    public ConvergenceChecker<UnivariatePointValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    public int getMaxEvaluations() {
        return this.maxEvaluations;
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

    public UnivariatePointValuePair optimize(int i, FUNC func, GoalType goalType, double d, double d2) {
        return optimize(i, func, goalType, d, d2, d + ((d2 - d) * 0.5d));
    }

    public UnivariatePointValuePair optimize(int i, FUNC func, GoalType goalType, double d, double d2, double d3) {
        double d4;
        this.optima = new UnivariatePointValuePair[this.starts];
        this.totalEvaluations = 0;
        RuntimeException e = null;
        for (int i2 = 0; i2 < this.starts; i2++) {
            if (i2 == 0) {
                d4 = d3;
            } else {
                try {
                    d4 = d + (this.generator.nextDouble() * (d2 - d));
                } catch (RuntimeException e2) {
                    e = e2;
                    this.optima[i2] = null;
                }
            }
            this.optima[i2] = this.optimizer.optimize(i - this.totalEvaluations, func, goalType, d, d2, d4);
            this.totalEvaluations += this.optimizer.getEvaluations();
        }
        sortPairs(goalType);
        UnivariatePointValuePair univariatePointValuePair = this.optima[0];
        if (univariatePointValuePair != null) {
            return univariatePointValuePair;
        }
        throw e;
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
