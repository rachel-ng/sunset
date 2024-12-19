package org.apache.commons.math3.optim.univariate;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.util.FastMath;

public class BrentOptimizer extends UnivariateOptimizer {
    private static final double GOLDEN_SECTION = ((3.0d - FastMath.sqrt(5.0d)) * 0.5d);
    private static final double MIN_RELATIVE_TOLERANCE = (FastMath.ulp(1.0d) * 2.0d);
    private final double absoluteThreshold;
    private final double relativeThreshold;

    public BrentOptimizer(double d, double d2, ConvergenceChecker<UnivariatePointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d3 = MIN_RELATIVE_TOLERANCE;
        if (d < d3) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d3), true);
        } else if (d2 > 0.0d) {
            this.relativeThreshold = d;
            this.absoluteThreshold = d2;
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
    }

    public BrentOptimizer(double d, double d2) {
        this(d, d2, (ConvergenceChecker<UnivariatePointValuePair>) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x012c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0166  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.optim.univariate.UnivariatePointValuePair doOptimize() {
        /*
            r49 = this;
            r0 = r49
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r1 = r49.getGoalType()
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r2 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            if (r1 != r2) goto L_0x000c
            r1 = 1
            goto L_0x000d
        L_0x000c:
            r1 = 0
        L_0x000d:
            double r2 = r49.getMin()
            double r4 = r49.getStartValue()
            double r6 = r49.getMax()
            org.apache.commons.math3.optim.ConvergenceChecker r8 = r49.getConvergenceChecker()
            int r9 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r9 >= 0) goto L_0x0022
            goto L_0x0027
        L_0x0022:
            r47 = r2
            r2 = r6
            r6 = r47
        L_0x0027:
            double r9 = r0.computeObjectiveValue(r4)
            if (r1 != 0) goto L_0x002e
            double r9 = -r9
        L_0x002e:
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r11 = new org.apache.commons.math3.optim.univariate.UnivariatePointValuePair
            if (r1 == 0) goto L_0x0034
            r12 = r9
            goto L_0x0035
        L_0x0034:
            double r12 = -r9
        L_0x0035:
            r11.<init>(r4, r12)
            r14 = 0
            r16 = r9
            r18 = r16
            r20 = r18
            r12 = r11
            r13 = r14
            r24 = 0
            r26 = 0
            r9 = r4
            r14 = r6
            r6 = r9
        L_0x0048:
            double r28 = r2 + r14
            r30 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r28 = r28 * r30
            r32 = r8
            r33 = r9
            double r8 = r0.relativeThreshold
            double r35 = org.apache.commons.math3.util.FastMath.abs((double) r4)
            double r8 = r8 * r35
            r35 = r6
            double r6 = r0.absoluteThreshold
            double r8 = r8 + r6
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r37 = r8 * r6
            double r39 = r4 - r28
            double r39 = org.apache.commons.math3.util.FastMath.abs((double) r39)
            double r41 = r14 - r2
            double r41 = r41 * r30
            double r41 = r37 - r41
            int r10 = (r39 > r41 ? 1 : (r39 == r41 ? 0 : -1))
            if (r10 > 0) goto L_0x007c
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r2 = r0.best(r13, r12, r1)
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r1 = r0.best(r11, r2, r1)
            return r1
        L_0x007c:
            double r39 = org.apache.commons.math3.util.FastMath.abs((double) r24)
            int r10 = (r39 > r8 ? 1 : (r39 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x00f5
            double r39 = r4 - r35
            double r41 = r16 - r18
            double r41 = r41 * r39
            double r43 = r4 - r33
            double r45 = r16 - r20
            double r45 = r45 * r43
            double r43 = r43 * r45
            double r39 = r39 * r41
            r13 = r11
            double r10 = r43 - r39
            double r45 = r45 - r41
            double r6 = r6 * r45
            r22 = 0
            int r39 = (r6 > r22 ? 1 : (r6 == r22 ? 0 : -1))
            if (r39 <= 0) goto L_0x00a3
            double r10 = -r10
            goto L_0x00a4
        L_0x00a3:
            double r6 = -r6
        L_0x00a4:
            double r39 = r2 - r4
            double r41 = r6 * r39
            int r41 = (r10 > r41 ? 1 : (r10 == r41 ? 0 : -1))
            if (r41 <= 0) goto L_0x00e6
            double r41 = r14 - r4
            double r41 = r41 * r6
            int r41 = (r10 > r41 ? 1 : (r10 == r41 ? 0 : -1))
            if (r41 >= 0) goto L_0x00e6
            double r41 = org.apache.commons.math3.util.FastMath.abs((double) r10)
            double r30 = r30 * r6
            double r30 = r30 * r24
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r30)
            int r24 = (r41 > r24 ? 1 : (r41 == r24 ? 0 : -1))
            if (r24 >= 0) goto L_0x00e6
            double r10 = r10 / r6
            double r6 = r4 + r10
            double r24 = r6 - r2
            int r24 = (r24 > r37 ? 1 : (r24 == r37 ? 0 : -1))
            if (r24 < 0) goto L_0x00d7
            double r6 = r14 - r6
            int r6 = (r6 > r37 ? 1 : (r6 == r37 ? 0 : -1))
            if (r6 >= 0) goto L_0x00d4
            goto L_0x00d7
        L_0x00d4:
            r24 = r26
            goto L_0x0104
        L_0x00d7:
            int r6 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r6 > 0) goto L_0x00e0
            r24 = r26
            r26 = r8
            goto L_0x0106
        L_0x00e0:
            double r6 = -r8
            r24 = r26
            r26 = r6
            goto L_0x0106
        L_0x00e6:
            int r6 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r6 >= 0) goto L_0x00ec
            double r39 = r14 - r4
        L_0x00ec:
            double r6 = GOLDEN_SECTION
            double r6 = r6 * r39
            r26 = r6
            r24 = r39
            goto L_0x0106
        L_0x00f5:
            r13 = r11
            int r6 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r6 >= 0) goto L_0x00fd
            double r6 = r14 - r4
            goto L_0x00ff
        L_0x00fd:
            double r6 = r2 - r4
        L_0x00ff:
            double r10 = GOLDEN_SECTION
            double r10 = r10 * r6
            r24 = r6
        L_0x0104:
            r26 = r10
        L_0x0106:
            double r6 = org.apache.commons.math3.util.FastMath.abs((double) r26)
            int r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r6 >= 0) goto L_0x0119
            r6 = 0
            int r10 = (r26 > r6 ? 1 : (r26 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x0116
            double r8 = r8 + r4
            goto L_0x011d
        L_0x0116:
            double r8 = r4 - r8
            goto L_0x011d
        L_0x0119:
            r6 = 0
            double r8 = r4 + r26
        L_0x011d:
            double r10 = r0.computeObjectiveValue(r8)
            if (r1 != 0) goto L_0x0124
            double r10 = -r10
        L_0x0124:
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r6 = new org.apache.commons.math3.optim.univariate.UnivariatePointValuePair
            r28 = r2
            if (r1 == 0) goto L_0x012c
            r2 = r10
            goto L_0x012d
        L_0x012c:
            double r2 = -r10
        L_0x012d:
            r6.<init>(r8, r2)
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r2 = r0.best(r12, r6, r1)
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r2 = r0.best(r13, r2, r1)
            if (r32 == 0) goto L_0x0147
            int r3 = r49.getIterations()
            r7 = r32
            boolean r3 = r7.converged(r3, r12, r6)
            if (r3 == 0) goto L_0x0149
            return r2
        L_0x0147:
            r7 = r32
        L_0x0149:
            int r3 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r3 > 0) goto L_0x0166
            int r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x0153
            r14 = r4
            goto L_0x0155
        L_0x0153:
            r28 = r4
        L_0x0155:
            r30 = r1
            r13 = r2
            r0 = r4
            r4 = r8
            r18 = r20
            r2 = r28
            r20 = r16
            r16 = r10
            r9 = r35
            goto L_0x01b2
        L_0x0166:
            int r3 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x016d
            r28 = r8
            goto L_0x016e
        L_0x016d:
            r14 = r8
        L_0x016e:
            int r3 = (r10 > r20 ? 1 : (r10 == r20 ? 0 : -1))
            if (r3 <= 0) goto L_0x01a2
            r3 = r1
            r0 = r35
            boolean r13 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r4)
            if (r13 == 0) goto L_0x017f
            r13 = r2
            r30 = r3
            goto L_0x01a7
        L_0x017f:
            int r13 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1))
            if (r13 <= 0) goto L_0x0199
            r13 = r2
            r30 = r3
            r2 = r33
            boolean r31 = org.apache.commons.math3.util.Precision.equals((double) r2, (double) r4)
            if (r31 != 0) goto L_0x019c
            boolean r31 = org.apache.commons.math3.util.Precision.equals((double) r2, (double) r0)
            if (r31 == 0) goto L_0x0195
            goto L_0x019c
        L_0x0195:
            r9 = r2
            r2 = r28
            goto L_0x01b2
        L_0x0199:
            r13 = r2
            r30 = r3
        L_0x019c:
            r18 = r10
            r2 = r28
            r9 = r8
            goto L_0x01b2
        L_0x01a2:
            r30 = r1
            r13 = r2
            r0 = r35
        L_0x01a7:
            r18 = r20
            r2 = r28
            r20 = r10
            r47 = r0
            r0 = r8
            r9 = r47
        L_0x01b2:
            r49.incrementIterationCount()
            r8 = r7
            r11 = r13
            r13 = r12
            r12 = r6
            r6 = r0
            r1 = r30
            r0 = r49
            goto L_0x0048
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.univariate.BrentOptimizer.doOptimize():org.apache.commons.math3.optim.univariate.UnivariatePointValuePair");
    }

    private UnivariatePointValuePair best(UnivariatePointValuePair univariatePointValuePair, UnivariatePointValuePair univariatePointValuePair2, boolean z) {
        if (univariatePointValuePair == null) {
            return univariatePointValuePair2;
        }
        if (univariatePointValuePair2 == null) {
            return univariatePointValuePair;
        }
        return z ? univariatePointValuePair.getValue() <= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2 : univariatePointValuePair.getValue() >= univariatePointValuePair2.getValue() ? univariatePointValuePair : univariatePointValuePair2;
    }
}
