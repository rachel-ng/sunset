package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda25 implements ListenerSet.Event {
    public final /* synthetic */ Tracks f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda25(Tracks tracks) {
        this.f$0 = tracks;
    }

    public final void invoke(Object obj) {
        ((Player.Listener) obj).onTracksChanged(this.f$0);
    }
}
