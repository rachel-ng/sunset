package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda48 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ PlaybackParameters f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda48(SimpleBasePlayer.State state, PlaybackParameters playbackParameters) {
        this.f$0 = state;
        this.f$1 = playbackParameters;
    }

    public final Object get() {
        return this.f$0.buildUpon().setPlaybackParameters(this.f$1).build();
    }
}
