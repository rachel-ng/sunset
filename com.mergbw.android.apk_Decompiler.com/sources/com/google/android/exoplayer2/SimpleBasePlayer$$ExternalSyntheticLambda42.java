package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda42 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda42(SimpleBasePlayer.State state, int i) {
        this.f$0 = state;
        this.f$1 = i;
    }

    public final Object get() {
        return this.f$0.buildUpon().setDeviceVolume(this.f$1).build();
    }
}
