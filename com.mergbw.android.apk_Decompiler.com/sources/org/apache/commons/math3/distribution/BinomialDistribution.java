package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.util.FastMath;

public class BinomialDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 6751309484392813623L;
    private final int numberOfTrials;
    private final double probabilityOfSuccess;

    public boolean isSupportConnected() {
        return true;
    }

    public BinomialDistribution(int i, double d) {
        this(new Well19937c(), i, d);
    }

    public BinomialDistribution(RandomGenerator randomGenerator, int i, double d) {
        super(randomGenerator);
        if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_TRIALS, Integer.valueOf(i));
        } else if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else {
            this.probabilityOfSuccess = d;
            this.numberOfTrials = i;
        }
    }

    public int getNumberOfTrials() {
        return this.numberOfTrials;
    }

    public double getProbabilityOfSuccess() {
        return this.probabilityOfSuccess;
    }

    public double probability(int i) {
        double logProbability = logProbability(i);
        if (logProbability == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(logProbability);
    }

    public double logProbability(int i) {
        int i2 = this.numberOfTrials;
        if (i2 == 0) {
            return i == 0 ? 0.0d : Double.NEGATIVE_INFINITY;
        }
        if (i < 0 || i > i2) {
            return Double.NEGATIVE_INFINITY;
        }
        double d = this.probabilityOfSuccess;
        return SaddlePointExpansion.logBinomialProbability(i, i2, d, 1.0d - d);
    }

    public double cumulativeProbability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        int i2 = this.numberOfTrials;
        if (i >= i2) {
            return 1.0d;
        }
        return 1.0d - Beta.regularizedBeta(this.probabilityOfSuccess, ((double) i) + 1.0d, (double) (i2 - i));
    }

    public double getNumericalMean() {
        return ((double) this.numberOfTrials) * this.probabilityOfSuccess;
    }

    public double getNumericalVariance() {
        double d = this.probabilityOfSuccess;
        return ((double) this.numberOfTrials) * d * (1.0d - d);
    }

    public int getSupportLowerBound() {
        if (this.probabilityOfSuccess < 1.0d) {
            return 0;
        }
        return this.numberOfTrials;
    }

    public int getSupportUpperBound() {
        if (this.probabilityOfSuccess > 0.0d) {
            return this.numberOfTrials;
        }
        return 0;
    }
}
