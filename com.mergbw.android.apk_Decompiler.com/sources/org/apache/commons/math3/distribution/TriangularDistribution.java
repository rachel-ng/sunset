package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

public class TriangularDistribution extends AbstractRealDistribution {
    private static final long serialVersionUID = 20120112;

    /* renamed from: a  reason: collision with root package name */
    private final double f69a;

    /* renamed from: b  reason: collision with root package name */
    private final double f70b;

    /* renamed from: c  reason: collision with root package name */
    private final double f71c;
    private final double solverAbsoluteAccuracy;

    public boolean isSupportConnected() {
        return true;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return true;
    }

    public TriangularDistribution(double d, double d2, double d3) throws NumberIsTooLargeException, NumberIsTooSmallException {
        this(new Well19937c(), d, d2, d3);
    }

    public TriangularDistribution(RandomGenerator randomGenerator, double d, double d2, double d3) throws NumberIsTooLargeException, NumberIsTooSmallException {
        super(randomGenerator);
        if (d >= d3) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d3), false);
        } else if (d2 < d) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Double.valueOf(d2), Double.valueOf(d), true);
        } else if (d2 <= d3) {
            this.f69a = d;
            this.f71c = d2;
            this.f70b = d3;
            this.solverAbsoluteAccuracy = FastMath.max(FastMath.ulp(d), FastMath.ulp(d3));
        } else {
            throw new NumberIsTooLargeException(LocalizedFormats.NUMBER_TOO_LARGE, Double.valueOf(d2), Double.valueOf(d3), true);
        }
    }

    public double getMode() {
        return this.f71c;
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public double density(double d) {
        double d2 = this.f69a;
        if (d < d2) {
            return 0.0d;
        }
        if (d2 <= d) {
            double d3 = this.f71c;
            if (d < d3) {
                return ((d - d2) * 2.0d) / ((this.f70b - d2) * (d3 - d2));
            }
        }
        double d4 = this.f71c;
        if (d == d4) {
            return 2.0d / (this.f70b - d2);
        }
        if (d4 < d) {
            double d5 = this.f70b;
            if (d <= d5) {
                return ((d5 - d) * 2.0d) / ((d5 - d2) * (d5 - d4));
            }
        }
        return 0.0d;
    }

    public double cumulativeProbability(double d) {
        double d2 = this.f69a;
        if (d < d2) {
            return 0.0d;
        }
        if (d2 <= d) {
            double d3 = this.f71c;
            if (d < d3) {
                return ((d - d2) * (d - d2)) / ((this.f70b - d2) * (d3 - d2));
            }
        }
        double d4 = this.f71c;
        if (d == d4) {
            return (d4 - d2) / (this.f70b - d2);
        }
        if (d4 >= d) {
            return 1.0d;
        }
        double d5 = this.f70b;
        if (d <= d5) {
            return 1.0d - (((d5 - d) * (d5 - d)) / ((d5 - d2) * (d5 - d4)));
        }
        return 1.0d;
    }

    public double getNumericalMean() {
        return ((this.f69a + this.f70b) + this.f71c) / 3.0d;
    }

    public double getNumericalVariance() {
        double d = this.f69a;
        double d2 = this.f70b;
        double d3 = (d * d) + (d2 * d2);
        double d4 = this.f71c;
        return ((((d3 + (d4 * d4)) - (d * d2)) - (d * d4)) - (d2 * d4)) / 18.0d;
    }

    public double getSupportLowerBound() {
        return this.f69a;
    }

    public double getSupportUpperBound() {
        return this.f70b;
    }

    public double inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        } else if (d == 0.0d) {
            return this.f69a;
        } else {
            if (i == 0) {
                return this.f70b;
            }
            double d2 = this.f71c;
            double d3 = this.f69a;
            double d4 = this.f70b;
            if (d < (d2 - d3) / (d4 - d3)) {
                return d3 + FastMath.sqrt(d * (d4 - d3) * (d2 - d3));
            }
            return d4 - FastMath.sqrt(((1.0d - d) * (d4 - d3)) * (d4 - d2));
        }
    }
}
