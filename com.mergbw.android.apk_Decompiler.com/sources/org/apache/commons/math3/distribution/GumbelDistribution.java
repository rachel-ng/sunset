package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class GumbelDistribution extends AbstractRealDistribution {
    private static final double EULER = 0.5778636748954609d;
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

    public GumbelDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public GumbelDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        if (d2 > 0.0d) {
            this.beta = d2;
            this.mu = d;
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
    }

    public double getLocation() {
        return this.mu;
    }

    public double getScale() {
        return this.beta;
    }

    public double density(double d) {
        double d2 = -((d - this.mu) / this.beta);
        return FastMath.exp(d2 - FastMath.exp(d2)) / this.beta;
    }

    public double cumulativeProbability(double d) {
        return FastMath.exp(-FastMath.exp(-((d - this.mu) / this.beta)));
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
            return this.mu - (FastMath.log(-FastMath.log(d)) * this.beta);
        }
    }

    public double getNumericalMean() {
        return this.mu + (this.beta * EULER);
    }

    public double getNumericalVariance() {
        double d = this.beta;
        return d * d * 1.6449340668482264d;
    }
}
