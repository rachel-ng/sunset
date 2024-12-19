package okio.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okio.Base64;
import okio.Buffer;
import okio.ByteString;
import okio.Platform;
import okio.SegmentedByteString;
import okio.Util;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007H\b\u001a\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\nH\b\u001a\r\u0010\u0011\u001a\u00020\u0010*\u00020\nH\b\u001a\u0015\u0010\u0012\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\b\u001a\u000f\u0010\u0014\u001a\u0004\u0018\u00010\n*\u00020\u0010H\b\u001a\r\u0010\u0015\u001a\u00020\n*\u00020\u0010H\b\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0010H\u0000\u001a\r\u0010\u0018\u001a\u00020\n*\u00020\u0010H\b\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0007H\b\u001a\u0015\u0010\u0019\u001a\u00020\u001a*\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\b\u001a\u0017\u0010\u001c\u001a\u00020\u001a*\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u001dH\b\u001a\u0015\u0010\u001e\u001a\u00020\u001f*\u00020\n2\u0006\u0010 \u001a\u00020\u0005H\b\u001a\r\u0010!\u001a\u00020\u0005*\u00020\nH\b\u001a\r\u0010\"\u001a\u00020\u0005*\u00020\nH\b\u001a\r\u0010#\u001a\u00020\u0010*\u00020\nH\b\u001a\u001d\u0010$\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0005H\b\u001a\r\u0010&\u001a\u00020\u0007*\u00020\nH\b\u001a\u001d\u0010'\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0005H\b\u001a\u001d\u0010'\u001a\u00020\u0005*\u00020\n2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010%\u001a\u00020\u0005H\b\u001a-\u0010(\u001a\u00020\u001a*\u00020\n2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\b\u001a-\u0010(\u001a\u00020\u001a*\u00020\n2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\b\u001a\u0014\u0010,\u001a\u00020\n*\u00020-2\u0006\u0010\u0017\u001a\u00020\u0010H\u0000\u001a\u0015\u0010.\u001a\u00020\u001a*\u00020\n2\u0006\u0010/\u001a\u00020\u0007H\b\u001a\u0015\u0010.\u001a\u00020\u001a*\u00020\n2\u0006\u0010/\u001a\u00020\nH\b\u001a\u001d\u00100\u001a\u00020\n*\u00020\n2\u0006\u00101\u001a\u00020\u00052\u0006\u00102\u001a\u00020\u0005H\b\u001a\r\u00103\u001a\u00020\n*\u00020\nH\b\u001a\r\u00104\u001a\u00020\n*\u00020\nH\b\u001a\r\u00105\u001a\u00020\u0007*\u00020\nH\b\u001a\u001d\u00106\u001a\u00020\n*\u00020\u00072\u0006\u0010)\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\b\u001a\r\u00107\u001a\u00020\u0010*\u00020\nH\b\u001a\r\u00108\u001a\u00020\u0010*\u00020\nH\b\u001a$\u00109\u001a\u00020:*\u00020\n2\u0006\u0010;\u001a\u00020<2\u0006\u0010)\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\u0005H\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006="}, d2 = {"HEX_DIGIT_CHARS", "", "getHEX_DIGIT_CHARS", "()[C", "codePointIndexToCharIndex", "", "s", "", "codePointCount", "commonOf", "Lokio/ByteString;", "data", "decodeHexDigit", "c", "", "commonBase64", "", "commonBase64Url", "commonCompareTo", "other", "commonDecodeBase64", "commonDecodeHex", "commonDigest", "algorithm", "commonEncodeUtf8", "commonEndsWith", "", "suffix", "commonEquals", "", "commonGetByte", "", "pos", "commonGetSize", "commonHashCode", "commonHex", "commonIndexOf", "fromIndex", "commonInternalArray", "commonLastIndexOf", "commonRangeEquals", "offset", "otherOffset", "byteCount", "commonSegmentDigest", "Lokio/SegmentedByteString;", "commonStartsWith", "prefix", "commonSubstring", "beginIndex", "endIndex", "commonToAsciiLowercase", "commonToAsciiUppercase", "commonToByteArray", "commonToByteString", "commonToString", "commonUtf8", "commonWrite", "", "buffer", "Lokio/Buffer;", "okio"}, k = 2, mv = {1, 4, 0})
/* compiled from: ByteString.kt */
public final class ByteStringKt {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final String commonUtf8(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonUtf8");
        String utf8$okio = byteString.getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        String utf8String = Platform.toUtf8String(byteString.internalArray$okio());
        byteString.setUtf8$okio(utf8String);
        return utf8String;
    }

    public static final String commonBase64(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonBase64");
        return Base64.encodeBase64$default(byteString.getData$okio(), (byte[]) null, 1, (Object) null);
    }

    public static final String commonBase64Url(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonBase64Url");
        return Base64.encodeBase64(byteString.getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static final String commonHex(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonHex");
        char[] cArr = new char[(byteString.getData$okio().length * 2)];
        int i = 0;
        for (byte b2 : byteString.getData$okio()) {
            int i2 = i + 1;
            cArr[i] = getHEX_DIGIT_CHARS()[(b2 >> 4) & 15];
            i += 2;
            cArr[i2] = getHEX_DIGIT_CHARS()[b2 & 15];
        }
        return new String(cArr);
    }

    public static final ByteString commonDigest(ByteString byteString, String str) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonDigest");
        Intrinsics.checkNotNullParameter(str, "algorithm");
        HashFunction newHashFunction = HashFunctionKt.newHashFunction(str);
        newHashFunction.update(byteString.getData$okio(), 0, byteString.size());
        return new ByteString(newHashFunction.digest());
    }

    public static final ByteString commonSegmentDigest(SegmentedByteString segmentedByteString, String str) {
        Intrinsics.checkNotNullParameter(segmentedByteString, "$this$commonSegmentDigest");
        Intrinsics.checkNotNullParameter(str, "algorithm");
        HashFunction newHashFunction = HashFunctionKt.newHashFunction(str);
        int length = ((Object[]) segmentedByteString.getSegments$okio()).length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = segmentedByteString.getDirectory$okio()[length + i];
            int i4 = segmentedByteString.getDirectory$okio()[i];
            newHashFunction.update(segmentedByteString.getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        return new ByteString(newHashFunction.digest());
    }

    public static final ByteString commonToAsciiLowercase(ByteString byteString) {
        byte b2;
        Intrinsics.checkNotNullParameter(byteString, "$this$commonToAsciiLowercase");
        int i = 0;
        while (i < byteString.getData$okio().length) {
            byte b3 = byteString.getData$okio()[i];
            byte b4 = (byte) 65;
            if (b3 < b4 || b3 > (b2 = (byte) 90)) {
                i++;
            } else {
                byte[] data$okio = byteString.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b3 + 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b5 = copyOf[i2];
                    if (b5 >= b4 && b5 <= b2) {
                        copyOf[i2] = (byte) (b5 + 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final ByteString commonToAsciiUppercase(ByteString byteString) {
        byte b2;
        Intrinsics.checkNotNullParameter(byteString, "$this$commonToAsciiUppercase");
        int i = 0;
        while (i < byteString.getData$okio().length) {
            byte b3 = byteString.getData$okio()[i];
            byte b4 = (byte) 97;
            if (b3 < b4 || b3 > (b2 = (byte) 122)) {
                i++;
            } else {
                byte[] data$okio = byteString.getData$okio();
                byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
                copyOf[i] = (byte) (b3 - 32);
                for (int i2 = i + 1; i2 < copyOf.length; i2++) {
                    byte b5 = copyOf[i2];
                    if (b5 >= b4 && b5 <= b2) {
                        copyOf[i2] = (byte) (b5 - 32);
                    }
                }
                return new ByteString(copyOf);
            }
        }
        return byteString;
    }

    public static final ByteString commonSubstring(ByteString byteString, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonSubstring");
        boolean z = true;
        if (i >= 0) {
            if (i2 <= byteString.getData$okio().length) {
                if (i2 - i < 0) {
                    z = false;
                }
                if (!z) {
                    throw new IllegalArgumentException("endIndex < beginIndex".toString());
                } else if (i == 0 && i2 == byteString.getData$okio().length) {
                    return byteString;
                } else {
                    return new ByteString(ArraysKt.copyOfRange(byteString.getData$okio(), i, i2));
                }
            } else {
                throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0".toString());
        }
    }

    public static final byte commonGetByte(ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonGetByte");
        return byteString.getData$okio()[i];
    }

    public static final int commonGetSize(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonGetSize");
        return byteString.getData$okio().length;
    }

    public static final byte[] commonToByteArray(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonToByteArray");
        byte[] data$okio = byteString.getData$okio();
        byte[] copyOf = Arrays.copyOf(data$okio, data$okio.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return copyOf;
    }

    public static final byte[] commonInternalArray(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonInternalArray");
        return byteString.getData$okio();
    }

    public static final boolean commonRangeEquals(ByteString byteString, int i, ByteString byteString2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(byteString2, "other");
        return byteString2.rangeEquals(i2, byteString.getData$okio(), i, i3);
    }

    public static final boolean commonRangeEquals(ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonRangeEquals");
        Intrinsics.checkNotNullParameter(bArr, "other");
        return i >= 0 && i <= byteString.getData$okio().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && Util.arrayRangeEquals(byteString.getData$okio(), i, bArr, i2, i3);
    }

    public static final boolean commonStartsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonStartsWith");
        Intrinsics.checkNotNullParameter(byteString2, "prefix");
        return byteString.rangeEquals(0, byteString2, 0, byteString2.size());
    }

    public static final boolean commonStartsWith(ByteString byteString, byte[] bArr) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonStartsWith");
        Intrinsics.checkNotNullParameter(bArr, "prefix");
        return byteString.rangeEquals(0, bArr, 0, bArr.length);
    }

    public static final boolean commonEndsWith(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonEndsWith");
        Intrinsics.checkNotNullParameter(byteString2, "suffix");
        return byteString.rangeEquals(byteString.size() - byteString2.size(), byteString2, 0, byteString2.size());
    }

    public static final boolean commonEndsWith(ByteString byteString, byte[] bArr) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonEndsWith");
        Intrinsics.checkNotNullParameter(bArr, "suffix");
        return byteString.rangeEquals(byteString.size() - bArr.length, bArr, 0, bArr.length);
    }

    public static final int commonIndexOf(ByteString byteString, byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonIndexOf");
        Intrinsics.checkNotNullParameter(bArr, "other");
        int length = byteString.getData$okio().length - bArr.length;
        int max = Math.max(i, 0);
        if (max > length) {
            return -1;
        }
        while (!Util.arrayRangeEquals(byteString.getData$okio(), max, bArr, 0, bArr.length)) {
            if (max == length) {
                return -1;
            }
            max++;
        }
        return max;
    }

    public static final int commonLastIndexOf(ByteString byteString, ByteString byteString2, int i) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonLastIndexOf");
        Intrinsics.checkNotNullParameter(byteString2, "other");
        return byteString.lastIndexOf(byteString2.internalArray$okio(), i);
    }

    public static final int commonLastIndexOf(ByteString byteString, byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonLastIndexOf");
        Intrinsics.checkNotNullParameter(bArr, "other");
        for (int min = Math.min(i, byteString.getData$okio().length - bArr.length); min >= 0; min--) {
            if (Util.arrayRangeEquals(byteString.getData$okio(), min, bArr, 0, bArr.length)) {
                return min;
            }
        }
        return -1;
    }

    public static final boolean commonEquals(ByteString byteString, Object obj) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonEquals");
        if (obj == byteString) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString2 = (ByteString) obj;
            if (byteString2.size() == byteString.getData$okio().length && byteString2.rangeEquals(0, byteString.getData$okio(), 0, byteString.getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public static final int commonHashCode(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonHashCode");
        int hashCode$okio = byteString.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int hashCode = Arrays.hashCode(byteString.getData$okio());
        byteString.setHashCode$okio(hashCode);
        return hashCode;
    }

    public static final int commonCompareTo(ByteString byteString, ByteString byteString2) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonCompareTo");
        Intrinsics.checkNotNullParameter(byteString2, "other");
        int size = byteString.size();
        int size2 = byteString2.size();
        int min = Math.min(size, size2);
        int i = 0;
        while (i < min) {
            byte b2 = byteString.getByte(i) & 255;
            byte b3 = byteString2.getByte(i) & 255;
            if (b2 == b3) {
                i++;
            } else if (b2 < b3) {
                return -1;
            } else {
                return 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        if (size < size2) {
            return -1;
        }
        return 1;
    }

    public static final ByteString commonOf(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, size)");
        return new ByteString(copyOf);
    }

    public static final ByteString commonToByteString(byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "$this$commonToByteString");
        Util.checkOffsetAndCount((long) bArr.length, (long) i, (long) i2);
        return new ByteString(ArraysKt.copyOfRange(bArr, i, i2 + i));
    }

    public static final ByteString commonEncodeUtf8(String str) {
        Intrinsics.checkNotNullParameter(str, "$this$commonEncodeUtf8");
        ByteString byteString = new ByteString(Platform.asUtf8ToByteArray(str));
        byteString.setUtf8$okio(str);
        return byteString;
    }

    public static final ByteString commonDecodeBase64(String str) {
        Intrinsics.checkNotNullParameter(str, "$this$commonDecodeBase64");
        byte[] decodeBase64ToArray = Base64.decodeBase64ToArray(str);
        if (decodeBase64ToArray != null) {
            return new ByteString(decodeBase64ToArray);
        }
        return null;
    }

    public static final ByteString commonDecodeHex(String str) {
        Intrinsics.checkNotNullParameter(str, "$this$commonDecodeHex");
        if (str.length() % 2 == 0) {
            int length = str.length() / 2;
            byte[] bArr = new byte[length];
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                bArr[i] = (byte) ((decodeHexDigit(str.charAt(i2)) << 4) + decodeHexDigit(str.charAt(i2 + 1)));
            }
            return new ByteString(bArr);
        }
        throw new IllegalArgumentException(("Unexpected hex string: " + str).toString());
    }

    public static final void commonWrite(ByteString byteString, Buffer buffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteString, "$this$commonWrite");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.write(byteString.getData$okio(), i, i2);
    }

    /* access modifiers changed from: private */
    public static final int decodeHexDigit(char c2) {
        if ('0' <= c2 && '9' >= c2) {
            return c2 - '0';
        }
        if ('a' <= c2 && 'f' >= c2) {
            return c2 - 'W';
        }
        if ('A' <= c2 && 'F' >= c2) {
            return c2 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c2);
    }

    public static final String commonToString(ByteString byteString) {
        ByteString byteString2 = byteString;
        Intrinsics.checkNotNullParameter(byteString2, "$this$commonToString");
        if (byteString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int access$codePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$okio(), 64);
        if (access$codePointIndexToCharIndex != -1) {
            String utf8 = byteString.utf8();
            if (utf8 != null) {
                String substring = utf8.substring(0, access$codePointIndexToCharIndex);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                String replace$default = StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(substring, "\\", "\\\\", false, 4, (Object) null), "\n", "\\n", false, 4, (Object) null), "\r", "\\r", false, 4, (Object) null);
                if (access$codePointIndexToCharIndex < utf8.length()) {
                    return "[size=" + byteString.getData$okio().length + " text=" + replace$default + "…]";
                }
                return "[text=" + replace$default + ']';
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } else if (byteString.getData$okio().length <= 64) {
            return "[hex=" + byteString.hex() + ']';
        } else {
            StringBuilder sb = new StringBuilder("[size=");
            sb.append(byteString.getData$okio().length);
            sb.append(" hex=");
            if (64 <= byteString.getData$okio().length) {
                if (64 != byteString.getData$okio().length) {
                    byteString2 = new ByteString(ArraysKt.copyOfRange(byteString.getData$okio(), 0, 64));
                }
                sb.append(byteString2.hex());
                sb.append("…]");
                return sb.toString();
            }
            throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x005c, code lost:
        return -1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int codePointIndexToCharIndex(byte[] r18, int r19) {
        /*
            r0 = r18
            r1 = r19
            int r2 = r0.length
            r3 = 0
            r4 = r3
            r5 = r4
        L_0x0008:
            if (r3 >= r2) goto L_0x01a1
            byte r6 = r0[r3]
            r7 = 127(0x7f, float:1.78E-43)
            r8 = 159(0x9f, float:2.23E-43)
            r9 = 31
            r10 = 13
            r11 = 65533(0xfffd, float:9.1831E-41)
            r12 = 10
            r13 = 65536(0x10000, float:9.18355E-41)
            r16 = -1
            if (r6 < 0) goto L_0x0064
            int r17 = r5 + 1
            if (r5 != r1) goto L_0x0024
            return r4
        L_0x0024:
            if (r6 == r12) goto L_0x0032
            if (r6 == r10) goto L_0x0032
            if (r6 < 0) goto L_0x002c
            if (r9 >= r6) goto L_0x0034
        L_0x002c:
            if (r7 <= r6) goto L_0x002f
            goto L_0x0032
        L_0x002f:
            if (r8 < r6) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            if (r6 != r11) goto L_0x0035
        L_0x0034:
            return r16
        L_0x0035:
            if (r6 >= r13) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 2
        L_0x003a:
            int r4 = r4 + r5
            int r3 = r3 + 1
        L_0x003d:
            r5 = r17
            if (r3 >= r2) goto L_0x0008
            byte r6 = r0[r3]
            if (r6 < 0) goto L_0x0008
            int r3 = r3 + 1
            int r17 = r5 + 1
            if (r5 != r1) goto L_0x004c
            return r4
        L_0x004c:
            if (r6 == r12) goto L_0x005a
            if (r6 == r10) goto L_0x005a
            if (r6 < 0) goto L_0x0054
            if (r9 >= r6) goto L_0x005c
        L_0x0054:
            if (r7 <= r6) goto L_0x0057
            goto L_0x005a
        L_0x0057:
            if (r8 < r6) goto L_0x005a
            goto L_0x005c
        L_0x005a:
            if (r6 != r11) goto L_0x005d
        L_0x005c:
            return r16
        L_0x005d:
            if (r6 >= r13) goto L_0x0061
            r5 = 1
            goto L_0x0062
        L_0x0061:
            r5 = 2
        L_0x0062:
            int r4 = r4 + r5
            goto L_0x003d
        L_0x0064:
            int r14 = r6 >> 5
            r15 = -2
            r13 = 128(0x80, float:1.794E-43)
            if (r14 != r15) goto L_0x00ad
            int r14 = r3 + 1
            if (r2 > r14) goto L_0x0073
            if (r5 != r1) goto L_0x0072
            return r4
        L_0x0072:
            return r16
        L_0x0073:
            byte r14 = r0[r14]
            r15 = r14 & 192(0xc0, float:2.69E-43)
            if (r15 != r13) goto L_0x00a9
            r14 = r14 ^ 3968(0xf80, float:5.56E-42)
            int r6 = r6 << 6
            r6 = r6 ^ r14
            if (r6 >= r13) goto L_0x0084
            if (r5 != r1) goto L_0x0083
            return r4
        L_0x0083:
            return r16
        L_0x0084:
            int r13 = r5 + 1
            if (r5 != r1) goto L_0x0089
            return r4
        L_0x0089:
            if (r6 == r12) goto L_0x0097
            if (r6 == r10) goto L_0x0097
            if (r6 < 0) goto L_0x0091
            if (r9 >= r6) goto L_0x0099
        L_0x0091:
            if (r7 <= r6) goto L_0x0094
            goto L_0x0097
        L_0x0094:
            if (r8 < r6) goto L_0x0097
            goto L_0x0099
        L_0x0097:
            if (r6 != r11) goto L_0x009a
        L_0x0099:
            return r16
        L_0x009a:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x00a0
            r14 = 1
            goto L_0x00a1
        L_0x00a0:
            r14 = 2
        L_0x00a1:
            int r4 = r4 + r14
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            int r3 = r3 + 2
            r5 = r13
            goto L_0x0008
        L_0x00a9:
            if (r5 != r1) goto L_0x00ac
            return r4
        L_0x00ac:
            return r16
        L_0x00ad:
            int r14 = r6 >> 4
            r11 = 55296(0xd800, float:7.7486E-41)
            r8 = 57343(0xdfff, float:8.0355E-41)
            if (r14 != r15) goto L_0x011c
            int r14 = r3 + 2
            if (r2 > r14) goto L_0x00bf
            if (r5 != r1) goto L_0x00be
            return r4
        L_0x00be:
            return r16
        L_0x00bf:
            int r15 = r3 + 1
            byte r15 = r0[r15]
            r7 = r15 & 192(0xc0, float:2.69E-43)
            if (r7 != r13) goto L_0x0118
            byte r7 = r0[r14]
            r14 = r7 & 192(0xc0, float:2.69E-43)
            if (r14 != r13) goto L_0x0114
            r13 = -123008(0xfffffffffffe1f80, float:NaN)
            r7 = r7 ^ r13
            int r13 = r15 << 6
            r7 = r7 ^ r13
            int r6 = r6 << 12
            r6 = r6 ^ r7
            r7 = 2048(0x800, float:2.87E-42)
            if (r6 >= r7) goto L_0x00df
            if (r5 != r1) goto L_0x00de
            return r4
        L_0x00de:
            return r16
        L_0x00df:
            if (r11 <= r6) goto L_0x00e2
            goto L_0x00e8
        L_0x00e2:
            if (r8 < r6) goto L_0x00e8
            if (r5 != r1) goto L_0x00e7
            return r4
        L_0x00e7:
            return r16
        L_0x00e8:
            int r7 = r5 + 1
            if (r5 != r1) goto L_0x00ed
            return r4
        L_0x00ed:
            if (r6 == r12) goto L_0x00ff
            if (r6 == r10) goto L_0x00ff
            if (r6 < 0) goto L_0x00f5
            if (r9 >= r6) goto L_0x0104
        L_0x00f5:
            r5 = 127(0x7f, float:1.78E-43)
            if (r5 <= r6) goto L_0x00fa
            goto L_0x00ff
        L_0x00fa:
            r5 = 159(0x9f, float:2.23E-43)
            if (r5 < r6) goto L_0x00ff
            goto L_0x0104
        L_0x00ff:
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r6 != r5) goto L_0x0105
        L_0x0104:
            return r16
        L_0x0105:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x010b
            r14 = 1
            goto L_0x010c
        L_0x010b:
            r14 = 2
        L_0x010c:
            int r4 = r4 + r14
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            int r3 = r3 + 3
        L_0x0111:
            r5 = r7
            goto L_0x0008
        L_0x0114:
            if (r5 != r1) goto L_0x0117
            return r4
        L_0x0117:
            return r16
        L_0x0118:
            if (r5 != r1) goto L_0x011b
            return r4
        L_0x011b:
            return r16
        L_0x011c:
            int r7 = r6 >> 3
            if (r7 != r15) goto L_0x019d
            int r7 = r3 + 3
            if (r2 > r7) goto L_0x0128
            if (r5 != r1) goto L_0x0127
            return r4
        L_0x0127:
            return r16
        L_0x0128:
            int r14 = r3 + 1
            byte r14 = r0[r14]
            r15 = r14 & 192(0xc0, float:2.69E-43)
            if (r15 != r13) goto L_0x0199
            int r15 = r3 + 2
            byte r15 = r0[r15]
            r9 = r15 & 192(0xc0, float:2.69E-43)
            if (r9 != r13) goto L_0x0195
            byte r7 = r0[r7]
            r9 = r7 & 192(0xc0, float:2.69E-43)
            if (r9 != r13) goto L_0x0191
            r9 = 3678080(0x381f80, float:5.154088E-39)
            r7 = r7 ^ r9
            int r9 = r15 << 6
            r7 = r7 ^ r9
            int r9 = r14 << 12
            r7 = r7 ^ r9
            int r6 = r6 << 18
            r6 = r6 ^ r7
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r6 <= r7) goto L_0x0154
            if (r5 != r1) goto L_0x0153
            return r4
        L_0x0153:
            return r16
        L_0x0154:
            if (r11 <= r6) goto L_0x0157
            goto L_0x015d
        L_0x0157:
            if (r8 < r6) goto L_0x015d
            if (r5 != r1) goto L_0x015c
            return r4
        L_0x015c:
            return r16
        L_0x015d:
            r7 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r7) goto L_0x0165
            if (r5 != r1) goto L_0x0164
            return r4
        L_0x0164:
            return r16
        L_0x0165:
            int r7 = r5 + 1
            if (r5 != r1) goto L_0x016a
            return r4
        L_0x016a:
            if (r6 == r12) goto L_0x017e
            if (r6 == r10) goto L_0x017e
            if (r6 < 0) goto L_0x0174
            r5 = 31
            if (r5 >= r6) goto L_0x0183
        L_0x0174:
            r5 = 127(0x7f, float:1.78E-43)
            if (r5 <= r6) goto L_0x0179
            goto L_0x017e
        L_0x0179:
            r5 = 159(0x9f, float:2.23E-43)
            if (r5 < r6) goto L_0x017e
            goto L_0x0183
        L_0x017e:
            r5 = 65533(0xfffd, float:9.1831E-41)
            if (r6 != r5) goto L_0x0184
        L_0x0183:
            return r16
        L_0x0184:
            r5 = 65536(0x10000, float:9.18355E-41)
            if (r6 >= r5) goto L_0x018a
            r14 = 1
            goto L_0x018b
        L_0x018a:
            r14 = 2
        L_0x018b:
            int r4 = r4 + r14
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            int r3 = r3 + 4
            goto L_0x0111
        L_0x0191:
            if (r5 != r1) goto L_0x0194
            return r4
        L_0x0194:
            return r16
        L_0x0195:
            if (r5 != r1) goto L_0x0198
            return r4
        L_0x0198:
            return r16
        L_0x0199:
            if (r5 != r1) goto L_0x019c
            return r4
        L_0x019c:
            return r16
        L_0x019d:
            if (r5 != r1) goto L_0x01a0
            return r4
        L_0x01a0:
            return r16
        L_0x01a1:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ByteStringKt.codePointIndexToCharIndex(byte[], int):int");
    }
}
