package org.apache.commons.math3.ode.nonstiff;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.ode.AbstractIntegrator;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.sampling.AbstractStepInterpolator;

abstract class RungeKuttaStepInterpolator extends AbstractStepInterpolator {
    protected AbstractIntegrator integrator;
    protected double[] previousState;
    protected double[][] yDotK;

    protected RungeKuttaStepInterpolator() {
        this.previousState = null;
        double[][] dArr = null;
        this.yDotK = null;
        this.integrator = null;
    }

    public RungeKuttaStepInterpolator(RungeKuttaStepInterpolator rungeKuttaStepInterpolator) {
        super(rungeKuttaStepInterpolator);
        if (rungeKuttaStepInterpolator.currentState != null) {
            this.previousState = (double[]) rungeKuttaStepInterpolator.previousState.clone();
            this.yDotK = new double[rungeKuttaStepInterpolator.yDotK.length][];
            int i = 0;
            while (true) {
                double[][] dArr = rungeKuttaStepInterpolator.yDotK;
                if (i >= dArr.length) {
                    break;
                }
                this.yDotK[i] = (double[]) dArr[i].clone();
                i++;
            }
        } else {
            this.previousState = null;
            double[][] dArr2 = null;
            this.yDotK = null;
        }
        this.integrator = null;
    }

    public void reinitialize(AbstractIntegrator abstractIntegrator, double[] dArr, double[][] dArr2, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        reinitialize(dArr, z, equationsMapper, equationsMapperArr);
        this.previousState = null;
        this.yDotK = dArr2;
        this.integrator = abstractIntegrator;
    }

    public void shift() {
        this.previousState = (double[]) this.currentState.clone();
        super.shift();
    }

    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        writeBaseExternal(objectOutput);
        int i = -1;
        int length = this.currentState == null ? -1 : this.currentState.length;
        for (int i2 = 0; i2 < length; i2++) {
            objectOutput.writeDouble(this.previousState[i2]);
        }
        double[][] dArr = this.yDotK;
        if (dArr != null) {
            i = dArr.length;
        }
        objectOutput.writeInt(i);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < length; i4++) {
                objectOutput.writeDouble(this.yDotK[i3][i4]);
            }
        }
    }

    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        double[][] dArr;
        double readBaseExternal = readBaseExternal(objectInput);
        int length = this.currentState == null ? -1 : this.currentState.length;
        if (length < 0) {
            this.previousState = null;
        } else {
            this.previousState = new double[length];
            for (int i = 0; i < length; i++) {
                this.previousState[i] = objectInput.readDouble();
            }
        }
        int readInt = objectInput.readInt();
        if (readInt < 0) {
            double[][] dArr2 = null;
            dArr = null;
        } else {
            dArr = new double[readInt][];
        }
        this.yDotK = dArr;
        for (int i2 = 0; i2 < readInt; i2++) {
            this.yDotK[i2] = length < 0 ? null : new double[length];
            for (int i3 = 0; i3 < length; i3++) {
                this.yDotK[i2][i3] = objectInput.readDouble();
            }
        }
        this.integrator = null;
        if (this.currentState != null) {
            setInterpolatedTime(readBaseExternal);
        } else {
            this.interpolatedTime = readBaseExternal;
        }
    }
}
