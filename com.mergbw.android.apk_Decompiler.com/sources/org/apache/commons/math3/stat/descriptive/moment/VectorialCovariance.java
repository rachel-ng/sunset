package org.apache.commons.math3.stat.descriptive.moment;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class VectorialCovariance implements Serializable {
    private static final long serialVersionUID = 4118372414238930270L;
    private final boolean isBiasCorrected;
    private long n = 0;
    private final double[] productsSums;
    private final double[] sums;

    public VectorialCovariance(int i, boolean z) {
        this.sums = new double[i];
        this.productsSums = new double[((i * (i + 1)) / 2)];
        this.isBiasCorrected = z;
    }

    public void increment(double[] dArr) throws DimensionMismatchException {
        if (dArr.length == this.sums.length) {
            int i = 0;
            for (int i2 = 0; i2 < dArr.length; i2++) {
                double[] dArr2 = this.sums;
                dArr2[i2] = dArr2[i2] + dArr[i2];
                int i3 = 0;
                while (i3 <= i2) {
                    double[] dArr3 = this.productsSums;
                    dArr3[i] = dArr3[i] + (dArr[i2] * dArr[i3]);
                    i3++;
                    i++;
                }
            }
            this.n++;
            return;
        }
        throw new DimensionMismatchException(dArr.length, this.sums.length);
    }

    public RealMatrix getResult() {
        int length = this.sums.length;
        RealMatrix createRealMatrix = MatrixUtils.createRealMatrix(length, length);
        long j = this.n;
        if (j > 1) {
            double d = 1.0d / ((double) (j * (this.isBiasCorrected ? j - 1 : j)));
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = 0;
                while (i3 <= i2) {
                    int i4 = i + 1;
                    double[] dArr = this.sums;
                    double d2 = ((((double) this.n) * this.productsSums[i]) - (dArr[i2] * dArr[i3])) * d;
                    createRealMatrix.setEntry(i2, i3, d2);
                    createRealMatrix.setEntry(i3, i2, d2);
                    i3++;
                    i = i4;
                }
            }
        }
        return createRealMatrix;
    }

    public long getN() {
        return this.n;
    }

    public void clear() {
        this.n = 0;
        Arrays.fill(this.sums, 0.0d);
        Arrays.fill(this.productsSums, 0.0d);
    }

    public int hashCode() {
        int i = this.isBiasCorrected ? 1231 : 1237;
        long j = this.n;
        return ((((((i + 31) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + Arrays.hashCode(this.productsSums)) * 31) + Arrays.hashCode(this.sums);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VectorialCovariance)) {
            return false;
        }
        VectorialCovariance vectorialCovariance = (VectorialCovariance) obj;
        return this.isBiasCorrected == vectorialCovariance.isBiasCorrected && this.n == vectorialCovariance.n && Arrays.equals(this.productsSums, vectorialCovariance.productsSums) && Arrays.equals(this.sums, vectorialCovariance.sums);
    }
}
