package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.util.ASMUtils;
import com.alibaba.fastjson.util.IOUtils;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.SimpleTimeZone;
import kotlin.text.Typography;

public final class JSONScanner extends JSONLexerBase {
    private final int len;
    private final String text;

    static boolean checkDate(char c2, char c3, char c4, char c5, char c6, char c7, int i, int i2) {
        if (c2 >= '0' && c2 <= '9' && c3 >= '0' && c3 <= '9' && c4 >= '0' && c4 <= '9' && c5 >= '0' && c5 <= '9') {
            if (c6 == '0') {
                if (c7 < '1' || c7 > '9') {
                    return false;
                }
            } else if (!(c6 == '1' && (c7 == '0' || c7 == '1' || c7 == '2'))) {
                return false;
            }
            return i == 48 ? i2 >= 49 && i2 <= 57 : (i == 49 || i == 50) ? i2 >= 48 && i2 <= 57 : i == 51 && (i2 == 48 || i2 == 49);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001d, code lost:
        if (r6 <= '4') goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean checkTime(char r5, char r6, char r7, char r8, char r9, char r10) {
        /*
            r4 = this;
            r0 = 57
            r1 = 0
            r2 = 48
            if (r5 != r2) goto L_0x000c
            if (r6 < r2) goto L_0x000b
            if (r6 <= r0) goto L_0x0020
        L_0x000b:
            return r1
        L_0x000c:
            r3 = 49
            if (r5 != r3) goto L_0x0015
            if (r6 < r2) goto L_0x0014
            if (r6 <= r0) goto L_0x0020
        L_0x0014:
            return r1
        L_0x0015:
            r3 = 50
            if (r5 != r3) goto L_0x0042
            if (r6 < r2) goto L_0x0042
            r5 = 52
            if (r6 <= r5) goto L_0x0020
            goto L_0x0042
        L_0x0020:
            r5 = 53
            r6 = 54
            if (r7 < r2) goto L_0x002d
            if (r7 > r5) goto L_0x002d
            if (r8 < r2) goto L_0x002c
            if (r8 <= r0) goto L_0x0032
        L_0x002c:
            return r1
        L_0x002d:
            if (r7 != r6) goto L_0x0042
            if (r8 == r2) goto L_0x0032
            return r1
        L_0x0032:
            if (r9 < r2) goto L_0x003b
            if (r9 > r5) goto L_0x003b
            if (r10 < r2) goto L_0x003a
            if (r10 <= r0) goto L_0x0040
        L_0x003a:
            return r1
        L_0x003b:
            if (r9 != r6) goto L_0x0042
            if (r10 == r2) goto L_0x0040
            return r1
        L_0x0040:
            r5 = 1
            return r5
        L_0x0042:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.checkTime(char, char, char, char, char, char):boolean");
    }

    public JSONScanner(String str) {
        this(str, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(String str, int i) {
        super(i);
        this.text = str;
        this.len = str.length();
        this.bp = -1;
        next();
        if (this.ch == 65279) {
            next();
        }
    }

    public final char charAt(int i) {
        if (i >= this.len) {
            return JSONLexer.EOI;
        }
        return this.text.charAt(i);
    }

    public final char next() {
        char c2;
        int i = this.bp + 1;
        this.bp = i;
        if (i >= this.len) {
            c2 = JSONLexer.EOI;
        } else {
            c2 = this.text.charAt(i);
        }
        this.ch = c2;
        return c2;
    }

    public JSONScanner(char[] cArr, int i) {
        this(cArr, i, JSON.DEFAULT_PARSER_FEATURE);
    }

    public JSONScanner(char[] cArr, int i, int i2) {
        this(new String(cArr, 0, i), i2);
    }

    /* access modifiers changed from: protected */
    public final void copyTo(int i, int i2, char[] cArr) {
        this.text.getChars(i, i2 + i, cArr, 0);
    }

    static boolean charArrayCompare(String str, int i, char[] cArr) {
        int length = cArr.length;
        if (length + i > str.length()) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (cArr[i2] != str.charAt(i + i2)) {
                return false;
            }
        }
        return true;
    }

    public final boolean charArrayCompare(char[] cArr) {
        return charArrayCompare(this.text, this.bp, cArr);
    }

    public final int indexOf(char c2, int i) {
        return this.text.indexOf(c2, i);
    }

    public final String addSymbol(int i, int i2, int i3, SymbolTable symbolTable) {
        return symbolTable.addSymbol(this.text, i, i2, i3);
    }

    public byte[] bytesValue() {
        if (this.token == 26) {
            int i = this.np + 1;
            int i2 = this.sp;
            if (i2 % 2 == 0) {
                int i3 = i2 / 2;
                byte[] bArr = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = (i4 * 2) + i;
                    char charAt = this.text.charAt(i5);
                    char charAt2 = this.text.charAt(i5 + 1);
                    char c2 = '0';
                    int i6 = charAt - (charAt <= '9' ? '0' : '7');
                    if (charAt2 > '9') {
                        c2 = '7';
                    }
                    bArr[i4] = (byte) ((i6 << 4) | (charAt2 - c2));
                }
                return bArr;
            }
            throw new JSONException("illegal state. " + i2);
        } else if (!this.hasSpecial) {
            return IOUtils.decodeBase64(this.text, this.np + 1, this.sp);
        } else {
            return IOUtils.decodeBase64(new String(this.sbuf, 0, this.sp));
        }
    }

    public final String stringVal() {
        if (!this.hasSpecial) {
            return subString(this.np + 1, this.sp);
        }
        return new String(this.sbuf, 0, this.sp);
    }

    public final String subString(int i, int i2) {
        if (!ASMUtils.IS_ANDROID) {
            return this.text.substring(i, i2 + i);
        }
        if (i2 < this.sbuf.length) {
            this.text.getChars(i, i + i2, this.sbuf, 0);
            return new String(this.sbuf, 0, i2);
        }
        char[] cArr = new char[i2];
        this.text.getChars(i, i2 + i, cArr, 0);
        return new String(cArr);
    }

    public final char[] sub_chars(int i, int i2) {
        if (!ASMUtils.IS_ANDROID || i2 >= this.sbuf.length) {
            char[] cArr = new char[i2];
            this.text.getChars(i, i2 + i, cArr, 0);
            return cArr;
        }
        this.text.getChars(i, i2 + i, this.sbuf, 0);
        return this.sbuf;
    }

    public final String numberString() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        return subString(this.np, i);
    }

    public final BigDecimal decimalValue() {
        char charAt = charAt((this.np + this.sp) - 1);
        int i = this.sp;
        if (charAt == 'L' || charAt == 'S' || charAt == 'B' || charAt == 'F' || charAt == 'D') {
            i--;
        }
        int i2 = this.np;
        if (i < this.sbuf.length) {
            this.text.getChars(i2, i2 + i, this.sbuf, 0);
            return new BigDecimal(this.sbuf, 0, i);
        }
        char[] cArr = new char[i];
        this.text.getChars(i2, i + i2, cArr, 0);
        return new BigDecimal(cArr);
    }

    public boolean scanISO8601DateIfMatch() {
        return scanISO8601DateIfMatch(true);
    }

    public boolean scanISO8601DateIfMatch(boolean z) {
        return scanISO8601DateIfMatch(z, this.len - this.bp);
    }

    /* JADX WARNING: Removed duplicated region for block: B:117:0x021b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x021d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean scanISO8601DateIfMatch(boolean r35, int r36) {
        /*
            r34 = this;
            r9 = r34
            r10 = r36
            r11 = 0
            r12 = 8
            if (r10 >= r12) goto L_0x000a
            return r11
        L_0x000a:
            int r0 = r9.bp
            char r13 = r9.charAt(r0)
            int r0 = r9.bp
            r14 = 1
            int r0 = r0 + r14
            char r15 = r9.charAt(r0)
            int r0 = r9.bp
            r8 = 2
            int r0 = r0 + r8
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            r16 = 3
            int r1 = r1 + 3
            char r7 = r9.charAt(r1)
            int r1 = r9.bp
            int r1 = r1 + 4
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            r6 = 5
            int r2 = r2 + r6
            char r2 = r9.charAt(r2)
            int r3 = r9.bp
            r17 = 6
            int r3 = r3 + 6
            char r3 = r9.charAt(r3)
            int r4 = r9.bp
            int r4 = r4 + 7
            char r4 = r9.charAt(r4)
            r5 = 57
            r12 = 48
            if (r35 != 0) goto L_0x00c9
            r6 = 13
            if (r10 <= r6) goto L_0x00c8
            int r6 = r9.bp
            int r6 = r6 + r10
            int r6 = r6 - r14
            char r6 = r9.charAt(r6)
            int r14 = r9.bp
            int r14 = r14 + r10
            int r14 = r14 - r8
            char r14 = r9.charAt(r14)
            r8 = 47
            if (r13 != r8) goto L_0x00c8
            r8 = 68
            if (r15 != r8) goto L_0x00c8
            r8 = 97
            if (r0 != r8) goto L_0x00c8
            r8 = 116(0x74, float:1.63E-43)
            if (r7 != r8) goto L_0x00c8
            r8 = 101(0x65, float:1.42E-43)
            if (r1 != r8) goto L_0x00c8
            r8 = 40
            if (r2 != r8) goto L_0x00c8
            r8 = 47
            if (r6 != r8) goto L_0x00c8
            r6 = 41
            if (r14 != r6) goto L_0x00c8
            r0 = -1
            r1 = r17
        L_0x0089:
            if (r1 >= r10) goto L_0x00a0
            int r2 = r9.bp
            int r2 = r2 + r1
            char r2 = r9.charAt(r2)
            r3 = 43
            if (r2 != r3) goto L_0x0098
            r0 = r1
            goto L_0x009d
        L_0x0098:
            if (r2 < r12) goto L_0x00a0
            if (r2 <= r5) goto L_0x009d
            goto L_0x00a0
        L_0x009d:
            int r1 = r1 + 1
            goto L_0x0089
        L_0x00a0:
            r1 = -1
            if (r0 != r1) goto L_0x00a4
            return r11
        L_0x00a4:
            int r1 = r9.bp
            int r1 = r1 + 6
            int r2 = r9.bp
            int r2 = r2 + r0
            int r2 = r2 - r1
            java.lang.String r0 = r9.subString(r1, r2)
            long r0 = java.lang.Long.parseLong(r0)
            java.util.TimeZone r2 = r9.timeZone
            java.util.Locale r3 = r9.locale
            java.util.Calendar r2 = java.util.Calendar.getInstance(r2, r3)
            r9.calendar = r2
            java.util.Calendar r2 = r9.calendar
            r2.setTimeInMillis(r0)
            r6 = 5
            r9.token = r6
        L_0x00c6:
            r0 = 1
            return r0
        L_0x00c8:
            r6 = 5
        L_0x00c9:
            r14 = 16
            r12 = 14
            r11 = 45
            r21 = 10
            r5 = 8
            if (r10 == r5) goto L_0x0598
            if (r10 == r12) goto L_0x0598
            if (r10 != r14) goto L_0x00f0
            int r5 = r9.bp
            int r5 = r5 + 10
            char r5 = r9.charAt(r5)
            r6 = 84
            if (r5 == r6) goto L_0x00ea
            r6 = 32
            if (r5 == r6) goto L_0x00ea
            goto L_0x00f0
        L_0x00ea:
            r5 = 0
            r12 = 58
            r14 = 5
            goto L_0x059c
        L_0x00f0:
            r5 = 17
            if (r10 != r5) goto L_0x00ff
            int r5 = r9.bp
            int r5 = r5 + 6
            char r5 = r9.charAt(r5)
            if (r5 == r11) goto L_0x00ff
            goto L_0x00ea
        L_0x00ff:
            r5 = 9
            if (r10 >= r5) goto L_0x0105
            r6 = 0
            return r6
        L_0x0105:
            int r6 = r9.bp
            r18 = 8
            int r6 = r6 + 8
            char r6 = r9.charAt(r6)
            int r8 = r9.bp
            int r8 = r8 + r5
            char r5 = r9.charAt(r8)
            r8 = 51068(0xc77c, float:7.1562E-41)
            r12 = 26085(0x65e5, float:3.6553E-41)
            if (r1 != r11) goto L_0x0123
            if (r4 == r11) goto L_0x0120
            goto L_0x0123
        L_0x0120:
            r14 = 32
            goto L_0x012c
        L_0x0123:
            r14 = 47
            if (r1 != r14) goto L_0x014a
            r14 = 47
            if (r4 != r14) goto L_0x014a
            goto L_0x0120
        L_0x012c:
            if (r5 != r14) goto L_0x013b
            r5 = r2
            r4 = r7
            r1 = r13
            r2 = r15
            r7 = 48
            r13 = 9
        L_0x0136:
            r15 = r6
            r6 = r3
        L_0x0138:
            r3 = r0
            goto L_0x0205
        L_0x013b:
            r4 = r7
            r1 = r13
            r13 = r21
        L_0x013f:
            r7 = r6
            r6 = r3
            r3 = r0
            r32 = r5
            r5 = r2
            r2 = r15
            r15 = r32
            goto L_0x0205
        L_0x014a:
            r14 = 32
            if (r1 != r11) goto L_0x0173
            if (r3 != r11) goto L_0x0173
            if (r6 != r14) goto L_0x0160
            r3 = r0
            r6 = r2
            r1 = r13
            r2 = r15
            r5 = 48
            r13 = 8
        L_0x015a:
            r15 = r4
            r4 = r7
            r7 = 48
            goto L_0x0205
        L_0x0160:
            r3 = r0
            r1 = r13
            r5 = 48
            r13 = 9
        L_0x0166:
            r32 = r6
            r6 = r2
            r2 = r15
            r15 = r32
            r33 = r7
            r7 = r4
            r4 = r33
            goto L_0x0205
        L_0x0173:
            r14 = 46
            if (r0 != r14) goto L_0x017b
            r14 = 46
            if (r2 == r14) goto L_0x017f
        L_0x017b:
            if (r0 != r11) goto L_0x018d
            if (r2 != r11) goto L_0x018d
        L_0x017f:
            r2 = r4
            r4 = r5
            r5 = r7
            r7 = r13
            r13 = r21
            r32 = r6
            r6 = r1
            r1 = r3
            r3 = r32
            goto L_0x0205
        L_0x018d:
            r14 = 84
            if (r6 != r14) goto L_0x019b
            r5 = r1
            r6 = r2
            r1 = r13
            r2 = r15
            r13 = 8
            r15 = r4
            r4 = r7
            r7 = r3
            goto L_0x0138
        L_0x019b:
            r14 = 24180(0x5e74, float:3.3883E-41)
            if (r1 == r14) goto L_0x01a7
            r14 = 45380(0xb144, float:6.3591E-41)
            if (r1 != r14) goto L_0x01a5
            goto L_0x01a7
        L_0x01a5:
            r1 = 0
            return r1
        L_0x01a7:
            r1 = 26376(0x6708, float:3.696E-41)
            if (r4 == r1) goto L_0x01d9
            r1 = 50900(0xc6d4, float:7.1326E-41)
            if (r4 != r1) goto L_0x01b1
            goto L_0x01d9
        L_0x01b1:
            r1 = 26376(0x6708, float:3.696E-41)
            if (r3 == r1) goto L_0x01bd
            r1 = 50900(0xc6d4, float:7.1326E-41)
            if (r3 != r1) goto L_0x01bb
            goto L_0x01bd
        L_0x01bb:
            r1 = 0
            return r1
        L_0x01bd:
            r1 = 0
            if (r6 == r12) goto L_0x01d0
            if (r6 != r8) goto L_0x01c3
            goto L_0x01d0
        L_0x01c3:
            if (r5 == r12) goto L_0x01c9
            if (r5 != r8) goto L_0x01c8
            goto L_0x01c9
        L_0x01c8:
            return r1
        L_0x01c9:
            r3 = r0
            r1 = r13
            r13 = r21
            r5 = 48
            goto L_0x0166
        L_0x01d0:
            r3 = r0
            r6 = r2
            r1 = r13
            r2 = r15
            r13 = r21
            r5 = 48
            goto L_0x015a
        L_0x01d9:
            if (r5 == r12) goto L_0x01fb
            if (r5 != r8) goto L_0x01de
            goto L_0x01fb
        L_0x01de:
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            if (r1 == r12) goto L_0x01f5
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            if (r1 != r8) goto L_0x01f3
            goto L_0x01f5
        L_0x01f3:
            r1 = 0
            return r1
        L_0x01f5:
            r4 = r7
            r1 = r13
            r13 = 11
            goto L_0x013f
        L_0x01fb:
            r5 = r2
            r4 = r7
            r1 = r13
            r2 = r15
            r13 = r21
            r7 = 48
            goto L_0x0136
        L_0x0205:
            r24 = r1
            r25 = r2
            r26 = r3
            r27 = r4
            r28 = r5
            r29 = r6
            r30 = r7
            r31 = r15
            boolean r0 = checkDate(r24, r25, r26, r27, r28, r29, r30, r31)
            if (r0 != 0) goto L_0x021d
            r0 = 0
            return r0
        L_0x021d:
            r0 = r34
            r14 = 57
            r14 = 5
            r11 = 2
            r8 = r15
            r0.setCalendar(r1, r2, r3, r4, r5, r6, r7, r8)
            int r0 = r9.bp
            int r0 = r0 + r13
            char r7 = r9.charAt(r0)
            r0 = 84
            if (r7 != r0) goto L_0x02c1
            r0 = 16
            if (r10 != r0) goto L_0x02bf
            r0 = 8
            if (r13 != r0) goto L_0x02bf
            int r0 = r9.bp
            int r0 = r0 + 15
            char r0 = r9.charAt(r0)
            r1 = 90
            if (r0 != r1) goto L_0x02bf
            int r0 = r9.bp
            int r0 = r0 + r13
            r1 = 1
            int r0 = r0 + r1
            char r7 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + r11
            char r8 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 3
            char r10 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 4
            char r11 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + r14
            char r12 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 6
            char r13 = r9.charAt(r0)
            r0 = r34
            r1 = r7
            r2 = r8
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            boolean r0 = r0.checkTime(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x028a
            r15 = 0
            return r15
        L_0x028a:
            r15 = 0
            r0 = r34
            r1 = r7
            r2 = r8
            r3 = r10
            r4 = r11
            r5 = r12
            r6 = r13
            r0.setTime(r1, r2, r3, r4, r5, r6)
            java.util.Calendar r0 = r9.calendar
            r1 = 14
            r0.set(r1, r15)
            java.util.Calendar r0 = r9.calendar
            java.util.TimeZone r0 = r0.getTimeZone()
            int r0 = r0.getRawOffset()
            if (r0 == 0) goto L_0x02bb
            java.lang.String[] r0 = java.util.TimeZone.getAvailableIDs(r15)
            int r1 = r0.length
            if (r1 <= 0) goto L_0x02bb
            r0 = r0[r15]
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r0)
            java.util.Calendar r1 = r9.calendar
            r1.setTimeZone(r0)
        L_0x02bb:
            r9.token = r14
            goto L_0x00c6
        L_0x02bf:
            r0 = 84
        L_0x02c1:
            if (r7 == r0) goto L_0x036f
            r0 = 32
            if (r7 != r0) goto L_0x02cb
            if (r35 != 0) goto L_0x02cb
            goto L_0x036f
        L_0x02cb:
            r0 = 34
            if (r7 == r0) goto L_0x0343
            r0 = 26
            if (r7 == r0) goto L_0x0343
            if (r7 == r12) goto L_0x0343
            r0 = 51068(0xc77c, float:7.1562E-41)
            if (r7 != r0) goto L_0x02db
            goto L_0x0343
        L_0x02db:
            r0 = 43
            if (r7 == r0) goto L_0x02e6
            r0 = 45
            if (r7 != r0) goto L_0x02e4
            goto L_0x02e6
        L_0x02e4:
            r0 = 0
            return r0
        L_0x02e6:
            int r0 = r9.len
            int r1 = r13 + 6
            if (r0 != r1) goto L_0x0341
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 3
            char r0 = r9.charAt(r0)
            r12 = 58
            if (r0 != r12) goto L_0x033f
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            r1 = 48
            if (r0 != r1) goto L_0x033f
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + r14
            char r0 = r9.charAt(r0)
            if (r0 == r1) goto L_0x0311
            goto L_0x033f
        L_0x0311:
            r5 = 48
            r6 = 48
            r1 = 48
            r2 = 48
            r3 = 48
            r4 = 48
            r0 = r34
            r0.setTime(r1, r2, r3, r4, r5, r6)
            java.util.Calendar r0 = r9.calendar
            r1 = 14
            r2 = 0
            r0.set(r1, r2)
            int r0 = r9.bp
            int r0 = r0 + r13
            r1 = 1
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            int r2 = r9.bp
            int r2 = r2 + r13
            int r2 = r2 + r11
            char r2 = r9.charAt(r2)
            r9.setTimeZone(r7, r0, r2)
            return r1
        L_0x033f:
            r0 = 0
            return r0
        L_0x0341:
            r0 = 0
            return r0
        L_0x0343:
            r0 = 0
            java.util.Calendar r1 = r9.calendar
            r2 = 11
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r2 = 12
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r2 = 13
            r1.set(r2, r0)
            java.util.Calendar r1 = r9.calendar
            r2 = 14
            r1.set(r2, r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            r9.bp = r0
            char r0 = r9.charAt(r0)
            r9.ch = r0
            r9.token = r14
            goto L_0x00c6
        L_0x036f:
            r12 = 58
            int r0 = r13 + 9
            if (r10 >= r0) goto L_0x0377
            r0 = 0
            return r0
        L_0x0377:
            r0 = 0
            int r1 = r9.bp
            int r1 = r1 + r13
            int r1 = r1 + 3
            char r1 = r9.charAt(r1)
            if (r1 == r12) goto L_0x0384
            return r0
        L_0x0384:
            int r1 = r9.bp
            int r1 = r1 + r13
            int r1 = r1 + 6
            char r1 = r9.charAt(r1)
            if (r1 == r12) goto L_0x0390
            return r0
        L_0x0390:
            int r0 = r9.bp
            int r0 = r0 + r13
            r1 = 1
            int r0 = r0 + r1
            char r7 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + r11
            char r8 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 4
            char r15 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + r14
            char r19 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 7
            char r20 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            r1 = 8
            int r0 = r0 + r1
            char r18 = r9.charAt(r0)
            r0 = r34
            r1 = r7
            r2 = r8
            r3 = r15
            r4 = r19
            r5 = r20
            r6 = r18
            boolean r0 = r0.checkTime(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x03d8
            r0 = 0
            return r0
        L_0x03d8:
            r0 = r34
            r1 = r7
            r2 = r8
            r3 = r15
            r4 = r19
            r5 = r20
            r6 = r18
            r0.setTime(r1, r2, r3, r4, r5, r6)
            int r0 = r9.bp
            int r0 = r0 + r13
            r1 = 9
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            r1 = 46
            if (r0 != r1) goto L_0x0442
            int r0 = r13 + 11
            if (r10 >= r0) goto L_0x03fa
            r1 = 0
            return r1
        L_0x03fa:
            int r1 = r9.bp
            int r1 = r1 + r13
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            r2 = 48
            if (r1 < r2) goto L_0x0440
            r3 = 57
            if (r1 <= r3) goto L_0x040c
            goto L_0x0440
        L_0x040c:
            int r1 = r1 - r2
            if (r10 <= r0) goto L_0x0423
            int r0 = r9.bp
            int r0 = r0 + r13
            r4 = 11
            int r0 = r0 + r4
            char r0 = r9.charAt(r0)
            if (r0 < r2) goto L_0x0423
            if (r0 > r3) goto L_0x0423
            int r1 = r1 * 10
            int r0 = r0 - r2
            int r1 = r1 + r0
            r8 = r11
            goto L_0x0424
        L_0x0423:
            r8 = 1
        L_0x0424:
            if (r8 != r11) goto L_0x043e
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 12
            char r0 = r9.charAt(r0)
            r2 = 48
            if (r0 < r2) goto L_0x043e
            r3 = 57
            if (r0 > r3) goto L_0x043e
            int r1 = r1 * 10
            int r0 = r0 - r2
            int r0 = r0 + r1
            r8 = r16
            goto L_0x0444
        L_0x043e:
            r0 = r1
            goto L_0x0444
        L_0x0440:
            r0 = 0
            return r0
        L_0x0442:
            r8 = -1
            r0 = 0
        L_0x0444:
            java.util.Calendar r1 = r9.calendar
            r2 = 14
            r1.set(r2, r0)
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            char r0 = r9.charAt(r0)
            r1 = 32
            if (r0 != r1) goto L_0x0465
            int r8 = r8 + 1
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            char r0 = r9.charAt(r0)
        L_0x0465:
            r1 = r0
            r0 = 43
            if (r1 == r0) goto L_0x049a
            r0 = 45
            if (r1 != r0) goto L_0x046f
            goto L_0x049a
        L_0x046f:
            r0 = 90
            if (r1 != r0) goto L_0x0496
            java.util.Calendar r0 = r9.calendar
            java.util.TimeZone r0 = r0.getTimeZone()
            int r0 = r0.getRawOffset()
            if (r0 == 0) goto L_0x0492
            r0 = 0
            java.lang.String[] r1 = java.util.TimeZone.getAvailableIDs(r0)
            int r2 = r1.length
            if (r2 <= 0) goto L_0x0492
            r1 = r1[r0]
            java.util.TimeZone r0 = java.util.TimeZone.getTimeZone(r1)
            java.util.Calendar r1 = r9.calendar
            r1.setTimeZone(r0)
        L_0x0492:
            r16 = 1
            goto L_0x056f
        L_0x0496:
            r16 = 0
            goto L_0x056f
        L_0x049a:
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            r2 = 1
            int r0 = r0 + r2
            char r2 = r9.charAt(r0)
            r0 = 48
            if (r2 < r0) goto L_0x0596
            r3 = 49
            if (r2 <= r3) goto L_0x04b0
            goto L_0x0596
        L_0x04b0:
            int r3 = r9.bp
            int r3 = r3 + r13
            int r3 = r3 + 10
            int r3 = r3 + r8
            int r3 = r3 + r11
            char r3 = r9.charAt(r3)
            if (r3 < r0) goto L_0x0594
            r0 = 57
            if (r3 <= r0) goto L_0x04c3
            goto L_0x0594
        L_0x04c3:
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            int r0 = r0 + 3
            char r0 = r9.charAt(r0)
            r4 = 51
            if (r0 != r12) goto L_0x051b
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            int r5 = r9.bp
            int r5 = r5 + r13
            int r5 = r5 + 10
            int r5 = r5 + r8
            int r5 = r5 + r14
            char r5 = r9.charAt(r5)
            r6 = 52
            if (r0 != r6) goto L_0x050c
            r6 = 53
            if (r5 != r6) goto L_0x050c
            r6 = 49
            if (r2 != r6) goto L_0x04fd
            r6 = 50
            if (r3 == r6) goto L_0x0517
            if (r3 != r4) goto L_0x04fd
            goto L_0x0517
        L_0x04fd:
            r4 = 48
            if (r2 != r4) goto L_0x050a
            r4 = 53
            if (r3 == r4) goto L_0x0517
            r4 = 56
            if (r3 != r4) goto L_0x050a
            goto L_0x0517
        L_0x050a:
            r6 = 0
            return r6
        L_0x050c:
            r6 = 0
            r7 = 48
            if (r0 == r7) goto L_0x0514
            if (r0 == r4) goto L_0x0514
            return r6
        L_0x0514:
            if (r5 == r7) goto L_0x0517
            return r6
        L_0x0517:
            r4 = r0
            r16 = r17
            goto L_0x056a
        L_0x051b:
            r7 = 48
            if (r0 != r7) goto L_0x0537
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            if (r0 == r7) goto L_0x0531
            if (r0 == r4) goto L_0x0531
            r4 = 0
            return r4
        L_0x0531:
            r4 = r0
        L_0x0532:
            r16 = r14
        L_0x0534:
            r5 = 48
            goto L_0x056a
        L_0x0537:
            if (r0 != r4) goto L_0x054a
            int r5 = r9.bp
            int r5 = r5 + r13
            int r5 = r5 + 10
            int r5 = r5 + r8
            int r5 = r5 + 4
            char r5 = r9.charAt(r5)
            r6 = 48
            if (r5 != r6) goto L_0x054a
            goto L_0x0532
        L_0x054a:
            r4 = 52
            if (r0 != r4) goto L_0x0567
            int r0 = r9.bp
            int r0 = r0 + r13
            int r0 = r0 + 10
            int r0 = r0 + r8
            int r0 = r0 + 4
            char r0 = r9.charAt(r0)
            r4 = 53
            if (r0 != r4) goto L_0x0567
            r0 = 52
            r4 = 53
            r5 = r4
            r16 = r14
            r4 = r0
            goto L_0x056a
        L_0x0567:
            r4 = 48
            goto L_0x0534
        L_0x056a:
            r0 = r34
            r0.setTimeZone(r1, r2, r3, r4, r5)
        L_0x056f:
            int r0 = r9.bp
            int r13 = r13 + 10
            int r13 = r13 + r8
            int r13 = r13 + r16
            int r0 = r0 + r13
            char r0 = r9.charAt(r0)
            r1 = 26
            if (r0 == r1) goto L_0x0585
            r1 = 34
            if (r0 == r1) goto L_0x0585
            r5 = 0
            return r5
        L_0x0585:
            int r0 = r9.bp
            int r0 = r0 + r13
            r9.bp = r0
            char r0 = r9.charAt(r0)
            r9.ch = r0
            r9.token = r14
            goto L_0x00c6
        L_0x0594:
            r5 = 0
            return r5
        L_0x0596:
            r5 = 0
            return r5
        L_0x0598:
            r14 = r6
            r5 = 0
            r12 = 58
        L_0x059c:
            if (r35 == 0) goto L_0x059f
            return r5
        L_0x059f:
            int r5 = r9.bp
            r6 = 8
            int r5 = r5 + r6
            char r11 = r9.charAt(r5)
            r5 = 45
            if (r1 != r5) goto L_0x05b0
            if (r4 != r5) goto L_0x05b0
            r5 = 1
            goto L_0x05b1
        L_0x05b0:
            r5 = 0
        L_0x05b1:
            if (r5 == 0) goto L_0x05ba
            r6 = 16
            if (r10 != r6) goto L_0x05ba
            r16 = 1
            goto L_0x05bc
        L_0x05ba:
            r16 = 0
        L_0x05bc:
            if (r5 == 0) goto L_0x05c5
            r5 = 17
            if (r10 != r5) goto L_0x05c5
            r17 = 1
            goto L_0x05c7
        L_0x05c5:
            r17 = 0
        L_0x05c7:
            if (r17 != 0) goto L_0x05e4
            if (r16 == 0) goto L_0x05cc
            goto L_0x05e4
        L_0x05cc:
            r5 = 45
            if (r1 != r5) goto L_0x05db
            if (r3 != r5) goto L_0x05db
            r22 = r2
            r24 = r4
            r19 = 48
            r23 = 48
            goto L_0x05f5
        L_0x05db:
            r19 = r1
            r22 = r2
            r23 = r3
            r24 = r4
            goto L_0x05f5
        L_0x05e4:
            int r1 = r9.bp
            r4 = 9
            int r1 = r1 + r4
            char r1 = r9.charAt(r1)
            r24 = r1
            r19 = r2
            r22 = r3
            r23 = r11
        L_0x05f5:
            r1 = r13
            r2 = r15
            r3 = r0
            r4 = r7
            r5 = r19
            r6 = r22
            r25 = r7
            r7 = r23
            r8 = r24
            boolean r1 = checkDate(r1, r2, r3, r4, r5, r6, r7, r8)
            if (r1 != 0) goto L_0x060b
            r1 = 0
            return r1
        L_0x060b:
            r3 = r0
            r0 = r34
            r1 = r13
            r2 = r15
            r4 = r25
            r5 = r19
            r6 = r22
            r7 = r23
            r8 = r24
            r0.setCalendar(r1, r2, r3, r4, r5, r6, r7, r8)
            r0 = 8
            if (r10 == r0) goto L_0x06f8
            int r0 = r9.bp
            r1 = 9
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            int r1 = r1 + 10
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            r3 = 11
            int r2 = r2 + r3
            char r2 = r9.charAt(r2)
            int r3 = r9.bp
            int r3 = r3 + 12
            char r3 = r9.charAt(r3)
            int r4 = r9.bp
            int r4 = r4 + 13
            char r4 = r9.charAt(r4)
            if (r17 == 0) goto L_0x0660
            r5 = 84
            if (r1 != r5) goto L_0x0660
            if (r4 != r12) goto L_0x0660
            int r5 = r9.bp
            r6 = 16
            int r5 = r5 + r6
            char r5 = r9.charAt(r5)
            r6 = 90
            if (r5 == r6) goto L_0x066c
        L_0x0660:
            if (r16 == 0) goto L_0x0686
            r5 = 32
            if (r1 == r5) goto L_0x066a
            r5 = 84
            if (r1 != r5) goto L_0x0686
        L_0x066a:
            if (r4 != r12) goto L_0x0686
        L_0x066c:
            int r0 = r9.bp
            r1 = 14
            int r0 = r0 + r1
            char r1 = r9.charAt(r0)
            int r0 = r9.bp
            int r0 = r0 + 15
            char r0 = r9.charAt(r0)
            r13 = r0
            r8 = r1
            r15 = r2
            r7 = r3
            r11 = 48
            r12 = 48
            goto L_0x068c
        L_0x0686:
            r7 = r0
            r8 = r1
            r13 = r2
            r12 = r4
            r15 = r11
            r11 = r3
        L_0x068c:
            r0 = r34
            r1 = r15
            r2 = r7
            r3 = r8
            r4 = r13
            r5 = r11
            r6 = r12
            boolean r0 = r0.checkTime(r1, r2, r3, r4, r5, r6)
            if (r0 != 0) goto L_0x069c
            r0 = 0
            return r0
        L_0x069c:
            r0 = 17
            if (r10 != r0) goto L_0x06df
            if (r17 != 0) goto L_0x06df
            int r0 = r9.bp
            r1 = 14
            int r0 = r0 + r1
            char r0 = r9.charAt(r0)
            int r1 = r9.bp
            int r1 = r1 + 15
            char r1 = r9.charAt(r1)
            int r2 = r9.bp
            r3 = 16
            int r2 = r2 + r3
            char r2 = r9.charAt(r2)
            r3 = 48
            if (r0 < r3) goto L_0x06dd
            r4 = 57
            if (r0 <= r4) goto L_0x06c5
            goto L_0x06dd
        L_0x06c5:
            if (r1 < r3) goto L_0x06db
            if (r1 <= r4) goto L_0x06ca
            goto L_0x06db
        L_0x06ca:
            if (r2 < r3) goto L_0x06d9
            if (r2 <= r4) goto L_0x06cf
            goto L_0x06d9
        L_0x06cf:
            int r0 = r0 - r3
            int r0 = r0 * 100
            int r1 = r1 - r3
            int r1 = r1 * 10
            int r0 = r0 + r1
            int r2 = r2 - r3
            int r0 = r0 + r2
            goto L_0x06e0
        L_0x06d9:
            r0 = 0
            return r0
        L_0x06db:
            r0 = 0
            return r0
        L_0x06dd:
            r0 = 0
            return r0
        L_0x06df:
            r0 = 0
        L_0x06e0:
            r1 = 48
            int r15 = r15 - r1
            int r15 = r15 * 10
            int r7 = r7 - r1
            int r2 = r15 + r7
            int r8 = r8 - r1
            int r8 = r8 * 10
            int r13 = r13 - r1
            int r3 = r8 + r13
            int r11 = r11 - r1
            int r11 = r11 * 10
            int r12 = r12 - r1
            int r11 = r11 + r12
            r1 = r11
            r11 = r2
            r2 = r0
            r0 = r3
            goto L_0x06fc
        L_0x06f8:
            r0 = 0
            r1 = r0
            r2 = r1
            r11 = r2
        L_0x06fc:
            java.util.Calendar r3 = r9.calendar
            r4 = 11
            r3.set(r4, r11)
            java.util.Calendar r3 = r9.calendar
            r4 = 12
            r3.set(r4, r0)
            java.util.Calendar r0 = r9.calendar
            r3 = 13
            r0.set(r3, r1)
            java.util.Calendar r0 = r9.calendar
            r1 = 14
            r0.set(r1, r2)
            r9.token = r14
            goto L_0x00c6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanISO8601DateIfMatch(boolean, int):boolean");
    }

    /* access modifiers changed from: protected */
    public void setTime(char c2, char c3, char c4, char c5, char c6, char c7) {
        this.calendar.set(11, ((c2 - '0') * 10) + (c3 - '0'));
        this.calendar.set(12, ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(13, ((c6 - '0') * 10) + (c7 - '0'));
    }

    /* access modifiers changed from: protected */
    public void setTimeZone(char c2, char c3, char c4) {
        setTimeZone(c2, c3, c4, '0', '0');
    }

    /* access modifiers changed from: protected */
    public void setTimeZone(char c2, char c3, char c4, char c5, char c6) {
        int i = ((((c3 - '0') * 10) + (c4 - '0')) * 3600000) + ((((c5 - '0') * 10) + (c6 - '0')) * 60000);
        if (c2 == '-') {
            i = -i;
        }
        if (this.calendar.getTimeZone().getRawOffset() != i) {
            this.calendar.setTimeZone(new SimpleTimeZone(i, Integer.toString(i)));
        }
    }

    private void setCalendar(char c2, char c3, char c4, char c5, char c6, char c7, char c8, char c9) {
        this.calendar = Calendar.getInstance(this.timeZone, this.locale);
        this.calendar.set(1, ((c2 - '0') * 1000) + ((c3 - '0') * 100) + ((c4 - '0') * 10) + (c5 - '0'));
        this.calendar.set(2, (((c6 - '0') * 10) + (c7 - '0')) - 1);
        this.calendar.set(5, ((c8 - '0') * 10) + (c9 - '0'));
    }

    public boolean isEOF() {
        if (this.bp != this.len) {
            return this.ch == 26 && this.bp + 1 >= this.len;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scanFieldInt(char[] r15) {
        /*
            r14 = this;
            r0 = 0
            r14.matchStat = r0
            int r1 = r14.bp
            char r2 = r14.ch
            java.lang.String r3 = r14.text
            int r4 = r14.bp
            boolean r3 = charArrayCompare(r3, r4, r15)
            if (r3 != 0) goto L_0x0015
            r15 = -2
            r14.matchStat = r15
            return r0
        L_0x0015:
            int r3 = r14.bp
            int r15 = r15.length
            int r3 = r3 + r15
            int r15 = r3 + 1
            char r4 = r14.charAt(r3)
            r5 = 34
            r6 = 1
            if (r4 != r5) goto L_0x0026
            r7 = r6
            goto L_0x0027
        L_0x0026:
            r7 = r0
        L_0x0027:
            if (r7 == 0) goto L_0x0030
            int r3 = r3 + 2
            char r4 = r14.charAt(r15)
            r15 = r3
        L_0x0030:
            r3 = 45
            if (r4 != r3) goto L_0x0036
            r3 = r6
            goto L_0x0037
        L_0x0036:
            r3 = r0
        L_0x0037:
            if (r3 == 0) goto L_0x0042
            int r4 = r15 + 1
            char r15 = r14.charAt(r15)
            r13 = r4
            r4 = r15
            r15 = r13
        L_0x0042:
            r8 = 48
            r9 = -1
            if (r4 < r8) goto L_0x011b
            r10 = 57
            if (r4 > r10) goto L_0x011b
            int r4 = r4 - r8
        L_0x004c:
            int r11 = r15 + 1
            char r12 = r14.charAt(r15)
            if (r12 < r8) goto L_0x0063
            if (r12 > r10) goto L_0x0063
            int r15 = r4 * 10
            if (r15 >= r4) goto L_0x005d
            r14.matchStat = r9
            return r0
        L_0x005d:
            int r12 = r12 + -48
            int r4 = r15 + r12
            r15 = r11
            goto L_0x004c
        L_0x0063:
            r8 = 46
            if (r12 != r8) goto L_0x006a
            r14.matchStat = r9
            return r0
        L_0x006a:
            if (r4 >= 0) goto L_0x006f
            r14.matchStat = r9
            return r0
        L_0x006f:
            if (r7 == 0) goto L_0x007d
            if (r12 == r5) goto L_0x0076
            r14.matchStat = r9
            return r0
        L_0x0076:
            int r15 = r15 + 2
            char r12 = r14.charAt(r11)
        L_0x007c:
            r11 = r15
        L_0x007d:
            r15 = 125(0x7d, float:1.75E-43)
            r5 = 44
            if (r12 == r5) goto L_0x0096
            if (r12 != r15) goto L_0x0086
            goto L_0x0096
        L_0x0086:
            boolean r15 = isWhitespace(r12)
            if (r15 == 0) goto L_0x0093
            int r15 = r11 + 1
            char r12 = r14.charAt(r11)
            goto L_0x007c
        L_0x0093:
            r14.matchStat = r9
            return r0
        L_0x0096:
            int r11 = r11 - r6
            r14.bp = r11
            r7 = 16
            if (r12 != r5) goto L_0x00b1
            int r15 = r14.bp
            int r15 = r15 + r6
            r14.bp = r15
            char r15 = r14.charAt(r15)
            r14.ch = r15
            r15 = 3
            r14.matchStat = r15
            r14.token = r7
            if (r3 == 0) goto L_0x00b0
            int r4 = -r4
        L_0x00b0:
            return r4
        L_0x00b1:
            if (r12 != r15) goto L_0x0117
            r14.bp = r11
            int r8 = r14.bp
            int r8 = r8 + r6
            r14.bp = r8
            char r8 = r14.charAt(r8)
        L_0x00be:
            if (r8 != r5) goto L_0x00ce
            r14.token = r7
            int r15 = r14.bp
            int r15 = r15 + r6
            r14.bp = r15
            char r15 = r14.charAt(r15)
            r14.ch = r15
            goto L_0x00fc
        L_0x00ce:
            r10 = 93
            if (r8 != r10) goto L_0x00e2
            r15 = 15
            r14.token = r15
            int r15 = r14.bp
            int r15 = r15 + r6
            r14.bp = r15
            char r15 = r14.charAt(r15)
            r14.ch = r15
            goto L_0x00fc
        L_0x00e2:
            if (r8 != r15) goto L_0x00f4
            r15 = 13
            r14.token = r15
            int r15 = r14.bp
            int r15 = r15 + r6
            r14.bp = r15
            char r15 = r14.charAt(r15)
            r14.ch = r15
            goto L_0x00fc
        L_0x00f4:
            r10 = 26
            if (r8 != r10) goto L_0x0100
            r15 = 20
            r14.token = r15
        L_0x00fc:
            r15 = 4
            r14.matchStat = r15
            goto L_0x0117
        L_0x0100:
            boolean r8 = isWhitespace(r8)
            if (r8 == 0) goto L_0x0110
            int r8 = r14.bp
            int r8 = r8 + r6
            r14.bp = r8
            char r8 = r14.charAt(r8)
            goto L_0x00be
        L_0x0110:
            r14.bp = r1
            r14.ch = r2
            r14.matchStat = r9
            return r0
        L_0x0117:
            if (r3 == 0) goto L_0x011a
            int r4 = -r4
        L_0x011a:
            return r4
        L_0x011b:
            r14.matchStat = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldInt(char[]):int");
    }

    public String scanFieldString(char[] cArr) {
        this.matchStat = 0;
        int i = this.bp;
        char c2 = this.ch;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
                while (isWhitespace(this.ch)) {
                    next();
                }
            } else {
                this.matchStat = -2;
                return stringDefaultValue();
            }
        }
        int length = this.bp + cArr.length;
        int i2 = length + 1;
        char charAt = charAt(length);
        int i3 = 0;
        if (charAt != '\"') {
            while (isWhitespace(charAt)) {
                i3++;
                char charAt2 = charAt(i2);
                i2++;
                charAt = charAt2;
            }
            if (charAt != '\"') {
                this.matchStat = -1;
                return stringDefaultValue();
            }
        }
        int indexOf = indexOf(Typography.quote, i2);
        if (indexOf != -1) {
            String subString = subString(i2, indexOf - i2);
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
                int length2 = indexOf - (((this.bp + cArr.length) + 1) + i3);
                subString = readString(sub_chars(this.bp + cArr.length + 1 + i3, length2), length2);
            }
            char charAt3 = charAt(indexOf + 1);
            while (charAt3 != ',' && charAt3 != '}') {
                if (isWhitespace(charAt3)) {
                    char charAt4 = charAt(indexOf + 2);
                    indexOf++;
                    charAt3 = charAt4;
                } else {
                    this.matchStat = -1;
                    return stringDefaultValue();
                }
            }
            this.bp = indexOf + 1;
            this.ch = charAt3;
            if (charAt3 == ',') {
                int i6 = this.bp + 1;
                this.bp = i6;
                this.ch = charAt(i6);
                this.matchStat = 3;
                return subString;
            }
            int i7 = this.bp + 1;
            this.bp = i7;
            char charAt5 = charAt(i7);
            if (charAt5 == ',') {
                this.token = 16;
                int i8 = this.bp + 1;
                this.bp = i8;
                this.ch = charAt(i8);
            } else if (charAt5 == ']') {
                this.token = 15;
                int i9 = this.bp + 1;
                this.bp = i9;
                this.ch = charAt(i9);
            } else if (charAt5 == '}') {
                this.token = 13;
                int i10 = this.bp + 1;
                this.bp = i10;
                this.ch = charAt(i10);
            } else if (charAt5 == 26) {
                this.token = 20;
            } else {
                this.bp = i;
                this.ch = c2;
                this.matchStat = -1;
                return stringDefaultValue();
            }
            this.matchStat = 4;
            return subString;
        }
        throw new JSONException("unclosed str");
    }

    public Date scanFieldDate(char[] cArr) {
        char c2;
        Date date;
        long j;
        char charAt;
        char[] cArr2 = cArr;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c3 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return null;
        }
        int length = this.bp + cArr2.length;
        int i2 = length + 1;
        char charAt2 = charAt(length);
        if (charAt2 == '\"') {
            int indexOf = indexOf(Typography.quote, i2);
            if (indexOf != -1) {
                this.bp = i2;
                if (scanISO8601DateIfMatch(false, indexOf - i2)) {
                    date = this.calendar.getTime();
                    c2 = charAt(indexOf + 1);
                    this.bp = i;
                    while (c2 != ',' && c2 != '}') {
                        if (isWhitespace(c2)) {
                            char charAt3 = charAt(indexOf + 2);
                            indexOf++;
                            c2 = charAt3;
                        } else {
                            this.matchStat = -1;
                            return null;
                        }
                    }
                    this.bp = indexOf + 1;
                    this.ch = c2;
                } else {
                    this.bp = i;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else {
            char c4 = '9';
            char c5 = '0';
            if (charAt2 == '-' || (charAt2 >= '0' && charAt2 <= '9')) {
                if (charAt2 == '-') {
                    charAt2 = charAt(i2);
                    i2 = length + 2;
                    z = true;
                }
                if (charAt2 < '0' || charAt2 > '9') {
                    j = 0;
                } else {
                    long j2 = (long) (charAt2 - '0');
                    while (true) {
                        int i3 = i2 + 1;
                        charAt = charAt(i2);
                        if (charAt >= c5 && charAt <= c4) {
                            j2 = (j2 * 10) + ((long) (charAt - '0'));
                            i2 = i3;
                            c4 = '9';
                            c5 = '0';
                        } else if (charAt == ',' || charAt == '}') {
                            this.bp = i2;
                        }
                    }
                    this.bp = i2;
                    long j3 = j2;
                    charAt2 = charAt;
                    j = j3;
                }
                if (j < 0) {
                    this.matchStat = -1;
                    return null;
                }
                if (z) {
                    j = -j;
                }
                date = new Date(j);
                c2 = charAt2;
            } else {
                this.matchStat = -1;
                return null;
            }
        }
        if (c2 == ',') {
            int i4 = this.bp + 1;
            this.bp = i4;
            this.ch = charAt(i4);
            this.matchStat = 3;
            this.token = 16;
            return date;
        }
        int i5 = this.bp + 1;
        this.bp = i5;
        char charAt4 = charAt(i5);
        if (charAt4 == ',') {
            this.token = 16;
            int i6 = this.bp + 1;
            this.bp = i6;
            this.ch = charAt(i6);
        } else if (charAt4 == ']') {
            this.token = 15;
            int i7 = this.bp + 1;
            this.bp = i7;
            this.ch = charAt(i7);
        } else if (charAt4 == '}') {
            this.token = 13;
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
        } else if (charAt4 == 26) {
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c3;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    public long scanFieldSymbol(char[] cArr) {
        this.matchStat = 0;
        while (!charArrayCompare(this.text, this.bp, cArr)) {
            if (isWhitespace(this.ch)) {
                next();
                while (isWhitespace(this.ch)) {
                    next();
                }
            } else {
                this.matchStat = -2;
                return 0;
            }
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char charAt = charAt(length);
        if (charAt != '\"') {
            while (isWhitespace(charAt)) {
                int i2 = i + 1;
                charAt = charAt(i);
                i = i2;
            }
            if (charAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
        }
        long j = -3750763034362895579L;
        while (true) {
            int i3 = i + 1;
            char charAt2 = charAt(i);
            if (charAt2 == '\"') {
                this.bp = i3;
                char charAt3 = charAt(this.bp);
                this.ch = charAt3;
                while (charAt3 != ',') {
                    if (charAt3 == '}') {
                        next();
                        skipWhitespace();
                        char current = getCurrent();
                        if (current == ',') {
                            this.token = 16;
                            int i4 = this.bp + 1;
                            this.bp = i4;
                            this.ch = charAt(i4);
                        } else if (current == ']') {
                            this.token = 15;
                            int i5 = this.bp + 1;
                            this.bp = i5;
                            this.ch = charAt(i5);
                        } else if (current == '}') {
                            this.token = 13;
                            int i6 = this.bp + 1;
                            this.bp = i6;
                            this.ch = charAt(i6);
                        } else if (current == 26) {
                            this.token = 20;
                        } else {
                            this.matchStat = -1;
                            return 0;
                        }
                        this.matchStat = 4;
                        return j;
                    } else if (isWhitespace(charAt3)) {
                        int i7 = this.bp + 1;
                        this.bp = i7;
                        charAt3 = charAt(i7);
                    } else {
                        this.matchStat = -1;
                        return 0;
                    }
                }
                int i8 = this.bp + 1;
                this.bp = i8;
                this.ch = charAt(i8);
                this.matchStat = 3;
                return j;
            } else if (i3 > this.len) {
                this.matchStat = -1;
                return 0;
            } else {
                j = (j ^ ((long) charAt2)) * 1099511628211L;
                i = i3;
            }
        }
    }

    public Collection<String> scanFieldStringArray(char[] cArr, Class<?> cls) {
        char c2;
        int i;
        char c3;
        boolean z;
        char c4;
        int i2;
        char[] cArr2 = cArr;
        this.matchStat = 0;
        while (true) {
            if (this.ch != 10 && this.ch != ' ') {
                break;
            }
            Class<?> cls2 = cls;
            int i3 = this.bp + 1;
            this.bp = i3;
            if (i3 >= this.len) {
                c2 = 26;
            } else {
                c2 = this.text.charAt(i3);
            }
            this.ch = c2;
        }
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return null;
        }
        Collection<String> newCollectionByType = newCollectionByType(cls);
        int i4 = this.bp;
        char c5 = this.ch;
        int length = this.bp + cArr2.length;
        int i5 = length + 1;
        if (charAt(length) == '[') {
            int i6 = length + 2;
            char charAt = charAt(i5);
            while (true) {
                if (charAt == '\"') {
                    int indexOf = indexOf(Typography.quote, i6);
                    if (indexOf != -1) {
                        String subString = subString(i6, indexOf - i6);
                        if (subString.indexOf(92) != -1) {
                            while (true) {
                                int i7 = indexOf - 1;
                                int i8 = 0;
                                while (i7 >= 0 && charAt(i7) == '\\') {
                                    i8++;
                                    i7--;
                                }
                                if (i8 % 2 == 0) {
                                    break;
                                }
                                indexOf = indexOf(Typography.quote, indexOf + 1);
                            }
                            int i9 = indexOf - i6;
                            subString = readString(sub_chars(i6, i9), i9);
                        }
                        int i10 = indexOf + 1;
                        i2 = indexOf + 2;
                        c4 = charAt(i10);
                        newCollectionByType.add(subString);
                    } else {
                        throw new JSONException("unclosed str");
                    }
                } else if (charAt == 'n' && this.text.startsWith("ull", i6)) {
                    char charAt2 = charAt(i6 + 3);
                    newCollectionByType.add((Object) null);
                    c4 = charAt2;
                    i2 = i6 + 4;
                } else if (charAt == ']' && newCollectionByType.size() == 0) {
                    i = i6 + 1;
                    c3 = charAt(i6);
                } else {
                    this.matchStat = -1;
                    return null;
                }
                if (c4 == ',') {
                    i6 = i2 + 1;
                    charAt = charAt(i2);
                } else if (c4 == ']') {
                    i = i2 + 1;
                    c3 = charAt(i2);
                    while (isWhitespace(c3)) {
                        char charAt3 = charAt(i);
                        i++;
                        c3 = charAt3;
                    }
                } else {
                    this.matchStat = -1;
                    return null;
                }
            }
        } else if (this.text.startsWith("ull", i5)) {
            i = length + 5;
            c3 = charAt(length + 4);
            newCollectionByType = null;
        } else {
            this.matchStat = -1;
            return null;
        }
        this.bp = i;
        if (c3 == ',') {
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            return newCollectionByType;
        } else if (c3 == '}') {
            char charAt4 = charAt(this.bp);
            do {
                if (charAt4 == ',') {
                    this.token = 16;
                    int i11 = this.bp + 1;
                    this.bp = i11;
                    this.ch = charAt(i11);
                } else if (charAt4 == ']') {
                    this.token = 15;
                    int i12 = this.bp + 1;
                    this.bp = i12;
                    this.ch = charAt(i12);
                } else if (charAt4 == '}') {
                    this.token = 13;
                    int i13 = this.bp + 1;
                    this.bp = i13;
                    this.ch = charAt(i13);
                } else if (charAt4 == 26) {
                    this.token = 20;
                    this.ch = charAt4;
                } else {
                    z = false;
                    while (isWhitespace(charAt4)) {
                        int i14 = i + 1;
                        char charAt5 = charAt(i);
                        this.bp = i14;
                        z = true;
                        char c6 = charAt5;
                        i = i14;
                        charAt4 = c6;
                    }
                }
                this.matchStat = 4;
                return newCollectionByType;
            } while (z);
            this.matchStat = -1;
            return null;
        } else {
            this.ch = c5;
            this.bp = i4;
            this.matchStat = -1;
            return null;
        }
    }

    public long scanFieldLong(char[] cArr) {
        int i;
        char charAt;
        char[] cArr2 = cArr;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        char c2 = this.ch;
        if (!charArrayCompare(this.text, this.bp, cArr2)) {
            this.matchStat = -2;
            return 0;
        }
        int length = this.bp + cArr2.length;
        int i3 = length + 1;
        char charAt2 = charAt(length);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(i3);
            i3 = length + 2;
        }
        if (charAt2 == '-') {
            charAt2 = charAt(i3);
            i3++;
            z = true;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            this.bp = i2;
            this.ch = c2;
            this.matchStat = -1;
            return 0;
        }
        long j = (long) (charAt2 - '0');
        while (true) {
            i = i3 + 1;
            charAt = charAt(i3);
            if (charAt >= '0' && charAt <= '9') {
                j = (j * 10) + ((long) (charAt - '0'));
                i3 = i;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (z2) {
            if (charAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            charAt = charAt(i);
            i = i3 + 2;
        }
        if (charAt == ',' || charAt == '}') {
            this.bp = i - 1;
        }
        if (j >= 0 || (j == Long.MIN_VALUE && z)) {
            while (charAt != ',') {
                if (charAt == '}') {
                    int i4 = 1;
                    int i5 = this.bp + 1;
                    this.bp = i5;
                    char charAt3 = charAt(i5);
                    while (true) {
                        if (charAt3 == ',') {
                            this.token = 16;
                            int i6 = this.bp + i4;
                            this.bp = i6;
                            this.ch = charAt(i6);
                            break;
                        } else if (charAt3 == ']') {
                            this.token = 15;
                            int i7 = this.bp + i4;
                            this.bp = i7;
                            this.ch = charAt(i7);
                            break;
                        } else if (charAt3 == '}') {
                            this.token = 13;
                            int i8 = this.bp + i4;
                            this.bp = i8;
                            this.ch = charAt(i8);
                            break;
                        } else if (charAt3 == 26) {
                            this.token = 20;
                            break;
                        } else if (isWhitespace(charAt3)) {
                            int i9 = this.bp + 1;
                            this.bp = i9;
                            charAt3 = charAt(i9);
                            i4 = 1;
                        } else {
                            this.bp = i2;
                            this.ch = c2;
                            this.matchStat = -1;
                            return 0;
                        }
                    }
                    this.matchStat = 4;
                    return z ? -j : j;
                } else if (isWhitespace(charAt)) {
                    this.bp = i;
                    charAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return 0;
                }
            }
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        }
        this.bp = i2;
        this.ch = c2;
        this.matchStat = -1;
        return 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x00fb A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x010c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanFieldBoolean(char[] r12) {
        /*
            r11 = this;
            r0 = 0
            r11.matchStat = r0
            java.lang.String r1 = r11.text
            int r2 = r11.bp
            boolean r1 = charArrayCompare(r1, r2, r12)
            if (r1 != 0) goto L_0x0011
            r12 = -2
            r11.matchStat = r12
            return r0
        L_0x0011:
            int r1 = r11.bp
            int r2 = r11.bp
            int r12 = r12.length
            int r2 = r2 + r12
            int r12 = r2 + 1
            char r3 = r11.charAt(r2)
            r4 = 34
            r5 = 1
            if (r3 != r4) goto L_0x0024
            r6 = r5
            goto L_0x0025
        L_0x0024:
            r6 = r0
        L_0x0025:
            if (r6 == 0) goto L_0x002e
            int r2 = r2 + 2
            char r3 = r11.charAt(r12)
            r12 = r2
        L_0x002e:
            r2 = 116(0x74, float:1.63E-43)
            r7 = 101(0x65, float:1.42E-43)
            r8 = 4
            r9 = -1
            if (r3 != r2) goto L_0x0071
            int r2 = r12 + 1
            char r3 = r11.charAt(r12)
            r10 = 114(0x72, float:1.6E-43)
            if (r3 == r10) goto L_0x0043
            r11.matchStat = r9
            return r0
        L_0x0043:
            int r3 = r12 + 2
            char r2 = r11.charAt(r2)
            r10 = 117(0x75, float:1.64E-43)
            if (r2 == r10) goto L_0x0050
            r11.matchStat = r9
            return r0
        L_0x0050:
            int r2 = r12 + 3
            char r3 = r11.charAt(r3)
            if (r3 == r7) goto L_0x005b
            r11.matchStat = r9
            return r0
        L_0x005b:
            if (r6 == 0) goto L_0x0068
            int r12 = r12 + r8
            char r2 = r11.charAt(r2)
            if (r2 == r4) goto L_0x0067
            r11.matchStat = r9
            return r0
        L_0x0067:
            r2 = r12
        L_0x0068:
            r11.bp = r2
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            goto L_0x00d8
        L_0x0071:
            r2 = 102(0x66, float:1.43E-43)
            if (r3 != r2) goto L_0x00be
            int r2 = r12 + 1
            char r3 = r11.charAt(r12)
            r10 = 97
            if (r3 == r10) goto L_0x0082
            r11.matchStat = r9
            return r0
        L_0x0082:
            int r3 = r12 + 2
            char r2 = r11.charAt(r2)
            r10 = 108(0x6c, float:1.51E-43)
            if (r2 == r10) goto L_0x008f
            r11.matchStat = r9
            return r0
        L_0x008f:
            int r2 = r12 + 3
            char r3 = r11.charAt(r3)
            r10 = 115(0x73, float:1.61E-43)
            if (r3 == r10) goto L_0x009c
            r11.matchStat = r9
            return r0
        L_0x009c:
            int r3 = r12 + 4
            char r2 = r11.charAt(r2)
            if (r2 == r7) goto L_0x00a7
            r11.matchStat = r9
            return r0
        L_0x00a7:
            if (r6 == 0) goto L_0x00b5
            int r12 = r12 + 5
            char r2 = r11.charAt(r3)
            if (r2 == r4) goto L_0x00b4
            r11.matchStat = r9
            return r0
        L_0x00b4:
            r3 = r12
        L_0x00b5:
            r11.bp = r3
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
            goto L_0x00f4
        L_0x00be:
            r2 = 49
            if (r3 != r2) goto L_0x00da
            if (r6 == 0) goto L_0x00d0
            int r2 = r12 + 1
            char r12 = r11.charAt(r12)
            if (r12 == r4) goto L_0x00cf
            r11.matchStat = r9
            return r0
        L_0x00cf:
            r12 = r2
        L_0x00d0:
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
        L_0x00d8:
            r2 = r5
            goto L_0x00f5
        L_0x00da:
            r2 = 48
            if (r3 != r2) goto L_0x0188
            if (r6 == 0) goto L_0x00ec
            int r2 = r12 + 1
            char r12 = r11.charAt(r12)
            if (r12 == r4) goto L_0x00eb
            r11.matchStat = r9
            return r0
        L_0x00eb:
            r12 = r2
        L_0x00ec:
            r11.bp = r12
            int r12 = r11.bp
            char r12 = r11.charAt(r12)
        L_0x00f4:
            r2 = r0
        L_0x00f5:
            r3 = 16
            r4 = 44
            if (r12 != r4) goto L_0x010c
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            r11.ch = r12
            r12 = 3
            r11.matchStat = r12
            r11.token = r3
            goto L_0x0159
        L_0x010c:
            r6 = 125(0x7d, float:1.75E-43)
            if (r12 != r6) goto L_0x016d
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
        L_0x0119:
            if (r12 != r4) goto L_0x0129
            r11.token = r3
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0157
        L_0x0129:
            r1 = 93
            if (r12 != r1) goto L_0x013d
            r12 = 15
            r11.token = r12
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0157
        L_0x013d:
            if (r12 != r6) goto L_0x014f
            r12 = 13
            r11.token = r12
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            r11.ch = r12
            goto L_0x0157
        L_0x014f:
            r1 = 26
            if (r12 != r1) goto L_0x015a
            r12 = 20
            r11.token = r12
        L_0x0157:
            r11.matchStat = r8
        L_0x0159:
            return r2
        L_0x015a:
            boolean r12 = isWhitespace(r12)
            if (r12 == 0) goto L_0x016a
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            goto L_0x0119
        L_0x016a:
            r11.matchStat = r9
            return r0
        L_0x016d:
            boolean r12 = isWhitespace(r12)
            if (r12 == 0) goto L_0x017e
            int r12 = r11.bp
            int r12 = r12 + r5
            r11.bp = r12
            char r12 = r11.charAt(r12)
            goto L_0x00f5
        L_0x017e:
            r11.bp = r1
            int r12 = r11.bp
            r11.charAt(r12)
            r11.matchStat = r9
            return r0
        L_0x0188:
            r11.matchStat = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.parser.JSONScanner.scanFieldBoolean(char[]):boolean");
    }

    public final int scanInt(char c2) {
        int i;
        char charAt;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = this.bp;
        int i4 = i3 + 1;
        char charAt2 = charAt(i3);
        while (isWhitespace(charAt2)) {
            char charAt3 = charAt(i4);
            i4++;
            charAt2 = charAt3;
        }
        boolean z = true;
        boolean z2 = charAt2 == '\"';
        if (z2) {
            char charAt4 = charAt(i4);
            i4++;
            charAt2 = charAt4;
        }
        if (charAt2 != '-') {
            z = false;
        }
        if (z) {
            char charAt5 = charAt(i4);
            i4++;
            charAt2 = charAt5;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            if (charAt2 == 'n') {
                int i5 = i4 + 1;
                if (charAt(i4) == 'u') {
                    int i6 = i4 + 2;
                    if (charAt(i5) == 'l') {
                        int i7 = i4 + 3;
                        if (charAt(i6) == 'l') {
                            this.matchStat = 5;
                            int i8 = i4 + 4;
                            char charAt6 = charAt(i7);
                            if (z2 && charAt6 == '\"') {
                                charAt6 = charAt(i8);
                                i8 = i4 + 5;
                            }
                            while (charAt6 != ',') {
                                if (charAt6 == ']') {
                                    this.bp = i8;
                                    this.ch = charAt(this.bp);
                                    this.matchStat = 5;
                                    this.token = 15;
                                    return 0;
                                } else if (isWhitespace(charAt6)) {
                                    char charAt7 = charAt(i8);
                                    i8++;
                                    charAt6 = charAt7;
                                } else {
                                    this.matchStat = -1;
                                    return 0;
                                }
                            }
                            this.bp = i8;
                            this.ch = charAt(this.bp);
                            this.matchStat = 5;
                            this.token = 16;
                            return 0;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return 0;
        }
        int i9 = charAt2 - '0';
        while (true) {
            i = i4 + 1;
            charAt = charAt(i4);
            if (charAt >= '0' && charAt <= '9') {
                int i10 = i9 * 10;
                if (i10 >= i9) {
                    i9 = i10 + (charAt - '0');
                    i4 = i;
                } else {
                    throw new JSONException("parseInt error : " + subString(i2, i4));
                }
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (z2) {
            if (charAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            charAt = charAt(i);
            i = i4 + 2;
        }
        if (i9 < 0) {
            this.matchStat = -1;
            return 0;
        }
        char c3 = c2;
        while (charAt != c3) {
            if (isWhitespace(charAt)) {
                charAt = charAt(i);
                i++;
            } else {
                this.matchStat = -1;
                return z ? -i9 : i9;
            }
        }
        this.bp = i;
        this.ch = charAt(this.bp);
        this.matchStat = 3;
        this.token = 16;
        return z ? -i9 : i9;
    }

    public double scanDouble(char c2) {
        long j;
        int i;
        char charAt;
        int i2;
        long j2;
        char c3;
        int i3;
        int i4;
        double d;
        char charAt2;
        this.matchStat = 0;
        int i5 = this.bp;
        int i6 = i5 + 1;
        char charAt3 = charAt(i5);
        boolean z = charAt3 == '\"';
        if (z) {
            charAt3 = charAt(i6);
            i6 = i5 + 2;
        }
        boolean z2 = charAt3 == '-';
        if (z2) {
            charAt3 = charAt(i6);
            i6++;
        }
        if (charAt3 >= '0') {
            char c4 = '9';
            if (charAt3 <= '9') {
                long j3 = (long) (charAt3 - '0');
                while (true) {
                    i = i6 + 1;
                    charAt = charAt(i6);
                    if (charAt >= '0' && charAt <= '9') {
                        j3 = (j * 10) + ((long) (charAt - '0'));
                        i6 = i;
                    }
                }
                if (charAt == '.') {
                    int i7 = i6 + 2;
                    char charAt4 = charAt(i);
                    if (charAt4 < '0' || charAt4 > '9') {
                        this.matchStat = -1;
                        return 0.0d;
                    }
                    j = (j * 10) + ((long) (charAt4 - '0'));
                    long j4 = 10;
                    while (true) {
                        i = i7 + 1;
                        charAt2 = charAt(i7);
                        if (charAt2 < '0' || charAt2 > c4) {
                            long j5 = j4;
                            charAt = charAt2;
                            j2 = j5;
                        } else {
                            j = (j * 10) + ((long) (charAt2 - '0'));
                            j4 *= 10;
                            i7 = i;
                            c4 = '9';
                        }
                    }
                    long j52 = j4;
                    charAt = charAt2;
                    j2 = j52;
                } else {
                    j2 = 1;
                }
                boolean z3 = c3 == 'e' || c3 == 'E';
                if (z3) {
                    int i8 = i2 + 1;
                    char charAt5 = charAt(i2);
                    if (charAt5 == '+' || charAt5 == '-') {
                        i2 += 2;
                        c3 = charAt(i8);
                    } else {
                        i2 = i8;
                        c3 = charAt5;
                    }
                    while (c3 >= '0' && c3 <= '9') {
                        char charAt6 = charAt(i2);
                        i2++;
                        c3 = charAt6;
                    }
                }
                if (!z) {
                    i4 = this.bp;
                    i3 = (i2 - i4) - 1;
                } else if (c3 != '\"') {
                    this.matchStat = -1;
                    return 0.0d;
                } else {
                    int i9 = i2 + 1;
                    char charAt7 = charAt(i2);
                    i4 = this.bp + 1;
                    i3 = (i9 - i4) - 2;
                    char c5 = charAt7;
                    i2 = i9;
                    c3 = c5;
                }
                if (z3 || i3 >= 18) {
                    d = Double.parseDouble(subString(i4, i3));
                } else {
                    d = ((double) j) / ((double) j2);
                    if (z2) {
                        d = -d;
                    }
                }
                if (c3 == c2) {
                    this.bp = i2;
                    this.ch = charAt(this.bp);
                    this.matchStat = 3;
                    this.token = 16;
                    return d;
                }
                this.matchStat = -1;
                return d;
            }
        }
        if (charAt3 == 'n') {
            int i10 = i6 + 1;
            if (charAt(i6) == 'u') {
                int i11 = i6 + 2;
                if (charAt(i10) == 'l') {
                    int i12 = i6 + 3;
                    if (charAt(i11) == 'l') {
                        this.matchStat = 5;
                        int i13 = i6 + 4;
                        char charAt8 = charAt(i12);
                        if (z && charAt8 == '\"') {
                            charAt8 = charAt(i13);
                            i13 = i6 + 5;
                        }
                        while (charAt8 != ',') {
                            if (charAt8 == ']') {
                                this.bp = i13;
                                this.ch = charAt(this.bp);
                                this.matchStat = 5;
                                this.token = 15;
                                return 0.0d;
                            } else if (isWhitespace(charAt8)) {
                                char charAt9 = charAt(i13);
                                i13++;
                                charAt8 = charAt9;
                            } else {
                                this.matchStat = -1;
                                return 0.0d;
                            }
                        }
                        this.bp = i13;
                        this.ch = charAt(this.bp);
                        this.matchStat = 5;
                        this.token = 16;
                        return 0.0d;
                    }
                }
            }
        }
        this.matchStat = -1;
        return 0.0d;
    }

    public long scanLong(char c2) {
        int i;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char charAt2 = charAt(i2);
        boolean z2 = charAt2 == '\"';
        if (z2) {
            charAt2 = charAt(i3);
            i3 = i2 + 2;
        }
        if (charAt2 == '-') {
            z = true;
        }
        if (z) {
            charAt2 = charAt(i3);
            i3++;
        }
        if (charAt2 < '0' || charAt2 > '9') {
            if (charAt2 == 'n') {
                int i4 = i3 + 1;
                if (charAt(i3) == 'u') {
                    int i5 = i3 + 2;
                    if (charAt(i4) == 'l') {
                        int i6 = i3 + 3;
                        if (charAt(i5) == 'l') {
                            this.matchStat = 5;
                            int i7 = i3 + 4;
                            char charAt3 = charAt(i6);
                            if (z2 && charAt3 == '\"') {
                                charAt3 = charAt(i7);
                                i7 = i3 + 5;
                            }
                            while (charAt3 != ',') {
                                if (charAt3 == ']') {
                                    this.bp = i7;
                                    this.ch = charAt(this.bp);
                                    this.matchStat = 5;
                                    this.token = 15;
                                    return 0;
                                } else if (isWhitespace(charAt3)) {
                                    char charAt4 = charAt(i7);
                                    i7++;
                                    charAt3 = charAt4;
                                } else {
                                    this.matchStat = -1;
                                    return 0;
                                }
                            }
                            this.bp = i7;
                            this.ch = charAt(this.bp);
                            this.matchStat = 5;
                            this.token = 16;
                            return 0;
                        }
                    }
                }
            }
            this.matchStat = -1;
            return 0;
        }
        long j = (long) (charAt2 - '0');
        while (true) {
            i = i3 + 1;
            charAt = charAt(i3);
            if (charAt >= '0' && charAt <= '9') {
                j = (j * 10) + ((long) (charAt - '0'));
                i3 = i;
            }
        }
        if (charAt == '.') {
            this.matchStat = -1;
            return 0;
        }
        if (z2) {
            if (charAt != '\"') {
                this.matchStat = -1;
                return 0;
            }
            charAt = charAt(i);
            i = i3 + 2;
        }
        if (j >= 0 || (j == Long.MIN_VALUE && z)) {
            char c3 = c2;
            while (charAt != c3) {
                if (isWhitespace(charAt)) {
                    charAt = charAt(i);
                    i++;
                } else {
                    this.matchStat = -1;
                    return j;
                }
            }
            this.bp = i;
            this.ch = charAt(this.bp);
            this.matchStat = 3;
            this.token = 16;
            return z ? -j : j;
        }
        this.matchStat = -1;
        return 0;
    }

    public Date scanDate(char c2) {
        char c3;
        Date date;
        long j;
        char c4;
        char charAt;
        boolean z = false;
        this.matchStat = 0;
        int i = this.bp;
        char c5 = this.ch;
        int i2 = this.bp;
        int i3 = i2 + 1;
        char charAt2 = charAt(i2);
        if (charAt2 == '\"') {
            int indexOf = indexOf(Typography.quote, i3);
            if (indexOf != -1) {
                this.bp = i3;
                if (scanISO8601DateIfMatch(false, indexOf - i3)) {
                    date = this.calendar.getTime();
                    c3 = charAt(indexOf + 1);
                    this.bp = i;
                    while (c3 != ',' && c3 != ']') {
                        if (isWhitespace(c3)) {
                            c3 = charAt(indexOf + 2);
                            indexOf++;
                        } else {
                            this.bp = i;
                            this.ch = c5;
                            this.matchStat = -1;
                            return null;
                        }
                    }
                    this.bp = indexOf + 1;
                    this.ch = c3;
                } else {
                    this.bp = i;
                    this.ch = c5;
                    this.matchStat = -1;
                    return null;
                }
            } else {
                throw new JSONException("unclosed str");
            }
        } else if (charAt2 == '-' || (charAt2 >= '0' && charAt2 <= '9')) {
            if (charAt2 == '-') {
                charAt2 = charAt(i3);
                i3 = i2 + 2;
                z = true;
            }
            if (charAt2 < '0' || charAt2 > '9') {
                c4 = charAt2;
                j = 0;
            } else {
                j = (long) (charAt2 - '0');
                while (true) {
                    int i4 = i3 + 1;
                    charAt = charAt(i3);
                    if (charAt >= '0' && charAt <= '9') {
                        j = (j * 10) + ((long) (charAt - '0'));
                        i3 = i4;
                    } else if (charAt == ',' || charAt == ']') {
                        this.bp = i3;
                    }
                }
                this.bp = i3;
                c4 = charAt;
            }
            if (j < 0) {
                this.bp = i;
                this.ch = c5;
                this.matchStat = -1;
                return null;
            }
            if (z) {
                j = -j;
            }
            date = new Date(j);
        } else {
            if (charAt2 == 'n') {
                int i5 = i2 + 2;
                if (charAt(i3) == 'u') {
                    int i6 = i2 + 3;
                    if (charAt(i5) == 'l') {
                        int i7 = i2 + 4;
                        if (charAt(i6) == 'l') {
                            c3 = charAt(i7);
                            this.bp = i7;
                            date = null;
                        }
                    }
                }
            }
            this.bp = i;
            this.ch = c5;
            this.matchStat = -1;
            return null;
        }
        if (c3 == ',') {
            int i8 = this.bp + 1;
            this.bp = i8;
            this.ch = charAt(i8);
            this.matchStat = 3;
            return date;
        }
        int i9 = this.bp + 1;
        this.bp = i9;
        char charAt3 = charAt(i9);
        if (charAt3 == ',') {
            this.token = 16;
            int i10 = this.bp + 1;
            this.bp = i10;
            this.ch = charAt(i10);
        } else if (charAt3 == ']') {
            this.token = 15;
            int i11 = this.bp + 1;
            this.bp = i11;
            this.ch = charAt(i11);
        } else if (charAt3 == '}') {
            this.token = 13;
            int i12 = this.bp + 1;
            this.bp = i12;
            this.ch = charAt(i12);
        } else if (charAt3 == 26) {
            this.ch = JSONLexer.EOI;
            this.token = 20;
        } else {
            this.bp = i;
            this.ch = c5;
            this.matchStat = -1;
            return null;
        }
        this.matchStat = 4;
        return date;
    }

    /* access modifiers changed from: protected */
    public final void arrayCopy(int i, char[] cArr, int i2, int i3) {
        this.text.getChars(i, i3 + i, cArr, i2);
    }

    public String info() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (i3 < this.bp) {
            if (this.text.charAt(i3) == 10) {
                i++;
                i2 = 1;
            }
            i3++;
            i2++;
        }
        sb.append("pos ");
        sb.append(this.bp);
        sb.append(", line ");
        sb.append(i);
        sb.append(", column ");
        sb.append(i2);
        if (this.text.length() < 65535) {
            sb.append(this.text);
        } else {
            sb.append(this.text.substring(0, 65535));
        }
        return sb.toString();
    }

    public String[] scanFieldStringArray(char[] cArr, int i, SymbolTable symbolTable) {
        char c2;
        int i2;
        int i3 = this.bp;
        char c3 = this.ch;
        while (isWhitespace(this.ch)) {
            next();
        }
        if (cArr != null) {
            this.matchStat = 0;
            if (!charArrayCompare(cArr)) {
                this.matchStat = -2;
                return null;
            }
            int length = this.bp + cArr.length;
            int i4 = length + 1;
            char charAt = this.text.charAt(length);
            while (isWhitespace(charAt)) {
                charAt = this.text.charAt(i4);
                i4++;
            }
            if (charAt == ':') {
                i2 = i4 + 1;
                c2 = this.text.charAt(i4);
                while (isWhitespace(c2)) {
                    c2 = this.text.charAt(i2);
                    i2++;
                }
            } else {
                this.matchStat = -1;
                return null;
            }
        } else {
            i2 = this.bp + 1;
            c2 = this.ch;
        }
        if (c2 == '[') {
            this.bp = i2;
            this.ch = this.text.charAt(this.bp);
            String[] strArr = i >= 0 ? new String[i] : new String[4];
            int i5 = 0;
            while (true) {
                if (isWhitespace(this.ch)) {
                    next();
                } else if (this.ch != '\"') {
                    this.bp = i3;
                    this.ch = c3;
                    this.matchStat = -1;
                    return null;
                } else {
                    String scanSymbol = scanSymbol(symbolTable, Typography.quote);
                    if (i5 == strArr.length) {
                        String[] strArr2 = new String[(strArr.length + (strArr.length >> 1) + 1)];
                        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
                        strArr = strArr2;
                    }
                    int i6 = i5 + 1;
                    strArr[i5] = scanSymbol;
                    while (isWhitespace(this.ch)) {
                        next();
                    }
                    if (this.ch == ',') {
                        next();
                        i5 = i6;
                    } else {
                        if (strArr.length != i6) {
                            String[] strArr3 = new String[i6];
                            System.arraycopy(strArr, 0, strArr3, 0, i6);
                            strArr = strArr3;
                        }
                        while (isWhitespace(this.ch)) {
                            next();
                        }
                        if (this.ch == ']') {
                            next();
                            return strArr;
                        }
                        this.bp = i3;
                        this.ch = c3;
                        this.matchStat = -1;
                        return null;
                    }
                }
            }
        } else if (c2 != 'n' || !this.text.startsWith("ull", this.bp + 1)) {
            this.matchStat = -1;
            return null;
        } else {
            this.bp += 4;
            this.ch = this.text.charAt(this.bp);
            return null;
        }
    }

    public boolean matchField2(char[] cArr) {
        while (isWhitespace(this.ch)) {
            next();
        }
        if (!charArrayCompare(cArr)) {
            this.matchStat = -2;
            return false;
        }
        int length = this.bp + cArr.length;
        int i = length + 1;
        char charAt = this.text.charAt(length);
        while (isWhitespace(charAt)) {
            charAt = this.text.charAt(i);
            i++;
        }
        if (charAt == ':') {
            this.bp = i;
            this.ch = charAt(this.bp);
            return true;
        }
        this.matchStat = -2;
        return false;
    }

    public final void skipObject() {
        skipObject(false);
    }

    public final void skipObject(boolean z) {
        int i = this.bp;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char charAt = this.text.charAt(i);
            if (charAt == '\\') {
                if (i < this.len - 1) {
                    i++;
                } else {
                    this.ch = charAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
            } else if (charAt == '\"') {
                z2 = !z2;
            } else if (charAt == '{') {
                if (!z2) {
                    i2++;
                }
            } else if (charAt == '}' && !z2 && i2 - 1 == -1) {
                this.bp = i + 1;
                int i3 = this.bp;
                int length = this.text.length();
                char c2 = JSONLexer.EOI;
                if (i3 == length) {
                    this.ch = JSONLexer.EOI;
                    this.token = 20;
                    return;
                }
                this.ch = this.text.charAt(this.bp);
                if (this.ch == ',') {
                    this.token = 16;
                    int i4 = this.bp + 1;
                    this.bp = i4;
                    if (i4 < this.text.length()) {
                        c2 = this.text.charAt(i4);
                    }
                    this.ch = c2;
                    return;
                } else if (this.ch == '}') {
                    this.token = 13;
                    next();
                    return;
                } else if (this.ch == ']') {
                    this.token = 15;
                    next();
                    return;
                } else {
                    nextToken(16);
                    return;
                }
            }
            i++;
        }
        for (int i5 = 0; i5 < this.bp; i5++) {
            if (i5 < this.text.length() && this.text.charAt(i5) == ' ') {
                i++;
            }
        }
        if (i == this.text.length()) {
            throw new JSONException("illegal str, " + info());
        }
    }

    public final void skipArray() {
        skipArray(false);
    }

    public final void skipArray(boolean z) {
        int i = this.bp;
        boolean z2 = false;
        int i2 = 0;
        while (i < this.text.length()) {
            char charAt = this.text.charAt(i);
            if (charAt == '\\') {
                if (i < this.len - 1) {
                    i++;
                } else {
                    this.ch = charAt;
                    this.bp = i;
                    throw new JSONException("illegal str, " + info());
                }
            } else if (charAt == '\"') {
                z2 = !z2;
            } else if (charAt != '[') {
                char c2 = JSONLexer.EOI;
                if (charAt == '{' && z) {
                    int i3 = this.bp + 1;
                    this.bp = i3;
                    if (i3 < this.text.length()) {
                        c2 = this.text.charAt(i3);
                    }
                    this.ch = c2;
                    skipObject(z);
                } else if (charAt == ']' && !z2 && i2 - 1 == -1) {
                    this.bp = i + 1;
                    if (this.bp == this.text.length()) {
                        this.ch = JSONLexer.EOI;
                        this.token = 20;
                        return;
                    }
                    this.ch = this.text.charAt(this.bp);
                    nextToken(16);
                    return;
                }
            } else if (!z2) {
                i2++;
            }
            i++;
        }
        if (i == this.text.length()) {
            throw new JSONException("illegal str, " + info());
        }
    }

    public final void skipString() {
        if (this.ch == '\"') {
            int i = this.bp;
            while (true) {
                i++;
                if (i < this.text.length()) {
                    char charAt = this.text.charAt(i);
                    if (charAt == '\\') {
                        if (i < this.len - 1) {
                            i++;
                        }
                    } else if (charAt == '\"') {
                        String str = this.text;
                        int i2 = i + 1;
                        this.bp = i2;
                        this.ch = str.charAt(i2);
                        return;
                    }
                } else {
                    throw new JSONException("unclosed str");
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public boolean seekArrayToItem(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("index must > 0, but " + i);
        } else if (this.token == 20) {
            return false;
        } else {
            if (this.token == 14) {
                for (int i2 = 0; i2 < i; i2++) {
                    skipWhitespace();
                    if (this.ch == '\"' || this.ch == '\'') {
                        skipString();
                        if (this.ch == ',') {
                            next();
                        } else if (this.ch == ']') {
                            next();
                            nextToken(16);
                            return false;
                        } else {
                            throw new JSONException("illegal json.");
                        }
                    } else {
                        if (this.ch == '{') {
                            next();
                            this.token = 12;
                            skipObject(false);
                        } else if (this.ch == '[') {
                            next();
                            this.token = 14;
                            skipArray(false);
                        } else {
                            int i3 = this.bp + 1;
                            while (i3 < this.text.length()) {
                                char charAt = this.text.charAt(i3);
                                if (charAt == ',') {
                                    this.bp = i3 + 1;
                                    this.ch = charAt(this.bp);
                                } else if (charAt == ']') {
                                    this.bp = i3 + 1;
                                    this.ch = charAt(this.bp);
                                    nextToken();
                                    return false;
                                } else {
                                    i3++;
                                }
                            }
                            throw new JSONException("illegal json.");
                        }
                        if (this.token != 16) {
                            if (this.token == 15) {
                                return false;
                            }
                            throw new UnsupportedOperationException();
                        }
                    }
                }
                nextToken();
                return true;
            }
            throw new UnsupportedOperationException();
        }
    }

    public int seekObjectToField(long j, boolean z) {
        char c2;
        char c3;
        char c4;
        char c5;
        char c6;
        char c7;
        char c8;
        int i = -1;
        if (this.token == 20) {
            return -1;
        }
        if (this.token != 13) {
            int i2 = 15;
            if (this.token != 15) {
                int i3 = 16;
                if (this.token == 12 || this.token == 16) {
                    while (this.ch != '}') {
                        if (this.ch == 26) {
                            return i;
                        }
                        if (this.ch != '\"') {
                            skipWhitespace();
                        }
                        if (this.ch == '\"') {
                            int i4 = this.bp + 1;
                            long j2 = -3750763034362895579L;
                            while (true) {
                                if (i4 >= this.text.length()) {
                                    break;
                                }
                                char charAt = this.text.charAt(i4);
                                if (charAt == '\\') {
                                    i4++;
                                    if (i4 != this.text.length()) {
                                        charAt = this.text.charAt(i4);
                                    } else {
                                        throw new JSONException("unclosed str, " + info());
                                    }
                                }
                                if (charAt == '\"') {
                                    this.bp = i4 + 1;
                                    if (this.bp >= this.text.length()) {
                                        c8 = 26;
                                    } else {
                                        c8 = this.text.charAt(this.bp);
                                    }
                                    this.ch = c8;
                                } else {
                                    j2 = (j2 ^ ((long) charAt)) * 1099511628211L;
                                    i4++;
                                }
                            }
                            if (j2 == j) {
                                if (this.ch != ':') {
                                    skipWhitespace();
                                }
                                if (this.ch != ':') {
                                    return 3;
                                }
                                int i5 = this.bp + 1;
                                this.bp = i5;
                                if (i5 >= this.text.length()) {
                                    c2 = JSONLexer.EOI;
                                } else {
                                    c2 = this.text.charAt(i5);
                                }
                                this.ch = c2;
                                if (this.ch == ',') {
                                    int i6 = this.bp + 1;
                                    this.bp = i6;
                                    if (i6 >= this.text.length()) {
                                        c5 = JSONLexer.EOI;
                                    } else {
                                        c5 = this.text.charAt(i6);
                                    }
                                    this.ch = c5;
                                    this.token = i3;
                                    return 3;
                                } else if (this.ch == ']') {
                                    int i7 = this.bp + 1;
                                    this.bp = i7;
                                    if (i7 >= this.text.length()) {
                                        c4 = JSONLexer.EOI;
                                    } else {
                                        c4 = this.text.charAt(i7);
                                    }
                                    this.ch = c4;
                                    this.token = i2;
                                    return 3;
                                } else if (this.ch == '}') {
                                    int i8 = this.bp + 1;
                                    this.bp = i8;
                                    if (i8 >= this.text.length()) {
                                        c3 = JSONLexer.EOI;
                                    } else {
                                        c3 = this.text.charAt(i8);
                                    }
                                    this.ch = c3;
                                    this.token = 13;
                                    return 3;
                                } else if (this.ch < '0' || this.ch > '9') {
                                    nextToken(2);
                                    return 3;
                                } else {
                                    this.sp = 0;
                                    this.pos = this.bp;
                                    scanNumber();
                                    return 3;
                                }
                            } else {
                                if (this.ch != ':') {
                                    skipWhitespace();
                                }
                                if (this.ch == ':') {
                                    int i9 = this.bp + 1;
                                    this.bp = i9;
                                    if (i9 >= this.text.length()) {
                                        c6 = JSONLexer.EOI;
                                    } else {
                                        c6 = this.text.charAt(i9);
                                    }
                                    this.ch = c6;
                                    if (!(this.ch == '\"' || this.ch == '\'' || this.ch == '{' || this.ch == '[' || this.ch == '0' || this.ch == '1' || this.ch == '2' || this.ch == '3' || this.ch == '4' || this.ch == '5' || this.ch == '6' || this.ch == '7' || this.ch == '8' || this.ch == '9' || this.ch == '+' || this.ch == '-')) {
                                        skipWhitespace();
                                    }
                                    if (this.ch == '-' || this.ch == '+' || (this.ch >= '0' && this.ch <= '9')) {
                                        next();
                                        while (this.ch >= '0' && this.ch <= '9') {
                                            next();
                                        }
                                        if (this.ch == '.') {
                                            next();
                                            while (this.ch >= '0' && this.ch <= '9') {
                                                next();
                                            }
                                        }
                                        if (this.ch == 'E' || this.ch == 'e') {
                                            next();
                                            if (this.ch == '-' || this.ch == '+') {
                                                next();
                                            }
                                            while (this.ch >= '0' && this.ch <= '9') {
                                                next();
                                            }
                                        }
                                        if (this.ch != ',') {
                                            skipWhitespace();
                                        }
                                        if (this.ch == ',') {
                                            next();
                                        }
                                    } else if (this.ch == '\"') {
                                        skipString();
                                        if (!(this.ch == ',' || this.ch == '}')) {
                                            skipWhitespace();
                                        }
                                        if (this.ch == ',') {
                                            next();
                                        }
                                    } else if (this.ch == 't') {
                                        next();
                                        if (this.ch == 'r') {
                                            next();
                                            if (this.ch == 'u') {
                                                next();
                                                if (this.ch == 'e') {
                                                    next();
                                                }
                                            }
                                        }
                                        if (!(this.ch == ',' || this.ch == '}')) {
                                            skipWhitespace();
                                        }
                                        if (this.ch == ',') {
                                            next();
                                        }
                                    } else if (this.ch == 'n') {
                                        next();
                                        if (this.ch == 'u') {
                                            next();
                                            if (this.ch == 'l') {
                                                next();
                                                if (this.ch == 'l') {
                                                    next();
                                                }
                                            }
                                        }
                                        if (!(this.ch == ',' || this.ch == '}')) {
                                            skipWhitespace();
                                        }
                                        if (this.ch == ',') {
                                            next();
                                        }
                                    } else if (this.ch == 'f') {
                                        next();
                                        if (this.ch == 'a') {
                                            next();
                                            if (this.ch == 'l') {
                                                next();
                                                if (this.ch == 's') {
                                                    next();
                                                    if (this.ch == 'e') {
                                                        next();
                                                    }
                                                }
                                            }
                                        }
                                        if (!(this.ch == ',' || this.ch == '}')) {
                                            skipWhitespace();
                                        }
                                        if (this.ch == ',') {
                                            next();
                                        }
                                    } else if (this.ch == '{') {
                                        int i10 = this.bp + 1;
                                        this.bp = i10;
                                        if (i10 >= this.text.length()) {
                                            c7 = JSONLexer.EOI;
                                        } else {
                                            c7 = this.text.charAt(i10);
                                        }
                                        this.ch = c7;
                                        if (z) {
                                            this.token = 12;
                                            return 1;
                                        }
                                        skipObject(false);
                                        if (this.token == 13) {
                                            return -1;
                                        }
                                    } else if (this.ch == '[') {
                                        next();
                                        if (z) {
                                            this.token = 14;
                                            return 2;
                                        }
                                        skipArray(false);
                                        if (this.token == 13) {
                                            return -1;
                                        }
                                    } else {
                                        throw new UnsupportedOperationException();
                                    }
                                    i = -1;
                                    i2 = 15;
                                    i3 = 16;
                                } else {
                                    throw new JSONException("illegal json, " + info());
                                }
                            }
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }
                    next();
                    nextToken();
                    return i;
                }
                throw new UnsupportedOperationException(JSONToken.name(this.token));
            }
        }
        nextToken();
        return -1;
    }

    public int seekObjectToField(long[] jArr) {
        char c2;
        char c3;
        char c4;
        if (this.token == 12 || this.token == 16) {
            while (this.ch != '}') {
                char c5 = this.ch;
                char c6 = JSONLexer.EOI;
                if (c5 == 26) {
                    this.matchStat = -1;
                    return -1;
                }
                if (this.ch != '\"') {
                    skipWhitespace();
                }
                if (this.ch == '\"') {
                    int i = this.bp + 1;
                    long j = -3750763034362895579L;
                    while (true) {
                        if (i >= this.text.length()) {
                            break;
                        }
                        char charAt = this.text.charAt(i);
                        if (charAt == '\\') {
                            i++;
                            if (i != this.text.length()) {
                                charAt = this.text.charAt(i);
                            } else {
                                throw new JSONException("unclosed str, " + info());
                            }
                        }
                        if (charAt == '\"') {
                            this.bp = i + 1;
                            if (this.bp >= this.text.length()) {
                                c4 = 26;
                            } else {
                                c4 = this.text.charAt(this.bp);
                            }
                            this.ch = c4;
                        } else {
                            j = (j ^ ((long) charAt)) * 1099511628211L;
                            i++;
                        }
                    }
                    int i2 = 0;
                    while (true) {
                        if (i2 >= jArr.length) {
                            i2 = -1;
                            break;
                        } else if (j == jArr[i2]) {
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i2 != -1) {
                        if (this.ch != ':') {
                            skipWhitespace();
                        }
                        if (this.ch == ':') {
                            int i3 = this.bp + 1;
                            this.bp = i3;
                            if (i3 >= this.text.length()) {
                                c2 = 26;
                            } else {
                                c2 = this.text.charAt(i3);
                            }
                            this.ch = c2;
                            if (this.ch == ',') {
                                int i4 = this.bp + 1;
                                this.bp = i4;
                                if (i4 < this.text.length()) {
                                    c6 = this.text.charAt(i4);
                                }
                                this.ch = c6;
                                this.token = 16;
                            } else if (this.ch == ']') {
                                int i5 = this.bp + 1;
                                this.bp = i5;
                                if (i5 < this.text.length()) {
                                    c6 = this.text.charAt(i5);
                                }
                                this.ch = c6;
                                this.token = 15;
                            } else if (this.ch == '}') {
                                int i6 = this.bp + 1;
                                this.bp = i6;
                                if (i6 < this.text.length()) {
                                    c6 = this.text.charAt(i6);
                                }
                                this.ch = c6;
                                this.token = 13;
                            } else if (this.ch < '0' || this.ch > '9') {
                                nextToken(2);
                            } else {
                                this.sp = 0;
                                this.pos = this.bp;
                                scanNumber();
                            }
                        }
                        this.matchStat = 3;
                        return i2;
                    }
                    if (this.ch != ':') {
                        skipWhitespace();
                    }
                    if (this.ch == ':') {
                        int i7 = this.bp + 1;
                        this.bp = i7;
                        if (i7 >= this.text.length()) {
                            c3 = 26;
                        } else {
                            c3 = this.text.charAt(i7);
                        }
                        this.ch = c3;
                        if (!(this.ch == '\"' || this.ch == '\'' || this.ch == '{' || this.ch == '[' || this.ch == '0' || this.ch == '1' || this.ch == '2' || this.ch == '3' || this.ch == '4' || this.ch == '5' || this.ch == '6' || this.ch == '7' || this.ch == '8' || this.ch == '9' || this.ch == '+' || this.ch == '-')) {
                            skipWhitespace();
                        }
                        if (this.ch == '-' || this.ch == '+' || (this.ch >= '0' && this.ch <= '9')) {
                            next();
                            while (this.ch >= '0' && this.ch <= '9') {
                                next();
                            }
                            if (this.ch == '.') {
                                next();
                                while (this.ch >= '0' && this.ch <= '9') {
                                    next();
                                }
                            }
                            if (this.ch == 'E' || this.ch == 'e') {
                                next();
                                if (this.ch == '-' || this.ch == '+') {
                                    next();
                                }
                                while (this.ch >= '0' && this.ch <= '9') {
                                    next();
                                }
                            }
                            if (this.ch != ',') {
                                skipWhitespace();
                            }
                            if (this.ch == ',') {
                                next();
                            }
                        } else if (this.ch == '\"') {
                            skipString();
                            if (!(this.ch == ',' || this.ch == '}')) {
                                skipWhitespace();
                            }
                            if (this.ch == ',') {
                                next();
                            }
                        } else if (this.ch == '{') {
                            int i8 = this.bp + 1;
                            this.bp = i8;
                            if (i8 < this.text.length()) {
                                c6 = this.text.charAt(i8);
                            }
                            this.ch = c6;
                            skipObject(false);
                        } else if (this.ch == '[') {
                            next();
                            skipArray(false);
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    } else {
                        throw new JSONException("illegal json, " + info());
                    }
                } else {
                    throw new UnsupportedOperationException();
                }
            }
            next();
            nextToken();
            this.matchStat = -1;
            return -1;
        }
        throw new UnsupportedOperationException();
    }

    public String scanTypeName(SymbolTable symbolTable) {
        int indexOf;
        if (!this.text.startsWith("\"@type\":\"", this.bp) || (indexOf = this.text.indexOf(34, this.bp + 9)) == -1) {
            return null;
        }
        this.bp += 9;
        int i = 0;
        for (int i2 = this.bp; i2 < indexOf; i2++) {
            i = (i * 31) + this.text.charAt(i2);
        }
        String addSymbol = addSymbol(this.bp, indexOf - this.bp, i, symbolTable);
        char charAt = this.text.charAt(indexOf + 1);
        if (charAt != ',' && charAt != ']') {
            return null;
        }
        this.bp = indexOf + 2;
        this.ch = this.text.charAt(this.bp);
        return addSymbol;
    }
}
