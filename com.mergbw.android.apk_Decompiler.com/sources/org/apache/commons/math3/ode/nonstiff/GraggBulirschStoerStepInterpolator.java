package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

class GraggBulirschStoerStepInterpolator extends AbstractStepInterpolator {
    private static final long serialVersionUID = 20110928;
    private int currentDegree;
    private double[] errfac;
    private double[][] polynomials;
    private double[] y0Dot;
    private double[] y1;
    private double[] y1Dot;
    private double[][] yMidDots;

    public GraggBulirschStoerStepInterpolator() {
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        double[][] dArr = null;
        this.yMidDots = null;
        resetTables(-1);
    }

    public GraggBulirschStoerStepInterpolator(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4, double[][] dArr5, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        super(dArr, z, equationsMapper, equationsMapperArr);
        this.y0Dot = dArr2;
        this.y1 = dArr3;
        this.y1Dot = dArr4;
        this.yMidDots = dArr5;
        resetTables(dArr5.length + 4);
    }

    public GraggBulirschStoerStepInterpolator(GraggBulirschStoerStepInterpolator graggBulirschStoerStepInterpolator) {
        super(graggBulirschStoerStepInterpolator);
        int length = this.currentState.length;
        this.y0Dot = null;
        this.y1 = null;
        this.y1Dot = null;
        double[][] dArr = null;
        this.yMidDots = null;
        if (graggBulirschStoerStepInterpolator.polynomials == null) {
            this.polynomials = null;
            this.currentDegree = -1;
            return;
        }
        resetTables(graggBulirschStoerStepInterpolator.currentDegree);
        int i = 0;
        while (true) {
            double[][] dArr2 = this.polynomials;
            if (i < dArr2.length) {
                double[] dArr3 = new double[length];
                dArr2[i] = dArr3;
                System.arraycopy(graggBulirschStoerStepInterpolator.polynomials[i], 0, dArr3, 0, length);
                i++;
            } else {
                this.currentDegree = graggBulirschStoerStepInterpolator.currentDegree;
                return;
            }
        }
    }

    private void resetTables(int i) {
        if (i < 0) {
            double[][] dArr = null;
            this.polynomials = null;
            this.errfac = null;
            this.currentDegree = -1;
            return;
        }
        int i2 = i + 1;
        double[][] dArr2 = new double[i2][];
        double[][] dArr3 = this.polynomials;
        if (dArr3 != null) {
            System.arraycopy(dArr3, 0, dArr2, 0, dArr3.length);
            for (int length = this.polynomials.length; length < i2; length++) {
                dArr2[length] = new double[this.currentState.length];
            }
        } else {
            for (int i3 = 0; i3 < i2; i3++) {
                dArr2[i3] = new double[this.currentState.length];
            }
        }
        this.polynomials = dArr2;
        if (i > 4) {
            this.errfac = new double[(i - 4)];
            int i4 = 0;
            while (true) {
                double[] dArr4 = this.errfac;
                if (i4 >= dArr4.length) {
                    break;
                }
                int i5 = i4 + 5;
                dArr4[i4] = 1.0d / ((double) (i5 * i5));
                int i6 = i4 + 1;
                double sqrt = FastMath.sqrt(((double) i6) / ((double) i5)) * 0.5d;
                int i7 = 0;
                while (i7 <= i4) {
                    double[] dArr5 = this.errfac;
                    i7++;
                    dArr5[i4] = dArr5[i4] * (sqrt / ((double) i7));
                }
                i4 = i6;
            }
        } else {
            this.errfac = null;
        }
        this.currentDegree = 0;
    }

    /* access modifiers changed from: protected */
    public StepInterpolator doCopy() {
        return new GraggBulirschStoerStepInterpolator(this);
    }

    public void computeCoefficients(int i, double d) {
        int i2 = i;
        double[][] dArr = this.polynomials;
        if (dArr == null || dArr.length <= i2 + 4) {
            resetTables(i2 + 4);
        }
        this.currentDegree = i2 + 4;
        char c2 = 0;
        int i3 = 0;
        while (i3 < this.currentState.length) {
            double d2 = d * this.y0Dot[i3];
            double d3 = d * this.y1Dot[i3];
            double d4 = this.y1[i3] - this.currentState[i3];
            double d5 = d4 - d3;
            double d6 = d2 - d4;
            this.polynomials[c2][i3] = this.currentState[i3];
            double[][] dArr2 = this.polynomials;
            dArr2[1][i3] = d4;
            dArr2[2][i3] = d5;
            dArr2[3][i3] = d6;
            if (i2 >= 0) {
                double[][] dArr3 = this.polynomials;
                double[] dArr4 = dArr3[4];
                double[][] dArr5 = this.yMidDots;
                dArr4[i3] = (dArr5[c2][i3] - (((this.currentState[i3] + this.y1[i3]) * 0.5d) + ((d5 + d6) * 0.125d))) * 16.0d;
                if (i2 > 0) {
                    double[] dArr6 = dArr3[5];
                    dArr6[i3] = (dArr5[1][i3] - (d4 + ((d5 - d6) * 0.25d))) * 16.0d;
                    if (i2 > 1) {
                        dArr3[6][i3] = ((dArr5[2][i3] - (d3 - d2)) + dArr4[i3]) * 16.0d;
                        if (i2 > 2) {
                            dArr3[7][i3] = ((dArr5[3][i3] - ((d6 - d5) * 6.0d)) + (dArr6[i3] * 3.0d)) * 16.0d;
                            for (int i4 = 4; i4 <= i2; i4++) {
                                double d7 = ((double) i4) * 0.5d * ((double) (i4 - 1));
                                double[][] dArr7 = this.polynomials;
                                dArr7[i4 + 4][i3] = ((this.yMidDots[i4][i3] + (d7 * dArr7[i4 + 2][i3])) - ((((2.0d * d7) * ((double) (i4 - 2))) * ((double) (i4 - 3))) * dArr7[i4][i3])) * 16.0d;
                            }
                        }
                    }
                }
                i3++;
                c2 = 0;
            } else {
                return;
            }
        }
    }

    public double estimateError(double[] dArr) {
        double d = 0.0d;
        if (this.currentDegree < 5) {
            return 0.0d;
        }
        for (int i = 0; i < dArr.length; i++) {
            double d2 = this.polynomials[this.currentDegree][i] / dArr[i];
            d += d2 * d2;
        }
        return FastMath.sqrt(d / ((double) dArr.length)) * this.errfac[this.currentDegree - 5];
    }

    /* access modifiers changed from: protected */
    public void computeInterpolatedStateAndDerivatives(double d, double d2) {
        double d3;
        double d4;
        int length = this.currentState.length;
        double d5 = 1.0d - d;
        double d6 = d - 0.5d;
        double d7 = d * d5;
        double d8 = d7 * d7;
        double d9 = d7 * 2.0d * (1.0d - (d * 2.0d));
        double d10 = 1.0d / this.h;
        double d11 = 3.0d * d;
        double d12 = ((2.0d - d11) * d) / this.h;
        double d13 = (((d11 - 4.0d) * d) + 1.0d) / this.h;
        char c2 = 0;
        int i = 0;
        while (true) {
            double d14 = 0.0d;
            if (i >= length) {
                break;
            }
            int i2 = length;
            double[][] dArr = this.polynomials;
            double d15 = dArr[c2][i];
            double d16 = dArr[1][i];
            double d17 = dArr[2][i];
            double d18 = dArr[3][i];
            this.interpolatedState[i] = d15 + ((d16 + (((d17 * d) + (d18 * d5)) * d5)) * d);
            this.interpolatedDerivatives[i] = (d16 * d10) + (d17 * d12) + (d18 * d13);
            int i3 = this.currentDegree;
            if (i3 > 3) {
                double d19 = this.polynomials[i3][i];
                int i4 = i3 - 1;
                while (i4 > 3) {
                    double d20 = d5;
                    double d21 = 1.0d / ((double) (i4 - 3));
                    d14 = ((d14 * d6) + d19) * d21;
                    d19 = this.polynomials[i4][i] + (d19 * d21 * d6);
                    i4--;
                    d5 = d20;
                }
                d4 = d5;
                double[] dArr2 = this.interpolatedState;
                dArr2[i] = dArr2[i] + (d8 * d19);
                double[] dArr3 = this.interpolatedDerivatives;
                d3 = d6;
                dArr3[i] = dArr3[i] + (((d14 * d8) + (d19 * d9)) / this.h);
            } else {
                d4 = d5;
                d3 = d6;
            }
            i++;
            d6 = d3;
            length = i2;
            d5 = d4;
            c2 = 0;
        }
        int i5 = length;
        if (this.h == 0.0d) {
            System.arraycopy(this.yMidDots[1], 0, this.interpolatedDerivatives, 0, i5);
        }
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        int length = this.currentState == null ? -1 : this.currentState.length;
        writeBaseExternal(objectOutput);
        objectOutput.writeInt(this.currentDegree);
        for (int i = 0; i <= this.currentDegree; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                objectOutput.writeDouble(this.polynomials[i][i2]);
            }
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double readBaseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        int readInt = objectInput.readInt();
        resetTables(readInt);
        this.currentDegree = readInt;
        for (int i = 0; i <= this.currentDegree; i++) {
            for (int i2 = 0; i2 < length; i2++) {
                this.polynomials[i][i2] = objectInput.readDouble();
            }
        }
        setInterpolatedTime(readBaseExternal);
    }
}
