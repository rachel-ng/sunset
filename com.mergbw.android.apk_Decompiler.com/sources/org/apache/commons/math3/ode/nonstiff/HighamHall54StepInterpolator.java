package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.ode.sampling.StepInterpolator;

class HighamHall54StepInterpolator extends RungeKuttaStepInterpolator {
    private static final long serialVersionUID = 20111120;

    public HighamHall54StepInterpolator() {
    }

    public HighamHall54StepInterpolator(HighamHall54StepInterpolator highamHall54StepInterpolator) {
        super(highamHall54StepInterpolator);
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new HighamHall54StepInterpolator(this);
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3 = (((d * (16.0d - (10.0d * d))) - 0.5625d) * d) + 1.0d;
        double d4 = ((((67.5d * d) - 0.04925537109375d) * d) + 28.6875d) * d;
        double d5 = ((d * (152.0d - (120.0d * d))) - 0.1015625d) * d;
        double d6 = ((((62.5d * d) - 0.05560302734375d) * d) + 23.4375d) * d;
        double d7 = 5.0d * d;
        double d8 = (d7 / 8.0d) * ((d * 2.0d) - 1.0d);
        if (this.previousState == null || d > 0.5d) {
            double d9 = d8;
            double d10 = d * d;
            double d11 = this.h * ((d * ((((d * (((-5.0d * d) / 2.0d) + 5.333333333333333d)) - 1.125d) * d) + 1.0d)) - 53.333333333333336d);
            double d12 = d6;
            double d13 = this.h * (((((((d * 135.0d) / 8.0d) - 0.1376953125d) * d) + 14.34375d) * d10) - 5.25d);
            double d14 = d5;
            double d15 = this.h * ((((((-30.0d * d) + 50.666666666666664d) * d) - 0.203125d) * d10) + 1.3333333333333333d);
            double d16 = d4;
            double d17 = this.h * (((((((125.0d * d) / 8.0d) - 0.17154947916666666d) * d) + 11.71875d) * d10) - 3.3958333333333335d);
            double d18 = d3;
            double d19 = this.h * ((d10 * ((d7 / 12.0d) - 14.0d)) - 42.666666666666664d);
            int i = 0;
            while (i < this.interpolatedState.length) {
                double d20 = this.yDotK[0][i];
                double d21 = this.yDotK[2][i];
                double d22 = this.yDotK[3][i];
                double d23 = this.yDotK[4][i];
                double d24 = this.yDotK[5][i];
                double d25 = d12;
                this.interpolatedState[i] = this.currentState[i] + (d11 * d20) + (d13 * d21) + (d15 * d22) + (d17 * d23) + (d19 * d24);
                this.interpolatedDerivatives[i] = (d20 * d18) + (d21 * d16) + (d14 * d22) + (d25 * d23) + (d9 * d24);
                i++;
                d12 = d25;
            }
            return;
        }
        double d26 = d8;
        double d27 = this.h * d;
        double d28 = ((((d * (5.333333333333333d - (2.5d * d))) - 1.125d) * d) + 1.0d) * d27;
        double d29 = (((((d * 135.0d) / 8.0d) - 0.1376953125d) * d) + 14.34375d) * d * d27;
        double d30 = ((((-30.0d * d) + 50.666666666666664d) * d) - 0.203125d) * d * d27;
        double d31 = (((((125.0d * d) / 8.0d) - 0.17154947916666666d) * d) + 11.71875d) * d * d27;
        double d32 = d27 * ((d7 / 12.0d) - 14.0d) * d;
        for (int i2 = 0; i2 < this.interpolatedState.length; i2++) {
            double d33 = this.yDotK[0][i2];
            double d34 = this.yDotK[2][i2];
            double d35 = this.yDotK[3][i2];
            double d36 = this.yDotK[4][i2];
            double d37 = this.yDotK[5][i2];
            this.interpolatedState[i2] = this.previousState[i2] + (d28 * d33) + (d29 * d34) + (d30 * d35) + (d31 * d36) + (d32 * d37);
            this.interpolatedDerivatives[i2] = (d33 * d3) + (d34 * d4) + (d35 * d5) + (d36 * d6) + (d26 * d37);
        }
    }
}
