package pl.edu.icm.jlargearrays;

import org.apache.commons.math3.util.FastMath;

public class ComplexFloatLargeArray extends LargeArray {
    private static final long serialVersionUID = 155390537810310407L;
    private FloatLargeArray dataIm;
    private FloatLargeArray dataRe;

    public ComplexFloatLargeArray(long j) {
        this(j, true);
    }

    public ComplexFloatLargeArray(long j, boolean z) {
        this.type = LargeArrayType.COMPLEX_FLOAT;
        this.sizeof = 4;
        if (j > 0) {
            this.length = j;
            this.dataRe = new FloatLargeArray(j, z);
            this.dataIm = new FloatLargeArray(j, z);
            return;
        }
        throw new IllegalArgumentException(j + " is not a positive long value");
    }

    public ComplexFloatLargeArray(long j, float[] fArr) {
        this.type = LargeArrayType.COMPLEX_FLOAT;
        this.sizeof = 4;
        if (j <= 0) {
            throw new IllegalArgumentException(j + " is not a positive long value");
        } else if (fArr == null || fArr.length != 2) {
            throw new IllegalArgumentException("constantValue == null || constantValue.length != 2");
        } else {
            this.length = j;
            this.isConstant = true;
            this.dataRe = new FloatLargeArray(j, fArr[0]);
            this.dataIm = new FloatLargeArray(j, fArr[1]);
        }
    }

    public ComplexFloatLargeArray(float[] fArr) {
        this(new FloatLargeArray(fArr));
    }

    public ComplexFloatLargeArray(FloatLargeArray floatLargeArray) {
        if (floatLargeArray.length() % 2 != 0) {
            throw new IllegalArgumentException("The length of the data array must be even.");
        } else if (floatLargeArray.length() > 0) {
            this.type = LargeArrayType.COMPLEX_FLOAT;
            this.sizeof = 4;
            this.length = floatLargeArray.length / 2;
            this.isConstant = floatLargeArray.isConstant;
            if (this.isConstant) {
                this.dataRe = new FloatLargeArray(this.length, floatLargeArray.getFloat(0));
                this.dataIm = new FloatLargeArray(this.length, floatLargeArray.getFloat(1));
                return;
            }
            this.dataRe = new FloatLargeArray(this.length, false);
            this.dataIm = new FloatLargeArray(this.length, false);
            for (long j = 0; j < this.length; j++) {
                long j2 = j * 2;
                this.dataRe.setFloat(j, floatLargeArray.getFloat(j2));
                this.dataIm.setFloat(j, floatLargeArray.getFloat(j2 + 1));
            }
        } else {
            throw new IllegalArgumentException(floatLargeArray.length() + " is not a positive long value");
        }
    }

    public ComplexFloatLargeArray(float[] fArr, float[] fArr2) {
        this(new FloatLargeArray(fArr), new FloatLargeArray(fArr2));
    }

    public ComplexFloatLargeArray(FloatLargeArray floatLargeArray, FloatLargeArray floatLargeArray2) {
        if (floatLargeArray.length() != floatLargeArray2.length()) {
            throw new IllegalArgumentException("The length of the dataRe must be equal to the length of dataIm.");
        } else if (floatLargeArray.length() <= 0) {
            throw new IllegalArgumentException(floatLargeArray.length() + " is not a positive long value");
        } else if (floatLargeArray.isLarge() == floatLargeArray2.isLarge()) {
            this.type = LargeArrayType.COMPLEX_FLOAT;
            this.sizeof = 4;
            this.length = floatLargeArray.length();
            this.dataRe = floatLargeArray;
            this.dataIm = floatLargeArray2;
        } else {
            throw new IllegalArgumentException("dataRe.isLarge() != dataIm.isLarge()");
        }
    }

    public ComplexFloatLargeArray clone() {
        if (this.isConstant) {
            return new ComplexFloatLargeArray(this.length, new float[]{this.dataRe.getFloat(0), this.dataIm.getFloat(0)});
        }
        ComplexFloatLargeArray complexFloatLargeArray = new ComplexFloatLargeArray(this.length, false);
        LargeArrayUtils.arraycopy(this, 0, complexFloatLargeArray, 0, this.length);
        return complexFloatLargeArray;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        ComplexFloatLargeArray complexFloatLargeArray = (ComplexFloatLargeArray) obj;
        if (!this.dataRe.equals(complexFloatLargeArray.dataRe) || !this.dataIm.equals(complexFloatLargeArray.dataIm)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 29;
        FloatLargeArray floatLargeArray = this.dataRe;
        int i = 0;
        int hashCode2 = (hashCode + (floatLargeArray != null ? floatLargeArray.hashCode() : 0)) * 29;
        FloatLargeArray floatLargeArray2 = this.dataIm;
        if (floatLargeArray2 != null) {
            i = floatLargeArray2.hashCode();
        }
        return hashCode2 + i;
    }

    public boolean isLarge() {
        return this.dataRe.isLarge();
    }

    public final FloatLargeArray getRealArray() {
        return this.dataRe;
    }

    public final FloatLargeArray getImaginaryArray() {
        return this.dataIm;
    }

    public final FloatLargeArray getAbsArray() {
        FloatLargeArray floatLargeArray = new FloatLargeArray(this.length, false);
        for (long j = 0; j < this.length; j++) {
            double d = (double) this.dataRe.getFloat(j);
            double d2 = (double) this.dataIm.getFloat(j);
            floatLargeArray.setFloat(j, (float) FastMath.sqrt((d * d) + (d2 * d2)));
        }
        return floatLargeArray;
    }

    public final FloatLargeArray getArgArray() {
        FloatLargeArray floatLargeArray = new FloatLargeArray(this.length, false);
        for (long j = 0; j < this.length; j++) {
            floatLargeArray.setFloat(j, (float) FastMath.atan2((double) this.dataIm.getFloat(j), (double) this.dataRe.getFloat(j)));
        }
        return floatLargeArray;
    }

    public final float[] get(long j) {
        return getComplexFloat(j);
    }

    public final float[] getFromNative(long j) {
        return new float[]{this.dataRe.getFromNative(j).floatValue(), this.dataIm.getFromNative(j).floatValue()};
    }

    public final boolean getBoolean(long j) {
        return this.dataRe.getBoolean(j);
    }

    public final byte getByte(long j) {
        return this.dataRe.getByte(j);
    }

    public final short getUnsignedByte(long j) {
        return this.dataRe.getUnsignedByte(j);
    }

    public final short getShort(long j) {
        return this.dataRe.getShort(j);
    }

    public final int getInt(long j) {
        return this.dataRe.getInt(j);
    }

    public final long getLong(long j) {
        return this.dataRe.getLong(j);
    }

    public final float getFloat(long j) {
        return this.dataRe.getFloat(j);
    }

    public final double getDouble(long j) {
        return this.dataRe.getDouble(j);
    }

    public final float[] getComplexFloat(long j) {
        return new float[]{this.dataRe.getFloat(j), this.dataIm.getFloat(j)};
    }

    public final double[] getComplexDouble(long j) {
        return new double[]{this.dataRe.getDouble(j), this.dataIm.getDouble(j)};
    }

    public final float[][] getData() {
        if (isLarge()) {
            float[][] fArr = null;
            return null;
        }
        return new float[][]{this.dataRe.getData(), this.dataIm.getData()};
    }

    public final boolean[] getBooleanData() {
        return this.dataRe.getBooleanData();
    }

    public final boolean[] getBooleanData(boolean[] zArr, long j, long j2, long j3) {
        return this.dataRe.getBooleanData(zArr, j, j2, j3);
    }

    public final byte[] getByteData() {
        return this.dataRe.getByteData();
    }

    public final byte[] getByteData(byte[] bArr, long j, long j2, long j3) {
        return this.dataRe.getByteData(bArr, j, j2, j3);
    }

    public final short[] getShortData() {
        return this.dataRe.getShortData();
    }

    public final short[] getShortData(short[] sArr, long j, long j2, long j3) {
        return this.dataRe.getShortData(sArr, j, j2, j3);
    }

    public final int[] getIntData() {
        return this.dataRe.getIntData();
    }

    public final int[] getIntData(int[] iArr, long j, long j2, long j3) {
        return this.dataRe.getIntData(iArr, j, j2, j3);
    }

    public final long[] getLongData() {
        return this.dataRe.getLongData();
    }

    public final long[] getLongData(long[] jArr, long j, long j2, long j3) {
        return this.dataRe.getLongData(jArr, j, j2, j3);
    }

    public final float[] getFloatData() {
        return this.dataRe.getFloatData();
    }

    public final float[] getFloatData(float[] fArr, long j, long j2, long j3) {
        return this.dataRe.getFloatData(fArr, j, j2, j3);
    }

    public final double[] getDoubleData() {
        return this.dataRe.getDoubleData();
    }

    public final double[] getDoubleData(double[] dArr, long j, long j2, long j3) {
        return this.dataRe.getDoubleData(dArr, j, j2, j3);
    }

    public final float[] getComplexData() {
        if (this.length * 2 > 1073741824) {
            return null;
        }
        float[] fArr = new float[((int) (this.length * 2))];
        int i = 0;
        while (true) {
            long j = (long) i;
            if (j >= this.length) {
                return fArr;
            }
            int i2 = i * 2;
            fArr[i2] = this.dataRe.getFloat(j);
            fArr[i2 + 1] = this.dataIm.getFloat(j);
            i++;
        }
    }

    public final float[] getComplexData(float[] fArr, long j, long j2, long j3) {
        if (j < 0 || j >= this.length) {
            throw new ArrayIndexOutOfBoundsException("startPos < 0 || startPos >= length");
        } else if (j2 < 0 || j2 > this.length || j2 < j) {
            throw new ArrayIndexOutOfBoundsException("endPos < 0 || endPos > length || endPos < startPos");
        } else if (j3 >= 1) {
            long ceil = ((long) FastMath.ceil(((double) (j2 - j)) / ((double) j3))) * 2;
            if (ceil > 1073741824) {
                return null;
            }
            if (fArr == null || ((long) fArr.length) < ceil) {
                fArr = new float[((int) ceil)];
            }
            int i = 0;
            while (j < j2) {
                int i2 = i + 1;
                fArr[i] = this.dataRe.getFloat(j);
                i += 2;
                fArr[i2] = this.dataIm.getFloat(j);
                j += j3;
            }
            return fArr;
        } else {
            throw new IllegalArgumentException("step < 1");
        }
    }

    public final void setToNative(long j, Object obj) {
        if (obj instanceof float[]) {
            float[] fArr = (float[]) obj;
            this.dataRe.setToNative(j, Float.valueOf(fArr[0]));
            this.dataIm.setToNative(j, Float.valueOf(fArr[1]));
            return;
        }
        throw new IllegalArgumentException(obj + " is not an array of floats.");
    }

    public final void setBoolean(long j, boolean z) {
        this.dataRe.setBoolean(j, z);
        this.dataIm.setBoolean(j, false);
    }

    public final void setByte(long j, byte b2) {
        this.dataRe.setByte(j, b2);
        this.dataIm.setByte(j, (byte) 0);
    }

    public final void setUnsignedByte(long j, short s) {
        this.dataRe.setUnsignedByte(j, s);
        this.dataIm.setUnsignedByte(j, 0);
    }

    public final void setShort(long j, short s) {
        this.dataRe.setShort(j, s);
        this.dataIm.setShort(j, 0);
    }

    public final void setInt(long j, int i) {
        this.dataRe.setInt(j, i);
        this.dataIm.setInt(j, 0);
    }

    public final void setLong(long j, long j2) {
        this.dataRe.setLong(j, j2);
        this.dataIm.setLong(j, 0);
    }

    public final void setFloat(long j, float f) {
        this.dataRe.setFloat(j, f);
        this.dataIm.setFloat(j, 0.0f);
    }

    public final void setDouble(long j, double d) {
        this.dataRe.setDouble(j, d);
        this.dataIm.setDouble(j, 0.0d);
    }

    public final void set(long j, Object obj) {
        if (obj instanceof float[]) {
            setComplexFloat(j, (float[]) obj);
            return;
        }
        throw new IllegalArgumentException(obj + " is not an array of floats.");
    }

    public final void setComplexFloat(long j, float[] fArr) {
        this.dataRe.setFloat(j, fArr[0]);
        this.dataIm.setFloat(j, fArr[1]);
    }

    public final void setComplexDouble(long j, double[] dArr) {
        this.dataRe.setDouble(j, dArr[0]);
        this.dataIm.setDouble(j, dArr[1]);
    }
}
