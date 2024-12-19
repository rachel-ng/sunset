package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfra implements Continuation {
    public final Object then(Task task) {
        return Boolean.valueOf(task.isSuccessful());
    }
}
