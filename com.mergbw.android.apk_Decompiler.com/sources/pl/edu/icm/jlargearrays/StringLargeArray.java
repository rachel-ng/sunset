package pl.edu.icm.jlargearrays;

import java.io.UnsupportedEncodingException;
import org.apache.commons.math3.util.FastMath;
import pl.edu.icm.jlargearrays.LargeArray;
import sun.misc.Cleaner;

public class StringLargeArray extends LargeArray {
    private static final String CHARSET = "UTF-8";
    private static final int CHARSET_SIZE = 4;
    private static final long serialVersionUID = -4096759496772248522L;
    private byte[] byteArray;
    private String[] data;
    private int maxStringLength;
    private long size;
    private ShortLargeArray stringLengths;

    public StringLargeArray(long j) {
        this(j, 100);
    }

    public StringLargeArray(long j, int i) {
        this(j, i, true);
    }

    public StringLargeArray(long j, int i, boolean z) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value.");
        } else if (i > 0) {
            this.length = j;
            this.size = ((long) i) * j * 4;
            this.maxStringLength = i;
            if (j > ((long) getMaxSizeOf32bitArray())) {
                this.ptr = LargeArrayUtils.UNSAFE.allocateMemory(this.size * this.sizeof);
                if (z) {
                    zeroNativeMemory(this.size);
                }
                Cleaner.create(this, new LargeArray.Deallocator(this.ptr, this.size, this.sizeof));
                MemoryCounter.increaseCounter(this.size * this.sizeof);
                this.stringLengths = new ShortLargeArray(j);
                this.byteArray = new byte[(i * 4)];
                return;
            }
            this.data = new String[((int) j)];
        } else {
            throw new IllegalArgumentException(i + " is not a positive int value.");
        }
    }

    public StringLargeArray(long j, String str) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1;
        if (j > 0) {
            this.length = j;
            this.isConstant = true;
            this.data = new String[]{str};
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public StringLargeArray(String[] strArr) {
        this.type = LargeArrayType.STRING;
        this.sizeof = 1;
        this.length = (long) strArr.length;
        this.data = strArr;
    }

    public StringLargeArray clone() {
        if (this.isConstant) {
            return new StringLargeArray(this.length, get(0));
        }
        StringLargeArray stringLargeArray = new StringLargeArray(this.length, FastMath.max(1, this.maxStringLength), false);
        LargeArrayUtils.arraycopy(this, 0, stringLargeArray, 0, this.length);
        return stringLargeArray;
    }

    public boolean equals(Object obj) {
        ShortLargeArray shortLargeArray;
        if (super.equals(obj)) {
            StringLargeArray stringLargeArray = (StringLargeArray) obj;
            boolean z = this.maxStringLength == stringLargeArray.maxStringLength && this.data == stringLargeArray.data;
            ShortLargeArray shortLargeArray2 = this.stringLengths;
            if (shortLargeArray2 == null || (shortLargeArray = stringLargeArray.stringLengths) == null) {
                if (shortLargeArray2 == stringLargeArray.stringLengths) {
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
        String[] strArr = this.data;
        int i = 0;
        int hashCode2 = strArr != null ? strArr.hashCode() : 0;
        int i2 = this.maxStringLength;
        int i3 = (((hashCode + hashCode2) * 29) + (i2 ^ (i2 >>> 16))) * 29;
        ShortLargeArray shortLargeArray = this.stringLengths;
        if (shortLargeArray != null) {
            i = shortLargeArray.hashCode();
        }
        return i3 + i;
    }

    public final String get(long j) {
        if (this.ptr != 0) {
            short s = this.stringLengths.getShort(j);
            if (s < 0) {
                return null;
            }
            long j2 = this.sizeof * j * ((long) this.maxStringLength) * 4;
            for (int i = 0; i < s; i++) {
                this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * ((long) i)));
            }
            try {
                return new String(this.byteArray, 0, s, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        } else if (this.isConstant) {
            return this.data[0];
        } else {
            return this.data[(int) j];
        }
    }

    public final String getFromNative(long j) {
        short s = this.stringLengths.getShort(j);
        if (s < 0) {
            return null;
        }
        long j2 = this.sizeof * j * ((long) this.maxStringLength) * 4;
        for (int i = 0; i < s; i++) {
            this.byteArray[i] = LargeArrayUtils.UNSAFE.getByte(this.ptr + j2 + (this.sizeof * ((long) i)));
        }
        try {
            return new String(this.byteArray, 0, s, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public final boolean getBoolean(long j) {
        String str = get(j);
        if (str == null || str.length() == 0) {
            return false;
        }
        return true;
    }

    public final byte getByte(long j) {
        String str = get(j);
        return (byte) (str != null ? str.length() : 0);
    }

    public final short getUnsignedByte(long j) {
        String str = get(j);
        return (short) (str != null ? str.length() & 255 : 0);
    }

    public final short getShort(long j) {
        String str = get(j);
        return (short) (str != null ? str.length() : 0);
    }

    public final int getInt(long j) {
        String str = get(j);
        if (str != null) {
            return str.length();
        }
        return 0;
    }

    public final long getLong(long j) {
        String str = get(j);
        return (long) (str != null ? str.length() : 0);
    }

    public final float getFloat(long j) {
        String str = get(j);
        return (float) (str != null ? str.length() : 0);
    }

    public final double getDouble(long j) {
        String str = get(j);
        return (double) (str != null ? str.length() : 0);
    }

    public final String[] getData() {
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
                zArr[i] = this.stringLengths.getShort(j) != 0;
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            if (str == null || str.length() == 0) {
                z = false;
            }
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                zArr[i2] = z;
            }
        } else {
            for (int i3 = 0; ((long) i3) < this.length; i3++) {
                String str2 = this.data[i3];
                zArr[i3] = (str2 == null || str2.length() == 0) ? false : true;
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
            boolean z = true;
            int i = 0;
            if (this.ptr != 0) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    zArr[i2] = this.stringLengths.getShort(j) > 0;
                    j += j3;
                    i2 = i3;
                }
            } else if (this.isConstant) {
                String str = this.data[0];
                if (str == null || str.length() == 0) {
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
                    String str2 = this.data[(int) j];
                    int i5 = i4 + 1;
                    zArr[i4] = (str2 != null ? str2.length() : 0) != 0;
                    j += j3;
                    i4 = i5;
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
                bArr[i] = (byte) this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            byte length = (byte) (str != null ? str.length() : 0);
            while (((long) i) < this.length) {
                bArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                String str2 = this.data[i2];
                bArr[i2] = (byte) (str2 != null ? str2.length() : 0);
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
                    bArr[i] = (byte) this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    bArr[i2] = (byte) (str != null ? str.length() : 0);
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    bArr[i4] = (byte) (str2 != null ? str2.length() : 0);
                    j += j3;
                    i4 = i5;
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
                sArr[i] = this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            short length = (short) (str != null ? str.length() : 0);
            while (((long) i) < this.length) {
                sArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                String str2 = this.data[i2];
                sArr[i2] = (short) (str2 != null ? str2.length() : 0);
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
                    sArr[i] = this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    sArr[i2] = (short) (str != null ? str.length() : 0);
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    sArr[i4] = (short) (str2 != null ? str2.length() : 0);
                    j += j3;
                    i4 = i5;
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
                iArr[i] = this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (((long) i) < this.length) {
                iArr[i] = length;
                i++;
            }
        } else {
            for (int i2 = 0; ((long) i2) < this.length; i2++) {
                String str2 = this.data[i2];
                iArr[i2] = str2 != null ? str2.length() : 0;
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
                    iArr[i] = this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    iArr[i2] = str != null ? str.length() : 0;
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    iArr[i4] = str2 != null ? str2.length() : 0;
                    j += j3;
                    i4 = i5;
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
                jArr[i] = (long) this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (((long) i) < this.length) {
                jArr[i] = (long) length;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                String str2 = this.data[i];
                jArr[i] = str2 != null ? (long) str2.length() : 0;
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
                    jArr[i] = (long) this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    jArr[i2] = (long) (str != null ? str.length() : 0);
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    jArr[i4] = (long) (str2 != null ? str2.length() : 0);
                    j += j3;
                    i4 = i5;
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
                fArr[i] = (float) this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (((long) i) < this.length) {
                fArr[i] = (float) length;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                String str2 = this.data[i];
                fArr[i] = str2 != null ? (float) str2.length() : 0.0f;
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
                    fArr[i] = (float) this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    fArr[i2] = str != null ? (float) str.length() : 0.0f;
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    fArr[i4] = (float) (str2 != null ? str2.length() : 0);
                    j += j3;
                    i4 = i5;
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
                dArr[i] = (double) this.stringLengths.getShort(j);
                i++;
            }
        } else if (this.isConstant) {
            String str = this.data[0];
            int length = str != null ? str.length() : 0;
            while (((long) i) < this.length) {
                dArr[i] = (double) length;
                i++;
            }
        } else {
            while (((long) i) < this.length) {
                String str2 = this.data[i];
                dArr[i] = str2 != null ? (double) str2.length() : 0.0d;
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
                    dArr[i] = (double) this.stringLengths.getShort(j);
                    j += j3;
                    i++;
                }
            } else if (this.isConstant) {
                int i2 = 0;
                while (j < j2) {
                    int i3 = i2 + 1;
                    String str = this.data[0];
                    dArr[i2] = str != null ? (double) str.length() : 0.0d;
                    j += j3;
                    i2 = i3;
                }
            } else {
                int i4 = 0;
                while (j < j2) {
                    int i5 = i4 + 1;
                    String str2 = this.data[(int) j];
                    dArr[i4] = (double) (str2 != null ? str2.length() : 0);
                    j += j3;
                    i4 = i5;
                }
            }
            return dArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final void setToNative(long j, Object obj) {
        if (obj == null) {
            this.stringLengths.setShort(j, -1);
        } else if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() <= this.maxStringLength) {
                try {
                    byte[] bytes = str.getBytes("UTF-8");
                    int length = bytes.length;
                    if (length <= 32767) {
                        this.stringLengths.setShort(j, (short) length);
                        long j2 = this.sizeof * j * ((long) this.maxStringLength) * 4;
                        for (int i = 0; i < length; i++) {
                            LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * ((long) i)), bytes[i]);
                        }
                        return;
                    }
                    throw new IllegalArgumentException("String  " + str + " is too long.");
                } catch (UnsupportedEncodingException unused) {
                }
            } else {
                throw new IllegalArgumentException("String  " + str + " is too long.");
            }
        } else {
            throw new IllegalArgumentException(obj + " is not a string.");
        }
    }

    public final void set(long j, Object obj) {
        if (obj == null) {
            if (this.ptr != 0) {
                this.stringLengths.setShort(j, -1);
            } else if (!this.isConstant) {
                this.data[(int) j] = null;
            } else {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
        } else if (obj instanceof String) {
            String str = (String) obj;
            if (this.ptr != 0) {
                if (str.length() <= this.maxStringLength) {
                    try {
                        byte[] bytes = str.getBytes("UTF-8");
                        int length = bytes.length;
                        if (length <= 32767) {
                            this.stringLengths.setShort(j, (short) length);
                            long j2 = this.sizeof * j * ((long) this.maxStringLength) * 4;
                            for (int i = 0; i < length; i++) {
                                LargeArrayUtils.UNSAFE.putByte(this.ptr + j2 + (this.sizeof * ((long) i)), bytes[i]);
                            }
                            return;
                        }
                        throw new IllegalArgumentException("String  " + str + " is too long.");
                    } catch (UnsupportedEncodingException unused) {
                    }
                } else {
                    throw new IllegalArgumentException("String  " + str + " is too long.");
                }
            } else if (!this.isConstant) {
                this.data[(int) j] = str;
            } else {
                throw new IllegalAccessError("Constant arrays cannot be modified.");
            }
        } else {
            throw new IllegalArgumentException(obj + " is not a string.");
        }
    }

    public final void set_safe(long j, Object obj) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException(Long.toString(j));
        }
        set(j, obj);
    }

    public final void setBoolean(long j, boolean z) {
        set(j, Boolean.toString(z));
    }

    public final void setByte(long j, byte b2) {
        set(j, Byte.toString(b2));
    }

    public final void setUnsignedByte(long j, short s) {
        setShort(j, s);
    }

    public final void setShort(long j, short s) {
        set(j, Short.toString(s));
    }

    public final void setInt(long j, int i) {
        set(j, Integer.toString(i));
    }

    public final void setLong(long j, long j2) {
        set(j, Long.toString(j2));
    }

    public final void setFloat(long j, float f) {
        set(j, Float.toString(f));
    }

    public final void setDouble(long j, double d) {
        set(j, Double.toString(d));
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }
}
