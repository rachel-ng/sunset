package org.apache.commons.math3.optimization.direct;

import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.GoalType;
import org.apache.commons.math3.optimization.MultivariateOptimizer;
import org.apache.commons.math3.optimization.PointValuePair;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public class BOBYQAOptimizer extends BaseAbstractMultivariateSimpleBoundsOptimizer<MultivariateFunction> implements MultivariateOptimizer {
    public static final double DEFAULT_INITIAL_RADIUS = 10.0d;
    public static final double DEFAULT_STOPPING_RADIUS = 1.0E-8d;
    private static final double HALF = 0.5d;
    public static final int MINIMUM_PROBLEM_DIMENSION = 2;
    private static final double MINUS_ONE = -1.0d;
    private static final double ONE = 1.0d;
    private static final double ONE_OVER_A_THOUSAND = 0.001d;
    private static final double ONE_OVER_EIGHT = 0.125d;
    private static final double ONE_OVER_FOUR = 0.25d;
    private static final double ONE_OVER_TEN = 0.1d;
    private static final double SIXTEEN = 16.0d;
    private static final double TEN = 10.0d;
    private static final double TWO = 2.0d;
    private static final double TWO_HUNDRED_FIFTY = 250.0d;
    private static final double ZERO = 0.0d;
    private ArrayRealVector alternativeNewPoint;
    private Array2DRowRealMatrix bMatrix;
    private double[] boundDifference;
    private ArrayRealVector currentBest;
    private ArrayRealVector fAtInterpolationPoints;
    private ArrayRealVector gradientAtTrustRegionCenter;
    private double initialTrustRegionRadius;
    private Array2DRowRealMatrix interpolationPoints;
    private boolean isMinimize;
    private ArrayRealVector lagrangeValuesAtNewPoint;
    private ArrayRealVector lowerDifference;
    private ArrayRealVector modelSecondDerivativesParameters;
    private ArrayRealVector modelSecondDerivativesValues;
    private ArrayRealVector newPoint;
    private final int numberOfInterpolationPoints;
    private ArrayRealVector originShift;
    private final double stoppingTrustRegionRadius;
    private ArrayRealVector trialStepPoint;
    private int trustRegionCenterInterpolationPointIndex;
    private ArrayRealVector trustRegionCenterOffset;
    private ArrayRealVector upperDifference;
    private Array2DRowRealMatrix zMatrix;

    private static void printMethod() {
    }

    private static void printState(int i) {
    }

    public BOBYQAOptimizer(int i) {
        this(i, 10.0d, 1.0E-8d);
    }

    public BOBYQAOptimizer(int i, double d, double d2) {
        super((ConvergenceChecker<PointValuePair>) null);
        this.numberOfInterpolationPoints = i;
        this.initialTrustRegionRadius = d;
        this.stoppingTrustRegionRadius = d2;
    }

    /* access modifiers changed from: protected */
    public PointValuePair doOptimize() {
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        setup(lowerBound, upperBound);
        this.isMinimize = getGoalType() == GoalType.MINIMIZE;
        this.currentBest = new ArrayRealVector(getStartPoint());
        double bobyqa = bobyqa(lowerBound, upperBound);
        double[] dataRef = this.currentBest.getDataRef();
        if (!this.isMinimize) {
            bobyqa = -bobyqa;
        }
        return new PointValuePair(dataRef, bobyqa);
    }

    private double bobyqa(double[] dArr, double[] dArr2) {
        printMethod();
        int dimension = this.currentBest.getDimension();
        for (int i = 0; i < dimension; i++) {
            double d = this.boundDifference[i];
            this.lowerDifference.setEntry(i, dArr[i] - this.currentBest.getEntry(i));
            this.upperDifference.setEntry(i, dArr2[i] - this.currentBest.getEntry(i));
            if (this.lowerDifference.getEntry(i) >= (-this.initialTrustRegionRadius)) {
                if (this.lowerDifference.getEntry(i) >= 0.0d) {
                    this.currentBest.setEntry(i, dArr[i]);
                    this.lowerDifference.setEntry(i, 0.0d);
                    this.upperDifference.setEntry(i, d);
                } else {
                    this.currentBest.setEntry(i, dArr[i] + this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, -this.initialTrustRegionRadius);
                    this.upperDifference.setEntry(i, FastMath.max(dArr2[i] - this.currentBest.getEntry(i), this.initialTrustRegionRadius));
                }
            } else if (this.upperDifference.getEntry(i) <= this.initialTrustRegionRadius) {
                if (this.upperDifference.getEntry(i) <= 0.0d) {
                    this.currentBest.setEntry(i, dArr2[i]);
                    this.lowerDifference.setEntry(i, -d);
                    this.upperDifference.setEntry(i, 0.0d);
                } else {
                    this.currentBest.setEntry(i, dArr2[i] - this.initialTrustRegionRadius);
                    this.lowerDifference.setEntry(i, FastMath.min(dArr[i] - this.currentBest.getEntry(i), -this.initialTrustRegionRadius));
                    this.upperDifference.setEntry(i, this.initialTrustRegionRadius);
                }
            }
        }
        return bobyqb(dArr, dArr2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x04cc  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x04d2  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x057c  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0938  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x095e  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x099b  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0a09  */
    /* JADX WARNING: Removed duplicated region for block: B:293:0x0a47  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x0d4e  */
    /* JADX WARNING: Removed duplicated region for block: B:417:0x0fa3  */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x0fb3  */
    /* JADX WARNING: Removed duplicated region for block: B:420:0x0fc1  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x039f  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x03fc  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0401  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0420  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double bobyqb(double[] r97, double[] r98) {
        /*
            r96 = this;
            r8 = r96
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.currentBest
            int r9 = r0.getDimension()
            int r10 = r8.numberOfInterpolationPoints
            int r0 = r9 + 1
            int r11 = r10 - r0
            int r0 = r0 * r9
            int r12 = r0 / 2
            org.apache.commons.math3.linear.ArrayRealVector r13 = new org.apache.commons.math3.linear.ArrayRealVector
            r13.<init>((int) r9)
            org.apache.commons.math3.linear.ArrayRealVector r14 = new org.apache.commons.math3.linear.ArrayRealVector
            r14.<init>((int) r10)
            org.apache.commons.math3.linear.ArrayRealVector r15 = new org.apache.commons.math3.linear.ArrayRealVector
            r15.<init>((int) r10)
            r7 = 0
            r8.trustRegionCenterInterpolationPointIndex = r7
            r96.prelim(r97, r98)
            r0 = r7
            r1 = 0
        L_0x002c:
            if (r0 >= r9) goto L_0x0046
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trustRegionCenterOffset
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r8.interpolationPoints
            int r5 = r8.trustRegionCenterInterpolationPointIndex
            double r4 = r4.getEntry(r5, r0)
            r3.setEntry(r0, r4)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r8.trustRegionCenterOffset
            double r3 = r3.getEntry(r0)
            double r3 = r3 * r3
            double r1 = r1 + r3
            int r0 = r0 + 1
            goto L_0x002c
        L_0x0046:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r8.fAtInterpolationPoints
            double r3 = r0.getEntry(r7)
            int r0 = r96.getEvaluations()
            double r5 = r8.initialTrustRegionRadius
            r19 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r22 = r3
            r3 = r5
            r24 = r19
            r28 = r24
            r26 = 0
            r30 = 0
            r31 = 0
            r33 = 0
            r35 = 0
            r36 = 0
            r38 = 0
            r40 = 0
            r42 = 0
            r44 = 0
            r46 = 0
            r48 = 0
            r50 = 0
            r19 = r0
            r20 = r1
            r0 = r28
            r2 = 20
        L_0x007d:
            r51 = 4621819117588971520(0x4024000000000000, double:10.0)
            r7 = 60
            r60 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r62 = r0
            r1 = 1
            r0 = 20
            if (r2 == r0) goto L_0x0b19
            if (r2 == r7) goto L_0x0afa
            r0 = 90
            if (r2 == r0) goto L_0x0ace
            r64 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            r7 = 720(0x2d0, float:1.009E-42)
            r8 = 360(0x168, float:5.04E-43)
            r68 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            r0 = 210(0xd2, float:2.94E-43)
            if (r2 == r0) goto L_0x0120
            r0 = 230(0xe6, float:3.22E-43)
            if (r2 == r0) goto L_0x0112
            if (r2 == r8) goto L_0x00f5
            r0 = 650(0x28a, float:9.11E-43)
            if (r2 == r0) goto L_0x00dc
            r0 = 680(0x2a8, float:9.53E-43)
            if (r2 == r0) goto L_0x00c4
            if (r2 != r7) goto L_0x00b4
            r13 = r96
            r0 = r7
            goto L_0x0a64
        L_0x00b4:
            org.apache.commons.math3.exception.MathIllegalStateException r0 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.SIMPLE_MESSAGE
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r3 = "bobyqb"
            r18 = 0
            r1[r18] = r3
            r0.<init>(r2, r1)
            throw r0
        L_0x00c4:
            r18 = 0
            r6 = r5
            r93 = r11
            r8 = r12
            r66 = r13
            r73 = r15
            r5 = r30
            r74 = r31
            r11 = r35
            r13 = r96
            r12 = r0
            r15 = r14
            r0 = r42
            goto L_0x09fe
        L_0x00dc:
            r18 = 0
            r7 = r0
            r70 = r5
            r93 = r11
            r8 = r12
            r66 = r13
            r73 = r15
            r74 = r31
            r76 = r35
            r81 = r42
            r0 = r62
            r13 = r96
            r15 = r14
            goto L_0x0931
        L_0x00f5:
            r18 = 0
            r0 = r96
            r79 = r3
            r70 = r5
            r6 = r8
            r76 = r11
            r66 = r13
            r72 = r14
            r73 = r15
            r33 = r30
            r74 = r31
            r11 = r35
            r77 = r62
            r32 = r12
            goto L_0x0399
        L_0x0112:
            r18 = 0
            r7 = r0
            r66 = r13
            r2 = r30
            r74 = r31
            r0 = r96
            r32 = r12
            goto L_0x0164
        L_0x0120:
            r54 = r0
            r18 = 0
            printState(r54)
            r0 = r96
            r66 = r13
            r2 = r30
            r7 = r31
            r32 = r12
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double[] r24 = r0.altmov(r2, r7)
            r25 = r24[r18]
            r27 = r24[r1]
            r1 = 0
        L_0x013c:
            if (r1 >= r9) goto L_0x015a
            org.apache.commons.math3.linear.ArrayRealVector r12 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.newPoint
            double r70 = r13.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.trustRegionCenterOffset
            double r72 = r13.getEntry(r1)
            r74 = r7
            double r7 = r70 - r72
            r12.setEntry(r1, r7)
            int r1 = r1 + 1
            r7 = r74
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x013c
        L_0x015a:
            r74 = r7
            r7 = 230(0xe6, float:3.22E-43)
            r94 = r25
            r24 = r27
            r28 = r94
        L_0x0164:
            printState(r7)
            r1 = 0
        L_0x0168:
            if (r1 >= r10) goto L_0x01bf
            r70 = r5
            r5 = 0
            r7 = 0
            r12 = 0
            r26 = 0
        L_0x0173:
            if (r5 >= r9) goto L_0x01a8
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.interpolationPoints
            double r72 = r6.getEntry(r1, r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trialStepPoint
            double r76 = r6.getEntry(r5)
            double r72 = r72 * r76
            double r12 = r12 + r72
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.interpolationPoints
            double r72 = r6.getEntry(r1, r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r76 = r6.getEntry(r5)
            double r72 = r72 * r76
            double r26 = r26 + r72
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r0.bMatrix
            double r72 = r6.getEntry(r1, r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trialStepPoint
            double r76 = r6.getEntry(r5)
            double r72 = r72 * r76
            double r7 = r7 + r72
            int r5 = r5 + 1
            goto L_0x0173
        L_0x01a8:
            double r5 = r12 * r60
            double r5 = r5 + r26
            double r5 = r5 * r12
            r15.setEntry(r1, r5)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.lagrangeValuesAtNewPoint
            r5.setEntry(r1, r7)
            r14.setEntry(r1, r12)
            int r1 = r1 + 1
            r5 = r70
            r7 = 230(0xe6, float:3.22E-43)
            goto L_0x0168
        L_0x01bf:
            r70 = r5
            r1 = 0
            r5 = 0
        L_0x01c4:
            if (r1 >= r11) goto L_0x0200
            r7 = 0
            r12 = 0
        L_0x01c9:
            if (r12 >= r10) goto L_0x01dc
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r0.zMatrix
            double r26 = r13.getEntry(r12, r1)
            double r72 = r15.getEntry(r12)
            double r26 = r26 * r72
            double r7 = r7 + r26
            int r12 = r12 + 1
            goto L_0x01c9
        L_0x01dc:
            double r12 = r7 * r7
            double r5 = r5 - r12
            r12 = 0
        L_0x01e0:
            if (r12 >= r10) goto L_0x01fb
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.lagrangeValuesAtNewPoint
            double r26 = r13.getEntry(r12)
            r72 = r5
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.zMatrix
            double r5 = r5.getEntry(r12, r1)
            double r5 = r5 * r7
            double r5 = r26 + r5
            r13.setEntry(r12, r5)
            int r12 = r12 + 1
            r5 = r72
            goto L_0x01e0
        L_0x01fb:
            r72 = r5
            int r1 = r1 + 1
            goto L_0x01c4
        L_0x0200:
            r1 = 0
            r7 = 0
            r12 = 0
            r26 = 0
        L_0x0207:
            if (r1 >= r9) goto L_0x0284
            r72 = r14
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.trialStepPoint
            double r76 = r14.getEntry(r1)
            double r76 = r76 * r76
            double r7 = r7 + r76
            r14 = 0
            r76 = 0
        L_0x0218:
            if (r14 >= r10) goto L_0x022f
            double r78 = r15.getEntry(r14)
            r80 = r7
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r0.bMatrix
            double r7 = r7.getEntry(r14, r1)
            double r78 = r78 * r7
            double r76 = r76 + r78
            int r14 = r14 + 1
            r7 = r80
            goto L_0x0218
        L_0x022f:
            r80 = r7
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.trialStepPoint
            double r7 = r7.getEntry(r1)
            double r7 = r7 * r76
            double r26 = r26 + r7
            int r7 = r10 + r1
            r73 = r15
            r14 = r76
            r8 = 0
        L_0x0242:
            if (r8 >= r9) goto L_0x025b
            r76 = r11
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.bMatrix
            double r77 = r11.getEntry(r7, r8)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trialStepPoint
            double r82 = r11.getEntry(r8)
            double r77 = r77 * r82
            double r14 = r14 + r77
            int r8 = r8 + 1
            r11 = r76
            goto L_0x0242
        L_0x025b:
            r76 = r11
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.lagrangeValuesAtNewPoint
            r8.setEntry(r7, r14)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.trialStepPoint
            double r7 = r7.getEntry(r1)
            double r14 = r14 * r7
            double r26 = r26 + r14
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.trialStepPoint
            double r7 = r7.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r14 = r11.getEntry(r1)
            double r7 = r7 * r14
            double r12 = r12 + r7
            int r1 = r1 + 1
            r14 = r72
            r15 = r73
            r11 = r76
            r7 = r80
            goto L_0x0207
        L_0x0284:
            r76 = r11
            r72 = r14
            r73 = r15
            double r14 = r12 * r12
            double r77 = r20 + r12
            double r77 = r77 + r12
            double r11 = r7 * r60
            double r77 = r77 + r11
            double r77 = r77 * r7
            double r14 = r14 + r77
            double r14 = r14 + r5
            double r26 = r14 - r26
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.lagrangeValuesAtNewPoint
            int r5 = r0.trustRegionCenterInterpolationPointIndex
            double r11 = r1.getEntry(r5)
            r13 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r11 = r11 + r13
            r1.setEntry(r5, r11)
            r11 = r35
            if (r11 != 0) goto L_0x030a
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.lagrangeValuesAtNewPoint
            double r5 = r1.getEntry(r2)
            double r5 = r5 * r5
            double r12 = r28 * r26
            double r44 = r5 + r12
            int r1 = (r44 > r24 ? 1 : (r44 == r24 ? 0 : -1))
            if (r1 >= 0) goto L_0x0302
            r5 = 0
            int r1 = (r24 > r5 ? 1 : (r24 == r5 ? 0 : -1))
            if (r1 <= 0) goto L_0x0302
            r1 = 0
        L_0x02c3:
            if (r1 >= r9) goto L_0x02e5
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.alternativeNewPoint
            double r12 = r6.getEntry(r1)
            r5.setEntry(r1, r12)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.newPoint
            double r12 = r6.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trustRegionCenterOffset
            double r14 = r6.getEntry(r1)
            double r12 = r12 - r14
            r5.setEntry(r1, r12)
            int r1 = r1 + 1
            goto L_0x02c3
        L_0x02e5:
            r30 = r2
            r35 = r11
            r12 = r32
            r13 = r66
            r5 = r70
            r14 = r72
            r15 = r73
            r31 = r74
            r11 = r76
            r2 = 230(0xe6, float:3.22E-43)
            r24 = 0
            r94 = r7
            r8 = r0
            r0 = r94
            goto L_0x007d
        L_0x0302:
            r33 = r2
            r79 = r3
            r77 = r7
            goto L_0x0397
        L_0x030a:
            double r1 = r3 * r3
            r5 = 0
            r12 = 0
            r14 = 0
            r15 = 0
        L_0x0312:
            if (r14 >= r10) goto L_0x0391
            r77 = r7
            int r7 = r0.trustRegionCenterInterpolationPointIndex
            if (r14 != r7) goto L_0x0320
            r81 = r1
            r79 = r3
            goto L_0x0388
        L_0x0320:
            r79 = r3
            r33 = r15
            r3 = r76
            r7 = 0
            r15 = 0
        L_0x0329:
            if (r15 >= r3) goto L_0x0338
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.zMatrix
            double r34 = r4.getEntry(r14, r15)
            double r34 = r34 * r34
            double r7 = r7 + r34
            int r15 = r15 + 1
            goto L_0x0329
        L_0x0338:
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lagrangeValuesAtNewPoint
            double r34 = r4.getEntry(r14)
            double r7 = r7 * r26
            double r34 = r34 * r34
            double r7 = r7 + r34
            r4 = 0
            r34 = 0
        L_0x0347:
            if (r4 >= r9) goto L_0x035e
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r0.interpolationPoints
            double r48 = r15.getEntry(r14, r4)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r81 = r15.getEntry(r4)
            double r48 = r48 - r81
            double r48 = r48 * r48
            double r34 = r34 + r48
            int r4 = r4 + 1
            goto L_0x0347
        L_0x035e:
            double r48 = r34 / r1
            r81 = r1
            double r1 = r48 * r48
            r76 = r3
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r1)
            double r3 = r1 * r7
            int r15 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r15 <= 0) goto L_0x0377
            r5 = r3
            r44 = r7
            r15 = r14
            goto L_0x0379
        L_0x0377:
            r15 = r33
        L_0x0379:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.lagrangeValuesAtNewPoint
            double r3 = r3.getEntry(r14)
            double r3 = r3 * r3
            double r1 = r1 * r3
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r12, (double) r1)
            r12 = r1
            r48 = r34
        L_0x0388:
            int r14 = r14 + 1
            r7 = r77
            r3 = r79
            r1 = r81
            goto L_0x0312
        L_0x0391:
            r79 = r3
            r77 = r7
            r33 = r15
        L_0x0397:
            r6 = 360(0x168, float:5.04E-43)
        L_0x0399:
            printState(r6)
            r1 = 0
        L_0x039d:
            if (r1 >= r9) goto L_0x03ee
            r2 = r97[r1]
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.originShift
            double r4 = r4.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.newPoint
            double r7 = r7.getEntry(r1)
            double r4 = r4 + r7
            double r2 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r4)
            r4 = r98[r1]
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.currentBest
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r2, (double) r4)
            r7.setEntry(r1, r2)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.newPoint
            double r2 = r2.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r4 = r4.getEntry(r1)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x03d4
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.currentBest
            r3 = r97[r1]
            r2.setEntry(r1, r3)
        L_0x03d4:
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.newPoint
            double r2 = r2.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r4 = r4.getEntry(r1)
            int r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x03eb
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.currentBest
            r3 = r98[r1]
            r2.setEntry(r1, r3)
        L_0x03eb:
            int r1 = r1 + 1
            goto L_0x039d
        L_0x03ee:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.currentBest
            double[] r1 = r1.toArray()
            double r1 = r0.computeObjectiveValue(r1)
            boolean r3 = r0.isMinimize
            if (r3 != 0) goto L_0x03fd
            double r1 = -r1
        L_0x03fd:
            r7 = r1
            r1 = -1
            if (r11 != r1) goto L_0x0420
            r22 = r7
            r35 = r11
            r12 = r32
            r30 = r33
            r13 = r66
            r5 = r70
            r14 = r72
            r15 = r73
            r31 = r74
            r11 = r76
            r3 = r79
            r2 = 720(0x2d0, float:1.009E-42)
            r8 = r0
            r33 = r22
            r0 = r77
            goto L_0x007d
        L_0x0420:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.fAtInterpolationPoints
            int r2 = r0.trustRegionCenterInterpolationPointIndex
            double r12 = r1.getEntry(r2)
            r1 = 0
            r3 = 0
            r4 = 0
        L_0x042c:
            if (r3 >= r9) goto L_0x0468
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r14 = r5.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.gradientAtTrustRegionCenter
            double r34 = r5.getEntry(r3)
            double r14 = r14 * r34
            double r1 = r1 + r14
            r5 = 0
        L_0x043e:
            if (r5 > r3) goto L_0x0463
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.trialStepPoint
            double r14 = r14.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.trialStepPoint
            double r34 = r6.getEntry(r3)
            double r14 = r14 * r34
            if (r5 != r3) goto L_0x0452
            double r14 = r14 * r60
        L_0x0452:
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.modelSecondDerivativesValues
            double r34 = r6.getEntry(r4)
            double r34 = r34 * r14
            double r1 = r1 + r34
            int r4 = r4 + 1
            int r5 = r5 + 1
            r6 = 360(0x168, float:5.04E-43)
            goto L_0x043e
        L_0x0463:
            int r3 = r3 + 1
            r6 = 360(0x168, float:5.04E-43)
            goto L_0x042c
        L_0x0468:
            r14 = r1
            r1 = 0
        L_0x046a:
            if (r1 >= r10) goto L_0x0480
            r6 = r72
            double r2 = r6.getEntry(r1)
            double r2 = r2 * r2
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.modelSecondDerivativesParameters
            double r4 = r4.getEntry(r1)
            double r4 = r4 * r60
            double r4 = r4 * r2
            double r14 = r14 + r4
            int r1 = r1 + 1
            goto L_0x046a
        L_0x0480:
            r6 = r72
            double r1 = r7 - r12
            double r34 = r1 - r14
            double r40 = org.apache.commons.math3.util.FastMath.abs((double) r34)
            r3 = r42
            int r5 = (r3 > r70 ? 1 : (r3 == r70 ? 0 : -1))
            if (r5 <= 0) goto L_0x0496
            int r5 = r96.getEvaluations()
            r19 = r5
        L_0x0496:
            r42 = 4611686018427387904(0x4000000000000000, double:2.0)
            if (r11 <= 0) goto L_0x059d
            r16 = 0
            int r5 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r5 >= 0) goto L_0x058b
            double r1 = r1 / r14
            r72 = r6
            double r5 = r79 * r60
            int r46 = (r1 > r68 ? 1 : (r1 == r68 ? 0 : -1))
            if (r46 > 0) goto L_0x04b0
            double r5 = org.apache.commons.math3.util.FastMath.min((double) r5, (double) r3)
        L_0x04ad:
            r46 = r1
            goto L_0x04c6
        L_0x04b0:
            r46 = 4604480259023595110(0x3fe6666666666666, double:0.7)
            int r46 = (r1 > r46 ? 1 : (r1 == r46 ? 0 : -1))
            if (r46 > 0) goto L_0x04be
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r3)
            goto L_0x04ad
        L_0x04be:
            r46 = r1
            double r1 = r3 * r42
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r1)
        L_0x04c6:
            double r1 = r70 * r64
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x04ce
            r5 = r70
        L_0x04ce:
            int r1 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r1 >= 0) goto L_0x057c
            double r1 = r5 * r5
            r81 = r3
            r79 = r5
            r3 = r16
            r85 = r44
            r83 = r48
            r5 = 0
            r6 = 0
            r48 = r3
        L_0x04e2:
            if (r5 >= r10) goto L_0x0561
            r54 = r6
            r83 = r16
            r6 = 0
            r94 = r76
            r76 = r11
            r11 = r94
        L_0x04ef:
            if (r6 >= r11) goto L_0x0502
            r58 = r11
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.zMatrix
            double r87 = r11.getEntry(r5, r6)
            double r87 = r87 * r87
            double r83 = r83 + r87
            int r6 = r6 + 1
            r11 = r58
            goto L_0x04ef
        L_0x0502:
            r58 = r11
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lagrangeValuesAtNewPoint
            double r87 = r6.getEntry(r5)
            double r83 = r83 * r26
            double r87 = r87 * r87
            double r83 = r83 + r87
            r87 = r16
            r6 = 0
        L_0x0513:
            if (r6 >= r9) goto L_0x052a
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.interpolationPoints
            double r89 = r11.getEntry(r5, r6)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.newPoint
            double r91 = r11.getEntry(r6)
            double r89 = r89 - r91
            double r89 = r89 * r89
            double r87 = r87 + r89
            int r6 = r6 + 1
            goto L_0x0513
        L_0x052a:
            double r89 = r87 / r1
            r91 = r1
            double r1 = r89 * r89
            r89 = r12
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r1 = org.apache.commons.math3.util.FastMath.max((double) r11, (double) r1)
            double r62 = r1 * r83
            int r6 = (r62 > r48 ? 1 : (r62 == r48 ? 0 : -1))
            if (r6 <= 0) goto L_0x0544
            r6 = r5
            r48 = r62
            r85 = r83
            goto L_0x0546
        L_0x0544:
            r6 = r54
        L_0x0546:
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.lagrangeValuesAtNewPoint
            double r62 = r13.getEntry(r5)
            double r62 = r62 * r62
            double r1 = r1 * r62
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r1)
            int r5 = r5 + 1
            r11 = r76
            r83 = r87
            r12 = r89
            r1 = r91
            r76 = r58
            goto L_0x04e2
        L_0x0561:
            r54 = r6
            r89 = r12
            r58 = r76
            r76 = r11
            double r3 = r3 * r60
            int r1 = (r48 > r3 ? 1 : (r48 == r3 ? 0 : -1))
            r13 = r0
            if (r1 > 0) goto L_0x0575
            r6 = r33
            r48 = r83
            goto L_0x0589
        L_0x0575:
            r6 = r54
            r48 = r83
            r44 = r85
            goto L_0x0589
        L_0x057c:
            r81 = r3
            r79 = r5
            r89 = r12
            r58 = r76
            r76 = r11
            r13 = r0
            r6 = r33
        L_0x0589:
            r11 = 0
            goto L_0x05ad
        L_0x058b:
            org.apache.commons.math3.exception.MathIllegalStateException r1 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.TRUST_REGION_STEP_FAILED
            java.lang.Double r3 = java.lang.Double.valueOf(r14)
            r4 = 1
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r11 = 0
            r4[r11] = r3
            r1.<init>(r2, r4)
            throw r1
        L_0x059d:
            r81 = r3
            r72 = r6
            r89 = r12
            r58 = r76
            r16 = 0
            r76 = r11
            r11 = 0
            r13 = r0
            r6 = r33
        L_0x05ad:
            r12 = 90
            r0 = r96
            r1 = r26
            r5 = r58
            r11 = r81
            r3 = r44
            r93 = r5
            r11 = r16
            r5 = r6
            r0.update(r1, r3, r5)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.modelSecondDerivativesParameters
            double r0 = r0.getEntry(r6)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.modelSecondDerivativesParameters
            r2.setEntry(r6, r11)
            r2 = 0
            r3 = 0
        L_0x05ce:
            if (r2 >= r9) goto L_0x05fc
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r13.interpolationPoints
            double r4 = r4.getEntry(r6, r2)
            double r4 = r4 * r0
            r11 = 0
        L_0x05d8:
            if (r11 > r2) goto L_0x05f5
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.modelSecondDerivativesValues
            double r62 = r12.getEntry(r3)
            r83 = r0
            org.apache.commons.math3.linear.Array2DRowRealMatrix r0 = r13.interpolationPoints
            double r0 = r0.getEntry(r6, r11)
            double r0 = r0 * r4
            double r0 = r62 + r0
            r12.setEntry(r3, r0)
            int r3 = r3 + 1
            int r11 = r11 + 1
            r0 = r83
            goto L_0x05d8
        L_0x05f5:
            r83 = r0
            int r2 = r2 + 1
            r11 = 0
            goto L_0x05ce
        L_0x05fc:
            r11 = r93
            r0 = 0
        L_0x05ff:
            if (r0 >= r11) goto L_0x0629
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r13.zMatrix
            double r1 = r1.getEntry(r6, r0)
            double r1 = r1 * r34
            r3 = 0
        L_0x060a:
            if (r3 >= r10) goto L_0x0626
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.modelSecondDerivativesParameters
            double r62 = r4.getEntry(r3)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r13.zMatrix
            double r83 = r5.getEntry(r3, r0)
            double r83 = r83 * r1
            r85 = r1
            double r1 = r62 + r83
            r4.setEntry(r3, r1)
            int r3 = r3 + 1
            r1 = r85
            goto L_0x060a
        L_0x0626:
            int r0 = r0 + 1
            goto L_0x05ff
        L_0x0629:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.fAtInterpolationPoints
            r0.setEntry(r6, r7)
            r0 = 0
        L_0x062f:
            if (r0 >= r9) goto L_0x064a
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r13.interpolationPoints
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.newPoint
            double r2 = r2.getEntry(r0)
            r1.setEntry(r6, r0, r2)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r1 = r13.bMatrix
            double r1 = r1.getEntry(r6, r0)
            r12 = r66
            r12.setEntry(r0, r1)
            int r0 = r0 + 1
            goto L_0x062f
        L_0x064a:
            r12 = r66
            r0 = 0
        L_0x064d:
            if (r0 >= r10) goto L_0x06a5
            r1 = 0
            r3 = 0
        L_0x0652:
            if (r3 >= r11) goto L_0x0669
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r13.zMatrix
            double r4 = r4.getEntry(r6, r3)
            r62 = r14
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r13.zMatrix
            double r14 = r14.getEntry(r0, r3)
            double r4 = r4 * r14
            double r1 = r1 + r4
            int r3 = r3 + 1
            r14 = r62
            goto L_0x0652
        L_0x0669:
            r62 = r14
            r3 = 0
            r5 = 0
        L_0x066e:
            if (r5 >= r9) goto L_0x0686
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r13.interpolationPoints
            double r14 = r14.getEntry(r0, r5)
            r93 = r11
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trustRegionCenterOffset
            double r66 = r11.getEntry(r5)
            double r14 = r14 * r66
            double r3 = r3 + r14
            int r5 = r5 + 1
            r11 = r93
            goto L_0x066e
        L_0x0686:
            r93 = r11
            double r1 = r1 * r3
            r3 = 0
        L_0x068a:
            if (r3 >= r9) goto L_0x069e
            double r4 = r12.getEntry(r3)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r13.interpolationPoints
            double r14 = r11.getEntry(r0, r3)
            double r14 = r14 * r1
            double r4 = r4 + r14
            r12.setEntry(r3, r4)
            int r3 = r3 + 1
            goto L_0x068a
        L_0x069e:
            int r0 = r0 + 1
            r14 = r62
            r11 = r93
            goto L_0x064d
        L_0x06a5:
            r93 = r11
            r62 = r14
            r0 = 0
        L_0x06aa:
            if (r0 >= r9) goto L_0x06bf
            org.apache.commons.math3.linear.ArrayRealVector r1 = r13.gradientAtTrustRegionCenter
            double r2 = r1.getEntry(r0)
            double r4 = r12.getEntry(r0)
            double r4 = r4 * r34
            double r2 = r2 + r4
            r1.setEntry(r0, r2)
            int r0 = r0 + 1
            goto L_0x06aa
        L_0x06bf:
            int r0 = (r7 > r89 ? 1 : (r7 == r89 ? 0 : -1))
            if (r0 >= 0) goto L_0x0760
            r13.trustRegionCenterInterpolationPointIndex = r6
            r0 = 0
            r2 = 0
            r3 = 0
        L_0x06c9:
            if (r2 >= r9) goto L_0x071d
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.trustRegionCenterOffset
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.newPoint
            double r14 = r5.getEntry(r2)
            r4.setEntry(r2, r14)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.trustRegionCenterOffset
            double r4 = r4.getEntry(r2)
            double r4 = r4 * r4
            double r0 = r0 + r4
            r4 = 0
        L_0x06df:
            if (r4 > r2) goto L_0x071a
            if (r4 >= r2) goto L_0x06fc
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.gradientAtTrustRegionCenter
            double r14 = r5.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.modelSecondDerivativesValues
            double r20 = r11.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trialStepPoint
            double r33 = r11.getEntry(r4)
            double r20 = r20 * r33
            double r14 = r14 + r20
            r5.setEntry(r2, r14)
        L_0x06fc:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.gradientAtTrustRegionCenter
            double r14 = r5.getEntry(r4)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.modelSecondDerivativesValues
            double r20 = r11.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trialStepPoint
            double r33 = r11.getEntry(r2)
            double r20 = r20 * r33
            double r14 = r14 + r20
            r5.setEntry(r4, r14)
            int r3 = r3 + 1
            int r4 = r4 + 1
            goto L_0x06df
        L_0x071a:
            int r2 = r2 + 1
            goto L_0x06c9
        L_0x071d:
            r2 = 0
        L_0x071e:
            if (r2 >= r10) goto L_0x075e
            r3 = 0
            r5 = 0
        L_0x0723:
            if (r5 >= r9) goto L_0x0737
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r13.interpolationPoints
            double r14 = r11.getEntry(r2, r5)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trialStepPoint
            double r20 = r11.getEntry(r5)
            double r14 = r14 * r20
            double r3 = r3 + r14
            int r5 = r5 + 1
            goto L_0x0723
        L_0x0737:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.modelSecondDerivativesParameters
            double r14 = r5.getEntry(r2)
            double r3 = r3 * r14
            r5 = 0
        L_0x073f:
            if (r5 >= r9) goto L_0x0759
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.gradientAtTrustRegionCenter
            double r14 = r11.getEntry(r5)
            r20 = r0
            org.apache.commons.math3.linear.Array2DRowRealMatrix r0 = r13.interpolationPoints
            double r0 = r0.getEntry(r2, r5)
            double r0 = r0 * r3
            double r14 = r14 + r0
            r11.setEntry(r5, r14)
            int r5 = r5 + 1
            r0 = r20
            goto L_0x073f
        L_0x0759:
            r20 = r0
            int r2 = r2 + 1
            goto L_0x071e
        L_0x075e:
            r20 = r0
        L_0x0760:
            if (r76 <= 0) goto L_0x08dc
            r0 = 0
        L_0x0763:
            if (r0 >= r10) goto L_0x0783
            org.apache.commons.math3.linear.ArrayRealVector r1 = r13.lagrangeValuesAtNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.fAtInterpolationPoints
            double r2 = r2.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.fAtInterpolationPoints
            int r5 = r13.trustRegionCenterInterpolationPointIndex
            double r4 = r4.getEntry(r5)
            double r2 = r2 - r4
            r1.setEntry(r0, r2)
            r11 = r73
            r1 = 0
            r11.setEntry(r0, r1)
            int r0 = r0 + 1
            goto L_0x0763
        L_0x0783:
            r11 = r73
            r14 = r93
            r0 = 0
        L_0x0788:
            if (r0 >= r14) goto L_0x07bb
            r1 = 0
            r3 = 0
        L_0x078d:
            if (r3 >= r10) goto L_0x07a1
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r13.zMatrix
            double r4 = r4.getEntry(r3, r0)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r13.lagrangeValuesAtNewPoint
            double r33 = r15.getEntry(r3)
            double r4 = r4 * r33
            double r1 = r1 + r4
            int r3 = r3 + 1
            goto L_0x078d
        L_0x07a1:
            r3 = 0
        L_0x07a2:
            if (r3 >= r10) goto L_0x07b8
            double r4 = r11.getEntry(r3)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r13.zMatrix
            double r33 = r15.getEntry(r3, r0)
            double r33 = r33 * r1
            double r4 = r4 + r33
            r11.setEntry(r3, r4)
            int r3 = r3 + 1
            goto L_0x07a2
        L_0x07b8:
            int r0 = r0 + 1
            goto L_0x0788
        L_0x07bb:
            r0 = 0
        L_0x07bc:
            if (r0 >= r10) goto L_0x07e9
            r1 = 0
            r3 = 0
        L_0x07c1:
            if (r3 >= r9) goto L_0x07d5
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r13.interpolationPoints
            double r4 = r4.getEntry(r0, r3)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r13.trustRegionCenterOffset
            double r33 = r15.getEntry(r3)
            double r4 = r4 * r33
            double r1 = r1 + r4
            int r3 = r3 + 1
            goto L_0x07c1
        L_0x07d5:
            double r3 = r11.getEntry(r0)
            r15 = r72
            r15.setEntry(r0, r3)
            double r3 = r11.getEntry(r0)
            double r1 = r1 * r3
            r11.setEntry(r0, r1)
            int r0 = r0 + 1
            goto L_0x07bc
        L_0x07e9:
            r15 = r72
            r0 = 0
            r2 = 0
            r4 = 0
        L_0x07f0:
            if (r4 >= r9) goto L_0x0893
            r33 = r6
            r93 = r14
            r5 = 0
            r14 = 0
        L_0x07f9:
            if (r14 >= r10) goto L_0x0820
            r66 = r12
            org.apache.commons.math3.linear.Array2DRowRealMatrix r12 = r13.bMatrix
            double r34 = r12.getEntry(r14, r4)
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.lagrangeValuesAtNewPoint
            double r72 = r12.getEntry(r14)
            double r34 = r34 * r72
            org.apache.commons.math3.linear.Array2DRowRealMatrix r12 = r13.interpolationPoints
            double r72 = r12.getEntry(r14, r4)
            double r83 = r11.getEntry(r14)
            double r72 = r72 * r83
            double r34 = r34 + r72
            double r5 = r5 + r34
            int r14 = r14 + 1
            r12 = r66
            goto L_0x07f9
        L_0x0820:
            r66 = r12
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.trustRegionCenterOffset
            double r34 = r12.getEntry(r4)
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.lowerDifference
            double r72 = r12.getEntry(r4)
            int r12 = (r34 > r72 ? 1 : (r34 == r72 ? 0 : -1))
            if (r12 != 0) goto L_0x084b
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.gradientAtTrustRegionCenter
            r73 = r11
            double r11 = r12.getEntry(r4)
            r34 = r7
            r7 = 0
            double r11 = org.apache.commons.math3.util.FastMath.min((double) r7, (double) r11)
            double r11 = r11 * r11
            double r0 = r0 + r11
            double r11 = org.apache.commons.math3.util.FastMath.min((double) r7, (double) r5)
            double r11 = r11 * r11
            double r2 = r2 + r11
            goto L_0x087e
        L_0x084b:
            r34 = r7
            r73 = r11
            org.apache.commons.math3.linear.ArrayRealVector r7 = r13.trustRegionCenterOffset
            double r7 = r7.getEntry(r4)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.upperDifference
            double r11 = r11.getEntry(r4)
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 != 0) goto L_0x0873
            org.apache.commons.math3.linear.ArrayRealVector r7 = r13.gradientAtTrustRegionCenter
            double r7 = r7.getEntry(r4)
            r11 = 0
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r11, (double) r7)
            double r7 = r7 * r7
            double r0 = r0 + r7
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r11, (double) r5)
            double r7 = r7 * r7
            goto L_0x087d
        L_0x0873:
            org.apache.commons.math3.linear.ArrayRealVector r7 = r13.gradientAtTrustRegionCenter
            double r7 = r7.getEntry(r4)
            double r7 = r7 * r7
            double r0 = r0 + r7
            double r7 = r5 * r5
        L_0x087d:
            double r2 = r2 + r7
        L_0x087e:
            org.apache.commons.math3.linear.ArrayRealVector r7 = r13.lagrangeValuesAtNewPoint
            int r8 = r10 + r4
            r7.setEntry(r8, r5)
            int r4 = r4 + 1
            r6 = r33
            r7 = r34
            r12 = r66
            r11 = r73
            r14 = r93
            goto L_0x07f0
        L_0x0893:
            r33 = r6
            r34 = r7
            r73 = r11
            r66 = r12
            r93 = r14
            int r4 = r50 + 1
            double r2 = r2 * r51
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x08a6
            r4 = 0
        L_0x08a6:
            r0 = 3
            r8 = r32
            if (r4 < r0) goto L_0x08d9
            int r0 = org.apache.commons.math3.util.FastMath.max((int) r10, (int) r8)
            r1 = 0
        L_0x08b0:
            if (r1 >= r0) goto L_0x08d9
            if (r1 >= r9) goto L_0x08c1
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.gradientAtTrustRegionCenter
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.lagrangeValuesAtNewPoint
            int r4 = r10 + r1
            double r3 = r3.getEntry(r4)
            r2.setEntry(r1, r3)
        L_0x08c1:
            if (r1 >= r10) goto L_0x08cc
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.modelSecondDerivativesParameters
            double r3 = r15.getEntry(r1)
            r2.setEntry(r1, r3)
        L_0x08cc:
            if (r1 >= r8) goto L_0x08d5
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.modelSecondDerivativesValues
            r3 = 0
            r2.setEntry(r1, r3)
        L_0x08d5:
            int r1 = r1 + 1
            r4 = 0
            goto L_0x08b0
        L_0x08d9:
            r50 = r4
            goto L_0x08e6
        L_0x08dc:
            r33 = r6
            r34 = r7
            r66 = r12
            r8 = r32
            r15 = r72
        L_0x08e6:
            if (r76 != 0) goto L_0x090d
        L_0x08e8:
            r12 = r8
            r8 = r13
            r14 = r15
            r30 = r33
            r33 = r34
            r13 = r66
            r5 = r70
            r15 = r73
            r31 = r74
            r35 = r76
            r0 = r77
            r3 = r79
            r42 = r81
            r11 = r93
            r2 = 60
            r94 = r36
            r36 = r40
            r40 = r38
            r38 = r94
            goto L_0x007d
        L_0x090d:
            double r0 = r62 * r68
            double r0 = r89 + r0
            int r0 = (r34 > r0 ? 1 : (r34 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0916
            goto L_0x08e8
        L_0x0916:
            double r42 = r42 * r79
            double r5 = r70 * r51
            double r0 = r42 * r42
            double r5 = r5 * r5
            double r48 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r5)
            r33 = r34
            r0 = r77
            r3 = r79
            r7 = 650(0x28a, float:9.11E-43)
            r94 = r36
            r36 = r40
            r40 = r38
            r38 = r94
        L_0x0931:
            printState(r7)
            r2 = 0
            r5 = -1
        L_0x0936:
            if (r2 >= r10) goto L_0x095c
            r6 = 0
            r11 = 0
        L_0x093b:
            if (r11 >= r9) goto L_0x0952
            org.apache.commons.math3.linear.Array2DRowRealMatrix r12 = r13.interpolationPoints
            double r42 = r12.getEntry(r2, r11)
            org.apache.commons.math3.linear.ArrayRealVector r12 = r13.trustRegionCenterOffset
            double r51 = r12.getEntry(r11)
            double r42 = r42 - r51
            double r42 = r42 * r42
            double r6 = r6 + r42
            int r11 = r11 + 1
            goto L_0x093b
        L_0x0952:
            int r11 = (r6 > r48 ? 1 : (r6 == r48 ? 0 : -1))
            if (r11 <= 0) goto L_0x0959
            r5 = r2
            r48 = r6
        L_0x0959:
            int r2 = r2 + 1
            goto L_0x0936
        L_0x095c:
            if (r5 < 0) goto L_0x099b
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r48)
            r11 = r76
            r2 = -1
            if (r11 != r2) goto L_0x0979
            double r3 = r3 * r68
            double r6 = r0 * r60
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r3, (double) r6)
            double r6 = r70 * r64
            int r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r4 > 0) goto L_0x0978
            r3 = r70
            goto L_0x0979
        L_0x0978:
            r3 = r2
        L_0x0979:
            double r0 = r0 * r68
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r3)
            r6 = r70
            double r31 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r6)
            double r0 = r31 * r31
            r30 = r5
            r5 = r6
            r12 = r8
            r8 = r13
            r14 = r15
            r13 = r66
            r15 = r73
            r42 = r81
            r11 = r93
            r2 = 90
            r35 = 0
            goto L_0x007d
        L_0x099b:
            r6 = r70
            r11 = r76
            r2 = -1
            if (r11 != r2) goto L_0x09b8
            r30 = r5
            r5 = r6
            r12 = r8
            r35 = r11
            r8 = r13
            r14 = r15
            r13 = r66
            r15 = r73
            r31 = r74
            r42 = r81
            r11 = r93
            r2 = 680(0x2a8, float:9.53E-43)
            goto L_0x007d
        L_0x09b8:
            r16 = 0
            int r2 = (r46 > r16 ? 1 : (r46 == r16 ? 0 : -1))
            if (r2 <= 0) goto L_0x09d4
            r30 = r5
            r5 = r6
            r12 = r8
            r35 = r11
            r8 = r13
            r14 = r15
            r13 = r66
            r15 = r73
            r31 = r74
            r42 = r81
            r11 = r93
            r2 = 60
            goto L_0x007d
        L_0x09d4:
            r42 = r0
            r0 = r81
            double r51 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r0)
            int r2 = (r51 > r6 ? 1 : (r51 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x09fa
            r30 = r5
            r5 = r6
            r12 = r8
            r35 = r11
            r8 = r13
            r14 = r15
            r13 = r66
            r15 = r73
            r31 = r74
            r11 = r93
            r2 = 60
            r94 = r0
            r0 = r42
            r42 = r94
            goto L_0x007d
        L_0x09fa:
            r62 = r42
            r12 = 680(0x2a8, float:9.53E-43)
        L_0x09fe:
            printState(r12)
            r81 = r0
            double r0 = r13.stoppingTrustRegionRadius
            int r2 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r2 <= 0) goto L_0x0a47
            double r2 = r6 * r60
            double r46 = r6 / r0
            r11 = 4625196817309499392(0x4030000000000000, double:16.0)
            int r4 = (r46 > r11 ? 1 : (r46 == r11 ? 0 : -1))
            if (r4 > 0) goto L_0x0a14
            goto L_0x0a27
        L_0x0a14:
            r0 = 4643000109586448384(0x406f400000000000, double:250.0)
            int r0 = (r46 > r0 ? 1 : (r46 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0a25
            double r0 = org.apache.commons.math3.util.FastMath.sqrt(r46)
            double r6 = r13.stoppingTrustRegionRadius
            double r0 = r0 * r6
            goto L_0x0a27
        L_0x0a25:
            double r0 = r6 * r68
        L_0x0a27:
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r2, (double) r0)
            int r19 = r96.getEvaluations()
            r30 = r5
            r12 = r8
            r8 = r13
            r14 = r15
            r13 = r66
            r15 = r73
            r31 = r74
            r42 = r81
            r11 = r93
            r2 = 60
            r35 = 0
            r5 = r0
            r0 = r62
            goto L_0x007d
        L_0x0a47:
            r14 = -1
            if (r11 != r14) goto L_0x0a62
            r30 = r5
            r5 = r6
            r12 = r8
            r35 = r11
            r8 = r13
            r14 = r15
            r0 = r62
            r13 = r66
            r15 = r73
            r31 = r74
            r42 = r81
            r11 = r93
            r2 = 360(0x168, float:5.04E-43)
            goto L_0x007d
        L_0x0a62:
            r0 = 720(0x2d0, float:1.009E-42)
        L_0x0a64:
            printState(r0)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.fAtInterpolationPoints
            int r1 = r13.trustRegionCenterInterpolationPointIndex
            double r0 = r0.getEntry(r1)
            int r0 = (r0 > r22 ? 1 : (r0 == r22 ? 0 : -1))
            if (r0 > 0) goto L_0x0acd
            r7 = 0
        L_0x0a74:
            if (r7 >= r9) goto L_0x0ac5
            r0 = r97[r7]
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.originShift
            double r2 = r2.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.trustRegionCenterOffset
            double r4 = r4.getEntry(r7)
            double r2 = r2 + r4
            double r0 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r2)
            r2 = r98[r7]
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.currentBest
            double r0 = org.apache.commons.math3.util.FastMath.min((double) r0, (double) r2)
            r4.setEntry(r7, r0)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.trustRegionCenterOffset
            double r0 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.lowerDifference
            double r2 = r2.getEntry(r7)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0aab
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.currentBest
            r1 = r97[r7]
            r0.setEntry(r7, r1)
        L_0x0aab:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.trustRegionCenterOffset
            double r0 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.upperDifference
            double r2 = r2.getEntry(r7)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x0ac2
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.currentBest
            r1 = r98[r7]
            r0.setEntry(r7, r1)
        L_0x0ac2:
            int r7 = r7 + 1
            goto L_0x0a74
        L_0x0ac5:
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.fAtInterpolationPoints
            int r1 = r13.trustRegionCenterInterpolationPointIndex
            double r33 = r0.getEntry(r1)
        L_0x0acd:
            return r33
        L_0x0ace:
            r6 = r5
            r93 = r11
            r66 = r13
            r73 = r15
            r11 = r35
            r81 = r42
            r54 = 210(0xd2, float:2.94E-43)
            r13 = r8
            r70 = r6
            r35 = r12
            r12 = r14
            r43 = r30
            r74 = r31
            r5 = r38
            r53 = r54
            r30 = 0
            r42 = 20
            r57 = 230(0xe6, float:3.22E-43)
            r32 = r0
            r14 = r3
            r38 = r36
            r0 = r62
            r4 = r66
            goto L_0x0d40
        L_0x0afa:
            r79 = r3
            r70 = r5
            r93 = r11
            r66 = r13
            r73 = r15
            r2 = r30
            r74 = r31
            r11 = r35
            r54 = 210(0xd2, float:2.94E-43)
            r13 = r8
            r8 = r12
            r15 = r14
            r12 = 680(0x2a8, float:9.53E-43)
            r14 = -1
            r0 = r7
            r35 = r8
            r30 = 20
            goto L_0x0bd8
        L_0x0b19:
            r79 = r3
            r70 = r5
            r93 = r11
            r66 = r13
            r73 = r15
            r2 = r30
            r74 = r31
            r11 = r35
            r7 = 650(0x28a, float:9.11E-43)
            r54 = 210(0xd2, float:2.94E-43)
            r30 = r0
            r4 = r1
            r13 = r8
            r8 = r12
            r15 = r14
            r12 = 680(0x2a8, float:9.53E-43)
            r14 = -1
            printState(r30)
            int r0 = r13.trustRegionCenterInterpolationPointIndex
            if (r0 == 0) goto L_0x0bd4
            r0 = 0
            r1 = 0
        L_0x0b3f:
            if (r0 >= r9) goto L_0x0b8d
            r3 = 0
        L_0x0b42:
            if (r3 > r0) goto L_0x0b86
            if (r3 >= r0) goto L_0x0b62
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.gradientAtTrustRegionCenter
            double r31 = r5.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r13.modelSecondDerivativesValues
            double r42 = r6.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r6 = r13.trustRegionCenterOffset
            double r56 = r6.getEntry(r3)
            double r42 = r42 * r56
            r35 = r8
            double r7 = r31 + r42
            r5.setEntry(r0, r7)
            goto L_0x0b64
        L_0x0b62:
            r35 = r8
        L_0x0b64:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.gradientAtTrustRegionCenter
            double r6 = r5.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r13.modelSecondDerivativesValues
            double r31 = r8.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r13.trustRegionCenterOffset
            double r42 = r8.getEntry(r0)
            double r31 = r31 * r42
            double r6 = r6 + r31
            r5.setEntry(r3, r6)
            int r1 = r1 + 1
            int r3 = r3 + 1
            r8 = r35
            r7 = 650(0x28a, float:9.11E-43)
            goto L_0x0b42
        L_0x0b86:
            r35 = r8
            int r0 = r0 + 1
            r7 = 650(0x28a, float:9.11E-43)
            goto L_0x0b3f
        L_0x0b8d:
            r35 = r8
            int r0 = r96.getEvaluations()
            if (r0 <= r10) goto L_0x0bd6
            r0 = 0
        L_0x0b96:
            if (r0 >= r10) goto L_0x0bd6
            r1 = 0
            r5 = 0
        L_0x0b9b:
            if (r1 >= r9) goto L_0x0baf
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r13.interpolationPoints
            double r7 = r3.getEntry(r0, r1)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.trustRegionCenterOffset
            double r31 = r3.getEntry(r1)
            double r7 = r7 * r31
            double r5 = r5 + r7
            int r1 = r1 + 1
            goto L_0x0b9b
        L_0x0baf:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r13.modelSecondDerivativesParameters
            double r7 = r1.getEntry(r0)
            double r5 = r5 * r7
            r1 = 0
        L_0x0bb7:
            if (r1 >= r9) goto L_0x0bd0
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.gradientAtTrustRegionCenter
            double r7 = r3.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r13.interpolationPoints
            double r31 = r4.getEntry(r0, r1)
            double r31 = r31 * r5
            double r7 = r7 + r31
            r3.setEntry(r1, r7)
            int r1 = r1 + 1
            r4 = 1
            goto L_0x0bb7
        L_0x0bd0:
            int r0 = r0 + 1
            r4 = 1
            goto L_0x0b96
        L_0x0bd4:
            r35 = r8
        L_0x0bd6:
            r0 = 60
        L_0x0bd8:
            printState(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = new org.apache.commons.math3.linear.ArrayRealVector
            r3.<init>((int) r9)
            org.apache.commons.math3.linear.ArrayRealVector r4 = new org.apache.commons.math3.linear.ArrayRealVector
            r4.<init>((int) r9)
            org.apache.commons.math3.linear.ArrayRealVector r5 = new org.apache.commons.math3.linear.ArrayRealVector
            r5.<init>((int) r9)
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>((int) r9)
            org.apache.commons.math3.linear.ArrayRealVector r7 = new org.apache.commons.math3.linear.ArrayRealVector
            r7.<init>((int) r9)
            r0 = r96
            r8 = r2
            r31 = 1
            r1 = r79
            r72 = r15
            r14 = r79
            r12 = r72
            r42 = r30
            r53 = r54
            r18 = -1
            r30 = 0
            r32 = 90
            r55 = 680(0x2a8, float:9.53E-43)
            r56 = 650(0x28a, float:9.11E-43)
            r57 = 230(0xe6, float:3.22E-43)
            double[] r0 = r0.trsbox(r1, r3, r4, r5, r6, r7)
            r1 = r0[r30]
            r3 = r0[r31]
            double r5 = org.apache.commons.math3.util.FastMath.sqrt(r1)
            double r5 = org.apache.commons.math3.util.FastMath.min((double) r14, (double) r5)
            double r58 = r70 * r60
            int r0 = (r5 > r58 ? 1 : (r5 == r58 ? 0 : -1))
            if (r0 >= 0) goto L_0x0d2b
            double r31 = r70 * r51
            double r48 = r31 * r31
            int r0 = r96.getEvaluations()
            int r7 = r19 + 2
            if (r0 > r7) goto L_0x0c4d
            r0 = r1
            r42 = r5
            r30 = r8
            r8 = r13
            r3 = r14
            r2 = r56
            r13 = r66
            r5 = r70
            r15 = r73
            r31 = r74
            r11 = r93
            r14 = r12
            r12 = r35
        L_0x0c49:
            r35 = r18
            goto L_0x007d
        L_0x0c4d:
            r51 = r1
            r2 = r8
            r0 = r36
            r36 = r5
            r5 = r38
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r0, (double) r5)
            r38 = r0
            r0 = r40
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r7, (double) r0)
            r31 = 4593671619917905920(0x3fc0000000000000, double:0.125)
            double r31 = r31 * r70
            double r31 = r31 * r70
            r16 = 0
            int r11 = (r3 > r16 ? 1 : (r3 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x0c92
            double r31 = r31 * r3
            int r3 = (r7 > r31 ? 1 : (r7 == r31 ? 0 : -1))
            if (r3 <= 0) goto L_0x0c92
            r40 = r0
            r30 = r2
            r8 = r13
            r3 = r14
            r42 = r36
            r36 = r38
            r0 = r51
            r2 = r56
            r13 = r66
            r15 = r73
            r31 = r74
            r11 = r93
        L_0x0c8a:
            r38 = r5
            r14 = r12
            r12 = r35
            r5 = r70
            goto L_0x0c49
        L_0x0c92:
            double r7 = r7 / r70
            r3 = r30
        L_0x0c96:
            if (r3 >= r9) goto L_0x0d10
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.newPoint
            double r31 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r13.lowerDifference
            double r40 = r4.getEntry(r3)
            int r4 = (r31 > r40 ? 1 : (r31 == r40 ? 0 : -1))
            if (r4 != 0) goto L_0x0caf
            r4 = r66
            double r31 = r4.getEntry(r3)
            goto L_0x0cb3
        L_0x0caf:
            r4 = r66
            r31 = r7
        L_0x0cb3:
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.newPoint
            double r40 = r11.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.upperDifference
            double r53 = r11.getEntry(r3)
            int r11 = (r40 > r53 ? 1 : (r40 == r53 ? 0 : -1))
            r40 = r0
            if (r11 != 0) goto L_0x0ccc
            double r0 = r4.getEntry(r3)
            double r0 = -r0
            r31 = r0
        L_0x0ccc:
            int r0 = (r31 > r7 ? 1 : (r31 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0d05
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.modelSecondDerivativesValues
            int r1 = r3 * r3
            int r1 = r1 + r3
            int r1 = r1 / 2
            double r0 = r0.getEntry(r1)
            r11 = r30
        L_0x0cdd:
            if (r11 >= r10) goto L_0x0cf8
            r43 = r2
            org.apache.commons.math3.linear.Array2DRowRealMatrix r2 = r13.interpolationPoints
            double r53 = r2.getEntry(r11, r3)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r13.modelSecondDerivativesParameters
            double r56 = r2.getEntry(r11)
            double r53 = r53 * r53
            double r56 = r56 * r53
            double r0 = r0 + r56
            int r11 = r11 + 1
            r2 = r43
            goto L_0x0cdd
        L_0x0cf8:
            r43 = r2
            double r0 = r0 * r60
            double r0 = r0 * r70
            double r31 = r31 + r0
            int r0 = (r31 > r7 ? 1 : (r31 == r7 ? 0 : -1))
            if (r0 >= 0) goto L_0x0d07
            goto L_0x0d16
        L_0x0d05:
            r43 = r2
        L_0x0d07:
            int r3 = r3 + 1
            r66 = r4
            r0 = r40
            r2 = r43
            goto L_0x0c96
        L_0x0d10:
            r40 = r0
            r43 = r2
            r4 = r66
        L_0x0d16:
            r8 = r13
            r30 = r43
            r0 = r51
            r2 = r55
            r31 = r74
            r11 = r93
            r13 = r4
            r3 = r14
            r42 = r36
            r36 = r38
            r15 = r73
            goto L_0x0c8a
        L_0x0d2b:
            r51 = r1
            r43 = r8
            r4 = r66
            r94 = r5
            r5 = r38
            r38 = r36
            r36 = r94
            int r0 = r11 + 1
            r11 = r0
            r81 = r36
            r0 = r51
        L_0x0d40:
            printState(r32)
            r2 = 4562254508917369340(0x3f50624dd2f1a9fc, double:0.001)
            double r2 = r2 * r20
            int r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0fa3
            r2 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            double r2 = r2 * r20
            r31 = r0
            r0 = r30
            r7 = 0
        L_0x0d58:
            r36 = -4620693217682128896(0xbfe0000000000000, double:-0.5)
            if (r0 >= r10) goto L_0x0dfb
            org.apache.commons.math3.linear.ArrayRealVector r1 = r13.modelSecondDerivativesParameters
            double r51 = r1.getEntry(r0)
            double r7 = r7 + r51
            double r36 = r36 * r20
            r51 = r5
            r1 = r30
            r5 = r36
        L_0x0d6c:
            if (r1 >= r9) goto L_0x0d87
            r54 = r7
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r13.interpolationPoints
            double r7 = r7.getEntry(r0, r1)
            r79 = r14
            org.apache.commons.math3.linear.ArrayRealVector r14 = r13.trustRegionCenterOffset
            double r14 = r14.getEntry(r1)
            double r7 = r7 * r14
            double r5 = r5 + r7
            int r1 = r1 + 1
            r7 = r54
            r14 = r79
            goto L_0x0d6c
        L_0x0d87:
            r54 = r7
            r79 = r14
            r12.setEntry(r0, r5)
            double r7 = r5 * r60
            double r7 = r2 - r7
            r1 = r30
        L_0x0d94:
            if (r1 >= r9) goto L_0x0df1
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r13.bMatrix
            double r14 = r14.getEntry(r0, r1)
            r4.setEntry(r1, r14)
            org.apache.commons.math3.linear.ArrayRealVector r14 = r13.lagrangeValuesAtNewPoint
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r13.interpolationPoints
            double r36 = r15.getEntry(r0, r1)
            double r36 = r36 * r5
            org.apache.commons.math3.linear.ArrayRealVector r15 = r13.trustRegionCenterOffset
            double r58 = r15.getEntry(r1)
            double r58 = r58 * r7
            r62 = r5
            double r5 = r36 + r58
            r14.setEntry(r1, r5)
            int r5 = r10 + r1
            r6 = r30
        L_0x0dbc:
            if (r6 > r1) goto L_0x0dea
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r13.bMatrix
            double r36 = r14.getEntry(r5, r6)
            double r58 = r4.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r13.lagrangeValuesAtNewPoint
            double r64 = r15.getEntry(r6)
            double r58 = r58 * r64
            double r36 = r36 + r58
            org.apache.commons.math3.linear.ArrayRealVector r15 = r13.lagrangeValuesAtNewPoint
            double r58 = r15.getEntry(r1)
            double r64 = r4.getEntry(r6)
            double r58 = r58 * r64
            r64 = r7
            double r7 = r36 + r58
            r14.setEntry(r5, r6, r7)
            int r6 = r6 + 1
            r7 = r64
            goto L_0x0dbc
        L_0x0dea:
            r64 = r7
            int r1 = r1 + 1
            r5 = r62
            goto L_0x0d94
        L_0x0df1:
            int r0 = r0 + 1
            r5 = r51
            r7 = r54
            r14 = r79
            goto L_0x0d58
        L_0x0dfb:
            r51 = r5
            r79 = r14
            r0 = r30
            r1 = r93
        L_0x0e03:
            if (r0 >= r1) goto L_0x0ed3
            r76 = r1
            r1 = r30
            r5 = 0
            r14 = 0
        L_0x0e0d:
            if (r1 >= r10) goto L_0x0e3a
            r18 = r11
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r13.zMatrix
            double r20 = r11.getEntry(r1, r0)
            double r5 = r5 + r20
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.lagrangeValuesAtNewPoint
            double r20 = r12.getEntry(r1)
            r54 = r5
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r13.zMatrix
            double r5 = r5.getEntry(r1, r0)
            double r5 = r5 * r20
            r11.setEntry(r1, r5)
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.lagrangeValuesAtNewPoint
            double r5 = r5.getEntry(r1)
            double r14 = r14 + r5
            int r1 = r1 + 1
            r11 = r18
            r5 = r54
            goto L_0x0e0d
        L_0x0e3a:
            r18 = r11
            r1 = r30
        L_0x0e3e:
            if (r1 >= r9) goto L_0x0e9e
            double r20 = r2 * r5
            double r54 = r14 * r60
            double r20 = r20 - r54
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trustRegionCenterOffset
            double r54 = r11.getEntry(r1)
            double r20 = r20 * r54
            r54 = r2
            r2 = r20
            r11 = r30
        L_0x0e54:
            if (r11 >= r10) goto L_0x0e70
            r20 = r5
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.lagrangeValuesAtNewPoint
            double r5 = r5.getEntry(r11)
            r72 = r12
            org.apache.commons.math3.linear.Array2DRowRealMatrix r12 = r13.interpolationPoints
            double r58 = r12.getEntry(r11, r1)
            double r5 = r5 * r58
            double r2 = r2 + r5
            int r11 = r11 + 1
            r5 = r20
            r12 = r72
            goto L_0x0e54
        L_0x0e70:
            r20 = r5
            r72 = r12
            r4.setEntry(r1, r2)
            r5 = r30
        L_0x0e79:
            if (r5 >= r10) goto L_0x0e93
            org.apache.commons.math3.linear.Array2DRowRealMatrix r6 = r13.bMatrix
            double r11 = r6.getEntry(r5, r1)
            r58 = r14
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r13.zMatrix
            double r14 = r14.getEntry(r5, r0)
            double r14 = r14 * r2
            double r11 = r11 + r14
            r6.setEntry(r5, r1, r11)
            int r5 = r5 + 1
            r14 = r58
            goto L_0x0e79
        L_0x0e93:
            r58 = r14
            int r1 = r1 + 1
            r5 = r20
            r2 = r54
            r12 = r72
            goto L_0x0e3e
        L_0x0e9e:
            r54 = r2
            r72 = r12
            r1 = r30
        L_0x0ea4:
            if (r1 >= r9) goto L_0x0ec7
            int r2 = r1 + r10
            double r5 = r4.getEntry(r1)
            r3 = r30
        L_0x0eae:
            if (r3 > r1) goto L_0x0ec4
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r13.bMatrix
            double r14 = r11.getEntry(r2, r3)
            double r20 = r4.getEntry(r3)
            double r20 = r20 * r5
            double r14 = r14 + r20
            r11.setEntry(r2, r3, r14)
            int r3 = r3 + 1
            goto L_0x0eae
        L_0x0ec4:
            int r1 = r1 + 1
            goto L_0x0ea4
        L_0x0ec7:
            int r0 = r0 + 1
            r11 = r18
            r2 = r54
            r12 = r72
            r1 = r76
            goto L_0x0e03
        L_0x0ed3:
            r76 = r1
            r18 = r11
            r72 = r12
            r0 = r30
            r1 = r0
        L_0x0edc:
            if (r0 >= r9) goto L_0x0f50
            double r2 = r7 * r36
            org.apache.commons.math3.linear.ArrayRealVector r5 = r13.trustRegionCenterOffset
            double r5 = r5.getEntry(r0)
            double r2 = r2 * r5
            r4.setEntry(r0, r2)
            r2 = r30
        L_0x0eec:
            if (r2 >= r10) goto L_0x0f16
            double r5 = r4.getEntry(r0)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.modelSecondDerivativesParameters
            double r11 = r3.getEntry(r2)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r13.interpolationPoints
            double r14 = r3.getEntry(r2, r0)
            double r11 = r11 * r14
            double r5 = r5 + r11
            r4.setEntry(r0, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r13.interpolationPoints
            double r5 = r3.getEntry(r2, r0)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trustRegionCenterOffset
            double r11 = r11.getEntry(r0)
            double r5 = r5 - r11
            r3.setEntry(r2, r0, r5)
            int r2 = r2 + 1
            goto L_0x0eec
        L_0x0f16:
            r2 = r30
        L_0x0f18:
            if (r2 > r0) goto L_0x0f4d
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.modelSecondDerivativesValues
            double r5 = r3.getEntry(r1)
            double r11 = r4.getEntry(r2)
            org.apache.commons.math3.linear.ArrayRealVector r14 = r13.trustRegionCenterOffset
            double r14 = r14.getEntry(r0)
            double r11 = r11 * r14
            double r5 = r5 + r11
            org.apache.commons.math3.linear.ArrayRealVector r11 = r13.trustRegionCenterOffset
            double r11 = r11.getEntry(r2)
            double r14 = r4.getEntry(r0)
            double r11 = r11 * r14
            double r5 = r5 + r11
            r3.setEntry(r1, r5)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r3 = r13.bMatrix
            int r5 = r10 + r2
            int r6 = r10 + r0
            double r11 = r3.getEntry(r6, r2)
            r3.setEntry(r5, r0, r11)
            int r1 = r1 + 1
            int r2 = r2 + 1
            goto L_0x0f18
        L_0x0f4d:
            int r0 = r0 + 1
            goto L_0x0edc
        L_0x0f50:
            r7 = r30
        L_0x0f52:
            if (r7 >= r9) goto L_0x0f9e
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.originShift
            double r1 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.trustRegionCenterOffset
            double r5 = r3.getEntry(r7)
            double r1 = r1 + r5
            r0.setEntry(r7, r1)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.newPoint
            double r1 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.trustRegionCenterOffset
            double r5 = r3.getEntry(r7)
            double r1 = r1 - r5
            r0.setEntry(r7, r1)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.lowerDifference
            double r1 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.trustRegionCenterOffset
            double r5 = r3.getEntry(r7)
            double r1 = r1 - r5
            r0.setEntry(r7, r1)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.upperDifference
            double r1 = r0.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r13.trustRegionCenterOffset
            double r5 = r3.getEntry(r7)
            double r1 = r1 - r5
            r0.setEntry(r7, r1)
            org.apache.commons.math3.linear.ArrayRealVector r0 = r13.trustRegionCenterOffset
            r1 = 0
            r0.setEntry(r7, r1)
            int r7 = r7 + 1
            goto L_0x0f52
        L_0x0f9e:
            r1 = 0
            r20 = r1
            goto L_0x0fb1
        L_0x0fa3:
            r31 = r0
            r51 = r5
            r18 = r11
            r72 = r12
            r79 = r14
            r76 = r93
            r1 = 0
        L_0x0fb1:
            if (r18 != 0) goto L_0x0fc1
            r8 = r13
            r0 = r31
            r12 = r35
            r36 = r38
            r30 = r43
            r38 = r51
            r2 = r53
            goto L_0x0fce
        L_0x0fc1:
            r8 = r13
            r0 = r31
            r12 = r35
            r36 = r38
            r30 = r43
            r38 = r51
            r2 = r57
        L_0x0fce:
            r5 = r70
            r14 = r72
            r15 = r73
            r31 = r74
            r11 = r76
            r42 = r81
            r13 = r4
            r35 = r18
            r3 = r79
            goto L_0x007d
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.bobyqb(double[], double[]):double");
    }

    /* JADX WARNING: Removed duplicated region for block: B:146:0x04f8  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x051b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double[] altmov(int r51, double r52) {
        /*
            r50 = this;
            r0 = r50
            r1 = r51
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.currentBest
            int r2 = r2.getDimension()
            int r3 = r0.numberOfInterpolationPoints
            org.apache.commons.math3.linear.ArrayRealVector r4 = new org.apache.commons.math3.linear.ArrayRealVector
            r4.<init>((int) r2)
            org.apache.commons.math3.linear.ArrayRealVector r5 = new org.apache.commons.math3.linear.ArrayRealVector
            r5.<init>((int) r3)
            org.apache.commons.math3.linear.ArrayRealVector r6 = new org.apache.commons.math3.linear.ArrayRealVector
            r6.<init>((int) r2)
            org.apache.commons.math3.linear.ArrayRealVector r7 = new org.apache.commons.math3.linear.ArrayRealVector
            r7.<init>((int) r2)
            r9 = 0
        L_0x0024:
            r10 = 0
            if (r9 >= r3) goto L_0x002e
            r5.setEntry(r9, r10)
            int r9 = r9 + 1
            goto L_0x0024
        L_0x002e:
            int r9 = r3 - r2
            r12 = 1
            int r9 = r9 - r12
            r13 = 0
        L_0x0033:
            if (r13 >= r9) goto L_0x005b
            org.apache.commons.math3.linear.Array2DRowRealMatrix r14 = r0.zMatrix
            double r14 = r14.getEntry(r1, r13)
            r8 = 0
        L_0x003c:
            if (r8 >= r3) goto L_0x0055
            double r16 = r5.getEntry(r8)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r12 = r0.zMatrix
            double r19 = r12.getEntry(r8, r13)
            double r19 = r19 * r14
            double r10 = r16 + r19
            r5.setEntry(r8, r10)
            int r8 = r8 + 1
            r10 = 0
            r12 = 1
            goto L_0x003c
        L_0x0055:
            int r13 = r13 + 1
            r10 = 0
            r12 = 1
            goto L_0x0033
        L_0x005b:
            double r8 = r5.getEntry(r1)
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r12 = r8 * r10
            r14 = 0
        L_0x0064:
            if (r14 >= r2) goto L_0x0074
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r0.bMatrix
            double r10 = r15.getEntry(r1, r14)
            r4.setEntry(r14, r10)
            int r14 = r14 + 1
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            goto L_0x0064
        L_0x0074:
            r10 = 0
        L_0x0075:
            if (r10 >= r3) goto L_0x00bb
            r11 = 0
            r14 = 0
        L_0x007a:
            if (r11 >= r2) goto L_0x0093
            r19 = r7
            org.apache.commons.math3.linear.Array2DRowRealMatrix r7 = r0.interpolationPoints
            double r23 = r7.getEntry(r10, r11)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.trustRegionCenterOffset
            double r25 = r7.getEntry(r11)
            double r23 = r23 * r25
            double r14 = r14 + r23
            int r11 = r11 + 1
            r7 = r19
            goto L_0x007a
        L_0x0093:
            r19 = r7
            double r23 = r5.getEntry(r10)
            double r14 = r14 * r23
            r7 = 0
        L_0x009c:
            if (r7 >= r2) goto L_0x00b6
            double r23 = r4.getEntry(r7)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.interpolationPoints
            double r25 = r11.getEntry(r10, r7)
            double r25 = r25 * r14
            r27 = r14
            double r14 = r23 + r25
            r4.setEntry(r7, r14)
            int r7 = r7 + 1
            r14 = r27
            goto L_0x009c
        L_0x00b6:
            int r10 = r10 + 1
            r7 = r19
            goto L_0x0075
        L_0x00bb:
            r19 = r7
            r10 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            r27 = r10
            r7 = 0
            r14 = 0
            r15 = 0
            r23 = 0
            r25 = 0
        L_0x00c8:
            if (r7 >= r3) goto L_0x0296
            int r10 = r0.trustRegionCenterInterpolationPointIndex
            if (r7 != r10) goto L_0x00da
            r35 = r3
            r39 = r4
            r20 = r5
            r37 = r6
            r33 = r8
            goto L_0x0288
        L_0x00da:
            r10 = 0
            r27 = 0
            r31 = 0
        L_0x00df:
            if (r10 >= r2) goto L_0x00fe
            org.apache.commons.math3.linear.Array2DRowRealMatrix r11 = r0.interpolationPoints
            double r33 = r11.getEntry(r7, r10)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r35 = r11.getEntry(r10)
            double r33 = r33 - r35
            double r35 = r4.getEntry(r10)
            double r35 = r35 * r33
            double r31 = r31 + r35
            double r33 = r33 * r33
            double r27 = r27 + r33
            int r10 = r10 + 1
            goto L_0x00df
        L_0x00fe:
            double r10 = org.apache.commons.math3.util.FastMath.sqrt(r27)
            double r10 = r52 / r10
            r33 = r8
            double r8 = -r10
            r20 = r5
            r37 = r6
            r35 = r8
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = org.apache.commons.math3.util.FastMath.min((double) r8, (double) r10)
            r8 = r35
            r36 = 0
            r38 = 0
            r35 = r3
            r3 = 0
        L_0x011c:
            if (r3 >= r2) goto L_0x01ea
            r39 = r4
            org.apache.commons.math3.linear.Array2DRowRealMatrix r4 = r0.interpolationPoints
            double r40 = r4.getEntry(r7, r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r42 = r4.getEntry(r3)
            double r40 = r40 - r42
            r21 = 0
            int r4 = (r40 > r21 ? 1 : (r40 == r21 ? 0 : -1))
            if (r4 <= 0) goto L_0x018a
            double r42 = r8 * r40
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r44 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r46 = r4.getEntry(r3)
            double r44 = r44 - r46
            int r4 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r4 >= 0) goto L_0x015d
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r8 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r42 = r4.getEntry(r3)
            double r8 = r8 - r42
            double r8 = r8 / r40
            int r4 = -r3
            r18 = 1
            int r36 = r4 + -1
        L_0x015d:
            double r42 = r10 * r40
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r44 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r46 = r4.getEntry(r3)
            double r44 = r44 - r46
            int r4 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r4 <= 0) goto L_0x01e4
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r10 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r42 = r4.getEntry(r3)
            double r10 = r10 - r42
            double r10 = r10 / r40
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r10)
            int r4 = r3 + 1
        L_0x0187:
            r38 = r4
            goto L_0x01e4
        L_0x018a:
            r21 = 0
            int r4 = (r40 > r21 ? 1 : (r40 == r21 ? 0 : -1))
            if (r4 >= 0) goto L_0x01e4
            double r42 = r8 * r40
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r44 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r46 = r4.getEntry(r3)
            double r44 = r44 - r46
            int r4 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r4 <= 0) goto L_0x01b6
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r8 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r42 = r4.getEntry(r3)
            double r8 = r8 - r42
            double r8 = r8 / r40
            int r36 = r3 + 1
        L_0x01b6:
            double r42 = r10 * r40
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r44 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r46 = r4.getEntry(r3)
            double r44 = r44 - r46
            int r4 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r4 >= 0) goto L_0x01e4
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r10 = r4.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r42 = r4.getEntry(r3)
            double r10 = r10 - r42
            double r10 = r10 / r40
            double r10 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r10)
            int r4 = -r3
            r18 = 1
            int r4 = r4 + -1
            goto L_0x0187
        L_0x01e4:
            int r3 = r3 + 1
            r4 = r39
            goto L_0x011c
        L_0x01ea:
            r39 = r4
            if (r7 != r1) goto L_0x0234
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r31 - r3
            double r3 = r8 * r5
            double r40 = r31 - r3
            double r40 = r40 * r8
            double r42 = r10 * r5
            double r44 = r31 - r42
            double r44 = r44 * r10
            double r46 = org.apache.commons.math3.util.FastMath.abs((double) r44)
            double r48 = org.apache.commons.math3.util.FastMath.abs((double) r40)
            int r46 = (r46 > r48 ? 1 : (r46 == r48 ? 0 : -1))
            if (r46 <= 0) goto L_0x020f
            r8 = r10
            r36 = r38
            r40 = r44
        L_0x020f:
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r31 = r31 * r10
            double r3 = r31 - r3
            double r10 = r31 - r42
            double r3 = r3 * r10
            r10 = 0
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 >= 0) goto L_0x0268
            double r3 = r31 * r31
            double r3 = r3 / r5
            double r10 = org.apache.commons.math3.util.FastMath.abs((double) r3)
            double r42 = org.apache.commons.math3.util.FastMath.abs((double) r40)
            int r10 = (r10 > r42 ? 1 : (r10 == r42 ? 0 : -1))
            if (r10 <= 0) goto L_0x0268
            double r8 = r31 / r5
            r40 = r3
            r36 = 0
            goto L_0x0268
        L_0x0234:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r5 = r3 - r8
            double r5 = r5 * r8
            double r40 = r3 - r10
            double r40 = r40 * r10
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r40)
            double r42 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            int r3 = (r3 > r42 ? 1 : (r3 == r42 ? 0 : -1))
            if (r3 <= 0) goto L_0x024e
            r8 = r10
            r36 = r38
            r5 = r40
        L_0x024e:
            r3 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r10 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
            if (r10 <= 0) goto L_0x0264
            double r3 = org.apache.commons.math3.util.FastMath.abs((double) r5)
            r10 = 4598175219545276416(0x3fd0000000000000, double:0.25)
            int r3 = (r3 > r10 ? 1 : (r3 == r10 ? 0 : -1))
            if (r3 >= 0) goto L_0x0264
            r5 = r10
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r36 = 0
            goto L_0x0265
        L_0x0264:
            r10 = r8
        L_0x0265:
            double r40 = r5 * r31
            r8 = r10
        L_0x0268:
            r3 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r10 = r3 - r8
            double r10 = r10 * r8
            double r10 = r10 * r27
            double r40 = r40 * r40
            double r3 = r12 * r10
            double r3 = r3 * r10
            double r3 = r40 + r3
            double r40 = r40 * r3
            int r3 = (r40 > r25 ? 1 : (r40 == r25 ? 0 : -1))
            if (r3 <= 0) goto L_0x0286
            r14 = r7
            r23 = r8
            r27 = r23
            r15 = r36
            r25 = r40
            goto L_0x0288
        L_0x0286:
            r27 = r8
        L_0x0288:
            int r7 = r7 + 1
            r5 = r20
            r8 = r33
            r3 = r35
            r6 = r37
            r4 = r39
            goto L_0x00c8
        L_0x0296:
            r35 = r3
            r39 = r4
            r20 = r5
            r37 = r6
            r33 = r8
            r1 = 0
        L_0x02a1:
            if (r1 >= r2) goto L_0x02d5
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trustRegionCenterOffset
            double r3 = r3.getEntry(r1)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r5 = r0.interpolationPoints
            double r5 = r5.getEntry(r14, r1)
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.trustRegionCenterOffset
            double r7 = r7.getEntry(r1)
            double r5 = r5 - r7
            double r5 = r5 * r23
            double r3 = r3 + r5
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.lowerDifference
            double r6 = r6.getEntry(r1)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.upperDifference
            double r8 = r8.getEntry(r1)
            double r3 = org.apache.commons.math3.util.FastMath.min((double) r8, (double) r3)
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r6, (double) r3)
            r5.setEntry(r1, r3)
            int r1 = r1 + 1
            goto L_0x02a1
        L_0x02d5:
            if (r15 >= 0) goto L_0x02e6
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.newPoint
            int r3 = -r15
            r4 = 1
            int r3 = r3 - r4
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.lowerDifference
            double r5 = r5.getEntry(r3)
            r1.setEntry(r3, r5)
            goto L_0x02e7
        L_0x02e6:
            r4 = 1
        L_0x02e7:
            if (r15 <= 0) goto L_0x02f5
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.newPoint
            int r15 = r15 - r4
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.upperDifference
            double r3 = r3.getEntry(r15)
            r1.setEntry(r15, r3)
        L_0x02f5:
            double r3 = r52 + r52
            r10 = r27
            r1 = 0
            r5 = 0
        L_0x02fc:
            r7 = 0
            r8 = 0
        L_0x02ff:
            if (r7 >= r2) goto L_0x0350
            r12 = r39
            double r13 = r12.getEntry(r7)
            r23 = r10
            r15 = r37
            r10 = 0
            r15.setEntry(r7, r10)
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trustRegionCenterOffset
            double r10 = r10.getEntry(r7)
            r25 = r5
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.lowerDifference
            double r5 = r5.getEntry(r7)
            double r10 = r10 - r5
            double r5 = org.apache.commons.math3.util.FastMath.min((double) r10, (double) r13)
            r10 = 0
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 > 0) goto L_0x0340
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trustRegionCenterOffset
            double r5 = r5.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.upperDifference
            double r10 = r10.getEntry(r7)
            double r5 = r5 - r10
            double r5 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r13)
            r10 = 0
            int r5 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r5 >= 0) goto L_0x0345
        L_0x0340:
            r15.setEntry(r7, r3)
            double r13 = r13 * r13
            double r8 = r8 + r13
        L_0x0345:
            int r7 = r7 + 1
            r39 = r12
            r37 = r15
            r10 = r23
            r5 = r25
            goto L_0x02ff
        L_0x0350:
            r25 = r5
            r23 = r10
            r15 = r37
            r12 = r39
            r5 = 0
            int r7 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            r10 = 2
            if (r7 != 0) goto L_0x0368
            double[] r1 = new double[r10]
            r2 = 0
            r1[r2] = r33
            r2 = 1
            r1[r2] = r5
            return r1
        L_0x0368:
            double r13 = r52 * r52
            double r13 = r13 - r5
            int r7 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x03cd
            double r13 = r13 / r8
            double r5 = org.apache.commons.math3.util.FastMath.sqrt(r13)
            r7 = 0
        L_0x0375:
            if (r7 >= r2) goto L_0x03cf
            double r8 = r15.getEntry(r7)
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x03ca
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r8 = r8.getEntry(r7)
            double r13 = r12.getEntry(r7)
            double r13 = r13 * r5
            double r8 = r8 - r13
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.lowerDifference
            double r13 = r11.getEntry(r7)
            int r11 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r11 > 0) goto L_0x03a9
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.lowerDifference
            double r8 = r8.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r13 = r11.getEntry(r7)
            double r8 = r8 - r13
            r15.setEntry(r7, r8)
            r15.getEntry(r7)
            goto L_0x03ca
        L_0x03a9:
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.upperDifference
            double r13 = r11.getEntry(r7)
            int r8 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r8 < 0) goto L_0x03c7
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.upperDifference
            double r8 = r8.getEntry(r7)
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trustRegionCenterOffset
            double r13 = r11.getEntry(r7)
            double r8 = r8 - r13
            r15.setEntry(r7, r8)
            r15.getEntry(r7)
            goto L_0x03ca
        L_0x03c7:
            r12.getEntry(r7)
        L_0x03ca:
            int r7 = r7 + 1
            goto L_0x0375
        L_0x03cd:
            r5 = r23
        L_0x03cf:
            r7 = 0
            r8 = 0
        L_0x03d2:
            if (r7 >= r2) goto L_0x0455
            double r13 = r12.getEntry(r7)
            double r23 = r15.getEntry(r7)
            int r11 = (r23 > r3 ? 1 : (r23 == r3 ? 0 : -1))
            if (r11 != 0) goto L_0x0411
            double r10 = -r5
            double r10 = r10 * r13
            r15.setEntry(r7, r10)
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.upperDifference
            double r10 = r10.getEntry(r7)
            r23 = r3
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trustRegionCenterOffset
            double r3 = r3.getEntry(r7)
            double r27 = r15.getEntry(r7)
            double r3 = r3 + r27
            double r3 = org.apache.commons.math3.util.FastMath.min((double) r10, (double) r3)
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.lowerDifference
            r27 = r5
            double r5 = r11.getEntry(r7)
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r5, (double) r3)
            r10.setEntry(r7, r3)
            r21 = 0
            goto L_0x0446
        L_0x0411:
            r23 = r3
            r27 = r5
            double r3 = r15.getEntry(r7)
            r21 = 0
            int r3 = (r3 > r21 ? 1 : (r3 == r21 ? 0 : -1))
            if (r3 != 0) goto L_0x042b
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r4 = r4.getEntry(r7)
            r3.setEntry(r7, r4)
            goto L_0x0446
        L_0x042b:
            int r3 = (r13 > r21 ? 1 : (r13 == r21 ? 0 : -1))
            if (r3 <= 0) goto L_0x043b
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.lowerDifference
            double r4 = r4.getEntry(r7)
            r3.setEntry(r7, r4)
            goto L_0x0446
        L_0x043b:
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.upperDifference
            double r4 = r4.getEntry(r7)
            r3.setEntry(r7, r4)
        L_0x0446:
            double r3 = r15.getEntry(r7)
            double r13 = r13 * r3
            double r8 = r8 + r13
            int r7 = r7 + 1
            r3 = r23
            r5 = r27
            r10 = 2
            goto L_0x03d2
        L_0x0455:
            r23 = r3
            r27 = r5
            r21 = 0
            r4 = r21
            r6 = r35
            r3 = 0
        L_0x0460:
            if (r3 >= r6) goto L_0x0483
            r10 = r21
            r7 = 0
        L_0x0465:
            if (r7 >= r2) goto L_0x0477
            org.apache.commons.math3.linear.Array2DRowRealMatrix r13 = r0.interpolationPoints
            double r13 = r13.getEntry(r3, r7)
            double r31 = r15.getEntry(r7)
            double r13 = r13 * r31
            double r10 = r10 + r13
            int r7 = r7 + 1
            goto L_0x0465
        L_0x0477:
            r7 = r20
            double r13 = r7.getEntry(r3)
            double r13 = r13 * r10
            double r13 = r13 * r10
            double r4 = r4 + r13
            int r3 = r3 + 1
            goto L_0x0460
        L_0x0483:
            r7 = r20
            r3 = 1
            if (r1 != r3) goto L_0x0489
            double r4 = -r4
        L_0x0489:
            double r10 = -r8
            int r3 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r3 <= 0) goto L_0x04e9
            r13 = 4611686018427387904(0x4000000000000000, double:2.0)
            double r13 = org.apache.commons.math3.util.FastMath.sqrt(r13)
            r29 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r13 + r29
            double r13 = r13 * r10
            int r3 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r3 >= 0) goto L_0x04e0
            double r10 = r10 / r4
            r3 = 0
        L_0x049f:
            if (r3 >= r2) goto L_0x04d5
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trustRegionCenterOffset
            double r4 = r4.getEntry(r3)
            double r13 = r15.getEntry(r3)
            double r13 = r13 * r10
            double r4 = r4 + r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.alternativeNewPoint
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.lowerDifference
            r35 = r6
            r20 = r7
            double r6 = r14.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r14 = r0.upperDifference
            r37 = r15
            double r14 = r14.getEntry(r3)
            double r4 = org.apache.commons.math3.util.FastMath.min((double) r14, (double) r4)
            double r4 = org.apache.commons.math3.util.FastMath.max((double) r6, (double) r4)
            r13.setEntry(r3, r4)
            int r3 = r3 + 1
            r7 = r20
            r6 = r35
            r15 = r37
            goto L_0x049f
        L_0x04d5:
            r35 = r6
            r20 = r7
            r37 = r15
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r8 = r8 * r6
            double r8 = r8 * r10
            goto L_0x04f5
        L_0x04e0:
            r35 = r6
            r20 = r7
            r37 = r15
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            goto L_0x04f3
        L_0x04e9:
            r35 = r6
            r20 = r7
            r37 = r15
            r6 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r29 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x04f3:
            double r4 = r4 * r6
            double r8 = r8 + r4
        L_0x04f5:
            double r8 = r8 * r8
            if (r1 != 0) goto L_0x051b
            r1 = 0
        L_0x04f9:
            if (r1 >= r2) goto L_0x0511
            double r3 = r12.getEntry(r1)
            double r3 = -r3
            r12.setEntry(r1, r3)
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.alternativeNewPoint
            double r3 = r3.getEntry(r1)
            r5 = r19
            r5.setEntry(r1, r3)
            int r1 = r1 + 1
            goto L_0x04f9
        L_0x0511:
            r5 = r8
            r39 = r12
            r3 = r23
            r10 = r27
            r1 = 1
            goto L_0x02fc
        L_0x051b:
            r5 = r19
            int r1 = (r25 > r8 ? 1 : (r25 == r8 ? 0 : -1))
            if (r1 <= 0) goto L_0x0533
            r1 = 0
        L_0x0522:
            if (r1 >= r2) goto L_0x0530
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.alternativeNewPoint
            double r6 = r5.getEntry(r1)
            r3.setEntry(r1, r6)
            int r1 = r1 + 1
            goto L_0x0522
        L_0x0530:
            r5 = r25
            goto L_0x0534
        L_0x0533:
            r5 = r8
        L_0x0534:
            r1 = 2
            double[] r1 = new double[r1]
            r2 = 0
            r1[r2] = r33
            r2 = 1
            r1[r2] = r5
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.altmov(int, double):double[]");
    }

    private void prelim(double[] dArr, double[] dArr2) {
        double d;
        double d2;
        int i;
        double d3;
        double d4;
        int i2;
        long j;
        int i3;
        int i4;
        char c2;
        double d5;
        int i5;
        double d6;
        int i6;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i7 = this.numberOfInterpolationPoints;
        int rowDimension = this.bMatrix.getRowDimension();
        double d7 = this.initialTrustRegionRadius;
        double d8 = d7 * d7;
        double d9 = 1.0d / d8;
        int i8 = dimension + 1;
        for (int i9 = 0; i9 < dimension; i9++) {
            this.originShift.setEntry(i9, this.currentBest.getEntry(i9));
            for (int i10 = 0; i10 < i7; i10++) {
                this.interpolationPoints.setEntry(i10, i9, 0.0d);
            }
            for (int i11 = 0; i11 < rowDimension; i11++) {
                this.bMatrix.setEntry(i11, i9, 0.0d);
            }
        }
        int i12 = (dimension * i8) / 2;
        for (int i13 = 0; i13 < i12; i13++) {
            this.modelSecondDerivativesValues.setEntry(i13, 0.0d);
        }
        for (int i14 = 0; i14 < i7; i14++) {
            this.modelSecondDerivativesParameters.setEntry(i14, 0.0d);
            int i15 = i7 - i8;
            for (int i16 = 0; i16 < i15; i16++) {
                this.zMatrix.setEntry(i14, i16, 0.0d);
            }
        }
        double d10 = Double.NaN;
        int i17 = 0;
        int i18 = 0;
        while (true) {
            int evaluations = getEvaluations();
            int i19 = evaluations - dimension;
            int i20 = evaluations - 1;
            int i21 = i19 - 1;
            int i22 = dimension * 2;
            if (evaluations <= i22) {
                if (evaluations < 1 || evaluations > dimension) {
                    int i23 = i17;
                    i6 = i19;
                    if (evaluations > dimension) {
                        int i24 = i6;
                        double entry = this.interpolationPoints.getEntry(i24, i21);
                        int i25 = i18;
                        d = d9;
                        double d11 = -this.initialTrustRegionRadius;
                        if (this.lowerDifference.getEntry(i21) == 0.0d) {
                            i = i24;
                            d11 = FastMath.min(this.initialTrustRegionRadius * TWO, this.upperDifference.getEntry(i21));
                        } else {
                            i = i24;
                        }
                        if (this.upperDifference.getEntry(i21) == 0.0d) {
                            d11 = FastMath.max(this.initialTrustRegionRadius * -2.0d, this.lowerDifference.getEntry(i21));
                        }
                        this.interpolationPoints.setEntry(evaluations, i21, d11);
                        d3 = entry;
                        double d12 = d11;
                        i18 = i25;
                        i2 = i23;
                        d2 = d8;
                        d4 = d12;
                    } else {
                        int i26 = i18;
                        d = d9;
                        i2 = i23;
                        d3 = 0.0d;
                    }
                } else {
                    i6 = i19;
                    d3 = this.initialTrustRegionRadius;
                    int i27 = i17;
                    if (this.upperDifference.getEntry(i20) == 0.0d) {
                        d3 = -d3;
                    }
                    this.interpolationPoints.setEntry(evaluations, i20, d3);
                    d = d9;
                    i2 = i27;
                }
                d2 = d8;
                d4 = 0.0d;
            } else {
                d = d9;
                i = i19;
                int i28 = (evaluations - i8) / dimension;
                i18 = (evaluations - (i28 * dimension)) - dimension;
                int i29 = i28 + i18;
                if (i29 > dimension) {
                    int i30 = i18;
                    i18 = i29 - dimension;
                    i29 = i30;
                }
                int i31 = i2 - 1;
                int i32 = i18 - 1;
                Array2DRowRealMatrix array2DRowRealMatrix = this.interpolationPoints;
                d2 = d8;
                array2DRowRealMatrix.setEntry(evaluations, i31, array2DRowRealMatrix.getEntry(i2, i31));
                Array2DRowRealMatrix array2DRowRealMatrix2 = this.interpolationPoints;
                array2DRowRealMatrix2.setEntry(evaluations, i32, array2DRowRealMatrix2.getEntry(i18, i32));
                d4 = 0.0d;
                d3 = 0.0d;
            }
            int i33 = 0;
            while (i33 < dimension) {
                int i34 = i2;
                double d13 = d4;
                int i35 = dimension;
                int i36 = i7;
                this.currentBest.setEntry(i33, FastMath.min(FastMath.max(dArr[i33], this.originShift.getEntry(i33) + this.interpolationPoints.getEntry(evaluations, i33)), dArr2[i33]));
                if (this.interpolationPoints.getEntry(evaluations, i33) == this.lowerDifference.getEntry(i33)) {
                    this.currentBest.setEntry(i33, dArr[i33]);
                }
                if (this.interpolationPoints.getEntry(evaluations, i33) == this.upperDifference.getEntry(i33)) {
                    this.currentBest.setEntry(i33, dArr2[i33]);
                }
                i33++;
                dimension = i35;
                i2 = i34;
                d4 = d13;
                i7 = i36;
            }
            int i37 = i7;
            int i38 = i2;
            double d14 = d4;
            int i39 = dimension;
            double computeObjectiveValue = computeObjectiveValue(this.currentBest.toArray());
            if (!this.isMinimize) {
                computeObjectiveValue = -computeObjectiveValue;
            }
            int evaluations2 = getEvaluations();
            this.fAtInterpolationPoints.setEntry(evaluations, computeObjectiveValue);
            if (evaluations2 == 1) {
                this.trustRegionCenterInterpolationPointIndex = 0;
                d10 = computeObjectiveValue;
            } else if (computeObjectiveValue < this.fAtInterpolationPoints.getEntry(this.trustRegionCenterInterpolationPointIndex)) {
                this.trustRegionCenterInterpolationPointIndex = evaluations;
            }
            if (evaluations2 > i22 + 1) {
                i4 = i39;
                j = 0;
                d5 = d;
                this.zMatrix.setEntry(0, i21, d5);
                this.zMatrix.setEntry(evaluations, i21, d5);
                double d15 = -d5;
                i3 = i38;
                this.zMatrix.setEntry(i3, i21, d15);
                this.zMatrix.setEntry(i18, i21, d15);
                int i40 = i3 - 1;
                c2 = 2;
                this.modelSecondDerivativesValues.setEntry((((i3 * i40) / 2) + i18) - 1, (((d10 - this.fAtInterpolationPoints.getEntry(i3)) - this.fAtInterpolationPoints.getEntry(i18)) + computeObjectiveValue) / (this.interpolationPoints.getEntry(evaluations, i40) * this.interpolationPoints.getEntry(evaluations, i18 - 1)));
            } else if (evaluations2 < 2 || evaluations2 > i8) {
                int i41 = i37;
                if (evaluations2 >= i39 + 2) {
                    double d16 = (computeObjectiveValue - d10) / d14;
                    double d17 = d14 - d3;
                    this.modelSecondDerivativesValues.setEntry((((i + 1) * i) / 2) - 1, ((d16 - this.gradientAtTrustRegionCenter.getEntry(i21)) * TWO) / d17);
                    ArrayRealVector arrayRealVector = this.gradientAtTrustRegionCenter;
                    arrayRealVector.setEntry(i21, ((arrayRealVector.getEntry(i21) * d14) - (d16 * d3)) / d17);
                    double d18 = d3 * d14;
                    j = 0;
                    if (d18 < 0.0d) {
                        i5 = i;
                        if (computeObjectiveValue < this.fAtInterpolationPoints.getEntry(i5)) {
                            ArrayRealVector arrayRealVector2 = this.fAtInterpolationPoints;
                            i37 = i41;
                            i4 = i39;
                            arrayRealVector2.setEntry(evaluations, arrayRealVector2.getEntry(i5));
                            this.fAtInterpolationPoints.setEntry(i5, computeObjectiveValue);
                            if (this.trustRegionCenterInterpolationPointIndex == evaluations) {
                                this.trustRegionCenterInterpolationPointIndex = i5;
                            }
                            d6 = d14;
                            this.interpolationPoints.setEntry(i5, i21, d6);
                            this.interpolationPoints.setEntry(evaluations, i21, d3);
                            this.bMatrix.setEntry(0, i21, (-(d3 + d6)) / d18);
                            this.bMatrix.setEntry(evaluations, i21, -0.5d / this.interpolationPoints.getEntry(i5, i21));
                            Array2DRowRealMatrix array2DRowRealMatrix3 = this.bMatrix;
                            array2DRowRealMatrix3.setEntry(i5, i21, (-array2DRowRealMatrix3.getEntry(0, i21)) - this.bMatrix.getEntry(evaluations, i21));
                            this.zMatrix.setEntry(0, i21, FastMath.sqrt(TWO) / d18);
                            this.zMatrix.setEntry(evaluations, i21, FastMath.sqrt(HALF) / d2);
                            Array2DRowRealMatrix array2DRowRealMatrix4 = this.zMatrix;
                            array2DRowRealMatrix4.setEntry(i5, i21, (-array2DRowRealMatrix4.getEntry(0, i21)) - this.zMatrix.getEntry(evaluations, i21));
                        } else {
                            i37 = i41;
                            i4 = i39;
                        }
                    } else {
                        i37 = i41;
                        i4 = i39;
                        i5 = i;
                    }
                    d6 = d14;
                    this.bMatrix.setEntry(0, i21, (-(d3 + d6)) / d18);
                    this.bMatrix.setEntry(evaluations, i21, -0.5d / this.interpolationPoints.getEntry(i5, i21));
                    Array2DRowRealMatrix array2DRowRealMatrix32 = this.bMatrix;
                    array2DRowRealMatrix32.setEntry(i5, i21, (-array2DRowRealMatrix32.getEntry(0, i21)) - this.bMatrix.getEntry(evaluations, i21));
                    this.zMatrix.setEntry(0, i21, FastMath.sqrt(TWO) / d18);
                    this.zMatrix.setEntry(evaluations, i21, FastMath.sqrt(HALF) / d2);
                    Array2DRowRealMatrix array2DRowRealMatrix42 = this.zMatrix;
                    array2DRowRealMatrix42.setEntry(i5, i21, (-array2DRowRealMatrix42.getEntry(0, i21)) - this.zMatrix.getEntry(evaluations, i21));
                } else {
                    i37 = i41;
                    i4 = i39;
                    j = 0;
                }
                i3 = i38;
                d5 = d;
                c2 = 2;
            } else {
                this.gradientAtTrustRegionCenter.setEntry(i20, (computeObjectiveValue - d10) / d3);
                int i42 = i37;
                if (i42 < evaluations2 + i39) {
                    double d19 = 1.0d / d3;
                    this.bMatrix.setEntry(0, i20, -d19);
                    this.bMatrix.setEntry(evaluations, i20, d19);
                    this.bMatrix.setEntry(i42 + i20, i20, -0.5d * d2);
                }
                i37 = i42;
                i4 = i39;
                i3 = i38;
                d5 = d;
                c2 = 2;
                j = 0;
            }
            i7 = i37;
            if (getEvaluations() < i7) {
                dimension = i4;
                i17 = i3;
                long j2 = j;
                char c3 = c2;
                d9 = d5;
                d8 = d2;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:216:0x0787  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x07a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private double[] trsbox(double r81, org.apache.commons.math3.linear.ArrayRealVector r83, org.apache.commons.math3.linear.ArrayRealVector r84, org.apache.commons.math3.linear.ArrayRealVector r85, org.apache.commons.math3.linear.ArrayRealVector r86, org.apache.commons.math3.linear.ArrayRealVector r87) {
        /*
            r80 = this;
            r0 = r80
            r1 = r83
            r2 = r84
            r3 = r85
            r4 = r86
            r5 = r87
            printMethod()
            org.apache.commons.math3.linear.ArrayRealVector r6 = r0.currentBest
            int r6 = r6.getDimension()
            int r7 = r0.numberOfInterpolationPoints
            r9 = 0
            r10 = 0
        L_0x0019:
            r11 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r13 = 0
            if (r9 >= r6) goto L_0x007a
            r2.setEntry(r9, r13)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r15 = r15.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.lowerDifference
            double r20 = r8.getEntry(r9)
            int r8 = (r15 > r20 ? 1 : (r15 == r20 ? 0 : -1))
            if (r8 > 0) goto L_0x0040
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r15 = r8.getEntry(r9)
            int r8 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r8 < 0) goto L_0x005f
            r2.setEntry(r9, r11)
            goto L_0x005f
        L_0x0040:
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trustRegionCenterOffset
            double r11 = r8.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.upperDifference
            double r15 = r8.getEntry(r9)
            int r8 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r8 < 0) goto L_0x005f
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r11 = r8.getEntry(r9)
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 > 0) goto L_0x005f
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r9, r11)
        L_0x005f:
            double r11 = r2.getEntry(r9)
            int r8 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r8 == 0) goto L_0x0069
            int r10 = r10 + 1
        L_0x0069:
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.trialStepPoint
            r8.setEntry(r9, r13)
            org.apache.commons.math3.linear.ArrayRealVector r8 = r0.gradientAtTrustRegionCenter
            double r11 = r8.getEntry(r9)
            r1.setEntry(r9, r11)
            int r9 = r9 + 1
            goto L_0x0019
        L_0x007a:
            double r8 = r81 * r81
            r15 = 20
            r22 = r13
            r24 = r22
            r26 = r24
            r28 = r26
            r30 = r28
            r32 = r30
            r34 = r32
            r39 = r34
            r41 = r39
            r43 = r41
            r45 = r43
            r48 = r45
            r51 = r48
            r12 = r15
            r11 = 0
            r36 = 0
            r37 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r47 = -1
            r50 = 0
        L_0x00a2:
            r53 = 4547007122018943789(0x3f1a36e2eb1c432d, double:1.0E-4)
            r13 = 30
            if (r12 == r15) goto L_0x0814
            if (r12 == r13) goto L_0x07f9
            r57 = 4576918229304087675(0x3f847ae147ae147b, double:0.01)
            r13 = 100
            r15 = 90
            r14 = 50
            r60 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            if (r12 == r14) goto L_0x0568
            if (r12 == r15) goto L_0x054e
            if (r12 == r13) goto L_0x0532
            r15 = 120(0x78, float:1.68E-43)
            if (r12 == r15) goto L_0x03d3
            r13 = 150(0x96, float:2.1E-43)
            r14 = 1
            if (r12 == r13) goto L_0x01a5
            r15 = 190(0xbe, float:2.66E-43)
            if (r12 == r15) goto L_0x01a0
            r15 = 210(0xd2, float:2.94E-43)
            if (r12 != r15) goto L_0x0191
            printState(r15)
            r12 = 0
            r14 = 0
        L_0x00d6:
            if (r12 >= r6) goto L_0x0121
            r15 = r14
            r13 = 0
            r4.setEntry(r12, r13)
            r14 = r15
            r13 = 0
        L_0x00e0:
            if (r13 > r12) goto L_0x011a
            if (r13 >= r12) goto L_0x00fc
            double r53 = r4.getEntry(r12)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.modelSecondDerivativesValues
            double r57 = r15.getEntry(r14)
            double r59 = r3.getEntry(r13)
            double r57 = r57 * r59
            r63 = r8
            double r8 = r53 + r57
            r4.setEntry(r12, r8)
            goto L_0x00fe
        L_0x00fc:
            r63 = r8
        L_0x00fe:
            double r8 = r4.getEntry(r13)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.modelSecondDerivativesValues
            double r53 = r15.getEntry(r14)
            double r57 = r3.getEntry(r12)
            double r53 = r53 * r57
            double r8 = r8 + r53
            r4.setEntry(r13, r8)
            int r14 = r14 + 1
            int r13 = r13 + 1
            r8 = r63
            goto L_0x00e0
        L_0x011a:
            r63 = r8
            int r12 = r12 + 1
            r13 = 150(0x96, float:2.1E-43)
            goto L_0x00d6
        L_0x0121:
            r63 = r8
            org.apache.commons.math3.linear.Array2DRowRealMatrix r8 = r0.interpolationPoints
            org.apache.commons.math3.linear.RealVector r8 = r8.operate((org.apache.commons.math3.linear.RealVector) r3)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.modelSecondDerivativesParameters
            org.apache.commons.math3.linear.RealVector r8 = r8.ebeMultiply(r9)
            r9 = 0
        L_0x0130:
            if (r9 >= r7) goto L_0x015c
            org.apache.commons.math3.linear.ArrayRealVector r12 = r0.modelSecondDerivativesParameters
            double r12 = r12.getEntry(r9)
            r14 = 0
            int r12 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r12 == 0) goto L_0x0159
            r12 = 0
        L_0x013f:
            if (r12 >= r6) goto L_0x0159
            double r13 = r4.getEntry(r12)
            double r53 = r8.getEntry(r9)
            org.apache.commons.math3.linear.Array2DRowRealMatrix r15 = r0.interpolationPoints
            double r57 = r15.getEntry(r9, r12)
            double r53 = r53 * r57
            double r13 = r13 + r53
            r4.setEntry(r12, r13)
            int r12 = r12 + 1
            goto L_0x013f
        L_0x0159:
            int r9 = r9 + 1
            goto L_0x0130
        L_0x015c:
            r12 = r37
            r14 = 0
            int r8 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r8 == 0) goto L_0x016c
            r37 = r12
            r8 = r63
            r12 = 50
            goto L_0x079c
        L_0x016c:
            r8 = r36
            if (r11 <= r8) goto L_0x017a
            r36 = r8
            r37 = r12
            r8 = r63
            r12 = 150(0x96, float:2.1E-43)
            goto L_0x079c
        L_0x017a:
            r9 = 0
        L_0x017b:
            if (r9 >= r6) goto L_0x0187
            double r14 = r4.getEntry(r9)
            r5.setEntry(r9, r14)
            int r9 = r9 + 1
            goto L_0x017b
        L_0x0187:
            r36 = r8
            r37 = r12
            r8 = r63
        L_0x018d:
            r12 = 120(0x78, float:1.68E-43)
            goto L_0x079c
        L_0x0191:
            org.apache.commons.math3.exception.MathIllegalStateException r1 = new org.apache.commons.math3.exception.MathIllegalStateException
            org.apache.commons.math3.exception.util.LocalizedFormats r2 = org.apache.commons.math3.exception.util.LocalizedFormats.SIMPLE_MESSAGE
            java.lang.Object[] r3 = new java.lang.Object[r14]
            java.lang.String r4 = "trsbox"
            r5 = 0
            r3[r5] = r4
            r1.<init>(r2, r3)
            throw r1
        L_0x01a0:
            r3 = r15
            r72 = r37
            goto L_0x0354
        L_0x01a5:
            r63 = r8
            r9 = r13
            r8 = r36
            r12 = r37
            printState(r9)
            r9 = 0
            r36 = 0
            r53 = 0
            r65 = 0
        L_0x01b6:
            if (r9 >= r6) goto L_0x01ed
            double r67 = r2.getEntry(r9)
            r55 = 0
            int r15 = (r67 > r55 ? 1 : (r67 == r55 ? 0 : -1))
            if (r15 != 0) goto L_0x01ea
            double r67 = r3.getEntry(r9)
            double r69 = r4.getEntry(r9)
            double r67 = r67 * r69
            double r36 = r36 + r67
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r67 = r15.getEntry(r9)
            double r69 = r4.getEntry(r9)
            double r67 = r67 * r69
            double r53 = r53 + r67
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r67 = r15.getEntry(r9)
            double r69 = r5.getEntry(r9)
            double r67 = r67 * r69
            double r65 = r65 + r67
        L_0x01ea:
            int r9 = r9 + 1
            goto L_0x01b6
        L_0x01ed:
            r67 = 4625478292286210048(0x4031000000000000, double:17.0)
            double r67 = r67 * r32
            r69 = 4614162998222441677(0x4008cccccccccccd, double:3.1)
            double r14 = r67 + r69
            int r9 = (int) r14
            r14 = 0
            r15 = -1
            r67 = 0
            r69 = 0
        L_0x01ff:
            if (r14 >= r9) goto L_0x024b
            r38 = r7
            r62 = r8
            double r7 = (double) r14
            double r7 = r7 * r32
            r71 = r11
            r72 = r12
            double r11 = (double) r9
            double r39 = r7 / r11
            double r7 = r39 + r39
            double r11 = r39 * r39
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r11 = r11 + r17
            double r7 = r7 / r11
            double r11 = r39 * r65
            double r11 = r11 - r53
            double r11 = r11 - r53
            double r11 = r11 * r39
            double r11 = r36 + r11
            double r74 = r39 * r26
            double r74 = r74 - r45
            double r76 = r7 * r60
            double r76 = r76 * r11
            double r74 = r74 - r76
            double r7 = r7 * r74
            int r11 = (r7 > r67 ? 1 : (r7 == r67 ? 0 : -1))
            if (r11 <= 0) goto L_0x0238
            r67 = r7
            r15 = r14
            r41 = r69
            goto L_0x023e
        L_0x0238:
            int r11 = r15 + 1
            if (r14 != r11) goto L_0x023e
            r43 = r7
        L_0x023e:
            int r14 = r14 + 1
            r69 = r7
            r7 = r38
            r8 = r62
            r11 = r71
            r12 = r72
            goto L_0x01ff
        L_0x024b:
            r38 = r7
            r62 = r8
            r71 = r11
            r72 = r12
            if (r15 >= 0) goto L_0x025f
        L_0x0255:
            r7 = r38
            r36 = r62
            r8 = r63
            r11 = r71
            goto L_0x0798
        L_0x025f:
            if (r15 >= r9) goto L_0x0275
            double r7 = r43 - r41
            double r67 = r67 + r67
            double r67 = r67 - r41
            double r67 = r67 - r43
            double r7 = r7 / r67
            double r11 = (double) r15
            double r7 = r7 * r60
            double r11 = r11 + r7
            double r11 = r11 * r32
            double r7 = (double) r9
            double r11 = r11 / r7
            r39 = r11
        L_0x0275:
            double r7 = r39 * r39
            r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r13 = r11 - r7
            double r7 = r7 + r11
            double r13 = r13 / r7
            double r17 = r39 + r39
            double r17 = r17 / r7
            double r65 = r65 * r39
            double r65 = r65 - r53
            double r65 = r65 - r53
            double r65 = r65 * r39
            double r36 = r36 + r65
            double r7 = r39 * r26
            double r7 = r7 - r45
            double r60 = r60 * r17
            double r60 = r60 * r36
            double r7 = r7 - r60
            double r7 = r7 * r17
            r36 = 0
            int r53 = (r7 > r36 ? 1 : (r7 == r36 ? 0 : -1))
            if (r53 > 0) goto L_0x029e
            goto L_0x0255
        L_0x029e:
            r11 = 0
            r22 = 0
            r26 = 0
        L_0x02a3:
            if (r11 >= r6) goto L_0x030d
            double r53 = r1.getEntry(r11)
            r36 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r60 = r13 - r36
            double r65 = r5.getEntry(r11)
            double r60 = r60 * r65
            double r53 = r53 + r60
            double r60 = r4.getEntry(r11)
            double r60 = r60 * r17
            r65 = r9
            r12 = r10
            double r9 = r53 + r60
            r1.setEntry(r11, r9)
            double r9 = r2.getEntry(r11)
            r53 = 0
            int r9 = (r9 > r53 ? 1 : (r9 == r53 ? 0 : -1))
            if (r9 != 0) goto L_0x02f4
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.trialStepPoint
            double r53 = r9.getEntry(r11)
            double r53 = r53 * r13
            double r60 = r3.getEntry(r11)
            double r60 = r60 * r17
            double r2 = r53 + r60
            r9.setEntry(r11, r2)
            org.apache.commons.math3.linear.ArrayRealVector r2 = r0.trialStepPoint
            double r2 = r2.getEntry(r11)
            double r9 = r1.getEntry(r11)
            double r2 = r2 * r9
            double r26 = r26 + r2
            double r2 = r1.getEntry(r11)
            double r2 = r2 * r2
            double r22 = r22 + r2
        L_0x02f4:
            double r2 = r5.getEntry(r11)
            double r2 = r2 * r13
            double r9 = r4.getEntry(r11)
            double r9 = r9 * r17
            double r2 = r2 + r9
            r5.setEntry(r11, r2)
            int r11 = r11 + 1
            r2 = r84
            r3 = r85
            r10 = r12
            r9 = r65
            goto L_0x02a3
        L_0x030d:
            r65 = r9
            r12 = r10
            double r28 = r28 + r7
            r11 = r47
            if (r11 < 0) goto L_0x0335
            r2 = r65
            if (r15 != r2) goto L_0x0335
            int r10 = r12 + 1
            r2 = r84
            r13 = r48
            r2.setEntry(r11, r13)
            r3 = r85
            r47 = r11
            r7 = r38
            r36 = r62
            r8 = r63
            r11 = r71
            r37 = r72
            r12 = 100
            goto L_0x079c
        L_0x0335:
            r2 = r84
            r13 = r48
            double r57 = r57 * r28
            int r3 = (r7 > r57 ? 1 : (r7 == r57 ? 0 : -1))
            if (r3 <= 0) goto L_0x0352
            r3 = r85
            r47 = r11
            r10 = r12
            r48 = r13
            r7 = r38
            r36 = r62
            r8 = r63
            r11 = r71
            r37 = r72
            goto L_0x018d
        L_0x0352:
            r3 = 190(0xbe, float:2.66E-43)
        L_0x0354:
            printState(r3)
            r5 = 0
            r13 = 0
        L_0x035a:
            if (r5 >= r6) goto L_0x03c9
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.trustRegionCenterOffset
            double r3 = r1.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.trialStepPoint
            double r7 = r1.getEntry(r5)
            double r3 = r3 + r7
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.upperDifference
            double r7 = r1.getEntry(r5)
            double r3 = org.apache.commons.math3.util.FastMath.min((double) r3, (double) r7)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r7 = r0.lowerDifference
            double r7 = r7.getEntry(r5)
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r7)
            r1.setEntry(r5, r3)
            double r3 = r2.getEntry(r5)
            r7 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 != 0) goto L_0x0397
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.lowerDifference
            double r3 = r3.getEntry(r5)
            r1.setEntry(r5, r3)
        L_0x0397:
            double r3 = r2.getEntry(r5)
            r7 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r1 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r1 != 0) goto L_0x03ac
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.newPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.upperDifference
            double r3 = r3.getEntry(r5)
            r1.setEntry(r5, r3)
        L_0x03ac:
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.trialStepPoint
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.newPoint
            double r3 = r3.getEntry(r5)
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.trustRegionCenterOffset
            double r9 = r9.getEntry(r5)
            double r3 = r3 - r9
            r1.setEntry(r5, r3)
            org.apache.commons.math3.linear.ArrayRealVector r1 = r0.trialStepPoint
            double r3 = r1.getEntry(r5)
            double r3 = r3 * r3
            double r13 = r13 + r3
            int r5 = r5 + 1
            goto L_0x035a
        L_0x03c9:
            r1 = 2
            double[] r1 = new double[r1]
            r9 = 0
            r1[r9] = r13
            r2 = 1
            r1[r2] = r72
            return r1
        L_0x03d3:
            r63 = r8
            r12 = r10
            r71 = r11
            r10 = r15
            r62 = r36
            r72 = r37
            r11 = r47
            r13 = r48
            r3 = 190(0xbe, float:2.66E-43)
            r9 = 0
            r15 = 210(0xd2, float:2.94E-43)
            r38 = r7
            printState(r10)
            int r10 = r71 + 1
            double r16 = r22 * r24
            double r18 = r26 * r26
            double r16 = r16 - r18
            double r53 = r53 * r28
            double r53 = r53 * r28
            int r18 = (r16 > r53 ? 1 : (r16 == r53 ? 0 : -1))
            if (r18 > 0) goto L_0x0412
            r47 = r11
            r48 = r13
            r7 = r38
            r36 = r62
            r8 = r63
            r37 = r72
            r13 = 0
            r15 = 20
            r11 = r10
            r10 = r12
            r12 = r3
            r3 = r85
            goto L_0x00a2
        L_0x0412:
            double r7 = org.apache.commons.math3.util.FastMath.sqrt(r16)
            r3 = r9
        L_0x0417:
            if (r3 >= r6) goto L_0x044a
            double r16 = r2.getEntry(r3)
            r82 = r10
            r9 = 0
            int r11 = (r16 > r9 ? 1 : (r16 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x043f
            org.apache.commons.math3.linear.ArrayRealVector r11 = r0.trialStepPoint
            double r16 = r11.getEntry(r3)
            double r16 = r16 * r26
            double r32 = r1.getEntry(r3)
            double r32 = r32 * r24
            double r16 = r16 - r32
            double r9 = r16 / r7
            r11 = r85
            r11.setEntry(r3, r9)
            r9 = 0
            goto L_0x0444
        L_0x043f:
            r11 = r85
            r11.setEntry(r3, r9)
        L_0x0444:
            int r3 = r3 + 1
            r10 = r82
            r9 = 0
            goto L_0x0417
        L_0x044a:
            r11 = r85
            r82 = r10
            r9 = 0
            double r7 = -r7
            r48 = r13
            r3 = 0
            r32 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r47 = -1
        L_0x0458:
            if (r3 >= r6) goto L_0x051c
            double r13 = r2.getEntry(r3)
            int r13 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r13 != 0) goto L_0x0514
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.trustRegionCenterOffset
            double r9 = r9.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.trialStepPoint
            double r13 = r13.getEntry(r3)
            double r9 = r9 + r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.lowerDifference
            double r13 = r13.getEntry(r3)
            double r9 = r9 - r13
            org.apache.commons.math3.linear.ArrayRealVector r13 = r0.upperDifference
            double r13 = r13.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r17 = r15.getEntry(r3)
            double r13 = r13 - r17
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r17 = r15.getEntry(r3)
            double r13 = r13 - r17
            r17 = 0
            int r15 = (r9 > r17 ? 1 : (r9 == r17 ? 0 : -1))
            if (r15 > 0) goto L_0x049b
            int r10 = r12 + 1
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r2.setEntry(r3, r12)
            goto L_0x051d
        L_0x049b:
            int r15 = (r13 > r17 ? 1 : (r13 == r17 ? 0 : -1))
            if (r15 > 0) goto L_0x04a8
            int r10 = r12 + 1
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r3, r12)
            goto L_0x051d
        L_0x04a8:
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r36 = r15.getEntry(r3)
            double r45 = r11.getEntry(r3)
            double r36 = r36 * r36
            double r45 = r45 * r45
            double r36 = r36 + r45
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r45 = r15.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.lowerDifference
            double r53 = r15.getEntry(r3)
            double r45 = r45 - r53
            double r45 = r45 * r45
            double r45 = r36 - r45
            r53 = 0
            int r15 = (r45 > r53 ? 1 : (r45 == r53 ? 0 : -1))
            if (r15 <= 0) goto L_0x04e6
            double r45 = org.apache.commons.math3.util.FastMath.sqrt(r45)
            double r53 = r11.getEntry(r3)
            double r45 = r45 - r53
            double r53 = r32 * r45
            int r15 = (r53 > r9 ? 1 : (r53 == r9 ? 0 : -1))
            if (r15 <= 0) goto L_0x04e6
            double r32 = r9 / r45
            r47 = r3
            r48 = -4616189618054758400(0xbff0000000000000, double:-1.0)
        L_0x04e6:
            org.apache.commons.math3.linear.ArrayRealVector r9 = r0.upperDifference
            double r9 = r9.getEntry(r3)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r45 = r15.getEntry(r3)
            double r9 = r9 - r45
            double r9 = r9 * r9
            double r36 = r36 - r9
            r9 = 0
            int r15 = (r36 > r9 ? 1 : (r36 == r9 ? 0 : -1))
            if (r15 <= 0) goto L_0x0514
            double r9 = org.apache.commons.math3.util.FastMath.sqrt(r36)
            double r36 = r11.getEntry(r3)
            double r9 = r9 + r36
            double r36 = r32 * r9
            int r15 = (r36 > r13 ? 1 : (r36 == r13 ? 0 : -1))
            if (r15 <= 0) goto L_0x0514
            double r13 = r13 / r9
            r47 = r3
            r32 = r13
            r48 = 4607182418800017408(0x3ff0000000000000, double:1.0)
        L_0x0514:
            int r3 = r3 + 1
            r9 = 0
            r15 = 210(0xd2, float:2.94E-43)
            goto L_0x0458
        L_0x051c:
            r10 = r12
        L_0x051d:
            r45 = r7
            r3 = r11
            r7 = r38
            r36 = r62
            r8 = r63
            r37 = r72
            r12 = 210(0xd2, float:2.94E-43)
            r13 = 0
            r15 = 20
            r11 = r82
            goto L_0x00a2
        L_0x0532:
            r63 = r8
            r71 = r11
            r62 = r36
            r72 = r37
            r11 = r47
            r38 = r7
            r7 = r3
            r3 = r10
            r4 = r13
            r67 = r48
            r9 = r50
            r36 = r71
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r15 = -1
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x077f
        L_0x054e:
            r38 = r7
            r63 = r8
            r71 = r11
            r62 = r36
            r11 = r47
            r7 = r3
            r3 = r10
            r4 = r15
            r67 = r48
            r9 = r50
            r11 = r71
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r15 = -1
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L_0x0776
        L_0x0568:
            r63 = r8
            r12 = r10
            r71 = r11
            r9 = r14
            r62 = r36
            r72 = r37
            r11 = r47
            r13 = r48
            r8 = 210(0xd2, float:2.94E-43)
            r38 = r7
            r7 = r3
            r3 = 190(0xbe, float:2.66E-43)
            printState(r9)
            r53 = r63
            r9 = 0
            r36 = 0
            r47 = 0
        L_0x0587:
            if (r9 >= r6) goto L_0x05ba
            double r65 = r2.getEntry(r9)
            r55 = 0
            int r10 = (r65 > r55 ? 1 : (r65 == r55 ? 0 : -1))
            if (r10 != 0) goto L_0x05b7
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r65 = r10.getEntry(r9)
            double r65 = r65 * r65
            double r53 = r53 - r65
            double r65 = r7.getEntry(r9)
            org.apache.commons.math3.linear.ArrayRealVector r10 = r0.trialStepPoint
            double r67 = r10.getEntry(r9)
            double r65 = r65 * r67
            double r36 = r36 + r65
            double r65 = r7.getEntry(r9)
            double r67 = r4.getEntry(r9)
            double r65 = r65 * r67
            double r47 = r47 + r65
        L_0x05b7:
            int r9 = r9 + 1
            goto L_0x0587
        L_0x05ba:
            r9 = 0
            int r49 = (r53 > r9 ? 1 : (r53 == r9 ? 0 : -1))
            if (r49 > 0) goto L_0x05d4
            r3 = r7
            r47 = r11
            r48 = r13
            r7 = r38
            r36 = r62
            r11 = r71
            r37 = r72
            r13 = r9
            r10 = r12
            r12 = r15
            r8 = r63
            goto L_0x079e
        L_0x05d4:
            double r55 = r30 * r53
            double r65 = r36 * r36
            double r55 = r55 + r65
            double r55 = org.apache.commons.math3.util.FastMath.sqrt(r55)
            int r11 = (r36 > r9 ? 1 : (r36 == r9 ? 0 : -1))
            if (r11 >= 0) goto L_0x05e7
            double r55 = r55 - r36
            double r55 = r55 / r30
            goto L_0x05eb
        L_0x05e7:
            double r55 = r55 + r36
            double r55 = r53 / r55
        L_0x05eb:
            r3 = r55
            int r11 = (r47 > r9 ? 1 : (r47 == r9 ? 0 : -1))
            if (r11 <= 0) goto L_0x05f8
            double r8 = r22 / r47
            double r8 = org.apache.commons.math3.util.FastMath.min((double) r3, (double) r8)
            goto L_0x05f9
        L_0x05f8:
            r8 = r3
        L_0x05f9:
            r9 = r8
            r8 = 0
            r11 = -1
        L_0x05fc:
            if (r8 >= r6) goto L_0x0645
            double r36 = r7.getEntry(r8)
            r53 = 0
            int r36 = (r36 > r53 ? 1 : (r36 == r53 ? 0 : -1))
            if (r36 == 0) goto L_0x0640
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trustRegionCenterOffset
            double r55 = r15.getEntry(r8)
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.trialStepPoint
            double r65 = r15.getEntry(r8)
            double r65 = r55 + r65
            double r55 = r7.getEntry(r8)
            int r15 = (r55 > r53 ? 1 : (r55 == r53 ? 0 : -1))
            if (r15 <= 0) goto L_0x062b
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.upperDifference
            double r53 = r15.getEntry(r8)
            double r53 = r53 - r65
            double r65 = r7.getEntry(r8)
            goto L_0x0637
        L_0x062b:
            org.apache.commons.math3.linear.ArrayRealVector r15 = r0.lowerDifference
            double r53 = r15.getEntry(r8)
            double r53 = r53 - r65
            double r65 = r7.getEntry(r8)
        L_0x0637:
            double r53 = r53 / r65
            int r15 = (r53 > r9 ? 1 : (r53 == r9 ? 0 : -1))
            if (r15 >= 0) goto L_0x0640
            r11 = r8
            r9 = r53
        L_0x0640:
            int r8 = r8 + 1
            r15 = 90
            goto L_0x05fc
        L_0x0645:
            r53 = 0
            int r8 = (r9 > r53 ? 1 : (r9 == r53 ? 0 : -1))
            if (r8 <= 0) goto L_0x06c8
            int r8 = r71 + 1
            r65 = r3
            double r3 = r47 / r30
            r15 = -1
            if (r11 != r15) goto L_0x0668
            int r37 = (r3 > r53 ? 1 : (r3 == r53 ? 0 : -1))
            if (r37 <= 0) goto L_0x0668
            r67 = r13
            r13 = r72
            double r13 = org.apache.commons.math3.util.FastMath.min((double) r13, (double) r3)
            r20 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            int r37 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r37 != 0) goto L_0x066c
            r13 = r3
            goto L_0x066c
        L_0x0668:
            r67 = r13
            r13 = r72
        L_0x066c:
            r3 = 0
            r51 = 0
        L_0x066f:
            if (r3 >= r6) goto L_0x06ad
            double r53 = r1.getEntry(r3)
            r4 = r86
            double r69 = r4.getEntry(r3)
            double r69 = r69 * r9
            double r4 = r53 + r69
            r1.setEntry(r3, r4)
            double r4 = r2.getEntry(r3)
            r53 = 0
            int r4 = (r4 > r53 ? 1 : (r4 == r53 ? 0 : -1))
            if (r4 != 0) goto L_0x0693
            double r4 = r1.getEntry(r3)
            double r4 = r4 * r4
            double r51 = r51 + r4
        L_0x0693:
            org.apache.commons.math3.linear.ArrayRealVector r4 = r0.trialStepPoint
            double r53 = r4.getEntry(r3)
            double r69 = r7.getEntry(r3)
            double r69 = r69 * r9
            r71 = r13
            double r13 = r53 + r69
            r4.setEntry(r3, r13)
            int r3 = r3 + 1
            r5 = r87
            r13 = r71
            goto L_0x066f
        L_0x06ad:
            r71 = r13
            double r60 = r60 * r9
            double r60 = r60 * r47
            double r3 = r22 - r60
            double r3 = r3 * r9
            r13 = 0
            double r55 = org.apache.commons.math3.util.FastMath.max((double) r3, (double) r13)
            double r28 = r28 + r55
            r3 = r13
            r13 = r55
            r78 = r22
            r22 = r51
            r51 = r78
            goto L_0x06d6
        L_0x06c8:
            r65 = r3
            r67 = r13
            r3 = r53
            r13 = r72
            r15 = -1
            r8 = r71
            r71 = r13
            r13 = r3
        L_0x06d6:
            if (r11 < 0) goto L_0x0726
            int r10 = r12 + 1
            r12 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r2.setEntry(r11, r12)
            double r16 = r7.getEntry(r11)
            int r5 = (r16 > r3 ? 1 : (r16 == r3 ? 0 : -1))
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            if (r5 >= 0) goto L_0x06ec
            r2.setEntry(r11, r12)
        L_0x06ec:
            org.apache.commons.math3.linear.ArrayRealVector r5 = r0.trialStepPoint
            double r20 = r5.getEntry(r11)
            double r20 = r20 * r20
            double r20 = r63 - r20
            int r5 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r5 > 0) goto L_0x070c
            r4 = r86
            r5 = r87
            r3 = r7
            r47 = r11
            r7 = r38
            r36 = r62
            r48 = r67
            r37 = r71
            r12 = 190(0xbe, float:2.66E-43)
            goto L_0x071d
        L_0x070c:
            r4 = r86
            r5 = r87
            r3 = r7
            r47 = r11
            r7 = r38
            r36 = r62
            r48 = r67
            r37 = r71
            r12 = 20
        L_0x071d:
            r13 = 0
            r15 = 20
            r11 = r8
            r8 = r20
            goto L_0x00a2
        L_0x0726:
            r3 = r12
            r4 = r13
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r9 = (r9 > r65 ? 1 : (r9 == r65 ? 0 : -1))
            if (r9 >= 0) goto L_0x076f
            r9 = r50
            if (r8 != r9) goto L_0x0751
        L_0x0734:
            r4 = r86
            r5 = r87
            r10 = r3
            r3 = r7
            r50 = r9
            r47 = r11
            r7 = r38
            r36 = r62
            r48 = r67
            r37 = r71
            r12 = 190(0xbe, float:2.66E-43)
        L_0x0748:
            r13 = 0
            r15 = 20
            r11 = r8
            r8 = r63
            goto L_0x00a2
        L_0x0751:
            double r57 = r57 * r28
            int r4 = (r4 > r57 ? 1 : (r4 == r57 ? 0 : -1))
            if (r4 > 0) goto L_0x0758
            goto L_0x0734
        L_0x0758:
            double r34 = r22 / r51
            r4 = r86
            r5 = r87
            r10 = r3
            r3 = r7
            r50 = r9
            r47 = r11
            r7 = r38
            r36 = r62
            r48 = r67
            r37 = r71
            r12 = 30
            goto L_0x0748
        L_0x076f:
            r9 = r50
            r47 = r11
            r4 = 90
            r11 = r8
        L_0x0776:
            printState(r4)
            r36 = r11
            r4 = 100
            r72 = 0
        L_0x077f:
            printState(r4)
            int r4 = r6 + -1
            r10 = r3
            if (r10 < r4) goto L_0x07a2
            r4 = r86
            r5 = r87
            r3 = r7
            r50 = r9
            r11 = r36
            r7 = r38
            r36 = r62
            r8 = r63
            r48 = r67
        L_0x0798:
            r37 = r72
            r12 = 190(0xbe, float:2.66E-43)
        L_0x079c:
            r13 = 0
        L_0x079e:
            r15 = 20
            goto L_0x00a2
        L_0x07a2:
            r5 = 0
            r22 = 0
            r24 = 0
            r26 = 0
        L_0x07a9:
            if (r5 >= r6) goto L_0x07e5
            double r3 = r2.getEntry(r5)
            r20 = 0
            int r3 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x07dd
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trialStepPoint
            double r3 = r3.getEntry(r5)
            double r3 = r3 * r3
            double r24 = r24 + r3
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trialStepPoint
            double r3 = r3.getEntry(r5)
            double r20 = r1.getEntry(r5)
            double r3 = r3 * r20
            double r26 = r26 + r3
            double r3 = r1.getEntry(r5)
            double r3 = r3 * r3
            double r22 = r22 + r3
            org.apache.commons.math3.linear.ArrayRealVector r3 = r0.trialStepPoint
            double r3 = r3.getEntry(r5)
            r7.setEntry(r5, r3)
            goto L_0x07e2
        L_0x07dd:
            r3 = 0
            r7.setEntry(r5, r3)
        L_0x07e2:
            int r5 = r5 + 1
            goto L_0x07a9
        L_0x07e5:
            r4 = r86
            r5 = r87
            r3 = r7
            r50 = r9
            r11 = r36
            r7 = r38
            r8 = r63
            r48 = r67
            r37 = r72
            r12 = 210(0xd2, float:2.94E-43)
            goto L_0x079c
        L_0x07f9:
            r63 = r8
            r71 = r11
            r62 = r36
            r72 = r37
            r11 = r47
            r67 = r48
            r9 = r50
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r15 = -1
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r38 = r7
            r7 = r3
            r3 = 20
            r4 = 30
            goto L_0x0832
        L_0x0814:
            r63 = r8
            r71 = r11
            r62 = r36
            r72 = r37
            r11 = r47
            r67 = r48
            r9 = r50
            r12 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r17 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r38 = r7
            r7 = r3
            r3 = r15
            r15 = -1
            printState(r3)
            r4 = 30
            r34 = 0
        L_0x0832:
            printState(r4)
            r5 = 0
            r30 = 0
        L_0x0838:
            if (r5 >= r6) goto L_0x0870
            double r20 = r2.getEntry(r5)
            r3 = 0
            int r8 = (r20 > r3 ? 1 : (r20 == r3 ? 0 : -1))
            if (r8 == 0) goto L_0x0848
            r7.setEntry(r5, r3)
            goto L_0x0864
        L_0x0848:
            int r8 = (r34 > r3 ? 1 : (r34 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x0855
            double r3 = r1.getEntry(r5)
            double r3 = -r3
            r7.setEntry(r5, r3)
            goto L_0x0864
        L_0x0855:
            double r3 = r7.getEntry(r5)
            double r3 = r3 * r34
            double r20 = r1.getEntry(r5)
            double r3 = r3 - r20
            r7.setEntry(r5, r3)
        L_0x0864:
            double r3 = r7.getEntry(r5)
            double r3 = r3 * r3
            double r30 = r30 + r3
            int r5 = r5 + 1
            r3 = 20
            goto L_0x0838
        L_0x0870:
            r3 = 0
            int r5 = (r30 > r3 ? 1 : (r30 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x0892
            r5 = r87
            r13 = r3
            r3 = r7
            r50 = r9
        L_0x087c:
            r47 = r11
            r7 = r38
            r36 = r62
            r8 = r63
            r48 = r67
            r11 = r71
            r37 = r72
            r12 = 190(0xbe, float:2.66E-43)
        L_0x088c:
            r15 = 20
            r4 = r86
            goto L_0x00a2
        L_0x0892:
            int r5 = (r34 > r3 ? 1 : (r34 == r3 ? 0 : -1))
            if (r5 != 0) goto L_0x089d
            int r5 = r71 + r6
            int r50 = r5 - r10
            r22 = r30
            goto L_0x089f
        L_0x089d:
            r50 = r9
        L_0x089f:
            double r8 = r22 * r63
            double r53 = r53 * r28
            double r53 = r53 * r28
            int r5 = (r8 > r53 ? 1 : (r8 == r53 ? 0 : -1))
            if (r5 > 0) goto L_0x08ae
            r5 = r87
            r13 = r3
            r3 = r7
            goto L_0x087c
        L_0x08ae:
            r5 = r87
            r13 = r3
            r3 = r7
            r47 = r11
            r7 = r38
            r36 = r62
            r8 = r63
            r48 = r67
            r11 = r71
            r37 = r72
            r12 = 210(0xd2, float:2.94E-43)
            goto L_0x088c
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optimization.direct.BOBYQAOptimizer.trsbox(double, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector, org.apache.commons.math3.linear.ArrayRealVector):double[]");
    }

    private void update(double d, double d2, int i) {
        double d3;
        int i2 = i;
        printMethod();
        int dimension = this.currentBest.getDimension();
        int i3 = this.numberOfInterpolationPoints;
        int i4 = 1;
        int i5 = (i3 - dimension) - 1;
        ArrayRealVector arrayRealVector = new ArrayRealVector(i3 + dimension);
        int i6 = 0;
        double d4 = 0.0d;
        for (int i7 = 0; i7 < i3; i7++) {
            for (int i8 = 0; i8 < i5; i8++) {
                d4 = FastMath.max(d4, FastMath.abs(this.zMatrix.getEntry(i7, i8)));
            }
        }
        double d5 = d4 * 1.0E-20d;
        while (i4 < i5) {
            if (FastMath.abs(this.zMatrix.getEntry(i2, i4)) > d5) {
                double entry = this.zMatrix.getEntry(i2, 0);
                double entry2 = this.zMatrix.getEntry(i2, i4);
                double sqrt = FastMath.sqrt((entry * entry) + (entry2 * entry2));
                double entry3 = this.zMatrix.getEntry(i2, 0) / sqrt;
                double entry4 = this.zMatrix.getEntry(i2, i4) / sqrt;
                int i9 = 0;
                while (i9 < i3) {
                    double entry5 = (this.zMatrix.getEntry(i9, 0) * entry3) + (this.zMatrix.getEntry(i9, i4) * entry4);
                    Array2DRowRealMatrix array2DRowRealMatrix = this.zMatrix;
                    array2DRowRealMatrix.setEntry(i9, i4, (array2DRowRealMatrix.getEntry(i9, i4) * entry3) - (this.zMatrix.getEntry(i9, 0) * entry4));
                    this.zMatrix.setEntry(i9, 0, entry5);
                    i9++;
                    d5 = d5;
                }
            }
            this.zMatrix.setEntry(i2, i4, 0.0d);
            i4++;
            d5 = d5;
        }
        for (int i10 = 0; i10 < i3; i10++) {
            arrayRealVector.setEntry(i10, this.zMatrix.getEntry(i2, 0) * this.zMatrix.getEntry(i10, 0));
        }
        double entry6 = arrayRealVector.getEntry(i2);
        double entry7 = this.lagrangeValuesAtNewPoint.getEntry(i2);
        ArrayRealVector arrayRealVector2 = this.lagrangeValuesAtNewPoint;
        arrayRealVector2.setEntry(i2, arrayRealVector2.getEntry(i2) - 1.0d);
        double sqrt2 = FastMath.sqrt(d2);
        double d6 = entry7 / sqrt2;
        double entry8 = this.zMatrix.getEntry(i2, 0) / sqrt2;
        int i11 = 0;
        while (i11 < i3) {
            Array2DRowRealMatrix array2DRowRealMatrix2 = this.zMatrix;
            i6 = 0;
            array2DRowRealMatrix2.setEntry(i11, 0, (array2DRowRealMatrix2.getEntry(i11, i6) * d6) - (this.lagrangeValuesAtNewPoint.getEntry(i11) * entry8));
            i11++;
            d6 = d6;
        }
        int i12 = i6;
        while (i12 < dimension) {
            int i13 = i3 + i12;
            arrayRealVector.setEntry(i13, this.bMatrix.getEntry(i2, i12));
            double entry9 = ((this.lagrangeValuesAtNewPoint.getEntry(i13) * entry6) - (arrayRealVector.getEntry(i13) * entry7)) / d2;
            int i14 = i12;
            double entry10 = (((-d) * arrayRealVector.getEntry(i13)) - (this.lagrangeValuesAtNewPoint.getEntry(i13) * entry7)) / d2;
            int i15 = 0;
            while (i15 <= i13) {
                int i16 = dimension;
                Array2DRowRealMatrix array2DRowRealMatrix3 = this.bMatrix;
                double d7 = entry6;
                int i17 = i14;
                ArrayRealVector arrayRealVector3 = arrayRealVector;
                array2DRowRealMatrix3.setEntry(i15, i17, array2DRowRealMatrix3.getEntry(i15, i17) + (this.lagrangeValuesAtNewPoint.getEntry(i15) * entry9) + (arrayRealVector.getEntry(i15) * entry10));
                if (i15 >= i3) {
                    Array2DRowRealMatrix array2DRowRealMatrix4 = this.bMatrix;
                    d3 = entry7;
                    array2DRowRealMatrix4.setEntry(i13, i15 - i3, array2DRowRealMatrix4.getEntry(i15, i17));
                } else {
                    d3 = entry7;
                }
                i15++;
                arrayRealVector = arrayRealVector3;
                dimension = i16;
                entry7 = d3;
                i14 = i17;
                entry6 = d7;
            }
            int i18 = dimension;
            double d8 = entry6;
            double d9 = entry7;
            int i19 = i14;
            ArrayRealVector arrayRealVector4 = arrayRealVector;
            i12 = i19 + 1;
            i2 = i;
            entry6 = d8;
        }
    }

    private void setup(double[] dArr, double[] dArr2) {
        printMethod();
        int length = getStartPoint().length;
        if (length >= 2) {
            int i = length + 2;
            int i2 = length + 1;
            int[] iArr = {i, (i * i2) / 2};
            int i3 = this.numberOfInterpolationPoints;
            if (i3 < iArr[0] || i3 > iArr[1]) {
                throw new OutOfRangeException(LocalizedFormats.NUMBER_OF_INTERPOLATION_POINTS, Integer.valueOf(this.numberOfInterpolationPoints), Integer.valueOf(iArr[0]), Integer.valueOf(iArr[1]));
            }
            this.boundDifference = new double[length];
            double d = this.initialTrustRegionRadius * TWO;
            double d2 = Double.POSITIVE_INFINITY;
            for (int i4 = 0; i4 < length; i4++) {
                double[] dArr3 = this.boundDifference;
                double d3 = dArr2[i4] - dArr[i4];
                dArr3[i4] = d3;
                d2 = FastMath.min(d2, d3);
            }
            if (d2 < d) {
                this.initialTrustRegionRadius = d2 / 3.0d;
            }
            this.bMatrix = new Array2DRowRealMatrix(this.numberOfInterpolationPoints + length, length);
            int i5 = this.numberOfInterpolationPoints;
            this.zMatrix = new Array2DRowRealMatrix(i5, (i5 - length) - 1);
            this.interpolationPoints = new Array2DRowRealMatrix(this.numberOfInterpolationPoints, length);
            this.originShift = new ArrayRealVector(length);
            this.fAtInterpolationPoints = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.trustRegionCenterOffset = new ArrayRealVector(length);
            this.gradientAtTrustRegionCenter = new ArrayRealVector(length);
            this.lowerDifference = new ArrayRealVector(length);
            this.upperDifference = new ArrayRealVector(length);
            this.modelSecondDerivativesParameters = new ArrayRealVector(this.numberOfInterpolationPoints);
            this.newPoint = new ArrayRealVector(length);
            this.alternativeNewPoint = new ArrayRealVector(length);
            this.trialStepPoint = new ArrayRealVector(length);
            this.lagrangeValuesAtNewPoint = new ArrayRealVector(this.numberOfInterpolationPoints + length);
            this.modelSecondDerivativesValues = new ArrayRealVector((length * i2) / 2);
            return;
        }
        throw new NumberIsTooSmallException(Integer.valueOf(length), 2, true);
    }

    /* access modifiers changed from: private */
    public static String caller(int i) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[i];
        return stackTraceElement.getMethodName() + " (at line " + stackTraceElement.getLineNumber() + ")";
    }

    private static class PathIsExploredException extends RuntimeException {
        private static final String PATH_IS_EXPLORED = "If this exception is thrown, just remove it from the code";
        private static final long serialVersionUID = 745350979634801853L;

        PathIsExploredException() {
            super("If this exception is thrown, just remove it from the code " + BOBYQAOptimizer.caller(3));
        }
    }
}
