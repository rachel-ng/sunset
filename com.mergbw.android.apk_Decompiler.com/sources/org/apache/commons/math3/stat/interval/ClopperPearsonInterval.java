package org.apache.commons.math3.stat.interval;

import org.apache.commons.math3.distribution.FDistribution;

public class ClopperPearsonInterval implements BinomialConfidenceInterval {
    public ConfidenceInterval createInterval(int i, int i2, double d) {
        double d2;
        double d3;
        int i3 = i2;
        IntervalUtils.checkParameters(i, i2, d);
        int i4 = i - i3;
        int i5 = i4 + 1;
        double d4 = 1.0d - ((1.0d - d) / 2.0d);
        double inverseCumulativeProbability = new FDistribution((double) (i5 * 2), (double) (i3 * 2)).inverseCumulativeProbability(d4);
        if (i3 > 0) {
            double d5 = (double) i3;
            d2 = d5 / ((((double) i5) * inverseCumulativeProbability) + d5);
        } else {
            d2 = 0.0d;
        }
        int i6 = i3 + 1;
        double inverseCumulativeProbability2 = new FDistribution((double) (i6 * 2), (double) (i4 * 2)).inverseCumulativeProbability(d4);
        if (i3 > 0) {
            double d6 = ((double) i6) * inverseCumulativeProbability2;
            d3 = d6 / (((double) i4) + d6);
        } else {
            d3 = 0.0d;
        }
        return new ConfidenceInterval(d2, d3, d);
    }
}
