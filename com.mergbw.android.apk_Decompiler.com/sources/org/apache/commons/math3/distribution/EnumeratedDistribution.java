package org.apache.commons.math3.distribution;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotANumberException;
import org.apache.commons.math3.exception.NotFiniteNumberException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.Pair;

public class EnumeratedDistribution<T> implements Serializable {
    private static final long serialVersionUID = 20123308;
    private final double[] cumulativeProbabilities;
    private final double[] probabilities;
    protected final RandomGenerator random;
    private final List<T> singletons;

    public EnumeratedDistribution(List<Pair<T, Double>> list) throws NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this(new Well19937c(), list);
    }

    public EnumeratedDistribution(RandomGenerator randomGenerator, List<Pair<T, Double>> list) throws NotPositiveException, MathArithmeticException, NotFiniteNumberException, NotANumberException {
        this.random = randomGenerator;
        this.singletons = new ArrayList(list.size());
        double[] dArr = new double[list.size()];
        int i = 0;
        int i2 = 0;
        while (true) {
            double d = 0.0d;
            if (i2 < list.size()) {
                Pair pair = list.get(i2);
                this.singletons.add(pair.getKey());
                Double d2 = (Double) pair.getValue();
                double doubleValue = d2.doubleValue();
                if (doubleValue < 0.0d) {
                    throw new NotPositiveException((Number) pair.getValue());
                } else if (Double.isInfinite(doubleValue)) {
                    throw new NotFiniteNumberException(d2, new Object[0]);
                } else if (!Double.isNaN(doubleValue)) {
                    dArr[i2] = doubleValue;
                    i2++;
                } else {
                    throw new NotANumberException();
                }
            } else {
                double[] normalizeArray = MathArrays.normalizeArray(dArr, 1.0d);
                this.probabilities = normalizeArray;
                this.cumulativeProbabilities = new double[normalizeArray.length];
                while (true) {
                    double[] dArr2 = this.probabilities;
                    if (i < dArr2.length) {
                        d += dArr2[i];
                        this.cumulativeProbabilities[i] = d;
                        i++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void reseedRandomGenerator(long j) {
        this.random.setSeed(j);
    }

    /* access modifiers changed from: package-private */
    public double probability(T t) {
        double d = 0.0d;
        for (int i = 0; i < this.probabilities.length; i++) {
            if ((t == null && this.singletons.get(i) == null) || (t != null && t.equals(this.singletons.get(i)))) {
                d += this.probabilities[i];
            }
        }
        return d;
    }

    public List<Pair<T, Double>> getPmf() {
        ArrayList arrayList = new ArrayList(this.probabilities.length);
        for (int i = 0; i < this.probabilities.length; i++) {
            arrayList.add(new Pair(this.singletons.get(i), Double.valueOf(this.probabilities[i])));
        }
        return arrayList;
    }

    public T sample() {
        double nextDouble = this.random.nextDouble();
        int binarySearch = Arrays.binarySearch(this.cumulativeProbabilities, nextDouble);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 1;
        }
        if (binarySearch >= 0 && binarySearch < this.probabilities.length && nextDouble < this.cumulativeProbabilities[binarySearch]) {
            return this.singletons.get(binarySearch);
        }
        List<T> list = this.singletons;
        return list.get(list.size() - 1);
    }

    public Object[] sample(int i) throws NotStrictlyPositiveException {
        if (i > 0) {
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = sample();
            }
            return objArr;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }

    public T[] sample(int i, T[] tArr) throws NotStrictlyPositiveException {
        if (i > 0) {
            if (tArr != null) {
                if (tArr.length < i) {
                    tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
                }
                for (int i2 = 0; i2 < i; i2++) {
                    tArr[i2] = sample();
                }
                return tArr;
            }
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.NUMBER_OF_SAMPLES, Integer.valueOf(i));
    }
}
