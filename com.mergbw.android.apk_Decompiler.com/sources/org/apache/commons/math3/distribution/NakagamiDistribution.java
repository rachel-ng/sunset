package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class NakagamiDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20141003;
    private final double inverseAbsoluteAccuracy;
    private final double mu;
    private final double omega;

    public double getSupportLowerBound() {
        return 0.0d;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public NakagamiDistribution(double d, double d2) {
        this(d, d2, 1.0E-9d);
    }

    public NakagamiDistribution(double d, double d2, double d3) {
        this(new Well19937c(), d, d2, d3);
    }

    public NakagamiDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) {
        super(randomGenerator);
        if (d < 0.5d) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(0.5d), true);
        } else if (d2 > 0.0d) {
            this.mu = d;
            this.omega = d2;
            this.inverseAbsoluteAccuracy = d3;
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, Double.valueOf(d2));
        }
    }

    public double getShape() {
        return this.mu;
    }

    public double getScale() {
        return this.omega;
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.inverseAbsoluteAccuracy;
    }

    public double density(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        double d2 = this.mu;
        return ((FastMath.pow(d2, d2) * 2.0d) / (Gamma.gamma(this.mu) * FastMath.pow(this.omega, this.mu))) * FastMath.pow(d, (this.mu * 2.0d) - 1.0d) * FastMath.exp((((-this.mu) * d) * d) / this.omega);
    }

    public double cumulativeProbability(double d) {
        double d2 = this.mu;
        return Gamma.regularizedGammaP(d2, ((d2 * d) * d) / this.omega);
    }

    public double getNumericalMean() {
        return (Gamma.gamma(this.mu + 0.5d) / Gamma.gamma(this.mu)) * FastMath.sqrt(this.omega / this.mu);
    }

    public double getNumericalVariance() {
        double gamma = Gamma.gamma(this.mu + 0.5d) / Gamma.gamma(this.mu);
        return this.omega * (1.0d - (((1.0d / this.mu) * gamma) * gamma));
    }
}
