package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.MediaSourceList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda2(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
    }

    public final void run() {
        this.f$0.m422lambda$onDrmKeysLoaded$7$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(this.f$1);
    }
}