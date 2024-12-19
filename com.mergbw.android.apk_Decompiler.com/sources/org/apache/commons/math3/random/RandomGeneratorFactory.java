package org.apache.commons.math3.random;

import java.util.Random;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;

public class RandomGeneratorFactory {
    private RandomGeneratorFactory() {
    }

    public static RandomGenerator createRandomGenerator(final Random random) {
        return new RandomGenerator() {
            public void setSeed(int i) {
                random.setSeed((long) i);
            }

            public void setSeed(int[] iArr) {
                random.setSeed(RandomGeneratorFactory.convertToLong(iArr));
            }

            public void setSeed(long j) {
                random.setSeed(j);
            }

            public void nextBytes(byte[] bArr) {
                random.nextBytes(bArr);
            }

            public int nextInt() {
                return random.nextInt();
            }

            public int nextInt(int i) {
                if (i > 0) {
                    return random.nextInt(i);
                }
                throw new NotStrictlyPositiveException(Integer.valueOf(i));
            }

            public long nextLong() {
                return random.nextLong();
            }

            public boolean nextBoolean() {
                return random.nextBoolean();
            }

            public float nextFloat() {
                return random.nextFloat();
            }

            public double nextDouble() {
                return random.nextDouble();
            }

            public double nextGaussian() {
                return random.nextGaussian();
            }
        };
    }

    public static long convertToLong(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            j = (j * 4294967291L) + ((long) i);
        }
        return j;
    }
}
