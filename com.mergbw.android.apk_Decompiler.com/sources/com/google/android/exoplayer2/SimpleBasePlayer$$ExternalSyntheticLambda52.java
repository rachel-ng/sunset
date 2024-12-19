package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.android.exoplayer2.util.Size;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda52 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ Size f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda52(SimpleBasePlayer.State state, Size size) {
        this.f$0 = state;
        this.f$1 = size;
    }

    public final Object get() {
        return this.f$0.buildUpon().setSurfaceSize(this.f$1).build();
    }
}
