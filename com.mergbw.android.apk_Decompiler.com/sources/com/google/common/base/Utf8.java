package com.google.common.base;

@ElementTypesAreNonnullByDefault
public final class Utf8 {
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < 128) {
            i++;
        }
        int i2 = length;
        while (true) {
            if (i < length) {
                char charAt = charSequence.charAt(i);
                if (charAt >= 2048) {
                    i2 += encodedLengthGeneral(charSequence, i);
                    break;
                }
                i2 += (127 - charAt) >>> 31;
                i++;
            } else {
                break;
            }
        }
        if (i2 >= length) {
            return i2;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i2) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if (55296 <= charAt && charAt <= 57343) {
                    if (Character.codePointAt(charSequence, i) != charAt) {
                        i++;
                    } else {
                        throw new IllegalArgumentException(unpairedSurrogateMsg(i));
                    }
                }
            }
            i++;
        }
        return i2;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    public static boolean isWellFormed(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        Preconditions.checkPositionIndexes(i, i3, bArr.length);
        while (i < i3) {
            if (bArr[i] < 0) {
                return isWellFormedSlowPath(bArr, i, i3);
            }
            i++;
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int i, int i2) {
        byte b2;
        while (i < i2) {
            int i3 = i + 1;
            byte b3 = bArr[i];
            if (b3 >= 0) {
                i = i3;
            } else if (b3 < -32) {
                if (i3 != i2 && b3 >= -62) {
                    i += 2;
                    if (bArr[i3] > -65) {
                    }
                }
                return false;
            } else if (b3 < -16) {
                int i4 = i + 2;
                if (i4 < i2 && (b2 = bArr[i3]) <= -65 && ((b3 != -32 || b2 >= -96) && (b3 != -19 || -96 > b2))) {
                    i += 3;
                    if (bArr[i4] > -65) {
                    }
                }
                return false;
            } else if (i + 3 >= i2) {
                return false;
            } else {
                int i5 = i + 2;
                byte b4 = bArr[i3];
                if (b4 <= -65 && (((b3 << Ascii.FS) + (b4 + 112)) >> 30) == 0) {
                    int i6 = i + 3;
                    if (bArr[i5] <= -65) {
                        i += 4;
                        if (bArr[i6] > -65) {
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    private static String unpairedSurrogateMsg(int i) {
        StringBuilder sb = new StringBuilder(39);
        sb.append("Unpaired surrogate at index ");
        sb.append(i);
        return sb.toString();
    }

    private Utf8() {
    }
}
