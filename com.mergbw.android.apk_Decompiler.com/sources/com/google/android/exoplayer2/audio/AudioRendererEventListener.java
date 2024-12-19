package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.decoder.DecoderReuseEvaluation;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;

public interface AudioRendererEventListener {

    /* renamed from: com.google.android.exoplayer2.audio.AudioRendererEventListener$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onAudioCodecError(AudioRendererEventListener _this, Exception exc) {
        }

        public static void $default$onAudioDecoderInitialized(AudioRendererEventListener _this, String str, long j, long j2) {
        }

        public static void $default$onAudioDecoderReleased(AudioRendererEventListener _this, String str) {
        }

        public static void $default$onAudioDisabled(AudioRendererEventListener _this, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioEnabled(AudioRendererEventListener _this, DecoderCounters decoderCounters) {
        }

        @Deprecated
        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener _this, Format format) {
        }

        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener _this, Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
        }

        public static void $default$onAudioPositionAdvancing(AudioRendererEventListener _this, long j) {
        }

        public static void $default$onAudioSinkError(AudioRendererEventListener _this, Exception exc) {
        }

        public static void $default$onAudioUnderrun(AudioRendererEventListener _this, int i, long j, long j2) {
        }

        public static void $default$onSkipSilenceEnabledChanged(AudioRendererEventListener _this, boolean z) {
        }
    }

    void onAudioCodecError(Exception exc);

    void onAudioDecoderInitialized(String str, long j, long j2);

    void onAudioDecoderReleased(String str);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    @Deprecated
    void onAudioInputFormatChanged(Format format);

    void onAudioInputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation);

    void onAudioPositionAdvancing(long j);

    void onAudioSinkError(Exception exc);

    void onAudioUnderrun(int i, long j, long j2);

    void onSkipSilenceEnabledChanged(boolean z);

    public static final class EventDispatcher {
        private final Handler handler;
        private final AudioRendererEventListener listener;

        public EventDispatcher(Handler handler2, AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = audioRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda6(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$enabled$0$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m448lambda$enabled$0$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioEnabled(decoderCounters);
        }

        public void decoderInitialized(String str, long j, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda3(this, str, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderInitialized$1$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m445lambda$decoderInitialized$1$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(String str, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderInitialized(str, j, j2);
        }

        public void inputFormatChanged(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda8(this, format, decoderReuseEvaluation));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$inputFormatChanged$2$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m449lambda$inputFormatChanged$2$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(Format format, DecoderReuseEvaluation decoderReuseEvaluation) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format);
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioInputFormatChanged(format, decoderReuseEvaluation);
        }

        public void positionAdvancing(long j) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda5(this, j));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$positionAdvancing$3$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m450lambda$positionAdvancing$3$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(long j) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioPositionAdvancing(j);
        }

        public void underrun(int i, long j, long j2) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda1(this, i, j, j2));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$underrun$4$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m452lambda$underrun$4$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(int i, long j, long j2) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioUnderrun(i, j, j2);
        }

        public void decoderReleased(String str) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda0(this, str));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$decoderReleased$5$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m446lambda$decoderReleased$5$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(String str) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDecoderReleased(str);
        }

        public void disabled(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda7(this, decoderCounters));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$disabled$6$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m447lambda$disabled$6$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioDisabled(decoderCounters);
        }

        public void skipSilenceEnabledChanged(boolean z) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda2(this, z));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$skipSilenceEnabledChanged$7$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m451lambda$skipSilenceEnabledChanged$7$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(boolean z) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onSkipSilenceEnabledChanged(z);
        }

        public void audioSinkError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda9(this, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioSinkError$8$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m444lambda$audioSinkError$8$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioSinkError(exc);
        }

        public void audioCodecError(Exception exc) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                handler2.post(new AudioRendererEventListener$EventDispatcher$$ExternalSyntheticLambda4(this, exc));
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: lambda$audioCodecError$9$com-google-android-exoplayer2-audio-AudioRendererEventListener$EventDispatcher  reason: not valid java name */
        public /* synthetic */ void m443lambda$audioCodecError$9$comgoogleandroidexoplayer2audioAudioRendererEventListener$EventDispatcher(Exception exc) {
            ((AudioRendererEventListener) Util.castNonNull(this.listener)).onAudioCodecError(exc);
        }
    }
}
