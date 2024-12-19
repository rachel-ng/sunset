package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.NullArgumentException;

public class KthSelector implements Serializable {
    private static final int MIN_SELECT_SIZE = 15;
    private static final long serialVersionUID = 20140713;
    private final PivotingStrategyInterface pivotingStrategy;

    public KthSelector() {
        this.pivotingStrategy = new MedianOf3PivotingStrategy();
    }

    public KthSelector(PivotingStrategyInterface pivotingStrategyInterface) throws NullArgumentException {
        MathUtils.checkNotNull(pivotingStrategyInterface);
        this.pivotingStrategy = pivotingStrategyInterface;
    }

    public PivotingStrategyInterface getPivotingStrategy() {
        return this.pivotingStrategy;
    }

    public double select(double[] dArr, int[] iArr, int i) {
        int i2;
        int length = dArr.length;
        int i3 = 0;
        boolean z = iArr != null;
        int i4 = 0;
        while (length - i3 > 15) {
            if (!z || i4 >= iArr.length || (i2 = iArr[i4]) < 0) {
                i2 = partition(dArr, i3, length, this.pivotingStrategy.pivotIndex(dArr, i3, length));
                if (z && i4 < iArr.length) {
                    iArr[i4] = i2;
                }
            }
            if (i == i2) {
                return dArr[i];
            }
            if (i < i2) {
                i4 = FastMath.min((i4 * 2) + 1, z ? iArr.length : i2);
                length = i2;
            } else {
                int i5 = i2 + 1;
                i4 = FastMath.min((i4 * 2) + 2, z ? iArr.length : length);
                i3 = i5;
            }
        }
        Arrays.sort(dArr, i3, length);
        return dArr[i];
    }

    private int partition(double[] dArr, int i, int i2, int i3) {
        double d = dArr[i3];
        dArr[i3] = dArr[i];
        int i4 = i + 1;
        int i5 = i2 - 1;
        while (i4 < i5) {
            while (i4 < i5 && dArr[i5] > d) {
                i5--;
            }
            while (i4 < i5 && dArr[i4] < d) {
                i4++;
            }
            if (i4 < i5) {
                double d2 = dArr[i4];
                dArr[i4] = dArr[i5];
                dArr[i5] = d2;
                i5--;
                i4++;
            }
        }
        if (i4 >= i2 || dArr[i4] > d) {
            i4--;
        }
        dArr[i] = dArr[i4];
        dArr[i4] = d;
        return i4;
    }
}
