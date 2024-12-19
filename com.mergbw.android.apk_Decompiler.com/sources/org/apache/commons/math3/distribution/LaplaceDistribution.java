package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class LaplaceDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20141003;
    private final double beta;
    private final double mu;

    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public LaplaceDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public LaplaceDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        if (d2 > 0.0d) {
            this.mu = d;
            this.beta = d2;
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, Double.valueOf(d2));
    }

    public double getLocation() {
        return this.mu;
    }

    public double getScale() {
        return this.beta;
    }

    public double density(double d) {
        return FastMath.exp((-FastMath.abs(d - this.mu)) / this.beta) / (this.beta * 2.0d);
    }

    public double cumulativeProbability(double d) {
        double d2 = this.mu;
        if (d <= d2) {
            return FastMath.exp((d - d2) / this.beta) / 2.0d;
        }
        return 1.0d - (FastMath.exp((d2 - d) / this.beta) / 2.0d);
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        } else if (d == 0.0d) {
            return Double.NEGATIVE_INFINITY;
        } else {
            if (i == 0) {
                return Double.POSITIVE_INFINITY;
            }
            int i2 = (d > 0.5d ? 1 : (d == 0.5d ? 0 : -1));
            double d2 = d * 2.0d;
            return this.mu + (this.beta * (i2 > 0 ? -Math.log(2.0d - d2) : Math.log(d2)));
        }
    }

    public double getNumericalMean() {
        return this.mu;
    }

    public double getNumericalVariance() {
        double d = this.beta;
        return 2.0d * d * d;
    }
}
