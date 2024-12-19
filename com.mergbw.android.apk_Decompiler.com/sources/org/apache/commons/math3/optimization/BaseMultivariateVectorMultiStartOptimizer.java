package org.apache.commons.math3.optimization;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateVectorFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomVectorGenerator;

@Deprecated
public class BaseMultivariateVectorMultiStartOptimizer<FUNC extends MultivariateVectorFunction> implements BaseMultivariateVectorOptimizer<FUNC> {
    private RandomVectorGenerator generator;
    private int maxEvaluations;
    private PointVectorValuePair[] optima;
    private final BaseMultivariateVectorOptimizer<FUNC> optimizer;
    private int starts;
    private int totalEvaluations;

    protected BaseMultivariateVectorMultiStartOptimizer(BaseMultivariateVectorOptimizer<FUNC> baseMultivariateVectorOptimizer, int i, RandomVectorGenerator randomVectorGenerator) {
        if (baseMultivariateVectorOptimizer == null || randomVectorGenerator == null) {
            throw new NullArgumentException();
        } else if (i >= 1) {
            this.optimizer = baseMultivariateVectorOptimizer;
            this.starts = i;
            this.generator = randomVectorGenerator;
        } else {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
    }

    public PointVectorValuePair[] getOptima() {
        PointVectorValuePair[] pointVectorValuePairArr = this.optima;
        if (pointVectorValuePairArr != null) {
            return (PointVectorValuePair[]) pointVectorValuePairArr.clone();
        }
        throw new MathIllegalStateException(LocalizedFormats.NO_OPTIMUM_COMPUTED_YET, new Object[0]);
    }

    public int getMaxEvaluations() {
        return this.maxEvaluations;
    }

    public int getEvaluations() {
        return this.totalEvaluations;
    }

    public ConvergenceChecker<PointVectorValuePair> getConvergenceChecker() {
        return this.optimizer.getConvergenceChecker();
    }

    public PointVectorValuePair optimize(int i, FUNC func, double[] dArr, double[] dArr2, double[] dArr3) {
        int i2 = i;
        this.maxEvaluations = i2;
        this.optima = new PointVectorValuePair[this.starts];
        this.totalEvaluations = 0;
        int i3 = 0;
        RuntimeException e = null;
        while (i3 < this.starts) {
            try {
                this.optima[i3] = this.optimizer.optimize(i2 - this.totalEvaluations, func, dArr, dArr2, i3 == 0 ? dArr3 : this.generator.nextVector());
            } catch (ConvergenceException unused) {
                this.optima[i3] = null;
            } catch (RuntimeException e2) {
                e = e2;
                this.optima[i3] = null;
            }
            this.totalEvaluations += this.optimizer.getEvaluations();
            i3++;
        }
        sortPairs(dArr, dArr2);
        PointVectorValuePair pointVectorValuePair = this.optima[0];
        if (pointVectorValuePair != null) {
            return pointVectorValuePair;
        }
        throw e;
    }

    private void sortPairs(final double[] dArr, final double[] dArr2) {
        Arrays.sort(this.optima, new Comparator<PointVectorValuePair>() {
            public int compare(PointVectorValuePair pointVectorValuePair, PointVectorValuePair pointVectorValuePair2) {
                if (pointVectorValuePair == null) {
                    return pointVectorValuePair2 == null ? 0 : 1;
                }
                if (pointVectorValuePair2 == null) {
                    return -1;
                }
                return Double.compare(weightedResidual(pointVectorValuePair), weightedResidual(pointVectorValuePair2));
            }

            private double weightedResidual(PointVectorValuePair pointVectorValuePair) {
                double[] valueRef = pointVectorValuePair.getValueRef();
                double d = 0.0d;
                for (int i = 0; i < valueRef.length; i++) {
                    double d2 = valueRef[i] - dArr[i];
                    d += dArr2[i] * d2 * d2;
                }
                return d;
            }
        });
    }
}
