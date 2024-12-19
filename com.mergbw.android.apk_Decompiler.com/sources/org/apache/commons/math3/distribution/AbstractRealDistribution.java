package org.apache.commons.math3.distribution;

import java.io.Serializable;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomDataImpl;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public abstract class AbstractRealDistribution implements RealDistribution, Serializable {
    public static final double SOLVER_DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final long serialVersionUID = -38038050983108802L;
    protected final RandomGenerator random;
    @Deprecated
    protected RandomDataImpl randomData;
    private double solverAbsoluteAccuracy;

    public double probability(double d) {
        return 0.0d;
    }

    @Deprecated
    protected AbstractRealDistribution() {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = null;
    }

    protected AbstractRealDistribution(RandomGenerator randomGenerator) {
        this.randomData = new RandomDataImpl();
        this.solverAbsoluteAccuracy = 1.0E-6d;
        this.random = randomGenerator;
    }

    @Deprecated
    public double cumulativeProbability(double d, double d2) throws NumberIsTooLargeException {
        return probability(d, d2);
    }

    public double probability(double d, double d2) {
        if (d <= d2) {
            return cumulativeProbability(d2) - cumulativeProbability(d);
        }
        throw new NumberIsTooLargeException(LocalizedFormats.LOWER_ENDPOINT_ABOVE_UPPER_ENDPOINT, Double.valueOf(d), Double.valueOf(d2), true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x00a4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double inverseCumulativeProbability(double r26) throws org.apache.commons.math3.exception.OutOfRangeException {
        /*
            r25 = this;
            r0 = r25
            r1 = r26
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            r6 = 1
            r7 = 0
            if (r5 < 0) goto L_0x00d6
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r5 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r5 > 0) goto L_0x00d6
            double r10 = r25.getSupportLowerBound()
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x001b
            return r10
        L_0x001b:
            double r3 = r25.getSupportUpperBound()
            if (r5 != 0) goto L_0x0022
            return r3
        L_0x0022:
            double r12 = r25.getNumericalMean()
            double r14 = r25.getNumericalVariance()
            double r14 = org.apache.commons.math3.util.FastMath.sqrt(r14)
            boolean r5 = java.lang.Double.isInfinite(r12)
            if (r5 != 0) goto L_0x0047
            boolean r5 = java.lang.Double.isNaN(r12)
            if (r5 != 0) goto L_0x0047
            boolean r5 = java.lang.Double.isInfinite(r14)
            if (r5 != 0) goto L_0x0047
            boolean r5 = java.lang.Double.isNaN(r14)
            if (r5 != 0) goto L_0x0047
            goto L_0x0048
        L_0x0047:
            r6 = r7
        L_0x0048:
            r16 = -4503599627370496(0xfff0000000000000, double:-Infinity)
            int r5 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            r16 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r5 != 0) goto L_0x006a
            if (r6 == 0) goto L_0x005d
            double r10 = r8 - r1
            double r10 = r10 / r1
            double r10 = org.apache.commons.math3.util.FastMath.sqrt(r10)
            double r10 = r10 * r14
            double r10 = r12 - r10
            goto L_0x006a
        L_0x005d:
            r10 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x005f:
            double r18 = r0.cumulativeProbability(r10)
            int r5 = (r18 > r1 ? 1 : (r18 == r1 ? 0 : -1))
            if (r5 < 0) goto L_0x006a
            double r10 = r10 * r16
            goto L_0x005f
        L_0x006a:
            r18 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            int r5 = (r3 > r18 ? 1 : (r3 == r18 ? 0 : -1))
            if (r5 != 0) goto L_0x008b
            if (r6 == 0) goto L_0x007d
            double r8 = r8 - r1
            double r3 = r1 / r8
            double r3 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            double r14 = r14 * r3
            double r3 = r12 + r14
            goto L_0x008b
        L_0x007d:
            double r3 = r0.cumulativeProbability(r8)
            int r3 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r3 >= 0) goto L_0x0088
            double r8 = r8 * r16
            goto L_0x007d
        L_0x0088:
            r21 = r8
            goto L_0x008d
        L_0x008b:
            r21 = r3
        L_0x008d:
            org.apache.commons.math3.distribution.AbstractRealDistribution$1 r3 = new org.apache.commons.math3.distribution.AbstractRealDistribution$1
            r3.<init>(r1)
            double r23 = r25.getSolverAbsoluteAccuracy()
            r18 = r3
            r19 = r10
            double r1 = org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.solve(r18, r19, r21, r23)
            boolean r3 = r25.isSupportConnected()
            if (r3 != 0) goto L_0x00d5
            double r3 = r25.getSolverAbsoluteAccuracy()
            double r5 = r1 - r3
            double r7 = r25.getSupportLowerBound()
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x00d5
            double r7 = r0.cumulativeProbability(r1)
            double r5 = r0.cumulativeProbability(r5)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 != 0) goto L_0x00d5
        L_0x00be:
            double r5 = r1 - r10
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x00d5
            r5 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r12 = r10 + r1
            double r12 = r12 * r5
            double r5 = r0.cumulativeProbability(r12)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L_0x00d3
            r10 = r12
            goto L_0x00be
        L_0x00d3:
            r1 = r12
            goto L_0x00be
        L_0x00d5:
            return r1
        L_0x00d6:
            org.apache.commons.math3.exception.OutOfRangeException r3 = new org.apache.commons.math3.exception.OutOfRangeException
            java.lang.Double r1 = java.lang.Double.valueOf(r26)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            r3.<init>(r1, r2, r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.distribution.AbstractRealDistribution.inverseCumulativeProbability(double):double");
    }

    /* access modifiers changed from: protected */
    public double getSolverAbsoluteAccuracy() {
        return this.solverAbsoluteAccuracy;
    }

    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
        this.randomData.reSeed(j);
    }

    public double sample() {
        return inverseCumulativeProbability(this.random.nextDouble());
    }

    public double[] sample(int i) {
        if (i > 0) {
            double[] dArr = new double[i];
            for (int i2 = 0; i2 < i; i2++) {
                dArr[i2] = sample();
            }
            return dArr;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }

    public double logDensity(double d) {
        return FastMath.log(density(d));
    }
}
