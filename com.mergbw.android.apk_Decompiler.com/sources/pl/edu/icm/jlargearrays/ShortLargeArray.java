package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;
import sun.misc.Unsafe;

public class ShortLargeArray extends LargeArray {
    private static final long serialVersionUID = 8813991144303908703L;
    private short[] data;

    public ShortLargeArray(long j) {
        this(j, true);
    }

    public ShortLargeArray(long j, boolean z) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2;
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
            this.data = new short[((int) j)];
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public ShortLargeArray(long j, short s) {
        this.type = LargeArrayType.DOUBLE;
        this.sizeof = 8;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new short[]{s};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public ShortLargeArray(short[] sArr) {
        this.type = LargeArrayType.SHORT;
        this.sizeof = 2;
        this.length = (long) sArr.length;
        this.data = sArr;
    }

    public ShortLargeArray clone() {
        if (this.isConstant) {
            return new ShortLargeArray(this.length, getShort(0));
        }
        ShortLargeArray shortLargeArray = new ShortLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, shortLargeArray, 0, this.length);
        return shortLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj) || this.data != ((ShortLargeArray) obj).data) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        short[] sArr = this.data;
        return hashCode + (sArr != null ? sArr.hashCode() : 0);
    }

    public final Short get(long j) {
        return Short.valueOf(getShort(j));
    }

    public final Short getFromNative(long j) {
        return Short.valueOf(LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)));
    }

    public final boolean getBoolean(long j) {
        if (this.ptr != 0) {
            if (LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0) {
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
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (byte) s;
    }

    public final short getUnsignedByte(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (short) (s & 255);
    }

    public final short getShort(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final int getInt(long j) {
        if (this.ptr != 0) {
            return LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        }
        if (this.isConstant) {
            return this.data[0];
        }
        return this.data[(int) j];
    }

    public final long getLong(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (long) s;
    }

    public final float getFloat(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (float) s;
    }

    public final double getDouble(long j) {
        short s;
        if (this.ptr != 0) {
            s = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
        } else if (this.isConstant) {
            s = this.data[0];
        } else {
            s = this.data[(int) j];
        }
        return (double) s;
    }

    public final short[] getData() {
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
                zArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0;
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
                    zArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j)) != 0;
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
                bArr[i] = (byte) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            byte b2 = (byte) this.data[0];
            while (((long) i) < this.length) {
                bArr[i] = b2;
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
                    bArr[i] = (byte) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                sArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            short s = this.data[0];
            while (((long) i) < this.length) {
                sArr[i] = s;
                i++;
            }
        } else {
            System.arraycopy(this.data, 0, sArr, 0, (int) this.length);
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
                    sArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    sArr[i2] = this.data[0];
                    j += j3;
                    i2++;
                }
            } else {
                while (j < j2) {
                    sArr[i] = this.data[(int) j];
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
                iArr[i] = LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            short s = this.data[0];
            while (((long) i) < this.length) {
                iArr[i] = s;
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

    /* JADX WARNING: type inference failed for: r2v15, types: [short[]] */
    /* JADX WARNING: type inference failed for: r2v16, types: [short] */
    /* JADX WARNING: type inference failed for: r3v1, types: [short[]] */
    /* JADX WARNING: type inference failed for: r3v2, types: [short] */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        if (r4 >= 0) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int[] getIntData(int[] r8, long r9, long r11, long r13) {
        /*
            r7 = this;
            r0 = 0
            int r2 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x0092
            long r2 = r7.length
            int r2 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r2 >= 0) goto L_0x0092
            int r2 = (r11 > r0 ? 1 : (r11 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x008a
            long r2 = r7.length
            int r2 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r2 > 0) goto L_0x008a
            int r2 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r2 < 0) goto L_0x008a
            r2 = 1
            int r2 = (r13 > r2 ? 1 : (r13 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0082
            long r2 = r11 - r9
            double r2 = (double) r2
            double r4 = (double) r13
            double r2 = r2 / r4
            double r2 = org.apache.commons.math3.util.FastMath.ceil(r2)
            long r2 = (long) r2
            r4 = 1073741824(0x40000000, double:5.304989477E-315)
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r4 <= 0) goto L_0x0033
            r8 = 0
            return r8
        L_0x0033:
            if (r8 == 0) goto L_0x003c
            int r4 = r8.length
            long r4 = (long) r4
            int r4 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x003c
            goto L_0x003f
        L_0x003c:
            int r8 = (int) r2
            int[] r8 = new int[r8]
        L_0x003f:
            long r2 = r7.ptr
            int r0 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            r1 = 0
            if (r0 == 0) goto L_0x005d
        L_0x0046:
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x0081
            int r0 = r1 + 1
            sun.misc.Unsafe r2 = pl.edu.icm.jlargearrays.LargeArrayUtils.UNSAFE
            long r3 = r7.ptr
            long r5 = r7.sizeof
            long r5 = r5 * r9
            long r3 = r3 + r5
            short r2 = r2.getShort(r3)
            r8[r1] = r2
            long r9 = r9 + r13
            r1 = r0
            goto L_0x0046
        L_0x005d:
            boolean r0 = r7.isConstant
            if (r0 == 0) goto L_0x0071
            r0 = r1
        L_0x0062:
            int r2 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r2 >= 0) goto L_0x0081
            int r2 = r0 + 1
            short[] r3 = r7.data
            short r3 = r3[r1]
            r8[r0] = r3
            long r9 = r9 + r13
            r0 = r2
            goto L_0x0062
        L_0x0071:
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 >= 0) goto L_0x0081
            int r0 = r1 + 1
            short[] r2 = r7.data
            int r3 = (int) r9
            short r2 = r2[r3]
            r8[r1] = r2
            long r9 = r9 + r13
            r1 = r0
            goto L_0x0071
        L_0x0081:
            return r8
        L_0x0082:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "step < 1"
            r8.<init>(r9)
            throw r8
        L_0x008a:
            java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r9 = "endPos < 0 || endPos > length || endPos < startPos"
            r8.<init>(r9)
            throw r8
        L_0x0092:
            java.lang.ArrayIndexOutOfBoundsException r8 = new java.lang.ArrayIndexOutOfBoundsException
            java.lang.String r9 = "startPos < 0 || startPos >= length"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.ShortLargeArray.getIntData(int[], long, long, long):int[]");
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
                jArr[i] = (long) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
                i++;
            }
        } else if (this.isConstant) {
            long j2 = (long) this.data[0];
            while (((long) i) < this.length) {
                jArr[i] = j2;
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
                    jArr[i] = (long) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                fArr[i] = (float) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                    fArr[i] = (float) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                dArr[i] = (double) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
                    dArr[i] = (double) LargeArrayUtils.UNSAFE.getShort(this.ptr + (this.sizeof * j));
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
        LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), ((Short) obj).shortValue());
    }

    public final void setBoolean(long j, boolean z) {
        short s = 0;
        if (this.ptr != 0) {
            Unsafe unsafe = LargeArrayUtils.UNSAFE;
            long j2 = this.ptr + (this.sizeof * j);
            if (z) {
                s = 1;
            }
            unsafe.putShort(j2, s);
        } else if (!this.isConstant) {
            short[] sArr = this.data;
            int i = (int) j;
            if (z) {
                s = 1;
            }
            sArr[i] = s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setByte(long j, byte b2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) b2);
        } else if (!this.isConstant) {
            this.data[(int) j] = (short) b2;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public final void setShort(long j, short s) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), s);
        } else if (!this.isConstant) {
            this.data[(int) j] = s;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setInt(long j, int i) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) i);
        } else if (!this.isConstant) {
            this.data[(int) j] = (short) i;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setLong(long j, long j2) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) ((int) j2));
        } else if (!this.isConstant) {
            this.data[(int) j] = (short) ((int) j2);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setFloat(long j, float f) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) ((int) f));
        } else if (!this.isConstant) {
            this.data[(int) j] = (short) ((int) f);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void setDouble(long j, double d) {
        if (this.ptr != 0) {
            LargeArrayUtils.UNSAFE.putShort(this.ptr + (this.sizeof * j), (short) ((int) d));
        } else if (!this.isConstant) {
            this.data[(int) j] = (short) ((int) d);
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }
}
