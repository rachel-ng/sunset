package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.android.exoplayer2.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda1 implements ListenerSet.Event {
    public final /* synthetic */ SimpleBasePlayer.State f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda1(SimpleBasePlayer.State state) {
        this.f$0 = state;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onIsPlayingChanged(SimpleBasePlayer.isPlaying(this.f$0));
    }
}
