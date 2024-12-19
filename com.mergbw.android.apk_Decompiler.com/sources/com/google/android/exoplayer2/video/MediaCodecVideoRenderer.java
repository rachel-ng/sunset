package com.google.android.exoplayer2.video;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Pair;
import android.view.Display;
import android.view.Surface;
import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.decoder.DecoderInputBuffer;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.mediacodec.MediaCodecAdapter;
import com.google.android.exoplayer2.mediacodec.MediaCodecDecoderException;
import com.google.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MediaFormatUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.TraceUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.gms.common.Scopes;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline0;
import com.google.common.collect.ImmutableList;
import java.nio.ByteBuffer;
import java.util.List;

public class MediaCodecVideoRenderer extends MediaCodecRenderer {
    private static final int HEVC_MAX_INPUT_SIZE_THRESHOLD = 2097152;
    private static final float INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR = 1.5f;
    private static final String KEY_CROP_BOTTOM = "crop-bottom";
    private static final String KEY_CROP_LEFT = "crop-left";
    private static final String KEY_CROP_RIGHT = "crop-right";
    private static final String KEY_CROP_TOP = "crop-top";
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "MediaCodecVideoRenderer";
    private static final long TUNNELING_EOS_PRESENTATION_TIME_US = Long.MAX_VALUE;
    private static boolean deviceNeedsSetOutputSurfaceWorkaround;
    private static boolean evaluatedDeviceNeedsSetOutputSurfaceWorkaround;
    private final long allowedJoiningTimeMs;
    private int buffersInCodecCount;
    private boolean codecHandlesHdr10PlusOutOfBandMetadata;
    private CodecMaxValues codecMaxValues;
    private boolean codecNeedsSetOutputSurfaceWorkaround;
    private int consecutiveDroppedFrameCount;
    private final Context context;
    private int currentHeight;
    private float currentPixelWidthHeightRatio;
    private int currentUnappliedRotationDegrees;
    private int currentWidth;
    private final boolean deviceNeedsNoPostProcessWorkaround;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private VideoFrameMetadataListener frameMetadataListener;
    private final VideoFrameReleaseHelper frameReleaseHelper;
    private boolean haveReportedFirstFrameRenderedForCurrentSurface;
    private long initialPositionUs;
    private long joiningDeadlineMs;
    private long lastBufferPresentationTimeUs;
    private long lastFrameReleaseTimeNs;
    private long lastRenderRealtimeUs;
    private final int maxDroppedFramesToNotify;
    private boolean mayRenderFirstFrameAfterEnableIfNotStarted;
    private PlaceholderSurface placeholderSurface;
    private boolean renderedFirstFrameAfterEnable;
    private boolean renderedFirstFrameAfterReset;
    private VideoSize reportedVideoSize;
    private int scalingMode;
    private Surface surface;
    private long totalVideoFrameProcessingOffsetUs;
    private boolean tunneling;
    private int tunnelingAudioSessionId;
    OnFrameRenderedListenerV23 tunnelingOnFrameRenderedListener;
    private int videoFrameProcessingOffsetCount;

    private static boolean isBufferLate(long j) {
        return j < -30000;
    }

    private static boolean isBufferVeryLate(long j) {
        return j < -500000;
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector) {
        this(context2, mediaCodecSelector, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j) {
        this(context2, mediaCodecSelector, j, (Handler) null, (VideoRendererEventListener) null, 0);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, j, false, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, MediaCodecAdapter.Factory.DEFAULT, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i) {
        this(context2, factory, mediaCodecSelector, j, z, handler, videoRendererEventListener, i, 30.0f);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaCodecVideoRenderer(Context context2, MediaCodecAdapter.Factory factory, MediaCodecSelector mediaCodecSelector, long j, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, int i, float f) {
        super(2, factory, mediaCodecSelector, z, f);
        this.allowedJoiningTimeMs = j;
        this.maxDroppedFramesToNotify = i;
        Context applicationContext = context2.getApplicationContext();
        this.context = applicationContext;
        this.frameReleaseHelper = new VideoFrameReleaseHelper(applicationContext);
        Handler handler2 = handler;
        VideoRendererEventListener videoRendererEventListener2 = videoRendererEventListener;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.deviceNeedsNoPostProcessWorkaround = deviceNeedsNoPostProcessWorkaround();
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.currentWidth = -1;
        this.currentHeight = -1;
        this.currentPixelWidthHeightRatio = -1.0f;
        this.scalingMode = 1;
        this.tunnelingAudioSessionId = 0;
        clearReportedVideoSize();
    }

    public String getName() {
        return TAG;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0085  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int supportsFormat(com.google.android.exoplayer2.mediacodec.MediaCodecSelector r11, com.google.android.exoplayer2.Format r12) throws com.google.android.exoplayer2.mediacodec.MediaCodecUtil.DecoderQueryException {
        /*
            r10 = this;
            java.lang.String r0 = r12.sampleMimeType
            boolean r0 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000e
            int r11 = com.google.android.exoplayer2.RendererCapabilities.CC.create(r1)
            return r11
        L_0x000e:
            com.google.android.exoplayer2.drm.DrmInitData r0 = r12.drmInitData
            r2 = 1
            if (r0 == 0) goto L_0x0015
            r0 = r2
            goto L_0x0016
        L_0x0015:
            r0 = r1
        L_0x0016:
            android.content.Context r3 = r10.context
            java.util.List r3 = getDecoderInfos(r3, r11, r12, r0, r1)
            if (r0 == 0) goto L_0x002a
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x002a
            android.content.Context r3 = r10.context
            java.util.List r3 = getDecoderInfos(r3, r11, r12, r1, r1)
        L_0x002a:
            boolean r4 = r3.isEmpty()
            if (r4 == 0) goto L_0x0035
            int r11 = com.google.android.exoplayer2.RendererCapabilities.CC.create(r2)
            return r11
        L_0x0035:
            boolean r4 = supportsFormatDrm(r12)
            if (r4 != 0) goto L_0x0041
            r11 = 2
            int r11 = com.google.android.exoplayer2.RendererCapabilities.CC.create(r11)
            return r11
        L_0x0041:
            java.lang.Object r4 = r3.get(r1)
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r4 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r4
            boolean r5 = r4.isFormatSupported(r12)
            if (r5 != 0) goto L_0x0067
            r6 = r2
        L_0x004e:
            int r7 = r3.size()
            if (r6 >= r7) goto L_0x0067
            java.lang.Object r7 = r3.get(r6)
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r7 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r7
            boolean r8 = r7.isFormatSupported(r12)
            if (r8 == 0) goto L_0x0064
            r3 = r1
            r5 = r2
            r4 = r7
            goto L_0x0068
        L_0x0064:
            int r6 = r6 + 1
            goto L_0x004e
        L_0x0067:
            r3 = r2
        L_0x0068:
            if (r5 == 0) goto L_0x006c
            r6 = 4
            goto L_0x006d
        L_0x006c:
            r6 = 3
        L_0x006d:
            boolean r7 = r4.isSeamlessAdaptationSupported(r12)
            if (r7 == 0) goto L_0x0076
            r7 = 16
            goto L_0x0078
        L_0x0076:
            r7 = 8
        L_0x0078:
            boolean r4 = r4.hardwareAccelerated
            if (r4 == 0) goto L_0x007f
            r4 = 64
            goto L_0x0080
        L_0x007f:
            r4 = r1
        L_0x0080:
            if (r3 == 0) goto L_0x0085
            r3 = 128(0x80, float:1.794E-43)
            goto L_0x0086
        L_0x0085:
            r3 = r1
        L_0x0086:
            int r8 = com.google.android.exoplayer2.util.Util.SDK_INT
            r9 = 26
            if (r8 < r9) goto L_0x00a0
            java.lang.String r8 = "video/dolby-vision"
            java.lang.String r9 = r12.sampleMimeType
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x00a0
            android.content.Context r8 = r10.context
            boolean r8 = com.google.android.exoplayer2.video.MediaCodecVideoRenderer.Api26.doesDisplaySupportDolbyVision(r8)
            if (r8 != 0) goto L_0x00a0
            r3 = 256(0x100, float:3.59E-43)
        L_0x00a0:
            if (r5 == 0) goto L_0x00c6
            android.content.Context r5 = r10.context
            java.util.List r11 = getDecoderInfos(r5, r11, r12, r0, r2)
            boolean r0 = r11.isEmpty()
            if (r0 != 0) goto L_0x00c6
            java.util.List r11 = com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getDecoderInfosSortedByFormatSupport(r11, r12)
            java.lang.Object r11 = r11.get(r1)
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r11 = (com.google.android.exoplayer2.mediacodec.MediaCodecInfo) r11
            boolean r0 = r11.isFormatSupported(r12)
            if (r0 == 0) goto L_0x00c6
            boolean r11 = r11.isSeamlessAdaptationSupported(r12)
            if (r11 == 0) goto L_0x00c6
            r1 = 32
        L_0x00c6:
            int r11 = com.google.android.exoplayer2.RendererCapabilities.CC.create(r6, r7, r1, r4, r3)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.supportsFormat(com.google.android.exoplayer2.mediacodec.MediaCodecSelector, com.google.android.exoplayer2.Format):int");
    }

    /* access modifiers changed from: protected */
    public List<MediaCodecInfo> getDecoderInfos(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return MediaCodecUtil.getDecoderInfosSortedByFormatSupport(getDecoderInfos(this.context, mediaCodecSelector, format, z, this.tunneling), format);
    }

    private static List<MediaCodecInfo> getDecoderInfos(Context context2, MediaCodecSelector mediaCodecSelector, Format format, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        String str = format.sampleMimeType;
        if (str == null) {
            return ImmutableList.of();
        }
        List<MediaCodecInfo> decoderInfos = mediaCodecSelector.getDecoderInfos(str, z, z2);
        String alternativeCodecMimeType = MediaCodecUtil.getAlternativeCodecMimeType(format);
        if (alternativeCodecMimeType == null) {
            return ImmutableList.copyOf(decoderInfos);
        }
        List<MediaCodecInfo> decoderInfos2 = mediaCodecSelector.getDecoderInfos(alternativeCodecMimeType, z, z2);
        if (Util.SDK_INT < 26 || !MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) || decoderInfos2.isEmpty() || Api26.doesDisplaySupportDolbyVision(context2)) {
            return ImmutableList.builder().addAll((Iterable) decoderInfos).addAll((Iterable) decoderInfos2).build();
        }
        return ImmutableList.copyOf(decoderInfos2);
    }

    private static final class Api26 {
        private Api26() {
        }

        public static boolean doesDisplaySupportDolbyVision(Context context) {
            DisplayManager displayManager = (DisplayManager) context.getSystemService("display");
            Display display = displayManager != null ? displayManager.getDisplay(0) : null;
            if (display == null || !Chip$$ExternalSyntheticApiModelOutline0.m(display)) {
                return false;
            }
            for (int i : Chip$$ExternalSyntheticApiModelOutline0.m(display).getSupportedHdrTypes()) {
                if (i == 1) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onEnabled(boolean z, boolean z2) throws ExoPlaybackException {
        super.onEnabled(z, z2);
        boolean z3 = getConfiguration().tunneling;
        Assertions.checkState(!z3 || this.tunnelingAudioSessionId != 0);
        if (this.tunneling != z3) {
            this.tunneling = z3;
            releaseCodec();
        }
        this.eventDispatcher.enabled(this.decoderCounters);
        this.mayRenderFirstFrameAfterEnableIfNotStarted = z2;
        this.renderedFirstFrameAfterEnable = false;
    }

    /* access modifiers changed from: protected */
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        clearRenderedFirstFrame();
        this.frameReleaseHelper.onPositionReset();
        this.lastBufferPresentationTimeUs = C.TIME_UNSET;
        this.initialPositionUs = C.TIME_UNSET;
        this.consecutiveDroppedFrameCount = 0;
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
    }

    public boolean isReady() {
        PlaceholderSurface placeholderSurface2;
        if (super.isReady() && (this.renderedFirstFrameAfterReset || (((placeholderSurface2 = this.placeholderSurface) != null && this.surface == placeholderSurface2) || getCodec() == null || this.tunneling))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onStarted() {
        super.onStarted();
        this.droppedFrames = 0;
        this.droppedFrameAccumulationStartTimeMs = SystemClock.elapsedRealtime();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.totalVideoFrameProcessingOffsetUs = 0;
        this.videoFrameProcessingOffsetCount = 0;
        this.frameReleaseHelper.onStarted();
    }

    /* access modifiers changed from: protected */
    public void onStopped() {
        this.joiningDeadlineMs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
        maybeNotifyVideoFrameProcessingOffset();
        this.frameReleaseHelper.onStopped();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    public void onDisabled() {
        clearReportedVideoSize();
        clearRenderedFirstFrame();
        this.haveReportedFirstFrameRenderedForCurrentSurface = false;
        this.tunnelingOnFrameRenderedListener = null;
        try {
            super.onDisabled();
        } finally {
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        try {
            super.onReset();
        } finally {
            if (this.placeholderSurface != null) {
                releasePlaceholderSurface();
            }
        }
    }

    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setOutput(obj);
        } else if (i == 7) {
            this.frameMetadataListener = (VideoFrameMetadataListener) obj;
        } else if (i == 10) {
            int intValue = ((Integer) obj).intValue();
            if (this.tunnelingAudioSessionId != intValue) {
                this.tunnelingAudioSessionId = intValue;
                if (this.tunneling) {
                    releaseCodec();
                }
            }
        } else if (i == 4) {
            this.scalingMode = ((Integer) obj).intValue();
            MediaCodecAdapter codec = getCodec();
            if (codec != null) {
                codec.setVideoScalingMode(this.scalingMode);
            }
        } else if (i != 5) {
            super.handleMessage(i, obj);
        } else {
            this.frameReleaseHelper.setChangeFrameRateStrategy(((Integer) obj).intValue());
        }
    }

    /* JADX WARNING: Failed to insert additional move for type inference */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setOutput(java.lang.Object r5) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r4 = this;
            boolean r0 = r5 instanceof android.view.Surface
            if (r0 == 0) goto L_0x0007
            android.view.Surface r5 = (android.view.Surface) r5
            goto L_0x0008
        L_0x0007:
            r5 = 0
        L_0x0008:
            if (r5 != 0) goto L_0x0026
            com.google.android.exoplayer2.video.PlaceholderSurface r0 = r4.placeholderSurface
            if (r0 == 0) goto L_0x0010
            r5 = r0
            goto L_0x0026
        L_0x0010:
            com.google.android.exoplayer2.mediacodec.MediaCodecInfo r0 = r4.getCodecInfo()
            if (r0 == 0) goto L_0x0026
            boolean r1 = r4.shouldUsePlaceholderSurface(r0)
            if (r1 == 0) goto L_0x0026
            android.content.Context r5 = r4.context
            boolean r0 = r0.secure
            com.google.android.exoplayer2.video.PlaceholderSurface r5 = com.google.android.exoplayer2.video.PlaceholderSurface.newInstanceV17(r5, r0)
            r4.placeholderSurface = r5
        L_0x0026:
            android.view.Surface r0 = r4.surface
            if (r0 == r5) goto L_0x006e
            r4.surface = r5
            com.google.android.exoplayer2.video.VideoFrameReleaseHelper r0 = r4.frameReleaseHelper
            r0.onSurfaceChanged(r5)
            r0 = 0
            r4.haveReportedFirstFrameRenderedForCurrentSurface = r0
            int r0 = r4.getState()
            com.google.android.exoplayer2.mediacodec.MediaCodecAdapter r1 = r4.getCodec()
            if (r1 == 0) goto L_0x0054
            int r2 = com.google.android.exoplayer2.util.Util.SDK_INT
            r3 = 23
            if (r2 < r3) goto L_0x004e
            if (r5 == 0) goto L_0x004e
            boolean r2 = r4.codecNeedsSetOutputSurfaceWorkaround
            if (r2 != 0) goto L_0x004e
            r4.setOutputSurfaceV23(r1, r5)
            goto L_0x0054
        L_0x004e:
            r4.releaseCodec()
            r4.maybeInitCodecOrBypass()
        L_0x0054:
            if (r5 == 0) goto L_0x0067
            com.google.android.exoplayer2.video.PlaceholderSurface r1 = r4.placeholderSurface
            if (r5 == r1) goto L_0x0067
            r4.maybeRenotifyVideoSizeChanged()
            r4.clearRenderedFirstFrame()
            r5 = 2
            if (r0 != r5) goto L_0x007a
            r4.setJoiningDeadlineMs()
            goto L_0x007a
        L_0x0067:
            r4.clearReportedVideoSize()
            r4.clearRenderedFirstFrame()
            goto L_0x007a
        L_0x006e:
            if (r5 == 0) goto L_0x007a
            com.google.android.exoplayer2.video.PlaceholderSurface r0 = r4.placeholderSurface
            if (r5 == r0) goto L_0x007a
            r4.maybeRenotifyVideoSizeChanged()
            r4.maybeRenotifyRenderedFirstFrame()
        L_0x007a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.setOutput(java.lang.Object):void");
    }

    /* access modifiers changed from: protected */
    public boolean shouldInitCodec(MediaCodecInfo mediaCodecInfo) {
        return this.surface != null || shouldUsePlaceholderSurface(mediaCodecInfo);
    }

    /* access modifiers changed from: protected */
    public boolean getCodecNeedsEosPropagation() {
        return this.tunneling && Util.SDK_INT < 23;
    }

    /* access modifiers changed from: protected */
    public MediaCodecAdapter.Configuration getMediaCodecConfiguration(MediaCodecInfo mediaCodecInfo, Format format, MediaCrypto mediaCrypto, float f) {
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (!(placeholderSurface2 == null || placeholderSurface2.secure == mediaCodecInfo.secure)) {
            releasePlaceholderSurface();
        }
        String str = mediaCodecInfo.codecMimeType;
        CodecMaxValues codecMaxValues2 = getCodecMaxValues(mediaCodecInfo, format, getStreamFormats());
        this.codecMaxValues = codecMaxValues2;
        MediaFormat mediaFormat = getMediaFormat(format, str, codecMaxValues2, f, this.deviceNeedsNoPostProcessWorkaround, this.tunneling ? this.tunnelingAudioSessionId : 0);
        if (this.surface == null) {
            if (shouldUsePlaceholderSurface(mediaCodecInfo)) {
                if (this.placeholderSurface == null) {
                    this.placeholderSurface = PlaceholderSurface.newInstanceV17(this.context, mediaCodecInfo.secure);
                }
                this.surface = this.placeholderSurface;
            } else {
                throw new IllegalStateException();
            }
        }
        return MediaCodecAdapter.Configuration.createForVideoDecoding(mediaCodecInfo, mediaFormat, format, this.surface, mediaCrypto);
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation canReuseCodec(MediaCodecInfo mediaCodecInfo, Format format, Format format2) {
        int i;
        DecoderReuseEvaluation canReuseCodec = mediaCodecInfo.canReuseCodec(format, format2);
        int i2 = canReuseCodec.discardReasons;
        if (format2.width > this.codecMaxValues.width || format2.height > this.codecMaxValues.height) {
            i2 |= 256;
        }
        if (getMaxInputSize(mediaCodecInfo, format2) > this.codecMaxValues.inputSize) {
            i2 |= 64;
        }
        int i3 = i2;
        String str = mediaCodecInfo.name;
        if (i3 != 0) {
            i = 0;
        } else {
            i = canReuseCodec.result;
        }
        return new DecoderReuseEvaluation(str, format, format2, i, i3);
    }

    /* access modifiers changed from: protected */
    public void resetCodecStateForFlush() {
        super.resetCodecStateForFlush();
        this.buffersInCodecCount = 0;
    }

    public void setPlaybackSpeed(float f, float f2) throws ExoPlaybackException {
        super.setPlaybackSpeed(f, f2);
        this.frameReleaseHelper.onPlaybackSpeed(f);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007a, code lost:
        if (r3.equals(com.google.android.exoplayer2.util.MimeTypes.VIDEO_AV1) == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
        r10 = ((java.lang.Integer) r10.first).intValue();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo r9, com.google.android.exoplayer2.Format r10) {
        /*
            int r0 = r10.width
            int r1 = r10.height
            r2 = -1
            if (r0 == r2) goto L_0x00e3
            if (r1 != r2) goto L_0x000b
            goto L_0x00e3
        L_0x000b:
            java.lang.String r3 = r10.sampleMimeType
            java.lang.String r4 = "video/dolby-vision"
            boolean r4 = r4.equals(r3)
            java.lang.String r5 = "video/avc"
            java.lang.String r6 = "video/hevc"
            r7 = 1
            r8 = 2
            if (r4 == 0) goto L_0x0034
            android.util.Pair r10 = com.google.android.exoplayer2.mediacodec.MediaCodecUtil.getCodecProfileAndLevel(r10)
            if (r10 == 0) goto L_0x0033
            java.lang.Object r10 = r10.first
            java.lang.Integer r10 = (java.lang.Integer) r10
            int r10 = r10.intValue()
            r3 = 512(0x200, float:7.175E-43)
            if (r10 == r3) goto L_0x0031
            if (r10 == r7) goto L_0x0031
            if (r10 != r8) goto L_0x0033
        L_0x0031:
            r3 = r5
            goto L_0x0034
        L_0x0033:
            r3 = r6
        L_0x0034:
            r3.hashCode()
            int r10 = r3.hashCode()
            r4 = 4
            switch(r10) {
                case -1664118616: goto L_0x007d;
                case -1662735862: goto L_0x0074;
                case -1662541442: goto L_0x006b;
                case 1187890754: goto L_0x0060;
                case 1331836730: goto L_0x0057;
                case 1599127256: goto L_0x004c;
                case 1599127257: goto L_0x0041;
                default: goto L_0x003f;
            }
        L_0x003f:
            r7 = r2
            goto L_0x0087
        L_0x0041:
            java.lang.String r10 = "video/x-vnd.on2.vp9"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x004a
            goto L_0x003f
        L_0x004a:
            r7 = 6
            goto L_0x0087
        L_0x004c:
            java.lang.String r10 = "video/x-vnd.on2.vp8"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0055
            goto L_0x003f
        L_0x0055:
            r7 = 5
            goto L_0x0087
        L_0x0057:
            boolean r10 = r3.equals(r5)
            if (r10 != 0) goto L_0x005e
            goto L_0x003f
        L_0x005e:
            r7 = r4
            goto L_0x0087
        L_0x0060:
            java.lang.String r10 = "video/mp4v-es"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0069
            goto L_0x003f
        L_0x0069:
            r7 = 3
            goto L_0x0087
        L_0x006b:
            boolean r10 = r3.equals(r6)
            if (r10 != 0) goto L_0x0072
            goto L_0x003f
        L_0x0072:
            r7 = r8
            goto L_0x0087
        L_0x0074:
            java.lang.String r10 = "video/av01"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0087
            goto L_0x003f
        L_0x007d:
            java.lang.String r10 = "video/3gpp"
            boolean r10 = r3.equals(r10)
            if (r10 != 0) goto L_0x0086
            goto L_0x003f
        L_0x0086:
            r7 = 0
        L_0x0087:
            switch(r7) {
                case 0: goto L_0x00dd;
                case 1: goto L_0x00dd;
                case 2: goto L_0x00d1;
                case 3: goto L_0x00dd;
                case 4: goto L_0x0091;
                case 5: goto L_0x00dd;
                case 6: goto L_0x008b;
                default: goto L_0x008a;
            }
        L_0x008a:
            return r2
        L_0x008b:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r4)
            return r9
        L_0x0091:
            java.lang.String r10 = "BRAVIA 4K 2015"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 != 0) goto L_0x00d0
            java.lang.String r10 = "Amazon"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.MANUFACTURER
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x00be
            java.lang.String r10 = "KFSOWI"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 != 0) goto L_0x00d0
            java.lang.String r10 = "AFTS"
            java.lang.String r3 = com.google.android.exoplayer2.util.Util.MODEL
            boolean r10 = r10.equals(r3)
            if (r10 == 0) goto L_0x00be
            boolean r9 = r9.secure
            if (r9 == 0) goto L_0x00be
            goto L_0x00d0
        L_0x00be:
            r9 = 16
            int r10 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r0, (int) r9)
            int r9 = com.google.android.exoplayer2.util.Util.ceilDivide((int) r1, (int) r9)
            int r10 = r10 * r9
            int r10 = r10 * 256
            int r9 = getMaxSampleSize(r10, r8)
            return r9
        L_0x00d0:
            return r2
        L_0x00d1:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r8)
            r10 = 2097152(0x200000, float:2.938736E-39)
            int r9 = java.lang.Math.max(r10, r9)
            return r9
        L_0x00dd:
            int r0 = r0 * r1
            int r9 = getMaxSampleSize(r0, r8)
            return r9
        L_0x00e3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.getCodecMaxInputSize(com.google.android.exoplayer2.mediacodec.MediaCodecInfo, com.google.android.exoplayer2.Format):int");
    }

    /* access modifiers changed from: protected */
    public float getCodecOperatingRateV23(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.frameRate;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    public void onCodecInitialized(String str, MediaCodecAdapter.Configuration configuration, long j, long j2) {
        this.eventDispatcher.decoderInitialized(str, j, j2);
        this.codecNeedsSetOutputSurfaceWorkaround = codecNeedsSetOutputSurfaceWorkaround(str);
        this.codecHandlesHdr10PlusOutOfBandMetadata = ((MediaCodecInfo) Assertions.checkNotNull(getCodecInfo())).isHdr10PlusOutOfBandMetadataSupported();
        if (Util.SDK_INT >= 23 && this.tunneling) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23((MediaCodecAdapter) Assertions.checkNotNull(getCodec()));
        }
    }

    /* access modifiers changed from: protected */
    public void onCodecReleased(String str) {
        this.eventDispatcher.decoderReleased(str);
    }

    /* access modifiers changed from: protected */
    public void onCodecError(Exception exc) {
        Log.e(TAG, "Video codec error", exc);
        this.eventDispatcher.videoCodecError(exc);
    }

    /* access modifiers changed from: protected */
    public DecoderReuseEvaluation onInputFormatChanged(FormatHolder formatHolder) throws ExoPlaybackException {
        DecoderReuseEvaluation onInputFormatChanged = super.onInputFormatChanged(formatHolder);
        this.eventDispatcher.inputFormatChanged(formatHolder.format, onInputFormatChanged);
        return onInputFormatChanged;
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (!this.tunneling) {
            this.buffersInCodecCount++;
        }
        if (Util.SDK_INT < 23 && this.tunneling) {
            onProcessedTunneledBuffer(decoderInputBuffer.timeUs);
        }
    }

    /* access modifiers changed from: protected */
    public void onOutputFormatChanged(Format format, MediaFormat mediaFormat) {
        int i;
        int i2;
        MediaCodecAdapter codec = getCodec();
        if (codec != null) {
            codec.setVideoScalingMode(this.scalingMode);
        }
        if (this.tunneling) {
            this.currentWidth = format.width;
            this.currentHeight = format.height;
        } else {
            Assertions.checkNotNull(mediaFormat);
            boolean z = mediaFormat.containsKey(KEY_CROP_RIGHT) && mediaFormat.containsKey(KEY_CROP_LEFT) && mediaFormat.containsKey(KEY_CROP_BOTTOM) && mediaFormat.containsKey(KEY_CROP_TOP);
            if (z) {
                i = (mediaFormat.getInteger(KEY_CROP_RIGHT) - mediaFormat.getInteger(KEY_CROP_LEFT)) + 1;
            } else {
                i = mediaFormat.getInteger("width");
            }
            this.currentWidth = i;
            if (z) {
                i2 = (mediaFormat.getInteger(KEY_CROP_BOTTOM) - mediaFormat.getInteger(KEY_CROP_TOP)) + 1;
            } else {
                i2 = mediaFormat.getInteger("height");
            }
            this.currentHeight = i2;
        }
        this.currentPixelWidthHeightRatio = format.pixelWidthHeightRatio;
        if (Util.SDK_INT < 21) {
            this.currentUnappliedRotationDegrees = format.rotationDegrees;
        } else if (format.rotationDegrees == 90 || format.rotationDegrees == 270) {
            int i3 = this.currentWidth;
            this.currentWidth = this.currentHeight;
            this.currentHeight = i3;
            this.currentPixelWidthHeightRatio = 1.0f / this.currentPixelWidthHeightRatio;
        }
        this.frameReleaseHelper.onFormatChanged(format.frameRate);
    }

    /* access modifiers changed from: protected */
    public void handleInputBufferSupplementalData(DecoderInputBuffer decoderInputBuffer) throws ExoPlaybackException {
        if (this.codecHandlesHdr10PlusOutOfBandMetadata) {
            ByteBuffer byteBuffer = (ByteBuffer) Assertions.checkNotNull(decoderInputBuffer.supplementalData);
            if (byteBuffer.remaining() >= 7) {
                byte b2 = byteBuffer.get();
                short s = byteBuffer.getShort();
                short s2 = byteBuffer.getShort();
                byte b3 = byteBuffer.get();
                byte b4 = byteBuffer.get();
                byteBuffer.position(0);
                if (b2 != -75 || s != 60 || s2 != 1 || b3 != 4) {
                    return;
                }
                if (b4 == 0 || b4 == 1) {
                    byte[] bArr = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bArr);
                    byteBuffer.position(0);
                    setHdr10PlusInfoV29(getCodec(), bArr);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean processOutputBuffer(long j, long j2, MediaCodecAdapter mediaCodecAdapter, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, Format format) throws ExoPlaybackException {
        long j4;
        boolean z3;
        long j5 = j;
        MediaCodecAdapter mediaCodecAdapter2 = mediaCodecAdapter;
        int i4 = i;
        long j6 = j3;
        Assertions.checkNotNull(mediaCodecAdapter);
        if (this.initialPositionUs == C.TIME_UNSET) {
            this.initialPositionUs = j5;
        }
        if (j6 != this.lastBufferPresentationTimeUs) {
            this.frameReleaseHelper.onNextFrame(j6);
            this.lastBufferPresentationTimeUs = j6;
        }
        long outputStreamOffsetUs = getOutputStreamOffsetUs();
        long j7 = j6 - outputStreamOffsetUs;
        if (!z || z2) {
            double playbackSpeed = (double) getPlaybackSpeed();
            boolean z4 = getState() == 2;
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            long j8 = (long) (((double) (j6 - j5)) / playbackSpeed);
            if (z4) {
                j8 -= elapsedRealtime - j2;
            }
            if (this.surface != this.placeholderSurface) {
                long j9 = elapsedRealtime - this.lastRenderRealtimeUs;
                if (this.renderedFirstFrameAfterEnable ? this.renderedFirstFrameAfterReset : !z4 && !this.mayRenderFirstFrameAfterEnableIfNotStarted) {
                    j4 = j9;
                    z3 = false;
                } else {
                    z3 = true;
                    j4 = j9;
                }
                if (this.joiningDeadlineMs != C.TIME_UNSET || j5 < outputStreamOffsetUs || (!z3 && (!z4 || !shouldForceRenderOutputBuffer(j8, j4)))) {
                    if (z4 && j5 != this.initialPositionUs) {
                        long nanoTime = System.nanoTime();
                        long adjustReleaseTime = this.frameReleaseHelper.adjustReleaseTime((j8 * 1000) + nanoTime);
                        long j10 = (adjustReleaseTime - nanoTime) / 1000;
                        boolean z5 = this.joiningDeadlineMs != C.TIME_UNSET;
                        long j11 = j10;
                        long j12 = adjustReleaseTime;
                        if (shouldDropBuffersToKeyframe(j10, j2, z2) && maybeDropBuffersToKeyframe(j5, z5)) {
                            return false;
                        }
                        if (shouldDropOutputBuffer(j11, j2, z2)) {
                            if (z5) {
                                skipOutputBuffer(mediaCodecAdapter2, i4, j7);
                            } else {
                                dropOutputBuffer(mediaCodecAdapter2, i4, j7);
                            }
                            updateVideoFrameProcessingOffsetCounters(j11);
                            return true;
                        } else if (Util.SDK_INT < 21) {
                            long j13 = j12;
                            if (j11 < 30000) {
                                if (j11 > 11000) {
                                    try {
                                        Thread.sleep((j11 - WorkRequest.MIN_BACKOFF_MILLIS) / 1000);
                                    } catch (InterruptedException unused) {
                                        Thread.currentThread().interrupt();
                                        return false;
                                    }
                                }
                                notifyFrameMetadataListener(j7, j13, format);
                                renderOutputBuffer(mediaCodecAdapter2, i4, j7);
                                updateVideoFrameProcessingOffsetCounters(j11);
                                return true;
                            }
                        } else if (j11 < 50000) {
                            long j14 = j12;
                            if (j14 == this.lastFrameReleaseTimeNs) {
                                skipOutputBuffer(mediaCodecAdapter2, i4, j7);
                            } else {
                                notifyFrameMetadataListener(j7, j14, format);
                                renderOutputBufferV21(mediaCodecAdapter, i, j7, j14);
                            }
                            updateVideoFrameProcessingOffsetCounters(j11);
                            this.lastFrameReleaseTimeNs = j14;
                            return true;
                        }
                    }
                    return false;
                }
                long nanoTime2 = System.nanoTime();
                notifyFrameMetadataListener(j7, nanoTime2, format);
                if (Util.SDK_INT >= 21) {
                    renderOutputBufferV21(mediaCodecAdapter, i, j7, nanoTime2);
                } else {
                    renderOutputBuffer(mediaCodecAdapter2, i4, j7);
                }
                updateVideoFrameProcessingOffsetCounters(j8);
                return true;
            } else if (!isBufferLate(j8)) {
                return false;
            } else {
                skipOutputBuffer(mediaCodecAdapter2, i4, j7);
                updateVideoFrameProcessingOffsetCounters(j8);
                return true;
            }
        } else {
            skipOutputBuffer(mediaCodecAdapter2, i4, j7);
            return true;
        }
    }

    private void notifyFrameMetadataListener(long j, long j2, Format format) {
        VideoFrameMetadataListener videoFrameMetadataListener = this.frameMetadataListener;
        if (videoFrameMetadataListener != null) {
            videoFrameMetadataListener.onVideoFrameAboutToBeRendered(j, j2, format, getCodecOutputMediaFormat());
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedTunneledBuffer(long j) throws ExoPlaybackException {
        updateOutputFormatForTime(j);
        maybeNotifyVideoSizeChanged();
        this.decoderCounters.renderedOutputBufferCount++;
        maybeNotifyRenderedFirstFrame();
        onProcessedOutputBuffer(j);
    }

    /* access modifiers changed from: private */
    public void onProcessedTunneledEndOfStream() {
        setPendingOutputEndOfStream();
    }

    /* access modifiers changed from: protected */
    public void onProcessedOutputBuffer(long j) {
        super.onProcessedOutputBuffer(j);
        if (!this.tunneling) {
            this.buffersInCodecCount--;
        }
    }

    /* access modifiers changed from: protected */
    public void onProcessedStreamChange() {
        super.onProcessedStreamChange();
        clearRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropOutputBuffer(long j, long j2, boolean z) {
        return isBufferLate(j) && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2, boolean z) {
        return isBufferVeryLate(j) && !z;
    }

    /* access modifiers changed from: protected */
    public boolean shouldForceRenderOutputBuffer(long j, long j2) {
        return isBufferLate(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void skipOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("skipVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        this.decoderCounters.skippedOutputBufferCount++;
    }

    /* access modifiers changed from: protected */
    public void dropOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        TraceUtil.beginSection("dropVideoBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, false);
        TraceUtil.endSection();
        updateDroppedBufferCounters(0, 1);
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(long j, boolean z) throws ExoPlaybackException {
        int skipSource = skipSource(j);
        if (skipSource == 0) {
            return false;
        }
        if (z) {
            this.decoderCounters.skippedInputBufferCount += skipSource;
            this.decoderCounters.skippedOutputBufferCount += this.buffersInCodecCount;
        } else {
            this.decoderCounters.droppedToKeyframeCount++;
            updateDroppedBufferCounters(skipSource, this.buffersInCodecCount);
        }
        flushOrReinitializeCodec();
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i, int i2) {
        this.decoderCounters.droppedInputBufferCount += i;
        int i3 = i + i2;
        this.decoderCounters.droppedBufferCount += i3;
        this.droppedFrames += i3;
        this.consecutiveDroppedFrameCount += i3;
        this.decoderCounters.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, this.decoderCounters.maxConsecutiveDroppedBufferCount);
        int i4 = this.maxDroppedFramesToNotify;
        if (i4 > 0 && this.droppedFrames >= i4) {
            maybeNotifyDroppedFrames();
        }
    }

    /* access modifiers changed from: protected */
    public void updateVideoFrameProcessingOffsetCounters(long j) {
        this.decoderCounters.addVideoFrameProcessingOffset(j);
        this.totalVideoFrameProcessingOffsetUs += j;
        this.videoFrameProcessingOffsetCount++;
    }

    /* access modifiers changed from: protected */
    public void renderOutputBuffer(MediaCodecAdapter mediaCodecAdapter, int i, long j) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, true);
        TraceUtil.endSection();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    /* access modifiers changed from: protected */
    public void renderOutputBufferV21(MediaCodecAdapter mediaCodecAdapter, int i, long j, long j2) {
        maybeNotifyVideoSizeChanged();
        TraceUtil.beginSection("releaseOutputBuffer");
        mediaCodecAdapter.releaseOutputBuffer(i, j2);
        TraceUtil.endSection();
        this.lastRenderRealtimeUs = SystemClock.elapsedRealtime() * 1000;
        this.decoderCounters.renderedOutputBufferCount++;
        this.consecutiveDroppedFrameCount = 0;
        maybeNotifyRenderedFirstFrame();
    }

    private boolean shouldUsePlaceholderSurface(MediaCodecInfo mediaCodecInfo) {
        return Util.SDK_INT >= 23 && !this.tunneling && !codecNeedsSetOutputSurfaceWorkaround(mediaCodecInfo.name) && (!mediaCodecInfo.secure || PlaceholderSurface.isSecureSupported(this.context));
    }

    private void releasePlaceholderSurface() {
        Surface surface2 = this.surface;
        PlaceholderSurface placeholderSurface2 = this.placeholderSurface;
        if (surface2 == placeholderSurface2) {
            this.surface = null;
        }
        placeholderSurface2.release();
        this.placeholderSurface = null;
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    private void clearRenderedFirstFrame() {
        MediaCodecAdapter codec;
        this.renderedFirstFrameAfterReset = false;
        if (Util.SDK_INT >= 23 && this.tunneling && (codec = getCodec()) != null) {
            this.tunnelingOnFrameRenderedListener = new OnFrameRenderedListenerV23(codec);
        }
    }

    /* access modifiers changed from: package-private */
    public void maybeNotifyRenderedFirstFrame() {
        this.renderedFirstFrameAfterEnable = true;
        if (!this.renderedFirstFrameAfterReset) {
            this.renderedFirstFrameAfterReset = true;
            this.eventDispatcher.renderedFirstFrame(this.surface);
            this.haveReportedFirstFrameRenderedForCurrentSurface = true;
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.haveReportedFirstFrameRenderedForCurrentSurface) {
            this.eventDispatcher.renderedFirstFrame(this.surface);
        }
    }

    private void clearReportedVideoSize() {
        this.reportedVideoSize = null;
    }

    private void maybeNotifyVideoSizeChanged() {
        if (this.currentWidth != -1 || this.currentHeight != -1) {
            VideoSize videoSize = this.reportedVideoSize;
            if (videoSize == null || videoSize.width != this.currentWidth || this.reportedVideoSize.height != this.currentHeight || this.reportedVideoSize.unappliedRotationDegrees != this.currentUnappliedRotationDegrees || this.reportedVideoSize.pixelWidthHeightRatio != this.currentPixelWidthHeightRatio) {
                VideoSize videoSize2 = new VideoSize(this.currentWidth, this.currentHeight, this.currentUnappliedRotationDegrees, this.currentPixelWidthHeightRatio);
                this.reportedVideoSize = videoSize2;
                this.eventDispatcher.videoSizeChanged(videoSize2);
            }
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        VideoSize videoSize = this.reportedVideoSize;
        if (videoSize != null) {
            this.eventDispatcher.videoSizeChanged(videoSize);
        }
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private void maybeNotifyVideoFrameProcessingOffset() {
        int i = this.videoFrameProcessingOffsetCount;
        if (i != 0) {
            this.eventDispatcher.reportVideoFrameProcessingOffset(this.totalVideoFrameProcessingOffsetUs, i);
            this.totalVideoFrameProcessingOffsetUs = 0;
            this.videoFrameProcessingOffsetCount = 0;
        }
    }

    private static void setHdr10PlusInfoV29(MediaCodecAdapter mediaCodecAdapter, byte[] bArr) {
        Bundle bundle = new Bundle();
        bundle.putByteArray("hdr10-plus-info", bArr);
        mediaCodecAdapter.setParameters(bundle);
    }

    /* access modifiers changed from: protected */
    public void setOutputSurfaceV23(MediaCodecAdapter mediaCodecAdapter, Surface surface2) {
        mediaCodecAdapter.setOutputSurface(surface2);
    }

    private static void configureTunnelingV21(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled("tunneled-playback", true);
        mediaFormat.setInteger("audio-session-id", i);
    }

    /* access modifiers changed from: protected */
    public MediaFormat getMediaFormat(Format format, String str, CodecMaxValues codecMaxValues2, float f, boolean z, int i) {
        Pair<Integer, Integer> codecProfileAndLevel;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str);
        mediaFormat.setInteger("width", format.width);
        mediaFormat.setInteger("height", format.height);
        MediaFormatUtil.setCsdBuffers(mediaFormat, format.initializationData);
        MediaFormatUtil.maybeSetFloat(mediaFormat, "frame-rate", format.frameRate);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "rotation-degrees", format.rotationDegrees);
        MediaFormatUtil.maybeSetColorInfo(mediaFormat, format.colorInfo);
        if (MimeTypes.VIDEO_DOLBY_VISION.equals(format.sampleMimeType) && (codecProfileAndLevel = MediaCodecUtil.getCodecProfileAndLevel(format)) != null) {
            MediaFormatUtil.maybeSetInteger(mediaFormat, Scopes.PROFILE, ((Integer) codecProfileAndLevel.first).intValue());
        }
        mediaFormat.setInteger("max-width", codecMaxValues2.width);
        mediaFormat.setInteger("max-height", codecMaxValues2.height);
        MediaFormatUtil.maybeSetInteger(mediaFormat, "max-input-size", codecMaxValues2.inputSize);
        if (Util.SDK_INT >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            configureTunnelingV21(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format, Format[] formatArr) {
        int codecMaxInputSize;
        int i = format.width;
        int i2 = format.height;
        int maxInputSize = getMaxInputSize(mediaCodecInfo, format);
        if (formatArr.length == 1) {
            if (!(maxInputSize == -1 || (codecMaxInputSize = getCodecMaxInputSize(mediaCodecInfo, format)) == -1)) {
                maxInputSize = Math.min((int) (((float) maxInputSize) * INITIAL_FORMAT_MAX_INPUT_SIZE_SCALE_FACTOR), codecMaxInputSize);
            }
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int length = formatArr.length;
        boolean z = false;
        for (int i3 = 0; i3 < length; i3++) {
            Format format2 = formatArr[i3];
            if (format.colorInfo != null && format2.colorInfo == null) {
                format2 = format2.buildUpon().setColorInfo(format.colorInfo).build();
            }
            if (mediaCodecInfo.canReuseCodec(format, format2).result != 0) {
                z |= format2.width == -1 || format2.height == -1;
                i = Math.max(i, format2.width);
                i2 = Math.max(i2, format2.height);
                maxInputSize = Math.max(maxInputSize, getMaxInputSize(mediaCodecInfo, format2));
            }
        }
        if (z) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i + "x" + i2);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format);
            if (codecMaxSize != null) {
                i = Math.max(i, codecMaxSize.x);
                i2 = Math.max(i2, codecMaxSize.y);
                maxInputSize = Math.max(maxInputSize, getCodecMaxInputSize(mediaCodecInfo, format.buildUpon().setWidth(i).setHeight(i2).build()));
                Log.w(TAG, "Codec max resolution adjusted to: " + i + "x" + i2);
            }
        }
        return new CodecMaxValues(i, i2, maxInputSize);
    }

    /* access modifiers changed from: protected */
    public MediaCodecDecoderException createDecoderException(Throwable th, MediaCodecInfo mediaCodecInfo) {
        return new MediaCodecVideoDecoderException(th, mediaCodecInfo, this.surface);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format) {
        boolean z = format.height > format.width;
        int i = z ? format.height : format.width;
        int i2 = z ? format.width : format.height;
        float f = ((float) i2) / ((float) i);
        for (int i3 : STANDARD_LONG_EDGE_VIDEO_PX) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                try {
                    int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                    int ceilDivide2 = Util.ceilDivide(i4, 16) * 16;
                    if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                        int i6 = z ? ceilDivide2 : ceilDivide;
                        if (!z) {
                            ceilDivide = ceilDivide2;
                        }
                        return new Point(i6, ceilDivide);
                    }
                } catch (MediaCodecUtil.DecoderQueryException unused) {
                }
            }
        }
        return null;
    }

    protected static int getMaxInputSize(MediaCodecInfo mediaCodecInfo, Format format) {
        if (format.maxInputSize == -1) {
            return getCodecMaxInputSize(mediaCodecInfo, format);
        }
        int size = format.initializationData.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.initializationData.get(i2).length;
        }
        return format.maxInputSize + i;
    }

    private static boolean deviceNeedsNoPostProcessWorkaround() {
        return "NVIDIA".equals(Util.MANUFACTURER);
    }

    /* access modifiers changed from: protected */
    public boolean codecNeedsSetOutputSurfaceWorkaround(String str) {
        if (str.startsWith("OMX.google")) {
            return false;
        }
        synchronized (MediaCodecVideoRenderer.class) {
            if (!evaluatedDeviceNeedsSetOutputSurfaceWorkaround) {
                deviceNeedsSetOutputSurfaceWorkaround = evaluateDeviceNeedsSetOutputSurfaceWorkaround();
                evaluatedDeviceNeedsSetOutputSurfaceWorkaround = true;
            }
        }
        return deviceNeedsSetOutputSurfaceWorkaround;
    }

    /* access modifiers changed from: protected */
    public Surface getSurface() {
        return this.surface;
    }

    protected static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    private static int getMaxSampleSize(int i, int i2) {
        return (i * 3) / (i2 * 2);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:469:0x0848, code lost:
        if (r0.equals("PGN528") == false) goto L_0x0114;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:494:0x08b0, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean evaluateDeviceNeedsSetOutputSurfaceWorkaround() {
        /*
            int r0 = com.google.android.exoplayer2.util.Util.SDK_INT
            r1 = 7
            r2 = 6
            r3 = 5
            r4 = 4
            r5 = 3
            r6 = 2
            r7 = -1
            r8 = 0
            r9 = 1
            r10 = 28
            if (r0 > r10) goto L_0x007a
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.DEVICE
            r0.hashCode()
            int r11 = r0.hashCode()
            switch(r11) {
                case -1339091551: goto L_0x006b;
                case -1220081023: goto L_0x0060;
                case -1220066608: goto L_0x0055;
                case -1012436106: goto L_0x004a;
                case -760312546: goto L_0x003f;
                case -64886864: goto L_0x0034;
                case 3415681: goto L_0x0029;
                case 825323514: goto L_0x001e;
                default: goto L_0x001b;
            }
        L_0x001b:
            r0 = r7
            goto L_0x0075
        L_0x001e:
            java.lang.String r11 = "machuca"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0027
            goto L_0x001b
        L_0x0027:
            r0 = r1
            goto L_0x0075
        L_0x0029:
            java.lang.String r11 = "once"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0032
            goto L_0x001b
        L_0x0032:
            r0 = r2
            goto L_0x0075
        L_0x0034:
            java.lang.String r11 = "magnolia"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x003d
            goto L_0x001b
        L_0x003d:
            r0 = r3
            goto L_0x0075
        L_0x003f:
            java.lang.String r11 = "aquaman"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0048
            goto L_0x001b
        L_0x0048:
            r0 = r4
            goto L_0x0075
        L_0x004a:
            java.lang.String r11 = "oneday"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0053
            goto L_0x001b
        L_0x0053:
            r0 = r5
            goto L_0x0075
        L_0x0055:
            java.lang.String r11 = "dangalUHD"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x005e
            goto L_0x001b
        L_0x005e:
            r0 = r6
            goto L_0x0075
        L_0x0060:
            java.lang.String r11 = "dangalFHD"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0069
            goto L_0x001b
        L_0x0069:
            r0 = r9
            goto L_0x0075
        L_0x006b:
            java.lang.String r11 = "dangal"
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x0074
            goto L_0x001b
        L_0x0074:
            r0 = r8
        L_0x0075:
            switch(r0) {
                case 0: goto L_0x0079;
                case 1: goto L_0x0079;
                case 2: goto L_0x0079;
                case 3: goto L_0x0079;
                case 4: goto L_0x0079;
                case 5: goto L_0x0079;
                case 6: goto L_0x0079;
                case 7: goto L_0x0079;
                default: goto L_0x0078;
            }
        L_0x0078:
            goto L_0x007a
        L_0x0079:
            return r9
        L_0x007a:
            int r0 = com.google.android.exoplayer2.util.Util.SDK_INT
            r11 = 27
            if (r0 > r11) goto L_0x008b
            java.lang.String r0 = "HWEML"
            java.lang.String r12 = com.google.android.exoplayer2.util.Util.DEVICE
            boolean r0 = r0.equals(r12)
            if (r0 == 0) goto L_0x008b
            return r9
        L_0x008b:
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.MODEL
            r0.hashCode()
            int r12 = r0.hashCode()
            r13 = 8
            switch(r12) {
                case -349662828: goto L_0x00f5;
                case -321033677: goto L_0x00ea;
                case 2006354: goto L_0x00df;
                case 2006367: goto L_0x00d4;
                case 2006371: goto L_0x00c9;
                case 1785421873: goto L_0x00be;
                case 1785421876: goto L_0x00b3;
                case 1798172390: goto L_0x00a8;
                case 2119412532: goto L_0x009c;
                default: goto L_0x0099;
            }
        L_0x0099:
            r0 = r7
            goto L_0x00ff
        L_0x009c:
            java.lang.String r12 = "AFTEUFF014"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00a5
            goto L_0x0099
        L_0x00a5:
            r0 = r13
            goto L_0x00ff
        L_0x00a8:
            java.lang.String r12 = "AFTSO001"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00b1
            goto L_0x0099
        L_0x00b1:
            r0 = r1
            goto L_0x00ff
        L_0x00b3:
            java.lang.String r12 = "AFTEU014"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00bc
            goto L_0x0099
        L_0x00bc:
            r0 = r2
            goto L_0x00ff
        L_0x00be:
            java.lang.String r12 = "AFTEU011"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00c7
            goto L_0x0099
        L_0x00c7:
            r0 = r3
            goto L_0x00ff
        L_0x00c9:
            java.lang.String r12 = "AFTR"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00d2
            goto L_0x0099
        L_0x00d2:
            r0 = r4
            goto L_0x00ff
        L_0x00d4:
            java.lang.String r12 = "AFTN"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00dd
            goto L_0x0099
        L_0x00dd:
            r0 = r5
            goto L_0x00ff
        L_0x00df:
            java.lang.String r12 = "AFTA"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00e8
            goto L_0x0099
        L_0x00e8:
            r0 = r6
            goto L_0x00ff
        L_0x00ea:
            java.lang.String r12 = "AFTKMST12"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00f3
            goto L_0x0099
        L_0x00f3:
            r0 = r9
            goto L_0x00ff
        L_0x00f5:
            java.lang.String r12 = "AFTJMST12"
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00fe
            goto L_0x0099
        L_0x00fe:
            r0 = r8
        L_0x00ff:
            switch(r0) {
                case 0: goto L_0x08b2;
                case 1: goto L_0x08b2;
                case 2: goto L_0x08b2;
                case 3: goto L_0x08b2;
                case 4: goto L_0x08b2;
                case 5: goto L_0x08b2;
                case 6: goto L_0x08b2;
                case 7: goto L_0x08b2;
                case 8: goto L_0x08b2;
                default: goto L_0x0102;
            }
        L_0x0102:
            int r0 = com.google.android.exoplayer2.util.Util.SDK_INT
            r12 = 26
            if (r0 > r12) goto L_0x08b1
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.DEVICE
            r0.hashCode()
            int r14 = r0.hashCode()
            switch(r14) {
                case -2144781245: goto L_0x0894;
                case -2144781185: goto L_0x0888;
                case -2144781160: goto L_0x087c;
                case -2097309513: goto L_0x0870;
                case -2022874474: goto L_0x0864;
                case -1978993182: goto L_0x0858;
                case -1978990237: goto L_0x084c;
                case -1936688988: goto L_0x0842;
                case -1936688066: goto L_0x0835;
                case -1936688065: goto L_0x0827;
                case -1931988508: goto L_0x0819;
                case -1885099851: goto L_0x080b;
                case -1696512866: goto L_0x07fd;
                case -1680025915: goto L_0x07ef;
                case -1615810839: goto L_0x07e1;
                case -1600724499: goto L_0x07d3;
                case -1554255044: goto L_0x07c5;
                case -1481772737: goto L_0x07b7;
                case -1481772730: goto L_0x07a9;
                case -1481772729: goto L_0x079b;
                case -1320080169: goto L_0x078d;
                case -1217592143: goto L_0x077f;
                case -1180384755: goto L_0x0771;
                case -1139198265: goto L_0x0763;
                case -1052835013: goto L_0x0755;
                case -993250464: goto L_0x0747;
                case -993250458: goto L_0x073a;
                case -965403638: goto L_0x072d;
                case -958336948: goto L_0x0720;
                case -879245230: goto L_0x0712;
                case -842500323: goto L_0x0704;
                case -821392978: goto L_0x06f6;
                case -797483286: goto L_0x06e8;
                case -794946968: goto L_0x06da;
                case -788334647: goto L_0x06cc;
                case -782144577: goto L_0x06be;
                case -575125681: goto L_0x06b0;
                case -521118391: goto L_0x06a2;
                case -430914369: goto L_0x0694;
                case -290434366: goto L_0x0686;
                case -282781963: goto L_0x0678;
                case -277133239: goto L_0x066a;
                case -173639913: goto L_0x065c;
                case -56598463: goto L_0x064e;
                case 2126: goto L_0x0640;
                case 2564: goto L_0x0632;
                case 2715: goto L_0x0624;
                case 2719: goto L_0x0616;
                case 3091: goto L_0x0608;
                case 3483: goto L_0x05fa;
                case 73405: goto L_0x05ec;
                case 75537: goto L_0x05de;
                case 75739: goto L_0x05d0;
                case 76779: goto L_0x05c2;
                case 78669: goto L_0x05b4;
                case 79305: goto L_0x05a6;
                case 80618: goto L_0x0598;
                case 88274: goto L_0x058a;
                case 98846: goto L_0x057c;
                case 98848: goto L_0x056e;
                case 99329: goto L_0x0560;
                case 101481: goto L_0x0552;
                case 1513190: goto L_0x0544;
                case 1514184: goto L_0x0536;
                case 1514185: goto L_0x0528;
                case 2133089: goto L_0x051a;
                case 2133091: goto L_0x050c;
                case 2133120: goto L_0x04fe;
                case 2133151: goto L_0x04f0;
                case 2133182: goto L_0x04e2;
                case 2133184: goto L_0x04d4;
                case 2436959: goto L_0x04c6;
                case 2463773: goto L_0x04b8;
                case 2464648: goto L_0x04aa;
                case 2689555: goto L_0x049c;
                case 3154429: goto L_0x048e;
                case 3284551: goto L_0x0480;
                case 3351335: goto L_0x0472;
                case 3386211: goto L_0x0464;
                case 41325051: goto L_0x0456;
                case 51349633: goto L_0x0448;
                case 51350594: goto L_0x043a;
                case 55178625: goto L_0x042c;
                case 61542055: goto L_0x041e;
                case 65355429: goto L_0x0410;
                case 66214468: goto L_0x0402;
                case 66214470: goto L_0x03f4;
                case 66214473: goto L_0x03e6;
                case 66215429: goto L_0x03d8;
                case 66215431: goto L_0x03ca;
                case 66215433: goto L_0x03bc;
                case 66216390: goto L_0x03ae;
                case 76402249: goto L_0x03a0;
                case 76404105: goto L_0x0392;
                case 76404911: goto L_0x0384;
                case 80963634: goto L_0x0376;
                case 82882791: goto L_0x0368;
                case 98715550: goto L_0x035a;
                case 101370885: goto L_0x034c;
                case 102844228: goto L_0x033e;
                case 165221241: goto L_0x0330;
                case 182191441: goto L_0x0322;
                case 245388979: goto L_0x0314;
                case 287431619: goto L_0x0306;
                case 307593612: goto L_0x02f8;
                case 308517133: goto L_0x02ea;
                case 316215098: goto L_0x02dc;
                case 316215116: goto L_0x02ce;
                case 316246811: goto L_0x02c0;
                case 316246818: goto L_0x02b2;
                case 407160593: goto L_0x02a4;
                case 507412548: goto L_0x0296;
                case 793982701: goto L_0x0288;
                case 794038622: goto L_0x027a;
                case 794040393: goto L_0x026c;
                case 835649806: goto L_0x025e;
                case 917340916: goto L_0x0250;
                case 958008161: goto L_0x0242;
                case 1060579533: goto L_0x0234;
                case 1150207623: goto L_0x0226;
                case 1176899427: goto L_0x0218;
                case 1280332038: goto L_0x020a;
                case 1306947716: goto L_0x01fc;
                case 1349174697: goto L_0x01ee;
                case 1522194893: goto L_0x01e0;
                case 1691543273: goto L_0x01d2;
                case 1691544261: goto L_0x01c4;
                case 1709443163: goto L_0x01b6;
                case 1865889110: goto L_0x01a8;
                case 1906253259: goto L_0x019a;
                case 1977196784: goto L_0x018c;
                case 2006372676: goto L_0x017f;
                case 2019281702: goto L_0x0172;
                case 2029784656: goto L_0x0165;
                case 2030379515: goto L_0x0158;
                case 2033393791: goto L_0x014b;
                case 2047190025: goto L_0x013e;
                case 2047252157: goto L_0x0131;
                case 2048319463: goto L_0x0124;
                case 2048855701: goto L_0x0117;
                default: goto L_0x0114;
            }
        L_0x0114:
            r1 = r7
            goto L_0x089f
        L_0x0117:
            java.lang.String r1 = "HWWAS-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0120
            goto L_0x0114
        L_0x0120:
            r1 = 139(0x8b, float:1.95E-43)
            goto L_0x089f
        L_0x0124:
            java.lang.String r1 = "HWVNS-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x012d
            goto L_0x0114
        L_0x012d:
            r1 = 138(0x8a, float:1.93E-43)
            goto L_0x089f
        L_0x0131:
            java.lang.String r1 = "ELUGA_Prim"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x013a
            goto L_0x0114
        L_0x013a:
            r1 = 137(0x89, float:1.92E-43)
            goto L_0x089f
        L_0x013e:
            java.lang.String r1 = "ELUGA_Note"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0147
            goto L_0x0114
        L_0x0147:
            r1 = 136(0x88, float:1.9E-43)
            goto L_0x089f
        L_0x014b:
            java.lang.String r1 = "ASUS_X00AD_2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0154
            goto L_0x0114
        L_0x0154:
            r1 = 135(0x87, float:1.89E-43)
            goto L_0x089f
        L_0x0158:
            java.lang.String r1 = "HWCAM-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0161
            goto L_0x0114
        L_0x0161:
            r1 = 134(0x86, float:1.88E-43)
            goto L_0x089f
        L_0x0165:
            java.lang.String r1 = "HWBLN-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x016e
            goto L_0x0114
        L_0x016e:
            r1 = 133(0x85, float:1.86E-43)
            goto L_0x089f
        L_0x0172:
            java.lang.String r1 = "DM-01K"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x017b
            goto L_0x0114
        L_0x017b:
            r1 = 132(0x84, float:1.85E-43)
            goto L_0x089f
        L_0x017f:
            java.lang.String r1 = "BRAVIA_ATV3_4K"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0188
            goto L_0x0114
        L_0x0188:
            r1 = 131(0x83, float:1.84E-43)
            goto L_0x089f
        L_0x018c:
            java.lang.String r1 = "Infinix-X572"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0196
            goto L_0x0114
        L_0x0196:
            r1 = 130(0x82, float:1.82E-43)
            goto L_0x089f
        L_0x019a:
            java.lang.String r1 = "PB2-670M"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01a4
            goto L_0x0114
        L_0x01a4:
            r1 = 129(0x81, float:1.81E-43)
            goto L_0x089f
        L_0x01a8:
            java.lang.String r1 = "santoni"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01b2
            goto L_0x0114
        L_0x01b2:
            r1 = 128(0x80, float:1.794E-43)
            goto L_0x089f
        L_0x01b6:
            java.lang.String r1 = "iball8735_9806"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01c0
            goto L_0x0114
        L_0x01c0:
            r1 = 127(0x7f, float:1.78E-43)
            goto L_0x089f
        L_0x01c4:
            java.lang.String r1 = "CPH1715"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01ce
            goto L_0x0114
        L_0x01ce:
            r1 = 126(0x7e, float:1.77E-43)
            goto L_0x089f
        L_0x01d2:
            java.lang.String r1 = "CPH1609"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01dc
            goto L_0x0114
        L_0x01dc:
            r1 = 125(0x7d, float:1.75E-43)
            goto L_0x089f
        L_0x01e0:
            java.lang.String r1 = "woods_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01ea
            goto L_0x0114
        L_0x01ea:
            r1 = 124(0x7c, float:1.74E-43)
            goto L_0x089f
        L_0x01ee:
            java.lang.String r1 = "htc_e56ml_dtul"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x01f8
            goto L_0x0114
        L_0x01f8:
            r1 = 123(0x7b, float:1.72E-43)
            goto L_0x089f
        L_0x01fc:
            java.lang.String r1 = "EverStar_S"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0206
            goto L_0x0114
        L_0x0206:
            r1 = 122(0x7a, float:1.71E-43)
            goto L_0x089f
        L_0x020a:
            java.lang.String r1 = "hwALE-H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0214
            goto L_0x0114
        L_0x0214:
            r1 = 121(0x79, float:1.7E-43)
            goto L_0x089f
        L_0x0218:
            java.lang.String r1 = "itel_S41"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0222
            goto L_0x0114
        L_0x0222:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x089f
        L_0x0226:
            java.lang.String r1 = "LS-5017"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0230
            goto L_0x0114
        L_0x0230:
            r1 = 119(0x77, float:1.67E-43)
            goto L_0x089f
        L_0x0234:
            java.lang.String r1 = "panell_d"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x023e
            goto L_0x0114
        L_0x023e:
            r1 = 118(0x76, float:1.65E-43)
            goto L_0x089f
        L_0x0242:
            java.lang.String r1 = "j2xlteins"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x024c
            goto L_0x0114
        L_0x024c:
            r1 = 117(0x75, float:1.64E-43)
            goto L_0x089f
        L_0x0250:
            java.lang.String r1 = "A7000plus"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x025a
            goto L_0x0114
        L_0x025a:
            r1 = 116(0x74, float:1.63E-43)
            goto L_0x089f
        L_0x025e:
            java.lang.String r1 = "manning"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0268
            goto L_0x0114
        L_0x0268:
            r1 = 115(0x73, float:1.61E-43)
            goto L_0x089f
        L_0x026c:
            java.lang.String r1 = "GIONEE_WBL7519"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0276
            goto L_0x0114
        L_0x0276:
            r1 = 114(0x72, float:1.6E-43)
            goto L_0x089f
        L_0x027a:
            java.lang.String r1 = "GIONEE_WBL7365"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0284
            goto L_0x0114
        L_0x0284:
            r1 = 113(0x71, float:1.58E-43)
            goto L_0x089f
        L_0x0288:
            java.lang.String r1 = "GIONEE_WBL5708"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0292
            goto L_0x0114
        L_0x0292:
            r1 = 112(0x70, float:1.57E-43)
            goto L_0x089f
        L_0x0296:
            java.lang.String r1 = "QM16XE_U"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02a0
            goto L_0x0114
        L_0x02a0:
            r1 = 111(0x6f, float:1.56E-43)
            goto L_0x089f
        L_0x02a4:
            java.lang.String r1 = "Pixi5-10_4G"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02ae
            goto L_0x0114
        L_0x02ae:
            r1 = 110(0x6e, float:1.54E-43)
            goto L_0x089f
        L_0x02b2:
            java.lang.String r1 = "TB3-850M"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02bc
            goto L_0x0114
        L_0x02bc:
            r1 = 109(0x6d, float:1.53E-43)
            goto L_0x089f
        L_0x02c0:
            java.lang.String r1 = "TB3-850F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02ca
            goto L_0x0114
        L_0x02ca:
            r1 = 108(0x6c, float:1.51E-43)
            goto L_0x089f
        L_0x02ce:
            java.lang.String r1 = "TB3-730X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02d8
            goto L_0x0114
        L_0x02d8:
            r1 = 107(0x6b, float:1.5E-43)
            goto L_0x089f
        L_0x02dc:
            java.lang.String r1 = "TB3-730F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02e6
            goto L_0x0114
        L_0x02e6:
            r1 = 106(0x6a, float:1.49E-43)
            goto L_0x089f
        L_0x02ea:
            java.lang.String r1 = "A7020a48"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x02f4
            goto L_0x0114
        L_0x02f4:
            r1 = 105(0x69, float:1.47E-43)
            goto L_0x089f
        L_0x02f8:
            java.lang.String r1 = "A7010a48"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0302
            goto L_0x0114
        L_0x0302:
            r1 = 104(0x68, float:1.46E-43)
            goto L_0x089f
        L_0x0306:
            java.lang.String r1 = "griffin"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0310
            goto L_0x0114
        L_0x0310:
            r1 = 103(0x67, float:1.44E-43)
            goto L_0x089f
        L_0x0314:
            java.lang.String r1 = "marino_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x031e
            goto L_0x0114
        L_0x031e:
            r1 = 102(0x66, float:1.43E-43)
            goto L_0x089f
        L_0x0322:
            java.lang.String r1 = "CPY83_I00"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x032c
            goto L_0x0114
        L_0x032c:
            r1 = 101(0x65, float:1.42E-43)
            goto L_0x089f
        L_0x0330:
            java.lang.String r1 = "A2016a40"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x033a
            goto L_0x0114
        L_0x033a:
            r1 = 100
            goto L_0x089f
        L_0x033e:
            java.lang.String r1 = "le_x6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0348
            goto L_0x0114
        L_0x0348:
            r1 = 99
            goto L_0x089f
        L_0x034c:
            java.lang.String r1 = "l5460"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0356
            goto L_0x0114
        L_0x0356:
            r1 = 98
            goto L_0x089f
        L_0x035a:
            java.lang.String r1 = "i9031"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0364
            goto L_0x0114
        L_0x0364:
            r1 = 97
            goto L_0x089f
        L_0x0368:
            java.lang.String r1 = "X3_HK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0372
            goto L_0x0114
        L_0x0372:
            r1 = 96
            goto L_0x089f
        L_0x0376:
            java.lang.String r1 = "V23GB"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0380
            goto L_0x0114
        L_0x0380:
            r1 = 95
            goto L_0x089f
        L_0x0384:
            java.lang.String r1 = "Q4310"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x038e
            goto L_0x0114
        L_0x038e:
            r1 = 94
            goto L_0x089f
        L_0x0392:
            java.lang.String r1 = "Q4260"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x039c
            goto L_0x0114
        L_0x039c:
            r1 = 93
            goto L_0x089f
        L_0x03a0:
            java.lang.String r1 = "PRO7S"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03aa
            goto L_0x0114
        L_0x03aa:
            r1 = 92
            goto L_0x089f
        L_0x03ae:
            java.lang.String r1 = "F3311"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03b8
            goto L_0x0114
        L_0x03b8:
            r1 = 91
            goto L_0x089f
        L_0x03bc:
            java.lang.String r1 = "F3215"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03c6
            goto L_0x0114
        L_0x03c6:
            r1 = 90
            goto L_0x089f
        L_0x03ca:
            java.lang.String r1 = "F3213"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03d4
            goto L_0x0114
        L_0x03d4:
            r1 = 89
            goto L_0x089f
        L_0x03d8:
            java.lang.String r1 = "F3211"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03e2
            goto L_0x0114
        L_0x03e2:
            r1 = 88
            goto L_0x089f
        L_0x03e6:
            java.lang.String r1 = "F3116"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03f0
            goto L_0x0114
        L_0x03f0:
            r1 = 87
            goto L_0x089f
        L_0x03f4:
            java.lang.String r1 = "F3113"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x03fe
            goto L_0x0114
        L_0x03fe:
            r1 = 86
            goto L_0x089f
        L_0x0402:
            java.lang.String r1 = "F3111"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x040c
            goto L_0x0114
        L_0x040c:
            r1 = 85
            goto L_0x089f
        L_0x0410:
            java.lang.String r1 = "E5643"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x041a
            goto L_0x0114
        L_0x041a:
            r1 = 84
            goto L_0x089f
        L_0x041e:
            java.lang.String r1 = "A1601"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0428
            goto L_0x0114
        L_0x0428:
            r1 = 83
            goto L_0x089f
        L_0x042c:
            java.lang.String r1 = "Aura_Note_2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0436
            goto L_0x0114
        L_0x0436:
            r1 = 82
            goto L_0x089f
        L_0x043a:
            java.lang.String r1 = "602LV"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0444
            goto L_0x0114
        L_0x0444:
            r1 = 81
            goto L_0x089f
        L_0x0448:
            java.lang.String r1 = "601LV"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0452
            goto L_0x0114
        L_0x0452:
            r1 = 80
            goto L_0x089f
        L_0x0456:
            java.lang.String r1 = "MEIZU_M5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0460
            goto L_0x0114
        L_0x0460:
            r1 = 79
            goto L_0x089f
        L_0x0464:
            java.lang.String r1 = "p212"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x046e
            goto L_0x0114
        L_0x046e:
            r1 = 78
            goto L_0x089f
        L_0x0472:
            java.lang.String r1 = "mido"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x047c
            goto L_0x0114
        L_0x047c:
            r1 = 77
            goto L_0x089f
        L_0x0480:
            java.lang.String r1 = "kate"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x048a
            goto L_0x0114
        L_0x048a:
            r1 = 76
            goto L_0x089f
        L_0x048e:
            java.lang.String r1 = "fugu"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0498
            goto L_0x0114
        L_0x0498:
            r1 = 75
            goto L_0x089f
        L_0x049c:
            java.lang.String r1 = "XE2X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04a6
            goto L_0x0114
        L_0x04a6:
            r1 = 74
            goto L_0x089f
        L_0x04aa:
            java.lang.String r1 = "Q427"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04b4
            goto L_0x0114
        L_0x04b4:
            r1 = 73
            goto L_0x089f
        L_0x04b8:
            java.lang.String r1 = "Q350"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04c2
            goto L_0x0114
        L_0x04c2:
            r1 = 72
            goto L_0x089f
        L_0x04c6:
            java.lang.String r1 = "P681"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04d0
            goto L_0x0114
        L_0x04d0:
            r1 = 71
            goto L_0x089f
        L_0x04d4:
            java.lang.String r1 = "F04J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04de
            goto L_0x0114
        L_0x04de:
            r1 = 70
            goto L_0x089f
        L_0x04e2:
            java.lang.String r1 = "F04H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04ec
            goto L_0x0114
        L_0x04ec:
            r1 = 69
            goto L_0x089f
        L_0x04f0:
            java.lang.String r1 = "F03H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x04fa
            goto L_0x0114
        L_0x04fa:
            r1 = 68
            goto L_0x089f
        L_0x04fe:
            java.lang.String r1 = "F02H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0508
            goto L_0x0114
        L_0x0508:
            r1 = 67
            goto L_0x089f
        L_0x050c:
            java.lang.String r1 = "F01J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0516
            goto L_0x0114
        L_0x0516:
            r1 = 66
            goto L_0x089f
        L_0x051a:
            java.lang.String r1 = "F01H"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0524
            goto L_0x0114
        L_0x0524:
            r1 = 65
            goto L_0x089f
        L_0x0528:
            java.lang.String r1 = "1714"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0532
            goto L_0x0114
        L_0x0532:
            r1 = 64
            goto L_0x089f
        L_0x0536:
            java.lang.String r1 = "1713"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0540
            goto L_0x0114
        L_0x0540:
            r1 = 63
            goto L_0x089f
        L_0x0544:
            java.lang.String r1 = "1601"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x054e
            goto L_0x0114
        L_0x054e:
            r1 = 62
            goto L_0x089f
        L_0x0552:
            java.lang.String r1 = "flo"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x055c
            goto L_0x0114
        L_0x055c:
            r1 = 61
            goto L_0x089f
        L_0x0560:
            java.lang.String r1 = "deb"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x056a
            goto L_0x0114
        L_0x056a:
            r1 = 60
            goto L_0x089f
        L_0x056e:
            java.lang.String r1 = "cv3"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0578
            goto L_0x0114
        L_0x0578:
            r1 = 59
            goto L_0x089f
        L_0x057c:
            java.lang.String r1 = "cv1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0586
            goto L_0x0114
        L_0x0586:
            r1 = 58
            goto L_0x089f
        L_0x058a:
            java.lang.String r1 = "Z80"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0594
            goto L_0x0114
        L_0x0594:
            r1 = 57
            goto L_0x089f
        L_0x0598:
            java.lang.String r1 = "QX1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05a2
            goto L_0x0114
        L_0x05a2:
            r1 = 56
            goto L_0x089f
        L_0x05a6:
            java.lang.String r1 = "PLE"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05b0
            goto L_0x0114
        L_0x05b0:
            r1 = 55
            goto L_0x089f
        L_0x05b4:
            java.lang.String r1 = "P85"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05be
            goto L_0x0114
        L_0x05be:
            r1 = 54
            goto L_0x089f
        L_0x05c2:
            java.lang.String r1 = "MX6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05cc
            goto L_0x0114
        L_0x05cc:
            r1 = 53
            goto L_0x089f
        L_0x05d0:
            java.lang.String r1 = "M5c"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05da
            goto L_0x0114
        L_0x05da:
            r1 = 52
            goto L_0x089f
        L_0x05de:
            java.lang.String r1 = "M04"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05e8
            goto L_0x0114
        L_0x05e8:
            r1 = 51
            goto L_0x089f
        L_0x05ec:
            java.lang.String r1 = "JGZ"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x05f6
            goto L_0x0114
        L_0x05f6:
            r1 = 50
            goto L_0x089f
        L_0x05fa:
            java.lang.String r1 = "mh"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0604
            goto L_0x0114
        L_0x0604:
            r1 = 49
            goto L_0x089f
        L_0x0608:
            java.lang.String r1 = "b5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0612
            goto L_0x0114
        L_0x0612:
            r1 = 48
            goto L_0x089f
        L_0x0616:
            java.lang.String r1 = "V5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0620
            goto L_0x0114
        L_0x0620:
            r1 = 47
            goto L_0x089f
        L_0x0624:
            java.lang.String r1 = "V1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x062e
            goto L_0x0114
        L_0x062e:
            r1 = 46
            goto L_0x089f
        L_0x0632:
            java.lang.String r1 = "Q5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x063c
            goto L_0x0114
        L_0x063c:
            r1 = 45
            goto L_0x089f
        L_0x0640:
            java.lang.String r1 = "C1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x064a
            goto L_0x0114
        L_0x064a:
            r1 = 44
            goto L_0x089f
        L_0x064e:
            java.lang.String r1 = "woods_fn"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0658
            goto L_0x0114
        L_0x0658:
            r1 = 43
            goto L_0x089f
        L_0x065c:
            java.lang.String r1 = "ELUGA_A3_Pro"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0666
            goto L_0x0114
        L_0x0666:
            r1 = 42
            goto L_0x089f
        L_0x066a:
            java.lang.String r1 = "Z12_PRO"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0674
            goto L_0x0114
        L_0x0674:
            r1 = 41
            goto L_0x089f
        L_0x0678:
            java.lang.String r1 = "BLACK-1X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0682
            goto L_0x0114
        L_0x0682:
            r1 = 40
            goto L_0x089f
        L_0x0686:
            java.lang.String r1 = "taido_row"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0690
            goto L_0x0114
        L_0x0690:
            r1 = 39
            goto L_0x089f
        L_0x0694:
            java.lang.String r1 = "Pixi4-7_3G"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x069e
            goto L_0x0114
        L_0x069e:
            r1 = 38
            goto L_0x089f
        L_0x06a2:
            java.lang.String r1 = "GIONEE_GBL7360"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06ac
            goto L_0x0114
        L_0x06ac:
            r1 = 37
            goto L_0x089f
        L_0x06b0:
            java.lang.String r1 = "GiONEE_CBL7513"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06ba
            goto L_0x0114
        L_0x06ba:
            r1 = 36
            goto L_0x089f
        L_0x06be:
            java.lang.String r1 = "OnePlus5T"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06c8
            goto L_0x0114
        L_0x06c8:
            r1 = 35
            goto L_0x089f
        L_0x06cc:
            java.lang.String r1 = "whyred"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06d6
            goto L_0x0114
        L_0x06d6:
            r1 = 34
            goto L_0x089f
        L_0x06da:
            java.lang.String r1 = "watson"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06e4
            goto L_0x0114
        L_0x06e4:
            r1 = 33
            goto L_0x089f
        L_0x06e8:
            java.lang.String r1 = "SVP-DTV15"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x06f2
            goto L_0x0114
        L_0x06f2:
            r1 = 32
            goto L_0x089f
        L_0x06f6:
            java.lang.String r1 = "A7000-a"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0700
            goto L_0x0114
        L_0x0700:
            r1 = 31
            goto L_0x089f
        L_0x0704:
            java.lang.String r1 = "nicklaus_f"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x070e
            goto L_0x0114
        L_0x070e:
            r1 = 30
            goto L_0x089f
        L_0x0712:
            java.lang.String r1 = "tcl_eu"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x071c
            goto L_0x0114
        L_0x071c:
            r1 = 29
            goto L_0x089f
        L_0x0720:
            java.lang.String r1 = "ELUGA_Ray_X"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x072a
            goto L_0x0114
        L_0x072a:
            r1 = r10
            goto L_0x089f
        L_0x072d:
            java.lang.String r1 = "s905x018"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0737
            goto L_0x0114
        L_0x0737:
            r1 = r11
            goto L_0x089f
        L_0x073a:
            java.lang.String r1 = "A10-70L"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0744
            goto L_0x0114
        L_0x0744:
            r1 = r12
            goto L_0x089f
        L_0x0747:
            java.lang.String r1 = "A10-70F"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0751
            goto L_0x0114
        L_0x0751:
            r1 = 25
            goto L_0x089f
        L_0x0755:
            java.lang.String r1 = "namath"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x075f
            goto L_0x0114
        L_0x075f:
            r1 = 24
            goto L_0x089f
        L_0x0763:
            java.lang.String r1 = "Slate_Pro"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x076d
            goto L_0x0114
        L_0x076d:
            r1 = 23
            goto L_0x089f
        L_0x0771:
            java.lang.String r1 = "iris60"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x077b
            goto L_0x0114
        L_0x077b:
            r1 = 22
            goto L_0x089f
        L_0x077f:
            java.lang.String r1 = "BRAVIA_ATV2"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0789
            goto L_0x0114
        L_0x0789:
            r1 = 21
            goto L_0x089f
        L_0x078d:
            java.lang.String r1 = "GiONEE_GBL7319"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0797
            goto L_0x0114
        L_0x0797:
            r1 = 20
            goto L_0x089f
        L_0x079b:
            java.lang.String r1 = "panell_dt"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07a5
            goto L_0x0114
        L_0x07a5:
            r1 = 19
            goto L_0x089f
        L_0x07a9:
            java.lang.String r1 = "panell_ds"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07b3
            goto L_0x0114
        L_0x07b3:
            r1 = 18
            goto L_0x089f
        L_0x07b7:
            java.lang.String r1 = "panell_dl"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07c1
            goto L_0x0114
        L_0x07c1:
            r1 = 17
            goto L_0x089f
        L_0x07c5:
            java.lang.String r1 = "vernee_M5"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07cf
            goto L_0x0114
        L_0x07cf:
            r1 = 16
            goto L_0x089f
        L_0x07d3:
            java.lang.String r1 = "pacificrim"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07dd
            goto L_0x0114
        L_0x07dd:
            r1 = 15
            goto L_0x089f
        L_0x07e1:
            java.lang.String r1 = "Phantom6"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07eb
            goto L_0x0114
        L_0x07eb:
            r1 = 14
            goto L_0x089f
        L_0x07ef:
            java.lang.String r1 = "ComioS1"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x07f9
            goto L_0x0114
        L_0x07f9:
            r1 = 13
            goto L_0x089f
        L_0x07fd:
            java.lang.String r1 = "XT1663"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0807
            goto L_0x0114
        L_0x0807:
            r1 = 12
            goto L_0x089f
        L_0x080b:
            java.lang.String r1 = "RAIJIN"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0815
            goto L_0x0114
        L_0x0815:
            r1 = 11
            goto L_0x089f
        L_0x0819:
            java.lang.String r1 = "AquaPowerM"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0823
            goto L_0x0114
        L_0x0823:
            r1 = 10
            goto L_0x089f
        L_0x0827:
            java.lang.String r1 = "PGN611"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0831
            goto L_0x0114
        L_0x0831:
            r1 = 9
            goto L_0x089f
        L_0x0835:
            java.lang.String r1 = "PGN610"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x083f
            goto L_0x0114
        L_0x083f:
            r1 = r13
            goto L_0x089f
        L_0x0842:
            java.lang.String r2 = "PGN528"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x089f
            goto L_0x0114
        L_0x084c:
            java.lang.String r1 = "NX573J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0856
            goto L_0x0114
        L_0x0856:
            r1 = r2
            goto L_0x089f
        L_0x0858:
            java.lang.String r1 = "NX541J"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0862
            goto L_0x0114
        L_0x0862:
            r1 = r3
            goto L_0x089f
        L_0x0864:
            java.lang.String r1 = "CP8676_I02"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x086e
            goto L_0x0114
        L_0x086e:
            r1 = r4
            goto L_0x089f
        L_0x0870:
            java.lang.String r1 = "K50a40"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x087a
            goto L_0x0114
        L_0x087a:
            r1 = r5
            goto L_0x089f
        L_0x087c:
            java.lang.String r1 = "GIONEE_SWW1631"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0886
            goto L_0x0114
        L_0x0886:
            r1 = r6
            goto L_0x089f
        L_0x0888:
            java.lang.String r1 = "GIONEE_SWW1627"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0892
            goto L_0x0114
        L_0x0892:
            r1 = r9
            goto L_0x089f
        L_0x0894:
            java.lang.String r1 = "GIONEE_SWW1609"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x089e
            goto L_0x0114
        L_0x089e:
            r1 = r8
        L_0x089f:
            switch(r1) {
                case 0: goto L_0x08b0;
                case 1: goto L_0x08b0;
                case 2: goto L_0x08b0;
                case 3: goto L_0x08b0;
                case 4: goto L_0x08b0;
                case 5: goto L_0x08b0;
                case 6: goto L_0x08b0;
                case 7: goto L_0x08b0;
                case 8: goto L_0x08b0;
                case 9: goto L_0x08b0;
                case 10: goto L_0x08b0;
                case 11: goto L_0x08b0;
                case 12: goto L_0x08b0;
                case 13: goto L_0x08b0;
                case 14: goto L_0x08b0;
                case 15: goto L_0x08b0;
                case 16: goto L_0x08b0;
                case 17: goto L_0x08b0;
                case 18: goto L_0x08b0;
                case 19: goto L_0x08b0;
                case 20: goto L_0x08b0;
                case 21: goto L_0x08b0;
                case 22: goto L_0x08b0;
                case 23: goto L_0x08b0;
                case 24: goto L_0x08b0;
                case 25: goto L_0x08b0;
                case 26: goto L_0x08b0;
                case 27: goto L_0x08b0;
                case 28: goto L_0x08b0;
                case 29: goto L_0x08b0;
                case 30: goto L_0x08b0;
                case 31: goto L_0x08b0;
                case 32: goto L_0x08b0;
                case 33: goto L_0x08b0;
                case 34: goto L_0x08b0;
                case 35: goto L_0x08b0;
                case 36: goto L_0x08b0;
                case 37: goto L_0x08b0;
                case 38: goto L_0x08b0;
                case 39: goto L_0x08b0;
                case 40: goto L_0x08b0;
                case 41: goto L_0x08b0;
                case 42: goto L_0x08b0;
                case 43: goto L_0x08b0;
                case 44: goto L_0x08b0;
                case 45: goto L_0x08b0;
                case 46: goto L_0x08b0;
                case 47: goto L_0x08b0;
                case 48: goto L_0x08b0;
                case 49: goto L_0x08b0;
                case 50: goto L_0x08b0;
                case 51: goto L_0x08b0;
                case 52: goto L_0x08b0;
                case 53: goto L_0x08b0;
                case 54: goto L_0x08b0;
                case 55: goto L_0x08b0;
                case 56: goto L_0x08b0;
                case 57: goto L_0x08b0;
                case 58: goto L_0x08b0;
                case 59: goto L_0x08b0;
                case 60: goto L_0x08b0;
                case 61: goto L_0x08b0;
                case 62: goto L_0x08b0;
                case 63: goto L_0x08b0;
                case 64: goto L_0x08b0;
                case 65: goto L_0x08b0;
                case 66: goto L_0x08b0;
                case 67: goto L_0x08b0;
                case 68: goto L_0x08b0;
                case 69: goto L_0x08b0;
                case 70: goto L_0x08b0;
                case 71: goto L_0x08b0;
                case 72: goto L_0x08b0;
                case 73: goto L_0x08b0;
                case 74: goto L_0x08b0;
                case 75: goto L_0x08b0;
                case 76: goto L_0x08b0;
                case 77: goto L_0x08b0;
                case 78: goto L_0x08b0;
                case 79: goto L_0x08b0;
                case 80: goto L_0x08b0;
                case 81: goto L_0x08b0;
                case 82: goto L_0x08b0;
                case 83: goto L_0x08b0;
                case 84: goto L_0x08b0;
                case 85: goto L_0x08b0;
                case 86: goto L_0x08b0;
                case 87: goto L_0x08b0;
                case 88: goto L_0x08b0;
                case 89: goto L_0x08b0;
                case 90: goto L_0x08b0;
                case 91: goto L_0x08b0;
                case 92: goto L_0x08b0;
                case 93: goto L_0x08b0;
                case 94: goto L_0x08b0;
                case 95: goto L_0x08b0;
                case 96: goto L_0x08b0;
                case 97: goto L_0x08b0;
                case 98: goto L_0x08b0;
                case 99: goto L_0x08b0;
                case 100: goto L_0x08b0;
                case 101: goto L_0x08b0;
                case 102: goto L_0x08b0;
                case 103: goto L_0x08b0;
                case 104: goto L_0x08b0;
                case 105: goto L_0x08b0;
                case 106: goto L_0x08b0;
                case 107: goto L_0x08b0;
                case 108: goto L_0x08b0;
                case 109: goto L_0x08b0;
                case 110: goto L_0x08b0;
                case 111: goto L_0x08b0;
                case 112: goto L_0x08b0;
                case 113: goto L_0x08b0;
                case 114: goto L_0x08b0;
                case 115: goto L_0x08b0;
                case 116: goto L_0x08b0;
                case 117: goto L_0x08b0;
                case 118: goto L_0x08b0;
                case 119: goto L_0x08b0;
                case 120: goto L_0x08b0;
                case 121: goto L_0x08b0;
                case 122: goto L_0x08b0;
                case 123: goto L_0x08b0;
                case 124: goto L_0x08b0;
                case 125: goto L_0x08b0;
                case 126: goto L_0x08b0;
                case 127: goto L_0x08b0;
                case 128: goto L_0x08b0;
                case 129: goto L_0x08b0;
                case 130: goto L_0x08b0;
                case 131: goto L_0x08b0;
                case 132: goto L_0x08b0;
                case 133: goto L_0x08b0;
                case 134: goto L_0x08b0;
                case 135: goto L_0x08b0;
                case 136: goto L_0x08b0;
                case 137: goto L_0x08b0;
                case 138: goto L_0x08b0;
                case 139: goto L_0x08b0;
                default: goto L_0x08a2;
            }
        L_0x08a2:
            java.lang.String r0 = com.google.android.exoplayer2.util.Util.MODEL
            r0.hashCode()
            java.lang.String r1 = "JSN-L21"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x08b0
            goto L_0x08b1
        L_0x08b0:
            return r9
        L_0x08b1:
            return r8
        L_0x08b2:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.MediaCodecVideoRenderer.evaluateDeviceNeedsSetOutputSurfaceWorkaround():boolean");
    }

    private final class OnFrameRenderedListenerV23 implements MediaCodecAdapter.OnFrameRenderedListener, Handler.Callback {
        private static final int HANDLE_FRAME_RENDERED = 0;
        private final Handler handler;

        public OnFrameRenderedListenerV23(MediaCodecAdapter mediaCodecAdapter) {
            Handler createHandlerForCurrentLooper = Util.createHandlerForCurrentLooper(this);
            this.handler = createHandlerForCurrentLooper;
            mediaCodecAdapter.setOnFrameRenderedListener(this, createHandlerForCurrentLooper);
        }

        public void onFrameRendered(MediaCodecAdapter mediaCodecAdapter, long j, long j2) {
            if (Util.SDK_INT < 30) {
                this.handler.sendMessageAtFrontOfQueue(Message.obtain(this.handler, 0, (int) (j >> 32), (int) j));
                return;
            }
            handleFrameRendered(j);
        }

        public boolean handleMessage(Message message) {
            if (message.what != 0) {
                return false;
            }
            handleFrameRendered(Util.toLong(message.arg1, message.arg2));
            return true;
        }

        private void handleFrameRendered(long j) {
            if (this == MediaCodecVideoRenderer.this.tunnelingOnFrameRenderedListener && MediaCodecVideoRenderer.this.getCodec() != null) {
                if (j == Long.MAX_VALUE) {
                    MediaCodecVideoRenderer.this.onProcessedTunneledEndOfStream();
                    return;
                }
                try {
                    MediaCodecVideoRenderer.this.onProcessedTunneledBuffer(j);
                } catch (ExoPlaybackException e) {
                    MediaCodecVideoRenderer.this.setPendingPlaybackException(e);
                }
            }
        }
    }
}
