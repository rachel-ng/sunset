package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda35 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda35(SimpleBasePlayer.State state) {
        this.f$0 = state;
    }

    public final Object get() {
        return this.f$0.buildUpon().setPlaybackState(1).setTotalBufferedDurationMs(SimpleBasePlayer.PositionSupplier.ZERO).setContentBufferedPositionMs(SimpleBasePlayer.PositionSupplier.CC.getConstant(SimpleBasePlayer.getContentPositionMsInternal(this.f$0))).setAdBufferedPositionMs(this.f$0.adPositionMsSupplier).setIsLoading(false).build();
    }
}
