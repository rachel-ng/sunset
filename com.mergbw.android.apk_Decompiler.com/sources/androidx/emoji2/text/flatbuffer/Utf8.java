package androidx.emoji2.text.flatbuffer;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;

public abstract class Utf8 {
    private static Utf8 DEFAULT;

    public abstract String decodeUtf8(ByteBuffer byteBuffer, int i, int i2);

    public abstract void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer);

    public abstract int encodedLength(CharSequence charSequence);

    public static Utf8 getDefault() {
        if (DEFAULT == null) {
            DEFAULT = new Utf8Safe();
        }
        return DEFAULT;
    }

    public static void setDefault(Utf8 utf8) {
        DEFAULT = utf8;
    }

    static class DecodeUtil {
        private static char highSurrogate(int i) {
            return (char) ((i >>> 10) + okio.Utf8.HIGH_SURROGATE_HEADER);
        }

        private static boolean isNotTrailingByte(byte b2) {
            return b2 > -65;
        }

        static boolean isOneByte(byte b2) {
            return b2 >= 0;
        }

        static boolean isThreeBytes(byte b2) {
            return b2 < -16;
        }

        static boolean isTwoBytes(byte b2) {
            return b2 < -32;
        }

        private static char lowSurrogate(int i) {
            return (char) ((i & AnalyticsListener.EVENT_DRM_KEYS_LOADED) + okio.Utf8.LOG_SURROGATE_HEADER);
        }

        private static int trailingByteValue(byte b2) {
            return b2 & okio.Utf8.REPLACEMENT_BYTE;
        }

        DecodeUtil() {
        }

        static void handleOneByte(byte b2, char[] cArr, int i) {
            cArr[i] = (char) b2;
        }

        static void handleTwoBytes(byte b2, byte b3, char[] cArr, int i) throws IllegalArgumentException {
            if (b2 < -62) {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal leading byte in 2 bytes utf");
            } else if (!isNotTrailingByte(b3)) {
                cArr[i] = (char) (((b2 & Ascii.US) << 6) | trailingByteValue(b3));
            } else {
                throw new IllegalArgumentException("Invalid UTF-8: Illegal trailing byte in 2 bytes utf");
            }
        }

        static void handleThreeBytes(byte b2, byte b3, byte b4, char[] cArr, int i) throws IllegalArgumentException {
            if (isNotTrailingByte(b3) || ((b2 == -32 && b3 < -96) || ((b2 == -19 && b3 >= -96) || isNotTrailingByte(b4)))) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            cArr[i] = (char) (((b2 & 15) << 12) | (trailingByteValue(b3) << 6) | trailingByteValue(b4));
        }

        static void handleFourBytes(byte b2, byte b3, byte b4, byte b5, char[] cArr, int i) throws IllegalArgumentException {
            if (isNotTrailingByte(b3) || (((b2 << Ascii.FS) + (b3 + 112)) >> 30) != 0 || isNotTrailingByte(b4) || isNotTrailingByte(b5)) {
                throw new IllegalArgumentException("Invalid UTF-8");
            }
            int trailingByteValue = ((b2 & 7) << Ascii.DC2) | (trailingByteValue(b3) << 12) | (trailingByteValue(b4) << 6) | trailingByteValue(b5);
            cArr[i] = highSurrogate(trailingByteValue);
            cArr[i + 1] = lowSurrogate(trailingByteValue);
        }
    }

    static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }
}
