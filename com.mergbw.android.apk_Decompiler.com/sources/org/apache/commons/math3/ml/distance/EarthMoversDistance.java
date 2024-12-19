package org.apache.commons.math3.ml.distance;

import org.apache.commons.math3.util.FastMath;

public class EarthMoversDistance implements DistanceMeasure {
    private static final long serialVersionUID = -5406732779747414922L;

    public double compute(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < dArr.length; i++) {
            d2 = (dArr[i] + d2) - dArr2[i];
            d += FastMath.abs(d2);
        }
        return d;
    }
}
