package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda40 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ long f$2;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda40(SimpleBasePlayer.State state, int i, long j) {
        this.f$0 = state;
        this.f$1 = i;
        this.f$2 = j;
    }

    public final Object get() {
        return SimpleBasePlayer.getStateWithNewPlaylistAndPosition(this.f$0, this.f$0.playlist, this.f$1, this.f$2);
    }
}
