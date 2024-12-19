package org.apache.commons.math3.ode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class ContinuousOutputModel implements StepHandler, Serializable {
    private static final long serialVersionUID = -1417964919405031606L;
    private double finalTime = Double.NaN;
    private boolean forward = true;
    private int index = 0;
    private double initialTime = Double.NaN;
    private List<StepInterpolator> steps = new ArrayList();

    public void append(ContinuousOutputModel continuousOutputModel) throws MathIllegalArgumentException, MaxCountExceededException {
        if (continuousOutputModel.steps.size() != 0) {
            if (this.steps.size() == 0) {
                this.initialTime = continuousOutputModel.initialTime;
                this.forward = continuousOutputModel.forward;
            } else if (getInterpolatedState().length != continuousOutputModel.getInterpolatedState().length) {
                throw new DimensionMismatchException(continuousOutputModel.getInterpolatedState().length, getInterpolatedState().length);
            } else if (!(this.forward ^ continuousOutputModel.forward)) {
                StepInterpolator stepInterpolator = this.steps.get(this.index);
                double currentTime = stepInterpolator.getCurrentTime();
                double previousTime = currentTime - stepInterpolator.getPreviousTime();
                double initialTime2 = continuousOutputModel.getInitialTime() - currentTime;
                if (FastMath.abs(initialTime2) > FastMath.abs(previousTime) * 0.001d) {
                    throw new MathIllegalArgumentException(LocalizedFormats.HOLE_BETWEEN_MODELS_TIME_RANGES, Double.valueOf(FastMath.abs(initialTime2)));
                }
            } else {
                throw new MathIllegalArgumentException(LocalizedFormats.PROPAGATION_DIRECTION_MISMATCH, new Object[0]);
            }
            for (StepInterpolator copy : continuousOutputModel.steps) {
                this.steps.add(copy.copy());
            }
            int size = this.steps.size() - 1;
            this.index = size;
            this.finalTime = this.steps.get(size).getCurrentTime();
        }
    }

    public void init(double d, double[] dArr, double d2) {
        this.initialTime = Double.NaN;
        this.finalTime = Double.NaN;
        this.forward = true;
        this.index = 0;
        this.steps.clear();
    }

    public void handleStep(StepInterpolator stepInterpolator, boolean z) throws MaxCountExceededException {
        if (this.steps.size() == 0) {
            this.initialTime = stepInterpolator.getPreviousTime();
            this.forward = stepInterpolator.isForward();
        }
        this.steps.add(stepInterpolator.copy());
        if (z) {
            this.finalTime = stepInterpolator.getCurrentTime();
            this.index = this.steps.size() - 1;
        }
    }

    public double getInitialTime() {
        return this.initialTime;
    }

    public double getFinalTime() {
        return this.finalTime;
    }

    public double getInterpolatedTime() {
        return this.steps.get(this.index).getInterpolatedTime();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setInterpolatedTime(double r28) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r3 = r0.steps
            r4 = 0
            java.lang.Object r3 = r3.get(r4)
            org.apache.commons.math3.ode.sampling.StepInterpolator r3 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r3
            double r5 = r3.getPreviousTime()
            double r7 = r3.getCurrentTime()
            double r5 = r5 + r7
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r5 = r5 * r7
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r9 = r0.steps
            int r9 = r9.size()
            int r9 = r9 + -1
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r10 = r0.steps
            java.lang.Object r10 = r10.get(r9)
            org.apache.commons.math3.ode.sampling.StepInterpolator r10 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r10
            double r11 = r10.getPreviousTime()
            double r13 = r10.getCurrentTime()
            double r11 = r11 + r13
            double r11 = r11 * r7
            int r13 = r0.locatePoint(r1, r3)
            if (r13 > 0) goto L_0x003f
            r0.index = r4
            r3.setInterpolatedTime(r1)
            return
        L_0x003f:
            int r3 = r0.locatePoint(r1, r10)
            if (r3 < 0) goto L_0x004b
            r0.index = r9
            r10.setInterpolatedTime(r1)
            return
        L_0x004b:
            int r3 = r9 - r4
            r10 = 5
            if (r3 <= r10) goto L_0x0106
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r3 = r0.steps
            int r10 = r0.index
            java.lang.Object r3 = r3.get(r10)
            org.apache.commons.math3.ode.sampling.StepInterpolator r3 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r3
            int r10 = r0.locatePoint(r1, r3)
            if (r10 >= 0) goto L_0x006e
            int r9 = r0.index
            double r10 = r3.getPreviousTime()
            double r12 = r3.getCurrentTime()
            double r10 = r10 + r12
            double r10 = r10 * r7
            r11 = r10
            goto L_0x007c
        L_0x006e:
            if (r10 <= 0) goto L_0x0102
            int r4 = r0.index
            double r5 = r3.getPreviousTime()
            double r13 = r3.getCurrentTime()
            double r5 = r5 + r13
            double r5 = r5 * r7
        L_0x007c:
            int r3 = r4 + r9
            int r3 = r3 / 2
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r10 = r0.steps
            java.lang.Object r10 = r10.get(r3)
            org.apache.commons.math3.ode.sampling.StepInterpolator r10 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r10
            double r13 = r10.getPreviousTime()
            double r15 = r10.getCurrentTime()
            double r13 = r13 + r15
            double r13 = r13 * r7
            double r15 = r13 - r5
            double r17 = org.apache.commons.math3.util.FastMath.abs((double) r15)
            r19 = 4517329193108106637(0x3eb0c6f7a0b5ed8d, double:1.0E-6)
            int r10 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r10 < 0) goto L_0x00db
            double r17 = r11 - r13
            double r21 = org.apache.commons.math3.util.FastMath.abs((double) r17)
            int r10 = (r21 > r19 ? 1 : (r21 == r19 ? 0 : -1))
            if (r10 >= 0) goto L_0x00ac
            goto L_0x00db
        L_0x00ac:
            double r19 = r11 - r5
            double r21 = r1 - r11
            double r13 = r1 - r13
            double r23 = r1 - r5
            double r25 = r13 * r23
            double r25 = r25 * r15
            double r7 = (double) r9
            double r25 = r25 * r7
            double r23 = r23 * r21
            double r23 = r23 * r19
            double r7 = (double) r3
            double r23 = r23 * r7
            double r25 = r25 - r23
            double r21 = r21 * r13
            double r21 = r21 * r17
            double r7 = (double) r4
            double r21 = r21 * r7
            double r25 = r25 + r21
            double r17 = r17 * r15
            double r17 = r17 * r19
            double r25 = r25 / r17
            double r7 = org.apache.commons.math3.util.FastMath.rint(r25)
            int r3 = (int) r7
            r0.index = r3
            goto L_0x00dd
        L_0x00db:
            r0.index = r3
        L_0x00dd:
            int r3 = r4 + 1
            int r7 = r4 * 9
            int r7 = r7 + r9
            int r7 = r7 / 10
            int r3 = org.apache.commons.math3.util.FastMath.max((int) r3, (int) r7)
            int r7 = r9 + -1
            int r8 = r9 * 9
            int r8 = r8 + r4
            int r8 = r8 / 10
            int r7 = org.apache.commons.math3.util.FastMath.min((int) r7, (int) r8)
            int r8 = r0.index
            if (r8 >= r3) goto L_0x00fa
            r0.index = r3
            goto L_0x00fe
        L_0x00fa:
            if (r8 <= r7) goto L_0x00fe
            r0.index = r7
        L_0x00fe:
            r7 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            goto L_0x004b
        L_0x0102:
            r3.setInterpolatedTime(r1)
            return
        L_0x0106:
            r0.index = r4
        L_0x0108:
            int r3 = r0.index
            if (r3 > r9) goto L_0x0121
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r4 = r0.steps
            java.lang.Object r3 = r4.get(r3)
            org.apache.commons.math3.ode.sampling.StepInterpolator r3 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r3
            int r3 = r0.locatePoint(r1, r3)
            if (r3 <= 0) goto L_0x0121
            int r3 = r0.index
            int r3 = r3 + 1
            r0.index = r3
            goto L_0x0108
        L_0x0121:
            java.util.List<org.apache.commons.math3.ode.sampling.StepInterpolator> r3 = r0.steps
            int r4 = r0.index
            java.lang.Object r3 = r3.get(r4)
            org.apache.commons.math3.ode.sampling.StepInterpolator r3 = (org.apache.commons.math3.ode.sampling.StepInterpolator) r3
            r3.setInterpolatedTime(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.ContinuousOutputModel.setInterpolatedTime(double):void");
    }

    public double[] getInterpolatedState() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedState();
    }

    public double[] getInterpolatedDerivatives() throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedDerivatives();
    }

    public double[] getInterpolatedSecondaryState(int i) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryState(i);
    }

    public double[] getInterpolatedSecondaryDerivatives(int i) throws MaxCountExceededException {
        return this.steps.get(this.index).getInterpolatedSecondaryDerivatives(i);
    }

    private int locatePoint(double d, StepInterpolator stepInterpolator) {
        if (this.forward) {
            if (d < stepInterpolator.getPreviousTime()) {
                return -1;
            }
            return d > stepInterpolator.getCurrentTime() ? 1 : 0;
        } else if (d > stepInterpolator.getPreviousTime()) {
            return -1;
        } else {
            return d < stepInterpolator.getCurrentTime() ? 1 : 0;
        }
    }
}
