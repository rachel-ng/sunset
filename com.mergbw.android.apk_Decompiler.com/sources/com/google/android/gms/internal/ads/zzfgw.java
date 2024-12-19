package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonToken;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.zzbw;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfgw {
    public final List zza;
    public final String zzb;
    public final int zzc;
    public final String zzd;
    public final int zze;
    public final long zzf;
    public final boolean zzg;
    public final String zzh;
    public final zzfgv zzi;
    public final Bundle zzj;
    public final String zzk;
    public final String zzl;
    public final String zzm;
    public final JSONObject zzn;
    public final JSONObject zzo;
    public final String zzp;
    public final int zzq;

    zzfgw(JsonReader jsonReader) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        List emptyList = Collections.emptyList();
        Bundle bundle = new Bundle();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jsonReader.beginObject();
        String str = "";
        String str2 = str;
        String str3 = str2;
        String str4 = str3;
        String str5 = str4;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        zzfgv zzfgv = null;
        long j = 0;
        int i3 = 1;
        String str6 = str5;
        String str7 = str6;
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            String str8 = str5;
            if ("nofill_urls".equals(nextName)) {
                emptyList = zzbw.zzd(jsonReader);
            } else if ("refresh_interval".equals(nextName)) {
                i2 = jsonReader.nextInt();
            } else if ("gws_query_id".equals(nextName)) {
                str = jsonReader.nextString();
            } else if ("analytics_query_ad_event_id".equals(nextName)) {
                str6 = jsonReader.nextString();
            } else if ("is_idless".equals(nextName)) {
                z = jsonReader.nextBoolean();
            } else if ("response_code".equals(nextName)) {
                i = jsonReader.nextInt();
            } else if ("latency".equals(nextName)) {
                j = jsonReader.nextLong();
            } else {
                JSONObject jSONObject3 = jSONObject2;
                if (!((Boolean) zzba.zzc().zza(zzbep.zzir)).booleanValue() || !"public_error".equals(nextName) || jsonReader.peek() != JsonToken.BEGIN_OBJECT) {
                    JsonReader jsonReader2 = jsonReader;
                    if ("bidding_data".equals(nextName)) {
                        str7 = jsonReader.nextString();
                    } else {
                        if (((Boolean) zzba.zzc().zza(zzbep.zzkt)).booleanValue() && Objects.equals(nextName, "topics_should_record_observation")) {
                            jsonReader.nextBoolean();
                        } else if ("adapter_response_replacement_key".equals(nextName)) {
                            str5 = jsonReader.nextString();
                            jSONObject2 = jSONObject3;
                        } else if ("response_info_extras".equals(nextName)) {
                            if (((Boolean) zzba.zzc().zza(zzbep.zzgZ)).booleanValue()) {
                                try {
                                    Bundle zza2 = zzbw.zza(zzbw.zzi(jsonReader));
                                    if (zza2 != null) {
                                        bundle = zza2;
                                    }
                                } catch (IOException | JSONException unused) {
                                } catch (IllegalStateException unused2) {
                                    jsonReader.skipValue();
                                }
                            } else {
                                jsonReader.skipValue();
                            }
                        } else if ("adRequestPostBody".equals(nextName)) {
                            if (((Boolean) zzba.zzc().zza(zzbep.zzjn)).booleanValue()) {
                                str3 = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        } else if ("adRequestUrl".equals(nextName)) {
                            if (((Boolean) zzba.zzc().zza(zzbep.zzjn)).booleanValue()) {
                                str2 = jsonReader.nextString();
                            } else {
                                jsonReader.skipValue();
                            }
                        } else {
                            if (!((Boolean) zzba.zzc().zza(zzbep.zzjo)).booleanValue() || !Objects.equals(nextName, "adResponseBody")) {
                                if (((Boolean) zzba.zzc().zza(zzbep.zzjo)).booleanValue() && Objects.equals(nextName, "adResponseHeaders")) {
                                    jSONObject = zzbw.zzi(jsonReader);
                                } else if (Objects.equals(nextName, "max_parallel_renderers")) {
                                    i3 = Math.max(1, jsonReader.nextInt());
                                } else {
                                    if (!((Boolean) zzba.zzc().zza(zzbep.zzjv)).booleanValue() || !Objects.equals(nextName, "inspector_ad_transaction_extras")) {
                                        jsonReader.skipValue();
                                    } else {
                                        jSONObject2 = zzbw.zzi(jsonReader);
                                    }
                                }
                            } else {
                                str4 = jsonReader.nextString();
                            }
                        }
                    }
                } else {
                    zzfgv = new zzfgv(jsonReader);
                }
                str5 = str8;
                jSONObject2 = jSONObject3;
            }
            str5 = str8;
        }
        String str9 = str5;
        jsonReader.endObject();
        this.zza = emptyList;
        this.zzc = i2;
        this.zzb = str;
        this.zzd = str6;
        this.zze = i;
        this.zzf = j;
        this.zzi = zzfgv;
        this.zzg = z;
        this.zzh = str7;
        this.zzj = bundle;
        this.zzk = str2;
        this.zzl = str3;
        this.zzm = str4;
        this.zzn = jSONObject;
        this.zzo = jSONObject2;
        this.zzp = str5;
        this.zzq = ((Long) zzbgo.zza.zze()).longValue() > 0 ? ((Long) zzbgo.zza.zze()).intValue() : i3;
    }
}
