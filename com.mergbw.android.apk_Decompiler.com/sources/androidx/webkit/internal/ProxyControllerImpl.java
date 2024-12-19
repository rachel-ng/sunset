package androidx.webkit.internal;

import androidx.webkit.ProxyConfig;
import androidx.webkit.ProxyController;
import androidx.webkit.internal.ApiFeature;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.ProxyControllerBoundaryInterface;

public class ProxyControllerImpl extends ProxyController {
    private ProxyControllerBoundaryInterface mBoundaryInterface;

    public void setProxyOverride(ProxyConfig proxyConfig, Executor executor, Runnable runnable) {
        ApiFeature.NoFramework noFramework = WebViewFeatureInternal.PROXY_OVERRIDE;
        ApiFeature.NoFramework noFramework2 = WebViewFeatureInternal.PROXY_OVERRIDE_REVERSE_BYPASS;
        String[][] proxyRulesToStringArray = proxyRulesToStringArray(proxyConfig.getProxyRules());
        String[] strArr = (String[]) proxyConfig.getBypassRules().toArray(new String[0]);
        if (noFramework.isSupportedByWebView() && !proxyConfig.isReverseBypassEnabled()) {
            getBoundaryInterface().setProxyOverride(proxyRulesToStringArray, strArr, runnable, executor);
        } else if (!noFramework.isSupportedByWebView() || !noFramework2.isSupportedByWebView()) {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        } else {
            getBoundaryInterface().setProxyOverride(proxyRulesToStringArray, strArr, runnable, executor, proxyConfig.isReverseBypassEnabled());
        }
    }

    public void clearProxyOverride(Executor executor, Runnable runnable) {
        if (WebViewFeatureInternal.PROXY_OVERRIDE.isSupportedByWebView()) {
            getBoundaryInterface().clearProxyOverride(runnable, executor);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public static String[][] proxyRulesToStringArray(List<ProxyConfig.ProxyRule> list) {
        int size = list.size();
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = size;
        String[][] strArr = (String[][]) Array.newInstance(String.class, iArr);
        for (int i = 0; i < list.size(); i++) {
            strArr[i][0] = list.get(i).getSchemeFilter();
            strArr[i][1] = list.get(i).getUrl();
        }
        return strArr;
    }

    private ProxyControllerBoundaryInterface getBoundaryInterface() {
        if (this.mBoundaryInterface == null) {
            this.mBoundaryInterface = WebViewGlueCommunicator.getFactory().getProxyController();
        }
        return this.mBoundaryInterface;
    }
}
