package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

public class GammaDistribution extends AbstractRealDistribution {
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1.0E-9d;
    private static final long serialVersionUID = 20120524;
    private final double densityPrefactor1;
    private final double densityPrefactor2;
    private final double logDensityPrefactor1;
    private final double logDensityPrefactor2;
    private final double maxLogY;
    private final double minY;
    private final double scale;
    private final double shape;
    private final double shiftedShape;
    private final double solverAbsoluteAccuracy;

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

    public GammaDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, 1.0E-9d);
    }

    public GammaDistribution(double d, double d2, double d3) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, d3);
    }

    public GammaDistribution(RandomGenerator randomGenerator, double d, double d2) throws NotStrictlyPositiveException {
        this(randomGenerator, d, d2, 1.0E-9d);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public GammaDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NotStrictlyPositiveException {
        super(randomGenerator);
        double d4 = d;
        double d5 = d2;
        if (d4 <= 0.0d) {
            throw new NotStrictlyPositiveException(LocalizedFormats.SHAPE, Double.valueOf(d));
        } else if (d5 > 0.0d) {
            this.shape = d4;
            this.scale = d5;
            this.solverAbsoluteAccuracy = d3;
            double d6 = d4 + 4.7421875d;
            double d7 = d6 + 0.5d;
            this.shiftedShape = d7;
            double d8 = 2.718281828459045d / (6.283185307179586d * d7);
            double sqrt = (FastMath.sqrt(d8) * d4) / Gamma.lanczos(d);
            this.densityPrefactor2 = sqrt;
            double log = (FastMath.log(d) + (FastMath.log(d8) * 0.5d)) - FastMath.log(Gamma.lanczos(d));
            this.logDensityPrefactor2 = log;
            this.densityPrefactor1 = (sqrt / d5) * FastMath.pow(d7, -d4) * FastMath.exp(d6);
            this.logDensityPrefactor1 = ((log - FastMath.log(d2)) - (FastMath.log(d7) * d4)) + d4 + 4.7421875d;
            this.minY = d6 - FastMath.log(Double.MAX_VALUE);
            this.maxLogY = FastMath.log(Double.MAX_VALUE) / (d4 - 1.0d);
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.SCALE, Double.valueOf(d2));
        }
    }

    @Deprecated
    public double getAlpha() {
        return this.shape;
    }

    public double getShape() {
        return this.shape;
    }

    @Deprecated
    public double getBeta() {
        return this.scale;
    }

    public double getScale() {
        return this.scale;
    }

    public double density(double d) {
        if (d < 0.0d) {
            return 0.0d;
        }
        double d2 = d / this.scale;
        if (d2 > this.minY && FastMath.log(d2) < this.maxLogY) {
            return this.densityPrefactor1 * FastMath.exp(-d2) * FastMath.pow(d2, this.shape - 1.0d);
        }
        double d3 = this.shiftedShape;
        double d4 = (d2 - d3) / d3;
        return (this.densityPrefactor2 / d) * FastMath.exp((((-d2) * 5.2421875d) / this.shiftedShape) + 4.7421875d + (this.shape * (FastMath.log1p(d4) - d4)));
    }

    public double logDensity(double d) {
        if (d < 0.0d) {
            return Double.NEGATIVE_INFINITY;
        }
        double d2 = d / this.scale;
        if (d2 > this.minY && FastMath.log(d2) < this.maxLogY) {
            return (this.logDensityPrefactor1 - d2) + (FastMath.log(d2) * (this.shape - 1.0d));
        }
        double d3 = this.shiftedShape;
        double d4 = (d2 - d3) / d3;
        return (this.logDensityPrefactor2 - FastMath.log(d)) + (((-d2) * 5.2421875d) / this.shiftedShape) + 4.7421875d + (this.shape * (FastMath.log1p(d4) - d4));
    }

    public double cumulativeProbability(double d) {
        if (d <= 0.0d) {
            return 0.0d;
        }
        return Gamma.regularizedGammaP(this.shape, d / this.scale);
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double getNumericalMean() {
        return this.shape * this.scale;
    }

    public double getNumericalVariance() {
        double d = this.shape;
        double d2 = this.scale;
        return d * d2 * d2;
    }

    public double sample() {
        double d = this.shape;
        if (d < 1.0d) {
            while (true) {
                double nextDouble = this.random.nextDouble();
                double d2 = this.shape;
                double d3 = (d2 / 2.718281828459045d) + 1.0d;
                double d4 = nextDouble * d3;
                if (d4 <= 1.0d) {
                    double pow = FastMath.pow(d4, 1.0d / d2);
                    if (this.random.nextDouble() <= FastMath.exp(-pow)) {
                        return this.scale * pow;
                    }
                } else {
                    double log = FastMath.log((d3 - d4) / d2) * -1.0d;
                    if (this.random.nextDouble() <= FastMath.pow(log, this.shape - 1.0d)) {
                        return this.scale * log;
                    }
                }
            }
        } else {
            double d5 = d - 0.3333333333333333d;
            double sqrt = 1.0d / (FastMath.sqrt(d5) * 3.0d);
            while (true) {
                double nextGaussian = this.random.nextGaussian();
                double d6 = (sqrt * nextGaussian) + 1.0d;
                double d7 = d6 * d6 * d6;
                if (d7 > 0.0d) {
                    double d8 = nextGaussian * nextGaussian;
                    double nextDouble2 = this.random.nextDouble();
                    if (nextDouble2 < 1.0d - ((0.0331d * d8) * d8)) {
                        return this.scale * d5 * d7;
                    }
                    if (FastMath.log(nextDouble2) < (d8 * 0.5d) + (((1.0d - d7) + FastMath.log(d7)) * d5)) {
                        return this.scale * d5 * d7;
                    }
                }
            }
        }
    }
}
