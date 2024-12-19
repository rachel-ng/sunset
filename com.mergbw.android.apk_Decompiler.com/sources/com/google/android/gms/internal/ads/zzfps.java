package com.google.android.gms.internal.ads;

import android.os.Build;
import android.webkit.WebView;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.Date;
import java.util.Iterator;
import org.apache.commons.math3.geometry.VectorFormat;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzfps {
    private final String zza;
    private zzfqw zzb = new zzfqw((WebView) null);
    private long zzc;
    private int zzd;

    public zzfps(String str) {
        zzb();
        this.zza = str;
    }

    public final WebView zza() {
        return (WebView) this.zzb.get();
    }

    public final void zzb() {
        this.zzc = System.nanoTime();
        this.zzd = 1;
    }

    public void zzc() {
        this.zzb.clear();
    }

    public final void zzd(String str, long j) {
        if (j >= this.zzc && this.zzd != 3) {
            this.zzd = 3;
            zzfpl.zza().zzg(zza(), this.zza, str);
        }
    }

    public final void zze() {
        zzfpl.zza().zzc(zza(), this.zza);
    }

    public final void zzf(zzfok zzfok) {
        zzfpl.zza().zzd(zza(), this.zza, zzfok.zzb());
    }

    public final void zzg(Date date) {
        if (date != null) {
            JSONObject jSONObject = new JSONObject();
            zzfpy.zze(jSONObject, "timestamp", Long.valueOf(date.getTime()));
            zzfpl.zza().zzf(zza(), jSONObject);
        }
    }

    public final void zzh(String str, long j) {
        if (j >= this.zzc) {
            this.zzd = 2;
            zzfpl.zza().zzg(zza(), this.zza, str);
        }
    }

    public void zzi(zzfon zzfon, zzfol zzfol) {
        zzj(zzfon, zzfol, (JSONObject) null);
    }

    /* access modifiers changed from: protected */
    public final void zzj(zzfon zzfon, zzfol zzfol, JSONObject jSONObject) {
        String zzh = zzfon.zzh();
        JSONObject jSONObject2 = new JSONObject();
        zzfpy.zze(jSONObject2, "environment", "app");
        zzfpy.zze(jSONObject2, "adSessionType", zzfol.zzd());
        JSONObject jSONObject3 = new JSONObject();
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        zzfpy.zze(jSONObject3, "deviceType", str + VectorFormat.DEFAULT_SEPARATOR + str2);
        zzfpy.zze(jSONObject3, "osVersion", Integer.toString(Build.VERSION.SDK_INT));
        zzfpy.zze(jSONObject3, "os", "Android");
        zzfpy.zze(jSONObject2, "deviceInfo", jSONObject3);
        zzfpy.zze(jSONObject2, "deviceCategory", zzfpx.zza().toString());
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("clid");
        jSONArray.put("vlid");
        zzfpy.zze(jSONObject2, "supports", jSONArray);
        JSONObject jSONObject4 = new JSONObject();
        zzfpy.zze(jSONObject4, "partnerName", zzfol.zze().zzb());
        zzfpy.zze(jSONObject4, "partnerVersion", zzfol.zze().zzc());
        zzfpy.zze(jSONObject2, "omidNativeInfo", jSONObject4);
        JSONObject jSONObject5 = new JSONObject();
        zzfpy.zze(jSONObject5, "libraryVersion", "1.4.10-google_20240110");
        zzfpy.zze(jSONObject5, RemoteConfigConstants.RequestFieldKey.APP_ID, zzfpj.zzb().zza().getApplicationContext().getPackageName());
        zzfpy.zze(jSONObject2, "app", jSONObject5);
        if (zzfol.zzf() != null) {
            zzfpy.zze(jSONObject2, "contentUrl", zzfol.zzf());
        }
        if (zzfol.zzg() != null) {
            zzfpy.zze(jSONObject2, "customReferenceData", zzfol.zzg());
        }
        JSONObject jSONObject6 = new JSONObject();
        Iterator it = zzfol.zzh().iterator();
        if (!it.hasNext()) {
            zzfpl.zza().zzi(zza(), zzh, jSONObject2, jSONObject6, jSONObject);
        } else {
            zzfox zzfox = (zzfox) it.next();
            throw null;
        }
    }

    public final void zzk(boolean z) {
        String str;
        if (this.zzb.get() != null) {
            if (true != z) {
                str = "backgrounded";
            } else {
                str = "foregrounded";
            }
            zzfpl.zza().zzh(zza(), this.zza, str);
        }
    }

    public final void zzl(float f) {
        zzfpl.zza().zze(zza(), this.zza, f);
    }

    /* access modifiers changed from: package-private */
    public final void zzm(WebView webView) {
        this.zzb = new zzfqw(webView);
    }

    public void zzn() {
    }
}
