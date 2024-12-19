package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfwy implements OnCompleteListener {
    public final /* synthetic */ zzfxg zza;
    public final /* synthetic */ TaskCompletionSource zzb;

    public /* synthetic */ zzfwy(zzfxg zzfxg, TaskCompletionSource taskCompletionSource) {
        this.zza = zzfxg;
        this.zzb = taskCompletionSource;
    }

    public final void onComplete(Task task) {
        this.zza.zzt(this.zzb, task);
    }
}
