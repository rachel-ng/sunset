package org.apache.commons.math3.distribution;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;

public abstract class AbstractMultivariateRealDistribution implements MultivariateRealDistribution {
    private final int dimension;
    protected final RandomGenerator random;

    public abstract double[] sample();

    protected AbstractMultivariateRealDistribution(RandomGenerator randomGenerator, int i) {
        this.random = randomGenerator;
        this.dimension = i;
    }

    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
    }

    public int getDimension() {
        return this.dimension;
    }

    public double[][] sample(int i) {
        if (i > 0) {
            int[] iArr = new int[2];
            iArr[1] = this.dimension;
            iArr[0] = i;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i2 = 0; i2 < i; i2++) {
                dArr[i2] = sample();
            }
            return dArr;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }
}
