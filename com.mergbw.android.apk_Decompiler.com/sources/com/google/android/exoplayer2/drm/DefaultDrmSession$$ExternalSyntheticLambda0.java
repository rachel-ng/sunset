package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSessionEventListener;
import com.google.android.exoplayer2.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultDrmSession$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int f$0;

    public /* synthetic */ DefaultDrmSession$$ExternalSyntheticLambda0(int i) {
        this.f$0 = i;
    }

    public final void accept(Object obj) {
        ((DrmSessionEventListener.EventDispatcher) obj).drmSessionAcquired(this.f$0);
    }
}
