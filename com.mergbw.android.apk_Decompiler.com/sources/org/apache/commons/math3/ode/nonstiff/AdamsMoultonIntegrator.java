package org.apache.commons.math3.ode.nonstiff;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrixPreservingVisitor;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsMoultonIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Moulton";

    public AdamsMoultonIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i + 1, d, d2, d3, d4);
    }

    public AdamsMoultonIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i + 1, d, d2, dArr, dArr2);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        boolean z;
        boolean z2;
        boolean z3;
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z4 = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr = (double[]) completeState.clone();
        double[] dArr2 = new double[dArr.length];
        double[] dArr3 = new double[dArr.length];
        double[] dArr4 = new double[dArr.length];
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(dArr, z4, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        NordsieckStepInterpolator nordsieckStepInterpolator2 = nordsieckStepInterpolator;
        double d2 = d;
        initIntegration(expandableStatefulODE.getTime(), completeState, d2);
        start(expandableStatefulODE.getTime(), dArr, d2);
        nordsieckStepInterpolator2.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        NordsieckStepInterpolator nordsieckStepInterpolator3 = nordsieckStepInterpolator2;
        nordsieckStepInterpolator3.storeTime(this.stepStart);
        double d3 = this.stepSize;
        nordsieckStepInterpolator3.rescale(d3);
        this.isLastStep = false;
        Array2DRowRealMatrix array2DRowRealMatrix = null;
        while (true) {
            Array2DRowRealMatrix array2DRowRealMatrix2 = array2DRowRealMatrix;
            double d4 = d3;
            double d5 = 10.0d;
            while (d5 >= 1.0d) {
                this.stepSize = d4;
                boolean z5 = z4;
                double d6 = this.stepStart + this.stepSize;
                nordsieckStepInterpolator3.setInterpolatedTime(d6);
                ExpandableStatefulODE expandable = getExpandable();
                expandable.getPrimaryMapper().insertEquationData(nordsieckStepInterpolator3.getInterpolatedState(), dArr3);
                EquationsMapper[] secondaryMappers = expandable.getSecondaryMappers();
                int length = secondaryMappers.length;
                int i = 0;
                int i2 = 0;
                while (i2 < length) {
                    secondaryMappers[i2].insertEquationData(nordsieckStepInterpolator3.getInterpolatedSecondaryState(i), dArr3);
                    i++;
                    i2++;
                    d4 = d4;
                }
                double d7 = d4;
                computeDerivatives(d6, dArr3, dArr2);
                for (int i3 = 0; i3 < completeState.length; i3++) {
                    dArr4[i3] = this.stepSize * dArr2[i3];
                }
                array2DRowRealMatrix2 = updateHighOrderDerivativesPhase1(this.nordsieck);
                updateHighOrderDerivativesPhase2(this.scaled, dArr4, array2DRowRealMatrix2);
                d5 = array2DRowRealMatrix2.walkInOptimizedOrder((RealMatrixPreservingVisitor) new Corrector(dArr, dArr4, dArr3));
                if (d5 >= 1.0d) {
                    z3 = z5;
                    double filterStep = filterStep(this.stepSize * computeStepGrowShrinkFactor(d5), z3, false);
                    nordsieckStepInterpolator3.rescale(filterStep);
                    d4 = filterStep;
                } else {
                    z3 = z5;
                    d4 = d7;
                }
            }
            double d8 = d4;
            double d9 = this.stepStart + this.stepSize;
            computeDerivatives(d9, dArr3, dArr2);
            double[] dArr5 = new double[completeState.length];
            double d10 = d5;
            for (int i4 = 0; i4 < completeState.length; i4++) {
                dArr5[i4] = this.stepSize * dArr2[i4];
            }
            updateHighOrderDerivativesPhase2(dArr4, dArr5, array2DRowRealMatrix2);
            System.arraycopy(dArr3, 0, dArr, 0, dArr.length);
            nordsieckStepInterpolator3.reinitialize(d9, this.stepSize, dArr5, array2DRowRealMatrix2);
            nordsieckStepInterpolator3.storeTime(this.stepStart);
            nordsieckStepInterpolator3.shift();
            nordsieckStepInterpolator3.storeTime(d9);
            boolean z6 = z4;
            double d11 = d8;
            double d12 = d10;
            double[] dArr6 = dArr2;
            double[] dArr7 = completeState;
            double[] dArr8 = dArr2;
            NordsieckStepInterpolator nordsieckStepInterpolator4 = nordsieckStepInterpolator3;
            Array2DRowRealMatrix array2DRowRealMatrix3 = array2DRowRealMatrix2;
            this.stepStart = acceptStep(nordsieckStepInterpolator3, dArr, dArr6, d);
            this.scaled = dArr5;
            this.nordsieck = array2DRowRealMatrix3;
            if (!this.isLastStep) {
                nordsieckStepInterpolator4.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, dArr, d);
                    nordsieckStepInterpolator4.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                }
                double computeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d12);
                double d13 = this.stepStart + computeStepGrowShrinkFactor;
                if (!z6 ? d13 > d : d13 < d) {
                    z = z6;
                    z2 = false;
                } else {
                    z = z6;
                    z2 = true;
                }
                d3 = filterStep(computeStepGrowShrinkFactor, z, z2);
                double d14 = this.stepStart + d3;
                if (!z ? d14 <= d : d14 >= d) {
                    d3 = d - this.stepStart;
                }
                nordsieckStepInterpolator4.rescale(d3);
            } else {
                z = z6;
                d3 = d11;
            }
            if (this.isLastStep) {
                expandableStatefulODE2.setTime(this.stepStart);
                expandableStatefulODE2.setCompleteState(dArr);
                resetInternalState();
                return;
            }
            z4 = z;
            nordsieckStepInterpolator3 = nordsieckStepInterpolator4;
            array2DRowRealMatrix = array2DRowRealMatrix3;
            completeState = dArr7;
            dArr2 = dArr8;
        }
    }

    private class Corrector implements RealMatrixPreservingVisitor {
        private final double[] after;
        private final double[] before;
        private final double[] previous;
        private final double[] scaled;

        public Corrector(double[] dArr, double[] dArr2, double[] dArr3) {
            this.previous = dArr;
            this.scaled = dArr2;
            this.after = dArr3;
            this.before = (double[]) dArr3.clone();
        }

        public void start(int i, int i2, int i3, int i4, int i5, int i6) {
            Arrays.fill(this.after, 0.0d);
        }

        public void visit(int i, int i2, double d) {
            if ((i & 1) == 0) {
                double[] dArr = this.after;
                dArr[i2] = dArr[i2] - d;
                return;
            }
            double[] dArr2 = this.after;
            dArr2[i2] = dArr2[i2] + d;
        }

        public double end() {
            double d = 0.0d;
            int i = 0;
            while (true) {
                double[] dArr = this.after;
                if (i >= dArr.length) {
                    return FastMath.sqrt(d / ((double) AdamsMoultonIntegrator.this.mainSetDimension));
                }
                dArr[i] = dArr[i] + this.previous[i] + this.scaled[i];
                if (i < AdamsMoultonIntegrator.this.mainSetDimension) {
                    double max = FastMath.max(FastMath.abs(this.previous[i]), FastMath.abs(this.after[i]));
                    double d2 = (this.after[i] - this.before[i]) / (AdamsMoultonIntegrator.this.vecAbsoluteTolerance == null ? AdamsMoultonIntegrator.this.scalAbsoluteTolerance + (AdamsMoultonIntegrator.this.scalRelativeTolerance * max) : AdamsMoultonIntegrator.this.vecAbsoluteTolerance[i] + (AdamsMoultonIntegrator.this.vecRelativeTolerance[i] * max));
                    d += d2 * d2;
                }
                i++;
            }
        }
    }
}
