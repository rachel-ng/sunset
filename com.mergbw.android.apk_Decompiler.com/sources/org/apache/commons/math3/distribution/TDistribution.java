package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Beta;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class TDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = -5852615386664158222L;
    private final double degreesOfFreedom;
    private final double factor;
    private final double solverAbsoluteAccuracy;

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

    public TDistribution(double d) throws NotStrictlyPositiveException {
        this(d, 1.0E-9d);
    }

    public TDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2);
    }

    public TDistribution(RandomGenerator randomGenerator, double d) throws NotStrictlyPositiveException {
        this(randomGenerator, d, 1.0E-9d);
    }

    public TDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d > 0.0d) {
            this.degreesOfFreedom = d;
            this.solverAbsoluteAccuracy = d2;
            this.factor = (Gamma.logGamma((1.0d + d) / 2.0d) - ((FastMath.log(3.141592653589793d) + FastMath.log(d)) * 0.5d)) - Gamma.logGamma(d / 2.0d);
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.DEGREES_OF_FREEDOM, Double.valueOf(d));
    }

    public double getDegreesOfFreedom() {
        return this.degreesOfFreedom;
    }

    public double density(double d) {
        return FastMath.exp(logDensity(d));
    }

    public double logDensity(double d) {
        double d2 = this.degreesOfFreedom;
        return this.factor - (((d2 + 1.0d) / 2.0d) * FastMath.log(((d * d) / d2) + 1.0d));
    }

    public double cumulativeProbability(double d) {
        if (d == 0.0d) {
            return 0.5d;
        }
        double d2 = this.degreesOfFreedom;
        double regularizedBeta = Beta.regularizedBeta(d2 / ((d * d) + d2), d2 * 0.5d, 0.5d);
        return d < 0.0d ? 0.5d * regularizedBeta : 1.0d - (regularizedBeta * 0.5d);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return getDegreesOfFreedom() > 1.0d ? 0.0d : Double.NaN;
    }

    public double getNumericalVariance() {
        double degreesOfFreedom2 = getDegreesOfFreedom();
        if (degreesOfFreedom2 > 2.0d) {
            return degreesOfFreedom2 / (degreesOfFreedom2 - 2.0d);
        }
        return (degreesOfFreedom2 <= 1.0d || degreesOfFreedom2 > 2.0d) ? Double.NaN : Double.POSITIVE_INFINITY;
    }
}
