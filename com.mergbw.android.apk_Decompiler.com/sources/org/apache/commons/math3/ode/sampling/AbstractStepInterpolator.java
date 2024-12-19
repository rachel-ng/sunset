package org.apache.commons.math3.ode.sampling;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.EquationsMapper;

public abstract class AbstractStepInterpolator implements StepInterpolator {
    protected double[] currentState;
    private boolean dirtyState;
    private boolean finalized;
    private boolean forward;
    private double globalCurrentTime;
    private double globalPreviousTime;
    protected double h;
    protected double[] interpolatedDerivatives;
    protected double[] interpolatedPrimaryDerivatives;
    protected double[] interpolatedPrimaryState;
    protected double[][] interpolatedSecondaryDerivatives;
    protected double[][] interpolatedSecondaryState;
    protected double[] interpolatedState;
    protected double interpolatedTime;
    private EquationsMapper primaryMapper;
    private EquationsMapper[] secondaryMappers;
    private double softCurrentTime;
    private double softPreviousTime;

    /* access modifiers changed from: protected */
    public abstract void computeInterpolatedStateAndDerivatives(double d, double d2) throws MaxCountExceededException;

    /* access modifiers changed from: protected */
    public abstract StepInterpolator doCopy();

    /* access modifiers changed from: protected */
    public void doFinalize() throws MaxCountExceededException {
    }

    public abstract void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException;

    public abstract void writeExternal(ObjectOutput objectOutput) throws IOException;

    protected AbstractStepInterpolator() {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = null;
        this.finalized = false;
        this.forward = true;
        this.dirtyState = true;
        this.primaryMapper = null;
        this.secondaryMappers = null;
        allocateInterpolatedArrays(-1);
    }

    protected AbstractStepInterpolator(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        EquationsMapper[] equationsMapperArr2;
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        if (equationsMapperArr == null) {
            equationsMapperArr2 = null;
        } else {
            equationsMapperArr2 = (EquationsMapper[]) equationsMapperArr.clone();
        }
        this.secondaryMappers = equationsMapperArr2;
        allocateInterpolatedArrays(dArr.length);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: org.apache.commons.math3.ode.EquationsMapper[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractStepInterpolator(org.apache.commons.math3.ode.sampling.AbstractStepInterpolator r5) {
        /*
            r4 = this;
            r4.<init>()
            double r0 = r5.globalPreviousTime
            r4.globalPreviousTime = r0
            double r0 = r5.globalCurrentTime
            r4.globalCurrentTime = r0
            double r0 = r5.softPreviousTime
            r4.softPreviousTime = r0
            double r0 = r5.softCurrentTime
            r4.softCurrentTime = r0
            double r0 = r5.h
            r4.h = r0
            double r0 = r5.interpolatedTime
            r4.interpolatedTime = r0
            double[] r0 = r5.currentState
            r1 = 0
            if (r0 != 0) goto L_0x002b
            r4.currentState = r1
            r4.primaryMapper = r1
            r4.secondaryMappers = r1
            r0 = -1
            r4.allocateInterpolatedArrays(r0)
            goto L_0x008c
        L_0x002b:
            java.lang.Object r0 = r0.clone()
            double[] r0 = (double[]) r0
            r4.currentState = r0
            double[] r0 = r5.interpolatedState
            java.lang.Object r0 = r0.clone()
            double[] r0 = (double[]) r0
            r4.interpolatedState = r0
            double[] r0 = r5.interpolatedDerivatives
            java.lang.Object r0 = r0.clone()
            double[] r0 = (double[]) r0
            r4.interpolatedDerivatives = r0
            double[] r0 = r5.interpolatedPrimaryState
            java.lang.Object r0 = r0.clone()
            double[] r0 = (double[]) r0
            r4.interpolatedPrimaryState = r0
            double[] r0 = r5.interpolatedPrimaryDerivatives
            java.lang.Object r0 = r0.clone()
            double[] r0 = (double[]) r0
            r4.interpolatedPrimaryDerivatives = r0
            double[][] r0 = r5.interpolatedSecondaryState
            int r0 = r0.length
            double[][] r0 = new double[r0][]
            r4.interpolatedSecondaryState = r0
            double[][] r0 = r5.interpolatedSecondaryDerivatives
            int r0 = r0.length
            double[][] r0 = new double[r0][]
            r4.interpolatedSecondaryDerivatives = r0
            r0 = 0
        L_0x006a:
            double[][] r2 = r4.interpolatedSecondaryState
            int r3 = r2.length
            if (r0 >= r3) goto L_0x008c
            double[][] r3 = r5.interpolatedSecondaryState
            r3 = r3[r0]
            java.lang.Object r3 = r3.clone()
            double[] r3 = (double[]) r3
            r2[r0] = r3
            double[][] r2 = r4.interpolatedSecondaryDerivatives
            double[][] r3 = r5.interpolatedSecondaryDerivatives
            r3 = r3[r0]
            java.lang.Object r3 = r3.clone()
            double[] r3 = (double[]) r3
            r2[r0] = r3
            int r0 = r0 + 1
            goto L_0x006a
        L_0x008c:
            boolean r0 = r5.finalized
            r4.finalized = r0
            boolean r0 = r5.forward
            r4.forward = r0
            boolean r0 = r5.dirtyState
            r4.dirtyState = r0
            org.apache.commons.math3.ode.EquationsMapper r0 = r5.primaryMapper
            r4.primaryMapper = r0
            org.apache.commons.math3.ode.EquationsMapper[] r5 = r5.secondaryMappers
            if (r5 != 0) goto L_0x00a1
            goto L_0x00a8
        L_0x00a1:
            java.lang.Object r5 = r5.clone()
            r1 = r5
            org.apache.commons.math3.ode.EquationsMapper[] r1 = (org.apache.commons.math3.ode.EquationsMapper[]) r1
        L_0x00a8:
            r4.secondaryMappers = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.sampling.AbstractStepInterpolator.<init>(org.apache.commons.math3.ode.sampling.AbstractStepInterpolator):void");
    }

    private void allocateInterpolatedArrays(int i) {
        if (i < 0) {
            this.interpolatedState = null;
            this.interpolatedDerivatives = null;
            this.interpolatedPrimaryState = null;
            this.interpolatedPrimaryDerivatives = null;
            double[][] dArr = null;
            this.interpolatedSecondaryState = null;
            this.interpolatedSecondaryDerivatives = null;
            return;
        }
        this.interpolatedState = new double[i];
        this.interpolatedDerivatives = new double[i];
        this.interpolatedPrimaryState = new double[this.primaryMapper.getDimension()];
        this.interpolatedPrimaryDerivatives = new double[this.primaryMapper.getDimension()];
        EquationsMapper[] equationsMapperArr = this.secondaryMappers;
        if (equationsMapperArr == null) {
            double[][] dArr2 = null;
            this.interpolatedSecondaryState = null;
            this.interpolatedSecondaryDerivatives = null;
            return;
        }
        this.interpolatedSecondaryState = new double[equationsMapperArr.length][];
        this.interpolatedSecondaryDerivatives = new double[equationsMapperArr.length][];
        int i2 = 0;
        while (true) {
            EquationsMapper[] equationsMapperArr2 = this.secondaryMappers;
            if (i2 < equationsMapperArr2.length) {
                this.interpolatedSecondaryState[i2] = new double[equationsMapperArr2[i2].getDimension()];
                this.interpolatedSecondaryDerivatives[i2] = new double[this.secondaryMappers[i2].getDimension()];
                i2++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void reinitialize(double[] dArr, boolean z, EquationsMapper equationsMapper, EquationsMapper[] equationsMapperArr) {
        this.globalPreviousTime = Double.NaN;
        this.globalCurrentTime = Double.NaN;
        this.softPreviousTime = Double.NaN;
        this.softCurrentTime = Double.NaN;
        this.h = Double.NaN;
        this.interpolatedTime = Double.NaN;
        this.currentState = dArr;
        this.finalized = false;
        this.forward = z;
        this.dirtyState = true;
        this.primaryMapper = equationsMapper;
        this.secondaryMappers = (EquationsMapper[]) equationsMapperArr.clone();
        allocateInterpolatedArrays(dArr.length);
    }

    public StepInterpolator copy() throws MaxCountExceededException {
        finalizeStep();
        return doCopy();
    }

    public void shift() {
        double d = this.globalCurrentTime;
        this.globalPreviousTime = d;
        this.softPreviousTime = d;
        this.softCurrentTime = d;
    }

    public void storeTime(double d) {
        this.globalCurrentTime = d;
        this.softCurrentTime = d;
        this.h = d - this.globalPreviousTime;
        setInterpolatedTime(d);
        this.finalized = false;
    }

    public void setSoftPreviousTime(double d) {
        this.softPreviousTime = d;
    }

    public void setSoftCurrentTime(double d) {
        this.softCurrentTime = d;
    }

    public double getGlobalPreviousTime() {
        return this.globalPreviousTime;
    }

    public double getGlobalCurrentTime() {
        return this.globalCurrentTime;
    }

    public double getPreviousTime() {
        return this.softPreviousTime;
    }

    public double getCurrentTime() {
        return this.softCurrentTime;
    }

    public double getInterpolatedTime() {
        return this.interpolatedTime;
    }

    public void setInterpolatedTime(double d) {
        this.interpolatedTime = d;
        this.dirtyState = true;
    }

    public boolean isForward() {
        return this.forward;
    }

    private void evaluateCompleteInterpolatedState() throws MaxCountExceededException {
        if (this.dirtyState) {
            double d = this.globalCurrentTime - this.interpolatedTime;
            double d2 = this.h;
            double d3 = 0.0d;
            if (d2 != 0.0d) {
                d3 = (d2 - d) / d2;
            }
            computeInterpolatedStateAndDerivatives(d3, d);
            this.dirtyState = false;
        }
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedState, this.interpolatedPrimaryState);
        return this.interpolatedPrimaryState;
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.primaryMapper.extractEquationData(this.interpolatedDerivatives, this.interpolatedPrimaryDerivatives);
        return this.interpolatedPrimaryDerivatives;
    }

    public double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedState, this.interpolatedSecondaryState[i]);
        return this.interpolatedSecondaryState[i];
    }

    public double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException {
        evaluateCompleteInterpolatedState();
        this.secondaryMappers[i].extractEquationData(this.interpolatedDerivatives, this.interpolatedSecondaryDerivatives[i]);
        return this.interpolatedSecondaryDerivatives[i];
    }

    public final void finalizeStep() throws MaxCountExceededException {
        if (!this.finalized) {
            doFinalize();
            this.finalized = true;
        }
    }

    /* access modifiers changed from: protected */
    public void writeBaseExternal(ObjectOutput objectOutput) throws IOException {
        double[] dArr = this.currentState;
        if (dArr == null) {
            objectOutput.writeInt(-1);
        } else {
            objectOutput.writeInt(dArr.length);
        }
        objectOutput.writeDouble(this.globalPreviousTime);
        objectOutput.writeDouble(this.globalCurrentTime);
        objectOutput.writeDouble(this.softPreviousTime);
        objectOutput.writeDouble(this.softCurrentTime);
        objectOutput.writeDouble(this.h);
        objectOutput.writeBoolean(this.forward);
        objectOutput.writeObject(this.primaryMapper);
        objectOutput.write(this.secondaryMappers.length);
        int i = 0;
        for (EquationsMapper writeObject : this.secondaryMappers) {
            objectOutput.writeObject(writeObject);
        }
        if (this.currentState != null) {
            while (true) {
                double[] dArr2 = this.currentState;
                if (i >= dArr2.length) {
                    break;
                }
                objectOutput.writeDouble(dArr2[i]);
                i++;
            }
        }
        objectOutput.writeDouble(this.interpolatedTime);
        try {
            finalizeStep();
        } catch (MaxCountExceededException e) {
            IOException iOException = new IOException(e.getLocalizedMessage());
            iOException.initCause(e);
            throw iOException;
        }
    }

    /* access modifiers changed from: protected */
    public double readBaseExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        int readInt = objectInput.readInt();
        this.globalPreviousTime = objectInput.readDouble();
        this.globalCurrentTime = objectInput.readDouble();
        this.softPreviousTime = objectInput.readDouble();
        this.softCurrentTime = objectInput.readDouble();
        this.h = objectInput.readDouble();
        this.forward = objectInput.readBoolean();
        this.primaryMapper = (EquationsMapper) objectInput.readObject();
        this.secondaryMappers = new EquationsMapper[objectInput.read()];
        int i = 0;
        int i2 = 0;
        while (true) {
            EquationsMapper[] equationsMapperArr = this.secondaryMappers;
            if (i2 >= equationsMapperArr.length) {
                break;
            }
            equationsMapperArr[i2] = (EquationsMapper) objectInput.readObject();
            i2++;
        }
        this.dirtyState = true;
        if (readInt >= 0) {
            this.currentState = new double[readInt];
            while (true) {
                double[] dArr = this.currentState;
                if (i >= dArr.length) {
                    break;
                }
                dArr[i] = objectInput.readDouble();
                i++;
            }
        } else {
            this.currentState = null;
        }
        this.interpolatedTime = Double.NaN;
        allocateInterpolatedArrays(readInt);
        this.finalized = true;
        return objectInput.readDouble();
    }
}
