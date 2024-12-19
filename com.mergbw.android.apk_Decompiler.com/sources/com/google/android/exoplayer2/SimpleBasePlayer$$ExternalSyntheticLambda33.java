package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.android.exoplayer2.util.Size;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda33 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda33(SimpleBasePlayer.State state) {
        this.f$0 = state;
    }

    public final Object get() {
        return this.f$0.buildUpon().setSurfaceSize(Size.UNKNOWN).build();
    }
}
