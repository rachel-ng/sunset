package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExoPlayerImpl$$ExternalSyntheticLambda7 implements ListenerSet.Event {
    public final /* synthetic */ MediaMetadata f$0;

    public /* synthetic */ ExoPlayerImpl$$ExternalSyntheticLambda7(MediaMetadata mediaMetadata) {
        this.f$0 = mediaMetadata;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onMediaMetadataChanged(this.f$0);
    }
}
