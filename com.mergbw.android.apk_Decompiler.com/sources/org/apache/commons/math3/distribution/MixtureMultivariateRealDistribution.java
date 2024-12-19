package org.apache.commons.math3.distribution;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.MultivariateRealDistribution;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.Pair;

public class MixtureMultivariateRealDistribution<T extends MultivariateRealDistribution> extends AbstractMultivariateRealDistribution {
    private final List<T> distribution;
    private final double[] weight;

    public MixtureMultivariateRealDistribution(List<Pair<Double, T>> list) {
        this(new Well19937c(), list);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MixtureMultivariateRealDistribution(RandomGenerator randomGenerator, List<Pair<Double, T>> list) {
        super(randomGenerator, ((MultivariateRealDistribution) list.get(0).getSecond()).getDimension());
        int size = list.size();
        int dimension = getDimension();
        int i = 0;
        double d = 0.0d;
        while (i < size) {
            Pair pair = list.get(i);
            if (((MultivariateRealDistribution) pair.getSecond()).getDimension() != dimension) {
                throw new DimensionMismatchException(((MultivariateRealDistribution) pair.getSecond()).getDimension(), dimension);
            } else if (((Double) pair.getFirst()).doubleValue() >= 0.0d) {
                d += ((Double) pair.getFirst()).doubleValue();
                i++;
            } else {
                throw new NotPositiveException((Number) pair.getFirst());
            }
        }
        if (!Double.isInfinite(d)) {
            this.distribution = new ArrayList();
            this.weight = new double[size];
            for (int i2 = 0; i2 < size; i2++) {
                Pair pair2 = list.get(i2);
                this.weight[i2] = ((Double) pair2.getFirst()).doubleValue() / d;
                this.distribution.add(pair2.getSecond());
            }
            return;
        }
        throw new MathArithmeticException(LocalizedFormats.OVERFLOW, new Object[0]);
    }

    public double density(double[] dArr) {
        double d = 0.0d;
        int i = 0;
        while (true) {
            double[] dArr2 = this.weight;
            if (i >= dArr2.length) {
                return d;
            }
            d += dArr2[i] * ((MultivariateRealDistribution) this.distribution.get(i)).density(dArr);
            i++;
        }
    }

    public double[] sample() {
        double[] dArr;
        double nextDouble = this.random.nextDouble();
        double d = 0.0d;
        int i = 0;
        while (true) {
            double[] dArr2 = this.weight;
            if (i >= dArr2.length) {
                dArr = null;
                break;
            }
            d += dArr2[i];
            if (nextDouble <= d) {
                dArr = ((MultivariateRealDistribution) this.distribution.get(i)).sample();
                break;
            }
            i++;
        }
        return dArr == null ? ((MultivariateRealDistribution) this.distribution.get(this.weight.length - 1)).sample() : dArr;
    }

    public void reseedRandomGenerator(long j) {
        super.reseedRandomGenerator(j);
        int i = 0;
        while (i < this.distribution.size()) {
            i++;
            ((MultivariateRealDistribution) this.distribution.get(i)).reseedRandomGenerator(((long) i) + j);
        }
    }

    public List<Pair<Double, T>> getComponents() {
        ArrayList arrayList = new ArrayList(this.weight.length);
        int i = 0;
        while (true) {
            double[] dArr = this.weight;
            if (i >= dArr.length) {
                return arrayList;
            }
            arrayList.add(new Pair(Double.valueOf(dArr[i]), this.distribution.get(i)));
            i++;
        }
    }
}
