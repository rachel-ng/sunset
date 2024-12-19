package org.apache.commons.math3.ode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.events.EventState;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Incrementor;

public abstract class AbstractIntegrator implements FirstOrderIntegrator {
    private Incrementor evaluations;
    private Collection<EventState> eventsStates;
    private transient ExpandableStatefulODE expandable;
    protected boolean isLastStep;
    private final String name;
    protected boolean resetOccurred;
    private boolean statesInitialized;
    protected Collection<StepHandler> stepHandlers;
    protected double stepSize;
    protected double stepStart;

    public abstract void integrate(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException, MaxCountExceededException, NoBracketingException;

    public AbstractIntegrator(String str) {
        this.name = str;
        this.stepHandlers = new ArrayList();
        this.stepStart = Double.NaN;
        this.stepSize = Double.NaN;
        this.eventsStates = new ArrayList();
        this.statesInitialized = false;
        this.evaluations = new Incrementor();
        setMaxEvaluations(-1);
        this.evaluations.resetCount();
    }

    protected AbstractIntegrator() {
        this((String) null);
    }

    public String getName() {
        return this.name;
    }

    public void addStepHandler(StepHandler stepHandler) {
        this.stepHandlers.add(stepHandler);
    }

    public Collection<StepHandler> getStepHandlers() {
        return Collections.unmodifiableCollection(this.stepHandlers);
    }

    public void clearStepHandlers() {
        this.stepHandlers.clear();
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i) {
        addEventHandler(eventHandler, d, d2, i, new BracketingNthOrderBrentSolver(d2, 5));
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        this.eventsStates.add(new EventState(eventHandler, d, d2, i, univariateSolver));
    }

    public Collection<EventHandler> getEventHandlers() {
        ArrayList arrayList = new ArrayList(this.eventsStates.size());
        for (EventState eventHandler : this.eventsStates) {
            arrayList.add(eventHandler.getEventHandler());
        }
        return Collections.unmodifiableCollection(arrayList);
    }

    public void clearEventHandlers() {
        this.eventsStates.clear();
    }

    public double getCurrentStepStart() {
        return this.stepStart;
    }

    public double getCurrentSignedStepsize() {
        return this.stepSize;
    }

    public void setMaxEvaluations(int i) {
        Incrementor incrementor = this.evaluations;
        if (i < 0) {
            i = Integer.MAX_VALUE;
        }
        incrementor.setMaximalCount(i);
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    /* access modifiers changed from: protected */
    public void initIntegration(double d, double[] dArr, double d2) {
        this.evaluations.resetCount();
        for (EventState next : this.eventsStates) {
            next.setExpandable(this.expandable);
            next.getEventHandler().init(d, dArr, d2);
        }
        for (StepHandler init : this.stepHandlers) {
            init.init(d, dArr, d2);
        }
        setStateInitialized(false);
    }

    /* access modifiers changed from: protected */
    public void setEquations(ExpandableStatefulODE expandableStatefulODE) {
        this.expandable = expandableStatefulODE;
    }

    /* access modifiers changed from: protected */
    public ExpandableStatefulODE getExpandable() {
        return this.expandable;
    }

    /* access modifiers changed from: protected */
    public Incrementor getEvaluationsCounter() {
        return this.evaluations;
    }

    public double integrate(FirstOrderDifferentialEquations firstOrderDifferentialEquations, double d, double[] dArr, double d2, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, MaxCountExceededException, NoBracketingException {
        if (dArr.length != firstOrderDifferentialEquations.getDimension()) {
            throw new DimensionMismatchException(dArr.length, firstOrderDifferentialEquations.getDimension());
        } else if (dArr2.length == firstOrderDifferentialEquations.getDimension()) {
            ExpandableStatefulODE expandableStatefulODE = new ExpandableStatefulODE(firstOrderDifferentialEquations);
            expandableStatefulODE.setTime(d);
            expandableStatefulODE.setPrimaryState(dArr);
            integrate(expandableStatefulODE, d2);
            System.arraycopy(expandableStatefulODE.getPrimaryState(), 0, dArr2, 0, dArr2.length);
            return expandableStatefulODE.getTime();
        } else {
            throw new DimensionMismatchException(dArr2.length, firstOrderDifferentialEquations.getDimension());
        }
    }

    public void computeDerivatives(double d, double[] dArr, double[] dArr2) throws MaxCountExceededException, DimensionMismatchException {
        this.evaluations.incrementCount();
        this.expandable.computeDerivatives(d, dArr, dArr2);
    }

    /* access modifiers changed from: protected */
    public void setStateInitialized(boolean z) {
        this.statesInitialized = z;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01a5 A[LOOP:10: B:77:0x019f->B:79:0x01a5, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double acceptStep(org.apache.commons.math3.ode.sampling.AbstractStepInterpolator r18, double[] r19, double[] r20, double r21) throws org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            double r3 = r18.getGlobalPreviousTime()
            double r5 = r18.getGlobalCurrentTime()
            boolean r7 = r0.statesInitialized
            r8 = 1
            if (r7 != 0) goto L_0x002b
            java.util.Collection<org.apache.commons.math3.ode.events.EventState> r7 = r0.eventsStates
            java.util.Iterator r7 = r7.iterator()
        L_0x0019:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x0029
            java.lang.Object r9 = r7.next()
            org.apache.commons.math3.ode.events.EventState r9 = (org.apache.commons.math3.ode.events.EventState) r9
            r9.reinitializeBegin(r1)
            goto L_0x0019
        L_0x0029:
            r0.statesInitialized = r8
        L_0x002b:
            boolean r7 = r18.isForward()
            if (r7 == 0) goto L_0x0033
            r7 = r8
            goto L_0x0034
        L_0x0033:
            r7 = -1
        L_0x0034:
            java.util.TreeSet r9 = new java.util.TreeSet
            org.apache.commons.math3.ode.AbstractIntegrator$1 r10 = new org.apache.commons.math3.ode.AbstractIntegrator$1
            r10.<init>(r7)
            r9.<init>(r10)
            java.util.Collection<org.apache.commons.math3.ode.events.EventState> r7 = r0.eventsStates
            java.util.Iterator r7 = r7.iterator()
        L_0x0044:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x005a
            java.lang.Object r10 = r7.next()
            org.apache.commons.math3.ode.events.EventState r10 = (org.apache.commons.math3.ode.events.EventState) r10
            boolean r11 = r10.evaluateStep(r1)
            if (r11 == 0) goto L_0x0044
            r9.add(r10)
            goto L_0x0044
        L_0x005a:
            boolean r7 = r9.isEmpty()
            r10 = 0
            if (r7 != 0) goto L_0x0132
            java.util.Iterator r7 = r9.iterator()
            java.lang.Object r11 = r7.next()
            org.apache.commons.math3.ode.events.EventState r11 = (org.apache.commons.math3.ode.events.EventState) r11
            r7.remove()
            double r12 = r11.getEventTime()
            r1.setSoftPreviousTime(r3)
            r1.setSoftCurrentTime(r12)
            r1.setInterpolatedTime(r12)
            int r3 = r2.length
            double[] r3 = new double[r3]
            org.apache.commons.math3.ode.ExpandableStatefulODE r4 = r0.expandable
            org.apache.commons.math3.ode.EquationsMapper r4 = r4.getPrimaryMapper()
            double[] r7 = r18.getInterpolatedState()
            r4.insertEquationData(r7, r3)
            org.apache.commons.math3.ode.ExpandableStatefulODE r4 = r0.expandable
            org.apache.commons.math3.ode.EquationsMapper[] r4 = r4.getSecondaryMappers()
            int r7 = r4.length
            r14 = r10
            r15 = r14
        L_0x0094:
            if (r14 >= r7) goto L_0x00a7
            r8 = r4[r14]
            int r16 = r15 + 1
            double[] r15 = r1.getInterpolatedSecondaryState(r15)
            r8.insertEquationData(r15, r3)
            int r14 = r14 + 1
            r15 = r16
            r8 = 1
            goto L_0x0094
        L_0x00a7:
            java.util.Collection<org.apache.commons.math3.ode.events.EventState> r4 = r0.eventsStates
            java.util.Iterator r4 = r4.iterator()
        L_0x00ad:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x00cd
            java.lang.Object r7 = r4.next()
            org.apache.commons.math3.ode.events.EventState r7 = (org.apache.commons.math3.ode.events.EventState) r7
            r7.stepAccepted(r12, r3)
            boolean r8 = r0.isLastStep
            if (r8 != 0) goto L_0x00c9
            boolean r7 = r7.stop()
            if (r7 == 0) goto L_0x00c7
            goto L_0x00c9
        L_0x00c7:
            r7 = r10
            goto L_0x00ca
        L_0x00c9:
            r7 = 1
        L_0x00ca:
            r0.isLastStep = r7
            goto L_0x00ad
        L_0x00cd:
            java.util.Collection<org.apache.commons.math3.ode.sampling.StepHandler> r4 = r0.stepHandlers
            java.util.Iterator r4 = r4.iterator()
        L_0x00d3:
            boolean r7 = r4.hasNext()
            if (r7 == 0) goto L_0x00e5
            java.lang.Object r7 = r4.next()
            org.apache.commons.math3.ode.sampling.StepHandler r7 = (org.apache.commons.math3.ode.sampling.StepHandler) r7
            boolean r8 = r0.isLastStep
            r7.handleStep(r1, r8)
            goto L_0x00d3
        L_0x00e5:
            boolean r4 = r0.isLastStep
            if (r4 == 0) goto L_0x00ee
            int r1 = r2.length
            java.lang.System.arraycopy(r3, r10, r2, r10, r1)
            return r12
        L_0x00ee:
            java.util.Collection<org.apache.commons.math3.ode.events.EventState> r4 = r0.eventsStates
            java.util.Iterator r4 = r4.iterator()
        L_0x00f4:
            r7 = r10
        L_0x00f5:
            boolean r8 = r4.hasNext()
            if (r8 == 0) goto L_0x010b
            java.lang.Object r8 = r4.next()
            org.apache.commons.math3.ode.events.EventState r8 = (org.apache.commons.math3.ode.events.EventState) r8
            if (r7 != 0) goto L_0x0109
            boolean r7 = r8.reset(r12, r3)
            if (r7 == 0) goto L_0x00f4
        L_0x0109:
            r7 = 1
            goto L_0x00f5
        L_0x010b:
            if (r7 == 0) goto L_0x011d
            r1.setInterpolatedTime(r12)
            int r1 = r2.length
            java.lang.System.arraycopy(r3, r10, r2, r10, r1)
            r3 = r20
            r0.computeDerivatives(r12, r2, r3)
            r1 = 1
            r0.resetOccurred = r1
            return r12
        L_0x011d:
            r3 = r20
            r1.setSoftPreviousTime(r12)
            r1.setSoftCurrentTime(r5)
            boolean r4 = r11.evaluateStep(r1)
            if (r4 == 0) goto L_0x012e
            r9.add(r11)
        L_0x012e:
            r3 = r12
            r8 = 1
            goto L_0x005a
        L_0x0132:
            r1.setInterpolatedTime(r5)
            int r2 = r2.length
            double[] r2 = new double[r2]
            org.apache.commons.math3.ode.ExpandableStatefulODE r3 = r0.expandable
            org.apache.commons.math3.ode.EquationsMapper r3 = r3.getPrimaryMapper()
            double[] r4 = r18.getInterpolatedState()
            r3.insertEquationData(r4, r2)
            org.apache.commons.math3.ode.ExpandableStatefulODE r3 = r0.expandable
            org.apache.commons.math3.ode.EquationsMapper[] r3 = r3.getSecondaryMappers()
            int r4 = r3.length
            r7 = r10
            r8 = r7
        L_0x014e:
            if (r7 >= r4) goto L_0x015f
            r9 = r3[r7]
            int r11 = r8 + 1
            double[] r8 = r1.getInterpolatedSecondaryState(r8)
            r9.insertEquationData(r8, r2)
            int r7 = r7 + 1
            r8 = r11
            goto L_0x014e
        L_0x015f:
            java.util.Collection<org.apache.commons.math3.ode.events.EventState> r3 = r0.eventsStates
            java.util.Iterator r3 = r3.iterator()
        L_0x0165:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0185
            java.lang.Object r4 = r3.next()
            org.apache.commons.math3.ode.events.EventState r4 = (org.apache.commons.math3.ode.events.EventState) r4
            r4.stepAccepted(r5, r2)
            boolean r7 = r0.isLastStep
            if (r7 != 0) goto L_0x0181
            boolean r4 = r4.stop()
            if (r4 == 0) goto L_0x017f
            goto L_0x0181
        L_0x017f:
            r4 = r10
            goto L_0x0182
        L_0x0181:
            r4 = 1
        L_0x0182:
            r0.isLastStep = r4
            goto L_0x0165
        L_0x0185:
            boolean r2 = r0.isLastStep
            if (r2 != 0) goto L_0x0195
            r2 = r21
            r4 = 1
            boolean r2 = org.apache.commons.math3.util.Precision.equals((double) r5, (double) r2, (int) r4)
            if (r2 == 0) goto L_0x0193
            goto L_0x0196
        L_0x0193:
            r8 = r10
            goto L_0x0197
        L_0x0195:
            r4 = 1
        L_0x0196:
            r8 = r4
        L_0x0197:
            r0.isLastStep = r8
            java.util.Collection<org.apache.commons.math3.ode.sampling.StepHandler> r2 = r0.stepHandlers
            java.util.Iterator r2 = r2.iterator()
        L_0x019f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x01b1
            java.lang.Object r3 = r2.next()
            org.apache.commons.math3.ode.sampling.StepHandler r3 = (org.apache.commons.math3.ode.sampling.StepHandler) r3
            boolean r4 = r0.isLastStep
            r3.handleStep(r1, r4)
            goto L_0x019f
        L_0x01b1:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.AbstractIntegrator.acceptStep(org.apache.commons.math3.ode.sampling.AbstractStepInterpolator, double[], double[], double):double");
    }

    /* access modifiers changed from: protected */
    public void sanityChecks(ExpandableStatefulODE expandableStatefulODE, double d) throws NumberIsTooSmallException, DimensionMismatchException {
        double ulp = FastMath.ulp(FastMath.max(FastMath.abs(expandableStatefulODE.getTime()), FastMath.abs(d))) * 1000.0d;
        double abs = FastMath.abs(expandableStatefulODE.getTime() - d);
        if (abs <= ulp) {
            throw new NumberIsTooSmallException(LocalizedFormats.TOO_SMALL_INTEGRATION_INTERVAL, Double.valueOf(abs), Double.valueOf(ulp), false);
        }
    }
}
