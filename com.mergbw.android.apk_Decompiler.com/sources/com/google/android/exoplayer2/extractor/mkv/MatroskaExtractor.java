package com.google.android.exoplayer2.extractor.mkv;

import android.util.Pair;
import android.util.SparseArray;
import com.alibaba.fastjson.asm.Opcodes;
import com.alibaba.fastjson.parser.JSONLexer;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrueHdSampleRechunker;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.ColorInfo;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class MatroskaExtractor implements Extractor {
    private static final int BLOCK_ADDITIONAL_ID_VP9_ITU_T_35 = 4;
    private static final int BLOCK_ADD_ID_TYPE_DVCC = 1685480259;
    private static final int BLOCK_ADD_ID_TYPE_DVVC = 1685485123;
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_AV1 = "V_AV1";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_FLOAT = "A_PCM/FLOAT/IEEE";
    private static final String CODEC_ID_PCM_INT_BIG = "A_PCM/INT/BIG";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String CODEC_ID_VTT = "S_TEXT/WEBVTT";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = new MatroskaExtractor$$ExternalSyntheticLambda0();
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_H263 = 859189832;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_ADDITIONAL = 165;
    private static final int ID_BLOCK_ADDITIONS = 30113;
    private static final int ID_BLOCK_ADDITION_MAPPING = 16868;
    private static final int ID_BLOCK_ADD_ID = 238;
    private static final int ID_BLOCK_ADD_ID_EXTRA_DATA = 16877;
    private static final int ID_BLOCK_ADD_ID_TYPE = 16871;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_BLOCK_MORE = 166;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISCARD_PADDING = 30114;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_BLOCK_ADDITION_ID = 21998;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    /* access modifiers changed from: private */
    public static final Map<String, Integer> TRACK_NAME_TO_ROTATION_DEGREES;
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final byte[] VTT_PREFIX = {87, 69, 66, 86, 84, 84, 10, 10, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 46, 48, 48, 48, 10};
    private static final int VTT_PREFIX_END_TIMECODE_OFFSET = 25;
    private static final String VTT_TIMECODE_FORMAT = "%02d:%02d:%02d.%03d";
    private static final long VTT_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private int blockAdditionalId;
    private long blockDurationUs;
    private int blockFlags;
    private long blockGroupDiscardPaddingNs;
    private boolean blockHasReferenceBlock;
    private int blockSampleCount;
    private int blockSampleIndex;
    private int[] blockSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private boolean haveOutputSample;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private final ParsableByteArray supplementalData;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    /* access modifiers changed from: protected */
    public int getElementType(int i) {
        switch (i) {
            case ID_TRACK_TYPE /*131*/:
            case ID_FLAG_DEFAULT /*136*/:
            case ID_BLOCK_DURATION /*155*/:
            case 159:
            case 176:
            case ID_CUE_TIME /*179*/:
            case ID_PIXEL_HEIGHT /*186*/:
            case ID_TRACK_NUMBER /*215*/:
            case ID_TIME_CODE /*231*/:
            case ID_BLOCK_ADD_ID /*238*/:
            case ID_CUE_CLUSTER_POSITION /*241*/:
            case ID_REFERENCE_BLOCK /*251*/:
            case ID_BLOCK_ADD_ID_TYPE /*16871*/:
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
            case ID_EBML_READ_VERSION /*17143*/:
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
            case ID_SEEK_POSITION /*21420*/:
            case ID_STEREO_MODE /*21432*/:
            case ID_DISPLAY_WIDTH /*21680*/:
            case ID_DISPLAY_UNIT /*21682*/:
            case ID_DISPLAY_HEIGHT /*21690*/:
            case ID_FLAG_FORCED /*21930*/:
            case ID_COLOUR_RANGE /*21945*/:
            case ID_COLOUR_TRANSFER /*21946*/:
            case ID_COLOUR_PRIMARIES /*21947*/:
            case ID_MAX_CLL /*21948*/:
            case ID_MAX_FALL /*21949*/:
            case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
            case ID_CODEC_DELAY /*22186*/:
            case ID_SEEK_PRE_ROLL /*22203*/:
            case ID_AUDIO_BIT_DEPTH /*25188*/:
            case ID_DISCARD_PADDING /*30114*/:
            case ID_PROJECTION_TYPE /*30321*/:
            case ID_DEFAULT_DURATION /*2352003*/:
            case ID_TIMECODE_SCALE /*2807729*/:
                return 2;
            case 134:
            case 17026:
            case ID_NAME /*21358*/:
            case ID_LANGUAGE /*2274716*/:
                return 3;
            case 160:
            case 166:
            case ID_TRACK_ENTRY /*174*/:
            case 183:
            case 187:
            case 224:
            case ID_AUDIO /*225*/:
            case ID_BLOCK_ADDITION_MAPPING /*16868*/:
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
            case ID_SEEK /*19899*/:
            case ID_CONTENT_COMPRESSION /*20532*/:
            case ID_CONTENT_ENCRYPTION /*20533*/:
            case ID_COLOUR /*21936*/:
            case ID_MASTERING_METADATA /*21968*/:
            case ID_CONTENT_ENCODING /*25152*/:
            case ID_CONTENT_ENCODINGS /*28032*/:
            case ID_BLOCK_ADDITIONS /*30113*/:
            case ID_PROJECTION /*30320*/:
            case ID_SEEK_HEAD /*290298740*/:
            case 357149030:
            case ID_TRACKS /*374648427*/:
            case ID_SEGMENT /*408125543*/:
            case ID_EBML /*440786851*/:
            case ID_CUES /*475249515*/:
            case ID_CLUSTER /*524531317*/:
                return 1;
            case 161:
            case 163:
            case 165:
            case ID_BLOCK_ADD_ID_EXTRA_DATA /*16877*/:
            case ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
            case ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
            case ID_SEEK_ID /*21419*/:
            case ID_CODEC_PRIVATE /*25506*/:
            case ID_PROJECTION_PRIVATE /*30322*/:
                return 4;
            case 181:
            case ID_DURATION /*17545*/:
            case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
            case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
            case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
            case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
            case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
            case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
            case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
            case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
            case ID_LUMNINANCE_MAX /*21977*/:
            case ID_LUMNINANCE_MIN /*21978*/:
            case ID_PROJECTION_POSE_YAW /*30323*/:
            case ID_PROJECTION_POSE_PITCH /*30324*/:
            case ID_PROJECTION_POSE_ROLL /*30325*/:
                return 5;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public boolean isLevel1Element(int i) {
        return i == 357149030 || i == ID_CLUSTER || i == ID_CUES || i == ID_TRACKS;
    }

    public final void release() {
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("htc_video_rotA-000", 0);
        hashMap.put("htc_video_rotA-090", 90);
        hashMap.put("htc_video_rotA-180", Integer.valueOf(Opcodes.GETFIELD));
        hashMap.put("htc_video_rotA-270", 270);
        TRACK_NAME_TO_ROTATION_DEGREES = Collections.unmodifiableMap(hashMap);
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = C.TIME_UNSET;
        this.durationTimecode = C.TIME_UNSET;
        this.durationUs = C.TIME_UNSET;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.reader = ebmlReader;
        ebmlReader.init(new InnerEbmlProcessor());
        this.seekForCuesEnabled = (i & 1) == 0;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
        this.supplementalData = new ParsableByteArray();
        this.blockSampleSizes = new int[1];
    }

    public final boolean sniff(ExtractorInput extractorInput) throws IOException {
        return new Sniffer().sniff(extractorInput);
    }

    public final void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = C.TIME_UNSET;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetWriteSampleData();
        for (int i = 0; i < this.tracks.size(); i++) {
            this.tracks.valueAt(i).reset();
        }
    }

    public final int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException {
        this.haveOutputSample = false;
        boolean z = true;
        while (z && !this.haveOutputSample) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            Track valueAt = this.tracks.valueAt(i);
            valueAt.assertOutputInitialized();
            valueAt.outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        assertInitialized();
        if (i == 160) {
            this.blockHasReferenceBlock = false;
            this.blockGroupDiscardPaddingNs = 0;
        } else if (i == ID_TRACK_ENTRY) {
            this.currentTrack = new Track();
        } else if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            getCurrentTrack(i).hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            getCurrentTrack(i).hasColorInfo = true;
        } else if (i == ID_SEGMENT) {
            long j3 = this.segmentContentPosition;
            if (j3 == -1 || j3 == j) {
                this.segmentContentPosition = j;
                this.segmentContentSize = j2;
                return;
            }
            throw ParserException.createForMalformedContainer("Multiple Segment elements not supported", (Throwable) null);
        } else if (i == ID_CUES) {
            this.cueTimesUs = new LongArray();
            this.cueClusterPositions = new LongArray();
        } else if (i != ID_CLUSTER || this.sentSeekMap) {
        } else {
            if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                this.extractorOutput.seekMap(new SeekMap.Unseekable(this.durationUs));
                this.sentSeekMap = true;
                return;
            }
            this.seekForCues = true;
        }
    }

    /* access modifiers changed from: protected */
    public void endMasterElement(int i) throws ParserException {
        assertInitialized();
        if (i != 160) {
            if (i == ID_TRACK_ENTRY) {
                Track track = (Track) Assertions.checkStateNotNull(this.currentTrack);
                if (track.codecId != null) {
                    if (isCodecSupported(track.codecId)) {
                        track.initializeOutput(this.extractorOutput, track.number);
                        this.tracks.put(track.number, track);
                    }
                    this.currentTrack = null;
                    return;
                }
                throw ParserException.createForMalformedContainer("CodecId is missing in TrackEntry element", (Throwable) null);
            } else if (i == ID_SEEK) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == ID_CUES) {
                            this.cuesContentPosition = j;
                            return;
                        }
                        return;
                    }
                }
                throw ParserException.createForMalformedContainer("Mandatory element SeekID or SeekPosition not found", (Throwable) null);
            } else if (i == ID_CONTENT_ENCODING) {
                assertInTrackEntry(i);
                if (!this.currentTrack.hasContentEncryption) {
                    return;
                }
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new DrmInitData.SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                    return;
                }
                throw ParserException.createForMalformedContainer("Encrypted Track found but ContentEncKeyID was not found", (Throwable) null);
            } else if (i == ID_CONTENT_ENCODINGS) {
                assertInTrackEntry(i);
                if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw ParserException.createForMalformedContainer("Combining encryption and compression is not supported", (Throwable) null);
                }
            } else if (i == 357149030) {
                if (this.timecodeScale == C.TIME_UNSET) {
                    this.timecodeScale = 1000000;
                }
                long j2 = this.durationTimecode;
                if (j2 != C.TIME_UNSET) {
                    this.durationUs = scaleTimecodeToUs(j2);
                }
            } else if (i != ID_TRACKS) {
                if (i == ID_CUES) {
                    if (!this.sentSeekMap) {
                        this.extractorOutput.seekMap(buildSeekMap(this.cueTimesUs, this.cueClusterPositions));
                        this.sentSeekMap = true;
                    }
                    this.cueTimesUs = null;
                    this.cueClusterPositions = null;
                }
            } else if (this.tracks.size() != 0) {
                this.extractorOutput.endTracks();
            } else {
                throw ParserException.createForMalformedContainer("No valid tracks were found", (Throwable) null);
            }
        } else if (this.blockState == 2) {
            Track track2 = this.tracks.get(this.blockTrackNumber);
            track2.assertOutputInitialized();
            if (this.blockGroupDiscardPaddingNs > 0 && CODEC_ID_OPUS.equals(track2.codecId)) {
                this.supplementalData.reset(ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.blockGroupDiscardPaddingNs).array());
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.blockSampleCount; i4++) {
                i3 += this.blockSampleSizes[i4];
            }
            int i5 = 0;
            while (i5 < this.blockSampleCount) {
                long j3 = this.blockTimeUs + ((long) ((track2.defaultSampleDurationNs * i5) / 1000));
                int i6 = this.blockFlags;
                if (i5 == 0 && !this.blockHasReferenceBlock) {
                    i6 |= 1;
                }
                int i7 = this.blockSampleSizes[i5];
                int i8 = i3 - i7;
                commitSampleToOutput(track2, j3, i6, i7, i8);
                i5++;
                i3 = i8;
            }
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void integerElement(int i, long j) throws ParserException {
        if (i != ID_CONTENT_ENCODING_ORDER) {
            if (i != ID_CONTENT_ENCODING_SCOPE) {
                boolean z = false;
                switch (i) {
                    case ID_TRACK_TYPE /*131*/:
                        getCurrentTrack(i).type = (int) j;
                        return;
                    case ID_FLAG_DEFAULT /*136*/:
                        Track currentTrack2 = getCurrentTrack(i);
                        if (j == 1) {
                            z = true;
                        }
                        currentTrack2.flagDefault = z;
                        return;
                    case ID_BLOCK_DURATION /*155*/:
                        this.blockDurationUs = scaleTimecodeToUs(j);
                        return;
                    case 159:
                        getCurrentTrack(i).channelCount = (int) j;
                        return;
                    case 176:
                        getCurrentTrack(i).width = (int) j;
                        return;
                    case ID_CUE_TIME /*179*/:
                        assertInCues(i);
                        this.cueTimesUs.add(scaleTimecodeToUs(j));
                        return;
                    case ID_PIXEL_HEIGHT /*186*/:
                        getCurrentTrack(i).height = (int) j;
                        return;
                    case ID_TRACK_NUMBER /*215*/:
                        getCurrentTrack(i).number = (int) j;
                        return;
                    case ID_TIME_CODE /*231*/:
                        this.clusterTimecodeUs = scaleTimecodeToUs(j);
                        return;
                    case ID_BLOCK_ADD_ID /*238*/:
                        this.blockAdditionalId = (int) j;
                        return;
                    case ID_CUE_CLUSTER_POSITION /*241*/:
                        if (!this.seenClusterPositionForCurrentCuePoint) {
                            assertInCues(i);
                            this.cueClusterPositions.add(j);
                            this.seenClusterPositionForCurrentCuePoint = true;
                            return;
                        }
                        return;
                    case ID_REFERENCE_BLOCK /*251*/:
                        this.blockHasReferenceBlock = true;
                        return;
                    case ID_BLOCK_ADD_ID_TYPE /*16871*/:
                        int unused = getCurrentTrack(i).blockAddIdType = (int) j;
                        return;
                    case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                        if (j != 3) {
                            throw ParserException.createForMalformedContainer("ContentCompAlgo " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_DOC_TYPE_READ_VERSION /*17029*/:
                        if (j < 1 || j > 2) {
                            throw ParserException.createForMalformedContainer("DocTypeReadVersion " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_EBML_READ_VERSION /*17143*/:
                        if (j != 1) {
                            throw ParserException.createForMalformedContainer("EBMLReadVersion " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                        if (j != 5) {
                            throw ParserException.createForMalformedContainer("ContentEncAlgo " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                        if (j != 1) {
                            throw ParserException.createForMalformedContainer("AESSettingsCipherMode " + j + " not supported", (Throwable) null);
                        }
                        return;
                    case ID_SEEK_POSITION /*21420*/:
                        this.seekEntryPosition = j + this.segmentContentPosition;
                        return;
                    case ID_STEREO_MODE /*21432*/:
                        int i2 = (int) j;
                        assertInTrackEntry(i);
                        if (i2 == 0) {
                            this.currentTrack.stereoMode = 0;
                            return;
                        } else if (i2 == 1) {
                            this.currentTrack.stereoMode = 2;
                            return;
                        } else if (i2 == 3) {
                            this.currentTrack.stereoMode = 1;
                            return;
                        } else if (i2 == 15) {
                            this.currentTrack.stereoMode = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DISPLAY_WIDTH /*21680*/:
                        getCurrentTrack(i).displayWidth = (int) j;
                        return;
                    case ID_DISPLAY_UNIT /*21682*/:
                        getCurrentTrack(i).displayUnit = (int) j;
                        return;
                    case ID_DISPLAY_HEIGHT /*21690*/:
                        getCurrentTrack(i).displayHeight = (int) j;
                        return;
                    case ID_FLAG_FORCED /*21930*/:
                        Track currentTrack3 = getCurrentTrack(i);
                        if (j == 1) {
                            z = true;
                        }
                        currentTrack3.flagForced = z;
                        return;
                    case ID_MAX_BLOCK_ADDITION_ID /*21998*/:
                        getCurrentTrack(i).maxBlockAdditionId = (int) j;
                        return;
                    case ID_CODEC_DELAY /*22186*/:
                        getCurrentTrack(i).codecDelayNs = j;
                        return;
                    case ID_SEEK_PRE_ROLL /*22203*/:
                        getCurrentTrack(i).seekPreRollNs = j;
                        return;
                    case ID_AUDIO_BIT_DEPTH /*25188*/:
                        getCurrentTrack(i).audioBitDepth = (int) j;
                        return;
                    case ID_DISCARD_PADDING /*30114*/:
                        this.blockGroupDiscardPaddingNs = j;
                        return;
                    case ID_PROJECTION_TYPE /*30321*/:
                        assertInTrackEntry(i);
                        int i3 = (int) j;
                        if (i3 == 0) {
                            this.currentTrack.projectionType = 0;
                            return;
                        } else if (i3 == 1) {
                            this.currentTrack.projectionType = 1;
                            return;
                        } else if (i3 == 2) {
                            this.currentTrack.projectionType = 2;
                            return;
                        } else if (i3 == 3) {
                            this.currentTrack.projectionType = 3;
                            return;
                        } else {
                            return;
                        }
                    case ID_DEFAULT_DURATION /*2352003*/:
                        getCurrentTrack(i).defaultSampleDurationNs = (int) j;
                        return;
                    case ID_TIMECODE_SCALE /*2807729*/:
                        this.timecodeScale = j;
                        return;
                    default:
                        switch (i) {
                            case ID_COLOUR_RANGE /*21945*/:
                                assertInTrackEntry(i);
                                int i4 = (int) j;
                                if (i4 == 1) {
                                    this.currentTrack.colorRange = 2;
                                    return;
                                } else if (i4 == 2) {
                                    this.currentTrack.colorRange = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case ID_COLOUR_TRANSFER /*21946*/:
                                assertInTrackEntry(i);
                                int isoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer((int) j);
                                if (isoTransferCharacteristicsToColorTransfer != -1) {
                                    this.currentTrack.colorTransfer = isoTransferCharacteristicsToColorTransfer;
                                    return;
                                }
                                return;
                            case ID_COLOUR_PRIMARIES /*21947*/:
                                assertInTrackEntry(i);
                                this.currentTrack.hasColorInfo = true;
                                int isoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace((int) j);
                                if (isoColorPrimariesToColorSpace != -1) {
                                    this.currentTrack.colorSpace = isoColorPrimariesToColorSpace;
                                    return;
                                }
                                return;
                            case ID_MAX_CLL /*21948*/:
                                getCurrentTrack(i).maxContentLuminance = (int) j;
                                return;
                            case ID_MAX_FALL /*21949*/:
                                getCurrentTrack(i).maxFrameAverageLuminance = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                throw ParserException.createForMalformedContainer("ContentEncodingScope " + j + " not supported", (Throwable) null);
            }
        } else if (j != 0) {
            throw ParserException.createForMalformedContainer("ContentEncodingOrder " + j + " not supported", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void floatElement(int i, double d) throws ParserException {
        if (i == 181) {
            getCurrentTrack(i).sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    getCurrentTrack(i).primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    getCurrentTrack(i).primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    getCurrentTrack(i).primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    getCurrentTrack(i).primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    getCurrentTrack(i).primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    getCurrentTrack(i).primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    getCurrentTrack(i).whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    getCurrentTrack(i).whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    getCurrentTrack(i).maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    getCurrentTrack(i).minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case ID_PROJECTION_POSE_YAW /*30323*/:
                            getCurrentTrack(i).projectionPoseYaw = (float) d;
                            return;
                        case ID_PROJECTION_POSE_PITCH /*30324*/:
                            getCurrentTrack(i).projectionPosePitch = (float) d;
                            return;
                        case ID_PROJECTION_POSE_ROLL /*30325*/:
                            getCurrentTrack(i).projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: protected */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            getCurrentTrack(i).codecId = str;
        } else if (i != 17026) {
            if (i == ID_NAME) {
                getCurrentTrack(i).name = str;
            } else if (i == ID_LANGUAGE) {
                String unused = getCurrentTrack(i).language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            throw ParserException.createForMalformedContainer("DocType " + str + " not supported", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0239, code lost:
        throw com.google.android.exoplayer2.ParserException.createForMalformedContainer("EBML lacing sample size out of range.", (java.lang.Throwable) null);
     */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0288  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x028a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r22, int r23, com.google.android.exoplayer2.extractor.ExtractorInput r24) throws java.io.IOException {
        /*
            r21 = this;
            r7 = r21
            r0 = r22
            r1 = r23
            r8 = r24
            r2 = 161(0xa1, float:2.26E-43)
            r3 = 163(0xa3, float:2.28E-43)
            r4 = 0
            r5 = 2
            r9 = 0
            r10 = 1
            if (r0 == r2) goto L_0x00ce
            if (r0 == r3) goto L_0x00ce
            r2 = 165(0xa5, float:2.31E-43)
            if (r0 == r2) goto L_0x00b8
            r2 = 16877(0x41ed, float:2.365E-41)
            if (r0 == r2) goto L_0x00af
            r2 = 16981(0x4255, float:2.3795E-41)
            if (r0 == r2) goto L_0x009d
            r2 = 18402(0x47e2, float:2.5787E-41)
            if (r0 == r2) goto L_0x008b
            r2 = 21419(0x53ab, float:3.0014E-41)
            if (r0 == r2) goto L_0x0067
            r2 = 25506(0x63a2, float:3.5742E-41)
            if (r0 == r2) goto L_0x0055
            r2 = 30322(0x7672, float:4.249E-41)
            if (r0 != r2) goto L_0x0042
            r21.assertInTrackEntry(r22)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.projectionData = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.projectionData
            r8.readFully(r0, r9, r1)
            goto L_0x02f1
        L_0x0042:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected id: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x0055:
            r21.assertInTrackEntry(r22)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.codecPrivate = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.codecPrivate
            r8.readFully(r0, r9, r1)
            goto L_0x02f1
        L_0x0067:
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.getData()
            java.util.Arrays.fill(r0, r9)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            byte[] r0 = r0.getData()
            int r2 = 4 - r1
            r8.readFully(r0, r2, r1)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            r0.setPosition(r9)
            com.google.android.exoplayer2.util.ParsableByteArray r0 = r7.seekEntryIdBytes
            long r0 = r0.readUnsignedInt()
            int r0 = (int) r0
            r7.seekEntryId = r0
            goto L_0x02f1
        L_0x008b:
            byte[] r2 = new byte[r1]
            r8.readFully(r2, r9, r1)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r21.getCurrentTrack(r22)
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r1 = new com.google.android.exoplayer2.extractor.TrackOutput$CryptoData
            r1.<init>(r10, r2, r9, r9)
            r0.cryptoData = r1
            goto L_0x02f1
        L_0x009d:
            r21.assertInTrackEntry(r22)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r2 = new byte[r1]
            r0.sampleStrippedBytes = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r7.currentTrack
            byte[] r0 = r0.sampleStrippedBytes
            r8.readFully(r0, r9, r1)
            goto L_0x02f1
        L_0x00af:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = r21.getCurrentTrack(r22)
            r7.handleBlockAddIDExtraData(r0, r8, r1)
            goto L_0x02f1
        L_0x00b8:
            int r0 = r7.blockState
            if (r0 == r5) goto L_0x00bd
            return
        L_0x00bd:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r0 = r7.tracks
            int r2 = r7.blockTrackNumber
            java.lang.Object r0 = r0.get(r2)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r0 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r0
            int r2 = r7.blockAdditionalId
            r7.handleBlockAdditionalData(r0, r2, r8, r1)
            goto L_0x02f1
        L_0x00ce:
            int r2 = r7.blockState
            r6 = 8
            if (r2 != 0) goto L_0x00f3
            com.google.android.exoplayer2.extractor.mkv.VarintReader r2 = r7.varintReader
            long r11 = r2.readUnsignedVarint(r8, r9, r10, r6)
            int r2 = (int) r11
            r7.blockTrackNumber = r2
            com.google.android.exoplayer2.extractor.mkv.VarintReader r2 = r7.varintReader
            int r2 = r2.getLastLength()
            r7.blockTrackNumberLength = r2
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r7.blockDurationUs = r11
            r7.blockState = r10
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r7.scratch
            r2.reset((int) r9)
        L_0x00f3:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r2 = r7.tracks
            int r11 = r7.blockTrackNumber
            java.lang.Object r2 = r2.get(r11)
            r11 = r2
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r11 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r11
            if (r11 != 0) goto L_0x010a
            int r0 = r7.blockTrackNumberLength
            int r0 = r1 - r0
            r8.skipFully(r0)
            r7.blockState = r9
            return
        L_0x010a:
            r11.assertOutputInitialized()
            int r2 = r7.blockState
            if (r2 != r10) goto L_0x02a8
            r2 = 3
            r7.readScratch(r8, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r7.scratch
            byte[] r12 = r12.getData()
            byte r12 = r12[r5]
            r12 = r12 & 6
            int r12 = r12 >> r10
            r13 = 255(0xff, float:3.57E-43)
            if (r12 != 0) goto L_0x0138
            r7.blockSampleCount = r10
            int[] r4 = r7.blockSampleSizes
            int[] r4 = ensureArrayCapacity(r4, r10)
            r7.blockSampleSizes = r4
            int r12 = r7.blockTrackNumberLength
            int r1 = r1 - r12
            int r1 = r1 - r2
            r4[r9] = r1
        L_0x0134:
            r18 = r11
            goto L_0x024e
        L_0x0138:
            r14 = 4
            r7.readScratch(r8, r14)
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r7.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r2]
            r15 = r15 & r13
            int r15 = r15 + r10
            r7.blockSampleCount = r15
            int[] r3 = r7.blockSampleSizes
            int[] r3 = ensureArrayCapacity(r3, r15)
            r7.blockSampleSizes = r3
            if (r12 != r5) goto L_0x015d
            int r2 = r7.blockTrackNumberLength
            int r1 = r1 - r2
            int r1 = r1 - r14
            int r2 = r7.blockSampleCount
            int r1 = r1 / r2
            java.util.Arrays.fill(r3, r9, r2, r1)
            goto L_0x0134
        L_0x015d:
            if (r12 != r10) goto L_0x0194
            r2 = r9
            r3 = r2
        L_0x0161:
            int r4 = r7.blockSampleCount
            int r12 = r4 + -1
            if (r2 >= r12) goto L_0x0189
            int[] r4 = r7.blockSampleSizes
            r4[r2] = r9
        L_0x016b:
            int r4 = r14 + 1
            r7.readScratch(r8, r4)
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r7.scratch
            byte[] r12 = r12.getData()
            byte r12 = r12[r14]
            r12 = r12 & r13
            int[] r14 = r7.blockSampleSizes
            r15 = r14[r2]
            int r15 = r15 + r12
            r14[r2] = r15
            if (r12 == r13) goto L_0x0187
            int r3 = r3 + r15
            int r2 = r2 + 1
            r14 = r4
            goto L_0x0161
        L_0x0187:
            r14 = r4
            goto L_0x016b
        L_0x0189:
            int[] r2 = r7.blockSampleSizes
            int r4 = r4 - r10
            int r12 = r7.blockTrackNumberLength
            int r1 = r1 - r12
            int r1 = r1 - r14
            int r1 = r1 - r3
            r2[r4] = r1
            goto L_0x0134
        L_0x0194:
            if (r12 != r2) goto L_0x0295
            r2 = r9
            r3 = r2
        L_0x0198:
            int r12 = r7.blockSampleCount
            int r15 = r12 + -1
            if (r2 >= r15) goto L_0x0241
            int[] r12 = r7.blockSampleSizes
            r12[r2] = r9
            int r12 = r14 + 1
            r7.readScratch(r8, r12)
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r7.scratch
            byte[] r15 = r15.getData()
            byte r15 = r15[r14]
            if (r15 == 0) goto L_0x023a
            r15 = r9
        L_0x01b2:
            if (r15 >= r6) goto L_0x0207
            int r16 = 7 - r15
            int r5 = r10 << r16
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.scratch
            byte[] r9 = r9.getData()
            byte r9 = r9[r14]
            r9 = r9 & r5
            if (r9 == 0) goto L_0x01fd
            int r12 = r12 + r15
            r7.readScratch(r8, r12)
            com.google.android.exoplayer2.util.ParsableByteArray r9 = r7.scratch
            byte[] r9 = r9.getData()
            int r17 = r14 + 1
            byte r9 = r9[r14]
            r9 = r9 & r13
            int r5 = ~r5
            r5 = r5 & r9
            r18 = r11
            long r10 = (long) r5
            r5 = r17
        L_0x01d9:
            if (r5 >= r12) goto L_0x01ee
            long r10 = r10 << r6
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r7.scratch
            byte[] r14 = r14.getData()
            int r17 = r5 + 1
            byte r5 = r14[r5]
            r5 = r5 & r13
            long r13 = (long) r5
            long r10 = r10 | r13
            r5 = r17
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x01d9
        L_0x01ee:
            if (r2 <= 0) goto L_0x020b
            int r15 = r15 * 7
            int r15 = r15 + 6
            r13 = 1
            long r19 = r13 << r15
            long r19 = r19 - r13
            long r10 = r10 - r19
            goto L_0x020b
        L_0x01fd:
            r18 = r11
            int r15 = r15 + 1
            r5 = 2
            r9 = 0
            r10 = 1
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x01b2
        L_0x0207:
            r18 = r11
            r10 = 0
        L_0x020b:
            r14 = r12
            r12 = -2147483648(0xffffffff80000000, double:NaN)
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 < 0) goto L_0x0233
            r12 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r5 > 0) goto L_0x0233
            int r5 = (int) r10
            int[] r10 = r7.blockSampleSizes
            if (r2 != 0) goto L_0x0220
            goto L_0x0225
        L_0x0220:
            int r11 = r2 + -1
            r11 = r10[r11]
            int r5 = r5 + r11
        L_0x0225:
            r10[r2] = r5
            int r3 = r3 + r5
            int r2 = r2 + 1
            r11 = r18
            r5 = 2
            r9 = 0
            r10 = 1
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x0198
        L_0x0233:
            java.lang.String r0 = "EBML lacing sample size out of range."
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x023a:
            java.lang.String r0 = "No valid varint length mask found"
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x0241:
            r18 = r11
            int[] r2 = r7.blockSampleSizes
            r4 = 1
            int r12 = r12 - r4
            int r4 = r7.blockTrackNumberLength
            int r1 = r1 - r4
            int r1 = r1 - r14
            int r1 = r1 - r3
            r2[r12] = r1
        L_0x024e:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.getData()
            r2 = 0
            byte r1 = r1[r2]
            int r1 = r1 << r6
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r7.scratch
            byte[] r2 = r2.getData()
            r3 = 1
            byte r2 = r2[r3]
            r3 = 255(0xff, float:3.57E-43)
            r2 = r2 & r3
            r1 = r1 | r2
            long r2 = r7.clusterTimecodeUs
            long r4 = (long) r1
            long r4 = r7.scaleTimecodeToUs(r4)
            long r2 = r2 + r4
            r7.blockTimeUs = r2
            r10 = r18
            int r1 = r10.type
            r2 = 2
            if (r1 == r2) goto L_0x028a
            r1 = 163(0xa3, float:2.28E-43)
            if (r0 != r1) goto L_0x0288
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r7.scratch
            byte[] r1 = r1.getData()
            byte r1 = r1[r2]
            r3 = 128(0x80, float:1.794E-43)
            r1 = r1 & r3
            if (r1 != r3) goto L_0x0288
            goto L_0x028a
        L_0x0288:
            r1 = 0
            goto L_0x028b
        L_0x028a:
            r1 = 1
        L_0x028b:
            r7.blockFlags = r1
            r7.blockState = r2
            r1 = 0
            r7.blockSampleIndex = r1
            r1 = 163(0xa3, float:2.28E-43)
            goto L_0x02aa
        L_0x0295:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unexpected lacing value: "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            com.google.android.exoplayer2.ParserException r0 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r0, r4)
            throw r0
        L_0x02a8:
            r10 = r11
            r1 = r3
        L_0x02aa:
            if (r0 != r1) goto L_0x02da
        L_0x02ac:
            int r0 = r7.blockSampleIndex
            int r1 = r7.blockSampleCount
            if (r0 >= r1) goto L_0x02d6
            int[] r1 = r7.blockSampleSizes
            r0 = r1[r0]
            r1 = 0
            int r5 = r7.writeSampleData(r8, r10, r0, r1)
            long r0 = r7.blockTimeUs
            int r2 = r7.blockSampleIndex
            int r3 = r10.defaultSampleDurationNs
            int r2 = r2 * r3
            int r2 = r2 / 1000
            long r2 = (long) r2
            long r2 = r2 + r0
            int r4 = r7.blockFlags
            r6 = 0
            r0 = r21
            r1 = r10
            r0.commitSampleToOutput(r1, r2, r4, r5, r6)
            int r0 = r7.blockSampleIndex
            r1 = 1
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x02ac
        L_0x02d6:
            r0 = 0
            r7.blockState = r0
            goto L_0x02f1
        L_0x02da:
            r1 = 1
        L_0x02db:
            int r0 = r7.blockSampleIndex
            int r2 = r7.blockSampleCount
            if (r0 >= r2) goto L_0x02f1
            int[] r2 = r7.blockSampleSizes
            r3 = r2[r0]
            int r3 = r7.writeSampleData(r8, r10, r3, r1)
            r2[r0] = r3
            int r0 = r7.blockSampleIndex
            int r0 = r0 + r1
            r7.blockSampleIndex = r0
            goto L_0x02db
        L_0x02f1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    /* access modifiers changed from: protected */
    public void handleBlockAddIDExtraData(Track track, ExtractorInput extractorInput, int i) throws IOException {
        if (track.blockAddIdType == 1685485123 || track.blockAddIdType == 1685480259) {
            track.dolbyVisionConfigBytes = new byte[i];
            extractorInput.readFully(track.dolbyVisionConfigBytes, 0, i);
            return;
        }
        extractorInput.skipFully(i);
    }

    /* access modifiers changed from: protected */
    public void handleBlockAdditionalData(Track track, int i, ExtractorInput extractorInput, int i2) throws IOException {
        if (i != 4 || !CODEC_ID_VP9.equals(track.codecId)) {
            extractorInput.skipFully(i2);
            return;
        }
        this.supplementalData.reset(i2);
        extractorInput.readFully(this.supplementalData.getData(), 0, i2);
    }

    @EnsuresNonNull({"currentTrack"})
    private void assertInTrackEntry(int i) throws ParserException {
        if (this.currentTrack == null) {
            throw ParserException.createForMalformedContainer("Element " + i + " must be in a TrackEntry", (Throwable) null);
        }
    }

    @EnsuresNonNull({"cueTimesUs", "cueClusterPositions"})
    private void assertInCues(int i) throws ParserException {
        if (this.cueTimesUs == null || this.cueClusterPositions == null) {
            throw ParserException.createForMalformedContainer("Element " + i + " must be in a Cues", (Throwable) null);
        }
    }

    /* access modifiers changed from: protected */
    public Track getCurrentTrack(int i) throws ParserException {
        assertInTrackEntry(i);
        return this.currentTrack;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x009d  */
    @org.checkerframework.checker.nullness.qual.RequiresNonNull({"#1.output"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void commitSampleToOutput(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track r13, long r14, int r16, int r17, int r18) {
        /*
            r12 = this;
            r0 = r12
            r1 = r13
            com.google.android.exoplayer2.extractor.TrueHdSampleRechunker r2 = r1.trueHdSampleRechunker
            r3 = 1
            if (r2 == 0) goto L_0x0019
            com.google.android.exoplayer2.extractor.TrueHdSampleRechunker r4 = r1.trueHdSampleRechunker
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r9 = r17
            r10 = r18
            r4.sampleMetadata(r5, r6, r8, r9, r10, r11)
            goto L_0x00c4
        L_0x0019:
            java.lang.String r2 = "S_TEXT/UTF8"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0037
            java.lang.String r2 = "S_TEXT/ASS"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 != 0) goto L_0x0037
            java.lang.String r2 = "S_TEXT/WEBVTT"
            java.lang.String r4 = r1.codecId
            boolean r2 = r2.equals(r4)
            if (r2 == 0) goto L_0x0053
        L_0x0037:
            int r2 = r0.blockSampleCount
            java.lang.String r4 = "MatroskaExtractor"
            if (r2 <= r3) goto L_0x0043
            java.lang.String r2 = "Skipping subtitle sample in laced block."
            com.google.android.exoplayer2.util.Log.w(r4, r2)
            goto L_0x0053
        L_0x0043:
            long r5 = r0.blockDurationUs
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r2 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x0056
            java.lang.String r2 = "Skipping subtitle sample with no duration."
            com.google.android.exoplayer2.util.Log.w(r4, r2)
        L_0x0053:
            r2 = r17
            goto L_0x0097
        L_0x0056:
            java.lang.String r2 = r1.codecId
            long r4 = r0.blockDurationUs
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.subtitleSample
            byte[] r6 = r6.getData()
            setSubtitleEndTime(r2, r4, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.getPosition()
        L_0x0069:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            int r4 = r4.limit()
            if (r2 >= r4) goto L_0x0084
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            byte[] r4 = r4.getData()
            byte r4 = r4[r2]
            if (r4 != 0) goto L_0x0081
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            r4.setLimit(r2)
            goto L_0x0084
        L_0x0081:
            int r2 = r2 + 1
            goto L_0x0069
        L_0x0084:
            com.google.android.exoplayer2.extractor.TrackOutput r2 = r1.output
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.subtitleSample
            int r5 = r4.limit()
            r2.sampleData(r4, r5)
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.subtitleSample
            int r2 = r2.limit()
            int r2 = r17 + r2
        L_0x0097:
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            r4 = r16 & r4
            if (r4 == 0) goto L_0x00b7
            int r4 = r0.blockSampleCount
            if (r4 <= r3) goto L_0x00a8
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.supplementalData
            r5 = 0
            r4.reset((int) r5)
            goto L_0x00b7
        L_0x00a8:
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.supplementalData
            int r4 = r4.limit()
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.supplementalData
            r7 = 2
            r5.sampleData((com.google.android.exoplayer2.util.ParsableByteArray) r6, (int) r4, (int) r7)
            int r2 = r2 + r4
        L_0x00b7:
            r9 = r2
            com.google.android.exoplayer2.extractor.TrackOutput r5 = r1.output
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r11 = r1.cryptoData
            r6 = r14
            r8 = r16
            r10 = r18
            r5.sampleMetadata(r6, r8, r9, r10, r11)
        L_0x00c4:
            r0.haveOutputSample = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.commitSampleToOutput(com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track, long, int, int, int):void");
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.ensureCapacity(Math.max(parsableByteArray.capacity() * 2, i));
            }
            extractorInput.readFully(this.scratch.getData(), this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    @RequiresNonNull({"#2.output"})
    private int writeSampleData(ExtractorInput extractorInput, Track track, int i, boolean z) throws IOException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
            return finishWriteSampleData();
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
            return finishWriteSampleData();
        } else if (CODEC_ID_VTT.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, VTT_PREFIX, i);
            return finishWriteSampleData();
        } else {
            TrackOutput trackOutput = track.output;
            boolean z2 = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.getData(), 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.getData()[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.getData()[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw ParserException.createForMalformedContainer("Extension bit is set in signal byte", (Throwable) null);
                        }
                    }
                    byte b2 = this.sampleSignalByte;
                    if ((b2 & 1) == 1) {
                        boolean z3 = (b2 & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.getData(), 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] data = this.scratch.getData();
                            if (!z3) {
                                i3 = 0;
                            }
                            data[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8, 1);
                            this.sampleBytesWritten += 8;
                        }
                        if (z3) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.getData(), 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.getData(), 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5, 1);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                if (track.samplesHaveSupplementalData(z)) {
                    this.blockFlags |= 268435456;
                    this.supplementalData.reset(0);
                    int limit = (this.sampleStrippedBytes.limit() + i) - this.sampleBytesRead;
                    this.scratch.reset(4);
                    this.scratch.getData()[0] = (byte) ((limit >> 24) & 255);
                    this.scratch.getData()[1] = (byte) ((limit >> 16) & 255);
                    this.scratch.getData()[2] = (byte) ((limit >> 8) & 255);
                    this.scratch.getData()[3] = (byte) (limit & 255);
                    trackOutput.sampleData(this.scratch, 4, 2);
                    this.sampleBytesWritten += 4;
                }
                this.sampleEncodingHandled = true;
            }
            int limit2 = i + this.sampleStrippedBytes.limit();
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                    track.trueHdSampleRechunker.startSample(extractorInput);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit2) {
                        break;
                    }
                    int writeToOutput = writeToOutput(extractorInput, trackOutput, limit2 - i9);
                    this.sampleBytesRead += writeToOutput;
                    this.sampleBytesWritten += writeToOutput;
                }
            } else {
                byte[] data2 = this.nalLength.getData();
                data2[0] = 0;
                data2[1] = 0;
                data2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit2) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        writeToTarget(extractorInput, data2, i11, i10);
                        this.sampleBytesRead += i10;
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        int writeToOutput2 = writeToOutput(extractorInput, trackOutput, i12);
                        this.sampleBytesRead += writeToOutput2;
                        this.sampleBytesWritten += writeToOutput2;
                        this.sampleCurrentNalBytesRemaining -= writeToOutput2;
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
            return finishWriteSampleData();
        }
    }

    private int finishWriteSampleData() {
        int i = this.sampleBytesWritten;
        resetWriteSampleData();
        return i;
    }

    private void resetWriteSampleData() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset(0);
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.reset(Arrays.copyOf(bArr, length + i));
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.getData(), 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.getData(), bArr.length, i);
        this.subtitleSample.setPosition(0);
        this.subtitleSample.setLimit(length);
    }

    private static void setSubtitleEndTime(String str, long j, byte[] bArr) {
        int i;
        byte[] bArr2;
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c2 = 0;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c2 = 1;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                bArr2 = formatSubtitleTimecode(j, SSA_TIMECODE_FORMAT, 10000);
                i = 21;
                break;
            case 1:
                bArr2 = formatSubtitleTimecode(j, VTT_TIMECODE_FORMAT, 1000);
                i = 25;
                break;
            case 2:
                bArr2 = formatSubtitleTimecode(j, SUBRIP_TIMECODE_FORMAT, 1000);
                i = 19;
                break;
            default:
                throw new IllegalArgumentException();
        }
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
    }

    private static byte[] formatSubtitleTimecode(long j, String str, long j2) {
        Assertions.checkArgument(j != C.TIME_UNSET);
        int i = (int) (j / 3600000000L);
        long j3 = j - (((long) i) * 3600000000L);
        int i2 = (int) (j3 / 60000000);
        long j4 = j3 - (((long) i2) * 60000000);
        int i3 = (int) (j4 / 1000000);
        return Util.getUtf8Bytes(String.format(Locale.US, str, new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - (((long) i3) * 1000000)) / j2))}));
    }

    private void writeToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
    }

    private int writeToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException {
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft <= 0) {
            return trackOutput.sampleData((DataReader) extractorInput, i, false);
        }
        int min = Math.min(i, bytesLeft);
        trackOutput.sampleData(this.sampleStrippedBytes, min);
        return min;
    }

    private SeekMap buildSeekMap(LongArray longArray, LongArray longArray2) {
        int i;
        if (this.segmentContentPosition == -1 || this.durationUs == C.TIME_UNSET || longArray == null || longArray.size() == 0 || longArray2 == null || longArray2.size() != longArray.size()) {
            return new SeekMap.Unseekable(this.durationUs);
        }
        int size = longArray.size();
        int[] iArr = new int[size];
        long[] jArr = new long[size];
        long[] jArr2 = new long[size];
        long[] jArr3 = new long[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            jArr3[i3] = longArray.get(i3);
            jArr[i3] = this.segmentContentPosition + longArray2.get(i3);
        }
        while (true) {
            i = size - 1;
            if (i2 >= i) {
                break;
            }
            int i4 = i2 + 1;
            iArr[i2] = (int) (jArr[i4] - jArr[i2]);
            jArr2[i2] = jArr3[i4] - jArr3[i2];
            i2 = i4;
        }
        iArr[i] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i]);
        long j = this.durationUs - jArr3[i];
        jArr2[i] = j;
        if (j <= 0) {
            Log.w(TAG, "Discarding last cue point with unexpected duration: " + j);
            iArr = Arrays.copyOf(iArr, i);
            jArr = Arrays.copyOf(jArr, i);
            jArr2 = Arrays.copyOf(jArr2, i);
            jArr3 = Arrays.copyOf(jArr3, i);
        }
        return new ChunkIndex(iArr, jArr, jArr2, jArr3);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != C.TIME_UNSET) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw ParserException.createForMalformedContainer("Can't scale timecode prior to timecodeScale being set.", (Throwable) null);
    }

    private static boolean isCodecSupported(String str) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2095576542:
                if (str.equals(CODEC_ID_MPEG4_AP)) {
                    c2 = 0;
                    break;
                }
                break;
            case -2095575984:
                if (str.equals(CODEC_ID_MPEG4_SP)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1985379776:
                if (str.equals(CODEC_ID_ACM)) {
                    c2 = 2;
                    break;
                }
                break;
            case -1784763192:
                if (str.equals(CODEC_ID_TRUEHD)) {
                    c2 = 3;
                    break;
                }
                break;
            case -1730367663:
                if (str.equals(CODEC_ID_VORBIS)) {
                    c2 = 4;
                    break;
                }
                break;
            case -1482641358:
                if (str.equals(CODEC_ID_MP2)) {
                    c2 = 5;
                    break;
                }
                break;
            case -1482641357:
                if (str.equals(CODEC_ID_MP3)) {
                    c2 = 6;
                    break;
                }
                break;
            case -1373388978:
                if (str.equals(CODEC_ID_FOURCC)) {
                    c2 = 7;
                    break;
                }
                break;
            case -933872740:
                if (str.equals(CODEC_ID_DVBSUB)) {
                    c2 = 8;
                    break;
                }
                break;
            case -538363189:
                if (str.equals(CODEC_ID_MPEG4_ASP)) {
                    c2 = 9;
                    break;
                }
                break;
            case -538363109:
                if (str.equals(CODEC_ID_H264)) {
                    c2 = 10;
                    break;
                }
                break;
            case -425012669:
                if (str.equals(CODEC_ID_VOBSUB)) {
                    c2 = 11;
                    break;
                }
                break;
            case -356037306:
                if (str.equals(CODEC_ID_DTS_LOSSLESS)) {
                    c2 = 12;
                    break;
                }
                break;
            case 62923557:
                if (str.equals(CODEC_ID_AAC)) {
                    c2 = 13;
                    break;
                }
                break;
            case 62923603:
                if (str.equals(CODEC_ID_AC3)) {
                    c2 = 14;
                    break;
                }
                break;
            case 62927045:
                if (str.equals(CODEC_ID_DTS)) {
                    c2 = 15;
                    break;
                }
                break;
            case 82318131:
                if (str.equals(CODEC_ID_AV1)) {
                    c2 = 16;
                    break;
                }
                break;
            case 82338133:
                if (str.equals(CODEC_ID_VP8)) {
                    c2 = 17;
                    break;
                }
                break;
            case 82338134:
                if (str.equals(CODEC_ID_VP9)) {
                    c2 = 18;
                    break;
                }
                break;
            case 99146302:
                if (str.equals(CODEC_ID_PGS)) {
                    c2 = 19;
                    break;
                }
                break;
            case 444813526:
                if (str.equals(CODEC_ID_THEORA)) {
                    c2 = 20;
                    break;
                }
                break;
            case 542569478:
                if (str.equals(CODEC_ID_DTS_EXPRESS)) {
                    c2 = 21;
                    break;
                }
                break;
            case 635596514:
                if (str.equals(CODEC_ID_PCM_FLOAT)) {
                    c2 = 22;
                    break;
                }
                break;
            case 725948237:
                if (str.equals(CODEC_ID_PCM_INT_BIG)) {
                    c2 = 23;
                    break;
                }
                break;
            case 725957860:
                if (str.equals(CODEC_ID_PCM_INT_LIT)) {
                    c2 = 24;
                    break;
                }
                break;
            case 738597099:
                if (str.equals(CODEC_ID_ASS)) {
                    c2 = 25;
                    break;
                }
                break;
            case 855502857:
                if (str.equals(CODEC_ID_H265)) {
                    c2 = JSONLexer.EOI;
                    break;
                }
                break;
            case 1045209816:
                if (str.equals(CODEC_ID_VTT)) {
                    c2 = 27;
                    break;
                }
                break;
            case 1422270023:
                if (str.equals(CODEC_ID_SUBRIP)) {
                    c2 = 28;
                    break;
                }
                break;
            case 1809237540:
                if (str.equals(CODEC_ID_MPEG2)) {
                    c2 = 29;
                    break;
                }
                break;
            case 1950749482:
                if (str.equals(CODEC_ID_E_AC3)) {
                    c2 = 30;
                    break;
                }
                break;
            case 1950789798:
                if (str.equals(CODEC_ID_FLAC)) {
                    c2 = 31;
                    break;
                }
                break;
            case 1951062397:
                if (str.equals(CODEC_ID_OPUS)) {
                    c2 = ' ';
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
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case ' ':
                return true;
            default:
                return false;
        }
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }

    @EnsuresNonNull({"extractorOutput"})
    private void assertInitialized() {
        Assertions.checkStateNotNull(this.extractorOutput);
    }

    private final class InnerEbmlProcessor implements EbmlProcessor {
        private InnerEbmlProcessor() {
        }

        public int getElementType(int i) {
            return MatroskaExtractor.this.getElementType(i);
        }

        public boolean isLevel1Element(int i) {
            return MatroskaExtractor.this.isLevel1Element(i);
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    protected static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth = -1;
        /* access modifiers changed from: private */
        public int blockAddIdType;
        public int channelCount = 1;
        public long codecDelayNs = 0;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange = -1;
        public int colorSpace = -1;
        public int colorTransfer = -1;
        public TrackOutput.CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight = -1;
        public int displayUnit = 0;
        public int displayWidth = -1;
        public byte[] dolbyVisionConfigBytes;
        public DrmInitData drmInitData;
        public boolean flagDefault = true;
        public boolean flagForced;
        public boolean hasColorInfo = false;
        public boolean hasContentEncryption;
        public int height = -1;
        /* access modifiers changed from: private */
        public String language = "eng";
        public int maxBlockAdditionId;
        public int maxContentLuminance = 1000;
        public int maxFrameAverageLuminance = 200;
        public float maxMasteringLuminance = -1.0f;
        public float minMasteringLuminance = -1.0f;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX = -1.0f;
        public float primaryBChromaticityY = -1.0f;
        public float primaryGChromaticityX = -1.0f;
        public float primaryGChromaticityY = -1.0f;
        public float primaryRChromaticityX = -1.0f;
        public float primaryRChromaticityY = -1.0f;
        public byte[] projectionData = null;
        public float projectionPosePitch = 0.0f;
        public float projectionPoseRoll = 0.0f;
        public float projectionPoseYaw = 0.0f;
        public int projectionType = -1;
        public int sampleRate = 8000;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs = 0;
        public int stereoMode = -1;
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX = -1.0f;
        public float whitePointChromaticityY = -1.0f;
        public int width = -1;

        protected Track() {
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v14, resolved type: java.lang.String} */
        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* JADX WARNING: Code restructure failed: missing block: B:117:0x0299, code lost:
            r17 = com.google.android.exoplayer2.util.MimeTypes.AUDIO_UNKNOWN;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:118:0x029c, code lost:
            r1 = null;
            r3 = null;
            r4 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:119:0x029f, code lost:
            r6 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:133:0x02f0, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:134:0x02f1, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:135:0x02f2, code lost:
            r4 = 0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:136:0x02f3, code lost:
            r6 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:137:0x02f4, code lost:
            r8 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:145:0x0346, code lost:
            r4 = 0;
            r6 = -1;
            r8 = -1;
            r19 = r3;
            r3 = r1;
            r1 = r19;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:148:0x037d, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:151:0x0387, code lost:
            r6 = 4096;
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:153:0x039c, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:155:0x03a9, code lost:
            r1 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:170:0x03f8, code lost:
            if (r0.dolbyVisionConfigBytes == null) goto L_0x040b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:171:0x03fa, code lost:
            r7 = com.google.android.exoplayer2.video.DolbyVisionConfig.parse(new com.google.android.exoplayer2.util.ParsableByteArray(r0.dolbyVisionConfigBytes));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:172:0x0405, code lost:
            if (r7 == null) goto L_0x040b;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:173:0x0407, code lost:
            r3 = r7.codecs;
            r17 = com.google.android.exoplayer2.util.MimeTypes.VIDEO_DOLBY_VISION;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:174:0x040b, code lost:
            r7 = r17;
            r9 = r0.flagDefault;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:175:0x0411, code lost:
            if (r0.flagForced == false) goto L_0x0415;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:176:0x0413, code lost:
            r10 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:177:0x0415, code lost:
            r10 = r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:178:0x0416, code lost:
            r9 = r9 | r10;
            r10 = new com.google.android.exoplayer2.Format.Builder();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:179:0x0420, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isAudio(r7) == false) goto L_0x0434;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:180:0x0422, code lost:
            r10.setChannelCount(r0.channelCount).setSampleRate(r0.sampleRate).setPcmEncoding(r8);
            r5 = 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:182:0x0438, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.isVideo(r7) == false) goto L_0x0510;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:184:0x043c, code lost:
            if (r0.displayUnit != 0) goto L_0x0450;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:185:0x043e, code lost:
            r2 = r0.displayWidth;
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:186:0x0441, code lost:
            if (r2 != -1) goto L_0x0445;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:187:0x0443, code lost:
            r2 = r0.width;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:188:0x0445, code lost:
            r0.displayWidth = r2;
            r2 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:189:0x0449, code lost:
            if (r2 != -1) goto L_0x044d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:190:0x044b, code lost:
            r2 = r0.height;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:191:0x044d, code lost:
            r0.displayHeight = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:192:0x0450, code lost:
            r5 = -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:193:0x0451, code lost:
            r2 = r0.displayWidth;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:194:0x0453, code lost:
            if (r2 == r5) goto L_0x0463;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:195:0x0455, code lost:
            r8 = r0.displayHeight;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:196:0x0457, code lost:
            if (r8 == r5) goto L_0x0463;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:197:0x0459, code lost:
            r2 = ((float) (r0.height * r2)) / ((float) (r0.width * r8));
         */
        /* JADX WARNING: Code restructure failed: missing block: B:198:0x0463, code lost:
            r2 = -1.0f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:200:0x0467, code lost:
            if (r0.hasColorInfo == false) goto L_0x0479;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:201:0x0469, code lost:
            r11 = new com.google.android.exoplayer2.video.ColorInfo(r0.colorSpace, r0.colorRange, r0.colorTransfer, getHdrStaticInfo());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:202:0x0479, code lost:
            r11 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:204:0x047c, code lost:
            if (r0.name == null) goto L_0x049a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:206:0x0488, code lost:
            if (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) == false) goto L_0x049a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:207:0x048a, code lost:
            r5 = ((java.lang.Integer) com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$600().get(r0.name)).intValue();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:209:0x049c, code lost:
            if (r0.projectionType != 0) goto L_0x04ea;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:211:0x04a5, code lost:
            if (java.lang.Float.compare(r0.projectionPoseYaw, 0.0f) != 0) goto L_0x04ea;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:213:0x04ad, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 0.0f) != 0) goto L_0x04ea;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:215:0x04b5, code lost:
            if (java.lang.Float.compare(r0.projectionPoseRoll, 0.0f) != 0) goto L_0x04b8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:217:0x04c0, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 90.0f) != 0) goto L_0x04c5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:218:0x04c2, code lost:
            r4 = 90;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:220:0x04cd, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -180.0f) == 0) goto L_0x04e7;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:222:0x04d7, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, 180.0f) != 0) goto L_0x04da;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:224:0x04e2, code lost:
            if (java.lang.Float.compare(r0.projectionPosePitch, -90.0f) != 0) goto L_0x04ea;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:225:0x04e4, code lost:
            r4 = 270;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:226:0x04e7, code lost:
            r4 = com.alibaba.fastjson.asm.Opcodes.GETFIELD;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:227:0x04ea, code lost:
            r4 = r5;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:228:0x04eb, code lost:
            r10.setWidth(r0.width).setHeight(r0.height).setPixelWidthHeightRatio(r2).setRotationDegrees(r4).setProjectionData(r0.projectionData).setStereoMode(r0.stereoMode).setColorInfo(r11);
            r5 = 2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:230:0x0514, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_SUBRIP.equals(r7) != false) goto L_0x053d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:232:0x051a, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.TEXT_SSA.equals(r7) != false) goto L_0x053d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:234:0x0520, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.TEXT_VTT.equals(r7) != false) goto L_0x053d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:236:0x0526, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_VOBSUB.equals(r7) != false) goto L_0x053d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:238:0x052c, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_PGS.equals(r7) != false) goto L_0x053d;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:240:0x0532, code lost:
            if (com.google.android.exoplayer2.util.MimeTypes.APPLICATION_DVBSUBS.equals(r7) == false) goto L_0x0535;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:242:0x053c, code lost:
            throw com.google.android.exoplayer2.ParserException.createForMalformedContainer("Unexpected MIME type.", (java.lang.Throwable) null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:243:0x053d, code lost:
            r5 = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:245:0x0540, code lost:
            if (r0.name == null) goto L_0x0553;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:247:0x054c, code lost:
            if (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.access$600().containsKey(r0.name) != false) goto L_0x0553;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:248:0x054e, code lost:
            r10.setLabel(r0.name);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:249:0x0553, code lost:
            r1 = r10.setId(r22).setSampleMimeType(r7).setMaxInputSize(r6).setLanguage(r0.language).setSelectionFlags(r9).setInitializationData(r1).setCodecs(r3).setDrmInitData(r0.drmInitData).build();
            r2 = r21.track(r0.number, r5);
            r0.output = r2;
            r2.format(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:250:0x058a, code lost:
            return;
         */
        /* JADX WARNING: Multi-variable type inference failed */
        @org.checkerframework.checker.nullness.qual.RequiresNonNull({"codecId"})
        @org.checkerframework.checker.nullness.qual.EnsuresNonNull({"this.output"})
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r21, int r22) throws com.google.android.exoplayer2.ParserException {
            /*
                r20 = this;
                r0 = r20
                java.lang.String r1 = r0.codecId
                r1.hashCode()
                int r2 = r1.hashCode()
                r3 = 32
                r4 = 16
                r7 = 8
                r8 = 4
                r9 = 3
                switch(r2) {
                    case -2095576542: goto L_0x01be;
                    case -2095575984: goto L_0x01b2;
                    case -1985379776: goto L_0x01a6;
                    case -1784763192: goto L_0x019a;
                    case -1730367663: goto L_0x018e;
                    case -1482641358: goto L_0x0182;
                    case -1482641357: goto L_0x0176;
                    case -1373388978: goto L_0x016a;
                    case -933872740: goto L_0x015d;
                    case -538363189: goto L_0x014f;
                    case -538363109: goto L_0x0141;
                    case -425012669: goto L_0x0133;
                    case -356037306: goto L_0x0125;
                    case 62923557: goto L_0x0117;
                    case 62923603: goto L_0x0109;
                    case 62927045: goto L_0x00fb;
                    case 82318131: goto L_0x00ee;
                    case 82338133: goto L_0x00e0;
                    case 82338134: goto L_0x00d2;
                    case 99146302: goto L_0x00c4;
                    case 444813526: goto L_0x00b6;
                    case 542569478: goto L_0x00a8;
                    case 635596514: goto L_0x009a;
                    case 725948237: goto L_0x008d;
                    case 725957860: goto L_0x0080;
                    case 738597099: goto L_0x0073;
                    case 855502857: goto L_0x0066;
                    case 1045209816: goto L_0x0059;
                    case 1422270023: goto L_0x004c;
                    case 1809237540: goto L_0x003f;
                    case 1950749482: goto L_0x0032;
                    case 1950789798: goto L_0x0025;
                    case 1951062397: goto L_0x0019;
                    default: goto L_0x0016;
                }
            L_0x0016:
                r1 = -1
                goto L_0x01c9
            L_0x0019:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0022
                goto L_0x0016
            L_0x0022:
                r1 = r3
                goto L_0x01c9
            L_0x0025:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x002e
                goto L_0x0016
            L_0x002e:
                r1 = 31
                goto L_0x01c9
            L_0x0032:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x003b
                goto L_0x0016
            L_0x003b:
                r1 = 30
                goto L_0x01c9
            L_0x003f:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0048
                goto L_0x0016
            L_0x0048:
                r1 = 29
                goto L_0x01c9
            L_0x004c:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0055
                goto L_0x0016
            L_0x0055:
                r1 = 28
                goto L_0x01c9
            L_0x0059:
                java.lang.String r2 = "S_TEXT/WEBVTT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0062
                goto L_0x0016
            L_0x0062:
                r1 = 27
                goto L_0x01c9
            L_0x0066:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x006f
                goto L_0x0016
            L_0x006f:
                r1 = 26
                goto L_0x01c9
            L_0x0073:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x007c
                goto L_0x0016
            L_0x007c:
                r1 = 25
                goto L_0x01c9
            L_0x0080:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0089
                goto L_0x0016
            L_0x0089:
                r1 = 24
                goto L_0x01c9
            L_0x008d:
                java.lang.String r2 = "A_PCM/INT/BIG"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0096
                goto L_0x0016
            L_0x0096:
                r1 = 23
                goto L_0x01c9
            L_0x009a:
                java.lang.String r2 = "A_PCM/FLOAT/IEEE"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00a4
                goto L_0x0016
            L_0x00a4:
                r1 = 22
                goto L_0x01c9
            L_0x00a8:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00b2
                goto L_0x0016
            L_0x00b2:
                r1 = 21
                goto L_0x01c9
            L_0x00b6:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00c0
                goto L_0x0016
            L_0x00c0:
                r1 = 20
                goto L_0x01c9
            L_0x00c4:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00ce
                goto L_0x0016
            L_0x00ce:
                r1 = 19
                goto L_0x01c9
            L_0x00d2:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00dc
                goto L_0x0016
            L_0x00dc:
                r1 = 18
                goto L_0x01c9
            L_0x00e0:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00ea
                goto L_0x0016
            L_0x00ea:
                r1 = 17
                goto L_0x01c9
            L_0x00ee:
                java.lang.String r2 = "V_AV1"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x00f8
                goto L_0x0016
            L_0x00f8:
                r1 = r4
                goto L_0x01c9
            L_0x00fb:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0105
                goto L_0x0016
            L_0x0105:
                r1 = 15
                goto L_0x01c9
            L_0x0109:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0113
                goto L_0x0016
            L_0x0113:
                r1 = 14
                goto L_0x01c9
            L_0x0117:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0121
                goto L_0x0016
            L_0x0121:
                r1 = 13
                goto L_0x01c9
            L_0x0125:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x012f
                goto L_0x0016
            L_0x012f:
                r1 = 12
                goto L_0x01c9
            L_0x0133:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x013d
                goto L_0x0016
            L_0x013d:
                r1 = 11
                goto L_0x01c9
            L_0x0141:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x014b
                goto L_0x0016
            L_0x014b:
                r1 = 10
                goto L_0x01c9
            L_0x014f:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0159
                goto L_0x0016
            L_0x0159:
                r1 = 9
                goto L_0x01c9
            L_0x015d:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0167
                goto L_0x0016
            L_0x0167:
                r1 = r7
                goto L_0x01c9
            L_0x016a:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0174
                goto L_0x0016
            L_0x0174:
                r1 = 7
                goto L_0x01c9
            L_0x0176:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0180
                goto L_0x0016
            L_0x0180:
                r1 = 6
                goto L_0x01c9
            L_0x0182:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x018c
                goto L_0x0016
            L_0x018c:
                r1 = 5
                goto L_0x01c9
            L_0x018e:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x0198
                goto L_0x0016
            L_0x0198:
                r1 = r8
                goto L_0x01c9
            L_0x019a:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01a4
                goto L_0x0016
            L_0x01a4:
                r1 = r9
                goto L_0x01c9
            L_0x01a6:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01b0
                goto L_0x0016
            L_0x01b0:
                r1 = 2
                goto L_0x01c9
            L_0x01b2:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01bc
                goto L_0x0016
            L_0x01bc:
                r1 = 1
                goto L_0x01c9
            L_0x01be:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 != 0) goto L_0x01c8
                goto L_0x0016
            L_0x01c8:
                r1 = 0
            L_0x01c9:
                java.lang.String r2 = "application/dvbsubs"
                java.lang.String r12 = "application/pgs"
                java.lang.String r13 = "application/vobsub"
                java.lang.String r14 = "text/vtt"
                java.lang.String r15 = "text/x-ssa"
                java.lang.String r5 = "application/x-subrip"
                r16 = 4096(0x1000, float:5.74E-42)
                java.lang.String r6 = ". Setting mimeType to audio/x-unknown"
                java.lang.String r17 = "audio/raw"
                java.lang.String r11 = "MatroskaExtractor"
                java.lang.String r18 = "audio/x-unknown"
                r10 = 0
                switch(r1) {
                    case 0: goto L_0x03e8;
                    case 1: goto L_0x03e8;
                    case 2: goto L_0x03ab;
                    case 3: goto L_0x039f;
                    case 4: goto L_0x038b;
                    case 5: goto L_0x0384;
                    case 6: goto L_0x0380;
                    case 7: goto L_0x0363;
                    case 8: goto L_0x0350;
                    case 9: goto L_0x03e8;
                    case 10: goto L_0x032d;
                    case 11: goto L_0x0320;
                    case 12: goto L_0x031d;
                    case 13: goto L_0x0300;
                    case 14: goto L_0x02fd;
                    case 15: goto L_0x02fa;
                    case 16: goto L_0x02f7;
                    case 17: goto L_0x02ee;
                    case 18: goto L_0x02eb;
                    case 19: goto L_0x02e8;
                    case 20: goto L_0x02e5;
                    case 21: goto L_0x02fa;
                    case 22: goto L_0x02c9;
                    case 23: goto L_0x02a2;
                    case 24: goto L_0x027b;
                    case 25: goto L_0x0269;
                    case 26: goto L_0x024e;
                    case 27: goto L_0x024a;
                    case 28: goto L_0x0246;
                    case 29: goto L_0x0242;
                    case 30: goto L_0x023e;
                    case 31: goto L_0x0230;
                    case 32: goto L_0x01ea;
                    default: goto L_0x01e3;
                }
            L_0x01e3:
                java.lang.String r1 = "Unrecognized codec identifier."
                com.google.android.exoplayer2.ParserException r1 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r1, r10)
                throw r1
            L_0x01ea:
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>(r9)
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r7)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r9 = r0.codecDelayNs
                java.nio.ByteBuffer r3 = r3.putLong(r9)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.nio.ByteBuffer r3 = java.nio.ByteBuffer.allocate(r7)
                java.nio.ByteOrder r4 = java.nio.ByteOrder.LITTLE_ENDIAN
                java.nio.ByteBuffer r3 = r3.order(r4)
                long r6 = r0.seekPreRollNs
                java.nio.ByteBuffer r3 = r3.putLong(r6)
                byte[] r3 = r3.array()
                r1.add(r3)
                java.lang.String r17 = "audio/opus"
                r16 = 5760(0x1680, float:8.071E-42)
                r6 = r16
                r3 = 0
                r4 = 0
                goto L_0x02f4
            L_0x0230:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                java.lang.String r17 = "audio/flac"
                goto L_0x02f1
            L_0x023e:
                java.lang.String r17 = "audio/eac3"
                goto L_0x02f0
            L_0x0242:
                java.lang.String r17 = "video/mpeg2"
                goto L_0x02f0
            L_0x0246:
                r17 = r5
                goto L_0x02f0
            L_0x024a:
                r17 = r14
                goto L_0x02f0
            L_0x024e:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                com.google.android.exoplayer2.video.HevcConfig r1 = com.google.android.exoplayer2.video.HevcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/hevc"
                goto L_0x0346
            L_0x0269:
                byte[] r1 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1, r3)
                r17 = r15
                goto L_0x02f1
            L_0x027b:
                int r1 = r0.audioBitDepth
                int r8 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r1)
                if (r8 != 0) goto L_0x029c
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported little endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.w(r11, r1)
            L_0x0299:
                r17 = r18
                goto L_0x02f0
            L_0x029c:
                r1 = 0
                r3 = 0
                r4 = 0
            L_0x029f:
                r6 = -1
                goto L_0x03f6
            L_0x02a2:
                int r1 = r0.audioBitDepth
                if (r1 != r7) goto L_0x02ad
                r1 = 0
                r3 = 0
                r4 = 0
                r6 = -1
                r8 = 3
                goto L_0x03f6
            L_0x02ad:
                if (r1 != r4) goto L_0x02b2
                r8 = 268435456(0x10000000, float:2.5243549E-29)
                goto L_0x029c
            L_0x02b2:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported big endian PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.w(r11, r1)
                goto L_0x0299
            L_0x02c9:
                int r1 = r0.audioBitDepth
                if (r1 != r3) goto L_0x02ce
                goto L_0x029c
            L_0x02ce:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported floating point PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.w(r11, r1)
                goto L_0x0299
            L_0x02e5:
                java.lang.String r17 = "video/x-unknown"
                goto L_0x02f0
            L_0x02e8:
                r17 = r12
                goto L_0x02f0
            L_0x02eb:
                java.lang.String r17 = "video/x-vnd.on2.vp9"
                goto L_0x02f0
            L_0x02ee:
                java.lang.String r17 = "video/x-vnd.on2.vp8"
            L_0x02f0:
                r1 = 0
            L_0x02f1:
                r3 = 0
            L_0x02f2:
                r4 = 0
            L_0x02f3:
                r6 = -1
            L_0x02f4:
                r8 = -1
                goto L_0x03f6
            L_0x02f7:
                java.lang.String r17 = "video/av01"
                goto L_0x02f0
            L_0x02fa:
                java.lang.String r17 = "audio/vnd.dts"
                goto L_0x02f0
            L_0x02fd:
                java.lang.String r17 = "audio/ac3"
                goto L_0x02f0
            L_0x0300:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = java.util.Collections.singletonList(r1)
                byte[] r3 = r0.codecPrivate
                com.google.android.exoplayer2.audio.AacUtil$Config r3 = com.google.android.exoplayer2.audio.AacUtil.parseAudioSpecificConfig(r3)
                int r4 = r3.sampleRateHz
                r0.sampleRate = r4
                int r4 = r3.channelCount
                r0.channelCount = r4
                java.lang.String r3 = r3.codecs
                java.lang.String r17 = "audio/mp4a-latm"
                goto L_0x02f2
            L_0x031d:
                java.lang.String r17 = "audio/vnd.dts.hd"
                goto L_0x02f0
            L_0x0320:
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1)
                r17 = r13
                goto L_0x02f1
            L_0x032d:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                com.google.android.exoplayer2.video.AvcConfig r1 = com.google.android.exoplayer2.video.AvcConfig.parse(r1)
                java.util.List<byte[]> r3 = r1.initializationData
                int r4 = r1.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r4
                java.lang.String r1 = r1.codecs
                java.lang.String r17 = "video/avc"
            L_0x0346:
                r4 = 0
                r6 = -1
                r8 = -1
                r19 = r3
                r3 = r1
                r1 = r19
                goto L_0x03f6
            L_0x0350:
                byte[] r1 = new byte[r8]
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r4 = 0
                java.lang.System.arraycopy(r3, r4, r1, r4, r8)
                com.google.common.collect.ImmutableList r1 = com.google.common.collect.ImmutableList.of(r1)
                r17 = r2
                goto L_0x037d
            L_0x0363:
                r4 = 0
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r3 = r1.first
                r17 = r3
                java.lang.String r17 = (java.lang.String) r17
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
            L_0x037d:
                r3 = 0
                goto L_0x02f3
            L_0x0380:
                r4 = 0
                java.lang.String r17 = "audio/mpeg"
                goto L_0x0387
            L_0x0384:
                r4 = 0
                java.lang.String r17 = "audio/mpeg-L2"
            L_0x0387:
                r6 = r16
                r1 = 0
                goto L_0x039c
            L_0x038b:
                r4 = 0
                java.lang.String r1 = r0.codecId
                byte[] r1 = r0.getCodecPrivate(r1)
                java.util.List r1 = parseVorbisCodecPrivate(r1)
                java.lang.String r17 = "audio/vorbis"
                r16 = 8192(0x2000, float:1.14794E-41)
                r6 = r16
            L_0x039c:
                r3 = 0
                goto L_0x02f4
            L_0x039f:
                r4 = 0
                com.google.android.exoplayer2.extractor.TrueHdSampleRechunker r1 = new com.google.android.exoplayer2.extractor.TrueHdSampleRechunker
                r1.<init>()
                r0.trueHdSampleRechunker = r1
                java.lang.String r17 = "audio/true-hd"
            L_0x03a9:
                r1 = 0
                goto L_0x037d
            L_0x03ab:
                r4 = 0
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                java.lang.String r3 = r0.codecId
                byte[] r3 = r0.getCodecPrivate(r3)
                r1.<init>((byte[]) r3)
                boolean r1 = parseMsAcmCodecPrivate(r1)
                if (r1 == 0) goto L_0x03e0
                int r1 = r0.audioBitDepth
                int r8 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r1)
                if (r8 != 0) goto L_0x03dc
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r3 = "Unsupported PCM bit depth: "
                r1.<init>(r3)
                int r3 = r0.audioBitDepth
                r1.append(r3)
                r1.append(r6)
                java.lang.String r1 = r1.toString()
                com.google.android.exoplayer2.util.Log.w(r11, r1)
                goto L_0x03e5
            L_0x03dc:
                r1 = 0
                r3 = 0
                goto L_0x029f
            L_0x03e0:
                java.lang.String r1 = "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown"
                com.google.android.exoplayer2.util.Log.w(r11, r1)
            L_0x03e5:
                r17 = r18
                goto L_0x03a9
            L_0x03e8:
                r4 = 0
                byte[] r1 = r0.codecPrivate
                if (r1 != 0) goto L_0x03ef
                r1 = 0
                goto L_0x03f3
            L_0x03ef:
                java.util.List r1 = java.util.Collections.singletonList(r1)
            L_0x03f3:
                java.lang.String r17 = "video/mp4v-es"
                goto L_0x037d
            L_0x03f6:
                byte[] r7 = r0.dolbyVisionConfigBytes
                if (r7 == 0) goto L_0x040b
                com.google.android.exoplayer2.util.ParsableByteArray r7 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r9 = r0.dolbyVisionConfigBytes
                r7.<init>((byte[]) r9)
                com.google.android.exoplayer2.video.DolbyVisionConfig r7 = com.google.android.exoplayer2.video.DolbyVisionConfig.parse(r7)
                if (r7 == 0) goto L_0x040b
                java.lang.String r3 = r7.codecs
                java.lang.String r17 = "video/dolby-vision"
            L_0x040b:
                r7 = r17
                boolean r9 = r0.flagDefault
                boolean r10 = r0.flagForced
                if (r10 == 0) goto L_0x0415
                r10 = 2
                goto L_0x0416
            L_0x0415:
                r10 = r4
            L_0x0416:
                r9 = r9 | r10
                com.google.android.exoplayer2.Format$Builder r10 = new com.google.android.exoplayer2.Format$Builder
                r10.<init>()
                boolean r11 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r7)
                if (r11 == 0) goto L_0x0434
                int r2 = r0.channelCount
                com.google.android.exoplayer2.Format$Builder r2 = r10.setChannelCount(r2)
                int r4 = r0.sampleRate
                com.google.android.exoplayer2.Format$Builder r2 = r2.setSampleRate(r4)
                r2.setPcmEncoding(r8)
                r5 = 1
                goto L_0x053e
            L_0x0434:
                boolean r8 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r7)
                if (r8 == 0) goto L_0x0510
                int r2 = r0.displayUnit
                if (r2 != 0) goto L_0x0450
                int r2 = r0.displayWidth
                r5 = -1
                if (r2 != r5) goto L_0x0445
                int r2 = r0.width
            L_0x0445:
                r0.displayWidth = r2
                int r2 = r0.displayHeight
                if (r2 != r5) goto L_0x044d
                int r2 = r0.height
            L_0x044d:
                r0.displayHeight = r2
                goto L_0x0451
            L_0x0450:
                r5 = -1
            L_0x0451:
                int r2 = r0.displayWidth
                if (r2 == r5) goto L_0x0463
                int r8 = r0.displayHeight
                if (r8 == r5) goto L_0x0463
                int r11 = r0.height
                int r11 = r11 * r2
                float r2 = (float) r11
                int r11 = r0.width
                int r11 = r11 * r8
                float r8 = (float) r11
                float r2 = r2 / r8
                goto L_0x0465
            L_0x0463:
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x0465:
                boolean r8 = r0.hasColorInfo
                if (r8 == 0) goto L_0x0479
                byte[] r8 = r20.getHdrStaticInfo()
                com.google.android.exoplayer2.video.ColorInfo r11 = new com.google.android.exoplayer2.video.ColorInfo
                int r12 = r0.colorSpace
                int r13 = r0.colorRange
                int r14 = r0.colorTransfer
                r11.<init>(r12, r13, r14, r8)
                goto L_0x047a
            L_0x0479:
                r11 = 0
            L_0x047a:
                java.lang.String r8 = r0.name
                if (r8 == 0) goto L_0x049a
                java.util.Map r8 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r12 = r0.name
                boolean r8 = r8.containsKey(r12)
                if (r8 == 0) goto L_0x049a
                java.util.Map r5 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r8 = r0.name
                java.lang.Object r5 = r5.get(r8)
                java.lang.Integer r5 = (java.lang.Integer) r5
                int r5 = r5.intValue()
            L_0x049a:
                int r8 = r0.projectionType
                if (r8 != 0) goto L_0x04ea
                float r8 = r0.projectionPoseYaw
                r12 = 0
                int r8 = java.lang.Float.compare(r8, r12)
                if (r8 != 0) goto L_0x04ea
                float r8 = r0.projectionPosePitch
                int r8 = java.lang.Float.compare(r8, r12)
                if (r8 != 0) goto L_0x04ea
                float r8 = r0.projectionPoseRoll
                int r8 = java.lang.Float.compare(r8, r12)
                if (r8 != 0) goto L_0x04b8
                goto L_0x04eb
            L_0x04b8:
                float r4 = r0.projectionPosePitch
                r8 = 1119092736(0x42b40000, float:90.0)
                int r4 = java.lang.Float.compare(r4, r8)
                if (r4 != 0) goto L_0x04c5
                r4 = 90
                goto L_0x04eb
            L_0x04c5:
                float r4 = r0.projectionPosePitch
                r8 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r4 = java.lang.Float.compare(r4, r8)
                if (r4 == 0) goto L_0x04e7
                float r4 = r0.projectionPosePitch
                r8 = 1127481344(0x43340000, float:180.0)
                int r4 = java.lang.Float.compare(r4, r8)
                if (r4 != 0) goto L_0x04da
                goto L_0x04e7
            L_0x04da:
                float r4 = r0.projectionPosePitch
                r8 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r4 = java.lang.Float.compare(r4, r8)
                if (r4 != 0) goto L_0x04ea
                r4 = 270(0x10e, float:3.78E-43)
                goto L_0x04eb
            L_0x04e7:
                r4 = 180(0xb4, float:2.52E-43)
                goto L_0x04eb
            L_0x04ea:
                r4 = r5
            L_0x04eb:
                int r5 = r0.width
                com.google.android.exoplayer2.Format$Builder r5 = r10.setWidth(r5)
                int r8 = r0.height
                com.google.android.exoplayer2.Format$Builder r5 = r5.setHeight(r8)
                com.google.android.exoplayer2.Format$Builder r2 = r5.setPixelWidthHeightRatio(r2)
                com.google.android.exoplayer2.Format$Builder r2 = r2.setRotationDegrees(r4)
                byte[] r4 = r0.projectionData
                com.google.android.exoplayer2.Format$Builder r2 = r2.setProjectionData(r4)
                int r4 = r0.stereoMode
                com.google.android.exoplayer2.Format$Builder r2 = r2.setStereoMode(r4)
                r2.setColorInfo(r11)
                r5 = 2
                goto L_0x053e
            L_0x0510:
                boolean r4 = r5.equals(r7)
                if (r4 != 0) goto L_0x053d
                boolean r4 = r15.equals(r7)
                if (r4 != 0) goto L_0x053d
                boolean r4 = r14.equals(r7)
                if (r4 != 0) goto L_0x053d
                boolean r4 = r13.equals(r7)
                if (r4 != 0) goto L_0x053d
                boolean r4 = r12.equals(r7)
                if (r4 != 0) goto L_0x053d
                boolean r2 = r2.equals(r7)
                if (r2 == 0) goto L_0x0535
                goto L_0x053d
            L_0x0535:
                java.lang.String r1 = "Unexpected MIME type."
                r2 = 0
                com.google.android.exoplayer2.ParserException r1 = com.google.android.exoplayer2.ParserException.createForMalformedContainer(r1, r2)
                throw r1
            L_0x053d:
                r5 = 3
            L_0x053e:
                java.lang.String r2 = r0.name
                if (r2 == 0) goto L_0x0553
                java.util.Map r2 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.TRACK_NAME_TO_ROTATION_DEGREES
                java.lang.String r4 = r0.name
                boolean r2 = r2.containsKey(r4)
                if (r2 != 0) goto L_0x0553
                java.lang.String r2 = r0.name
                r10.setLabel(r2)
            L_0x0553:
                r2 = r22
                com.google.android.exoplayer2.Format$Builder r2 = r10.setId((int) r2)
                com.google.android.exoplayer2.Format$Builder r2 = r2.setSampleMimeType(r7)
                com.google.android.exoplayer2.Format$Builder r2 = r2.setMaxInputSize(r6)
                java.lang.String r4 = r0.language
                com.google.android.exoplayer2.Format$Builder r2 = r2.setLanguage(r4)
                com.google.android.exoplayer2.Format$Builder r2 = r2.setSelectionFlags(r9)
                com.google.android.exoplayer2.Format$Builder r1 = r2.setInitializationData(r1)
                com.google.android.exoplayer2.Format$Builder r1 = r1.setCodecs(r3)
                com.google.android.exoplayer2.drm.DrmInitData r2 = r0.drmInitData
                com.google.android.exoplayer2.Format$Builder r1 = r1.setDrmInitData(r2)
                com.google.android.exoplayer2.Format r1 = r1.build()
                int r2 = r0.number
                r3 = r21
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r3.track(r2, r5)
                r0.output = r2
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        @RequiresNonNull({"output"})
        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this.output, this.cryptoData);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        /* access modifiers changed from: private */
        public boolean samplesHaveSupplementalData(boolean z) {
            if (MatroskaExtractor.CODEC_ID_OPUS.equals(this.codecId)) {
                return z;
            }
            return this.maxBlockAdditionId > 0;
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer order = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            order.put((byte) 0);
            order.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            order.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            order.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            order.putShort((short) this.maxContentLuminance);
            order.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_DIVX, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 859189832) {
                    return new Pair<>(MimeTypes.VIDEO_H263, (Object) null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] data = parsableByteArray.getData();
                    for (int position = parsableByteArray.getPosition() + 20; position < data.length - 4; position++) {
                        if (data[position] == 0 && data[position + 1] == 0 && data[position + 2] == 1 && data[position + 3] == 15) {
                            return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(data, position, data.length)));
                        }
                    }
                    throw ParserException.createForMalformedContainer("Failed to find FourCC VC1 initialization data", (Throwable) null);
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>(MimeTypes.VIDEO_UNKNOWN, (Object) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing FourCC private data", (Throwable) null);
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            byte b2;
            byte b3;
            try {
                if (bArr[0] == 2) {
                    int i = 0;
                    int i2 = 1;
                    while (true) {
                        b2 = bArr[i2];
                        if ((b2 & 255) != 255) {
                            break;
                        }
                        i += 255;
                        i2++;
                    }
                    int i3 = i2 + 1;
                    int i4 = i + (b2 & 255);
                    int i5 = 0;
                    while (true) {
                        b3 = bArr[i3];
                        if ((b3 & 255) != 255) {
                            break;
                        }
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + (b3 & 255);
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                        }
                        throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                    }
                    throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
                }
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing vorbis codec private", (Throwable) null);
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != 65534) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits()) {
                    return true;
                }
                return false;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw ParserException.createForMalformedContainer("Error parsing MS/ACM codec private", (Throwable) null);
            }
        }

        /* access modifiers changed from: private */
        @EnsuresNonNull({"output"})
        public void assertOutputInitialized() {
            Assertions.checkNotNull(this.output);
        }

        @EnsuresNonNull({"codecPrivate"})
        private byte[] getCodecPrivate(String str) throws ParserException {
            byte[] bArr = this.codecPrivate;
            if (bArr != null) {
                return bArr;
            }
            throw ParserException.createForMalformedContainer("Missing CodecPrivate for codec " + str, (Throwable) null);
        }
    }
}
