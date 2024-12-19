package org.apache.commons.math3.ml.neuralnet;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Constant;
import org.apache.commons.math3.distribution.RealDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;
import org.apache.commons.math3.random.RandomGenerator;

public class FeatureInitializerFactory {
    private FeatureInitializerFactory() {
    }

    public static FeatureInitializer uniform(RandomGenerator randomGenerator, double d, double d2) {
        return randomize(new UniformRealDistribution(randomGenerator, d, d2), function(new Constant(0.0d), 0.0d, 0.0d));
    }

    public static FeatureInitializer uniform(double d, double d2) {
        return randomize(new UniformRealDistribution(d, d2), function(new Constant(0.0d), 0.0d, 0.0d));
    }

    public static FeatureInitializer function(UnivariateFunction univariateFunction, double d, double d2) {
        return new FeatureInitializer(d, univariateFunction, d2) {
            private double arg;
            final /* synthetic */ UnivariateFunction val$f;
            final /* synthetic */ double val$inc;
            final /* synthetic */ double val$init;

            {
                this.val$init = r1;
                this.val$f = r3;
                this.val$inc = r4;
                this.arg = r1;
            }

            public double value() {
                double value = this.val$f.value(this.arg);
                this.arg += this.val$inc;
                return value;
            }
        };
    }

    public static FeatureInitializer randomize(final RealDistribution realDistribution, final FeatureInitializer featureInitializer) {
        return new FeatureInitializer() {
            public double value() {
                return featureInitializer.value() + realDistribution.sample();
            }
        };
    }
}
