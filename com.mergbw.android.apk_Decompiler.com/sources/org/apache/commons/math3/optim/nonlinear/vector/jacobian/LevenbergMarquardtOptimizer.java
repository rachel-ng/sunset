package org.apache.commons.math3.optim.nonlinear.vector.jacobian;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optim.ConvergenceChecker;
import org.apache.commons.math3.optim.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
    private static final double TWO_EPS = (Precision.EPSILON * 2.0d);
    private double[] beta;
    private final double costRelativeTolerance;
    private double[] diagR;
    private final double initialStepBoundFactor;
    private double[] jacNorm;
    private double[] lmDir;
    private double lmPar;
    private final double orthoTolerance;
    private final double parRelativeTolerance;
    private int[] permutation;
    private final double qrRankingThreshold;
    private int rank;
    private int solvedCols;
    private double[][] weightedJacobian;
    private double[] weightedResidual;

    public LevenbergMarquardtOptimizer() {
        this(100.0d, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        this(100.0d, convergenceChecker, 1.0E-10d, 1.0E-10d, 1.0E-10d, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, ConvergenceChecker<PointVectorValuePair> convergenceChecker, double d2, double d3, double d4, double d5) {
        super(convergenceChecker);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3) {
        this(100.0d, d, d2, d3, Precision.SAFE_MIN);
    }

    public LevenbergMarquardtOptimizer(double d, double d2, double d3, double d4, double d5) {
        super((ConvergenceChecker<PointVectorValuePair>) null);
        this.initialStepBoundFactor = d;
        this.costRelativeTolerance = d2;
        this.parRelativeTolerance = d3;
        this.orthoTolerance = d4;
        this.qrRankingThreshold = d5;
    }

    /* access modifiers changed from: protected */
    public PointVectorValuePair doOptimize() {
        double d;
        RealMatrix realMatrix;
        double d2;
        boolean z;
        PointVectorValuePair pointVectorValuePair;
        double d3;
        double d4;
        double[] dArr;
        double d5;
        double[] dArr2;
        RealMatrix realMatrix2;
        checkParameters();
        int length = getTarget().length;
        double[] startPoint = getStartPoint();
        int length2 = startPoint.length;
        this.solvedCols = FastMath.min(length, length2);
        this.diagR = new double[length2];
        this.jacNorm = new double[length2];
        this.beta = new double[length2];
        this.permutation = new int[length2];
        this.lmDir = new double[length2];
        double[] dArr3 = new double[length2];
        double[] dArr4 = new double[length2];
        double[] dArr5 = new double[length];
        double[] dArr6 = new double[length];
        double[] dArr7 = new double[length];
        double[] dArr8 = new double[length2];
        RealMatrix weightSquareRoot = getWeightSquareRoot();
        double[] computeObjectiveValue = computeObjectiveValue(startPoint);
        double[] computeResiduals = computeResiduals(computeObjectiveValue);
        PointVectorValuePair pointVectorValuePair2 = new PointVectorValuePair(startPoint, computeObjectiveValue);
        double computeCost = computeCost(computeResiduals);
        double[] dArr9 = new double[length2];
        double[] dArr10 = new double[length2];
        this.lmPar = 0.0d;
        ConvergenceChecker convergenceChecker = getConvergenceChecker();
        double d6 = computeCost;
        double d7 = 0.0d;
        double d8 = 0.0d;
        PointVectorValuePair pointVectorValuePair3 = pointVectorValuePair2;
        boolean z2 = true;
        loop0:
        while (true) {
            incrementIterationCount();
            double[] dArr11 = dArr5;
            qrDecomposition(computeWeightedJacobian(startPoint));
            this.weightedResidual = weightSquareRoot.operate(computeResiduals);
            int i = 0;
            while (i < length) {
                dArr7[i] = this.weightedResidual[i];
                i++;
                computeObjectiveValue = computeObjectiveValue;
            }
            double[] dArr12 = computeObjectiveValue;
            qTy(dArr7);
            int i2 = 0;
            while (i2 < this.solvedCols) {
                int i3 = this.permutation[i2];
                this.weightedJacobian[i2][i3] = this.diagR[i3];
                i2++;
                computeResiduals = computeResiduals;
                convergenceChecker = convergenceChecker;
            }
            double[] dArr13 = computeResiduals;
            ConvergenceChecker convergenceChecker2 = convergenceChecker;
            if (z2) {
                double d9 = 0.0d;
                for (int i4 = 0; i4 < length2; i4++) {
                    double d10 = this.jacNorm[i4];
                    if (d10 == 0.0d) {
                        d10 = 1.0d;
                    }
                    double d11 = startPoint[i4] * d10;
                    d9 += d11 * d11;
                    dArr3[i4] = d10;
                }
                d8 = FastMath.sqrt(d9);
                d = 0.0d;
                d7 = d8 == 0.0d ? this.initialStepBoundFactor : this.initialStepBoundFactor * d8;
            } else {
                d = 0.0d;
            }
            if (d6 != d) {
                double d12 = d;
                int i5 = 0;
                while (i5 < this.solvedCols) {
                    int i6 = this.permutation[i5];
                    boolean z3 = z2;
                    double d13 = this.jacNorm[i6];
                    if (d13 != d) {
                        double d14 = d;
                        int i7 = 0;
                        while (i7 <= i5) {
                            d14 += this.weightedJacobian[i7][i6] * dArr7[i7];
                            i7++;
                            weightSquareRoot = weightSquareRoot;
                        }
                        realMatrix2 = weightSquareRoot;
                        d12 = FastMath.max(d12, FastMath.abs(d14) / (d13 * d6));
                    } else {
                        realMatrix2 = weightSquareRoot;
                    }
                    i5++;
                    z2 = z3;
                    weightSquareRoot = realMatrix2;
                }
                z = z2;
                realMatrix = weightSquareRoot;
                d2 = d12;
            } else {
                z = z2;
                realMatrix = weightSquareRoot;
                d2 = d;
            }
            if (d2 <= this.orthoTolerance) {
                setCost(d6);
                return pointVectorValuePair3;
            }
            for (int i8 = 0; i8 < length2; i8++) {
                dArr3[i8] = FastMath.max(dArr3[i8], this.jacNorm[i8]);
            }
            pointVectorValuePair = pointVectorValuePair3;
            double d15 = d;
            dArr5 = dArr11;
            computeResiduals = dArr13;
            double[] dArr14 = dArr12;
            double d16 = d8;
            double d17 = d7;
            double d18 = d6;
            while (d15 < 1.0E-4d) {
                for (int i9 = 0; i9 < this.solvedCols; i9++) {
                    int i10 = this.permutation[i9];
                    dArr4[i10] = startPoint[i10];
                }
                double[] dArr15 = this.weightedResidual;
                this.weightedResidual = dArr5;
                ConvergenceChecker convergenceChecker3 = convergenceChecker2;
                int i11 = length;
                RealMatrix realMatrix3 = realMatrix;
                ConvergenceChecker convergenceChecker4 = convergenceChecker3;
                double[] dArr16 = dArr9;
                double d19 = d;
                double[] dArr17 = dArr7;
                double[] dArr18 = dArr15;
                double[] dArr19 = dArr10;
                double[] dArr20 = dArr14;
                double[] dArr21 = dArr10;
                double[] dArr22 = dArr18;
                PointVectorValuePair pointVectorValuePair4 = pointVectorValuePair3;
                double[] dArr23 = dArr21;
                determineLMParameter(dArr7, d17, dArr3, dArr8, dArr19, dArr16);
                double d20 = d19;
                for (int i12 = 0; i12 < this.solvedCols; i12++) {
                    int i13 = this.permutation[i12];
                    double[] dArr24 = this.lmDir;
                    double d21 = -dArr24[i13];
                    dArr24[i13] = d21;
                    startPoint[i13] = dArr4[i13] + d21;
                    double d22 = dArr3[i13] * dArr24[i13];
                    d20 += d22 * d22;
                }
                double sqrt = FastMath.sqrt(d20);
                double d23 = d17;
                if (z) {
                    d23 = FastMath.min(d23, sqrt);
                }
                double[] computeObjectiveValue2 = computeObjectiveValue(startPoint);
                double[] computeResiduals2 = computeResiduals(computeObjectiveValue2);
                PointVectorValuePair pointVectorValuePair5 = new PointVectorValuePair(startPoint, computeObjectiveValue2);
                double[] dArr25 = computeObjectiveValue2;
                double computeCost2 = computeCost(computeResiduals2);
                double d24 = 0.1d;
                double d25 = computeCost2 * 0.1d;
                if (d25 < d18) {
                    double d26 = computeCost2 / d18;
                    d3 = 1.0d - (d26 * d26);
                } else {
                    d3 = -1.0d;
                }
                double[] dArr26 = computeResiduals2;
                double[] dArr27 = dArr4;
                int i14 = 0;
                while (i14 < this.solvedCols) {
                    int i15 = this.permutation[i14];
                    double d27 = computeCost2;
                    double d28 = this.lmDir[i15];
                    dArr8[i14] = d19;
                    for (int i16 = 0; i16 <= i14; i16++) {
                        dArr8[i16] = dArr8[i16] + (this.weightedJacobian[i16][i15] * d28);
                    }
                    i14++;
                    computeCost2 = d27;
                }
                double d29 = computeCost2;
                double d30 = d19;
                for (int i17 = 0; i17 < this.solvedCols; i17++) {
                    double d31 = dArr8[i17];
                    d30 += d31 * d31;
                }
                double d32 = d18 * d18;
                double d33 = d30 / d32;
                PointVectorValuePair pointVectorValuePair6 = pointVectorValuePair5;
                double d34 = this.lmPar;
                double d35 = ((d34 * sqrt) * sqrt) / d32;
                double d36 = d33 + (d35 * 2.0d);
                double d37 = -(d33 + d35);
                double d38 = d36 == d19 ? d19 : d3 / d36;
                double d39 = 0.5d;
                if (d38 <= 0.25d) {
                    if (d3 < d19) {
                        d39 = (d37 * 0.5d) / (d37 + (0.5d * d3));
                    }
                    if (d25 < d18 && d39 >= 0.1d) {
                        d24 = d39;
                    }
                    d4 = FastMath.min(d23, sqrt * 10.0d) * d24;
                    this.lmPar /= d24;
                } else if (d34 == d19 || d38 >= 0.75d) {
                    d4 = sqrt * 2.0d;
                    this.lmPar = d34 * 0.5d;
                } else {
                    d4 = d23;
                }
                if (d38 >= 1.0E-4d) {
                    double d40 = d19;
                    for (int i18 = 0; i18 < length2; i18++) {
                        double d41 = dArr3[i18] * startPoint[i18];
                        d40 += d41 * d41;
                    }
                    d16 = FastMath.sqrt(d40);
                    if (convergenceChecker4 == null || !convergenceChecker4.converged(getIterations(), pointVectorValuePair4, pointVectorValuePair6)) {
                        d5 = d29;
                        pointVectorValuePair = pointVectorValuePair6;
                        dArr2 = dArr22;
                        dArr = dArr25;
                        z = false;
                    } else {
                        setCost(d29);
                        return pointVectorValuePair6;
                    }
                } else {
                    for (int i19 = 0; i19 < this.solvedCols; i19++) {
                        int i20 = this.permutation[i19];
                        startPoint[i20] = dArr27[i20];
                    }
                    dArr2 = this.weightedResidual;
                    this.weightedResidual = dArr22;
                    double[] dArr28 = dArr20;
                    pointVectorValuePair = new PointVectorValuePair(startPoint, dArr28);
                    dArr = dArr28;
                    d5 = d18;
                }
                double abs = FastMath.abs(d3);
                double[] dArr29 = dArr;
                double d42 = this.costRelativeTolerance;
                if ((abs > d42 || d36 > d42 || d38 > 2.0d) && d4 > this.parRelativeTolerance * d16) {
                    double abs2 = FastMath.abs(d3);
                    double d43 = TWO_EPS;
                    if (abs2 <= d43 && d36 <= d43 && d38 <= 2.0d) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, Double.valueOf(this.costRelativeTolerance));
                    } else if (d4 <= d43 * d16) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, Double.valueOf(this.parRelativeTolerance));
                    } else if (d2 > d43) {
                        d18 = d5;
                        dArr14 = dArr29;
                        dArr10 = dArr23;
                        realMatrix = realMatrix3;
                        dArr4 = dArr27;
                        d15 = d38;
                        pointVectorValuePair3 = pointVectorValuePair4;
                        dArr7 = dArr17;
                        d = d19;
                        dArr9 = dArr16;
                        double d44 = d4;
                        dArr5 = dArr2;
                        computeResiduals = dArr26;
                        d17 = d44;
                        int i21 = i11;
                        convergenceChecker2 = convergenceChecker4;
                        length = i21;
                    } else {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, Double.valueOf(this.orthoTolerance));
                    }
                }
            }
            double[] dArr30 = dArr9;
            double d45 = d;
            double d46 = d17;
            pointVectorValuePair3 = pointVectorValuePair;
            computeObjectiveValue = dArr14;
            dArr9 = dArr30;
            d6 = d18;
            d8 = d16;
            z2 = z;
            d7 = d46;
            convergenceChecker = convergenceChecker2;
            length = length;
            weightSquareRoot = realMatrix;
        }
        setCost(d5);
        return pointVectorValuePair;
    }

    private void determineLMParameter(double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) {
        int i;
        double d2;
        double d3;
        double d4;
        double[] dArr6 = dArr;
        double d5 = d;
        double[] dArr7 = dArr3;
        double[] dArr8 = dArr4;
        double[] dArr9 = dArr5;
        int i2 = 0;
        int length = this.weightedJacobian[0].length;
        int i3 = 0;
        while (true) {
            i = this.rank;
            if (i3 >= i) {
                break;
            }
            this.lmDir[this.permutation[i3]] = dArr6[i3];
            i3++;
        }
        while (i < length) {
            this.lmDir[this.permutation[i]] = 0.0d;
            i++;
        }
        int i4 = this.rank - 1;
        while (i4 >= 0) {
            int i5 = this.permutation[i4];
            double d6 = this.lmDir[i5] / this.diagR[i5];
            for (int i6 = i2; i6 < i4; i6++) {
                double[] dArr10 = this.lmDir;
                int i7 = this.permutation[i6];
                dArr10[i7] = dArr10[i7] - (this.weightedJacobian[i6][i5] * d6);
            }
            this.lmDir[i5] = d6;
            i4--;
            i2 = 0;
        }
        double d7 = 0.0d;
        for (int i8 = 0; i8 < this.solvedCols; i8++) {
            int i9 = this.permutation[i8];
            double d8 = dArr2[i9] * this.lmDir[i9];
            dArr7[i9] = d8;
            d7 += d8 * d8;
        }
        double sqrt = FastMath.sqrt(d7);
        double d9 = sqrt - d5;
        double d10 = d5 * 0.1d;
        if (d9 <= d10) {
            this.lmPar = 0.0d;
            return;
        }
        if (this.rank == this.solvedCols) {
            for (int i10 = 0; i10 < this.solvedCols; i10++) {
                int i11 = this.permutation[i10];
                dArr7[i11] = dArr7[i11] * (dArr2[i11] / sqrt);
            }
            d2 = d10;
            double d11 = 0.0d;
            int i12 = 0;
            while (i12 < this.solvedCols) {
                int i13 = this.permutation[i12];
                int i14 = 0;
                double d12 = 0.0d;
                while (i14 < i12) {
                    d12 += this.weightedJacobian[i14][i13] * dArr7[this.permutation[i14]];
                    i14++;
                    double[] dArr11 = dArr4;
                }
                double d13 = (dArr7[i13] - d12) / this.diagR[i13];
                dArr7[i13] = d13;
                d11 += d13 * d13;
                i12++;
                double[] dArr12 = dArr4;
            }
            d3 = d9 / (d11 * d5);
        } else {
            d2 = d10;
            d3 = 0.0d;
        }
        int i15 = 0;
        double d14 = 0.0d;
        while (i15 < this.solvedCols) {
            int i16 = this.permutation[i15];
            double d15 = d9;
            double d16 = 0.0d;
            for (int i17 = 0; i17 <= i15; i17++) {
                d16 += this.weightedJacobian[i17][i16] * dArr6[i17];
            }
            double d17 = d16 / dArr2[i16];
            d14 += d17 * d17;
            i15++;
            d9 = d15;
        }
        double d18 = d9;
        double sqrt2 = FastMath.sqrt(d14);
        double d19 = sqrt2 / d5;
        double d20 = 0.0d;
        if (d19 == 0.0d) {
            d19 = Precision.SAFE_MIN / FastMath.min(d5, 0.1d);
        }
        double min = FastMath.min(d19, FastMath.max(this.lmPar, d3));
        this.lmPar = min;
        if (min == 0.0d) {
            this.lmPar = sqrt2 / sqrt;
        }
        int i18 = 10;
        double d21 = d18;
        while (i18 >= 0) {
            if (this.lmPar == d20) {
                d4 = d21;
                this.lmPar = FastMath.max(Precision.SAFE_MIN, d19 * 0.001d);
            } else {
                d4 = d21;
            }
            double sqrt3 = FastMath.sqrt(this.lmPar);
            for (int i19 = 0; i19 < this.solvedCols; i19++) {
                int i20 = this.permutation[i19];
                dArr7[i20] = dArr2[i20] * sqrt3;
            }
            double[] dArr13 = dArr4;
            double[] dArr14 = dArr5;
            determineLMDirection(dArr6, dArr7, dArr13, dArr14);
            double d22 = 0.0d;
            int i21 = 0;
            while (i21 < this.solvedCols) {
                int i22 = this.permutation[i21];
                double d23 = dArr2[i22] * this.lmDir[i22];
                dArr14[i22] = d23;
                d22 += d23 * d23;
                i21++;
                double[] dArr15 = dArr;
            }
            double sqrt4 = FastMath.sqrt(d22);
            double d24 = sqrt4 - d5;
            if (FastMath.abs(d24) <= d2) {
                return;
            }
            if (d3 != 0.0d || d24 > d4 || d4 >= 0.0d) {
                for (int i23 = 0; i23 < this.solvedCols; i23++) {
                    int i24 = this.permutation[i23];
                    dArr7[i24] = (dArr14[i24] * dArr2[i24]) / sqrt4;
                }
                int i25 = 0;
                while (i25 < this.solvedCols) {
                    int i26 = this.permutation[i25];
                    double d25 = dArr7[i26] / dArr13[i25];
                    dArr7[i26] = d25;
                    i25++;
                    for (int i27 = i25; i27 < this.solvedCols; i27++) {
                        int i28 = this.permutation[i27];
                        dArr7[i28] = dArr7[i28] - (this.weightedJacobian[i27][i26] * d25);
                    }
                }
                double d26 = 0.0d;
                for (int i29 = 0; i29 < this.solvedCols; i29++) {
                    double d27 = dArr7[this.permutation[i29]];
                    d26 += d27 * d27;
                }
                double d28 = d24 / (d26 * d5);
                d20 = 0.0d;
                if (d24 > 0.0d) {
                    d3 = FastMath.max(d3, this.lmPar);
                } else if (d24 < 0.0d) {
                    d19 = FastMath.min(d19, this.lmPar);
                }
                this.lmPar = FastMath.max(d3, this.lmPar + d28);
                i18--;
                dArr6 = dArr;
                d5 = d;
                d21 = d24;
            } else {
                return;
            }
        }
    }

    private void determineLMDirection(double[] dArr, double[] dArr2, double[] dArr3, double[] dArr4) {
        int i;
        double d;
        double d2;
        double[] dArr5 = dArr3;
        int i2 = 0;
        int i3 = 0;
        while (i3 < this.solvedCols) {
            int i4 = this.permutation[i3];
            int i5 = i3 + 1;
            for (int i6 = i5; i6 < this.solvedCols; i6++) {
                double[][] dArr6 = this.weightedJacobian;
                dArr6[i6][i4] = dArr6[i3][this.permutation[i6]];
            }
            this.lmDir[i3] = this.diagR[i4];
            dArr4[i3] = dArr[i3];
            i3 = i5;
        }
        int i7 = 0;
        while (true) {
            i = this.solvedCols;
            double d3 = 0.0d;
            if (i7 >= i) {
                break;
            }
            double d4 = dArr2[this.permutation[i7]];
            if (d4 != 0.0d) {
                Arrays.fill(dArr5, i7 + 1, dArr5.length, 0.0d);
            }
            dArr5[i7] = d4;
            int i8 = i7;
            double d5 = 0.0d;
            while (i8 < this.solvedCols) {
                int i9 = this.permutation[i8];
                if (dArr5[i8] != d3) {
                    double d6 = this.weightedJacobian[i8][i9];
                    if (FastMath.abs(d6) < FastMath.abs(dArr5[i8])) {
                        double d7 = d6 / dArr5[i8];
                        d2 = 1.0d / FastMath.sqrt((d7 * d7) + 1.0d);
                        d = d7 * d2;
                    } else {
                        double d8 = dArr5[i8] / d6;
                        double sqrt = 1.0d / FastMath.sqrt((d8 * d8) + 1.0d);
                        double d9 = sqrt;
                        d2 = d8 * sqrt;
                        d = d9;
                    }
                    this.weightedJacobian[i8][i9] = (d6 * d) + (dArr5[i8] * d2);
                    double d10 = dArr4[i8];
                    double d11 = -d2;
                    d5 = (d5 * d) + (d10 * d11);
                    dArr4[i8] = (d * d10) + (d2 * d5);
                    for (int i10 = i8 + 1; i10 < this.solvedCols; i10++) {
                        double[] dArr7 = this.weightedJacobian[i10];
                        double d12 = dArr7[i9];
                        double d13 = dArr5[i10];
                        dArr5[i10] = (d12 * d11) + (d13 * d);
                        dArr7[i9] = (d * d12) + (d2 * d13);
                    }
                }
                i8++;
                d3 = 0.0d;
            }
            double[] dArr8 = this.weightedJacobian[i7];
            int i11 = this.permutation[i7];
            dArr5[i7] = dArr8[i11];
            dArr8[i11] = this.lmDir[i7];
            i7++;
        }
        int i12 = 0;
        while (true) {
            int i13 = this.solvedCols;
            if (i12 >= i13) {
                break;
            }
            if (dArr5[i12] == 0.0d && i == i13) {
                i = i12;
            }
            if (i < i13) {
                dArr4[i12] = 0.0d;
            }
            i12++;
        }
        if (i > 0) {
            for (int i14 = i - 1; i14 >= 0; i14--) {
                int i15 = this.permutation[i14];
                double d14 = 0.0d;
                for (int i16 = i14 + 1; i16 < i; i16++) {
                    d14 += this.weightedJacobian[i16][i15] * dArr4[i16];
                }
                dArr4[i14] = (dArr4[i14] - d14) / dArr5[i14];
            }
        }
        while (true) {
            double[] dArr9 = this.lmDir;
            if (i2 < dArr9.length) {
                dArr9[this.permutation[i2]] = dArr4[i2];
                i2++;
            } else {
                return;
            }
        }
    }

    private void qrDecomposition(RealMatrix realMatrix) throws ConvergenceException {
        double[][] data = realMatrix.scalarMultiply(-1.0d).getData();
        this.weightedJacobian = data;
        int length = data.length;
        char c2 = 0;
        int length2 = data[0].length;
        int i = 0;
        while (true) {
            double d = 0.0d;
            if (i >= length2) {
                break;
            }
            this.permutation[i] = i;
            for (int i2 = 0; i2 < length; i2++) {
                double d2 = this.weightedJacobian[i2][i];
                d += d2 * d2;
            }
            this.jacNorm[i] = FastMath.sqrt(d);
            i++;
        }
        int i3 = 0;
        while (i3 < length2) {
            int i4 = -1;
            double d3 = Double.NEGATIVE_INFINITY;
            for (int i5 = i3; i5 < length2; i5++) {
                double d4 = 0.0d;
                for (int i6 = i3; i6 < length; i6++) {
                    double d5 = this.weightedJacobian[i6][this.permutation[i5]];
                    d4 += d5 * d5;
                }
                if (Double.isInfinite(d4) || Double.isNaN(d4)) {
                    LocalizedFormats localizedFormats = LocalizedFormats.UNABLE_TO_PERFORM_QR_DECOMPOSITION_ON_JACOBIAN;
                    Integer valueOf = Integer.valueOf(length);
                    Integer valueOf2 = Integer.valueOf(length2);
                    Object[] objArr = new Object[2];
                    objArr[c2] = valueOf;
                    objArr[1] = valueOf2;
                    throw new ConvergenceException(localizedFormats, objArr);
                }
                if (d4 > d3) {
                    i4 = i5;
                    d3 = d4;
                }
            }
            if (d3 <= this.qrRankingThreshold) {
                this.rank = i3;
                return;
            }
            int[] iArr = this.permutation;
            int i7 = iArr[i4];
            iArr[i4] = iArr[i3];
            iArr[i3] = i7;
            double d6 = this.weightedJacobian[i3][i7];
            int i8 = (d6 > 0.0d ? 1 : (d6 == 0.0d ? 0 : -1));
            double sqrt = FastMath.sqrt(d3);
            if (i8 > 0) {
                sqrt = -sqrt;
            }
            double d7 = 1.0d / (d3 - (d6 * sqrt));
            this.beta[i7] = d7;
            this.diagR[i7] = sqrt;
            double[] dArr = this.weightedJacobian[i3];
            dArr[i7] = dArr[i7] - sqrt;
            for (int i9 = (length2 - 1) - i3; i9 > 0; i9--) {
                double d8 = 0.0d;
                for (int i10 = i3; i10 < length; i10++) {
                    double[] dArr2 = this.weightedJacobian[i10];
                    d8 += dArr2[i7] * dArr2[this.permutation[i3 + i9]];
                }
                double d9 = d8 * d7;
                for (int i11 = i3; i11 < length; i11++) {
                    double[] dArr3 = this.weightedJacobian[i11];
                    int i12 = this.permutation[i3 + i9];
                    dArr3[i12] = dArr3[i12] - (dArr3[i7] * d9);
                }
            }
            i3++;
            c2 = 0;
        }
        this.rank = this.solvedCols;
    }

    private void qTy(double[] dArr) {
        double[][] dArr2 = this.weightedJacobian;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        for (int i = 0; i < length2; i++) {
            int i2 = this.permutation[i];
            double d = 0.0d;
            for (int i3 = i; i3 < length; i3++) {
                d += this.weightedJacobian[i3][i2] * dArr[i3];
            }
            double d2 = d * this.beta[i2];
            for (int i4 = i; i4 < length; i4++) {
                dArr[i4] = dArr[i4] - (this.weightedJacobian[i4][i2] * d2);
            }
        }
    }

    private void checkParameters() {
        if (getLowerBound() != null || getUpperBound() != null) {
            throw new MathUnsupportedOperationException(LocalizedFormats.CONSTRAINT, new Object[0]);
        }
    }
}
