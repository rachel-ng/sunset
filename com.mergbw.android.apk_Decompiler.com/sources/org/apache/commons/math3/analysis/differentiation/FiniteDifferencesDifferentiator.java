package org.apache.commons.math3.analysis.differentiation;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateMatrixFunction;
import org.apache.commons.math3.analysis.UnivariateVectorFunction;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.util.FastMath;

public class FiniteDifferencesDifferentiator implements UnivariateFunctionDifferentiator, UnivariateVectorFunctionDifferentiator, UnivariateMatrixFunctionDifferentiator, Serializable {
    private static final long serialVersionUID = 20120917;
    /* access modifiers changed from: private */
    public final double halfSampleSpan;
    /* access modifiers changed from: private */
    public final int nbPoints;
    /* access modifiers changed from: private */
    public final double stepSize;
    /* access modifiers changed from: private */
    public final double tMax;
    /* access modifiers changed from: private */
    public final double tMin;

    public FiniteDifferencesDifferentiator(int i, double d) throws NotPositiveException, NumberIsTooSmallException {
        this(i, d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public FiniteDifferencesDifferentiator(int i, double d, double d2, double d3) throws NotPositiveException, NumberIsTooSmallException, NumberIsTooLargeException {
        if (i > 1) {
            this.nbPoints = i;
            if (d > 0.0d) {
                this.stepSize = d;
                double d4 = d * 0.5d * ((double) (i - 1));
                this.halfSampleSpan = d4;
                double d5 = d3 - d2;
                if (d4 * 2.0d < d5) {
                    double ulp = FastMath.ulp(d4);
                    this.tMin = d2 + d4 + ulp;
                    this.tMax = (d3 - d4) - ulp;
                    return;
                }
                throw new NumberIsTooLargeException(Double.valueOf(d4 * 2.0d), Double.valueOf(d5), false);
            }
            throw new NotPositiveException(Double.valueOf(d));
        }
        throw new NumberIsTooSmallException(Double.valueOf(d), 1, false);
    }

    public int getNbPoints() {
        return this.nbPoints;
    }

    public double getStepSize() {
        return this.stepSize;
    }

    /* access modifiers changed from: private */
    public DerivativeStructure evaluate(DerivativeStructure derivativeStructure, double d, double[] dArr) throws NumberIsTooLargeException {
        int i = this.nbPoints;
        double[] dArr2 = new double[i];
        double[] dArr3 = new double[i];
        for (int i2 = 0; i2 < this.nbPoints; i2++) {
            dArr3[i2] = dArr[i2];
            for (int i3 = 1; i3 <= i2; i3++) {
                int i4 = i2 - i3;
                dArr3[i4] = (dArr3[i4 + 1] - dArr3[i4]) / (((double) i3) * this.stepSize);
            }
            dArr2[i2] = dArr3[0];
        }
        int order = derivativeStructure.getOrder();
        int freeParameters = derivativeStructure.getFreeParameters();
        double[] allDerivatives = derivativeStructure.getAllDerivatives();
        double value = derivativeStructure.getValue() - d;
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(freeParameters, order, 0.0d);
        DerivativeStructure derivativeStructure3 = null;
        for (int i5 = 0; i5 < this.nbPoints; i5++) {
            if (i5 == 0) {
                derivativeStructure3 = new DerivativeStructure(freeParameters, order, 1.0d);
            } else {
                allDerivatives[0] = value - (((double) (i5 - 1)) * this.stepSize);
                derivativeStructure3 = derivativeStructure3.multiply(new DerivativeStructure(freeParameters, order, allDerivatives));
            }
            derivativeStructure2 = derivativeStructure2.add(derivativeStructure3.multiply(dArr2[i5]));
        }
        return derivativeStructure2;
    }

    public UnivariateDifferentiableFunction differentiate(final UnivariateFunction univariateFunction) {
        return new UnivariateDifferentiableFunction() {
            public double value(double d) throws MathIllegalArgumentException {
                return univariateFunction.value(d);
            }

            public DerivativeStructure value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() < FiniteDifferencesDifferentiator.this.nbPoints) {
                    double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                    double[] dArr = new double[FiniteDifferencesDifferentiator.this.nbPoints];
                    for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                        dArr[i] = univariateFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                    }
                    return FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr);
                }
                throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
            }
        };
    }

    public UnivariateDifferentiableVectorFunction differentiate(final UnivariateVectorFunction univariateVectorFunction) {
        return new UnivariateDifferentiableVectorFunction() {
            public double[] value(double d) throws MathIllegalArgumentException {
                return univariateVectorFunction.value(d);
            }

            public DerivativeStructure[] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() < FiniteDifferencesDifferentiator.this.nbPoints) {
                    double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                    double[][] dArr = null;
                    double[][] dArr2 = null;
                    for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                        double[] value = univariateVectorFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                        if (i == 0) {
                            int length = value.length;
                            int[] iArr = new int[2];
                            iArr[1] = FiniteDifferencesDifferentiator.this.nbPoints;
                            iArr[0] = length;
                            dArr = (double[][]) Array.newInstance(Double.TYPE, iArr);
                        }
                        for (int i2 = 0; i2 < value.length; i2++) {
                            dArr[i2][i] = value[i2];
                        }
                    }
                    int length2 = dArr.length;
                    DerivativeStructure[] derivativeStructureArr = new DerivativeStructure[length2];
                    for (int i3 = 0; i3 < length2; i3++) {
                        derivativeStructureArr[i3] = FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr[i3]);
                    }
                    return derivativeStructureArr;
                }
                throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
            }
        };
    }

    public UnivariateDifferentiableMatrixFunction differentiate(final UnivariateMatrixFunction univariateMatrixFunction) {
        return new UnivariateDifferentiableMatrixFunction() {
            public double[][] value(double d) throws MathIllegalArgumentException {
                return univariateMatrixFunction.value(d);
            }

            public DerivativeStructure[][] value(DerivativeStructure derivativeStructure) throws MathIllegalArgumentException {
                if (derivativeStructure.getOrder() < FiniteDifferencesDifferentiator.this.nbPoints) {
                    double max = FastMath.max(FastMath.min(derivativeStructure.getValue(), FiniteDifferencesDifferentiator.this.tMax), FiniteDifferencesDifferentiator.this.tMin) - FiniteDifferencesDifferentiator.this.halfSampleSpan;
                    double[][][] dArr = null;
                    double[][][] dArr2 = null;
                    for (int i = 0; i < FiniteDifferencesDifferentiator.this.nbPoints; i++) {
                        double[][] value = univariateMatrixFunction.value((((double) i) * FiniteDifferencesDifferentiator.this.stepSize) + max);
                        if (i == 0) {
                            int length = value.length;
                            int length2 = value[0].length;
                            int[] iArr = new int[3];
                            iArr[2] = FiniteDifferencesDifferentiator.this.nbPoints;
                            iArr[1] = length2;
                            iArr[0] = length;
                            dArr = (double[][][]) Array.newInstance(Double.TYPE, iArr);
                        }
                        for (int i2 = 0; i2 < value.length; i2++) {
                            int i3 = 0;
                            while (true) {
                                double[] dArr3 = value[i2];
                                if (i3 >= dArr3.length) {
                                    break;
                                }
                                dArr[i2][i3][i] = dArr3[i3];
                                i3++;
                            }
                        }
                    }
                    int length3 = dArr.length;
                    int[] iArr2 = new int[2];
                    iArr2[1] = dArr[0].length;
                    iArr2[0] = length3;
                    DerivativeStructure[][] derivativeStructureArr = (DerivativeStructure[][]) Array.newInstance(DerivativeStructure.class, iArr2);
                    for (int i4 = 0; i4 < derivativeStructureArr.length; i4++) {
                        int i5 = 0;
                        while (true) {
                            double[][] dArr4 = dArr[i4];
                            if (i5 >= dArr4.length) {
                                break;
                            }
                            derivativeStructureArr[i4][i5] = FiniteDifferencesDifferentiator.this.evaluate(derivativeStructure, max, dArr4[i5]);
                            i5++;
                        }
                    }
                    return derivativeStructureArr;
                }
                throw new NumberIsTooLargeException(Integer.valueOf(derivativeStructure.getOrder()), Integer.valueOf(FiniteDifferencesDifferentiator.this.nbPoints), false);
            }
        };
    }
}
