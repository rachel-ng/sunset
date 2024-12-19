package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;
import org.apache.commons.math3.util.FastMath;

public class NeighbourhoodSizeFunctionFactory {
    private NeighbourhoodSizeFunctionFactory() {
    }

    public static NeighbourhoodSizeFunction exponentialDecay(double d, double d2, long j) {
        return new NeighbourhoodSizeFunction(d, d2, j) {
            private final ExponentialDecayFunction decay;
            final /* synthetic */ double val$initValue;
            final /* synthetic */ long val$numCall;
            final /* synthetic */ double val$valueAtNumCall;

            {
                this.val$initValue = r9;
                this.val$valueAtNumCall = r11;
                this.val$numCall = r13;
                this.decay = new ExponentialDecayFunction(r9, r11, r13);
            }

            public int value(long j) {
                return (int) FastMath.rint(this.decay.value(j));
            }
        };
    }

    public static NeighbourhoodSizeFunction quasiSigmoidDecay(double d, double d2, long j) {
        return new NeighbourhoodSizeFunction(d, d2, j) {
            private final QuasiSigmoidDecayFunction decay;
            final /* synthetic */ double val$initValue;
            final /* synthetic */ long val$numCall;
            final /* synthetic */ double val$slope;

            {
                this.val$initValue = r9;
                this.val$slope = r11;
                this.val$numCall = r13;
                this.decay = new QuasiSigmoidDecayFunction(r9, r11, r13);
            }

            public int value(long j) {
                return (int) FastMath.rint(this.decay.value(j));
            }
        };
    }
}
