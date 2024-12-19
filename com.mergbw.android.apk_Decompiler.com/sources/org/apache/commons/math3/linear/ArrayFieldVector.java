package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathUtils;

public class ArrayFieldVector<T extends FieldElement<T>> implements FieldVector<T>, Serializable {
    private static final long serialVersionUID = 7648186910365927050L;
    private T[] data;
    private final Field<T> field;

    public ArrayFieldVector(Field<T> field2) {
        this(field2, 0);
    }

    public ArrayFieldVector(Field<T> field2, int i) {
        this.field = field2;
        this.data = (FieldElement[]) MathArrays.buildArray(field2, i);
    }

    public ArrayFieldVector(int i, T t) {
        this(t.getField(), i);
        Arrays.fill(this.data, t);
    }

    public ArrayFieldVector(T[] tArr) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        try {
            this.field = tArr[0].getField();
            this.data = (FieldElement[]) tArr.clone();
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr) throws NullArgumentException {
        MathUtils.checkNotNull(tArr);
        this.field = field2;
        this.data = (FieldElement[]) tArr.clone();
    }

    public ArrayFieldVector(T[] tArr, boolean z) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        if (tArr.length != 0) {
            this.field = tArr[0].getField();
            this.data = z ? (FieldElement[]) tArr.clone() : tArr;
            return;
        }
        throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: T[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ArrayFieldVector(org.apache.commons.math3.Field<T> r1, T[] r2, boolean r3) throws org.apache.commons.math3.exception.NullArgumentException {
        /*
            r0 = this;
            r0.<init>()
            org.apache.commons.math3.util.MathUtils.checkNotNull(r2)
            r0.field = r1
            if (r3 == 0) goto L_0x0011
            java.lang.Object r1 = r2.clone()
            r2 = r1
            org.apache.commons.math3.FieldElement[] r2 = (org.apache.commons.math3.FieldElement[]) r2
        L_0x0011:
            r0.data = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.math3.linear.ArrayFieldVector.<init>(org.apache.commons.math3.Field, org.apache.commons.math3.FieldElement[], boolean):void");
    }

    public ArrayFieldVector(T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        MathUtils.checkNotNull(tArr);
        int i3 = i + i2;
        if (tArr.length >= i3) {
            Field<T> field2 = tArr[0].getField();
            this.field = field2;
            T[] tArr2 = (FieldElement[]) MathArrays.buildArray(field2, i2);
            this.data = tArr2;
            System.arraycopy(tArr, i, tArr2, 0, i2);
            return;
        }
        throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(tArr.length), true);
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        MathUtils.checkNotNull(tArr);
        int i3 = i + i2;
        if (tArr.length >= i3) {
            this.field = field2;
            T[] tArr2 = (FieldElement[]) MathArrays.buildArray(field2, i2);
            this.data = tArr2;
            System.arraycopy(tArr, i, tArr2, 0, i2);
            return;
        }
        throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(tArr.length), true);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        Field<T> field2 = fieldVector.getField();
        this.field = field2;
        this.data = (FieldElement[]) MathArrays.buildArray(field2, fieldVector.getDimension());
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i < tArr.length) {
                tArr[i] = fieldVector.getEntry(i);
                i++;
            } else {
                return;
            }
        }
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(arrayFieldVector);
        this.field = arrayFieldVector.getField();
        this.data = (FieldElement[]) arrayFieldVector.data.clone();
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, boolean z) throws NullArgumentException {
        MathUtils.checkNotNull(arrayFieldVector);
        this.field = arrayFieldVector.getField();
        T[] tArr = arrayFieldVector.data;
        this.data = z ? (FieldElement[]) tArr.clone() : tArr;
    }

    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, ArrayFieldVector<T> arrayFieldVector2) throws NullArgumentException {
        this(arrayFieldVector, arrayFieldVector2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector, FieldVector<T> fieldVector2) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        MathUtils.checkNotNull(fieldVector2);
        Field<T> field2 = fieldVector.getField();
        this.field = field2;
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        T[] array2 = fieldVector2 instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector2).data : fieldVector2.toArray();
        T[] tArr = (FieldElement[]) MathArrays.buildArray(field2, array.length + array2.length);
        this.data = tArr;
        System.arraycopy(array, 0, tArr, 0, array.length);
        System.arraycopy(array2, 0, this.data, array.length, array2.length);
    }

    @Deprecated
    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, T[] tArr) throws NullArgumentException {
        this(arrayFieldVector, tArr);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector, T[] tArr) throws NullArgumentException {
        MathUtils.checkNotNull(fieldVector);
        MathUtils.checkNotNull(tArr);
        Field<T> field2 = fieldVector.getField();
        this.field = field2;
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        T[] tArr2 = (FieldElement[]) MathArrays.buildArray(field2, array.length + tArr.length);
        this.data = tArr2;
        System.arraycopy(array, 0, tArr2, 0, array.length);
        System.arraycopy(tArr, 0, this.data, array.length, tArr.length);
    }

    @Deprecated
    public ArrayFieldVector(T[] tArr, ArrayFieldVector<T> arrayFieldVector) throws NullArgumentException {
        this(tArr, arrayFieldVector);
    }

    public ArrayFieldVector(T[] tArr, FieldVector<T> fieldVector) throws NullArgumentException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(fieldVector);
        Field<T> field2 = fieldVector.getField();
        this.field = field2;
        T[] array = fieldVector instanceof ArrayFieldVector ? ((ArrayFieldVector) fieldVector).data : fieldVector.toArray();
        T[] tArr2 = (FieldElement[]) MathArrays.buildArray(field2, tArr.length + array.length);
        this.data = tArr2;
        System.arraycopy(tArr, 0, tArr2, 0, tArr.length);
        System.arraycopy(array, 0, this.data, tArr.length, array.length);
    }

    public ArrayFieldVector(T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(tArr2);
        if (tArr.length + tArr2.length != 0) {
            T[] tArr3 = (FieldElement[]) MathArrays.buildArray(tArr[0].getField(), tArr.length + tArr2.length);
            this.data = tArr3;
            System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
            System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
            this.field = this.data[0].getField();
            return;
        }
        throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        MathUtils.checkNotNull(tArr);
        MathUtils.checkNotNull(tArr2);
        if (tArr.length + tArr2.length != 0) {
            T[] tArr3 = (FieldElement[]) MathArrays.buildArray(field2, tArr.length + tArr2.length);
            this.data = tArr3;
            System.arraycopy(tArr, 0, tArr3, 0, tArr.length);
            System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
            this.field = field2;
            return;
        }
        throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
    }

    public Field<T> getField() {
        return this.field;
    }

    public FieldVector<T> copy() {
        return new ArrayFieldVector(this, true);
    }

    public FieldVector<T> add(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return add((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
                }
                fieldElementArr[i] = (FieldElement) tArr[i].add(fieldVector.getEntry(i));
                i++;
            }
        }
    }

    public ArrayFieldVector<T> add(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector<>(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].add(arrayFieldVector.data[i]);
            i++;
        }
    }

    public FieldVector<T> subtract(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return subtract((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
                }
                fieldElementArr[i] = (FieldElement) tArr[i].subtract(fieldVector.getEntry(i));
                i++;
            }
        }
    }

    public ArrayFieldVector<T> subtract(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector<>(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].subtract(arrayFieldVector.data[i]);
            i++;
        }
    }

    public FieldVector<T> mapAdd(T t) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].add(t);
            i++;
        }
    }

    public FieldVector<T> mapAddToSelf(T t) throws NullArgumentException {
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return this;
            }
            tArr[i] = (FieldElement) tArr[i].add(t);
            i++;
        }
    }

    public FieldVector<T> mapSubtract(T t) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].subtract(t);
            i++;
        }
    }

    public FieldVector<T> mapSubtractToSelf(T t) throws NullArgumentException {
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return this;
            }
            tArr[i] = (FieldElement) tArr[i].subtract(t);
            i++;
        }
    }

    public FieldVector<T> mapMultiply(T t) throws NullArgumentException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].multiply(t);
            i++;
        }
    }

    public FieldVector<T> mapMultiplyToSelf(T t) throws NullArgumentException {
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return this;
            }
            tArr[i] = (FieldElement) tArr[i].multiply(t);
            i++;
        }
    }

    public FieldVector<T> mapDivide(T t) throws NullArgumentException, MathArithmeticException {
        MathUtils.checkNotNull(t);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].divide(t);
            i++;
        }
    }

    public FieldVector<T> mapDivideToSelf(T t) throws NullArgumentException, MathArithmeticException {
        MathUtils.checkNotNull(t);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return this;
            }
            tArr[i] = (FieldElement) tArr[i].divide(t);
            i++;
        }
    }

    public FieldVector<T> mapInv() throws MathArithmeticException {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        FieldElement fieldElement = (FieldElement) this.field.getOne();
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
            }
            try {
                fieldElementArr[i] = (FieldElement) fieldElement.divide(tArr[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
    }

    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        FieldElement fieldElement = (FieldElement) this.field.getOne();
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return this;
            }
            try {
                tArr[i] = (FieldElement) fieldElement.divide(tArr[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
    }

    public FieldVector<T> ebeMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return ebeMultiply((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
                }
                fieldElementArr[i] = (FieldElement) tArr[i].multiply(fieldVector.getEntry(i));
                i++;
            }
        }
    }

    public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector<>(this.field, (T[]) fieldElementArr, false);
            }
            fieldElementArr[i] = (FieldElement) tArr[i].multiply(arrayFieldVector.data[i]);
            i++;
        }
    }

    public FieldVector<T> ebeDivide(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        try {
            return ebeDivide((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
                }
                try {
                    fieldElementArr[i] = (FieldElement) tArr[i].divide(fieldVector.getEntry(i));
                    i++;
                } catch (MathArithmeticException unused2) {
                    throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
                }
            }
        }
    }

    public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length);
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return new ArrayFieldVector<>(this.field, (T[]) fieldElementArr, false);
            }
            try {
                fieldElementArr[i] = (FieldElement) tArr[i].divide(arrayFieldVector.data[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
    }

    public T[] getData() {
        return (FieldElement[]) this.data.clone();
    }

    public T[] getDataRef() {
        return this.data;
    }

    public T dotProduct(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return dotProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            T t = (FieldElement) this.field.getZero();
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return t;
                }
                t = (FieldElement) t.add(tArr[i].multiply(fieldVector.getEntry(i)));
                i++;
            }
        }
    }

    public T dotProduct(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        T t = (FieldElement) this.field.getZero();
        int i = 0;
        while (true) {
            T[] tArr = this.data;
            if (i >= tArr.length) {
                return t;
            }
            t = (FieldElement) t.add(tArr[i].multiply(arrayFieldVector.data[i]));
            i++;
        }
    }

    public FieldVector<T> projection(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        return fieldVector.mapMultiply((FieldElement) dotProduct(fieldVector).divide(fieldVector.dotProduct(fieldVector)));
    }

    public ArrayFieldVector<T> projection(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException, MathArithmeticException {
        return (ArrayFieldVector) arrayFieldVector.mapMultiply((FieldElement) dotProduct(arrayFieldVector).divide(arrayFieldVector.dotProduct(arrayFieldVector)));
    }

    public FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) {
        try {
            return outerProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            int length = this.data.length;
            int dimension = fieldVector.getDimension();
            Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, dimension);
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < dimension; i2++) {
                    array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(fieldVector.getEntry(i2)));
                }
            }
            return array2DRowFieldMatrix;
        }
    }

    public FieldMatrix<T> outerProduct(ArrayFieldVector<T> arrayFieldVector) {
        int length = this.data.length;
        int length2 = arrayFieldVector.data.length;
        Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, length2);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(arrayFieldVector.data[i2]));
            }
        }
        return array2DRowFieldMatrix;
    }

    public T getEntry(int i) {
        return this.data[i];
    }

    public int getDimension() {
        return this.data.length;
    }

    public FieldVector<T> append(FieldVector<T> fieldVector) {
        try {
            return append((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            return new ArrayFieldVector(this, new ArrayFieldVector(fieldVector));
        }
    }

    public ArrayFieldVector<T> append(ArrayFieldVector<T> arrayFieldVector) {
        return new ArrayFieldVector<>(this, (ArrayFieldVector) arrayFieldVector);
    }

    public FieldVector<T> append(T t) {
        FieldElement[] fieldElementArr = (FieldElement[]) MathArrays.buildArray(this.field, this.data.length + 1);
        T[] tArr = this.data;
        System.arraycopy(tArr, 0, fieldElementArr, 0, tArr.length);
        fieldElementArr[this.data.length] = t;
        return new ArrayFieldVector(this.field, (T[]) fieldElementArr, false);
    }

    public FieldVector<T> getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 >= 0) {
            ArrayFieldVector arrayFieldVector = new ArrayFieldVector(this.field, i2);
            try {
                System.arraycopy(this.data, i, arrayFieldVector.data, 0, i2);
            } catch (IndexOutOfBoundsException unused) {
                checkIndex(i);
                checkIndex((i + i2) - 1);
            }
            return arrayFieldVector;
        }
        throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
    }

    public void setEntry(int i, T t) {
        try {
            this.data[i] = t;
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
        }
    }

    public void setSubVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException {
        try {
            set(i, (ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            int i2 = i;
            while (i2 < fieldVector.getDimension() + i) {
                try {
                    this.data[i2] = fieldVector.getEntry(i2 - i);
                    i2++;
                } catch (IndexOutOfBoundsException unused2) {
                    checkIndex(i);
                    checkIndex((i + fieldVector.getDimension()) - 1);
                    return;
                }
            }
        }
    }

    public void set(int i, ArrayFieldVector<T> arrayFieldVector) throws OutOfRangeException {
        try {
            T[] tArr = arrayFieldVector.data;
            System.arraycopy(tArr, 0, this.data, i, tArr.length);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + arrayFieldVector.data.length) - 1);
        }
    }

    public void set(T t) {
        Arrays.fill(this.data, t);
    }

    public T[] toArray() {
        return (FieldElement[]) this.data.clone();
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(FieldVector<T> fieldVector) throws DimensionMismatchException {
        checkVectorDimensions(fieldVector.getDimension());
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(int i) throws DimensionMismatchException {
        if (this.data.length != i) {
            throw new DimensionMismatchException(this.data.length, i);
        }
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> fieldVectorPreservingVisitor) {
        int dimension = getDimension();
        fieldVectorPreservingVisitor.start(dimension, 0, dimension - 1);
        for (int i = 0; i < dimension; i++) {
            fieldVectorPreservingVisitor.visit(i, getEntry(i));
        }
        return fieldVectorPreservingVisitor.end();
    }

    public T walkInDefaultOrder(FieldVectorPreservingVisitor<T> fieldVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        fieldVectorPreservingVisitor.start(getDimension(), i, i2);
        while (i <= i2) {
            fieldVectorPreservingVisitor.visit(i, getEntry(i));
            i++;
        }
        return fieldVectorPreservingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> fieldVectorPreservingVisitor) {
        return walkInDefaultOrder(fieldVectorPreservingVisitor);
    }

    public T walkInOptimizedOrder(FieldVectorPreservingVisitor<T> fieldVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(fieldVectorPreservingVisitor, i, i2);
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor) {
        int dimension = getDimension();
        fieldVectorChangingVisitor.start(dimension, 0, dimension - 1);
        for (int i = 0; i < dimension; i++) {
            setEntry(i, fieldVectorChangingVisitor.visit(i, getEntry(i)));
        }
        return fieldVectorChangingVisitor.end();
    }

    public T walkInDefaultOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        fieldVectorChangingVisitor.start(getDimension(), i, i2);
        while (i <= i2) {
            setEntry(i, fieldVectorChangingVisitor.visit(i, getEntry(i)));
            i++;
        }
        return fieldVectorChangingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor) {
        return walkInDefaultOrder(fieldVectorChangingVisitor);
    }

    public T walkInOptimizedOrder(FieldVectorChangingVisitor<T> fieldVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(fieldVectorChangingVisitor, i, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            FieldVector fieldVector = (FieldVector) obj;
            if (this.data.length != fieldVector.getDimension()) {
                return false;
            }
            int i = 0;
            while (true) {
                T[] tArr = this.data;
                if (i >= tArr.length) {
                    return true;
                }
                if (!tArr[i].equals(fieldVector.getEntry(i))) {
                    return false;
                }
                i++;
            }
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        int i = 3542;
        for (T hashCode : this.data) {
            i ^= hashCode.hashCode();
        }
        return i;
    }

    private void checkIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getDimension()) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), 0, Integer.valueOf(getDimension() - 1));
        }
    }

    private void checkIndices(int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        int dimension = getDimension();
        if (i < 0 || i >= dimension) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), 0, Integer.valueOf(dimension - 1));
        } else if (i2 < 0 || i2 >= dimension) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i2), 0, Integer.valueOf(dimension - 1));
        } else if (i2 < i) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(i2), Integer.valueOf(i), false);
        }
    }
}
