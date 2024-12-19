package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class FloatLargeArray extends LargeArray {
    private static final long serialVersionUID = -8342458159338079576L;
    private float[] data;

    public FloatLargeArray(long j) {
        this(j, true);
    }

    public FloatLargeArray(long j, boolean z) {
        this.type = LargeArrayType.FLOAT;
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
            this.data = new float[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public FloatLargeArray(long j, float f) {
        this.type = LargeArrayType.FLOAT;
        this.sizeof = 4;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new float[]{f};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public FloatLargeArray(float[] fArr) {
        this.type = LargeArrayType.FLOAT;
        this.sizeof = 4;
        this.length = (long) fArr.length;
        this.data = fArr;
    }

    public FloatLargeArray clone() {
        if (this.isConstant) {
            return new FloatLargeArray(this.length, getFloat(0));
        }
        FloatLargeArray floatLargeArray = new FloatLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, floatLargeArray, 0, this.length);
        return floatLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((FloatLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        float[] fArr = this.data;
        return hashCode + (fArr != null ? fArr.hashCode() : 0);
    }

    public final Float get(long j) {
        return Float.valueOf(getFloat(j));
    }

    public final Float getFromNative(long j) {
        return Float.valueOf(LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)) != 0.0f) {
                return true;
            }
            return false;
        } else if (this.isConstant) {
            if (this.data[0] != 0.0f) {
                return true;
            }
            return false;
        } else if (this.data[(int) j] != 0.0f) {
            return true;
        } else {
            return false;
        }
    }

    public final byte getByte(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (byte) ((int) f);
    }

    public final short getUnsignedByte(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (short) (((int) f) & 255);
    }

    public final short getShort(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (short) ((int) f);
    }

    public final int getInt(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (int) f;
    }

    public final long getLong(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (long) f;
    }

    public final float getFloat(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final double getDouble(long j) {
        float f;
        if (this.ptr != 0) {
            f = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            f = this.data[0];
        } else {
            f = this.data[(int) j];
        }
        return (double) f;
    }

    public final float[] getData() {
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
                zArr[i] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)) != 0.0f;
                i++;
            }
        } else if (this.isConstant) {
            if (this.data[0] == 0.0f) {
                z = false;
            }
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; ((long) i3) < this.length; i3++) {
                zArr[i3] = this.data[i3] != 0.0f;
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
                    zArr2[i] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j5)) != 0.0f;
                    j5 += j4;
                    i = i2;
                }
            } else if (this.isConstant) {
                long j6 = j;
                int i3 = 0;
                while (j6 < j2) {
                    int i4 = i3 + 1;
                    zArr2[i3] = this.data[0] != 0.0f;
                    j6 += j4;
                    i3 = i4;
                }
            } else {
                long j7 = j;
                int i5 = 0;
                while (j7 < j2) {
                    int i6 = i5 + 1;
                    zArr2[i5] = this.data[(int) j7] != 0.0f;
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
                bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (((long) i) < this.length) {
                bArr[i] = (byte) ((int) f);
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
                    bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)));
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
                sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = (short) ((int) f);
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
                    sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j)));
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
                iArr[i] = (int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = (int) f;
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
                    iArr[i] = (int) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = (long) f;
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
                    jArr[i] = (long) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
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
                fArr[i] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            float f = this.data[0];
            while (((long) i) < this.length) {
                fArr[i] = f;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, fArr, 0, (int) this.length);
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
                    fArr[i] = LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    fArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    fArr[i] = this.data[(int) j];
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
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
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
                    dArr[i] = (double) LargeArrayUtils.UNSAFE.getFloat(this.ptr + (this.sizeof * j));
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
        LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), ((Float) obj).floatValue());
    }

    public final void setBoolean(long j, boolean z) {
        float f = 1.0f;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j2 = this.ptr + (this.sizeof * j);
            if (!z) {
                f = 0.0f;
            }
            unsafe.putFloat(j2, f);
        } else if (!this.isConstant) {
            float[] fArr = this.data;
            int i = (int) j;
            if (!z) {
                f = 0.0f;
            }
            fArr[i] = f;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), (float) b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (float) b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), (float) s);
        } else if (!this.isConstant) {
            this.data[(int) j] = (float) s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), (float) i);
        } else if (!this.isConstant) {
            this.data[(int) j] = (float) i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), (float) j2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (float) j2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), f);
        } else if (!this.isConstant) {
            this.data[(int) j] = f;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putFloat(this.ptr + (this.sizeof * j), (float) d);
        } else if (!this.isConstant) {
            this.data[(int) j] = (float) d;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
