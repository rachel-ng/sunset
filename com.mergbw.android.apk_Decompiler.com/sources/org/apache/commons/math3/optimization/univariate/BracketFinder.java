package org.apache.commons.math3.optimization.univariate;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.util.Incrementor;

@Deprecated
public class BracketFinder {
    private static final double EPS_MIN = 1.0E-21d;
    private static final double GOLD = 1.618034d;
    private final Incrementor evaluations;
    private double fHi;
    private double fLo;
    private double fMid;
    private final double growLimit;
    private double hi;
    private double lo;
    private double mid;

    public BracketFinder() {
        this(100.0d, 50);
    }

    public BracketFinder(double d, int i) {
        Incrementor incrementor = new Incrementor();
        this.evaluations = incrementor;
        if (d <= 0.0d) {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        } else if (i > 0) {
            this.growLimit = d;
            incrementor.setMaximalCount(i);
        } else {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009f, code lost:
        r1 = r15;
        r31 = r5;
        r5 = r9;
        r9 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x012e, code lost:
        r5 = r5;
        r13 = r3;
        r3 = r7;
        r1 = r15;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void search(org.apache.commons.math3.analysis.UnivariateFunction r34, org.apache.commons.math3.optimization.GoalType r35, double r36, double r38) {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
            org.apache.commons.math3.util.Incrementor r2 = r0.evaluations
            r2.resetCount()
            org.apache.commons.math3.optimization.GoalType r2 = org.apache.commons.math3.optimization.GoalType.MINIMIZE
            r3 = r35
            if (r3 != r2) goto L_0x0011
            r2 = 1
            goto L_0x0012
        L_0x0011:
            r2 = 0
        L_0x0012:
            r3 = r36
            double r5 = r0.eval(r1, r3)
            r7 = r38
            double r9 = r0.eval(r1, r7)
            if (r2 == 0) goto L_0x0025
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x002f
            goto L_0x0029
        L_0x0025:
            int r11 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x002f
        L_0x0029:
            r31 = r5
            r5 = r9
            r9 = r31
            goto L_0x0034
        L_0x002f:
            r31 = r3
            r3 = r7
            r7 = r31
        L_0x0034:
            double r11 = r3 - r7
            r13 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r11 = r11 * r13
            double r11 = r11 + r3
            double r15 = r0.eval(r1, r11)
        L_0x0041:
            if (r2 == 0) goto L_0x0048
            int r17 = (r15 > r9 ? 1 : (r15 == r9 ? 0 : -1))
            if (r17 >= 0) goto L_0x012e
            goto L_0x004c
        L_0x0048:
            int r17 = (r15 > r9 ? 1 : (r15 == r9 ? 0 : -1))
            if (r17 <= 0) goto L_0x012e
        L_0x004c:
            double r17 = r3 - r7
            double r19 = r9 - r15
            double r19 = r19 * r17
            double r21 = r3 - r11
            double r23 = r9 - r5
            double r23 = r23 * r21
            double r25 = r23 - r19
            double r27 = org.apache.commons.math3.util.FastMath.abs((double) r25)
            r29 = 4292743757239851855(0x3b92e3b40a0e9b4f, double:1.0E-21)
            int r27 = (r27 > r29 ? 1 : (r27 == r29 ? 0 : -1))
            if (r27 >= 0) goto L_0x006d
            r25 = 4297247356867222351(0x3ba2e3b40a0e9b4f, double:2.0E-21)
            goto L_0x0071
        L_0x006d:
            r27 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r25 = r25 * r27
        L_0x0071:
            double r21 = r21 * r23
            double r17 = r17 * r19
            double r21 = r21 - r17
            double r21 = r21 / r25
            double r13 = r3 - r21
            r37 = r5
            double r5 = r0.growLimit
            double r17 = r11 - r3
            double r5 = r5 * r17
            double r5 = r5 + r3
            double r19 = r13 - r11
            double r21 = r3 - r13
            double r21 = r21 * r19
            r23 = 0
            int r21 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r21 <= 0) goto L_0x00c8
            double r5 = r0.eval(r1, r13)
            if (r2 == 0) goto L_0x009b
            int r19 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r19 >= 0) goto L_0x00a7
            goto L_0x009f
        L_0x009b:
            int r19 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r19 <= 0) goto L_0x00a7
        L_0x009f:
            r1 = r15
            r31 = r5
            r5 = r9
            r9 = r31
            goto L_0x0135
        L_0x00a7:
            if (r2 == 0) goto L_0x00ae
            int r19 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r19 <= 0) goto L_0x00ba
            goto L_0x00b2
        L_0x00ae:
            int r19 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r19 >= 0) goto L_0x00ba
        L_0x00b2:
            r1 = r5
            r11 = r13
            r5 = r37
            r13 = r3
            r3 = r7
            goto L_0x0135
        L_0x00ba:
            r5 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r17 = r17 * r5
            double r5 = r11 + r17
            double r7 = r0.eval(r1, r5)
            goto L_0x00d6
        L_0x00c8:
            double r7 = r13 - r5
            double r21 = r5 - r11
            double r21 = r21 * r7
            int r21 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r21 < 0) goto L_0x00e5
            double r7 = r0.eval(r1, r5)
        L_0x00d6:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
        L_0x00db:
            r31 = r9
            r9 = r15
            r15 = r7
            r7 = r3
            r3 = r11
            r11 = r5
        L_0x00e2:
            r5 = r31
            goto L_0x012a
        L_0x00e5:
            double r5 = r11 - r13
            double r7 = r7 * r5
            int r5 = (r7 > r23 ? 1 : (r7 == r23 ? 0 : -1))
            if (r5 <= 0) goto L_0x011c
            double r5 = r0.eval(r1, r13)
            if (r2 == 0) goto L_0x00f7
            int r7 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r7 >= 0) goto L_0x010f
            goto L_0x00fb
        L_0x00f7:
            int r7 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r7 <= 0) goto L_0x010f
        L_0x00fb:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r19 = r19 * r21
            double r3 = r13 + r19
            double r7 = r0.eval(r1, r3)
            r9 = r5
            r5 = r15
            r15 = r7
            r7 = r11
            r11 = r3
            r3 = r13
            goto L_0x012a
        L_0x010f:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            r7 = r3
            r3 = r11
            r11 = r13
            r31 = r9
            r9 = r15
            r15 = r5
            goto L_0x00e2
        L_0x011c:
            r21 = 4609965796492119705(0x3ff9e3779e9d0e99, double:1.618034)
            double r17 = r17 * r21
            double r5 = r11 + r17
            double r7 = r0.eval(r1, r5)
            goto L_0x00db
        L_0x012a:
            r13 = r21
            goto L_0x0041
        L_0x012e:
            r37 = r5
            r5 = r37
            r13 = r3
            r3 = r7
            r1 = r15
        L_0x0135:
            r0.lo = r3
            r0.fLo = r5
            r0.mid = r13
            r0.fMid = r9
            r0.hi = r11
            r0.fHi = r1
            int r7 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r7 <= 0) goto L_0x014d
            r0.lo = r11
            r0.hi = r3
            r0.fLo = r1
            r0.fHi = r5
        L_0x014d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.univariate.BracketFinder.search(org.apache.commons.math3.analysis.UnivariateFunction, org.apache.commons.math3.optimization.GoalType, double, double):void");
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public double getLo() {
        return this.lo;
    }

    public double getFLo() {
        return this.fLo;
    }

    public double getHi() {
        return this.hi;
    }

    public double getFHi() {
        return this.fHi;
    }

    public double getMid() {
        return this.mid;
    }

    public double getFMid() {
        return this.fMid;
    }

    private double eval(UnivariateFunction univariateFunction, double d) {
        try {
            this.evaluations.incrementCount();
            return univariateFunction.value(d);
        } catch (MaxCountExceededException e) {
            throw new TooManyEvaluationsException(e.getMax());
        }
    }
}
