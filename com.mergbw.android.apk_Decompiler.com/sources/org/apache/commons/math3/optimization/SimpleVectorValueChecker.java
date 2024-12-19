package org.apache.commons.math3.optimization;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class SimpleVectorValueChecker extends AbstractConvergenceChecker<PointVectorValuePair> {
    private static final int ITERATION_CHECK_DISABLED = -1;
    private final int maxIterationCount;

    @Deprecated
    public SimpleVectorValueChecker() {
        this.maxIterationCount = -1;
    }

    public SimpleVectorValueChecker(double d, double d2) {
        super(d, d2);
        this.maxIterationCount = -1;
    }

    public SimpleVectorValueChecker(double d, double d2, int i) {
        super(d, d2);
        if (i > 0) {
            this.maxIterationCount = i;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i));
    }

    public boolean converged(int i, PointVectorValuePair pointVectorValuePair, PointVectorValuePair pointVectorValuePair2) {
        int i2 = this.maxIterationCount;
        if (i2 != -1 && i >= i2) {
            return true;
        }
        double[] valueRef = pointVectorValuePair.getValueRef();
        double[] valueRef2 = pointVectorValuePair2.getValueRef();
        for (int i3 = 0; i3 < valueRef.length; i3++) {
            double d = valueRef[i3];
            double d2 = valueRef2[i3];
            double abs = FastMath.abs(d - d2);
            if (abs > FastMath.max(FastMath.abs(d), FastMath.abs(d2)) * getRelativeThreshold() && abs > getAbsoluteThreshold()) {
                return false;
            }
        }
        return true;
    }
}
