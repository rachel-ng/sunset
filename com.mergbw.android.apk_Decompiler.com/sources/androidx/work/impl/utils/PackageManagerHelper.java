package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.Logger;

public class PackageManagerHelper {
    private static final String TAG = Logger.tagWithPrefix("PackageManagerHelper");

    private PackageManagerHelper() {
    }

    public static void setComponentEnabled(Context context, Class<?> cls, boolean z) {
        String str;
        String str2 = "enabled";
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, cls.getName()), z ? 1 : 2, 1);
            Logger logger = Logger.get();
            String str3 = TAG;
            String name = cls.getName();
            if (z) {
                str = str2;
            } else {
                str = "disabled";
            }
            logger.debug(str3, String.format("%s %s", new Object[]{name, str}), new Throwable[0]);
        } catch (Exception e) {
            Logger logger2 = Logger.get();
            String str4 = TAG;
            String name2 = cls.getName();
            if (!z) {
                str2 = "disabled";
            }
            logger2.debug(str4, String.format("%s could not be %s", new Object[]{name2, str2}), e);
        }
    }

    public static boolean isComponentExplicitlyEnabled(Context context, Class<?> cls) {
        return isComponentExplicitlyEnabled(context, cls.getName());
    }

    public static boolean isComponentExplicitlyEnabled(Context context, String str) {
        return context.getPackageManager().getComponentEnabledSetting(new ComponentName(context, str)) == 1;
    }
}
