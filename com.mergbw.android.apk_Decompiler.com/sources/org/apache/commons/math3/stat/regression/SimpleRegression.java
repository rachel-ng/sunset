package org.apache.commons.math3.stat.regression;

import java.io.Serializable;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

public class SimpleRegression implements Serializable, UpdatingMultipleLinearRegression {
    private static final long serialVersionUID = -3004689053607543335L;
    private final boolean hasIntercept;
    private long n;
    private double sumX;
    private double sumXX;
    private double sumXY;
    private double sumY;
    private double sumYY;
    private double xbar;
    private double ybar;

    public SimpleRegression() {
        this(true);
    }

    public SimpleRegression(boolean z) {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0;
        this.xbar = 0.0d;
        this.ybar = 0.0d;
        this.hasIntercept = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addData(double r22, double r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r3 = r24
            long r5 = r0.n
            r7 = 0
            int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r7 != 0) goto L_0x0013
            r0.xbar = r1
            r0.ybar = r3
            goto L_0x0051
        L_0x0013:
            boolean r7 = r0.hasIntercept
            if (r7 == 0) goto L_0x0051
            double r7 = (double) r5
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            double r7 = r7 + r9
            double r11 = (double) r5
            double r13 = (double) r5
            double r13 = r13 + r9
            double r11 = r11 / r13
            double r9 = r0.xbar
            double r13 = r1 - r9
            r15 = r5
            double r5 = r0.ybar
            double r17 = r3 - r5
            double r3 = r0.sumXX
            double r19 = r13 * r13
            double r19 = r19 * r11
            double r3 = r3 + r19
            r0.sumXX = r3
            double r3 = r0.sumYY
            double r19 = r17 * r17
            double r19 = r19 * r11
            double r3 = r3 + r19
            r0.sumYY = r3
            double r3 = r0.sumXY
            double r19 = r13 * r17
            double r19 = r19 * r11
            double r3 = r3 + r19
            r0.sumXY = r3
            double r13 = r13 / r7
            double r9 = r9 + r13
            r0.xbar = r9
            double r17 = r17 / r7
            double r5 = r5 + r17
            r0.ybar = r5
            goto L_0x0052
        L_0x0051:
            r15 = r5
        L_0x0052:
            boolean r3 = r0.hasIntercept
            if (r3 != 0) goto L_0x006b
            double r3 = r0.sumXX
            double r5 = r1 * r1
            double r3 = r3 + r5
            r0.sumXX = r3
            double r3 = r0.sumYY
            double r5 = r24 * r24
            double r3 = r3 + r5
            r0.sumYY = r3
            double r3 = r0.sumXY
            double r5 = r1 * r24
            double r3 = r3 + r5
            r0.sumXY = r3
        L_0x006b:
            double r3 = r0.sumX
            double r3 = r3 + r1
            r0.sumX = r3
            double r1 = r0.sumY
            double r1 = r1 + r24
            r0.sumY = r1
            r1 = 1
            long r5 = r15 + r1
            r0.n = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.stat.regression.SimpleRegression.addData(double, double):void");
    }

    public void append(SimpleRegression simpleRegression) {
        long j;
        SimpleRegression simpleRegression2 = simpleRegression;
        long j2 = this.n;
        if (j2 == 0) {
            this.xbar = simpleRegression2.xbar;
            this.ybar = simpleRegression2.ybar;
            this.sumXX = simpleRegression2.sumXX;
            this.sumYY = simpleRegression2.sumYY;
            this.sumXY = simpleRegression2.sumXY;
            j = j2;
        } else if (this.hasIntercept) {
            long j3 = simpleRegression2.n;
            double d = ((double) j3) / ((double) (j3 + j2));
            double d2 = ((double) (j2 * j3)) / ((double) (j3 + j2));
            double d3 = simpleRegression2.xbar;
            double d4 = this.xbar;
            double d5 = d3 - d4;
            double d6 = simpleRegression2.ybar;
            double d7 = this.ybar;
            double d8 = d6 - d7;
            j = j2;
            this.sumXX += simpleRegression2.sumXX + (d5 * d5 * d2);
            this.sumYY += simpleRegression2.sumYY + (d8 * d8 * d2);
            this.sumXY += simpleRegression2.sumXY + (d5 * d8 * d2);
            this.xbar = d4 + (d5 * d);
            this.ybar = d7 + (d8 * d);
        } else {
            j = j2;
            this.sumXX += simpleRegression2.sumXX;
            this.sumYY += simpleRegression2.sumYY;
            this.sumXY += simpleRegression2.sumXY;
        }
        this.sumX += simpleRegression2.sumX;
        this.sumY += simpleRegression2.sumY;
        this.n = j + simpleRegression2.n;
    }

    public void removeData(double d, double d2) {
        long j = this.n;
        if (j > 0) {
            if (this.hasIntercept) {
                double d3 = ((double) j) - 1.0d;
                double d4 = ((double) j) / (((double) j) - 1.0d);
                double d5 = this.xbar;
                double d6 = d - d5;
                double d7 = this.ybar;
                double d8 = d2 - d7;
                this.sumXX -= (d6 * d6) * d4;
                this.sumYY -= (d8 * d8) * d4;
                this.sumXY -= (d6 * d8) * d4;
                this.xbar = d5 - (d6 / d3);
                this.ybar = d7 - (d8 / d3);
                j = j;
            } else {
                double d9 = ((double) j) - 1.0d;
                this.sumXX -= d * d;
                this.sumYY -= d2 * d2;
                this.sumXY -= d * d2;
                this.xbar -= d / d9;
                this.ybar -= d2 / d9;
            }
            this.sumX -= d;
            this.sumY -= d2;
            this.n = j - 1;
        }
    }

    public void addData(double[][] dArr) throws ModelSpecificationException {
        int i = 0;
        while (i < dArr.length) {
            double[] dArr2 = dArr[i];
            if (dArr2.length >= 2) {
                addData(dArr2[0], dArr2[1]);
                i++;
            } else {
                throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr[i].length), 2);
            }
        }
    }

    public void addObservation(double[] dArr, double d) throws ModelSpecificationException {
        if (dArr == null || dArr.length == 0) {
            throw new ModelSpecificationException(LocalizedFormats.INVALID_REGRESSION_OBSERVATION, Integer.valueOf(dArr != null ? dArr.length : 0), 1);
        }
        addData(dArr[0], d);
    }

    public void addObservations(double[][] dArr, double[] dArr2) throws ModelSpecificationException {
        if (dArr == null || dArr2 == null || dArr.length != dArr2.length) {
            throw new ModelSpecificationException(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, Integer.valueOf(dArr == null ? 0 : dArr.length), Integer.valueOf(dArr2 == null ? 0 : dArr2.length));
        }
        boolean z = true;
        for (double[] dArr3 : dArr) {
            if (dArr3 == null || dArr3.length == 0) {
                z = false;
            }
        }
        if (z) {
            for (int i = 0; i < dArr.length; i++) {
                addData(dArr[i][0], dArr2[i]);
            }
            return;
        }
        throw new ModelSpecificationException(LocalizedFormats.NOT_ENOUGH_DATA_FOR_NUMBER_OF_PREDICTORS, 0, 1);
    }

    public void removeData(double[][] dArr) {
        for (int i = 0; i < dArr.length && this.n > 0; i++) {
            double[] dArr2 = dArr[i];
            removeData(dArr2[0], dArr2[1]);
        }
    }

    public void clear() {
        this.sumX = 0.0d;
        this.sumXX = 0.0d;
        this.sumY = 0.0d;
        this.sumYY = 0.0d;
        this.sumXY = 0.0d;
        this.n = 0;
    }

    public long getN() {
        return this.n;
    }

    public double predict(double d) {
        double slope = getSlope();
        return this.hasIntercept ? getIntercept(slope) + (slope * d) : slope * d;
    }

    public double getIntercept() {
        if (this.hasIntercept) {
            return getIntercept(getSlope());
        }
        return 0.0d;
    }

    public boolean hasIntercept() {
        return this.hasIntercept;
    }

    public double getSlope() {
        if (this.n >= 2 && FastMath.abs(this.sumXX) >= 4.9E-323d) {
            return this.sumXY / this.sumXX;
        }
        return Double.NaN;
    }

    public double getSumSquaredErrors() {
        double d = this.sumYY;
        double d2 = this.sumXY;
        return FastMath.max(0.0d, d - ((d2 * d2) / this.sumXX));
    }

    public double getTotalSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumYY;
    }

    public double getXSumSquares() {
        if (this.n < 2) {
            return Double.NaN;
        }
        return this.sumXX;
    }

    public double getSumOfCrossProducts() {
        return this.sumXY;
    }

    public double getRegressionSumSquares() {
        return getRegressionSumSquares(getSlope());
    }

    public double getMeanSquareError() {
        long j;
        long j2;
        double d;
        if (this.n < 3) {
            return Double.NaN;
        }
        if (this.hasIntercept) {
            d = getSumSquaredErrors();
            j2 = this.n;
            j = 2;
        } else {
            d = getSumSquaredErrors();
            j2 = this.n;
            j = 1;
        }
        return d / ((double) (j2 - j));
    }

    public double getR() {
        double slope = getSlope();
        double sqrt = FastMath.sqrt(getRSquare());
        return slope < 0.0d ? -sqrt : sqrt;
    }

    public double getRSquare() {
        double totalSumSquares = getTotalSumSquares();
        return (totalSumSquares - getSumSquaredErrors()) / totalSumSquares;
    }

    public double getInterceptStdErr() {
        if (!this.hasIntercept) {
            return Double.NaN;
        }
        double meanSquareError = getMeanSquareError();
        double d = 1.0d / ((double) this.n);
        double d2 = this.xbar;
        return FastMath.sqrt(meanSquareError * (d + ((d2 * d2) / this.sumXX)));
    }

    public double getSlopeStdErr() {
        return FastMath.sqrt(getMeanSquareError() / this.sumXX);
    }

    public double getSlopeConfidenceInterval() throws OutOfRangeException {
        return getSlopeConfidenceInterval(0.05d);
    }

    public double getSlopeConfidenceInterval(double d) throws OutOfRangeException {
        long j = this.n;
        if (j < 3) {
            return Double.NaN;
        }
        if (d >= 1.0d || d <= 0.0d) {
            throw new OutOfRangeException(LocalizedFormats.SIGNIFICANCE_LEVEL, Double.valueOf(d), 0, 1);
        }
        return getSlopeStdErr() * new TDistribution((double) (j - 2)).inverseCumulativeProbability(1.0d - (d / 2.0d));
    }

    public double getSignificance() {
        long j = this.n;
        if (j < 3) {
            return Double.NaN;
        }
        return (1.0d - new TDistribution((double) (j - 2)).cumulativeProbability(FastMath.abs(getSlope()) / getSlopeStdErr())) * 2.0d;
    }

    private double getIntercept(double d) {
        if (this.hasIntercept) {
            return (this.sumY - (d * this.sumX)) / ((double) this.n);
        }
        return 0.0d;
    }

    private double getRegressionSumSquares(double d) {
        return d * d * this.sumXX;
    }

    public RegressionResults regress() throws ModelSpecificationException, NoDataException {
        if (this.hasIntercept) {
            if (this.n < 3) {
                throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
            } else if (FastMath.abs(this.sumXX) > Precision.SAFE_MIN) {
                double[] dArr = {getIntercept(), getSlope()};
                double meanSquareError = getMeanSquareError();
                double d = this.sumYY;
                double d2 = this.sumY;
                long j = this.n;
                double d3 = d + ((d2 * d2) / ((double) j));
                double d4 = this.xbar;
                double d5 = this.sumXX;
                double d6 = d2;
                return new RegressionResults(dArr, new double[][]{new double[]{(((d4 * d4) / d5) + (1.0d / ((double) j))) * meanSquareError, ((-d4) * meanSquareError) / d5, meanSquareError / d5}}, true, j, 2, d6, d3, getSumSquaredErrors(), true, false);
            } else {
                double d7 = this.sumY;
                long j2 = this.n;
                return new RegressionResults(new double[]{d7 / ((double) j2), Double.NaN}, new double[][]{new double[]{this.ybar / (((double) j2) - 1.0d), Double.NaN, Double.NaN}}, true, j2, 1, d7, this.sumYY, getSumSquaredErrors(), true, false);
            }
        } else if (this.n < 2) {
            throw new NoDataException(LocalizedFormats.NOT_ENOUGH_DATA_REGRESSION);
        } else if (!Double.isNaN(this.sumXX)) {
            double meanSquareError2 = getMeanSquareError();
            double d8 = this.sumXX;
            double[] dArr2 = {this.sumXY / d8};
            double[][] dArr3 = {new double[]{meanSquareError2 / d8}};
            long j3 = this.n;
            double d9 = this.sumY;
            return new RegressionResults(dArr2, dArr3, true, j3, 1, d9, this.sumYY, getSumSquaredErrors(), false, false);
        } else {
            return new RegressionResults(new double[]{Double.NaN}, new double[][]{new double[]{Double.NaN}}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
        }
    }

    public RegressionResults regress(int[] iArr) throws MathIllegalArgumentException {
        int[] iArr2 = iArr;
        if (iArr2 == null || iArr2.length == 0) {
            throw new MathIllegalArgumentException(LocalizedFormats.ARRAY_ZERO_LENGTH_OR_NULL_NOT_ALLOWED, new Object[0]);
        }
        int i = 2;
        if (iArr2.length > 2 || (iArr2.length > 1 && !this.hasIntercept)) {
            LocalizedFormats localizedFormats = LocalizedFormats.ARRAY_SIZE_EXCEEDS_MAX_VARIABLES;
            if (iArr2.length > 1 && !this.hasIntercept) {
                i = 1;
            }
            throw new ModelSpecificationException(localizedFormats, Integer.valueOf(i));
        } else if (this.hasIntercept) {
            if (iArr2.length == 2) {
                int i2 = iArr2[0];
                if (i2 == 1) {
                    throw new ModelSpecificationException(LocalizedFormats.NOT_INCREASING_SEQUENCE, new Object[0]);
                } else if (i2 != 0) {
                    throw new OutOfRangeException(Integer.valueOf(iArr2[0]), 0, 1);
                } else if (iArr2[1] == 1) {
                    return regress();
                } else {
                    throw new OutOfRangeException(Integer.valueOf(iArr2[0]), 0, 1);
                }
            } else {
                int i3 = iArr2[0];
                if (i3 == 1 || i3 == 0) {
                    double d = this.sumY;
                    long j = this.n;
                    double d2 = (d * d) / ((double) j);
                    double d3 = this.sumYY;
                    double d4 = d3 + d2;
                    if (i3 == 0) {
                        return new RegressionResults(new double[]{this.ybar}, new double[][]{new double[]{d3 / ((double) ((j - 1) * j))}}, true, j, 1, d, d4 + d2, d3, true, false);
                    } else if (i3 != 1) {
                        return null;
                    } else {
                        double d5 = this.sumXX;
                        double d6 = this.sumX;
                        double d7 = d5 + ((d6 * d6) / ((double) j));
                        double d8 = this.sumXY + ((d6 * d) / ((double) j));
                        double max = FastMath.max(0.0d, d4 - ((d8 * d8) / d7));
                        double d9 = max / ((double) (this.n - 1));
                        if (!Double.isNaN(d7)) {
                            return new RegressionResults(new double[]{d8 / d7}, new double[][]{new double[]{d9 / d7}}, true, this.n, 1, this.sumY, d4, max, false, false);
                        }
                        return new RegressionResults(new double[]{Double.NaN}, new double[][]{new double[]{Double.NaN}}, true, this.n, 1, Double.NaN, Double.NaN, Double.NaN, false, false);
                    }
                } else {
                    throw new OutOfRangeException(Integer.valueOf(iArr2[0]), 0, 1);
                }
            }
        } else if (iArr2[0] == 0) {
            return regress();
        } else {
            throw new OutOfRangeException(Integer.valueOf(iArr2[0]), 0, 0);
        }
    }
}
