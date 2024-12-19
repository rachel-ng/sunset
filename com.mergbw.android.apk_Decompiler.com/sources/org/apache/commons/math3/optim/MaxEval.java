package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;

public class MaxEval implements OptimizationData {
    private final int maxEval;

    public MaxEval(int i) {
        if (i > 0) {
            this.maxEval = i;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public int getMaxEval() {
        return this.maxEval;
    }

    public static MaxEval unlimited() {
        return new MaxEval(Integer.MAX_VALUE);
    }
}
