package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

public class AgrestiCoullInterval implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int i, int i2, double d) {
        IntervalUtils.checkParameters(i, i2, d);
        double inverseCumulativeProbability = new NormalDistribution().inverseCumulativeProbability(1.0d - ((1.0d - d) / 2.0d));
        double pow = FastMath.pow(inverseCumulativeProbability, 2);
        double d2 = 1.0d / (((double) i) + pow);
        double d3 = (((double) i2) + (pow * 0.5d)) * d2;
        double sqrt = inverseCumulativeProbability * FastMath.sqrt(d2 * d3 * (1.0d - d3));
        return new ConfidenceInterval(d3 - sqrt, d3 + sqrt, d);
    }
}
