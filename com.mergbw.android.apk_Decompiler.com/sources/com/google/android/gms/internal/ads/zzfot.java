package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.webkit.WebView;
import androidx.webkit.JavaScriptReplyProxy;
import androidx.webkit.WebMessageCompat;
import androidx.webkit.WebViewCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfot implements WebViewCompat.WebMessageListener {
    final /* synthetic */ zzfou zza;

    zzfot(zzfou zzfou) {
        this.zza = zzfou;
    }

    public final void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy) {
        try {
            JSONObject jSONObject = new JSONObject(webMessageCompat.getData());
            String string = jSONObject.getString(FirebaseAnalytics.Param.METHOD);
            String string2 = jSONObject.getJSONObject("data").getString("adSessionId");
            if (string.equals("startSession")) {
                zzfou.zzd(this.zza, string2);
            } else if (!string.equals("finishSession")) {
                zzfog.zza.booleanValue();
            } else {
                zzfou.zzb(this.zza, string2);
            }
        } catch (JSONException e) {
            zzfpz.zza("Error parsing JS message in JavaScriptSessionService.", e);
        }
    }
}
