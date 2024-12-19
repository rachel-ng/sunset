package com.google.android.exoplayer2.util;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Util$$ExternalSyntheticLambda7 implements Runnable {
    public final /* synthetic */ ListenableFuture f$0;
    public final /* synthetic */ SettableFuture f$1;
    public final /* synthetic */ AsyncFunction f$2;

    public /* synthetic */ Util$$ExternalSyntheticLambda7(ListenableFuture listenableFuture, SettableFuture settableFuture, AsyncFunction asyncFunction) {
        this.f$0 = listenableFuture;
        this.f$1 = settableFuture;
        this.f$2 = asyncFunction;
    }

    public final void run() {
        Util.lambda$transformFutureAsync$2(this.f$0, this.f$1, this.f$2);
    }
}
