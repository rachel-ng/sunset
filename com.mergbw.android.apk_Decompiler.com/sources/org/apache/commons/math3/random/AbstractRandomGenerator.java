package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRandomGenerator implements RandomGenerator {
    private double cachedNormalDeviate = Double.NaN;

    public abstract double nextDouble();

    public abstract void setSeed(long j);

    public void clear() {
        this.cachedNormalDeviate = Double.NaN;
    }

    public void setSeed(int i) {
        setSeed((long) i);
    }

    public void setSeed(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            j = (j * 4294967291L) + ((long) i);
        }
        setSeed(j);
    }

    public void nextBytes(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int nextInt = nextInt();
            int i2 = 0;
            while (true) {
                if (i2 < 3) {
                    if (i2 > 0) {
                        nextInt >>= 8;
                    }
                    int i3 = i + 1;
                    bArr[i] = (byte) nextInt;
                    if (i3 != bArr.length) {
                        i2++;
                        i = i3;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public int nextInt() {
        return (int) (((nextDouble() * 2.0d) - 1.0d) * 2.147483647E9d);
    }

    public int nextInt(int i) {
        if (i > 0) {
            int nextDouble = (int) (nextDouble() * ((double) i));
            return nextDouble < i ? nextDouble : i - 1;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public long nextLong() {
        return (long) (((nextDouble() * 2.0d) - 1.0d) * 9.223372036854776E18d);
    }

    public boolean nextBoolean() {
        return nextDouble() <= 0.5d;
    }

    public float nextFloat() {
        return (float) nextDouble();
    }

    public double nextGaussian() {
        if (!Double.isNaN(this.cachedNormalDeviate)) {
            double d = this.cachedNormalDeviate;
            this.cachedNormalDeviate = Double.NaN;
            return d;
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 1.0d;
        while (d4 >= 1.0d) {
            double nextDouble = (nextDouble() * 2.0d) - 1.0d;
            d3 = (nextDouble() * 2.0d) - 1.0d;
            double d5 = nextDouble;
            d4 = (nextDouble * nextDouble) + (d3 * d3);
            d2 = d5;
        }
        if (d4 != 0.0d) {
            d4 = FastMath.sqrt((FastMath.log(d4) * -2.0d) / d4);
        }
        this.cachedNormalDeviate = d3 * d4;
        return d2 * d4;
    }
}
