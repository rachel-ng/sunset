package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.sampling.NordsieckStepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class AdamsBashforthIntegrator extends AdamsIntegrator {
    private static final String METHOD_NAME = "Adams-Bashforth";

    public AdamsBashforthIntegrator(int i, double d, double d2, double d3, double d4) throws NumberIsTooSmallException {
        super(METHOD_NAME, i, i, d, d2, d3, d4);
    }

    public AdamsBashforthIntegrator(int i, double d, double d2, double[] dArr, double[] dArr2) throws IllegalArgumentException {
        super(METHOD_NAME, i, i, d, d2, dArr, dArr2);
    }

    public void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException {
        double[] dArr;
        double d2;
        double d3;
        double d4;
        ExpandableStatefulODE expandableStatefulODE2 = expandableStatefulODE;
        sanityChecks(expandableStatefulODE, d);
        setEquations(expandableStatefulODE);
        boolean z = d > expandableStatefulODE.getTime();
        double[] completeState = expandableStatefulODE.getCompleteState();
        double[] dArr2 = (double[]) completeState.clone();
        double[] dArr3 = new double[dArr2.length];
        NordsieckStepInterpolator nordsieckStepInterpolator = new NordsieckStepInterpolator();
        nordsieckStepInterpolator.reinitialize(dArr2, z, expandableStatefulODE.getPrimaryMapper(), expandableStatefulODE.getSecondaryMappers());
        double d5 = d;
        initIntegration(expandableStatefulODE.getTime(), completeState, d5);
        start(expandableStatefulODE.getTime(), dArr2, d5);
        NordsieckStepInterpolator nordsieckStepInterpolator2 = nordsieckStepInterpolator;
        nordsieckStepInterpolator.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
        nordsieckStepInterpolator2.storeTime(this.stepStart);
        int rowDimension = this.nordsieck.getRowDimension() - 1;
        double d6 = this.stepSize;
        nordsieckStepInterpolator2.rescale(d6);
        this.isLastStep = false;
        while (true) {
            double d7 = d6;
            double d8 = 10.0d;
            double d9 = d7;
            while (d8 >= 1.0d) {
                this.stepSize = d9;
                double d10 = 0.0d;
                int i = 0;
                while (i < this.mainSetDimension) {
                    double abs = FastMath.abs(dArr2[i]);
                    if (this.vecAbsoluteTolerance == null) {
                        d3 = d9;
                        d4 = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * abs);
                    } else {
                        d3 = d9;
                        d4 = this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * abs);
                    }
                    double entry = this.nordsieck.getEntry(rowDimension, i) / d4;
                    d10 += entry * entry;
                    i++;
                    d9 = d3;
                }
                double d11 = d9;
                d8 = FastMath.sqrt(d10 / ((double) this.mainSetDimension));
                if (d8 >= 1.0d) {
                    d9 = filterStep(this.stepSize * computeStepGrowShrinkFactor(d8), z, false);
                    nordsieckStepInterpolator2.rescale(d9);
                } else {
                    d9 = d11;
                }
            }
            double d12 = d9;
            int i2 = 0;
            double d13 = this.stepStart + this.stepSize;
            nordsieckStepInterpolator2.shift();
            nordsieckStepInterpolator2.setInterpolatedTime(d13);
            ExpandableStatefulODE expandable = getExpandable();
            expandable.getPrimaryMapper().insertEquationData(nordsieckStepInterpolator2.getInterpolatedState(), dArr2);
            EquationsMapper[] secondaryMappers = expandable.getSecondaryMappers();
            int length = secondaryMappers.length;
            int i3 = 0;
            while (i3 < length) {
                secondaryMappers[i3].insertEquationData(nordsieckStepInterpolator2.getInterpolatedSecondaryState(i2), dArr2);
                i2++;
                i3++;
                d8 = d8;
            }
            double d14 = d8;
            computeDerivatives(d13, dArr2, dArr3);
            double[] dArr4 = new double[completeState.length];
            for (int i4 = 0; i4 < completeState.length; i4++) {
                dArr4[i4] = this.stepSize * dArr3[i4];
            }
            Array2DRowRealMatrix updateHighOrderDerivativesPhase1 = updateHighOrderDerivativesPhase1(this.nordsieck);
            updateHighOrderDerivativesPhase2(this.scaled, dArr4, updateHighOrderDerivativesPhase1);
            nordsieckStepInterpolator2.reinitialize(d13, this.stepSize, dArr4, updateHighOrderDerivativesPhase1);
            nordsieckStepInterpolator2.storeTime(d13);
            double d15 = d14;
            int i5 = rowDimension;
            double[] dArr5 = completeState;
            this.stepStart = acceptStep(nordsieckStepInterpolator2, dArr2, dArr3, d);
            this.scaled = dArr4;
            this.nordsieck = updateHighOrderDerivativesPhase1;
            double d16 = d15;
            nordsieckStepInterpolator2.reinitialize(d13, this.stepSize, this.scaled, this.nordsieck);
            if (!this.isLastStep) {
                nordsieckStepInterpolator2.storeTime(this.stepStart);
                if (this.resetOccurred) {
                    start(this.stepStart, dArr2, d);
                    dArr = dArr2;
                    d2 = d16;
                    nordsieckStepInterpolator2.reinitialize(this.stepStart, this.stepSize, this.scaled, this.nordsieck);
                } else {
                    dArr = dArr2;
                    d2 = d16;
                }
                double computeStepGrowShrinkFactor = this.stepSize * computeStepGrowShrinkFactor(d2);
                double d17 = this.stepStart + computeStepGrowShrinkFactor;
                d6 = filterStep(computeStepGrowShrinkFactor, z, !z ? d17 <= d : d17 >= d);
                double d18 = this.stepStart + d6;
                if (!z ? d18 <= d : d18 >= d) {
                    d6 = d - this.stepStart;
                }
                nordsieckStepInterpolator2.rescale(d6);
            } else {
                dArr = dArr2;
                d6 = d12;
            }
            if (this.isLastStep) {
                expandableStatefulODE2.setTime(this.stepStart);
                expandableStatefulODE2.setCompleteState(dArr);
                resetInternalState();
                return;
            }
            dArr2 = dArr;
            rowDimension = i5;
            completeState = dArr5;
        }
    }
}
