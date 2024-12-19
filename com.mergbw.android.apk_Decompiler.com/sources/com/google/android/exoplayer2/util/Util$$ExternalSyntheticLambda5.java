package com.google.android.exoplayer2.util;

import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Util$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ SettableFuture f$0;
    public final /* synthetic */ Runnable f$1;
    public final /* synthetic */ Object f$2;

    public /* synthetic */ Util$$ExternalSyntheticLambda5(SettableFuture settableFuture, Runnable runnable, Object obj) {
        this.f$0 = settableFuture;
        this.f$1 = runnable;
        this.f$2 = obj;
    }

    public final void run() {
        Util.lambda$postOrRunWithCompletion$0(this.f$0, this.f$1, this.f$2);
    }
}
