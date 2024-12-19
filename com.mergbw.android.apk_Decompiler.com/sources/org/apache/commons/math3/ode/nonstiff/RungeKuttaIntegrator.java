package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.util.FastMath;

public abstract class RungeKuttaIntegrator extends AbstractIntegrator {

    /* renamed from: a  reason: collision with root package name */
    private final double[][] f82a;

    /* renamed from: b  reason: collision with root package name */
    private final double[] f83b;

    /* renamed from: c  reason: collision with root package name */
    private final double[] f84c;
    private final RungeKuttaStepInterpolator prototype;
    private final double step;

    protected RungeKuttaIntegrator(String str, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d) {
        super(str);
        this.f84c = dArr;
        this.f82a = dArr2;
        this.f83b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        this.step = FastMath.abs(d);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        int i = 0;
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        int length = this.f84c.length;
        int i2 = length + 1;
        double[][] dArr2 = new double[i2][];
        for (int i3 = 0; i3 < i2; i3++) {
            dArr2[i3] = new double[completeState.length];
        }
        double[] dArr3 = (double[]) completeState.clone();
        double[] dArr4 = new double[completeState.length];
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator = (RungeKuttaStepInterpolator) this.prototype.copy();
        RungeKuttaStepInterpolator rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator;
        double[] dArr5 = dArr4;
        double[] dArr6 = dArr3;
        double[][] dArr7 = dArr2;
        rungeKuttaStepInterpolator.reinitialize(this, dArr3, dArr2, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        rungeKuttaStepInterpolator2.storeTime(expandableStatefulODE.getTime());
        this.stepStart = expandableStatefulODE.getTime();
        if (z) {
            double d2 = this.stepStart;
            double d3 = this.step;
            if (d2 + d3 >= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = d3;
            }
        } else {
            double d4 = this.stepStart;
            double d5 = this.step;
            if (d4 - d5 <= d) {
                this.stepSize = d - this.stepStart;
            } else {
                this.stepSize = -d5;
            }
        }
        initIntegration(expandableStatefulODE.getTime(), completeState, d);
        this.isLastStep = false;
        while (true) {
            rungeKuttaStepInterpolator2.shift();
            computeDerivatives(this.stepStart, dArr, dArr7[i]);
            int i4 = 1;
            while (i4 < i2) {
                int i5 = i;
                while (i5 < completeState.length) {
                    int i6 = i4 - 1;
                    double d6 = this.f82a[i6][i] * dArr7[i][i5];
                    for (int i7 = 1; i7 < i4; i7++) {
                        d6 += this.f82a[i6][i7] * dArr7[i7][i5];
                    }
                    dArr6[i5] = dArr[i5] + (this.stepSize * d6);
                    i5++;
                    z = z;
                    i = 0;
                }
                computeDerivatives(this.stepStart + (this.f84c[i4 - 1] * this.stepSize), dArr6, dArr7[i4]);
                i4++;
                ExpandableStatefulODE expandableStatefulODE3 = expandableStatefulODE;
                rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator2;
                z = z;
                i = 0;
            }
            boolean z2 = z;
            double[] dArr8 = dArr6;
            RungeKuttaStepInterpolator rungeKuttaStepInterpolator3 = rungeKuttaStepInterpolator2;
            for (int i8 = 0; i8 < completeState.length; i8++) {
                double d7 = this.f83b[0] * dArr7[0][i8];
                for (int i9 = 1; i9 < i2; i9++) {
                    d7 += this.f83b[i9] * dArr7[i9][i8];
                }
                dArr8[i8] = dArr[i8] + (this.stepSize * d7);
            }
            rungeKuttaStepInterpolator3.storeTime(this.stepStart + this.stepSize);
            System.arraycopy(dArr8, 0, dArr, 0, completeState.length);
            double[] dArr9 = dArr5;
            System.arraycopy(dArr7[length], 0, dArr9, 0, completeState.length);
            this.stepStart = acceptStep(rungeKuttaStepInterpolator3, dArr, dArr9, d);
            if (!this.isLastStep) {
                rungeKuttaStepInterpolator3.storeTime(this.stepStart);
                double d8 = this.stepStart + this.stepSize;
                if (!z2 ? d8 <= d : d8 >= d) {
                    this.stepSize = d - this.stepStart;
                }
            }
            if (this.isLastStep) {
                ExpandableStatefulODE expandableStatefulODE4 = expandableStatefulODE;
                expandableStatefulODE4.setTime(this.stepStart);
                expandableStatefulODE4.setCompleteState(dArr);
                this.stepStart = Double.NaN;
                this.stepSize = Double.NaN;
                return;
            }
            dArr5 = dArr9;
            dArr6 = dArr8;
            rungeKuttaStepInterpolator2 = rungeKuttaStepInterpolator3;
            z = z2;
            ExpandableStatefulODE expandableStatefulODE5 = expandableStatefulODE;
            i = 0;
        }
    }

    public double[] singleStep(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2) {
        FirstOrderDifferentialEquations firstOrderDifferentialEquations2 = firstOrderDifferentialEquations;
        double d3 = d;
        double[] dArr2 = dArr;
        double[] dArr3 = (double[]) dArr.clone();
        int i = 1;
        int length = this.f84c.length + 1;
        double[][] dArr4 = new double[length][];
        for (int i2 = 0; i2 < length; i2++) {
            dArr4[i2] = new double[dArr2.length];
        }
        double[] dArr5 = (double[]) dArr.clone();
        double d4 = d2 - d3;
        firstOrderDifferentialEquations2.computeDerivatives(d3, dArr3, dArr4[0]);
        int i3 = 1;
        while (i3 < length) {
            int i4 = 0;
            while (i4 < dArr2.length) {
                int i5 = i3 - 1;
                double d5 = this.f82a[i5][0] * dArr4[0][i4];
                for (int i6 = i; i6 < i3; i6++) {
                    d5 += this.f82a[i5][i6] * dArr4[i6][i4];
                }
                dArr5[i4] = dArr3[i4] + (d5 * d4);
                i4++;
                i = 1;
            }
            firstOrderDifferentialEquations2.computeDerivatives((this.f84c[i3 - 1] * d4) + d3, dArr5, dArr4[i3]);
            i3++;
            i = 1;
        }
        for (int i7 = 0; i7 < dArr2.length; i7++) {
            double d6 = this.f83b[0] * dArr4[0][i7];
            for (int i8 = 1; i8 < length; i8++) {
                d6 += this.f83b[i8] * dArr4[i8][i7];
            }
            dArr3[i7] = dArr3[i7] + (d6 * d4);
        }
        return dArr3;
    }
}
