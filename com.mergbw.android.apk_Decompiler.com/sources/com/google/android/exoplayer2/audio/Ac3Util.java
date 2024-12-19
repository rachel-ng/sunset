package com.google.android.exoplayer2.audio;

import androidx.core.location.LocationRequestCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import okio.Utf8;

public final class Ac3Util {
    public static final int AC3_MAX_RATE_BYTES_PER_SECOND = 80000;
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, Opcodes.IF_ICMPNE, 192, 224, 256, 320, RendererCapabilities.MODE_SUPPORT_MASK, 448, 512, 576, 640};
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    public static final int E_AC3_MAX_RATE_BYTES_PER_SECOND = 768000;
    private static final int[] SAMPLE_RATE_BY_FSCOD = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, LocationRequestCompat.QUALITY_LOW_POWER, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};
    public static final int TRUEHD_MAX_RATE_BYTES_PER_SECOND = 3062500;
    public static final int TRUEHD_RECHUNK_SAMPLE_COUNT = 16;
    public static final int TRUEHD_SYNCFRAME_PREFIX_LENGTH = 10;

    public static final class SyncFrameInfo {
        public static final int STREAM_TYPE_TYPE0 = 0;
        public static final int STREAM_TYPE_TYPE1 = 1;
        public static final int STREAM_TYPE_TYPE2 = 2;
        public static final int STREAM_TYPE_UNDEFINED = -1;
        public final int bitrate;
        public final int channelCount;
        public final int frameSize;
        public final String mimeType;
        public final int sampleCount;
        public final int sampleRate;
        public final int streamType;

        @Documented
        @Target({ElementType.TYPE_USE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface StreamType {
        }

        private SyncFrameInfo(String str, int i, int i2, int i3, int i4, int i5, int i6) {
            this.mimeType = str;
            this.streamType = i;
            this.channelCount = i2;
            this.sampleRate = i3;
            this.frameSize = i4;
            this.sampleCount = i5;
            this.bitrate = i6;
        }
    }

    public static Format parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i = SAMPLE_RATE_BY_FSCOD[parsableBitArray.readBits(2)];
        parsableBitArray.skipBits(8);
        int i2 = CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)];
        if (parsableBitArray.readBits(1) != 0) {
            i2++;
        }
        int i3 = BITRATE_BY_HALF_FRMSIZECOD[parsableBitArray.readBits(5)] * 1000;
        parsableBitArray.byteAlign();
        parsableByteArray.setPosition(parsableBitArray.getBytePosition());
        return new Format.Builder().setId(str).setSampleMimeType(MimeTypes.AUDIO_AC3).setChannelCount(i2).setSampleRate(i).setDrmInitData(drmInitData).setLanguage(str2).setAverageBitrate(i3).setPeakBitrate(i3).build();
    }

    public static Format parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, String str2, DrmInitData drmInitData) {
        String str3;
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int readBits = parsableBitArray.readBits(13) * 1000;
        parsableBitArray.skipBits(3);
        int i = SAMPLE_RATE_BY_FSCOD[parsableBitArray.readBits(2)];
        parsableBitArray.skipBits(10);
        int i2 = CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)];
        if (parsableBitArray.readBits(1) != 0) {
            i2++;
        }
        parsableBitArray.skipBits(3);
        int readBits2 = parsableBitArray.readBits(4);
        parsableBitArray.skipBits(1);
        if (readBits2 > 0) {
            parsableBitArray.skipBytes(6);
            if (parsableBitArray.readBits(1) != 0) {
                i2 += 2;
            }
            parsableBitArray.skipBits(1);
        }
        if (parsableBitArray.bitsLeft() > 7) {
            parsableBitArray.skipBits(7);
            if (parsableBitArray.readBits(1) != 0) {
                str3 = MimeTypes.AUDIO_E_AC3_JOC;
                parsableBitArray.byteAlign();
                parsableByteArray.setPosition(parsableBitArray.getBytePosition());
                return new Format.Builder().setId(str).setSampleMimeType(str3).setChannelCount(i2).setSampleRate(i).setDrmInitData(drmInitData).setLanguage(str2).setPeakBitrate(readBits).build();
            }
        }
        str3 = MimeTypes.AUDIO_E_AC3;
        parsableBitArray.byteAlign();
        parsableByteArray.setPosition(parsableBitArray.getBytePosition());
        return new Format.Builder().setId(str).setSampleMimeType(str3).setChannelCount(i2).setSampleRate(i).setDrmInitData(drmInitData).setLanguage(str2).setPeakBitrate(readBits).build();
    }

    public static SyncFrameInfo parseAc3SyncframeInfo(ParsableBitArray parsableBitArray) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        String str;
        int i6;
        String str2;
        int i7;
        int i8;
        int i9;
        int i10;
        String str3;
        int i11;
        int i12;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int position = parsableBitArray.getPosition();
        parsableBitArray2.skipBits(40);
        boolean z = parsableBitArray2.readBits(5) > 10;
        parsableBitArray2.setPosition(position);
        int i13 = -1;
        if (z) {
            parsableBitArray2.skipBits(16);
            int readBits = parsableBitArray2.readBits(2);
            if (readBits == 0) {
                i13 = 0;
            } else if (readBits == 1) {
                i13 = 1;
            } else if (readBits == 2) {
                i13 = 2;
            }
            parsableBitArray2.skipBits(3);
            int readBits2 = (parsableBitArray2.readBits(11) + 1) * 2;
            int readBits3 = parsableBitArray2.readBits(2);
            if (readBits3 == 3) {
                i9 = SAMPLE_RATE_BY_FSCOD2[parsableBitArray2.readBits(2)];
                i8 = 3;
                i7 = 6;
            } else {
                int readBits4 = parsableBitArray2.readBits(2);
                int i14 = BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[readBits4];
                i8 = readBits4;
                i9 = SAMPLE_RATE_BY_FSCOD[readBits3];
                i7 = i14;
            }
            int i15 = i7 * 256;
            int calculateEac3Bitrate = calculateEac3Bitrate(readBits2, i9, i7);
            int readBits5 = parsableBitArray2.readBits(3);
            boolean readBit = parsableBitArray.readBit();
            i6 = CHANNEL_COUNT_BY_ACMOD[readBits5] + (readBit ? 1 : 0);
            parsableBitArray2.skipBits(10);
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(8);
            }
            if (readBits5 == 0) {
                parsableBitArray2.skipBits(5);
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
            }
            if (i13 == 1 && parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(16);
            }
            if (parsableBitArray.readBit()) {
                if (readBits5 > 2) {
                    parsableBitArray2.skipBits(2);
                }
                if ((readBits5 & 1) == 0 || readBits5 <= 2) {
                    i11 = 6;
                } else {
                    i11 = 6;
                    parsableBitArray2.skipBits(6);
                }
                if ((readBits5 & 4) != 0) {
                    parsableBitArray2.skipBits(i11);
                }
                if (readBit && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(5);
                }
                if (i13 == 0) {
                    if (parsableBitArray.readBit()) {
                        i12 = 6;
                        parsableBitArray2.skipBits(6);
                    } else {
                        i12 = 6;
                    }
                    if (readBits5 == 0 && parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(i12);
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(i12);
                    }
                    int readBits6 = parsableBitArray2.readBits(2);
                    if (readBits6 == 1) {
                        parsableBitArray2.skipBits(5);
                    } else if (readBits6 == 2) {
                        parsableBitArray2.skipBits(12);
                    } else if (readBits6 == 3) {
                        int readBits7 = parsableBitArray2.readBits(5);
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(4);
                            }
                            if (parsableBitArray.readBit()) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(4);
                                }
                            }
                        }
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(5);
                            if (parsableBitArray.readBit()) {
                                parsableBitArray2.skipBits(7);
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(8);
                                }
                            }
                        }
                        parsableBitArray2.skipBits((readBits7 + 2) * 8);
                        parsableBitArray.byteAlign();
                    }
                    if (readBits5 < 2) {
                        if (parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                        if (readBits5 == 0 && parsableBitArray.readBit()) {
                            parsableBitArray2.skipBits(14);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        if (i8 == 0) {
                            parsableBitArray2.skipBits(5);
                        } else {
                            for (int i16 = 0; i16 < i7; i16++) {
                                if (parsableBitArray.readBit()) {
                                    parsableBitArray2.skipBits(5);
                                }
                            }
                        }
                    }
                }
            }
            if (parsableBitArray.readBit()) {
                parsableBitArray2.skipBits(5);
                if (readBits5 == 2) {
                    parsableBitArray2.skipBits(4);
                }
                if (readBits5 >= 6) {
                    parsableBitArray2.skipBits(2);
                }
                if (parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits5 == 0 && parsableBitArray.readBit()) {
                    parsableBitArray2.skipBits(8);
                }
                if (readBits3 < 3) {
                    parsableBitArray.skipBit();
                }
            }
            if (i13 == 0 && i8 != 3) {
                parsableBitArray.skipBit();
            }
            if (i13 != 2 || (i8 != 3 && !parsableBitArray.readBit())) {
                i10 = 6;
            } else {
                i10 = 6;
                parsableBitArray2.skipBits(6);
            }
            if (parsableBitArray.readBit() && parsableBitArray2.readBits(i10) == 1 && parsableBitArray2.readBits(8) == 1) {
                str3 = MimeTypes.AUDIO_E_AC3_JOC;
            } else {
                str3 = MimeTypes.AUDIO_E_AC3;
            }
            str = str3;
            i5 = i13;
            i2 = i15;
            i3 = readBits2;
            i4 = i9;
            i = calculateEac3Bitrate;
        } else {
            parsableBitArray2.skipBits(32);
            int readBits8 = parsableBitArray2.readBits(2);
            if (readBits8 == 3) {
                str2 = null;
            } else {
                str2 = MimeTypes.AUDIO_AC3;
            }
            int readBits9 = parsableBitArray2.readBits(6);
            int i17 = BITRATE_BY_HALF_FRMSIZECOD[readBits9 / 2] * 1000;
            int ac3SyncframeSize = getAc3SyncframeSize(readBits8, readBits9);
            parsableBitArray2.skipBits(8);
            int readBits10 = parsableBitArray2.readBits(3);
            if (!((readBits10 & 1) == 0 || readBits10 == 1)) {
                parsableBitArray2.skipBits(2);
            }
            if ((readBits10 & 4) != 0) {
                parsableBitArray2.skipBits(2);
            }
            if (readBits10 == 2) {
                parsableBitArray2.skipBits(2);
            }
            int[] iArr = SAMPLE_RATE_BY_FSCOD;
            int i18 = readBits8 < iArr.length ? iArr[readBits8] : -1;
            i6 = CHANNEL_COUNT_BY_ACMOD[readBits10] + (parsableBitArray.readBit() ? 1 : 0);
            i5 = -1;
            str = str2;
            i2 = AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
            i = i17;
            i3 = ac3SyncframeSize;
            i4 = i18;
        }
        return new SyncFrameInfo(str, i5, i6, i4, i3, i2, i);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        if (bArr.length < 6) {
            return -1;
        }
        if (((bArr[5] & 248) >> 3) > 10) {
            return (((bArr[3] & 255) | ((bArr[2] & 7) << 8)) + 1) * 2;
        }
        byte b2 = bArr[4];
        return getAc3SyncframeSize((b2 & 192) >> 6, b2 & Utf8.REPLACEMENT_BYTE);
    }

    public static int parseAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        int i = 3;
        if (((byteBuffer.get(byteBuffer.position() + 5) & 248) >> 3) <= 10) {
            return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
        }
        if (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3) {
            i = (byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4;
        }
        return BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[i] * 256;
    }

    public static int findTrueHdSyncframeOffset(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit() - 10;
        for (int i = position; i <= limit; i++) {
            if ((Util.getBigEndianInt(byteBuffer, i + 4) & -2) == -126718022) {
                return i - position;
            }
        }
        return -1;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(byte[] bArr) {
        boolean z = false;
        if (bArr[4] == -8 && bArr[5] == 114 && bArr[6] == 111) {
            byte b2 = bArr[7];
            if ((b2 & 254) == 186) {
                if ((b2 & 255) == 187) {
                    z = true;
                }
                return 40 << ((bArr[z ? (char) 9 : 8] >> 4) & 7);
            }
        }
        return 0;
    }

    public static int parseTrueHdSyncframeAudioSampleCount(ByteBuffer byteBuffer, int i) {
        return 40 << ((byteBuffer.get((byteBuffer.position() + i) + ((byteBuffer.get((byteBuffer.position() + i) + 7) & 255) == 187 ? 9 : 8)) >> 4) & 7);
    }

    private static int getAc3SyncframeSize(int i, int i2) {
        int i3 = i2 / 2;
        if (i < 0) {
            return -1;
        }
        int[] iArr = SAMPLE_RATE_BY_FSCOD;
        if (i >= iArr.length || i2 < 0) {
            return -1;
        }
        int[] iArr2 = SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
        if (i3 >= iArr2.length) {
            return -1;
        }
        int i4 = iArr[i];
        if (i4 == 44100) {
            return (iArr2[i3] + (i2 % 2)) * 2;
        }
        int i5 = BITRATE_BY_HALF_FRMSIZECOD[i3];
        return i4 == 32000 ? i5 * 6 : i5 * 4;
    }

    private static int calculateEac3Bitrate(int i, int i2, int i3) {
        return (i * i2) / (i3 * 32);
    }

    private Ac3Util() {
    }
}
