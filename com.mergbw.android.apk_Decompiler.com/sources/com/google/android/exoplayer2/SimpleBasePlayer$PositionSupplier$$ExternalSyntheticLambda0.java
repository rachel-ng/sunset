package com.google.android.exoplayer2;

import com.google.android.exoplayer2.SimpleBasePlayer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda0 implements SimpleBasePlayer.PositionSupplier {
    public final /* synthetic */ long f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ float f$2;

    public /* synthetic */ SimpleBasePlayer$PositionSupplier$$ExternalSyntheticLambda0(long j, long j2, float f) {
        this.f$0 = j;
        this.f$1 = j2;
        this.f$2 = f;
    }

    public final long get() {
        return SimpleBasePlayer.PositionSupplier.CC.lambda$getExtrapolating$1(this.f$0, this.f$1, this.f$2);
    }
}
