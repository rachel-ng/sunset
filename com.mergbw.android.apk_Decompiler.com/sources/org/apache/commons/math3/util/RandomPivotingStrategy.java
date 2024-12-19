package org.apache.commons.math3.util;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.random.RandomGenerator;

public class RandomPivotingStrategy implements PivotingStrategyInterface, Serializable {
    private static final long serialVersionUID = 20140713;
    private final RandomGenerator random;

    public RandomPivotingStrategy(RandomGenerator randomGenerator) {
        this.random = randomGenerator;
    }

    public int pivotIndex(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        int i3 = i2 - i;
        MathArrays.verifyValues(dArr, i, i3);
        return i + this.random.nextInt(i3 - 1);
    }
}
