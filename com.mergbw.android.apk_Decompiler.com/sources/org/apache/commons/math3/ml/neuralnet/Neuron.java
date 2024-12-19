package org.apache.commons.math3.ml.neuralnet;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.Precision;

public class Neuron implements Serializable {
    private static final long serialVersionUID = 20130207;
    private final AtomicReference<double[]> features;
    private final long identifier;
    private final int size;

    Neuron(long j, double[] dArr) {
        this.identifier = j;
        this.size = dArr.length;
        this.features = new AtomicReference<>(dArr.clone());
    }

    public long getIdentifier() {
        return this.identifier;
    }

    public int getSize() {
        return this.size;
    }

    public double[] getFeatures() {
        return (double[]) this.features.get().clone();
    }

    public boolean compareAndSetFeatures(double[] dArr, double[] dArr2) {
        if (dArr2.length == this.size) {
            double[] dArr3 = this.features.get();
            if (containSameValues(dArr3, dArr) && LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.features, dArr3, dArr2.clone())) {
                return true;
            }
            return false;
        }
        throw new DimensionMismatchException(dArr2.length, this.size);
    }

    private boolean containSameValues(double[] dArr, double[] dArr2) {
        if (dArr2.length == this.size) {
            for (int i = 0; i < this.size; i++) {
                if (!Precision.equals(dArr[i], dArr2[i])) {
                    return false;
                }
            }
            return true;
        }
        throw new DimensionMismatchException(dArr2.length, this.size);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new IllegalStateException();
    }

    private Object writeReplace() {
        return new SerializationProxy(this.identifier, this.features.get());
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 20130207;
        private final double[] features;
        private final long identifier;

        SerializationProxy(long j, double[] dArr) {
            this.identifier = j;
            this.features = dArr;
        }

        private Object readResolve() {
            return new Neuron(this.identifier, this.features);
        }
    }
}
