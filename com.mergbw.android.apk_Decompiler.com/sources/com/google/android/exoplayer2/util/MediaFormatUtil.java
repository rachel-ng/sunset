package com.google.android.exoplayer2.util;

import android.media.MediaFormat;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.video.ColorInfo;
import java.nio.ByteBuffer;
import java.util.List;

public final class MediaFormatUtil {
    public static final String KEY_MAX_BIT_RATE = "max-bitrate";
    public static final String KEY_PCM_ENCODING_EXTENDED = "exo-pcm-encoding-int";
    public static final String KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT = "exo-pixel-width-height-ratio-float";
    private static final int MAX_POWER_OF_TWO_INT = 1073741824;

    private static boolean isValidColorRange(int i) {
        return i == 2 || i == 1 || i == -1;
    }

    private static boolean isValidColorSpace(int i) {
        return i == 2 || i == 1 || i == 6 || i == -1;
    }

    private static boolean isValidColorTransfer(int i) {
        return i == 3 || i == 6 || i == 7 || i == -1;
    }

    public static MediaFormat createMediaFormatFromFormat(Format format) {
        MediaFormat mediaFormat = new MediaFormat();
        maybeSetInteger(mediaFormat, "bitrate", format.bitrate);
        maybeSetInteger(mediaFormat, KEY_MAX_BIT_RATE, format.peakBitrate);
        maybeSetInteger(mediaFormat, "channel-count", format.channelCount);
        maybeSetColorInfo(mediaFormat, format.colorInfo);
        maybeSetString(mediaFormat, "mime", format.sampleMimeType);
        maybeSetString(mediaFormat, "codecs-string", format.codecs);
        maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        maybeSetInteger(mediaFormat, "width", format.width);
        maybeSetInteger(mediaFormat, "height", format.height);
        setCsdBuffers(mediaFormat, format.initializationData);
        maybeSetPcmEncoding(mediaFormat, format.pcmEncoding);
        maybeSetString(mediaFormat, "language", format.language);
        maybeSetInteger(mediaFormat, "max-input-size", format.maxInputSize);
        maybeSetInteger(mediaFormat, "sample-rate", format.sampleRate);
        maybeSetInteger(mediaFormat, "caption-service-number", format.accessibilityChannel);
        mediaFormat.setInteger("rotation-degrees", format.rotationDegrees);
        int i = format.selectionFlags;
        setBooleanAsInt(mediaFormat, "is-autoselect", i & 4);
        setBooleanAsInt(mediaFormat, "is-default", i & 1);
        setBooleanAsInt(mediaFormat, "is-forced-subtitle", i & 2);
        mediaFormat.setInteger("encoder-delay", format.encoderDelay);
        mediaFormat.setInteger("encoder-padding", format.encoderPadding);
        maybeSetPixelAspectRatio(mediaFormat, format.pixelWidthHeightRatio);
        return mediaFormat;
    }

    public static void maybeSetString(MediaFormat mediaFormat, String str, String str2) {
        if (str2 != null) {
            mediaFormat.setString(str, str2);
        }
    }

    public static void setCsdBuffers(MediaFormat mediaFormat, List<byte[]> list) {
        for (int i = 0; i < list.size(); i++) {
            mediaFormat.setByteBuffer("csd-" + i, ByteBuffer.wrap(list.get(i)));
        }
    }

    public static void maybeSetInteger(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    public static void maybeSetFloat(MediaFormat mediaFormat, String str, float f) {
        if (f != -1.0f) {
            mediaFormat.setFloat(str, f);
        }
    }

    public static void maybeSetByteBuffer(MediaFormat mediaFormat, String str, byte[] bArr) {
        if (bArr != null) {
            mediaFormat.setByteBuffer(str, ByteBuffer.wrap(bArr));
        }
    }

    public static void maybeSetColorInfo(MediaFormat mediaFormat, ColorInfo colorInfo) {
        if (colorInfo != null) {
            maybeSetInteger(mediaFormat, "color-transfer", colorInfo.colorTransfer);
            maybeSetInteger(mediaFormat, "color-standard", colorInfo.colorSpace);
            maybeSetInteger(mediaFormat, "color-range", colorInfo.colorRange);
            maybeSetByteBuffer(mediaFormat, "hdr-static-info", colorInfo.hdrStaticInfo);
        }
    }

    public static ColorInfo getColorInfo(MediaFormat mediaFormat) {
        if (Util.SDK_INT < 29) {
            return null;
        }
        int m = mediaFormat.getInteger("color-standard", -1);
        int m2 = mediaFormat.getInteger("color-range", -1);
        int m3 = mediaFormat.getInteger("color-transfer", -1);
        ByteBuffer byteBuffer = mediaFormat.getByteBuffer("hdr-static-info");
        byte[] array = byteBuffer != null ? getArray(byteBuffer) : null;
        if (!isValidColorSpace(m)) {
            m = -1;
        }
        if (!isValidColorRange(m2)) {
            m2 = -1;
        }
        if (!isValidColorTransfer(m3)) {
            m3 = -1;
        }
        if (m == -1 && m2 == -1 && m3 == -1 && array == null) {
            return null;
        }
        return new ColorInfo(m, m2, m3, array);
    }

    public static byte[] getArray(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static void setBooleanAsInt(MediaFormat mediaFormat, String str, int i) {
        mediaFormat.setInteger(str, i != 0 ? 1 : 0);
    }

    private static void maybeSetPixelAspectRatio(MediaFormat mediaFormat, float f) {
        int i;
        mediaFormat.setFloat(KEY_PIXEL_WIDTH_HEIGHT_RATIO_FLOAT, f);
        int i2 = 1073741824;
        if (f < 1.0f) {
            i2 = (int) (f * ((float) 1073741824));
            i = 1073741824;
        } else if (f > 1.0f) {
            i = (int) (((float) 1073741824) / f);
        } else {
            i2 = 1;
            i = 1;
        }
        mediaFormat.setInteger("sar-width", i2);
        mediaFormat.setInteger("sar-height", i);
    }

    private static void maybeSetPcmEncoding(MediaFormat mediaFormat, int i) {
        int i2;
        if (i != -1) {
            maybeSetInteger(mediaFormat, KEY_PCM_ENCODING_EXTENDED, i);
            if (i == 0) {
                i2 = 0;
            } else if (i == 536870912) {
                i2 = 21;
            } else if (i != 805306368) {
                i2 = 2;
                if (i != 2) {
                    i2 = 3;
                    if (i != 3) {
                        i2 = 4;
                        if (i != 4) {
                            return;
                        }
                    }
                }
            } else {
                i2 = 22;
            }
            mediaFormat.setInteger("pcm-encoding", i2);
        }
    }

    private MediaFormatUtil() {
    }
}
