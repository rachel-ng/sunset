package org.apache.commons.math3.random;

import androidx.appcompat.app.AppCompatDelegate;
import com.alibaba.fastjson.asm.Opcodes;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathUtils;

public class HaltonSequenceGenerator implements RandomVectorGenerator {
    private static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 113, 127, 131, 137, 139, Opcodes.FCMPL, Opcodes.DCMPL, 157, Opcodes.IF_ICMPGT, Opcodes.GOTO, 173};
    private static final int[] WEIGHTS = {1, 2, 3, 3, 8, 11, 12, 14, 7, 18, 12, 13, 17, 18, 29, 14, 18, 43, 41, 44, 40, 30, 47, 65, 71, 28, 40, 60, 79, 89, 56, 50, 52, 61, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 56, 66, 63, 60, 66};
    private final int[] base;
    private int count;
    private final int dimension;
    private final int[] weight;

    public HaltonSequenceGenerator(int i) throws OutOfRangeException {
        this(i, PRIMES, WEIGHTS);
    }

    public HaltonSequenceGenerator(int i, int[] iArr, int[] iArr2) throws NullArgumentException, OutOfRangeException, DimensionMismatchException {
        int[] iArr3;
        this.count = 0;
        MathUtils.checkNotNull(iArr);
        if (i < 1 || i > iArr.length) {
            throw new OutOfRangeException(Integer.valueOf(i), 1, Integer.valueOf(PRIMES.length));
        } else if (iArr2 == null || iArr2.length == iArr.length) {
            this.dimension = i;
            this.base = (int[]) iArr.clone();
            if (iArr2 == null) {
                iArr3 = null;
            } else {
                iArr3 = (int[]) iArr2.clone();
            }
            this.weight = iArr3;
            this.count = 0;
        } else {
            throw new DimensionMismatchException(iArr2.length, iArr.length);
        }
    }

    public double[] nextVector() {
        double[] dArr = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++) {
            int i2 = this.count;
            double d = (double) this.base[i];
            double d2 = 1.0d;
            while (true) {
                d2 /= d;
                if (i2 <= 0) {
                    break;
                }
                int i3 = this.base[i];
                dArr[i] = dArr[i] + (((double) scramble(i, 0, i3, i2 % i3)) * d2);
                int i4 = this.base[i];
                i2 /= i4;
                d = (double) i4;
            }
        }
        this.count++;
        return dArr;
    }

    /* access modifiers changed from: protected */
    public int scramble(int i, int i2, int i3, int i4) {
        int[] iArr = this.weight;
        return iArr != null ? (iArr[i] * i4) % i3 : i4;
    }

    public double[] skipTo(int i) throws NotPositiveException {
        this.count = i;
        return nextVector();
    }

    public int getNextIndex() {
        return this.count;
    }
}
