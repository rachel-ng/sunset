package pl.edu.icm.jlargearrays;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class LogicLargeArray extends LargeArray {
    private static final long serialVersionUID = 3135411647668758832L;
    private byte[] data;

    public LogicLargeArray(long j) {
        this(j, true);
    }

    public LogicLargeArray(long j, boolean z) {
        this.type = LargeArrayType.LOGIC;
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

    public LogicLargeArray(long j, byte b2) {
        this.type = LargeArrayType.LOGIC;
        this.sizeof = 1;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new byte[]{b2 != 0 ? (byte) 1 : 0};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public LogicLargeArray(byte[] bArr) {
        this.type = LargeArrayType.LOGIC;
        this.sizeof = 1;
        this.length = (long) bArr.length;
        int i = 0;
        while (i < bArr.length) {
            byte b2 = bArr[i];
            if (b2 == 0 || b2 == 1) {
                i++;
            } else {
                throw new IllegalArgumentException("The array contans values different than 0 and 1.");
            }
        }
        this.data = bArr;
    }

    public LogicLargeArray(boolean[] zArr) {
        this.type = LargeArrayType.LOGIC;
        this.sizeof = 1;
        this.length = (long) zArr.length;
        this.data = new byte[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            byte[] bArr = this.data;
            byte b2 = 1;
            if (!zArr[i]) {
                b2 = 0;
            }
            bArr[i] = b2;
        }
    }

    public LogicLargeArray clone() {
        if (this.isConstant) {
            return new LogicLargeArray(this.length, getByte(0));
        }
        LogicLargeArray logicLargeArray = new LogicLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, logicLargeArray, 0, this.length);
        return logicLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((LogicLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        byte[] bArr = this.data;
        return hashCode + (bArr != null ? bArr.hashCode() : 0);
    }

    public final Byte get(long j) {
        return Byte.valueOf(getByte(j));
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
        return (short) getByte(j);
    }

    public final short getShort(long j) {
        byte b2;
        if (this.ptr != 0) {
            b2 = LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        } else if (this.isConstant) {
            b2 = this.data[0];
        } else {
            b2 = this.data[(int) j];
        }
        return (short) b2;
    }

    public final int getInt(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
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
        return (long) b2;
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
        return (float) b2;
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
        return (double) b2;
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
                    zArr[i2] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j) == 1;
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
                zArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j)) != 0;
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
                bArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
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
                sArr[i] = (short) LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = (short) b2;
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
                    sArr[i] = (short) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
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
                iArr[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
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

    /* JADX WARNING: type inference failed for: r2v15, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r2v16, types: [byte] */
    /* JADX WARNING: type inference failed for: r3v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v2, types: [byte] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        if (r4 >= 0) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] getIntData(int[] r7, long r8, long r10, long r12) {
        /*
            r6 = this;
            r0 = 0
            int r2 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x008f
            long r2 = r6.length
            int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x008f
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0087
            long r2 = r6.length
            int r2 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x0087
            int r2 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r2 < 0) goto L_0x0087
            r2 = 1
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x007f
            long r2 = r10 - r8
            double r2 = (double) r2
            double r4 = (double) r12
            double r2 = r2 / r4
            double r2 = org.apache.commons.math3.util.FastMath.ceil(r2)
            long r2 = (long) r2
            r4 = 1073741824(0x40000000, double:5.304989477E-315)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x0033
            r7 = 0
            return r7
        L_0x0033:
            if (r7 == 0) goto L_0x003c
            int r4 = r7.length
            long r4 = (long) r4
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x003c
            goto L_0x003f
        L_0x003c:
            int r7 = (int) r2
            int[] r7 = new int[r7]
        L_0x003f:
            long r2 = r6.ptr
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L_0x005a
        L_0x0046:
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 >= 0) goto L_0x007e
            int r0 = r1 + 1
            sun.misc.Unsafe r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.UNSAFE
            long r3 = r6.ptr
            long r3 = r3 + r8
            byte r2 = r2.getByte(r3)
            r7[r1] = r2
            long r8 = r8 + r12
            r1 = r0
            goto L_0x0046
        L_0x005a:
            boolean r0 = r6.isConstant
            if (r0 == 0) goto L_0x006e
            r0 = r1
        L_0x005f:
            int r2 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r2 >= 0) goto L_0x007e
            int r2 = r0 + 1
            byte[] r3 = r6.data
            byte r3 = r3[r1]
            r7[r0] = r3
            long r8 = r8 + r12
            r0 = r2
            goto L_0x005f
        L_0x006e:
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 >= 0) goto L_0x007e
            int r0 = r1 + 1
            byte[] r2 = r6.data
            int r3 = (int) r8
            byte r2 = r2[r3]
            r7[r1] = r2
            long r8 = r8 + r12
            r1 = r0
            goto L_0x006e
        L_0x007e:
            return r7
        L_0x007f:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "step < 1"
            r7.<init>(r8)
            throw r7
        L_0x0087:
            java.lang.ArrayIndexOutOfBoundsException r7 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r8 = "endPos < 0 || endPos > length || endPos < startPos"
            r7.<init>(r8)
            throw r7
        L_0x008f:
            java.lang.ArrayIndexOutOfBoundsException r7 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r8 = "startPos < 0 || startPos >= length"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LogicLargeArray.getIntData(int[], long, long, long):int[]");
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
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
                    jArr[i] = (long) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
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
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
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
                    fArr[i] = (float) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
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
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getByte(this.ptr + (this.sizeof * j));
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
                    dArr[i] = (double) LargeArrayUtils.UNSAFE.getByte(this.ptr + j);
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
        if (b2 < 0 || b2 > 1) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, b2);
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
        if (s < 0 || s > 1) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) s);
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (i < 0 || i > 1) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) i);
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (j2 < 0 || j2 > 1) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) ((int) j2));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) ((int) j2);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        double d = (double) f;
        if (d != 0.0d && d != 1.0d) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) ((int) f));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) ((int) f);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (d != 0.0d && d != 1.0d) {
            throw new IllegalArgumentException("The value has to be 0 or 1.");
        } else if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putByte(this.ptr + j, (byte) ((int) d));
        } else if (!this.isConstant) {
            this.data[(int) j] = (byte) ((int) d);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public LogicLargeArray and(LogicLargeArray logicLargeArray) {
        LogicLargeArray logicLargeArray2 = logicLargeArray;
        if (logicLargeArray2 == null || logicLargeArray.length() != this.length) {
            throw new IllegalArgumentException("array == null || array.length() != length");
        }
        LogicLargeArray logicLargeArray3 = new LogicLargeArray(logicLargeArray.length(), false);
        int min = (int) FastMath.min(this.length, (long) ConcurrencyUtils.getNumberOfThreads());
        long j = 0;
        if (min <= 2 || this.length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < this.length) {
                logicLargeArray3.setByte(j, (byte) (getByte(j) & logicLargeArray2.getByte(j)));
                j++;
            }
        } else {
            long j2 = this.length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                final long j4 = i == min + -1 ? this.length : j3 + j2;
                int i2 = i;
                final LogicLargeArray logicLargeArray4 = logicLargeArray3;
                Future[] futureArr2 = futureArr;
                final LogicLargeArray logicLargeArray5 = logicLargeArray;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j3; j < j4; j++) {
                            logicLargeArray4.setByte(j, (byte) (LogicLargeArray.this.getByte(j) & logicLargeArray5.getByte(j)));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) & logicLargeArray2.getByte(j)));
                    j++;
                }
            } catch (ExecutionException unused2) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) & logicLargeArray2.getByte(j)));
                    j++;
                }
            }
        }
        return logicLargeArray3;
    }

    public LogicLargeArray or(LogicLargeArray logicLargeArray) {
        LogicLargeArray logicLargeArray2 = logicLargeArray;
        if (logicLargeArray2 == null || logicLargeArray.length() != this.length) {
            throw new IllegalArgumentException("array == null || array.length() != length");
        }
        LogicLargeArray logicLargeArray3 = new LogicLargeArray(logicLargeArray.length(), false);
        int min = (int) FastMath.min(this.length, (long) ConcurrencyUtils.getNumberOfThreads());
        long j = 0;
        if (min <= 2 || this.length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < this.length) {
                logicLargeArray3.setByte(j, (byte) (getByte(j) | logicLargeArray2.getByte(j)));
                j++;
            }
        } else {
            long j2 = this.length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                final long j4 = i == min + -1 ? this.length : j3 + j2;
                int i2 = i;
                final LogicLargeArray logicLargeArray4 = logicLargeArray3;
                Future[] futureArr2 = futureArr;
                final LogicLargeArray logicLargeArray5 = logicLargeArray;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j3; j < j4; j++) {
                            logicLargeArray4.setByte(j, (byte) (LogicLargeArray.this.getByte(j) | logicLargeArray5.getByte(j)));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) | logicLargeArray2.getByte(j)));
                    j++;
                }
            } catch (ExecutionException unused2) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) | logicLargeArray2.getByte(j)));
                    j++;
                }
            }
        }
        return logicLargeArray3;
    }

    public LogicLargeArray xor(LogicLargeArray logicLargeArray) {
        LogicLargeArray logicLargeArray2 = logicLargeArray;
        if (logicLargeArray2 == null || logicLargeArray.length() != this.length) {
            throw new IllegalArgumentException("array == null || array.length() != length");
        }
        LogicLargeArray logicLargeArray3 = new LogicLargeArray(logicLargeArray.length(), false);
        int min = (int) FastMath.min(this.length, (long) ConcurrencyUtils.getNumberOfThreads());
        long j = 0;
        if (min <= 2 || this.length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < this.length) {
                logicLargeArray3.setByte(j, (byte) (getByte(j) ^ logicLargeArray2.getByte(j)));
                j++;
            }
        } else {
            long j2 = this.length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                final long j4 = i == min + -1 ? this.length : j3 + j2;
                int i2 = i;
                final LogicLargeArray logicLargeArray4 = logicLargeArray3;
                Future[] futureArr2 = futureArr;
                final LogicLargeArray logicLargeArray5 = logicLargeArray;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j3; j < j4; j++) {
                            logicLargeArray4.setByte(j, (byte) (LogicLargeArray.this.getByte(j) ^ logicLargeArray5.getByte(j)));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) ^ logicLargeArray2.getByte(j)));
                    j++;
                }
            } catch (ExecutionException unused2) {
                while (j < this.length) {
                    logicLargeArray3.setByte(j, (byte) (getByte(j) ^ logicLargeArray2.getByte(j)));
                    j++;
                }
            }
        }
        return logicLargeArray3;
    }

    public LogicLargeArray not() {
        LogicLargeArray logicLargeArray = new LogicLargeArray(this.length, false);
        int min = (int) FastMath.min(this.length, (long) ConcurrencyUtils.getNumberOfThreads());
        long j = 0;
        if (min <= 2 || this.length < ConcurrencyUtils.getConcurrentThreshold()) {
            while (j < this.length) {
                logicLargeArray.setByte(j, (byte) (1 - getByte(j)));
                j++;
            }
        } else {
            long j2 = this.length / ((long) min);
            Future[] futureArr = new Future[min];
            int i = 0;
            while (i < min) {
                final long j3 = ((long) i) * j2;
                int i2 = i;
                final long j4 = i == min + -1 ? this.length : j3 + j2;
                Future[] futureArr2 = futureArr;
                final LogicLargeArray logicLargeArray2 = logicLargeArray;
                futureArr2[i2] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        for (long j = j3; j < j4; j++) {
                            logicLargeArray2.setByte(j, (byte) (1 - LogicLargeArray.this.getByte(j)));
                        }
                    }
                });
                i = i2 + 1;
                futureArr = futureArr2;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                while (j < this.length) {
                    logicLargeArray.setByte(j, (byte) (1 - getByte(j)));
                    j++;
                }
            } catch (ExecutionException unused2) {
                while (j < this.length) {
                    logicLargeArray.setByte(j, (byte) (1 - getByte(j)));
                    j++;
                }
            }
        }
        return logicLargeArray;
    }
}
