package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.util.FastMath;

public class CanberraDistance implements DistanceMeasure {
    private static final long serialVersionUID = -6972277381587032228L;

    public double compute(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            double abs = FastMath.abs(dArr[i] - dArr2[i]);
            double abs2 = FastMath.abs(dArr[i]) + FastMath.abs(dArr2[i]);
            d += (abs == 0.0d && abs2 == 0.0d) ? 0.0d : abs / abs2;
        }
        return d;
    }
}
