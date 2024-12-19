package pl.edu.icm.jlargearrays;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.commons.math3.util.FastMath;

public abstract class LargeArray implements Serializable, Cloneable {
    public static final int LARGEST_SUBARRAY = 1073741824;
    private static int maxSizeOf32bitArray = 1073741824;
    private static final long serialVersionUID = 7921589398878016801L;
    protected boolean isConstant;
    protected long length;
    protected Object parent;
    protected long ptr;
    protected long sizeof;
    protected LargeArrayType type;

    public abstract Object get(long j);

    public abstract boolean getBoolean(long j);

    public abstract boolean[] getBooleanData();

    public abstract boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3);

    public abstract byte getByte(long j);

    public abstract byte[] getByteData();

    public abstract byte[] getByteData(byte[] bArr, long j, long j2, long j3);

    public abstract Object getData();

    public abstract double getDouble(long j);

    public abstract double[] getDoubleData();

    public abstract double[] getDoubleData(double[] dArr, long j, long j2, long j3);

    public abstract float getFloat(long j);

    public abstract float[] getFloatData();

    public abstract float[] getFloatData(float[] fArr, long j, long j2, long j3);

    public abstract Object getFromNative(long j);

    public abstract int getInt(long j);

    public abstract int[] getIntData();

    public abstract int[] getIntData(int[] iArr, long j, long j2, long j3);

    public abstract long getLong(long j);

    public abstract long[] getLongData();

    public abstract long[] getLongData(long[] jArr, long j, long j2, long j3);

    public abstract short getShort(long j);

    public abstract short[] getShortData();

    public abstract short[] getShortData(short[] sArr, long j, long j2, long j3);

    public abstract short getUnsignedByte(long j);

    public abstract void setBoolean(long j, boolean z);

    public abstract void setByte(long j, byte b2);

    public abstract void setDouble(long j, double d);

    public abstract void setFloat(long j, float f);

    public abstract void setInt(long j, int i);

    public abstract void setLong(long j, long j2);

    public abstract void setShort(long j, short s);

    public abstract void setToNative(long j, Object obj);

    public abstract void setUnsignedByte(long j, short s);

    protected LargeArray() {
        this.isConstant = false;
        this.parent = null;
        this.ptr = 0;
    }

    public LargeArray(Object obj, long j, LargeArrayType largeArrayType, long j2) {
        this.isConstant = false;
        this.parent = obj;
        this.ptr = j;
        this.type = largeArrayType;
        this.sizeof = largeArrayType.sizeOf();
        if (j2 > 0) {
            this.length = j2;
            return;
        }
        throw new IllegalArgumentException(j2 + " is not a positive long value");
    }

    public long nativePointer() {
        return this.ptr;
    }

    public long length() {
        return this.length;
    }

    public LargeArrayType getType() {
        return this.type;
    }

    public Object get_safe(long j) {
        if (j >= 0 && j < this.length) {
            return get(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public boolean getBoolean_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getBoolean(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public byte getByte_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getByte(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public short getUnsignedByte_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getUnsignedByte(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public short getShort_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getShort(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public int getInt_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getInt(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public long getLong_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getLong(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public float getFloat_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getFloat(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public double getDouble_safe(long j) {
        if (j >= 0 && j < this.length) {
            return getDouble(j);
        }
        throw new ArrayIndexOutOfBoundsException(Long.toString(j));
    }

    public void set(long j, Object obj) {
        if (obj instanceof Boolean) {
            setBoolean(j, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            setByte(j, ((Byte) obj).byteValue());
        } else if (obj instanceof Short) {
            setShort(j, ((Short) obj).shortValue());
        } else if (obj instanceof Integer) {
            setInt(j, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            setLong(j, ((Long) obj).longValue());
        } else if (obj instanceof Float) {
            setFloat(j, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            setDouble(j, ((Double) obj).doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported type.");
        }
    }

    public void set_safe(long j, Object obj) {
        if (obj instanceof Boolean) {
            setBoolean_safe(j, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Byte) {
            setByte_safe(j, ((Byte) obj).byteValue());
        } else if (obj instanceof Short) {
            setShort_safe(j, ((Short) obj).shortValue());
        } else if (obj instanceof Integer) {
            setInt_safe(j, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            setLong_safe(j, ((Long) obj).longValue());
        } else if (obj instanceof Float) {
            setFloat_safe(j, ((Float) obj).floatValue());
        } else if (obj instanceof Double) {
            setDouble_safe(j, ((Double) obj).doubleValue());
        } else {
            throw new IllegalArgumentException("Unsupported type.");
        }
    }

    public void setBoolean_safe(long j, boolean z) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setBoolean(j, z);
    }

    public void setByte_safe(long j, byte b2) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setByte(j, b2);
    }

    public void setUnsignedByte_safe(long j, byte b2) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setUnsignedByte(j, (short) b2);
    }

    public void setShort_safe(long j, short s) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setShort(j, s);
    }

    public void setInt_safe(long j, int i) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setInt(j, i);
    }

    public void setLong_safe(long j, long j2) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setLong(j, j2);
    }

    public void setFloat_safe(long j, float f) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setFloat(j, f);
    }

    public void setDouble_safe(long j, double d) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        setDouble(j, d);
    }

    public boolean isLarge() {
        return this.ptr != 0;
    }

    public boolean isNumeric() {
        return this.type.isNumericType();
    }

    public boolean isConstant() {
        return this.isConstant;
    }

    public static void setMaxSizeOf32bitArray(int i) {
        if (i >= 0) {
            maxSizeOf32bitArray = i;
            return;
        }
        throw new IllegalArgumentException("index cannot be negative");
    }

    public static int getMaxSizeOf32bitArray() {
        return maxSizeOf32bitArray;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    public boolean equals(Object obj) {
        Object obj2;
        if (obj == null || !(obj instanceof LargeArray)) {
            return false;
        }
        LargeArray largeArray = (LargeArray) obj;
        boolean z = this.type == largeArray.type && this.length == largeArray.length && this.sizeof == largeArray.sizeof && this.isConstant == largeArray.isConstant && this.ptr == largeArray.ptr;
        Object obj3 = this.parent;
        if (obj3 == null || (obj2 = largeArray.parent) == null) {
            if (obj3 == null && largeArray.parent == null) {
                return z;
            }
            return false;
        } else if (!z || !obj3.equals(obj2)) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        LargeArrayType largeArrayType = this.type;
        int i = 0;
        int hashCode = largeArrayType != null ? largeArrayType.hashCode() : 0;
        long j = this.length;
        long j2 = this.sizeof;
        int i2 = (((((((203 + hashCode) * 29) + ((int) (j ^ (j >>> 32)))) * 29) + ((int) (j2 ^ (j2 >>> 32)))) * 29) + (this.isConstant ? 1 : 0)) * 29;
        Object obj = this.parent;
        if (obj != null) {
            i = obj.hashCode();
        }
        long j3 = this.ptr;
        return ((i2 + i) * 29) + ((int) ((j3 >>> 32) ^ j3));
    }

    protected static class Deallocator implements Runnable {
        private final long length;
        private long ptr;
        private final long sizeof;

        public Deallocator(long j, long j2, long j3) {
            this.ptr = j;
            this.length = j2;
            this.sizeof = j3;
        }

        public void run() {
            if (this.ptr != 0) {
                LargeArrayUtils.UNSAFE.freeMemory(this.ptr);
                this.ptr = 0;
                MemoryCounter.decreaseCounter(this.length * this.sizeof);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void zeroNativeMemory(long j) {
        long j2 = j;
        if (this.ptr != 0) {
            int min = (int) FastMath.min(j2, (long) ConcurrencyUtils.getNumberOfThreads());
            if (min <= 2 || j2 < ConcurrencyUtils.getConcurrentThreshold()) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, j2 * this.sizeof, (byte) 0);
                return;
            }
            long j3 = j2 / ((long) min);
            Future[] futureArr = new Future[min];
            final long j4 = this.ptr;
            int i = 0;
            while (i < min) {
                final long j5 = ((long) i) * j3;
                final long j6 = i == min + -1 ? j2 : j5 + j3;
                long j7 = j4;
                futureArr[i] = ConcurrencyUtils.submit((Runnable) new Runnable() {
                    public void run() {
                        switch (AnonymousClass2.$SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType[LargeArray.this.type.ordinal()]) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                                for (long j = j5; j < j6; j++) {
                                    LargeArrayUtils.UNSAFE.putByte(j4 + (LargeArray.this.sizeof * j), (byte) 0);
                                }
                                return;
                            case 6:
                                for (long j2 = j5; j2 < j6; j2++) {
                                    LargeArrayUtils.UNSAFE.putShort(j4 + (LargeArray.this.sizeof * j2), 0);
                                }
                                return;
                            case 7:
                                for (long j3 = j5; j3 < j6; j3++) {
                                    LargeArrayUtils.UNSAFE.putInt(j4 + (LargeArray.this.sizeof * j3), 0);
                                }
                                return;
                            case 8:
                                for (long j4 = j5; j4 < j6; j4++) {
                                    LargeArrayUtils.UNSAFE.putLong(j4 + (LargeArray.this.sizeof * j4), 0);
                                }
                                return;
                            case 9:
                                for (long j5 = j5; j5 < j6; j5++) {
                                    LargeArrayUtils.UNSAFE.putFloat(j4 + (LargeArray.this.sizeof * j5), 0.0f);
                                }
                                return;
                            case 10:
                                for (long j6 = j5; j6 < j6; j6++) {
                                    LargeArrayUtils.UNSAFE.putDouble(j4 + (LargeArray.this.sizeof * j6), 0.0d);
                                }
                                return;
                            default:
                                throw new IllegalArgumentException("Invalid array type.");
                        }
                    }
                });
                i++;
            }
            try {
                ConcurrencyUtils.waitForCompletion(futureArr);
            } catch (InterruptedException unused) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, j2 * this.sizeof, (byte) 0);
            } catch (ExecutionException unused2) {
                LargeArrayUtils.UNSAFE.setMemory(this.ptr, this.sizeof * j2, (byte) 0);
            }
        }
    }

    /* renamed from: pl.edu.icm.jlargearrays.LargeArray$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                pl.edu.icm.jlargearrays.LargeArrayType[] r0 = pl.edu.icm.jlargearrays.LargeArrayType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType = r0
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.LOGIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x001d }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.BYTE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0028 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.UNSIGNED_BYTE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0033 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.STRING     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x003e }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.OBJECT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0049 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.SHORT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0054 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.INT     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0060 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.LONG     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x006c }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.FLOAT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$pl$edu$icm$jlargearrays$LargeArrayType     // Catch:{ NoSuchFieldError -> 0x0078 }
                pl.edu.icm.jlargearrays.LargeArrayType r1 = pl.edu.icm.jlargearrays.LargeArrayType.DOUBLE     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: pl.edu.icm.jlargearrays.LargeArray.AnonymousClass2.<clinit>():void");
        }
    }
}
