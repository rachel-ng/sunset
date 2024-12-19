package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class LongLargeArray extends LargeArray {
    private static final long serialVersionUID = -2579271120060523901L;
    private long[] data;

    public LongLargeArray(long j) {
        this(j, true);
    }

    public LongLargeArray(long j, boolean z) {
        this.type = LargeArrayType.LONG;
        this.sizeof = 8;
        if (j > 0) {
            this.length = j;
            if (j > ((long) getMaxSizeOf32bitArray())) {
                this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.length * this.sizeof);
                if (z) {
                    zeroNativeMemory(j);
                }
                Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.length, this.sizeof));
                MemoryCounter.increaseCounter(this.length * this.sizeof);
                return;
            }
            this.data = new long[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public LongLargeArray(long j, long j2) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new long[]{j2};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public LongLargeArray(long[] jArr) {
        this.type = LargeArrayType.LONG;
        this.sizeof = 8;
        this.length = (long) jArr.length;
        this.data = jArr;
    }

    public LongLargeArray clone() {
        if (this.isConstant) {
            return new LongLargeArray(this.length, getLong(0));
        }
        LongLargeArray longLargeArray = new LongLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, longLargeArray, 0, this.length);
        return longLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((LongLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        long[] jArr = this.data;
        return hashCode + (jArr != null ? jArr.hashCode() : 0);
    }

    public final Long get(long j) {
        return Long.valueOf(getLong(j));
    }

    public final Long getFromNative(long j) {
        return Long.valueOf(LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)) != 0) {
                return true;
            }
            return false;
        } else if (this.isConstant) {
            if (this.data[0] != 0) {
                return true;
            }
            return false;
        } else if (this.data[(int) j] != 0) {
            return true;
        } else {
            return false;
        }
    }

    public final byte getByte(long j) {
        long j2;
        int i;
        if (this.ptr != 0) {
            j2 = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = (int) this.data[0];
            return (byte) i;
        } else {
            j2 = this.data[(int) j];
        }
        i = (int) j2;
        return (byte) i;
    }

    public final short getUnsignedByte(long j) {
        long j2;
        long j3;
        if (this.ptr != 0) {
            j2 = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            j3 = this.data[0] & 255;
            return (short) ((int) j3);
        } else {
            j2 = this.data[(int) j];
        }
        j3 = j2 & 255;
        return (short) ((int) j3);
    }

    public final short getShort(long j) {
        long j2;
        int i;
        if (this.ptr != 0) {
            j2 = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = (int) this.data[0];
            return (short) i;
        } else {
            j2 = this.data[(int) j];
        }
        i = (int) j2;
        return (short) i;
    }

    public final int getInt(long j) {
        if (this.ptr != 0) {
            return (int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (int) this.data[0];
        }
        return (int) this.data[(int) j];
    }

    public final long getLong(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final float getFloat(long j) {
        if (this.ptr != 0) {
            return (float) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (float) this.data[0];
        }
        return (float) this.data[(int) j];
    }

    public final double getDouble(long j) {
        if (this.ptr != 0) {
            return (double) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (double) this.data[0];
        }
        return (double) this.data[(int) j];
    }

    public final long[] getData() {
        return this.data;
    }

    public final boolean[] getBooleanData() {
        if (this.length > 1073741824) {
            return null;
        }
        boolean[] zArr = new boolean[((int) this.length)];
        boolean z = true;
        if (this.ptr != 0) {
            int i = 0;
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                zArr[i] = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)) != 0;
                i++;
            }
        } else if (this.isConstant) {
            if (this.data[0] == 0) {
                z = false;
            }
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; ((long) i3) < this.length; i3++) {
                zArr[i3] = this.data[i3] != 0;
            }
        }
        return zArr;
    }

    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        boolean[] zArr2 = zArr;
        long j4 = j3;
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j4 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j4));
            if (ceil > 1073741824) {
                return null;
            }
            if (zArr2 == null || ((long) zArr2.length) < ceil) {
                zArr2 = new boolean[((int) ceil)];
            }
            if (this.ptr != 0) {
                long j5 = j;
                int i = 0;
                while (j5 < j2) {
                    int i2 = i + 1;
                    zArr2[i] = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j5)) != 0;
                    j5 += j4;
                    i = i2;
                }
            } else if (this.isConstant) {
                long j6 = j;
                int i3 = 0;
                while (j6 < j2) {
                    int i4 = i3 + 1;
                    zArr2[i3] = this.data[0] != 0;
                    j6 += j4;
                    i3 = i4;
                }
            } else {
                long j7 = j;
                int i5 = 0;
                while (j7 < j2) {
                    int i6 = i5 + 1;
                    zArr2[i5] = this.data[(int) j7] != 0;
                    j7 += j4;
                    i5 = i6;
                }
            }
            return zArr2;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final byte[] getByteData() {
        if (this.length > 1073741824) {
            return null;
        }
        byte[] bArr = new byte[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = (byte) ((int) this.data[0]);
            while (((long) i) < this.length) {
                bArr[i] = b2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                bArr[i] = (byte) ((int) this.data[i]);
                i++;
            }
        }
        return bArr;
    }

    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (bArr == null || ((long) bArr.length) < ceil) {
                bArr = new byte[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    bArr[i2] = (byte) ((int) this.data[0]);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    bArr[i] = (byte) ((int) this.data[(int) j]);
                    j += j3;
                    i++;
                }
            }
            return bArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final short[] getShortData() {
        if (this.length > 1073741824) {
            return null;
        }
        short[] sArr = new short[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            short s = (short) ((int) this.data[0]);
            while (((long) i) < this.length) {
                sArr[i] = s;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                sArr[i] = (short) ((int) this.data[i]);
                i++;
            }
        }
        return sArr;
    }

    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (sArr == null || ((long) sArr.length) < ceil) {
                sArr = new short[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j)));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    sArr[i2] = (short) ((int) this.data[0]);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    sArr[i] = (short) ((int) this.data[(int) j]);
                    j += j3;
                    i++;
                }
            }
            return sArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final int[] getIntData() {
        if (this.length > 1073741824) {
            return null;
        }
        int[] iArr = new int[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                iArr[i] = (int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            int i2 = (int) this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = i2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                iArr[i] = (int) this.data[i];
                i++;
            }
        }
        return iArr;
    }

    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (iArr == null || ((long) iArr.length) < ceil) {
                iArr = new int[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    iArr[i] = (int) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    iArr[i2] = (int) this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    iArr[i] = (int) this.data[(int) j];
                    j += j3;
                    i++;
                }
            }
            return iArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final long[] getLongData() {
        if (this.length > 1073741824) {
            return null;
        }
        long[] jArr = new long[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                jArr[i] = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            long j2 = this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = j2;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, jArr, 0, (int) this.length);
        }
        return jArr;
    }

    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (jArr == null || ((long) jArr.length) < ceil) {
                jArr = new long[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    jArr[i] = LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    jArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    jArr[i] = this.data[(int) j];
                    j += j3;
                    i++;
                }
            }
            return jArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final float[] getFloatData() {
        if (this.length > 1073741824) {
            return null;
        }
        float[] fArr = new float[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            float f = (float) this.data[0];
            while (((long) i) < this.length) {
                fArr[i] = f;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                fArr[i] = (float) this.data[i];
                i++;
            }
        }
        return fArr;
    }

    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (fArr == null || ((long) fArr.length) < ceil) {
                fArr = new float[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    fArr[i] = (float) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    fArr[i2] = (float) this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    fArr[i] = (float) this.data[(int) j];
                    j += j3;
                    i++;
                }
            }
            return fArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final double[] getDoubleData() {
        if (this.length > 1073741824) {
            return null;
        }
        double[] dArr = new double[((int) this.length)];
        int i = 0;
        if (this.ptr != 0) {
            while (true) {
                long j = (long) i;
                if (j >= this.length) {
                    break;
                }
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = (double) this.data[0];
            while (((long) i) < this.length) {
                dArr[i] = d;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                dArr[i] = (double) this.data[i];
                i++;
            }
        }
        return dArr;
    }

    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (dArr == null || ((long) dArr.length) < ceil) {
                dArr = new double[((int) ceil)];
            }
            int i = 0;
            if (this.ptr != 0) {
                while (j < j2) {
                    dArr[i] = (double) LargeArrayUtils.UNSAFE.getLong(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    dArr[i2] = (double) this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    dArr[i] = (double) this.data[(int) j];
                    j += j3;
                    i++;
                }
            }
            return dArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final void setToNative(long j, Object obj) {
        LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), ((Long) obj).longValue());
    }

    public final void setBoolean(long j, boolean z) {
        long j2 = 0;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j3 = this.ptr + (this.sizeof * j);
            if (z) {
                j2 = 1;
            }
            unsafe.putLong(j3, j2);
        } else if (!this.isConstant) {
            long[] jArr = this.data;
            int i = (int) j;
            if (z) {
                j2 = 1;
            }
            jArr[i] = j2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), (long) b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (long) b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), (long) s);
        } else if (!this.isConstant) {
            this.data[(int) j] = (long) s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), (long) i);
        } else if (!this.isConstant) {
            this.data[(int) j] = (long) i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), j2);
        } else if (!this.isConstant) {
            this.data[(int) j] = j2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), (long) f);
        } else if (!this.isConstant) {
            this.data[(int) j] = (long) f;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putLong(this.ptr + (this.sizeof * j), (long) d);
        } else if (!this.isConstant) {
            this.data[(int) j] = (long) d;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
