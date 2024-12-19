package com.google.android.exoplayer2;

import android.view.SurfaceView;
import com.google.android.exoplayer2.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda20 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ SurfaceView f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda20(SimpleBasePlayer.State state, SurfaceView surfaceView) {
        this.f$0 = state;
        this.f$1 = surfaceView;
    }

    public final Object get() {
        return this.f$0.buildUpon().setSurfaceSize(SimpleBasePlayer.getSurfaceHolderSize(this.f$1.getHolder())).build();
    }
}
