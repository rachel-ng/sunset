package com.google.android.exoplayer2.source.rtsp.reader;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.source.rtsp.RtpPacket;
import com.google.android.exoplayer2.source.rtsp.RtpPayloadFormat;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.common.primitives.Bytes;

final class RtpMpeg4Reader implements RtpPayloadReader {
    private static final int I_VOP = 0;
    private static final int MEDIA_CLOCK_FREQUENCY = 90000;
    private static final String TAG = "RtpMpeg4Reader";
    private int bufferFlags;
    private long firstReceivedTimestamp = C.TIME_UNSET;
    private final RtpPayloadFormat payloadFormat;
    private int previousSequenceNumber = -1;
    private int sampleLength;
    private long startTimeOffsetUs;
    private TrackOutput trackOutput;

    public void onReceivingFirstPacket(long j, int i) {
    }

    public RtpMpeg4Reader(RtpPayloadFormat rtpPayloadFormat) {
        this.payloadFormat = rtpPayloadFormat;
    }

    public void createTracks(ExtractorOutput extractorOutput, int i) {
        TrackOutput track = extractorOutput.track(i, 2);
        this.trackOutput = track;
        ((TrackOutput) Util.castNonNull(track)).format(this.payloadFormat.format);
    }

    public void consume(ParsableByteArray parsableByteArray, long j, int i, boolean z) {
        int nextSequenceNumber;
        int i2 = i;
        Assertions.checkStateNotNull(this.trackOutput);
        int i3 = this.previousSequenceNumber;
        if (!(i3 == -1 || i2 == (nextSequenceNumber = RtpPacket.getNextSequenceNumber(i3)))) {
            Log.w(TAG, Util.formatInvariant("Received RTP packet with unexpected sequence number. Expected: %d; received: %d. Dropping packet.", Integer.valueOf(nextSequenceNumber), Integer.valueOf(i)));
        }
        int bytesLeft = parsableByteArray.bytesLeft();
        this.trackOutput.sampleData(parsableByteArray, bytesLeft);
        if (this.sampleLength == 0) {
            this.bufferFlags = getBufferFlagsFromVop(parsableByteArray);
        }
        this.sampleLength += bytesLeft;
        if (z) {
            if (this.firstReceivedTimestamp == C.TIME_UNSET) {
                this.firstReceivedTimestamp = j;
            } else {
                long j2 = j;
            }
            this.trackOutput.sampleMetadata(RtpReaderUtils.toSampleTimeUs(this.startTimeOffsetUs, j, this.firstReceivedTimestamp, MEDIA_CLOCK_FREQUENCY), this.bufferFlags, this.sampleLength, 0, (TrackOutput.CryptoData) null);
            this.sampleLength = 0;
        }
        this.previousSequenceNumber = i2;
    }

    public void seek(long j, long j2) {
        this.firstReceivedTimestamp = j;
        this.startTimeOffsetUs = j2;
        this.sampleLength = 0;
    }

    private static int getBufferFlagsFromVop(ParsableByteArray parsableByteArray) {
        int indexOf = Bytes.indexOf(parsableByteArray.getData(), new byte[]{0, 0, 1, -74});
        if (indexOf == -1) {
            return 0;
        }
        parsableByteArray.setPosition(indexOf + 4);
        if ((parsableByteArray.peekUnsignedByte() >> 6) == 0) {
            return 1;
        }
        return 0;
    }
}
