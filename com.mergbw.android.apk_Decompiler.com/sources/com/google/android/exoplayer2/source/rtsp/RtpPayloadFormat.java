package com.google.android.exoplayer2.source.rtsp;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import com.google.common.collect.ImmutableMap;
import java.util.Map;

public final class RtpPayloadFormat {
    public static final String RTP_MEDIA_AC3 = "AC3";
    public static final String RTP_MEDIA_AMR = "AMR";
    public static final String RTP_MEDIA_AMR_WB = "AMR-WB";
    public static final String RTP_MEDIA_H263_1998 = "H263-1998";
    public static final String RTP_MEDIA_H263_2000 = "H263-2000";
    public static final String RTP_MEDIA_H264 = "H264";
    public static final String RTP_MEDIA_H265 = "H265";
    public static final String RTP_MEDIA_MPEG4_GENERIC = "MPEG4-GENERIC";
    public static final String RTP_MEDIA_MPEG4_LATM_AUDIO = "MP4A-LATM";
    public static final String RTP_MEDIA_MPEG4_VIDEO = "MP4V-ES";
    public static final String RTP_MEDIA_OPUS = "OPUS";
    public static final String RTP_MEDIA_PCMA = "PCMA";
    public static final String RTP_MEDIA_PCMU = "PCMU";
    public static final String RTP_MEDIA_PCM_L16 = "L16";
    public static final String RTP_MEDIA_PCM_L8 = "L8";
    public static final String RTP_MEDIA_VP8 = "VP8";
    public static final String RTP_MEDIA_VP9 = "VP9";
    public final int clockRate;
    public final ImmutableMap<String, String> fmtpParameters;
    public final Format format;
    public final String mediaEncoding;
    public final int rtpPayloadType;

    static boolean isFormatSupported(MediaDescription mediaDescription) {
        String upperCase = Ascii.toUpperCase(mediaDescription.rtpMapAttribute.mediaEncoding);
        upperCase.hashCode();
        char c2 = 65535;
        switch (upperCase.hashCode()) {
            case -1922091719:
                if (upperCase.equals(RTP_MEDIA_MPEG4_GENERIC)) {
                    c2 = 0;
                    break;
                }
                break;
            case 2412:
                if (upperCase.equals(RTP_MEDIA_PCM_L8)) {
                    c2 = 1;
                    break;
                }
                break;
            case 64593:
                if (upperCase.equals(RTP_MEDIA_AC3)) {
                    c2 = 2;
                    break;
                }
                break;
            case 64934:
                if (upperCase.equals(RTP_MEDIA_AMR)) {
                    c2 = 3;
                    break;
                }
                break;
            case 74609:
                if (upperCase.equals(RTP_MEDIA_PCM_L16)) {
                    c2 = 4;
                    break;
                }
                break;
            case 85182:
                if (upperCase.equals(RTP_MEDIA_VP8)) {
                    c2 = 5;
                    break;
                }
                break;
            case 85183:
                if (upperCase.equals(RTP_MEDIA_VP9)) {
                    c2 = 6;
                    break;
                }
                break;
            case 2194728:
                if (upperCase.equals(RTP_MEDIA_H264)) {
                    c2 = 7;
                    break;
                }
                break;
            case 2194729:
                if (upperCase.equals(RTP_MEDIA_H265)) {
                    c2 = 8;
                    break;
                }
                break;
            case 2433087:
                if (upperCase.equals(RTP_MEDIA_OPUS)) {
                    c2 = 9;
                    break;
                }
                break;
            case 2450119:
                if (upperCase.equals(RTP_MEDIA_PCMA)) {
                    c2 = 10;
                    break;
                }
                break;
            case 2450139:
                if (upperCase.equals(RTP_MEDIA_PCMU)) {
                    c2 = 11;
                    break;
                }
                break;
            case 1061166827:
                if (upperCase.equals(RTP_MEDIA_MPEG4_LATM_AUDIO)) {
                    c2 = 12;
                    break;
                }
                break;
            case 1934494802:
                if (upperCase.equals(RTP_MEDIA_AMR_WB)) {
                    c2 = 13;
                    break;
                }
                break;
            case 1959269366:
                if (upperCase.equals(RTP_MEDIA_MPEG4_VIDEO)) {
                    c2 = 14;
                    break;
                }
                break;
            case 2137188397:
                if (upperCase.equals(RTP_MEDIA_H263_1998)) {
                    c2 = 15;
                    break;
                }
                break;
            case 2137209252:
                if (upperCase.equals(RTP_MEDIA_H263_2000)) {
                    c2 = 16;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                return true;
            default:
                return false;
        }
    }

    public static String getMimeTypeFromRtpMediaType(String str) {
        String upperCase = Ascii.toUpperCase(str);
        upperCase.hashCode();
        char c2 = 65535;
        switch (upperCase.hashCode()) {
            case -1922091719:
                if (upperCase.equals(RTP_MEDIA_MPEG4_GENERIC)) {
                    c2 = 0;
                    break;
                }
                break;
            case 2412:
                if (upperCase.equals(RTP_MEDIA_PCM_L8)) {
                    c2 = 1;
                    break;
                }
                break;
            case 64593:
                if (upperCase.equals(RTP_MEDIA_AC3)) {
                    c2 = 2;
                    break;
                }
                break;
            case 64934:
                if (upperCase.equals(RTP_MEDIA_AMR)) {
                    c2 = 3;
                    break;
                }
                break;
            case 74609:
                if (upperCase.equals(RTP_MEDIA_PCM_L16)) {
                    c2 = 4;
                    break;
                }
                break;
            case 85182:
                if (upperCase.equals(RTP_MEDIA_VP8)) {
                    c2 = 5;
                    break;
                }
                break;
            case 85183:
                if (upperCase.equals(RTP_MEDIA_VP9)) {
                    c2 = 6;
                    break;
                }
                break;
            case 2194728:
                if (upperCase.equals(RTP_MEDIA_H264)) {
                    c2 = 7;
                    break;
                }
                break;
            case 2194729:
                if (upperCase.equals(RTP_MEDIA_H265)) {
                    c2 = 8;
                    break;
                }
                break;
            case 2433087:
                if (upperCase.equals(RTP_MEDIA_OPUS)) {
                    c2 = 9;
                    break;
                }
                break;
            case 2450119:
                if (upperCase.equals(RTP_MEDIA_PCMA)) {
                    c2 = 10;
                    break;
                }
                break;
            case 2450139:
                if (upperCase.equals(RTP_MEDIA_PCMU)) {
                    c2 = 11;
                    break;
                }
                break;
            case 1061166827:
                if (upperCase.equals(RTP_MEDIA_MPEG4_LATM_AUDIO)) {
                    c2 = 12;
                    break;
                }
                break;
            case 1934494802:
                if (upperCase.equals(RTP_MEDIA_AMR_WB)) {
                    c2 = 13;
                    break;
                }
                break;
            case 1959269366:
                if (upperCase.equals(RTP_MEDIA_MPEG4_VIDEO)) {
                    c2 = 14;
                    break;
                }
                break;
            case 2137188397:
                if (upperCase.equals(RTP_MEDIA_H263_1998)) {
                    c2 = 15;
                    break;
                }
                break;
            case 2137209252:
                if (upperCase.equals(RTP_MEDIA_H263_2000)) {
                    c2 = 16;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 12:
                return MimeTypes.AUDIO_AAC;
            case 1:
            case 4:
                return MimeTypes.AUDIO_RAW;
            case 2:
                return MimeTypes.AUDIO_AC3;
            case 3:
                return MimeTypes.AUDIO_AMR_NB;
            case 5:
                return MimeTypes.VIDEO_VP8;
            case 6:
                return MimeTypes.VIDEO_VP9;
            case 7:
                return MimeTypes.VIDEO_H264;
            case 8:
                return MimeTypes.VIDEO_H265;
            case 9:
                return MimeTypes.AUDIO_OPUS;
            case 10:
                return MimeTypes.AUDIO_ALAW;
            case 11:
                return MimeTypes.AUDIO_MLAW;
            case 13:
                return MimeTypes.AUDIO_AMR_WB;
            case 14:
                return MimeTypes.VIDEO_MP4V;
            case 15:
            case 16:
                return MimeTypes.VIDEO_H263;
            default:
                throw new IllegalArgumentException(str);
        }
    }

    public static int getRawPcmEncodingType(String str) {
        Assertions.checkArgument(str.equals(RTP_MEDIA_PCM_L8) || str.equals(RTP_MEDIA_PCM_L16));
        return str.equals(RTP_MEDIA_PCM_L8) ? 3 : 268435456;
    }

    public RtpPayloadFormat(Format format2, int i, int i2, Map<String, String> map, String str) {
        this.rtpPayloadType = i;
        this.clockRate = i2;
        this.format = format2;
        this.fmtpParameters = ImmutableMap.copyOf(map);
        this.mediaEncoding = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RtpPayloadFormat rtpPayloadFormat = (RtpPayloadFormat) obj;
        if (this.rtpPayloadType != rtpPayloadFormat.rtpPayloadType || this.clockRate != rtpPayloadFormat.clockRate || !this.format.equals(rtpPayloadFormat.format) || !this.fmtpParameters.equals(rtpPayloadFormat.fmtpParameters) || !this.mediaEncoding.equals(rtpPayloadFormat.mediaEncoding)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((217 + this.rtpPayloadType) * 31) + this.clockRate) * 31) + this.format.hashCode()) * 31) + this.fmtpParameters.hashCode()) * 31) + this.mediaEncoding.hashCode();
    }
}
