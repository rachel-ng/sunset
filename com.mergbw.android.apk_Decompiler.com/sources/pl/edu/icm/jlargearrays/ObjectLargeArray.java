package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

public class ObjectLargeArray extends LargeArray {
    private static final long serialVersionUID = -4096759496772248522L;
    private byte[] byteArray;
    private Object[] data;
    private int maxObjectLength;
    private ShortLargeArray objectLengths;
    private long size;

    public ObjectLargeArray(long j) {
        this(j, 1024);
    }

    public ObjectLargeArray(long j, int i) {
        this(j, i, true);
    }

    public ObjectLargeArray(long j, int i, boolean z) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value.");
        } else if (i > 0) {
            this.length = j;
            this.size = ((long) i) * j;
            this.maxObjectLength = i;
            if (j > ((long) getMaxSizeOf32bitArray())) {
                this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
                if (z) {
                    zeroNativeMemory(this.size);
                }
                Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
                MemoryCounter.increaseCounter(this.size * this.sizeof);
                this.objectLengths = new ShortLargeArray(j);
                this.byteArray = new byte[i];
                return;
            }
            this.data = new Object[((int) j)];
        } else {
            throw new IllegalArgumentException(i + " is not a positive int value.");
        }
    }

    public ObjectLargeArray(long j, Object obj) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new Object[]{obj};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public ObjectLargeArray(Object[] objArr) {
        this.type = LargeArrayType.OBJECT;
        this.sizeof = 1;
        this.length = (long) objArr.length;
        this.data = objArr;
    }

    public ObjectLargeArray clone() {
        if (this.isConstant) {
            return new ObjectLargeArray(this.length, get(0));
        }
        ObjectLargeArray objectLargeArray = new ObjectLargeArray(this.length, FastMath.max(1, this.maxObjectLength), false);
        LargeArrayUtils.arraycopy(this, 0, objectLargeArray, 0, this.length);
        return objectLargeArray;
    }

    public boolean equals(Object obj) {
        ShortLargeArray shortLargeArray;
        if (super.equals(obj)) {
            ObjectLargeArray objectLargeArray = (ObjectLargeArray) obj;
            boolean z = this.maxObjectLength == objectLargeArray.maxObjectLength && this.data == objectLargeArray.data;
            ShortLargeArray shortLargeArray2 = this.objectLengths;
            if (shortLargeArray2 == null || (shortLargeArray = objectLargeArray.objectLengths) == null) {
                if (shortLargeArray2 == objectLargeArray.objectLengths) {
                    return z;
                }
            } else if (!z || !shortLargeArray2.equals(shortLargeArray)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        Object[] objArr = this.data;
        int i = 0;
        int hashCode2 = objArr != null ? objArr.hashCode() : 0;
        int i2 = this.maxObjectLength;
        int i3 = (((hashCode + hashCode2) * 29) + (i2 ^ (i2 >>> 16))) * 29;
        ShortLargeArray shortLargeArray = this.objectLengths;
        if (shortLargeArray != null) {
            i = shortLargeArray.hashCode();
        }
        return i3 + i;
    }

    public final Object get(long j) {
        if (this.ptr != 0) {
            short s = this.objectLengths.getShort(j);
            if (s < 0) {
                return null;
            }
            long j2 = this.sizeof * j * ((long) this.maxObjectLength);
            for (int i = 0; i < s; i++) {
                this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * ((long) i)));
            }
            return fromByteArray(this.byteArray);
        } else if (this.isConstant) {
            return this.data[0];
        } else {
            return this.data[(int) j];
        }
    }

    public final Object getFromNative(long j) {
        short s = this.objectLengths.getShort(j);
        if (s < 0) {
            return null;
        }
        long j2 = this.sizeof * j * ((long) this.maxObjectLength);
        for (int i = 0; i < s; i++) {
            this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * ((long) i)));
        }
        return fromByteArray(this.byteArray);
    }

    public final boolean getBoolean(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final byte getByte(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final short getUnsignedByte(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final short getShort(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final int getInt(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final long getLong(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final float getFloat(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final double getDouble(long j) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final Object[] getData() {
        return this.data;
    }

    public final boolean[] getBooleanData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final byte[] getByteData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final short[] getShortData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final int[] getIntData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final long[] getLongData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final float[] getFloatData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final double[] getDoubleData() {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setToNative(long j, Object obj) {
        if (obj == null) {
            this.objectLengths.setShort(j, -1);
            return;
        }
        byte[] byteArray2 = toByteArray(obj);
        if (byteArray2.length <= this.maxObjectLength) {
            int length = byteArray2.length;
            if (length <= 32767) {
                this.objectLengths.setShort(j, (short) length);
                long j2 = this.sizeof * j * ((long) this.maxObjectLength);
                for (int i = 0; i < length; i++) {
                    LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * ((long) i)), byteArray2[i]);
                }
                return;
            }
            throw new IllegalArgumentException("Object  " + obj + " is too long.");
        }
        throw new IllegalArgumentException("Object  " + obj + " is too long.");
    }

    public final void set(long j, Object obj) {
        if (obj == null) {
            if (this.ptr != 0) {
                this.objectLengths.setShort(j, -1);
            } else if (!this.isConstant) {
                this.data[(int) j] = null;
            } else {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
        } else if (this.ptr != 0) {
            byte[] byteArray2 = toByteArray(obj);
            if (byteArray2.length <= this.maxObjectLength) {
                int length = byteArray2.length;
                if (length <= 32767) {
                    this.objectLengths.setShort(j, (short) length);
                    long j2 = this.sizeof * j * ((long) this.maxObjectLength);
                    for (int i = 0; i < length; i++) {
                        LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * ((long) i)), byteArray2[i]);
                    }
                    return;
                }
                throw new IllegalArgumentException("Object  " + obj + " is too long.");
            }
            throw new IllegalArgumentException("Object  " + obj + " is too long.");
        } else if (!this.isConstant) {
            this.data[(int) j] = obj;
        } else {
            throw new IllegalAccessError("Constant arrays cannot be modified.");
        }
    }

    public final void set_safe(long j, Object obj) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        set(j, obj);
    }

    public final void setBoolean(long j, boolean z) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setByte(long j, byte b2) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setUnsignedByte(long j, short s) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setShort(long j, short s) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setInt(long j, int i) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setLong(long j, long j2) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setFloat(long j, float f) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    public final void setDouble(long j, double d) {
        throw new UnsupportedOperationException("Not supported yet");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0029 A[SYNTHETIC, Splitter:B:20:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] toByteArray(java.lang.Object r3) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r1 = 512(0x200, float:7.175E-43)
            r0.<init>(r1)
            r1 = 0
            java.io.ObjectOutputStream r2 = new java.io.ObjectOutputStream     // Catch:{ Exception -> 0x0020 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0020 }
            r2.writeObject(r3)     // Catch:{ Exception -> 0x001b, all -> 0x0018 }
            r2.close()     // Catch:{ IOException -> 0x0013 }
        L_0x0013:
            byte[] r3 = r0.toByteArray()
            return r3
        L_0x0018:
            r3 = move-exception
            r1 = r2
            goto L_0x0027
        L_0x001b:
            r3 = move-exception
            r1 = r2
            goto L_0x0021
        L_0x001e:
            r3 = move-exception
            goto L_0x0027
        L_0x0020:
            r3 = move-exception
        L_0x0021:
            com.sun.xml.internal.ws.encoding.soap.SerializationException r0 = new com.sun.xml.internal.ws.encoding.soap.SerializationException     // Catch:{ all -> 0x001e }
            r0.<init>(r3)     // Catch:{ all -> 0x001e }
            throw r0     // Catch:{ all -> 0x001e }
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.ObjectLargeArray.toByteArray(java.lang.Object):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0025 A[SYNTHETIC, Splitter:B:19:0x0025] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Object fromByteArray(byte[] r2) {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r2)
            r2 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ Exception -> 0x0019, all -> 0x0015 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0019, all -> 0x0015 }
            java.lang.Object r2 = r1.readObject()     // Catch:{ Exception -> 0x0013 }
            r1.close()     // Catch:{ IOException -> 0x0012 }
        L_0x0012:
            return r2
        L_0x0013:
            r2 = move-exception
            goto L_0x001c
        L_0x0015:
            r0 = move-exception
            r1 = r2
            r2 = r0
            goto L_0x0023
        L_0x0019:
            r0 = move-exception
            r1 = r2
            r2 = r0
        L_0x001c:
            com.sun.xml.internal.ws.encoding.soap.SerializationException r0 = new com.sun.xml.internal.ws.encoding.soap.SerializationException     // Catch:{ all -> 0x0022 }
            r0.<init>(r2)     // Catch:{ all -> 0x0022 }
            throw r0     // Catch:{ all -> 0x0022 }
        L_0x0022:
            r2 = move-exception
        L_0x0023:
            if (r1 == 0) goto L_0x0028
            r1.close()     // Catch:{ IOException -> 0x0028 }
        L_0x0028:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.ObjectLargeArray.fromByteArray(byte[]):java.lang.Object");
    }

    public int getMaxObjectLength() {
        return this.maxObjectLength;
    }
}
