package androidx.work.impl.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.work.Configuration;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import androidx.work.Logger;
import java.lang.reflect.Method;
import java.util.List;

public class ProcessUtils {
    private static final String TAG = Logger.tagWithPrefix("ProcessUtils");

    private ProcessUtils() {
    }

    public static boolean isDefaultProcess(Context context, Configuration configuration) {
        String processName = getProcessName(context);
        if (!TextUtils.isEmpty(configuration.getDefaultProcessName())) {
            return TextUtils.equals(processName, configuration.getDefaultProcessName());
        }
        return TextUtils.equals(processName, context.getApplicationInfo().processName);
    }

    public static String getProcessName(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (Build.VERSION.SDK_INT >= 28) {
            return Constraints$Builder$$ExternalSyntheticApiModelOutline0.m();
        }
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, ProcessUtils.class.getClassLoader()).getDeclaredMethod("currentProcessName", (Class[]) null);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke((Object) null, (Object[]) null);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (Throwable th) {
            Logger.get().debug(TAG, "Unable to check ActivityThread for processName", th);
        }
        int myPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (!(activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.isEmpty())) {
            for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
                if (next.pid == myPid) {
                    return next.processName;
                }
            }
        }
        return null;
    }
}
