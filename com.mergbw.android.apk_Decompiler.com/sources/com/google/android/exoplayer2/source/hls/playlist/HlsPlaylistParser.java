package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

public final class HlsPlaylistParser implements ParsingLoadable.Parser<HlsPlaylist> {
    private static final String ATTR_CLOSED_CAPTIONS_NONE = "CLOSED-CAPTIONS=NONE";
    private static final String BOOLEAN_FALSE = "NO";
    private static final String BOOLEAN_TRUE = "YES";
    private static final String KEYFORMAT_IDENTITY = "identity";
    private static final String KEYFORMAT_PLAYREADY = "com.microsoft.playready";
    private static final String KEYFORMAT_WIDEVINE_PSSH_BINARY = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed";
    private static final String KEYFORMAT_WIDEVINE_PSSH_JSON = "com.widevine";
    private static final String LOG_TAG = "HlsPlaylistParser";
    private static final String METHOD_AES_128 = "AES-128";
    private static final String METHOD_NONE = "NONE";
    private static final String METHOD_SAMPLE_AES = "SAMPLE-AES";
    private static final String METHOD_SAMPLE_AES_CENC = "SAMPLE-AES-CENC";
    private static final String METHOD_SAMPLE_AES_CTR = "SAMPLE-AES-CTR";
    private static final String PLAYLIST_HEADER = "#EXTM3U";
    private static final Pattern REGEX_ATTR_BYTERANGE = Pattern.compile("BYTERANGE=\"(\\d+(?:@\\d+)?)\\b\"");
    private static final Pattern REGEX_ATTR_DURATION = Pattern.compile("DURATION=([\\d\\.]+)\\b");
    private static final Pattern REGEX_AUDIO = Pattern.compile("AUDIO=\"(.+?)\"");
    private static final Pattern REGEX_AUTOSELECT = compileBooleanAttrPattern("AUTOSELECT");
    private static final Pattern REGEX_AVERAGE_BANDWIDTH = Pattern.compile("AVERAGE-BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BANDWIDTH = Pattern.compile("[^-]BANDWIDTH=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE = Pattern.compile("#EXT-X-BYTERANGE:(\\d+(?:@\\d+)?)\\b");
    private static final Pattern REGEX_BYTERANGE_LENGTH = Pattern.compile("BYTERANGE-LENGTH=(\\d+)\\b");
    private static final Pattern REGEX_BYTERANGE_START = Pattern.compile("BYTERANGE-START=(\\d+)\\b");
    private static final Pattern REGEX_CAN_BLOCK_RELOAD = compileBooleanAttrPattern("CAN-BLOCK-RELOAD");
    private static final Pattern REGEX_CAN_SKIP_DATE_RANGES = compileBooleanAttrPattern("CAN-SKIP-DATERANGES");
    private static final Pattern REGEX_CAN_SKIP_UNTIL = Pattern.compile("CAN-SKIP-UNTIL=([\\d\\.]+)\\b");
    private static final Pattern REGEX_CHANNELS = Pattern.compile("CHANNELS=\"(.+?)\"");
    private static final Pattern REGEX_CHARACTERISTICS = Pattern.compile("CHARACTERISTICS=\"(.+?)\"");
    private static final Pattern REGEX_CLOSED_CAPTIONS = Pattern.compile("CLOSED-CAPTIONS=\"(.+?)\"");
    private static final Pattern REGEX_CODECS = Pattern.compile("CODECS=\"(.+?)\"");
    private static final Pattern REGEX_DEFAULT = compileBooleanAttrPattern("DEFAULT");
    private static final Pattern REGEX_FORCED = compileBooleanAttrPattern("FORCED");
    private static final Pattern REGEX_FRAME_RATE = Pattern.compile("FRAME-RATE=([\\d\\.]+)\\b");
    private static final Pattern REGEX_GAP = compileBooleanAttrPattern("GAP");
    private static final Pattern REGEX_GROUP_ID = Pattern.compile("GROUP-ID=\"(.+?)\"");
    private static final Pattern REGEX_HOLD_BACK = Pattern.compile("[:|,]HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_IMPORT = Pattern.compile("IMPORT=\"(.+?)\"");
    private static final Pattern REGEX_INDEPENDENT = compileBooleanAttrPattern("INDEPENDENT");
    private static final Pattern REGEX_INSTREAM_ID = Pattern.compile("INSTREAM-ID=\"((?:CC|SERVICE)\\d+)\"");
    private static final Pattern REGEX_IV = Pattern.compile("IV=([^,.*]+)");
    private static final Pattern REGEX_KEYFORMAT = Pattern.compile("KEYFORMAT=\"(.+?)\"");
    private static final Pattern REGEX_KEYFORMATVERSIONS = Pattern.compile("KEYFORMATVERSIONS=\"(.+?)\"");
    private static final Pattern REGEX_LANGUAGE = Pattern.compile("LANGUAGE=\"(.+?)\"");
    private static final Pattern REGEX_LAST_MSN = Pattern.compile("LAST-MSN=(\\d+)\\b");
    private static final Pattern REGEX_LAST_PART = Pattern.compile("LAST-PART=(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_DURATION = Pattern.compile("#EXTINF:([\\d\\.]+)\\b");
    private static final Pattern REGEX_MEDIA_SEQUENCE = Pattern.compile("#EXT-X-MEDIA-SEQUENCE:(\\d+)\\b");
    private static final Pattern REGEX_MEDIA_TITLE = Pattern.compile("#EXTINF:[\\d\\.]+\\b,(.+)");
    private static final Pattern REGEX_METHOD = Pattern.compile("METHOD=(NONE|AES-128|SAMPLE-AES|SAMPLE-AES-CENC|SAMPLE-AES-CTR)\\s*(?:,|$)");
    private static final Pattern REGEX_NAME = Pattern.compile("NAME=\"(.+?)\"");
    private static final Pattern REGEX_PART_HOLD_BACK = Pattern.compile("PART-HOLD-BACK=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PART_TARGET_DURATION = Pattern.compile("PART-TARGET=([\\d\\.]+)\\b");
    private static final Pattern REGEX_PLAYLIST_TYPE = Pattern.compile("#EXT-X-PLAYLIST-TYPE:(.+)\\b");
    private static final Pattern REGEX_PRECISE = compileBooleanAttrPattern("PRECISE");
    private static final Pattern REGEX_PRELOAD_HINT_TYPE = Pattern.compile("TYPE=(PART|MAP)");
    private static final Pattern REGEX_RESOLUTION = Pattern.compile("RESOLUTION=(\\d+x\\d+)");
    private static final Pattern REGEX_SKIPPED_SEGMENTS = Pattern.compile("SKIPPED-SEGMENTS=(\\d+)\\b");
    private static final Pattern REGEX_SUBTITLES = Pattern.compile("SUBTITLES=\"(.+?)\"");
    private static final Pattern REGEX_TARGET_DURATION = Pattern.compile("#EXT-X-TARGETDURATION:(\\d+)\\b");
    private static final Pattern REGEX_TIME_OFFSET = Pattern.compile("TIME-OFFSET=(-?[\\d\\.]+)\\b");
    private static final Pattern REGEX_TYPE = Pattern.compile("TYPE=(AUDIO|VIDEO|SUBTITLES|CLOSED-CAPTIONS)");
    private static final Pattern REGEX_URI = Pattern.compile("URI=\"(.+?)\"");
    private static final Pattern REGEX_VALUE = Pattern.compile("VALUE=\"(.+?)\"");
    private static final Pattern REGEX_VARIABLE_REFERENCE = Pattern.compile("\\{\\$([a-zA-Z0-9\\-_]+)\\}");
    private static final Pattern REGEX_VERSION = Pattern.compile("#EXT-X-VERSION:(\\d+)\\b");
    private static final Pattern REGEX_VIDEO = Pattern.compile("VIDEO=\"(.+?)\"");
    private static final String TAG_BYTERANGE = "#EXT-X-BYTERANGE";
    private static final String TAG_DEFINE = "#EXT-X-DEFINE";
    private static final String TAG_DISCONTINUITY = "#EXT-X-DISCONTINUITY";
    private static final String TAG_DISCONTINUITY_SEQUENCE = "#EXT-X-DISCONTINUITY-SEQUENCE";
    private static final String TAG_ENDLIST = "#EXT-X-ENDLIST";
    private static final String TAG_GAP = "#EXT-X-GAP";
    private static final String TAG_IFRAME = "#EXT-X-I-FRAMES-ONLY";
    private static final String TAG_INDEPENDENT_SEGMENTS = "#EXT-X-INDEPENDENT-SEGMENTS";
    private static final String TAG_INIT_SEGMENT = "#EXT-X-MAP";
    private static final String TAG_I_FRAME_STREAM_INF = "#EXT-X-I-FRAME-STREAM-INF";
    private static final String TAG_KEY = "#EXT-X-KEY";
    private static final String TAG_MEDIA = "#EXT-X-MEDIA";
    private static final String TAG_MEDIA_DURATION = "#EXTINF";
    private static final String TAG_MEDIA_SEQUENCE = "#EXT-X-MEDIA-SEQUENCE";
    private static final String TAG_PART = "#EXT-X-PART";
    private static final String TAG_PART_INF = "#EXT-X-PART-INF";
    private static final String TAG_PLAYLIST_TYPE = "#EXT-X-PLAYLIST-TYPE";
    private static final String TAG_PREFIX = "#EXT";
    private static final String TAG_PRELOAD_HINT = "#EXT-X-PRELOAD-HINT";
    private static final String TAG_PROGRAM_DATE_TIME = "#EXT-X-PROGRAM-DATE-TIME";
    private static final String TAG_RENDITION_REPORT = "#EXT-X-RENDITION-REPORT";
    private static final String TAG_SERVER_CONTROL = "#EXT-X-SERVER-CONTROL";
    private static final String TAG_SESSION_KEY = "#EXT-X-SESSION-KEY";
    private static final String TAG_SKIP = "#EXT-X-SKIP";
    private static final String TAG_START = "#EXT-X-START";
    private static final String TAG_STREAM_INF = "#EXT-X-STREAM-INF";
    private static final String TAG_TARGET_DURATION = "#EXT-X-TARGETDURATION";
    private static final String TAG_VERSION = "#EXT-X-VERSION";
    private static final String TYPE_AUDIO = "AUDIO";
    private static final String TYPE_CLOSED_CAPTIONS = "CLOSED-CAPTIONS";
    private static final String TYPE_MAP = "MAP";
    private static final String TYPE_PART = "PART";
    private static final String TYPE_SUBTITLES = "SUBTITLES";
    private static final String TYPE_VIDEO = "VIDEO";
    private final HlsMultivariantPlaylist multivariantPlaylist;
    private final HlsMediaPlaylist previousMediaPlaylist;

    public static final class DeltaUpdateException extends IOException {
    }

    public HlsPlaylistParser() {
        this(HlsMultivariantPlaylist.EMPTY, (HlsMediaPlaylist) null);
    }

    public HlsPlaylistParser(HlsMultivariantPlaylist hlsMultivariantPlaylist, HlsMediaPlaylist hlsMediaPlaylist) {
        this.multivariantPlaylist = hlsMultivariantPlaylist;
        this.previousMediaPlaylist = hlsMediaPlaylist;
    }

    public HlsPlaylist parse(Uri uri, InputStream inputStream) throws IOException {
        String trim;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        ArrayDeque arrayDeque = new ArrayDeque();
        try {
            if (checkPlaylistHeader(bufferedReader)) {
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        trim = readLine.trim();
                        if (!trim.isEmpty()) {
                            if (!trim.startsWith(TAG_STREAM_INF)) {
                                if (trim.startsWith(TAG_TARGET_DURATION) || trim.startsWith(TAG_MEDIA_SEQUENCE) || trim.startsWith(TAG_MEDIA_DURATION) || trim.startsWith(TAG_KEY) || trim.startsWith(TAG_BYTERANGE) || trim.equals(TAG_DISCONTINUITY) || trim.equals(TAG_DISCONTINUITY_SEQUENCE)) {
                                    break;
                                } else if (trim.equals(TAG_ENDLIST)) {
                                    break;
                                } else {
                                    arrayDeque.add(trim);
                                }
                            } else {
                                arrayDeque.add(trim);
                                HlsMultivariantPlaylist parseMultivariantPlaylist = parseMultivariantPlaylist(new LineIterator(arrayDeque, bufferedReader), uri.toString());
                                Util.closeQuietly(bufferedReader);
                                return parseMultivariantPlaylist;
                            }
                        }
                    } else {
                        Util.closeQuietly(bufferedReader);
                        throw ParserException.createForMalformedManifest("Failed to parse the playlist, could not identify any tags.", (Throwable) null);
                    }
                }
                arrayDeque.add(trim);
                return parseMediaPlaylist(this.multivariantPlaylist, this.previousMediaPlaylist, new LineIterator(arrayDeque, bufferedReader), uri.toString());
            }
            throw ParserException.createForMalformedManifest("Input does not start with the #EXTM3U header.", (Throwable) null);
        } finally {
            Util.closeQuietly(bufferedReader);
        }
    }

    private static boolean checkPlaylistHeader(BufferedReader bufferedReader) throws IOException {
        int read = bufferedReader.read();
        if (read == 239) {
            if (bufferedReader.read() != 187 || bufferedReader.read() != 191) {
                return false;
            }
            read = bufferedReader.read();
        }
        int skipIgnorableWhitespace = skipIgnorableWhitespace(bufferedReader, true, read);
        for (int i = 0; i < 7; i++) {
            if (skipIgnorableWhitespace != PLAYLIST_HEADER.charAt(i)) {
                return false;
            }
            skipIgnorableWhitespace = bufferedReader.read();
        }
        return Util.isLinebreak(skipIgnorableWhitespace(bufferedReader, false, skipIgnorableWhitespace));
    }

    private static int skipIgnorableWhitespace(BufferedReader bufferedReader, boolean z, int i) throws IOException {
        while (i != -1 && Character.isWhitespace(i) && (z || !Util.isLinebreak(i))) {
            i = bufferedReader.read();
        }
        return i;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x045c, code lost:
        r6 = r31;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x04a4, code lost:
        r0 = r0 + 1;
        r31 = r6;
        r32 = r9;
        r33 = r14;
        r15 = r20;
        r9 = r21;
        r8 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist parseMultivariantPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r36, java.lang.String r37) throws java.io.IOException {
        /*
            r1 = r37
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.util.HashMap r11 = new java.util.HashMap
            r11.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r10 = 0
            r13 = 0
        L_0x0036:
            boolean r14 = r36.hasNext()
            java.lang.String r15 = "application/x-mpegURL"
            if (r14 == 0) goto L_0x021b
            java.lang.String r14 = r36.next()
            java.lang.String r9 = "#EXT"
            boolean r9 = r14.startsWith(r9)
            if (r9 == 0) goto L_0x004d
            r8.add(r14)
        L_0x004d:
            java.lang.String r9 = "#EXT-X-I-FRAME-STREAM-INF"
            boolean r9 = r14.startsWith(r9)
            r19 = r10
            java.lang.String r10 = "#EXT-X-DEFINE"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x006d
            java.util.regex.Pattern r9 = REGEX_NAME
            java.lang.String r9 = parseStringAttr(r14, r9, r11)
            java.util.regex.Pattern r10 = REGEX_VALUE
            java.lang.String r10 = parseStringAttr(r14, r10, r11)
            r11.put(r9, r10)
            goto L_0x00ce
        L_0x006d:
            java.lang.String r10 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r10 = r14.equals(r10)
            if (r10 == 0) goto L_0x0087
            r1 = r0
            r34 = r3
            r33 = r4
            r32 = r5
            r31 = r6
            r29 = r7
            r30 = r8
            r28 = r12
            r10 = 1
            goto L_0x0200
        L_0x0087:
            java.lang.String r10 = "#EXT-X-MEDIA"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x0093
            r3.add(r14)
            goto L_0x00ce
        L_0x0093:
            java.lang.String r10 = "#EXT-X-SESSION-KEY"
            boolean r10 = r14.startsWith(r10)
            if (r10 == 0) goto L_0x00c3
            java.util.regex.Pattern r9 = REGEX_KEYFORMAT
            java.lang.String r10 = "identity"
            java.lang.String r9 = parseOptionalStringAttr(r14, r9, r10, r11)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r9 = parseDrmSchemeData(r14, r9, r11)
            if (r9 == 0) goto L_0x00ce
            java.util.regex.Pattern r10 = REGEX_METHOD
            java.lang.String r10 = parseStringAttr(r14, r10, r11)
            java.lang.String r10 = parseEncryptionScheme(r10)
            com.google.android.exoplayer2.drm.DrmInitData r14 = new com.google.android.exoplayer2.drm.DrmInitData
            r15 = 1
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r15 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r15]
            r16 = 0
            r15[r16] = r9
            r14.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r15)
            r12.add(r14)
            goto L_0x00ce
        L_0x00c3:
            java.lang.String r10 = "#EXT-X-STREAM-INF"
            boolean r10 = r14.startsWith(r10)
            if (r10 != 0) goto L_0x00e1
            if (r9 == 0) goto L_0x00ce
            goto L_0x00e1
        L_0x00ce:
            r1 = r0
            r34 = r3
            r33 = r4
            r32 = r5
            r31 = r6
            r29 = r7
            r30 = r8
            r28 = r12
            r10 = r19
            goto L_0x0200
        L_0x00e1:
            java.lang.String r10 = "CLOSED-CAPTIONS=NONE"
            boolean r10 = r14.contains(r10)
            r13 = r13 | r10
            if (r9 == 0) goto L_0x00ef
            r10 = 16384(0x4000, float:2.2959E-41)
            r20 = r13
            goto L_0x00f2
        L_0x00ef:
            r20 = r13
            r10 = 0
        L_0x00f2:
            java.util.regex.Pattern r13 = REGEX_BANDWIDTH
            int r13 = parseIntAttr(r14, r13)
            r28 = r12
            java.util.regex.Pattern r12 = REGEX_AVERAGE_BANDWIDTH
            r29 = r7
            r7 = -1
            int r12 = parseOptionalIntAttr(r14, r12, r7)
            java.util.regex.Pattern r7 = REGEX_CODECS
            java.lang.String r7 = parseOptionalStringAttr(r14, r7, r11)
            r30 = r8
            java.util.regex.Pattern r8 = REGEX_RESOLUTION
            java.lang.String r8 = parseOptionalStringAttr(r14, r8, r11)
            if (r8 == 0) goto L_0x013b
            r31 = r6
            java.lang.String r6 = "x"
            java.lang.String[] r6 = com.google.android.exoplayer2.util.Util.split(r8, r6)
            r8 = 0
            r21 = r6[r8]
            int r8 = java.lang.Integer.parseInt(r21)
            r18 = 1
            r6 = r6[r18]
            int r6 = java.lang.Integer.parseInt(r6)
            if (r8 <= 0) goto L_0x0132
            if (r6 > 0) goto L_0x012f
            goto L_0x0132
        L_0x012f:
            r17 = r8
            goto L_0x0135
        L_0x0132:
            r6 = -1
            r17 = -1
        L_0x0135:
            r32 = r5
            r8 = r6
            r6 = r17
            goto L_0x0141
        L_0x013b:
            r31 = r6
            r32 = r5
            r6 = -1
            r8 = -1
        L_0x0141:
            java.util.regex.Pattern r5 = REGEX_FRAME_RATE
            java.lang.String r5 = parseOptionalStringAttr(r14, r5, r11)
            if (r5 == 0) goto L_0x014e
            float r5 = java.lang.Float.parseFloat(r5)
            goto L_0x0150
        L_0x014e:
            r5 = -1082130432(0xffffffffbf800000, float:-1.0)
        L_0x0150:
            r33 = r4
            java.util.regex.Pattern r4 = REGEX_VIDEO
            java.lang.String r4 = parseOptionalStringAttr(r14, r4, r11)
            r34 = r3
            java.util.regex.Pattern r3 = REGEX_AUDIO
            java.lang.String r3 = parseOptionalStringAttr(r14, r3, r11)
            r35 = r0
            java.util.regex.Pattern r0 = REGEX_SUBTITLES
            java.lang.String r0 = parseOptionalStringAttr(r14, r0, r11)
            r17 = r0
            java.util.regex.Pattern r0 = REGEX_CLOSED_CAPTIONS
            java.lang.String r0 = parseOptionalStringAttr(r14, r0, r11)
            if (r9 == 0) goto L_0x017d
            java.util.regex.Pattern r9 = REGEX_URI
            java.lang.String r9 = parseStringAttr(r14, r9, r11)
            android.net.Uri r9 = com.google.android.exoplayer2.util.UriUtil.resolveToUri(r1, r9)
            goto L_0x018f
        L_0x017d:
            boolean r9 = r36.hasNext()
            if (r9 == 0) goto L_0x0213
            java.lang.String r9 = r36.next()
            java.lang.String r9 = replaceVariableReferences(r9, r11)
            android.net.Uri r9 = com.google.android.exoplayer2.util.UriUtil.resolveToUri(r1, r9)
        L_0x018f:
            com.google.android.exoplayer2.Format$Builder r14 = new com.google.android.exoplayer2.Format$Builder
            r14.<init>()
            int r1 = r2.size()
            com.google.android.exoplayer2.Format$Builder r1 = r14.setId((int) r1)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setContainerMimeType(r15)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setCodecs(r7)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setAverageBitrate(r12)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setPeakBitrate(r13)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setWidth(r6)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setHeight(r8)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setFrameRate(r5)
            com.google.android.exoplayer2.Format$Builder r1 = r1.setRoleFlags(r10)
            com.google.android.exoplayer2.Format r23 = r1.build()
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r1 = new com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant
            r21 = r1
            r22 = r9
            r24 = r4
            r25 = r3
            r26 = r17
            r27 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27)
            r2.add(r1)
            r1 = r35
            java.lang.Object r5 = r1.get(r9)
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            if (r5 != 0) goto L_0x01e6
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r1.put(r9, r5)
        L_0x01e6:
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo r6 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry$VariantInfo
            r21 = r6
            r22 = r12
            r23 = r13
            r24 = r4
            r25 = r3
            r26 = r17
            r27 = r0
            r21.<init>(r22, r23, r24, r25, r26, r27)
            r5.add(r6)
            r10 = r19
            r13 = r20
        L_0x0200:
            r0 = r1
            r12 = r28
            r7 = r29
            r8 = r30
            r6 = r31
            r5 = r32
            r4 = r33
            r3 = r34
            r1 = r37
            goto L_0x0036
        L_0x0213:
            java.lang.String r0 = "#EXT-X-STREAM-INF must be followed by another line"
            r1 = 0
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedManifest(r0, r1)
            throw r0
        L_0x021b:
            r1 = r0
            r34 = r3
            r33 = r4
            r32 = r5
            r31 = r6
            r29 = r7
            r30 = r8
            r19 = r10
            r28 = r12
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.HashSet r0 = new java.util.HashSet
            r0.<init>()
            r4 = 0
        L_0x0237:
            int r5 = r2.size()
            if (r4 >= r5) goto L_0x0290
            java.lang.Object r5 = r2.get(r4)
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r5 = (com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist.Variant) r5
            android.net.Uri r6 = r5.url
            boolean r6 = r0.add(r6)
            if (r6 == 0) goto L_0x028c
            com.google.android.exoplayer2.Format r6 = r5.format
            com.google.android.exoplayer2.metadata.Metadata r6 = r6.metadata
            if (r6 != 0) goto L_0x0253
            r6 = 1
            goto L_0x0254
        L_0x0253:
            r6 = 0
        L_0x0254:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r6 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            android.net.Uri r7 = r5.url
            java.lang.Object r7 = r1.get(r7)
            java.util.ArrayList r7 = (java.util.ArrayList) r7
            java.lang.Object r7 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r7)
            java.util.List r7 = (java.util.List) r7
            r8 = 0
            r6.<init>(r8, r8, r7)
            com.google.android.exoplayer2.metadata.Metadata r7 = new com.google.android.exoplayer2.metadata.Metadata
            r9 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r10 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r9]
            r9 = 0
            r10[r9] = r6
            r7.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r10)
            com.google.android.exoplayer2.Format r6 = r5.format
            com.google.android.exoplayer2.Format$Builder r6 = r6.buildUpon()
            com.google.android.exoplayer2.Format$Builder r6 = r6.setMetadata(r7)
            com.google.android.exoplayer2.Format r6 = r6.build()
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r5 = r5.copyWithFormat(r6)
            r3.add(r5)
            goto L_0x028d
        L_0x028c:
            r8 = 0
        L_0x028d:
            int r4 = r4 + 1
            goto L_0x0237
        L_0x0290:
            r8 = 0
            r1 = r8
            r9 = r1
            r0 = 0
        L_0x0294:
            int r4 = r34.size()
            if (r0 >= r4) goto L_0x04b3
            r4 = r34
            java.lang.Object r5 = r4.get(r0)
            java.lang.String r5 = (java.lang.String) r5
            java.util.regex.Pattern r6 = REGEX_GROUP_ID
            java.lang.String r6 = parseStringAttr(r5, r6, r11)
            java.util.regex.Pattern r7 = REGEX_NAME
            java.lang.String r7 = parseStringAttr(r5, r7, r11)
            com.google.android.exoplayer2.Format$Builder r10 = new com.google.android.exoplayer2.Format$Builder
            r10.<init>()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            r12.append(r6)
            java.lang.String r14 = ":"
            r12.append(r14)
            r12.append(r7)
            java.lang.String r12 = r12.toString()
            com.google.android.exoplayer2.Format$Builder r10 = r10.setId((java.lang.String) r12)
            com.google.android.exoplayer2.Format$Builder r10 = r10.setLabel(r7)
            com.google.android.exoplayer2.Format$Builder r10 = r10.setContainerMimeType(r15)
            int r12 = parseSelectionFlags(r5)
            com.google.android.exoplayer2.Format$Builder r10 = r10.setSelectionFlags(r12)
            int r12 = parseRoleFlags(r5, r11)
            com.google.android.exoplayer2.Format$Builder r10 = r10.setRoleFlags(r12)
            java.util.regex.Pattern r12 = REGEX_LANGUAGE
            java.lang.String r12 = parseOptionalStringAttr(r5, r12, r11)
            com.google.android.exoplayer2.Format$Builder r10 = r10.setLanguage(r12)
            java.util.regex.Pattern r12 = REGEX_URI
            java.lang.String r12 = parseOptionalStringAttr(r5, r12, r11)
            r14 = r37
            if (r12 != 0) goto L_0x02f9
            r12 = r8
            goto L_0x02fd
        L_0x02f9:
            android.net.Uri r12 = com.google.android.exoplayer2.util.UriUtil.resolveToUri(r14, r12)
        L_0x02fd:
            com.google.android.exoplayer2.metadata.Metadata r8 = new com.google.android.exoplayer2.metadata.Metadata
            r34 = r4
            r4 = 1
            com.google.android.exoplayer2.metadata.Metadata$Entry[] r14 = new com.google.android.exoplayer2.metadata.Metadata.Entry[r4]
            com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry r4 = new com.google.android.exoplayer2.source.hls.HlsTrackMetadataEntry
            r20 = r15
            java.util.List r15 = java.util.Collections.emptyList()
            r4.<init>(r6, r7, r15)
            r15 = 0
            r14[r15] = r4
            r8.<init>((com.google.android.exoplayer2.metadata.Metadata.Entry[]) r14)
            java.util.regex.Pattern r4 = REGEX_TYPE
            java.lang.String r4 = parseStringAttr(r5, r4, r11)
            r4.hashCode()
            int r14 = r4.hashCode()
            r15 = 2
            switch(r14) {
                case -959297733: goto L_0x0349;
                case -333210994: goto L_0x033e;
                case 62628790: goto L_0x0333;
                case 81665115: goto L_0x0328;
                default: goto L_0x0326;
            }
        L_0x0326:
            r4 = -1
            goto L_0x0353
        L_0x0328:
            java.lang.String r14 = "VIDEO"
            boolean r4 = r4.equals(r14)
            if (r4 != 0) goto L_0x0331
            goto L_0x0326
        L_0x0331:
            r4 = 3
            goto L_0x0353
        L_0x0333:
            java.lang.String r14 = "AUDIO"
            boolean r4 = r4.equals(r14)
            if (r4 != 0) goto L_0x033c
            goto L_0x0326
        L_0x033c:
            r4 = r15
            goto L_0x0353
        L_0x033e:
            java.lang.String r14 = "CLOSED-CAPTIONS"
            boolean r4 = r4.equals(r14)
            if (r4 != 0) goto L_0x0347
            goto L_0x0326
        L_0x0347:
            r4 = 1
            goto L_0x0353
        L_0x0349:
            java.lang.String r14 = "SUBTITLES"
            boolean r4 = r4.equals(r14)
            if (r4 != 0) goto L_0x0352
            goto L_0x0326
        L_0x0352:
            r4 = 0
        L_0x0353:
            switch(r4) {
                case 0: goto L_0x045f;
                case 1: goto L_0x041b;
                case 2: goto L_0x03a8;
                case 3: goto L_0x0362;
                default: goto L_0x0356;
            }
        L_0x0356:
            r21 = r9
            r6 = r31
            r9 = r32
            r14 = r33
        L_0x035e:
            r16 = 0
            goto L_0x04a4
        L_0x0362:
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r4 = getVariantWithVideoGroup(r2, r6)
            if (r4 == 0) goto L_0x038d
            com.google.android.exoplayer2.Format r4 = r4.format
            java.lang.String r5 = r4.codecs
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r5, r15)
            com.google.android.exoplayer2.Format$Builder r14 = r10.setCodecs(r5)
            java.lang.String r5 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r5)
            com.google.android.exoplayer2.Format$Builder r5 = r14.setSampleMimeType(r5)
            int r14 = r4.width
            com.google.android.exoplayer2.Format$Builder r5 = r5.setWidth(r14)
            int r14 = r4.height
            com.google.android.exoplayer2.Format$Builder r5 = r5.setHeight(r14)
            float r4 = r4.frameRate
            r5.setFrameRate(r4)
        L_0x038d:
            if (r12 != 0) goto L_0x0390
            goto L_0x0356
        L_0x0390:
            r10.setMetadata(r8)
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition r4 = new com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition
            com.google.android.exoplayer2.Format r5 = r10.build()
            r4.<init>(r12, r5, r6, r7)
            r14 = r33
            r14.add(r4)
            r21 = r9
            r6 = r31
            r9 = r32
            goto L_0x035e
        L_0x03a8:
            r14 = r33
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r4 = getVariantWithAudioGroup(r2, r6)
            if (r4 == 0) goto L_0x03c3
            com.google.android.exoplayer2.Format r15 = r4.format
            java.lang.String r15 = r15.codecs
            r21 = r9
            r9 = 1
            java.lang.String r15 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r15, r9)
            r10.setCodecs(r15)
            java.lang.String r15 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r15)
            goto L_0x03c6
        L_0x03c3:
            r21 = r9
            r15 = 0
        L_0x03c6:
            java.util.regex.Pattern r9 = REGEX_CHANNELS
            java.lang.String r5 = parseOptionalStringAttr(r5, r9, r11)
            if (r5 == 0) goto L_0x03f7
            java.lang.String r9 = "/"
            java.lang.String[] r9 = com.google.android.exoplayer2.util.Util.splitAtFirst(r5, r9)
            r16 = 0
            r9 = r9[r16]
            int r9 = java.lang.Integer.parseInt(r9)
            r10.setChannelCount(r9)
            java.lang.String r9 = "audio/eac3"
            boolean r9 = r9.equals(r15)
            if (r9 == 0) goto L_0x03f9
            java.lang.String r9 = "/JOC"
            boolean r5 = r5.endsWith(r9)
            if (r5 == 0) goto L_0x03f9
            java.lang.String r5 = "ec+3"
            r10.setCodecs(r5)
            java.lang.String r15 = "audio/eac3-joc"
            goto L_0x03f9
        L_0x03f7:
            r16 = 0
        L_0x03f9:
            r10.setSampleMimeType(r15)
            if (r12 == 0) goto L_0x0410
            r10.setMetadata(r8)
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition r4 = new com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition
            com.google.android.exoplayer2.Format r5 = r10.build()
            r4.<init>(r12, r5, r6, r7)
            r9 = r32
            r9.add(r4)
            goto L_0x045c
        L_0x0410:
            r9 = r32
            if (r4 == 0) goto L_0x045c
            com.google.android.exoplayer2.Format r4 = r10.build()
            r21 = r4
            goto L_0x045c
        L_0x041b:
            r21 = r9
            r9 = r32
            r14 = r33
            r16 = 0
            java.util.regex.Pattern r4 = REGEX_INSTREAM_ID
            java.lang.String r4 = parseStringAttr(r5, r4, r11)
            java.lang.String r5 = "CC"
            boolean r5 = r4.startsWith(r5)
            if (r5 == 0) goto L_0x043c
            java.lang.String r4 = r4.substring(r15)
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r5 = "application/cea-608"
            goto L_0x0447
        L_0x043c:
            r5 = 7
            java.lang.String r4 = r4.substring(r5)
            int r4 = java.lang.Integer.parseInt(r4)
            java.lang.String r5 = "application/cea-708"
        L_0x0447:
            if (r1 != 0) goto L_0x044e
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x044e:
            com.google.android.exoplayer2.Format$Builder r5 = r10.setSampleMimeType(r5)
            r5.setAccessibilityChannel(r4)
            com.google.android.exoplayer2.Format r4 = r10.build()
            r1.add(r4)
        L_0x045c:
            r6 = r31
            goto L_0x04a4
        L_0x045f:
            r21 = r9
            r9 = r32
            r14 = r33
            r16 = 0
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Variant r4 = getVariantWithSubtitleGroup(r2, r6)
            if (r4 == 0) goto L_0x047e
            com.google.android.exoplayer2.Format r4 = r4.format
            java.lang.String r4 = r4.codecs
            r5 = 3
            java.lang.String r4 = com.google.android.exoplayer2.util.Util.getCodecsOfType(r4, r5)
            r10.setCodecs(r4)
            java.lang.String r4 = com.google.android.exoplayer2.util.MimeTypes.getMediaMimeType(r4)
            goto L_0x047f
        L_0x047e:
            r4 = 0
        L_0x047f:
            if (r4 != 0) goto L_0x0483
            java.lang.String r4 = "text/vtt"
        L_0x0483:
            com.google.android.exoplayer2.Format$Builder r4 = r10.setSampleMimeType(r4)
            r4.setMetadata(r8)
            if (r12 == 0) goto L_0x049b
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition r4 = new com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist$Rendition
            com.google.android.exoplayer2.Format r5 = r10.build()
            r4.<init>(r12, r5, r6, r7)
            r6 = r31
            r6.add(r4)
            goto L_0x04a4
        L_0x049b:
            r6 = r31
            java.lang.String r4 = "HlsPlaylistParser"
            java.lang.String r5 = "EXT-X-MEDIA tag with missing mandatory URI attribute: skipping"
            com.google.android.exoplayer2.util.Log.w(r4, r5)
        L_0x04a4:
            int r0 = r0 + 1
            r31 = r6
            r32 = r9
            r33 = r14
            r15 = r20
            r9 = r21
            r8 = 0
            goto L_0x0294
        L_0x04b3:
            r21 = r9
            r6 = r31
            r9 = r32
            r14 = r33
            if (r13 == 0) goto L_0x04c3
            java.util.List r0 = java.util.Collections.emptyList()
            r10 = r0
            goto L_0x04c4
        L_0x04c3:
            r10 = r1
        L_0x04c4:
            com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist r13 = new com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist
            r0 = r13
            r1 = r37
            r2 = r30
            r4 = r14
            r5 = r9
            r7 = r29
            r8 = r21
            r9 = r10
            r10 = r19
            r12 = r28
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseMultivariantPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist");
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithAudioGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.audioGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithVideoGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.videoGroupId)) {
                return variant;
            }
        }
        return null;
    }

    private static HlsMultivariantPlaylist.Variant getVariantWithSubtitleGroup(ArrayList<HlsMultivariantPlaylist.Variant> arrayList, String str) {
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMultivariantPlaylist.Variant variant = arrayList.get(i);
            if (str.equals(variant.subtitleGroupId)) {
                return variant;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v0, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r65v0, resolved type: com.google.android.exoplayer2.drm.DrmInitData} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r65v1, resolved type: com.google.android.exoplayer2.drm.DrmInitData} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v12, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v13, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r50v15, resolved type: java.lang.Object} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v1, types: [boolean] */
    /* JADX WARNING: type inference failed for: r13v4 */
    /* JADX WARNING: type inference failed for: r10v50 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist parseMediaPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist r92, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r93, com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.LineIterator r94, java.lang.String r95) throws java.io.IOException {
        /*
            r0 = r92
            r1 = r93
            boolean r2 = r0.hasIndependentSegments
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$ServerControl r7 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$ServerControl
            r22 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r24 = 0
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r19 = 0
            r20 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r16 = r7
            r16.<init>(r17, r19, r20, r22, r24)
            java.util.TreeMap r9 = new java.util.TreeMap
            r9.<init>()
            r10 = 0
            java.lang.String r13 = ""
            r20 = r13
            r35 = r2
            r56 = r7
            r2 = r10
            r23 = r2
            r26 = r23
            r27 = r26
            r36 = r27
            r54 = r36
            r79 = r54
            r84 = r79
            r41 = r20
            r14 = 0
            r21 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r24 = 0
            r28 = 0
            r30 = 1
            r31 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r33 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r37 = 0
            r39 = 0
            r50 = 0
            r51 = 0
            r75 = -1
            r77 = 0
            r78 = 0
            r80 = 0
            r82 = 0
            r85 = 0
            r86 = 0
            r88 = 0
            r7 = r5
            r5 = 0
        L_0x008d:
            boolean r42 = r94.hasNext()
            if (r42 == 0) goto L_0x0661
            java.lang.String r13 = r94.next()
            java.lang.String r12 = "#EXT"
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x00a2
            r8.add(r13)
        L_0x00a2:
            java.lang.String r12 = "#EXT-X-PLAYLIST-TYPE"
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x00c4
            java.util.regex.Pattern r12 = REGEX_PLAYLIST_TYPE
            java.lang.String r12 = parseStringAttr(r13, r12, r3)
            java.lang.String r13 = "VOD"
            boolean r13 = r13.equals(r12)
            if (r13 == 0) goto L_0x00ba
            r2 = 1
            goto L_0x008d
        L_0x00ba:
            java.lang.String r13 = "EVENT"
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x008d
            r2 = 2
            goto L_0x008d
        L_0x00c4:
            java.lang.String r12 = "#EXT-X-I-FRAMES-ONLY"
            boolean r12 = r13.equals(r12)
            if (r12 == 0) goto L_0x00cf
            r84 = 1
            goto L_0x008d
        L_0x00cf:
            java.lang.String r12 = "#EXT-X-START"
            boolean r12 = r13.startsWith(r12)
            r43 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            if (r12 == 0) goto L_0x00ee
            java.util.regex.Pattern r12 = REGEX_TIME_OFFSET
            double r21 = parseDoubleAttr(r13, r12)
            double r11 = r21 * r43
            long r11 = (long) r11
            r21 = r11
            java.util.regex.Pattern r11 = REGEX_PRECISE
            boolean r23 = parseOptionalBooleanAttribute(r13, r11, r10)
            goto L_0x008d
        L_0x00ee:
            java.lang.String r11 = "#EXT-X-SERVER-CONTROL"
            boolean r11 = r13.startsWith(r11)
            if (r11 == 0) goto L_0x00fb
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$ServerControl r56 = parseServerControl(r13)
            goto L_0x008d
        L_0x00fb:
            java.lang.String r11 = "#EXT-X-PART-INF"
            boolean r11 = r13.startsWith(r11)
            if (r11 == 0) goto L_0x0110
            java.util.regex.Pattern r11 = REGEX_PART_TARGET_DURATION
            double r11 = parseDoubleAttr(r13, r11)
            double r11 = r11 * r43
            long r11 = (long) r11
            r33 = r11
            goto L_0x008d
        L_0x0110:
            java.lang.String r11 = "#EXT-X-MAP"
            boolean r11 = r13.startsWith(r11)
            java.lang.String r12 = "@"
            if (r11 == 0) goto L_0x016f
            java.util.regex.Pattern r11 = REGEX_URI
            java.lang.String r43 = parseStringAttr(r13, r11, r3)
            java.util.regex.Pattern r11 = REGEX_ATTR_BYTERANGE
            java.lang.String r11 = parseOptionalStringAttr(r13, r11, r3)
            if (r11 == 0) goto L_0x013e
            java.lang.String[] r11 = com.google.android.exoplayer2.util.Util.split(r11, r12)
            r12 = r11[r10]
            long r75 = java.lang.Long.parseLong(r12)
            int r12 = r11.length
            r13 = 1
            if (r12 <= r13) goto L_0x013e
            r11 = r11[r13]
            long r11 = java.lang.Long.parseLong(r11)
            r39 = r11
        L_0x013e:
            r11 = -1
            int r13 = (r75 > r11 ? 1 : (r75 == r11 ? 0 : -1))
            if (r13 != 0) goto L_0x0146
            r39 = 0
        L_0x0146:
            r11 = r77
            if (r14 == 0) goto L_0x0155
            if (r11 == 0) goto L_0x014d
            goto L_0x0155
        L_0x014d:
            java.lang.String r0 = "The encryption IV attribute must be present when an initialization segment is encrypted with METHOD=AES-128."
            r12 = 0
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedManifest(r0, r12)
            throw r0
        L_0x0155:
            r12 = 0
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r85 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment
            r42 = r85
            r44 = r39
            r46 = r75
            r48 = r14
            r49 = r11
            r42.<init>(r43, r44, r46, r48, r49)
            if (r13 == 0) goto L_0x0169
            long r39 = r39 + r75
        L_0x0169:
            r77 = r11
            r75 = -1
            goto L_0x008d
        L_0x016f:
            r11 = r77
            r77 = 0
            java.lang.String r10 = "#EXT-X-TARGETDURATION"
            boolean r10 = r13.startsWith(r10)
            if (r10 == 0) goto L_0x018c
            java.util.regex.Pattern r10 = REGEX_TARGET_DURATION
            int r10 = parseIntAttr(r13, r10)
            long r12 = (long) r10
            r31 = 1000000(0xf4240, double:4.940656E-318)
            long r31 = r31 * r12
        L_0x0187:
            r77 = r11
        L_0x0189:
            r10 = 0
            goto L_0x008d
        L_0x018c:
            java.lang.String r10 = "#EXT-X-MEDIA-SEQUENCE"
            boolean r10 = r13.startsWith(r10)
            if (r10 == 0) goto L_0x019f
            java.util.regex.Pattern r10 = REGEX_MEDIA_SEQUENCE
            long r82 = parseLongAttr(r13, r10)
            r77 = r11
            r28 = r82
            goto L_0x0189
        L_0x019f:
            java.lang.String r10 = "#EXT-X-VERSION"
            boolean r10 = r13.startsWith(r10)
            if (r10 == 0) goto L_0x01ae
            java.util.regex.Pattern r10 = REGEX_VERSION
            int r30 = parseIntAttr(r13, r10)
            goto L_0x0187
        L_0x01ae:
            java.lang.String r10 = "#EXT-X-DEFINE"
            boolean r10 = r13.startsWith(r10)
            if (r10 == 0) goto L_0x01e9
            java.util.regex.Pattern r10 = REGEX_IMPORT
            java.lang.String r10 = parseOptionalStringAttr(r13, r10, r3)
            if (r10 == 0) goto L_0x01cc
            java.util.Map<java.lang.String, java.lang.String> r12 = r0.variableDefinitions
            java.lang.Object r12 = r12.get(r10)
            java.lang.String r12 = (java.lang.String) r12
            if (r12 == 0) goto L_0x01db
            r3.put(r10, r12)
            goto L_0x01db
        L_0x01cc:
            java.util.regex.Pattern r10 = REGEX_NAME
            java.lang.String r10 = parseStringAttr(r13, r10, r3)
            java.util.regex.Pattern r12 = REGEX_VALUE
            java.lang.String r12 = parseStringAttr(r13, r12, r3)
            r3.put(r10, r12)
        L_0x01db:
            r12 = r7
            r91 = r8
            r10 = r78
            r7 = r82
            r1 = 0
            r78 = r2
        L_0x01e5:
            r82 = r5
            goto L_0x064d
        L_0x01e9:
            java.lang.String r10 = "#EXTINF"
            boolean r10 = r13.startsWith(r10)
            if (r10 == 0) goto L_0x0200
            java.util.regex.Pattern r10 = REGEX_MEDIA_DURATION
            long r86 = parseTimeSecondsToUs(r13, r10)
            java.util.regex.Pattern r10 = REGEX_MEDIA_TITLE
            r12 = r20
            java.lang.String r41 = parseOptionalStringAttr(r13, r10, r12, r3)
            goto L_0x0187
        L_0x0200:
            r10 = r20
            java.lang.String r0 = "#EXT-X-SKIP"
            boolean r0 = r13.startsWith(r0)
            r45 = 1
            if (r0 == 0) goto L_0x02bd
            java.util.regex.Pattern r0 = REGEX_SKIPPED_SEGMENTS
            int r0 = parseIntAttr(r13, r0)
            if (r1 == 0) goto L_0x021c
            boolean r12 = r15.isEmpty()
            if (r12 == 0) goto L_0x021c
            r12 = 1
            goto L_0x021d
        L_0x021c:
            r12 = 0
        L_0x021d:
            com.google.android.exoplayer2.util.Assertions.checkState(r12)
            java.lang.Object r12 = com.google.android.exoplayer2.util.Util.castNonNull(r93)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r12 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist) r12
            long r12 = r12.mediaSequence
            long r12 = r28 - r12
            int r12 = (int) r12
            int r0 = r0 + r12
            if (r12 < 0) goto L_0x02b7
            java.util.List<com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment> r13 = r1.segments
            int r13 = r13.size()
            if (r0 > r13) goto L_0x02b7
            r20 = r10
            r13 = r11
            r10 = r80
        L_0x023b:
            if (r12 >= r0) goto L_0x02ab
            java.util.List<com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment> r14 = r1.segments
            java.lang.Object r14 = r14.get(r12)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r14 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r14
            r55 = r7
            r91 = r8
            long r7 = r1.mediaSequence
            int r7 = (r28 > r7 ? 1 : (r28 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x025a
            int r7 = r1.discontinuitySequence
            int r7 = r7 - r27
            int r8 = r14.relativeDiscontinuitySequence
            int r7 = r7 + r8
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r14 = r14.copyWith(r10, r7)
        L_0x025a:
            r15.add(r14)
            long r7 = r14.durationUs
            long r10 = r10 + r7
            long r7 = r14.byteRangeLength
            r42 = -1
            int r7 = (r7 > r42 ? 1 : (r7 == r42 ? 0 : -1))
            if (r7 == 0) goto L_0x0271
            long r7 = r14.byteRangeOffset
            r38 = r0
            long r0 = r14.byteRangeLength
            long r39 = r7 + r0
            goto L_0x0273
        L_0x0271:
            r38 = r0
        L_0x0273:
            int r0 = r14.relativeDiscontinuitySequence
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r1 = r14.initializationSegment
            com.google.android.exoplayer2.drm.DrmInitData r7 = r14.drmInitData
            java.lang.String r8 = r14.fullSegmentEncryptionKeyUri
            r42 = r0
            java.lang.String r0 = r14.encryptionIV
            if (r0 == 0) goto L_0x0290
            java.lang.String r0 = r14.encryptionIV
            r43 = r1
            java.lang.String r1 = java.lang.Long.toHexString(r82)
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0295
            goto L_0x0292
        L_0x0290:
            r43 = r1
        L_0x0292:
            java.lang.String r0 = r14.encryptionIV
            r13 = r0
        L_0x0295:
            long r82 = r82 + r45
            int r12 = r12 + 1
            r1 = r93
            r50 = r7
            r14 = r8
            r51 = r10
            r0 = r38
            r79 = r42
            r85 = r43
            r7 = r55
            r8 = r91
            goto L_0x023b
        L_0x02ab:
            r55 = r7
            r0 = r92
            r1 = r93
            r80 = r10
            r77 = r13
            goto L_0x0189
        L_0x02b7:
            com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$DeltaUpdateException r0 = new com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$DeltaUpdateException
            r0.<init>()
            throw r0
        L_0x02bd:
            r55 = r7
            r91 = r8
            r20 = r10
            java.lang.String r0 = "#EXT-X-KEY"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x0327
            java.util.regex.Pattern r0 = REGEX_METHOD
            java.lang.String r0 = parseStringAttr(r13, r0, r3)
            java.util.regex.Pattern r1 = REGEX_KEYFORMAT
            java.lang.String r7 = "identity"
            java.lang.String r1 = parseOptionalStringAttr(r13, r1, r7, r3)
            java.lang.String r8 = "NONE"
            boolean r8 = r8.equals(r0)
            if (r8 == 0) goto L_0x02ea
            r9.clear()
            r8 = r77
            r14 = r8
        L_0x02e7:
            r50 = r14
            goto L_0x0320
        L_0x02ea:
            java.util.regex.Pattern r8 = REGEX_IV
            java.lang.String r8 = parseOptionalStringAttr(r13, r8, r3)
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x0309
            java.lang.String r1 = "AES-128"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0306
            java.util.regex.Pattern r0 = REGEX_URI
            java.lang.String r0 = parseStringAttr(r13, r0, r3)
            r14 = r0
            goto L_0x0320
        L_0x0306:
            r14 = r77
            goto L_0x0320
        L_0x0309:
            r7 = r78
            if (r7 != 0) goto L_0x0312
            java.lang.String r78 = parseEncryptionScheme(r0)
            goto L_0x0314
        L_0x0312:
            r78 = r7
        L_0x0314:
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = parseDrmSchemeData(r13, r1, r3)
            if (r0 == 0) goto L_0x0306
            r9.put(r1, r0)
            r14 = r77
            goto L_0x02e7
        L_0x0320:
            r0 = r92
            r1 = r93
            r77 = r8
            goto L_0x0356
        L_0x0327:
            r7 = r78
            java.lang.String r0 = "#EXT-X-BYTERANGE"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x035c
            java.util.regex.Pattern r0 = REGEX_BYTERANGE
            java.lang.String r0 = parseStringAttr(r13, r0, r3)
            java.lang.String[] r0 = com.google.android.exoplayer2.util.Util.split(r0, r12)
            r1 = 0
            r8 = r0[r1]
            long r75 = java.lang.Long.parseLong(r8)
            int r1 = r0.length
            r8 = 1
            if (r1 <= r8) goto L_0x034e
            r0 = r0[r8]
            long r0 = java.lang.Long.parseLong(r0)
            r39 = r0
        L_0x034e:
            r0 = r92
            r1 = r93
            r78 = r7
            r77 = r11
        L_0x0356:
            r7 = r55
        L_0x0358:
            r8 = r91
            goto L_0x0189
        L_0x035c:
            r8 = 1
            java.lang.String r0 = "#EXT-X-DISCONTINUITY-SEQUENCE"
            boolean r0 = r13.startsWith(r0)
            r1 = 58
            if (r0 == 0) goto L_0x0385
            int r0 = r13.indexOf(r1)
            int r0 = r0 + r8
            java.lang.String r0 = r13.substring(r0)
            int r27 = java.lang.Integer.parseInt(r0)
            r0 = r92
            r1 = r93
            r78 = r7
            r77 = r11
            r7 = r55
            r8 = r91
            r10 = 0
            r26 = 1
            goto L_0x008d
        L_0x0385:
            java.lang.String r0 = "#EXT-X-DISCONTINUITY"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0390
            int r79 = r79 + 1
            goto L_0x034e
        L_0x0390:
            java.lang.String r0 = "#EXT-X-PROGRAM-DATE-TIME"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x03bd
            r18 = 0
            int r0 = (r24 > r18 ? 1 : (r24 == r18 ? 0 : -1))
            if (r0 != 0) goto L_0x03b3
            int r0 = r13.indexOf(r1)
            r1 = 1
            int r0 = r0 + r1
            java.lang.String r0 = r13.substring(r0)
            long r0 = com.google.android.exoplayer2.util.Util.parseXsDateTime(r0)
            long r0 = com.google.android.exoplayer2.util.Util.msToUs(r0)
            long r24 = r0 - r80
            goto L_0x034e
        L_0x03b3:
            r78 = r2
            r10 = r7
        L_0x03b6:
            r12 = r55
            r7 = r82
            r1 = 0
            goto L_0x01e5
        L_0x03bd:
            java.lang.String r0 = "#EXT-X-GAP"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x03d6
            r0 = r92
            r1 = r93
            r78 = r7
            r77 = r11
            r7 = r55
            r8 = r91
            r10 = 0
            r54 = 1
            goto L_0x008d
        L_0x03d6:
            java.lang.String r0 = "#EXT-X-INDEPENDENT-SEGMENTS"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x03ef
            r0 = r92
            r1 = r93
            r78 = r7
            r77 = r11
            r7 = r55
            r8 = r91
            r10 = 0
            r35 = 1
            goto L_0x008d
        L_0x03ef:
            java.lang.String r0 = "#EXT-X-ENDLIST"
            boolean r0 = r13.equals(r0)
            if (r0 == 0) goto L_0x0408
            r0 = r92
            r1 = r93
            r78 = r7
            r77 = r11
            r7 = r55
            r8 = r91
            r10 = 0
            r36 = 1
            goto L_0x008d
        L_0x0408:
            java.lang.String r0 = "#EXT-X-RENDITION-REPORT"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x043c
            java.util.regex.Pattern r0 = REGEX_LAST_MSN
            r78 = r2
            r10 = r7
            r1 = -1
            long r7 = parseOptionalLongAttr(r13, r0, r1)
            java.util.regex.Pattern r0 = REGEX_LAST_PART
            r1 = -1
            int r0 = parseOptionalIntAttr(r13, r0, r1)
            java.util.regex.Pattern r1 = REGEX_URI
            java.lang.String r1 = parseStringAttr(r13, r1, r3)
            r2 = r95
            java.lang.String r1 = com.google.android.exoplayer2.util.UriUtil.resolve(r2, r1)
            android.net.Uri r1 = android.net.Uri.parse(r1)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$RenditionReport r12 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$RenditionReport
            r12.<init>(r1, r7, r0)
            r6.add(r12)
            goto L_0x03b6
        L_0x043c:
            r78 = r2
            r10 = r7
            r2 = r95
            java.lang.String r0 = "#EXT-X-PRELOAD-HINT"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x04d4
            if (r5 == 0) goto L_0x044d
        L_0x044b:
            goto L_0x03b6
        L_0x044d:
            java.util.regex.Pattern r0 = REGEX_PRELOAD_HINT_TYPE
            java.lang.String r0 = parseStringAttr(r13, r0, r3)
            java.lang.String r1 = "PART"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L_0x045c
            goto L_0x044b
        L_0x045c:
            java.util.regex.Pattern r0 = REGEX_URI
            java.lang.String r58 = parseStringAttr(r13, r0, r3)
            java.util.regex.Pattern r0 = REGEX_BYTERANGE_START
            r7 = -1
            long r0 = parseOptionalLongAttr(r13, r0, r7)
            java.util.regex.Pattern r12 = REGEX_BYTERANGE_LENGTH
            long r70 = parseOptionalLongAttr(r13, r12, r7)
            r7 = r82
            java.lang.String r67 = getSegmentEncryptionIV(r7, r14, r11)
            if (r50 != 0) goto L_0x0498
            boolean r12 = r9.isEmpty()
            if (r12 != 0) goto L_0x0498
            java.util.Collection r12 = r9.values()
            r13 = 0
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r2 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r13]
            java.lang.Object[] r2 = r12.toArray(r2)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r2 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r2
            com.google.android.exoplayer2.drm.DrmInitData r12 = new com.google.android.exoplayer2.drm.DrmInitData
            r12.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r2)
            if (r37 != 0) goto L_0x0496
            com.google.android.exoplayer2.drm.DrmInitData r37 = getPlaylistProtectionSchemes(r10, r2)
        L_0x0496:
            r50 = r12
        L_0x0498:
            r12 = -1
            int r2 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r2 == 0) goto L_0x04a2
            int r38 = (r70 > r12 ? 1 : (r70 == r12 ? 0 : -1))
            if (r38 == 0) goto L_0x04c2
        L_0x04a2:
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part r5 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part
            if (r2 == 0) goto L_0x04a9
            r68 = r0
            goto L_0x04ab
        L_0x04a9:
            r68 = 0
        L_0x04ab:
            r73 = 0
            r74 = 1
            r60 = 0
            r72 = 0
            r57 = r5
            r59 = r85
            r62 = r79
            r63 = r51
            r65 = r50
            r66 = r14
            r57.<init>(r58, r59, r60, r62, r63, r65, r66, r67, r68, r70, r72, r73, r74)
        L_0x04c2:
            r0 = r92
            r1 = r93
            r82 = r7
            r77 = r11
            r7 = r55
            r2 = r78
            r8 = r91
            r78 = r10
            goto L_0x0189
        L_0x04d4:
            r7 = r82
            java.lang.String r0 = "#EXT-X-PART"
            boolean r0 = r13.startsWith(r0)
            if (r0 == 0) goto L_0x0591
            java.lang.String r67 = getSegmentEncryptionIV(r7, r14, r11)
            java.util.regex.Pattern r0 = REGEX_URI
            java.lang.String r58 = parseStringAttr(r13, r0, r3)
            java.util.regex.Pattern r0 = REGEX_ATTR_DURATION
            double r0 = parseDoubleAttr(r13, r0)
            double r0 = r0 * r43
            long r0 = (long) r0
            java.util.regex.Pattern r2 = REGEX_INDEPENDENT
            r82 = r5
            r5 = 0
            boolean r2 = parseOptionalBooleanAttribute(r13, r2, r5)
            if (r35 == 0) goto L_0x0505
            boolean r38 = r55.isEmpty()
            if (r38 == 0) goto L_0x0505
            r38 = 1
            goto L_0x0507
        L_0x0505:
            r38 = r5
        L_0x0507:
            r73 = r2 | r38
            java.util.regex.Pattern r2 = REGEX_GAP
            boolean r72 = parseOptionalBooleanAttribute(r13, r2, r5)
            java.util.regex.Pattern r2 = REGEX_ATTR_BYTERANGE
            java.lang.String r2 = parseOptionalStringAttr(r13, r2, r3)
            if (r2 == 0) goto L_0x0530
            java.lang.String[] r2 = com.google.android.exoplayer2.util.Util.split(r2, r12)
            r12 = r2[r5]
            long r12 = java.lang.Long.parseLong(r12)
            int r5 = r2.length
            r42 = r12
            r12 = 1
            if (r5 <= r12) goto L_0x052d
            r2 = r2[r12]
            long r88 = java.lang.Long.parseLong(r2)
        L_0x052d:
            r12 = -1
            goto L_0x0534
        L_0x0530:
            r12 = -1
            r42 = -1
        L_0x0534:
            int r2 = (r42 > r12 ? 1 : (r42 == r12 ? 0 : -1))
            if (r2 != 0) goto L_0x053a
            r88 = 0
        L_0x053a:
            if (r50 != 0) goto L_0x055c
            boolean r5 = r9.isEmpty()
            if (r5 != 0) goto L_0x055c
            java.util.Collection r5 = r9.values()
            r12 = 0
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r13 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r12]
            java.lang.Object[] r5 = r5.toArray(r13)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r5 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r5
            com.google.android.exoplayer2.drm.DrmInitData r12 = new com.google.android.exoplayer2.drm.DrmInitData
            r12.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r5)
            if (r37 != 0) goto L_0x055a
            com.google.android.exoplayer2.drm.DrmInitData r37 = getPlaylistProtectionSchemes(r10, r5)
        L_0x055a:
            r50 = r12
        L_0x055c:
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part r5 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part
            r57 = r5
            r74 = 0
            r59 = r85
            r60 = r0
            r62 = r79
            r63 = r51
            r65 = r50
            r66 = r14
            r68 = r88
            r70 = r42
            r57.<init>(r58, r59, r60, r62, r63, r65, r66, r67, r68, r70, r72, r73, r74)
            r12 = r55
            r12.add(r5)
            long r51 = r51 + r0
            if (r2 == 0) goto L_0x0580
            long r88 = r88 + r42
        L_0x0580:
            r0 = r92
            r1 = r93
            r77 = r11
            r2 = r78
            r5 = r82
            r82 = r7
            r78 = r10
            r7 = r12
            goto L_0x0358
        L_0x0591:
            r82 = r5
            r12 = r55
            java.lang.String r0 = "#"
            boolean r0 = r13.startsWith(r0)
            if (r0 != 0) goto L_0x064c
            java.lang.String r0 = getSegmentEncryptionIV(r7, r14, r11)
            long r1 = r7 + r45
            java.lang.String r5 = replaceVariableReferences(r13, r3)
            java.lang.Object r7 = r4.get(r5)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r7 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r7
            r42 = -1
            int r8 = (r75 > r42 ? 1 : (r75 == r42 ? 0 : -1))
            if (r8 != 0) goto L_0x05b6
            r57 = 0
            goto L_0x05d2
        L_0x05b6:
            if (r84 == 0) goto L_0x05d0
            if (r85 != 0) goto L_0x05d0
            if (r7 != 0) goto L_0x05d0
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r7 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment
            r48 = 0
            r49 = 0
            r44 = 0
            r42 = r7
            r43 = r5
            r46 = r39
            r42.<init>(r43, r44, r46, r48, r49)
            r4.put(r5, r7)
        L_0x05d0:
            r57 = r39
        L_0x05d2:
            if (r50 != 0) goto L_0x05f5
            boolean r13 = r9.isEmpty()
            if (r13 != 0) goto L_0x05f5
            java.util.Collection r13 = r9.values()
            r59 = r1
            r1 = 0
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r2 = new com.google.android.exoplayer2.drm.DrmInitData.SchemeData[r1]
            java.lang.Object[] r2 = r13.toArray(r2)
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData[] r2 = (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r2
            com.google.android.exoplayer2.drm.DrmInitData r13 = new com.google.android.exoplayer2.drm.DrmInitData
            r13.<init>((java.lang.String) r10, (com.google.android.exoplayer2.drm.DrmInitData.SchemeData[]) r2)
            if (r37 != 0) goto L_0x05fa
            com.google.android.exoplayer2.drm.DrmInitData r37 = getPlaylistProtectionSchemes(r10, r2)
            goto L_0x05fa
        L_0x05f5:
            r59 = r1
            r1 = 0
            r13 = r50
        L_0x05fa:
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r2 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment
            if (r85 == 0) goto L_0x0601
            r40 = r85
            goto L_0x0603
        L_0x0601:
            r40 = r7
        L_0x0603:
            r38 = r2
            r39 = r5
            r42 = r86
            r44 = r79
            r45 = r80
            r47 = r13
            r48 = r14
            r49 = r0
            r50 = r57
            r52 = r75
            r55 = r12
            r38.<init>(r39, r40, r41, r42, r44, r45, r47, r48, r49, r50, r52, r54, r55)
            r15.add(r2)
            long r51 = r80 + r86
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            if (r8 == 0) goto L_0x062a
            long r57 = r57 + r75
        L_0x062a:
            r39 = r57
            r0 = r92
            r54 = r1
            r77 = r11
            r50 = r13
            r41 = r20
            r80 = r51
            r2 = r78
            r5 = r82
            r8 = r91
            r75 = -1
            r86 = 0
            r78 = r10
            r82 = r59
            r1 = r93
            r10 = r54
            goto L_0x008d
        L_0x064c:
            r1 = 0
        L_0x064d:
            r0 = r92
            r77 = r11
            r2 = r78
            r5 = r82
            r82 = r7
            r78 = r10
            r7 = r12
            r8 = r91
            r10 = r1
            r1 = r93
            goto L_0x008d
        L_0x0661:
            r78 = r2
            r82 = r5
            r12 = r7
            r91 = r8
            r1 = r10
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            r2 = r1
        L_0x066f:
            int r3 = r6.size()
            if (r2 >= r3) goto L_0x06cd
            java.lang.Object r3 = r6.get(r2)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$RenditionReport r3 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.RenditionReport) r3
            long r4 = r3.lastMediaSequence
            r7 = -1
            int r9 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r9 != 0) goto L_0x0690
            int r4 = r15.size()
            long r4 = (long) r4
            long r4 = r28 + r4
            boolean r9 = r12.isEmpty()
            long r9 = (long) r9
            long r4 = r4 - r9
        L_0x0690:
            int r9 = r3.lastPartIndex
            r10 = -1
            if (r9 != r10) goto L_0x06b7
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r11 = (r33 > r13 ? 1 : (r33 == r13 ? 0 : -1))
            if (r11 == 0) goto L_0x06b5
            boolean r9 = r12.isEmpty()
            if (r9 == 0) goto L_0x06ad
            java.lang.Object r9 = com.google.common.collect.Iterables.getLast(r15)
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Segment r9 = (com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist.Segment) r9
            java.util.List<com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$Part> r9 = r9.parts
            goto L_0x06ae
        L_0x06ad:
            r9 = r12
        L_0x06ae:
            int r9 = r9.size()
            r11 = 1
            int r9 = r9 - r11
            goto L_0x06bd
        L_0x06b5:
            r11 = 1
            goto L_0x06bd
        L_0x06b7:
            r11 = 1
            r13 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x06bd:
            android.net.Uri r1 = r3.playlistUri
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$RenditionReport r7 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist$RenditionReport
            android.net.Uri r3 = r3.playlistUri
            r7.<init>(r3, r4, r9)
            r0.put(r1, r7)
            int r2 = r2 + 1
            r1 = 0
            goto L_0x066f
        L_0x06cd:
            r11 = 1
            if (r82 == 0) goto L_0x06d5
            r5 = r82
            r12.add(r5)
        L_0x06d5:
            com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist r1 = new com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist
            r2 = 0
            int r2 = (r24 > r2 ? 1 : (r24 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x06e0
            r90 = r11
            goto L_0x06e2
        L_0x06e0:
            r90 = 0
        L_0x06e2:
            r5 = r1
            r6 = r78
            r55 = r12
            r7 = r95
            r8 = r91
            r9 = r21
            r11 = r23
            r12 = r24
            r14 = r26
            r2 = r15
            r15 = r27
            r16 = r28
            r18 = r30
            r19 = r31
            r21 = r33
            r23 = r35
            r24 = r36
            r25 = r90
            r26 = r37
            r27 = r2
            r28 = r55
            r29 = r56
            r30 = r0
            r5.<init>(r6, r7, r8, r9, r11, r12, r14, r15, r16, r18, r19, r21, r23, r24, r25, r26, r27, r28, r29, r30)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseMediaPlaylist(com.google.android.exoplayer2.source.hls.playlist.HlsMultivariantPlaylist, com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist, com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser$LineIterator, java.lang.String):com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist");
    }

    private static DrmInitData getPlaylistProtectionSchemes(String str, DrmInitData.SchemeData[] schemeDataArr) {
        DrmInitData.SchemeData[] schemeDataArr2 = new DrmInitData.SchemeData[schemeDataArr.length];
        for (int i = 0; i < schemeDataArr.length; i++) {
            schemeDataArr2[i] = schemeDataArr[i].copyWithData((byte[]) null);
        }
        return new DrmInitData(str, schemeDataArr2);
    }

    private static String getSegmentEncryptionIV(long j, String str, String str2) {
        if (str == null) {
            return null;
        }
        return str2 != null ? str2 : Long.toHexString(j);
    }

    private static int parseSelectionFlags(String str) {
        boolean parseOptionalBooleanAttribute = parseOptionalBooleanAttribute(str, REGEX_DEFAULT, false);
        if (parseOptionalBooleanAttribute(str, REGEX_FORCED, false)) {
            parseOptionalBooleanAttribute |= true;
        }
        return parseOptionalBooleanAttribute(str, REGEX_AUTOSELECT, false) ? parseOptionalBooleanAttribute | true ? 1 : 0 : parseOptionalBooleanAttribute ? 1 : 0;
    }

    private static int parseRoleFlags(String str, Map<String, String> map) {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_CHARACTERISTICS, map);
        int i = 0;
        if (TextUtils.isEmpty(parseOptionalStringAttr)) {
            return 0;
        }
        String[] split = Util.split(parseOptionalStringAttr, ",");
        if (Util.contains(split, "public.accessibility.describes-video")) {
            i = 512;
        }
        if (Util.contains(split, "public.accessibility.transcribes-spoken-dialog")) {
            i |= 4096;
        }
        if (Util.contains(split, "public.accessibility.describes-music-and-sound")) {
            i |= 1024;
        }
        return Util.contains(split, "public.easy-to-read") ? i | 8192 : i;
    }

    private static DrmInitData.SchemeData parseDrmSchemeData(String str, String str2, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, REGEX_KEYFORMATVERSIONS, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, map);
        if (KEYFORMAT_WIDEVINE_PSSH_BINARY.equals(str2)) {
            String parseStringAttr = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, MimeTypes.VIDEO_MP4, Base64.decode(parseStringAttr.substring(parseStringAttr.indexOf(44)), 0));
        } else if (KEYFORMAT_WIDEVINE_PSSH_JSON.equals(str2)) {
            return new DrmInitData.SchemeData(C.WIDEVINE_UUID, "hls", Util.getUtf8Bytes(str));
        } else {
            if (!KEYFORMAT_PLAYREADY.equals(str2) || !IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(parseOptionalStringAttr)) {
                return null;
            }
            String parseStringAttr2 = parseStringAttr(str, REGEX_URI, map);
            return new DrmInitData.SchemeData(C.PLAYREADY_UUID, MimeTypes.VIDEO_MP4, PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(parseStringAttr2.substring(parseStringAttr2.indexOf(44)), 0)));
        }
    }

    private static HlsMediaPlaylist.ServerControl parseServerControl(String str) {
        String str2 = str;
        double parseOptionalDoubleAttr = parseOptionalDoubleAttr(str2, REGEX_CAN_SKIP_UNTIL, -9.223372036854776E18d);
        int i = (parseOptionalDoubleAttr > -9.223372036854776E18d ? 1 : (parseOptionalDoubleAttr == -9.223372036854776E18d ? 0 : -1));
        long j = C.TIME_UNSET;
        long j2 = i == 0 ? -9223372036854775807L : (long) (parseOptionalDoubleAttr * 1000000.0d);
        boolean parseOptionalBooleanAttribute = parseOptionalBooleanAttribute(str2, REGEX_CAN_SKIP_DATE_RANGES, false);
        double parseOptionalDoubleAttr2 = parseOptionalDoubleAttr(str2, REGEX_HOLD_BACK, -9.223372036854776E18d);
        long j3 = parseOptionalDoubleAttr2 == -9.223372036854776E18d ? -9223372036854775807L : (long) (parseOptionalDoubleAttr2 * 1000000.0d);
        double parseOptionalDoubleAttr3 = parseOptionalDoubleAttr(str2, REGEX_PART_HOLD_BACK, -9.223372036854776E18d);
        if (parseOptionalDoubleAttr3 != -9.223372036854776E18d) {
            j = (long) (parseOptionalDoubleAttr3 * 1000000.0d);
        }
        return new HlsMediaPlaylist.ServerControl(j2, parseOptionalBooleanAttribute, j3, j, parseOptionalBooleanAttribute(str2, REGEX_CAN_BLOCK_RELOAD, false));
    }

    private static String parseEncryptionScheme(String str) {
        if (METHOD_SAMPLE_AES_CENC.equals(str) || METHOD_SAMPLE_AES_CTR.equals(str)) {
            return C.CENC_TYPE_cenc;
        }
        return C.CENC_TYPE_cbcs;
    }

    private static int parseIntAttr(String str, Pattern pattern) throws ParserException {
        return Integer.parseInt(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static int parseOptionalIntAttr(String str, Pattern pattern, int i) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt((String) Assertions.checkNotNull(matcher.group(1))) : i;
    }

    private static long parseLongAttr(String str, Pattern pattern) throws ParserException {
        return Long.parseLong(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static long parseOptionalLongAttr(String str, Pattern pattern, long j) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Long.parseLong((String) Assertions.checkNotNull(matcher.group(1))) : j;
    }

    private static long parseTimeSecondsToUs(String str, Pattern pattern) throws ParserException {
        return new BigDecimal(parseStringAttr(str, pattern, Collections.emptyMap())).multiply(new BigDecimal(1000000)).longValue();
    }

    private static double parseDoubleAttr(String str, Pattern pattern) throws ParserException {
        return Double.parseDouble(parseStringAttr(str, pattern, Collections.emptyMap()));
    }

    private static String parseStringAttr(String str, Pattern pattern, Map<String, String> map) throws ParserException {
        String parseOptionalStringAttr = parseOptionalStringAttr(str, pattern, map);
        if (parseOptionalStringAttr != null) {
            return parseOptionalStringAttr;
        }
        throw ParserException.createForMalformedManifest("Couldn't match " + pattern.pattern() + " in " + str, (Throwable) null);
    }

    private static String parseOptionalStringAttr(String str, Pattern pattern, Map<String, String> map) {
        return parseOptionalStringAttr(str, pattern, (String) null, map);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String parseOptionalStringAttr(java.lang.String r0, java.util.regex.Pattern r1, java.lang.String r2, java.util.Map<java.lang.String, java.lang.String> r3) {
        /*
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0016
            r1 = 1
            java.lang.String r0 = r0.group(r1)
            java.lang.Object r0 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r0)
            r2 = r0
            java.lang.String r2 = (java.lang.String) r2
        L_0x0016:
            boolean r0 = r3.isEmpty()
            if (r0 != 0) goto L_0x0023
            if (r2 != 0) goto L_0x001f
            goto L_0x0023
        L_0x001f:
            java.lang.String r2 = replaceVariableReferences(r2, r3)
        L_0x0023:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser.parseOptionalStringAttr(java.lang.String, java.util.regex.Pattern, java.lang.String, java.util.Map):java.lang.String");
    }

    private static double parseOptionalDoubleAttr(String str, Pattern pattern, double d) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Double.parseDouble((String) Assertions.checkNotNull(matcher.group(1))) : d;
    }

    private static String replaceVariableReferences(String str, Map<String, String> map) {
        Matcher matcher = REGEX_VARIABLE_REFERENCE.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String group = matcher.group(1);
            if (map.containsKey(group)) {
                matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement(map.get(group)));
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    private static boolean parseOptionalBooleanAttribute(String str, Pattern pattern, boolean z) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? BOOLEAN_TRUE.equals(matcher.group(1)) : z;
    }

    private static Pattern compileBooleanAttrPattern(String str) {
        return Pattern.compile(str + "=(NO|YES)");
    }

    private static class LineIterator {
        private final Queue<String> extraLines;
        private String next;
        private final BufferedReader reader;

        public LineIterator(Queue<String> queue, BufferedReader bufferedReader) {
            this.extraLines = queue;
            this.reader = bufferedReader;
        }

        @EnsuresNonNullIf(expression = {"next"}, result = true)
        public boolean hasNext() throws IOException {
            String trim;
            if (this.next != null) {
                return true;
            }
            if (!this.extraLines.isEmpty()) {
                this.next = (String) Assertions.checkNotNull(this.extraLines.poll());
                return true;
            }
            do {
                String readLine = this.reader.readLine();
                this.next = readLine;
                if (readLine == null) {
                    return false;
                }
                trim = readLine.trim();
                this.next = trim;
            } while (trim.isEmpty());
            return true;
        }

        public String next() throws IOException {
            if (hasNext()) {
                String str = this.next;
                this.next = null;
                return str;
            }
            throw new NoSuchElementException();
        }
    }
}
