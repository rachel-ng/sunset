package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractIntegerDistribution implements IntegerDistribution, Serializable {
    private static final long serialVersionUID = -1146319659338487221L;
    protected final RandomGenerator random;
    @Deprecated
    protected final RandomDataImpl randomData;

    @Deprecated
    protected AbstractIntegerDistribution() {
        this.randomData = new RandomDataImpl();
        this.random = null;
    }

    protected AbstractIntegerDistribution(RandomGenerator randomGenerator) {
        this.randomData = new RandomDataImpl();
        this.random = randomGenerator;
    }

    public double cumulativeProbability(int i, int i2) throws NumberIsTooLargeException {
        if (i2 >= i) {
            return cumulativeProbability(i2) - cumulativeProbability(i);
        }
        throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Integer.valueOf(i), Integer.valueOf(i2), true);
    }

    public int inverseCumulativeProbability(double d) throws OutOfRangeException {
        int i;
        double d2 = d;
        if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), 0, 1);
        }
        int supportLowerBound = getSupportLowerBound();
        if (d2 == 0.0d) {
            return supportLowerBound;
        }
        if (supportLowerBound != Integer.MIN_VALUE) {
            supportLowerBound--;
        } else if (checkedCumulativeProbability(supportLowerBound) >= d2) {
            return supportLowerBound;
        }
        int supportUpperBound = getSupportUpperBound();
        if (i == 0) {
            return supportUpperBound;
        }
        double numericalMean = getNumericalMean();
        double sqrt = FastMath.sqrt(getNumericalVariance());
        if (!Double.isInfinite(numericalMean) && !Double.isNaN(numericalMean) && !Double.isInfinite(sqrt) && !Double.isNaN(sqrt) && sqrt != 0.0d) {
            double sqrt2 = FastMath.sqrt((1.0d - d2) / d2);
            double d3 = numericalMean - (sqrt2 * sqrt);
            if (d3 > ((double) supportLowerBound)) {
                supportLowerBound = ((int) FastMath.ceil(d3)) - 1;
            }
            double d4 = numericalMean + ((1.0d / sqrt2) * sqrt);
            if (d4 < ((double) supportUpperBound)) {
                supportUpperBound = ((int) FastMath.ceil(d4)) - 1;
            }
        }
        return solveInverseCumulativeProbability(d2, supportLowerBound, supportUpperBound);
    }

    /* access modifiers changed from: protected */
    public int solveInverseCumulativeProbability(double d, int i, int i2) {
        while (i + 1 < i2) {
            int i3 = (i + i2) / 2;
            if (i3 < i || i3 > i2) {
                i3 = ((i2 - i) / 2) + i;
            }
            if (checkedCumulativeProbability(i3) >= d) {
                i2 = i3;
            } else {
                i = i3;
            }
        }
        return i2;
    }

    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
        this.randomData.reSeed(j);
    }

    public int sample() {
        return inverseCumulativeProbability(this.random.nextDouble());
    }

    public int[] sample(int i) {
        if (i > 0) {
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = sample();
            }
            return iArr;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }

    private double checkedCumulativeProbability(int i) throws MathInternalError {
        double cumulativeProbability = cumulativeProbability(i);
        if (!Double.isNaN(cumulativeProbability)) {
            return cumulativeProbability;
        }
        throw new MathInternalError(LocalizedFormats.DISCRETE_CUMULATIVE_PROBABILITY_RETURNED_NAN, Integer.valueOf(i));
    }

    public double logProbability(int i) {
        return FastMath.log(probability(i));
    }
}
