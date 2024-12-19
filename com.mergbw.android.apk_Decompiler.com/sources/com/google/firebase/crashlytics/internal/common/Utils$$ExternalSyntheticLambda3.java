package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Utils$$ExternalSyntheticLambda3 implements Continuation {
    public final /* synthetic */ TaskCompletionSource f$0;

    public /* synthetic */ Utils$$ExternalSyntheticLambda3(TaskCompletionSource taskCompletionSource) {
        this.f$0 = taskCompletionSource;
    }

    public final Object then(Task task) {
        return Utils.lambda$callTask$2(this.f$0, task);
    }
}
