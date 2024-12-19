package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class ParetoDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20130424;
    private final double scale;
    private final double shape;
    private final double solverAbsoluteAccuracy;

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

    public ParetoDistribution() {
        this(1.0d, 1.0d);
    }

    public ParetoDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public ParetoDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public ParetoDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    public ParetoDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d));
        } else if (d2 > 0.0d) {
            this.scale = d;
            this.shape = d2;
            this.solverAbsoluteAccuracy = d3;
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d2));
        }
    }

    public double getScale() {
        return this.scale;
    }

    public double getShape() {
        return this.shape;
    }

    public double density(double d) {
        double d2 = this.scale;
        if (d < d2) {
            return 0.0d;
        }
        return (FastMath.pow(d2, this.shape) / FastMath.pow(d, this.shape + 1.0d)) * this.shape;
    }

    public double logDensity(double d) {
        double d2 = this.scale;
        if (d < d2) {
            return Double.NEGATIVE_INFINITY;
        }
        double log = FastMath.log(d2) * this.shape;
        double log2 = FastMath.log(d);
        double d3 = this.shape;
        return (log - (log2 * (1.0d + d3))) + FastMath.log(d3);
    }

    public double cumulativeProbability(double d) {
        double d2 = this.scale;
        if (d <= d2) {
            return 0.0d;
        }
        return 1.0d - FastMath.pow(d2 / d, this.shape);
    }

    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        double d = this.shape;
        if (d <= 1.0d) {
            return Double.POSITIVE_INFINITY;
        }
        return (this.scale * d) / (d - 1.0d);
    }

    public double getNumericalVariance() {
        double d = this.shape;
        if (d <= 2.0d) {
            return Double.POSITIVE_INFINITY;
        }
        double d2 = d - 1.0d;
        double d3 = this.scale;
        return (((d3 * d3) * d) / (d2 * d2)) / (d - 2.0d);
    }

    public double getSupportLowerBound() {
        return this.scale;
    }

    public double sample() {
        return this.scale / FastMath.pow(this.random.nextDouble(), 1.0d / this.shape);
    }
}
