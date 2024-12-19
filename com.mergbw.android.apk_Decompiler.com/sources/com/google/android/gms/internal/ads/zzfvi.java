package com.google.android.gms.internal.ads;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final /* synthetic */ class zzfvi implements OnCompleteListener {
    public final /* synthetic */ zzfvj zza;

    public /* synthetic */ zzfvi(zzfvj zzfvj) {
        this.zza = zzfvj;
    }

    public final void onComplete(Task task) {
        zzfvj zzfvj = this.zza;
        if (task.isCanceled()) {
            zzfvj.cancel(false);
        } else if (task.isSuccessful()) {
            zzfvj.zzc(task.getResult());
        } else {
            Exception exception = task.getException();
            if (exception != null) {
                zzfvj.zzd(exception);
                return;
            }
            throw new IllegalStateException();
        }
    }
}
