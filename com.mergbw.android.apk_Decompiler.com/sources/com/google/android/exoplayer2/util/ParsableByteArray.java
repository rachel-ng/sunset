package com.google.android.exoplayer2.util;

import com.google.common.base.Ascii;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import com.google.common.primitives.UnsignedBytes;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import okio.Utf8;

public final class ParsableByteArray {
    private static final char[] CR_AND_LF = {13, 10};
    private static final char[] LF = {10};
    private static final ImmutableSet<Charset> SUPPORTED_CHARSETS_FOR_READLINE = ImmutableSet.of(Charsets.US_ASCII, Charsets.UTF_8, Charsets.UTF_16, Charsets.UTF_16BE, Charsets.UTF_16LE);
    private byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
        this.data = Util.EMPTY_BYTE_ARRAY;
    }

    public ParsableByteArray(int i) {
        this.data = new byte[i];
        this.limit = i;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public ParsableByteArray(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
    }

    public void reset(int i) {
        reset(capacity() < i ? new byte[i] : this.data, i);
    }

    public void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
        this.position = 0;
    }

    public void ensureCapacity(int i) {
        if (i > capacity()) {
            this.data = Arrays.copyOf(this.data, i);
        }
    }

    public int bytesLeft() {
        return this.limit - this.position;
    }

    public int limit() {
        return this.limit;
    }

    public void setLimit(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.data.length);
        this.limit = i;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.limit);
        this.position = i;
    }

    public byte[] getData() {
        return this.data;
    }

    public int capacity() {
        return this.data.length;
    }

    public void skipBytes(int i) {
        setPosition(this.position + i);
    }

    public void readBytes(ParsableBitArray parsableBitArray, int i) {
        readBytes(parsableBitArray.data, 0, i);
        parsableBitArray.setPosition(0);
    }

    public void readBytes(byte[] bArr, int i, int i2) {
        System.arraycopy(this.data, this.position, bArr, i, i2);
        this.position += i2;
    }

    public void readBytes(ByteBuffer byteBuffer, int i) {
        byteBuffer.put(this.data, this.position, i);
        this.position += i;
    }

    public int peekUnsignedByte() {
        return this.data[this.position] & 255;
    }

    public char peekChar() {
        byte[] bArr = this.data;
        int i = this.position;
        return (char) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int readUnsignedByte() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & 255;
    }

    public int readUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        this.position = i + 2;
        return (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
    }

    public int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        this.position = i + 2;
        return ((bArr[i2] & 255) << 8) | (bArr[i] & 255);
    }

    public short readShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        this.position = i + 2;
        return (short) ((bArr[i2] & 255) | ((bArr[i] & 255) << 8));
    }

    public short readLittleEndianShort() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        this.position = i + 2;
        return (short) (((bArr[i2] & 255) << 8) | (bArr[i] & 255));
    }

    public int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = (bArr[i2] & 255) << 8;
        this.position = i + 3;
        return (bArr[i3] & 255) | i4 | ((bArr[i] & 255) << 16);
    }

    public int readInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = (bArr[i2] & 255) << 8;
        this.position = i + 3;
        return (bArr[i3] & 255) | i4 | (((bArr[i] & 255) << Ascii.CAN) >> 8);
    }

    public int readLittleEndianInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = (bArr[i2] & 255) << 8;
        this.position = i + 3;
        return ((bArr[i3] & 255) << 16) | i4 | (bArr[i] & 255);
    }

    public int readLittleEndianUnsignedInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = (bArr[i2] & 255) << 8;
        this.position = i + 3;
        return ((bArr[i3] & 255) << 16) | i4 | (bArr[i] & 255);
    }

    public long readUnsignedInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = i + 3;
        this.position = i4;
        this.position = i + 4;
        return (((long) bArr[i4]) & 255) | ((((long) bArr[i]) & 255) << 24) | ((((long) bArr[i2]) & 255) << 16) | ((((long) bArr[i3]) & 255) << 8);
    }

    public long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = i + 3;
        this.position = i4;
        this.position = i + 4;
        return ((((long) bArr[i4]) & 255) << 24) | (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8) | ((((long) bArr[i3]) & 255) << 16);
    }

    public int readInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = (bArr[i] & 255) << Ascii.CAN;
        int i4 = i + 2;
        this.position = i4;
        byte b2 = ((bArr[i2] & 255) << 16) | i3;
        int i5 = i + 3;
        this.position = i5;
        this.position = i + 4;
        return (bArr[i5] & 255) | b2 | ((bArr[i4] & 255) << 8);
    }

    public int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        byte b2 = ((bArr[i2] & 255) << 8) | (bArr[i] & 255);
        int i4 = i + 3;
        this.position = i4;
        this.position = i + 4;
        return ((bArr[i4] & 255) << Ascii.CAN) | b2 | ((bArr[i3] & 255) << 16);
    }

    public long readLong() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        int i4 = i + 3;
        this.position = i4;
        long j = ((((long) bArr[i]) & 255) << 56) | ((((long) bArr[i2]) & 255) << 48) | ((((long) bArr[i3]) & 255) << 40);
        int i5 = i + 4;
        this.position = i5;
        int i6 = i + 5;
        this.position = i6;
        long j2 = j | ((((long) bArr[i4]) & 255) << 32) | ((((long) bArr[i5]) & 255) << 24);
        int i7 = i + 6;
        this.position = i7;
        int i8 = i + 7;
        this.position = i8;
        this.position = i + 8;
        return (((long) bArr[i8]) & 255) | j2 | ((((long) bArr[i6]) & 255) << 16) | ((((long) bArr[i7]) & 255) << 8);
    }

    public long readLittleEndianLong() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        int i3 = i + 2;
        this.position = i3;
        long j = (((long) bArr[i]) & 255) | ((((long) bArr[i2]) & 255) << 8);
        int i4 = i + 3;
        this.position = i4;
        int i5 = i + 4;
        this.position = i5;
        long j2 = j | ((((long) bArr[i3]) & 255) << 16) | ((((long) bArr[i4]) & 255) << 24);
        int i6 = i + 5;
        this.position = i6;
        int i7 = i + 6;
        this.position = i7;
        long j3 = j2 | ((((long) bArr[i5]) & 255) << 32) | ((((long) bArr[i6]) & 255) << 40);
        int i8 = i + 7;
        this.position = i8;
        this.position = i + 8;
        return ((((long) bArr[i8]) & 255) << 56) | j3 | ((((long) bArr[i7]) & 255) << 48);
    }

    public int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i = this.position;
        int i2 = i + 1;
        this.position = i2;
        this.position = i + 2;
        byte b2 = (bArr[i2] & 255) | ((bArr[i] & 255) << 8);
        this.position = i + 4;
        return b2;
    }

    public int readSynchSafeInt() {
        return (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7) | readUnsignedByte();
    }

    public int readUnsignedIntToInt() {
        int readInt = readInt();
        if (readInt >= 0) {
            return readInt;
        }
        throw new IllegalStateException("Top bit not zero: " + readInt);
    }

    public int readLittleEndianUnsignedIntToInt() {
        int readLittleEndianInt = readLittleEndianInt();
        if (readLittleEndianInt >= 0) {
            return readLittleEndianInt;
        }
        throw new IllegalStateException("Top bit not zero: " + readLittleEndianInt);
    }

    public long readUnsignedLongToLong() {
        long readLong = readLong();
        if (readLong >= 0) {
            return readLong;
        }
        throw new IllegalStateException("Top bit not zero: " + readLong);
    }

    public float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public String readString(int i) {
        return readString(i, Charsets.UTF_8);
    }

    public String readString(int i, Charset charset) {
        String str = new String(this.data, this.position, i, charset);
        this.position += i;
        return str;
    }

    public String readNullTerminatedString(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = this.position;
        int i3 = (i2 + i) - 1;
        String fromUtf8Bytes = Util.fromUtf8Bytes(this.data, i2, (i3 >= this.limit || this.data[i3] != 0) ? i : i - 1);
        this.position += i;
        return fromUtf8Bytes;
    }

    public String readNullTerminatedString() {
        return readDelimiterTerminatedString(0);
    }

    public String readDelimiterTerminatedString(char c2) {
        if (bytesLeft() == 0) {
            return null;
        }
        int i = this.position;
        while (i < this.limit && this.data[i] != c2) {
            i++;
        }
        byte[] bArr = this.data;
        int i2 = this.position;
        String fromUtf8Bytes = Util.fromUtf8Bytes(bArr, i2, i - i2);
        this.position = i;
        if (i < this.limit) {
            this.position = i + 1;
        }
        return fromUtf8Bytes;
    }

    public String readLine() {
        return readLine(Charsets.UTF_8);
    }

    public String readLine(Charset charset) {
        boolean contains = SUPPORTED_CHARSETS_FOR_READLINE.contains(charset);
        Assertions.checkArgument(contains, "Unsupported charset: " + charset);
        if (bytesLeft() == 0) {
            return null;
        }
        if (!charset.equals(Charsets.US_ASCII)) {
            readUtfCharsetFromBom();
        }
        String readString = readString(findNextLineTerminator(charset) - this.position, charset);
        if (this.position == this.limit) {
            return readString;
        }
        skipLineTerminator(charset);
        return readString;
    }

    public long readUtf8EncodedLong() {
        int i;
        int i2;
        long j = (long) this.data[this.position];
        int i3 = 7;
        while (true) {
            i = 1;
            if (i3 < 0) {
                break;
            }
            int i4 = 1 << i3;
            if ((((long) i4) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= (long) (i4 - 1);
                i2 = 7 - i3;
            } else if (i3 == 7) {
                i2 = 1;
            }
        }
        i2 = 0;
        if (i2 != 0) {
            while (i < i2) {
                byte b2 = this.data[this.position + i];
                if ((b2 & 192) == 128) {
                    j = (j << 6) | ((long) (b2 & Utf8.REPLACEMENT_BYTE));
                    i++;
                } else {
                    throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
                }
            }
            this.position += i2;
            return j;
        }
        throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
    }

    public Charset readUtfCharsetFromBom() {
        if (bytesLeft() >= 3) {
            byte[] bArr = this.data;
            int i = this.position;
            if (bArr[i] == -17 && bArr[i + 1] == -69 && bArr[i + 2] == -65) {
                this.position = i + 3;
                return Charsets.UTF_8;
            }
        }
        if (bytesLeft() < 2) {
            return null;
        }
        byte[] bArr2 = this.data;
        int i2 = this.position;
        byte b2 = bArr2[i2];
        if (b2 == -2 && bArr2[i2 + 1] == -1) {
            this.position = i2 + 2;
            return Charsets.UTF_16BE;
        } else if (b2 != -1 || bArr2[i2 + 1] != -2) {
            return null;
        } else {
            this.position = i2 + 2;
            return Charsets.UTF_16LE;
        }
    }

    private int findNextLineTerminator(Charset charset) {
        int i;
        if (charset.equals(Charsets.UTF_8) || charset.equals(Charsets.US_ASCII)) {
            i = 1;
        } else if (charset.equals(Charsets.UTF_16) || charset.equals(Charsets.UTF_16LE) || charset.equals(Charsets.UTF_16BE)) {
            i = 2;
        } else {
            throw new IllegalArgumentException("Unsupported charset: " + charset);
        }
        int i2 = this.position;
        while (true) {
            int i3 = this.limit;
            if (i2 >= i3 - (i - 1)) {
                return i3;
            }
            if ((charset.equals(Charsets.UTF_8) || charset.equals(Charsets.US_ASCII)) && Util.isLinebreak(this.data[i2])) {
                return i2;
            }
            if (charset.equals(Charsets.UTF_16) || charset.equals(Charsets.UTF_16BE)) {
                byte[] bArr = this.data;
                if (bArr[i2] == 0 && Util.isLinebreak(bArr[i2 + 1])) {
                    return i2;
                }
            }
            if (charset.equals(Charsets.UTF_16LE)) {
                byte[] bArr2 = this.data;
                if (bArr2[i2 + 1] == 0 && Util.isLinebreak(bArr2[i2])) {
                    return i2;
                }
            }
            i2 += i;
        }
    }

    private void skipLineTerminator(Charset charset) {
        if (readCharacterIfInList(charset, CR_AND_LF) == 13) {
            readCharacterIfInList(charset, LF);
        }
    }

    private char readCharacterIfInList(Charset charset, char[] cArr) {
        char c2;
        int i = 1;
        if ((charset.equals(Charsets.UTF_8) || charset.equals(Charsets.US_ASCII)) && bytesLeft() >= 1) {
            c2 = Chars.checkedCast((long) UnsignedBytes.toInt(this.data[this.position]));
        } else {
            if ((charset.equals(Charsets.UTF_16) || charset.equals(Charsets.UTF_16BE)) && bytesLeft() >= 2) {
                byte[] bArr = this.data;
                int i2 = this.position;
                c2 = Chars.fromBytes(bArr[i2], bArr[i2 + 1]);
            } else {
                if (charset.equals(Charsets.UTF_16LE) && bytesLeft() >= 2) {
                    byte[] bArr2 = this.data;
                    int i3 = this.position;
                    c2 = Chars.fromBytes(bArr2[i3 + 1], bArr2[i3]);
                }
                return 0;
            }
            i = 2;
        }
        if (Chars.contains(cArr, c2)) {
            this.position += i;
            return Chars.checkedCast((long) c2);
        }
        return 0;
    }
}
