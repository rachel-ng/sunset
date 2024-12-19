package androidx.webkit;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Looper;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebViewRenderProcess;
import android.webkit.WebViewRenderProcessClient;
import androidx.webkit.internal.ApiFeature;
import androidx.webkit.internal.ApiHelperForM;
import androidx.webkit.internal.ApiHelperForO;
import androidx.webkit.internal.ApiHelperForOMR1;
import androidx.webkit.internal.ApiHelperForP;
import androidx.webkit.internal.ApiHelperForQ;
import androidx.webkit.internal.WebMessageAdapter;
import androidx.webkit.internal.WebMessagePortImpl;
import androidx.webkit.internal.WebViewFeatureInternal;
import androidx.webkit.internal.WebViewGlueCommunicator;
import androidx.webkit.internal.WebViewProviderAdapter;
import androidx.webkit.internal.WebViewProviderFactory;
import androidx.webkit.internal.WebViewRenderProcessClientFrameworkAdapter;
import androidx.webkit.internal.WebViewRenderProcessImpl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

public class WebViewCompat {
    private static final Uri EMPTY_URI = Uri.parse("");
    private static final Uri WILDCARD_URI = Uri.parse(ProxyConfig.MATCH_ALL_SCHEMES);

    public interface VisualStateCallback {
        void onComplete(long j);
    }

    public interface WebMessageListener {
        void onPostMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri, boolean z, JavaScriptReplyProxy javaScriptReplyProxy);
    }

    private WebViewCompat() {
    }

    public static void postVisualStateCallback(WebView webView, long j, VisualStateCallback visualStateCallback) {
        ApiFeature.M m = WebViewFeatureInternal.VISUAL_STATE_CALLBACK;
        if (m.isSupportedByFramework()) {
            ApiHelperForM.postVisualStateCallback(webView, j, visualStateCallback);
        } else if (m.isSupportedByWebView()) {
            checkThread(webView);
            getProvider(webView).insertVisualStateCallback(j, visualStateCallback);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void startSafeBrowsing(Context context, ValueCallback<Boolean> valueCallback) {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.START_SAFE_BROWSING;
        if (o_mr1.isSupportedByFramework()) {
            ApiHelperForOMR1.startSafeBrowsing(context, valueCallback);
        } else if (o_mr1.isSupportedByWebView()) {
            getFactory().getStatics().initSafeBrowsing(context, valueCallback);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setSafeBrowsingAllowlist(Set<String> set, ValueCallback<Boolean> valueCallback) {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_PREFERRED;
        ApiFeature.O_MR1 o_mr12 = WebViewFeatureInternal.SAFE_BROWSING_ALLOWLIST_PREFERRED_TO_DEPRECATED;
        if (o_mr1.isSupportedByWebView()) {
            getFactory().getStatics().setSafeBrowsingAllowlist(set, valueCallback);
            return;
        }
        ArrayList arrayList = new ArrayList(set);
        if (o_mr12.isSupportedByFramework()) {
            ApiHelperForOMR1.setSafeBrowsingWhitelist(arrayList, valueCallback);
        } else if (o_mr12.isSupportedByWebView()) {
            getFactory().getStatics().setSafeBrowsingWhitelist(arrayList, valueCallback);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    @Deprecated
    public static void setSafeBrowsingWhitelist(List<String> list, ValueCallback<Boolean> valueCallback) {
        setSafeBrowsingAllowlist(new HashSet(list), valueCallback);
    }

    public static Uri getSafeBrowsingPrivacyPolicyUrl() {
        ApiFeature.O_MR1 o_mr1 = WebViewFeatureInternal.SAFE_BROWSING_PRIVACY_POLICY_URL;
        if (o_mr1.isSupportedByFramework()) {
            return ApiHelperForOMR1.getSafeBrowsingPrivacyPolicyUrl();
        }
        if (o_mr1.isSupportedByWebView()) {
            return getFactory().getStatics().getSafeBrowsingPrivacyPolicyUrl();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static PackageInfo getCurrentWebViewPackage(Context context) {
        PackageInfo currentLoadedWebViewPackage = getCurrentLoadedWebViewPackage();
        if (currentLoadedWebViewPackage != null) {
            return currentLoadedWebViewPackage;
        }
        return getNotYetLoadedWebViewPackageInfo(context);
    }

    public static PackageInfo getCurrentLoadedWebViewPackage() {
        if (Build.VERSION.SDK_INT >= 26) {
            return ApiHelperForO.getCurrentWebViewPackage();
        }
        try {
            return getLoadedWebViewPackageInfo();
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    private static PackageInfo getLoadedWebViewPackageInfo() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return (PackageInfo) Class.forName("android.webkit.WebViewFactory").getMethod("getLoadedPackageInfo", (Class[]) null).invoke((Object) null, (Object[]) null);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.content.pm.PackageInfo getNotYetLoadedWebViewPackageInfo(android.content.Context r3) {
        /*
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            r2 = 23
            if (r1 > r2) goto L_0x001a
            java.lang.String r1 = "android.webkit.WebViewFactory"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.String r2 = "getWebViewPackageName"
            java.lang.reflect.Method r1 = r1.getMethod(r2, r0)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.Object r1 = r1.invoke(r0, r0)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            goto L_0x002c
        L_0x001a:
            java.lang.String r1 = "android.webkit.WebViewUpdateService"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.String r2 = "getCurrentWebViewPackageName"
            java.lang.reflect.Method r1 = r1.getMethod(r2, r0)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.Object r1 = r1.invoke(r0, r0)     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException -> 0x0039 }
        L_0x002c:
            if (r1 != 0) goto L_0x002f
            return r0
        L_0x002f:
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r2 = 0
            android.content.pm.PackageInfo r3 = r3.getPackageInfo(r1, r2)     // Catch:{  }
            return r3
        L_0x0039:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.webkit.WebViewCompat.getNotYetLoadedWebViewPackageInfo(android.content.Context):android.content.pm.PackageInfo");
    }

    private static WebViewProviderAdapter getProvider(WebView webView) {
        return new WebViewProviderAdapter(createProvider(webView));
    }

    public static WebMessagePortCompat[] createWebMessageChannel(WebView webView) {
        ApiFeature.M m = WebViewFeatureInternal.CREATE_WEB_MESSAGE_CHANNEL;
        if (m.isSupportedByFramework()) {
            return WebMessagePortImpl.portsToCompat(ApiHelperForM.createWebMessageChannel(webView));
        }
        if (m.isSupportedByWebView()) {
            return getProvider(webView).createWebMessageChannel();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void postWebMessage(WebView webView, WebMessageCompat webMessageCompat, Uri uri) {
        if (WILDCARD_URI.equals(uri)) {
            uri = EMPTY_URI;
        }
        ApiFeature.M m = WebViewFeatureInternal.POST_WEB_MESSAGE;
        if (m.isSupportedByFramework() && webMessageCompat.getType() == 0) {
            ApiHelperForM.postWebMessage(webView, WebMessagePortImpl.compatToFrameworkMessage(webMessageCompat), uri);
        } else if (!m.isSupportedByWebView() || !WebMessageAdapter.isMessagePayloadTypeSupportedByWebView(webMessageCompat.getType())) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        } else {
            getProvider(webView).postWebMessage(webMessageCompat, uri);
        }
    }

    public static void addWebMessageListener(WebView webView, String str, Set<String> set, WebMessageListener webMessageListener) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            getProvider(webView).addWebMessageListener(str, (String[]) set.toArray(new String[0]), webMessageListener);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void removeWebMessageListener(WebView webView, String str) {
        if (WebViewFeatureInternal.WEB_MESSAGE_LISTENER.isSupportedByWebView()) {
            getProvider(webView).removeWebMessageListener(str);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static ScriptHandler addDocumentStartJavaScript(WebView webView, String str, Set<String> set) {
        if (WebViewFeatureInternal.DOCUMENT_START_SCRIPT.isSupportedByWebView()) {
            return getProvider(webView).addDocumentStartJavaScript(str, (String[]) set.toArray(new String[0]));
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewClient getWebViewClient(WebView webView) {
        ApiFeature.O o = WebViewFeatureInternal.GET_WEB_VIEW_CLIENT;
        if (o.isSupportedByFramework()) {
            return ApiHelperForO.getWebViewClient(webView);
        }
        if (o.isSupportedByWebView()) {
            return getProvider(webView).getWebViewClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebChromeClient getWebChromeClient(WebView webView) {
        ApiFeature.O o = WebViewFeatureInternal.GET_WEB_CHROME_CLIENT;
        if (o.isSupportedByFramework()) {
            return ApiHelperForO.getWebChromeClient(webView);
        }
        if (o.isSupportedByWebView()) {
            return getProvider(webView).getWebChromeClient();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static WebViewRenderProcess getWebViewRenderProcess(WebView webView) {
        ApiFeature.Q q = WebViewFeatureInternal.GET_WEB_VIEW_RENDERER;
        if (q.isSupportedByFramework()) {
            WebViewRenderProcess webViewRenderProcess = ApiHelperForQ.getWebViewRenderProcess(webView);
            if (webViewRenderProcess != null) {
                return WebViewRenderProcessImpl.forFrameworkObject(webViewRenderProcess);
            }
            return null;
        } else if (q.isSupportedByWebView()) {
            return getProvider(webView).getWebViewRenderProcess();
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setWebViewRenderProcessClient(WebView webView, Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            ApiHelperForQ.setWebViewRenderProcessClient(webView, executor, webViewRenderProcessClient);
        } else if (q.isSupportedByWebView()) {
            getProvider(webView).setWebViewRenderProcessClient(executor, webViewRenderProcessClient);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static void setWebViewRenderProcessClient(WebView webView, WebViewRenderProcessClient webViewRenderProcessClient) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            ApiHelperForQ.setWebViewRenderProcessClient(webView, webViewRenderProcessClient);
        } else if (q.isSupportedByWebView()) {
            getProvider(webView).setWebViewRenderProcessClient((Executor) null, webViewRenderProcessClient);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static WebViewRenderProcessClient getWebViewRenderProcessClient(WebView webView) {
        ApiFeature.Q q = WebViewFeatureInternal.WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE;
        if (q.isSupportedByFramework()) {
            WebViewRenderProcessClient webViewRenderProcessClient = ApiHelperForQ.getWebViewRenderProcessClient(webView);
            if (webViewRenderProcessClient == null || !(webViewRenderProcessClient instanceof WebViewRenderProcessClientFrameworkAdapter)) {
                return null;
            }
            return ((WebViewRenderProcessClientFrameworkAdapter) webViewRenderProcessClient).getFrameworkRenderProcessClient();
        } else if (q.isSupportedByWebView()) {
            return getProvider(webView).getWebViewRenderProcessClient();
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public static boolean isMultiProcessEnabled() {
        if (WebViewFeatureInternal.MULTI_PROCESS.isSupportedByWebView()) {
            return getFactory().getStatics().isMultiProcessEnabled();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static String getVariationsHeader() {
        if (WebViewFeatureInternal.GET_VARIATIONS_HEADER.isSupportedByWebView()) {
            return getFactory().getStatics().getVariationsHeader();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setProfile(WebView webView, String str) {
        if (WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            getProvider(webView).setProfileWithName(str);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static Profile getProfile(WebView webView) {
        if (WebViewFeatureInternal.MULTI_PROFILE.isSupportedByWebView()) {
            return getProvider(webView).getProfile();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static boolean isAudioMuted(WebView webView) {
        if (WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            return getProvider(webView).isAudioMuted();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static void setAudioMuted(WebView webView, boolean z) {
        if (WebViewFeatureInternal.MUTE_AUDIO.isSupportedByWebView()) {
            getProvider(webView).setAudioMuted(z);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    private static WebViewProviderFactory getFactory() {
        return WebViewGlueCommunicator.getFactory();
    }

    private static WebViewProviderBoundaryInterface createProvider(WebView webView) {
        return getFactory().createWebView(webView);
    }

    private static void checkThread(WebView webView) {
        if (Build.VERSION.SDK_INT >= 28) {
            Looper webViewLooper = ApiHelperForP.getWebViewLooper(webView);
            if (webViewLooper != Looper.myLooper()) {
                throw new RuntimeException("A WebView method was called on thread '" + Thread.currentThread().getName() + "'. All WebView methods must be called on the same thread. (Expected Looper " + webViewLooper + " called on " + Looper.myLooper() + ", FYI main Looper is " + Looper.getMainLooper() + ")");
            }
            return;
        }
        try {
            Method declaredMethod = WebView.class.getDeclaredMethod("checkThread", (Class[]) null);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(webView, (Object[]) null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }
}
