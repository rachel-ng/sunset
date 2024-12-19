package org.apache.commons.math3.distribution.fitting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.distribution.MixtureMultivariateNormalDistribution;
import org.apache.commons.math3.distribution.MultivariateNormalDistribution;
import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SingularMatrixException;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

public class MultivariateNormalMixtureExpectationMaximization {
    private static final int DEFAULT_MAX_ITERATIONS = 1000;
    private static final double DEFAULT_THRESHOLD = 1.0E-5d;
    private final double[][] data;
    private MixtureMultivariateNormalDistribution fittedModel;
    private double logLikelihood = 0.0d;

    public MultivariateNormalMixtureExpectationMaximization(double[][] dArr) throws NotStrictlyPositiveException, DimensionMismatchException, NumberIsTooSmallException {
        if (dArr.length >= 1) {
            int length = dArr.length;
            int[] iArr = new int[2];
            iArr[1] = dArr[0].length;
            iArr[0] = length;
            this.data = (double[][]) Array.newInstance(Double.TYPE, iArr);
            int i = 0;
            while (i < dArr.length) {
                double[] dArr2 = dArr[i];
                if (dArr2.length != dArr[0].length) {
                    throw new DimensionMismatchException(dArr[i].length, dArr[0].length);
                } else if (dArr2.length >= 2) {
                    this.data[i] = MathArrays.copyOf(dArr2, dArr2.length);
                    i++;
                } else {
                    throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_TOO_SMALL, Integer.valueOf(dArr[i].length), 2, true);
                }
            }
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(dArr.length));
    }

    public void fit(MixtureMultivariateNormalDistribution mixtureMultivariateNormalDistribution, int i, double d) throws SingularMatrixException, NotStrictlyPositiveException, DimensionMismatchException {
        int i2 = i;
        char c2 = 1;
        if (i2 < 1) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        } else if (d >= Double.MIN_VALUE) {
            double[][] dArr = this.data;
            int length = dArr.length;
            int i3 = 0;
            int length2 = dArr[0].length;
            int size = mixtureMultivariateNormalDistribution.getComponents().size();
            int length3 = ((MultivariateNormalDistribution) ((Pair) mixtureMultivariateNormalDistribution.getComponents().get(0)).getSecond()).getMeans().length;
            if (length3 == length2) {
                this.logLikelihood = Double.NEGATIVE_INFINITY;
                this.fittedModel = new MixtureMultivariateNormalDistribution(mixtureMultivariateNormalDistribution.getComponents());
                int i4 = 0;
                double d2 = 0.0d;
                while (true) {
                    int i5 = i4 + 1;
                    if (i4 <= i2 && FastMath.abs(d2 - this.logLikelihood) > d) {
                        double d3 = this.logLikelihood;
                        List components = this.fittedModel.getComponents();
                        double[] dArr2 = new double[size];
                        MultivariateNormalDistribution[] multivariateNormalDistributionArr = new MultivariateNormalDistribution[size];
                        for (int i6 = i3; i6 < size; i6++) {
                            dArr2[i6] = ((Double) ((Pair) components.get(i6)).getFirst()).doubleValue();
                            multivariateNormalDistributionArr[i6] = (MultivariateNormalDistribution) ((Pair) components.get(i6)).getSecond();
                        }
                        int[] iArr = new int[2];
                        iArr[c2] = size;
                        iArr[i3] = length;
                        double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                        double[] dArr4 = new double[size];
                        int[] iArr2 = new int[2];
                        iArr2[c2] = length2;
                        iArr2[i3] = size;
                        double[][] dArr5 = (double[][]) Array.newInstance(Double.TYPE, iArr2);
                        int i7 = 0;
                        double d4 = 0.0d;
                        while (i7 < length) {
                            double density = this.fittedModel.density(this.data[i7]);
                            d4 += FastMath.log(density);
                            int i8 = 0;
                            while (i8 < size) {
                                double d5 = d3;
                                dArr3[i7][i8] = (dArr2[i8] * multivariateNormalDistributionArr[i8].density(this.data[i7])) / density;
                                dArr4[i8] = dArr4[i8] + dArr3[i7][i8];
                                int i9 = 0;
                                while (i9 < length2) {
                                    double[] dArr6 = dArr5[i8];
                                    dArr6[i9] = dArr6[i9] + (dArr3[i7][i8] * this.data[i7][i9]);
                                    i9++;
                                    i5 = i5;
                                }
                                int i10 = i5;
                                i8++;
                                int i11 = i;
                                d3 = d5;
                            }
                            double d6 = d3;
                            int i12 = i5;
                            i7++;
                            int i13 = i;
                        }
                        double d7 = d3;
                        int i14 = i5;
                        double d8 = (double) length;
                        this.logLikelihood = d4 / d8;
                        double[] dArr7 = new double[size];
                        int[] iArr3 = new int[2];
                        iArr3[1] = length2;
                        iArr3[0] = size;
                        double[][] dArr8 = (double[][]) Array.newInstance(Double.TYPE, iArr3);
                        for (int i15 = 0; i15 < size; i15++) {
                            dArr7[i15] = dArr4[i15] / d8;
                            for (int i16 = 0; i16 < length2; i16++) {
                                dArr8[i15][i16] = dArr5[i15][i16] / dArr4[i15];
                            }
                        }
                        RealMatrix[] realMatrixArr = new RealMatrix[size];
                        for (int i17 = 0; i17 < size; i17++) {
                            realMatrixArr[i17] = new Array2DRowRealMatrix(length2, length2);
                        }
                        for (int i18 = 0; i18 < length; i18++) {
                            for (int i19 = 0; i19 < size; i19++) {
                                Array2DRowRealMatrix array2DRowRealMatrix = new Array2DRowRealMatrix(MathArrays.ebeSubtract(this.data[i18], dArr8[i19]));
                                realMatrixArr[i19] = realMatrixArr[i19].add(array2DRowRealMatrix.multiply(array2DRowRealMatrix.transpose()).scalarMultiply(dArr3[i18][i19]));
                            }
                        }
                        int[] iArr4 = new int[3];
                        iArr4[2] = length2;
                        iArr4[1] = length2;
                        iArr4[0] = size;
                        double[][][] dArr9 = (double[][][]) Array.newInstance(Double.TYPE, iArr4);
                        for (int i20 = 0; i20 < size; i20++) {
                            RealMatrix scalarMultiply = realMatrixArr[i20].scalarMultiply(1.0d / dArr4[i20]);
                            realMatrixArr[i20] = scalarMultiply;
                            dArr9[i20] = scalarMultiply.getData();
                        }
                        this.fittedModel = new MixtureMultivariateNormalDistribution(dArr7, dArr8, dArr9);
                        i2 = i;
                        c2 = 1;
                        i3 = 0;
                        i4 = i14;
                        d2 = d7;
                    }
                }
                if (FastMath.abs(d2 - this.logLikelihood) > d) {
                    throw new ConvergenceException();
                }
                return;
            }
            throw new DimensionMismatchException(length3, length2);
        } else {
            throw new NotStrictlyPositiveException(Double.valueOf(d));
        }
    }

    public void fit(MixtureMultivariateNormalDistribution mixtureMultivariateNormalDistribution) throws SingularMatrixException, NotStrictlyPositiveException {
        fit(mixtureMultivariateNormalDistribution, 1000, 1.0E-5d);
    }

    public static MixtureMultivariateNormalDistribution estimate(double[][] dArr, int i) throws NotStrictlyPositiveException, DimensionMismatchException {
        double[][] dArr2 = dArr;
        int i2 = i;
        if (dArr2.length < 2) {
            throw new NotStrictlyPositiveException(Integer.valueOf(dArr2.length));
        } else if (i2 < 2) {
            throw new NumberIsTooSmallException(Integer.valueOf(i), 2, true);
        } else if (i2 <= dArr2.length) {
            int length = dArr2.length;
            int i3 = 0;
            int length2 = dArr2[0].length;
            DataRow[] dataRowArr = new DataRow[length];
            for (int i4 = 0; i4 < length; i4++) {
                dataRowArr[i4] = new DataRow(dArr2[i4]);
            }
            Arrays.sort(dataRowArr);
            double d = 1.0d / ((double) i2);
            ArrayList arrayList = new ArrayList(i2);
            int i5 = 0;
            while (i5 < i2) {
                int i6 = (i5 * length) / i2;
                i5++;
                int i7 = (i5 * length) / i2;
                int i8 = i7 - i6;
                int[] iArr = new int[2];
                iArr[1] = length2;
                iArr[i3] = i8;
                double[][] dArr3 = (double[][]) Array.newInstance(Double.TYPE, iArr);
                double[] dArr4 = new double[length2];
                int i9 = i3;
                while (i6 < i7) {
                    while (i3 < length2) {
                        double d2 = dataRowArr[i6].getRow()[i3];
                        dArr4[i3] = dArr4[i3] + d2;
                        dArr3[i9][i3] = d2;
                        i3++;
                    }
                    i6++;
                    i9++;
                    i3 = 0;
                }
                MathArrays.scaleInPlace(1.0d / ((double) i8), dArr4);
                arrayList.add(new Pair(Double.valueOf(d), new MultivariateNormalDistribution(dArr4, new Covariance(dArr3).getCovarianceMatrix().getData())));
                i3 = 0;
            }
            return new MixtureMultivariateNormalDistribution(arrayList);
        } else {
            throw new NumberIsTooLargeException(Integer.valueOf(i), Integer.valueOf(dArr2.length), true);
        }
    }

    public double getLogLikelihood() {
        return this.logLikelihood;
    }

    public MixtureMultivariateNormalDistribution getFittedModel() {
        return new MixtureMultivariateNormalDistribution(this.fittedModel.getComponents());
    }

    private static class DataRow implements Comparable<DataRow> {
        private Double mean = Double.valueOf(0.0d);
        private final double[] row;

        DataRow(double[] dArr) {
            this.row = dArr;
            for (double doubleValue : dArr) {
                this.mean = Double.valueOf(this.mean.doubleValue() + doubleValue);
            }
            this.mean = Double.valueOf(this.mean.doubleValue() / ((double) dArr.length));
        }

        public int compareTo(DataRow dataRow) {
            return this.mean.compareTo(dataRow.mean);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof DataRow) {
                return MathArrays.equals(this.row, ((DataRow) obj).row);
            }
            return false;
        }

        public int hashCode() {
            return Arrays.hashCode(this.row);
        }

        public double[] getRow() {
            return this.row;
        }
    }
}
