package org.apache.commons.math3.complex;

import java.io.Serializable;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class RootsOfUnity implements Serializable {
    private static final long serialVersionUID = 20120201;
    private boolean isCounterClockWise = true;
    private int omegaCount = 0;
    private double[] omegaImaginaryClockwise = null;
    private double[] omegaImaginaryCounterClockwise = null;
    private double[] omegaReal = null;

    public synchronized boolean isCounterClockWise() throws MathIllegalStateException {
        if (this.omegaCount != 0) {
        } else {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        }
        return this.isCounterClockWise;
    }

    public synchronized void computeRoots(int i) throws ZeroException {
        if (i != 0) {
            this.isCounterClockWise = i > 0;
            int abs = FastMath.abs(i);
            if (abs != this.omegaCount) {
                double d = 6.283185307179586d / ((double) abs);
                double cos = FastMath.cos(d);
                double sin = FastMath.sin(d);
                double[] dArr = new double[abs];
                this.omegaReal = dArr;
                double[] dArr2 = new double[abs];
                this.omegaImaginaryCounterClockwise = dArr2;
                double[] dArr3 = new double[abs];
                this.omegaImaginaryClockwise = dArr3;
                dArr[0] = 1.0d;
                dArr2[0] = 0.0d;
                dArr3[0] = 0.0d;
                for (int i2 = 1; i2 < abs; i2++) {
                    double[] dArr4 = this.omegaReal;
                    int i3 = i2 - 1;
                    double[] dArr5 = this.omegaImaginaryCounterClockwise;
                    dArr4[i2] = (dArr4[i3] * cos) - (dArr5[i3] * sin);
                    double d2 = (dArr4[i3] * sin) + (dArr5[i3] * cos);
                    dArr5[i2] = d2;
                    this.omegaImaginaryClockwise[i2] = -d2;
                }
                this.omegaCount = abs;
                return;
            }
            return;
        }
        throw new ZeroException(LocalizedFormats.CANNOT_COMPUTE_0TH_ROOT_OF_UNITY, new Object[0]);
    }

    public synchronized double getReal(int i) throws MathIllegalStateException, MathIllegalArgumentException {
        int i2 = this.omegaCount;
        if (i2 == 0) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        } else if (i < 0 || i >= i2) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(i), 0, Integer.valueOf(this.omegaCount - 1));
        }
        return this.omegaReal[i];
    }

    public synchronized double getImaginary(int i) throws MathIllegalStateException, OutOfRangeException {
        int i2 = this.omegaCount;
        if (i2 == 0) {
            throw new MathIllegalStateException(LocalizedFormats.ROOTS_OF_UNITY_NOT_COMPUTED_YET, new Object[0]);
        } else if (i < 0 || i >= i2) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_ROOT_OF_UNITY_INDEX, Integer.valueOf(i), 0, Integer.valueOf(this.omegaCount - 1));
        }
        return this.isCounterClockWise ? this.omegaImaginaryCounterClockwise[i] : this.omegaImaginaryClockwise[i];
    }

    public synchronized int getNumberOfRoots() {
        return this.omegaCount;
    }
}
