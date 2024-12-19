package com.google.android.gms.internal.consent_sdk;

import android.os.Handler;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzby implements Executor {
    public final /* synthetic */ Handler zza;

    public /* synthetic */ zzby(Handler handler) {
        this.zza = handler;
    }

    public final void execute(Runnable runnable) {
        this.zza.post(runnable);
    }
}
