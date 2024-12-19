package org.apache.commons.math3.ml.neuralnet.sofm;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.ml.neuralnet.sofm.util.ExponentialDecayFunction;
import org.apache.commons.math3.ml.neuralnet.sofm.util.QuasiSigmoidDecayFunction;

public class LearningFactorFunctionFactory {
    private LearningFactorFunctionFactory() {
    }

    public static LearningFactorFunction exponentialDecay(double d, double d2, long j) {
        if (d > 0.0d && d <= 1.0d) {
            return new LearningFactorFunction(d, d2, j) {
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

                public double value(long j) {
                    return this.decay.value(j);
                }
            };
        }
        throw new OutOfRangeException(Double.valueOf(d), 0, 1);
    }

    public static LearningFactorFunction quasiSigmoidDecay(double d, double d2, long j) {
        if (d > 0.0d && d <= 1.0d) {
            return new LearningFactorFunction(d, d2, j) {
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

                public double value(long j) {
                    return this.decay.value(j);
                }
            };
        }
        throw new OutOfRangeException(Double.valueOf(d), 0, 1);
    }
}
