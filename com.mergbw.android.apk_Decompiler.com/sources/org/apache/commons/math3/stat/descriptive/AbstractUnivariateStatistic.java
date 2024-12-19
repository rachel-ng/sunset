package org.apache.commons.math3.stat.descriptive;

import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public abstract class AbstractUnivariateStatistic implements UnivariateStatistic {
    private double[] storedData;

    public abstract UnivariateStatistic copy();

    public abstract double evaluate(double[] dArr, int i, int i2) throws MathIllegalArgumentException;

    public void setData(double[] dArr) {
        this.storedData = dArr == null ? null : (double[]) dArr.clone();
    }

    public double[] getData() {
        double[] dArr = this.storedData;
        if (dArr == null) {
            return null;
        }
        return (double[]) dArr.clone();
    }

    /* access modifiers changed from: protected */
    public double[] getDataRef() {
        return this.storedData;
    }

    public void setData(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        if (dArr == null) {
            throw new NullArgumentException(LocalizedFormats.INPUT_ARRAY, new Object[0]);
        } else if (i < 0) {
            throw new NotPositiveException(LocalizedFormats.START_POSITION, Integer.valueOf(i));
        } else if (i2 >= 0) {
            int i3 = i + i2;
            if (i3 <= dArr.length) {
                double[] dArr2 = new double[i2];
                this.storedData = dArr2;
                System.arraycopy(dArr, i, dArr2, 0, i2);
                return;
            }
            throw new NumberIsTooLargeException(LocalizedFormats.SUBARRAY_ENDS_AFTER_ARRAY_END, Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        } else {
            throw new NotPositiveException(LocalizedFormats.LENGTH, Integer.valueOf(i2));
        }
    }

    public double evaluate() throws MathIllegalArgumentException {
        return evaluate(this.storedData);
    }

    public double evaluate(double[] dArr) throws MathIllegalArgumentException {
        test(dArr, 0, 0);
        return evaluate(dArr, 0, dArr.length);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, int i, int i2) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(dArr, i, i2, false);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, int i, int i2, boolean z) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(dArr, i, i2, z);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, double[] dArr2, int i, int i2) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(dArr, dArr2, i, i2, false);
    }

    /* access modifiers changed from: protected */
    public boolean test(double[] dArr, double[] dArr2, int i, int i2, boolean z) throws MathIllegalArgumentException {
        return MathArrays.verifyValues(dArr, dArr2, i, i2, z);
    }
}
