package org.apache.commons.math3.ode.sampling;

public class DummyStepHandler implements StepHandler {
    public void handleStep(StepInterpolator stepInterpolator, boolean z) {
    }

    public void init(double d, double[] dArr, double d2) {
    }

    private DummyStepHandler() {
    }

    public static DummyStepHandler getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final DummyStepHandler INSTANCE = new DummyStepHandler();

        private LazyHolder() {
        }
    }

    private Object readResolve() {
        return LazyHolder.INSTANCE;
    }
}
