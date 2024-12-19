package org.apache.commons.math3.ode.nonstiff;

import org.apache.commons.math3.analysis.solvers.UnivariateSolver;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.ode.events.EventHandler;
import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.util.FastMath;

public class GraggBulirschStoerIntegrator extends AdaptiveStepsizeIntegrator {
    private static final String METHOD_NAME = "Gragg-Bulirsch-Stoer";
    private double[][] coeff;
    private int[] costPerStep;
    private double[] costPerTimeUnit;
    private int maxChecks;
    private int maxIter;
    private int maxOrder;
    private int mudif;
    private double[] optimalStep;
    private double orderControl1;
    private double orderControl2;
    private boolean performTest;
    private int[] sequence;
    private double stabilityReduction;
    private double stepControl1;
    private double stepControl2;
    private double stepControl3;
    private double stepControl4;
    private boolean useInterpolationError;

    public GraggBulirschStoerIntegrator(double d, double d2, double d3, double d4) {
        super(METHOD_NAME, d, d2, d3, d4);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public GraggBulirschStoerIntegrator(double d, double d2, double[] dArr, double[] dArr2) {
        super(METHOD_NAME, d, d2, dArr, dArr2);
        setStabilityCheck(true, -1, -1, -1.0d);
        setControlFactors(-1.0d, -1.0d, -1.0d, -1.0d);
        setOrderControl(-1, -1.0d, -1.0d);
        setInterpolationControl(true, -1);
    }

    public void setStabilityCheck(boolean z, int i, int i2, double d) {
        this.performTest = z;
        if (i <= 0) {
            i = 2;
        }
        this.maxIter = i;
        if (i2 <= 0) {
            i2 = 1;
        }
        this.maxChecks = i2;
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stabilityReduction = 0.5d;
        } else {
            this.stabilityReduction = d;
        }
    }

    public void setControlFactors(double d, double d2, double d3, double d4) {
        if (d < 1.0E-4d || d > 0.9999d) {
            this.stepControl1 = 0.65d;
        } else {
            this.stepControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.stepControl2 = 0.94d;
        } else {
            this.stepControl2 = d2;
        }
        if (d3 < 1.0E-4d || d3 > 0.9999d) {
            this.stepControl3 = 0.02d;
        } else {
            this.stepControl3 = d3;
        }
        if (d4 < 1.0001d || d4 > 999.9d) {
            this.stepControl4 = 4.0d;
        } else {
            this.stepControl4 = d4;
        }
    }

    public void setOrderControl(int i, double d, double d2) {
        if (i <= 6 || i % 2 != 0) {
            this.maxOrder = 18;
        }
        if (d < 1.0E-4d || d > 0.9999d) {
            this.orderControl1 = 0.8d;
        } else {
            this.orderControl1 = d;
        }
        if (d2 < 1.0E-4d || d2 > 0.9999d) {
            this.orderControl2 = 0.9d;
        } else {
            this.orderControl2 = d2;
        }
        initializeArrays();
    }

    public void addStepHandler(StepHandler stepHandler) {
        super.addStepHandler(stepHandler);
        initializeArrays();
    }

    public void addEventHandler(EventHandler eventHandler, double d, double d2, int i, UnivariateSolver univariateSolver) {
        super.addEventHandler(eventHandler, d, d2, i, univariateSolver);
        initializeArrays();
    }

    private void initializeArrays() {
        int i = this.maxOrder / 2;
        int[] iArr = this.sequence;
        if (iArr == null || iArr.length != i) {
            this.sequence = new int[i];
            this.costPerStep = new int[i];
            this.coeff = new double[i][];
            this.costPerTimeUnit = new double[i];
            this.optimalStep = new double[i];
        }
        for (int i2 = 0; i2 < i; i2++) {
            this.sequence[i2] = (i2 * 4) + 2;
        }
        this.costPerStep[0] = this.sequence[0] + 1;
        for (int i3 = 1; i3 < i; i3++) {
            int[] iArr2 = this.costPerStep;
            iArr2[i3] = iArr2[i3 - 1] + this.sequence[i3];
        }
        int i4 = 0;
        while (i4 < i) {
            this.coeff[i4] = i4 > 0 ? new double[i4] : null;
            for (int i5 = 0; i5 < i4; i5++) {
                int[] iArr3 = this.sequence;
                double d = ((double) iArr3[i4]) / ((double) iArr3[(i4 - i5) - 1]);
                this.coeff[i4][i5] = 1.0d / ((d * d) - 1.0d);
            }
            i4++;
        }
    }

    public void setInterpolationControl(boolean z, int i) {
        this.useInterpolationError = z;
        if (i <= 0 || i >= 7) {
            this.mudif = 4;
        } else {
            this.mudif = i;
        }
    }

    private void rescale(double[] dArr, double[] dArr2, double[] dArr3) {
        int i = 0;
        if (this.vecAbsoluteTolerance == null) {
            while (i < dArr3.length) {
                dArr3[i] = this.scalAbsoluteTolerance + (this.scalRelativeTolerance * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
                i++;
            }
            return;
        }
        while (i < dArr3.length) {
            dArr3[i] = this.vecAbsoluteTolerance[i] + (this.vecRelativeTolerance[i] * FastMath.max(FastMath.abs(dArr[i]), FastMath.abs(dArr2[i])));
            i++;
        }
    }

    private boolean tryStep(double d, double[] dArr, double d2, int i, double[] dArr2, double[][] dArr3, double[] dArr4, double[] dArr5, double[] dArr6) throws MaxCountExceededException, DimensionMismatchException {
        double d3;
        GraggBulirschStoerIntegrator graggBulirschStoerIntegrator = this;
        double[] dArr7 = dArr;
        int i2 = i;
        double[] dArr8 = dArr2;
        double[] dArr9 = dArr5;
        int i3 = graggBulirschStoerIntegrator.sequence[i2];
        double d4 = d2 / ((double) i3);
        double d5 = 2.0d * d4;
        double d6 = d + d4;
        int i4 = 0;
        for (int i5 = 0; i5 < dArr7.length; i5++) {
            dArr6[i5] = dArr7[i5];
            dArr9[i5] = dArr7[i5] + (dArr3[0][i5] * d4);
        }
        graggBulirschStoerIntegrator.computeDerivatives(d6, dArr9, dArr3[1]);
        int i6 = 1;
        while (i6 < i3) {
            if (i6 * 2 == i3) {
                System.arraycopy(dArr9, i4, dArr4, i4, dArr7.length);
            } else {
                double[] dArr10 = dArr4;
            }
            d6 += d4;
            for (int i7 = i4; i7 < dArr7.length; i7++) {
                double d7 = dArr9[i7];
                dArr9[i7] = dArr6[i7] + (dArr3[i6][i7] * d5);
                dArr6[i7] = d7;
            }
            int i8 = i6 + 1;
            graggBulirschStoerIntegrator.computeDerivatives(d6, dArr9, dArr3[i8]);
            if (!graggBulirschStoerIntegrator.performTest || i6 > graggBulirschStoerIntegrator.maxChecks || i2 >= graggBulirschStoerIntegrator.maxIter) {
                d3 = d5;
            } else {
                d3 = d5;
                double d8 = 0.0d;
                for (int i9 = 0; i9 < dArr8.length; i9++) {
                    double d9 = dArr3[0][i9] / dArr8[i9];
                    d8 += d9 * d9;
                }
                double d10 = 0.0d;
                for (int i10 = 0; i10 < dArr8.length; i10++) {
                    double d11 = (dArr3[i8][i10] - dArr3[0][i10]) / dArr8[i10];
                    d10 += d11 * d11;
                }
                if (d10 > FastMath.max(1.0E-15d, d8) * 4.0d) {
                    return false;
                }
            }
            i2 = i;
            dArr8 = dArr2;
            i6 = i8;
            d5 = d3;
            i4 = 0;
            graggBulirschStoerIntegrator = this;
        }
        int i11 = i4;
        while (i4 < dArr7.length) {
            dArr9[i4] = (dArr6[i4] + dArr9[i4] + (dArr3[i3][i4] * d4)) * 0.5d;
            i4++;
        }
        return true;
    }

    private void extrapolate(int i, int i2, double[][] dArr, double[] dArr2) {
        int i3 = 1;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            for (int i4 = 0; i4 < dArr2.length; i4++) {
                int i5 = i2 - i3;
                double[] dArr3 = dArr[i5 - 1];
                double d = dArr[i5][i4];
                dArr3[i4] = d + (this.coeff[i2 + i][i3 - 1] * (d - dArr3[i4]));
            }
            i3++;
        }
        for (int i6 = 0; i6 < dArr2.length; i6++) {
            double d2 = dArr[0][i6];
            dArr2[i6] = d2 + (this.coeff[i2 + i][i2 - 1] * (d2 - dArr2[i6]));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v18, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r18v0, resolved type: double[][]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x0538  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x05f4  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x060c  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0660  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x067a  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x067d  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x0682  */
    /* JADX WARNING: Removed duplicated region for block: B:230:0x0696 A[LOOP:3: B:23:0x0137->B:230:0x0696, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:234:0x0688 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void integrate(org.apache.commons.math3.ode.ExpandableStatefulODE r53, double r54) throws org.apache.commons.math3.exception.NumberIsTooSmallException, org.apache.commons.math3.exception.DimensionMismatchException, org.apache.commons.math3.exception.MaxCountExceededException, org.apache.commons.math3.exception.NoBracketingException {
        /*
            r52 = this;
            r12 = r52
            r13 = r53
            r52.sanityChecks(r53, r54)
            r52.setEquations(r53)
            double r0 = r53.getTime()
            int r0 = (r54 > r0 ? 1 : (r54 == r0 ? 0 : -1))
            r15 = 1
            if (r0 <= 0) goto L_0x0015
            r11 = r15
            goto L_0x0016
        L_0x0015:
            r11 = 0
        L_0x0016:
            double[] r10 = r53.getCompleteState()
            java.lang.Object r0 = r10.clone()
            double[] r0 = (double[]) r0
            int r1 = r0.length
            double[] r9 = new double[r1]
            int r1 = r0.length
            double[] r8 = new double[r1]
            int r1 = r0.length
            double[] r7 = new double[r1]
            int r1 = r0.length
            double[] r6 = new double[r1]
            int[] r1 = r12.sequence
            int r2 = r1.length
            int r2 = r2 - r15
            double[][] r5 = new double[r2][]
            int r1 = r1.length
            int r1 = r1 - r15
            double[][] r4 = new double[r1][]
            r1 = 0
        L_0x0037:
            int[] r2 = r12.sequence
            int r3 = r2.length
            int r3 = r3 - r15
            if (r1 >= r3) goto L_0x004a
            int r2 = r0.length
            double[] r2 = new double[r2]
            r5[r1] = r2
            int r2 = r0.length
            double[] r2 = new double[r2]
            r4[r1] = r2
            int r1 = r1 + 1
            goto L_0x0037
        L_0x004a:
            int r1 = r2.length
            double[][][] r3 = new double[r1][][]
            r1 = 0
        L_0x004e:
            int[] r2 = r12.sequence
            int r14 = r2.length
            if (r1 >= r14) goto L_0x0073
            r2 = r2[r1]
            int r2 = r2 + r15
            double[][] r2 = new double[r2][]
            r3[r1] = r2
            r14 = 0
            r2[r14] = r9
            r2 = 0
        L_0x005e:
            int[] r14 = r12.sequence
            r14 = r14[r1]
            if (r2 >= r14) goto L_0x006f
            r14 = r3[r1]
            int r2 = r2 + 1
            int r15 = r10.length
            double[] r15 = new double[r15]
            r14[r2] = r15
            r15 = 1
            goto L_0x005e
        L_0x006f:
            int r1 = r1 + 1
            r15 = 1
            goto L_0x004e
        L_0x0073:
            if (r0 == r10) goto L_0x007a
            int r1 = r10.length
            r2 = 0
            java.lang.System.arraycopy(r10, r2, r0, r2, r1)
        L_0x007a:
            int r1 = r10.length
            double[] r14 = new double[r1]
            int[] r1 = r12.sequence
            int r1 = r1.length
            r15 = 2
            int r1 = r1 * r15
            r2 = 1
            int r1 = r1 + r2
            r16 = r3
            int r3 = r10.length
            r17 = r4
            int[] r4 = new int[r15]
            r4[r2] = r3
            r2 = 0
            r4[r2] = r1
            java.lang.Class r1 = java.lang.Double.TYPE
            java.lang.Object r1 = java.lang.reflect.Array.newInstance(r1, r4)
            r18 = r1
            double[][] r18 = (double[][]) r18
            int r1 = r12.mainSetDimension
            double[] r4 = new double[r1]
            r12.rescale(r0, r0, r4)
            double[] r1 = r12.vecRelativeTolerance
            if (r1 != 0) goto L_0x00a8
            double r1 = r12.scalRelativeTolerance
            goto L_0x00af
        L_0x00a8:
            double[] r1 = r12.vecRelativeTolerance
            r2 = 0
            r19 = r1[r2]
            r1 = r19
        L_0x00af:
            r19 = r4
            r3 = 4457293557087583675(0x3ddb7cdfd9d7bdbb, double:1.0E-10)
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r1)
            double r1 = org.apache.commons.math3.util.FastMath.log10(r1)
            int[] r3 = r12.sequence
            int r3 = r3.length
            int r3 = r3 - r15
            r20 = 4603579539098121011(0x3fe3333333333333, double:0.6)
            double r1 = r1 * r20
            r20 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r1 = r20 - r1
            double r1 = org.apache.commons.math3.util.FastMath.floor(r1)
            int r1 = (int) r1
            int r1 = org.apache.commons.math3.util.FastMath.min((int) r3, (int) r1)
            r2 = 1
            int r22 = org.apache.commons.math3.util.FastMath.max((int) r2, (int) r1)
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r4 = new org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator
            org.apache.commons.math3.ode.EquationsMapper r23 = r53.getPrimaryMapper()
            org.apache.commons.math3.ode.EquationsMapper[] r24 = r53.getSecondaryMappers()
            r1 = r4
            r2 = r0
            r3 = r9
            r13 = r4
            r15 = r17
            r4 = r8
            r15 = r5
            r5 = r14
            r26 = r6
            r6 = r18
            r27 = r7
            r7 = r11
            r28 = r14
            r14 = r8
            r8 = r23
            r23 = r14
            r14 = r9
            r9 = r24
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            double r1 = r53.getTime()
            r13.storeTime(r1)
            double r1 = r53.getTime()
            r12.stepStart = r1
            double r1 = r53.getTime()
            r9 = r0
            r0 = r52
            r3 = r10
            r4 = r54
            r0.initIntegration(r1, r3, r4)
            double[] r0 = r12.costPerTimeUnit
            r29 = 0
            r1 = 0
            r0[r1] = r29
            r12.isLastStep = r1
            r0 = 9218868437227405311(0x7fefffffffffffff, double:1.7976931348623157E308)
            r31 = r0
            r34 = r22
            r1 = r29
            r0 = 1
            r22 = 0
            r24 = 1
            r33 = 0
        L_0x0137:
            if (r0 == 0) goto L_0x0164
            r13.shift()
            if (r22 != 0) goto L_0x0143
            double r3 = r12.stepStart
            r12.computeDerivatives(r3, r9, r14)
        L_0x0143:
            if (r24 == 0) goto L_0x015f
            int r0 = r34 * 2
            r1 = 1
            int r2 = r0 + 1
            double r4 = r12.stepStart
            r0 = r52
            r1 = r11
            r3 = r19
            r6 = r9
            r7 = r14
            r8 = r27
            r35 = r9
            r9 = r26
            double r0 = r0.initializeStep(r1, r2, r3, r4, r6, r7, r8, r9)
            r1 = r0
            goto L_0x0161
        L_0x015f:
            r35 = r9
        L_0x0161:
            r36 = 0
            goto L_0x0168
        L_0x0164:
            r35 = r9
            r36 = r0
        L_0x0168:
            r12.stepSize = r1
            if (r11 == 0) goto L_0x0175
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r54 ? 1 : (r3 == r54 ? 0 : -1))
            if (r0 > 0) goto L_0x0180
        L_0x0175:
            if (r11 != 0) goto L_0x0186
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            int r0 = (r3 > r54 ? 1 : (r3 == r54 ? 0 : -1))
            if (r0 >= 0) goto L_0x0186
        L_0x0180:
            double r3 = r12.stepStart
            double r3 = r54 - r3
            r12.stepSize = r3
        L_0x0186:
            double r3 = r12.stepStart
            double r5 = r12.stepSize
            double r3 = r3 + r5
            if (r11 == 0) goto L_0x0192
            int r0 = (r3 > r54 ? 1 : (r3 == r54 ? 0 : -1))
            if (r0 < 0) goto L_0x0198
            goto L_0x0196
        L_0x0192:
            int r0 = (r3 > r54 ? 1 : (r3 == r54 ? 0 : -1))
            if (r0 > 0) goto L_0x0198
        L_0x0196:
            r0 = 1
            goto L_0x0199
        L_0x0198:
            r0 = 0
        L_0x0199:
            r12.isLastStep = r0
            r9 = -1
            r8 = r9
            r38 = r31
            r7 = r34
            r34 = 1
            r37 = 0
            r31 = r1
        L_0x01a7:
            if (r34 == 0) goto L_0x03ce
            int r6 = r8 + 1
            double r1 = r12.stepStart
            double r4 = r12.stepSize
            r42 = r16[r6]
            if (r6 != 0) goto L_0x01b9
            r0 = 0
            r3 = r18[r0]
            r43 = r3
            goto L_0x01bd
        L_0x01b9:
            r0 = r15[r8]
            r43 = r0
        L_0x01bd:
            if (r6 != 0) goto L_0x01c2
            r44 = r23
            goto L_0x01c6
        L_0x01c2:
            r0 = r17[r8]
            r44 = r0
        L_0x01c6:
            r0 = r52
            r3 = r35
            r45 = r13
            r46 = r14
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r40 = r6
            r47 = r7
            r7 = r19
            r48 = r8
            r8 = r42
            r9 = r43
            r49 = r10
            r10 = r44
            r13 = r11
            r11 = r27
            boolean r0 = r0.tryStep(r1, r3, r4, r6, r7, r8, r9, r10, r11)
            if (r0 != 0) goto L_0x0208
            double r0 = r12.stepSize
            double r2 = r12.stabilityReduction
            double r0 = r0 * r2
            r2 = 0
            double r0 = r12.filterStep(r0, r13, r2)
            double r31 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r34 = r2
            r11 = r13
            r8 = r40
            r13 = r45
            r14 = r46
            r7 = r47
            r10 = r49
            r9 = -1
        L_0x0205:
            r37 = 1
            goto L_0x01a7
        L_0x0208:
            r8 = r40
            r2 = 0
            if (r8 <= 0) goto L_0x03c1
            r7 = r17
            r6 = r23
            r12.extrapolate(r2, r8, r7, r6)
            r10 = r19
            r9 = r35
            r12.rescale(r9, r6, r10)
            r0 = r2
            r3 = r29
        L_0x021e:
            int r1 = r12.mainSetDimension
            if (r0 >= r1) goto L_0x0238
            r43 = r6[r0]
            r1 = r7[r2]
            r50 = r1[r0]
            double r43 = r43 - r50
            double r1 = org.apache.commons.math3.util.FastMath.abs((double) r43)
            r43 = r10[r0]
            double r1 = r1 / r43
            double r1 = r1 * r1
            double r3 = r3 + r1
            int r0 = r0 + 1
            r2 = 0
            goto L_0x021e
        L_0x0238:
            int r0 = r12.mainSetDimension
            double r0 = (double) r0
            double r3 = r3 / r0
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r3)
            r2 = 4831355200913801216(0x430c6bf526340000, double:1.0E15)
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0394
            r2 = 1
            if (r8 <= r2) goto L_0x0252
            int r3 = (r0 > r38 ? 1 : (r0 == r38 ? 0 : -1))
            if (r3 <= 0) goto L_0x0252
            goto L_0x0394
        L_0x0252:
            r3 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r3 = r3 * r0
            r35 = r9
            r19 = r10
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r38 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r9)
            int r3 = r8 * 2
            int r3 = r3 + r2
            double r2 = (double) r3
            double r4 = r9 / r2
            double r2 = r12.stepControl2
            double r9 = r12.stepControl1
            double r9 = r0 / r9
            double r9 = org.apache.commons.math3.util.FastMath.pow((double) r9, (double) r4)
            double r2 = r2 / r9
            double r9 = r12.stepControl3
            double r4 = org.apache.commons.math3.util.FastMath.pow((double) r9, (double) r4)
            double r9 = r12.stepControl4
            double r9 = r4 / r9
            r40 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r4 = r40 / r4
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r2)
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r9, (double) r2)
            double[] r4 = r12.optimalStep
            double r9 = r12.stepSize
            double r9 = r9 * r2
            r2 = 1
            double r9 = r12.filterStep(r9, r13, r2)
            double r2 = org.apache.commons.math3.util.FastMath.abs((double) r9)
            r4[r8] = r2
            double[] r2 = r12.costPerTimeUnit
            int[] r3 = r12.costPerStep
            r3 = r3[r8]
            double r3 = (double) r3
            double[] r5 = r12.optimalStep
            r9 = r5[r8]
            double r3 = r3 / r9
            r2[r8] = r3
            r9 = r47
            int r10 = r8 - r9
            r11 = -1
            if (r10 == r11) goto L_0x0340
            if (r10 == 0) goto L_0x02f1
            r3 = 1
            if (r10 == r3) goto L_0x02c4
            if (r24 != 0) goto L_0x02b6
            boolean r2 = r12.isLastStep
            if (r2 == 0) goto L_0x02bd
        L_0x02b6:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 > 0) goto L_0x02bd
            goto L_0x02f7
        L_0x02bd:
            r23 = r6
            r40 = r7
            r11 = r12
            goto L_0x038a
        L_0x02c4:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 <= 0) goto L_0x02e4
            r0 = 1
            if (r9 <= r0) goto L_0x02de
            int r0 = r9 + -1
            r0 = r2[r0]
            double r3 = r12.orderControl1
            r31 = r2[r9]
            double r3 = r3 * r31
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x02de
            int r0 = r9 + -1
            goto L_0x02df
        L_0x02de:
            r0 = r9
        L_0x02df:
            r31 = r5[r0]
            r37 = 1
            goto L_0x02e5
        L_0x02e4:
            r0 = r9
        L_0x02e5:
            r23 = r6
            r40 = r7
            r11 = r12
            r17 = r15
            r34 = 0
        L_0x02ee:
            r7 = r0
            goto L_0x038d
        L_0x02f1:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 > 0) goto L_0x0303
        L_0x02f7:
            r23 = r6
            r40 = r7
            r7 = r9
            r11 = r12
        L_0x02fd:
            r17 = r15
            r34 = 0
            goto L_0x038d
        L_0x0303:
            int[] r3 = r12.sequence
            r10 = r48
            int r4 = r10 + 2
            r4 = r3[r4]
            double r11 = (double) r4
            r4 = 0
            r3 = r3[r4]
            double r3 = (double) r3
            double r11 = r11 / r3
            double r11 = r11 * r11
            int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r0 <= 0) goto L_0x0336
            r0 = 1
            if (r9 <= r0) goto L_0x032c
            int r0 = r9 + -1
            r0 = r2[r0]
            r11 = r52
            double r3 = r11.orderControl1
            r31 = r2[r9]
            double r3 = r3 * r31
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x032e
            int r0 = r9 + -1
            goto L_0x032f
        L_0x032c:
            r11 = r52
        L_0x032e:
            r0 = r9
        L_0x032f:
            r31 = r5[r0]
            r34 = 0
            r37 = 1
            goto L_0x0339
        L_0x0336:
            r11 = r52
            r0 = r9
        L_0x0339:
            r23 = r6
            r40 = r7
            r17 = r15
            goto L_0x02ee
        L_0x0340:
            r11 = r12
            r10 = r48
            r12 = 1
            if (r9 <= r12) goto L_0x0386
            if (r33 != 0) goto L_0x0386
            r40 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r12 = (r0 > r40 ? 1 : (r0 == r40 ? 0 : -1))
            if (r12 > 0) goto L_0x0354
            r23 = r6
            r40 = r7
            r7 = r9
            goto L_0x02fd
        L_0x0354:
            int[] r12 = r11.sequence
            r14 = r12[r9]
            r17 = r15
            double r14 = (double) r14
            int r23 = r9 + 1
            r40 = r7
            r7 = r12[r23]
            r23 = r6
            double r6 = (double) r7
            double r14 = r14 * r6
            r6 = 0
            r7 = r12[r6]
            int r7 = r7 * r7
            double r6 = (double) r7
            double r14 = r14 / r6
            double r14 = r14 * r14
            int r0 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r0 <= 0) goto L_0x038c
            r0 = 1
            if (r8 <= r0) goto L_0x037d
            r0 = r2[r10]
            double r6 = r11.orderControl1
            double r6 = r6 * r3
            int r0 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x037d
            goto L_0x037e
        L_0x037d:
            r10 = r8
        L_0x037e:
            r31 = r5[r10]
            r7 = r10
            r34 = 0
            r37 = 1
            goto L_0x038d
        L_0x0386:
            r23 = r6
            r40 = r7
        L_0x038a:
            r17 = r15
        L_0x038c:
            r7 = r9
        L_0x038d:
            r12 = r11
            r11 = r13
            r15 = r17
            r17 = r40
            goto L_0x03c5
        L_0x0394:
            r23 = r6
            r40 = r7
            r35 = r9
            r19 = r10
            r11 = r12
            r17 = r15
            r9 = r47
            double r0 = r11.stepSize
            double r2 = r11.stabilityReduction
            double r0 = r0 * r2
            r2 = 0
            double r0 = r11.filterStep(r0, r13, r2)
            double r31 = org.apache.commons.math3.util.FastMath.abs((double) r0)
            r7 = r9
            r12 = r11
            r11 = r13
            r15 = r17
            r17 = r40
            r13 = r45
            r14 = r46
            r10 = r49
            r9 = -1
            r34 = 0
            goto L_0x0205
        L_0x03c1:
            r9 = r47
            r7 = r9
            r11 = r13
        L_0x03c5:
            r13 = r45
            r14 = r46
            r10 = r49
            r9 = -1
            goto L_0x01a7
        L_0x03ce:
            r9 = r7
            r49 = r10
            r45 = r13
            r46 = r14
            r40 = r17
            r10 = r8
            r13 = r11
            r11 = r12
            r17 = r15
            if (r37 != 0) goto L_0x03eb
            double r0 = r11.stepStart
            double r2 = r11.stepSize
            double r0 = r0 + r2
            r6 = r23
            r7 = r28
            r11.computeDerivatives(r0, r6, r7)
            goto L_0x03ef
        L_0x03eb:
            r6 = r23
            r7 = r28
        L_0x03ef:
            double r0 = r52.getMaxStep()
            if (r37 != 0) goto L_0x0526
            r2 = 1
        L_0x03f6:
            if (r2 > r10) goto L_0x0403
            r3 = 0
            r4 = r18[r3]
            r8 = r17
            r11.extrapolate(r3, r2, r8, r4)
            int r2 = r2 + 1
            goto L_0x03f6
        L_0x0403:
            r8 = r17
            int r2 = r10 * 2
            int r3 = r11.mudif
            int r2 = r2 - r3
            int r3 = r2 + 3
            r4 = 0
        L_0x040d:
            if (r4 >= r3) goto L_0x04d0
            int r5 = r4 / 2
            int[] r12 = r11.sequence
            r12 = r12[r5]
            double r14 = (double) r12
            double r14 = r14 * r20
            double r14 = org.apache.commons.math3.util.FastMath.pow((double) r14, (int) r4)
            r12 = r16[r5]
            int r12 = r12.length
            r17 = 2
            int r12 = r12 / 2
            r23 = r0
            r17 = r13
            r1 = r49
            r0 = 0
        L_0x042a:
            int r13 = r1.length
            if (r0 >= r13) goto L_0x0440
            int r13 = r4 + 1
            r13 = r18[r13]
            r28 = r16[r5]
            int r34 = r12 + r4
            r28 = r28[r34]
            r43 = r28[r0]
            double r43 = r43 * r14
            r13[r0] = r43
            int r0 = r0 + 1
            goto L_0x042a
        L_0x0440:
            r0 = 1
        L_0x0441:
            int r12 = r10 - r5
            if (r0 > r12) goto L_0x0482
            int[] r12 = r11.sequence
            int r13 = r0 + r5
            r12 = r12[r13]
            double r14 = (double) r12
            double r14 = r14 * r20
            double r14 = org.apache.commons.math3.util.FastMath.pow((double) r14, (int) r4)
            r12 = r16[r13]
            int r12 = r12.length
            r25 = 2
            int r12 = r12 / 2
            r28 = r7
            r47 = r9
            r9 = 0
        L_0x045e:
            int r7 = r1.length
            if (r9 >= r7) goto L_0x0474
            int r7 = r0 + -1
            r7 = r8[r7]
            r34 = r16[r13]
            int r43 = r12 + r4
            r34 = r34[r43]
            r43 = r34[r9]
            double r43 = r43 * r14
            r7[r9] = r43
            int r9 = r9 + 1
            goto L_0x045e
        L_0x0474:
            int r7 = r4 + 1
            r7 = r18[r7]
            r11.extrapolate(r5, r0, r8, r7)
            int r0 = r0 + 1
            r7 = r28
            r9 = r47
            goto L_0x0441
        L_0x0482:
            r28 = r7
            r47 = r9
            r0 = 0
        L_0x0487:
            int r5 = r1.length
            if (r0 >= r5) goto L_0x0498
            int r5 = r4 + 1
            r5 = r18[r5]
            r12 = r5[r0]
            double r14 = r11.stepSize
            double r12 = r12 * r14
            r5[r0] = r12
            int r0 = r0 + 1
            goto L_0x0487
        L_0x0498:
            int r4 = r4 + 1
            int r0 = r4 / 2
        L_0x049c:
            if (r0 > r10) goto L_0x04c4
            r5 = r16[r0]
            int r5 = r5.length
            r7 = 1
            int r5 = r5 - r7
        L_0x04a3:
            int r7 = r4 * 2
            if (r5 < r7) goto L_0x04c1
            r7 = 0
        L_0x04a8:
            int r9 = r1.length
            if (r7 >= r9) goto L_0x04be
            r9 = r16[r0]
            r12 = r9[r5]
            r13 = r12[r7]
            int r15 = r5 + -2
            r9 = r9[r15]
            r43 = r9[r7]
            double r13 = r13 - r43
            r12[r7] = r13
            int r7 = r7 + 1
            goto L_0x04a8
        L_0x04be:
            int r5 = r5 + -1
            goto L_0x04a3
        L_0x04c1:
            int r0 = r0 + 1
            goto L_0x049c
        L_0x04c4:
            r49 = r1
            r13 = r17
            r0 = r23
            r7 = r28
            r9 = r47
            goto L_0x040d
        L_0x04d0:
            r23 = r0
            r28 = r7
            r47 = r9
            r17 = r13
            r1 = r49
            if (r3 < 0) goto L_0x051f
            r4 = r45
            org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator r4 = (org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerStepInterpolator) r4
            double r4 = r11.stepSize
            r7 = r45
            r7.computeCoefficients(r3, r4)
            boolean r0 = r11.useInterpolationError
            if (r0 == 0) goto L_0x051a
            r9 = r19
            double r3 = r7.estimateError(r9)
            double r12 = r11.stepSize
            int r2 = r2 + 7
            double r14 = (double) r2
            r23 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r14 = r23 / r14
            double r14 = org.apache.commons.math3.util.FastMath.pow((double) r3, (double) r14)
            r49 = r1
            r0 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            double r0 = org.apache.commons.math3.util.FastMath.max((double) r14, (double) r0)
            double r12 = r12 / r0
            double r0 = org.apache.commons.math3.util.FastMath.abs((double) r12)
            r12 = 4621819117588971520(0x4024000000000000, double:10.0)
            int r2 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            r12 = r0
            if (r2 <= 0) goto L_0x0536
            r31 = r12
            r37 = 1
            goto L_0x0536
        L_0x051a:
            r49 = r1
            r9 = r19
            goto L_0x0534
        L_0x051f:
            r49 = r1
            r9 = r19
            r7 = r45
            goto L_0x0534
        L_0x0526:
            r23 = r0
            r28 = r7
            r47 = r9
            r8 = r17
            r9 = r19
            r7 = r45
            r17 = r13
        L_0x0534:
            r12 = r23
        L_0x0536:
            if (r37 != 0) goto L_0x0660
            double r0 = r11.stepStart
            double r2 = r11.stepSize
            double r0 = r0 + r2
            r7.storeTime(r0)
            r0 = r52
            r14 = r49
            r1 = r7
            r2 = r6
            r3 = r28
            r4 = r54
            double r0 = r0.acceptStep(r1, r2, r3, r4)
            r11.stepStart = r0
            double r0 = r11.stepStart
            r7.storeTime(r0)
            int r0 = r14.length
            r1 = r35
            r2 = 0
            java.lang.System.arraycopy(r6, r2, r1, r2, r0)
            int r0 = r14.length
            r4 = r28
            r3 = r46
            java.lang.System.arraycopy(r4, r2, r3, r2, r0)
            r2 = 1
            if (r10 != r2) goto L_0x0578
            r46 = r3
            r28 = r4
            r23 = r6
            r45 = r7
            r0 = r47
            if (r33 == 0) goto L_0x0574
            goto L_0x0575
        L_0x0574:
            r2 = 2
        L_0x0575:
            r3 = 2
            goto L_0x05f2
        L_0x0578:
            r0 = r47
            if (r10 > r0) goto L_0x05b9
            double[] r5 = r11.costPerTimeUnit
            int r15 = r10 + -1
            r22 = r5[r15]
            r46 = r3
            double r2 = r11.orderControl1
            r31 = r5[r10]
            double r2 = r2 * r31
            int r2 = (r22 > r2 ? 1 : (r22 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0596
            r28 = r4
            r23 = r6
            r45 = r7
            r2 = r15
            goto L_0x0575
        L_0x0596:
            double r2 = r11.orderControl2
            double r2 = r2 * r22
            int r2 = (r31 > r2 ? 1 : (r31 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x05b1
            int r2 = r10 + 1
            int[] r3 = r11.sequence
            int r3 = r3.length
            r5 = 2
            int r3 = r3 - r5
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r2, (int) r3)
            r28 = r4
            r3 = r5
            r23 = r6
            r45 = r7
            goto L_0x05f2
        L_0x05b1:
            r28 = r4
            r23 = r6
            r45 = r7
            r2 = r10
            goto L_0x0575
        L_0x05b9:
            r46 = r3
            r5 = 2
            int r2 = r10 + -1
            if (r10 <= r5) goto L_0x05d5
            double[] r3 = r11.costPerTimeUnit
            int r5 = r10 + -2
            r22 = r3[r5]
            r28 = r4
            r15 = r5
            double r4 = r11.orderControl1
            r31 = r3[r2]
            double r4 = r4 * r31
            int r3 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r3 >= 0) goto L_0x05d7
            r2 = r15
            goto L_0x05d7
        L_0x05d5:
            r28 = r4
        L_0x05d7:
            double[] r3 = r11.costPerTimeUnit
            r4 = r3[r10]
            r23 = r6
            r45 = r7
            double r6 = r11.orderControl2
            r31 = r3[r2]
            double r6 = r6 * r31
            int r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r3 >= 0) goto L_0x0575
            int[] r2 = r11.sequence
            int r2 = r2.length
            r3 = 2
            int r2 = r2 - r3
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r10, (int) r2)
        L_0x05f2:
            if (r33 == 0) goto L_0x060c
            int r2 = org.apache.commons.math3.util.FastMath.min((int) r2, (int) r10)
            double r4 = r11.stepSize
            double r4 = org.apache.commons.math3.util.FastMath.abs((double) r4)
            double[] r0 = r11.optimalStep
            r6 = r0[r2]
            double r4 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r6)
            r31 = r4
            r15 = r17
            r6 = 0
            goto L_0x0658
        L_0x060c:
            if (r2 > r10) goto L_0x0616
            double[] r0 = r11.optimalStep
            r4 = r0[r2]
            r15 = r17
            r6 = 0
            goto L_0x0656
        L_0x0616:
            if (r10 >= r0) goto L_0x0641
            double[] r0 = r11.costPerTimeUnit
            r4 = r0[r10]
            double r6 = r11.orderControl2
            int r15 = r10 + -1
            r24 = r0[r15]
            double r6 = r6 * r24
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 >= 0) goto L_0x0641
            double[] r0 = r11.optimalStep
            r4 = r0[r10]
            int[] r0 = r11.costPerStep
            int r6 = r2 + 1
            r6 = r0[r6]
            double r6 = (double) r6
            double r4 = r4 * r6
            r0 = r0[r10]
            double r6 = (double) r0
            double r4 = r4 / r6
            r15 = r17
            r0 = 0
            double r4 = r11.filterStep(r4, r15, r0)
            r6 = r0
            goto L_0x0656
        L_0x0641:
            r15 = r17
            double[] r0 = r11.optimalStep
            r4 = r0[r10]
            int[] r0 = r11.costPerStep
            r6 = r0[r2]
            double r6 = (double) r6
            double r4 = r4 * r6
            r0 = r0[r10]
            double r6 = (double) r0
            double r4 = r4 / r6
            r6 = 0
            double r4 = r11.filterStep(r4, r15, r6)
        L_0x0656:
            r31 = r4
        L_0x0658:
            r34 = r2
            r4 = r31
            r0 = 1
            r22 = 1
            goto L_0x0674
        L_0x0660:
            r23 = r6
            r45 = r7
            r15 = r17
            r1 = r35
            r0 = r47
            r14 = r49
            r3 = 2
            r6 = 0
            r34 = r0
            r4 = r31
            r0 = r36
        L_0x0674:
            double r4 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r12)
            if (r15 != 0) goto L_0x067b
            double r4 = -r4
        L_0x067b:
            if (r37 == 0) goto L_0x0682
            r11.isLastStep = r6
            r33 = 1
            goto L_0x0684
        L_0x0682:
            r33 = r6
        L_0x0684:
            boolean r2 = r11.isLastStep
            if (r2 == 0) goto L_0x0696
            double r2 = r11.stepStart
            r7 = r53
            r7.setTime(r2)
            r7.setCompleteState(r1)
            r52.resetInternalState()
            return
        L_0x0696:
            r7 = r53
            r24 = r6
            r19 = r9
            r12 = r11
            r10 = r14
            r11 = r15
            r31 = r38
            r17 = r40
            r13 = r45
            r14 = r46
            r9 = r1
            r1 = r4
            r15 = r8
            goto L_0x0137
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.ode.nonstiff.GraggBulirschStoerIntegrator.integrate(org.apache.commons.math3.ode.ExpandableStatefulODE, double):void");
    }
}
