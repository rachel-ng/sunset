package com.google.firebase.remoteconfig.internal;

import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigCacheClient$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ ConfigStorageClient f$0;

    public /* synthetic */ ConfigCacheClient$$ExternalSyntheticLambda2(ConfigStorageClient configStorageClient) {
        this.f$0 = configStorageClient;
    }

    public final Object call() {
        return this.f$0.read();
    }
}