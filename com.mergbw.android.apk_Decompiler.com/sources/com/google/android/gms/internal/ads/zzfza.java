package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfza {
    @CheckForNull
    private static final Object zza;
    @CheckForNull
    private static final Method zzb;
    @CheckForNull
    private static final Method zzc;

    static {
        Method method;
        Object zza2 = zza();
        zza = zza2;
        Method method2 = null;
        if (zza2 == null) {
            method = null;
        } else {
            method = zzb("getStackTraceElement", Throwable.class, Integer.TYPE);
        }
        zzb = method;
        if (zza2 != null) {
            method2 = zzc(zza2);
        }
        zzc = method2;
    }

    @CheckForNull
    private static Object zza() {
        try {
            return Class.forName("sun.misc.SharedSecrets", false, (ClassLoader) null).getMethod("getJavaLangAccess", (Class[]) null).invoke((Object) null, (Object[]) null);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzb(String str, Class... clsArr) throws ThreadDeath {
        try {
            return Class.forName("sun.misc.JavaLangAccess", false, (ClassLoader) null).getMethod(str, clsArr);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable unused) {
            return null;
        }
    }

    @CheckForNull
    private static Method zzc(Object obj) {
        try {
            Method zzb2 = zzb("getStackTraceDepth", Throwable.class);
            if (zzb2 == null) {
                return null;
            }
            zzb2.invoke(obj, new Object[]{new Throwable()});
            return zzb2;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }
}
