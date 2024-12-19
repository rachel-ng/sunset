package com.google.android.exoplayer2;

import java.util.concurrent.Executor;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda39 implements Executor {
    public final /* synthetic */ SimpleBasePlayer f$0;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda39(SimpleBasePlayer simpleBasePlayer) {
        this.f$0 = simpleBasePlayer;
    }

    public final void execute(Runnable runnable) {
        this.f$0.postOrRunOnApplicationHandler(runnable);
    }
}
