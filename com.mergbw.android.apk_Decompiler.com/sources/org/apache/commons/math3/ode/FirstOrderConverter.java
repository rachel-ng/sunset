package org.apache.commons.math3.ode;

public class FirstOrderConverter implements FirstOrderDifferentialEquations {
    private final int dimension;
    private final SecondOrderDifferentialEquations equations;
    private final double[] z;
    private final double[] zDDot;
    private final double[] zDot;

    public FirstOrderConverter(SecondOrderDifferentialEquations secondOrderDifferentialEquations) {
        this.equations = secondOrderDifferentialEquations;
        int dimension2 = secondOrderDifferentialEquations.getDimension();
        this.dimension = dimension2;
        this.z = new double[dimension2];
        this.zDot = new double[dimension2];
        this.zDDot = new double[dimension2];
    }

    public int getDimension() {
        return this.dimension * 2;
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) {
        System.arraycopy(dArr, 0, this.z, 0, this.dimension);
        int i = this.dimension;
        System.arraycopy(dArr, i, this.zDot, 0, i);
        this.equations.computeSecondDerivatives(d, this.z, this.zDot, this.zDDot);
        System.arraycopy(this.zDot, 0, dArr2, 0, this.dimension);
        double[] dArr3 = this.zDDot;
        int i2 = this.dimension;
        System.arraycopy(dArr3, 0, dArr2, i2, i2);
    }
}
