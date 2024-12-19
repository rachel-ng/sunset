package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfvk {
    public static ListenableFuture zza(Task task, CancellationTokenSource cancellationTokenSource) {
        zzfvj zzfvj = new zzfvj(task, (Runnable) null);
        task.addOnCompleteListener(zzggk.zzb(), new zzfvi(zzfvj));
        return zzfvj;
    }
}
