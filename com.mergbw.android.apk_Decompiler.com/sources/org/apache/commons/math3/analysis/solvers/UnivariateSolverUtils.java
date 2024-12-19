package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public class UnivariateSolverUtils {
    public static boolean isSequence(double d, double d2, double d3) {
        return d < d2 && d2 < d3;
    }

    public static double midpoint(double d, double d2) {
        return (d + d2) * 0.5d;
    }

    private UnivariateSolverUtils() {
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction != null) {
            return new BrentSolver().solve(Integer.MAX_VALUE, univariateFunction, d, d2);
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }

    public static double solve(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NullArgumentException, NoBracketingException {
        if (univariateFunction != null) {
            return new BrentSolver(d3).solve(Integer.MAX_VALUE, univariateFunction, d, d2);
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x006a, code lost:
        if (r12 >= 0) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0076, code lost:
        if (r13 <= 0) goto L_0x006c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x003d A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static double forceSide(int r29, org.apache.commons.math3.analysis.UnivariateFunction r30, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver<org.apache.commons.math3.analysis.UnivariateFunction> r31, double r32, double r34, double r36, org.apache.commons.math3.analysis.solvers.AllowedSolution r38) throws org.apache.commons.math3.exception.NoBracketingException {
        /*
            r2 = r30
            r0 = r34
            r3 = r36
            org.apache.commons.math3.analysis.solvers.AllowedSolution r5 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE
            r9 = r38
            if (r9 != r5) goto L_0x000d
            return r32
        L_0x000d:
            double r5 = r31.getAbsoluteAccuracy()
            double r7 = r31.getRelativeAccuracy()
            double r7 = r7 * r32
            double r7 = org.apache.commons.math3.util.FastMath.abs((double) r7)
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r7)
            double r7 = r32 - r5
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r7)
            double r10 = r2.value(r7)
            double r12 = r32 + r5
            double r12 = org.apache.commons.math3.util.FastMath.min((double) r3, (double) r12)
            double r14 = r2.value(r12)
            int r16 = r29 + -2
            r19 = r7
            r23 = r10
            r21 = r12
            r25 = r14
        L_0x003d:
            r7 = 0
            r8 = 1
            if (r16 <= 0) goto L_0x009f
            r10 = 0
            int r12 = (r23 > r10 ? 1 : (r23 == r10 ? 0 : -1))
            if (r12 < 0) goto L_0x004b
            int r13 = (r25 > r10 ? 1 : (r25 == r10 ? 0 : -1))
            if (r13 <= 0) goto L_0x0053
        L_0x004b:
            int r13 = (r23 > r10 ? 1 : (r23 == r10 ? 0 : -1))
            if (r13 > 0) goto L_0x0066
            int r10 = (r25 > r10 ? 1 : (r25 == r10 ? 0 : -1))
            if (r10 < 0) goto L_0x0066
        L_0x0053:
            r0 = r31
            r1 = r16
            r2 = r30
            r3 = r19
            r5 = r21
            r7 = r32
            r9 = r38
            double r0 = r0.solve(r1, r2, r3, r5, r7, r9)
            return r0
        L_0x0066:
            int r10 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
            if (r10 >= 0) goto L_0x0072
            if (r12 < 0) goto L_0x007a
        L_0x006c:
            r28 = r8
            r8 = r7
            r7 = r28
            goto L_0x007a
        L_0x0072:
            int r10 = (r23 > r25 ? 1 : (r23 == r25 ? 0 : -1))
            if (r10 <= 0) goto L_0x0079
            if (r13 > 0) goto L_0x007a
            goto L_0x006c
        L_0x0079:
            r7 = r8
        L_0x007a:
            if (r7 == 0) goto L_0x008c
            double r10 = r19 - r5
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r10)
            double r12 = r2.value(r10)
            int r16 = r16 + -1
            r19 = r10
            r23 = r12
        L_0x008c:
            if (r8 == 0) goto L_0x003d
            double r7 = r21 + r5
            double r7 = org.apache.commons.math3.util.FastMath.min((double) r3, (double) r7)
            double r10 = r2.value(r7)
            int r16 = r16 + -1
            r21 = r7
            r25 = r10
            goto L_0x003d
        L_0x009f:
            org.apache.commons.math3.exception.NoBracketingException r2 = new org.apache.commons.math3.exception.NoBracketingException
            org.apache.commons.math3.exception.util.LocalizedFormats r18 = org.apache.commons.math3.exception.util.LocalizedFormats.FAILED_BRACKETING
            int r5 = r29 - r16
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r29)
            java.lang.Double r9 = java.lang.Double.valueOf(r32)
            java.lang.Double r0 = java.lang.Double.valueOf(r34)
            java.lang.Double r1 = java.lang.Double.valueOf(r36)
            r3 = 5
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r7] = r5
            r3[r8] = r6
            r4 = 2
            r3[r4] = r9
            r4 = 3
            r3[r4] = r0
            r0 = 4
            r3[r0] = r1
            r17 = r2
            r27 = r3
            r17.<init>(r18, r19, r21, r23, r25, r27)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.UnivariateSolverUtils.forceSide(int, org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.analysis.solvers.BracketedUnivariateSolver, double, double, double, org.apache.commons.math3.analysis.solvers.AllowedSolution):double");
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(univariateFunction, d, d2, d3, 1.0d, 1.0d, Integer.MAX_VALUE);
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3, int i) throws NullArgumentException, NotStrictlyPositiveException, NoBracketingException {
        return bracket(univariateFunction, d, d2, d3, 1.0d, 1.0d, i);
    }

    public static double[] bracket(UnivariateFunction univariateFunction, double d, double d2, double d3, double d4, double d5, int i) throws NoBracketingException {
        long j;
        UnivariateFunction univariateFunction2 = univariateFunction;
        double d6 = d2;
        double d7 = d3;
        int i2 = i;
        if (univariateFunction2 == null) {
            throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
        } else if (d4 <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d4));
        } else if (i2 > 0) {
            double d8 = d;
            verifySequence(d2, d8, d3);
            double d9 = Double.NaN;
            double d10 = d8;
            double d11 = Double.NaN;
            double d12 = 0.0d;
            int i3 = 0;
            while (i3 < i2 && (d8 > d6 || d10 < d7)) {
                d12 = (d12 * d5) + d4;
                double max = FastMath.max(d - d12, d6);
                double min = FastMath.min(d + d12, d7);
                double value = univariateFunction2.value(max);
                double value2 = univariateFunction2.value(min);
                if (i3 == 0) {
                    j = 0;
                    if (value * value2 <= 0.0d) {
                        return new double[]{max, min};
                    }
                } else {
                    j = 0;
                    if (d9 * value <= 0.0d) {
                        return new double[]{max, d8};
                    } else if (d11 * value2 <= 0.0d) {
                        return new double[]{d10, min};
                    }
                }
                i3++;
                univariateFunction2 = univariateFunction;
                d10 = min;
                d8 = max;
                long j2 = j;
                d9 = value;
                d11 = value2;
                d6 = d2;
            }
            throw new NoBracketingException(d8, d10, d9, d11);
        } else {
            throw new NotStrictlyPositiveException(LocalizedFormats.INVALID_MAX_ITERATIONS, Integer.valueOf(i));
        }
    }

    public static boolean isBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException {
        if (univariateFunction != null) {
            double value = univariateFunction.value(d);
            double value2 = univariateFunction.value(d2);
            if ((value < 0.0d || value2 > 0.0d) && (value > 0.0d || value2 < 0.0d)) {
                return false;
            }
            return true;
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }

    public static void verifyInterval(double d, double d2) throws NumberIsTooLargeException {
        if (d >= d2) {
            throw new NumberIsTooLargeException(LocalizedFormats.ENDPOINTS_NOT_AN_INTERVAL, Double.valueOf(d), Double.valueOf(d2), false);
        }
    }

    public static void verifySequence(double d, double d2, double d3) throws NumberIsTooLargeException {
        verifyInterval(d, d2);
        verifyInterval(d2, d3);
    }

    public static void verifyBracketing(UnivariateFunction univariateFunction, double d, double d2) throws NullArgumentException, NoBracketingException {
        if (univariateFunction != null) {
            verifyInterval(d, d2);
            if (!isBracketing(univariateFunction, d, d2)) {
                throw new NoBracketingException(d, d2, univariateFunction.value(d), univariateFunction.value(d2));
            }
            return;
        }
        throw new NullArgumentException(LocalizedFormats.FUNCTION, new Object[0]);
    }
}
