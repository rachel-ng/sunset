package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class DoubleLargeArray extends LargeArray {
    private static final long serialVersionUID = 7436383149749497101L;
    private double[] data;

    public DoubleLargeArray(long j) {
        this(j, true);
    }

    public DoubleLargeArray(long j, boolean z) {
        this.type = LargeArrayType.DOUBLE;
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
            this.data = new double[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public DoubleLargeArray(long j, double d) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new double[]{d};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public DoubleLargeArray(double[] dArr) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8;
        this.length = (long) dArr.length;
        this.data = dArr;
    }

    public DoubleLargeArray clone() {
        if (this.isConstant) {
            return new DoubleLargeArray(this.length, getDouble(0));
        }
        DoubleLargeArray doubleLargeArray = new DoubleLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, doubleLargeArray, 0, this.length);
        return doubleLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((DoubleLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        double[] dArr = this.data;
        return hashCode + (dArr != null ? dArr.hashCode() : 0);
    }

    public final Double get(long j) {
        return Double.valueOf(getDouble(j));
    }

    public final Double getFromNative(long j) {
        return Double.valueOf(LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)) != 0.0d) {
                return true;
            }
            return false;
        } else if (this.isConstant) {
            if (this.data[0] != 0.0d) {
                return true;
            }
            return false;
        } else if (this.data[(int) j] != 0.0d) {
            return true;
        } else {
            return false;
        }
    }

    public final byte getByte(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = (int) this.data[0];
            return (byte) i;
        } else {
            d = this.data[(int) j];
        }
        i = (int) d;
        return (byte) i;
    }

    public final short getUnsignedByte(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = (int) this.data[0];
            return (short) (i & 255);
        } else {
            d = this.data[(int) j];
        }
        i = (int) d;
        return (short) (i & 255);
    }

    public final short getShort(long j) {
        double d;
        int i;
        if (this.ptr != 0) {
            d = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            i = (int) this.data[0];
            return (short) i;
        } else {
            d = this.data[(int) j];
        }
        i = (int) d;
        return (short) i;
    }

    public final int getInt(long j) {
        if (this.ptr != 0) {
            return (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (int) this.data[0];
        }
        return (int) this.data[(int) j];
    }

    public final long getLong(long j) {
        if (this.ptr != 0) {
            return (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (long) this.data[0];
        }
        return (long) this.data[(int) j];
    }

    public final float getFloat(long j) {
        if (this.ptr != 0) {
            return (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return (float) this.data[0];
        }
        return (float) this.data[(int) j];
    }

    public final double getDouble(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final double[] getData() {
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
                zArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)) != 0.0d;
                i++;
            }
        } else if (this.isConstant) {
            if (this.data[0] == 0.0d) {
                z = false;
            }
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; ((long) i3) < this.length; i3++) {
                zArr[i3] = this.data[i3] != 0.0d;
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
                    zArr2[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j5)) != 0.0d;
                    j5 += j4;
                    i = i2;
                }
            } else if (this.isConstant) {
                long j6 = j;
                int i3 = 0;
                while (j6 < j2) {
                    int i4 = i3 + 1;
                    zArr2[i3] = this.data[0] != 0.0d;
                    j6 += j4;
                    i3 = i4;
                }
            } else {
                long j7 = j;
                int i5 = 0;
                while (j7 < j2) {
                    int i6 = i5 + 1;
                    zArr2[i5] = this.data[(int) j7] != 0.0d;
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
                bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                bArr[i] = (byte) ((int) d);
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
                    bArr[i] = (byte) ((int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
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
                sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = (short) ((int) d);
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
                    sArr[i] = (short) ((int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j)));
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
                iArr[i] = (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = (int) d;
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
                    iArr[i] = (int) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = (long) d;
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
                    jArr[i] = (long) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
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
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                fArr[i] = (float) d;
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
                    fArr[i] = (float) LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
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
                dArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            double d = this.data[0];
            while (((long) i) < this.length) {
                dArr[i] = d;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, dArr, 0, (int) this.length);
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
                    dArr[i] = LargeArrayUtils.UNSAFE.getDouble(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    dArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    dArr[i] = this.data[(int) j];
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
        LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), ((Double) obj).doubleValue());
    }

    public final void setBoolean(long j, boolean z) {
        double d = 1.0d;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j2 = this.ptr + (this.sizeof * j);
            if (!z) {
                d = 0.0d;
            }
            unsafe.putDouble(j2, d);
        } else if (!this.isConstant) {
            double[] dArr = this.data;
            int i = (int) j;
            if (!z) {
                d = 0.0d;
            }
            dArr[i] = d;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), (double) b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (double) b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), (double) s);
        } else if (!this.isConstant) {
            this.data[(int) j] = (double) s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), (double) i);
        } else if (!this.isConstant) {
            this.data[(int) j] = (double) i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), (double) j2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (double) j2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), (double) f);
        } else if (!this.isConstant) {
            this.data[(int) j] = (double) f;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putDouble(this.ptr + (this.sizeof * j), d);
        } else if (!this.isConstant) {
            this.data[(int) j] = d;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
