package androidx.webkit.internal;

import android.content.pm.PackageInfo;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;

public class ApiHelperForO {
    private ApiHelperForO() {
    }

    public static void setSafeBrowsingEnabled(WebSettings webSettings, boolean z) {
        webSettings.setSafeBrowsingEnabled(z);
    }

    public static boolean getSafeBrowsingEnabled(WebSettings webSettings) {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(webSettings);
    }

    public static WebViewClient getWebViewClient(WebView webView) {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(webView);
    }

    public static WebChromeClient getWebChromeClient(WebView webView) {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(webView);
    }

    public static PackageInfo getCurrentWebViewPackage() {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m();
    }
}
