package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class ContinuedFraction {
    private static final double DEFAULT_EPSILON = 1.0E-8d;

    /* access modifiers changed from: protected */
    public abstract double getA(int i, double d);

    /* access modifiers changed from: protected */
    public abstract double getB(int i, double d);

    protected ContinuedFraction() {
    }

    public double evaluate(double d) throws ConvergenceException {
        return evaluate(d, 1.0E-8d, Integer.MAX_VALUE);
    }

    public double evaluate(double d, double d2) throws ConvergenceException {
        return evaluate(d, d2, Integer.MAX_VALUE);
    }

    public double evaluate(double d, int i) throws ConvergenceException, MaxCountExceededException {
        return evaluate(d, 1.0E-8d, i);
    }

    public double evaluate(double d, double d2, int i) throws ConvergenceException, MaxCountExceededException {
        double d3;
        double d4 = d;
        int i2 = i;
        double a2 = getA(0, d4);
        if (Precision.equals(a2, 0.0d, 1.0E-50d)) {
            a2 = 1.0E-50d;
        }
        double d5 = 0.0d;
        int i3 = 1;
        double d6 = d3;
        while (i3 < i2) {
            double a3 = getA(i3, d4);
            double b2 = getB(i3, d4);
            double d7 = (d5 * b2) + a3;
            if (Precision.equals(d7, 0.0d, 1.0E-50d)) {
                d7 = 1.0E-50d;
            }
            double d8 = a3 + (b2 / d3);
            d3 = Precision.equals(d8, 0.0d, 1.0E-50d) ? 1.0E-50d : d8;
            d5 = 1.0d / d7;
            double d9 = d3 * d5;
            d6 *= d9;
            if (Double.isInfinite(d6)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, Double.valueOf(d));
            } else if (Double.isNaN(d6)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, Double.valueOf(d));
            } else if (FastMath.abs(d9 - 1.0d) < d2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 < i2) {
            return d6;
        }
        throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, Integer.valueOf(i), Double.valueOf(d));
    }
}
