package org.apache.commons.math3.optim;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

public class PointValuePair extends Pair<double[], Double> implements Serializable {
    private static final long serialVersionUID = 20120513;

    public PointValuePair(double[] dArr, double d) {
        this(dArr, d, true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PointValuePair(double[] dArr, double d, boolean z) {
        super(z ? dArr == null ? null : (double[]) dArr.clone() : dArr, Double.valueOf(d));
    }

    public double[] getPoint() {
        double[] dArr = (double[]) getKey();
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getPointRef() {
        return (double[]) getKey();
    }

    private Object writeReplace() {
        return new DataTransferObject((double[]) getKey(), ((Double) getValue()).doubleValue());
    }

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120513;
        private final double[] point;
        private final double value;

        public DataTransferObject(double[] dArr, double d) {
            this.point = (double[]) dArr.clone();
            this.value = d;
        }

        private Object readResolve() {
            return new PointValuePair(this.point, this.value, false);
        }
    }
}
