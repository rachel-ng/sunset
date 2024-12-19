package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;

public class RegressionResults implements Serializable {
    private static final int ADJRSQ_IDX = 4;
    private static final int MSE_IDX = 3;
    private static final int RSQ_IDX = 2;
    private static final int SSE_IDX = 0;
    private static final int SST_IDX = 1;
    private static final long serialVersionUID = 1;
    private final boolean containsConstant;
    private final double[] globalFitInfo;
    private final boolean isSymmetricVCD;
    private final long nobs;
    private final double[] parameters;
    private final int rank;
    private final double[][] varCovData;

    private RegressionResults() {
        this.parameters = null;
        double[][] dArr = null;
        this.varCovData = null;
        this.rank = -1;
        this.nobs = -1;
        this.containsConstant = false;
        this.isSymmetricVCD = false;
        this.globalFitInfo = null;
    }

    public RegressionResults(double[] dArr, double[][] dArr2, boolean z, long j, int i, double d, double d2, double d3, boolean z2, boolean z3) {
        double[][] dArr3 = dArr2;
        long j2 = j;
        int i2 = i;
        boolean z4 = z2;
        if (z3) {
            this.parameters = MathArrays.copyOf(dArr);
            this.varCovData = new double[dArr3.length][];
            for (int i3 = 0; i3 < dArr3.length; i3++) {
                this.varCovData[i3] = MathArrays.copyOf(dArr3[i3]);
            }
        } else {
            this.parameters = dArr;
            this.varCovData = dArr3;
        }
        this.isSymmetricVCD = z;
        this.nobs = j2;
        this.rank = i2;
        this.containsConstant = z4;
        double[] dArr4 = new double[5];
        this.globalFitInfo = dArr4;
        Arrays.fill(dArr4, Double.NaN);
        if (i2 > 0) {
            dArr4[1] = z4 ? d2 - ((d * d) / ((double) j2)) : d2;
        }
        dArr4[0] = d3;
        double d4 = (double) (j2 - ((long) i2));
        dArr4[3] = d3 / d4;
        double d5 = dArr4[1];
        double d6 = 1.0d - (d3 / d5);
        dArr4[2] = d6;
        if (!z4) {
            dArr4[4] = 1.0d - ((1.0d - d6) * (((double) j2) / d4));
        } else {
            dArr4[4] = 1.0d - (((((double) j2) - 1.0d) * d3) / (d5 * d4));
        }
    }

    public double getParameterEstimate(int i) throws OutOfRangeException {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return Double.NaN;
        }
        if (i >= 0 && i < dArr.length) {
            return dArr[i];
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.parameters.length - 1));
    }

    public double[] getParameterEstimates() {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return null;
        }
        return MathArrays.copyOf(dArr);
    }

    public double getStdErrorOfEstimate(int i) throws OutOfRangeException {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return Double.NaN;
        }
        if (i < 0 || i >= dArr.length) {
            throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.parameters.length - 1));
        }
        double vcvElement = getVcvElement(i, i);
        if (Double.isNaN(vcvElement) || vcvElement <= Double.MIN_VALUE) {
            return Double.NaN;
        }
        return FastMath.sqrt(vcvElement);
    }

    public double[] getStdErrorOfEstimates() {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return null;
        }
        double[] dArr2 = new double[dArr.length];
        for (int i = 0; i < this.parameters.length; i++) {
            double vcvElement = getVcvElement(i, i);
            if (Double.isNaN(vcvElement) || vcvElement <= Double.MIN_VALUE) {
                dArr2[i] = Double.NaN;
            } else {
                dArr2[i] = FastMath.sqrt(vcvElement);
            }
        }
        return dArr2;
    }

    public double getCovarianceOfParameters(int i, int i2) throws OutOfRangeException {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return Double.NaN;
        }
        if (i < 0 || i >= dArr.length) {
            throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.parameters.length - 1));
        } else if (i2 >= 0 && i2 < dArr.length) {
            return getVcvElement(i, i2);
        } else {
            throw new OutOfRangeException(Integer.valueOf(i2), 0, Integer.valueOf(this.parameters.length - 1));
        }
    }

    public int getNumberOfParameters() {
        double[] dArr = this.parameters;
        if (dArr == null) {
            return -1;
        }
        return dArr.length;
    }

    public long getN() {
        return this.nobs;
    }

    public double getTotalSumSquares() {
        return this.globalFitInfo[1];
    }

    public double getRegressionSumSquares() {
        double[] dArr = this.globalFitInfo;
        return dArr[1] - dArr[0];
    }

    public double getErrorSumSquares() {
        return this.globalFitInfo[0];
    }

    public double getMeanSquareError() {
        return this.globalFitInfo[3];
    }

    public double getRSquared() {
        return this.globalFitInfo[2];
    }

    public double getAdjustedRSquared() {
        return this.globalFitInfo[4];
    }

    public boolean hasIntercept() {
        return this.containsConstant;
    }

    private double getVcvElement(int i, int i2) {
        if (!this.isSymmetricVCD) {
            return this.varCovData[i][i2];
        }
        double[][] dArr = this.varCovData;
        if (dArr.length > 1) {
            if (i == i2) {
                return dArr[i][i];
            }
            double[] dArr2 = dArr[i2];
            if (i >= dArr2.length) {
                return dArr[i][i2];
            }
            return dArr2[i];
        } else if (i > i2) {
            return dArr[0][(((i + 1) * i) / 2) + i2];
        } else {
            return dArr[0][(((i2 + 1) * i2) / 2) + i];
        }
    }
}
