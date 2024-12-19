package org.apache.commons.math3.dfp;

import org.apache.commons.math3.analysis.solvers.AllowedSolution;
import org.apache.commons.math3.exception.NoBracketingException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.Incrementor;

public class BracketingNthOrderBrentSolverDFP {
    private static final int MAXIMAL_AGING = 2;
    private final Dfp absoluteAccuracy;
    private final Incrementor evaluations = new Incrementor();
    private final Dfp functionValueAccuracy;
    private final int maximalOrder;
    private final Dfp relativeAccuracy;

    public BracketingNthOrderBrentSolverDFP(Dfp dfp, Dfp dfp2, Dfp dfp3, int i) throws NumberIsTooSmallException {
        if (i >= 2) {
            this.maximalOrder = i;
            this.absoluteAccuracy = dfp2;
            this.relativeAccuracy = dfp;
            this.functionValueAccuracy = dfp3;
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
    }

    public int getMaximalOrder() {
        return this.maximalOrder;
    }

    public int getMaxEvaluations() {
        return this.evaluations.getMaximalCount();
    }

    public int getEvaluations() {
        return this.evaluations.getCount();
    }

    public Dfp getAbsoluteAccuracy() {
        return this.absoluteAccuracy;
    }

    public Dfp getRelativeAccuracy() {
        return this.relativeAccuracy;
    }

    public Dfp getFunctionValueAccuracy() {
        return this.functionValueAccuracy;
    }

    public Dfp solve(int i, UnivariateDfpFunction univariateDfpFunction, Dfp dfp, Dfp dfp2, AllowedSolution allowedSolution) throws NullArgumentException, NoBracketingException {
        return solve(i, univariateDfpFunction, dfp, dfp2, dfp.add(dfp2).divide(2), allowedSolution);
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x01bd A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x017a A[LOOP:1: B:39:0x0125->B:53:0x017a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0190 A[EDGE_INSN: B:112:0x0190->B:54:0x0190 ?: BREAK  
    EDGE_INSN: B:113:0x0190->B:54:0x0190 ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0196  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0221  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.dfp.Dfp solve(int r30, org.apache.commons.math3.dfp.UnivariateDfpFunction r31, org.apache.commons.math3.dfp.Dfp r32, org.apache.commons.math3.dfp.Dfp r33, org.apache.commons.math3.dfp.Dfp r34, org.apache.commons.math3.analysis.solvers.AllowedSolution r35) throws org.apache.commons.math3.exception.NullArgumentException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r29 = this;
            r6 = r29
            r7 = r31
            org.apache.commons.math3.util.MathUtils.checkNotNull(r31)
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r1 = r30
            r0.setMaximalCount(r1)
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r0.resetCount()
            org.apache.commons.math3.dfp.Dfp r8 = r34.getZero()
            r9 = 1
            r10 = 3
            org.apache.commons.math3.dfp.Dfp r11 = r8.newInstance(r9, r10)
            int r12 = r6.maximalOrder
            int r13 = r12 + 1
            org.apache.commons.math3.dfp.Dfp[] r14 = new org.apache.commons.math3.dfp.Dfp[r13]
            int r0 = r12 + 1
            org.apache.commons.math3.dfp.Dfp[] r15 = new org.apache.commons.math3.dfp.Dfp[r0]
            r5 = 0
            r14[r5] = r32
            r14[r9] = r34
            r4 = 2
            r14[r4] = r33
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r0.incrementCount()
            r0 = r14[r9]
            org.apache.commons.math3.dfp.Dfp r0 = r7.value(r0)
            r15[r9] = r0
            boolean r0 = r0.isZero()
            if (r0 == 0) goto L_0x0045
            r0 = r14[r9]
            return r0
        L_0x0045:
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r0.incrementCount()
            r0 = r14[r5]
            org.apache.commons.math3.dfp.Dfp r0 = r7.value(r0)
            r15[r5] = r0
            boolean r0 = r0.isZero()
            if (r0 == 0) goto L_0x005b
            r0 = r14[r5]
            return r0
        L_0x005b:
            r0 = r15[r5]
            r1 = r15[r9]
            org.apache.commons.math3.dfp.Dfp r0 = r0.multiply((org.apache.commons.math3.dfp.Dfp) r1)
            boolean r0 = r0.negativeOrNull()
            if (r0 == 0) goto L_0x006c
            r1 = r4
            r0 = r9
            goto L_0x0092
        L_0x006c:
            org.apache.commons.math3.util.Incrementor r0 = r6.evaluations
            r0.incrementCount()
            r0 = r14[r4]
            org.apache.commons.math3.dfp.Dfp r0 = r7.value(r0)
            r15[r4] = r0
            boolean r0 = r0.isZero()
            if (r0 == 0) goto L_0x0082
            r0 = r14[r4]
            return r0
        L_0x0082:
            r0 = r15[r9]
            r1 = r15[r4]
            org.apache.commons.math3.dfp.Dfp r0 = r0.multiply((org.apache.commons.math3.dfp.Dfp) r1)
            boolean r0 = r0.negativeOrNull()
            if (r0 == 0) goto L_0x0287
            r0 = r4
            r1 = r10
        L_0x0092:
            org.apache.commons.math3.dfp.Dfp[] r3 = new org.apache.commons.math3.dfp.Dfp[r13]
            int r2 = r0 + -1
            r16 = r14[r2]
            r2 = r15[r2]
            org.apache.commons.math3.dfp.Dfp r10 = r16.abs()
            org.apache.commons.math3.dfp.Dfp r17 = r2.abs()
            r18 = r14[r0]
            r19 = r15[r0]
            org.apache.commons.math3.dfp.Dfp r9 = r18.abs()
            org.apache.commons.math3.dfp.Dfp r20 = r19.abs()
            r7 = r5
            r4 = r16
            r16 = r11
            r11 = r17
            r17 = r8
            r8 = r18
            r18 = r12
            r12 = r19
            r19 = r13
            r13 = r20
            r27 = r2
            r2 = r0
            r0 = r27
        L_0x00c6:
            boolean r21 = r10.lessThan(r9)
            r33 = r1
            if (r21 == 0) goto L_0x00d0
            r1 = r9
            goto L_0x00d1
        L_0x00d0:
            r1 = r10
        L_0x00d1:
            boolean r21 = r11.lessThan(r13)
            r34 = r2
            if (r21 == 0) goto L_0x00dd
            r21 = r9
            r2 = r13
            goto L_0x00e0
        L_0x00dd:
            r21 = r9
            r2 = r11
        L_0x00e0:
            org.apache.commons.math3.dfp.Dfp r9 = r6.absoluteAccuracy
            r22 = r10
            org.apache.commons.math3.dfp.Dfp r10 = r6.relativeAccuracy
            org.apache.commons.math3.dfp.Dfp r1 = r10.multiply((org.apache.commons.math3.dfp.Dfp) r1)
            org.apache.commons.math3.dfp.Dfp r1 = r9.add((org.apache.commons.math3.dfp.Dfp) r1)
            org.apache.commons.math3.dfp.Dfp r9 = r8.subtract((org.apache.commons.math3.dfp.Dfp) r4)
            org.apache.commons.math3.dfp.Dfp r1 = r9.subtract((org.apache.commons.math3.dfp.Dfp) r1)
            boolean r1 = r1.negativeOrNull()
            if (r1 != 0) goto L_0x023f
            org.apache.commons.math3.dfp.Dfp r1 = r6.functionValueAccuracy
            boolean r1 = r2.lessThan(r1)
            if (r1 == 0) goto L_0x0106
            goto L_0x023f
        L_0x0106:
            r1 = 16
            r9 = 2
            if (r7 < r9) goto L_0x0115
            org.apache.commons.math3.dfp.Dfp r1 = r12.divide((int) r1)
            org.apache.commons.math3.dfp.Dfp r1 = r1.negate()
        L_0x0113:
            r10 = r1
            goto L_0x0122
        L_0x0115:
            if (r5 < r9) goto L_0x0120
            org.apache.commons.math3.dfp.Dfp r1 = r0.divide((int) r1)
            org.apache.commons.math3.dfp.Dfp r1 = r1.negate()
            goto L_0x0113
        L_0x0120:
            r10 = r17
        L_0x0122:
            r23 = r33
            r2 = 0
        L_0x0125:
            int r1 = r23 - r2
            java.lang.System.arraycopy(r14, r2, r3, r2, r1)
            r1 = r0
            r0 = r29
            r9 = r33
            r33 = r7
            r7 = r1
            r1 = r10
            r24 = r2
            r27 = r10
            r10 = r34
            r34 = r27
            r2 = r3
            r25 = r3
            r3 = r15
            r32 = r11
            r26 = r12
            r11 = 2
            r12 = r4
            r4 = r24
            r20 = r5
            r5 = r23
            org.apache.commons.math3.dfp.Dfp r0 = r0.guessX(r1, r2, r3, r4, r5)
            boolean r1 = r0.greaterThan(r12)
            if (r1 == 0) goto L_0x015f
            boolean r1 = r0.lessThan(r8)
            if (r1 != 0) goto L_0x015c
            goto L_0x015f
        L_0x015c:
            r2 = r24
            goto L_0x016e
        L_0x015f:
            int r2 = r10 - r24
            int r0 = r23 - r10
            if (r2 < r0) goto L_0x0168
            int r2 = r24 + 1
            goto L_0x016c
        L_0x0168:
            int r23 = r23 + -1
            r2 = r24
        L_0x016c:
            r0 = r16
        L_0x016e:
            boolean r1 = r0.isNaN()
            if (r1 == 0) goto L_0x0190
            int r1 = r23 - r2
            r3 = 1
            if (r1 > r3) goto L_0x017a
            goto L_0x0190
        L_0x017a:
            r0 = r7
            r4 = r12
            r5 = r20
            r3 = r25
            r12 = r26
            r7 = r33
            r33 = r9
            r9 = r11
            r11 = r32
            r27 = r10
            r10 = r34
            r34 = r27
            goto L_0x0125
        L_0x0190:
            boolean r1 = r0.isNaN()
            if (r1 == 0) goto L_0x01a7
            org.apache.commons.math3.dfp.Dfp r0 = r8.subtract((org.apache.commons.math3.dfp.Dfp) r12)
            org.apache.commons.math3.dfp.Dfp r0 = r0.divide((int) r11)
            org.apache.commons.math3.dfp.Dfp r0 = r12.add((org.apache.commons.math3.dfp.Dfp) r0)
            int r2 = r10 + -1
            r1 = r2
            r2 = r10
            goto L_0x01aa
        L_0x01a7:
            r1 = r2
            r2 = r23
        L_0x01aa:
            org.apache.commons.math3.util.Incrementor r3 = r6.evaluations
            r3.incrementCount()
            r3 = r31
            r5 = r33
            org.apache.commons.math3.dfp.Dfp r4 = r3.value(r0)
            boolean r23 = r4.isZero()
            if (r23 == 0) goto L_0x01be
            return r0
        L_0x01be:
            if (r9 <= r11) goto L_0x01d4
            int r2 = r2 - r1
            if (r2 == r9) goto L_0x01d4
            r11 = 0
            java.lang.System.arraycopy(r14, r1, r14, r11, r2)
            java.lang.System.arraycopy(r15, r1, r15, r11, r2)
            int r1 = r10 - r1
            r24 = r19
        L_0x01ce:
            r27 = r2
            r2 = r1
            r1 = r27
            goto L_0x01f4
        L_0x01d4:
            r11 = 0
            r1 = r19
            if (r9 != r1) goto L_0x01f0
            int r2 = r9 + -1
            r9 = 2
            int r19 = r18 + 2
            r24 = r1
            int r1 = r19 / 2
            if (r10 < r1) goto L_0x01ee
            r1 = 1
            java.lang.System.arraycopy(r14, r1, r14, r11, r2)
            java.lang.System.arraycopy(r15, r1, r15, r11, r2)
            int r1 = r10 + -1
            goto L_0x01ce
        L_0x01ee:
            r1 = r2
            goto L_0x01f3
        L_0x01f0:
            r24 = r1
            r1 = r9
        L_0x01f3:
            r2 = r10
        L_0x01f4:
            int r9 = r2 + 1
            int r10 = r1 - r2
            java.lang.System.arraycopy(r14, r2, r14, r9, r10)
            r14[r2] = r0
            java.lang.System.arraycopy(r15, r2, r15, r9, r10)
            r15[r2] = r4
            r10 = 1
            int r1 = r1 + r10
            org.apache.commons.math3.dfp.Dfp r10 = r4.multiply((org.apache.commons.math3.dfp.Dfp) r7)
            boolean r10 = r10.negativeOrNull()
            if (r10 == 0) goto L_0x0221
            org.apache.commons.math3.dfp.Dfp r8 = r4.abs()
            int r5 = r5 + 1
            r9 = r32
            r13 = r8
            r8 = r0
            r0 = r7
            r7 = r5
            r5 = r11
            r27 = r12
            r12 = r4
            r4 = r27
            goto L_0x0234
        L_0x0221:
            org.apache.commons.math3.dfp.Dfp r2 = r4.abs()
            int r5 = r20 + 1
            r7 = r11
            r12 = r26
            r27 = r4
            r4 = r0
            r0 = r27
            r28 = r9
            r9 = r2
            r2 = r28
        L_0x0234:
            r11 = r9
            r9 = r21
            r10 = r22
            r19 = r24
            r3 = r25
            goto L_0x00c6
        L_0x023f:
            r7 = r0
            r12 = r4
            r32 = r11
            int[] r0 = org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.AnonymousClass1.$SwitchMap$org$apache$commons$math3$analysis$solvers$AllowedSolution
            int r1 = r35.ordinal()
            r0 = r0[r1]
            r1 = 1
            if (r0 == r1) goto L_0x027b
            r1 = 2
            if (r0 == r1) goto L_0x027a
            r1 = 3
            if (r0 == r1) goto L_0x0279
            r1 = 4
            if (r0 == r1) goto L_0x026d
            r1 = 5
            if (r0 != r1) goto L_0x0266
            r0 = r17
            boolean r0 = r7.lessThan(r0)
            if (r0 == 0) goto L_0x0264
            r4 = r8
            goto L_0x0265
        L_0x0264:
            r4 = r12
        L_0x0265:
            return r4
        L_0x0266:
            org.apache.commons.math3.exception.MathInternalError r0 = new org.apache.commons.math3.exception.MathInternalError
            r1 = 0
            r0.<init>(r1)
            throw r0
        L_0x026d:
            r0 = r17
            boolean r0 = r7.lessThan(r0)
            if (r0 == 0) goto L_0x0277
            r4 = r12
            goto L_0x0278
        L_0x0277:
            r4 = r8
        L_0x0278:
            return r4
        L_0x0279:
            return r8
        L_0x027a:
            return r12
        L_0x027b:
            r9 = r32
            boolean r0 = r9.lessThan(r13)
            if (r0 == 0) goto L_0x0285
            r4 = r12
            goto L_0x0286
        L_0x0285:
            r4 = r8
        L_0x0286:
            return r4
        L_0x0287:
            r11 = r5
            org.apache.commons.math3.exception.NoBracketingException r0 = new org.apache.commons.math3.exception.NoBracketingException
            r1 = r14[r11]
            double r8 = r1.toDouble()
            r1 = 2
            r2 = r14[r1]
            double r2 = r2.toDouble()
            r4 = r15[r11]
            double r12 = r4.toDouble()
            r1 = r15[r1]
            double r14 = r1.toDouble()
            r7 = r0
            r10 = r2
            r7.<init>(r8, r10, r12, r14)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.solve(int, org.apache.commons.math3.dfp.UnivariateDfpFunction, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.dfp.Dfp, org.apache.commons.math3.analysis.solvers.AllowedSolution):org.apache.commons.math3.dfp.Dfp");
    }

    /* renamed from: org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.dfp.BracketingNthOrderBrentSolverDFP.AnonymousClass1.<clinit>():void");
        }
    }

    private Dfp guessX(Dfp dfp, Dfp[] dfpArr, Dfp[] dfpArr2, int i, int i2) {
        int i3;
        int i4 = i;
        while (true) {
            i3 = i2 - 1;
            if (i4 >= i3) {
                break;
            }
            int i5 = i4 + 1;
            int i6 = i5 - i;
            while (i3 > i4) {
                dfpArr[i3] = dfpArr[i3].subtract(dfpArr[i3 - 1]).divide(dfpArr2[i3].subtract(dfpArr2[i3 - i6]));
                i3--;
            }
            i4 = i5;
        }
        Dfp zero = dfp.getZero();
        while (i3 >= i) {
            zero = dfpArr[i3].add(zero.multiply(dfp.subtract(dfpArr2[i3])));
            i3--;
        }
        return zero;
    }
}
