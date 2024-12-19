package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;

public class CentralPivotingStrategy implements PivotingStrategyInterface, Serializable {
    private static final long serialVersionUID = 20140713;

    public int pivotIndex(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        int i3 = i2 - i;
        MathArrays.verifyValues(dArr, i, i3);
        return i + (i3 / 2);
    }
}
