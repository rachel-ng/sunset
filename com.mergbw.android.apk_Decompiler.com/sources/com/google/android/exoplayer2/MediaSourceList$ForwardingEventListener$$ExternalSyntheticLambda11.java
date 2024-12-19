package com.google.android.exoplayer2;

import android.util.Pair;
import com.google.android.exoplayer2.MediaSourceList;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import java.io.IOException;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda11 implements Runnable {
    public final /* synthetic */ MediaSourceList.ForwardingEventListener f$0;
    public final /* synthetic */ Pair f$1;
    public final /* synthetic */ LoadEventInfo f$2;
    public final /* synthetic */ MediaLoadData f$3;
    public final /* synthetic */ IOException f$4;
    public final /* synthetic */ boolean f$5;

    public /* synthetic */ MediaSourceList$ForwardingEventListener$$ExternalSyntheticLambda11(MediaSourceList.ForwardingEventListener forwardingEventListener, Pair pair, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        this.f$0 = forwardingEventListener;
        this.f$1 = pair;
        this.f$2 = loadEventInfo;
        this.f$3 = mediaLoadData;
        this.f$4 = iOException;
        this.f$5 = z;
    }

    public final void run() {
        this.f$0.m430lambda$onLoadError$3$comgoogleandroidexoplayer2MediaSourceList$ForwardingEventListener(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
