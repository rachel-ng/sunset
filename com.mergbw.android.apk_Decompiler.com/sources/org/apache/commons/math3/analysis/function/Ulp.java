package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.util.FastMath;

public class Ulp implements UnivariateFunction {
    public double value(double d) {
        return FastMath.ulp(d);
    }
}
