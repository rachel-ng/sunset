package com.alibaba.fastjson;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public abstract class JSONValidator implements Cloneable {
    protected char ch;
    protected int count = 0;
    protected boolean eof;
    protected int pos = -1;
    protected boolean supportMultiValue = true;
    protected Type type;

    public enum Type {
        Object,
        Array,
        Value
    }

    static final boolean isWhiteSpace(char c2) {
        return c2 == ' ' || c2 == 9 || c2 == 13 || c2 == 10 || c2 == 12 || c2 == 8;
    }

    public void close() throws IOException {
    }

    /* access modifiers changed from: package-private */
    public abstract void next();

    public static JSONValidator fromUtf8(byte[] bArr) {
        return new UTF8Validator(bArr);
    }

    public static JSONValidator fromUtf8(InputStream inputStream) {
        return new UTF8InputStreamValidator(inputStream);
    }

    public static JSONValidator from(String str) {
        return new UTF16Validator(str);
    }

    public static JSONValidator from(Reader reader) {
        return new ReaderValidator(reader);
    }

    public Type getType() {
        return this.type;
    }

    public boolean validate() {
        while (any()) {
            this.count++;
            if (!this.supportMultiValue || this.eof) {
                return true;
            }
            skipWhiteSpace();
            if (this.eof) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:114:0x016a, code lost:
        if (r0 <= '9') goto L_0x016d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x017a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean any() {
        /*
            r13 = this;
            char r0 = r13.ch
            r1 = 117(0x75, float:1.64E-43)
            r2 = 34
            r3 = 1
            if (r0 == r2) goto L_0x01bc
            r4 = 101(0x65, float:1.42E-43)
            r5 = 45
            r6 = 43
            r7 = 0
            if (r0 == r6) goto L_0x0158
            if (r0 == r5) goto L_0x0158
            r8 = 91
            r9 = 44
            r10 = 93
            if (r0 == r8) goto L_0x0126
            r8 = 102(0x66, float:1.43E-43)
            r11 = 108(0x6c, float:1.51E-43)
            r12 = 125(0x7d, float:1.75E-43)
            if (r0 == r8) goto L_0x00e6
            r8 = 110(0x6e, float:1.54E-43)
            if (r0 == r8) goto L_0x00b2
            r8 = 116(0x74, float:1.63E-43)
            if (r0 == r8) goto L_0x007c
            r1 = 123(0x7b, float:1.72E-43)
            if (r0 == r1) goto L_0x0034
            switch(r0) {
                case 48: goto L_0x0158;
                case 49: goto L_0x0158;
                case 50: goto L_0x0158;
                case 51: goto L_0x0158;
                case 52: goto L_0x0158;
                case 53: goto L_0x0158;
                case 54: goto L_0x0158;
                case 55: goto L_0x0158;
                case 56: goto L_0x0158;
                case 57: goto L_0x0158;
                default: goto L_0x0033;
            }
        L_0x0033:
            return r7
        L_0x0034:
            r13.next()
            r13.skipWhiteSpace()
            char r0 = r13.ch
            if (r0 != r12) goto L_0x0046
            r13.next()
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Object
            r13.type = r0
            return r3
        L_0x0046:
            char r0 = r13.ch
            if (r0 != r2) goto L_0x007b
            r13.fieldName()
            r13.skipWhiteSpace()
            char r0 = r13.ch
            r1 = 58
            if (r0 != r1) goto L_0x007b
            r13.next()
            r13.skipWhiteSpace()
            boolean r0 = r13.any()
            if (r0 != 0) goto L_0x0063
            return r7
        L_0x0063:
            r13.skipWhiteSpace()
            char r0 = r13.ch
            if (r0 != r9) goto L_0x0071
            r13.next()
            r13.skipWhiteSpace()
            goto L_0x0046
        L_0x0071:
            if (r0 != r12) goto L_0x0046
            r13.next()
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Object
            r13.type = r0
            return r3
        L_0x007b:
            return r7
        L_0x007c:
            r13.next()
            char r0 = r13.ch
            r2 = 114(0x72, float:1.6E-43)
            if (r0 == r2) goto L_0x0086
            return r7
        L_0x0086:
            r13.next()
            char r0 = r13.ch
            if (r0 == r1) goto L_0x008e
            return r7
        L_0x008e:
            r13.next()
            char r0 = r13.ch
            if (r0 == r4) goto L_0x0096
            return r7
        L_0x0096:
            r13.next()
            char r0 = r13.ch
            boolean r0 = isWhiteSpace(r0)
            if (r0 != 0) goto L_0x00ad
            char r0 = r13.ch
            if (r0 == r9) goto L_0x00ad
            if (r0 == r10) goto L_0x00ad
            if (r0 == r12) goto L_0x00ad
            if (r0 != 0) goto L_0x00ac
            goto L_0x00ad
        L_0x00ac:
            return r7
        L_0x00ad:
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Value
            r13.type = r0
            return r3
        L_0x00b2:
            r13.next()
            char r0 = r13.ch
            if (r0 == r1) goto L_0x00ba
            return r7
        L_0x00ba:
            r13.next()
            char r0 = r13.ch
            if (r0 == r11) goto L_0x00c2
            return r7
        L_0x00c2:
            r13.next()
            char r0 = r13.ch
            if (r0 == r11) goto L_0x00ca
            return r7
        L_0x00ca:
            r13.next()
            char r0 = r13.ch
            boolean r0 = isWhiteSpace(r0)
            if (r0 != 0) goto L_0x00e1
            char r0 = r13.ch
            if (r0 == r9) goto L_0x00e1
            if (r0 == r10) goto L_0x00e1
            if (r0 == r12) goto L_0x00e1
            if (r0 != 0) goto L_0x00e0
            goto L_0x00e1
        L_0x00e0:
            return r7
        L_0x00e1:
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Value
            r13.type = r0
            return r3
        L_0x00e6:
            r13.next()
            char r0 = r13.ch
            r1 = 97
            if (r0 == r1) goto L_0x00f0
            return r7
        L_0x00f0:
            r13.next()
            char r0 = r13.ch
            if (r0 == r11) goto L_0x00f8
            return r7
        L_0x00f8:
            r13.next()
            char r0 = r13.ch
            r1 = 115(0x73, float:1.61E-43)
            if (r0 == r1) goto L_0x0102
            return r7
        L_0x0102:
            r13.next()
            char r0 = r13.ch
            if (r0 == r4) goto L_0x010a
            return r7
        L_0x010a:
            r13.next()
            char r0 = r13.ch
            boolean r0 = isWhiteSpace(r0)
            if (r0 != 0) goto L_0x0121
            char r0 = r13.ch
            if (r0 == r9) goto L_0x0121
            if (r0 == r10) goto L_0x0121
            if (r0 == r12) goto L_0x0121
            if (r0 != 0) goto L_0x0120
            goto L_0x0121
        L_0x0120:
            return r7
        L_0x0121:
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Value
            r13.type = r0
            return r3
        L_0x0126:
            r13.next()
            r13.skipWhiteSpace()
            char r0 = r13.ch
            if (r0 != r10) goto L_0x0138
            r13.next()
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Array
            r13.type = r0
            return r3
        L_0x0138:
            boolean r0 = r13.any()
            if (r0 != 0) goto L_0x013f
            return r7
        L_0x013f:
            r13.skipWhiteSpace()
            char r0 = r13.ch
            if (r0 != r9) goto L_0x014d
            r13.next()
            r13.skipWhiteSpace()
            goto L_0x0138
        L_0x014d:
            if (r0 != r10) goto L_0x0157
            r13.next()
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Array
            r13.type = r0
            return r3
        L_0x0157:
            return r7
        L_0x0158:
            r1 = 57
            r2 = 48
            if (r0 == r5) goto L_0x0160
            if (r0 != r6) goto L_0x016d
        L_0x0160:
            r13.next()
            r13.skipWhiteSpace()
            char r0 = r13.ch
            if (r0 < r2) goto L_0x01bb
            if (r0 <= r1) goto L_0x016d
            goto L_0x01bb
        L_0x016d:
            r13.next()
            char r0 = r13.ch
            if (r0 < r2) goto L_0x0176
            if (r0 <= r1) goto L_0x016d
        L_0x0176:
            r8 = 46
            if (r0 != r8) goto L_0x018f
            r13.next()
            char r0 = r13.ch
            if (r0 < r2) goto L_0x018e
            if (r0 <= r1) goto L_0x0184
            goto L_0x018e
        L_0x0184:
            char r0 = r13.ch
            if (r0 < r2) goto L_0x018f
            if (r0 > r1) goto L_0x018f
            r13.next()
            goto L_0x0184
        L_0x018e:
            return r7
        L_0x018f:
            char r0 = r13.ch
            if (r0 == r4) goto L_0x0197
            r4 = 69
            if (r0 != r4) goto L_0x01b6
        L_0x0197:
            r13.next()
            char r0 = r13.ch
            if (r0 == r5) goto L_0x01a0
            if (r0 != r6) goto L_0x01a3
        L_0x01a0:
            r13.next()
        L_0x01a3:
            char r0 = r13.ch
            if (r0 < r2) goto L_0x01bb
            if (r0 > r1) goto L_0x01bb
            r13.next()
        L_0x01ac:
            char r0 = r13.ch
            if (r0 < r2) goto L_0x01b6
            if (r0 > r1) goto L_0x01b6
            r13.next()
            goto L_0x01ac
        L_0x01b6:
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Value
            r13.type = r0
            return r3
        L_0x01bb:
            return r7
        L_0x01bc:
            r13.next()
        L_0x01bf:
            char r0 = r13.ch
            r4 = 92
            if (r0 != r4) goto L_0x01e0
            r13.next()
            char r0 = r13.ch
            if (r0 != r1) goto L_0x01dc
            r13.next()
            r13.next()
            r13.next()
            r13.next()
            r13.next()
            goto L_0x01bf
        L_0x01dc:
            r13.next()
            goto L_0x01bf
        L_0x01e0:
            if (r0 != r2) goto L_0x01ea
            r13.next()
            com.alibaba.fastjson.JSONValidator$Type r0 = com.alibaba.fastjson.JSONValidator.Type.Value
            r13.type = r0
            return r3
        L_0x01ea:
            r13.next()
            goto L_0x01bf
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.fastjson.JSONValidator.any():boolean");
    }

    /* access modifiers changed from: protected */
    public void fieldName() {
        next();
        while (true) {
            char c2 = this.ch;
            if (c2 == '\\') {
                next();
                if (this.ch == 'u') {
                    next();
                    next();
                    next();
                    next();
                    next();
                } else {
                    next();
                }
            } else if (c2 == '\"') {
                next();
                return;
            } else {
                next();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void skipWhiteSpace() {
        while (isWhiteSpace(this.ch)) {
            next();
        }
    }

    static class UTF8Validator extends JSONValidator {
        private final byte[] bytes;

        public UTF8Validator(byte[] bArr) {
            this.bytes = bArr;
            next();
            skipWhiteSpace();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            this.pos++;
            int i = this.pos;
            byte[] bArr = this.bytes;
            if (i >= bArr.length) {
                this.ch = 0;
                this.eof = true;
                return;
            }
            this.ch = (char) bArr[this.pos];
        }
    }

    static class UTF8InputStreamValidator extends JSONValidator {
        private static final ThreadLocal<byte[]> bufLocal = new ThreadLocal<>();
        private byte[] buf;
        private int end = -1;
        private final InputStream is;
        private int readCount = 0;

        public UTF8InputStreamValidator(InputStream inputStream) {
            this.is = inputStream;
            ThreadLocal<byte[]> threadLocal = bufLocal;
            byte[] bArr = threadLocal.get();
            this.buf = bArr;
            if (bArr != null) {
                threadLocal.set((Object) null);
            } else {
                this.buf = new byte[8192];
            }
            next();
            skipWhiteSpace();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            if (this.pos < this.end) {
                byte[] bArr = this.buf;
                int i = this.pos + 1;
                this.pos = i;
                this.ch = (char) bArr[i];
            } else if (!this.eof) {
                try {
                    InputStream inputStream = this.is;
                    byte[] bArr2 = this.buf;
                    int read = inputStream.read(bArr2, 0, bArr2.length);
                    this.readCount++;
                    if (read > 0) {
                        this.ch = (char) this.buf[0];
                        this.pos = 0;
                        this.end = read - 1;
                    } else if (read == -1) {
                        this.pos = 0;
                        this.end = 0;
                        this.buf = null;
                        this.ch = 0;
                        this.eof = true;
                    } else {
                        this.pos = 0;
                        this.end = 0;
                        this.buf = null;
                        this.ch = 0;
                        this.eof = true;
                        throw new JSONException("read error");
                    }
                } catch (IOException unused) {
                    throw new JSONException("read error");
                }
            }
        }

        public void close() throws IOException {
            bufLocal.set(this.buf);
            this.is.close();
        }
    }

    static class UTF16Validator extends JSONValidator {
        private final String str;

        public UTF16Validator(String str2) {
            this.str = str2;
            next();
            skipWhiteSpace();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            this.pos++;
            if (this.pos >= this.str.length()) {
                this.ch = 0;
                this.eof = true;
                return;
            }
            this.ch = this.str.charAt(this.pos);
        }
    }

    static class ReaderValidator extends JSONValidator {
        private static final ThreadLocal<char[]> bufLocal = new ThreadLocal<>();
        private char[] buf;
        private int end = -1;
        final Reader r;
        private int readCount = 0;

        ReaderValidator(Reader reader) {
            this.r = reader;
            ThreadLocal<char[]> threadLocal = bufLocal;
            char[] cArr = threadLocal.get();
            this.buf = cArr;
            if (cArr != null) {
                threadLocal.set((Object) null);
            } else {
                this.buf = new char[8192];
            }
            next();
            skipWhiteSpace();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            if (this.pos < this.end) {
                char[] cArr = this.buf;
                int i = this.pos + 1;
                this.pos = i;
                this.ch = cArr[i];
            } else if (!this.eof) {
                try {
                    Reader reader = this.r;
                    char[] cArr2 = this.buf;
                    int read = reader.read(cArr2, 0, cArr2.length);
                    this.readCount++;
                    if (read > 0) {
                        this.ch = this.buf[0];
                        this.pos = 0;
                        this.end = read - 1;
                    } else if (read == -1) {
                        this.pos = 0;
                        this.end = 0;
                        this.buf = null;
                        this.ch = 0;
                        this.eof = true;
                    } else {
                        this.pos = 0;
                        this.end = 0;
                        this.buf = null;
                        this.ch = 0;
                        this.eof = true;
                        throw new JSONException("read error");
                    }
                } catch (IOException unused) {
                    throw new JSONException("read error");
                }
            }
        }

        public void close() throws IOException {
            bufLocal.set(this.buf);
            this.r.close();
        }
    }
}
