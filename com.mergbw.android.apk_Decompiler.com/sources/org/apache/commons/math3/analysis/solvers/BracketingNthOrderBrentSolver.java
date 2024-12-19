package org.apache.commons.math3.analysis.solvers;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;

public class BracketingNthOrderBrentSolver extends AbstractUnivariateSolver implements BracketedUnivariateSolver<UnivariateFunction> {
    private static final double DEFAULT_ABSOLUTE_ACCURACY = 1.0E-6d;
    private static final int DEFAULT_MAXIMAL_ORDER = 5;
    private static final int MAXIMAL_AGING = 2;
    private static final double REDUCTION_FACTOR = 0.0625d;
    private AllowedSolution allowed;
    private final int maximalOrder;

    public BracketingNthOrderBrentSolver() {
        this(1.0E-6d, 5);
    }

    public BracketingNthOrderBrentSolver(double d, int i) throws NumberIsTooSmallException {
        super(d);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public BracketingNthOrderBrentSolver(double d, double d2, int i) throws NumberIsTooSmallException {
        super(d, d2);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public BracketingNthOrderBrentSolver(double d, double d2, double d3, int i) throws NumberIsTooSmallException {
        super(d, d2, d3);
        if (i >= 2) {
            this.maximalOrder = i;
            this.allowed = AllowedSolution.ANY_SIDE;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0155 A[LOOP:1: B:32:0x0109->B:46:0x0155, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0163 A[EDGE_INSN: B:101:0x0163->B:47:0x0163 ?: BREAK  
    EDGE_INSN: B:102:0x0163->B:47:0x0163 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0180  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x017f A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public double doSolve() throws org.apache.commons.math3.exception.TooManyEvaluationsException, org.apache.commons.math3.exception.NumberIsTooLargeException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r41 = this;
            r7 = r41
            int r8 = r7.maximalOrder
            int r9 = r8 + 1
            double[] r10 = new double[r9]
            int r0 = r8 + 1
            double[] r11 = new double[r0]
            double r0 = r41.getMin()
            r12 = 0
            r10[r12] = r0
            double r0 = r41.getStartValue()
            r13 = 1
            r10[r13] = r0
            double r5 = r41.getMax()
            r14 = 2
            r10[r14] = r5
            r1 = r10[r12]
            r3 = r10[r13]
            r0 = r41
            r0.verifySequence(r1, r3, r5)
            r0 = r10[r13]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r13] = r0
            r5 = 0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r5, (int) r13)
            if (r0 == 0) goto L_0x003d
            r0 = r10[r13]
            return r0
        L_0x003d:
            r0 = r10[r12]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r12] = r0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r5, (int) r13)
            if (r0 == 0) goto L_0x004e
            r0 = r10[r12]
            return r0
        L_0x004e:
            r0 = r11[r12]
            r2 = r11[r13]
            double r0 = r0 * r2
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x005a
            r0 = r13
            r1 = r14
            goto L_0x0076
        L_0x005a:
            r0 = r10[r14]
            double r0 = r7.computeObjectiveValue(r0)
            r11[r14] = r0
            boolean r0 = org.apache.commons.math3.util.Precision.equals((double) r0, (double) r5, (int) r13)
            if (r0 == 0) goto L_0x006b
            r0 = r10[r14]
            return r0
        L_0x006b:
            r0 = r11[r13]
            r2 = r11[r14]
            double r0 = r0 * r2
            int r0 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x022c
            r0 = r14
            r1 = 3
        L_0x0076:
            double[] r4 = new double[r9]
            int r2 = r0 + -1
            r16 = r10[r2]
            r2 = r11[r2]
            double r18 = org.apache.commons.math3.util.FastMath.abs((double) r2)
            r20 = r10[r0]
            r22 = r11[r0]
            double r24 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            r14 = r24
            r24 = r22
            r22 = r20
            r39 = r2
            r3 = r0
            r0 = r12
            r2 = r0
            r12 = r18
            r18 = r16
            r16 = r39
        L_0x009b:
            double r26 = r41.getAbsoluteAccuracy()
            double r28 = r41.getRelativeAccuracy()
            double r5 = org.apache.commons.math3.util.FastMath.abs((double) r18)
            r32 = r8
            r33 = r9
            double r8 = org.apache.commons.math3.util.FastMath.abs((double) r22)
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r8)
            double r28 = r28 * r5
            double r26 = r26 + r28
            double r8 = r22 - r18
            int r5 = (r8 > r26 ? 1 : (r8 == r26 ? 0 : -1))
            if (r5 <= 0) goto L_0x01ee
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r12, (double) r14)
            double r26 = r41.getFunctionValueAccuracy()
            int r5 = (r5 > r26 ? 1 : (r5 == r26 ? 0 : -1))
            if (r5 >= 0) goto L_0x00cb
            goto L_0x01ee
        L_0x00cb:
            r5 = 2
            if (r2 < r5) goto L_0x00ea
            int r5 = r2 + -2
            r6 = 1
            int r5 = r6 << r5
            int r5 = r5 - r6
            double r5 = (double) r5
            r28 = r1
            int r1 = r2 + -1
            r29 = r2
            double r1 = (double) r1
            double r34 = r5 * r16
            r26 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r26 = r26 * r1
            double r26 = r26 * r24
        L_0x00e4:
            double r34 = r34 - r26
            double r5 = r5 + r1
            double r34 = r34 / r5
            goto L_0x0106
        L_0x00ea:
            r28 = r1
            r29 = r2
            r1 = r5
            if (r0 < r1) goto L_0x0104
            int r1 = r0 + -2
            int r2 = r0 + -1
            double r5 = (double) r2
            r2 = 1
            int r1 = r2 << r1
            int r1 = r1 - r2
            double r1 = (double) r1
            double r34 = r1 * r24
            r26 = 4589168020290535424(0x3fb0000000000000, double:0.0625)
            double r26 = r26 * r5
            double r26 = r26 * r16
            goto L_0x00e4
        L_0x0104:
            r34 = 0
        L_0x0106:
            r26 = r28
            r6 = 0
        L_0x0109:
            int r1 = r26 - r6
            java.lang.System.arraycopy(r10, r6, r4, r6, r1)
            r27 = r0
            r0 = r41
            r5 = r28
            r28 = r29
            r1 = r34
            r36 = r12
            r12 = r3
            r3 = r4
            r13 = r4
            r4 = r11
            r29 = r13
            r30 = r14
            r14 = 0
            r13 = r5
            r5 = r6
            r38 = r6
            r6 = r26
            double r0 = r0.guessX(r1, r3, r4, r5, r6)
            int r2 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r2 <= 0) goto L_0x013a
            int r2 = (r0 > r22 ? 1 : (r0 == r22 ? 0 : -1))
            if (r2 < 0) goto L_0x0137
            goto L_0x013a
        L_0x0137:
            r6 = r38
            goto L_0x0149
        L_0x013a:
            int r3 = r12 - r38
            int r0 = r26 - r12
            if (r3 < r0) goto L_0x0143
            int r6 = r38 + 1
            goto L_0x0147
        L_0x0143:
            int r26 = r26 + -1
            r6 = r38
        L_0x0147:
            r0 = 9221120237041090560(0x7ff8000000000000, double:NaN)
        L_0x0149:
            boolean r2 = java.lang.Double.isNaN(r0)
            if (r2 == 0) goto L_0x0163
            int r2 = r26 - r6
            r3 = 1
            if (r2 > r3) goto L_0x0155
            goto L_0x0163
        L_0x0155:
            r3 = r12
            r0 = r27
            r4 = r29
            r14 = r30
            r29 = r28
            r28 = r13
            r12 = r36
            goto L_0x0109
        L_0x0163:
            boolean r2 = java.lang.Double.isNaN(r0)
            if (r2 == 0) goto L_0x0172
            r0 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r8 = r8 * r0
            double r0 = r18 + r8
            int r6 = r12 + -1
            r3 = r12
            goto L_0x0174
        L_0x0172:
            r3 = r26
        L_0x0174:
            double r4 = r7.computeObjectiveValue(r0)
            r2 = 1
            boolean r8 = org.apache.commons.math3.util.Precision.equals((double) r4, (double) r14, (int) r2)
            if (r8 == 0) goto L_0x0180
            return r0
        L_0x0180:
            r2 = 2
            if (r13 <= r2) goto L_0x0194
            int r2 = r3 - r6
            if (r2 == r13) goto L_0x0194
            r3 = 0
            java.lang.System.arraycopy(r10, r6, r10, r3, r2)
            java.lang.System.arraycopy(r11, r6, r11, r3, r2)
            int r6 = r12 - r6
            r3 = r6
            r8 = r33
            goto L_0x01af
        L_0x0194:
            r3 = 0
            r8 = r33
            if (r13 != r8) goto L_0x01ad
            int r2 = r13 + -1
            r6 = 2
            int r9 = r32 + 2
            int r9 = r9 / r6
            if (r12 < r9) goto L_0x01ab
            r6 = 1
            java.lang.System.arraycopy(r10, r6, r10, r3, r2)
            java.lang.System.arraycopy(r11, r6, r11, r3, r2)
            int r3 = r12 + -1
            goto L_0x01af
        L_0x01ab:
            r3 = r12
            goto L_0x01af
        L_0x01ad:
            r3 = r12
            r2 = r13
        L_0x01af:
            int r6 = r3 + 1
            int r9 = r2 - r3
            java.lang.System.arraycopy(r10, r3, r10, r6, r9)
            r10[r3] = r0
            java.lang.System.arraycopy(r11, r3, r11, r6, r9)
            r11[r3] = r4
            r9 = 1
            int r2 = r2 + r9
            double r12 = r4 * r16
            int r9 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r9 > 0) goto L_0x01d5
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r4)
            int r6 = r28 + 1
            r22 = r0
            r24 = r4
            r30 = r12
            r12 = r36
            r0 = 0
            goto L_0x01e2
        L_0x01d5:
            double r12 = org.apache.commons.math3.util.FastMath.abs((double) r4)
            int r3 = r27 + 1
            r18 = r0
            r0 = r3
            r16 = r4
            r3 = r6
            r6 = 0
        L_0x01e2:
            r1 = r2
            r2 = r6
            r9 = r8
            r5 = r14
            r4 = r29
            r14 = r30
            r8 = r32
            goto L_0x009b
        L_0x01ee:
            r36 = r12
            r30 = r14
            r14 = 0
            int[] r0 = org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = r7.allowed
            int r1 = r1.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L_0x0224
            r1 = 2
            if (r0 == r1) goto L_0x0223
            r1 = 3
            if (r0 == r1) goto L_0x0222
            r1 = 4
            if (r0 == r1) goto L_0x021a
            r1 = 5
            if (r0 != r1) goto L_0x0214
            int r0 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r0 >= 0) goto L_0x0213
            r18 = r22
        L_0x0213:
            return r18
        L_0x0214:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r0.<init>()
            throw r0
        L_0x021a:
            int r0 = (r16 > r14 ? 1 : (r16 == r14 ? 0 : -1))
            if (r0 > 0) goto L_0x021f
            goto L_0x0221
        L_0x021f:
            r18 = r22
        L_0x0221:
            return r18
        L_0x0222:
            return r22
        L_0x0223:
            return r18
        L_0x0224:
            int r0 = (r36 > r30 ? 1 : (r36 == r30 ? 0 : -1))
            if (r0 >= 0) goto L_0x0229
            goto L_0x022b
        L_0x0229:
            r18 = r22
        L_0x022b:
            return r18
        L_0x022c:
            org.apache.commons.math3.exception.NoBracketingException r0 = new org.apache.commons.math3.exception.NoBracketingException
            r1 = 0
            r2 = r10[r1]
            r4 = 2
            r5 = r10[r4]
            r13 = r11[r1]
            r15 = r11[r4]
            r8 = r0
            r9 = r2
            r11 = r5
            r8.<init>(r9, r11, r13, r15)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.doSolve():double");
    }

    /* renamed from: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.apache.commons.math3.analysis.solvers.AllowedSolution[] r0 = org.apache.commons.math3.analysis.solvers.AllowedSolution.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution = r0
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ANY_SIDE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.LEFT_SIDE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.RIGHT_SIDE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.BELOW_SIDE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution     // Catch:{ NoSuchFieldError -> 0x003e }
                org.apache.commons.math3.analysis.solvers.AllowedSolution r1 = org.apache.commons.math3.analysis.solvers.AllowedSolution.ABOVE_SIDE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.analysis.solvers.BracketingNthOrderBrentSolver.AnonymousClass1.<clinit>():void");
        }
    }

    private double guessX(double d, double[] dArr, double[] dArr2, int i, int i2) {
        int i3;
        int i4 = i;
        int i5 = i4;
        while (true) {
            i3 = i2 - 1;
            if (i5 >= i3) {
                break;
            }
            int i6 = i5 + 1;
            int i7 = i6 - i4;
            while (i3 > i5) {
                dArr[i3] = (dArr[i3] - dArr[i3 - 1]) / (dArr2[i3] - dArr2[i3 - i7]);
                i3--;
            }
            i5 = i6;
        }
        double d2 = 0.0d;
        while (i3 >= i4) {
            d2 = (d2 * (d - dArr2[i3])) + dArr[i3];
            i3--;
        }
        return d2;
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2);
    }

    public double solve(int i, UnivariateFunction univariateFunction, double d, double d2, double d3, AllowedSolution allowedSolution) throws TooManyEvaluationsException, NumberIsTooLargeException, NoBracketingException {
        this.allowed = allowedSolution;
        return super.solve(i, univariateFunction, d, d2, d3);
    }
}
