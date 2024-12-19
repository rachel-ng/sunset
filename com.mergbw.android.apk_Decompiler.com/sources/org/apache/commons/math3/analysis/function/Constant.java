package org.apache.commons.math3.analysis.function;

import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;

public class Constant implements UnivariateDifferentiableFunction, DifferentiableUnivariateFunction {

    /* renamed from: c  reason: collision with root package name */
    private final double f59c;

    public Constant(double d) {
        this.f59c = d;
    }

    public double value(double d) {
        return this.f59c;
    }

    @Deprecated
    public DifferentiableUnivariateFunction derivative() {
        return new Constant(0.0d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        return new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.f59c);
    }
}
