package org.apache.commons.math3.random;

import org.apache.commons.math3.util.FastMath;

public class UnitSphereRandomVectorGenerator implements RandomVectorGenerator {
    private final int dimension;
    private final RandomGenerator rand;

    public UnitSphereRandomVectorGenerator(int i, RandomGenerator randomGenerator) {
        this.dimension = i;
        this.rand = randomGenerator;
    }

    public UnitSphereRandomVectorGenerator(int i) {
        this(i, new MersenneTwister());
    }

    public double[] nextVector() {
        double[] dArr = new double[this.dimension];
        double d = 0.0d;
        for (int i = 0; i < this.dimension; i++) {
            double nextGaussian = this.rand.nextGaussian();
            dArr[i] = nextGaussian;
            d += nextGaussian * nextGaussian;
        }
        double sqrt = 1.0d / FastMath.sqrt(d);
        for (int i2 = 0; i2 < this.dimension; i2++) {
            dArr[i2] = dArr[i2] * sqrt;
        }
        return dArr;
    }
}
