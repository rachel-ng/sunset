package org.apache.commons.math3.special;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class BesselJ implements UnivariateFunction {
    private static final double ENMTEN = 8.9E-308d;
    private static final double ENSIG = 1.0E16d;
    private static final double ENTEN = 1.0E308d;
    private static final double[] FACT = {1.0d, 1.0d, 2.0d, 6.0d, 24.0d, 120.0d, 720.0d, 5040.0d, 40320.0d, 362880.0d, 3628800.0d, 3.99168E7d, 4.790016E8d, 6.2270208E9d, 8.71782912E10d, 1.307674368E12d, 2.0922789888E13d, 3.55687428096E14d, 6.402373705728E15d, 1.21645100408832E17d, 2.43290200817664E18d, 5.109094217170944E19d, 1.1240007277776077E21d, 2.585201673888498E22d, 6.204484017332394E23d};
    private static final double PI2 = 0.6366197723675814d;
    private static final double RTNSIG = 1.0E-4d;
    private static final double TOWPI1 = 6.28125d;
    private static final double TWOPI = 6.283185307179586d;
    private static final double TWOPI2 = 0.001935307179586477d;
    private static final double X_MAX = 10000.0d;
    private static final double X_MIN = 0.0d;
    private final double order;

    public BesselJ(double d) {
        this.order = d;
    }

    public double value(double d) throws MathIllegalArgumentException, ConvergenceException {
        return value(this.order, d);
    }

    public static double value(double d, double d2) throws MathIllegalArgumentException, ConvergenceException {
        int i = (int) d;
        int i2 = i + 1;
        BesselJResult rjBesl = rjBesl(d2, d - ((double) i), i2);
        if (rjBesl.nVals >= i2) {
            return rjBesl.vals[i];
        }
        if (rjBesl.nVals < 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.BESSEL_FUNCTION_BAD_ARGUMENT, Double.valueOf(d), Double.valueOf(d2));
        } else if (FastMath.abs(rjBesl.vals[rjBesl.nVals - 1]) < 1.0E-100d) {
            return rjBesl.vals[i];
        } else {
            throw new ConvergenceException(LocalizedFormats.BESSEL_FUNCTION_FAILED_CONVERGENCE, Double.valueOf(d), Double.valueOf(d2));
        }
    }

    public static class BesselJResult {
        /* access modifiers changed from: private */
        public final int nVals;
        /* access modifiers changed from: private */
        public final double[] vals;

        public BesselJResult(double[] dArr, int i) {
            this.vals = MathArrays.copyOf(dArr, dArr.length);
            this.nVals = i;
        }

        public double[] getVals() {
            double[] dArr = this.vals;
            return MathArrays.copyOf(dArr, dArr.length);
        }

        public int getnVals() {
            return this.nVals;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0072, code lost:
        if (r24 == 0.0d) goto L_0x0078;
     */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x03c3  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0400  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x043c  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x0456  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.apache.commons.math3.special.BesselJ.BesselJResult rjBesl(double r51, double r53, int r55) {
        /*
            r0 = r51
            r2 = r53
            r4 = r55
            double[] r5 = new double[r4]
            int r6 = (int) r0
            r7 = 0
            r8 = 0
            r10 = 1
            if (r4 <= 0) goto L_0x046f
            int r11 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r11 < 0) goto L_0x046f
            r12 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            int r12 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r12 > 0) goto L_0x046f
            int r12 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r12 < 0) goto L_0x046f
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r15 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r15 >= 0) goto L_0x046f
            r15 = r7
        L_0x0027:
            if (r15 >= r4) goto L_0x002e
            r5[r15] = r8
            int r15 = r15 + 1
            goto L_0x0027
        L_0x002e:
            r15 = 4547007122018943789(0x3f1a36e2eb1c432d, double:1.0E-4)
            int r15 = (r0 > r15 ? 1 : (r0 == r15 ? 0 : -1))
            r16 = 13510499899545819(0x2fffba634018db, double:8.9E-308)
            r18 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r15 >= 0) goto L_0x00bd
            double r20 = r2 + r13
            int r6 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r6 <= 0) goto L_0x0048
            double r18 = r18 * r0
            r8 = r18
        L_0x0048:
            if (r12 == 0) goto L_0x0057
            double r18 = org.apache.commons.math3.util.FastMath.pow((double) r8, (double) r2)
            double r24 = org.apache.commons.math3.special.Gamma.gamma(r53)
            double r2 = r2 * r24
            double r18 = r18 / r2
            goto L_0x0059
        L_0x0057:
            r18 = r13
        L_0x0059:
            double r2 = r0 + r13
            int r2 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r2 <= 0) goto L_0x0062
            double r2 = -r8
            double r2 = r2 * r8
            goto L_0x0064
        L_0x0062:
            r2 = 0
        L_0x0064:
            double r24 = r18 * r2
            double r24 = r24 / r20
            double r24 = r18 + r24
            r5[r7] = r24
            if (r11 == 0) goto L_0x0075
            r11 = 0
            int r6 = (r24 > r11 ? 1 : (r24 == r11 ? 0 : -1))
            if (r6 != 0) goto L_0x0077
            goto L_0x0078
        L_0x0075:
            r11 = 0
        L_0x0077:
            r7 = r4
        L_0x0078:
            if (r4 == r10) goto L_0x00ba
            int r6 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x0085
        L_0x007e:
            if (r10 >= r4) goto L_0x00ba
            r5[r10] = r11
            int r10 = r10 + 1
            goto L_0x007e
        L_0x0085:
            int r6 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r6 == 0) goto L_0x008c
            double r16 = r16 / r2
            goto L_0x0093
        L_0x008c:
            r11 = 18014099526916315(0x3fffba634018db, double:1.78E-307)
            double r16 = r11 / r0
        L_0x0093:
            if (r10 >= r4) goto L_0x00ba
            double r18 = r18 / r20
            double r20 = r20 + r13
            double r0 = r18 * r8
            double r11 = r16 * r20
            int r6 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r6 > 0) goto L_0x00a4
            r18 = 0
            goto L_0x00a6
        L_0x00a4:
            r18 = r0
        L_0x00a6:
            double r0 = r18 * r2
            double r0 = r0 / r20
            double r0 = r18 + r0
            r5[r10] = r0
            r11 = 0
            int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r0 != 0) goto L_0x00b7
            if (r7 <= r10) goto L_0x00b7
            r7 = r10
        L_0x00b7:
            int r10 = r10 + 1
            goto L_0x0093
        L_0x00ba:
            r0 = r4
            goto L_0x047e
        L_0x00bd:
            r8 = 4627730092099895296(0x4039000000000000, double:25.0)
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            r11 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r8 <= 0) goto L_0x01f4
            int r8 = r6 + 1
            if (r4 > r8) goto L_0x01f4
            r6 = 4603909380684499075(0x3fe45f306dc9c883, double:0.6366197723675814)
            double r15 = r6 / r0
            double r15 = org.apache.commons.math3.util.FastMath.sqrt(r15)
            r20 = 4593671619917905920(0x3fc0000000000000, double:0.125)
            double r20 = r20 / r0
            double r22 = r20 * r20
            r24 = 4638777984935788544(0x4060400000000000, double:130.0)
            int r8 = (r0 > r24 ? 1 : (r0 == r24 ? 0 : -1))
            if (r8 < 0) goto L_0x00e5
            r8 = 4
            goto L_0x00f3
        L_0x00e5:
            r24 = 4630122629401935872(0x4041800000000000, double:35.0)
            int r8 = (r0 > r24 ? 1 : (r0 == r24 ? 0 : -1))
            if (r8 < 0) goto L_0x00f1
            r8 = 8
            goto L_0x00f3
        L_0x00f1:
            r8 = 11
        L_0x00f3:
            double r13 = (double) r8
            r26 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r13 = r13 * r26
            r28 = 4618760256179416344(0x401921fb54442d18, double:6.283185307179586)
            double r28 = r0 / r28
            double r9 = r28 + r18
            int r9 = (int) r9
            double r9 = (double) r9
            r28 = 4618758077217243136(0x4019200000000000, double:6.28125)
            double r28 = r28 * r9
            double r28 = r0 - r28
            r30 = 4566567851960403050(0x3f5fb54442d1846a, double:0.001935307179586477)
            double r9 = r9 * r30
            double r28 = r28 - r9
            double r9 = r2 + r18
            double r9 = r9 / r6
            double r28 = r28 - r9
            double r6 = org.apache.commons.math3.util.FastMath.sin(r28)
            double r9 = org.apache.commons.math3.util.FastMath.cos(r28)
            double r2 = r2 * r11
            r30 = r2
            r11 = 1
            r12 = 2
        L_0x0127:
            if (r11 > r12) goto L_0x01d3
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r32 = r13 - r24
            double r34 = r32 - r30
            double r32 = r32 + r30
            double r34 = r34 * r32
            double r34 = r34 * r22
            double r34 = r34 * r18
            r32 = 4613937818241073152(0x4008000000000000, double:3.0)
            double r36 = r13 - r32
            double r38 = r30 - r36
            double r36 = r30 + r36
            double r38 = r38 * r36
            double r36 = r34 * r38
            double[] r12 = FACT
            int r17 = r8 * 2
            r40 = r12[r17]
            double r36 = r36 / r40
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r40 = r13 + r24
            double r42 = r30 - r40
            double r40 = r30 + r40
            double r42 = r42 * r40
            double r34 = r34 * r42
            int r40 = r17 + 1
            r40 = r12[r40]
            double r34 = r34 / r40
            r40 = r13
            r12 = 2
        L_0x0160:
            if (r12 > r8) goto L_0x019d
            double r40 = r40 - r26
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r42 = r40 - r24
            double r44 = r42 - r30
            double r42 = r42 + r30
            double r44 = r44 * r42
            double r42 = r40 - r32
            double r46 = r30 - r42
            double r42 = r30 + r42
            double r42 = r42 * r46
            double[] r46 = FACT
            int r47 = r17 + -2
            r47 = r46[r47]
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r47 = r24 / r47
            double r36 = r36 + r47
            double r36 = r36 * r44
            double r36 = r36 * r42
            double r36 = r36 * r22
            int r47 = r17 + -1
            r47 = r46[r47]
            double r46 = r24 / r47
            double r34 = r34 + r46
            double r34 = r34 * r44
            double r34 = r34 * r38
            double r34 = r34 * r22
            int r17 = r17 + -2
            int r12 = r12 + 1
            r38 = r42
            goto L_0x0160
        L_0x019d:
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r36 = r36 + r24
            double r34 = r34 + r24
            double r32 = r30 * r30
            double r32 = r32 - r24
            double r34 = r34 * r32
            double r34 = r34 * r20
            int r12 = r11 + -1
            double r36 = r36 * r9
            double r34 = r34 * r6
            double r36 = r36 - r34
            double r36 = r36 * r15
            r5[r12] = r36
            r12 = 1
            if (r4 != r12) goto L_0x01c4
            org.apache.commons.math3.special.BesselJ$BesselJResult r0 = new org.apache.commons.math3.special.BesselJ$BesselJResult
            double[] r1 = org.apache.commons.math3.util.MathArrays.copyOf((double[]) r5, (int) r4)
            r0.<init>(r1, r4)
            return r0
        L_0x01c4:
            double r9 = -r9
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r30 = r30 + r28
            int r11 = r11 + 1
            r12 = 2
            r49 = r6
            r6 = r9
            r9 = r49
            goto L_0x0127
        L_0x01d3:
            r6 = r12
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r4 <= r6) goto L_0x01f0
            double r2 = r2 + r28
            r9 = 2
        L_0x01db:
            if (r9 >= r4) goto L_0x01f0
            int r6 = r9 + -1
            r6 = r5[r6]
            double r6 = r6 * r2
            double r6 = r6 / r0
            int r8 = r9 + -2
            r10 = r5[r8]
            double r6 = r6 - r10
            r5[r9] = r6
            r6 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r2 = r2 + r6
            int r9 = r9 + 1
            goto L_0x01db
        L_0x01f0:
            r0 = r4
            r7 = r0
            goto L_0x047e
        L_0x01f4:
            int r8 = r4 - r6
            int r9 = r6 + 1
            double r10 = (double) r9
            double r10 = r10 + r2
            r12 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 * r12
            double r12 = r10 / r0
            r14 = 3
            r20 = 4850873199050653696(0x4351c37937e08000, double:2.0E16)
            if (r8 < r14) goto L_0x02cf
            int r8 = r6 + 2
            int r10 = r4 + -1
            r11 = 1
            int r6 = r6 + r11
            double r14 = (double) r6
            double r14 = r14 + r2
            r26 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r14 = r14 * r26
            r6 = r9
            r30 = r12
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r9 = r8
        L_0x0219:
            r32 = 4846369599423283200(0x4341c37937e08000, double:1.0E16)
            if (r9 > r10) goto L_0x029e
            double r14 = r14 + r26
            double r26 = r14 * r30
            double r26 = r26 / r0
            double r26 = r26 - r11
            r11 = 8975683443661690106(0x7c9008896bcf54fa, double:1.0E292)
            int r6 = (r26 > r11 ? 1 : (r26 == r11 ? 0 : -1))
            if (r6 <= 0) goto L_0x028f
            r11 = 9214871658872686752(0x7fe1ccf385ebc8a0, double:1.0E308)
            double r34 = r26 / r11
            double r36 = r30 / r11
            int r6 = r9 + 1
            r26 = r34
            r20 = r36
        L_0x0240:
            int r8 = r9 + 1
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r14 = r14 + r28
            double r30 = r14 * r26
            double r30 = r30 / r0
            double r30 = r30 - r20
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r10 = (r30 > r24 ? 1 : (r30 == r24 ? 0 : -1))
            if (r10 <= 0) goto L_0x0289
            double r30 = r14 / r0
            double r20 = r20 * r26
            double r30 = r30 * r30
            double r30 = r18 / r30
            double r30 = r18 - r30
            double r20 = r20 * r30
            double r20 = r20 / r32
            double r11 = r11 * r26
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r14 = r14 - r28
            int r8 = org.apache.commons.math3.util.FastMath.min((int) r4, (int) r9)
        L_0x026a:
            if (r6 > r8) goto L_0x0280
            double r30 = r14 * r34
            double r30 = r30 / r0
            double r30 = r30 - r36
            double r36 = r30 * r34
            int r10 = (r36 > r20 ? 1 : (r36 == r20 ? 0 : -1))
            if (r10 <= 0) goto L_0x0279
            goto L_0x0280
        L_0x0279:
            int r6 = r6 + 1
            r36 = r34
            r34 = r30
            goto L_0x026a
        L_0x0280:
            r6 = r9
            r12 = r11
            r10 = r14
            r14 = r20
            r20 = 1
            r9 = r8
            goto L_0x02a9
        L_0x0289:
            r9 = r8
            r20 = r26
            r26 = r30
            goto L_0x0240
        L_0x028f:
            int r6 = r9 + 1
            r11 = r30
            r30 = r26
            r26 = 4611686018427387904(0x4000000000000000, double:2.0)
            r49 = r9
            r9 = r6
            r6 = r49
            goto L_0x0219
        L_0x029e:
            r8 = r4
            r9 = r10
            r26 = r11
            r10 = r14
            r14 = r20
            r12 = r30
            r20 = r7
        L_0x02a9:
            if (r20 != 0) goto L_0x02c7
            double r10 = (double) r9
            double r10 = r10 + r2
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 * r28
            double r32 = r32 * r26
            double r30 = org.apache.commons.math3.util.FastMath.sqrt(r32)
            double r32 = r12 * r28
            double r32 = org.apache.commons.math3.util.FastMath.sqrt(r32)
            r21 = r8
            double r7 = r30 * r32
            double r6 = org.apache.commons.math3.util.FastMath.max((double) r14, (double) r7)
            r14 = r6
            goto L_0x02cc
        L_0x02c7:
            r21 = r8
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            r9 = r6
        L_0x02cc:
            r7 = r21
            goto L_0x02d8
        L_0x02cf:
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            r7 = r4
            r14 = r20
            r20 = 0
            r26 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x02d8:
            r6 = 1
            if (r20 != 0) goto L_0x02f0
        L_0x02db:
            int r9 = r9 + r6
            double r10 = r10 + r28
            double r20 = r10 * r12
            double r20 = r20 / r0
            double r20 = r20 - r26
            int r8 = (r20 > r14 ? 1 : (r20 == r14 ? 0 : -1))
            if (r8 < 0) goto L_0x02eb
            r12 = r20
            goto L_0x02f0
        L_0x02eb:
            r26 = r12
            r12 = r20
            goto L_0x02db
        L_0x02f0:
            int r9 = r9 + r6
            double r10 = r10 + r28
            r14 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r14 / r12
            int r6 = r9 * 2
            int r8 = r9 / 2
            int r20 = r8 * 4
            int r6 = r6 - r20
            r20 = r7
            double r7 = (double) r8
            double r26 = r7 - r14
            double r26 = r26 + r2
            r14 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r30 = r7 * r14
            double r30 = r30 + r2
            if (r6 == 0) goto L_0x0317
            double r26 = r26 * r12
            double r26 = r26 * r30
            double r14 = r26 / r7
            r21 = r6
            goto L_0x031b
        L_0x0317:
            r21 = r6
            r14 = 0
        L_0x031b:
            int r6 = r9 - r4
            r26 = r12
            r4 = 1
            r12 = 0
        L_0x0322:
            if (r4 > r6) goto L_0x035e
            int r9 = r9 + -1
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 - r28
            double r30 = r10 * r26
            double r30 = r30 / r0
            double r30 = r30 - r12
            int r21 = 2 - r21
            if (r21 == 0) goto L_0x0357
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r7 - r12
            double r24 = r7 * r28
            double r32 = r24 + r2
            r12 = 1
            if (r9 != r12) goto L_0x0343
            r12 = r26
            r26 = r30
            goto L_0x035e
        L_0x0343:
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r35 = r7 - r12
            double r12 = r35 + r2
            r22 = 0
            int r35 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r35 != 0) goto L_0x0351
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x0351:
            double r32 = r32 * r30
            double r14 = r14 + r32
            double r14 = r14 * r12
            double r14 = r14 / r7
        L_0x0357:
            int r4 = r4 + 1
            r12 = r26
            r26 = r30
            goto L_0x0322
        L_0x035e:
            int r4 = r9 + -1
            r5[r4] = r26
            r4 = r55
            if (r6 < 0) goto L_0x03b6
            r6 = 1
            if (r4 > r6) goto L_0x037f
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r12 = r2 + r24
            int r6 = (r12 > r24 ? 1 : (r12 == r24 ? 0 : -1))
            if (r6 != 0) goto L_0x0375
            r6 = 0
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0377
        L_0x0375:
            r12 = r2
            r6 = 0
        L_0x0377:
            r26 = r5[r6]
            double r26 = r26 * r12
            double r14 = r14 + r26
            r6 = 1
            goto L_0x03b7
        L_0x037f:
            int r6 = r9 + -1
            r28 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 - r28
            int r9 = r9 + -2
            double r26 = r26 * r10
            double r26 = r26 / r0
            double r26 = r26 - r12
            r5[r9] = r26
            r9 = 1
            if (r6 != r9) goto L_0x0396
            r9 = r6
            r6 = 0
            r12 = 1
            goto L_0x03b8
        L_0x0396:
            int r21 = 2 - r21
            if (r21 == 0) goto L_0x03b5
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r7 - r12
            r24 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r30 = r7 * r24
            double r30 = r30 + r2
            double r32 = r7 - r12
            double r12 = r32 + r2
            r22 = 0
            int r9 = (r12 > r22 ? 1 : (r12 == r22 ? 0 : -1))
            if (r9 != 0) goto L_0x03af
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x03af:
            double r26 = r26 * r30
            double r14 = r14 + r26
            double r14 = r14 * r12
            double r14 = r14 / r7
        L_0x03b5:
            r9 = r6
        L_0x03b6:
            r6 = 0
        L_0x03b7:
            r12 = 0
        L_0x03b8:
            if (r6 != 0) goto L_0x03fe
            if (r12 != 0) goto L_0x03fe
            int r13 = r9 + -2
            if (r13 == 0) goto L_0x03fe
            r4 = 1
        L_0x03c1:
            if (r4 > r13) goto L_0x03fe
            int r26 = r9 + -1
            r27 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r10 = r10 - r27
            int r27 = r9 + -2
            r30 = r5[r26]
            double r30 = r30 * r10
            double r30 = r30 / r0
            r32 = r5[r9]
            double r30 = r30 - r32
            r5[r27] = r30
            r9 = 2
            int r21 = 2 - r21
            if (r21 == 0) goto L_0x03f9
            r24 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r7 - r24
            r27 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r32 = r7 * r27
            double r32 = r32 + r2
            double r35 = r7 - r24
            double r35 = r35 + r2
            r22 = 0
            int r9 = (r35 > r22 ? 1 : (r35 == r22 ? 0 : -1))
            if (r9 != 0) goto L_0x03f2
            r35 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x03f2:
            double r30 = r30 * r32
            double r14 = r14 + r30
            double r14 = r14 * r35
            double r14 = r14 / r7
        L_0x03f9:
            int r4 = r4 + 1
            r9 = r26
            goto L_0x03c1
        L_0x03fe:
            if (r6 != 0) goto L_0x042f
            if (r12 != 0) goto L_0x0419
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r11 = r2 + r9
            r26 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r11 = r11 * r26
            r4 = 1
            r24 = r5[r4]
            double r11 = r11 * r24
            double r11 = r11 / r0
            r4 = 2
            r24 = r5[r4]
            double r11 = r11 - r24
            r4 = 0
            r5[r4] = r11
            goto L_0x041e
        L_0x0419:
            r4 = 0
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r26 = 4611686018427387904(0x4000000000000000, double:2.0)
        L_0x041e:
            double r7 = r7 - r9
            double r7 = r7 * r26
            double r6 = r7 + r2
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 != 0) goto L_0x042b
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x042b:
            r8 = r5[r4]
            double r8 = r8 * r6
            double r14 = r14 + r8
        L_0x042f:
            double r6 = org.apache.commons.math3.util.FastMath.abs((double) r53)
            r8 = 4367597403136100796(0x3c9cd2b297d889bc, double:1.0E-16)
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 <= 0) goto L_0x0449
            double r6 = org.apache.commons.math3.special.Gamma.gamma(r53)
            double r0 = r0 * r18
            double r2 = -r2
            double r0 = org.apache.commons.math3.util.FastMath.pow((double) r0, (double) r2)
            double r6 = r6 * r0
            double r14 = r14 * r6
        L_0x0449:
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0451
            double r16 = r16 * r14
        L_0x0451:
            r0 = r55
            r7 = 0
        L_0x0454:
            if (r7 >= r0) goto L_0x046c
            r1 = r5[r7]
            double r1 = org.apache.commons.math3.util.FastMath.abs((double) r1)
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 >= 0) goto L_0x0464
            r1 = 0
            r5[r7] = r1
        L_0x0464:
            r1 = r5[r7]
            double r1 = r1 / r14
            r5[r7] = r1
            int r7 = r7 + 1
            goto L_0x0454
        L_0x046c:
            r7 = r20
            goto L_0x047e
        L_0x046f:
            r0 = r4
            r1 = 0
            if (r0 <= 0) goto L_0x0477
            r2 = 0
            r5[r1] = r2
        L_0x0477:
            int r1 = org.apache.commons.math3.util.FastMath.min((int) r0, (int) r1)
            r2 = 1
            int r7 = r1 + -1
        L_0x047e:
            org.apache.commons.math3.special.BesselJ$BesselJResult r1 = new org.apache.commons.math3.special.BesselJ$BesselJResult
            double[] r0 = org.apache.commons.math3.util.MathArrays.copyOf((double[]) r5, (int) r0)
            r1.<init>(r0, r7)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.special.BesselJ.rjBesl(double, double, int):org.apache.commons.math3.special.BesselJ$BesselJResult");
    }
}
