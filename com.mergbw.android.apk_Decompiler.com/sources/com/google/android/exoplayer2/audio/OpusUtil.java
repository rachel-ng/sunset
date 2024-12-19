package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import okio.Utf8;

public class OpusUtil {
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int FULL_CODEC_INITIALIZATION_DATA_BUFFER_COUNT = 3;
    public static final int MAX_BYTES_PER_SECOND = 63750;
    public static final int SAMPLE_RATE = 48000;

    private static long getPacketDurationUs(byte b2, byte b3) {
        byte b4;
        byte b5 = b2 & 255;
        byte b6 = b2 & 3;
        if (b6 != 0) {
            b4 = 2;
            if (!(b6 == 1 || b6 == 2)) {
                b4 = b3 & Utf8.REPLACEMENT_BYTE;
            }
        } else {
            b4 = 1;
        }
        int i = b5 >> 3;
        int i2 = i & 3;
        return ((long) b4) * ((long) (i >= 16 ? DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS << i2 : i >= 12 ? 10000 << (i & 1) : i2 == 3 ? 60000 : 10000 << i2));
    }

    private OpusUtil() {
    }

    public static int getChannelCount(byte[] bArr) {
        return bArr[9] & 255;
    }

    public static List<byte[]> buildInitializationData(byte[] bArr) {
        long sampleCountToNanoseconds = sampleCountToNanoseconds((long) getPreSkipSamples(bArr));
        long sampleCountToNanoseconds2 = sampleCountToNanoseconds(3840);
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(bArr);
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds));
        arrayList.add(buildNativeOrderByteArray(sampleCountToNanoseconds2));
        return arrayList;
    }

    public static int parsePacketAudioSampleCount(ByteBuffer byteBuffer) {
        byte b2 = 0;
        byte b3 = byteBuffer.get(0);
        if (byteBuffer.limit() > 1) {
            b2 = byteBuffer.get(1);
        }
        return (int) ((getPacketDurationUs(b3, b2) * 48000) / 1000000);
    }

    public static long getPacketDurationUs(byte[] bArr) {
        byte b2 = 0;
        byte b3 = bArr[0];
        if (bArr.length > 1) {
            b2 = bArr[1];
        }
        return getPacketDurationUs(b3, b2);
    }

    private static int getPreSkipSamples(byte[] bArr) {
        return (bArr[10] & 255) | ((bArr[11] & 255) << 8);
    }

    private static byte[] buildNativeOrderByteArray(long j) {
        return ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(j).array();
    }

    private static long sampleCountToNanoseconds(long j) {
        return (j * C.NANOS_PER_SECOND) / 48000;
    }
}
