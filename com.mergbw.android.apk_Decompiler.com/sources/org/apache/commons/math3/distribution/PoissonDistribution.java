package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.CombinatoricsUtils;
import org.apache.commons.math3.util.FastMath;

public class PoissonDistribution extends AbstractIntegerDistribution {
    public static final double DEFAULT_EPSILON = 1.0E-12d;
    public static final int DEFAULT_MAX_ITERATIONS = 10000000;
    private static final long serialVersionUID = -3349935121172596109L;
    private final double epsilon;
    private final ExponentialDistribution exponential;
    private final int maxIterations;
    private final double mean;
    private final NormalDistribution normal;

    public int getSupportLowerBound() {
        return 0;
    }

    public int getSupportUpperBound() {
        return Integer.MAX_VALUE;
    }

    public boolean isSupportConnected() {
        return true;
    }

    public PoissonDistribution(double d) throws NotStrictlyPositiveException {
        this(d, 1.0E-12d, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double d, double d2, int i) throws NotStrictlyPositiveException {
        this(new Well19937c(), d, d2, i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PoissonDistribution(RandomGenerator randomGenerator, double d, double d2, int i) throws NotStrictlyPositiveException {
        super(randomGenerator);
        double d3 = d;
        if (d3 > 0.0d) {
            this.mean = d3;
            this.epsilon = d2;
            this.maxIterations = i;
            this.normal = new NormalDistribution(randomGenerator, d, FastMath.sqrt(d), 1.0E-9d);
            this.exponential = new ExponentialDistribution(randomGenerator, 1.0d, 1.0E-9d);
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.MEAN, Double.valueOf(d));
    }

    public PoissonDistribution(double d, double d2) throws NotStrictlyPositiveException {
        this(d, d2, DEFAULT_MAX_ITERATIONS);
    }

    public PoissonDistribution(double d, int i) {
        this(d, 1.0E-12d, i);
    }

    public double getMean() {
        return this.mean;
    }

    public double probability(int i) {
        double logProbability = logProbability(i);
        if (logProbability == Double.NEGATIVE_INFINITY) {
            return 0.0d;
        }
        return FastMath.exp(logProbability);
    }

    public double logProbability(int i) {
        if (i < 0 || i == Integer.MAX_VALUE) {
            return Double.NEGATIVE_INFINITY;
        }
        if (i == 0) {
            return -this.mean;
        }
        double d = (double) i;
        return (((-SaddlePointExpansion.getStirlingError(d)) - SaddlePointExpansion.getDeviancePart(d, this.mean)) - (FastMath.log(6.283185307179586d) * 0.5d)) - (FastMath.log(d) * 0.5d);
    }

    public double cumulativeProbability(int i) {
        if (i < 0) {
            return 0.0d;
        }
        if (i == Integer.MAX_VALUE) {
            return 1.0d;
        }
        return Gamma.regularizedGammaQ(((double) i) + 1.0d, this.mean, this.epsilon, this.maxIterations);
    }

    public double normalApproximateProbability(int i) {
        return this.normal.cumulativeProbability(((double) i) + 0.5d);
    }

    public double getNumericalMean() {
        return getMean();
    }

    public double getNumericalVariance() {
        return getMean();
    }

    public int sample() {
        return (int) FastMath.min(nextPoisson(this.mean), 2147483647L);
    }

    private long nextPoisson(double d) {
        double d2;
        double d3;
        double d4;
        double d5;
        double d6;
        PoissonDistribution poissonDistribution = this;
        double d7 = d;
        long j = 0;
        double d8 = 1.0d;
        if (d7 < 40.0d) {
            double exp = FastMath.exp(-d7);
            while (((double) j) < 1000.0d * d7) {
                d8 *= poissonDistribution.random.nextDouble();
                if (d8 < exp) {
                    break;
                }
                j++;
            }
            return j;
        }
        double floor = FastMath.floor(d);
        double d9 = d7 - floor;
        double log = FastMath.log(floor);
        double factorialLog = CombinatoricsUtils.factorialLog((int) floor);
        if (d9 >= Double.MIN_VALUE) {
            j = poissonDistribution.nextPoisson(d9);
        }
        double sqrt = FastMath.sqrt(FastMath.log(((32.0d * floor) / 3.141592653589793d) + 1.0d) * floor);
        double d10 = sqrt / 2.0d;
        double d11 = floor * 2.0d;
        double d12 = d11 + sqrt;
        double d13 = 1.0d / (8.0d * floor);
        double sqrt2 = FastMath.sqrt(3.141592653589793d * d12) * FastMath.exp(d13);
        double d14 = d12 / sqrt;
        long j2 = j;
        double exp2 = FastMath.exp(((-sqrt) * (sqrt + 1.0d)) / d12) * d14;
        double d15 = sqrt2 + exp2 + 1.0d;
        double d16 = sqrt2 / d15;
        double d17 = exp2 / d15;
        while (true) {
            double nextDouble = poissonDistribution.random.nextDouble();
            if (nextDouble <= d16) {
                double nextGaussian = poissonDistribution.random.nextGaussian();
                d3 = (FastMath.sqrt(floor + d10) * nextGaussian) - 0.5d;
                if (d3 > sqrt) {
                    continue;
                } else if (d3 >= (-floor)) {
                    double floor2 = d3 < 0.0d ? FastMath.floor(d3) : FastMath.ceil(d3);
                    d4 = d17;
                    d6 = ((-poissonDistribution.exponential.sample()) - ((nextGaussian * nextGaussian) / 2.0d)) + d13;
                    d5 = floor2;
                    d2 = 1.0d;
                }
            } else if (nextDouble > d16 + d17) {
                break;
            } else {
                d3 = sqrt + (poissonDistribution.exponential.sample() * d14);
                d5 = FastMath.ceil(d3);
                d4 = d17;
                d2 = 1.0d;
                d6 = (-poissonDistribution.exponential.sample()) - (((d3 + 1.0d) * sqrt) / d12);
            }
            int i = d3 < 0.0d ? 1 : 0;
            double d18 = d5 + d2;
            double d19 = sqrt;
            double d20 = (d5 * d18) / d11;
            double d21 = d16;
            if (d6 < (-d20) && i == 0) {
                break;
            }
            double d22 = ((((d5 * 2.0d) + 1.0d) / (6.0d * floor)) - 1.0d) * d20;
            double d23 = factorialLog;
            if (d6 < d22 - ((d20 * d20) / (((((double) i) * d18) + floor) * 3.0d))) {
                break;
            }
            if (d6 <= d22) {
                double d24 = d5 * log;
                double d25 = d5 + floor;
                if (d6 < (d24 - CombinatoricsUtils.factorialLog((int) d25)) + d23) {
                    floor = d25;
                    break;
                }
            }
            poissonDistribution = this;
            d17 = d4;
            d16 = d21;
            sqrt = d19;
            factorialLog = d23;
        }
        floor += d5;
        return j2 + ((long) floor);
    }
}
