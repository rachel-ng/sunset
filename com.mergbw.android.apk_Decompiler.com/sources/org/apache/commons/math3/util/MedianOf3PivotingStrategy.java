package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public class MedianOf3PivotingStrategy implements PivotingStrategyInterface, Serializable {
    private static final long serialVersionUID = 20140713;

    public int pivotIndex(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        MathArrays.verifyValues(dArr, i, i2 - i);
        int i3 = i2 - 1;
        int i4 = ((i3 - i) / 2) + i;
        double d = dArr[i];
        double d2 = dArr[i4];
        double d3 = dArr[i3];
        if (d < d2) {
            if (d2 < d3) {
                return i4;
            }
            return d < d3 ? i3 : i;
        } else if (d < d3) {
            return i;
        } else {
            return d2 < d3 ? i3 : i4;
        }
    }
}
