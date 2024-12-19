package com.google.android.gms.internal.consent_sdk;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzcv extends zzcu {
    public static boolean zza(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
