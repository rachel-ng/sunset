package com.google.android.exoplayer2.analytics;

import android.content.Context;
import android.media.DeniedByServerException;
import android.media.MediaCodec;
import android.media.MediaDrm;
import android.media.MediaDrmResetException;
import android.media.NotProvisionedException;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Pair;
import com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.analytics.PlaybackSessionManager;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.mediacodec.MediaCodecDecoderException;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.upstream.FileDataSource;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.UdpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NetworkTypeObserver;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class MediaMetricsListener implements AnalyticsListener, PlaybackSessionManager.Listener {
    private String activeSessionId;
    private int audioUnderruns;
    private final HashMap<String, Long> bandwidthBytes = new HashMap<>();
    private final HashMap<String, Long> bandwidthTimeMs = new HashMap<>();
    private final Context context;
    private Format currentAudioFormat;
    private int currentNetworkType = 0;
    private int currentPlaybackState = 0;
    private Format currentTextFormat;
    private Format currentVideoFormat;
    private int discontinuityReason;
    private int droppedFrames;
    private boolean hasFatalError;
    private int ioErrorType;
    private boolean isSeeking;
    private PlaybackMetrics.Builder metricsBuilder;
    private PendingFormatUpdate pendingAudioFormat;
    private PlaybackException pendingPlayerError;
    private PendingFormatUpdate pendingTextFormat;
    private PendingFormatUpdate pendingVideoFormat;
    private final Timeline.Period period = new Timeline.Period();
    private final PlaybackSession playbackSession;
    private int playedFrames;
    private boolean reportedEventsForCurrentSession;
    private final PlaybackSessionManager sessionManager;
    private final long startTimeMs = SystemClock.elapsedRealtime();
    private final Timeline.Window window = new Timeline.Window();

    private static int getTrackChangeReason(int i) {
        if (i == 1) {
            return 2;
        }
        if (i != 2) {
            return i != 3 ? 1 : 4;
        }
        return 3;
    }

    public void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2) {
    }

    public /* synthetic */ void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
        AnalyticsListener.CC.$default$onAudioAttributesChanged(this, eventTime, audioAttributes);
    }

    public /* synthetic */ void onAudioCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
        AnalyticsListener.CC.$default$onAudioCodecError(this, eventTime, exc);
    }

    public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
        AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j);
    }

    public /* synthetic */ void onAudioDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
        AnalyticsListener.CC.$default$onAudioDecoderInitialized(this, eventTime, str, j, j2);
    }

    public /* synthetic */ void onAudioDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        AnalyticsListener.CC.$default$onAudioDecoderReleased(this, eventTime, str);
    }

    public /* synthetic */ void onAudioDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        AnalyticsListener.CC.$default$onAudioDisabled(this, eventTime, decoderCounters);
    }

    public /* synthetic */ void onAudioEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        AnalyticsListener.CC.$default$onAudioEnabled(this, eventTime, decoderCounters);
    }

    public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
        AnalyticsListener.CC.$default$onAudioInputFormatChanged(this, eventTime, format);
    }

    public /* synthetic */ void onAudioInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.CC.$default$onAudioInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
    }

    public /* synthetic */ void onAudioPositionAdvancing(AnalyticsListener.EventTime eventTime, long j) {
        AnalyticsListener.CC.$default$onAudioPositionAdvancing(this, eventTime, j);
    }

    public /* synthetic */ void onAudioSessionIdChanged(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onAudioSessionIdChanged(this, eventTime, i);
    }

    public /* synthetic */ void onAudioSinkError(AnalyticsListener.EventTime eventTime, Exception exc) {
        AnalyticsListener.CC.$default$onAudioSinkError(this, eventTime, exc);
    }

    public /* synthetic */ void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        AnalyticsListener.CC.$default$onAudioUnderrun(this, eventTime, i, j, j2);
    }

    public /* synthetic */ void onAvailableCommandsChanged(AnalyticsListener.EventTime eventTime, Player.Commands commands) {
        AnalyticsListener.CC.$default$onAvailableCommandsChanged(this, eventTime, commands);
    }

    public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, CueGroup cueGroup) {
        AnalyticsListener.CC.$default$onCues((AnalyticsListener) this, eventTime, cueGroup);
    }

    public /* synthetic */ void onCues(AnalyticsListener.EventTime eventTime, List list) {
        AnalyticsListener.CC.$default$onCues((AnalyticsListener) this, eventTime, list);
    }

    public /* synthetic */ void onDecoderDisabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
        AnalyticsListener.CC.$default$onDecoderDisabled(this, eventTime, i, decoderCounters);
    }

    public /* synthetic */ void onDecoderEnabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
        AnalyticsListener.CC.$default$onDecoderEnabled(this, eventTime, i, decoderCounters);
    }

    public /* synthetic */ void onDecoderInitialized(AnalyticsListener.EventTime eventTime, int i, String str, long j) {
        AnalyticsListener.CC.$default$onDecoderInitialized(this, eventTime, i, str, j);
    }

    public /* synthetic */ void onDecoderInputFormatChanged(AnalyticsListener.EventTime eventTime, int i, Format format) {
        AnalyticsListener.CC.$default$onDecoderInputFormatChanged(this, eventTime, i, format);
    }

    public /* synthetic */ void onDeviceInfoChanged(AnalyticsListener.EventTime eventTime, DeviceInfo deviceInfo) {
        AnalyticsListener.CC.$default$onDeviceInfoChanged(this, eventTime, deviceInfo);
    }

    public /* synthetic */ void onDeviceVolumeChanged(AnalyticsListener.EventTime eventTime, int i, boolean z) {
        AnalyticsListener.CC.$default$onDeviceVolumeChanged(this, eventTime, i, z);
    }

    public /* synthetic */ void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onDrmKeysLoaded(this, eventTime);
    }

    public /* synthetic */ void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onDrmKeysRemoved(this, eventTime);
    }

    public /* synthetic */ void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onDrmKeysRestored(this, eventTime);
    }

    public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime);
    }

    public /* synthetic */ void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onDrmSessionAcquired(this, eventTime, i);
    }

    public /* synthetic */ void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        AnalyticsListener.CC.$default$onDrmSessionManagerError(this, eventTime, exc);
    }

    public /* synthetic */ void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onDrmSessionReleased(this, eventTime);
    }

    public /* synthetic */ void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
        AnalyticsListener.CC.$default$onDroppedVideoFrames(this, eventTime, i, j);
    }

    public /* synthetic */ void onIsLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        AnalyticsListener.CC.$default$onIsLoadingChanged(this, eventTime, z);
    }

    public /* synthetic */ void onIsPlayingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        AnalyticsListener.CC.$default$onIsPlayingChanged(this, eventTime, z);
    }

    public /* synthetic */ void onLoadCanceled(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.CC.$default$onLoadCanceled(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadCompleted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.CC.$default$onLoadCompleted(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadStarted(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
        AnalyticsListener.CC.$default$onLoadStarted(this, eventTime, loadEventInfo, mediaLoadData);
    }

    public /* synthetic */ void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        AnalyticsListener.CC.$default$onLoadingChanged(this, eventTime, z);
    }

    public /* synthetic */ void onMaxSeekToPreviousPositionChanged(AnalyticsListener.EventTime eventTime, long j) {
        AnalyticsListener.CC.$default$onMaxSeekToPreviousPositionChanged(this, eventTime, j);
    }

    public /* synthetic */ void onMediaItemTransition(AnalyticsListener.EventTime eventTime, MediaItem mediaItem, int i) {
        AnalyticsListener.CC.$default$onMediaItemTransition(this, eventTime, mediaItem, i);
    }

    public /* synthetic */ void onMediaMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
        AnalyticsListener.CC.$default$onMediaMetadataChanged(this, eventTime, mediaMetadata);
    }

    public /* synthetic */ void onMetadata(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        AnalyticsListener.CC.$default$onMetadata(this, eventTime, metadata);
    }

    public /* synthetic */ void onPlayWhenReadyChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
        AnalyticsListener.CC.$default$onPlayWhenReadyChanged(this, eventTime, z, i);
    }

    public /* synthetic */ void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        AnalyticsListener.CC.$default$onPlaybackParametersChanged(this, eventTime, playbackParameters);
    }

    public /* synthetic */ void onPlaybackStateChanged(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onPlaybackStateChanged(this, eventTime, i);
    }

    public /* synthetic */ void onPlaybackSuppressionReasonChanged(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onPlaybackSuppressionReasonChanged(this, eventTime, i);
    }

    public /* synthetic */ void onPlayerErrorChanged(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        AnalyticsListener.CC.$default$onPlayerErrorChanged(this, eventTime, playbackException);
    }

    public /* synthetic */ void onPlayerReleased(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onPlayerReleased(this, eventTime);
    }

    public /* synthetic */ void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
        AnalyticsListener.CC.$default$onPlayerStateChanged(this, eventTime, z, i);
    }

    public /* synthetic */ void onPlaylistMetadataChanged(AnalyticsListener.EventTime eventTime, MediaMetadata mediaMetadata) {
        AnalyticsListener.CC.$default$onPlaylistMetadataChanged(this, eventTime, mediaMetadata);
    }

    public /* synthetic */ void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onPositionDiscontinuity(this, eventTime, i);
    }

    public /* synthetic */ void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Object obj, long j) {
        AnalyticsListener.CC.$default$onRenderedFirstFrame(this, eventTime, obj, j);
    }

    public /* synthetic */ void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onRepeatModeChanged(this, eventTime, i);
    }

    public /* synthetic */ void onSeekBackIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
        AnalyticsListener.CC.$default$onSeekBackIncrementChanged(this, eventTime, j);
    }

    public /* synthetic */ void onSeekForwardIncrementChanged(AnalyticsListener.EventTime eventTime, long j) {
        AnalyticsListener.CC.$default$onSeekForwardIncrementChanged(this, eventTime, j);
    }

    public /* synthetic */ void onSeekProcessed(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onSeekProcessed(this, eventTime);
    }

    public /* synthetic */ void onSeekStarted(AnalyticsListener.EventTime eventTime) {
        AnalyticsListener.CC.$default$onSeekStarted(this, eventTime);
    }

    public void onSessionCreated(AnalyticsListener.EventTime eventTime, String str) {
    }

    public /* synthetic */ void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        AnalyticsListener.CC.$default$onShuffleModeChanged(this, eventTime, z);
    }

    public /* synthetic */ void onSkipSilenceEnabledChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        AnalyticsListener.CC.$default$onSkipSilenceEnabledChanged(this, eventTime, z);
    }

    public /* synthetic */ void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
        AnalyticsListener.CC.$default$onSurfaceSizeChanged(this, eventTime, i, i2);
    }

    public /* synthetic */ void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
        AnalyticsListener.CC.$default$onTimelineChanged(this, eventTime, i);
    }

    public /* synthetic */ void onTrackSelectionParametersChanged(AnalyticsListener.EventTime eventTime, TrackSelectionParameters trackSelectionParameters) {
        AnalyticsListener.CC.$default$onTrackSelectionParametersChanged(this, eventTime, trackSelectionParameters);
    }

    public /* synthetic */ void onTracksChanged(AnalyticsListener.EventTime eventTime, Tracks tracks) {
        AnalyticsListener.CC.$default$onTracksChanged(this, eventTime, tracks);
    }

    public /* synthetic */ void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        AnalyticsListener.CC.$default$onUpstreamDiscarded(this, eventTime, mediaLoadData);
    }

    public /* synthetic */ void onVideoCodecError(AnalyticsListener.EventTime eventTime, Exception exc) {
        AnalyticsListener.CC.$default$onVideoCodecError(this, eventTime, exc);
    }

    public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j) {
        AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j);
    }

    public /* synthetic */ void onVideoDecoderInitialized(AnalyticsListener.EventTime eventTime, String str, long j, long j2) {
        AnalyticsListener.CC.$default$onVideoDecoderInitialized(this, eventTime, str, j, j2);
    }

    public /* synthetic */ void onVideoDecoderReleased(AnalyticsListener.EventTime eventTime, String str) {
        AnalyticsListener.CC.$default$onVideoDecoderReleased(this, eventTime, str);
    }

    public /* synthetic */ void onVideoEnabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        AnalyticsListener.CC.$default$onVideoEnabled(this, eventTime, decoderCounters);
    }

    public /* synthetic */ void onVideoFrameProcessingOffset(AnalyticsListener.EventTime eventTime, long j, int i) {
        AnalyticsListener.CC.$default$onVideoFrameProcessingOffset(this, eventTime, j, i);
    }

    public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format) {
        AnalyticsListener.CC.$default$onVideoInputFormatChanged(this, eventTime, format);
    }

    public /* synthetic */ void onVideoInputFormatChanged(AnalyticsListener.EventTime eventTime, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        AnalyticsListener.CC.$default$onVideoInputFormatChanged(this, eventTime, format, decoderReuseEvaluation);
    }

    public /* synthetic */ void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
        AnalyticsListener.CC.$default$onVideoSizeChanged(this, eventTime, i, i2, i3, f);
    }

    public /* synthetic */ void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
        AnalyticsListener.CC.$default$onVolumeChanged(this, eventTime, f);
    }

    public static MediaMetricsListener create(Context context2) {
        MediaMetricsManager m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(context2.getSystemService("media_metrics"));
        if (m == null) {
            return null;
        }
        return new MediaMetricsListener(context2, m.createPlaybackSession());
    }

    private MediaMetricsListener(Context context2, PlaybackSession playbackSession2) {
        this.context = context2.getApplicationContext();
        this.playbackSession = playbackSession2;
        DefaultPlaybackSessionManager defaultPlaybackSessionManager = new DefaultPlaybackSessionManager();
        this.sessionManager = defaultPlaybackSessionManager;
        defaultPlaybackSessionManager.setListener(this);
    }

    public LogSessionId getLogSessionId() {
        return this.playbackSession.getSessionId();
    }

    public void onSessionActive(AnalyticsListener.EventTime eventTime, String str) {
        if (eventTime.mediaPeriodId == null || !eventTime.mediaPeriodId.isAd()) {
            finishCurrentSession();
            this.activeSessionId = str;
            this.metricsBuilder = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m().setPlayerName(ExoPlayerLibraryInfo.TAG).setPlayerVersion(ExoPlayerLibraryInfo.VERSION);
            maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
        }
    }

    public void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z) {
        if ((eventTime.mediaPeriodId == null || !eventTime.mediaPeriodId.isAd()) && str.equals(this.activeSessionId)) {
            finishCurrentSession();
        }
        this.bandwidthTimeMs.remove(str);
        this.bandwidthBytes.remove(str);
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i) {
        if (i == 1) {
            this.isSeeking = true;
        }
        this.discontinuityReason = i;
    }

    public void onVideoDisabled(AnalyticsListener.EventTime eventTime, DecoderCounters decoderCounters) {
        this.droppedFrames += decoderCounters.droppedBufferCount;
        this.playedFrames += decoderCounters.renderedOutputBufferCount;
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        long j3;
        if (eventTime.mediaPeriodId != null) {
            String sessionForMediaPeriodId = this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(eventTime.mediaPeriodId));
            Long l = this.bandwidthBytes.get(sessionForMediaPeriodId);
            Long l2 = this.bandwidthTimeMs.get(sessionForMediaPeriodId);
            HashMap<String, Long> hashMap = this.bandwidthBytes;
            long j4 = 0;
            if (l == null) {
                j3 = 0;
            } else {
                j3 = l.longValue();
            }
            hashMap.put(sessionForMediaPeriodId, Long.valueOf(j3 + j));
            HashMap<String, Long> hashMap2 = this.bandwidthTimeMs;
            if (l2 != null) {
                j4 = l2.longValue();
            }
            hashMap2.put(sessionForMediaPeriodId, Long.valueOf(j4 + ((long) i)));
        }
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaLoadData mediaLoadData) {
        if (eventTime.mediaPeriodId != null) {
            PendingFormatUpdate pendingFormatUpdate = new PendingFormatUpdate((Format) Assertions.checkNotNull(mediaLoadData.trackFormat), mediaLoadData.trackSelectionReason, this.sessionManager.getSessionForMediaPeriodId(eventTime.timeline, (MediaSource.MediaPeriodId) Assertions.checkNotNull(eventTime.mediaPeriodId)));
            int i = mediaLoadData.trackType;
            if (i != 0) {
                if (i == 1) {
                    this.pendingAudioFormat = pendingFormatUpdate;
                    return;
                } else if (i != 2) {
                    if (i == 3) {
                        this.pendingTextFormat = pendingFormatUpdate;
                        return;
                    }
                    return;
                }
            }
            this.pendingVideoFormat = pendingFormatUpdate;
        }
    }

    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, VideoSize videoSize) {
        PendingFormatUpdate pendingFormatUpdate = this.pendingVideoFormat;
        if (pendingFormatUpdate != null && pendingFormatUpdate.format.height == -1) {
            this.pendingVideoFormat = new PendingFormatUpdate(pendingFormatUpdate.format.buildUpon().setWidth(videoSize.width).setHeight(videoSize.height).build(), pendingFormatUpdate.selectionReason, pendingFormatUpdate.sessionId);
        }
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.ioErrorType = mediaLoadData.dataType;
    }

    public void onPlayerError(AnalyticsListener.EventTime eventTime, PlaybackException playbackException) {
        this.pendingPlayerError = playbackException;
    }

    public void onEvents(Player player, AnalyticsListener.Events events) {
        if (events.size() != 0) {
            maybeAddSessions(events);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            maybeUpdateMetricsBuilderValues(player, events);
            maybeReportPlaybackError(elapsedRealtime);
            maybeReportTrackChanges(player, events, elapsedRealtime);
            maybeReportNetworkChange(elapsedRealtime);
            maybeReportPlaybackStateChange(player, events, elapsedRealtime);
            if (events.contains(AnalyticsListener.EVENT_PLAYER_RELEASED)) {
                this.sessionManager.finishAllSessions(events.getEventTime(AnalyticsListener.EVENT_PLAYER_RELEASED));
            }
        }
    }

    private void maybeAddSessions(AnalyticsListener.Events events) {
        for (int i = 0; i < events.size(); i++) {
            int i2 = events.get(i);
            AnalyticsListener.EventTime eventTime = events.getEventTime(i2);
            if (i2 == 0) {
                this.sessionManager.updateSessionsWithTimelineChange(eventTime);
            } else if (i2 == 11) {
                this.sessionManager.updateSessionsWithDiscontinuity(eventTime, this.discontinuityReason);
            } else {
                this.sessionManager.updateSessions(eventTime);
            }
        }
    }

    private void maybeUpdateMetricsBuilderValues(Player player, AnalyticsListener.Events events) {
        DrmInitData drmInitData;
        if (events.contains(0)) {
            AnalyticsListener.EventTime eventTime = events.getEventTime(0);
            if (this.metricsBuilder != null) {
                maybeUpdateTimelineMetadata(eventTime.timeline, eventTime.mediaPeriodId);
            }
        }
        if (!(!events.contains(2) || this.metricsBuilder == null || (drmInitData = getDrmInitData(player.getCurrentTracks().getGroups())) == null)) {
            PlaybackMetrics.Builder unused = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(Util.castNonNull(this.metricsBuilder)).setDrmType(getDrmType(drmInitData));
        }
        if (events.contains(1011)) {
            this.audioUnderruns++;
        }
    }

    private void maybeReportPlaybackError(long j) {
        PlaybackException playbackException = this.pendingPlayerError;
        if (playbackException != null) {
            ErrorInfo errorInfo = getErrorInfo(playbackException, this.context, this.ioErrorType == 4);
            this.playbackSession.reportPlaybackErrorEvent(DiskLruCache$$ExternalSyntheticApiModelOutline0.m().setTimeSinceCreatedMillis(j - this.startTimeMs).setErrorCode(errorInfo.errorCode).setSubErrorCode(errorInfo.subErrorCode).setException(playbackException).build());
            this.reportedEventsForCurrentSession = true;
            this.pendingPlayerError = null;
        }
    }

    private void maybeReportTrackChanges(Player player, AnalyticsListener.Events events, long j) {
        if (events.contains(2)) {
            Tracks currentTracks = player.getCurrentTracks();
            boolean isTypeSelected = currentTracks.isTypeSelected(2);
            boolean isTypeSelected2 = currentTracks.isTypeSelected(1);
            boolean isTypeSelected3 = currentTracks.isTypeSelected(3);
            if (isTypeSelected || isTypeSelected2 || isTypeSelected3) {
                if (!isTypeSelected) {
                    maybeUpdateVideoFormat(j, (Format) null, 0);
                }
                if (!isTypeSelected2) {
                    maybeUpdateAudioFormat(j, (Format) null, 0);
                }
                if (!isTypeSelected3) {
                    maybeUpdateTextFormat(j, (Format) null, 0);
                }
            }
        }
        if (canReportPendingFormatUpdate(this.pendingVideoFormat) && this.pendingVideoFormat.format.height != -1) {
            maybeUpdateVideoFormat(j, this.pendingVideoFormat.format, this.pendingVideoFormat.selectionReason);
            this.pendingVideoFormat = null;
        }
        if (canReportPendingFormatUpdate(this.pendingAudioFormat)) {
            maybeUpdateAudioFormat(j, this.pendingAudioFormat.format, this.pendingAudioFormat.selectionReason);
            this.pendingAudioFormat = null;
        }
        if (canReportPendingFormatUpdate(this.pendingTextFormat)) {
            maybeUpdateTextFormat(j, this.pendingTextFormat.format, this.pendingTextFormat.selectionReason);
            this.pendingTextFormat = null;
        }
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    private boolean canReportPendingFormatUpdate(PendingFormatUpdate pendingFormatUpdate) {
        return pendingFormatUpdate != null && pendingFormatUpdate.sessionId.equals(this.sessionManager.getActiveSessionId());
    }

    private void maybeReportNetworkChange(long j) {
        int networkType = getNetworkType(this.context);
        if (networkType != this.currentNetworkType) {
            this.currentNetworkType = networkType;
            this.playbackSession.reportNetworkEvent(FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m().setNetworkType(networkType).setTimeSinceCreatedMillis(j - this.startTimeMs).build());
        }
    }

    private void maybeReportPlaybackStateChange(Player player, AnalyticsListener.Events events, long j) {
        if (player.getPlaybackState() != 2) {
            this.isSeeking = false;
        }
        if (player.getPlayerError() == null) {
            this.hasFatalError = false;
        } else if (events.contains(10)) {
            this.hasFatalError = true;
        }
        int resolveNewPlaybackState = resolveNewPlaybackState(player);
        if (this.currentPlaybackState != resolveNewPlaybackState) {
            this.currentPlaybackState = resolveNewPlaybackState;
            this.reportedEventsForCurrentSession = true;
            this.playbackSession.reportPlaybackStateEvent(FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m().setState(this.currentPlaybackState).setTimeSinceCreatedMillis(j - this.startTimeMs).build());
        }
    }

    private int resolveNewPlaybackState(Player player) {
        int playbackState = player.getPlaybackState();
        if (this.isSeeking) {
            return 5;
        }
        if (this.hasFatalError) {
            return 13;
        }
        if (playbackState == 4) {
            return 11;
        }
        if (playbackState == 2) {
            int i = this.currentPlaybackState;
            if (i == 0 || i == 2) {
                return 2;
            }
            if (!player.getPlayWhenReady()) {
                return 7;
            }
            return player.getPlaybackSuppressionReason() != 0 ? 10 : 6;
        } else if (playbackState == 3) {
            if (!player.getPlayWhenReady()) {
                return 4;
            }
            if (player.getPlaybackSuppressionReason() != 0) {
                return 9;
            }
            return 3;
        } else if (playbackState != 1 || this.currentPlaybackState == 0) {
            return this.currentPlaybackState;
        } else {
            return 12;
        }
    }

    private void maybeUpdateVideoFormat(long j, Format format, int i) {
        if (!Util.areEqual(this.currentVideoFormat, format)) {
            if (this.currentVideoFormat == null && i == 0) {
                i = 1;
            }
            this.currentVideoFormat = format;
            reportTrackChangeEvent(1, j, format, i);
        }
    }

    private void maybeUpdateAudioFormat(long j, Format format, int i) {
        if (!Util.areEqual(this.currentAudioFormat, format)) {
            if (this.currentAudioFormat == null && i == 0) {
                i = 1;
            }
            this.currentAudioFormat = format;
            reportTrackChangeEvent(0, j, format, i);
        }
    }

    private void maybeUpdateTextFormat(long j, Format format, int i) {
        if (!Util.areEqual(this.currentTextFormat, format)) {
            if (this.currentTextFormat == null && i == 0) {
                i = 1;
            }
            this.currentTextFormat = format;
            reportTrackChangeEvent(2, j, format, i);
        }
    }

    private void reportTrackChangeEvent(int i, long j, Format format, int i2) {
        TrackChangeEvent.Builder m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(i).setTimeSinceCreatedMillis(j - this.startTimeMs);
        if (format != null) {
            TrackChangeEvent.Builder unused = m.setTrackState(1);
            TrackChangeEvent.Builder unused2 = m.setTrackChangeReason(getTrackChangeReason(i2));
            if (format.containerMimeType != null) {
                TrackChangeEvent.Builder unused3 = m.setContainerMimeType(format.containerMimeType);
            }
            if (format.sampleMimeType != null) {
                TrackChangeEvent.Builder unused4 = m.setSampleMimeType(format.sampleMimeType);
            }
            if (format.codecs != null) {
                TrackChangeEvent.Builder unused5 = m.setCodecName(format.codecs);
            }
            if (format.bitrate != -1) {
                TrackChangeEvent.Builder unused6 = m.setBitrate(format.bitrate);
            }
            if (format.width != -1) {
                TrackChangeEvent.Builder unused7 = m.setWidth(format.width);
            }
            if (format.height != -1) {
                TrackChangeEvent.Builder unused8 = m.setHeight(format.height);
            }
            if (format.channelCount != -1) {
                TrackChangeEvent.Builder unused9 = m.setChannelCount(format.channelCount);
            }
            if (format.sampleRate != -1) {
                TrackChangeEvent.Builder unused10 = m.setAudioSampleRate(format.sampleRate);
            }
            if (format.language != null) {
                Pair<String, String> languageAndRegion = getLanguageAndRegion(format.language);
                TrackChangeEvent.Builder unused11 = m.setLanguage((String) languageAndRegion.first);
                if (languageAndRegion.second != null) {
                    TrackChangeEvent.Builder unused12 = m.setLanguageRegion((String) languageAndRegion.second);
                }
            }
            if (format.frameRate != -1.0f) {
                TrackChangeEvent.Builder unused13 = m.setVideoFrameRate(format.frameRate);
            }
        } else {
            TrackChangeEvent.Builder unused14 = m.setTrackState(0);
        }
        this.reportedEventsForCurrentSession = true;
        this.playbackSession.reportTrackChangeEvent(m.build());
    }

    @RequiresNonNull({"metricsBuilder"})
    private void maybeUpdateTimelineMetadata(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        int indexOfPeriod;
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (mediaPeriodId != null && (indexOfPeriod = timeline.getIndexOfPeriod(mediaPeriodId.periodUid)) != -1) {
            timeline.getPeriod(indexOfPeriod, this.period);
            timeline.getWindow(this.period.windowIndex, this.window);
            PlaybackMetrics.Builder unused = builder.setStreamType(getStreamType(this.window.mediaItem));
            if (this.window.durationUs != C.TIME_UNSET && !this.window.isPlaceholder && !this.window.isDynamic && !this.window.isLive()) {
                PlaybackMetrics.Builder unused2 = builder.setMediaDurationMillis(this.window.getDurationMs());
            }
            PlaybackMetrics.Builder unused3 = builder.setPlaybackType(this.window.isLive() ? 2 : 1);
            this.reportedEventsForCurrentSession = true;
        }
    }

    private void finishCurrentSession() {
        PlaybackMetrics.Builder builder = this.metricsBuilder;
        if (builder != null && this.reportedEventsForCurrentSession) {
            PlaybackMetrics.Builder unused = builder.setAudioUnderrunCount(this.audioUnderruns);
            PlaybackMetrics.Builder unused2 = this.metricsBuilder.setVideoFramesDropped(this.droppedFrames);
            PlaybackMetrics.Builder unused3 = this.metricsBuilder.setVideoFramesPlayed(this.playedFrames);
            Long l = this.bandwidthTimeMs.get(this.activeSessionId);
            PlaybackMetrics.Builder unused4 = this.metricsBuilder.setNetworkTransferDurationMillis(l == null ? 0 : l.longValue());
            Long l2 = this.bandwidthBytes.get(this.activeSessionId);
            PlaybackMetrics.Builder unused5 = this.metricsBuilder.setNetworkBytesRead(l2 == null ? 0 : l2.longValue());
            PlaybackMetrics.Builder unused6 = this.metricsBuilder.setStreamSource((l2 == null || l2.longValue() <= 0) ? 0 : 1);
            this.playbackSession.reportPlaybackMetrics(this.metricsBuilder.build());
        }
        this.metricsBuilder = null;
        this.activeSessionId = null;
        this.audioUnderruns = 0;
        this.droppedFrames = 0;
        this.playedFrames = 0;
        this.currentVideoFormat = null;
        this.currentAudioFormat = null;
        this.currentTextFormat = null;
        this.reportedEventsForCurrentSession = false;
    }

    private static Pair<String, String> getLanguageAndRegion(String str) {
        String[] split = Util.split(str, "-");
        return Pair.create(split[0], split.length >= 2 ? split[1] : null);
    }

    private static int getNetworkType(Context context2) {
        switch (NetworkTypeObserver.getInstance(context2).getNetworkType()) {
            case 0:
                return 0;
            case 1:
                return 9;
            case 2:
                return 2;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 7:
                return 3;
            case 9:
                return 8;
            case 10:
                return 7;
            default:
                return 1;
        }
    }

    private static int getStreamType(MediaItem mediaItem) {
        if (mediaItem.localConfiguration == null) {
            return 0;
        }
        int inferContentTypeForUriAndMimeType = Util.inferContentTypeForUriAndMimeType(mediaItem.localConfiguration.uri, mediaItem.localConfiguration.mimeType);
        if (inferContentTypeForUriAndMimeType == 0) {
            return 3;
        }
        if (inferContentTypeForUriAndMimeType != 1) {
            return inferContentTypeForUriAndMimeType != 2 ? 1 : 4;
        }
        return 5;
    }

    private static ErrorInfo getErrorInfo(PlaybackException playbackException, Context context2, boolean z) {
        boolean z2;
        int i;
        if (playbackException.errorCode == 1001) {
            return new ErrorInfo(20, 0);
        }
        if (playbackException instanceof ExoPlaybackException) {
            ExoPlaybackException exoPlaybackException = (ExoPlaybackException) playbackException;
            z2 = exoPlaybackException.type == 1;
            i = exoPlaybackException.rendererFormatSupport;
        } else {
            i = 0;
            z2 = false;
        }
        Throwable th = (Throwable) Assertions.checkNotNull(playbackException.getCause());
        if (th instanceof IOException) {
            if (th instanceof HttpDataSource.InvalidResponseCodeException) {
                return new ErrorInfo(5, ((HttpDataSource.InvalidResponseCodeException) th).responseCode);
            }
            if ((th instanceof HttpDataSource.InvalidContentTypeException) || (th instanceof ParserException)) {
                return new ErrorInfo(z ? 10 : 11, 0);
            }
            boolean z3 = th instanceof HttpDataSource.HttpDataSourceException;
            if (z3 || (th instanceof UdpDataSource.UdpDataSourceException)) {
                if (NetworkTypeObserver.getInstance(context2).getNetworkType() == 1) {
                    return new ErrorInfo(3, 0);
                }
                Throwable cause = th.getCause();
                if (cause instanceof UnknownHostException) {
                    return new ErrorInfo(6, 0);
                }
                if (cause instanceof SocketTimeoutException) {
                    return new ErrorInfo(7, 0);
                }
                if (!z3 || ((HttpDataSource.HttpDataSourceException) th).type != 1) {
                    return new ErrorInfo(8, 0);
                }
                return new ErrorInfo(4, 0);
            } else if (playbackException.errorCode == 1002) {
                return new ErrorInfo(21, 0);
            } else {
                if (th instanceof DrmSession.DrmSessionException) {
                    Throwable th2 = (Throwable) Assertions.checkNotNull(th.getCause());
                    if (Util.SDK_INT >= 21 && (th2 instanceof MediaDrm.MediaDrmStateException)) {
                        int errorCodeFromPlatformDiagnosticsInfo = Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaDrm.MediaDrmStateException) th2).getDiagnosticInfo());
                        return new ErrorInfo(getDrmErrorCode(errorCodeFromPlatformDiagnosticsInfo), errorCodeFromPlatformDiagnosticsInfo);
                    } else if (Util.SDK_INT >= 23 && (th2 instanceof MediaDrmResetException)) {
                        return new ErrorInfo(27, 0);
                    } else {
                        if (Util.SDK_INT >= 18 && (th2 instanceof NotProvisionedException)) {
                            return new ErrorInfo(24, 0);
                        }
                        if (Util.SDK_INT >= 18 && (th2 instanceof DeniedByServerException)) {
                            return new ErrorInfo(29, 0);
                        }
                        if (th2 instanceof UnsupportedDrmException) {
                            return new ErrorInfo(23, 0);
                        }
                        if (th2 instanceof DefaultDrmSessionManager.MissingSchemeDataException) {
                            return new ErrorInfo(28, 0);
                        }
                        return new ErrorInfo(30, 0);
                    }
                } else if (!(th instanceof FileDataSource.FileDataSourceException) || !(th.getCause() instanceof FileNotFoundException)) {
                    return new ErrorInfo(9, 0);
                } else {
                    Throwable cause2 = ((Throwable) Assertions.checkNotNull(th.getCause())).getCause();
                    if (Util.SDK_INT < 21 || !(cause2 instanceof ErrnoException) || ((ErrnoException) cause2).errno != OsConstants.EACCES) {
                        return new ErrorInfo(31, 0);
                    }
                    return new ErrorInfo(32, 0);
                }
            }
        } else if (z2 && (i == 0 || i == 1)) {
            return new ErrorInfo(35, 0);
        } else {
            if (z2 && i == 3) {
                return new ErrorInfo(15, 0);
            }
            if (z2 && i == 2) {
                return new ErrorInfo(23, 0);
            }
            if (th instanceof MediaCodecRenderer.DecoderInitializationException) {
                return new ErrorInfo(13, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecRenderer.DecoderInitializationException) th).diagnosticInfo));
            }
            if (th instanceof MediaCodecDecoderException) {
                return new ErrorInfo(14, Util.getErrorCodeFromPlatformDiagnosticsInfo(((MediaCodecDecoderException) th).diagnosticInfo));
            }
            if (th instanceof OutOfMemoryError) {
                return new ErrorInfo(14, 0);
            }
            if (th instanceof AudioSink.InitializationException) {
                return new ErrorInfo(17, ((AudioSink.InitializationException) th).audioTrackState);
            }
            if (th instanceof AudioSink.WriteException) {
                return new ErrorInfo(18, ((AudioSink.WriteException) th).errorCode);
            }
            if (Util.SDK_INT < 16 || !(th instanceof MediaCodec.CryptoException)) {
                return new ErrorInfo(22, 0);
            }
            int errorCode = ((MediaCodec.CryptoException) th).getErrorCode();
            return new ErrorInfo(getDrmErrorCode(errorCode), errorCode);
        }
    }

    private static DrmInitData getDrmInitData(ImmutableList<Tracks.Group> immutableList) {
        DrmInitData drmInitData;
        UnmodifiableIterator<Tracks.Group> it = immutableList.iterator();
        while (it.hasNext()) {
            Tracks.Group next = it.next();
            int i = 0;
            while (true) {
                if (i < next.length) {
                    if (next.isTrackSelected(i) && (drmInitData = next.getTrackFormat(i).drmInitData) != null) {
                        return drmInitData;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    private static int getDrmType(DrmInitData drmInitData) {
        for (int i = 0; i < drmInitData.schemeDataCount; i++) {
            UUID uuid = drmInitData.get(i).uuid;
            if (uuid.equals(C.WIDEVINE_UUID)) {
                return 3;
            }
            if (uuid.equals(C.PLAYREADY_UUID)) {
                return 2;
            }
            if (uuid.equals(C.CLEARKEY_UUID)) {
                return 6;
            }
        }
        return 1;
    }

    private static int getDrmErrorCode(int i) {
        switch (Util.getErrorCodeForMediaDrmErrorCode(i)) {
            case PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED:
                return 24;
            case PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR:
                return 28;
            case PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED:
                return 25;
            case PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION:
                return 26;
            default:
                return 27;
        }
    }

    private static final class ErrorInfo {
        public final int errorCode;
        public final int subErrorCode;

        public ErrorInfo(int i, int i2) {
            this.errorCode = i;
            this.subErrorCode = i2;
        }
    }

    private static final class PendingFormatUpdate {
        public final Format format;
        public final int selectionReason;
        public final String sessionId;

        public PendingFormatUpdate(Format format2, int i, String str) {
            this.format = format2;
            this.selectionReason = i;
            this.sessionId = str;
        }
    }
}
