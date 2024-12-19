package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class ResizableDoubleArray implements DoubleArray, Serializable {
    @Deprecated
    public static final int ADDITIVE_MODE = 1;
    private static final double DEFAULT_CONTRACTION_DELTA = 0.5d;
    private static final double DEFAULT_EXPANSION_FACTOR = 2.0d;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    @Deprecated
    public static final int MULTIPLICATIVE_MODE = 0;
    private static final long serialVersionUID = -3485529955529426875L;
    private double contractionCriterion;
    private double expansionFactor;
    private ExpansionMode expansionMode;
    private double[] internalArray;
    private int numElements;
    private int startIndex;

    public enum ExpansionMode {
        MULTIPLICATIVE,
        ADDITIVE
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setInitialCapacity(int i) throws MathIllegalArgumentException {
    }

    public ResizableDoubleArray() {
        this(16);
    }

    public ResizableDoubleArray(int i) throws MathIllegalArgumentException {
        this(i, (double) DEFAULT_EXPANSION_FACTOR);
    }

    public ResizableDoubleArray(double[] dArr) {
        this(16, DEFAULT_EXPANSION_FACTOR, 2.5d, ExpansionMode.MULTIPLICATIVE, dArr);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f) throws MathIllegalArgumentException {
        this(i, (double) f);
    }

    public ResizableDoubleArray(int i, double d) throws MathIllegalArgumentException {
        this(i, d, d + DEFAULT_CONTRACTION_DELTA);
    }

    @Deprecated
    public ResizableDoubleArray(int i, float f, float f2) throws MathIllegalArgumentException {
        this(i, (double) f, (double) f2);
    }

    public ResizableDoubleArray(int i, double d, double d2) throws MathIllegalArgumentException {
        this(i, d, d2, ExpansionMode.MULTIPLICATIVE, (double[]) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public ResizableDoubleArray(int i, float f, float f2, int i2) throws MathIllegalArgumentException {
        this(i, (double) f, (double) f2, i2 == 1 ? ExpansionMode.ADDITIVE : ExpansionMode.MULTIPLICATIVE, (double[]) null);
        setExpansionMode(i2);
    }

    public ResizableDoubleArray(int i, double d, double d2, ExpansionMode expansionMode2, double... dArr) throws MathIllegalArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        if (i > 0) {
            checkContractExpand(d2, d);
            this.expansionFactor = d;
            this.contractionCriterion = d2;
            this.expansionMode = expansionMode2;
            this.internalArray = new double[i];
            this.numElements = 0;
            this.startIndex = 0;
            if (dArr != null && dArr.length > 1) {
                addElements(dArr);
                return;
            }
            return;
        }
        throw new NotStrictlyPositiveException(LocalizedFormats.INITIAL_CAPACITY_NOT_POSITIVE, Integer.valueOf(i));
    }

    public ResizableDoubleArray(ResizableDoubleArray resizableDoubleArray) throws NullArgumentException {
        this.contractionCriterion = 2.5d;
        this.expansionFactor = DEFAULT_EXPANSION_FACTOR;
        this.expansionMode = ExpansionMode.MULTIPLICATIVE;
        this.numElements = 0;
        this.startIndex = 0;
        MathUtils.checkNotNull(resizableDoubleArray);
        copy(resizableDoubleArray, this);
    }

    public synchronized void addElement(double d) {
        if (this.internalArray.length <= this.startIndex + this.numElements) {
            expand();
        }
        double[] dArr = this.internalArray;
        int i = this.startIndex;
        int i2 = this.numElements;
        this.numElements = i2 + 1;
        dArr[i + i2] = d;
    }

    public synchronized void addElements(double[] dArr) {
        int i = this.numElements;
        double[] dArr2 = new double[(dArr.length + i + 1)];
        System.arraycopy(this.internalArray, this.startIndex, dArr2, 0, i);
        System.arraycopy(dArr, 0, dArr2, this.numElements, dArr.length);
        this.internalArray = dArr2;
        this.startIndex = 0;
        this.numElements += dArr.length;
    }

    public synchronized double addElementRolling(double d) {
        double d2;
        double[] dArr = this.internalArray;
        int i = this.startIndex;
        d2 = dArr[i];
        if (i + this.numElements + 1 > dArr.length) {
            expand();
        }
        int i2 = this.startIndex + 1;
        this.startIndex = i2;
        this.internalArray[i2 + (this.numElements - 1)] = d;
        if (shouldContract()) {
            contract();
        }
        return d2;
    }

    public synchronized double substituteMostRecentElement(double d) throws MathIllegalStateException {
        double d2;
        int i = this.numElements;
        if (i >= 1) {
            int i2 = this.startIndex + (i - 1);
            double[] dArr = this.internalArray;
            d2 = dArr[i2];
            dArr[i2] = d;
        } else {
            throw new MathIllegalStateException(LocalizedFormats.CANNOT_SUBSTITUTE_ELEMENT_FROM_EMPTY_ARRAY, new Object[0]);
        }
        return d2;
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void checkContractExpand(float f, float f2) throws MathIllegalArgumentException {
        checkContractExpand((double) f, (double) f2);
    }

    /* access modifiers changed from: protected */
    public void checkContractExpand(double d, double d2) throws NumberIsTooSmallException {
        if (d < d2) {
            NumberIsTooSmallException numberIsTooSmallException = new NumberIsTooSmallException(Double.valueOf(d), 1, true);
            numberIsTooSmallException.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_EXPANSION_FACTOR, Double.valueOf(d), Double.valueOf(d2));
            throw numberIsTooSmallException;
        } else if (d <= 1.0d) {
            NumberIsTooSmallException numberIsTooSmallException2 = new NumberIsTooSmallException(Double.valueOf(d), 1, false);
            numberIsTooSmallException2.getContext().addMessage(LocalizedFormats.CONTRACTION_CRITERIA_SMALLER_THAN_ONE, Double.valueOf(d));
            throw numberIsTooSmallException2;
        } else if (d2 <= 1.0d) {
            NumberIsTooSmallException numberIsTooSmallException3 = new NumberIsTooSmallException(Double.valueOf(d), 1, false);
            numberIsTooSmallException3.getContext().addMessage(LocalizedFormats.EXPANSION_FACTOR_SMALLER_THAN_ONE, Double.valueOf(d2));
            throw numberIsTooSmallException3;
        }
    }

    public synchronized void clear() {
        this.numElements = 0;
        this.startIndex = 0;
    }

    public synchronized void contract() {
        int i = this.numElements;
        double[] dArr = new double[(i + 1)];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, i);
        this.internalArray = dArr;
        this.startIndex = 0;
    }

    public synchronized void discardFrontElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, true);
    }

    public synchronized void discardMostRecentElements(int i) throws MathIllegalArgumentException {
        discardExtremeElements(i, false);
    }

    private synchronized void discardExtremeElements(int i, boolean z) throws MathIllegalArgumentException {
        int i2 = this.numElements;
        if (i > i2) {
            throw new MathIllegalArgumentException(LocalizedFormats.TOO_MANY_ELEMENTS_TO_DISCARD_FROM_ARRAY, Integer.valueOf(i), Integer.valueOf(this.numElements));
        } else if (i >= 0) {
            this.numElements = i2 - i;
            if (z) {
                this.startIndex += i;
            }
            if (shouldContract()) {
                contract();
            }
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.CANNOT_DISCARD_NEGATIVE_NUMBER_OF_ELEMENTS, Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void expand() {
        int i;
        if (this.expansionMode == ExpansionMode.MULTIPLICATIVE) {
            i = (int) FastMath.ceil(((double) this.internalArray.length) * this.expansionFactor);
        } else {
            i = (int) (((long) this.internalArray.length) + FastMath.round(this.expansionFactor));
        }
        double[] dArr = new double[i];
        double[] dArr2 = this.internalArray;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        this.internalArray = dArr;
    }

    private synchronized void expandTo(int i) {
        double[] dArr = new double[i];
        double[] dArr2 = this.internalArray;
        System.arraycopy(dArr2, 0, dArr, 0, dArr2.length);
        this.internalArray = dArr;
    }

    @Deprecated
    public float getContractionCriteria() {
        return (float) getContractionCriterion();
    }

    public double getContractionCriterion() {
        return this.contractionCriterion;
    }

    public synchronized double getElement(int i) {
        if (i >= this.numElements) {
            throw new ArrayIndexOutOfBoundsException(i);
        } else if (i >= 0) {
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return this.internalArray[this.startIndex + i];
    }

    public synchronized double[] getElements() {
        double[] dArr;
        int i = this.numElements;
        dArr = new double[i];
        System.arraycopy(this.internalArray, this.startIndex, dArr, 0, i);
        return dArr;
    }

    @Deprecated
    public float getExpansionFactor() {
        return (float) this.expansionFactor;
    }

    /* renamed from: org.apache.commons.math3.util.ResizableDoubleArray$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode[] r0 = org.apache.commons.math3.util.ResizableDoubleArray.ExpansionMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode = r0
                org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r1 = org.apache.commons.math3.util.ResizableDoubleArray.ExpansionMode.MULTIPLICATIVE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode     // Catch:{ NoSuchFieldError -> 0x001d }
                org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r1 = org.apache.commons.math3.util.ResizableDoubleArray.ExpansionMode.ADDITIVE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ResizableDoubleArray.AnonymousClass1.<clinit>():void");
        }
    }

    @Deprecated
    public int getExpansionMode() {
        int i = AnonymousClass1.$SwitchMap$org$apache$commons$math3$util$ResizableDoubleArray$ExpansionMode[this.expansionMode.ordinal()];
        if (i == 1) {
            return 0;
        }
        if (i == 2) {
            return 1;
        }
        throw new MathInternalError();
    }

    /* access modifiers changed from: package-private */
    @Deprecated
    public synchronized int getInternalLength() {
        return this.internalArray.length;
    }

    public int getCapacity() {
        return this.internalArray.length;
    }

    public synchronized int getNumElements() {
        return this.numElements;
    }

    @Deprecated
    public synchronized double[] getInternalValues() {
        return this.internalArray;
    }

    /* access modifiers changed from: protected */
    public double[] getArrayRef() {
        return this.internalArray;
    }

    /* access modifiers changed from: protected */
    public int getStartIndex() {
        return this.startIndex;
    }

    @Deprecated
    public void setContractionCriteria(float f) throws MathIllegalArgumentException {
        checkContractExpand(f, getExpansionFactor());
        synchronized (this) {
            this.contractionCriterion = (double) f;
        }
    }

    public double compute(MathArrays.Function function) {
        double[] dArr;
        int i;
        int i2;
        synchronized (this) {
            dArr = this.internalArray;
            i = this.startIndex;
            i2 = this.numElements;
        }
        return function.evaluate(dArr, i, i2);
    }

    public synchronized void setElement(int i, double d) {
        if (i >= 0) {
            int i2 = i + 1;
            if (i2 > this.numElements) {
                this.numElements = i2;
            }
            int i3 = this.startIndex;
            if (i3 + i >= this.internalArray.length) {
                expandTo(i3 + i2);
            }
            this.internalArray[this.startIndex + i] = d;
        } else {
            throw new ArrayIndexOutOfBoundsException(i);
        }
    }

    @Deprecated
    public void setExpansionFactor(float f) throws MathIllegalArgumentException {
        double d = (double) f;
        checkContractExpand(getContractionCriterion(), d);
        synchronized (this) {
            this.expansionFactor = d;
        }
    }

    @Deprecated
    public void setExpansionMode(int i) throws MathIllegalArgumentException {
        if (i == 0 || i == 1) {
            synchronized (this) {
                if (i == 0) {
                    try {
                        setExpansionMode(ExpansionMode.MULTIPLICATIVE);
                    } catch (Throwable th) {
                        throw th;
                    }
                } else if (i == 1) {
                    setExpansionMode(ExpansionMode.ADDITIVE);
                }
            }
            return;
        }
        throw new MathIllegalArgumentException(LocalizedFormats.UNSUPPORTED_EXPANSION_MODE, Integer.valueOf(i), 0, "MULTIPLICATIVE_MODE", 1, "ADDITIVE_MODE");
    }

    @Deprecated
    public void setExpansionMode(ExpansionMode expansionMode2) {
        this.expansionMode = expansionMode2;
    }

    public synchronized void setNumElements(int i) throws MathIllegalArgumentException {
        if (i >= 0) {
            int i2 = this.startIndex + i;
            if (i2 > this.internalArray.length) {
                expandTo(i2);
            }
            this.numElements = i;
        } else {
            throw new MathIllegalArgumentException(LocalizedFormats.INDEX_NOT_POSITIVE, Integer.valueOf(i));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002c, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean shouldContract() {
        /*
            r6 = this;
            monitor-enter(r6)
            org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r0 = r6.expansionMode     // Catch:{ all -> 0x002d }
            org.apache.commons.math3.util.ResizableDoubleArray$ExpansionMode r1 = org.apache.commons.math3.util.ResizableDoubleArray.ExpansionMode.MULTIPLICATIVE     // Catch:{ all -> 0x002d }
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x001c
            double[] r0 = r6.internalArray     // Catch:{ all -> 0x002d }
            int r0 = r0.length     // Catch:{ all -> 0x002d }
            float r0 = (float) r0     // Catch:{ all -> 0x002d }
            int r1 = r6.numElements     // Catch:{ all -> 0x002d }
            float r1 = (float) r1     // Catch:{ all -> 0x002d }
            float r0 = r0 / r1
            double r0 = (double) r0     // Catch:{ all -> 0x002d }
            double r4 = r6.contractionCriterion     // Catch:{ all -> 0x002d }
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0019
            goto L_0x001a
        L_0x0019:
            r2 = r3
        L_0x001a:
            monitor-exit(r6)
            return r2
        L_0x001c:
            double[] r0 = r6.internalArray     // Catch:{ all -> 0x002d }
            int r0 = r0.length     // Catch:{ all -> 0x002d }
            int r1 = r6.numElements     // Catch:{ all -> 0x002d }
            int r0 = r0 - r1
            double r0 = (double) r0     // Catch:{ all -> 0x002d }
            double r4 = r6.contractionCriterion     // Catch:{ all -> 0x002d }
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x002a
            goto L_0x002b
        L_0x002a:
            r2 = r3
        L_0x002b:
            monitor-exit(r6)
            return r2
        L_0x002d:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x002d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.util.ResizableDoubleArray.shouldContract():boolean");
    }

    @Deprecated
    public synchronized int start() {
        return this.startIndex;
    }

    public static void copy(ResizableDoubleArray resizableDoubleArray, ResizableDoubleArray resizableDoubleArray2) throws NullArgumentException {
        MathUtils.checkNotNull(resizableDoubleArray);
        MathUtils.checkNotNull(resizableDoubleArray2);
        synchronized (resizableDoubleArray) {
            synchronized (resizableDoubleArray2) {
                resizableDoubleArray2.contractionCriterion = resizableDoubleArray.contractionCriterion;
                resizableDoubleArray2.expansionFactor = resizableDoubleArray.expansionFactor;
                resizableDoubleArray2.expansionMode = resizableDoubleArray.expansionMode;
                double[] dArr = new double[resizableDoubleArray.internalArray.length];
                resizableDoubleArray2.internalArray = dArr;
                System.arraycopy(resizableDoubleArray.internalArray, 0, dArr, 0, dArr.length);
                resizableDoubleArray2.numElements = resizableDoubleArray.numElements;
                resizableDoubleArray2.startIndex = resizableDoubleArray.startIndex;
            }
        }
    }

    public synchronized ResizableDoubleArray copy() {
        ResizableDoubleArray resizableDoubleArray;
        resizableDoubleArray = new ResizableDoubleArray();
        copy(this, resizableDoubleArray);
        return resizableDoubleArray;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ResizableDoubleArray)) {
            return false;
        }
        synchronized (this) {
            synchronized (obj) {
                ResizableDoubleArray resizableDoubleArray = (ResizableDoubleArray) obj;
                if (resizableDoubleArray.contractionCriterion != this.contractionCriterion || resizableDoubleArray.expansionFactor != this.expansionFactor || resizableDoubleArray.expansionMode != this.expansionMode || resizableDoubleArray.numElements != this.numElements || resizableDoubleArray.startIndex != this.startIndex) {
                    return false;
                }
                boolean equals = Arrays.equals(this.internalArray, resizableDoubleArray.internalArray);
                return equals;
            }
        }
    }

    public synchronized int hashCode() {
        return Arrays.hashCode(new int[]{Double.valueOf(this.expansionFactor).hashCode(), Double.valueOf(this.contractionCriterion).hashCode(), this.expansionMode.hashCode(), Arrays.hashCode(this.internalArray), this.numElements, this.startIndex});
    }
}
