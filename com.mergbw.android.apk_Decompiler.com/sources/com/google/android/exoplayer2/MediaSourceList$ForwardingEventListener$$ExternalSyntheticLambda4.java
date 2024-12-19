package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.source.MediaLoadData;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ MediaLoadData f$2;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda4(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, MediaLoadData mediaLoadData) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
        this.f$2 = mediaLoadData;
    }

    public final void run() {
        this.f$0.m421lambda$onDownstreamFormatChanged$5$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(this.f$1, this.f$2);
    }
}
