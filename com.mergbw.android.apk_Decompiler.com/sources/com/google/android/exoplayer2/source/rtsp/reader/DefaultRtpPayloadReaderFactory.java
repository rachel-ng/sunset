package com.google.android.exoplayer2.source.rtsp.reader;

import com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat;
import com.google.android.exoplayer2.source.rtsp.reader.RtpPayloadReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;

public final class DefaultRtpPayloadReaderFactory implements RtpPayloadReader.Factory {
    public RtpPayloadReader createPayloadReader(RtpPayloadFormat rtpPayloadFormat) {
        String str = (String) Assertions.checkNotNull(rtpPayloadFormat.format.sampleMimeType);
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MimeTypes.VIDEO_H263)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1606874997:
                if (str.equals(MimeTypes.AUDIO_AMR_WB)) {
                    c2 = 2;
                    break;
                }
                break;
            case -53558318:
                if (str.equals(MimeTypes.AUDIO_AAC)) {
                    c2 = 3;
                    break;
                }
                break;
            case 187078296:
                if (str.equals(MimeTypes.AUDIO_AC3)) {
                    c2 = 4;
                    break;
                }
                break;
            case 187094639:
                if (str.equals(MimeTypes.AUDIO_RAW)) {
                    c2 = 5;
                    break;
                }
                break;
            case 1187890754:
                if (str.equals(MimeTypes.VIDEO_MP4V)) {
                    c2 = 6;
                    break;
                }
                break;
            case 1331836730:
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    c2 = 7;
                    break;
                }
                break;
            case 1503095341:
                if (str.equals(MimeTypes.AUDIO_AMR_NB)) {
                    c2 = 8;
                    break;
                }
                break;
            case 1504891608:
                if (str.equals(MimeTypes.AUDIO_OPUS)) {
                    c2 = 9;
                    break;
                }
                break;
            case 1599127256:
                if (str.equals(MimeTypes.VIDEO_VP8)) {
                    c2 = 10;
                    break;
                }
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    c2 = 11;
                    break;
                }
                break;
            case 1903231877:
                if (str.equals(MimeTypes.AUDIO_ALAW)) {
                    c2 = 12;
                    break;
                }
                break;
            case 1903589369:
                if (str.equals(MimeTypes.AUDIO_MLAW)) {
                    c2 = 13;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                return new RtpH263Reader(rtpPayloadFormat);
            case 1:
                return new RtpH265Reader(rtpPayloadFormat);
            case 2:
            case 8:
                return new RtpAmrReader(rtpPayloadFormat);
            case 3:
                if (rtpPayloadFormat.mediaEncoding.equals(RtpPayloadFormat.RTP_MEDIA_MPEG4_LATM_AUDIO)) {
                    return new RtpMp4aReader(rtpPayloadFormat);
                }
                return new RtpAacReader(rtpPayloadFormat);
            case 4:
                return new RtpAc3Reader(rtpPayloadFormat);
            case 5:
            case 12:
            case 13:
                return new RtpPcmReader(rtpPayloadFormat);
            case 6:
                return new RtpMpeg4Reader(rtpPayloadFormat);
            case 7:
                return new RtpH264Reader(rtpPayloadFormat);
            case 9:
                return new RtpOpusReader(rtpPayloadFormat);
            case 10:
                return new RtpVp8Reader(rtpPayloadFormat);
            case 11:
                return new RtpVp9Reader(rtpPayloadFormat);
            default:
                return null;
        }
    }
}
