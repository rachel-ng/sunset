package androidx.webkit.internal;

import android.content.Context;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import androidx.tracing.Trace$$ExternalSyntheticApiModelOutline0;
import androidx.webkit.ServiceWorkerClientCompat;
import java.io.File;

public class ApiHelperForN {
    private ApiHelperForN() {
    }

    public static File getDataDir(Context context) {
        return Trace$$ExternalSyntheticApiModelOutline0.m(context);
    }

    public static ServiceWorkerController getServiceWorkerControllerInstance() {
        return Trace$$ExternalSyntheticApiModelOutline0.m();
    }

    public static ServiceWorkerWebSettings getServiceWorkerWebSettings(ServiceWorkerController serviceWorkerController) {
        return serviceWorkerController.getServiceWorkerWebSettings();
    }

    public static ServiceWorkerWebSettingsImpl getServiceWorkerWebSettingsImpl(ServiceWorkerController serviceWorkerController) {
        return new ServiceWorkerWebSettingsImpl(getServiceWorkerWebSettings(serviceWorkerController));
    }

    public static void setServiceWorkerClient(ServiceWorkerController serviceWorkerController, ServiceWorkerClient serviceWorkerClient) {
        serviceWorkerController.setServiceWorkerClient(serviceWorkerClient);
    }

    public static void setServiceWorkerClientCompat(ServiceWorkerController serviceWorkerController, ServiceWorkerClientCompat serviceWorkerClientCompat) {
        serviceWorkerController.setServiceWorkerClient(new FrameworkServiceWorkerClient(serviceWorkerClientCompat));
    }

    public static void setCacheMode(ServiceWorkerWebSettings serviceWorkerWebSettings, int i) {
        serviceWorkerWebSettings.setCacheMode(i);
    }

    public static int getCacheMode(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return Trace$$ExternalSyntheticApiModelOutline0.m(serviceWorkerWebSettings);
    }

    public static void setAllowContentAccess(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z) {
        serviceWorkerWebSettings.setAllowContentAccess(z);
    }

    public static boolean getAllowContentAccess(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getAllowContentAccess();
    }

    public static void setAllowFileAccess(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z) {
        serviceWorkerWebSettings.setAllowFileAccess(z);
    }

    public static boolean getAllowFileAccess(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return serviceWorkerWebSettings.getAllowFileAccess();
    }

    public static void setBlockNetworkLoads(ServiceWorkerWebSettings serviceWorkerWebSettings, boolean z) {
        serviceWorkerWebSettings.setBlockNetworkLoads(z);
    }

    public static boolean getBlockNetworkLoads(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        return Trace$$ExternalSyntheticApiModelOutline0.m(serviceWorkerWebSettings);
    }

    public static boolean isRedirect(WebResourceRequest webResourceRequest) {
        return webResourceRequest.isRedirect();
    }

    public static void setDisabledActionModeMenuItems(WebSettings webSettings, int i) {
        webSettings.setDisabledActionModeMenuItems(i);
    }

    public static int getDisabledActionModeMenuItems(WebSettings webSettings) {
        return webSettings.getDisabledActionModeMenuItems();
    }
}
