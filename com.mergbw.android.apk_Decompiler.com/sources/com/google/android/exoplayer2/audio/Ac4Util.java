package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.primitives.SignedBytes;
import java.nio.ByteBuffer;

public final class Ac4Util {
    public static final int AC40_SYNCWORD = 44096;
    public static final int AC41_SYNCWORD = 44097;
    private static final int CHANNEL_COUNT_2 = 2;
    public static final int HEADER_SIZE_FOR_PARSER = 16;
    public static final int MAX_RATE_BYTES_PER_SECOND = 336000;
    private static final int[] SAMPLE_COUNT = {PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT, 2000, 1920, 1601, 1600, 1001, 1000, 960, 800, 800, 480, 400, 400, 2048};
    public static final int SAMPLE_HEADER_SIZE = 7;

    public static final class SyncFrameInfo {
        public final int bitstreamVersion;
        public final int channelCount;
        public final int frameSize;
        public final int sampleCount;
        public final int sampleRate;

        private SyncFrameInfo(int i, int i2, int i3, int i4, int i5) {
            this.bitstreamVersion = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
        }
    }

    public static Format parseAc4AnnexEFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        parsableByteArray.skipBytes(1);
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_AC4).setChannelCount(2).setSampleRate(((parsableByteArray.readUnsignedByte() & 32) >> 5) == 1 ? OpusUtil.SAMPLE_RATE : 44100).setDrmInitData(drmInitData).setLanguage(str2).build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0080, code lost:
        if (r11 != 11) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        if (r11 != 11) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x008a, code lost:
        if (r11 != 8) goto L_0x008d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.exoplayer2.audio.Ac4Util.SyncFrameInfo parseAc4SyncframeInfo(com.google.android.exoplayer2.util.ParsableBitArray r11) {
        /*
            r0 = 16
            int r1 = r11.readBits(r0)
            int r0 = r11.readBits(r0)
            r2 = 65535(0xffff, float:9.1834E-41)
            r3 = 4
            if (r0 != r2) goto L_0x0018
            r0 = 24
            int r0 = r11.readBits(r0)
            r2 = 7
            goto L_0x0019
        L_0x0018:
            r2 = r3
        L_0x0019:
            int r0 = r0 + r2
            r2 = 44097(0xac41, float:6.1793E-41)
            if (r1 != r2) goto L_0x0021
            int r0 = r0 + 2
        L_0x0021:
            r8 = r0
            r0 = 2
            int r1 = r11.readBits(r0)
            r2 = 3
            if (r1 != r2) goto L_0x002f
            int r4 = readVariableBits(r11, r0)
            int r1 = r1 + r4
        L_0x002f:
            r5 = r1
            r1 = 10
            int r1 = r11.readBits(r1)
            boolean r4 = r11.readBit()
            if (r4 == 0) goto L_0x0045
            int r4 = r11.readBits(r2)
            if (r4 <= 0) goto L_0x0045
            r11.skipBits(r0)
        L_0x0045:
            boolean r4 = r11.readBit()
            r6 = 48000(0xbb80, float:6.7262E-41)
            r7 = 44100(0xac44, float:6.1797E-41)
            if (r4 == 0) goto L_0x0053
            r9 = r6
            goto L_0x0054
        L_0x0053:
            r9 = r7
        L_0x0054:
            int r11 = r11.readBits(r3)
            if (r9 != r7) goto L_0x0063
            r4 = 13
            if (r11 != r4) goto L_0x0063
            int[] r0 = SAMPLE_COUNT
            r11 = r0[r11]
            goto L_0x0093
        L_0x0063:
            if (r9 != r6) goto L_0x0092
            int[] r4 = SAMPLE_COUNT
            int r6 = r4.length
            if (r11 >= r6) goto L_0x0092
            r4 = r4[r11]
            int r1 = r1 % 5
            r6 = 8
            r7 = 1
            if (r1 == r7) goto L_0x0088
            r7 = 11
            if (r1 == r0) goto L_0x0083
            if (r1 == r2) goto L_0x0088
            if (r1 == r3) goto L_0x007c
            goto L_0x008d
        L_0x007c:
            if (r11 == r2) goto L_0x008f
            if (r11 == r6) goto L_0x008f
            if (r11 != r7) goto L_0x008d
            goto L_0x008f
        L_0x0083:
            if (r11 == r6) goto L_0x008f
            if (r11 != r7) goto L_0x008d
            goto L_0x008f
        L_0x0088:
            if (r11 == r2) goto L_0x008f
            if (r11 != r6) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r11 = r4
            goto L_0x0093
        L_0x008f:
            int r4 = r4 + 1
            goto L_0x008d
        L_0x0092:
            r11 = 0
        L_0x0093:
            com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo r0 = new com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo
            r6 = 2
            r10 = 0
            r4 = r0
            r7 = r9
            r9 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.Ac4Util.parseAc4SyncframeInfo(com.google.android.exoplayer2.util.ParsableBitArray):com.google.android.exoplayer2.audio.Ac4Util$SyncFrameInfo");
    }

    public static int parseAc4SyncframeSize(byte[] bArr, int i) {
        int i2 = 7;
        if (bArr.length < 7) {
            return -1;
        }
        byte b2 = ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        if (b2 == 65535) {
            b2 = ((bArr[4] & 255) << 16) | ((bArr[5] & 255) << 8) | (bArr[6] & 255);
        } else {
            i2 = 4;
        }
        if (i == 44097) {
            i2 += 2;
        }
        return b2 + i2;
    }

    public static int parseAc4SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        byte[] bArr = new byte[16];
        int position = byteBuffer.position();
        byteBuffer.get(bArr);
        byteBuffer.position(position);
        return parseAc4SyncframeInfo(new ParsableBitArray(bArr)).sampleCount;
    }

    public static void getAc4SampleHeader(int i, ParsableByteArray parsableByteArray) {
        parsableByteArray.reset(7);
        byte[] data = parsableByteArray.getData();
        data[0] = -84;
        data[1] = SignedBytes.MAX_POWER_OF_TWO;
        data[2] = -1;
        data[3] = -1;
        data[4] = (byte) ((i >> 16) & 255);
        data[5] = (byte) ((i >> 8) & 255);
        data[6] = (byte) (i & 255);
    }

    private static int readVariableBits(ParsableBitArray parsableBitArray, int i) {
        int i2 = 0;
        while (true) {
            int readBits = i2 + parsableBitArray.readBits(i);
            if (!parsableBitArray.readBit()) {
                return readBits;
            }
            i2 = (readBits + 1) << i;
        }
    }

    private Ac4Util() {
    }
}
