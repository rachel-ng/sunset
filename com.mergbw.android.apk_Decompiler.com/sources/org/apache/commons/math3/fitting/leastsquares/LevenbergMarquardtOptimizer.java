package org.apache.commons.math3.fitting.leastsquares;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class LevenbergMarquardtOptimizer implements LeastSquaresOptimizer {
    private static final double TWO_EPS = (Precision.EPSILON * 2.0d);
    private final double costRelativeTolerance;
    private final double initialStepBoundFactor;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private final double qrRankingThreshold;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3, double d4, double d5) {
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    public LevenbergMarquardtOptimizer withInitialStepBoundFactor(double d) {
        return new LevenbergMarquardtOptimizer(d, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withCostRelativeTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, d, this.parRelativeTolerance, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withParameterRelativeTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, d, this.orthoTolerance, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withOrthoTolerance(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, d, this.qrRankingThreshold);
    }

    public LevenbergMarquardtOptimizer withRankingThreshold(double d) {
        return new LevenbergMarquardtOptimizer(this.initialStepBoundFactor, this.costRelativeTolerance, this.parRelativeTolerance, this.orthoTolerance, d);
    }

    public double getInitialStepBoundFactor() {
        return this.initialStepBoundFactor;
    }

    public double getCostRelativeTolerance() {
        return this.costRelativeTolerance;
    }

    public double getParameterRelativeTolerance() {
        return this.parRelativeTolerance;
    }

    public double getOrthoTolerance() {
        return this.orthoTolerance;
    }

    public double getRankingThreshold() {
        return this.qrRankingThreshold;
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x02bf  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02fb  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0285  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer.Optimum optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem r65) {
        /*
            r64 = this;
            r13 = r64
            r14 = r65
            int r15 = r65.getObservationSize()
            int r11 = r65.getParameterSize()
            org.apache.commons.math3.util.Incrementor r16 = r65.getIterationCounter()
            org.apache.commons.math3.util.Incrementor r17 = r65.getEvaluationCounter()
            org.apache.commons.math3.optim.ConvergenceChecker r12 = r65.getConvergenceChecker()
            int r10 = org.apache.commons.math3.util.FastMath.min((int) r15, (int) r11)
            double[] r9 = new double[r11]
            double[] r8 = new double[r11]
            double[] r7 = new double[r11]
            double[] r0 = new double[r15]
            double[] r6 = new double[r15]
            double[] r5 = new double[r11]
            double[] r4 = new double[r11]
            double[] r2 = new double[r11]
            r17.incrementCount()
            org.apache.commons.math3.linear.RealVector r0 = r65.getStart()
            org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem$Evaluation r0 = r14.evaluate(r0)
            org.apache.commons.math3.linear.RealVector r1 = r0.getResiduals()
            double[] r1 = r1.toArray()
            double r18 = r0.getCost()
            org.apache.commons.math3.linear.RealVector r3 = r0.getPoint()
            double[] r3 = r3.toArray()
            r20 = 0
            r23 = r20
            r25 = r23
            r27 = r25
            r22 = 1
            r63 = r3
            r3 = r0
            r0 = r63
        L_0x005a:
            r16.incrementCount()
            org.apache.commons.math3.linear.RealMatrix r14 = r3.getJacobian()
            org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer$InternalData r14 = r13.qrDecomposition(r14, r10)
            double[][] r30 = r14.weightedJacobian
            int[] r31 = r14.permutation
            double[] r32 = r14.diagR
            double[] r33 = r14.jacNorm
            r34 = 0
            r35 = r2
            r2 = r34
        L_0x007b:
            if (r2 >= r15) goto L_0x0084
            r36 = r1[r2]
            r6[r2] = r36
            int r2 = r2 + 1
            goto L_0x007b
        L_0x0084:
            r13.qTy(r6, r14)
            r2 = r34
        L_0x0089:
            if (r2 >= r10) goto L_0x0096
            r36 = r31[r2]
            r37 = r30[r2]
            r38 = r32[r36]
            r37[r36] = r38
            int r2 = r2 + 1
            goto L_0x0089
        L_0x0096:
            r36 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r22 == 0) goto L_0x00cb
            r25 = r20
            r2 = r34
        L_0x009e:
            if (r2 >= r11) goto L_0x00b5
            r27 = r33[r2]
            int r32 = (r27 > r20 ? 1 : (r27 == r20 ? 0 : -1))
            if (r32 != 0) goto L_0x00a8
            r27 = r36
        L_0x00a8:
            r38 = r0[r2]
            double r38 = r38 * r27
            double r38 = r38 * r38
            double r25 = r25 + r38
            r8[r2] = r27
            int r2 = r2 + 1
            goto L_0x009e
        L_0x00b5:
            double r27 = org.apache.commons.math3.util.FastMath.sqrt(r25)
            int r2 = (r27 > r20 ? 1 : (r27 == r20 ? 0 : -1))
            r32 = r0
            if (r2 != 0) goto L_0x00c3
            r2 = r1
            double r0 = r13.initialStepBoundFactor
            goto L_0x00c8
        L_0x00c3:
            r2 = r1
            double r0 = r13.initialStepBoundFactor
            double r0 = r0 * r27
        L_0x00c8:
            r25 = r0
            goto L_0x00ce
        L_0x00cb:
            r32 = r0
            r2 = r1
        L_0x00ce:
            int r0 = (r18 > r20 ? 1 : (r18 == r20 ? 0 : -1))
            r38 = r2
            if (r0 == 0) goto L_0x0118
            r1 = r20
            r0 = r34
        L_0x00d8:
            if (r0 >= r10) goto L_0x0111
            r39 = r31[r0]
            r40 = r33[r39]
            int r42 = (r40 > r20 ? 1 : (r40 == r20 ? 0 : -1))
            if (r42 == 0) goto L_0x0106
            r42 = r4
            r43 = r20
            r4 = r34
        L_0x00e8:
            if (r4 > r0) goto L_0x00f7
            r45 = r30[r4]
            r46 = r45[r39]
            r48 = r6[r4]
            double r46 = r46 * r48
            double r43 = r43 + r46
            int r4 = r4 + 1
            goto L_0x00e8
        L_0x00f7:
            double r43 = org.apache.commons.math3.util.FastMath.abs((double) r43)
            double r40 = r40 * r18
            r39 = r5
            double r4 = r43 / r40
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r1, (double) r4)
            goto L_0x010a
        L_0x0106:
            r42 = r4
            r39 = r5
        L_0x010a:
            int r0 = r0 + 1
            r5 = r39
            r4 = r42
            goto L_0x00d8
        L_0x0111:
            r42 = r4
            r39 = r5
            r40 = r1
            goto L_0x011e
        L_0x0118:
            r42 = r4
            r39 = r5
            r40 = r20
        L_0x011e:
            double r0 = r13.orthoTolerance
            int r0 = (r40 > r0 ? 1 : (r40 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0132
            org.apache.commons.math3.fitting.leastsquares.OptimumImpl r0 = new org.apache.commons.math3.fitting.leastsquares.OptimumImpl
            int r1 = r17.getCount()
            int r2 = r16.getCount()
            r0.<init>(r3, r1, r2)
            return r0
        L_0x0132:
            r0 = r34
        L_0x0134:
            if (r0 >= r11) goto L_0x0143
            r1 = r8[r0]
            r4 = r33[r0]
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r1, (double) r4)
            r8[r0] = r1
            int r0 = r0 + 1
            goto L_0x0134
        L_0x0143:
            r0 = r3
            r5 = r32
            r1 = r38
            r32 = r20
        L_0x014a:
            r43 = 4547007122018943789(0x3f1a36e2eb1c432d, double:1.0E-4)
            int r2 = (r32 > r43 ? 1 : (r32 == r43 ? 0 : -1))
            if (r2 >= 0) goto L_0x036e
            r0 = r34
        L_0x0155:
            if (r0 >= r10) goto L_0x0160
            r1 = r31[r0]
            r32 = r5[r1]
            r7[r1] = r32
            int r0 = r0 + 1
            goto L_0x0155
        L_0x0160:
            r0 = r64
            r1 = r6
            r4 = r3
            r32 = r35
            r2 = r25
            r35 = r15
            r33 = r42
            r15 = r4
            r4 = r8
            r13 = r5
            r38 = r39
            r5 = r14
            r39 = r6
            r6 = r10
            r42 = r7
            r7 = r38
            r45 = r8
            r8 = r33
            r46 = r9
            r9 = r32
            r47 = r14
            r14 = r10
            r10 = r46
            r50 = r12
            r48 = r15
            r15 = r11
            r11 = r23
            double r0 = r0.determineLMParameter(r1, r2, r4, r5, r6, r7, r8, r9, r10, r11)
            r3 = r20
            r2 = r34
        L_0x0195:
            if (r2 >= r14) goto L_0x01ad
            r5 = r31[r2]
            r6 = r46[r5]
            double r6 = -r6
            r46[r5] = r6
            r8 = r42[r5]
            double r8 = r8 + r6
            r13[r5] = r8
            r6 = r45[r5]
            r8 = r46[r5]
            double r6 = r6 * r8
            double r6 = r6 * r6
            double r3 = r3 + r6
            int r2 = r2 + 1
            goto L_0x0195
        L_0x01ad:
            double r2 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            r4 = r25
            if (r22 == 0) goto L_0x01bb
            double r25 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r2)
            r4 = r25
        L_0x01bb:
            r17.incrementCount()
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>((double[]) r13)
            r7 = r65
            r8 = 1
            org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem$Evaluation r6 = r7.evaluate(r6)
            org.apache.commons.math3.linear.RealVector r9 = r6.getResiduals()
            double[] r9 = r9.toArray()
            double r10 = r6.getCost()
            org.apache.commons.math3.linear.RealVector r12 = r6.getPoint()
            double[] r12 = r12.toArray()
            r23 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            double r25 = r10 * r23
            int r13 = (r25 > r18 ? 1 : (r25 == r18 ? 0 : -1))
            if (r13 >= 0) goto L_0x01f0
            double r51 = r10 / r18
            double r51 = r51 * r51
            double r51 = r36 - r51
            goto L_0x01f2
        L_0x01f0:
            r51 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x01f2:
            r13 = r34
        L_0x01f4:
            if (r13 >= r14) goto L_0x0213
            r29 = r31[r13]
            r53 = r46[r29]
            r38[r13] = r20
            r8 = r34
        L_0x01fe:
            if (r8 > r13) goto L_0x020f
            r55 = r38[r8]
            r57 = r30[r8]
            r58 = r57[r29]
            double r58 = r58 * r53
            double r55 = r55 + r58
            r38[r8] = r55
            int r8 = r8 + 1
            goto L_0x01fe
        L_0x020f:
            int r13 = r13 + 1
            r8 = 1
            goto L_0x01f4
        L_0x0213:
            r53 = r20
            r8 = r34
        L_0x0217:
            if (r8 >= r14) goto L_0x0222
            r55 = r38[r8]
            double r55 = r55 * r55
            double r53 = r53 + r55
            int r8 = r8 + 1
            goto L_0x0217
        L_0x0222:
            double r55 = r18 * r18
            double r53 = r53 / r55
            double r57 = r0 * r2
            double r57 = r57 * r2
            double r57 = r57 / r55
            r55 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r59 = r57 * r55
            double r59 = r53 + r59
            double r7 = r53 + r57
            double r7 = -r7
            int r13 = (r59 > r20 ? 1 : (r59 == r20 ? 0 : -1))
            if (r13 != 0) goto L_0x023c
            r53 = r20
            goto L_0x023e
        L_0x023c:
            double r53 = r51 / r59
        L_0x023e:
            r57 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            int r13 = (r53 > r57 ? 1 : (r53 == r57 ? 0 : -1))
            r57 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r13 > 0) goto L_0x0269
            int r13 = (r51 > r20 ? 1 : (r51 == r20 ? 0 : -1))
            if (r13 >= 0) goto L_0x0252
            double r61 = r7 * r57
            double r57 = r57 * r51
            double r7 = r7 + r57
            double r57 = r61 / r7
        L_0x0252:
            int r7 = (r25 > r18 ? 1 : (r25 == r18 ? 0 : -1))
            if (r7 >= 0) goto L_0x025d
            int r7 = (r57 > r23 ? 1 : (r57 == r23 ? 0 : -1))
            if (r7 >= 0) goto L_0x025b
            goto L_0x025d
        L_0x025b:
            r23 = r57
        L_0x025d:
            r7 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r2 = r2 * r7
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r2)
            double r2 = r2 * r23
            double r0 = r0 / r23
            goto L_0x027d
        L_0x0269:
            int r7 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r7 == 0) goto L_0x0279
            r7 = 4604930618986332160(0x3fe8000000000000, double:0.75)
            int r7 = (r53 > r7 ? 1 : (r53 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x0274
            goto L_0x0279
        L_0x0274:
            r23 = r0
            r25 = r4
            goto L_0x0281
        L_0x0279:
            double r2 = r2 * r55
            double r0 = r0 * r57
        L_0x027d:
            r23 = r0
            r25 = r2
        L_0x0281:
            int r0 = (r53 > r43 ? 1 : (r53 == r43 ? 0 : -1))
            if (r0 < 0) goto L_0x02bf
            r1 = r20
            r0 = r34
        L_0x0289:
            if (r0 >= r15) goto L_0x0295
            r3 = r45[r0]
            r7 = r12[r0]
            double r3 = r3 * r7
            double r3 = r3 * r3
            double r1 = r1 + r3
            int r0 = r0 + 1
            goto L_0x0289
        L_0x0295:
            double r27 = org.apache.commons.math3.util.FastMath.sqrt(r1)
            r2 = r50
            if (r2 == 0) goto L_0x02b7
            int r0 = r16.getCount()
            r1 = r48
            boolean r0 = r2.converged(r0, r1, r6)
            if (r0 == 0) goto L_0x02b9
            org.apache.commons.math3.fitting.leastsquares.OptimumImpl r0 = new org.apache.commons.math3.fitting.leastsquares.OptimumImpl
            int r1 = r17.getCount()
            int r2 = r16.getCount()
            r0.<init>(r6, r1, r2)
            return r0
        L_0x02b7:
            r1 = r48
        L_0x02b9:
            r0 = r6
            r18 = r10
            r22 = r34
            goto L_0x02d1
        L_0x02bf:
            r1 = r48
            r2 = r50
            r0 = r34
        L_0x02c5:
            if (r0 >= r14) goto L_0x02d0
            r3 = r31[r0]
            r4 = r42[r3]
            r12[r3] = r4
            int r0 = r0 + 1
            goto L_0x02c5
        L_0x02d0:
            r0 = r1
        L_0x02d1:
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r51)
            r6 = r64
            double r7 = r6.costRelativeTolerance
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x02e5
            int r3 = (r59 > r7 ? 1 : (r59 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x02e5
            int r3 = (r53 > r55 ? 1 : (r53 == r55 ? 0 : -1))
            if (r3 <= 0) goto L_0x02ed
        L_0x02e5:
            double r3 = r6.parRelativeTolerance
            double r3 = r3 * r27
            int r3 = (r25 > r3 ? 1 : (r25 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x02fb
        L_0x02ed:
            org.apache.commons.math3.fitting.leastsquares.OptimumImpl r1 = new org.apache.commons.math3.fitting.leastsquares.OptimumImpl
            int r2 = r17.getCount()
            int r3 = r16.getCount()
            r1.<init>(r0, r2, r3)
            return r1
        L_0x02fb:
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r51)
            double r7 = TWO_EPS
            int r3 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0321
            int r3 = (r59 > r7 ? 1 : (r59 == r7 ? 0 : -1))
            if (r3 > 0) goto L_0x0321
            int r3 = (r53 > r55 ? 1 : (r53 == r55 ? 0 : -1))
            if (r3 <= 0) goto L_0x030e
            goto L_0x0321
        L_0x030e:
            org.apache.commons.math3.exception.ConvergenceException r0 = new org.apache.commons.math3.exception.ConvergenceException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE
            double r2 = r6.costRelativeTolerance
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r34] = r2
            r0.<init>(r1, r3)
            throw r0
        L_0x0321:
            double r3 = r7 * r27
            int r3 = (r25 > r3 ? 1 : (r25 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x035b
            int r3 = (r40 > r7 ? 1 : (r40 == r7 ? 0 : -1))
            if (r3 <= 0) goto L_0x0348
            r3 = r1
            r13 = r6
            r1 = r9
            r5 = r12
            r10 = r14
            r11 = r15
            r15 = r35
            r6 = r39
            r7 = r42
            r8 = r45
            r9 = r46
            r14 = r47
            r12 = r2
            r35 = r32
            r42 = r33
            r39 = r38
            r32 = r53
            goto L_0x014a
        L_0x0348:
            org.apache.commons.math3.exception.ConvergenceException r0 = new org.apache.commons.math3.exception.ConvergenceException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE
            double r2 = r6.orthoTolerance
            java.lang.Double r2 = java.lang.Double.valueOf(r2)
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r34] = r2
            r0.<init>(r1, r3)
            throw r0
        L_0x035b:
            r3 = 1
            org.apache.commons.math3.exception.ConvergenceException r0 = new org.apache.commons.math3.exception.ConvergenceException
            org.apache.commons.math3.exception.util.LocalizedFormats r1 = org.apache.commons.math3.exception.util.LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE
            double r4 = r6.parRelativeTolerance
            java.lang.Double r2 = java.lang.Double.valueOf(r4)
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r34] = r2
            r0.<init>(r1, r3)
            throw r0
        L_0x036e:
            r2 = r12
            r32 = r35
            r38 = r39
            r12 = r5
            r4 = r25
            r3 = r0
            r0 = r12
            r5 = r38
            r4 = r42
            r12 = r2
            r2 = r32
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer.optimize(org.apache.commons.math3.fitting.leastsquares.LeastSquaresProblem):org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer$Optimum");
    }

    private static class InternalData {
        /* access modifiers changed from: private */
        public final double[] beta;
        /* access modifiers changed from: private */
        public final double[] diagR;
        /* access modifiers changed from: private */
        public final double[] jacNorm;
        /* access modifiers changed from: private */
        public final int[] permutation;
        /* access modifiers changed from: private */
        public final int rank;
        /* access modifiers changed from: private */
        public final double[][] weightedJacobian;

        InternalData(double[][] dArr, int[] iArr, int i, double[] dArr2, double[] dArr3, double[] dArr4) {
            this.weightedJacobian = dArr;
            this.permutation = iArr;
            this.rank = i;
            this.diagR = dArr2;
            this.jacNorm = dArr3;
            this.beta = dArr4;
        }
    }

    private double determineLMParameter(double[] dArr, double d, double[] dArr2, InternalData internalData, int i, double[] dArr3, double[] dArr4, double[] dArr5, double[] dArr6, double d2) {
        double d3;
        double d4;
        double d5;
        double d6 = d;
        int i2 = i;
        double[][] access$000 = internalData.weightedJacobian;
        int[] access$100 = internalData.permutation;
        int access$400 = internalData.rank;
        double[] access$200 = internalData.diagR;
        int i3 = 0;
        int length = access$000[0].length;
        for (int i4 = 0; i4 < access$400; i4++) {
            dArr6[access$100[i4]] = dArr[i4];
        }
        for (int i5 = access$400; i5 < length; i5++) {
            dArr6[access$100[i5]] = 0.0d;
        }
        for (int i6 = access$400 - 1; i6 >= 0; i6--) {
            int i7 = access$100[i6];
            double d7 = dArr6[i7] / access$200[i7];
            for (int i8 = 0; i8 < i6; i8++) {
                int i9 = access$100[i8];
                dArr6[i9] = dArr6[i9] - (access$000[i8][i7] * d7);
            }
            dArr6[i7] = d7;
        }
        double d8 = 0.0d;
        for (int i10 = 0; i10 < i2; i10++) {
            int i11 = access$100[i10];
            double d9 = dArr2[i11] * dArr6[i11];
            dArr3[i11] = d9;
            d8 += d9 * d9;
        }
        double sqrt = FastMath.sqrt(d8);
        double d10 = sqrt - d6;
        double d11 = d6 * 0.1d;
        if (d10 <= d11) {
            return 0.0d;
        }
        if (access$400 == i2) {
            for (int i12 = 0; i12 < i2; i12++) {
                int i13 = access$100[i12];
                dArr3[i13] = dArr3[i13] * (dArr2[i13] / sqrt);
            }
            int i14 = 0;
            double d12 = 0.0d;
            while (i14 < i2) {
                int i15 = access$100[i14];
                double d13 = 0.0d;
                while (i3 < i14) {
                    d13 += access$000[i3][i15] * dArr3[access$100[i3]];
                    i3++;
                }
                double d14 = (dArr3[i15] - d13) / access$200[i15];
                dArr3[i15] = d14;
                d12 += d14 * d14;
                i14++;
                i3 = 0;
            }
            d3 = d10 / (d6 * d12);
        } else {
            d3 = 0.0d;
        }
        double d15 = 0.0d;
        for (int i16 = 0; i16 < i2; i16++) {
            int i17 = access$100[i16];
            double d16 = 0.0d;
            for (int i18 = 0; i18 <= i16; i18++) {
                d16 += access$000[i18][i17] * dArr[i18];
            }
            double d17 = d16 / dArr2[i17];
            d15 += d17 * d17;
        }
        double sqrt2 = FastMath.sqrt(d15);
        double d18 = sqrt2 / d6;
        if (d18 == 0.0d) {
            d18 = Precision.SAFE_MIN / FastMath.min(d6, 0.1d);
        }
        double d19 = d18;
        double d20 = d10;
        double min = FastMath.min(d19, FastMath.max(d2, d3));
        double d21 = 0.0d;
        if (min == 0.0d) {
            min = sqrt2 / sqrt;
        }
        int i19 = 10;
        double d22 = d19;
        double d23 = d3;
        while (i19 >= 0) {
            if (min == d21) {
                min = FastMath.max(Precision.SAFE_MIN, 0.001d * d22);
            }
            double sqrt3 = FastMath.sqrt(min);
            for (int i20 = 0; i20 < i2; i20++) {
                int i21 = access$100[i20];
                dArr3[i21] = dArr2[i21] * sqrt3;
            }
            double d24 = min;
            double d25 = d22;
            determineLMDirection(dArr, dArr3, dArr4, internalData, i, dArr5, dArr6);
            double d26 = 0.0d;
            for (int i22 = 0; i22 < i2; i22++) {
                int i23 = access$100[i22];
                double d27 = dArr2[i23] * dArr6[i23];
                dArr5[i23] = d27;
                d26 += d27 * d27;
            }
            double sqrt4 = FastMath.sqrt(d26);
            double d28 = sqrt4 - d6;
            if (FastMath.abs(d28) <= d11 || (d23 == 0.0d && d28 <= d20 && d20 < 0.0d)) {
                return d24;
            }
            for (int i24 = 0; i24 < i2; i24++) {
                int i25 = access$100[i24];
                dArr3[i25] = (dArr5[i25] * dArr2[i25]) / sqrt4;
            }
            int i26 = 0;
            while (i26 < i2) {
                int i27 = access$100[i26];
                double d29 = dArr3[i27] / dArr4[i26];
                dArr3[i27] = d29;
                i26++;
                for (int i28 = i26; i28 < i2; i28++) {
                    int i29 = access$100[i28];
                    dArr3[i29] = dArr3[i29] - (access$000[i28][i27] * d29);
                }
            }
            double d30 = 0.0d;
            for (int i30 = 0; i30 < i2; i30++) {
                double d31 = dArr3[access$100[i30]];
                d30 += d31 * d31;
            }
            double d32 = d28 / (d6 * d30);
            if (d28 > 0.0d) {
                d4 = d24;
                d23 = FastMath.max(d23, d4);
                d5 = d25;
            } else {
                d4 = d24;
                d5 = d25;
                if (d28 < 0.0d) {
                    d5 = FastMath.min(d5, d4);
                }
            }
            i19--;
            d22 = d5;
            d20 = d28;
            d21 = 0.0d;
            min = FastMath.max(d23, d32 + d4);
        }
        return min;
    }

    private void determineLMDirection(double[] dArr, double[] dArr2, double[] dArr3, InternalData internalData, int i, double[] dArr4, double[] dArr5) {
        int[] iArr;
        double d;
        double d2;
        double[] dArr6 = dArr3;
        int i2 = i;
        double[] dArr7 = dArr5;
        int[] access$100 = internalData.permutation;
        double[][] access$000 = internalData.weightedJacobian;
        double[] access$200 = internalData.diagR;
        int i3 = 0;
        while (i3 < i2) {
            int i4 = access$100[i3];
            int i5 = i3 + 1;
            for (int i6 = i5; i6 < i2; i6++) {
                access$000[i6][i4] = access$000[i3][access$100[i6]];
            }
            dArr7[i3] = access$200[i4];
            dArr4[i3] = dArr[i3];
            i3 = i5;
        }
        int i7 = 0;
        while (true) {
            double d3 = 0.0d;
            if (i7 >= i2) {
                break;
            }
            double d4 = dArr2[access$100[i7]];
            if (d4 != 0.0d) {
                Arrays.fill(dArr6, i7 + 1, dArr6.length, 0.0d);
            }
            dArr6[i7] = d4;
            int i8 = i7;
            double d5 = 0.0d;
            while (i8 < i2) {
                int i9 = access$100[i8];
                if (dArr6[i8] != d3) {
                    double d6 = access$000[i8][i9];
                    if (FastMath.abs(d6) < FastMath.abs(dArr6[i8])) {
                        double d7 = d6 / dArr6[i8];
                        double sqrt = 1.0d / FastMath.sqrt((d7 * d7) + 1.0d);
                        d = d7 * sqrt;
                        d2 = sqrt;
                    } else {
                        double d8 = dArr6[i8] / d6;
                        double sqrt2 = 1.0d / FastMath.sqrt((d8 * d8) + 1.0d);
                        d2 = d8 * sqrt2;
                        d = sqrt2;
                    }
                    access$000[i8][i9] = (d6 * d) + (dArr6[i8] * d2);
                    double d9 = dArr4[i8];
                    iArr = access$100;
                    double d10 = -d2;
                    d5 = (d5 * d) + (d9 * d10);
                    dArr4[i8] = (d * d9) + (d2 * d5);
                    for (int i10 = i8 + 1; i10 < i2; i10++) {
                        double[] dArr8 = access$000[i10];
                        double d11 = dArr8[i9];
                        double d12 = dArr6[i10];
                        dArr6[i10] = (d11 * d10) + (d12 * d);
                        dArr8[i9] = (d * d11) + (d2 * d12);
                    }
                } else {
                    iArr = access$100;
                }
                i8++;
                double[] dArr9 = dArr5;
                access$100 = iArr;
                d3 = 0.0d;
            }
            int[] iArr2 = access$100;
            double[] dArr10 = access$000[i7];
            int i11 = iArr2[i7];
            dArr6[i7] = dArr10[i11];
            double[] dArr11 = dArr5;
            dArr10[i11] = dArr11[i7];
            i7++;
            dArr7 = dArr11;
            access$100 = iArr2;
        }
        double[] dArr12 = dArr7;
        int[] iArr3 = access$100;
        int i12 = i2;
        for (int i13 = 0; i13 < i2; i13++) {
            if (dArr6[i13] == 0.0d && i12 == i2) {
                i12 = i13;
            }
            if (i12 < i2) {
                dArr4[i13] = 0.0d;
            }
        }
        if (i12 > 0) {
            for (int i14 = i12 - 1; i14 >= 0; i14--) {
                int i15 = iArr3[i14];
                double d13 = 0.0d;
                for (int i16 = i14 + 1; i16 < i12; i16++) {
                    d13 += access$000[i16][i15] * dArr4[i16];
                }
                dArr4[i14] = (dArr4[i14] - d13) / dArr6[i14];
            }
        }
        for (int i17 = 0; i17 < dArr12.length; i17++) {
            dArr12[iArr3[i17]] = dArr4[i17];
        }
    }

    private InternalData qrDecomposition(RealMatrix realMatrix, int i) throws ConvergenceException {
        double[][] data = realMatrix.scalarMultiply(-1.0d).getData();
        int length = data[0].length;
        int[] iArr = new int[length];
        double[] dArr = new double[length];
        double[] dArr2 = new double[length];
        double[] dArr3 = new double[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = i2;
            double d = 0.0d;
            for (double[] dArr4 : data) {
                double d2 = dArr4[i2];
                d += d2 * d2;
            }
            dArr2[i2] = FastMath.sqrt(d);
        }
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = -1;
            double d3 = Double.NEGATIVE_INFINITY;
            for (int i5 = i3; i5 < length; i5++) {
                double d4 = 0.0d;
                for (int i6 = i3; i6 < r0; i6++) {
                    double d5 = data[i6][iArr[i5]];
                    d4 += d5 * d5;
                }
                if (Double.isInfinite(d4) || Double.isNaN(d4)) {
                    throw new ConvergenceException(LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN, Integer.valueOf(r0), Integer.valueOf(length));
                }
                if (d4 > d3) {
                    i4 = i5;
                    d3 = d4;
                }
            }
            if (d3 <= this.qrRankingThreshold) {
                return new InternalData(data, iArr, i3, dArr, dArr2, dArr3);
            }
            int i7 = iArr[i4];
            iArr[i4] = iArr[i3];
            iArr[i3] = i7;
            double d6 = data[i3][i7];
            int i8 = (d6 > 0.0d ? 1 : (d6 == 0.0d ? 0 : -1));
            double sqrt = FastMath.sqrt(d3);
            if (i8 > 0) {
                sqrt = -sqrt;
            }
            double d7 = 1.0d / (d3 - (d6 * sqrt));
            dArr3[i7] = d7;
            dArr[i7] = sqrt;
            double[] dArr5 = data[i3];
            dArr5[i7] = dArr5[i7] - sqrt;
            for (int i9 = (length - 1) - i3; i9 > 0; i9--) {
                double d8 = 0.0d;
                for (int i10 = i3; i10 < r0; i10++) {
                    double[] dArr6 = data[i10];
                    d8 += dArr6[i7] * dArr6[iArr[i3 + i9]];
                }
                double d9 = d8 * d7;
                for (int i11 = i3; i11 < r0; i11++) {
                    double[] dArr7 = data[i11];
                    int i12 = iArr[i3 + i9];
                    dArr7[i12] = dArr7[i12] - (dArr7[i7] * d9);
                }
            }
        }
        return new InternalData(data, iArr, i, dArr, dArr2, dArr3);
    }

    private void qTy(double[] dArr, InternalData internalData) {
        double[][] access$000 = internalData.weightedJacobian;
        int[] access$100 = internalData.permutation;
        double[] access$500 = internalData.beta;
        int length = access$000.length;
        int length2 = access$000[0].length;
        for (int i = 0; i < length2; i++) {
            int i2 = access$100[i];
            double d = 0.0d;
            for (int i3 = i; i3 < length; i3++) {
                d += access$000[i3][i2] * dArr[i3];
            }
            double d2 = d * access$500[i2];
            for (int i4 = i; i4 < length; i4++) {
                dArr[i4] = dArr[i4] - (access$000[i4][i2] * d2);
            }
        }
    }
}
