package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class OpenIntToDoubleHashMap implements Serializable {
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int PERTURB_SHIFT = 5;
    protected static final byte REMOVED = 2;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final long serialVersionUID = -3646337053166149105L;
    /* access modifiers changed from: private */
    public transient int count;
    /* access modifiers changed from: private */
    public int[] keys;
    private int mask;
    private final double missingEntries;
    private int size;
    /* access modifiers changed from: private */
    public byte[] states;
    /* access modifiers changed from: private */
    public double[] values;

    private static int changeIndexSign(int i) {
        return (-i) - 1;
    }

    private static int hashOf(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private static int perturb(int i) {
        return i & Integer.MAX_VALUE;
    }

    private static int probe(int i, int i2) {
        return (i2 << 2) + i2 + i + 1;
    }

    public OpenIntToDoubleHashMap() {
        this(16, Double.NaN);
    }

    public OpenIntToDoubleHashMap(double d) {
        this(16, d);
    }

    public OpenIntToDoubleHashMap(int i) {
        this(i, Double.NaN);
    }

    public OpenIntToDoubleHashMap(int i, double d) {
        int computeCapacity = computeCapacity(i);
        this.keys = new int[computeCapacity];
        this.values = new double[computeCapacity];
        this.states = new byte[computeCapacity];
        this.missingEntries = d;
        this.mask = computeCapacity - 1;
    }

    public OpenIntToDoubleHashMap(OpenIntToDoubleHashMap openIntToDoubleHashMap) {
        int length = openIntToDoubleHashMap.keys.length;
        int[] iArr = new int[length];
        this.keys = iArr;
        System.arraycopy(openIntToDoubleHashMap.keys, 0, iArr, 0, length);
        double[] dArr = new double[length];
        this.values = dArr;
        System.arraycopy(openIntToDoubleHashMap.values, 0, dArr, 0, length);
        byte[] bArr = new byte[length];
        this.states = bArr;
        System.arraycopy(openIntToDoubleHashMap.states, 0, bArr, 0, length);
        this.missingEntries = openIntToDoubleHashMap.missingEntries;
        this.size = openIntToDoubleHashMap.size;
        this.mask = openIntToDoubleHashMap.mask;
        this.count = openIntToDoubleHashMap.count;
    }

    private static int computeCapacity(int i) {
        if (i == 0) {
            return 1;
        }
        int ceil = (int) FastMath.ceil((double) (((float) i) / 0.5f));
        if (Integer.highestOneBit(ceil) == ceil) {
            return ceil;
        }
        return nextPowerOfTwo(ceil);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    public double get(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return this.values[i2];
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return this.values[i2];
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    public boolean containsKey(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return true;
        }
        if (this.states[i2] == 0) {
            return false;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return true;
            }
            perturb >>= 5;
        }
        return false;
    }

    public Iterator iterator() {
        return new Iterator();
    }

    private int findInsertionIndex(int i) {
        return findInsertionIndex(this.keys, this.states, i, this.mask);
    }

    private static int findInsertionIndex(int[] iArr, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int hashOf = hashOf(i);
        int i5 = hashOf & i2;
        byte b2 = bArr[i5];
        if (b2 == 0) {
            return i5;
        }
        if (b2 == 1 && iArr[i5] == i) {
            return changeIndexSign(i5);
        }
        int perturb = perturb(hashOf);
        if (bArr[i5] == 1) {
            do {
                i5 = probe(perturb, i5);
                i4 = i5 & i2;
                perturb >>= 5;
                if (bArr[i4] != 1 || iArr[i4] == i) {
                    int i6 = i4;
                    i3 = i5;
                    i5 = i6;
                }
                i5 = probe(perturb, i5);
                i4 = i5 & i2;
                perturb >>= 5;
                break;
            } while (iArr[i4] == i);
            int i62 = i4;
            i3 = i5;
            i5 = i62;
        } else {
            i3 = i5;
        }
        byte b3 = bArr[i5];
        if (b3 == 0) {
            return i5;
        }
        if (b3 == 1) {
            return changeIndexSign(i5);
        }
        while (true) {
            i3 = probe(perturb, i3);
            int i7 = i3 & i2;
            byte b4 = bArr[i7];
            if (b4 == 0) {
                return i5;
            }
            if (b4 == 1 && iArr[i7] == i) {
                return changeIndexSign(i7);
            }
            perturb >>= 5;
        }
    }

    public int size() {
        return this.size;
    }

    public double remove(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return doRemove(i2);
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return doRemove(i2);
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    private boolean containsKey(int i, int i2) {
        return (i != 0 || this.states[i2] == 1) && this.keys[i2] == i;
    }

    private double doRemove(int i) {
        this.keys[i] = 0;
        this.states[i] = 2;
        double[] dArr = this.values;
        double d = dArr[i];
        dArr[i] = this.missingEntries;
        this.size--;
        this.count++;
        return d;
    }

    public double put(int i, double d) {
        double d2;
        boolean z;
        int findInsertionIndex = findInsertionIndex(i);
        double d3 = this.missingEntries;
        if (findInsertionIndex < 0) {
            findInsertionIndex = changeIndexSign(findInsertionIndex);
            d2 = this.values[findInsertionIndex];
            z = false;
        } else {
            d2 = d3;
            z = true;
        }
        this.keys[findInsertionIndex] = i;
        this.states[findInsertionIndex] = 1;
        this.values[findInsertionIndex] = d;
        if (z) {
            this.size++;
            if (shouldGrowTable()) {
                growTable();
            }
            this.count++;
        }
        return d2;
    }

    private void growTable() {
        byte[] bArr = this.states;
        int length = bArr.length;
        int[] iArr = this.keys;
        double[] dArr = this.values;
        int i = length * 2;
        int[] iArr2 = new int[i];
        double[] dArr2 = new double[i];
        byte[] bArr2 = new byte[i];
        int i2 = i - 1;
        for (int i3 = 0; i3 < length; i3++) {
            if (bArr[i3] == 1) {
                int i4 = iArr[i3];
                int findInsertionIndex = findInsertionIndex(iArr2, bArr2, i4, i2);
                iArr2[findInsertionIndex] = i4;
                dArr2[findInsertionIndex] = dArr[i3];
                bArr2[findInsertionIndex] = 1;
            }
        }
        this.mask = i2;
        this.keys = iArr2;
        this.values = dArr2;
        this.states = bArr2;
    }

    private boolean shouldGrowTable() {
        return ((float) this.size) > ((float) (this.mask + 1)) * 0.5f;
    }

    public class Iterator {
        private int current;
        private int next;
        private final int referenceCount;

        private Iterator() {
            this.referenceCount = OpenIntToDoubleHashMap.this.count;
            this.next = -1;
            try {
                advance();
            } catch (NoSuchElementException unused) {
            }
        }

        public boolean hasNext() {
            return this.next >= 0;
        }

        public int key() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToDoubleHashMap.this.keys[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public double value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToDoubleHashMap.this.values[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            byte[] access$400;
            int i;
            if (this.referenceCount == OpenIntToDoubleHashMap.this.count) {
                this.current = this.next;
                do {
                    try {
                        access$400 = OpenIntToDoubleHashMap.this.states;
                        i = this.next + 1;
                        this.next = i;
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        this.next = -2;
                        if (this.current < 0) {
                            throw new NoSuchElementException();
                        }
                        return;
                    }
                } while (access$400[i] != 1);
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count = 0;
    }
}
