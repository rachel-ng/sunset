package org.apache.commons.math3.optim;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;

public abstract class BaseMultivariateOptimizer<PAIR> extends BaseOptimizer<PAIR> {
    private double[] lowerBound;
    private double[] start;
    private double[] upperBound;

    protected BaseMultivariateOptimizer(ConvergenceChecker<PAIR> convergenceChecker) {
        super(convergenceChecker);
    }

    public PAIR optimize(OptimizationData... optimizationDataArr) {
        return super.optimize(optimizationDataArr);
    }

    /* access modifiers changed from: protected */
    public void parseOptimizationData(OptimizationData... optimizationDataArr) {
        super.parseOptimizationData(optimizationDataArr);
        for (InitialGuess initialGuess : optimizationDataArr) {
            if (initialGuess instanceof InitialGuess) {
                this.start = initialGuess.getInitialGuess();
            } else if (initialGuess instanceof SimpleBounds) {
                SimpleBounds simpleBounds = (SimpleBounds) initialGuess;
                this.lowerBound = simpleBounds.getLower();
                this.upperBound = simpleBounds.getUpper();
            }
        }
        checkParameters();
    }

    public double[] getStartPoint() {
        double[] dArr = this.start;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getLowerBound() {
        double[] dArr = this.lowerBound;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getUpperBound() {
        double[] dArr = this.upperBound;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    private void checkParameters() {
        double[] dArr = this.start;
        if (dArr != null) {
            int length = dArr.length;
            double[] dArr2 = this.lowerBound;
            int i = 0;
            if (dArr2 != null) {
                if (dArr2.length == length) {
                    int i2 = 0;
                    while (i2 < length) {
                        double d = this.start[i2];
                        double d2 = this.lowerBound[i2];
                        if (d >= d2) {
                            i2++;
                        } else {
                            throw new NumberIsTooSmallException(Double.valueOf(d), Double.valueOf(d2), true);
                        }
                    }
                } else {
                    throw new DimensionMismatchException(this.lowerBound.length, length);
                }
            }
            double[] dArr3 = this.upperBound;
            if (dArr3 == null) {
                return;
            }
            if (dArr3.length == length) {
                while (i < length) {
                    double d3 = this.start[i];
                    double d4 = this.upperBound[i];
                    if (d3 <= d4) {
                        i++;
                    } else {
                        throw new NumberIsTooLargeException(Double.valueOf(d3), Double.valueOf(d4), true);
                    }
                }
                return;
            }
            throw new DimensionMismatchException(this.upperBound.length, length);
        }
    }
}
