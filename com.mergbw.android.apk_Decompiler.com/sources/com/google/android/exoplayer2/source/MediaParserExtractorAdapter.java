package com.google.android.exoplayer2.source;

import android.media.MediaParser;
import android.net.Uri;
import android.util.Pair;
import com.google.android.exoplayer2.analytics.PlayerId;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.source.ProgressiveMediaExtractor;
import com.google.android.exoplayer2.source.mediaparser.InputReaderAdapterV30;
import com.google.android.exoplayer2.source.mediaparser.MediaParserUtil;
import com.google.android.exoplayer2.source.mediaparser.OutputConsumerAdapterV30;
import com.google.android.exoplayer2.upstream.DataReader;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class MediaParserExtractorAdapter implements ProgressiveMediaExtractor {
    public static final ProgressiveMediaExtractor.Factory FACTORY = new MediaParserExtractorAdapter$$ExternalSyntheticLambda8();
    private final InputReaderAdapterV30 inputReaderAdapter = new InputReaderAdapterV30();
    private final MediaParser mediaParser;
    private final OutputConsumerAdapterV30 outputConsumerAdapter;
    private String parserName;

    public MediaParserExtractorAdapter(PlayerId playerId) {
        OutputConsumerAdapterV30 outputConsumerAdapterV30 = new OutputConsumerAdapterV30();
        this.outputConsumerAdapter = outputConsumerAdapterV30;
        MediaParser m = MediaParser.create(outputConsumerAdapterV30, new String[0]);
        this.mediaParser = m;
        MediaParser unused = m.setParameter(MediaParserUtil.PARAMETER_EAGERLY_EXPOSE_TRACK_TYPE, true);
        MediaParser unused2 = m.setParameter(MediaParserUtil.PARAMETER_IN_BAND_CRYPTO_INFO, true);
        MediaParser unused3 = m.setParameter(MediaParserUtil.PARAMETER_INCLUDE_SUPPLEMENTAL_DATA, true);
        this.parserName = "android.media.mediaparser.UNKNOWN";
        if (Util.SDK_INT >= 31) {
            MediaParserUtil.setLogSessionIdOnMediaParser(m, playerId);
        }
    }

    public void init(DataReader dataReader, Uri uri, Map<String, List<String>> map, long j, long j2, ExtractorOutput extractorOutput) throws IOException {
        this.outputConsumerAdapter.setExtractorOutput(extractorOutput);
        this.inputReaderAdapter.setDataReader(dataReader, j2);
        this.inputReaderAdapter.setCurrentPosition(j);
        String m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(this.mediaParser);
        if ("android.media.mediaparser.UNKNOWN".equals(m)) {
            boolean unused = this.mediaParser.advance(this.inputReaderAdapter);
            String m2 = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(this.mediaParser);
            this.parserName = m2;
            this.outputConsumerAdapter.setSelectedParserName(m2);
        } else if (!m.equals(this.parserName)) {
            String m3 = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(this.mediaParser);
            this.parserName = m3;
            this.outputConsumerAdapter.setSelectedParserName(m3);
        }
    }

    public void release() {
        FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(this.mediaParser);
    }

    public void disableSeekingOnMp3Streams() {
        if ("android.media.mediaparser.Mp3Parser".equals(this.parserName)) {
            this.outputConsumerAdapter.disableSeeking();
        }
    }

    public long getCurrentInputPosition() {
        return this.inputReaderAdapter.getPosition();
    }

    public void seek(long j, long j2) {
        this.inputReaderAdapter.setCurrentPosition(j);
        Pair<MediaParser.SeekPoint, MediaParser.SeekPoint> seekPoints = this.outputConsumerAdapter.getSeekPoints(j2);
        this.mediaParser.seek(FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(seekPoints.second).position == j ? FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(seekPoints.second) : FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(seekPoints.first));
    }

    public int read(PositionHolder positionHolder) throws IOException {
        boolean m = this.mediaParser.advance(this.inputReaderAdapter);
        positionHolder.position = this.inputReaderAdapter.getAndResetSeekPosition();
        if (!m) {
            return -1;
        }
        return positionHolder.position != -1 ? 1 : 0;
    }
}
