package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

public abstract class BitsStreamGenerator implements RandomGenerator, Serializable {
    private static final long serialVersionUID = 20130104;
    private double nextGaussian = Double.NaN;

    /* access modifiers changed from: protected */
    public abstract int next(int i);

    public abstract void setSeed(int i);

    public abstract void setSeed(long j);

    public abstract void setSeed(int[] iArr);

    public boolean nextBoolean() {
        return next(1) != 0;
    }

    public void nextBytes(byte[] bArr) {
        int length = bArr.length - 3;
        int i = 0;
        while (i < length) {
            int next = next(32);
            bArr[i] = (byte) (next & 255);
            bArr[i + 1] = (byte) ((next >> 8) & 255);
            bArr[i + 2] = (byte) ((next >> 16) & 255);
            bArr[i + 3] = (byte) ((next >> 24) & 255);
            i += 4;
        }
        int next2 = next(32);
        while (i < bArr.length) {
            bArr[i] = (byte) (next2 & 255);
            next2 >>= 8;
            i++;
        }
    }

    public double nextDouble() {
        return ((double) ((((long) next(26)) << 26) | ((long) next(26)))) * 2.220446049250313E-16d;
    }

    public float nextFloat() {
        return ((float) next(23)) * 1.1920929E-7f;
    }

    public double nextGaussian() {
        if (Double.isNaN(this.nextGaussian)) {
            double nextDouble = nextDouble() * 6.283185307179586d;
            double sqrt = FastMath.sqrt(FastMath.log(nextDouble()) * -2.0d);
            double cos = FastMath.cos(nextDouble) * sqrt;
            this.nextGaussian = sqrt * FastMath.sin(nextDouble);
            return cos;
        }
        double d = this.nextGaussian;
        this.nextGaussian = Double.NaN;
        return d;
    }

    public int nextInt() {
        return next(32);
    }

    public int nextInt(int i) throws IllegalArgumentException {
        int next;
        int i2;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) next(31))) >> 31);
        } else {
            do {
                next = next(31);
                i2 = next % i;
            } while ((next - i2) + (i - 1) < 0);
            return i2;
        }
    }

    public long nextLong() {
        return (((long) next(32)) << 32) | (((long) next(32)) & 4294967295L);
    }

    public long nextLong(long j) throws IllegalArgumentException {
        long next;
        long j2;
        if (j > 0) {
            do {
                next = (((long) next(31)) << 32) | (((long) next(32)) & 4294967295L);
                j2 = next % j;
            } while ((next - j2) + (j - 1) < 0);
            return j2;
        }
        throw new NotStrictlyPositiveException(Long.valueOf(j));
    }

    public void clear() {
        this.nextGaussian = Double.NaN;
    }
}
