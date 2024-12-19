package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;

public class ApiHelperForTiramisu {
    private ApiHelperForTiramisu() {
    }

    static PackageManager.ComponentInfoFlags of(long j) {
        return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m(j);
    }

    static ServiceInfo getServiceInfo(PackageManager packageManager, ComponentName componentName, PackageManager.ComponentInfoFlags componentInfoFlags) throws PackageManager.NameNotFoundException {
        return packageManager.getServiceInfo(componentName, componentInfoFlags);
    }
}
