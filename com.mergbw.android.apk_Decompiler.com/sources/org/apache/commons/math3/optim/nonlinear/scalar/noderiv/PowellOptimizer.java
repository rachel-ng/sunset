package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.LineSearch;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.util.FastMath;

public class PowellOptimizer extends MultivariateOptimizer {
    private static final double MIN_RELATIVE_TOLERANCE = (FastMath.ulp(1.0d) * 2.0d);
    private final double absoluteThreshold;
    private final LineSearch line;
    private final double relativeThreshold;

    public PowellOptimizer(double d, double d2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        this(d, d2, FastMath.sqrt(d), FastMath.sqrt(d2), convergenceChecker);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PowellOptimizer(double d, double d2, double d3, double d4, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        double d5 = d;
        double d6 = d2;
        double d7 = MIN_RELATIVE_TOLERANCE;
        if (d5 < d7) {
            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d7), true);
        } else if (d6 > 0.0d) {
            this.relativeThreshold = d5;
            this.absoluteThreshold = d6;
            this.line = new LineSearch(this, d3, d4, 1.0d);
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d2));
        }
    }

    public PowellOptimizer(double d, double d2) {
        this(d, d2, (ConvergenceChecker<PointValuePair>) null);
    }

    public PowellOptimizer(double d, double d2, double d3, double d4) {
        this(d, d2, d3, d4, (ConvergenceChecker<PointValuePair>) null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: double[]} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.optim.PointValuePair doOptimize() {
        /*
            r28 = this;
            r0 = r28
            r28.checkParameters()
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r1 = r28.getGoalType()
            double[] r2 = r28.getStartPoint()
            int r3 = r2.length
            r4 = 2
            int[] r4 = new int[r4]
            r5 = 1
            r4[r5] = r3
            r6 = 0
            r4[r6] = r3
            java.lang.Class r7 = java.lang.Double.TYPE
            java.lang.Object r4 = java.lang.reflect.Array.newInstance(r7, r4)
            double[][] r4 = (double[][]) r4
            r7 = r6
        L_0x0020:
            if (r7 >= r3) goto L_0x002b
            r8 = r4[r7]
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r8[r7] = r9
            int r7 = r7 + 1
            goto L_0x0020
        L_0x002b:
            org.apache.commons.math3.optim.ConvergenceChecker r7 = r28.getConvergenceChecker()
            double r8 = r0.computeObjectiveValue(r2)
            java.lang.Object r10 = r2.clone()
            double[] r10 = (double[]) r10
        L_0x0039:
            r28.incrementIterationCount()
            r13 = r6
            r18 = r13
            r14 = r8
            r16 = 0
        L_0x0042:
            if (r13 >= r3) goto L_0x006e
            r19 = r4[r13]
            double[] r5 = org.apache.commons.math3.util.MathArrays.copyOf((double[]) r19)
            org.apache.commons.math3.optim.nonlinear.scalar.LineSearch r11 = r0.line
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r11 = r11.search(r2, r5)
            double r20 = r11.getValue()
            double r11 = r11.getPoint()
            double[][] r2 = r0.newPointAndDirection(r2, r5, r11)
            r2 = r2[r6]
            double r14 = r14 - r20
            int r5 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r5 <= 0) goto L_0x0068
            r18 = r13
            r16 = r14
        L_0x0068:
            int r13 = r13 + 1
            r14 = r20
            r5 = 1
            goto L_0x0042
        L_0x006e:
            double r11 = r8 - r14
            r20 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r22 = r11 * r20
            r13 = r7
            double r6 = r0.relativeThreshold
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r8)
            double r26 = org.apache.commons.math3.util.FastMath.abs((double) r14)
            double r24 = r24 + r26
            double r6 = r6 * r24
            r19 = r4
            double r4 = r0.absoluteThreshold
            double r6 = r6 + r4
            int r4 = (r22 > r6 ? 1 : (r22 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x008e
            r4 = 1
            goto L_0x008f
        L_0x008e:
            r4 = 0
        L_0x008f:
            org.apache.commons.math3.optim.PointValuePair r5 = new org.apache.commons.math3.optim.PointValuePair
            r5.<init>(r10, r8)
            org.apache.commons.math3.optim.PointValuePair r6 = new org.apache.commons.math3.optim.PointValuePair
            r6.<init>(r2, r14)
            if (r4 != 0) goto L_0x00a5
            if (r13 == 0) goto L_0x00a5
            int r4 = r28.getIterations()
            boolean r4 = r13.converged(r4, r5, r6)
        L_0x00a5:
            if (r4 == 0) goto L_0x00b7
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r2 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            if (r1 != r2) goto L_0x00b1
            int r1 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r1 >= 0) goto L_0x00b0
            r5 = r6
        L_0x00b0:
            return r5
        L_0x00b1:
            int r1 = (r14 > r8 ? 1 : (r14 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x00b6
            r5 = r6
        L_0x00b6:
            return r5
        L_0x00b7:
            double[] r4 = new double[r3]
            double[] r5 = new double[r3]
            r6 = 0
        L_0x00bc:
            if (r6 >= r3) goto L_0x00d3
            r22 = r2[r6]
            r25 = r10[r6]
            double r22 = r22 - r25
            r4[r6] = r22
            r22 = r2[r6]
            double r22 = r22 * r20
            r25 = r10[r6]
            double r22 = r22 - r25
            r5[r6] = r22
            int r6 = r6 + 1
            goto L_0x00bc
        L_0x00d3:
            java.lang.Object r6 = r2.clone()
            r10 = r6
            double[] r10 = (double[]) r10
            double r5 = r0.computeObjectiveValue(r5)
            int r7 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x011e
            double r22 = r8 + r5
            double r25 = r14 * r20
            double r22 = r22 - r25
            double r22 = r22 * r20
            double r11 = r11 - r16
            double r11 = r11 * r11
            double r22 = r22 * r11
            double r8 = r8 - r5
            double r16 = r16 * r8
            double r16 = r16 * r8
            double r22 = r22 - r16
            r5 = 0
            int r5 = (r22 > r5 ? 1 : (r22 == r5 ? 0 : -1))
            if (r5 >= 0) goto L_0x011e
            org.apache.commons.math3.optim.nonlinear.scalar.LineSearch r5 = r0.line
            org.apache.commons.math3.optim.univariate.UnivariatePointValuePair r5 = r5.search(r2, r4)
            double r6 = r5.getValue()
            double r8 = r5.getPoint()
            double[][] r2 = r0.newPointAndDirection(r2, r4, r8)
            r4 = 0
            r5 = r2[r4]
            int r8 = r3 + -1
            r9 = r19[r8]
            r19[r18] = r9
            r9 = 1
            r2 = r2[r9]
            r19[r8] = r2
            r2 = r5
            goto L_0x0121
        L_0x011e:
            r4 = 0
            r9 = 1
            r6 = r14
        L_0x0121:
            r5 = r9
            r8 = r6
            r7 = r13
            r6 = r4
            r4 = r19
            goto L_0x0039
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.PowellOptimizer.doOptimize():org.apache.commons.math3.optim.PointValuePair");
    }

    private double[][] newPointAndDirection(double[] dArr, double[] dArr2, double d) {
        int length = dArr.length;
        double[] dArr3 = new double[length];
        double[] dArr4 = new double[length];
        for (int i = 0; i < length; i++) {
            double d2 = dArr2[i] * d;
            dArr4[i] = d2;
            dArr3[i] = dArr[i] + d2;
        }
        return new double[][]{dArr3, dArr4};
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
