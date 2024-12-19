package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.TooManyEvaluationsException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.nonlinear.scalar.MultivariateOptimizer;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class CMAESOptimizer extends MultivariateOptimizer {
    private RealMatrix B;
    private RealMatrix BD;
    private RealMatrix C;
    private RealMatrix D;
    private double cc;
    private double ccov1;
    private double ccov1Sep;
    private double ccovmu;
    private double ccovmuSep;
    private final int checkFeasableCount;
    private double chiN;
    private double cs;
    private double damps;
    private RealMatrix diagC;
    private RealMatrix diagD;
    private int diagonalOnly;
    private int dimension;
    private double[] fitnessHistory;
    private final boolean generateStatistics;
    private int historySize;
    private double[] inputSigma;
    private final boolean isActiveCMA;
    /* access modifiers changed from: private */
    public boolean isMinimize = true;
    private int iterations;
    private int lambda;
    private double logMu2;
    private final int maxIterations;
    private int mu;
    private double mueff;
    private double normps;
    private RealMatrix pc;
    private RealMatrix ps;
    private final RandomGenerator random;
    private double sigma;
    private final List<RealMatrix> statisticsDHistory = new ArrayList();
    private final List<Double> statisticsFitnessHistory = new ArrayList();
    private final List<RealMatrix> statisticsMeanHistory = new ArrayList();
    private final List<Double> statisticsSigmaHistory = new ArrayList();
    private final double stopFitness;
    private double stopTolFun;
    private double stopTolHistFun;
    private double stopTolUpX;
    private double stopTolX;
    private RealMatrix weights;
    private RealMatrix xmean;

    public CMAESOptimizer(int i, double d, boolean z, int i2, int i3, RandomGenerator randomGenerator, boolean z2, ConvergenceChecker<PointValuePair> convergenceChecker) {
        super(convergenceChecker);
        this.maxIterations = i;
        this.stopFitness = d;
        this.isActiveCMA = z;
        this.diagonalOnly = i2;
        this.checkFeasableCount = i3;
        this.random = randomGenerator;
        this.generateStatistics = z2;
    }

    public List<Double> getStatisticsSigmaHistory() {
        return this.statisticsSigmaHistory;
    }

    public List<RealMatrix> getStatisticsMeanHistory() {
        return this.statisticsMeanHistory;
    }

    public List<Double> getStatisticsFitnessHistory() {
        return this.statisticsFitnessHistory;
    }

    public List<RealMatrix> getStatisticsDHistory() {
        return this.statisticsDHistory;
    }

    public static class Sigma implements OptimizationData {
        private final double[] sigma;

        public Sigma(double[] dArr) throws NotPositiveException {
            int i = 0;
            while (i < dArr.length) {
                if (dArr[i] >= 0.0d) {
                    i++;
                } else {
                    throw new NotPositiveException(Double.valueOf(dArr[i]));
                }
            }
            this.sigma = (double[]) dArr.clone();
        }

        public double[] getSigma() {
            return (double[]) this.sigma.clone();
        }
    }

    public static class PopulationSize implements OptimizationData {
        private final int lambda;

        public PopulationSize(int i) throws NotStrictlyPositiveException {
            if (i > 0) {
                this.lambda = i;
                return;
            }
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }

        public int getPopulationSize() {
            return this.lambda;
        }
    }

    public PointValuePair optimize(OptimizationData... optimizationDataArr) throws TooManyEvaluationsException, DimensionMismatchException {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x02d0, code lost:
        r6.sigma *= org.apache.commons.math3.util.FastMath.exp((r6.cs / r6.damps) + 0.2d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x02e0, code lost:
        r0 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x02e2, code lost:
        push(r6.fitnessHistory, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x02e9, code lost:
        if (r6.generateStatistics == false) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02eb, code lost:
        r6.statisticsSigmaHistory.add(java.lang.Double.valueOf(r6.sigma));
        r6.statisticsFitnessHistory.add(java.lang.Double.valueOf(r0));
        r6.statisticsMeanHistory.add(r6.xmean.transpose());
        r6.statisticsDHistory.add(r6.diagD.transpose().scalarMultiply(100000.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x031e, code lost:
        r6.iterations++;
        r9 = 1;
        r11 = r5;
        r14 = r15;
        r7 = r16;
        r12 = r17;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01fe, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0201, code lost:
        if (r7 >= r6.dimension) goto L_0x0213;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x020c, code lost:
        if ((r6.sigma * r0[r7]) <= r6.stopTolUpX) goto L_0x0210;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0210, code lost:
        r7 = r7 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0213, code lost:
        r7 = min(r6.fitnessHistory);
        r11 = max(r6.fitnessHistory);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0222, code lost:
        if (r6.iterations <= 2) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0231, code lost:
        if ((org.apache.commons.math3.util.FastMath.max(r11, r3) - org.apache.commons.math3.util.FastMath.min(r7, r1)) >= r6.stopTolFun) goto L_0x0234;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0239, code lost:
        if (r6.iterations <= r6.fitnessHistory.length) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0241, code lost:
        if ((r11 - r7) >= r6.stopTolHistFun) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0258, code lost:
        if ((max(r6.diagD) / min(r6.diagD)) <= 1.0E7d) goto L_0x025b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x025f, code lost:
        if (getConvergenceChecker() == null) goto L_0x0287;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0261, code lost:
        r4 = r10.getColumn(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x026a, code lost:
        if (r6.isMinimize == false) goto L_0x026e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x026c, code lost:
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x026e, code lost:
        r13 = -r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x026f, code lost:
        r0 = new org.apache.commons.math3.optim.PointValuePair(r4, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0272, code lost:
        if (r21 == null) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0280, code lost:
        if (getConvergenceChecker().converged(r6.iterations, r0, r21) == false) goto L_0x0285;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0285, code lost:
        r14 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0287, code lost:
        r14 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x02a4, code lost:
        if (r17 != r15[r23[(int) ((((double) r6.lambda) / 4.0d) + 0.1d)]]) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x02a6, code lost:
        r21 = r1;
        r15 = r14;
        r6.sigma *= org.apache.commons.math3.util.FastMath.exp((r6.cs / r6.damps) + 0.2d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x02b9, code lost:
        r21 = r1;
        r15 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x02bf, code lost:
        if (r6.iterations <= 2) goto L_0x02e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x02c1, code lost:
        r0 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x02ce, code lost:
        if ((org.apache.commons.math3.util.FastMath.max(r11, r0) - org.apache.commons.math3.util.FastMath.min(r7, r0)) != 0.0d) goto L_0x02e2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.apache.commons.math3.optim.PointValuePair doOptimize() {
        /*
            r24 = this;
            r6 = r24
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r0 = r24.getGoalType()
            org.apache.commons.math3.optim.nonlinear.scalar.GoalType r1 = org.apache.commons.math3.optim.nonlinear.scalar.GoalType.MINIMIZE
            boolean r0 = r0.equals(r1)
            r6.isMinimize = r0
            org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer$FitnessFunction r7 = new org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer$FitnessFunction
            r7.<init>()
            double[] r0 = r24.getStartPoint()
            int r1 = r0.length
            r6.dimension = r1
            r6.initializeCMA(r0)
            r8 = 0
            r6.iterations = r8
            org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer$ValuePenaltyPair r0 = r7.value(r0)
            double r1 = r0.value
            double r3 = r0.penalty
            double r1 = r1 + r3
            double[] r0 = r6.fitnessHistory
            push(r0, r1)
            org.apache.commons.math3.optim.PointValuePair r0 = new org.apache.commons.math3.optim.PointValuePair
            double[] r3 = r24.getStartPoint()
            boolean r4 = r6.isMinimize
            if (r4 == 0) goto L_0x003e
            r4 = r1
            goto L_0x003f
        L_0x003e:
            double r4 = -r1
        L_0x003f:
            r0.<init>(r3, r4)
            r9 = 1
            r6.iterations = r9
            r11 = r0
            r12 = r1
            r14 = 0
        L_0x0048:
            int r0 = r6.iterations
            int r1 = r6.maxIterations
            if (r0 > r1) goto L_0x032e
            r24.incrementIterationCount()
            int r0 = r6.dimension
            int r1 = r6.lambda
            org.apache.commons.math3.linear.RealMatrix r3 = r6.randn1(r0, r1)
            int r0 = r6.dimension
            int r1 = r6.lambda
            org.apache.commons.math3.linear.RealMatrix r0 = zeros(r0, r1)
            int r1 = r6.lambda
            double[] r15 = new double[r1]
            org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer$ValuePenaltyPair[] r2 = new org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.ValuePenaltyPair[r1]
            r4 = r8
        L_0x0068:
            int r5 = r6.lambda
            if (r4 >= r5) goto L_0x00ec
            r5 = r8
            r16 = 0
        L_0x006f:
            int r10 = r6.checkFeasableCount
            int r10 = r10 + r9
            if (r5 >= r10) goto L_0x00d0
            int r10 = r6.diagonalOnly
            if (r10 > 0) goto L_0x0092
            org.apache.commons.math3.linear.RealMatrix r10 = r6.xmean
            org.apache.commons.math3.linear.RealMatrix r9 = r6.BD
            org.apache.commons.math3.linear.RealMatrix r8 = r3.getColumnMatrix(r4)
            org.apache.commons.math3.linear.RealMatrix r8 = r9.multiply(r8)
            r9 = r11
            r17 = r12
            double r11 = r6.sigma
            org.apache.commons.math3.linear.RealMatrix r8 = r8.scalarMultiply(r11)
            org.apache.commons.math3.linear.RealMatrix r8 = r10.add(r8)
            goto L_0x00ab
        L_0x0092:
            r9 = r11
            r17 = r12
            org.apache.commons.math3.linear.RealMatrix r8 = r6.xmean
            org.apache.commons.math3.linear.RealMatrix r10 = r6.diagD
            org.apache.commons.math3.linear.RealMatrix r11 = r3.getColumnMatrix(r4)
            org.apache.commons.math3.linear.RealMatrix r10 = times(r10, r11)
            double r11 = r6.sigma
            org.apache.commons.math3.linear.RealMatrix r10 = r10.scalarMultiply(r11)
            org.apache.commons.math3.linear.RealMatrix r8 = r8.add(r10)
        L_0x00ab:
            int r10 = r6.checkFeasableCount
            if (r5 >= r10) goto L_0x00ce
            r10 = 0
            double[] r11 = r8.getColumn(r10)
            boolean r10 = r7.isFeasible(r11)
            if (r10 == 0) goto L_0x00bb
            goto L_0x00ce
        L_0x00bb:
            int r10 = r6.dimension
            double[] r10 = r6.randn(r10)
            r3.setColumn(r4, r10)
            int r5 = r5 + 1
            r16 = r8
            r11 = r9
            r12 = r17
            r8 = 0
            r9 = 1
            goto L_0x006f
        L_0x00ce:
            r5 = 0
            goto L_0x00d6
        L_0x00d0:
            r9 = r11
            r17 = r12
            r5 = r8
            r8 = r16
        L_0x00d6:
            copyColumn(r8, r5, r0, r4)
            double[] r5 = r0.getColumn(r4)     // Catch:{ TooManyEvaluationsException -> 0x032f }
            org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer$ValuePenaltyPair r5 = r7.value(r5)     // Catch:{ TooManyEvaluationsException -> 0x032f }
            r2[r4] = r5     // Catch:{ TooManyEvaluationsException -> 0x032f }
            int r4 = r4 + 1
            r11 = r9
            r12 = r17
            r8 = 0
            r9 = 1
            goto L_0x0068
        L_0x00ec:
            r9 = r11
            r17 = r12
            double r4 = r6.valueRange(r2)
            r8 = 0
        L_0x00f4:
            if (r8 >= r1) goto L_0x0109
            r10 = r2[r8]
            double r10 = r10.value
            r12 = r2[r8]
            double r12 = r12.penalty
            double r12 = r12 * r4
            double r10 = r10 + r12
            r15[r8] = r10
            int r8 = r8 + 1
            goto L_0x00f4
        L_0x0109:
            int[] r8 = r6.sortedIndices(r15)
            org.apache.commons.math3.linear.RealMatrix r5 = r6.xmean
            int r1 = r6.mu
            int[] r1 = org.apache.commons.math3.util.MathArrays.copyOf((int[]) r8, (int) r1)
            org.apache.commons.math3.linear.RealMatrix r10 = selectColumns(r0, r1)
            org.apache.commons.math3.linear.RealMatrix r0 = r6.weights
            org.apache.commons.math3.linear.RealMatrix r0 = r10.multiply(r0)
            r6.xmean = r0
            int r0 = r6.mu
            int[] r0 = org.apache.commons.math3.util.MathArrays.copyOf((int[]) r8, (int) r0)
            org.apache.commons.math3.linear.RealMatrix r0 = selectColumns(r3, r0)
            org.apache.commons.math3.linear.RealMatrix r1 = r6.weights
            org.apache.commons.math3.linear.RealMatrix r1 = r0.multiply(r1)
            boolean r1 = r6.updateEvolutionPaths(r1, r5)
            int r2 = r6.diagonalOnly
            if (r2 > 0) goto L_0x0141
            r0 = r24
            r2 = r10
            r4 = r8
            r0.updateCovariance(r1, r2, r3, r4, r5)
            goto L_0x0144
        L_0x0141:
            r6.updateCovarianceDiagonalOnly(r1, r0)
        L_0x0144:
            double r0 = r6.sigma
            double r2 = r6.normps
            double r4 = r6.chiN
            double r2 = r2 / r4
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r2 = r2 - r4
            double r11 = r6.cs
            double r2 = r2 * r11
            double r11 = r6.damps
            double r2 = r2 / r11
            double r2 = org.apache.commons.math3.util.FastMath.min((double) r4, (double) r2)
            double r2 = org.apache.commons.math3.util.FastMath.exp(r2)
            double r0 = r0 * r2
            r6.sigma = r0
            r0 = 0
            r1 = r8[r0]
            r1 = r15[r1]
            int r3 = r8.length
            r4 = 1
            int r3 = r3 - r4
            r3 = r8[r3]
            r3 = r15[r3]
            int r5 = (r17 > r1 ? 1 : (r17 == r1 ? 0 : -1))
            if (r5 <= 0) goto L_0x019b
            org.apache.commons.math3.optim.PointValuePair r5 = new org.apache.commons.math3.optim.PointValuePair
            double[] r11 = r10.getColumn(r0)
            double[] r0 = r7.repair(r11)
            boolean r11 = r6.isMinimize
            if (r11 == 0) goto L_0x017f
            r11 = r1
            goto L_0x0180
        L_0x017f:
            double r11 = -r1
        L_0x0180:
            r5.<init>(r0, r11)
            org.apache.commons.math3.optim.ConvergenceChecker r0 = r24.getConvergenceChecker()
            if (r0 == 0) goto L_0x0197
            org.apache.commons.math3.optim.ConvergenceChecker r0 = r24.getConvergenceChecker()
            int r11 = r6.iterations
            boolean r0 = r0.converged(r11, r5, r9)
            if (r0 == 0) goto L_0x0197
            goto L_0x0282
        L_0x0197:
            r17 = r1
            r11 = r9
            goto L_0x019d
        L_0x019b:
            r5 = r9
            r11 = r14
        L_0x019d:
            double r12 = r6.stopFitness
            r19 = 0
            int r0 = (r12 > r19 ? 1 : (r12 == r19 ? 0 : -1))
            if (r0 == 0) goto L_0x01b1
            boolean r0 = r6.isMinimize
            if (r0 == 0) goto L_0x01aa
            goto L_0x01ab
        L_0x01aa:
            double r12 = -r12
        L_0x01ab:
            int r0 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r0 >= 0) goto L_0x01b1
            goto L_0x0282
        L_0x01b1:
            org.apache.commons.math3.linear.RealMatrix r0 = r6.diagC
            org.apache.commons.math3.linear.RealMatrix r0 = sqrt(r0)
            r9 = 0
            double[] r0 = r0.getColumn(r9)
            org.apache.commons.math3.linear.RealMatrix r12 = r6.pc
            double[] r12 = r12.getColumn(r9)
            r9 = 0
        L_0x01c3:
            int r13 = r6.dimension
            if (r9 >= r13) goto L_0x01f8
            double r13 = r6.sigma
            r21 = r12[r9]
            r16 = r7
            r23 = r8
            double r7 = org.apache.commons.math3.util.FastMath.abs((double) r21)
            r21 = r11
            r22 = r12
            r11 = r0[r9]
            double r7 = org.apache.commons.math3.util.FastMath.max((double) r7, (double) r11)
            double r13 = r13 * r7
            double r7 = r6.stopTolX
            int r7 = (r13 > r7 ? 1 : (r13 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x01e5
            goto L_0x01fe
        L_0x01e5:
            int r7 = r6.dimension
            r8 = 1
            int r7 = r7 - r8
            if (r9 < r7) goto L_0x01ed
            goto L_0x0282
        L_0x01ed:
            int r9 = r9 + 1
            r7 = r16
            r11 = r21
            r12 = r22
            r8 = r23
            goto L_0x01c3
        L_0x01f8:
            r16 = r7
            r23 = r8
            r21 = r11
        L_0x01fe:
            r7 = 0
        L_0x01ff:
            int r8 = r6.dimension
            if (r7 >= r8) goto L_0x0213
            double r8 = r6.sigma
            r11 = r0[r7]
            double r8 = r8 * r11
            double r11 = r6.stopTolUpX
            int r8 = (r8 > r11 ? 1 : (r8 == r11 ? 0 : -1))
            if (r8 <= 0) goto L_0x0210
            goto L_0x0282
        L_0x0210:
            int r7 = r7 + 1
            goto L_0x01ff
        L_0x0213:
            double[] r0 = r6.fitnessHistory
            double r7 = min((double[]) r0)
            double[] r0 = r6.fitnessHistory
            double r11 = max((double[]) r0)
            int r0 = r6.iterations
            r9 = 2
            if (r0 <= r9) goto L_0x0234
            double r3 = org.apache.commons.math3.util.FastMath.max((double) r11, (double) r3)
            double r13 = org.apache.commons.math3.util.FastMath.min((double) r7, (double) r1)
            double r3 = r3 - r13
            double r13 = r6.stopTolFun
            int r0 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x0234
            goto L_0x0282
        L_0x0234:
            int r0 = r6.iterations
            double[] r3 = r6.fitnessHistory
            int r3 = r3.length
            if (r0 <= r3) goto L_0x0244
            double r3 = r11 - r7
            double r13 = r6.stopTolHistFun
            int r0 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r0 >= 0) goto L_0x0244
            goto L_0x0282
        L_0x0244:
            org.apache.commons.math3.linear.RealMatrix r0 = r6.diagD
            double r3 = max((org.apache.commons.math3.linear.RealMatrix) r0)
            org.apache.commons.math3.linear.RealMatrix r0 = r6.diagD
            double r13 = min((org.apache.commons.math3.linear.RealMatrix) r0)
            double r3 = r3 / r13
            r13 = 4711630319722168320(0x416312d000000000, double:1.0E7)
            int r0 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x025b
            goto L_0x0282
        L_0x025b:
            org.apache.commons.math3.optim.ConvergenceChecker r0 = r24.getConvergenceChecker()
            if (r0 == 0) goto L_0x0287
            org.apache.commons.math3.optim.PointValuePair r0 = new org.apache.commons.math3.optim.PointValuePair
            r3 = 0
            double[] r4 = r10.getColumn(r3)
            boolean r10 = r6.isMinimize
            if (r10 == 0) goto L_0x026e
            r13 = r1
            goto L_0x026f
        L_0x026e:
            double r13 = -r1
        L_0x026f:
            r0.<init>(r4, r13)
            if (r21 == 0) goto L_0x0285
            org.apache.commons.math3.optim.ConvergenceChecker r4 = r24.getConvergenceChecker()
            int r10 = r6.iterations
            r14 = r21
            boolean r4 = r4.converged(r10, r0, r14)
            if (r4 == 0) goto L_0x0285
        L_0x0282:
            r11 = r5
            goto L_0x0330
        L_0x0285:
            r14 = r0
            goto L_0x028a
        L_0x0287:
            r14 = r21
            r3 = 0
        L_0x028a:
            int r0 = r6.lambda
            double r3 = (double) r0
            r21 = 4616189618054758400(0x4010000000000000, double:4.0)
            double r3 = r3 / r21
            r21 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            double r3 = r3 + r21
            int r0 = (int) r3
            r0 = r23[r0]
            r3 = r15[r0]
            int r0 = (r17 > r3 ? 1 : (r17 == r3 ? 0 : -1))
            r3 = 4596373779694328218(0x3fc999999999999a, double:0.2)
            if (r0 != 0) goto L_0x02b9
            double r9 = r6.sigma
            r21 = r1
            double r0 = r6.cs
            r15 = r14
            double r13 = r6.damps
            double r0 = r0 / r13
            double r0 = r0 + r3
            double r0 = org.apache.commons.math3.util.FastMath.exp(r0)
            double r9 = r9 * r0
            r6.sigma = r9
            goto L_0x02bc
        L_0x02b9:
            r21 = r1
            r15 = r14
        L_0x02bc:
            int r0 = r6.iterations
            r1 = 2
            if (r0 <= r1) goto L_0x02e0
            r0 = r21
            double r9 = org.apache.commons.math3.util.FastMath.max((double) r11, (double) r0)
            double r7 = org.apache.commons.math3.util.FastMath.min((double) r7, (double) r0)
            double r9 = r9 - r7
            int r2 = (r9 > r19 ? 1 : (r9 == r19 ? 0 : -1))
            if (r2 != 0) goto L_0x02e2
            double r7 = r6.sigma
            double r9 = r6.cs
            double r11 = r6.damps
            double r9 = r9 / r11
            double r9 = r9 + r3
            double r2 = org.apache.commons.math3.util.FastMath.exp(r9)
            double r7 = r7 * r2
            r6.sigma = r7
            goto L_0x02e2
        L_0x02e0:
            r0 = r21
        L_0x02e2:
            double[] r2 = r6.fitnessHistory
            push(r2, r0)
            boolean r2 = r6.generateStatistics
            if (r2 == 0) goto L_0x031e
            java.util.List<java.lang.Double> r2 = r6.statisticsSigmaHistory
            double r3 = r6.sigma
            java.lang.Double r3 = java.lang.Double.valueOf(r3)
            r2.add(r3)
            java.util.List<java.lang.Double> r2 = r6.statisticsFitnessHistory
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r2.add(r0)
            java.util.List<org.apache.commons.math3.linear.RealMatrix> r0 = r6.statisticsMeanHistory
            org.apache.commons.math3.linear.RealMatrix r1 = r6.xmean
            org.apache.commons.math3.linear.RealMatrix r1 = r1.transpose()
            r0.add(r1)
            java.util.List<org.apache.commons.math3.linear.RealMatrix> r0 = r6.statisticsDHistory
            org.apache.commons.math3.linear.RealMatrix r1 = r6.diagD
            org.apache.commons.math3.linear.RealMatrix r1 = r1.transpose()
            r2 = 4681608360884174848(0x40f86a0000000000, double:100000.0)
            org.apache.commons.math3.linear.RealMatrix r1 = r1.scalarMultiply(r2)
            r0.add(r1)
        L_0x031e:
            int r0 = r6.iterations
            r1 = 1
            int r0 = r0 + r1
            r6.iterations = r0
            r9 = r1
            r11 = r5
            r14 = r15
            r7 = r16
            r12 = r17
            r8 = 0
            goto L_0x0048
        L_0x032e:
            r9 = r11
        L_0x032f:
            r11 = r9
        L_0x0330:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.CMAESOptimizer.doOptimize():org.apache.commons.math3.optim.PointValuePair");
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (Sigma sigma2 : optimizationDataArr) {
            if (sigma2 instanceof Sigma) {
                this.inputSigma = sigma2.getSigma();
            } else if (sigma2 instanceof PopulationSize) {
                this.lambda = ((PopulationSize) sigma2).getPopulationSize();
            }
        }
        checkParameters();
    }

    private void checkParameters() {
        double[] startPoint = getStartPoint();
        double[] lowerBound = getLowerBound();
        double[] upperBound = getUpperBound();
        double[] dArr = this.inputSigma;
        if (dArr == null) {
            return;
        }
        if (dArr.length == startPoint.length) {
            int i = 0;
            while (i < startPoint.length) {
                if (this.inputSigma[i] <= upperBound[i] - lowerBound[i]) {
                    i++;
                } else {
                    throw new OutOfRangeException(Double.valueOf(this.inputSigma[i]), 0, Double.valueOf(upperBound[i] - lowerBound[i]));
                }
            }
            return;
        }
        throw new DimensionMismatchException(this.inputSigma.length, startPoint.length);
    }

    private void initializeCMA(double[] dArr) {
        double[] dArr2 = dArr;
        if (this.lambda > 0) {
            int length = dArr2.length;
            int[] iArr = new int[2];
            iArr[1] = 1;
            iArr[0] = length;
            double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i = 0; i < dArr2.length; i++) {
                dArr3[i][0] = this.inputSigma[i];
            }
            Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(dArr3, false);
            this.sigma = max((RealMatrix) array2DRowRealMatrix);
            this.stopTolUpX = max((RealMatrix) array2DRowRealMatrix) * 1000.0d;
            this.stopTolX = max((RealMatrix) array2DRowRealMatrix) * 1.0E-11d;
            this.stopTolFun = 1.0E-12d;
            this.stopTolHistFun = 1.0E-13d;
            int i2 = this.lambda / 2;
            this.mu = i2;
            this.logMu2 = FastMath.log(((double) i2) + 0.5d);
            this.weights = log(sequence(1.0d, (double) this.mu, 1.0d)).scalarMultiply(-1.0d).scalarAdd(this.logMu2);
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i3 = 0; i3 < this.mu; i3++) {
                double entry = this.weights.getEntry(i3, 0);
                d += entry;
                d2 += entry * entry;
            }
            Array2DRowRealMatrix array2DRowRealMatrix2 = array2DRowRealMatrix;
            this.weights = this.weights.scalarMultiply(1.0d / d);
            double d3 = (d * d) / d2;
            this.mueff = d3;
            int i4 = this.dimension;
            this.cc = ((d3 / ((double) i4)) + 4.0d) / (((double) (i4 + 4)) + ((d3 * 2.0d) / ((double) i4)));
            this.cs = (d3 + 2.0d) / ((((double) i4) + d3) + 3.0d);
            this.damps = (((FastMath.max(0.0d, FastMath.sqrt((d3 - 1.0d) / ((double) (i4 + 1))) - 1.0d) * 2.0d) + 1.0d) * FastMath.max(0.3d, 1.0d - (((double) this.dimension) / (((double) this.maxIterations) + 1.0E-6d)))) + this.cs;
            int i5 = this.dimension;
            double d4 = this.mueff;
            double d5 = 2.0d / (((((double) i5) + 1.3d) * (((double) i5) + 1.3d)) + d4);
            this.ccov1 = d5;
            this.ccovmu = FastMath.min(1.0d - d5, (((d4 - 2.0d) + (1.0d / d4)) * 2.0d) / (((double) ((i5 + 2) * (i5 + 2))) + d4));
            this.ccov1Sep = FastMath.min(1.0d, (this.ccov1 * (((double) this.dimension) + 1.5d)) / 3.0d);
            this.ccovmuSep = FastMath.min(1.0d - this.ccov1, (this.ccovmu * (((double) this.dimension) + 1.5d)) / 3.0d);
            double sqrt = FastMath.sqrt((double) this.dimension);
            int i6 = this.dimension;
            this.chiN = sqrt * ((1.0d - (1.0d / (((double) i6) * 4.0d))) + (1.0d / ((((double) i6) * 21.0d) * ((double) i6))));
            this.xmean = MatrixUtils.createColumnRealMatrix(dArr);
            RealMatrix scalarMultiply = array2DRowRealMatrix2.scalarMultiply(1.0d / this.sigma);
            this.diagD = scalarMultiply;
            this.diagC = square(scalarMultiply);
            this.pc = zeros(this.dimension, 1);
            RealMatrix zeros = zeros(this.dimension, 1);
            this.ps = zeros;
            this.normps = zeros.getFrobeniusNorm();
            int i7 = this.dimension;
            this.B = eye(i7, i7);
            this.D = ones(this.dimension, 1);
            this.BD = times(this.B, repmat(this.diagD.transpose(), this.dimension, 1));
            this.C = this.B.multiply(diag(square(this.D)).multiply(this.B.transpose()));
            int i8 = ((int) (((double) (this.dimension * 30)) / ((double) this.lambda))) + 10;
            this.historySize = i8;
            this.fitnessHistory = new double[i8];
            for (int i9 = 0; i9 < this.historySize; i9++) {
                this.fitnessHistory[i9] = Double.MAX_VALUE;
            }
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(this.lambda));
    }

    private boolean updateEvolutionPaths(RealMatrix realMatrix, RealMatrix realMatrix2) {
        RealMatrix scalarMultiply = this.ps.scalarMultiply(1.0d - this.cs);
        RealMatrix multiply = this.B.multiply(realMatrix);
        double d = this.cs;
        RealMatrix add = scalarMultiply.add(multiply.scalarMultiply(FastMath.sqrt(d * (2.0d - d) * this.mueff)));
        this.ps = add;
        double frobeniusNorm = add.getFrobeniusNorm();
        this.normps = frobeniusNorm;
        boolean z = (frobeniusNorm / FastMath.sqrt(1.0d - FastMath.pow(1.0d - this.cs, this.iterations * 2))) / this.chiN < (2.0d / (((double) this.dimension) + 1.0d)) + 1.4d;
        RealMatrix scalarMultiply2 = this.pc.scalarMultiply(1.0d - this.cc);
        this.pc = scalarMultiply2;
        if (z) {
            RealMatrix subtract = this.xmean.subtract(realMatrix2);
            double d2 = this.cc;
            this.pc = scalarMultiply2.add(subtract.scalarMultiply(FastMath.sqrt((d2 * (2.0d - d2)) * this.mueff) / this.sigma));
        }
        return z;
    }

    private void updateCovarianceDiagonalOnly(boolean z, RealMatrix realMatrix) {
        double d;
        if (z) {
            d = 0.0d;
        } else {
            double d2 = this.ccov1Sep;
            double d3 = this.cc;
            d = d2 * d3 * (2.0d - d3);
        }
        RealMatrix add = this.diagC.scalarMultiply(d + ((1.0d - this.ccov1Sep) - this.ccovmuSep)).add(square(this.pc).scalarMultiply(this.ccov1Sep)).add(times(this.diagC, square(realMatrix).multiply(this.weights)).scalarMultiply(this.ccovmuSep));
        this.diagC = add;
        this.diagD = sqrt(add);
        int i = this.diagonalOnly;
        if (i > 1 && this.iterations > i) {
            this.diagonalOnly = 0;
            int i2 = this.dimension;
            this.B = eye(i2, i2);
            this.BD = diag(this.diagD);
            this.C = diag(this.diagC);
        }
    }

    private void updateCovariance(boolean z, RealMatrix realMatrix, RealMatrix realMatrix2, int[] iArr, RealMatrix realMatrix3) {
        double d;
        double d2;
        if (this.ccov1 + this.ccovmu > 0.0d) {
            RealMatrix scalarMultiply = realMatrix.subtract(repmat(realMatrix3, 1, this.mu)).scalarMultiply(1.0d / this.sigma);
            RealMatrix realMatrix4 = this.pc;
            RealMatrix scalarMultiply2 = realMatrix4.multiply(realMatrix4.transpose()).scalarMultiply(this.ccov1);
            if (z) {
                d2 = 0.0d;
            } else {
                double d3 = this.ccov1;
                double d4 = this.cc;
                d2 = d3 * d4 * (2.0d - d4);
            }
            double d5 = this.ccovmu;
            double d6 = d2 + ((1.0d - this.ccov1) - d5);
            if (this.isActiveCMA) {
                double pow = (((1.0d - d5) * 0.25d) * this.mueff) / (FastMath.pow((double) (this.dimension + 2), 1.5d) + (this.mueff * 2.0d));
                RealMatrix selectColumns = selectColumns(realMatrix2, MathArrays.copyOf(reverse(iArr), this.mu));
                RealMatrix sqrt = sqrt(sumRows(square(selectColumns)));
                int[] sortedIndices = sortedIndices(sqrt.getRow(0));
                RealMatrix selectColumns2 = selectColumns(divide(selectColumns(sqrt, reverse(sortedIndices)), selectColumns(sqrt, sortedIndices)), inverse(sortedIndices));
                double entry = 0.33999999999999997d / square(selectColumns2).multiply(this.weights).getEntry(0, 0);
                if (pow <= entry) {
                    entry = pow;
                }
                RealMatrix multiply = this.BD.multiply(times(selectColumns, repmat(selectColumns2, this.dimension, 1)));
                double d7 = 0.5d * entry;
                this.C = this.C.scalarMultiply(d6 + d7).add(scalarMultiply2).add(scalarMultiply.scalarMultiply(this.ccovmu + d7).multiply(times(repmat(this.weights, 1, this.dimension), scalarMultiply.transpose()))).subtract(multiply.multiply(diag(this.weights)).multiply(multiply.transpose()).scalarMultiply(entry));
                d = entry;
                updateBD(d);
            }
            this.C = this.C.scalarMultiply(d6).add(scalarMultiply2).add(scalarMultiply.scalarMultiply(this.ccovmu).multiply(times(repmat(this.weights, 1, this.dimension), scalarMultiply.transpose())));
        }
        d = 0.0d;
        updateBD(d);
    }

    private void updateBD(double d) {
        double d2 = this.ccov1;
        double d3 = this.ccovmu;
        if (d2 + d3 + d > 0.0d && (((((double) this.iterations) % 1.0d) / ((d2 + d3) + d)) / ((double) this.dimension)) / 10.0d < 1.0d) {
            RealMatrix add = triu(this.C, 0).add(triu(this.C, 1).transpose());
            this.C = add;
            EigenDecomposition eigenDecomposition = new EigenDecomposition(add);
            this.B = eigenDecomposition.getV();
            RealMatrix d4 = eigenDecomposition.getD();
            this.D = d4;
            RealMatrix diag = diag(d4);
            this.diagD = diag;
            if (min(diag) <= 0.0d) {
                for (int i = 0; i < this.dimension; i++) {
                    if (this.diagD.getEntry(i, 0) < 0.0d) {
                        this.diagD.setEntry(i, 0, 0.0d);
                    }
                }
                double max = max(this.diagD) / 1.0E14d;
                RealMatrix realMatrix = this.C;
                int i2 = this.dimension;
                this.C = realMatrix.add(eye(i2, i2).scalarMultiply(max));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(max));
            }
            if (max(this.diagD) > min(this.diagD) * 1.0E14d) {
                double max2 = (max(this.diagD) / 1.0E14d) - min(this.diagD);
                RealMatrix realMatrix2 = this.C;
                int i3 = this.dimension;
                this.C = realMatrix2.add(eye(i3, i3).scalarMultiply(max2));
                this.diagD = this.diagD.add(ones(this.dimension, 1).scalarMultiply(max2));
            }
            this.diagC = diag(this.C);
            RealMatrix sqrt = sqrt(this.diagD);
            this.diagD = sqrt;
            this.BD = times(this.B, repmat(sqrt.transpose(), this.dimension, 1));
        }
    }

    private static void push(double[] dArr, double d) {
        for (int length = dArr.length - 1; length > 0; length--) {
            dArr[length] = dArr[length - 1];
        }
        dArr[0] = d;
    }

    private int[] sortedIndices(double[] dArr) {
        DoubleIndex[] doubleIndexArr = new DoubleIndex[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            doubleIndexArr[i] = new DoubleIndex(dArr[i], i);
        }
        Arrays.sort(doubleIndexArr);
        int[] iArr = new int[dArr.length];
        for (int i2 = 0; i2 < dArr.length; i2++) {
            iArr[i2] = doubleIndexArr[i2].index;
        }
        return iArr;
    }

    private double valueRange(ValuePenaltyPair[] valuePenaltyPairArr) {
        double d = Double.NEGATIVE_INFINITY;
        double d2 = Double.MAX_VALUE;
        for (ValuePenaltyPair valuePenaltyPair : valuePenaltyPairArr) {
            if (valuePenaltyPair.value > d) {
                d = valuePenaltyPair.value;
            }
            if (valuePenaltyPair.value < d2) {
                d2 = valuePenaltyPair.value;
            }
        }
        return d - d2;
    }

    private static class DoubleIndex implements Comparable<DoubleIndex> {
        /* access modifiers changed from: private */
        public final int index;
        private final double value;

        DoubleIndex(double d, int i) {
            this.value = d;
            this.index = i;
        }

        public int compareTo(DoubleIndex doubleIndex) {
            return Double.compare(this.value, doubleIndex.value);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DoubleIndex)) {
                return false;
            }
            if (Double.compare(this.value, ((DoubleIndex) obj).value) == 0) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.value);
            return (int) (doubleToLongBits ^ ((doubleToLongBits >>> 32) ^ 1438542));
        }
    }

    private static class ValuePenaltyPair {
        /* access modifiers changed from: private */
        public double penalty;
        /* access modifiers changed from: private */
        public double value;

        public ValuePenaltyPair(double d, double d2) {
            this.value = d;
            this.penalty = d2;
        }
    }

    private class FitnessFunction {
        private final boolean isRepairMode = true;

        public FitnessFunction() {
        }

        public ValuePenaltyPair value(double[] dArr) {
            double d;
            double d2;
            if (this.isRepairMode) {
                double[] repair = repair(dArr);
                d2 = CMAESOptimizer.this.computeObjectiveValue(repair);
                d = penalty(dArr, repair);
            } else {
                d2 = CMAESOptimizer.this.computeObjectiveValue(dArr);
                d = 0.0d;
            }
            if (!CMAESOptimizer.this.isMinimize) {
                d2 = -d2;
            }
            if (!CMAESOptimizer.this.isMinimize) {
                d = -d;
            }
            return new ValuePenaltyPair(d2, d);
        }

        public boolean isFeasible(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                if (d < lowerBound[i] || d > upperBound[i]) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: private */
        public double[] repair(double[] dArr) {
            double[] lowerBound = CMAESOptimizer.this.getLowerBound();
            double[] upperBound = CMAESOptimizer.this.getUpperBound();
            double[] dArr2 = new double[dArr.length];
            for (int i = 0; i < dArr.length; i++) {
                double d = dArr[i];
                double d2 = lowerBound[i];
                if (d < d2) {
                    dArr2[i] = d2;
                } else {
                    double d3 = upperBound[i];
                    if (d > d3) {
                        dArr2[i] = d3;
                    } else {
                        dArr2[i] = d;
                    }
                }
            }
            return dArr2;
        }

        private double penalty(double[] dArr, double[] dArr2) {
            double d = 0.0d;
            for (int i = 0; i < dArr.length; i++) {
                d += FastMath.abs(dArr[i] - dArr2[i]);
            }
            return CMAESOptimizer.this.isMinimize ? d : -d;
        }
    }

    private static RealMatrix log(RealMatrix realMatrix) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = FastMath.log(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sqrt(RealMatrix realMatrix) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = FastMath.sqrt(realMatrix.getEntry(i, i2));
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix square(RealMatrix realMatrix) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                dArr[i][i2] = entry * entry;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix times(RealMatrix realMatrix, RealMatrix realMatrix2) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) * realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix divide(RealMatrix realMatrix, RealMatrix realMatrix2) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, i2) / realMatrix2.getEntry(i, i2);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix selectColumns(RealMatrix realMatrix, int[] iArr) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr2 = new int[2];
        iArr2[1] = iArr.length;
        iArr2[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                dArr[i][i2] = realMatrix.getEntry(i, iArr[i2]);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix triu(RealMatrix realMatrix, int i) {
        int rowDimension = realMatrix.getRowDimension();
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = rowDimension;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        int i2 = 0;
        while (i2 < realMatrix.getRowDimension()) {
            for (int i3 = 0; i3 < realMatrix.getColumnDimension(); i3++) {
                dArr[i2][i3] = i2 <= i3 - i ? realMatrix.getEntry(i2, i3) : 0.0d;
            }
            i2++;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sumRows(RealMatrix realMatrix) {
        int[] iArr = new int[2];
        iArr[1] = realMatrix.getColumnDimension();
        iArr[0] = 1;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i = 0; i < realMatrix.getColumnDimension(); i++) {
            double d = 0.0d;
            for (int i2 = 0; i2 < realMatrix.getRowDimension(); i2++) {
                d += realMatrix.getEntry(i2, i);
            }
            dArr[0][i] = d;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix diag(RealMatrix realMatrix) {
        if (realMatrix.getColumnDimension() == 1) {
            int rowDimension = realMatrix.getRowDimension();
            int[] iArr = new int[2];
            iArr[1] = realMatrix.getRowDimension();
            iArr[0] = rowDimension;
            double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i = 0; i < realMatrix.getRowDimension(); i++) {
                dArr[i][i] = realMatrix.getEntry(i, 0);
            }
            return new Array2DRowRealMatrix(dArr, false);
        }
        int rowDimension2 = realMatrix.getRowDimension();
        int[] iArr2 = new int[2];
        iArr2[1] = 1;
        iArr2[0] = rowDimension2;
        double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
        for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
            dArr2[i2][0] = realMatrix.getEntry(i2, i2);
        }
        return new Array2DRowRealMatrix(dArr2, false);
    }

    private static void copyColumn(RealMatrix realMatrix, int i, RealMatrix realMatrix2, int i2) {
        for (int i3 = 0; i3 < realMatrix.getRowDimension(); i3++) {
            realMatrix2.setEntry(i3, i2, realMatrix.getEntry(i3, i));
        }
    }

    private static RealMatrix ones(int i, int i2) {
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i3 = 0; i3 < i; i3++) {
            Arrays.fill(dArr[i3], 1.0d);
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix eye(int i, int i2) {
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i3 = 0; i3 < i; i3++) {
            if (i3 < i2) {
                dArr[i3][i3] = 1.0d;
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix zeros(int i, int i2) {
        return new Array2DRowRealMatrix(i, i2);
    }

    private static RealMatrix repmat(RealMatrix realMatrix, int i, int i2) {
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        int i3 = i * rowDimension;
        int i4 = i2 * columnDimension;
        int[] iArr = new int[2];
        iArr[1] = i4;
        iArr[0] = i3;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i4; i6++) {
                dArr[i5][i6] = realMatrix.getEntry(i5 % rowDimension, i6 % columnDimension);
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static RealMatrix sequence(double d, double d2, double d3) {
        int i = (int) (((d2 - d) / d3) + 1.0d);
        int[] iArr = new int[2];
        iArr[1] = 1;
        iArr[0] = i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2][0] = d;
            d += d3;
        }
        return new Array2DRowRealMatrix(dArr, false);
    }

    private static double max(RealMatrix realMatrix) {
        double d = -1.7976931348623157E308d;
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d < entry) {
                    d = entry;
                }
            }
        }
        return d;
    }

    private static double min(RealMatrix realMatrix) {
        double d = Double.MAX_VALUE;
        for (int i = 0; i < realMatrix.getRowDimension(); i++) {
            for (int i2 = 0; i2 < realMatrix.getColumnDimension(); i2++) {
                double entry = realMatrix.getEntry(i, i2);
                if (d > entry) {
                    d = entry;
                }
            }
        }
        return d;
    }

    private static double max(double[] dArr) {
        double d = -1.7976931348623157E308d;
        for (double d2 : dArr) {
            if (d < d2) {
                d = d2;
            }
        }
        return d;
    }

    private static double min(double[] dArr) {
        double d = Double.MAX_VALUE;
        for (double d2 : dArr) {
            if (d > d2) {
                d = d2;
            }
        }
        return d;
    }

    private static int[] inverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[iArr[i]] = i;
        }
        return iArr2;
    }

    private static int[] reverse(int[] iArr) {
        int[] iArr2 = new int[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr2[i] = iArr[(iArr.length - i) - 1];
        }
        return iArr2;
    }

    private double[] randn(int i) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = this.random.nextGaussian();
        }
        return dArr;
    }

    private RealMatrix randn1(int i, int i2) {
        int[] iArr = new int[2];
        iArr[1] = i2;
        iArr[0] = i;
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                dArr[i3][i4] = this.random.nextGaussian();
            }
        }
        return new Array2DRowRealMatrix(dArr, false);
    }
}
