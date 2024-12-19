package com.alibaba.fastjson.parser;

import androidx.constraintlayout.widget.ConstraintLayout;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.util.IOUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.dvbsi.AppInfoTableDecoder;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import java.io.Closeable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.text.Typography;

public abstract class JSONLexerBase implements JSONLexer, Closeable {
    protected static final int INT_MULTMIN_RADIX_TEN = -214748364;
    protected static final long MULTMIN_RADIX_TEN = -922337203685477580L;
    private static final ThreadLocal<char[]> SBUF_LOCAL = new ThreadLocal<>();
    protected static final int[] digits = new int[103];
    protected static final char[] typeFieldName = ("\"" + JSON.DEFAULT_TYPE_KEY + "\":\"").toCharArray();
    protected int bp;
    protected Calendar calendar = null;
    protected char ch;
    protected int eofPos;
    protected int features;
    protected boolean hasSpecial;
    protected Locale locale = JSON.defaultLocale;
    public int matchStat = 0;
    protected int nanos = 0;
    protected int np;
    protected int pos;
    protected char[] sbuf;
    protected int sp;
    protected String stringDefaultValue = null;
    protected TimeZone timeZone = JSON.defaultTimeZone;
    protected int token;

    public static boolean isWhitespace(char c2) {
        return c2 <= ' ' && (c2 == ' ' || c2 == 10 || c2 == 13 || c2 == 9 || c2 == 12 || c2 == 8);
    }

    public abstract String addSymbol(int i, int i2, int i3, SymbolTable symbolTable);

    /* access modifiers changed from: protected */
    public abstract void arrayCopy(int i, char[] cArr, int i2, int i3);

    public abstract byte[] bytesValue();

    /* access modifiers changed from: protected */
    public abstract boolean charArrayCompare(char[] cArr);

    public abstract char charAt(int i);

    /* access modifiers changed from: protected */
    public abstract void copyTo(int i, int i2, char[] cArr);

    public abstract BigDecimal decimalValue();

    public abstract int indexOf(char c2, int i);

    public abstract boolean isEOF();

    public abstract char next();

    public abstract String numberString();

    public String scanTypeName(SymbolTable symbolTable) {
        return null;
    }

    public abstract String stringVal();

    public abstract String subString(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract char[] sub_chars(int i, int i2);

    /* access modifiers changed from: protected */
    public void lexError(String str, Object... objArr) {
        this.token = 1;
    }

    static {
        for (int i = 48; i <= 57; i++) {
            digits[i] = i - 48;
        }
        for (int i2 = 97; i2 <= 102; i2++) {
            digits[i2] = i2 - 87;
        }
        for (int i3 = 65; i3 <= 70; i3++) {
            digits[i3] = i3 - 55;
        }
    }

    public JSONLexerBase(int i) {
        this.features = i;
        if ((i & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
        char[] cArr = SBUF_LOCAL.get();
        this.sbuf = cArr;
        if (cArr == null) {
            this.sbuf = new char[512];
        }
    }

    public final int matchStat() {
        return this.matchStat;
    }

    public void setToken(int i) {
        this.token = i;
    }

    public final void nextToken() {
        this.sp = 0;
        while (true) {
            this.pos = this.bp;
            char c2 = this.ch;
            if (c2 == '/') {
                skipComment();
            } else if (c2 == '\"') {
                scanString();
                return;
            } else if (c2 == ',') {
                next();
                this.token = 16;
                return;
            } else if (c2 >= '0' && c2 <= '9') {
                scanNumber();
                return;
            } else if (c2 == '-') {
                scanNumber();
                return;
            } else {
                switch (c2) {
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        next();
                        break;
                    case '\'':
                        if (isEnabled(Feature.AllowSingleQuotes)) {
                            scanStringSingleQuote();
                            return;
                        }
                        throw new JSONException("Feature.AllowSingleQuotes is false");
                    case '(':
                        next();
                        this.token = 10;
                        return;
                    case ')':
                        next();
                        this.token = 11;
                        return;
                    case '+':
                        next();
                        scanNumber();
                        return;
                    case '.':
                        next();
                        this.token = 25;
                        return;
                    case Opcodes.ASTORE:
                        next();
                        this.token = 17;
                        return;
                    case ';':
                        next();
                        this.token = 24;
                        return;
                    case 'N':
                    case 'S':
                    case 'T':
                    case ModuleDescriptor.MODULE_VERSION /*117*/:
                        scanIdent();
                        return;
                    case '[':
                        next();
                        this.token = 14;
                        return;
                    case ']':
                        next();
                        this.token = 15;
                        return;
                    case 'f':
                        scanFalse();
                        return;
                    case 'n':
                        scanNullOrNew();
                        return;
                    case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /*116*/:
                        scanTrue();
                        return;
                    case 'x':
                        scanHex();
                        return;
                    case '{':
                        next();
                        this.token = 12;
                        return;
                    case '}':
                        next();
                        this.token = 13;
                        return;
                    default:
                        if (!isEOF()) {
                            char c3 = this.ch;
                            if (c3 <= 31 || c3 == 127) {
                                next();
                                break;
                            } else {
                                lexError("illegal.char", String.valueOf(c3));
                                next();
                                return;
                            }
                        } else if (this.token != 20) {
                            this.token = 20;
                            int i = this.bp;
                            this.pos = i;
                            this.eofPos = i;
                            return;
                        } else {
                            throw new JSONException("EOF error");
                        }
                }
            }
        }
    }

    public final void nextToken(int i) {
        this.sp = 0;
        while (true) {
            if (i == 2) {
                char c2 = this.ch;
                if (c2 >= '0' && c2 <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (c2 == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (c2 == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c2 == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 4) {
                char c3 = this.ch;
                if (c3 == '\"') {
                    this.pos = this.bp;
                    scanString();
                    return;
                } else if (c3 >= '0' && c3 <= '9') {
                    this.pos = this.bp;
                    scanNumber();
                    return;
                } else if (c3 == '[') {
                    this.token = 14;
                    next();
                    return;
                } else if (c3 == '{') {
                    this.token = 12;
                    next();
                    return;
                }
            } else if (i == 12) {
                char c4 = this.ch;
                if (c4 == '{') {
                    this.token = 12;
                    next();
                    return;
                } else if (c4 == '[') {
                    this.token = 14;
                    next();
                    return;
                }
            } else if (i != 18) {
                if (i != 20) {
                    switch (i) {
                        case 14:
                            char c5 = this.ch;
                            if (c5 == '[') {
                                this.token = 14;
                                next();
                                return;
                            } else if (c5 == '{') {
                                this.token = 12;
                                next();
                                return;
                            }
                            break;
                        case 15:
                            if (this.ch == ']') {
                                this.token = 15;
                                next();
                                return;
                            }
                            break;
                        case 16:
                            char c6 = this.ch;
                            if (c6 == ',') {
                                this.token = 16;
                                next();
                                return;
                            } else if (c6 == '}') {
                                this.token = 13;
                                next();
                                return;
                            } else if (c6 == ']') {
                                this.token = 15;
                                next();
                                return;
                            } else if (c6 == 26) {
                                this.token = 20;
                                return;
                            } else if (c6 == 'n') {
                                scanNullOrNew(false);
                                return;
                            }
                            break;
                    }
                }
                if (this.ch == 26) {
                    this.token = 20;
                    return;
                }
            } else {
                nextIdent();
                return;
            }
            char c7 = this.ch;
            if (c7 == ' ' || c7 == 10 || c7 == 13 || c7 == 9 || c7 == 12 || c7 == 8) {
                next();
            } else {
                nextToken();
                return;
            }
        }
    }

    public final void nextIdent() {
        while (isWhitespace(this.ch)) {
            next();
        }
        char c2 = this.ch;
        if (c2 == '_' || c2 == '$' || Character.isLetter(c2)) {
            scanIdent();
        } else {
            nextToken();
        }
    }

    public final void nextTokenWithColon() {
        nextTokenWithChar(':');
    }

    public final void nextTokenWithChar(char c2) {
        this.sp = 0;
        while (true) {
            char c3 = this.ch;
            if (c3 == c2) {
                next();
                nextToken();
                return;
            } else if (c3 == ' ' || c3 == 10 || c3 == 13 || c3 == 9 || c3 == 12 || c3 == 8) {
                next();
            } else {
                throw new JSONException("not match " + c2 + " - " + this.ch + ", info : " + info());
            }
        }
    }

    public final int token() {
        return this.token;
    }

    public final String tokenName() {
        return JSONToken.name(this.token);
    }

    public final int pos() {
        return this.pos;
    }

    public final String stringDefaultValue() {
        return this.stringDefaultValue;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bf  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Number integerValue() throws java.lang.NumberFormatException {
        /*
            r18 = this;
            r0 = r18
            int r1 = r0.np
            r2 = -1
            r3 = 0
            if (r1 != r2) goto L_0x000a
            r0.np = r3
        L_0x000a:
            int r1 = r0.np
            int r2 = r0.sp
            int r2 = r2 + r1
            int r4 = r2 + -1
            char r4 = r0.charAt(r4)
            r5 = 83
            r6 = 76
            r7 = 66
            if (r4 == r7) goto L_0x002c
            if (r4 == r6) goto L_0x0028
            if (r4 == r5) goto L_0x0024
            r4 = 32
            goto L_0x002f
        L_0x0024:
            int r2 = r2 + -1
            r4 = r5
            goto L_0x002f
        L_0x0028:
            int r2 = r2 + -1
            r4 = r6
            goto L_0x002f
        L_0x002c:
            int r2 = r2 + -1
            r4 = r7
        L_0x002f:
            int r8 = r0.np
            char r8 = r0.charAt(r8)
            r9 = 45
            r10 = 1
            if (r8 != r9) goto L_0x0040
            int r1 = r1 + 1
            r8 = -9223372036854775808
            r3 = r10
            goto L_0x0045
        L_0x0040:
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0045:
            if (r1 >= r2) goto L_0x0053
            int r11 = r1 + 1
            char r1 = r0.charAt(r1)
            int r1 = r1 + -48
            int r1 = -r1
            long r12 = (long) r1
        L_0x0051:
            r1 = r11
            goto L_0x0055
        L_0x0053:
            r12 = 0
        L_0x0055:
            if (r1 >= r2) goto L_0x0088
            int r11 = r1 + 1
            char r1 = r0.charAt(r1)
            int r1 = r1 + -48
            r14 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r14 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            if (r14 >= 0) goto L_0x0072
            java.math.BigInteger r1 = new java.math.BigInteger
            java.lang.String r2 = r18.numberString()
            r1.<init>(r2)
            return r1
        L_0x0072:
            r14 = 10
            long r12 = r12 * r14
            long r14 = (long) r1
            long r16 = r8 + r14
            int r1 = (r12 > r16 ? 1 : (r12 == r16 ? 0 : -1))
            if (r1 >= 0) goto L_0x0086
            java.math.BigInteger r1 = new java.math.BigInteger
            java.lang.String r2 = r18.numberString()
            r1.<init>(r2)
            return r1
        L_0x0086:
            long r12 = r12 - r14
            goto L_0x0051
        L_0x0088:
            if (r3 == 0) goto L_0x00bf
            int r2 = r0.np
            int r2 = r2 + r10
            if (r1 <= r2) goto L_0x00b5
            r1 = -2147483648(0xffffffff80000000, double:NaN)
            int r1 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x00b0
            if (r4 == r6) goto L_0x00b0
            if (r4 != r5) goto L_0x00a1
            int r1 = (int) r12
            short r1 = (short) r1
            java.lang.Short r1 = java.lang.Short.valueOf(r1)
            return r1
        L_0x00a1:
            if (r4 != r7) goto L_0x00aa
            int r1 = (int) r12
            byte r1 = (byte) r1
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)
            return r1
        L_0x00aa:
            int r1 = (int) r12
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            return r1
        L_0x00b0:
            java.lang.Long r1 = java.lang.Long.valueOf(r12)
            return r1
        L_0x00b5:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.String r2 = r18.numberString()
            r1.<init>(r2)
            throw r1
        L_0x00bf:
            long r1 = -r12
            r8 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r3 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r3 > 0) goto L_0x00e1
            if (r4 == r6) goto L_0x00e1
            if (r4 != r5) goto L_0x00d2
            int r1 = (int) r1
            short r1 = (short) r1
            java.lang.Short r1 = java.lang.Short.valueOf(r1)
            return r1
        L_0x00d2:
            if (r4 != r7) goto L_0x00db
            int r1 = (int) r1
            byte r1 = (byte) r1
            java.lang.Byte r1 = java.lang.Byte.valueOf(r1)
            return r1
        L_0x00db:
            int r1 = (int) r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            return r1
        L_0x00e1:
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.integerValue():java.lang.Number");
    }

    public final void nextTokenWithColon(int i) {
        nextTokenWithChar(':');
    }

    public float floatValue() {
        char charAt;
        String numberString = numberString();
        float parseFloat = Float.parseFloat(numberString);
        if ((parseFloat != 0.0f && parseFloat != Float.POSITIVE_INFINITY) || (charAt = numberString.charAt(0)) <= '0' || charAt > '9') {
            return parseFloat;
        }
        throw new JSONException("float overflow : " + numberString);
    }

    public double doubleValue() {
        return Double.parseDouble(numberString());
    }

    public void config(Feature feature, boolean z) {
        int config = Feature.config(this.features, feature, z);
        this.features = config;
        if ((config & Feature.InitStringFieldAsEmpty.mask) != 0) {
            this.stringDefaultValue = "";
        }
    }

    public final boolean isEnabled(Feature feature) {
        return isEnabled(feature.mask);
    }

    public final boolean isEnabled(int i) {
        return (i & this.features) != 0;
    }

    public final boolean isEnabled(int i, int i2) {
        return ((this.features & i2) == 0 && (i & i2) == 0) ? false : true;
    }

    public final char getCurrent() {
        return this.ch;
    }

    /* access modifiers changed from: protected */
    public void skipComment() {
        char c2;
        next();
        char c3 = this.ch;
        if (c3 == '/') {
            do {
                next();
                c2 = this.ch;
                if (c2 == 10) {
                    next();
                    return;
                }
            } while (c2 != 26);
        } else if (c3 == '*') {
            next();
            while (true) {
                char c4 = this.ch;
                if (c4 == 26) {
                    return;
                }
                if (c4 == '*') {
                    next();
                    if (this.ch == '/') {
                        next();
                        return;
                    }
                } else {
                    next();
                }
            }
        } else {
            throw new JSONException("invalid comment");
        }
    }

    public final String scanSymbol(SymbolTable symbolTable) {
        skipWhitespace();
        char c2 = this.ch;
        if (c2 == '\"') {
            return scanSymbol(symbolTable, Typography.quote);
        }
        if (c2 == '\'') {
            if (isEnabled(Feature.AllowSingleQuotes)) {
                return scanSymbol(symbolTable, '\'');
            }
            throw new JSONException("syntax error");
        } else if (c2 == '}') {
            next();
            this.token = 13;
            return null;
        } else if (c2 == ',') {
            next();
            this.token = 16;
            return null;
        } else if (c2 == 26) {
            this.token = 20;
            return null;
        } else if (isEnabled(Feature.AllowUnQuotedFieldNames)) {
            return scanSymbolUnQuoted(symbolTable);
        } else {
            throw new JSONException("syntax error");
        }
    }

    public final String scanSymbol(SymbolTable symbolTable, char c2) {
        String str;
        this.np = this.bp;
        this.sp = 0;
        boolean z = false;
        int i = 0;
        while (true) {
            char next = next();
            if (next == c2) {
                this.token = 4;
                if (!z) {
                    int i2 = this.np;
                    str = addSymbol(i2 == -1 ? 0 : i2 + 1, this.sp, i, symbolTable);
                } else {
                    str = symbolTable.addSymbol(this.sbuf, 0, this.sp, i);
                }
                this.sp = 0;
                next();
                return str;
            } else if (next == 26) {
                throw new JSONException("unclosed.str");
            } else if (next == '\\') {
                if (!z) {
                    int i3 = this.sp;
                    char[] cArr = this.sbuf;
                    if (i3 >= cArr.length) {
                        int length = cArr.length * 2;
                        if (i3 <= length) {
                            i3 = length;
                        }
                        char[] cArr2 = new char[i3];
                        System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                        this.sbuf = cArr2;
                    }
                    arrayCopy(this.np + 1, this.sbuf, 0, this.sp);
                    z = true;
                }
                char next2 = next();
                if (next2 == '\"') {
                    i = (i * 31) + 34;
                    putChar(Typography.quote);
                } else if (next2 != '\'') {
                    if (next2 != 'F') {
                        if (next2 == '\\') {
                            i = (i * 31) + 92;
                            putChar('\\');
                        } else if (next2 == 'b') {
                            i = (i * 31) + 8;
                            putChar(8);
                        } else if (next2 != 'f') {
                            if (next2 == 'n') {
                                i = (i * 31) + 10;
                                putChar(10);
                            } else if (next2 == 'r') {
                                i = (i * 31) + 13;
                                putChar(13);
                            } else if (next2 != 'x') {
                                switch (next2) {
                                    case '/':
                                        i = (i * 31) + 47;
                                        putChar('/');
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                                        i = (i * 31) + next2;
                                        putChar(0);
                                        break;
                                    case '1':
                                        i = (i * 31) + next2;
                                        putChar(1);
                                        break;
                                    case '2':
                                        i = (i * 31) + next2;
                                        putChar(2);
                                        break;
                                    case '3':
                                        i = (i * 31) + next2;
                                        putChar(3);
                                        break;
                                    case '4':
                                        i = (i * 31) + next2;
                                        putChar(4);
                                        break;
                                    case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                                        i = (i * 31) + next2;
                                        putChar(5);
                                        break;
                                    case '6':
                                        i = (i * 31) + next2;
                                        putChar(6);
                                        break;
                                    case '7':
                                        i = (i * 31) + next2;
                                        putChar(7);
                                        break;
                                    default:
                                        switch (next2) {
                                            case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /*116*/:
                                                i = (i * 31) + 9;
                                                putChar(9);
                                                break;
                                            case ModuleDescriptor.MODULE_VERSION /*117*/:
                                                int parseInt = Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16);
                                                i = (i * 31) + parseInt;
                                                putChar((char) parseInt);
                                                break;
                                            case 'v':
                                                i = (i * 31) + 11;
                                                putChar(11);
                                                break;
                                            default:
                                                this.ch = next2;
                                                throw new JSONException("unclosed.str.lit");
                                        }
                                }
                            } else {
                                char next3 = next();
                                this.ch = next3;
                                char next4 = next();
                                this.ch = next4;
                                int[] iArr = digits;
                                char c3 = (char) ((iArr[next3] * 16) + iArr[next4]);
                                i = (i * 31) + c3;
                                putChar(c3);
                            }
                        }
                    }
                    i = (i * 31) + 12;
                    putChar(12);
                } else {
                    i = (i * 31) + 39;
                    putChar('\'');
                }
            } else {
                i = (i * 31) + next;
                if (!z) {
                    this.sp++;
                } else {
                    int i4 = this.sp;
                    char[] cArr3 = this.sbuf;
                    if (i4 == cArr3.length) {
                        putChar(next);
                    } else {
                        this.sp = i4 + 1;
                        cArr3[i4] = next;
                    }
                }
            }
        }
    }

    public final void resetStringPosition() {
        this.sp = 0;
    }

    public String info() {
        return "";
    }

    public final String scanSymbolUnQuoted(SymbolTable symbolTable) {
        if (this.token == 1 && this.pos == 0 && this.bp == 1) {
            this.bp = 0;
        }
        boolean[] zArr = IOUtils.firstIdentifierFlags;
        int i = this.ch;
        if (i >= zArr.length || zArr[i]) {
            boolean[] zArr2 = IOUtils.identifierFlags;
            this.np = this.bp;
            this.sp = 1;
            while (true) {
                char next = next();
                if (next < zArr2.length && !zArr2[next]) {
                    break;
                }
                i = (i * 31) + next;
                this.sp++;
            }
            this.ch = charAt(this.bp);
            this.token = 18;
            if (this.sp == 4 && i == 3392903 && charAt(this.np) == 'n' && charAt(this.np + 1) == 'u' && charAt(this.np + 2) == 'l' && charAt(this.np + 3) == 'l') {
                return null;
            }
            if (symbolTable == null) {
                return subString(this.np, this.sp);
            }
            return addSymbol(this.np, this.sp, i, symbolTable);
        }
        throw new JSONException("illegal identifier : " + this.ch + info());
    }

    public final void scanString() {
        char next;
        char next2;
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next3 = next();
            if (next3 == '\"') {
                this.token = 4;
                this.ch = next();
                return;
            } else if (next3 != 26) {
                boolean z = true;
                if (next3 == '\\') {
                    if (!this.hasSpecial) {
                        this.hasSpecial = true;
                        int i = this.sp;
                        char[] cArr = this.sbuf;
                        if (i >= cArr.length) {
                            int length = cArr.length * 2;
                            if (i <= length) {
                                i = length;
                            }
                            char[] cArr2 = new char[i];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.sbuf = cArr2;
                        }
                        copyTo(this.np + 1, this.sp, this.sbuf);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        putChar(Typography.quote);
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                putChar('\\');
                            } else if (next4 == 'b') {
                                putChar(8);
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    putChar(10);
                                } else if (next4 == 'r') {
                                    putChar(13);
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            putChar('/');
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                                            putChar(0);
                                            break;
                                        case '1':
                                            putChar(1);
                                            break;
                                        case '2':
                                            putChar(2);
                                            break;
                                        case '3':
                                            putChar(3);
                                            break;
                                        case '4':
                                            putChar(4);
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                                            putChar(5);
                                            break;
                                        case '6':
                                            putChar(6);
                                            break;
                                        case '7':
                                            putChar(7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /*116*/:
                                                    putChar(9);
                                                    break;
                                                case ModuleDescriptor.MODULE_VERSION /*117*/:
                                                    putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    putChar(11);
                                                    break;
                                                default:
                                                    this.ch = next4;
                                                    throw new JSONException("unclosed string : " + next4);
                                            }
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z2 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z = false;
                                    }
                                    if (!z2 || !z) {
                                    } else {
                                        int[] iArr = digits;
                                        putChar((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        putChar(12);
                    } else {
                        putChar('\'');
                    }
                } else if (!this.hasSpecial) {
                    this.sp++;
                } else {
                    int i2 = this.sp;
                    char[] cArr3 = this.sbuf;
                    if (i2 == cArr3.length) {
                        putChar(next3);
                    } else {
                        this.sp = i2 + 1;
                        cArr3[i2] = next3;
                    }
                }
            } else if (!isEOF()) {
                putChar(JSONLexer.EOI);
            } else {
                throw new JSONException("unclosed string : " + next3);
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    public Calendar getCalendar() {
        return this.calendar;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(TimeZone timeZone2) {
        this.timeZone = timeZone2;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale2) {
        this.locale = locale2;
    }

    public final int intValue() {
        boolean z;
        int i;
        int i2 = 0;
        if (this.np == -1) {
            this.np = 0;
        }
        int i3 = this.np;
        int i4 = this.sp + i3;
        if (charAt(i3) == '-') {
            i3++;
            i = Integer.MIN_VALUE;
            z = true;
        } else {
            i = C.RATE_UNSET_INT;
            z = false;
        }
        if (i3 < i4) {
            i2 = -(charAt(i3) - '0');
            i3++;
        }
        while (true) {
            if (i3 >= i4) {
                break;
            }
            int i5 = i3 + 1;
            char charAt = charAt(i3);
            if (charAt == 'L' || charAt == 'S' || charAt == 'B') {
                i3 = i5;
            } else {
                int i6 = charAt - '0';
                if (((long) i2) >= -214748364) {
                    int i7 = i2 * 10;
                    if (i7 >= i + i6) {
                        i2 = i7 - i6;
                        i3 = i5;
                    } else {
                        throw new NumberFormatException(numberString());
                    }
                } else {
                    throw new NumberFormatException(numberString());
                }
            }
        }
        if (!z) {
            return -i2;
        }
        if (i3 > this.np + 1) {
            return i2;
        }
        throw new NumberFormatException(numberString());
    }

    public void close() {
        char[] cArr = this.sbuf;
        if (cArr.length <= 8192) {
            SBUF_LOCAL.set(cArr);
        }
        this.sbuf = null;
    }

    public final boolean isRef() {
        if (this.sp == 4 && charAt(this.np + 1) == '$' && charAt(this.np + 2) == 'r' && charAt(this.np + 3) == 'e' && charAt(this.np + 4) == 'f') {
            return true;
        }
        return false;
    }

    public final int scanType(String str) {
        int i;
        this.matchStat = 0;
        char[] cArr = typeFieldName;
        if (!charArrayCompare(cArr)) {
            return -2;
        }
        int length = this.bp + cArr.length;
        int length2 = str.length();
        for (int i2 = 0; i2 < length2; i2++) {
            if (str.charAt(i2) != charAt(length + i2)) {
                return -1;
            }
        }
        int i3 = length + length2;
        if (charAt(i3) != '\"') {
            return -1;
        }
        int i4 = i3 + 1;
        char charAt = charAt(i4);
        this.ch = charAt;
        if (charAt == ',') {
            int i5 = i3 + 2;
            this.ch = charAt(i5);
            this.bp = i5;
            this.token = 16;
            return 3;
        }
        if (charAt == '}') {
            i4 = i3 + 2;
            char charAt2 = charAt(i4);
            this.ch = charAt2;
            if (charAt2 == ',') {
                this.token = 16;
                i = i3 + 3;
                this.ch = charAt(i);
            } else if (charAt2 == ']') {
                this.token = 15;
                i = i3 + 3;
                this.ch = charAt(i);
            } else if (charAt2 == '}') {
                this.token = 13;
                i = i3 + 3;
                this.ch = charAt(i);
            } else if (charAt2 != 26) {
                return -1;
            } else {
                this.token = 20;
                this.matchStat = 4;
            }
            i4 = i;
            this.matchStat = 4;
        }
        this.bp = i4;
        return this.matchStat;
    }

    public final boolean matchField(char[] cArr) {
        while (!charArrayCompare(cArr)) {
            if (!isWhitespace(this.ch)) {
                return false;
            }
            next();
        }
        int length = this.bp + cArr.length;
        this.bp = length;
        char charAt = charAt(length);
        this.ch = charAt;
        if (charAt == '{') {
            next();
            this.token = 12;
        } else if (charAt == '[') {
            next();
            this.token = 14;
        } else if (charAt == 'S' && charAt(this.bp + 1) == 'e' && charAt(this.bp + 2) == 't' && charAt(this.bp + 3) == '[') {
            int i = this.bp + 3;
            this.bp = i;
            this.ch = charAt(i);
            this.token = 21;
        } else {
            nextToken();
        }
        return true;
    }

    public int matchField(long j) {
        throw new UnsupportedOperationException();
    }

    public boolean seekArrayToItem(int i) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long j, boolean z) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToField(long[] jArr) {
        throw new UnsupportedOperationException();
    }

    public int seekObjectToFieldDeepScan(long j) {
        throw new UnsupportedOperationException();
    }

    public void skipObject() {
        throw new UnsupportedOperationException();
    }

    public void skipObject(boolean z) {
        throw new UnsupportedOperationException();
    }

    public void skipArray() {
        throw new UnsupportedOperationException();
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return stringDefaultValue();
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return stringDefaultValue();
        }
        int indexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
        if (indexOf != -1) {
            int length2 = this.bp + cArr.length + 1;
            String subString = subString(length2, indexOf - length2);
            if (subString.indexOf(92) != -1) {
                while (true) {
                    int i2 = indexOf - 1;
                    int i3 = 0;
                    while (i2 >= 0 && charAt(i2) == '\\') {
                        i3++;
                        i2--;
                    }
                    if (i3 % 2 == 0) {
                        break;
                    }
                    indexOf = indexOf(Typography.quote, indexOf + 1);
                }
                int i4 = this.bp;
                int length3 = indexOf - ((cArr.length + i4) + 1);
                subString = readString(sub_chars(i4 + cArr.length + 1, length3), length3);
            }
            int i5 = this.bp;
            int length4 = i + (indexOf - ((cArr.length + i5) + 1)) + 1;
            int i6 = length4 + 1;
            char charAt = charAt(i5 + length4);
            if (charAt == ',') {
                int i7 = this.bp + i6;
                this.bp = i7;
                this.ch = charAt(i7);
                this.matchStat = 3;
                return subString;
            } else if (charAt == '}') {
                int i8 = length4 + 2;
                char charAt2 = charAt(this.bp + i6);
                if (charAt2 == ',') {
                    this.token = 16;
                    int i9 = this.bp + i8;
                    this.bp = i9;
                    this.ch = charAt(i9);
                } else if (charAt2 == ']') {
                    this.token = 15;
                    int i10 = this.bp + i8;
                    this.bp = i10;
                    this.ch = charAt(i10);
                } else if (charAt2 == '}') {
                    this.token = 13;
                    int i11 = this.bp + i8;
                    this.bp = i11;
                    this.ch = charAt(i11);
                } else if (charAt2 == 26) {
                    this.token = 20;
                    this.bp += length4 + 1;
                    this.ch = JSONLexer.EOI;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
                this.matchStat = 4;
                return subString;
            } else {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        } else {
            throw new JSONException("unclosed str");
        }
    }

    public String scanString(char c2) {
        this.matchStat = 0;
        char charAt = charAt(this.bp);
        if (charAt != 'n') {
            int i = 1;
            while (charAt != '\"') {
                if (isWhitespace(charAt)) {
                    charAt = charAt(this.bp + i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
            }
            int i2 = this.bp + i;
            int indexOf = indexOf(Typography.quote, i2);
            if (indexOf != -1) {
                String subString = subString(this.bp + i, indexOf - i2);
                if (subString.indexOf(92) != -1) {
                    while (true) {
                        int i3 = indexOf - 1;
                        int i4 = 0;
                        while (i3 >= 0 && charAt(i3) == '\\') {
                            i4++;
                            i3--;
                        }
                        if (i4 % 2 == 0) {
                            break;
                        }
                        indexOf = indexOf(Typography.quote, indexOf + 1);
                    }
                    int i5 = indexOf - i2;
                    subString = readString(sub_chars(this.bp + 1, i5), i5);
                }
                int i6 = i + (indexOf - i2) + 1;
                int i7 = i6 + 1;
                char charAt2 = charAt(this.bp + i6);
                while (charAt2 != c2) {
                    if (isWhitespace(charAt2)) {
                        charAt2 = charAt(this.bp + i7);
                        i7++;
                    } else {
                        if (charAt2 == ']') {
                            int i8 = this.bp + i7;
                            this.bp = i8;
                            this.ch = charAt(i8);
                            this.matchStat = -1;
                        }
                        return subString;
                    }
                }
                int i9 = this.bp + i7;
                this.bp = i9;
                this.ch = charAt(i9);
                this.matchStat = 3;
                this.token = 16;
                return subString;
            }
            throw new JSONException("unclosed str");
        } else if (charAt(this.bp + 1) != 'u' || charAt(this.bp + 2) != 'l' || charAt(this.bp + 3) != 'l') {
            this.matchStat = -1;
            return null;
        } else if (charAt(this.bp + 4) == c2) {
            int i10 = this.bp + 5;
            this.bp = i10;
            this.ch = charAt(i10);
            this.matchStat = 3;
            return null;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char charAt = charAt(this.bp + i);
            if (charAt == '\"') {
                int i3 = i + 2;
                char charAt2 = charAt(this.bp + i2);
                if (charAt2 == ',') {
                    int i4 = this.bp + i3;
                    this.bp = i4;
                    this.ch = charAt(i4);
                    this.matchStat = 3;
                    return j;
                } else if (charAt2 == '}') {
                    int i5 = i + 3;
                    char charAt3 = charAt(this.bp + i3);
                    if (charAt3 == ',') {
                        this.token = 16;
                        int i6 = this.bp + i5;
                        this.bp = i6;
                        this.ch = charAt(i6);
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        int i7 = this.bp + i5;
                        this.bp = i7;
                        this.ch = charAt(i7);
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        int i8 = this.bp + i5;
                        this.bp = i8;
                        this.ch = charAt(i8);
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i + 2;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return j;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            } else {
                j = (j ^ ((long) charAt)) * 1099511628211L;
                if (charAt == '\\') {
                    this.matchStat = -1;
                    return 0;
                }
                i = i2;
            }
        }
    }

    public long scanEnumSymbol(char[] cArr) {
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i = length + 1;
        if (charAt(this.bp + length) != '\"') {
            this.matchStat = -1;
            return 0;
        }
        long j = -3750763034362895579L;
        while (true) {
            int i2 = i + 1;
            char charAt = charAt(this.bp + i);
            if (charAt == '\"') {
                int i3 = i + 2;
                char charAt2 = charAt(this.bp + i2);
                if (charAt2 == ',') {
                    int i4 = this.bp + i3;
                    this.bp = i4;
                    this.ch = charAt(i4);
                    this.matchStat = 3;
                    return j;
                } else if (charAt2 == '}') {
                    int i5 = i + 3;
                    char charAt3 = charAt(this.bp + i3);
                    if (charAt3 == ',') {
                        this.token = 16;
                        int i6 = this.bp + i5;
                        this.bp = i6;
                        this.ch = charAt(i6);
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        int i7 = this.bp + i5;
                        this.bp = i7;
                        this.ch = charAt(i7);
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        int i8 = this.bp + i5;
                        this.bp = i8;
                        this.ch = charAt(i8);
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i + 2;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                    this.matchStat = 4;
                    return j;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            } else {
                j = (j ^ ((long) ((charAt < 'A' || charAt > 'Z') ? charAt : charAt + ' '))) * 1099511628211L;
                if (charAt == '\\') {
                    this.matchStat = -1;
                    return 0;
                }
                i = i2;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Enum<?> scanEnum(java.lang.Class<?> r1, com.alibaba.fastjson.parser.SymbolTable r2, char r3) {
        /*
            r0 = this;
            java.lang.String r2 = r0.scanSymbolWithSeperator(r2, r3)
            if (r2 != 0) goto L_0x0008
            r1 = 0
            return r1
        L_0x0008:
            java.lang.Enum r1 = java.lang.Enum.valueOf(r1, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanEnum(java.lang.Class, com.alibaba.fastjson.parser.SymbolTable, char):java.lang.Enum");
    }

    public String scanSymbolWithSeperator(SymbolTable symbolTable, char c2) {
        int i = 0;
        this.matchStat = 0;
        char charAt = charAt(this.bp);
        if (charAt == 'n') {
            if (charAt(this.bp + 1) != 'u' || charAt(this.bp + 2) != 'l' || charAt(this.bp + 3) != 'l') {
                this.matchStat = -1;
                return null;
            } else if (charAt(this.bp + 4) == c2) {
                int i2 = this.bp + 5;
                this.bp = i2;
                this.ch = charAt(i2);
                this.matchStat = 3;
                return null;
            } else {
                this.matchStat = -1;
                return null;
            }
        } else if (charAt != '\"') {
            this.matchStat = -1;
            return null;
        } else {
            int i3 = 1;
            while (true) {
                int i4 = i3 + 1;
                char charAt2 = charAt(this.bp + i3);
                if (charAt2 == '\"') {
                    int i5 = this.bp;
                    int i6 = i5 + 1;
                    String addSymbol = addSymbol(i6, ((i5 + i4) - i6) - 1, i, symbolTable);
                    int i7 = i3 + 2;
                    char charAt3 = charAt(this.bp + i4);
                    while (charAt3 != c2) {
                        if (isWhitespace(charAt3)) {
                            charAt3 = charAt(this.bp + i7);
                            i7++;
                        } else {
                            this.matchStat = -1;
                            return addSymbol;
                        }
                    }
                    int i8 = this.bp + i7;
                    this.bp = i8;
                    this.ch = charAt(i8);
                    this.matchStat = 3;
                    return addSymbol;
                }
                i = (i * 31) + charAt2;
                if (charAt2 == '\\') {
                    this.matchStat = -1;
                    return null;
                }
                i3 = i4;
            }
        }
    }

    public Collection<String> newCollectionByType(Class<?> cls) {
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(LinkedList.class)) {
            return new LinkedList();
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00fc, code lost:
        if (r13 != ',') goto L_0x010d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00fe, code lost:
        r13 = r12.bp + r0;
        r12.bp = r13;
        r12.ch = charAt(r13);
        r12.matchStat = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x010c, code lost:
        return r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x010f, code lost:
        if (r13 != '}') goto L_0x0166;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0111, code lost:
        r6 = r0 + 1;
        r13 = charAt(r12.bp + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x011a, code lost:
        if (r13 != ',') goto L_0x012c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x011c, code lost:
        r12.token = 16;
        r13 = r12.bp + r6;
        r12.bp = r13;
        r12.ch = charAt(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x012c, code lost:
        if (r13 != ']') goto L_0x013e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x012e, code lost:
        r12.token = 15;
        r13 = r12.bp + r6;
        r12.bp = r13;
        r12.ch = charAt(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x013e, code lost:
        if (r13 != '}') goto L_0x0150;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0140, code lost:
        r12.token = 13;
        r13 = r12.bp + r6;
        r12.bp = r13;
        r12.ch = charAt(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0152, code lost:
        if (r13 != 26) goto L_0x0163;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0154, code lost:
        r12.bp += r0;
        r12.token = 20;
        r12.ch = com.alibaba.fastjson.parser.JSONLexer.EOI;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x015f, code lost:
        r12.matchStat = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0162, code lost:
        return r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0163, code lost:
        r12.matchStat = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0165, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0166, code lost:
        r12.matchStat = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0168, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0169  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Collection<java.lang.String> scanFieldStringArray(char[] r13, java.lang.Class<?> r14) {
        /*
            r12 = this;
            r0 = 0
            r12.matchStat = r0
            boolean r1 = r12.charArrayCompare(r13)
            r2 = 0
            if (r1 != 0) goto L_0x000e
            r13 = -2
            r12.matchStat = r13
            return r2
        L_0x000e:
            java.util.Collection r14 = r12.newCollectionByType(r14)
            int r13 = r13.length
            int r1 = r12.bp
            int r3 = r13 + 1
            int r1 = r1 + r13
            char r1 = r12.charAt(r1)
            r4 = 91
            r5 = -1
            if (r1 == r4) goto L_0x0024
            r12.matchStat = r5
            return r2
        L_0x0024:
            int r1 = r12.bp
            int r13 = r13 + 2
            int r1 = r1 + r3
            char r1 = r12.charAt(r1)
        L_0x002d:
            r3 = 44
            r4 = 93
            r6 = 34
            if (r1 != r6) goto L_0x0095
            int r1 = r12.bp
            int r1 = r1 + r13
            int r1 = r12.indexOf(r6, r1)
            if (r1 == r5) goto L_0x008d
            int r7 = r12.bp
            int r7 = r7 + r13
            int r8 = r1 - r7
            java.lang.String r7 = r12.subString(r7, r8)
            r8 = 92
            int r9 = r7.indexOf(r8)
            if (r9 == r5) goto L_0x007a
        L_0x004f:
            int r7 = r1 + -1
            r9 = r0
        L_0x0052:
            if (r7 < 0) goto L_0x005f
            char r10 = r12.charAt(r7)
            if (r10 != r8) goto L_0x005f
            int r9 = r9 + 1
            int r7 = r7 + -1
            goto L_0x0052
        L_0x005f:
            int r9 = r9 % 2
            if (r9 != 0) goto L_0x0073
            int r6 = r12.bp
            int r7 = r6 + r13
            int r7 = r1 - r7
            int r6 = r6 + r13
            char[] r6 = r12.sub_chars(r6, r7)
            java.lang.String r7 = readString(r6, r7)
            goto L_0x007a
        L_0x0073:
            int r1 = r1 + 1
            int r1 = r12.indexOf(r6, r1)
            goto L_0x004f
        L_0x007a:
            int r6 = r12.bp
            int r8 = r6 + r13
            int r1 = r1 - r8
            int r1 = r1 + 1
            int r13 = r13 + r1
            int r1 = r13 + 1
            int r6 = r6 + r13
            char r13 = r12.charAt(r6)
            r14.add(r7)
            goto L_0x00cd
        L_0x008d:
            com.alibaba.fastjson.JSONException r13 = new com.alibaba.fastjson.JSONException
            java.lang.String r14 = "unclosed str"
            r13.<init>(r14)
            throw r13
        L_0x0095:
            r6 = 110(0x6e, float:1.54E-43)
            if (r1 != r6) goto L_0x00ea
            int r6 = r12.bp
            int r6 = r6 + r13
            char r6 = r12.charAt(r6)
            r7 = 117(0x75, float:1.64E-43)
            if (r6 != r7) goto L_0x00ea
            int r6 = r12.bp
            int r6 = r6 + r13
            int r6 = r6 + 1
            char r6 = r12.charAt(r6)
            r7 = 108(0x6c, float:1.51E-43)
            if (r6 != r7) goto L_0x00ea
            int r6 = r12.bp
            int r6 = r6 + r13
            int r6 = r6 + 2
            char r6 = r12.charAt(r6)
            if (r6 != r7) goto L_0x00ea
            int r1 = r13 + 3
            int r6 = r12.bp
            int r13 = r13 + 4
            int r6 = r6 + r1
            char r1 = r12.charAt(r6)
            r14.add(r2)
            r11 = r1
            r1 = r13
            r13 = r11
        L_0x00cd:
            if (r13 != r3) goto L_0x00db
            int r13 = r12.bp
            int r3 = r1 + 1
            int r13 = r13 + r1
            char r1 = r12.charAt(r13)
            r13 = r3
            goto L_0x002d
        L_0x00db:
            if (r13 != r4) goto L_0x00e7
            int r13 = r12.bp
            int r0 = r1 + 1
            int r13 = r13 + r1
            char r13 = r12.charAt(r13)
            goto L_0x00fc
        L_0x00e7:
            r12.matchStat = r5
            return r2
        L_0x00ea:
            if (r1 != r4) goto L_0x0169
            int r0 = r14.size()
            if (r0 != 0) goto L_0x0169
            int r0 = r12.bp
            int r1 = r13 + 1
            int r0 = r0 + r13
            char r13 = r12.charAt(r0)
            r0 = r1
        L_0x00fc:
            if (r13 != r3) goto L_0x010d
            int r13 = r12.bp
            int r13 = r13 + r0
            r12.bp = r13
            char r13 = r12.charAt(r13)
            r12.ch = r13
            r13 = 3
            r12.matchStat = r13
            return r14
        L_0x010d:
            r1 = 125(0x7d, float:1.75E-43)
            if (r13 != r1) goto L_0x0166
            int r13 = r12.bp
            int r6 = r0 + 1
            int r13 = r13 + r0
            char r13 = r12.charAt(r13)
            if (r13 != r3) goto L_0x012c
            r13 = 16
            r12.token = r13
            int r13 = r12.bp
            int r13 = r13 + r6
            r12.bp = r13
            char r13 = r12.charAt(r13)
            r12.ch = r13
            goto L_0x015f
        L_0x012c:
            if (r13 != r4) goto L_0x013e
            r13 = 15
            r12.token = r13
            int r13 = r12.bp
            int r13 = r13 + r6
            r12.bp = r13
            char r13 = r12.charAt(r13)
            r12.ch = r13
            goto L_0x015f
        L_0x013e:
            if (r13 != r1) goto L_0x0150
            r13 = 13
            r12.token = r13
            int r13 = r12.bp
            int r13 = r13 + r6
            r12.bp = r13
            char r13 = r12.charAt(r13)
            r12.ch = r13
            goto L_0x015f
        L_0x0150:
            r1 = 26
            if (r13 != r1) goto L_0x0163
            int r13 = r12.bp
            int r13 = r13 + r0
            r12.bp = r13
            r13 = 20
            r12.token = r13
            r12.ch = r1
        L_0x015f:
            r13 = 4
            r12.matchStat = r13
            return r14
        L_0x0163:
            r12.matchStat = r5
            return r2
        L_0x0166:
            r12.matchStat = r5
            return r2
        L_0x0169:
            com.alibaba.fastjson.JSONException r13 = new com.alibaba.fastjson.JSONException
            java.lang.String r14 = "illega str"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldStringArray(char[], java.lang.Class):java.util.Collection");
    }

    public void scanStringArray(Collection<String> collection, char c2) {
        int i;
        char c3;
        int i2;
        char c4;
        Collection<String> collection2 = collection;
        char c5 = c2;
        this.matchStat = 0;
        char charAt = charAt(this.bp);
        char c6 = 'u';
        char c7 = 'l';
        if (charAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l' && charAt(this.bp + 4) == c5) {
            int i3 = this.bp + 5;
            this.bp = i3;
            this.ch = charAt(i3);
            this.matchStat = 5;
        } else if (charAt != '[') {
            this.matchStat = -1;
        } else {
            char charAt2 = charAt(this.bp + 1);
            int i4 = 2;
            while (true) {
                if (charAt2 != 'n' || charAt(this.bp + i4) != c6 || charAt(this.bp + i4 + 1) != c7 || charAt(this.bp + i4 + 2) != c7) {
                    if (charAt2 == ']' && collection.size() == 0) {
                        i = i4 + 1;
                        c3 = charAt(this.bp + i4);
                        break;
                    } else if (charAt2 != '\"') {
                        this.matchStat = -1;
                        return;
                    } else {
                        int i5 = this.bp + i4;
                        int indexOf = indexOf(Typography.quote, i5);
                        if (indexOf != -1) {
                            String subString = subString(this.bp + i4, indexOf - i5);
                            if (subString.indexOf(92) != -1) {
                                while (true) {
                                    int i6 = indexOf - 1;
                                    int i7 = 0;
                                    while (i6 >= 0 && charAt(i6) == '\\') {
                                        i7++;
                                        i6--;
                                    }
                                    if (i7 % 2 == 0) {
                                        break;
                                    }
                                    indexOf = indexOf(Typography.quote, indexOf + 1);
                                }
                                int i8 = indexOf - i5;
                                subString = readString(sub_chars(this.bp + i4, i8), i8);
                            }
                            int i9 = this.bp;
                            int i10 = i4 + (indexOf - (i9 + i4)) + 1;
                            c4 = charAt(i9 + i10);
                            collection2.add(subString);
                            i2 = i10 + 1;
                        } else {
                            throw new JSONException("unclosed str");
                        }
                    }
                } else {
                    int i11 = i4 + 3;
                    i2 = i4 + 4;
                    c4 = charAt(this.bp + i11);
                    collection2.add((Object) null);
                }
                if (c4 == ',') {
                    i4 = i2 + 1;
                    c6 = 'u';
                    c7 = 'l';
                    charAt2 = charAt(this.bp + i2);
                } else if (c4 == ']') {
                    i = i2 + 1;
                    c3 = charAt(this.bp + i2);
                } else {
                    this.matchStat = -1;
                    return;
                }
            }
            if (c3 == c5) {
                int i12 = this.bp + i;
                this.bp = i12;
                this.ch = charAt(i12);
                this.matchStat = 3;
                return;
            }
            this.matchStat = -1;
        }
    }

    public int scanFieldInt(char[] cArr) {
        int i;
        char charAt;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt2 = charAt(this.bp + length);
        boolean z = charAt2 == '-';
        if (z) {
            charAt2 = charAt(this.bp + i2);
            i2 = length + 2;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        int i3 = charAt2 - '0';
        while (true) {
            i = i2 + 1;
            charAt = charAt(this.bp + i2);
            if (charAt >= '0' && charAt <= '9') {
                i3 = (i3 * 10) + (charAt - '0');
                i2 = i;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        } else if ((i3 < 0 || i > cArr.length + 14) && !(i3 == Integer.MIN_VALUE && i == 17 && z)) {
            this.matchStat = -1;
            return 0;
        } else if (charAt == ',') {
            int i4 = this.bp + i;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return z ? -i3 : i3;
        } else if (charAt == '}') {
            int i5 = i2 + 2;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                int i6 = this.bp + i5;
                this.bp = i6;
                this.ch = charAt(i6);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i7 = this.bp + i5;
                this.bp = i7;
                this.ch = charAt(i7);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i8 = this.bp + i5;
                this.bp = i8;
                this.ch = charAt(i8);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i2 + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -i3 : i3;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public final int[] scanFieldIntArray(char[] cArr) {
        int i;
        char c2;
        int i2;
        boolean z;
        int i3;
        char charAt;
        int[] iArr;
        this.matchStat = 0;
        int[] iArr2 = null;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i4 = length + 1;
        if (charAt(this.bp + length) != '[') {
            this.matchStat = -2;
            return null;
        }
        int i5 = length + 2;
        char charAt2 = charAt(this.bp + i4);
        int[] iArr3 = new int[16];
        if (charAt2 == ']') {
            i2 = length + 3;
            c2 = charAt(this.bp + i5);
            i = 0;
        } else {
            int i6 = 0;
            while (true) {
                if (charAt2 == '-') {
                    charAt2 = charAt(this.bp + i5);
                    i5++;
                    z = true;
                } else {
                    z = false;
                }
                if (charAt2 < '0' || charAt2 > '9') {
                    int[] iArr4 = iArr2;
                    this.matchStat = -1;
                } else {
                    int i7 = charAt2 - '0';
                    while (true) {
                        i3 = i5 + 1;
                        charAt = charAt(this.bp + i5);
                        if (charAt >= '0' && charAt <= '9') {
                            i7 = (i7 * 10) + (charAt - '0');
                            i5 = i3;
                        }
                    }
                    if (i6 >= iArr3.length) {
                        int[] iArr5 = new int[((iArr3.length * 3) / 2)];
                        System.arraycopy(iArr3, 0, iArr5, 0, i6);
                        iArr3 = iArr5;
                    }
                    i = i6 + 1;
                    if (z) {
                        i7 = -i7;
                    }
                    iArr3[i6] = i7;
                    if (charAt == ',') {
                        i5 += 2;
                        charAt2 = charAt(this.bp + i3);
                        iArr = null;
                    } else if (charAt == ']') {
                        c2 = charAt(this.bp + i3);
                        i2 = i5 + 2;
                        break;
                    } else {
                        iArr = null;
                        charAt2 = charAt;
                        i5 = i3;
                    }
                    iArr2 = iArr;
                    i6 = i;
                }
            }
            int[] iArr42 = iArr2;
            this.matchStat = -1;
            return iArr42;
        }
        if (i != iArr3.length) {
            int[] iArr6 = new int[i];
            System.arraycopy(iArr3, 0, iArr6, 0, i);
            iArr3 = iArr6;
        }
        if (c2 == ',') {
            this.bp += i2 - 1;
            next();
            this.matchStat = 3;
            this.token = 16;
            return iArr3;
        } else if (c2 == '}') {
            char charAt3 = charAt(this.bp + i2);
            if (charAt3 == ',') {
                this.token = 16;
                this.bp += i2;
                next();
            } else if (charAt3 == ']') {
                this.token = 15;
                this.bp += i2;
                next();
            } else if (charAt3 == '}') {
                this.token = 13;
                this.bp += i2;
                next();
            } else if (charAt3 == 26) {
                this.bp += i2;
                this.token = 20;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return iArr3;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanBoolean(char r10) {
        /*
            r9 = this;
            r0 = 0
            r9.matchStat = r0
            int r1 = r9.bp
            char r1 = r9.charAt(r1)
            r2 = 116(0x74, float:1.63E-43)
            r3 = 5
            r4 = 101(0x65, float:1.42E-43)
            r5 = 3
            r6 = -1
            r7 = 2
            r8 = 1
            if (r1 != r2) goto L_0x003f
            int r1 = r9.bp
            int r1 = r1 + r8
            char r1 = r9.charAt(r1)
            r2 = 114(0x72, float:1.6E-43)
            if (r1 != r2) goto L_0x003c
            int r1 = r9.bp
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x003c
            int r1 = r9.bp
            int r1 = r1 + r5
            char r1 = r9.charAt(r1)
            if (r1 != r4) goto L_0x003c
            int r0 = r9.bp
            int r0 = r0 + 4
            char r1 = r9.charAt(r0)
            goto L_0x0086
        L_0x003c:
            r9.matchStat = r6
            return r0
        L_0x003f:
            r2 = 102(0x66, float:1.43E-43)
            if (r1 != r2) goto L_0x007a
            int r1 = r9.bp
            int r1 = r1 + r8
            char r1 = r9.charAt(r1)
            r2 = 97
            if (r1 != r2) goto L_0x0077
            int r1 = r9.bp
            int r1 = r1 + r7
            char r1 = r9.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x0077
            int r1 = r9.bp
            int r1 = r1 + r5
            char r1 = r9.charAt(r1)
            r2 = 115(0x73, float:1.61E-43)
            if (r1 != r2) goto L_0x0077
            int r1 = r9.bp
            int r1 = r1 + 4
            char r1 = r9.charAt(r1)
            if (r1 != r4) goto L_0x0077
            int r1 = r9.bp
            int r1 = r1 + r3
            char r1 = r9.charAt(r1)
            r3 = 6
            goto L_0x0096
        L_0x0077:
            r9.matchStat = r6
            return r0
        L_0x007a:
            r2 = 49
            if (r1 != r2) goto L_0x0088
            int r0 = r9.bp
            int r0 = r0 + r8
            char r1 = r9.charAt(r0)
            r3 = r7
        L_0x0086:
            r0 = r8
            goto L_0x0096
        L_0x0088:
            r2 = 48
            if (r1 != r2) goto L_0x0095
            int r1 = r9.bp
            int r1 = r1 + r8
            char r1 = r9.charAt(r1)
            r3 = r7
            goto L_0x0096
        L_0x0095:
            r3 = r8
        L_0x0096:
            if (r1 != r10) goto L_0x00a6
            int r10 = r9.bp
            int r10 = r10 + r3
            r9.bp = r10
            char r10 = r9.charAt(r10)
            r9.ch = r10
            r9.matchStat = r5
            return r0
        L_0x00a6:
            boolean r1 = isWhitespace(r1)
            if (r1 == 0) goto L_0x00b7
            int r1 = r9.bp
            int r2 = r3 + 1
            int r1 = r1 + r3
            char r1 = r9.charAt(r1)
            r3 = r2
            goto L_0x0096
        L_0x00b7:
            r9.matchStat = r6
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanBoolean(char):boolean");
    }

    public int scanInt(char c2) {
        int i;
        int i2;
        char charAt;
        this.matchStat = 0;
        char charAt2 = charAt(this.bp);
        boolean z = charAt2 == '\"';
        if (z) {
            charAt2 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        boolean z2 = charAt2 == '-';
        if (z2) {
            charAt2 = charAt(this.bp + i);
            i++;
        }
        if (charAt2 >= '0' && charAt2 <= '9') {
            int i3 = charAt2 - '0';
            while (true) {
                i2 = i + 1;
                charAt = charAt(this.bp + i);
                if (charAt >= '0' && charAt <= '9') {
                    i3 = (i3 * 10) + (charAt - '0');
                    i = i2;
                }
            }
            if (charAt == '.') {
                this.matchStat = -1;
                return 0;
            } else if (i3 < 0) {
                this.matchStat = -1;
                return 0;
            } else {
                while (charAt != c2) {
                    if (isWhitespace(charAt)) {
                        i2++;
                        charAt = charAt(this.bp + i2);
                    } else {
                        this.matchStat = -1;
                        return z2 ? -i3 : i3;
                    }
                }
                int i4 = this.bp + i2;
                this.bp = i4;
                this.ch = charAt(i4);
                this.matchStat = 3;
                this.token = 16;
                return z2 ? -i3 : i3;
            }
        } else if (charAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
            this.matchStat = 5;
            int i5 = i + 4;
            char charAt3 = charAt(this.bp + i + 3);
            if (z && charAt3 == '\"') {
                charAt3 = charAt(this.bp + i5);
                i5 = i + 5;
            }
            while (charAt3 != ',') {
                if (charAt3 == ']') {
                    int i6 = this.bp + i5;
                    this.bp = i6;
                    this.ch = charAt(i6);
                    this.matchStat = 5;
                    this.token = 15;
                    return 0;
                } else if (isWhitespace(charAt3)) {
                    charAt3 = charAt(this.bp + i5);
                    i5++;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            }
            int i7 = this.bp + i5;
            this.bp = i7;
            this.ch = charAt(i7);
            this.matchStat = 5;
            this.token = 16;
            return 0;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public boolean scanFieldBoolean(char[] cArr) {
        int i;
        boolean z;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt = charAt(this.bp + length);
        if (charAt == 't') {
            int i3 = length + 2;
            if (charAt(this.bp + i2) != 'r') {
                this.matchStat = -1;
                return false;
            }
            int i4 = length + 3;
            if (charAt(this.bp + i3) != 'u') {
                this.matchStat = -1;
                return false;
            }
            i = length + 4;
            if (charAt(this.bp + i4) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = true;
        } else if (charAt == 'f') {
            int i5 = length + 2;
            if (charAt(this.bp + i2) != 'a') {
                this.matchStat = -1;
                return false;
            }
            int i6 = length + 3;
            if (charAt(this.bp + i5) != 'l') {
                this.matchStat = -1;
                return false;
            }
            int i7 = length + 4;
            if (charAt(this.bp + i6) != 's') {
                this.matchStat = -1;
                return false;
            }
            i = length + 5;
            if (charAt(this.bp + i7) != 'e') {
                this.matchStat = -1;
                return false;
            }
            z = false;
        } else {
            this.matchStat = -1;
            return false;
        }
        int i8 = i + 1;
        char charAt2 = charAt(this.bp + i);
        if (charAt2 == ',') {
            int i9 = this.bp + i8;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            this.token = 16;
            return z;
        } else if (charAt2 == '}') {
            int i10 = i + 2;
            char charAt3 = charAt(this.bp + i8);
            if (charAt3 == ',') {
                this.token = 16;
                int i11 = this.bp + i10;
                this.bp = i11;
                this.ch = charAt(i11);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i12 = this.bp + i10;
                this.bp = i12;
                this.ch = charAt(i12);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i13 = this.bp + i10;
                this.bp = i13;
                this.ch = charAt(i13);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return false;
            }
            this.matchStat = 4;
            return z;
        } else {
            this.matchStat = -1;
            return false;
        }
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return 0;
        }
        int length = cArr.length;
        int i2 = length + 1;
        char charAt2 = charAt(this.bp + length);
        if (charAt2 == '-') {
            charAt2 = charAt(this.bp + i2);
            i2 = length + 2;
            z = true;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.matchStat = -1;
            return 0;
        }
        long j = (long) (charAt2 - '0');
        while (true) {
            i = i2 + 1;
            charAt = charAt(this.bp + i2);
            if (charAt >= '0' && charAt <= '9') {
                j = (j * 10) + ((long) (charAt - '0'));
                i2 = i;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        } else if (i - cArr.length >= 21 || (j < 0 && (j != Long.MIN_VALUE || !z))) {
            this.matchStat = -1;
            return 0;
        } else if (charAt == ',') {
            int i3 = this.bp + i;
            this.bp = i3;
            this.ch = charAt(i3);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        } else if (charAt == '}') {
            int i4 = i2 + 2;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                int i5 = this.bp + i4;
                this.bp = i5;
                this.ch = charAt(i5);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i6 = this.bp + i4;
                this.bp = i6;
                this.ch = charAt(i6);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i7 = this.bp + i4;
                this.bp = i7;
                this.ch = charAt(i7);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i2 + 1;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return 0;
            }
            this.matchStat = 4;
            return z ? -j : j;
        } else {
            this.matchStat = -1;
            return 0;
        }
    }

    public long scanLong(char c2) {
        int i;
        int i2;
        char charAt;
        char c3;
        boolean z = false;
        this.matchStat = 0;
        char charAt2 = charAt(this.bp);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(this.bp + 1);
            i = 2;
        } else {
            i = 1;
        }
        if (charAt2 == '-') {
            z = true;
        }
        if (z) {
            charAt2 = charAt(this.bp + i);
            i++;
        }
        if (charAt2 >= '0') {
            char c4 = '9';
            if (charAt2 <= '9') {
                long j = (long) (charAt2 - '0');
                while (true) {
                    i2 = i + 1;
                    charAt = charAt(this.bp + i);
                    if (charAt >= '0' && charAt <= c4) {
                        j = (j * 10) + ((long) (charAt - '0'));
                        i = i2;
                        c4 = '9';
                    }
                }
                if (charAt == '.') {
                    this.matchStat = -1;
                    return 0;
                } else if (j >= 0 || (j == Long.MIN_VALUE && z)) {
                    if (!z2) {
                        c3 = c2;
                    } else if (charAt != '\"') {
                        this.matchStat = -1;
                        return 0;
                    } else {
                        charAt = charAt(this.bp + i2);
                        c3 = c2;
                        i2 = i + 2;
                    }
                    while (charAt != c3) {
                        if (isWhitespace(charAt)) {
                            charAt = charAt(this.bp + i2);
                            i2++;
                        } else {
                            this.matchStat = -1;
                            return j;
                        }
                    }
                    int i3 = this.bp + i2;
                    this.bp = i3;
                    this.ch = charAt(i3);
                    this.matchStat = 3;
                    this.token = 16;
                    return z ? -j : j;
                } else {
                    throw new NumberFormatException(subString(this.bp, i));
                }
            }
        }
        if (charAt2 == 'n' && charAt(this.bp + i) == 'u' && charAt(this.bp + i + 1) == 'l' && charAt(this.bp + i + 2) == 'l') {
            this.matchStat = 5;
            int i4 = i + 4;
            char charAt3 = charAt(this.bp + i + 3);
            if (z2 && charAt3 == '\"') {
                charAt3 = charAt(this.bp + i4);
                i4 = i + 5;
            }
            while (charAt3 != ',') {
                if (charAt3 == ']') {
                    int i5 = this.bp + i4;
                    this.bp = i5;
                    this.ch = charAt(i5);
                    this.matchStat = 5;
                    this.token = 15;
                    return 0;
                } else if (isWhitespace(charAt3)) {
                    charAt3 = charAt(this.bp + i4);
                    i4++;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            }
            int i6 = this.bp + i4;
            this.bp = i6;
            this.ch = charAt(i6);
            this.matchStat = 5;
            this.token = 16;
            return 0;
        }
        this.matchStat = -1;
        return 0;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float scanFieldFloat(char[] r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r20.charArrayCompare(r21)
            r4 = 0
            if (r3 != 0) goto L_0x0012
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0012:
            int r3 = r1.length
            int r5 = r0.bp
            int r6 = r3 + 1
            int r5 = r5 + r3
            char r5 = r0.charAt(r5)
            r7 = 34
            if (r5 != r7) goto L_0x0022
            r9 = 1
            goto L_0x0023
        L_0x0022:
            r9 = r2
        L_0x0023:
            if (r9 == 0) goto L_0x002f
            int r5 = r0.bp
            int r3 = r3 + 2
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            r6 = r3
        L_0x002f:
            r3 = 45
            if (r5 != r3) goto L_0x0035
            r10 = 1
            goto L_0x0036
        L_0x0035:
            r10 = r2
        L_0x0036:
            if (r10 == 0) goto L_0x0042
            int r5 = r0.bp
            int r11 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            r6 = r11
        L_0x0042:
            r15 = -1
            r2 = 48
            if (r5 < r2) goto L_0x0195
            r11 = 57
            if (r5 > r11) goto L_0x0195
            int r5 = r5 - r2
            long r12 = (long) r5
        L_0x004d:
            int r5 = r0.bp
            int r17 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            r18 = 10
            if (r5 < r2) goto L_0x0065
            if (r5 > r11) goto L_0x0065
            long r12 = r12 * r18
            int r5 = r5 + -48
            long r5 = (long) r5
            long r12 = r12 + r5
            r6 = r17
            goto L_0x004d
        L_0x0065:
            r14 = 46
            if (r5 != r14) goto L_0x0099
            int r5 = r0.bp
            int r6 = r6 + 2
            int r5 = r5 + r17
            char r5 = r0.charAt(r5)
            if (r5 < r2) goto L_0x0096
            if (r5 > r11) goto L_0x0096
            long r12 = r12 * r18
            int r5 = r5 - r2
            long r7 = (long) r5
            long r12 = r12 + r7
            r7 = r18
        L_0x007e:
            int r5 = r0.bp
            int r17 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            if (r5 < r2) goto L_0x009b
            if (r5 > r11) goto L_0x009b
            long r12 = r12 * r18
            int r5 = r5 + -48
            long r5 = (long) r5
            long r12 = r12 + r5
            long r7 = r7 * r18
            r6 = r17
            goto L_0x007e
        L_0x0096:
            r0.matchStat = r15
            return r4
        L_0x0099:
            r7 = 1
        L_0x009b:
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x00a7
            r6 = 69
            if (r5 != r6) goto L_0x00a4
            goto L_0x00a7
        L_0x00a4:
            r16 = 0
            goto L_0x00a9
        L_0x00a7:
            r16 = 1
        L_0x00a9:
            if (r16 == 0) goto L_0x00da
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r5 = r0.charAt(r5)
            r14 = 43
            if (r5 == r14) goto L_0x00bf
            if (r5 != r3) goto L_0x00bc
            goto L_0x00bf
        L_0x00bc:
            r17 = r6
            goto L_0x00c9
        L_0x00bf:
            int r3 = r0.bp
            int r17 = r17 + 2
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
        L_0x00c8:
            r5 = r3
        L_0x00c9:
            if (r5 < r2) goto L_0x00da
            if (r5 > r11) goto L_0x00da
            int r3 = r0.bp
            int r5 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            r17 = r5
            goto L_0x00c8
        L_0x00da:
            if (r9 == 0) goto L_0x00fa
            r2 = 34
            if (r5 == r2) goto L_0x00e3
            r0.matchStat = r15
            return r4
        L_0x00e3:
            int r2 = r0.bp
            int r3 = r17 + 1
            int r2 = r2 + r17
            char r5 = r0.charAt(r2)
            int r2 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r2
            r6 = 1
            int r1 = r1 + r6
            int r2 = r2 + r3
            int r2 = r2 - r1
            int r2 = r2 + -2
            r17 = r3
            goto L_0x0103
        L_0x00fa:
            r6 = 1
            int r2 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r2
            int r2 = r2 + r17
            int r2 = r2 - r1
            int r2 = r2 - r6
        L_0x0103:
            if (r16 != 0) goto L_0x0111
            r3 = 17
            if (r2 >= r3) goto L_0x0111
            double r1 = (double) r12
            double r6 = (double) r7
            double r1 = r1 / r6
            float r1 = (float) r1
            if (r10 == 0) goto L_0x0119
            float r1 = -r1
            goto L_0x0119
        L_0x0111:
            java.lang.String r1 = r0.subString(r1, r2)
            float r1 = java.lang.Float.parseFloat(r1)
        L_0x0119:
            r2 = 44
            if (r5 != r2) goto L_0x0131
            int r2 = r0.bp
            int r2 = r2 + r17
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r2 = 3
            r0.matchStat = r2
            r2 = 16
            r0.token = r2
            return r1
        L_0x0131:
            r2 = 16
            r3 = 125(0x7d, float:1.75E-43)
            if (r5 != r3) goto L_0x0192
            int r3 = r0.bp
            int r5 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            r6 = 44
            if (r3 != r6) goto L_0x0153
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x018b
        L_0x0153:
            r2 = 93
            if (r3 != r2) goto L_0x0167
            r2 = 15
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x018b
        L_0x0167:
            r2 = 125(0x7d, float:1.75E-43)
            if (r3 != r2) goto L_0x017b
            r2 = 13
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            goto L_0x018b
        L_0x017b:
            r2 = 26
            if (r3 != r2) goto L_0x018f
            int r3 = r0.bp
            int r3 = r3 + r17
            r0.bp = r3
            r3 = 20
            r0.token = r3
            r0.ch = r2
        L_0x018b:
            r2 = 4
            r0.matchStat = r2
            return r1
        L_0x018f:
            r0.matchStat = r15
            return r4
        L_0x0192:
            r0.matchStat = r15
            return r4
        L_0x0195:
            r1 = 110(0x6e, float:1.54E-43)
            if (r5 != r1) goto L_0x021d
            int r1 = r0.bp
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x021d
            int r1 = r0.bp
            int r1 = r1 + r6
            r2 = 1
            int r1 = r1 + r2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x021d
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x021d
            r1 = 5
            r0.matchStat = r1
            int r2 = r6 + 3
            int r3 = r0.bp
            int r5 = r6 + 4
            int r3 = r3 + r2
            char r2 = r0.charAt(r3)
            if (r9 == 0) goto L_0x01d9
            r3 = 34
            if (r2 != r3) goto L_0x01d9
            int r2 = r0.bp
            int r6 = r6 + r1
            int r2 = r2 + r5
            char r2 = r0.charAt(r2)
            r5 = r6
        L_0x01d9:
            r3 = 44
        L_0x01db:
            if (r2 != r3) goto L_0x01ef
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r6 = 16
            r0.token = r6
            return r4
        L_0x01ef:
            r6 = 16
            r7 = 125(0x7d, float:1.75E-43)
            if (r2 != r7) goto L_0x0207
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r8 = 13
            r0.token = r8
            return r4
        L_0x0207:
            r8 = 13
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x021a
            int r2 = r0.bp
            int r9 = r5 + 1
            int r2 = r2 + r5
            char r2 = r0.charAt(r2)
            r5 = r9
            goto L_0x01db
        L_0x021a:
            r0.matchStat = r15
            return r4
        L_0x021d:
            r0.matchStat = r15
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloat(char[]):float");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final float scanFloat(char r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r3 = 1
            r4 = 34
            if (r2 != r4) goto L_0x0012
            r5 = r3
            goto L_0x0013
        L_0x0012:
            r5 = r1
        L_0x0013:
            r6 = 2
            if (r5 == 0) goto L_0x001f
            int r2 = r0.bp
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r7 = r6
            goto L_0x0020
        L_0x001f:
            r7 = r3
        L_0x0020:
            r8 = 45
            if (r2 != r8) goto L_0x0026
            r9 = r3
            goto L_0x0027
        L_0x0026:
            r9 = r1
        L_0x0027:
            if (r9 == 0) goto L_0x0033
            int r2 = r0.bp
            int r10 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r10
        L_0x0033:
            r10 = 16
            r11 = 0
            r12 = -1
            r13 = 48
            if (r2 < r13) goto L_0x0124
            r14 = 57
            if (r2 > r14) goto L_0x0124
            int r2 = r2 - r13
            long r1 = (long) r2
        L_0x0041:
            int r15 = r0.bp
            int r17 = r7 + 1
            int r15 = r15 + r7
            char r15 = r0.charAt(r15)
            r18 = 10
            if (r15 < r13) goto L_0x005c
            if (r15 > r14) goto L_0x005c
            long r1 = r1 * r18
            int r15 = r15 + -48
            long r3 = (long) r15
            long r1 = r1 + r3
            r7 = r17
            r3 = 1
            r4 = 34
            goto L_0x0041
        L_0x005c:
            r3 = 46
            if (r15 != r3) goto L_0x0090
            int r3 = r0.bp
            int r7 = r7 + r6
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            if (r3 < r13) goto L_0x008d
            if (r3 > r14) goto L_0x008d
            long r1 = r1 * r18
            int r3 = r3 - r13
            long r3 = (long) r3
            long r1 = r1 + r3
            r3 = r18
        L_0x0074:
            int r15 = r0.bp
            int r17 = r7 + 1
            int r15 = r15 + r7
            char r15 = r0.charAt(r15)
            if (r15 < r13) goto L_0x0092
            if (r15 > r14) goto L_0x0092
            long r1 = r1 * r18
            int r15 = r15 + -48
            long r6 = (long) r15
            long r1 = r1 + r6
            long r3 = r3 * r18
            r7 = r17
            r6 = 2
            goto L_0x0074
        L_0x008d:
            r0.matchStat = r12
            return r11
        L_0x0090:
            r3 = 1
        L_0x0092:
            r6 = 101(0x65, float:1.42E-43)
            if (r15 == r6) goto L_0x009e
            r6 = 69
            if (r15 != r6) goto L_0x009b
            goto L_0x009e
        L_0x009b:
            r16 = 0
            goto L_0x00a0
        L_0x009e:
            r16 = 1
        L_0x00a0:
            if (r16 == 0) goto L_0x00d0
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            r15 = 43
            if (r6 == r15) goto L_0x00b7
            if (r6 != r8) goto L_0x00b3
            goto L_0x00b7
        L_0x00b3:
            r15 = r6
        L_0x00b4:
            r17 = r7
            goto L_0x00c1
        L_0x00b7:
            int r6 = r0.bp
            int r17 = r17 + 2
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r15 = r6
        L_0x00c1:
            if (r15 < r13) goto L_0x00d0
            if (r15 > r14) goto L_0x00d0
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r15 = r0.charAt(r6)
            goto L_0x00b4
        L_0x00d0:
            if (r5 == 0) goto L_0x00ee
            r5 = 34
            if (r15 == r5) goto L_0x00d9
            r0.matchStat = r12
            return r11
        L_0x00d9:
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r15 = r0.charAt(r5)
            int r5 = r0.bp
            int r7 = r5 + 1
            int r5 = r5 + r6
            int r5 = r5 - r7
            r8 = 2
            int r5 = r5 - r8
            r17 = r6
            goto L_0x00f5
        L_0x00ee:
            int r7 = r0.bp
            int r5 = r7 + r17
            int r5 = r5 - r7
            r6 = 1
            int r5 = r5 - r6
        L_0x00f5:
            if (r16 != 0) goto L_0x0103
            r6 = 17
            if (r5 >= r6) goto L_0x0103
            double r1 = (double) r1
            double r3 = (double) r3
            double r1 = r1 / r3
            float r1 = (float) r1
            if (r9 == 0) goto L_0x010b
            float r1 = -r1
            goto L_0x010b
        L_0x0103:
            java.lang.String r1 = r0.subString(r7, r5)
            float r1 = java.lang.Float.parseFloat(r1)
        L_0x010b:
            r2 = r21
            if (r15 != r2) goto L_0x0121
            int r2 = r0.bp
            int r2 = r2 + r17
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r2 = 3
            r0.matchStat = r2
            r0.token = r10
            return r1
        L_0x0121:
            r0.matchStat = r12
            return r1
        L_0x0124:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x01a6
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01a6
            int r1 = r0.bp
            int r1 = r1 + r7
            r2 = 1
            int r1 = r1 + r2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01a6
            int r1 = r0.bp
            int r1 = r1 + r7
            r3 = 2
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01a6
            r1 = 5
            r0.matchStat = r1
            int r2 = r7 + 3
            int r3 = r0.bp
            int r4 = r7 + 4
            int r3 = r3 + r2
            char r2 = r0.charAt(r3)
            if (r5 == 0) goto L_0x0168
            r3 = 34
            if (r2 != r3) goto L_0x0168
            int r2 = r0.bp
            int r7 = r7 + r1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r4 = r7
        L_0x0168:
            r3 = 44
            if (r2 != r3) goto L_0x017c
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r0.token = r10
            return r11
        L_0x017c:
            r3 = 93
            if (r2 != r3) goto L_0x0192
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 15
            r0.token = r1
            return r11
        L_0x0192:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01a3
            int r2 = r0.bp
            int r3 = r4 + 1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r4 = r3
            goto L_0x0168
        L_0x01a3:
            r0.matchStat = r12
            return r11
        L_0x01a6:
            r0.matchStat = r12
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFloat(char):float");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public double scanDouble(char r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r3 = 1
            r4 = 34
            if (r2 != r4) goto L_0x0012
            r5 = r3
            goto L_0x0013
        L_0x0012:
            r5 = r1
        L_0x0013:
            r6 = 2
            if (r5 == 0) goto L_0x001f
            int r2 = r0.bp
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r7 = r6
            goto L_0x0020
        L_0x001f:
            r7 = r3
        L_0x0020:
            r8 = 45
            if (r2 != r8) goto L_0x0026
            r9 = r3
            goto L_0x0027
        L_0x0026:
            r9 = r1
        L_0x0027:
            if (r9 == 0) goto L_0x0033
            int r2 = r0.bp
            int r10 = r7 + 1
            int r2 = r2 + r7
            char r2 = r0.charAt(r2)
            r7 = r10
        L_0x0033:
            r11 = 0
            r13 = -1
            r14 = 48
            if (r2 < r14) goto L_0x0124
            r15 = 57
            if (r2 > r15) goto L_0x0124
            int r2 = r2 - r14
            long r1 = (long) r2
        L_0x0040:
            int r10 = r0.bp
            int r17 = r7 + 1
            int r10 = r10 + r7
            char r10 = r0.charAt(r10)
            r18 = 10
            if (r10 < r14) goto L_0x005b
            if (r10 > r15) goto L_0x005b
            long r1 = r1 * r18
            int r10 = r10 + -48
            long r3 = (long) r10
            long r1 = r1 + r3
            r7 = r17
            r3 = 1
            r4 = 34
            goto L_0x0040
        L_0x005b:
            r3 = 46
            if (r10 != r3) goto L_0x008f
            int r3 = r0.bp
            int r7 = r7 + r6
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            if (r3 < r14) goto L_0x008c
            if (r3 > r15) goto L_0x008c
            long r1 = r1 * r18
            int r3 = r3 - r14
            long r3 = (long) r3
            long r1 = r1 + r3
            r3 = r18
        L_0x0073:
            int r10 = r0.bp
            int r17 = r7 + 1
            int r10 = r10 + r7
            char r10 = r0.charAt(r10)
            if (r10 < r14) goto L_0x0091
            if (r10 > r15) goto L_0x0091
            long r1 = r1 * r18
            int r10 = r10 + -48
            long r6 = (long) r10
            long r1 = r1 + r6
            long r3 = r3 * r18
            r7 = r17
            r6 = 2
            goto L_0x0073
        L_0x008c:
            r0.matchStat = r13
            return r11
        L_0x008f:
            r3 = 1
        L_0x0091:
            r6 = 101(0x65, float:1.42E-43)
            if (r10 == r6) goto L_0x009d
            r6 = 69
            if (r10 != r6) goto L_0x009a
            goto L_0x009d
        L_0x009a:
            r16 = 0
            goto L_0x009f
        L_0x009d:
            r16 = 1
        L_0x009f:
            if (r16 == 0) goto L_0x00cf
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            r10 = 43
            if (r6 == r10) goto L_0x00b6
            if (r6 != r8) goto L_0x00b2
            goto L_0x00b6
        L_0x00b2:
            r10 = r6
        L_0x00b3:
            r17 = r7
            goto L_0x00c0
        L_0x00b6:
            int r6 = r0.bp
            int r17 = r17 + 2
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r10 = r6
        L_0x00c0:
            if (r10 < r14) goto L_0x00cf
            if (r10 > r15) goto L_0x00cf
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r10 = r0.charAt(r6)
            goto L_0x00b3
        L_0x00cf:
            if (r5 == 0) goto L_0x00ed
            r5 = 34
            if (r10 == r5) goto L_0x00d8
            r0.matchStat = r13
            return r11
        L_0x00d8:
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r10 = r0.charAt(r5)
            int r5 = r0.bp
            int r7 = r5 + 1
            int r5 = r5 + r6
            int r5 = r5 - r7
            r8 = 2
            int r5 = r5 - r8
            r17 = r6
            goto L_0x00f4
        L_0x00ed:
            int r7 = r0.bp
            int r5 = r7 + r17
            int r5 = r5 - r7
            r6 = 1
            int r5 = r5 - r6
        L_0x00f4:
            if (r16 != 0) goto L_0x0101
            r6 = 17
            if (r5 >= r6) goto L_0x0101
            double r1 = (double) r1
            double r3 = (double) r3
            double r1 = r1 / r3
            if (r9 == 0) goto L_0x0109
            double r1 = -r1
            goto L_0x0109
        L_0x0101:
            java.lang.String r1 = r0.subString(r7, r5)
            double r1 = java.lang.Double.parseDouble(r1)
        L_0x0109:
            r3 = r21
            if (r10 != r3) goto L_0x0121
            int r3 = r0.bp
            int r3 = r3 + r17
            r0.bp = r3
            char r3 = r0.charAt(r3)
            r0.ch = r3
            r3 = 3
            r0.matchStat = r3
            r3 = 16
            r0.token = r3
            return r1
        L_0x0121:
            r0.matchStat = r13
            return r1
        L_0x0124:
            r1 = 110(0x6e, float:1.54E-43)
            if (r2 != r1) goto L_0x01aa
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L_0x01aa
            int r1 = r0.bp
            int r1 = r1 + r7
            r2 = 1
            int r1 = r1 + r2
            char r1 = r0.charAt(r1)
            r2 = 108(0x6c, float:1.51E-43)
            if (r1 != r2) goto L_0x01aa
            int r1 = r0.bp
            int r1 = r1 + r7
            r3 = 2
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            if (r1 != r2) goto L_0x01aa
            r1 = 5
            r0.matchStat = r1
            int r2 = r7 + 3
            int r3 = r0.bp
            int r4 = r7 + 4
            int r3 = r3 + r2
            char r2 = r0.charAt(r3)
            if (r5 == 0) goto L_0x0168
            r3 = 34
            if (r2 != r3) goto L_0x0168
            int r2 = r0.bp
            int r7 = r7 + r1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r4 = r7
        L_0x0168:
            r3 = 44
            if (r2 != r3) goto L_0x017e
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r3 = 16
            r0.token = r3
            return r11
        L_0x017e:
            r3 = 16
            r5 = 93
            if (r2 != r5) goto L_0x0196
            int r2 = r0.bp
            int r2 = r2 + r4
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r1 = 15
            r0.token = r1
            return r11
        L_0x0196:
            boolean r2 = isWhitespace(r2)
            if (r2 == 0) goto L_0x01a7
            int r2 = r0.bp
            int r5 = r4 + 1
            int r2 = r2 + r4
            char r2 = r0.charAt(r2)
            r4 = r5
            goto L_0x0168
        L_0x01a7:
            r0.matchStat = r13
            return r11
        L_0x01aa:
            r0.matchStat = r13
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDouble(char):double");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public java.math.BigDecimal scanDecimal(char r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = 0
            r0.matchStat = r1
            int r2 = r0.bp
            char r2 = r0.charAt(r2)
            r3 = 1
            r4 = 34
            if (r2 != r4) goto L_0x0011
            r1 = r3
        L_0x0011:
            r5 = 2
            if (r1 == 0) goto L_0x001d
            int r2 = r0.bp
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r6 = r5
            goto L_0x001e
        L_0x001d:
            r6 = r3
        L_0x001e:
            r7 = 45
            if (r2 != r7) goto L_0x002c
            int r2 = r0.bp
            int r8 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            r6 = r8
        L_0x002c:
            r9 = 125(0x7d, float:1.75E-43)
            r10 = 16
            r11 = 44
            r12 = 48
            r13 = -1
            r14 = 0
            if (r2 < r12) goto L_0x0140
            r15 = 57
            if (r2 > r15) goto L_0x0140
        L_0x003c:
            int r2 = r0.bp
            int r16 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            if (r2 < r12) goto L_0x004c
            if (r2 > r15) goto L_0x004c
            r6 = r16
            goto L_0x003c
        L_0x004c:
            r8 = 46
            if (r2 != r8) goto L_0x0070
            int r2 = r0.bp
            int r6 = r6 + r5
            int r2 = r2 + r16
            char r2 = r0.charAt(r2)
            if (r2 < r12) goto L_0x006d
            if (r2 > r15) goto L_0x006d
        L_0x005d:
            int r2 = r0.bp
            int r16 = r6 + 1
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            if (r2 < r12) goto L_0x0070
            if (r2 > r15) goto L_0x0070
            r6 = r16
            goto L_0x005d
        L_0x006d:
            r0.matchStat = r13
            return r14
        L_0x0070:
            r6 = 101(0x65, float:1.42E-43)
            if (r2 == r6) goto L_0x0078
            r6 = 69
            if (r2 != r6) goto L_0x00a4
        L_0x0078:
            int r2 = r0.bp
            int r6 = r16 + 1
            int r2 = r2 + r16
            char r2 = r0.charAt(r2)
            r8 = 43
            if (r2 == r8) goto L_0x008c
            if (r2 != r7) goto L_0x0089
            goto L_0x008c
        L_0x0089:
            r16 = r6
            goto L_0x0095
        L_0x008c:
            int r2 = r0.bp
            int r16 = r16 + 2
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
        L_0x0095:
            if (r2 < r12) goto L_0x00a4
            if (r2 > r15) goto L_0x00a4
            int r2 = r0.bp
            int r6 = r16 + 1
            int r2 = r2 + r16
            char r2 = r0.charAt(r2)
            goto L_0x0089
        L_0x00a4:
            if (r1 == 0) goto L_0x00c0
            if (r2 == r4) goto L_0x00ab
            r0.matchStat = r13
            return r14
        L_0x00ab:
            int r1 = r0.bp
            int r2 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            int r3 = r0.bp
            int r4 = r3 + 1
            int r3 = r3 + r2
            int r3 = r3 - r4
            int r3 = r3 - r5
            r16 = r2
            r2 = r1
            goto L_0x00c7
        L_0x00c0:
            int r4 = r0.bp
            int r1 = r4 + r16
            int r1 = r1 - r4
            int r3 = r1 + -1
        L_0x00c7:
            char[] r1 = r0.sub_chars(r4, r3)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r3.<init>(r1)
            if (r2 != r11) goto L_0x00e4
            int r1 = r0.bp
            int r1 = r1 + r16
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r1 = 3
            r0.matchStat = r1
            r0.token = r10
            return r3
        L_0x00e4:
            r1 = 93
            if (r2 != r1) goto L_0x013d
            int r2 = r0.bp
            int r4 = r16 + 1
            int r2 = r2 + r16
            char r2 = r0.charAt(r2)
            if (r2 != r11) goto L_0x0102
            r0.token = r10
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0136
        L_0x0102:
            if (r2 != r1) goto L_0x0114
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0136
        L_0x0114:
            if (r2 != r9) goto L_0x0126
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x0136
        L_0x0126:
            r1 = 26
            if (r2 != r1) goto L_0x013a
            r2 = 20
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r16
            r0.bp = r2
            r0.ch = r1
        L_0x0136:
            r1 = 4
            r0.matchStat = r1
            return r3
        L_0x013a:
            r0.matchStat = r13
            return r14
        L_0x013d:
            r0.matchStat = r13
            return r14
        L_0x0140:
            r7 = 110(0x6e, float:1.54E-43)
            if (r2 != r7) goto L_0x01bc
            int r2 = r0.bp
            int r2 = r2 + r6
            char r2 = r0.charAt(r2)
            r7 = 117(0x75, float:1.64E-43)
            if (r2 != r7) goto L_0x01bc
            int r2 = r0.bp
            int r2 = r2 + r6
            int r2 = r2 + r3
            char r2 = r0.charAt(r2)
            r3 = 108(0x6c, float:1.51E-43)
            if (r2 != r3) goto L_0x01bc
            int r2 = r0.bp
            int r2 = r2 + r6
            int r2 = r2 + r5
            char r2 = r0.charAt(r2)
            if (r2 != r3) goto L_0x01bc
            r2 = 5
            r0.matchStat = r2
            int r3 = r6 + 3
            int r5 = r0.bp
            int r7 = r6 + 4
            int r5 = r5 + r3
            char r3 = r0.charAt(r5)
            if (r1 == 0) goto L_0x0180
            if (r3 != r4) goto L_0x0180
            int r1 = r0.bp
            int r6 = r6 + r2
            int r1 = r1 + r7
            char r3 = r0.charAt(r1)
            r7 = r6
        L_0x0180:
            if (r3 != r11) goto L_0x0192
            int r1 = r0.bp
            int r1 = r1 + r7
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r0.token = r10
            return r14
        L_0x0192:
            if (r3 != r9) goto L_0x01a6
            int r1 = r0.bp
            int r1 = r1 + r7
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r0.matchStat = r2
            r1 = 13
            r0.token = r1
            return r14
        L_0x01a6:
            r1 = 13
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x01b9
            int r3 = r0.bp
            int r4 = r7 + 1
            int r3 = r3 + r7
            char r3 = r0.charAt(r3)
            r7 = r4
            goto L_0x0180
        L_0x01b9:
            r0.matchStat = r13
            return r14
        L_0x01bc:
            r0.matchStat = r13
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanDecimal(char):java.math.BigDecimal");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:94:0x01b9, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float[] scanFieldFloatArray(char[] r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = 0
            r0.matchStat = r1
            boolean r2 = r19.charArrayCompare(r20)
            r3 = -2
            r4 = 0
            if (r2 != 0) goto L_0x0010
            r0.matchStat = r3
            return r4
        L_0x0010:
            r2 = r20
            int r2 = r2.length
            int r5 = r0.bp
            int r6 = r2 + 1
            int r5 = r5 + r2
            char r5 = r0.charAt(r5)
            r7 = 91
            if (r5 == r7) goto L_0x0023
            r0.matchStat = r3
            return r4
        L_0x0023:
            int r3 = r0.bp
            int r2 = r2 + 2
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r5 = 16
            float[] r6 = new float[r5]
            r7 = r1
        L_0x0031:
            int r8 = r0.bp
            int r9 = r8 + r2
            r10 = 1
            int r9 = r9 - r10
            r11 = 45
            if (r3 != r11) goto L_0x003d
            r12 = r10
            goto L_0x003e
        L_0x003d:
            r12 = r1
        L_0x003e:
            if (r12 == 0) goto L_0x004c
            int r3 = r2 + 1
            int r8 = r8 + r2
            char r2 = r0.charAt(r8)
            r18 = r3
            r3 = r2
            r2 = r18
        L_0x004c:
            r8 = -1
            r13 = 48
            if (r3 < r13) goto L_0x01b6
            r14 = 57
            if (r3 > r14) goto L_0x01b6
            int r3 = r3 + -48
        L_0x0057:
            int r15 = r0.bp
            int r16 = r2 + 1
            int r15 = r15 + r2
            char r15 = r0.charAt(r15)
            if (r15 < r13) goto L_0x006c
            if (r15 > r14) goto L_0x006c
            int r3 = r3 * 10
            int r15 = r15 + -48
            int r3 = r3 + r15
            r2 = r16
            goto L_0x0057
        L_0x006c:
            r5 = 46
            r1 = 10
            if (r15 != r5) goto L_0x00a0
            int r5 = r0.bp
            int r2 = r2 + 2
            int r5 = r5 + r16
            char r5 = r0.charAt(r5)
            if (r5 < r13) goto L_0x009d
            if (r5 > r14) goto L_0x009d
            int r3 = r3 * 10
            int r5 = r5 + -48
            int r3 = r3 + r5
            r5 = r1
        L_0x0086:
            int r15 = r0.bp
            int r16 = r2 + 1
            int r15 = r15 + r2
            char r15 = r0.charAt(r15)
            if (r15 < r13) goto L_0x00a1
            if (r15 > r14) goto L_0x00a1
            int r3 = r3 * 10
            int r15 = r15 + -48
            int r3 = r3 + r15
            int r5 = r5 * 10
            r2 = r16
            goto L_0x0086
        L_0x009d:
            r0.matchStat = r8
            return r4
        L_0x00a0:
            r5 = r10
        L_0x00a1:
            r2 = 101(0x65, float:1.42E-43)
            if (r15 == r2) goto L_0x00ac
            r2 = 69
            if (r15 != r2) goto L_0x00aa
            goto L_0x00ac
        L_0x00aa:
            r2 = 0
            goto L_0x00ad
        L_0x00ac:
            r2 = r10
        L_0x00ad:
            if (r2 == 0) goto L_0x00df
            int r15 = r0.bp
            int r17 = r16 + 1
            int r15 = r15 + r16
            char r15 = r0.charAt(r15)
            r4 = 43
            if (r15 == r4) goto L_0x00c3
            if (r15 != r11) goto L_0x00c0
            goto L_0x00c3
        L_0x00c0:
            r16 = r17
            goto L_0x00ce
        L_0x00c3:
            int r4 = r0.bp
            int r16 = r16 + 2
            int r4 = r4 + r17
            char r4 = r0.charAt(r4)
            r15 = r4
        L_0x00ce:
            if (r15 < r13) goto L_0x00df
            if (r15 > r14) goto L_0x00df
            int r4 = r0.bp
            int r11 = r16 + 1
            int r4 = r4 + r16
            char r15 = r0.charAt(r4)
            r16 = r11
            goto L_0x00ce
        L_0x00df:
            int r4 = r0.bp
            int r4 = r4 + r16
            int r4 = r4 - r9
            int r4 = r4 - r10
            if (r2 != 0) goto L_0x00f0
            if (r4 >= r1) goto L_0x00f0
            float r1 = (float) r3
            float r2 = (float) r5
            float r1 = r1 / r2
            if (r12 == 0) goto L_0x00f8
            float r1 = -r1
            goto L_0x00f8
        L_0x00f0:
            java.lang.String r1 = r0.subString(r9, r4)
            float r1 = java.lang.Float.parseFloat(r1)
        L_0x00f8:
            int r2 = r6.length
            r3 = 3
            if (r7 < r2) goto L_0x0107
            int r2 = r6.length
            int r2 = r2 * r3
            int r2 = r2 / 2
            float[] r2 = new float[r2]
            r4 = 0
            java.lang.System.arraycopy(r6, r4, r2, r4, r7)
            r6 = r2
        L_0x0107:
            int r4 = r7 + 1
            r6[r7] = r1
            r1 = 44
            if (r15 != r1) goto L_0x0120
            int r1 = r0.bp
            int r2 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            r15 = r1
            r1 = 0
            r3 = 16
            r11 = 0
            goto L_0x01af
        L_0x0120:
            r2 = 93
            if (r15 != r2) goto L_0x01a9
            int r5 = r0.bp
            int r7 = r16 + 1
            int r5 = r5 + r16
            char r5 = r0.charAt(r5)
            int r9 = r6.length
            if (r4 == r9) goto L_0x0138
            float[] r9 = new float[r4]
            r11 = 0
            java.lang.System.arraycopy(r6, r11, r9, r11, r4)
            r6 = r9
        L_0x0138:
            if (r5 != r1) goto L_0x014a
            int r1 = r0.bp
            int r1 = r1 + r16
            r0.bp = r1
            r19.next()
            r0.matchStat = r3
            r3 = 16
            r0.token = r3
            return r6
        L_0x014a:
            r3 = 16
            r4 = 125(0x7d, float:1.75E-43)
            if (r5 != r4) goto L_0x01a5
            int r5 = r0.bp
            int r5 = r5 + r7
            char r5 = r0.charAt(r5)
            if (r5 != r1) goto L_0x0167
            r0.token = r3
            int r1 = r0.bp
            int r16 = r16 + 1
            int r1 = r1 + r16
            r0.bp = r1
            r19.next()
            goto L_0x019d
        L_0x0167:
            if (r5 != r2) goto L_0x0179
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r16 = r16 + 1
            int r1 = r1 + r16
            r0.bp = r1
            r19.next()
            goto L_0x019d
        L_0x0179:
            if (r5 != r4) goto L_0x018b
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r16 = r16 + 1
            int r1 = r1 + r16
            r0.bp = r1
            r19.next()
            goto L_0x019d
        L_0x018b:
            r1 = 26
            if (r5 != r1) goto L_0x01a1
            int r2 = r0.bp
            int r16 = r16 + 1
            int r2 = r2 + r16
            r0.bp = r2
            r2 = 20
            r0.token = r2
            r0.ch = r1
        L_0x019d:
            r1 = 4
            r0.matchStat = r1
            return r6
        L_0x01a1:
            r0.matchStat = r8
            r1 = 0
            return r1
        L_0x01a5:
            r1 = 0
            r0.matchStat = r8
            return r1
        L_0x01a9:
            r1 = 0
            r3 = 16
            r11 = 0
            r2 = r16
        L_0x01af:
            r5 = r3
            r7 = r4
            r3 = r15
            r4 = r1
            r1 = r11
            goto L_0x0031
        L_0x01b6:
            r1 = r4
            r0.matchStat = r8
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray(char[]):float[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00bd, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x013e, code lost:
        r4 = r18 + 1;
        r1 = charAt(r0.bp + r18);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0149, code lost:
        if (r2 == r3.length) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x014b, code lost:
        r5 = new float[r2];
        r7 = 0;
        java.lang.System.arraycopy(r3, 0, r5, 0, r2);
        r3 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0155, code lost:
        if (r8 < r6.length) goto L_0x0162;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0157, code lost:
        r5 = new float[((r6.length * 3) / 2)][];
        java.lang.System.arraycopy(r3, r7, r5, r7, r2);
        r6 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0162, code lost:
        r5 = r8 + 1;
        r6[r8] = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0168, code lost:
        if (r1 != ',') goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x016a, code lost:
        r3 = charAt(r0.bp + r4);
        r2 = r18 + 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0179, code lost:
        if (r1 != ']') goto L_0x0186;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x017b, code lost:
        r2 = r18 + 2;
        r3 = charAt(r0.bp + r4);
        r8 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0186, code lost:
        r3 = r1;
        r2 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x01a2, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final float[][] scanFieldFloatArray2(char[] r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = 0
            r0.matchStat = r1
            boolean r2 = r21.charArrayCompare(r22)
            r3 = -2
            r4 = 0
            if (r2 != 0) goto L_0x0013
            r0.matchStat = r3
            r1 = r4
            float[][] r1 = (float[][]) r1
            return r4
        L_0x0013:
            r2 = r22
            int r2 = r2.length
            int r5 = r0.bp
            int r6 = r2 + 1
            int r5 = r5 + r2
            char r5 = r0.charAt(r5)
            r7 = 91
            if (r5 == r7) goto L_0x0029
            r0.matchStat = r3
            r1 = r4
            float[][] r1 = (float[][]) r1
            return r4
        L_0x0029:
            int r3 = r0.bp
            int r2 = r2 + 2
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
            r5 = 16
            float[][] r6 = new float[r5][]
            r8 = r1
        L_0x0037:
            r11 = -1
            r13 = 1
            if (r3 != r7) goto L_0x01a3
            int r3 = r0.bp
            int r14 = r2 + 1
            int r3 = r3 + r2
            char r2 = r0.charAt(r3)
            float[] r3 = new float[r5]
            r15 = r1
        L_0x0047:
            int r7 = r0.bp
            int r16 = r7 + r14
            int r5 = r16 + -1
            r9 = 45
            if (r2 != r9) goto L_0x0054
            r17 = r13
            goto L_0x0056
        L_0x0054:
            r17 = r1
        L_0x0056:
            if (r17 == 0) goto L_0x0061
            int r2 = r14 + 1
            int r7 = r7 + r14
            char r7 = r0.charAt(r7)
            r14 = r2
            r2 = r7
        L_0x0061:
            r7 = 48
            if (r2 < r7) goto L_0x019c
            r12 = 57
            if (r2 > r12) goto L_0x019c
            int r2 = r2 + -48
        L_0x006b:
            int r1 = r0.bp
            int r18 = r14 + 1
            int r1 = r1 + r14
            char r1 = r0.charAt(r1)
            if (r1 < r7) goto L_0x0080
            if (r1 > r12) goto L_0x0080
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            r14 = r18
            goto L_0x006b
        L_0x0080:
            r10 = 46
            r13 = 10
            if (r1 != r10) goto L_0x00be
            int r1 = r0.bp
            int r14 = r14 + 2
            int r1 = r1 + r18
            char r1 = r0.charAt(r1)
            if (r1 < r7) goto L_0x00b8
            if (r1 > r12) goto L_0x00b8
            int r2 = r2 * 10
            int r1 = r1 + -48
            int r2 = r2 + r1
            r1 = r13
        L_0x009a:
            int r10 = r0.bp
            int r18 = r14 + 1
            int r10 = r10 + r14
            char r10 = r0.charAt(r10)
            if (r10 < r7) goto L_0x00b1
            if (r10 > r12) goto L_0x00b1
            int r2 = r2 * 10
            int r10 = r10 + -48
            int r2 = r2 + r10
            int r1 = r1 * 10
            r14 = r18
            goto L_0x009a
        L_0x00b1:
            r20 = r2
            r2 = r1
            r1 = r10
            r10 = r20
            goto L_0x00c0
        L_0x00b8:
            r0.matchStat = r11
            r1 = r4
            float[][] r1 = (float[][]) r1
            return r4
        L_0x00be:
            r10 = r2
            r2 = 1
        L_0x00c0:
            r14 = 101(0x65, float:1.42E-43)
            if (r1 == r14) goto L_0x00cb
            r14 = 69
            if (r1 != r14) goto L_0x00c9
            goto L_0x00cb
        L_0x00c9:
            r14 = 0
            goto L_0x00cc
        L_0x00cb:
            r14 = 1
        L_0x00cc:
            if (r14 == 0) goto L_0x00fd
            int r1 = r0.bp
            int r19 = r18 + 1
            int r1 = r1 + r18
            char r1 = r0.charAt(r1)
            r4 = 43
            if (r1 == r4) goto L_0x00e2
            if (r1 != r9) goto L_0x00df
            goto L_0x00e2
        L_0x00df:
            r18 = r19
            goto L_0x00ec
        L_0x00e2:
            int r1 = r0.bp
            int r18 = r18 + 2
            int r1 = r1 + r19
            char r1 = r0.charAt(r1)
        L_0x00ec:
            if (r1 < r7) goto L_0x00fd
            if (r1 > r12) goto L_0x00fd
            int r1 = r0.bp
            int r4 = r18 + 1
            int r1 = r1 + r18
            char r1 = r0.charAt(r1)
            r18 = r4
            goto L_0x00ec
        L_0x00fd:
            int r4 = r0.bp
            int r4 = r4 + r18
            int r4 = r4 - r5
            r7 = 1
            int r4 = r4 - r7
            if (r14 != 0) goto L_0x010f
            if (r4 >= r13) goto L_0x010f
            float r4 = (float) r10
            float r2 = (float) r2
            float r4 = r4 / r2
            if (r17 == 0) goto L_0x0117
            float r4 = -r4
            goto L_0x0117
        L_0x010f:
            java.lang.String r2 = r0.subString(r5, r4)
            float r4 = java.lang.Float.parseFloat(r2)
        L_0x0117:
            int r2 = r3.length
            if (r15 < r2) goto L_0x0126
            int r2 = r3.length
            r5 = 3
            int r2 = r2 * r5
            int r2 = r2 / 2
            float[] r2 = new float[r2]
            r5 = 0
            java.lang.System.arraycopy(r3, r5, r2, r5, r15)
            r3 = r2
        L_0x0126:
            int r2 = r15 + 1
            r3[r15] = r4
            r4 = 44
            if (r1 != r4) goto L_0x013a
            int r1 = r0.bp
            int r4 = r18 + 1
            int r1 = r1 + r18
            char r1 = r0.charAt(r1)
            r14 = r4
            goto L_0x0193
        L_0x013a:
            r4 = 93
            if (r1 != r4) goto L_0x0191
            int r1 = r0.bp
            int r4 = r18 + 1
            int r1 = r1 + r18
            char r1 = r0.charAt(r1)
            int r5 = r3.length
            if (r2 == r5) goto L_0x0153
            float[] r5 = new float[r2]
            r7 = 0
            java.lang.System.arraycopy(r3, r7, r5, r7, r2)
            r3 = r5
            goto L_0x0154
        L_0x0153:
            r7 = 0
        L_0x0154:
            int r5 = r6.length
            if (r8 < r5) goto L_0x0162
            int r5 = r6.length
            r6 = 3
            int r5 = r5 * r6
            int r5 = r5 / 2
            float[][] r5 = new float[r5][]
            java.lang.System.arraycopy(r3, r7, r5, r7, r2)
            r6 = r5
        L_0x0162:
            int r5 = r8 + 1
            r6[r8] = r3
            r2 = 44
            if (r1 != r2) goto L_0x0177
            int r1 = r0.bp
            int r18 = r18 + 2
            int r1 = r1 + r4
            char r1 = r0.charAt(r1)
            r3 = r1
            r2 = r18
            goto L_0x0188
        L_0x0177:
            r2 = 93
            if (r1 != r2) goto L_0x0186
            int r1 = r0.bp
            int r2 = r18 + 2
            int r1 = r1 + r4
            char r3 = r0.charAt(r1)
            r8 = r5
            goto L_0x01a3
        L_0x0186:
            r3 = r1
            r2 = r4
        L_0x0188:
            r8 = r5
            r1 = 0
            r4 = 0
            r5 = 16
            r7 = 91
            goto L_0x0037
        L_0x0191:
            r14 = r18
        L_0x0193:
            r15 = r2
            r4 = 0
            r5 = 16
            r13 = 1
            r2 = r1
            r1 = 0
            goto L_0x0047
        L_0x019c:
            r0.matchStat = r11
            r1 = 0
            r4 = r1
            float[][] r4 = (float[][]) r4
            return r1
        L_0x01a3:
            int r1 = r6.length
            if (r8 == r1) goto L_0x01ad
            float[][] r1 = new float[r8][]
            r4 = 0
            java.lang.System.arraycopy(r6, r4, r1, r4, r8)
            r6 = r1
        L_0x01ad:
            r1 = 44
            if (r3 != r1) goto L_0x01c3
            int r1 = r0.bp
            r3 = 1
            int r2 = r2 - r3
            int r1 = r1 + r2
            r0.bp = r1
            r21.next()
            r1 = 3
            r0.matchStat = r1
            r1 = 16
            r0.token = r1
            return r6
        L_0x01c3:
            r1 = 16
            r4 = 125(0x7d, float:1.75E-43)
            if (r3 != r4) goto L_0x0219
            int r3 = r0.bp
            int r3 = r3 + r2
            char r3 = r0.charAt(r3)
            r5 = 44
            if (r3 != r5) goto L_0x01df
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            r21.next()
            goto L_0x020e
        L_0x01df:
            r1 = 93
            if (r3 != r1) goto L_0x01f0
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            r21.next()
            goto L_0x020e
        L_0x01f0:
            if (r3 != r4) goto L_0x01ff
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r2
            r0.bp = r1
            r21.next()
            goto L_0x020e
        L_0x01ff:
            r1 = 26
            if (r3 != r1) goto L_0x0212
            int r3 = r0.bp
            int r3 = r3 + r2
            r0.bp = r3
            r2 = 20
            r0.token = r2
            r0.ch = r1
        L_0x020e:
            r1 = 4
            r0.matchStat = r1
            return r6
        L_0x0212:
            r0.matchStat = r11
            r1 = 0
            r4 = r1
            float[][] r4 = (float[][]) r4
            return r1
        L_0x0219:
            r1 = 0
            r0.matchStat = r11
            r4 = r1
            float[][] r4 = (float[][]) r4
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldFloatArray2(char[]):float[][]");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final double scanFieldDouble(char[] r21) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r20.charArrayCompare(r21)
            r4 = 0
            if (r3 != 0) goto L_0x0013
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0013:
            int r3 = r1.length
            int r6 = r0.bp
            int r7 = r3 + 1
            int r6 = r6 + r3
            char r6 = r0.charAt(r6)
            r8 = 34
            if (r6 != r8) goto L_0x0023
            r10 = 1
            goto L_0x0024
        L_0x0023:
            r10 = r2
        L_0x0024:
            if (r10 == 0) goto L_0x0030
            int r6 = r0.bp
            int r3 = r3 + 2
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r7 = r3
        L_0x0030:
            r3 = 45
            if (r6 != r3) goto L_0x0036
            r11 = 1
            goto L_0x0037
        L_0x0036:
            r11 = r2
        L_0x0037:
            if (r11 == 0) goto L_0x0043
            int r6 = r0.bp
            int r12 = r7 + 1
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r7 = r12
        L_0x0043:
            r15 = 44
            r2 = -1
            r12 = 48
            if (r6 < r12) goto L_0x019d
            r14 = 57
            if (r6 > r14) goto L_0x019d
            int r6 = r6 - r12
            long r8 = (long) r6
        L_0x0050:
            int r6 = r0.bp
            int r17 = r7 + 1
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            r18 = 10
            if (r6 < r12) goto L_0x0068
            if (r6 > r14) goto L_0x0068
            long r8 = r8 * r18
            int r6 = r6 + -48
            long r6 = (long) r6
            long r8 = r8 + r6
            r7 = r17
            goto L_0x0050
        L_0x0068:
            r13 = 46
            if (r6 != r13) goto L_0x009e
            int r6 = r0.bp
            int r7 = r7 + 2
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            if (r6 < r12) goto L_0x0099
            if (r6 > r14) goto L_0x0099
            long r8 = r8 * r18
            int r6 = r6 - r12
            long r3 = (long) r6
            long r8 = r8 + r3
            r3 = r18
        L_0x0081:
            int r6 = r0.bp
            int r17 = r7 + 1
            int r6 = r6 + r7
            char r6 = r0.charAt(r6)
            if (r6 < r12) goto L_0x00a0
            if (r6 > r14) goto L_0x00a0
            long r8 = r8 * r18
            int r6 = r6 + -48
            long r6 = (long) r6
            long r8 = r8 + r6
            long r3 = r3 * r18
            r7 = r17
            goto L_0x0081
        L_0x0099:
            r0.matchStat = r2
        L_0x009b:
            r1 = 0
            return r1
        L_0x009e:
            r3 = 1
        L_0x00a0:
            r7 = 101(0x65, float:1.42E-43)
            if (r6 == r7) goto L_0x00ac
            r7 = 69
            if (r6 != r7) goto L_0x00a9
            goto L_0x00ac
        L_0x00a9:
            r16 = 0
            goto L_0x00ae
        L_0x00ac:
            r16 = 1
        L_0x00ae:
            if (r16 == 0) goto L_0x00e1
            int r6 = r0.bp
            int r7 = r17 + 1
            int r6 = r6 + r17
            char r6 = r0.charAt(r6)
            r13 = 43
            if (r6 == r13) goto L_0x00c6
            r5 = 45
            if (r6 != r5) goto L_0x00c3
            goto L_0x00c6
        L_0x00c3:
            r17 = r7
            goto L_0x00d0
        L_0x00c6:
            int r5 = r0.bp
            int r17 = r17 + 2
            int r5 = r5 + r7
            char r5 = r0.charAt(r5)
        L_0x00cf:
            r6 = r5
        L_0x00d0:
            if (r6 < r12) goto L_0x00e1
            if (r6 > r14) goto L_0x00e1
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r5 = r0.charAt(r5)
            r17 = r6
            goto L_0x00cf
        L_0x00e1:
            if (r10 == 0) goto L_0x0102
            r5 = 34
            if (r6 == r5) goto L_0x00ea
            r0.matchStat = r2
            goto L_0x009b
        L_0x00ea:
            int r5 = r0.bp
            int r6 = r17 + 1
            int r5 = r5 + r17
            char r5 = r0.charAt(r5)
            int r7 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r7
            r10 = 1
            int r1 = r1 + r10
            int r7 = r7 + r6
            int r7 = r7 - r1
            int r7 = r7 + -2
            r17 = r6
            r6 = r5
            goto L_0x010c
        L_0x0102:
            r10 = 1
            int r5 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r5
            int r5 = r5 + r17
            int r5 = r5 - r1
            int r7 = r5 + -1
        L_0x010c:
            if (r16 != 0) goto L_0x0119
            r5 = 17
            if (r7 >= r5) goto L_0x0119
            double r7 = (double) r8
            double r3 = (double) r3
            double r7 = r7 / r3
            if (r11 == 0) goto L_0x0121
            double r7 = -r7
            goto L_0x0121
        L_0x0119:
            java.lang.String r1 = r0.subString(r1, r7)
            double r7 = java.lang.Double.parseDouble(r1)
        L_0x0121:
            if (r6 != r15) goto L_0x0137
            int r1 = r0.bp
            int r1 = r1 + r17
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r1 = 3
            r0.matchStat = r1
            r1 = 16
            r0.token = r1
            return r7
        L_0x0137:
            r1 = 16
            r3 = 125(0x7d, float:1.75E-43)
            if (r6 != r3) goto L_0x0198
            int r3 = r0.bp
            int r4 = r17 + 1
            int r3 = r3 + r17
            char r3 = r0.charAt(r3)
            if (r3 != r15) goto L_0x0157
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x018f
        L_0x0157:
            r1 = 93
            if (r3 != r1) goto L_0x016b
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x018f
        L_0x016b:
            r1 = 125(0x7d, float:1.75E-43)
            if (r3 != r1) goto L_0x017f
            r1 = 13
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r4
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x018f
        L_0x017f:
            r1 = 26
            if (r3 != r1) goto L_0x0193
            r2 = 20
            r0.token = r2
            int r2 = r0.bp
            int r2 = r2 + r17
            r0.bp = r2
            r0.ch = r1
        L_0x018f:
            r1 = 4
            r0.matchStat = r1
            return r7
        L_0x0193:
            r0.matchStat = r2
            r3 = 0
            return r3
        L_0x0198:
            r3 = 0
            r0.matchStat = r2
            return r3
        L_0x019d:
            r1 = 110(0x6e, float:1.54E-43)
            if (r6 != r1) goto L_0x0227
            int r1 = r0.bp
            int r1 = r1 + r7
            char r1 = r0.charAt(r1)
            r3 = 117(0x75, float:1.64E-43)
            if (r1 != r3) goto L_0x0227
            int r1 = r0.bp
            int r1 = r1 + r7
            r3 = 1
            int r1 = r1 + r3
            char r1 = r0.charAt(r1)
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 != r3) goto L_0x0227
            int r1 = r0.bp
            int r1 = r1 + r7
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r3) goto L_0x0227
            r1 = 5
            r0.matchStat = r1
            int r3 = r7 + 3
            int r4 = r0.bp
            int r5 = r7 + 4
            int r4 = r4 + r3
            char r3 = r0.charAt(r4)
            if (r10 == 0) goto L_0x01e1
            r4 = 34
            if (r3 != r4) goto L_0x01e1
            int r3 = r0.bp
            int r7 = r7 + r1
            int r3 = r3 + r5
            char r3 = r0.charAt(r3)
            r5 = r7
        L_0x01e1:
            if (r3 != r15) goto L_0x01f7
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r4 = 16
            r0.token = r4
            r6 = 0
            return r6
        L_0x01f7:
            r4 = 16
            r6 = 0
            r8 = 125(0x7d, float:1.75E-43)
            if (r3 != r8) goto L_0x0211
            int r2 = r0.bp
            int r2 = r2 + r5
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r9 = 13
            r0.token = r9
            return r6
        L_0x0211:
            r9 = 13
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x0224
            int r3 = r0.bp
            int r10 = r5 + 1
            int r3 = r3 + r5
            char r3 = r0.charAt(r3)
            r5 = r10
            goto L_0x01e1
        L_0x0224:
            r0.matchStat = r2
            return r6
        L_0x0227:
            r6 = 0
            r0.matchStat = r2
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDouble(char[]):double");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public java.math.BigDecimal scanFieldDecimal(char[] r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = 0
            r0.matchStat = r2
            boolean r3 = r17.charArrayCompare(r18)
            r4 = 0
            if (r3 != 0) goto L_0x0012
            r1 = -2
            r0.matchStat = r1
            return r4
        L_0x0012:
            int r3 = r1.length
            int r5 = r0.bp
            int r6 = r3 + 1
            int r5 = r5 + r3
            char r5 = r0.charAt(r5)
            r7 = 34
            r8 = 1
            if (r5 != r7) goto L_0x0022
            r2 = r8
        L_0x0022:
            if (r2 == 0) goto L_0x002e
            int r5 = r0.bp
            int r3 = r3 + 2
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            r6 = r3
        L_0x002e:
            r3 = 45
            if (r5 != r3) goto L_0x003c
            int r5 = r0.bp
            int r9 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            r6 = r9
        L_0x003c:
            r10 = 16
            r11 = 125(0x7d, float:1.75E-43)
            r12 = 44
            r13 = 48
            r14 = -1
            if (r5 < r13) goto L_0x0155
            r15 = 57
            if (r5 > r15) goto L_0x0155
        L_0x004b:
            int r5 = r0.bp
            int r16 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            if (r5 < r13) goto L_0x005b
            if (r5 > r15) goto L_0x005b
            r6 = r16
            goto L_0x004b
        L_0x005b:
            r9 = 46
            if (r5 != r9) goto L_0x0080
            int r5 = r0.bp
            int r6 = r6 + 2
            int r5 = r5 + r16
            char r5 = r0.charAt(r5)
            if (r5 < r13) goto L_0x007d
            if (r5 > r15) goto L_0x007d
        L_0x006d:
            int r5 = r0.bp
            int r16 = r6 + 1
            int r5 = r5 + r6
            char r5 = r0.charAt(r5)
            if (r5 < r13) goto L_0x0080
            if (r5 > r15) goto L_0x0080
            r6 = r16
            goto L_0x006d
        L_0x007d:
            r0.matchStat = r14
            return r4
        L_0x0080:
            r6 = 101(0x65, float:1.42E-43)
            if (r5 == r6) goto L_0x0088
            r6 = 69
            if (r5 != r6) goto L_0x00b7
        L_0x0088:
            int r5 = r0.bp
            int r6 = r16 + 1
            int r5 = r5 + r16
            char r5 = r0.charAt(r5)
            r9 = 43
            if (r5 == r9) goto L_0x009c
            if (r5 != r3) goto L_0x0099
            goto L_0x009c
        L_0x0099:
            r16 = r6
            goto L_0x00a6
        L_0x009c:
            int r3 = r0.bp
            int r16 = r16 + 2
            int r3 = r3 + r6
            char r3 = r0.charAt(r3)
        L_0x00a5:
            r5 = r3
        L_0x00a6:
            if (r5 < r13) goto L_0x00b7
            if (r5 > r15) goto L_0x00b7
            int r3 = r0.bp
            int r5 = r16 + 1
            int r3 = r3 + r16
            char r3 = r0.charAt(r3)
            r16 = r5
            goto L_0x00a5
        L_0x00b7:
            if (r2 == 0) goto L_0x00d4
            if (r5 == r7) goto L_0x00be
            r0.matchStat = r14
            return r4
        L_0x00be:
            int r2 = r0.bp
            int r3 = r16 + 1
            int r2 = r2 + r16
            char r5 = r0.charAt(r2)
            int r2 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r2
            int r1 = r1 + r8
            int r2 = r2 + r3
            int r2 = r2 - r1
            int r2 = r2 + -2
            r16 = r3
            goto L_0x00dc
        L_0x00d4:
            int r2 = r0.bp
            int r1 = r1.length
            int r1 = r1 + r2
            int r2 = r2 + r16
            int r2 = r2 - r1
            int r2 = r2 - r8
        L_0x00dc:
            char[] r1 = r0.sub_chars(r1, r2)
            java.math.BigDecimal r2 = new java.math.BigDecimal
            r2.<init>(r1)
            if (r5 != r12) goto L_0x00f9
            int r1 = r0.bp
            int r1 = r1 + r16
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            r1 = 3
            r0.matchStat = r1
            r0.token = r10
            return r2
        L_0x00f9:
            if (r5 != r11) goto L_0x0152
            int r1 = r0.bp
            int r3 = r16 + 1
            int r1 = r1 + r16
            char r1 = r0.charAt(r1)
            if (r1 != r12) goto L_0x0115
            r0.token = r10
            int r1 = r0.bp
            int r1 = r1 + r3
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x014b
        L_0x0115:
            r5 = 93
            if (r1 != r5) goto L_0x0129
            r1 = 15
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r3
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x014b
        L_0x0129:
            if (r1 != r11) goto L_0x013b
            r5 = 13
            r0.token = r5
            int r1 = r0.bp
            int r1 = r1 + r3
            r0.bp = r1
            char r1 = r0.charAt(r1)
            r0.ch = r1
            goto L_0x014b
        L_0x013b:
            r3 = 26
            if (r1 != r3) goto L_0x014f
            r1 = 20
            r0.token = r1
            int r1 = r0.bp
            int r1 = r1 + r16
            r0.bp = r1
            r0.ch = r3
        L_0x014b:
            r1 = 4
            r0.matchStat = r1
            return r2
        L_0x014f:
            r0.matchStat = r14
            return r4
        L_0x0152:
            r0.matchStat = r14
            return r4
        L_0x0155:
            r1 = 110(0x6e, float:1.54E-43)
            if (r5 != r1) goto L_0x01d2
            int r1 = r0.bp
            int r1 = r1 + r6
            char r1 = r0.charAt(r1)
            r3 = 117(0x75, float:1.64E-43)
            if (r1 != r3) goto L_0x01d2
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + r8
            char r1 = r0.charAt(r1)
            r3 = 108(0x6c, float:1.51E-43)
            if (r1 != r3) goto L_0x01d2
            int r1 = r0.bp
            int r1 = r1 + r6
            int r1 = r1 + 2
            char r1 = r0.charAt(r1)
            if (r1 != r3) goto L_0x01d2
            r1 = 5
            r0.matchStat = r1
            int r3 = r6 + 3
            int r5 = r0.bp
            int r8 = r6 + 4
            int r5 = r5 + r3
            char r3 = r0.charAt(r5)
            if (r2 == 0) goto L_0x0196
            if (r3 != r7) goto L_0x0196
            int r2 = r0.bp
            int r6 = r6 + r1
            int r2 = r2 + r8
            char r3 = r0.charAt(r2)
            r8 = r6
        L_0x0196:
            if (r3 != r12) goto L_0x01a8
            int r2 = r0.bp
            int r2 = r2 + r8
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r0.token = r10
            return r4
        L_0x01a8:
            if (r3 != r11) goto L_0x01bc
            int r2 = r0.bp
            int r2 = r2 + r8
            r0.bp = r2
            char r2 = r0.charAt(r2)
            r0.ch = r2
            r0.matchStat = r1
            r2 = 13
            r0.token = r2
            return r4
        L_0x01bc:
            r2 = 13
            boolean r3 = isWhitespace(r3)
            if (r3 == 0) goto L_0x01cf
            int r3 = r0.bp
            int r5 = r8 + 1
            int r3 = r3 + r8
            char r3 = r0.charAt(r3)
            r8 = r5
            goto L_0x0196
        L_0x01cf:
            r0.matchStat = r14
            return r4
        L_0x01d2:
            r0.matchStat = r14
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanFieldDecimal(char[]):java.math.BigDecimal");
    }

    public BigInteger scanFieldBigInteger(char[] cArr) {
        int i;
        char charAt;
        boolean z;
        int i2;
        int i3;
        BigInteger bigInteger;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr2.length;
        int i4 = length + 1;
        char charAt2 = charAt(this.bp + length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(this.bp + i4);
            i4 = length + 2;
        }
        boolean z3 = charAt2 == '-';
        if (z3) {
            charAt2 = charAt(this.bp + i4);
            i4++;
        }
        if (charAt2 >= '0') {
            char c2 = '9';
            if (charAt2 <= '9') {
                long j = (long) (charAt2 - '0');
                while (true) {
                    i = i4 + 1;
                    charAt = charAt(this.bp + i4);
                    if (charAt < '0' || charAt > c2) {
                        z = false;
                    } else {
                        long j2 = (10 * j) + ((long) (charAt - '0'));
                        if (j2 < j) {
                            z = true;
                            break;
                        }
                        j = j2;
                        i4 = i;
                        c2 = '9';
                    }
                }
                z = false;
                if (!z2) {
                    int i5 = this.bp;
                    i3 = cArr2.length + i5;
                    i2 = ((i5 + i) - i3) - 1;
                } else if (charAt != '\"') {
                    this.matchStat = -1;
                    return null;
                } else {
                    int i6 = i4 + 2;
                    charAt = charAt(this.bp + i);
                    int i7 = this.bp;
                    i3 = cArr2.length + i7 + 1;
                    i2 = ((i7 + i6) - i3) - 2;
                    i = i6;
                }
                if (z || (i2 >= 20 && (!z3 || i2 >= 21))) {
                    bigInteger = new BigInteger(subString(i3, i2));
                } else {
                    if (z3) {
                        j = -j;
                    }
                    bigInteger = BigInteger.valueOf(j);
                }
                if (charAt == ',') {
                    int i8 = this.bp + i;
                    this.bp = i8;
                    this.ch = charAt(i8);
                    this.matchStat = 3;
                    this.token = 16;
                    return bigInteger;
                } else if (charAt == '}') {
                    int i9 = i + 1;
                    char charAt3 = charAt(this.bp + i);
                    if (charAt3 == ',') {
                        this.token = 16;
                        int i10 = this.bp + i9;
                        this.bp = i10;
                        this.ch = charAt(i10);
                    } else if (charAt3 == ']') {
                        this.token = 15;
                        int i11 = this.bp + i9;
                        this.bp = i11;
                        this.ch = charAt(i11);
                    } else if (charAt3 == '}') {
                        this.token = 13;
                        int i12 = this.bp + i9;
                        this.bp = i12;
                        this.ch = charAt(i12);
                    } else if (charAt3 == 26) {
                        this.token = 20;
                        this.bp += i;
                        this.ch = JSONLexer.EOI;
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                    this.matchStat = 4;
                    return bigInteger;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        }
        if (charAt2 == 'n' && charAt(this.bp + i4) == 'u' && charAt(this.bp + i4 + 1) == 'l' && charAt(this.bp + i4 + 2) == 'l') {
            this.matchStat = 5;
            int i13 = i4 + 4;
            char charAt4 = charAt(this.bp + i4 + 3);
            if (z2 && charAt4 == '\"') {
                charAt4 = charAt(this.bp + i13);
                i13 = i4 + 5;
            }
            while (charAt4 != ',') {
                if (charAt4 == '}') {
                    int i14 = this.bp + i13;
                    this.bp = i14;
                    this.ch = charAt(i14);
                    this.matchStat = 5;
                    this.token = 13;
                    return null;
                } else if (isWhitespace(charAt4)) {
                    charAt4 = charAt(this.bp + i13);
                    i13++;
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
            int i15 = this.bp + i13;
            this.bp = i15;
            this.ch = charAt(i15);
            this.matchStat = 5;
            this.token = 16;
            return null;
        }
        this.matchStat = -1;
        return null;
    }

    public Date scanFieldDate(char[] cArr) {
        int i;
        char c2;
        Date date;
        long j;
        int i2;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr.length;
        int i3 = length + 1;
        char charAt2 = charAt(this.bp + length);
        if (charAt2 == '\"') {
            int indexOf = indexOf(Typography.quote, this.bp + cArr.length + 1);
            if (indexOf != -1) {
                int length2 = this.bp + cArr.length + 1;
                String subString = subString(length2, indexOf - length2);
                if (subString.indexOf(92) != -1) {
                    while (true) {
                        int i4 = indexOf - 1;
                        int i5 = 0;
                        while (i4 >= 0 && charAt(i4) == '\\') {
                            i5++;
                            i4--;
                        }
                        if (i5 % 2 == 0) {
                            break;
                        }
                        indexOf = indexOf(Typography.quote, indexOf + 1);
                    }
                    int i6 = this.bp;
                    int length3 = indexOf - ((cArr.length + i6) + 1);
                    subString = readString(sub_chars(i6 + cArr.length + 1, length3), length3);
                }
                int i7 = this.bp;
                int length4 = i3 + (indexOf - ((cArr.length + i7) + 1)) + 1;
                i = length4 + 1;
                c2 = charAt(i7 + length4);
                JSONScanner jSONScanner = new JSONScanner(subString);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch(false)) {
                        date = jSONScanner.getCalendar().getTime();
                    } else {
                        this.matchStat = -1;
                        jSONScanner.close();
                        return null;
                    }
                } finally {
                    jSONScanner.close();
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt2 == '-' || (charAt2 >= '0' && charAt2 <= '9')) {
            if (charAt2 == '-') {
                charAt2 = charAt(this.bp + i3);
                i3 = length + 2;
                z = true;
            }
            if (charAt2 < '0' || charAt2 > '9') {
                c2 = charAt2;
                i = i3;
                j = 0;
            } else {
                j = (long) (charAt2 - '0');
                while (true) {
                    i2 = i3 + 1;
                    charAt = charAt(this.bp + i3);
                    if (charAt < '0' || charAt > '9') {
                        int i8 = i2;
                        c2 = charAt;
                        i = i8;
                    } else {
                        j = (j * 10) + ((long) (charAt - '0'));
                        i3 = i2;
                    }
                }
                int i82 = i2;
                c2 = charAt;
                i = i82;
            }
            if (j < 0) {
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        } else {
            this.matchStat = -1;
            return null;
        }
        if (c2 == ',') {
            int i9 = this.bp + i;
            this.bp = i9;
            this.ch = charAt(i9);
            this.matchStat = 3;
            return date;
        } else if (c2 == '}') {
            int i10 = i + 1;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                int i11 = this.bp + i10;
                this.bp = i11;
                this.ch = charAt(i11);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i12 = this.bp + i10;
                this.bp = i12;
                this.ch = charAt(i12);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i13 = this.bp + i10;
                this.bp = i13;
                this.ch = charAt(i13);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public Date scanDate(char c2) {
        char c3;
        int i;
        Date date;
        long j;
        int i2;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        char charAt2 = charAt(this.bp);
        int i3 = 1;
        if (charAt2 == '\"') {
            int indexOf = indexOf(Typography.quote, this.bp + 1);
            if (indexOf != -1) {
                int i4 = this.bp + 1;
                String subString = subString(i4, indexOf - i4);
                if (subString.indexOf(92) != -1) {
                    while (true) {
                        int i5 = indexOf - 1;
                        int i6 = 0;
                        while (i5 >= 0 && charAt(i5) == '\\') {
                            i6++;
                            i5--;
                        }
                        if (i6 % 2 == 0) {
                            break;
                        }
                        indexOf = indexOf(Typography.quote, indexOf + 1);
                    }
                    int i7 = this.bp;
                    int i8 = indexOf - (i7 + 1);
                    subString = readString(sub_chars(i7 + 1, i8), i8);
                }
                int i9 = this.bp;
                int i10 = indexOf - (i9 + 1);
                int i11 = i10 + 2;
                i = i10 + 3;
                c3 = charAt(i9 + i11);
                JSONScanner jSONScanner = new JSONScanner(subString);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch(false)) {
                        date = jSONScanner.getCalendar().getTime();
                    } else {
                        this.matchStat = -1;
                        jSONScanner.close();
                        return null;
                    }
                } finally {
                    jSONScanner.close();
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            char c4 = '9';
            if (charAt2 == '-' || (charAt2 >= '0' && charAt2 <= '9')) {
                if (charAt2 == '-') {
                    charAt2 = charAt(this.bp + 1);
                    z = true;
                    i3 = 2;
                }
                if (charAt2 < '0' || charAt2 > '9') {
                    j = 0;
                    int i12 = i3;
                    c3 = charAt2;
                    i = i12;
                } else {
                    j = (long) (charAt2 - '0');
                    while (true) {
                        i2 = i3 + 1;
                        charAt = charAt(this.bp + i3);
                        if (charAt < '0' || charAt > c4) {
                            c3 = charAt;
                            i = i2;
                        } else {
                            j = (j * 10) + ((long) (charAt - '0'));
                            i3 = i2;
                            c4 = '9';
                        }
                    }
                    c3 = charAt;
                    i = i2;
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
            } else if (charAt2 == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
                i = 5;
                this.matchStat = 5;
                c3 = charAt(this.bp + 4);
                date = null;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (c3 == ',') {
            int i13 = this.bp + i;
            this.bp = i13;
            this.ch = charAt(i13);
            this.matchStat = 3;
            this.token = 16;
            return date;
        } else if (c3 == ']') {
            int i14 = i + 1;
            char charAt3 = charAt(this.bp + i);
            if (charAt3 == ',') {
                this.token = 16;
                int i15 = this.bp + i14;
                this.bp = i15;
                this.ch = charAt(i15);
            } else if (charAt3 == ']') {
                this.token = 15;
                int i16 = this.bp + i14;
                this.bp = i16;
                this.ch = charAt(i16);
            } else if (charAt3 == '}') {
                this.token = 13;
                int i17 = this.bp + i14;
                this.bp = i17;
                this.ch = charAt(i17);
            } else if (charAt3 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return date;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public UUID scanFieldUUID(char[] cArr) {
        char c2;
        UUID uuid;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return null;
        }
        int length = cArr2.length;
        int i9 = length + 1;
        char charAt = charAt(this.bp + length);
        char c3 = 4;
        if (charAt == '\"') {
            int indexOf = indexOf(Typography.quote, this.bp + cArr2.length + 1);
            if (indexOf != -1) {
                int length2 = this.bp + cArr2.length + 1;
                int i10 = indexOf - length2;
                char c4 = 'f';
                char c5 = 'A';
                char c6 = '9';
                char c7 = 'a';
                if (i10 == 36) {
                    int i11 = 0;
                    long j = 0;
                    while (i11 < 8) {
                        char charAt2 = charAt(length2 + i11);
                        if (charAt2 >= '0' && charAt2 <= '9') {
                            i8 = charAt2 - '0';
                        } else if (charAt2 >= 'a' && charAt2 <= 'f') {
                            i8 = charAt2 - 'W';
                        } else if (charAt2 < 'A' || charAt2 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i8 = charAt2 - '7';
                        }
                        j = (j << c3) | ((long) i8);
                        i11++;
                        c3 = 4;
                    }
                    for (int i12 = 9; i12 < 13; i12++) {
                        char charAt3 = charAt(length2 + i12);
                        if (charAt3 >= '0' && charAt3 <= '9') {
                            i7 = charAt3 - '0';
                        } else if (charAt3 >= 'a' && charAt3 <= 'f') {
                            i7 = charAt3 - 'W';
                        } else if (charAt3 < 'A' || charAt3 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i7 = charAt3 - '7';
                        }
                        j = (j << 4) | ((long) i7);
                    }
                    int i13 = 14;
                    long j2 = j;
                    while (i13 < 18) {
                        char charAt4 = charAt(length2 + i13);
                        if (charAt4 >= '0' && charAt4 <= '9') {
                            i6 = charAt4 - '0';
                        } else if (charAt4 >= 'a' && charAt4 <= c4) {
                            i6 = charAt4 - 'W';
                        } else if (charAt4 < 'A' || charAt4 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i6 = charAt4 - '7';
                        }
                        j2 = (j2 << 4) | ((long) i6);
                        i13++;
                        indexOf = indexOf;
                        c4 = 'f';
                    }
                    int i14 = indexOf;
                    int i15 = 19;
                    long j3 = 0;
                    while (i15 < 23) {
                        char charAt5 = charAt(length2 + i15);
                        if (charAt5 >= '0' && charAt5 <= '9') {
                            i5 = charAt5 - '0';
                        } else if (charAt5 >= c7 && charAt5 <= 'f') {
                            i5 = charAt5 - 'W';
                        } else if (charAt5 < 'A' || charAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i5 = charAt5 - '7';
                        }
                        j3 = (j3 << 4) | ((long) i5);
                        i15++;
                        j2 = j2;
                        c7 = 'a';
                    }
                    long j4 = j2;
                    int i16 = 24;
                    long j5 = j3;
                    while (i16 < 36) {
                        char charAt6 = charAt(length2 + i16);
                        if (charAt6 >= '0' && charAt6 <= c6) {
                            i4 = charAt6 - '0';
                        } else if (charAt6 >= 'a' && charAt6 <= 'f') {
                            i4 = charAt6 - 'W';
                        } else if (charAt6 < c5 || charAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i4 = charAt6 - '7';
                        }
                        j5 = (j5 << 4) | ((long) i4);
                        i16++;
                        c5 = 'A';
                        c6 = '9';
                    }
                    uuid = new UUID(j4, j5);
                    int i17 = this.bp;
                    int length3 = i9 + (i14 - ((cArr2.length + i17) + 1)) + 1;
                    i = length3 + 1;
                    c2 = charAt(i17 + length3);
                } else {
                    int i18 = indexOf;
                    if (i10 == 32) {
                        int i19 = 0;
                        long j6 = 0;
                        for (int i20 = 16; i19 < i20; i20 = 16) {
                            char charAt7 = charAt(length2 + i19);
                            if (charAt7 >= '0' && charAt7 <= '9') {
                                i3 = charAt7 - '0';
                            } else if (charAt7 >= 'a' && charAt7 <= 'f') {
                                i3 = charAt7 - 'W';
                            } else if (charAt7 < 'A' || charAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            } else {
                                i3 = charAt7 - '7';
                            }
                            j6 = (j6 << 4) | ((long) i3);
                            i19++;
                        }
                        int i21 = 16;
                        long j7 = 0;
                        for (int i22 = 32; i21 < i22; i22 = 32) {
                            char charAt8 = charAt(length2 + i21);
                            if (charAt8 >= '0' && charAt8 <= '9') {
                                i2 = charAt8 - '0';
                            } else if (charAt8 >= 'a' && charAt8 <= 'f') {
                                i2 = charAt8 - 'W';
                            } else if (charAt8 < 'A' || charAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            } else {
                                i2 = charAt8 - '7';
                                j7 = (j7 << 4) | ((long) i2);
                                i21++;
                            }
                            j7 = (j7 << 4) | ((long) i2);
                            i21++;
                        }
                        uuid = new UUID(j6, j7);
                        int i23 = this.bp;
                        int length4 = i9 + (i18 - ((cArr2.length + i23) + 1)) + 1;
                        i = length4 + 1;
                        c2 = charAt(i23 + length4);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            if (charAt == 'n') {
                int i24 = length + 2;
                if (charAt(this.bp + i9) == 'u') {
                    int i25 = length + 3;
                    if (charAt(this.bp + i24) == 'l') {
                        int i26 = length + 4;
                        if (charAt(this.bp + i25) == 'l') {
                            int i27 = length + 5;
                            c2 = charAt(this.bp + i26);
                            i = i27;
                            uuid = null;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return null;
        }
        if (c2 == ',') {
            int i28 = this.bp + i;
            this.bp = i28;
            this.ch = charAt(i28);
            this.matchStat = 3;
            return uuid;
        } else if (c2 == '}') {
            int i29 = i + 1;
            char charAt9 = charAt(this.bp + i);
            if (charAt9 == ',') {
                this.token = 16;
                int i30 = this.bp + i29;
                this.bp = i30;
                this.ch = charAt(i30);
            } else if (charAt9 == ']') {
                this.token = 15;
                int i31 = this.bp + i29;
                this.bp = i31;
                this.ch = charAt(i31);
            } else if (charAt9 == '}') {
                this.token = 13;
                int i32 = this.bp + i29;
                this.bp = i32;
                this.ch = charAt(i32);
            } else if (charAt9 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public UUID scanUUID(char c2) {
        int i;
        char c3;
        UUID uuid;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        this.matchStat = 0;
        char charAt = charAt(this.bp);
        if (charAt == '\"') {
            int indexOf = indexOf(Typography.quote, this.bp + 1);
            if (indexOf != -1) {
                int i9 = this.bp + 1;
                int i10 = indexOf - i9;
                char c4 = 'F';
                char c5 = 'A';
                char c6 = 'a';
                char c7 = '0';
                if (i10 == 36) {
                    int i11 = 0;
                    long j = 0;
                    while (i11 < 8) {
                        char charAt2 = charAt(i9 + i11);
                        if (charAt2 >= '0' && charAt2 <= '9') {
                            i8 = charAt2 - '0';
                        } else if (charAt2 >= 'a' && charAt2 <= 'f') {
                            i8 = charAt2 - 'W';
                        } else if (charAt2 < c5 || charAt2 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i8 = charAt2 - '7';
                        }
                        j = (j << 4) | ((long) i8);
                        i11++;
                        indexOf = indexOf;
                        c5 = 'A';
                    }
                    int i12 = indexOf;
                    for (int i13 = 9; i13 < 13; i13++) {
                        char charAt3 = charAt(i9 + i13);
                        if (charAt3 >= '0' && charAt3 <= '9') {
                            i7 = charAt3 - '0';
                        } else if (charAt3 >= 'a' && charAt3 <= 'f') {
                            i7 = charAt3 - 'W';
                        } else if (charAt3 < 'A' || charAt3 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i7 = charAt3 - '7';
                        }
                        j = (j << 4) | ((long) i7);
                    }
                    int i14 = 14;
                    long j2 = j;
                    while (i14 < 18) {
                        char charAt4 = charAt(i9 + i14);
                        if (charAt4 >= '0' && charAt4 <= '9') {
                            i6 = charAt4 - '0';
                        } else if (charAt4 >= c6 && charAt4 <= 'f') {
                            i6 = charAt4 - 'W';
                        } else if (charAt4 < 'A' || charAt4 > c4) {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i6 = charAt4 - '7';
                        }
                        j2 = (j2 << 4) | ((long) i6);
                        i14++;
                        c6 = 'a';
                        c4 = 'F';
                    }
                    int i15 = 19;
                    long j3 = 0;
                    while (i15 < 23) {
                        char charAt5 = charAt(i9 + i15);
                        if (charAt5 >= c7 && charAt5 <= '9') {
                            i5 = charAt5 - '0';
                        } else if (charAt5 >= 'a' && charAt5 <= 'f') {
                            i5 = charAt5 - 'W';
                        } else if (charAt5 < 'A' || charAt5 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i5 = charAt5 - '7';
                        }
                        j3 = (j3 << 4) | ((long) i5);
                        i15++;
                        c7 = '0';
                    }
                    long j4 = j3;
                    for (int i16 = 24; i16 < 36; i16++) {
                        char charAt6 = charAt(i9 + i16);
                        if (charAt6 >= '0' && charAt6 <= '9') {
                            i4 = charAt6 - '0';
                        } else if (charAt6 >= 'a' && charAt6 <= 'f') {
                            i4 = charAt6 - 'W';
                        } else if (charAt6 < 'A' || charAt6 > 'F') {
                            this.matchStat = -2;
                            return null;
                        } else {
                            i4 = charAt6 - '7';
                        }
                        j4 = (j4 << 4) | ((long) i4);
                    }
                    uuid = new UUID(j2, j4);
                    int i17 = this.bp;
                    int i18 = i12 - (i17 + 1);
                    int i19 = i18 + 2;
                    i = i18 + 3;
                    c3 = charAt(i17 + i19);
                } else {
                    int i20 = indexOf;
                    if (i10 == 32) {
                        int i21 = 0;
                        long j5 = 0;
                        for (int i22 = 16; i21 < i22; i22 = 16) {
                            char charAt7 = charAt(i9 + i21);
                            if (charAt7 >= '0' && charAt7 <= '9') {
                                i3 = charAt7 - '0';
                            } else if (charAt7 >= 'a' && charAt7 <= 'f') {
                                i3 = charAt7 - 'W';
                            } else if (charAt7 < 'A' || charAt7 > 'F') {
                                this.matchStat = -2;
                                return null;
                            } else {
                                i3 = charAt7 - '7';
                            }
                            j5 = (j5 << 4) | ((long) i3);
                            i21++;
                        }
                        int i23 = 16;
                        long j6 = 0;
                        for (int i24 = 32; i23 < i24; i24 = 32) {
                            char charAt8 = charAt(i9 + i23);
                            if (charAt8 >= '0' && charAt8 <= '9') {
                                i2 = charAt8 - '0';
                            } else if (charAt8 >= 'a' && charAt8 <= 'f') {
                                i2 = charAt8 - 'W';
                            } else if (charAt8 < 'A' || charAt8 > 'F') {
                                this.matchStat = -2;
                                return null;
                            } else {
                                i2 = charAt8 - '7';
                                j6 = (j6 << 4) | ((long) i2);
                                i23++;
                            }
                            j6 = (j6 << 4) | ((long) i2);
                            i23++;
                        }
                        uuid = new UUID(j5, j6);
                        int i25 = this.bp;
                        int i26 = i20 - (i25 + 1);
                        int i27 = i26 + 2;
                        i = i26 + 3;
                        c3 = charAt(i25 + i27);
                    } else {
                        this.matchStat = -1;
                        return null;
                    }
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt == 'n' && charAt(this.bp + 1) == 'u' && charAt(this.bp + 2) == 'l' && charAt(this.bp + 3) == 'l') {
            c3 = charAt(this.bp + 4);
            i = 5;
            uuid = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        if (c3 == ',') {
            int i28 = this.bp + i;
            this.bp = i28;
            this.ch = charAt(i28);
            this.matchStat = 3;
            return uuid;
        } else if (c3 == ']') {
            int i29 = i + 1;
            char charAt9 = charAt(this.bp + i);
            if (charAt9 == ',') {
                this.token = 16;
                int i30 = this.bp + i29;
                this.bp = i30;
                this.ch = charAt(i30);
            } else if (charAt9 == ']') {
                this.token = 15;
                int i31 = this.bp + i29;
                this.bp = i31;
                this.ch = charAt(i31);
            } else if (charAt9 == '}') {
                this.token = 13;
                int i32 = this.bp + i29;
                this.bp = i32;
                this.ch = charAt(i32);
            } else if (charAt9 == 26) {
                this.token = 20;
                this.bp += i;
                this.ch = JSONLexer.EOI;
            } else {
                this.matchStat = -1;
                return null;
            }
            this.matchStat = 4;
            return uuid;
        } else {
            this.matchStat = -1;
            return null;
        }
    }

    public final void scanTrue() {
        if (this.ch == 't') {
            next();
            if (this.ch == 'r') {
                next();
                if (this.ch == 'u') {
                    next();
                    if (this.ch == 'e') {
                        next();
                        char c2 = this.ch;
                        if (c2 == ' ' || c2 == ',' || c2 == '}' || c2 == ']' || c2 == 10 || c2 == 13 || c2 == 9 || c2 == 26 || c2 == 12 || c2 == 8 || c2 == ':' || c2 == '/') {
                            this.token = 6;
                            return;
                        }
                        throw new JSONException("scan true error");
                    }
                    throw new JSONException("error parse true");
                }
                throw new JSONException("error parse true");
            }
            throw new JSONException("error parse true");
        }
        throw new JSONException("error parse true");
    }

    public final void scanNullOrNew() {
        scanNullOrNew(true);
    }

    public final void scanNullOrNew(boolean z) {
        if (this.ch == 'n') {
            next();
            char c2 = this.ch;
            if (c2 == 'u') {
                next();
                if (this.ch == 'l') {
                    next();
                    if (this.ch == 'l') {
                        next();
                        char c3 = this.ch;
                        if (c3 == ' ' || c3 == ',' || c3 == '}' || c3 == ']' || c3 == 10 || c3 == 13 || c3 == 9 || c3 == 26 || ((c3 == ':' && z) || c3 == 12 || c3 == 8)) {
                            this.token = 8;
                            return;
                        }
                        throw new JSONException("scan null error");
                    }
                    throw new JSONException("error parse null");
                }
                throw new JSONException("error parse null");
            } else if (c2 == 'e') {
                next();
                if (this.ch == 'w') {
                    next();
                    char c4 = this.ch;
                    if (c4 == ' ' || c4 == ',' || c4 == '}' || c4 == ']' || c4 == 10 || c4 == 13 || c4 == 9 || c4 == 26 || c4 == 12 || c4 == 8) {
                        this.token = 9;
                        return;
                    }
                    throw new JSONException("scan new error");
                }
                throw new JSONException("error parse new");
            } else {
                throw new JSONException("error parse new");
            }
        } else {
            throw new JSONException("error parse null or new");
        }
    }

    public final void scanFalse() {
        if (this.ch == 'f') {
            next();
            if (this.ch == 'a') {
                next();
                if (this.ch == 'l') {
                    next();
                    if (this.ch == 's') {
                        next();
                        if (this.ch == 'e') {
                            next();
                            char c2 = this.ch;
                            if (c2 == ' ' || c2 == ',' || c2 == '}' || c2 == ']' || c2 == 10 || c2 == 13 || c2 == 9 || c2 == 26 || c2 == 12 || c2 == 8 || c2 == ':' || c2 == '/') {
                                this.token = 7;
                                return;
                            }
                            throw new JSONException("scan false error");
                        }
                        throw new JSONException("error parse false");
                    }
                    throw new JSONException("error parse false");
                }
                throw new JSONException("error parse false");
            }
            throw new JSONException("error parse false");
        }
        throw new JSONException("error parse false");
    }

    public final void scanIdent() {
        this.np = this.bp - 1;
        this.hasSpecial = false;
        do {
            this.sp++;
            next();
        } while (Character.isLetterOrDigit(this.ch));
        String stringVal = stringVal();
        if ("null".equalsIgnoreCase(stringVal)) {
            this.token = 8;
        } else if ("new".equals(stringVal)) {
            this.token = 9;
        } else if ("true".equals(stringVal)) {
            this.token = 6;
        } else if ("false".equals(stringVal)) {
            this.token = 7;
        } else if (AdError.UNDEFINED_DOMAIN.equals(stringVal)) {
            this.token = 23;
        } else if ("Set".equals(stringVal)) {
            this.token = 21;
        } else if ("TreeSet".equals(stringVal)) {
            this.token = 22;
        } else {
            this.token = 18;
        }
    }

    public static String readString(char[] cArr, int i) {
        int i2;
        int i3;
        int i4;
        int i5 = i;
        char[] cArr2 = new char[i5];
        int i6 = 0;
        int i7 = 0;
        while (i2 < i5) {
            char c2 = cArr[i2];
            if (c2 != '\\') {
                cArr2[i7] = c2;
                i7++;
            } else {
                int i8 = i2 + 1;
                char c3 = cArr[i8];
                if (c3 == '\"') {
                    i3 = i7 + 1;
                    cArr2[i7] = Typography.quote;
                } else if (c3 != '\'') {
                    if (c3 != 'F') {
                        if (c3 == '\\') {
                            i3 = i7 + 1;
                            cArr2[i7] = '\\';
                        } else if (c3 == 'b') {
                            i3 = i7 + 1;
                            cArr2[i7] = 8;
                        } else if (c3 != 'f') {
                            if (c3 == 'n') {
                                i3 = i7 + 1;
                                cArr2[i7] = 10;
                            } else if (c3 != 'r') {
                                if (c3 != 'x') {
                                    switch (c3) {
                                        case '/':
                                            i3 = i7 + 1;
                                            cArr2[i7] = '/';
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                                            i3 = i7 + 1;
                                            cArr2[i7] = 0;
                                            break;
                                        case '1':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 1;
                                            break;
                                        case '2':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 2;
                                            break;
                                        case '3':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 3;
                                            break;
                                        case '4':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 4;
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                                            i3 = i7 + 1;
                                            cArr2[i7] = 5;
                                            break;
                                        case '6':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 6;
                                            break;
                                        case '7':
                                            i3 = i7 + 1;
                                            cArr2[i7] = 7;
                                            break;
                                        default:
                                            switch (c3) {
                                                case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /*116*/:
                                                    i3 = i7 + 1;
                                                    cArr2[i7] = 9;
                                                    break;
                                                case ModuleDescriptor.MODULE_VERSION /*117*/:
                                                    i4 = i7 + 1;
                                                    char c4 = cArr[i2 + 2];
                                                    char c5 = cArr[i2 + 3];
                                                    char c6 = cArr[i2 + 4];
                                                    i2 += 5;
                                                    cArr2[i7] = (char) Integer.parseInt(new String(new char[]{c4, c5, c6, cArr[i2]}), 16);
                                                    break;
                                                case 'v':
                                                    i3 = i7 + 1;
                                                    cArr2[i7] = 11;
                                                    break;
                                                default:
                                                    throw new JSONException("unclosed.str.lit");
                                            }
                                    }
                                } else {
                                    i4 = i7 + 1;
                                    int[] iArr = digits;
                                    i2 += 3;
                                    cArr2[i7] = (char) ((iArr[cArr[i2 + 2]] * 16) + iArr[cArr[i2]]);
                                }
                                i7 = i4;
                            } else {
                                i3 = i7 + 1;
                                cArr2[i7] = 13;
                            }
                        }
                    }
                    i3 = i7 + 1;
                    cArr2[i7] = 12;
                } else {
                    i3 = i7 + 1;
                    cArr2[i7] = '\'';
                }
                i7 = i3;
                i2 = i8;
            }
            i6 = i2 + 1;
        }
        return new String(cArr2, 0, i7);
    }

    public boolean isBlankInput() {
        int i = 0;
        while (true) {
            char charAt = charAt(i);
            if (charAt == 26) {
                this.token = 20;
                return true;
            } else if (!isWhitespace(charAt)) {
                return false;
            } else {
                i++;
            }
        }
    }

    public final void skipWhitespace() {
        while (true) {
            char c2 = this.ch;
            if (c2 > '/') {
                return;
            }
            if (c2 == ' ' || c2 == 13 || c2 == 10 || c2 == 9 || c2 == 12 || c2 == 8) {
                next();
            } else if (c2 == '/') {
                skipComment();
            } else {
                return;
            }
        }
    }

    private void scanStringSingleQuote() {
        char next;
        char next2;
        this.np = this.bp;
        this.hasSpecial = false;
        while (true) {
            char next3 = next();
            if (next3 == '\'') {
                this.token = 4;
                next();
                return;
            } else if (next3 != 26) {
                boolean z = true;
                if (next3 == '\\') {
                    if (!this.hasSpecial) {
                        this.hasSpecial = true;
                        int i = this.sp;
                        char[] cArr = this.sbuf;
                        if (i > cArr.length) {
                            char[] cArr2 = new char[(i * 2)];
                            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                            this.sbuf = cArr2;
                        }
                        copyTo(this.np + 1, this.sp, this.sbuf);
                    }
                    char next4 = next();
                    if (next4 == '\"') {
                        putChar(Typography.quote);
                    } else if (next4 != '\'') {
                        if (next4 != 'F') {
                            if (next4 == '\\') {
                                putChar('\\');
                            } else if (next4 == 'b') {
                                putChar(8);
                            } else if (next4 != 'f') {
                                if (next4 == 'n') {
                                    putChar(10);
                                } else if (next4 == 'r') {
                                    putChar(13);
                                } else if (next4 != 'x') {
                                    switch (next4) {
                                        case '/':
                                            putChar('/');
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE:
                                            putChar(0);
                                            break;
                                        case '1':
                                            putChar(1);
                                            break;
                                        case '2':
                                            putChar(2);
                                            break;
                                        case '3':
                                            putChar(3);
                                            break;
                                        case '4':
                                            putChar(4);
                                            break;
                                        case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF:
                                            putChar(5);
                                            break;
                                        case '6':
                                            putChar(6);
                                            break;
                                        case '7':
                                            putChar(7);
                                            break;
                                        default:
                                            switch (next4) {
                                                case AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID /*116*/:
                                                    putChar(9);
                                                    break;
                                                case ModuleDescriptor.MODULE_VERSION /*117*/:
                                                    putChar((char) Integer.parseInt(new String(new char[]{next(), next(), next(), next()}), 16));
                                                    break;
                                                case 'v':
                                                    putChar(11);
                                                    break;
                                                default:
                                                    this.ch = next4;
                                                    throw new JSONException("unclosed single-quote string");
                                            }
                                    }
                                } else {
                                    next = next();
                                    next2 = next();
                                    boolean z2 = (next >= '0' && next <= '9') || (next >= 'a' && next <= 'f') || (next >= 'A' && next <= 'F');
                                    if ((next2 < '0' || next2 > '9') && ((next2 < 'a' || next2 > 'f') && (next2 < 'A' || next2 > 'F'))) {
                                        z = false;
                                    }
                                    if (!z2 || !z) {
                                    } else {
                                        int[] iArr = digits;
                                        putChar((char) ((iArr[next] * 16) + iArr[next2]));
                                    }
                                }
                            }
                        }
                        putChar(12);
                    } else {
                        putChar('\'');
                    }
                } else if (!this.hasSpecial) {
                    this.sp++;
                } else {
                    int i2 = this.sp;
                    char[] cArr3 = this.sbuf;
                    if (i2 == cArr3.length) {
                        putChar(next3);
                    } else {
                        this.sp = i2 + 1;
                        cArr3[i2] = next3;
                    }
                }
            } else if (!isEOF()) {
                putChar(JSONLexer.EOI);
            } else {
                throw new JSONException("unclosed single-quote string");
            }
        }
        throw new JSONException("invalid escape character \\x" + next + next2);
    }

    /* access modifiers changed from: protected */
    public final void putChar(char c2) {
        int i = this.sp;
        char[] cArr = this.sbuf;
        if (i == cArr.length) {
            char[] cArr2 = new char[(cArr.length * 2)];
            System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
            this.sbuf = cArr2;
        }
        char[] cArr3 = this.sbuf;
        int i2 = this.sp;
        this.sp = i2 + 1;
        cArr3[i2] = c2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanHex() {
        /*
            r5 = this;
            char r0 = r5.ch
            r1 = 120(0x78, float:1.68E-43)
            java.lang.String r2 = "illegal state. "
            if (r0 != r1) goto L_0x0073
            r5.next()
            char r0 = r5.ch
            r1 = 39
            if (r0 != r1) goto L_0x005f
            int r0 = r5.bp
            r5.np = r0
            r5.next()
            char r0 = r5.ch
            r3 = 26
            if (r0 != r1) goto L_0x0024
            r5.next()
            r5.token = r3
            return
        L_0x0024:
            char r0 = r5.next()
            r4 = 48
            if (r0 < r4) goto L_0x0030
            r4 = 57
            if (r0 <= r4) goto L_0x0038
        L_0x0030:
            r4 = 65
            if (r0 < r4) goto L_0x003f
            r4 = 70
            if (r0 > r4) goto L_0x003f
        L_0x0038:
            int r0 = r5.sp
            int r0 = r0 + 1
            r5.sp = r0
            goto L_0x0024
        L_0x003f:
            if (r0 != r1) goto L_0x004d
            int r0 = r5.sp
            int r0 = r0 + 1
            r5.sp = r0
            r5.next()
            r5.token = r3
            return
        L_0x004d:
            com.alibaba.fastjson.JSONException r1 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r1.<init>(r0)
            throw r1
        L_0x005f:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            char r2 = r5.ch
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0073:
            com.alibaba.fastjson.JSONException r0 = new com.alibaba.fastjson.JSONException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            char r2 = r5.ch
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanHex():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00cb  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void scanNumber() {
        /*
            r9 = this;
            int r0 = r9.bp
            r9.np = r0
            char r0 = r9.ch
            r1 = 1
            r2 = 45
            if (r0 != r2) goto L_0x0013
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
        L_0x0013:
            char r0 = r9.ch
            r3 = 57
            r4 = 48
            if (r0 < r4) goto L_0x0026
            if (r0 > r3) goto L_0x0026
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            goto L_0x0013
        L_0x0026:
            r5 = 46
            if (r0 != r5) goto L_0x0043
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
        L_0x0032:
            char r0 = r9.ch
            if (r0 < r4) goto L_0x0041
            if (r0 > r3) goto L_0x0041
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            goto L_0x0032
        L_0x0041:
            r0 = r1
            goto L_0x0044
        L_0x0043:
            r0 = 0
        L_0x0044:
            char r5 = r9.ch
            r6 = 76
            if (r5 != r6) goto L_0x0053
            int r2 = r9.sp
            int r2 = r2 + r1
            r9.sp = r2
            r9.next()
            goto L_0x0090
        L_0x0053:
            r6 = 83
            if (r5 != r6) goto L_0x0060
            int r2 = r9.sp
            int r2 = r2 + r1
            r9.sp = r2
            r9.next()
            goto L_0x0090
        L_0x0060:
            r6 = 66
            if (r5 != r6) goto L_0x006d
            int r2 = r9.sp
            int r2 = r2 + r1
            r9.sp = r2
            r9.next()
            goto L_0x0090
        L_0x006d:
            r6 = 70
            if (r5 != r6) goto L_0x007a
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            goto L_0x00c5
        L_0x007a:
            r7 = 68
            if (r5 != r7) goto L_0x0087
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            goto L_0x00c5
        L_0x0087:
            r8 = 101(0x65, float:1.42E-43)
            if (r5 == r8) goto L_0x0092
            r8 = 69
            if (r5 != r8) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r1 = r0
            goto L_0x00c5
        L_0x0092:
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            char r0 = r9.ch
            r5 = 43
            if (r0 == r5) goto L_0x00a2
            if (r0 != r2) goto L_0x00aa
        L_0x00a2:
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
        L_0x00aa:
            char r0 = r9.ch
            if (r0 < r4) goto L_0x00b9
            if (r0 > r3) goto L_0x00b9
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
            goto L_0x00aa
        L_0x00b9:
            if (r0 == r7) goto L_0x00bd
            if (r0 != r6) goto L_0x00c5
        L_0x00bd:
            int r0 = r9.sp
            int r0 = r0 + r1
            r9.sp = r0
            r9.next()
        L_0x00c5:
            if (r1 == 0) goto L_0x00cb
            r0 = 3
            r9.token = r0
            goto L_0x00ce
        L_0x00cb:
            r0 = 2
            r9.token = r0
        L_0x00ce:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.scanNumber():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long longValue() throws java.lang.NumberFormatException {
        /*
            r13 = this;
            int r0 = r13.np
            r1 = -1
            r2 = 0
            if (r0 != r1) goto L_0x0008
            r13.np = r2
        L_0x0008:
            int r0 = r13.np
            int r1 = r13.sp
            int r1 = r1 + r0
            char r3 = r13.charAt(r0)
            r4 = 45
            r5 = 1
            if (r3 != r4) goto L_0x001d
            int r0 = r0 + 1
            r2 = -9223372036854775808
            r3 = r2
            r2 = r5
            goto L_0x0022
        L_0x001d:
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0022:
            if (r0 >= r1) goto L_0x0030
            int r6 = r0 + 1
            char r0 = r13.charAt(r0)
            int r0 = r0 + -48
            int r0 = -r0
            long r7 = (long) r0
        L_0x002e:
            r0 = r6
            goto L_0x0032
        L_0x0030:
            r7 = 0
        L_0x0032:
            if (r0 >= r1) goto L_0x0073
            int r6 = r0 + 1
            char r0 = r13.charAt(r0)
            r9 = 76
            if (r0 == r9) goto L_0x0072
            r9 = 83
            if (r0 == r9) goto L_0x0072
            r9 = 66
            if (r0 != r9) goto L_0x0047
            goto L_0x0072
        L_0x0047:
            int r0 = r0 + -48
            r9 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r9 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r9 < 0) goto L_0x0068
            r9 = 10
            long r7 = r7 * r9
            long r9 = (long) r0
            long r11 = r3 + r9
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 < 0) goto L_0x005e
            long r7 = r7 - r9
            goto L_0x002e
        L_0x005e:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L_0x0068:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L_0x0072:
            r0 = r6
        L_0x0073:
            if (r2 == 0) goto L_0x0085
            int r1 = r13.np
            int r1 = r1 + r5
            if (r0 <= r1) goto L_0x007b
            return r7
        L_0x007b:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.String r1 = r13.numberString()
            r0.<init>(r1)
            throw r0
        L_0x0085:
            long r0 = -r7
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONLexerBase.longValue():long");
    }

    public final Number decimalValue(boolean z) {
        char charAt = charAt((this.np + this.sp) - 1);
        if (charAt == 'F') {
            try {
                return Float.valueOf(Float.parseFloat(numberString()));
            } catch (NumberFormatException e) {
                throw new JSONException(e.getMessage() + ", " + info());
            }
        } else if (charAt == 'D') {
            return Double.valueOf(Double.parseDouble(numberString()));
        } else {
            if (z) {
                return decimalValue();
            }
            return Double.valueOf(doubleValue());
        }
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        throw new UnsupportedOperationException();
    }

    public boolean matchField2(char[] cArr) {
        throw new UnsupportedOperationException();
    }

    public int getFeatures() {
        return this.features;
    }
}
