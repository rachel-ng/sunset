package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zzbw;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfbn implements zzexv {
    private final String zza;

    public zzfbn(String str) {
        this.zza = str;
    }

    public final /* bridge */ /* synthetic */ void zzj(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        try {
            if (!TextUtils.isEmpty(this.zza)) {
                zzbw.zzg(jSONObject, "pii").put("adsid", this.zza);
            }
        } catch (JSONException e) {
            zzm.zzk("Failed putting trustless token.", e);
        }
    }
}
