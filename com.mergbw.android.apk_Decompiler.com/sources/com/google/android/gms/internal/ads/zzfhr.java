package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zzba;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfhr {
    private final JSONObject zza;

    public zzfhr(JSONObject jSONObject) {
        this.zza = jSONObject;
    }

    public final String zza() {
        if (zzc() - 1 != 1) {
            return "javascript";
        }
        return null;
    }

    public final boolean zzb() {
        zzbeg zzbeg = zzbep.zzfd;
        return this.zza.optBoolean((String) zzba.zzc().zza(zzbeg), true);
    }

    public final int zzc() {
        int optInt = this.zza.optInt("media_type", -1);
        if (optInt != 0) {
            return optInt != 1 ? 3 : 1;
        }
        return 2;
    }
}
