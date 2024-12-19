package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;
import org.apache.commons.math3.util.Precision;

public abstract class AbstractStorelessUnivariateStatistic extends AbstractUnivariateStatistic implements StorelessUnivariateStatistic {
    public abstract void clear();

    public abstract StorelessUnivariateStatistic copy();

    public abstract double getResult();

    public abstract void increment(double d);

    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        if (dArr != null) {
            return evaluate(dArr, 0, dArr.length);
        }
        throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
    }

    public double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, i, i2)) {
            clear();
            incrementAll(dArr, i, i2);
        }
        return getResult();
    }

    public void incrementAll(double[] dArr) throws MathIllegalArgumentException {
        if (dArr != null) {
            incrementAll(dArr, 0, dArr.length);
            return;
        }
        throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
    }

    public void incrementAll(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (test(dArr, i, i2)) {
            int i3 = i2 + i;
            while (i < i3) {
                increment(dArr[i]);
                i++;
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbstractStorelessUnivariateStatistic)) {
            return false;
        }
        AbstractStorelessUnivariateStatistic abstractStorelessUnivariateStatistic = (AbstractStorelessUnivariateStatistic) obj;
        if (!Precision.equalsIncludingNaN(abstractStorelessUnivariateStatistic.getResult(), getResult()) || !Precision.equalsIncludingNaN((float) abstractStorelessUnivariateStatistic.getN(), (float) getN())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((MathUtils.hash(getResult()) + 31) * 31) + MathUtils.hash((double) getN());
    }
}
