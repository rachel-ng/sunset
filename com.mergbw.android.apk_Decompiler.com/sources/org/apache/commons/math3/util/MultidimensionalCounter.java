package org.apache.commons.math3.util;

import java.util.NoSuchElementException;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

public class MultidimensionalCounter implements Iterable<Integer> {
    /* access modifiers changed from: private */
    public final int dimension;
    /* access modifiers changed from: private */
    public final int last;
    /* access modifiers changed from: private */
    public final int[] size;
    /* access modifiers changed from: private */
    public final int totalSize;
    private final int[] uniCounterOffset;

    public class Iterator implements java.util.Iterator<Integer> {
        private int count = -1;
        private final int[] counter;
        private final int maxCount;

        Iterator() {
            int[] iArr = new int[MultidimensionalCounter.this.dimension];
            this.counter = iArr;
            this.maxCount = MultidimensionalCounter.this.totalSize - 1;
            iArr[MultidimensionalCounter.this.last] = -1;
        }

        public boolean hasNext() {
            return this.count < this.maxCount;
        }

        public Integer next() {
            if (hasNext()) {
                int access$200 = MultidimensionalCounter.this.last;
                while (true) {
                    if (access$200 >= 0) {
                        if (this.counter[access$200] != MultidimensionalCounter.this.size[access$200] - 1) {
                            int[] iArr = this.counter;
                            iArr[access$200] = iArr[access$200] + 1;
                            break;
                        }
                        this.counter[access$200] = 0;
                        access$200--;
                    } else {
                        break;
                    }
                }
                int i = this.count + 1;
                this.count = i;
                return Integer.valueOf(i);
            }
            throw new NoSuchElementException();
        }

        public int getCount() {
            return this.count;
        }

        public int[] getCounts() {
            return MathArrays.copyOf(this.counter);
        }

        public int getCount(int i) {
            return this.counter[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public MultidimensionalCounter(int... iArr) throws NotStrictlyPositiveException {
        int i;
        int length = iArr.length;
        this.dimension = length;
        this.size = MathArrays.copyOf(iArr);
        this.uniCounterOffset = new int[length];
        int i2 = length - 1;
        this.last = i2;
        int i3 = iArr[i2];
        int i4 = 0;
        while (true) {
            i = this.last;
            if (i4 >= i) {
                break;
            }
            int i5 = i4 + 1;
            int i6 = 1;
            for (int i7 = i5; i7 < this.dimension; i7++) {
                i6 *= iArr[i7];
            }
            this.uniCounterOffset[i4] = i6;
            i3 *= iArr[i4];
            i4 = i5;
        }
        this.uniCounterOffset[i] = 0;
        if (i3 > 0) {
            this.totalSize = i3;
            return;
        }
        throw new NotStrictlyPositiveException(Integer.valueOf(i3));
    }

    public Iterator iterator() {
        return new Iterator();
    }

    public int getDimension() {
        return this.dimension;
    }

    public int[] getCounts(int i) throws OutOfRangeException {
        if (i < 0 || i >= this.totalSize) {
            throw new OutOfRangeException(Integer.valueOf(i), 0, Integer.valueOf(this.totalSize));
        }
        int[] iArr = new int[this.dimension];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int i4 = this.last;
            if (i2 < i4) {
                int i5 = this.uniCounterOffset[i2];
                int i6 = 0;
                while (i3 <= i) {
                    i3 += i5;
                    i6++;
                }
                i3 -= i5;
                iArr[i2] = i6 - 1;
                i2++;
            } else {
                iArr[i4] = i - i3;
                return iArr;
            }
        }
    }

    public int getCount(int... iArr) throws OutOfRangeException, DimensionMismatchException {
        if (iArr.length == this.dimension) {
            int i = 0;
            for (int i2 = 0; i2 < this.dimension; i2++) {
                int i3 = iArr[i2];
                if (i3 < 0 || i3 >= this.size[i2]) {
                    throw new OutOfRangeException(Integer.valueOf(i3), 0, Integer.valueOf(this.size[i2] - 1));
                }
                i += this.uniCounterOffset[i2] * i3;
            }
            return i + iArr[this.last];
        }
        throw new DimensionMismatchException(iArr.length, this.dimension);
    }

    public int getSize() {
        return this.totalSize;
    }

    public int[] getSizes() {
        return MathArrays.copyOf(this.size);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dimension; i++) {
            sb.append("[");
            sb.append(getCount(i));
            sb.append("]");
        }
        return sb.toString();
    }
}
