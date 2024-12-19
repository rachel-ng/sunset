package com.google.android.gms.internal.consent_sdk;

import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzbt extends WebViewClient {
    final /* synthetic */ zzbu zza;

    /* synthetic */ zzbt(zzbu zzbu, zzbs zzbs) {
        this.zza = zzbu;
    }

    public final void onLoadResource(WebView webView, String str) {
        if (zzbu.zzf(this.zza, str)) {
            this.zza.zzb.zze(str);
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        if (!this.zza.zzc) {
            Log.d("UserMessagingPlatform", "Wall html loaded.");
            this.zza.zzc = true;
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.zza.zzb.zzf(i, str, str2);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        String uri = webResourceRequest.getUrl().toString();
        if (!zzbu.zzf(this.zza, uri)) {
            return false;
        }
        this.zza.zzb.zze(uri);
        return true;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (!zzbu.zzf(this.zza, str)) {
            return false;
        }
        this.zza.zzb.zze(str);
        return true;
    }
}
