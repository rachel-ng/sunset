package org.apache.commons.math3.optim.nonlinear.scalar.noderiv;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.commons.math3.analysis.MultivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.optim.OptimizationData;
import org.apache.commons.math3.optim.PointValuePair;

public abstract class AbstractSimplex implements OptimizationData {
    private final int dimension;
    private PointValuePair[] simplex;
    private double[][] startConfiguration;

    public abstract void iterate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator);

    protected AbstractSimplex(int i) {
        this(i, 1.0d);
    }

    protected AbstractSimplex(int i, double d) {
        this(createHypercubeSteps(i, d));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r0 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected AbstractSimplex(double[] r9) {
        /*
            r8 = this;
            r8.<init>()
            if (r9 == 0) goto L_0x004d
            int r0 = r9.length
            if (r0 == 0) goto L_0x0047
            int r0 = r9.length
            r8.dimension = r0
            r1 = 2
            int[] r1 = new int[r1]
            r2 = 1
            r1[r2] = r0
            r2 = 0
            r1[r2] = r0
            java.lang.Class r0 = java.lang.Double.TYPE
            java.lang.Object r0 = java.lang.reflect.Array.newInstance(r0, r1)
            double[][] r0 = (double[][]) r0
            r8.startConfiguration = r0
            r0 = r2
        L_0x001f:
            int r1 = r8.dimension
            if (r0 >= r1) goto L_0x0046
            double[][] r1 = r8.startConfiguration
            r1 = r1[r0]
            r3 = r2
        L_0x0028:
            int r4 = r0 + 1
            if (r3 >= r4) goto L_0x0044
            r4 = r9[r3]
            r6 = 0
            int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r4 == 0) goto L_0x003a
            int r3 = r3 + 1
            java.lang.System.arraycopy(r9, r2, r1, r2, r3)
            goto L_0x0028
        L_0x003a:
            org.apache.commons.math3.exception.ZeroException r9 = new org.apache.commons.math3.exception.ZeroException
            org.apache.commons.math3.exception.util.LocalizedFormats r0 = org.apache.commons.math3.exception.util.LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX
            java.lang.Object[] r1 = new java.lang.Object[r2]
            r9.<init>(r0, r1)
            throw r9
        L_0x0044:
            r0 = r4
            goto L_0x001f
        L_0x0046:
            return
        L_0x0047:
            org.apache.commons.math3.exception.ZeroException r9 = new org.apache.commons.math3.exception.ZeroException
            r9.<init>()
            throw r9
        L_0x004d:
            org.apache.commons.math3.exception.NullArgumentException r9 = new org.apache.commons.math3.exception.NullArgumentException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.optim.nonlinear.scalar.noderiv.AbstractSimplex.<init>(double[]):void");
    }

    protected AbstractSimplex(double[][] dArr) {
        if (dArr.length > 0) {
            int length = dArr.length - 1;
            this.dimension = length;
            int[] iArr = new int[2];
            iArr[1] = length;
            iArr[0] = length;
            this.startConfiguration = (double[][]) Array.newInstance(Double.TYPE, iArr);
            double[] dArr2 = dArr[0];
            int i = 0;
            while (i < dArr.length) {
                double[] dArr3 = dArr[i];
                if (dArr3.length == this.dimension) {
                    int i2 = 0;
                    while (i2 < i) {
                        double[] dArr4 = dArr[i2];
                        int i3 = 0;
                        while (i3 < this.dimension) {
                            if (dArr3[i3] != dArr4[i3]) {
                                i2++;
                            } else {
                                i3++;
                            }
                        }
                        throw new MathIllegalArgumentException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX, Integer.valueOf(i), Integer.valueOf(i2));
                    }
                    if (i > 0) {
                        double[] dArr5 = this.startConfiguration[i - 1];
                        for (int i4 = 0; i4 < this.dimension; i4++) {
                            dArr5[i4] = dArr3[i4] - dArr2[i4];
                        }
                    }
                    i++;
                } else {
                    throw new DimensionMismatchException(dArr3.length, this.dimension);
                }
            }
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.SIMPLEX_NEED_ONE_POINT, Integer.valueOf(dArr.length));
    }

    public int getDimension() {
        return this.dimension;
    }

    public int getSize() {
        return this.simplex.length;
    }

    public void build(double[] dArr) {
        int i = this.dimension;
        if (i == dArr.length) {
            PointValuePair[] pointValuePairArr = new PointValuePair[(i + 1)];
            this.simplex = pointValuePairArr;
            pointValuePairArr[0] = new PointValuePair(dArr, Double.NaN);
            int i2 = 0;
            while (true) {
                int i3 = this.dimension;
                if (i2 < i3) {
                    double[] dArr2 = this.startConfiguration[i2];
                    double[] dArr3 = new double[i3];
                    for (int i4 = 0; i4 < this.dimension; i4++) {
                        dArr3[i4] = dArr[i4] + dArr2[i4];
                    }
                    i2++;
                    this.simplex[i2] = new PointValuePair(dArr3, Double.NaN);
                } else {
                    return;
                }
            }
        } else {
            throw new DimensionMismatchException(this.dimension, dArr.length);
        }
    }

    public void evaluate(MultivariateFunction multivariateFunction, Comparator<PointValuePair> comparator) {
        int i = 0;
        while (true) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                PointValuePair pointValuePair = pointValuePairArr[i];
                double[] pointRef = pointValuePair.getPointRef();
                if (Double.isNaN(((Double) pointValuePair.getValue()).doubleValue())) {
                    this.simplex[i] = new PointValuePair(pointRef, multivariateFunction.value(pointRef), false);
                }
                i++;
            } else {
                Arrays.sort(pointValuePairArr, comparator);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void replaceWorstPoint(PointValuePair pointValuePair, Comparator<PointValuePair> comparator) {
        int i = 0;
        while (true) {
            int i2 = this.dimension;
            if (i < i2) {
                if (comparator.compare(this.simplex[i], pointValuePair) > 0) {
                    PointValuePair[] pointValuePairArr = this.simplex;
                    PointValuePair pointValuePair2 = pointValuePairArr[i];
                    pointValuePairArr[i] = pointValuePair;
                    pointValuePair = pointValuePair2;
                }
                i++;
            } else {
                this.simplex[i2] = pointValuePair;
                return;
            }
        }
    }

    public PointValuePair[] getPoints() {
        PointValuePair[] pointValuePairArr = this.simplex;
        PointValuePair[] pointValuePairArr2 = new PointValuePair[pointValuePairArr.length];
        System.arraycopy(pointValuePairArr, 0, pointValuePairArr2, 0, pointValuePairArr.length);
        return pointValuePairArr2;
    }

    public PointValuePair getPoint(int i) {
        if (i >= 0) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                return pointValuePairArr[i];
            }
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.simplex.length - 1));
    }

    /* access modifiers changed from: protected */
    public void setPoint(int i, PointValuePair pointValuePair) {
        if (i >= 0) {
            PointValuePair[] pointValuePairArr = this.simplex;
            if (i < pointValuePairArr.length) {
                pointValuePairArr[i] = pointValuePair;
                return;
            }
        }
        throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.simplex.length - 1));
    }

    /* access modifiers changed from: protected */
    public void setPoints(PointValuePair[] pointValuePairArr) {
        if (pointValuePairArr.length == this.simplex.length) {
            this.simplex = pointValuePairArr;
            return;
        }
        throw new DimensionMismatchException(pointValuePairArr.length, this.simplex.length);
    }

    private static double[] createHypercubeSteps(int i, double d) {
        double[] dArr = new double[i];
        for (int i2 = 0; i2 < i; i2++) {
            dArr[i2] = d;
        }
        return dArr;
    }
}
