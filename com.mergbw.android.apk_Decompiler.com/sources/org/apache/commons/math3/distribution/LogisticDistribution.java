package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class LogisticDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20141003;
    private final double mu;
    private final double s;

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

    public LogisticDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public LogisticDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        if (d2 > 0.0d) {
            this.mu = d;
            this.s = d2;
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, Double.valueOf(d2));
    }

    public double getLocation() {
        return this.mu;
    }

    public double getScale() {
        return this.s;
    }

    public double density(double d) {
        double exp = FastMath.exp(-((d - this.mu) / this.s));
        double d2 = (1.0d / this.s) * exp;
        double d3 = exp + 1.0d;
        return d2 / (d3 * d3);
    }

    public double cumulativeProbability(double d) {
        return 1.0d / (FastMath.exp(-((1.0d / this.s) * (d - this.mu))) + 1.0d);
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(0.0d), Double.valueOf(1.0d));
        } else if (d == 0.0d) {
            return 0.0d;
        } else {
            if (i == 0) {
                return Double.POSITIVE_INFINITY;
            }
            return (this.s * Math.log(d / (1.0d - d))) + this.mu;
        }
    }

    public double getNumericalMean() {
        return this.mu;
    }

    public double getNumericalVariance() {
        double d = this.s;
        return (1.0d / (d * d)) * 3.289868133696453d;
    }
}
