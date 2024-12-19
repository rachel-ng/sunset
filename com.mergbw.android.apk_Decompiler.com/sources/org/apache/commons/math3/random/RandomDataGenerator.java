package org.apache.commons.math3.random;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Collection;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.distribution.BinomialDistribution;
import org.apache.commons.math3.distribution.CauchyDistribution;
import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.GammaDistribution;
import org.apache.commons.math3.distribution.HypergeometricDistribution;
import org.apache.commons.math3.distribution.PascalDistribution;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.distribution.UniformIntegerDistribution;
import org.apache.commons.math3.distribution.WeibullDistribution;
import org.apache.commons.math3.distribution.ZipfDistribution;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class RandomDataGenerator implements RandomData, Serializable {
    private static final long serialVersionUID = -626730818244969716L;
    private RandomGenerator rand;
    private RandomGenerator secRand;

    public RandomDataGenerator() {
        this.rand = null;
        this.secRand = null;
    }

    public RandomDataGenerator(RandomGenerator randomGenerator) {
        this.secRand = null;
        this.rand = randomGenerator;
    }

    public String nextHexString(int i) throws NotStrictlyPositiveException {
        if (i > 0) {
            RandomGenerator randomGenerator = getRandomGenerator();
            StringBuilder sb = new StringBuilder();
            int i2 = (i / 2) + 1;
            byte[] bArr = new byte[i2];
            randomGenerator.nextBytes(bArr);
            for (int i3 = 0; i3 < i2; i3++) {
                byte b2 = bArr[i3];
                Integer.valueOf(b2).getClass();
                String hexString = Integer.toHexString(b2 + 128);
                if (hexString.length() == 1) {
                    hexString = SessionDescription.SUPPORTED_SDP_VERSION + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString().substring(0, i);
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i));
    }

    public int nextInt(int i, int i2) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(getRandomGenerator(), i, i2).sample();
    }

    public long nextLong(long j, long j2) throws NumberIsTooLargeException {
        long nextLong;
        if (j < j2) {
            long j3 = (j2 - j) + 1;
            if (j3 <= 0) {
                RandomGenerator randomGenerator = getRandomGenerator();
                while (true) {
                    long nextLong2 = randomGenerator.nextLong();
                    if (nextLong2 >= j && nextLong2 <= j2) {
                        return nextLong2;
                    }
                }
            } else {
                if (j3 < 2147483647L) {
                    nextLong = (long) getRandomGenerator().nextInt((int) j3);
                } else {
                    nextLong = nextLong(getRandomGenerator(), j3);
                }
                return j + nextLong;
            }
        } else {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(j), Long.valueOf(j2), false);
        }
    }

    private static long nextLong(RandomGenerator randomGenerator, long j) throws IllegalArgumentException {
        long j2;
        long j3;
        if (j > 0) {
            byte[] bArr = new byte[8];
            do {
                randomGenerator.nextBytes(bArr);
                long j4 = 0;
                for (int i = 0; i < 8; i++) {
                    j4 = (j4 << 8) | (((long) bArr[i]) & 255);
                }
                j2 = j4 & Long.MAX_VALUE;
                j3 = j2 % j;
            } while ((j2 - j3) + (j - 1) < 0);
            return j3;
        }
        throw new NotStrictlyPositiveException(Long.valueOf(j));
    }

    public String nextSecureHexString(int i) throws NotStrictlyPositiveException {
        if (i > 0) {
            RandomGenerator secRan = getSecRan();
            try {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.reset();
                int i2 = i / 40;
                StringBuilder sb = new StringBuilder();
                int i3 = 1;
                while (true) {
                    if (i3 >= i2 + 2) {
                        return sb.toString().substring(0, i);
                    }
                    byte[] bArr = new byte[40];
                    secRan.nextBytes(bArr);
                    instance.update(bArr);
                    byte[] digest = instance.digest();
                    for (byte b2 : digest) {
                        Integer.valueOf(b2).getClass();
                        String hexString = Integer.toHexString(b2 + 128);
                        if (hexString.length() == 1) {
                            hexString = SessionDescription.SUPPORTED_SDP_VERSION + hexString;
                        }
                        sb.append(hexString);
                    }
                    i3++;
                }
            } catch (NoSuchAlgorithmException e) {
                throw new MathInternalError(e);
            }
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i));
        }
    }

    public int nextSecureInt(int i, int i2) throws NumberIsTooLargeException {
        return new UniformIntegerDistribution(getSecRan(), i, i2).sample();
    }

    public long nextSecureLong(long j, long j2) throws NumberIsTooLargeException {
        long nextLong;
        if (j < j2) {
            RandomGenerator secRan = getSecRan();
            long j3 = (j2 - j) + 1;
            if (j3 <= 0) {
                while (true) {
                    long nextLong2 = secRan.nextLong();
                    if (nextLong2 >= j && nextLong2 <= j2) {
                        return nextLong2;
                    }
                }
            } else {
                if (j3 < 2147483647L) {
                    nextLong = (long) secRan.nextInt((int) j3);
                } else {
                    nextLong = nextLong(secRan, j3);
                }
                return j + nextLong;
            }
        } else {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Long.valueOf(j), Long.valueOf(j2), false);
        }
    }

    public long nextPoisson(double d) throws NotStrictlyPositiveException {
        return (long) new PoissonDistribution(getRandomGenerator(), d, 1.0E-12d, PoissonDistribution.DEFAULT_MAX_ITERATIONS).sample();
    }

    public double nextGaussian(double d, double d2) throws NotStrictlyPositiveException {
        if (d2 > 0.0d) {
            return (d2 * getRandomGenerator().nextGaussian()) + d;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.STANDARD_DEVIATION, Double.valueOf(d2));
    }

    public double nextExponential(double d) throws NotStrictlyPositiveException {
        return new ExponentialDistribution(getRandomGenerator(), d, 1.0E-9d).sample();
    }

    public double nextGamma(double d, double d2) throws NotStrictlyPositiveException {
        return new GammaDistribution(getRandomGenerator(), d, d2, 1.0E-9d).sample();
    }

    public int nextHypergeometric(int i, int i2, int i3) throws NotPositiveException, NotStrictlyPositiveException, NumberIsTooLargeException {
        return new HypergeometricDistribution(getRandomGenerator(), i, i2, i3).sample();
    }

    public int nextPascal(int i, double d) throws NotStrictlyPositiveException, OutOfRangeException {
        return new PascalDistribution(getRandomGenerator(), i, d).sample();
    }

    public double nextT(double d) throws NotStrictlyPositiveException {
        return new TDistribution(getRandomGenerator(), d, 1.0E-9d).sample();
    }

    public double nextWeibull(double d, double d2) throws NotStrictlyPositiveException {
        return new WeibullDistribution(getRandomGenerator(), d, d2, 1.0E-9d).sample();
    }

    public int nextZipf(int i, double d) throws NotStrictlyPositiveException {
        return new ZipfDistribution(getRandomGenerator(), i, d).sample();
    }

    public double nextBeta(double d, double d2) {
        return new BetaDistribution(getRandomGenerator(), d, d2, 1.0E-9d).sample();
    }

    public int nextBinomial(int i, double d) {
        return new BinomialDistribution(getRandomGenerator(), i, d).sample();
    }

    public double nextCauchy(double d, double d2) {
        return new CauchyDistribution(getRandomGenerator(), d, d2, 1.0E-9d).sample();
    }

    public double nextChiSquare(double d) {
        return new ChiSquaredDistribution(getRandomGenerator(), d, 1.0E-9d).sample();
    }

    public double nextF(double d, double d2) throws NotStrictlyPositiveException {
        return new FDistribution(getRandomGenerator(), d, d2, 1.0E-9d).sample();
    }

    public double nextUniform(double d, double d2) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        return nextUniform(d, d2, false);
    }

    public double nextUniform(double d, double d2, boolean z) throws NumberIsTooLargeException, NotFiniteNumberException, NotANumberException {
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.LOWER_BOUND_NOT_BELOW_UPPER_BOUND, Double.valueOf(d), Double.valueOf(d2), false);
        } else if (Double.isInfinite(d)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, Double.valueOf(d), new Object[0]);
        } else if (Double.isInfinite(d2)) {
            throw new NotFiniteNumberException(LocalizedFormats.INFINITE_BOUND, Double.valueOf(d2), new Object[0]);
        } else if (Double.isNaN(d) || Double.isNaN(d2)) {
            throw new NotANumberException();
        } else {
            RandomGenerator randomGenerator = getRandomGenerator();
            double nextDouble = randomGenerator.nextDouble();
            while (!z && nextDouble <= 0.0d) {
                nextDouble = randomGenerator.nextDouble();
            }
            return (d2 * nextDouble) + ((1.0d - nextDouble) * d);
        }
    }

    public int[] nextPermutation(int i, int i2) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        if (i2 > i) {
            throw new NumberIsTooLargeException(LocalizedFormats.PERMUTATION_EXCEEDS_N, Integer.valueOf(i2), Integer.valueOf(i), true);
        } else if (i2 > 0) {
            int[] natural = MathArrays.natural(i);
            MathArrays.shuffle(natural, getRandomGenerator());
            return MathArrays.copyOf(natural, i2);
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.PERMUTATION_SIZE, Integer.valueOf(i2));
        }
    }

    public Object[] nextSample(Collection<?> collection, int i) throws NumberIsTooLargeException, NotStrictlyPositiveException {
        int size = collection.size();
        if (i > size) {
            throw new NumberIsTooLargeException(LocalizedFormats.SAMPLE_SIZE_EXCEEDS_COLLECTION_SIZE, Integer.valueOf(i), Integer.valueOf(size), true);
        } else if (i > 0) {
            Object[] array = collection.toArray();
            int[] nextPermutation = nextPermutation(size, i);
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = array[nextPermutation[i2]];
            }
            return objArr;
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
        }
    }

    public void reSeed(long j) {
        getRandomGenerator().setSeed(j);
    }

    public void reSeedSecure() {
        getSecRan().setSeed(System.currentTimeMillis());
    }

    public void reSeedSecure(long j) {
        getSecRan().setSeed(j);
    }

    public void reSeed() {
        getRandomGenerator().setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    public void setSecureAlgorithm(String str, String str2) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.secRand = RandomGeneratorFactory.createRandomGenerator(SecureRandom.getInstance(str, str2));
    }

    public RandomGenerator getRandomGenerator() {
        if (this.rand == null) {
            initRan();
        }
        return this.rand;
    }

    private void initRan() {
        this.rand = new Well19937c(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    private RandomGenerator getSecRan() {
        if (this.secRand == null) {
            RandomGenerator createRandomGenerator = RandomGeneratorFactory.createRandomGenerator(new SecureRandom());
            this.secRand = createRandomGenerator;
            createRandomGenerator.setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
        }
        return this.secRand;
    }
}
