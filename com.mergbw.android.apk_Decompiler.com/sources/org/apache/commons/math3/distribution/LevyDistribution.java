package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Erf;
import org.apache.commons.math3.util.FastMath;

public class LevyDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20130314;

    /* renamed from: c  reason: collision with root package name */
    private final double f68c;
    private final double halfC;
    private final double mu;

    public double getNumericalMean() {
        return Double.POSITIVE_INFINITY;
    }

    public double getNumericalVariance() {
        return Double.POSITIVE_INFINITY;
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

    public LevyDistribution(double d, double d2) {
        this(new Well19937c(), d, d2);
    }

    public LevyDistribution(RandomGenerator randomGenerator, double d, double d2) {
        super(randomGenerator);
        this.mu = d;
        this.f68c = d2;
        this.halfC = d2 * 0.5d;
    }

    public double density(double d) {
        double d2 = this.mu;
        if (d < d2) {
            return Double.NaN;
        }
        double d3 = d - d2;
        double d4 = this.halfC / d3;
        return (FastMath.sqrt(d4 / 3.141592653589793d) * FastMath.exp(-d4)) / d3;
    }

    public double logDensity(double d) {
        double d2 = this.mu;
        if (d < d2) {
            return Double.NaN;
        }
        double d3 = d - d2;
        double d4 = this.halfC / d3;
        return ((FastMath.log(d4 / 3.141592653589793d) * 0.5d) - d4) - FastMath.log(d3);
    }

    public double cumulativeProbability(double d) {
        double d2 = this.mu;
        if (d < d2) {
            return Double.NaN;
        }
        return Erf.erfc(FastMath.sqrt(this.halfC / (d - d2)));
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        double erfcInv = Erf.erfcInv(d);
        return this.mu + (this.halfC / (erfcInv * erfcInv));
    }

    public double getScale() {
        return this.f68c;
    }

    public double getLocation() {
        return this.mu;
    }

    public double getSupportLowerBound() {
        return this.mu;
    }
}
