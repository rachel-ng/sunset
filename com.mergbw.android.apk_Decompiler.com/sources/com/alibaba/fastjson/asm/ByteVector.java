package com.alibaba.fastjson.asm;

public class ByteVector {
    public byte[] data;
    public int length;

    public ByteVector() {
        this.data = new byte[64];
    }

    public ByteVector(int i) {
        this.data = new byte[i];
    }

    public ByteVector putByte(int i) {
        int i2 = this.length;
        int i3 = i2 + 1;
        if (i3 > this.data.length) {
            enlarge(1);
        }
        this.data[i2] = (byte) i;
        this.length = i3;
        return this;
    }

    /* access modifiers changed from: package-private */
    public ByteVector put11(int i, int i2) {
        int i3 = this.length;
        if (i3 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        bArr[i3] = (byte) i;
        bArr[i3 + 1] = (byte) i2;
        this.length = i3 + 2;
        return this;
    }

    public ByteVector putShort(int i) {
        int i2 = this.length;
        if (i2 + 2 > this.data.length) {
            enlarge(2);
        }
        byte[] bArr = this.data;
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
        this.length = i2 + 2;
        return this;
    }

    public ByteVector put12(int i, int i2) {
        int i3 = this.length;
        if (i3 + 3 > this.data.length) {
            enlarge(3);
        }
        byte[] bArr = this.data;
        bArr[i3] = (byte) i;
        bArr[i3 + 1] = (byte) (i2 >>> 8);
        bArr[i3 + 2] = (byte) i2;
        this.length = i3 + 3;
        return this;
    }

    public ByteVector putInt(int i) {
        int i2 = this.length;
        if (i2 + 4 > this.data.length) {
            enlarge(4);
        }
        byte[] bArr = this.data;
        bArr[i2] = (byte) (i >>> 24);
        bArr[i2 + 1] = (byte) (i >>> 16);
        bArr[i2 + 2] = (byte) (i >>> 8);
        bArr[i2 + 3] = (byte) i;
        this.length = i2 + 4;
        return this;
    }

    public ByteVector putUTF8(String str) {
        int length2 = str.length();
        int i = this.length;
        if (i + 2 + length2 > this.data.length) {
            enlarge(length2 + 2);
        }
        byte[] bArr = this.data;
        int i2 = i + 1;
        bArr[i] = (byte) (length2 >>> 8);
        int i3 = i + 2;
        bArr[i2] = (byte) length2;
        int i4 = 0;
        while (i4 < length2) {
            char charAt = str.charAt(i4);
            if ((charAt < 1 || charAt > 127) && (charAt < 19968 || charAt > 40959)) {
                throw new UnsupportedOperationException();
            }
            bArr[i3] = (byte) charAt;
            i4++;
            i3++;
        }
        this.length = i3;
        return this;
    }

    public ByteVector putByteArray(byte[] bArr, int i, int i2) {
        if (this.length + i2 > this.data.length) {
            enlarge(i2);
        }
        if (bArr != null) {
            System.arraycopy(bArr, i, this.data, this.length, i2);
        }
        this.length += i2;
        return this;
    }

    private void enlarge(int i) {
        byte[] bArr = this.data;
        int length2 = bArr.length * 2;
        int i2 = this.length;
        int i3 = i + i2;
        if (length2 <= i3) {
            length2 = i3;
        }
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        this.data = bArr2;
    }
}
