package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PascalDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 6751309484392813623L;
    private final double log1mProbabilityOfSuccess;
    private final double logProbabilityOfSuccess;
    private final int numberOfSuccesses;
    private final double probabilityOfSuccess;

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public PascalDistribution(int i, double d) throws NotStrictlyPositiveException, OutOfRangeException {
        this(new Well19937c(), i, d);
    }

    public PascalDistribution(RandomGenerator randomGenerator, int i, double d) throws NotStrictlyPositiveException, OutOfRangeException {
        super(randomGenerator);
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SUCCESSES, Integer.valueOf(i));
        } else if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else {
            this.numberOfSuccesses = i;
            this.probabilityOfSuccess = d;
            this.logProbabilityOfSuccess = FastMath.log(d);
            this.log1mProbabilityOfSuccess = FastMath.log1p(-d);
        }
    }

    public int getNumberOfSuccesses() {
        return this.numberOfSuccesses;
    }

    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    public double probability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        int i2 = this.numberOfSuccesses;
        return CombinatoricsUtils.binomialCoefficientDouble((i + i2) - 1, i2 - 1) * FastMath.pow(this.probabilityOfSuccess, this.numberOfSuccesses) * FastMath.pow(1.0d - this.probabilityOfSuccess, i);
    }

    public double logProbability(int i) {
        if (i < 0) {
            return Double.NEGATIVE_INFINITY;
        }
        int i2 = this.numberOfSuccesses;
        return CombinatoricsUtils.binomialCoefficientLog((i + i2) - 1, i2 - 1) + (this.logProbabilityOfSuccess * ((double) this.numberOfSuccesses)) + (this.log1mProbabilityOfSuccess * ((double) i));
    }

    public double cumulativeProbability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        return Beta.regularizedBeta(this.probabilityOfSuccess, (double) this.numberOfSuccesses, 1.0d + ((double) i));
    }

    public double getNumericalMean() {
        double probabilityOfSuccess2 = getProbabilityOfSuccess();
        return (((double) getNumberOfSuccesses()) * (1.0d - probabilityOfSuccess2)) / probabilityOfSuccess2;
    }

    public double getNumericalVariance() {
        double probabilityOfSuccess2 = getProbabilityOfSuccess();
        return (((double) getNumberOfSuccesses()) * (1.0d - probabilityOfSuccess2)) / (probabilityOfSuccess2 * probabilityOfSuccess2);
    }
}
