package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class IntLargeArray extends LargeArray {
    private static final long serialVersionUID = 86623276977976615L;
    private int[] data;

    public IntLargeArray(long j) {
        this(j, true);
    }

    public IntLargeArray(long j, boolean z) {
        this.type = LargeArrayType.INT;
        this.sizeof = 4;
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
            this.data = new int[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public IntLargeArray(long j, int i) {
        this.type = LargeArrayType.INT;
        this.sizeof = 4;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new int[]{i};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public IntLargeArray(int[] iArr) {
        this.type = LargeArrayType.INT;
        this.sizeof = 4;
        this.length = (long) iArr.length;
        this.data = iArr;
    }

    public IntLargeArray clone() {
        if (this.isConstant) {
            return new IntLargeArray(this.length, getInt(0));
        }
        IntLargeArray intLargeArray = new IntLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, intLargeArray, 0, this.length);
        return intLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((IntLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        int[] iArr = this.data;
        return hashCode + (iArr != null ? iArr.hashCode() : 0);
    }

    public final Integer get(long j) {
        return Integer.valueOf(getInt(j));
    }

    public final Integer getFromNative(long j) {
        return Integer.valueOf(LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j)));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j)) != 0) {
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
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (byte) i;
    }

    public final short getUnsignedByte(long j) {
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (short) (i & 255);
    }

    public final short getShort(long j) {
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (short) i;
    }

    public final int getInt(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final long getLong(long j) {
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (long) i;
    }

    public final float getFloat(long j) {
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (float) i;
    }

    public final double getDouble(long j) {
        int i;
        if (this.ptr != 0) {
            i = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = this.data[0];
        } else {
            i = this.data[(int) j];
        }
        return (double) i;
    }

    public final int[] getData() {
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
                zArr[i] = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j)) != 0;
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
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = (long) FastMath.ceil(((double) (j2 - j)) / ((double) j3));
            if (ceil > 1073741824) {
                return null;
            }
            if (zArr == null || ((long) zArr.length) < ceil) {
                zArr = new boolean[((int) ceil)];
            }
            if (this.ptr != 0) {
                int i = 0;
                while (j < j2) {
                    int i2 = i + 1;
                    zArr[i] = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j)) != 0;
                    j += j3;
                    i = i2;
                }
            } else if (this.isConstant) {
                int i3 = 0;
                while (j < j2) {
                    int i4 = i3 + 1;
                    zArr[i3] = this.data[0] != 0;
                    j += j3;
                    i3 = i4;
                }
            } else {
                int i5 = 0;
                while (j < j2) {
                    int i6 = i5 + 1;
                    zArr[i5] = this.data[(int) j] != 0;
                    j += j3;
                    i5 = i6;
                }
            }
            return zArr;
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
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            int i2 = this.data[0];
            while (((long) i) < this.length) {
                bArr[i] = (byte) i2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                bArr[i] = (byte) this.data[i];
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
                    bArr[i] = (byte) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    bArr[i2] = (byte) this.data[0];
                    j += j3;
                    i2++;
                }
            } else if (this.isConstant) {
                int i3 = 0;
                while (j < j2) {
                    bArr[i3] = (byte) this.data[0];
                    j += j3;
                    i3++;
                }
            } else {
                while (j < j2) {
                    bArr[i] = (byte) this.data[(int) j];
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
                sArr[i] = (short) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            int i2 = this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = (short) i2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                sArr[i] = (short) this.data[i];
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
                    sArr[i] = (short) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    sArr[i2] = (short) this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    sArr[i] = (short) this.data[(int) j];
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
                iArr[i] = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            int i2 = this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = i2;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, iArr, 0, (int) this.length);
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
                    iArr[i] = LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    iArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    iArr[i] = this.data[(int) j];
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            int i2 = this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = (long) i2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                jArr[i] = (long) this.data[i];
                i++;
            }
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
                    jArr[i] = (long) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    jArr[i2] = (long) this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    jArr[i] = (long) this.data[(int) j];
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
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
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
                    fArr[i] = (float) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
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
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
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
                    dArr[i] = (double) LargeArrayUtils.UNSAFE.getInt(this.ptr + (this.sizeof * j));
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
        LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), ((Integer) obj).intValue());
    }

    public final void setBoolean(long j, boolean z) {
        int i = 0;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j2 = this.ptr + (this.sizeof * j);
            if (z) {
                i = 1;
            }
            unsafe.putInt(j2, i);
        } else if (!this.isConstant) {
            int[] iArr = this.data;
            int i2 = (int) j;
            if (z) {
                i = 1;
            }
            iArr[i2] = i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), s);
        } else if (!this.isConstant) {
            this.data[(int) j] = s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), i);
        } else if (!this.isConstant) {
            this.data[(int) j] = i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), (int) j2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (int) j2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), (int) f);
        } else if (!this.isConstant) {
            this.data[(int) j] = (int) f;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putInt(this.ptr + (this.sizeof * j), (int) d);
        } else if (!this.isConstant) {
            this.data[(int) j] = (int) d;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
