package com.google.android.gms.internal.consent_sdk;

import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zze {
    private final Executor zza;

    zze(Executor executor) {
        this.zza = executor;
    }

    public final Executor zza() {
        return this.zza;
    }

    public final void zzb(String str, String str2, zzd... zzdArr) {
        this.zza.execute(new zzc(str, str2, zzdArr));
    }
}
