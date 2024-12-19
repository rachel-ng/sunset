package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import com.google.android.exoplayer2.util.ConditionVariable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultAudioSink$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ AudioTrack f$0;
    public final /* synthetic */ ConditionVariable f$1;

    public /* synthetic */ DefaultAudioSink$$ExternalSyntheticLambda5(AudioTrack audioTrack, ConditionVariable conditionVariable) {
        this.f$0 = audioTrack;
        this.f$1 = conditionVariable;
    }

    public final void run() {
        DefaultAudioSink.lambda$releaseAudioTrackAsync$0(this.f$0, this.f$1);
    }
}
