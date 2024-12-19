package com.google.android.exoplayer2.source.rtsp;

import android.net.Uri;
import android.util.Base64;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

final class RtspMediaTrack {
    private static final String AAC_CODECS_PREFIX = "mp4a.40.";
    private static final int DEFAULT_H263_HEIGHT = 288;
    private static final int DEFAULT_H263_WIDTH = 352;
    private static final int DEFAULT_MP4V_HEIGHT = 288;
    private static final int DEFAULT_MP4V_WIDTH = 352;
    private static final int DEFAULT_VP8_HEIGHT = 240;
    private static final int DEFAULT_VP8_WIDTH = 320;
    private static final int DEFAULT_VP9_HEIGHT = 240;
    private static final int DEFAULT_VP9_WIDTH = 320;
    private static final String GENERIC_CONTROL_ATTR = "*";
    private static final String H264_CODECS_PREFIX = "avc1.";
    private static final String MPEG4_CODECS_PREFIX = "mp4v.";
    private static final int OPUS_CLOCK_RATE = 48000;
    private static final String PARAMETER_AMR_INTERLEAVING = "interleaving";
    private static final String PARAMETER_AMR_OCTET_ALIGN = "octet-align";
    private static final String PARAMETER_H265_SPROP_MAX_DON_DIFF = "sprop-max-don-diff";
    private static final String PARAMETER_H265_SPROP_PPS = "sprop-pps";
    private static final String PARAMETER_H265_SPROP_SPS = "sprop-sps";
    private static final String PARAMETER_H265_SPROP_VPS = "sprop-vps";
    private static final String PARAMETER_MP4A_CONFIG = "config";
    private static final String PARAMETER_MP4A_C_PRESENT = "cpresent";
    private static final String PARAMETER_PROFILE_LEVEL_ID = "profile-level-id";
    private static final String PARAMETER_SPROP_PARAMS = "sprop-parameter-sets";
    public final RtpPayloadFormat payloadFormat;
    public final Uri uri;

    public RtspMediaTrack(MediaDescription mediaDescription, Uri uri2) {
        Assertions.checkArgument(mediaDescription.attributes.containsKey(SessionDescription.ATTR_CONTROL), "missing attribute control");
        this.payloadFormat = generatePayloadFormat(mediaDescription);
        this.uri = extractTrackUri(uri2, (String) Util.castNonNull(mediaDescription.attributes.get(SessionDescription.ATTR_CONTROL)));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RtspMediaTrack rtspMediaTrack = (RtspMediaTrack) obj;
        if (!this.payloadFormat.equals(rtspMediaTrack.payloadFormat) || !this.uri.equals(rtspMediaTrack.uri)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((217 + this.payloadFormat.hashCode()) * 31) + this.uri.hashCode();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat generatePayloadFormat(com.google.android.exoplayer2.source.rtsp.MediaDescription r13) {
        /*
            com.google.android.exoplayer2.Format$Builder r0 = new com.google.android.exoplayer2.Format$Builder
            r0.<init>()
            int r1 = r13.bitrate
            if (r1 <= 0) goto L_0x000e
            int r1 = r13.bitrate
            r0.setAverageBitrate(r1)
        L_0x000e:
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r1 = r13.rtpMapAttribute
            int r4 = r1.payloadType
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r1 = r13.rtpMapAttribute
            java.lang.String r7 = r1.mediaEncoding
            java.lang.String r1 = com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat.getMimeTypeFromRtpMediaType(r7)
            r0.setSampleMimeType(r1)
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r2 = r13.rtpMapAttribute
            int r5 = r2.clockRate
            java.lang.String r2 = "audio"
            java.lang.String r3 = r13.mediaType
            boolean r2 = r2.equals(r3)
            r3 = -1
            if (r2 == 0) goto L_0x003c
            com.google.android.exoplayer2.source.rtsp.MediaDescription$RtpMapAttribute r2 = r13.rtpMapAttribute
            int r2 = r2.encodingParameters
            int r2 = inferChannelCount(r2, r1)
            com.google.android.exoplayer2.Format$Builder r6 = r0.setSampleRate(r5)
            r6.setChannelCount(r2)
            goto L_0x003d
        L_0x003c:
            r2 = r3
        L_0x003d:
            com.google.common.collect.ImmutableMap r6 = r13.getFmtpParametersAsMap()
            int r13 = r1.hashCode()
            r8 = 2
            r9 = 0
            r10 = 1
            switch(r13) {
                case -1664118616: goto L_0x00da;
                case -1662541442: goto L_0x00d0;
                case -1606874997: goto L_0x00c6;
                case -53558318: goto L_0x00bc;
                case 187078296: goto L_0x00b1;
                case 187094639: goto L_0x00a6;
                case 1187890754: goto L_0x009c;
                case 1331836730: goto L_0x0092;
                case 1503095341: goto L_0x0088;
                case 1504891608: goto L_0x007d;
                case 1599127256: goto L_0x0071;
                case 1599127257: goto L_0x0065;
                case 1903231877: goto L_0x0059;
                case 1903589369: goto L_0x004d;
                default: goto L_0x004b;
            }
        L_0x004b:
            goto L_0x00e4
        L_0x004d:
            java.lang.String r13 = "audio/g711-mlaw"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 13
            goto L_0x00e5
        L_0x0059:
            java.lang.String r13 = "audio/g711-alaw"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 12
            goto L_0x00e5
        L_0x0065:
            java.lang.String r13 = "video/x-vnd.on2.vp9"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 9
            goto L_0x00e5
        L_0x0071:
            java.lang.String r13 = "video/x-vnd.on2.vp8"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 8
            goto L_0x00e5
        L_0x007d:
            java.lang.String r13 = "audio/opus"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 3
            goto L_0x00e5
        L_0x0088:
            java.lang.String r13 = "audio/3gpp"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = r10
            goto L_0x00e5
        L_0x0092:
            java.lang.String r13 = "video/avc"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 6
            goto L_0x00e5
        L_0x009c:
            java.lang.String r13 = "video/mp4v-es"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 4
            goto L_0x00e5
        L_0x00a6:
            java.lang.String r13 = "audio/raw"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 10
            goto L_0x00e5
        L_0x00b1:
            java.lang.String r13 = "audio/ac3"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 11
            goto L_0x00e5
        L_0x00bc:
            java.lang.String r13 = "audio/mp4a-latm"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = r9
            goto L_0x00e5
        L_0x00c6:
            java.lang.String r13 = "audio/amr-wb"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = r8
            goto L_0x00e5
        L_0x00d0:
            java.lang.String r13 = "video/hevc"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 7
            goto L_0x00e5
        L_0x00da:
            java.lang.String r13 = "video/3gpp"
            boolean r13 = r1.equals(r13)
            if (r13 == 0) goto L_0x00e4
            r13 = 5
            goto L_0x00e5
        L_0x00e4:
            r13 = r3
        L_0x00e5:
            r1 = 240(0xf0, float:3.36E-43)
            r11 = 320(0x140, float:4.48E-43)
            java.lang.String r12 = "missing attribute fmtp"
            switch(r13) {
                case 0: goto L_0x0183;
                case 1: goto L_0x0156;
                case 2: goto L_0x0156;
                case 3: goto L_0x013f;
                case 4: goto L_0x0132;
                case 5: goto L_0x0125;
                case 6: goto L_0x0118;
                case 7: goto L_0x010b;
                case 8: goto L_0x0102;
                case 9: goto L_0x00f9;
                case 10: goto L_0x00f0;
                default: goto L_0x00ee;
            }
        L_0x00ee:
            goto L_0x01f9
        L_0x00f0:
            int r13 = com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat.getRawPcmEncodingType(r7)
            r0.setPcmEncoding(r13)
            goto L_0x01f9
        L_0x00f9:
            com.google.android.exoplayer2.Format$Builder r13 = r0.setWidth(r11)
            r13.setHeight(r1)
            goto L_0x01f9
        L_0x0102:
            com.google.android.exoplayer2.Format$Builder r13 = r0.setWidth(r11)
            r13.setHeight(r1)
            goto L_0x01f9
        L_0x010b:
            boolean r13 = r6.isEmpty()
            r13 = r13 ^ r10
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r12)
            processH265FmtpAttribute(r0, r6)
            goto L_0x01f9
        L_0x0118:
            boolean r13 = r6.isEmpty()
            r13 = r13 ^ r10
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r12)
            processH264FmtpAttribute(r0, r6)
            goto L_0x01f9
        L_0x0125:
            r13 = 352(0x160, float:4.93E-43)
            com.google.android.exoplayer2.Format$Builder r13 = r0.setWidth(r13)
            r1 = 288(0x120, float:4.04E-43)
            r13.setHeight(r1)
            goto L_0x01f9
        L_0x0132:
            boolean r13 = r6.isEmpty()
            r13 = r13 ^ r10
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13)
            processMPEG4FmtpAttribute(r0, r6)
            goto L_0x01f9
        L_0x013f:
            if (r2 == r3) goto L_0x0143
            r13 = r10
            goto L_0x0144
        L_0x0143:
            r13 = r9
        L_0x0144:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13)
            r13 = 48000(0xbb80, float:6.7262E-41)
            if (r5 != r13) goto L_0x014e
            r13 = r10
            goto L_0x014f
        L_0x014e:
            r13 = r9
        L_0x014f:
            java.lang.String r1 = "Invalid OPUS clock rate."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            goto L_0x01f9
        L_0x0156:
            if (r2 != r10) goto L_0x015a
            r13 = r10
            goto L_0x015b
        L_0x015a:
            r13 = r9
        L_0x015b:
            java.lang.String r1 = "Multi channel AMR is not currently supported."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            boolean r13 = r6.isEmpty()
            r13 = r13 ^ r10
            java.lang.String r1 = "fmtp parameters must include octet-align."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            java.lang.String r13 = "octet-align"
            boolean r13 = r6.containsKey(r13)
            java.lang.String r1 = "Only octet aligned mode is currently supported."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            java.lang.String r13 = "interleaving"
            boolean r13 = r6.containsKey(r13)
            r13 = r13 ^ r10
            java.lang.String r1 = "Interleaving mode is not currently supported."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            goto L_0x01f9
        L_0x0183:
            if (r2 == r3) goto L_0x0187
            r13 = r10
            goto L_0x0188
        L_0x0187:
            r13 = r9
        L_0x0188:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13)
            boolean r13 = r6.isEmpty()
            r13 = r13 ^ r10
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r12)
            java.lang.String r13 = "MP4A-LATM"
            boolean r13 = r7.equals(r13)
            if (r13 == 0) goto L_0x01f6
            java.lang.String r13 = "cpresent"
            boolean r1 = r6.containsKey(r13)
            if (r1 == 0) goto L_0x01b3
            java.lang.Object r13 = r6.get(r13)
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r1 = "0"
            boolean r13 = r13.equals(r1)
            if (r13 == 0) goto L_0x01b3
            r13 = r10
            goto L_0x01b4
        L_0x01b3:
            r13 = r9
        L_0x01b4:
            java.lang.String r1 = "Only supports cpresent=0 in AAC audio."
            com.google.android.exoplayer2.util.Assertions.checkArgument(r13, r1)
            java.lang.String r13 = "config"
            java.lang.Object r13 = r6.get(r13)
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r1 = "AAC audio stream must include config fmtp parameter"
            com.google.android.exoplayer2.util.Assertions.checkNotNull(r13, r1)
            int r1 = r13.length()
            int r1 = r1 % r8
            if (r1 != 0) goto L_0x01cf
            r1 = r10
            goto L_0x01d0
        L_0x01cf:
            r1 = r9
        L_0x01d0:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r8 = "Malformat MPEG4 config: "
            r3.<init>(r8)
            r3.append(r13)
            java.lang.String r3 = r3.toString()
            com.google.android.exoplayer2.util.Assertions.checkArgument(r1, r3)
            com.google.android.exoplayer2.audio.AacUtil$Config r13 = parseAacStreamMuxConfig(r13)
            int r1 = r13.sampleRateHz
            com.google.android.exoplayer2.Format$Builder r1 = r0.setSampleRate(r1)
            int r3 = r13.channelCount
            com.google.android.exoplayer2.Format$Builder r1 = r1.setChannelCount(r3)
            java.lang.String r13 = r13.codecs
            r1.setCodecs(r13)
        L_0x01f6:
            processAacFmtpAttribute(r0, r6, r2, r5)
        L_0x01f9:
            if (r5 <= 0) goto L_0x01fc
            r9 = r10
        L_0x01fc:
            com.google.android.exoplayer2.util.Assertions.checkArgument(r9)
            com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat r13 = new com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat
            com.google.android.exoplayer2.Format r3 = r0.build()
            r2 = r13
            r2.<init>(r3, r4, r5, r6, r7)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.rtsp.RtspMediaTrack.generatePayloadFormat(com.google.android.exoplayer2.source.rtsp.MediaDescription):com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat");
    }

    private static int inferChannelCount(int i, String str) {
        if (i != -1) {
            return i;
        }
        return str.equals(MimeTypes.AUDIO_AC3) ? 6 : 1;
    }

    private static void processAacFmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap, int i, int i2) {
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_PROFILE_LEVEL_ID), "missing profile-level-id param");
        builder.setCodecs(AAC_CODECS_PREFIX + ((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_PROFILE_LEVEL_ID))));
        builder.setInitializationData(ImmutableList.of(AacUtil.buildAacLcAudioSpecificConfig(i2, i)));
    }

    private static AacUtil.Config parseAacStreamMuxConfig(String str) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(Util.getBytesFromHexString(str));
        boolean z = true;
        Assertions.checkArgument(parsableBitArray.readBits(1) == 0, "Only supports audio mux version 0.");
        Assertions.checkArgument(parsableBitArray.readBits(1) == 1, "Only supports allStreamsSameTimeFraming.");
        parsableBitArray.skipBits(6);
        Assertions.checkArgument(parsableBitArray.readBits(4) == 0, "Only supports one program.");
        if (parsableBitArray.readBits(3) != 0) {
            z = false;
        }
        Assertions.checkArgument(z, "Only supports one numLayer.");
        try {
            return AacUtil.parseAudioSpecificConfig(parsableBitArray, false);
        } catch (ParserException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static void processMPEG4FmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap) {
        String str = immutableMap.get(PARAMETER_MP4A_CONFIG);
        if (str != null) {
            byte[] bytesFromHexString = Util.getBytesFromHexString(str);
            builder.setInitializationData(ImmutableList.of(bytesFromHexString));
            Pair<Integer, Integer> videoResolutionFromMpeg4VideoConfig = CodecSpecificDataUtil.getVideoResolutionFromMpeg4VideoConfig(bytesFromHexString);
            builder.setWidth(((Integer) videoResolutionFromMpeg4VideoConfig.first).intValue()).setHeight(((Integer) videoResolutionFromMpeg4VideoConfig.second).intValue());
        } else {
            builder.setWidth(352).setHeight(288);
        }
        String str2 = immutableMap.get(PARAMETER_PROFILE_LEVEL_ID);
        StringBuilder sb = new StringBuilder(MPEG4_CODECS_PREFIX);
        if (str2 == null) {
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        sb.append(str2);
        builder.setCodecs(sb.toString());
    }

    private static byte[] getInitializationDataFromParameterSet(String str) {
        byte[] decode = Base64.decode(str, 0);
        byte[] bArr = new byte[(decode.length + NalUnitUtil.NAL_START_CODE.length)];
        System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, 0, NalUnitUtil.NAL_START_CODE.length);
        System.arraycopy(decode, 0, bArr, NalUnitUtil.NAL_START_CODE.length, decode.length);
        return bArr;
    }

    private static void processH264FmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap) {
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_SPROP_PARAMS), "missing sprop parameter");
        String[] split = Util.split((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_SPROP_PARAMS)), ",");
        Assertions.checkArgument(split.length == 2, "empty sprop value");
        ImmutableList of = ImmutableList.of(getInitializationDataFromParameterSet(split[0]), getInitializationDataFromParameterSet(split[1]));
        builder.setInitializationData(of);
        byte[] bArr = (byte[]) of.get(0);
        NalUnitUtil.SpsData parseSpsNalUnit = NalUnitUtil.parseSpsNalUnit(bArr, NalUnitUtil.NAL_START_CODE.length, bArr.length);
        builder.setPixelWidthHeightRatio(parseSpsNalUnit.pixelWidthHeightRatio);
        builder.setHeight(parseSpsNalUnit.height);
        builder.setWidth(parseSpsNalUnit.width);
        String str = immutableMap.get(PARAMETER_PROFILE_LEVEL_ID);
        if (str != null) {
            builder.setCodecs(H264_CODECS_PREFIX + str);
            return;
        }
        builder.setCodecs(CodecSpecificDataUtil.buildAvcCodecString(parseSpsNalUnit.profileIdc, parseSpsNalUnit.constraintsFlagsAndReservedZero2Bits, parseSpsNalUnit.levelIdc));
    }

    private static void processH265FmtpAttribute(Format.Builder builder, ImmutableMap<String, String> immutableMap) {
        if (immutableMap.containsKey(PARAMETER_H265_SPROP_MAX_DON_DIFF)) {
            int parseInt = Integer.parseInt((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_H265_SPROP_MAX_DON_DIFF)));
            boolean z = parseInt == 0;
            Assertions.checkArgument(z, "non-zero sprop-max-don-diff " + parseInt + " is not supported");
        }
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_H265_SPROP_VPS), "missing sprop-vps parameter");
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_H265_SPROP_SPS), "missing sprop-sps parameter");
        Assertions.checkArgument(immutableMap.containsKey(PARAMETER_H265_SPROP_PPS), "missing sprop-pps parameter");
        ImmutableList of = ImmutableList.of(getInitializationDataFromParameterSet((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_H265_SPROP_VPS))), getInitializationDataFromParameterSet((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_H265_SPROP_SPS))), getInitializationDataFromParameterSet((String) Assertions.checkNotNull(immutableMap.get(PARAMETER_H265_SPROP_PPS))));
        builder.setInitializationData(of);
        byte[] bArr = (byte[]) of.get(1);
        NalUnitUtil.H265SpsData parseH265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, NalUnitUtil.NAL_START_CODE.length, bArr.length);
        builder.setPixelWidthHeightRatio(parseH265SpsNalUnit.pixelWidthHeightRatio);
        builder.setHeight(parseH265SpsNalUnit.height).setWidth(parseH265SpsNalUnit.width);
        builder.setCodecs(CodecSpecificDataUtil.buildHevcCodecString(parseH265SpsNalUnit.generalProfileSpace, parseH265SpsNalUnit.generalTierFlag, parseH265SpsNalUnit.generalProfileIdc, parseH265SpsNalUnit.generalProfileCompatibilityFlags, parseH265SpsNalUnit.constraintBytes, parseH265SpsNalUnit.generalLevelIdc));
    }

    private static Uri extractTrackUri(Uri uri2, String str) {
        Uri parse = Uri.parse(str);
        if (parse.isAbsolute()) {
            return parse;
        }
        if (str.equals("*")) {
            return uri2;
        }
        return uri2.buildUpon().appendEncodedPath(str).build();
    }
}
