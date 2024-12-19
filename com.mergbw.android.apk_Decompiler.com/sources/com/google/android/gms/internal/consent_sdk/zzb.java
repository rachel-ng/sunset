package com.google.android.gms.internal.consent_sdk;

import java.util.concurrent.Callable;
import org.json.JSONObject;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final /* synthetic */ class zzb implements Callable {
    public final /* synthetic */ zzd zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ JSONObject zzc;

    public /* synthetic */ zzb(zzd zzd, String str, JSONObject jSONObject) {
        this.zza = zzd;
        this.zzb = str;
        this.zzc = jSONObject;
    }

    public final Object call() {
        return Boolean.valueOf(this.zza.zzb(this.zzb, this.zzc));
    }
}
