package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzbqy {
    public final String zza;
    public final String zzb;
    public final List zzc;
    public final String zzd;
    public final String zze;
    public final List zzf;
    public final List zzg;
    public final List zzh;
    public final List zzi;
    public final List zzj;
    public final String zzk;
    public final List zzl;
    public final List zzm;
    public final List zzn;
    public final String zzo;
    public final String zzp;
    public final String zzq;
    public final String zzr;
    public final String zzs;
    public final List zzt;
    public final String zzu;
    public final String zzv;

    public zzbqy(JSONObject jSONObject) throws JSONException {
        List list;
        this.zzb = jSONObject.optString(TtmlNode.ATTR_ID);
        JSONArray jSONArray = jSONObject.getJSONArray("adapters");
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        this.zzc = Collections.unmodifiableList(arrayList);
        this.zzd = jSONObject.optString("allocation_id", (String) null);
        zzu.zzg();
        this.zzf = zzbra.zza(jSONObject, "clickurl");
        zzu.zzg();
        this.zzg = zzbra.zza(jSONObject, "imp_urls");
        zzu.zzg();
        this.zzh = zzbra.zza(jSONObject, "downloaded_imp_urls");
        zzu.zzg();
        this.zzj = zzbra.zza(jSONObject, "fill_urls");
        zzu.zzg();
        this.zzl = zzbra.zza(jSONObject, "video_start_urls");
        zzu.zzg();
        this.zzn = zzbra.zza(jSONObject, "video_complete_urls");
        zzu.zzg();
        this.zzm = zzbra.zza(jSONObject, "video_reward_urls");
        this.zzo = jSONObject.optString(FirebaseAnalytics.Param.TRANSACTION_ID);
        this.zzp = jSONObject.optString("valid_from_timestamp");
        JSONObject optJSONObject = jSONObject.optJSONObject("ad");
        if (optJSONObject != null) {
            zzu.zzg();
            list = zzbra.zza(optJSONObject, "manual_impression_urls");
        } else {
            list = null;
        }
        this.zzi = list;
        this.zza = optJSONObject != null ? optJSONObject.toString() : null;
        JSONObject optJSONObject2 = jSONObject.optJSONObject("data");
        this.zzk = optJSONObject2 != null ? optJSONObject2.toString() : null;
        this.zze = optJSONObject2 != null ? optJSONObject2.optString("class_name") : null;
        this.zzq = jSONObject.optString("html_template", (String) null);
        this.zzr = jSONObject.optString("ad_base_url", (String) null);
        JSONObject optJSONObject3 = jSONObject.optJSONObject("assets");
        this.zzs = optJSONObject3 != null ? optJSONObject3.toString() : null;
        zzu.zzg();
        this.zzt = zzbra.zza(jSONObject, "template_ids");
        JSONObject optJSONObject4 = jSONObject.optJSONObject("ad_loader_options");
        this.zzu = optJSONObject4 != null ? optJSONObject4.toString() : null;
        this.zzv = jSONObject.optString("response_type", (String) null);
        jSONObject.optLong("ad_network_timeout_millis", -1);
    }
}
