package org.apache.commons.math3.optimization.general;

import java.util.Arrays;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

@Deprecated
public class LevenbergMarquardtOptimizer extends AbstractLeastSquaresOptimizer {
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
        int i;
        double d2;
        RealMatrix realMatrix;
        double d3;
        double d4;
        double d5;
        double[] dArr;
        ConvergenceChecker<PointVectorValuePair> convergenceChecker;
        PointVectorValuePair pointVectorValuePair;
        int i2;
        int length = getTarget().length;
        double[] startPoint = getStartPoint();
        int length2 = startPoint.length;
        this.solvedCols = FastMath.min(length, length2);
        this.diagR = new double[length2];
        this.jacNorm = new double[length2];
        this.beta = new double[length2];
        this.permutation = new int[length2];
        this.lmDir = new double[length2];
        double[] dArr2 = new double[length2];
        double[] dArr3 = new double[length2];
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        RealMatrix weightSquareRoot = getWeightSquareRoot();
        double[] computeObjectiveValue = computeObjectiveValue(startPoint);
        double[] computeResiduals = computeResiduals(computeObjectiveValue);
        PointVectorValuePair pointVectorValuePair2 = new PointVectorValuePair(startPoint, computeObjectiveValue);
        double computeCost = computeCost(computeResiduals);
        double[] dArr6 = new double[length2];
        double[] dArr7 = new double[length2];
        this.lmPar = 0.0d;
        ConvergenceChecker<PointVectorValuePair> convergenceChecker2 = getConvergenceChecker();
        PointVectorValuePair pointVectorValuePair3 = pointVectorValuePair2;
        boolean z = true;
        double[] dArr8 = new double[length2];
        double d6 = 0.0d;
        double d7 = 0.0d;
        double[] dArr9 = new double[length];
        double d8 = computeCost;
        double[] dArr10 = computeObjectiveValue;
        double d9 = d8;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = i3 + 1;
            qrDecomposition(computeWeightedJacobian(startPoint));
            this.weightedResidual = weightSquareRoot.operate(computeResiduals);
            int i5 = 0;
            while (i5 < length) {
                dArr5[i5] = this.weightedResidual[i5];
                i5++;
                computeResiduals = computeResiduals;
            }
            double[] dArr11 = computeResiduals;
            qTy(dArr5);
            int i6 = 0;
            while (i6 < this.solvedCols) {
                int i7 = this.permutation[i6];
                this.weightedJacobian[i6][i7] = this.diagR[i7];
                i6++;
                dArr9 = dArr9;
                convergenceChecker2 = convergenceChecker2;
            }
            double[] dArr12 = dArr9;
            ConvergenceChecker<PointVectorValuePair> convergenceChecker3 = convergenceChecker2;
            if (z) {
                double d10 = 0.0d;
                for (int i8 = 0; i8 < length2; i8++) {
                    double d11 = this.jacNorm[i8];
                    if (d11 == 0.0d) {
                        d11 = 1.0d;
                    }
                    double d12 = startPoint[i8] * d11;
                    d10 += d12 * d12;
                    dArr2[i8] = d11;
                }
                d7 = FastMath.sqrt(d10);
                d = 0.0d;
                d6 = d7 == 0.0d ? this.initialStepBoundFactor : this.initialStepBoundFactor * d7;
            } else {
                d = 0.0d;
            }
            if (d9 != d) {
                double d13 = d;
                int i9 = 0;
                while (i9 < this.solvedCols) {
                    int i10 = this.permutation[i9];
                    RealMatrix realMatrix2 = weightSquareRoot;
                    double d14 = this.jacNorm[i10];
                    if (d14 != d) {
                        double d15 = d;
                        int i11 = 0;
                        while (i11 <= i9) {
                            d15 += this.weightedJacobian[i11][i10] * dArr5[i11];
                            i11++;
                            length = length;
                        }
                        i2 = length;
                        d13 = FastMath.max(d13, FastMath.abs(d15) / (d14 * d9));
                    } else {
                        i2 = length;
                    }
                    i9++;
                    weightSquareRoot = realMatrix2;
                    length = i2;
                }
                realMatrix = weightSquareRoot;
                i = length;
                d2 = d13;
            } else {
                realMatrix = weightSquareRoot;
                i = length;
                d2 = d;
            }
            if (d2 <= this.orthoTolerance) {
                setCost(d9);
                this.point = pointVectorValuePair3.getPoint();
                return pointVectorValuePair3;
            }
            int i12 = 0;
            while (i12 < length2) {
                dArr2[i12] = FastMath.max(dArr2[i12], this.jacNorm[i12]);
                i12++;
                d9 = d9;
            }
            double d16 = d9;
            PointVectorValuePair pointVectorValuePair4 = pointVectorValuePair3;
            double[] dArr13 = dArr10;
            double d17 = d;
            computeResiduals = dArr11;
            double[] dArr14 = dArr12;
            while (d17 < 1.0E-4d) {
                for (int i13 = 0; i13 < this.solvedCols; i13++) {
                    int i14 = this.permutation[i13];
                    dArr3[i14] = startPoint[i14];
                }
                double[] dArr15 = this.weightedResidual;
                this.weightedResidual = dArr14;
                ConvergenceChecker<PointVectorValuePair> convergenceChecker4 = convergenceChecker3;
                double[] dArr16 = dArr5;
                RealMatrix realMatrix3 = realMatrix;
                ConvergenceChecker<PointVectorValuePair> convergenceChecker5 = convergenceChecker4;
                double[] dArr17 = dArr6;
                double d18 = d;
                double[] dArr18 = dArr13;
                double[] dArr19 = dArr15;
                PointVectorValuePair pointVectorValuePair5 = pointVectorValuePair3;
                determineLMParameter(dArr5, d6, dArr2, dArr8, dArr7, dArr17);
                double d19 = d18;
                for (int i15 = 0; i15 < this.solvedCols; i15++) {
                    int i16 = this.permutation[i15];
                    double[] dArr20 = this.lmDir;
                    double d20 = -dArr20[i16];
                    dArr20[i16] = d20;
                    startPoint[i16] = dArr3[i16] + d20;
                    double d21 = dArr2[i16] * dArr20[i16];
                    d19 += d21 * d21;
                }
                double sqrt = FastMath.sqrt(d19);
                double d22 = d6;
                if (z) {
                    d22 = FastMath.min(d22, sqrt);
                }
                double[] computeObjectiveValue2 = computeObjectiveValue(startPoint);
                double[] computeResiduals2 = computeResiduals(computeObjectiveValue2);
                PointVectorValuePair pointVectorValuePair6 = new PointVectorValuePair(startPoint, computeObjectiveValue2);
                double computeCost2 = computeCost(computeResiduals2);
                double d23 = 0.1d;
                double d24 = computeCost2 * 0.1d;
                if (d24 < d16) {
                    double d25 = computeCost2 / d16;
                    d3 = 1.0d - (d25 * d25);
                } else {
                    d3 = -1.0d;
                }
                double[] dArr21 = computeObjectiveValue2;
                double[] dArr22 = computeResiduals2;
                int i17 = 0;
                while (i17 < this.solvedCols) {
                    int i18 = this.permutation[i17];
                    double[] dArr23 = dArr3;
                    double d26 = this.lmDir[i18];
                    dArr8[i17] = d18;
                    int i19 = 0;
                    while (i19 <= i17) {
                        dArr8[i19] = dArr8[i19] + (this.weightedJacobian[i19][i18] * d26);
                        i19++;
                        computeCost2 = computeCost2;
                    }
                    double d27 = computeCost2;
                    i17++;
                    dArr3 = dArr23;
                }
                double d28 = computeCost2;
                double[] dArr24 = dArr3;
                double d29 = d18;
                for (int i20 = 0; i20 < this.solvedCols; i20++) {
                    double d30 = dArr8[i20];
                    d29 += d30 * d30;
                }
                double d31 = d16 * d16;
                double d32 = d29 / d31;
                ConvergenceChecker<PointVectorValuePair> convergenceChecker6 = convergenceChecker5;
                double d33 = this.lmPar;
                double d34 = ((d33 * sqrt) * sqrt) / d31;
                double d35 = d32 + (d34 * 2.0d);
                double d36 = -(d32 + d34);
                double d37 = d35 == d18 ? d18 : d3 / d35;
                double d38 = 0.5d;
                if (d37 <= 0.25d) {
                    if (d3 < d18) {
                        d38 = (d36 * 0.5d) / (d36 + (0.5d * d3));
                    }
                    if (d24 < d16 && d38 >= 0.1d) {
                        d23 = d38;
                    }
                    d4 = FastMath.min(d22, sqrt * 10.0d) * d23;
                    this.lmPar /= d23;
                } else if (d33 == d18 || d37 >= 0.75d) {
                    d4 = sqrt * 2.0d;
                    this.lmPar = d33 * 0.5d;
                } else {
                    d4 = d22;
                }
                if (d37 >= 1.0E-4d) {
                    double d39 = d18;
                    for (int i21 = 0; i21 < length2; i21++) {
                        double d40 = dArr2[i21] * startPoint[i21];
                        d39 += d40 * d40;
                    }
                    d7 = FastMath.sqrt(d39);
                    pointVectorValuePair = pointVectorValuePair6;
                    convergenceChecker = convergenceChecker6;
                    if (convergenceChecker6 != null) {
                        d5 = d28;
                        if (convergenceChecker.converged(i4, pointVectorValuePair5, pointVectorValuePair)) {
                            setCost(d5);
                            this.point = pointVectorValuePair.getPoint();
                            return pointVectorValuePair;
                        }
                    } else {
                        d5 = d28;
                    }
                    z = false;
                    dArr = dArr21;
                } else {
                    convergenceChecker = convergenceChecker6;
                    for (int i22 = 0; i22 < this.solvedCols; i22++) {
                        int i23 = this.permutation[i22];
                        startPoint[i23] = dArr24[i23];
                    }
                    double[] dArr25 = this.weightedResidual;
                    this.weightedResidual = dArr19;
                    dArr = dArr18;
                    dArr19 = dArr25;
                    pointVectorValuePair = new PointVectorValuePair(startPoint, dArr);
                    d5 = d16;
                }
                double abs = FastMath.abs(d3);
                double d41 = this.costRelativeTolerance;
                if ((abs > d41 || d35 > d41 || d37 > 2.0d) && d4 > this.parRelativeTolerance * d7) {
                    if (FastMath.abs(d3) <= 2.2204E-16d && d35 <= 2.2204E-16d && d37 <= 2.0d) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_COST_RELATIVE_TOLERANCE, Double.valueOf(this.costRelativeTolerance));
                    } else if (d4 <= d7 * 2.2204E-16d) {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_PARAMETERS_RELATIVE_TOLERANCE, Double.valueOf(this.parRelativeTolerance));
                    } else if (d2 > 2.2204E-16d) {
                        d6 = d4;
                        pointVectorValuePair4 = pointVectorValuePair;
                        d16 = d5;
                        d = d18;
                        dArr5 = dArr16;
                        dArr14 = dArr19;
                        computeResiduals = dArr22;
                        dArr3 = dArr24;
                        d17 = d37;
                        convergenceChecker3 = convergenceChecker;
                        realMatrix = realMatrix3;
                        dArr6 = dArr17;
                        PointVectorValuePair pointVectorValuePair7 = pointVectorValuePair5;
                        dArr13 = dArr;
                        pointVectorValuePair3 = pointVectorValuePair7;
                    } else {
                        throw new ConvergenceException(LocalizedFormats.TOO_SMALL_ORTHOGONALITY_TOLERANCE, Double.valueOf(this.orthoTolerance));
                    }
                }
            }
            double[] dArr26 = dArr6;
            double d42 = d;
            double d43 = d6;
            convergenceChecker2 = convergenceChecker3;
            dArr10 = dArr13;
            dArr6 = dArr26;
            weightSquareRoot = realMatrix;
            length = i;
            pointVectorValuePair3 = pointVectorValuePair4;
            i3 = i4;
            dArr9 = dArr14;
            d9 = d16;
        }
        setCost(d5);
        this.point = pointVectorValuePair.getPoint();
        return pointVectorValuePair;
    }

    private void determineLMParameter(double[] dArr, double d, double[] dArr2, double[] dArr3, double[] dArr4, double[] dArr5) {
        int i;
        double d2;
        double d3;
        int i2;
        double d4;
        double[] dArr6 = dArr;
        double d5 = d;
        double[] dArr7 = dArr3;
        double[] dArr8 = dArr4;
        double[] dArr9 = dArr5;
        int i3 = 0;
        int length = this.weightedJacobian[0].length;
        int i4 = 0;
        while (true) {
            i = this.rank;
            if (i4 >= i) {
                break;
            }
            this.lmDir[this.permutation[i4]] = dArr6[i4];
            i4++;
        }
        while (i < length) {
            this.lmDir[this.permutation[i]] = 0.0d;
            i++;
        }
        int i5 = this.rank - 1;
        while (i5 >= 0) {
            int i6 = this.permutation[i5];
            double d6 = this.lmDir[i6] / this.diagR[i6];
            for (int i7 = i3; i7 < i5; i7++) {
                double[] dArr10 = this.lmDir;
                int i8 = this.permutation[i7];
                dArr10[i8] = dArr10[i8] - (this.weightedJacobian[i7][i6] * d6);
            }
            this.lmDir[i6] = d6;
            i5--;
            i3 = 0;
        }
        double d7 = 0.0d;
        for (int i9 = 0; i9 < this.solvedCols; i9++) {
            int i10 = this.permutation[i9];
            double d8 = dArr2[i10] * this.lmDir[i10];
            dArr7[i10] = d8;
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
            for (int i11 = 0; i11 < this.solvedCols; i11++) {
                int i12 = this.permutation[i11];
                dArr7[i12] = dArr7[i12] * (dArr2[i12] / sqrt);
            }
            d2 = d10;
            double d11 = 0.0d;
            int i13 = 0;
            while (i13 < this.solvedCols) {
                int i14 = this.permutation[i13];
                int i15 = 0;
                double d12 = 0.0d;
                while (i15 < i13) {
                    d12 += this.weightedJacobian[i15][i14] * dArr7[this.permutation[i15]];
                    i15++;
                    double[] dArr11 = dArr4;
                }
                double d13 = (dArr7[i14] - d12) / this.diagR[i14];
                dArr7[i14] = d13;
                d11 += d13 * d13;
                i13++;
                double[] dArr12 = dArr4;
            }
            d3 = d9 / (d11 * d5);
        } else {
            d2 = d10;
            d3 = 0.0d;
        }
        int i16 = 0;
        double d14 = 0.0d;
        while (i16 < this.solvedCols) {
            int i17 = this.permutation[i16];
            double d15 = d9;
            double d16 = 0.0d;
            for (int i18 = 0; i18 <= i16; i18++) {
                d16 += this.weightedJacobian[i18][i17] * dArr6[i18];
            }
            double d17 = d16 / dArr2[i17];
            d14 += d17 * d17;
            i16++;
            d9 = d15;
        }
        double d18 = d9;
        double sqrt2 = FastMath.sqrt(d14);
        double d19 = sqrt2 / d5;
        double d20 = 0.0d;
        double min = d19 == 0.0d ? 2.2251E-308d / FastMath.min(d5, 0.1d) : d19;
        double min2 = FastMath.min(min, FastMath.max(this.lmPar, d3));
        this.lmPar = min2;
        if (min2 == 0.0d) {
            this.lmPar = sqrt2 / sqrt;
        }
        int i19 = 10;
        double d21 = d18;
        while (i19 >= 0) {
            if (this.lmPar == d20) {
                i2 = i19;
                this.lmPar = FastMath.max(2.2251E-308d, 0.001d * min);
            } else {
                i2 = i19;
            }
            double sqrt3 = FastMath.sqrt(this.lmPar);
            for (int i20 = 0; i20 < this.solvedCols; i20++) {
                int i21 = this.permutation[i20];
                dArr7[i21] = dArr2[i21] * sqrt3;
            }
            double[] dArr13 = dArr4;
            double[] dArr14 = dArr5;
            determineLMDirection(dArr6, dArr7, dArr13, dArr14);
            int i22 = i2;
            int i23 = 0;
            double d22 = 0.0d;
            while (i23 < this.solvedCols) {
                int i24 = this.permutation[i23];
                double d23 = dArr2[i24] * this.lmDir[i24];
                dArr14[i24] = d23;
                d22 += d23 * d23;
                i23++;
                min = min;
            }
            double d24 = min;
            double sqrt4 = FastMath.sqrt(d22);
            double d25 = sqrt4 - d5;
            if (FastMath.abs(d25) <= d2) {
                return;
            }
            if (d3 != 0.0d || d25 > d21 || d21 >= 0.0d) {
                for (int i25 = 0; i25 < this.solvedCols; i25++) {
                    int i26 = this.permutation[i25];
                    dArr7[i26] = (dArr14[i26] * dArr2[i26]) / sqrt4;
                }
                int i27 = 0;
                while (i27 < this.solvedCols) {
                    int i28 = this.permutation[i27];
                    double d26 = dArr7[i28] / dArr13[i27];
                    dArr7[i28] = d26;
                    i27++;
                    int i29 = i27;
                    while (i29 < this.solvedCols) {
                        int i30 = this.permutation[i29];
                        dArr7[i30] = dArr7[i30] - (this.weightedJacobian[i29][i28] * d26);
                        i29++;
                        i27 = i27;
                    }
                    int i31 = i27;
                }
                double d27 = 0.0d;
                for (int i32 = 0; i32 < this.solvedCols; i32++) {
                    double d28 = dArr7[this.permutation[i32]];
                    d27 += d28 * d28;
                }
                double d29 = d25 / (d27 * d5);
                if (d25 > 0.0d) {
                    d3 = FastMath.max(d3, this.lmPar);
                } else if (d25 < 0.0d) {
                    d4 = FastMath.min(d24, this.lmPar);
                    this.lmPar = FastMath.max(d3, this.lmPar + d29);
                    min = d4;
                    i19 = i22 - 1;
                    d21 = d25;
                    d20 = 0.0d;
                    dArr6 = dArr;
                    d5 = d;
                }
                d4 = d24;
                this.lmPar = FastMath.max(d3, this.lmPar + d29);
                min = d4;
                i19 = i22 - 1;
                d21 = d25;
                d20 = 0.0d;
                dArr6 = dArr;
                d5 = d;
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
}
