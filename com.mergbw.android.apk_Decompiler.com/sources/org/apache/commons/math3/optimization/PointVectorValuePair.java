package org.apache.commons.math3.optimization;

import java.io.Serializable;
import org.apache.commons.math3.util.Pair;

@Deprecated
public class PointVectorValuePair extends Pair<double[], double[]> implements Serializable {
    private static final long serialVersionUID = 20120513;

    public PointVectorValuePair(double[] dArr, double[] dArr2) {
        this(dArr, dArr2, true);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PointVectorValuePair(double[] dArr, double[] dArr2, boolean z) {
        super(z ? dArr == null ? null : (double[]) dArr.clone() : dArr, z ? dArr2 == null ? null : (double[]) dArr2.clone() : dArr2);
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

    public double[] getValue() {
        double[] dArr = (double[]) super.getValue();
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    public double[] getValueRef() {
        return (double[]) super.getValue();
    }

    private Object writeReplace() {
        return new DataTransferObject((double[]) getKey(), getValue());
    }

    private static class DataTransferObject implements Serializable {
        private static final long serialVersionUID = 20120513;
        private final double[] point;
        private final double[] value;

        public DataTransferObject(double[] dArr, double[] dArr2) {
            this.point = (double[]) dArr.clone();
            this.value = (double[]) dArr2.clone();
        }

        private Object readResolve() {
            return new PointVectorValuePair(this.point, this.value, false);
        }
    }
}
