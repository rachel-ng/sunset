package org.apache.commons.math3.ode.nonstiff;

public abstract class EmbeddedRungeKuttaIntegrator extends AdaptiveStepsizeIntegrator {

    /* renamed from: a  reason: collision with root package name */
    private final double[][] f79a;

    /* renamed from: b  reason: collision with root package name */
    private final double[] f80b;

    /* renamed from: c  reason: collision with root package name */
    private final double[] f81c;
    private final double exp = (-1.0d / ((double) getOrder()));
    private final boolean fsal;
    private double maxGrowth;
    private double minReduction;
    private final RungeKuttaStepInterpolator prototype;
    private double safety;

    /* access modifiers changed from: protected */
    public abstract double estimateError(double[][] dArr, double[] dArr2, double[] dArr3, double d);

    public abstract int getOrder();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double d3, double d4) {
        super(str, d, d2, d3, d4);
        this.fsal = z;
        this.f81c = dArr;
        this.f79a = dArr2;
        this.f80b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected EmbeddedRungeKuttaIntegrator(String str, boolean z, double[] dArr, double[][] dArr2, double[] dArr3, RungeKuttaStepInterpolator rungeKuttaStepInterpolator, double d, double d2, double[] dArr4, double[] dArr5) {
        super(str, d, d2, dArr4, dArr5);
        this.fsal = z;
        this.f81c = dArr;
        this.f79a = dArr2;
        this.f80b = dArr3;
        this.prototype = rungeKuttaStepInterpolator;
        setSafety(0.9d);
        setMinReduction(0.2d);
        setMaxGrowth(10.0d);
    }

    public double getSafety() {
        return this.safety;
    }

    public void setSafety(double d) {
        this.safety = d;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r33, double r34) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r32 = this;
            r10 = r32
            r11 = r33
            r32.sanityChecks(r33, r34)
            r32.setEquations(r33)
            double r0 = r33.getTime()
            int r0 = (r34 > r0 ? 1 : (r34 == r0 ? 0 : -1))
            r12 = 1
            r13 = 0
            if (r0 <= 0) goto L_0x0016
            r14 = r12
            goto L_0x0017
        L_0x0016:
            r14 = r13
        L_0x0017:
            double[] r15 = r33.getCompleteState()
            java.lang.Object r0 = r15.clone()
            r9 = r0
            double[] r9 = (double[]) r9
            double[] r0 = r10.f81c
            int r8 = r0.length
            int r7 = r8 + 1
            int r0 = r9.length
            r1 = 2
            int[] r1 = new int[r1]
            r1[r12] = r0
            r1[r13] = r7
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            r16 = r0
            double[][] r16 = (double[][]) r16
            java.lang.Object r0 = r15.clone()
            r6 = r0
            double[] r6 = (double[]) r6
            int r0 = r9.length
            double[] r5 = new double[r0]
            org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator r0 = r10.prototype
            org.apache.commons.math3.ode.sampling.StepInterpolator r0 = r0.copy()
            r4 = r0
            org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator r4 = (org.apache.commons.math3.ode.nonstiff.RungeKuttaStepInterpolator) r4
            org.apache.commons.math3.ode.EquationsMapper r17 = r33.getPrimaryMapper()
            org.apache.commons.math3.ode.EquationsMapper[] r18 = r33.getSecondaryMappers()
            r0 = r4
            r1 = r32
            r2 = r6
            r3 = r16
            r12 = r4
            r4 = r14
            r20 = r5
            r5 = r17
            r17 = r6
            r6 = r18
            r0.reinitialize(r1, r2, r3, r4, r5, r6)
            double r0 = r33.getTime()
            r12.storeTime(r0)
            double r0 = r33.getTime()
            r10.stepStart = r0
            double r1 = r33.getTime()
            r0 = r32
            r3 = r15
            r4 = r34
            r0.initIntegration(r1, r3, r4)
            r10.isLastStep = r13
            r0 = 0
            r2 = 1
        L_0x0085:
            r12.shift()
            r3 = 4621819117588971520(0x4024000000000000, double:10.0)
            r21 = r0
            r6 = r2
            r4 = r3
        L_0x008e:
            r23 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r4 > r23 ? 1 : (r4 == r23 ? 0 : -1))
            if (r0 < 0) goto L_0x01ff
            if (r6 != 0) goto L_0x009a
            boolean r0 = r10.fsal
            if (r0 != 0) goto L_0x00a1
        L_0x009a:
            double r0 = r10.stepStart
            r2 = r16[r13]
            r10.computeDerivatives(r0, r9, r2)
        L_0x00a1:
            if (r6 == 0) goto L_0x0103
            int r0 = r10.mainSetDimension
            double[] r3 = new double[r0]
            double[] r1 = r10.vecAbsoluteTolerance
            if (r1 != 0) goto L_0x00c8
            r1 = r13
        L_0x00ac:
            if (r1 >= r0) goto L_0x00c5
            double r4 = r10.scalAbsoluteTolerance
            r25 = r14
            double r13 = r10.scalRelativeTolerance
            r21 = r9[r1]
            double r21 = org.apache.commons.math3.util.FastMath.abs((double) r21)
            double r13 = r13 * r21
            double r4 = r4 + r13
            r3[r1] = r4
            int r1 = r1 + 1
            r14 = r25
            r13 = 0
            goto L_0x00ac
        L_0x00c5:
            r25 = r14
            goto L_0x00e3
        L_0x00c8:
            r25 = r14
            r1 = 0
        L_0x00cb:
            if (r1 >= r0) goto L_0x00e3
            double[] r2 = r10.vecAbsoluteTolerance
            r4 = r2[r1]
            double[] r2 = r10.vecRelativeTolerance
            r13 = r2[r1]
            r21 = r9[r1]
            double r21 = org.apache.commons.math3.util.FastMath.abs((double) r21)
            double r13 = r13 * r21
            double r4 = r4 + r13
            r3[r1] = r4
            int r1 = r1 + 1
            goto L_0x00cb
        L_0x00e3:
            int r2 = r32.getOrder()
            double r4 = r10.stepStart
            r0 = 0
            r13 = r16[r0]
            r14 = 1
            r19 = r16[r14]
            r0 = r32
            r1 = r25
            r6 = r9
            r14 = r7
            r7 = r13
            r13 = r8
            r8 = r17
            r11 = r9
            r9 = r19
            double r0 = r0.initializeStep(r1, r2, r3, r4, r6, r7, r8, r9)
            r7 = r0
            r6 = 0
            goto L_0x010a
        L_0x0103:
            r13 = r8
            r11 = r9
            r25 = r14
            r14 = r7
            r7 = r21
        L_0x010a:
            r10.stepSize = r7
            if (r25 == 0) goto L_0x011e
            double r0 = r10.stepStart
            double r2 = r10.stepSize
            double r0 = r0 + r2
            int r0 = (r0 > r34 ? 1 : (r0 == r34 ? 0 : -1))
            if (r0 < 0) goto L_0x012d
            double r0 = r10.stepStart
            double r0 = r34 - r0
            r10.stepSize = r0
            goto L_0x012d
        L_0x011e:
            double r0 = r10.stepStart
            double r2 = r10.stepSize
            double r0 = r0 + r2
            int r0 = (r0 > r34 ? 1 : (r0 == r34 ? 0 : -1))
            if (r0 > 0) goto L_0x012d
            double r0 = r10.stepStart
            double r0 = r34 - r0
            r10.stepSize = r0
        L_0x012d:
            r0 = 1
        L_0x012e:
            if (r0 >= r14) goto L_0x0185
            r1 = 0
        L_0x0131:
            int r2 = r15.length
            if (r1 >= r2) goto L_0x0165
            double[][] r2 = r10.f79a
            int r3 = r0 + -1
            r2 = r2[r3]
            r4 = 0
            r21 = r2[r4]
            r2 = r16[r4]
            r4 = r2[r1]
            double r21 = r21 * r4
            r2 = 1
        L_0x0144:
            if (r2 >= r0) goto L_0x0157
            double[][] r4 = r10.f79a
            r4 = r4[r3]
            r26 = r4[r2]
            r4 = r16[r2]
            r28 = r4[r1]
            double r26 = r26 * r28
            double r21 = r21 + r26
            int r2 = r2 + 1
            goto L_0x0144
        L_0x0157:
            r2 = r11[r1]
            double r4 = r10.stepSize
            double r4 = r4 * r21
            double r2 = r2 + r4
            r9 = r17
            r9[r1] = r2
            int r1 = r1 + 1
            goto L_0x0131
        L_0x0165:
            r9 = r17
            double r1 = r10.stepStart
            double[] r3 = r10.f81c
            int r4 = r0 + -1
            r4 = r3[r4]
            r17 = r6
            r21 = r7
            double r6 = r10.stepSize
            double r4 = r4 * r6
            double r1 = r1 + r4
            r3 = r16[r0]
            r10.computeDerivatives(r1, r9, r3)
            int r0 = r0 + 1
            r6 = r17
            r7 = r21
            r17 = r9
            goto L_0x012e
        L_0x0185:
            r21 = r7
            r9 = r17
            r17 = r6
            r0 = 0
        L_0x018c:
            int r1 = r15.length
            if (r0 >= r1) goto L_0x01b4
            double[] r1 = r10.f80b
            r2 = 0
            r3 = r1[r2]
            r1 = r16[r2]
            r5 = r1[r0]
            double r3 = r3 * r5
            r1 = 1
        L_0x019a:
            if (r1 >= r14) goto L_0x01a9
            double[] r2 = r10.f80b
            r5 = r2[r1]
            r2 = r16[r1]
            r7 = r2[r0]
            double r5 = r5 * r7
            double r3 = r3 + r5
            int r1 = r1 + 1
            goto L_0x019a
        L_0x01a9:
            r1 = r11[r0]
            double r5 = r10.stepSize
            double r5 = r5 * r3
            double r1 = r1 + r5
            r9[r0] = r1
            int r0 = r0 + 1
            goto L_0x018c
        L_0x01b4:
            double r4 = r10.stepSize
            r0 = r32
            r1 = r16
            r2 = r11
            r3 = r9
            double r4 = r0.estimateError(r1, r2, r3, r4)
            int r0 = (r4 > r23 ? 1 : (r4 == r23 ? 0 : -1))
            if (r0 < 0) goto L_0x01f1
            double r0 = r10.maxGrowth
            double r2 = r10.minReduction
            double r6 = r10.safety
            r19 = r13
            r8 = r14
            double r13 = r10.exp
            double r13 = org.apache.commons.math3.util.FastMath.pow((double) r4, (double) r13)
            double r6 = r6 * r13
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r6)
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r2)
            double r2 = r10.stepSize
            double r2 = r2 * r0
            r13 = r25
            r7 = 0
            double r21 = r10.filterStep(r2, r13, r7)
            r14 = r13
            r6 = r17
            r13 = r7
            r7 = r8
            r17 = r9
            r9 = r11
            r8 = r19
            goto L_0x01fb
        L_0x01f1:
            r8 = r13
            r7 = r14
            r6 = r17
            r14 = r25
            r13 = 0
            r17 = r9
            r9 = r11
        L_0x01fb:
            r11 = r33
            goto L_0x008e
        L_0x01ff:
            r19 = r8
            r11 = r9
            r9 = r17
            r8 = r7
            r7 = r13
            r13 = r14
            double r0 = r10.stepStart
            double r2 = r10.stepSize
            double r0 = r0 + r2
            r12.storeTime(r0)
            int r0 = r15.length
            java.lang.System.arraycopy(r9, r7, r11, r7, r0)
            r0 = r16[r19]
            int r1 = r15.length
            r14 = r20
            java.lang.System.arraycopy(r0, r7, r14, r7, r1)
            r0 = r32
            r1 = r12
            r2 = r11
            r3 = r14
            r30 = r4
            r4 = r34
            double r0 = r0.acceptStep(r1, r2, r3, r4)
            r10.stepStart = r0
            int r0 = r11.length
            java.lang.System.arraycopy(r11, r7, r9, r7, r0)
            boolean r0 = r10.isLastStep
            if (r0 != 0) goto L_0x0289
            double r0 = r10.stepStart
            r12.storeTime(r0)
            boolean r0 = r10.fsal
            if (r0 == 0) goto L_0x0241
            r0 = r16[r7]
            int r1 = r15.length
            java.lang.System.arraycopy(r14, r7, r0, r7, r1)
        L_0x0241:
            double r0 = r10.maxGrowth
            double r2 = r10.minReduction
            double r4 = r10.safety
            r17 = r8
            double r7 = r10.exp
            r23 = r11
            r20 = r12
            r11 = r30
            double r7 = org.apache.commons.math3.util.FastMath.pow((double) r11, (double) r7)
            double r4 = r4 * r7
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r4)
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r2)
            double r2 = r10.stepSize
            double r2 = r2 * r0
            double r0 = r10.stepStart
            double r0 = r0 + r2
            if (r13 == 0) goto L_0x026b
            int r0 = (r0 > r34 ? 1 : (r0 == r34 ? 0 : -1))
            if (r0 < 0) goto L_0x0271
            goto L_0x026f
        L_0x026b:
            int r0 = (r0 > r34 ? 1 : (r0 == r34 ? 0 : -1))
            if (r0 > 0) goto L_0x0271
        L_0x026f:
            r0 = 1
            goto L_0x0272
        L_0x0271:
            r0 = 0
        L_0x0272:
            double r0 = r10.filterStep(r2, r13, r0)
            double r2 = r10.stepStart
            double r2 = r2 + r0
            if (r13 == 0) goto L_0x0280
            int r2 = (r2 > r34 ? 1 : (r2 == r34 ? 0 : -1))
            if (r2 < 0) goto L_0x0291
            goto L_0x0284
        L_0x0280:
            int r2 = (r2 > r34 ? 1 : (r2 == r34 ? 0 : -1))
            if (r2 > 0) goto L_0x0291
        L_0x0284:
            double r0 = r10.stepStart
            double r0 = r34 - r0
            goto L_0x0291
        L_0x0289:
            r17 = r8
            r23 = r11
            r20 = r12
            r0 = r21
        L_0x0291:
            boolean r2 = r10.isLastStep
            if (r2 == 0) goto L_0x02a5
            double r0 = r10.stepStart
            r2 = r33
            r3 = r23
            r2.setTime(r0)
            r2.setCompleteState(r3)
            r32.resetInternalState()
            return
        L_0x02a5:
            r11 = r33
            r2 = r6
            r7 = r17
            r8 = r19
            r12 = r20
            r17 = r9
            r20 = r14
            r9 = r23
            r14 = r13
            r13 = 0
            goto L_0x0085
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.EmbeddedRungeKuttaIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }

    public double getMinReduction() {
        return this.minReduction;
    }

    public void setMinReduction(double d) {
        this.minReduction = d;
    }

    public double getMaxGrowth() {
        return this.maxGrowth;
    }

    public void setMaxGrowth(double d) {
        this.maxGrowth = d;
    }
}
