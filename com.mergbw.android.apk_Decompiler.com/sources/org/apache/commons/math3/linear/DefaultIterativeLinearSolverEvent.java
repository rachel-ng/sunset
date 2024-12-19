package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;

public class DefaultIterativeLinearSolverEvent extends IterativeLinearSolverEvent {
    private static final long serialVersionUID = 20120129;

    /* renamed from: b  reason: collision with root package name */
    private final RealVector f75b;
    private final RealVector r;
    private final double rnorm;
    private final RealVector x;

    public DefaultIterativeLinearSolverEvent(Object obj, int i, RealVector realVector, RealVector realVector2, RealVector realVector3, double d) {
        super(obj, i);
        this.x = realVector;
        this.f75b = realVector2;
        this.r = realVector3;
        this.rnorm = d;
    }

    public DefaultIterativeLinearSolverEvent(Object obj, int i, RealVector realVector, RealVector realVector2, double d) {
        super(obj, i);
        this.x = realVector;
        this.f75b = realVector2;
        this.r = null;
        this.rnorm = d;
    }

    public double getNormOfResidual() {
        return this.rnorm;
    }

    public RealVector getResidual() {
        RealVector realVector = this.r;
        if (realVector != null) {
            return realVector;
        }
        throw new MathUnsupportedOperationException();
    }

    public RealVector getRightHandSideVector() {
        return this.f75b;
    }

    public RealVector getSolution() {
        return this.x;
    }

    public boolean providesResidual() {
        return this.r != null;
    }
}
