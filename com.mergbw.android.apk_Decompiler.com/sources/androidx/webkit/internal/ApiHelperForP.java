package androidx.webkit.internal;

import android.os.Looper;
import android.webkit.TracingController;
import android.webkit.WebView;
import androidx.webkit.TracingConfig;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import java.io.OutputStream;
import java.util.concurrent.Executor;

public class ApiHelperForP {
    private ApiHelperForP() {
    }

    public static TracingController getTracingControllerInstance() {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m();
    }

    public static boolean isTracing(TracingController tracingController) {
        return tracingController.isTracing();
    }

    public static void start(TracingController tracingController, TracingConfig tracingConfig) {
        tracingController.start(Constraints$Builder$$ExternalSyntheticApiModelOutline0.m().addCategories(new int[]{tracingConfig.getPredefinedCategories()}).addCategories(tracingConfig.getCustomIncludedCategories()).setTracingMode(tracingConfig.getTracingMode()).build());
    }

    public static boolean stop(TracingController tracingController, OutputStream outputStream, Executor executor) {
        return tracingController.stop(outputStream, executor);
    }

    public static ClassLoader getWebViewClassLoader() {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m();
    }

    public static Looper getWebViewLooper(WebView webView) {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(webView);
    }

    public static void setDataDirectorySuffix(String str) {
        Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(str);
    }
}
