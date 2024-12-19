package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.util.FastMath;

public class GillIntegrator extends RungeKuttaIntegrator {
    private static final double[][] STATIC_A;
    private static final double[] STATIC_B = {0.16666666666666666d, (2.0d - FastMath.sqrt(2.0d)) / 6.0d, (FastMath.sqrt(2.0d) + 2.0d) / 6.0d, 0.16666666666666666d};
    private static final double[] STATIC_C = {0.5d, 0.5d, 1.0d};

    static {
        double[] dArr = {0.5d};
        STATIC_A = new double[][]{dArr, new double[]{(FastMath.sqrt(2.0d) - 1.0d) / 2.0d, (2.0d - FastMath.sqrt(2.0d)) / 2.0d}, new double[]{0.0d, (-FastMath.sqrt(2.0d)) / 2.0d, (FastMath.sqrt(2.0d) + 2.0d) / 2.0d}};
    }

    public GillIntegrator(double d) {
        super("Gill", STATIC_C, STATIC_A, STATIC_B, new GillStepInterpolator(), d);
    }
}
