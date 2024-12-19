package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ConfigFetchHandler$$ExternalSyntheticLambda0 implements Continuation {
    public final /* synthetic */ ConfigFetchHandler f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ Map f$2;

    public /* synthetic */ ConfigFetchHandler$$ExternalSyntheticLambda0(ConfigFetchHandler configFetchHandler, long j, Map map) {
        this.f$0 = configFetchHandler;
        this.f$1 = j;
        this.f$2 = map;
    }

    public final Object then(Task task) {
        return this.f$0.m672lambda$fetch$0$comgooglefirebaseremoteconfiginternalConfigFetchHandler(this.f$1, this.f$2, task);
    }
}
