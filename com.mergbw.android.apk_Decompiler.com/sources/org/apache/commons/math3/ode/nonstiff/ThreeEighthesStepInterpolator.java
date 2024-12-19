package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class ThreeEighthesStepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public ThreeEighthesStepInterpolator() {
    }

    public ThreeEighthesStepInterpolator(ThreeEighthesStepInterpolator threeEighthesStepInterpolator) {
        super(threeEighthesStepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new ThreeEighthesStepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = 0.75d * d;
        double d4 = 4.0d * d;
        double d5 = ((d4 - 5.0d) * d3) + 1.0d;
        double d6 = (5.0d - (6.0d * d)) * d3;
        double d7 = ((d * 2.0d) - 1.0d) * d3;
        if (this.previousState == null || d > 0.5d) {
            double d8 = d6;
            double d9 = d2 / 8.0d;
            double d10 = d4 * d;
            double d11 = ((1.0d - (7.0d * d)) + (2.0d * d10)) * d9;
            double d12 = 3.0d * d9;
            double d13 = d + 1.0d;
            double d14 = (d13 - d10) * d12;
            double d15 = d12 * d13;
            double d16 = d9 * (d13 + d10);
            for (int i = 0; i < this.interpolatedState.length; i++) {
                double d17 = this.yDotK[0][i];
                double d18 = this.yDotK[1][i];
                double d19 = this.yDotK[2][i];
                double d20 = this.yDotK[3][i];
                this.interpolatedState[i] = (((this.currentState[i] - (d11 * d17)) - (d14 * d18)) - (d15 * d19)) - (d16 * d20);
                this.interpolatedDerivatives[i] = (d17 * d5) + (d18 * d8) + (d19 * d3) + (d20 * d7);
            }
            return;
        }
        double d21 = d6;
        double d22 = (this.h * d) / 8.0d;
        double d23 = d4 * d;
        double d24 = ((8.0d - (15.0d * d)) + (2.0d * d23)) * d22;
        double d25 = 3.0d * d22;
        double d26 = ((5.0d * d) - d23) * d25;
        double d27 = d25 * d;
        double d28 = d22 * ((-3.0d * d) + d23);
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d29 = this.yDotK[0][i2];
            double d30 = this.yDotK[1][i2];
            double d31 = this.yDotK[2][i2];
            double d32 = this.yDotK[3][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d24 * d29) + (d26 * d30) + (d27 * d31) + (d28 * d32);
            this.interpolatedDerivatives[i2] = (d29 * d5) + (d30 * d21) + (d31 * d3) + (d32 * d7);
        }
    }
}
