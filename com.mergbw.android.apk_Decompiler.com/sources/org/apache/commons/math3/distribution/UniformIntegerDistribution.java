package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;

public class UniformIntegerDistribution extends AbstractIntegerDistribution {
    private static final long serialVersionUID = 20120109;
    private final int lower;
    private final int upper;

    public boolean isSupportConnected() {
        return true;
    }

    public UniformIntegerDistribution(int i, int i2) throws NumberIsTooLargeException {
        this(new Well19937c(), i, i2);
    }

    public UniformIntegerDistribution(RandomGenerator randomGenerator, int i, int i2) throws NumberIsTooLargeException {
        super(randomGenerator);
        if (i <= i2) {
            this.lower = i;
            this.upper = i2;
            return;
        }
        throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Integer.valueOf(i), Integer.valueOf(i2), true);
    }

    public double probability(int i) {
        int i2;
        int i3 = this.lower;
        if (i < i3 || i > (i2 = this.upper)) {
            return 0.0d;
        }
        return 1.0d / ((double) ((i2 - i3) + 1));
    }

    public double cumulativeProbability(int i) {
        int i2 = this.lower;
        if (i < i2) {
            return 0.0d;
        }
        int i3 = this.upper;
        if (i > i3) {
            return 1.0d;
        }
        return (((double) (i - i2)) + 1.0d) / (((double) (i3 - i2)) + 1.0d);
    }

    public double getNumericalMean() {
        return ((double) (this.lower + this.upper)) * 0.5d;
    }

    public double getNumericalVariance() {
        double d = (double) ((this.upper - this.lower) + 1);
        return ((d * d) - 1.0d) / 12.0d;
    }

    public int getSupportLowerBound() {
        return this.lower;
    }

    public int getSupportUpperBound() {
        return this.upper;
    }

    public int sample() {
        int i = this.upper;
        int i2 = this.lower;
        int i3 = (i - i2) + 1;
        if (i3 > 0) {
            return i2 + this.random.nextInt(i3);
        }
        while (true) {
            int nextInt = this.random.nextInt();
            if (nextInt >= this.lower && nextInt <= this.upper) {
                return nextInt;
            }
        }
    }
}
