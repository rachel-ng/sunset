package com.google.android.gms.internal.consent_sdk;

import java.util.concurrent.ThreadFactory;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzcp implements ThreadFactory {
    public final /* synthetic */ zzcq zza;
    public final /* synthetic */ String zzb = "Google consent worker";

    public /* synthetic */ zzcp(zzcq zzcq, String str) {
        this.zza = zzcq;
    }

    public final Thread newThread(Runnable runnable) {
        return this.zza.zza(this.zzb, runnable);
    }
}
