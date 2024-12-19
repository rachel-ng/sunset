package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda32 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ float f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda32(SimpleBasePlayer.State state, float f) {
        this.f$0 = state;
        this.f$1 = f;
    }

    public final Object get() {
        return this.f$0.buildUpon().setVolume(this.f$1).build();
    }
}
