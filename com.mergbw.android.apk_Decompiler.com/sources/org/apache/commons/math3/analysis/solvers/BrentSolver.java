package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.FastMath;

public class BrentSolver extends AbstractUnivariateSolver {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;

    public BrentSolver() {
        this(1.0E-6d);
    }

    public BrentSolver(double d) {
        super(d);
    }

    public BrentSolver(double d, double d2) {
        super(d, d2);
    }

    public BrentSolver(double d, double d2, double d3) {
        super(d, d2, d3);
    }

    /* access modifiers changed from: protected */
    public double doSolve() throws NoBracketingException, TooManyEvaluationsException, NumberIsTooLargeException {
        double min = getMin();
        double max = getMax();
        double startValue = getStartValue();
        double functionValueAccuracy = getFunctionValueAccuracy();
        verifySequence(min, startValue, max);
        double computeObjectiveValue = computeObjectiveValue(startValue);
        if (FastMath.abs(computeObjectiveValue) <= functionValueAccuracy) {
            return startValue;
        }
        double computeObjectiveValue2 = computeObjectiveValue(min);
        if (FastMath.abs(computeObjectiveValue2) <= functionValueAccuracy) {
            return min;
        }
        if (computeObjectiveValue * computeObjectiveValue2 < 0.0d) {
            return brent(min, startValue, computeObjectiveValue2, computeObjectiveValue);
        }
        double computeObjectiveValue3 = computeObjectiveValue(max);
        if (FastMath.abs(computeObjectiveValue3) <= functionValueAccuracy) {
            return max;
        }
        if (computeObjectiveValue * computeObjectiveValue3 < 0.0d) {
            return brent(startValue, max, computeObjectiveValue, computeObjectiveValue3);
        }
        throw new NoBracketingException(min, max, computeObjectiveValue2, computeObjectiveValue3);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00d2  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0103  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double brent(double r44, double r46, double r48, double r50) {
        /*
            r43 = this;
            double r0 = r46 - r44
            double r2 = r43.getAbsoluteAccuracy()
            double r4 = r43.getRelativeAccuracy()
            r6 = r44
            r8 = r46
            r10 = r48
            r12 = r10
            r14 = r50
            r16 = r0
            r18 = r16
            r0 = r6
        L_0x0018:
            double r20 = org.apache.commons.math3.util.FastMath.abs((double) r10)
            double r22 = org.apache.commons.math3.util.FastMath.abs((double) r14)
            int r20 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1))
            if (r20 >= 0) goto L_0x0029
            r6 = r0
            r0 = r8
            r12 = r10
            r10 = r14
            goto L_0x0033
        L_0x0029:
            r39 = r12
            r12 = r14
            r14 = r39
            r41 = r6
            r6 = r8
            r8 = r41
        L_0x0033:
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r22 = r4 * r20
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r6)
            double r22 = r22 * r24
            double r22 = r22 + r2
            double r24 = r0 - r6
            r26 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r24 = r24 * r26
            double r28 = org.apache.commons.math3.util.FastMath.abs((double) r24)
            int r28 = (r28 > r22 ? 1 : (r28 == r22 ? 0 : -1))
            if (r28 <= 0) goto L_0x0113
            r28 = r2
            r2 = 0
            boolean r30 = org.apache.commons.math3.util.Precision.equals((double) r12, (double) r2)
            if (r30 == 0) goto L_0x0059
            goto L_0x0113
        L_0x0059:
            double r30 = org.apache.commons.math3.util.FastMath.abs((double) r16)
            int r30 = (r30 > r22 ? 1 : (r30 == r22 ? 0 : -1))
            if (r30 < 0) goto L_0x00c6
            double r30 = org.apache.commons.math3.util.FastMath.abs((double) r14)
            double r32 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            int r30 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r30 > 0) goto L_0x006e
            goto L_0x00c6
        L_0x006e:
            double r30 = r12 / r14
            int r32 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            r33 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r32 != 0) goto L_0x007d
            double r20 = r20 * r24
            double r20 = r20 * r30
            double r33 = r33 - r30
            goto L_0x009a
        L_0x007d:
            double r14 = r14 / r10
            double r35 = r12 / r10
            double r20 = r20 * r24
            double r20 = r20 * r14
            double r37 = r14 - r35
            double r20 = r20 * r37
            double r8 = r6 - r8
            double r35 = r35 - r33
            double r8 = r8 * r35
            double r20 = r20 - r8
            double r20 = r20 * r30
            double r14 = r14 - r33
            double r14 = r14 * r35
            double r30 = r30 - r33
            double r33 = r14 * r30
        L_0x009a:
            r8 = r20
            r14 = r33
            int r20 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r20 <= 0) goto L_0x00a4
            double r14 = -r14
            goto L_0x00a5
        L_0x00a4:
            double r8 = -r8
        L_0x00a5:
            r20 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r20 = r20 * r24
            double r20 = r20 * r14
            double r30 = r22 * r14
            double r30 = org.apache.commons.math3.util.FastMath.abs((double) r30)
            double r20 = r20 - r30
            int r20 = (r8 > r20 ? 1 : (r8 == r20 ? 0 : -1))
            if (r20 >= 0) goto L_0x00c6
            double r16 = r16 * r26
            double r16 = r16 * r14
            double r16 = org.apache.commons.math3.util.FastMath.abs((double) r16)
            int r16 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1))
            if (r16 < 0) goto L_0x00c4
            goto L_0x00c6
        L_0x00c4:
            double r8 = r8 / r14
            goto L_0x00ca
        L_0x00c6:
            r8 = r24
            r18 = r8
        L_0x00ca:
            double r14 = org.apache.commons.math3.util.FastMath.abs((double) r8)
            int r14 = (r14 > r22 ? 1 : (r14 == r22 ? 0 : -1))
            if (r14 <= 0) goto L_0x00d9
            double r14 = r6 + r8
        L_0x00d4:
            r44 = r0
        L_0x00d6:
            r0 = r43
            goto L_0x00e7
        L_0x00d9:
            int r14 = (r24 > r2 ? 1 : (r24 == r2 ? 0 : -1))
            if (r14 <= 0) goto L_0x00e4
            double r22 = r6 + r22
            r44 = r0
            r14 = r22
            goto L_0x00d6
        L_0x00e4:
            double r14 = r6 - r22
            goto L_0x00d4
        L_0x00e7:
            double r16 = r0.computeObjectiveValue(r14)
            int r1 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r1 <= 0) goto L_0x00f3
            int r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x00fb
        L_0x00f3:
            int r1 = (r16 > r2 ? 1 : (r16 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x0103
            int r1 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r1 > 0) goto L_0x0103
        L_0x00fb:
            double r1 = r14 - r6
            r8 = r1
            r18 = r8
            r1 = r6
            r10 = r12
            goto L_0x0105
        L_0x0103:
            r1 = r44
        L_0x0105:
            r0 = r1
            r2 = r28
            r39 = r16
            r16 = r18
            r18 = r8
            r8 = r14
            r14 = r39
            goto L_0x0018
        L_0x0113:
            r0 = r43
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BrentSolver.brent(double, double, double, double):double");
    }
}
