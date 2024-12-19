package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class UnsignedByteLargeArray extends LargeArray {
    private static final long serialVersionUID = 3135411647495793832L;
    private byte[] data;

    public UnsignedByteLargeArray(long j) {
        this(j, true);
    }

    public UnsignedByteLargeArray(long j, boolean z) {
        this.type = LargeArrayType.UNSIGNED_BYTE;
        this.sizeof = 1;
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
            this.data = new byte[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public UnsignedByteLargeArray(long j, short s) {
        this.type = LargeArrayType.UNSIGNED_BYTE;
        this.sizeof = 1;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            if (s < 0 || s > 255) {
                throw new IllegalArgumentException("The value cannot be smaller than 0 or greater than 255");
            }
            this.data = new byte[]{(byte) s};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public UnsignedByteLargeArray(byte[] bArr) {
        this.type = LargeArrayType.UNSIGNED_BYTE;
        this.sizeof = 1;
        this.length = (long) bArr.length;
        this.data = bArr;
    }

    public UnsignedByteLargeArray(short[] sArr) {
        this.type = LargeArrayType.UNSIGNED_BYTE;
        this.sizeof = 1;
        this.length = (long) sArr.length;
        this.data = new byte[sArr.length];
        for (int i = 0; ((long) i) < this.length; i++) {
            short s = sArr[i];
            if (s < 0 || s > 255) {
                throw new IllegalArgumentException("The value cannot be smaller than 0 or greater than 255");
            }
            this.data[i] = (byte) s;
        }
    }

    public UnsignedByteLargeArray clone() {
        if (this.isConstant) {
            return new UnsignedByteLargeArray(this.length, (short) getByte(0));
        }
        UnsignedByteLargeArray unsignedByteLargeArray = new UnsignedByteLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, unsignedByteLargeArray, 0, this.length);
        return unsignedByteLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((UnsignedByteLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        byte[] bArr = this.data;
        return hashCode + (bArr != null ? bArr.hashCode() : 0);
    }

    public final Short get(long j) {
        return Short.valueOf(getUnsignedByte(j));
    }

    public final Byte getFromNative(long j) {
        return Byte.valueOf(LargeArrayUtils.UNSAFE.getByte(this.ptr + j));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getByte(this.ptr + j) != 0) {
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
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final short getUnsignedByte(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return (short) (b2 & 255);
    }

    public final short getShort(long j) {
        return getUnsignedByte(j);
    }

    public final int getInt(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return b2 & 255;
    }

    public final long getLong(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return (long) (b2 & 255);
    }

    public final float getFloat(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return (float) (b2 & 255);
    }

    public final double getDouble(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return (double) (b2 & 255);
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
            int i = 0;
            boolean z = true;
            if (this.ptr != 0) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    zArr[i2] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j) != 0;
                    j += j3;
                    i2 = i3;
                }
            } else if (this.isConstant) {
                if (this.data[0] == 0) {
                    z = false;
                }
                while (j < j2) {
                    zArr[i] = z;
                    j += j3;
                    i++;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    zArr[i4] = this.data[(int) j] != 0;
                    j += j3;
                    i4 = i5;
                }
            }
            return zArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
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
                zArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j) != 0;
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

    public final byte[] getData() {
        return this.data;
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
                bArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                bArr[i] = b2;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, bArr, 0, (int) this.length);
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
                    bArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    bArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    bArr[i] = this.data[(int) j];
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
                sArr[i] = (short) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            short s = (short) this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = s;
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
                    sArr[i] = (short) (LargeArrayUtils.UNSAFE.getByte(this.ptr + j) & 255);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    sArr[i2] = (short) (this.data[0] & 255);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    sArr[i] = (short) (this.data[(int) j] & 255);
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
                iArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = b2;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                iArr[i] = this.data[i];
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
                    iArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j) & 255;
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    iArr[i2] = this.data[0] & 255;
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    iArr[i] = this.data[(int) j] & 255;
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = (long) b2;
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
                    jArr[i] = (long) (LargeArrayUtils.UNSAFE.getByte(this.ptr + j) & 255);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    jArr[i2] = (long) (this.data[0] & 255);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    jArr[i] = (long) (this.data[(int) j] & 255);
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
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                fArr[i] = (float) b2;
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
                    fArr[i] = (float) (LargeArrayUtils.UNSAFE.getByte(this.ptr + j) & 255);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    fArr[i2] = (float) (this.data[0] & 255);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    fArr[i] = (float) (this.data[(int) j] & 255);
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
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                dArr[i] = (double) b2;
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
                    dArr[i] = (double) (LargeArrayUtils.UNSAFE.getByte(this.ptr + j) & 255);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    dArr[i2] = (double) (this.data[0] & 255);
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    dArr[i] = (double) (this.data[(int) j] & 255);
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
        LargeArrayUtils.UNSAFE.putByte(this.ptr + j, ((Byte) obj).byteValue());
    }

    public final void setBoolean(long j, boolean z) {
        byte b2 = 0;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j2 = this.ptr + j;
            if (z) {
                b2 = 1;
            }
            unsafe.putByte(j2, b2);
        } else if (!this.isConstant) {
            byte[] bArr = this.data;
            int i = (int) j;
            if (z) {
                b2 = 1;
            }
            bArr[i] = b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        if (s < 0 || s > 255) {
            throw new IllegalArgumentException("The value cannot be smaller than 0 or greater than 255");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) s);
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) (s & 255));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) (s & 255);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) (i & 255));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) (i & 255);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) ((int) (j2 & 255)));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) ((int) (j2 & 255));
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) (((int) f) & 255));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) (((int) f) & 255);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) ((int) (((long) d) & 255)));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) ((int) (((long) d) & 255));
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
