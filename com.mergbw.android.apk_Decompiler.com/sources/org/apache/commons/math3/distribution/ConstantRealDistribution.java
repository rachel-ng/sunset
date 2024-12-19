package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;

public class ConstantRealDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = -4157745166772046273L;
    private final double value;

    public double getNumericalVariance() {
        return 0.0d;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public void reseedRandomGenerator(long j) {
    }

    public ConstantRealDistribution(double d) {
        super((RandomGenerator) null);
        this.value = d;
    }

    public double density(double d) {
        return d == this.value ? 1.0d : 0.0d;
    }

    public double cumulativeProbability(double d) {
        return d < this.value ? 0.0d : 1.0d;
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d >= 0.0d && d <= 1.0d) {
            return this.value;
        }
        throw new OutOfRangeException(Double.valueOf(d), 0, 1);
    }

    public double getNumericalMean() {
        return this.value;
    }

    public double getSupportLowerBound() {
        return this.value;
    }

    public double getSupportUpperBound() {
        return this.value;
    }

    public double sample() {
        return this.value;
    }
}
