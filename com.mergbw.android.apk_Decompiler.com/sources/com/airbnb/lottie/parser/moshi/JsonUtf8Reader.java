package com.airbnb.lottie.parser.moshi;

import com.airbnb.lottie.parser.moshi.JsonReader;
import java.io.EOFException;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;

final class JsonUtf8Reader extends JsonReader {
    private static final ByteString CLOSING_BLOCK_COMMENT = ByteString.encodeUtf8("*/");
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\"\\");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_BUFFERED_NAME = 15;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 18;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 16;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 17;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("'\\");
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private final Buffer buffer;
    private int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;

    JsonUtf8Reader(BufferedSource bufferedSource) {
        if (bufferedSource != null) {
            this.source = bufferedSource;
            this.buffer = bufferedSource.buffer();
            pushScope(6);
            return;
        }
        throw new NullPointerException("source == null");
    }

    public void beginArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 3) {
            pushScope(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void endArray() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void beginObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 1) {
            pushScope(3);
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    public void endObject() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    public boolean hasNext() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        return (i == 2 || i == 4 || i == 18) ? false : true;
    }

    public JsonReader.Token peek() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        switch (i) {
            case 1:
                return JsonReader.Token.BEGIN_OBJECT;
            case 2:
                return JsonReader.Token.END_OBJECT;
            case 3:
                return JsonReader.Token.BEGIN_ARRAY;
            case 4:
                return JsonReader.Token.END_ARRAY;
            case 5:
            case 6:
                return JsonReader.Token.BOOLEAN;
            case 7:
                return JsonReader.Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonReader.Token.STRING;
            case 12:
            case 13:
            case 14:
            case 15:
                return JsonReader.Token.NAME;
            case 16:
            case 17:
                return JsonReader.Token.NUMBER;
            case 18:
                return JsonReader.Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private int doPeek() throws IOException {
        int i = this.scopes[this.stackSize - 1];
        if (i == 1) {
            this.scopes[this.stackSize - 1] = 2;
        } else if (i == 2) {
            int nextNonWhitespace = nextNonWhitespace(true);
            this.buffer.readByte();
            if (nextNonWhitespace != 44) {
                if (nextNonWhitespace == 59) {
                    checkLenient();
                } else if (nextNonWhitespace == 93) {
                    this.peeked = 4;
                    return 4;
                } else {
                    throw syntaxError("Unterminated array");
                }
            }
        } else if (i == 3 || i == 5) {
            this.scopes[this.stackSize - 1] = 4;
            if (i == 5) {
                int nextNonWhitespace2 = nextNonWhitespace(true);
                this.buffer.readByte();
                if (nextNonWhitespace2 != 44) {
                    if (nextNonWhitespace2 == 59) {
                        checkLenient();
                    } else if (nextNonWhitespace2 == 125) {
                        this.peeked = 2;
                        return 2;
                    } else {
                        throw syntaxError("Unterminated object");
                    }
                }
            }
            int nextNonWhitespace3 = nextNonWhitespace(true);
            if (nextNonWhitespace3 == 34) {
                this.buffer.readByte();
                this.peeked = 13;
                return 13;
            } else if (nextNonWhitespace3 == 39) {
                this.buffer.readByte();
                checkLenient();
                this.peeked = 12;
                return 12;
            } else if (nextNonWhitespace3 != 125) {
                checkLenient();
                if (isLiteral((char) nextNonWhitespace3)) {
                    this.peeked = 14;
                    return 14;
                }
                throw syntaxError("Expected name");
            } else if (i != 5) {
                this.buffer.readByte();
                this.peeked = 2;
                return 2;
            } else {
                throw syntaxError("Expected name");
            }
        } else if (i == 4) {
            this.scopes[this.stackSize - 1] = 5;
            int nextNonWhitespace4 = nextNonWhitespace(true);
            this.buffer.readByte();
            if (nextNonWhitespace4 != 58) {
                if (nextNonWhitespace4 == 61) {
                    checkLenient();
                    if (this.source.request(1) && this.buffer.getByte(0) == 62) {
                        this.buffer.readByte();
                    }
                } else {
                    throw syntaxError("Expected ':'");
                }
            }
        } else if (i == 6) {
            this.scopes[this.stackSize - 1] = 7;
        } else if (i == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 18;
                return 18;
            }
            checkLenient();
        } else if (i == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        int nextNonWhitespace5 = nextNonWhitespace(true);
        if (nextNonWhitespace5 == 34) {
            this.buffer.readByte();
            this.peeked = 9;
            return 9;
        } else if (nextNonWhitespace5 != 39) {
            if (!(nextNonWhitespace5 == 44 || nextNonWhitespace5 == 59)) {
                if (nextNonWhitespace5 == 91) {
                    this.buffer.readByte();
                    this.peeked = 3;
                    return 3;
                } else if (nextNonWhitespace5 != 93) {
                    if (nextNonWhitespace5 != 123) {
                        int peekKeyword = peekKeyword();
                        if (peekKeyword != 0) {
                            return peekKeyword;
                        }
                        int peekNumber = peekNumber();
                        if (peekNumber != 0) {
                            return peekNumber;
                        }
                        if (isLiteral(this.buffer.getByte(0))) {
                            checkLenient();
                            this.peeked = 10;
                            return 10;
                        }
                        throw syntaxError("Expected value");
                    }
                    this.buffer.readByte();
                    this.peeked = 1;
                    return 1;
                } else if (i == 1) {
                    this.buffer.readByte();
                    this.peeked = 4;
                    return 4;
                }
            }
            if (i == 1 || i == 2) {
                checkLenient();
                this.peeked = 7;
                return 7;
            }
            throw syntaxError("Unexpected value");
        } else {
            checkLenient();
            this.buffer.readByte();
            this.peeked = 8;
            return 8;
        }
    }

    private int peekKeyword() throws IOException {
        int i;
        String str;
        String str2;
        byte b2 = this.buffer.getByte(0);
        if (b2 == 116 || b2 == 84) {
            str2 = "true";
            str = "TRUE";
            i = 5;
        } else if (b2 == 102 || b2 == 70) {
            str2 = "false";
            str = "FALSE";
            i = 6;
        } else if (b2 != 110 && b2 != 78) {
            return 0;
        } else {
            str2 = "null";
            str = "NULL";
            i = 7;
        }
        int length = str2.length();
        int i2 = 1;
        while (i2 < length) {
            int i3 = i2 + 1;
            if (!this.source.request((long) i3)) {
                return 0;
            }
            byte b3 = this.buffer.getByte((long) i2);
            if (b3 != str2.charAt(i2) && b3 != str.charAt(i2)) {
                return 0;
            }
            i2 = i3;
        }
        if (this.source.request((long) (length + 1)) && isLiteral(this.buffer.getByte((long) length))) {
            return 0;
        }
        this.buffer.skip((long) length);
        this.peeked = i;
        return i;
    }

    private int peekNumber() throws IOException {
        byte b2;
        char c2;
        boolean z = true;
        long j = 0;
        int i = 0;
        char c3 = 0;
        boolean z2 = false;
        boolean z3 = true;
        while (true) {
            int i2 = i + 1;
            if (!this.source.request((long) i2)) {
                break;
            }
            b2 = this.buffer.getByte((long) i);
            if (b2 == 43) {
                c2 = 6;
                if (c3 != 5) {
                    return 0;
                }
            } else if (b2 != 69 && b2 != 101) {
                if (b2 == 45) {
                    c2 = 6;
                    if (c3 == 0) {
                        c3 = 1;
                        z2 = true;
                    } else if (c3 != 5) {
                        return 0;
                    }
                } else if (b2 != 46) {
                    if (b2 >= 48 && b2 <= 57) {
                        if (c3 == z || c3 == 0) {
                            j = (long) (-(b2 - 48));
                            c3 = 2;
                        } else if (c3 == 2) {
                            if (j == 0) {
                                return 0;
                            }
                            long j2 = (10 * j) - ((long) (b2 - 48));
                            int i3 = (j > -922337203685477580L ? 1 : (j == -922337203685477580L ? 0 : -1));
                            z3 &= i3 > 0 || (i3 == 0 && j2 < j);
                            j = j2;
                        } else if (c3 == 3) {
                            c3 = 4;
                        } else if (c3 == 5 || c3 == 6) {
                            c3 = 7;
                        }
                    }
                } else if (c3 != 2) {
                    return 0;
                } else {
                    c3 = 3;
                }
                i = i2;
                z = true;
            } else if (c3 != 2 && c3 != 4) {
                return 0;
            } else {
                c3 = 5;
                i = i2;
                z = true;
            }
            c3 = c2;
            i = i2;
            z = true;
        }
        if (isLiteral(b2)) {
            return 0;
        }
        if (c3 == 2 && z3 && ((j != Long.MIN_VALUE || z2) && (j != 0 || !z2))) {
            if (!z2) {
                j = -j;
            }
            this.peekedLong = j;
            this.buffer.skip((long) i);
            this.peeked = 16;
            return 16;
        } else if (c3 != 2 && c3 != 4 && c3 != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i;
            this.peeked = 17;
            return 17;
        }
    }

    private boolean isLiteral(int i) throws IOException {
        if (i == 9 || i == 10 || i == 12 || i == 13 || i == 32) {
            return false;
        }
        if (i != 35) {
            if (i == 44) {
                return false;
            }
            if (!(i == 47 || i == 61)) {
                if (i == 123 || i == 125 || i == 58) {
                    return false;
                }
                if (i != 59) {
                    switch (i) {
                        case 91:
                        case 93:
                            return false;
                        case 92:
                            break;
                        default:
                            return true;
                    }
                }
            }
        }
        checkLenient();
        return false;
    }

    public String nextName() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 14) {
            str = nextUnquotedValue();
        } else if (i == 13) {
            str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i == 12) {
            str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i == 15) {
            str = this.peekedString;
        } else {
            throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = str;
        return str;
    }

    public int selectName(JsonReader.Options options) throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i < 12 || i > 15) {
            return -1;
        }
        if (i == 15) {
            return findName(this.peekedString, options);
        }
        int select = this.source.select(options.doubleQuoteSuffix);
        if (select != -1) {
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = options.strings[select];
            return select;
        }
        String str = this.pathNames[this.stackSize - 1];
        String nextName = nextName();
        int findName = findName(nextName, options);
        if (findName == -1) {
            this.peeked = 15;
            this.peekedString = nextName;
            this.pathNames[this.stackSize - 1] = str;
        }
        return findName;
    }

    public void skipName() throws IOException {
        if (!this.failOnUnknown) {
            int i = this.peeked;
            if (i == 0) {
                i = doPeek();
            }
            if (i == 14) {
                skipUnquotedValue();
            } else if (i == 13) {
                skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else if (i == 12) {
                skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
            } else if (i != 15) {
                throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
            }
            this.peeked = 0;
            this.pathNames[this.stackSize - 1] = "null";
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    private int findName(String str, JsonReader.Options options) {
        int length = options.strings.length;
        for (int i = 0; i < length; i++) {
            if (str.equals(options.strings[i])) {
                this.peeked = 0;
                this.pathNames[this.stackSize - 1] = str;
                return i;
            }
        }
        return -1;
    }

    public String nextString() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 10) {
            str = nextUnquotedValue();
        } else if (i == 9) {
            str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i == 8) {
            str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (i == 16) {
            str = Long.toString(this.peekedLong);
        } else if (i == 17) {
            str = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else {
            throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        return str;
    }

    public boolean nextBoolean() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return true;
        } else if (i == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    public double nextDouble() throws IOException {
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 16) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.peekedLong;
        }
        if (i == 17) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (i == 9) {
            this.peekedString = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (i == 8) {
            this.peekedString = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (i == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (i != 11) {
            throw new JsonDataException("Expected a double but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double parseDouble = Double.parseDouble(this.peekedString);
            if (this.lenient || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseDouble;
            }
            throw new JsonEncodingException("JSON forbids NaN and infinities: " + parseDouble + " at path " + getPath());
        } catch (NumberFormatException unused) {
            throw new JsonDataException("Expected a double but was " + this.peekedString + " at path " + getPath());
        }
    }

    private String nextQuotedValue(ByteString byteString) throws IOException {
        StringBuilder sb = null;
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(indexOfElement) == 92) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer.readUtf8(indexOfElement));
                this.buffer.readByte();
                sb.append(readEscapeCharacter());
            } else if (sb == null) {
                String readUtf8 = this.buffer.readUtf8(indexOfElement);
                this.buffer.readByte();
                return readUtf8;
            } else {
                sb.append(this.buffer.readUtf8(indexOfElement));
                this.buffer.readByte();
                return sb.toString();
            }
        }
    }

    private String nextUnquotedValue() throws IOException {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return indexOfElement != -1 ? this.buffer.readUtf8(indexOfElement) : this.buffer.readUtf8();
    }

    private void skipQuotedValue(ByteString byteString) throws IOException {
        while (true) {
            long indexOfElement = this.source.indexOfElement(byteString);
            if (indexOfElement == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(indexOfElement) == 92) {
                this.buffer.skip(indexOfElement + 1);
                readEscapeCharacter();
            } else {
                this.buffer.skip(indexOfElement + 1);
                return;
            }
        }
    }

    private void skipUnquotedValue() throws IOException {
        long indexOfElement = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer2 = this.buffer;
        if (indexOfElement == -1) {
            indexOfElement = buffer2.size();
        }
        buffer2.skip(indexOfElement);
    }

    public int nextInt() throws IOException {
        String str;
        int i = this.peeked;
        if (i == 0) {
            i = doPeek();
        }
        if (i == 16) {
            long j = this.peekedLong;
            int i2 = (int) j;
            if (j == ((long) i2)) {
                this.peeked = 0;
                int[] iArr = this.pathIndices;
                int i3 = this.stackSize - 1;
                iArr[i3] = iArr[i3] + 1;
                return i2;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedLong + " at path " + getPath());
        }
        if (i == 17) {
            this.peekedString = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else if (i == 9 || i == 8) {
            if (i == 9) {
                str = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else {
                str = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
            }
            this.peekedString = str;
            try {
                int parseInt = Integer.parseInt(str);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return parseInt;
            } catch (NumberFormatException unused) {
            }
        } else if (i != 11) {
            throw new JsonDataException("Expected an int but was " + peek() + " at path " + getPath());
        }
        this.peeked = 11;
        try {
            double parseDouble = Double.parseDouble(this.peekedString);
            int i5 = (int) parseDouble;
            if (((double) i5) == parseDouble) {
                this.peekedString = null;
                this.peeked = 0;
                int[] iArr3 = this.pathIndices;
                int i6 = this.stackSize - 1;
                iArr3[i6] = iArr3[i6] + 1;
                return i5;
            }
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        } catch (NumberFormatException unused2) {
            throw new JsonDataException("Expected an int but was " + this.peekedString + " at path " + getPath());
        }
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.scopes[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    public void skipValue() throws IOException {
        if (!this.failOnUnknown) {
            int i = 0;
            do {
                int i2 = this.peeked;
                if (i2 == 0) {
                    i2 = doPeek();
                }
                if (i2 == 3) {
                    pushScope(1);
                } else if (i2 == 1) {
                    pushScope(3);
                } else {
                    if (i2 == 4) {
                        i--;
                        if (i >= 0) {
                            this.stackSize--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i2 == 2) {
                        i--;
                        if (i >= 0) {
                            this.stackSize--;
                        } else {
                            throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                        }
                    } else if (i2 == 14 || i2 == 10) {
                        skipUnquotedValue();
                    } else if (i2 == 9 || i2 == 13) {
                        skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
                    } else if (i2 == 8 || i2 == 12) {
                        skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
                    } else if (i2 == 17) {
                        this.buffer.skip((long) this.peekedNumberLength);
                    } else if (i2 == 18) {
                        throw new JsonDataException("Expected a value but was " + peek() + " at path " + getPath());
                    }
                    this.peeked = 0;
                }
                i++;
                this.peeked = 0;
            } while (i != 0);
            int[] iArr = this.pathIndices;
            int i3 = this.stackSize - 1;
            iArr[i3] = iArr[i3] + 1;
            this.pathNames[this.stackSize - 1] = "null";
            return;
        }
        throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        r6.buffer.skip((long) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        if (r2 != 47) goto L_0x0072;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        if (r6.source.request(2) != false) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003a, code lost:
        checkLenient();
        r3 = r6.buffer.getByte(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0047, code lost:
        if (r3 == 42) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r3 == 47) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004c, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
        skipToEndOfLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005a, code lost:
        r6.buffer.readByte();
        r6.buffer.readByte();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0068, code lost:
        if (skipToEndOfBlockComment() == false) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0071, code lost:
        throw syntaxError("Unterminated comment");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0074, code lost:
        if (r2 != 35) goto L_0x007d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0076, code lost:
        checkLenient();
        skipToEndOfLine();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x007d, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int nextNonWhitespace(boolean r7) throws java.io.IOException {
        /*
            r6 = this;
            r0 = 0
        L_0x0001:
            r1 = r0
        L_0x0002:
            okio.BufferedSource r2 = r6.source
            int r3 = r1 + 1
            long r4 = (long) r3
            boolean r2 = r2.request(r4)
            if (r2 == 0) goto L_0x0080
            okio.Buffer r2 = r6.buffer
            long r4 = (long) r1
            byte r2 = r2.getByte(r4)
            r4 = 10
            if (r2 == r4) goto L_0x007e
            r4 = 32
            if (r2 == r4) goto L_0x007e
            r4 = 13
            if (r2 == r4) goto L_0x007e
            r4 = 9
            if (r2 != r4) goto L_0x0025
            goto L_0x007e
        L_0x0025:
            okio.Buffer r3 = r6.buffer
            long r4 = (long) r1
            r3.skip(r4)
            r1 = 47
            if (r2 != r1) goto L_0x0072
            okio.BufferedSource r3 = r6.source
            r4 = 2
            boolean r3 = r3.request(r4)
            if (r3 != 0) goto L_0x003a
            return r2
        L_0x003a:
            r6.checkLenient()
            okio.Buffer r3 = r6.buffer
            r4 = 1
            byte r3 = r3.getByte(r4)
            r4 = 42
            if (r3 == r4) goto L_0x005a
            if (r3 == r1) goto L_0x004c
            return r2
        L_0x004c:
            okio.Buffer r1 = r6.buffer
            r1.readByte()
            okio.Buffer r1 = r6.buffer
            r1.readByte()
            r6.skipToEndOfLine()
            goto L_0x0001
        L_0x005a:
            okio.Buffer r1 = r6.buffer
            r1.readByte()
            okio.Buffer r1 = r6.buffer
            r1.readByte()
            boolean r1 = r6.skipToEndOfBlockComment()
            if (r1 == 0) goto L_0x006b
            goto L_0x0001
        L_0x006b:
            java.lang.String r7 = "Unterminated comment"
            com.airbnb.lottie.parser.moshi.JsonEncodingException r7 = r6.syntaxError(r7)
            throw r7
        L_0x0072:
            r1 = 35
            if (r2 != r1) goto L_0x007d
            r6.checkLenient()
            r6.skipToEndOfLine()
            goto L_0x0001
        L_0x007d:
            return r2
        L_0x007e:
            r1 = r3
            goto L_0x0002
        L_0x0080:
            if (r7 != 0) goto L_0x0084
            r7 = -1
            return r7
        L_0x0084:
            java.io.EOFException r7 = new java.io.EOFException
            java.lang.String r0 = "End of input"
            r7.<init>(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.lottie.parser.moshi.JsonUtf8Reader.nextNonWhitespace(boolean):int");
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        long indexOfElement = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        Buffer buffer2 = this.buffer;
        buffer2.skip(indexOfElement != -1 ? indexOfElement + 1 : buffer2.size());
    }

    private boolean skipToEndOfBlockComment() throws IOException {
        BufferedSource bufferedSource = this.source;
        ByteString byteString = CLOSING_BLOCK_COMMENT;
        long indexOf = bufferedSource.indexOf(byteString);
        boolean z = indexOf != -1;
        Buffer buffer2 = this.buffer;
        buffer2.skip(z ? indexOf + ((long) byteString.size()) : buffer2.size());
        return z;
    }

    public String toString() {
        return "JsonReader(" + this.source + ")";
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        if (this.source.request(1)) {
            byte readByte = this.buffer.readByte();
            if (readByte == 10 || readByte == 34 || readByte == 39 || readByte == 47 || readByte == 92) {
                return (char) readByte;
            }
            if (readByte == 98) {
                return 8;
            }
            if (readByte == 102) {
                return 12;
            }
            if (readByte == 110) {
                return 10;
            }
            if (readByte == 114) {
                return 13;
            }
            if (readByte == 116) {
                return 9;
            }
            if (readByte != 117) {
                if (this.lenient) {
                    return (char) readByte;
                }
                throw syntaxError("Invalid escape sequence: \\" + ((char) readByte));
            } else if (this.source.request(4)) {
                char c2 = 0;
                for (int i2 = 0; i2 < 4; i2++) {
                    byte b2 = this.buffer.getByte((long) i2);
                    char c3 = (char) (c2 << 4);
                    if (b2 >= 48 && b2 <= 57) {
                        i = b2 - 48;
                    } else if (b2 >= 97 && b2 <= 102) {
                        i = b2 - 87;
                    } else if (b2 < 65 || b2 > 70) {
                        throw syntaxError("\\u" + this.buffer.readUtf8(4));
                    } else {
                        i = b2 - 55;
                    }
                    c2 = (char) (c3 + i);
                }
                this.buffer.skip(4);
                return c2;
            } else {
                throw new EOFException("Unterminated escape sequence at path " + getPath());
            }
        } else {
            throw syntaxError("Unterminated escape sequence");
        }
    }
}
