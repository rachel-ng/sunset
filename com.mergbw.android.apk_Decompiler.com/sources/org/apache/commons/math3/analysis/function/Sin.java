package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.util.FastMath;

public class Sin implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {
    public double value(double d) {
        return FastMath.sin(d);
    }

    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Cos();
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return derivativeStructure.sin();
    }
}
