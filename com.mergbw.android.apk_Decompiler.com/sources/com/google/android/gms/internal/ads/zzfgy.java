package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import com.google.android.gms.ads.internal.util.zzbw;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfgy {
    public final String zza;
    public final String zzb;
    public final JSONObject zzc;
    public final JSONObject zzd;

    zzfgy(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        JSONObject zzi = zzbw.zzi(jsonReader);
        this.zzd = zzi;
        this.zza = zzi.optString("ad_html", (String) null);
        this.zzb = zzi.optString("ad_base_url", (String) null);
        this.zzc = zzi.optJSONObject("ad_json");
    }
}
