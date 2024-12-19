package org.apache.commons.math3.ode.events;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver;
import org.apache.commons.math3.analysis.solvers.PegasusSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.ode.EquationsMapper;
import org.apache.commons.math3.ode.ExpandableStatefulODE;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;
import org.apache.commons.math3.util.FastMath;

public class EventState {
    private final double convergence;
    private ExpandableStatefulODE expandable = null;
    private boolean forward;
    private double g0 = Double.NaN;
    private boolean g0Positive = true;
    /* access modifiers changed from: private */
    public final EventHandler handler;
    private boolean increasing = true;
    private final double maxCheckInterval;
    private final int maxIterationCount;
    private EventHandler.Action nextAction = EventHandler.Action.CONTINUE;
    private boolean pendingEvent = false;
    private double pendingEventTime = Double.NaN;
    private double previousEventTime = Double.NaN;
    private final UnivariateSolver solver;
    private double t0 = Double.NaN;

    public EventState(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        this.handler = eventHandler;
        this.maxCheckInterval = d;
        this.convergence = FastMath.abs(d2);
        this.maxIterationCount = i;
        this.solver = univariateSolver;
    }

    public EventHandler getEventHandler() {
        return this.handler;
    }

    public void setExpandable(ExpandableStatefulODE expandableStatefulODE) {
        this.expandable = expandableStatefulODE;
    }

    public double getMaxCheckInterval() {
        return this.maxCheckInterval;
    }

    public double getConvergence() {
        return this.convergence;
    }

    public int getMaxIterationCount() {
        return this.maxIterationCount;
    }

    public void reinitializeBegin(StepInterpolator stepInterpolator) throws MaxCountExceededException {
        double previousTime = stepInterpolator.getPreviousTime();
        this.t0 = previousTime;
        stepInterpolator.setInterpolatedTime(previousTime);
        double g = this.handler.g(this.t0, getCompleteState(stepInterpolator));
        this.g0 = g;
        if (g == 0.0d) {
            double max = this.t0 + (FastMath.max(this.solver.getAbsoluteAccuracy(), FastMath.abs(this.solver.getRelativeAccuracy() * this.t0)) * 0.5d);
            stepInterpolator.setInterpolatedTime(max);
            this.g0 = this.handler.g(max, getCompleteState(stepInterpolator));
        }
        this.g0Positive = this.g0 >= 0.0d;
    }

    /* access modifiers changed from: private */
    public double[] getCompleteState(StepInterpolator stepInterpolator) {
        double[] dArr = new double[this.expandable.getTotalDimension()];
        this.expandable.getPrimaryMapper().insertEquationData(stepInterpolator.getInterpolatedState(), dArr);
        EquationsMapper[] secondaryMappers = this.expandable.getSecondaryMappers();
        int length = secondaryMappers.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            secondaryMappers[i].insertEquationData(stepInterpolator.getInterpolatedSecondaryState(i2), dArr);
            i++;
            i2++;
        }
        return dArr;
    }

    public boolean evaluateStep(StepInterpolator stepInterpolator) throws MaxCountExceededException, NoBracketingException {
        int i;
        AnonymousClass1 r5;
        int i2;
        double d;
        double d2;
        double d3;
        double d4;
        double d5;
        final StepInterpolator stepInterpolator2 = stepInterpolator;
        try {
            this.forward = stepInterpolator.isForward();
            double currentTime = stepInterpolator.getCurrentTime() - this.t0;
            boolean z = false;
            if (FastMath.abs(currentTime) < this.convergence) {
                return false;
            }
            boolean z2 = true;
            int max = FastMath.max(1, (int) FastMath.ceil(FastMath.abs(currentTime) / this.maxCheckInterval));
            double d6 = currentTime / ((double) max);
            AnonymousClass1 r15 = new UnivariateFunction() {
                public double value(double d) throws LocalMaxCountExceededException {
                    try {
                        stepInterpolator2.setInterpolatedTime(d);
                        return EventState.this.handler.g(d, EventState.this.getCompleteState(stepInterpolator2));
                    } catch (MaxCountExceededException e) {
                        throw new LocalMaxCountExceededException(e);
                    }
                }
            };
            double d7 = this.t0;
            double d8 = this.g0;
            int i3 = 0;
            double d9 = d7;
            while (i < max) {
                double d10 = this.t0 + (((double) (i + 1)) * d6);
                stepInterpolator2.setInterpolatedTime(d10);
                double g = this.handler.g(d10, getCompleteState(stepInterpolator));
                if (this.g0Positive ^ (g >= 0.0d ? z2 : z)) {
                    this.increasing = g >= d8 ? z2 : z;
                    UnivariateSolver univariateSolver = this.solver;
                    if (univariateSolver instanceof BracketedUnivariateSolver) {
                        BracketedUnivariateSolver bracketedUnivariateSolver = (BracketedUnivariateSolver) univariateSolver;
                        if (this.forward) {
                            d = d10;
                            i2 = i;
                            d2 = bracketedUnivariateSolver.solve(this.maxIterationCount, r15, d9, d10, AllowedSolution.RIGHT_SIDE);
                        } else {
                            d = d10;
                            i2 = i;
                            d2 = bracketedUnivariateSolver.solve(this.maxIterationCount, r15, d, d9, AllowedSolution.LEFT_SIDE);
                        }
                        r5 = r15;
                    } else {
                        d = d10;
                        i2 = i;
                        double solve = this.forward ? univariateSolver.solve(this.maxIterationCount, r15, d9, d) : univariateSolver.solve(this.maxIterationCount, r15, d, d9);
                        int evaluations = this.maxIterationCount - this.solver.getEvaluations();
                        PegasusSolver pegasusSolver = new PegasusSolver(this.solver.getRelativeAccuracy(), this.solver.getAbsoluteAccuracy());
                        if (this.forward) {
                            r5 = r15;
                            d5 = UnivariateSolverUtils.forceSide(evaluations, r15, pegasusSolver, solve, d9, d, AllowedSolution.RIGHT_SIDE);
                        } else {
                            r5 = r15;
                            d5 = UnivariateSolverUtils.forceSide(evaluations, r5, pegasusSolver, solve, d, d9, AllowedSolution.LEFT_SIDE);
                        }
                        d2 = d5;
                    }
                    if (Double.isNaN(this.previousEventTime) || FastMath.abs(d2 - d9) > this.convergence || FastMath.abs(d2 - this.previousEventTime) > this.convergence) {
                        if (!Double.isNaN(this.previousEventTime)) {
                            if (FastMath.abs(this.previousEventTime - d2) <= this.convergence) {
                                d3 = d;
                                i = i2;
                            }
                        }
                        this.pendingEventTime = d2;
                        this.pendingEvent = true;
                        return true;
                    }
                    while (true) {
                        d4 = this.forward ? d9 + this.convergence : d9 - this.convergence;
                        g = r5.value(d4);
                        if (!(this.g0Positive ^ (g >= 0.0d))) {
                            break;
                        }
                        if (!(this.forward ^ (d4 >= d))) {
                            break;
                        }
                        d9 = d4;
                    }
                    i = i2 - 1;
                    d3 = d4;
                    d9 = d3;
                    d8 = g;
                    z2 = true;
                } else {
                    int i4 = i;
                    r5 = r15;
                    d8 = g;
                    d9 = d10;
                }
                i3 = i + (z2 ? 1 : 0);
                r15 = r5;
                z = false;
            }
            boolean z3 = z;
            this.pendingEvent = z3;
            this.pendingEventTime = Double.NaN;
            return z3;
        } catch (LocalMaxCountExceededException e) {
            throw e.getException();
        }
    }

    public double getEventTime() {
        if (this.pendingEvent) {
            return this.pendingEventTime;
        }
        return this.forward ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY;
    }

    public void stepAccepted(double d, double[] dArr) {
        this.t0 = d;
        this.g0 = this.handler.g(d, dArr);
        boolean z = true;
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - d) > this.convergence) {
            if (this.g0 < 0.0d) {
                z = false;
            }
            this.g0Positive = z;
            this.nextAction = EventHandler.Action.CONTINUE;
            return;
        }
        this.previousEventTime = d;
        boolean z2 = this.increasing;
        this.g0Positive = z2;
        this.nextAction = this.handler.eventOccurred(d, dArr, !(z2 ^ this.forward));
    }

    public boolean stop() {
        return this.nextAction == EventHandler.Action.STOP;
    }

    public boolean reset(double d, double[] dArr) {
        if (!this.pendingEvent || FastMath.abs(this.pendingEventTime - d) > this.convergence) {
            return false;
        }
        if (this.nextAction == EventHandler.Action.RESET_STATE) {
            this.handler.resetState(d, dArr);
        }
        this.pendingEvent = false;
        this.pendingEventTime = Double.NaN;
        if (this.nextAction == EventHandler.Action.RESET_STATE || this.nextAction == EventHandler.Action.RESET_DERIVATIVES) {
            return true;
        }
        return false;
    }

    private static class LocalMaxCountExceededException extends RuntimeException {
        private static final long serialVersionUID = 20120901;
        private final MaxCountExceededException wrapped;

        public LocalMaxCountExceededException(MaxCountExceededException maxCountExceededException) {
            this.wrapped = maxCountExceededException;
        }

        public MaxCountExceededException getException() {
            return this.wrapped;
        }
    }
}
