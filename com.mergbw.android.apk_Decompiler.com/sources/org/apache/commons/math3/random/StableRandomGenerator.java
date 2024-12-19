package org.apache.commons.math3.random;

import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class StableRandomGenerator implements NormalizedRandomGenerator {
    private final double alpha;
    private final double beta;
    private final RandomGenerator generator;
    private final double zeta;

    public StableRandomGenerator(RandomGenerator randomGenerator, double d, double d2) throws NullArgumentException, OutOfRangeException {
        int i;
        if (randomGenerator == null) {
            throw new NullArgumentException();
        } else if (d <= 0.0d || d > 2.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_LEFT, Double.valueOf(d), 0, 2);
        } else if (d2 < -1.0d || d2 > 1.0d) {
            throw new OutOfRangeException(LocalizedFormats.OUT_OF_RANGE_SIMPLE, Double.valueOf(d2), -1, 1);
        } else {
            this.generator = randomGenerator;
            this.alpha = d;
            this.beta = d2;
            if (i >= 0 || d2 == 0.0d) {
                this.zeta = 0.0d;
            } else {
                this.zeta = d2 * FastMath.tan((d * 3.141592653589793d) / 2.0d);
            }
        }
    }

    public double nextNormalizedDouble() {
        double d = -FastMath.log(this.generator.nextDouble());
        double nextDouble = (this.generator.nextDouble() - 0.5d) * 3.141592653589793d;
        double d2 = this.alpha;
        if (d2 == 2.0d) {
            return FastMath.sqrt(d * 2.0d) * FastMath.sin(nextDouble);
        }
        if (this.beta != 0.0d) {
            double cos = FastMath.cos(nextDouble);
            if (FastMath.abs(this.alpha - 1.0d) > 1.0E-8d) {
                double d3 = this.alpha * nextDouble;
                double d4 = nextDouble - d3;
                double d5 = this.alpha;
                return (((FastMath.sin(d3) + (this.zeta * FastMath.cos(d3))) / cos) * (FastMath.cos(d4) + (this.zeta * FastMath.sin(d4)))) / FastMath.pow(d * cos, (1.0d - d5) / d5);
            }
            double d6 = (this.beta * nextDouble) + 1.5707963267948966d;
            double tan = 0.6366197723675814d * ((FastMath.tan(nextDouble) * d6) - (this.beta * FastMath.log(((d * 1.5707963267948966d) * cos) / d6)));
            double d7 = this.alpha;
            return d7 != 1.0d ? tan + (this.beta * FastMath.tan((d7 * 3.141592653589793d) / 2.0d)) : tan;
        } else if (d2 == 1.0d) {
            return FastMath.tan(nextDouble);
        } else {
            return (FastMath.pow(d * FastMath.cos((1.0d - d2) * nextDouble), (1.0d / this.alpha) - 1.0d) * FastMath.sin(this.alpha * nextDouble)) / FastMath.pow(FastMath.cos(nextDouble), 1.0d / this.alpha);
        }
    }
}
