package com.alibaba.fastjson.asm;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import okhttp3.HttpUrl;

public class Type {
    public static final Type BOOLEAN_TYPE = new Type(1, (char[]) null, 1509950721, 1);
    public static final Type BYTE_TYPE = new Type(3, (char[]) null, 1107297537, 1);
    public static final Type CHAR_TYPE = new Type(2, (char[]) null, 1124075009, 1);
    public static final Type DOUBLE_TYPE = new Type(8, (char[]) null, 1141048066, 1);
    public static final Type FLOAT_TYPE = new Type(6, (char[]) null, 1174536705, 1);
    public static final Type INT_TYPE = new Type(5, (char[]) null, 1224736769, 1);
    public static final Type LONG_TYPE = new Type(7, (char[]) null, 1241579778, 1);
    public static final Type SHORT_TYPE = new Type(4, (char[]) null, 1392510721, 1);
    public static final Type VOID_TYPE = new Type(0, (char[]) null, 1443168256, 1);
    private final char[] buf;
    private final int len;
    private final int off;
    protected final int sort;

    private Type(int i, char[] cArr, int i2, int i3) {
        this.sort = i;
        this.buf = cArr;
        this.off = i2;
        this.len = i3;
    }

    public static Type getType(String str) {
        return getType(str.toCharArray(), 0);
    }

    public static int getArgumentsAndReturnSizes(String str) {
        int i;
        int i2 = 1;
        int i3 = 1;
        int i4 = 1;
        while (true) {
            i = i3 + 1;
            char charAt = str.charAt(i3);
            if (charAt == ')') {
                break;
            } else if (charAt == 'L') {
                while (true) {
                    i3 = i + 1;
                    if (str.charAt(i) == ';') {
                        break;
                    }
                    i = i3;
                }
                i4++;
            } else {
                i4 = (charAt == 'D' || charAt == 'J') ? i4 + 2 : i4 + 1;
                i3 = i;
            }
        }
        char charAt2 = str.charAt(i);
        int i5 = i4 << 2;
        if (charAt2 == 'V') {
            i2 = 0;
        } else if (charAt2 == 'D' || charAt2 == 'J') {
            i2 = 2;
        }
        return i5 | i2;
    }

    private static Type getType(char[] cArr, int i) {
        char c2;
        char c3 = cArr[i];
        if (c3 == 'F') {
            return FLOAT_TYPE;
        }
        if (c3 == 'S') {
            return SHORT_TYPE;
        }
        if (c3 == 'V') {
            return VOID_TYPE;
        }
        if (c3 == 'I') {
            return INT_TYPE;
        }
        if (c3 == 'J') {
            return LONG_TYPE;
        }
        if (c3 == 'Z') {
            return BOOLEAN_TYPE;
        }
        if (c3 != '[') {
            switch (c3) {
                case 'B':
                    return BYTE_TYPE;
                case ConstraintLayout.LayoutParams.Table.GUIDELINE_USE_RTL:
                    return CHAR_TYPE;
                case 'D':
                    return DOUBLE_TYPE;
                default:
                    int i2 = 1;
                    while (cArr[i + i2] != ';') {
                        i2++;
                    }
                    return new Type(10, cArr, i + 1, i2 - 1);
            }
        } else {
            int i3 = 1;
            while (true) {
                c2 = cArr[i + i3];
                if (c2 != '[') {
                    break;
                }
                i3++;
            }
            if (c2 == 'L') {
                do {
                    i3++;
                } while (cArr[i + i3] != ';');
            }
            return new Type(9, cArr, i, i3 + 1);
        }
    }

    public String getInternalName() {
        return new String(this.buf, this.off, this.len);
    }

    /* access modifiers changed from: package-private */
    public String getDescriptor() {
        return new String(this.buf, this.off, this.len);
    }

    private int getDimensions() {
        int i = 1;
        while (this.buf[this.off + i] == '[') {
            i++;
        }
        return i;
    }

    static Type[] getArgumentTypes(String str) {
        char[] charArray = str.toCharArray();
        int i = 1;
        int i2 = 1;
        int i3 = 0;
        while (true) {
            int i4 = i2 + 1;
            char c2 = charArray[i2];
            if (c2 == ')') {
                break;
            } else if (c2 == 'L') {
                while (true) {
                    i2 = i4 + 1;
                    if (charArray[i4] == ';') {
                        break;
                    }
                    i4 = i2;
                }
                i3++;
            } else {
                if (c2 != '[') {
                    i3++;
                }
                i2 = i4;
            }
        }
        Type[] typeArr = new Type[i3];
        int i5 = 0;
        while (charArray[i] != ')') {
            Type type = getType(charArray, i);
            typeArr[i5] = type;
            i += type.len + (type.sort == 10 ? 2 : 0);
            i5++;
        }
        return typeArr;
    }

    /* access modifiers changed from: protected */
    public String getClassName() {
        switch (this.sort) {
            case 0:
                return "void";
            case 1:
                return TypedValues.Custom.S_BOOLEAN;
            case 2:
                return "char";
            case 3:
                return "byte";
            case 4:
                return "short";
            case 5:
                return "int";
            case 6:
                return TypedValues.Custom.S_FLOAT;
            case 7:
                return "long";
            case 8:
                return "double";
            case 9:
                StringBuilder sb = new StringBuilder(getType(this.buf, this.off + getDimensions()).getClassName());
                for (int dimensions = getDimensions(); dimensions > 0; dimensions--) {
                    sb.append(HttpUrl.PATH_SEGMENT_ENCODE_SET_URI);
                }
                return sb.toString();
            default:
                return new String(this.buf, this.off, this.len).replace('/', '.');
        }
    }
}