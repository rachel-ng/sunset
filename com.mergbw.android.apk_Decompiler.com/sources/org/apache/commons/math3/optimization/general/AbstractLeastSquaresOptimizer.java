package org.apache.commons.math3.optimization.general;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.DifferentiableMultivariateVectorFunction;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.MultivariateDifferentiableVectorFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DiagonalMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.QRDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.optimization.ConvergenceChecker;
import org.apache.commons.math3.optimization.DifferentiableMultivariateVectorOptimizer;
import org.apache.commons.math3.optimization.InitialGuess;
import org.apache.commons.math3.optimization.OptimizationData;
import org.apache.commons.math3.optimization.PointVectorValuePair;
import org.apache.commons.math3.optimization.Target;
import org.apache.commons.math3.optimization.Weight;
import org.apache.commons.math3.optimization.direct.BaseAbstractMultivariateVectorOptimizer;
import org.apache.commons.math3.util.FastMath;

@Deprecated
public abstract class AbstractLeastSquaresOptimizer extends BaseAbstractMultivariateVectorOptimizer<DifferentiableMultivariateVectorFunction> implements DifferentiableMultivariateVectorOptimizer {
    @Deprecated
    private static final double DEFAULT_SINGULARITY_THRESHOLD = 1.0E-14d;
    @Deprecated
    protected int cols;
    @Deprecated
    protected double cost;
    private MultivariateDifferentiableVectorFunction jF;
    private int jacobianEvaluations;
    @Deprecated
    protected double[] objective;
    @Deprecated
    protected double[] point;
    @Deprecated
    protected int rows;
    private RealMatrix weightMatrixSqrt;
    @Deprecated
    protected double[][] weightedResidualJacobian;
    @Deprecated
    protected double[] weightedResiduals;

    @Deprecated
    protected AbstractLeastSquaresOptimizer() {
    }

    protected AbstractLeastSquaresOptimizer(ConvergenceChecker<PointVectorValuePair> convergenceChecker) {
        super(convergenceChecker);
    }

    public int getJacobianEvaluations() {
        return this.jacobianEvaluations;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void updateJacobian() {
        this.weightedResidualJacobian = computeWeightedJacobian(this.point).scalarMultiply(-1.0d).getData();
    }

    /* access modifiers changed from: protected */
    public RealMatrix computeWeightedJacobian(double[] dArr) {
        this.jacobianEvaluations++;
        DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[dArr.length];
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            derivativeStructureArr[i] = new DerivativeStructure(length, 1, i, dArr[i]);
        }
        DerivativeStructure[] value = this.jF.value(derivativeStructureArr);
        int length2 = getTarget().length;
        if (value.length == length2) {
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length2;
            double[][] dArr2 = (double[][]) Array.newInstance(Double.TYPE, iArr);
            for (int i2 = 0; i2 < length2; i2++) {
                int[] iArr2 = new int[length];
                for (int i3 = 0; i3 < length; i3++) {
                    iArr2[i3] = 1;
                    dArr2[i2][i3] = value[i2].getPartialDerivative(iArr2);
                    iArr2[i3] = 0;
                }
            }
            return this.weightMatrixSqrt.multiply(MatrixUtils.createRealMatrix(dArr2));
        }
        throw new DimensionMismatchException(value.length, length2);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void updateResidualsAndCost() {
        double[] computeObjectiveValue = computeObjectiveValue(this.point);
        this.objective = computeObjectiveValue;
        double[] computeResiduals = computeResiduals(computeObjectiveValue);
        this.cost = computeCost(computeResiduals);
        this.weightedResiduals = this.weightMatrixSqrt.operate((RealVector) new ArrayRealVector(computeResiduals)).toArray();
    }

    /* access modifiers changed from: protected */
    public double computeCost(double[] dArr) {
        ArrayRealVector arrayRealVector = new ArrayRealVector(dArr);
        return FastMath.sqrt(arrayRealVector.dotProduct(getWeight().operate((RealVector) arrayRealVector)));
    }

    public double getRMS() {
        return FastMath.sqrt(getChiSquare() / ((double) this.rows));
    }

    public double getChiSquare() {
        double d = this.cost;
        return d * d;
    }

    public RealMatrix getWeightSquareRoot() {
        return this.weightMatrixSqrt.copy();
    }

    /* access modifiers changed from: protected */
    public void setCost(double d) {
        this.cost = d;
    }

    @Deprecated
    public double[][] getCovariances() {
        return getCovariances(DEFAULT_SINGULARITY_THRESHOLD);
    }

    @Deprecated
    public double[][] getCovariances(double d) {
        return computeCovariances(this.point, d);
    }

    public double[][] computeCovariances(double[] dArr, double d) {
        RealMatrix computeWeightedJacobian = computeWeightedJacobian(dArr);
        return new QRDecomposition(computeWeightedJacobian.transpose().multiply(computeWeightedJacobian), d).getSolver().getInverse().getData();
    }

    @Deprecated
    public double[] guessParametersErrors() {
        int i = this.rows;
        int i2 = this.cols;
        if (i > i2) {
            double[] dArr = new double[i2];
            double sqrt = FastMath.sqrt(getChiSquare() / ((double) (this.rows - this.cols)));
            double[][] computeCovariances = computeCovariances(this.point, DEFAULT_SINGULARITY_THRESHOLD);
            for (int i3 = 0; i3 < i2; i3++) {
                dArr[i3] = FastMath.sqrt(computeCovariances[i3][i3]) * sqrt;
            }
            return dArr;
        }
        throw new NumberIsTooSmallException(LocalizedFormats.NO_DEGREES_OF_FREEDOM, Integer.valueOf(this.rows), Integer.valueOf(this.cols), false);
    }

    public double[] computeSigma(double[] dArr, double d) {
        int length = dArr.length;
        double[] dArr2 = new double[length];
        double[][] computeCovariances = computeCovariances(dArr, d);
        for (int i = 0; i < length; i++) {
            dArr2[i] = FastMath.sqrt(computeCovariances[i][i]);
        }
        return dArr2;
    }

    @Deprecated
    public PointVectorValuePair optimize(int i, DifferentiableMultivariateVectorFunction differentiableMultivariateVectorFunction, double[] dArr, double[] dArr2, double[] dArr3) {
        return optimizeInternal(i, FunctionUtils.toMultivariateDifferentiableVectorFunction(differentiableMultivariateVectorFunction), new Target(dArr), new Weight(dArr2), new InitialGuess(dArr3));
    }

    @Deprecated
    public PointVectorValuePair optimize(int i, MultivariateDifferentiableVectorFunction multivariateDifferentiableVectorFunction, double[] dArr, double[] dArr2, double[] dArr3) {
        return optimizeInternal(i, multivariateDifferentiableVectorFunction, new Target(dArr), new Weight(dArr2), new InitialGuess(dArr3));
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public PointVectorValuePair optimizeInternal(int i, MultivariateDifferentiableVectorFunction multivariateDifferentiableVectorFunction, OptimizationData... optimizationDataArr) {
        return super.optimizeInternal(i, FunctionUtils.toDifferentiableMultivariateVectorFunction(multivariateDifferentiableVectorFunction), optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public void setUp() {
        super.setUp();
        this.jacobianEvaluations = 0;
        this.weightMatrixSqrt = squareRoot(getWeight());
        this.jF = FunctionUtils.toMultivariateDifferentiableVectorFunction((DifferentiableMultivariateVectorFunction) getObjectiveFunction());
        this.point = getStartPoint();
        this.rows = getTarget().length;
        this.cols = this.point.length;
    }

    /* access modifiers changed from: protected */
    public double[] computeResiduals(double[] dArr) {
        double[] target = getTarget();
        if (dArr.length == target.length) {
            double[] dArr2 = new double[target.length];
            for (int i = 0; i < target.length; i++) {
                dArr2[i] = target[i] - dArr[i];
            }
            return dArr2;
        }
        throw new DimensionMismatchException(target.length, dArr.length);
    }

    private RealMatrix squareRoot(RealMatrix realMatrix) {
        if (!(realMatrix instanceof DiagonalMatrix)) {
            return new EigenDecomposition(realMatrix).getSquareRoot();
        }
        int rowDimension = realMatrix.getRowDimension();
        DiagonalMatrix diagonalMatrix = new DiagonalMatrix(rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            diagonalMatrix.setEntry(i, i, FastMath.sqrt(realMatrix.getEntry(i, i)));
        }
        return diagonalMatrix;
    }
}
