package com.google.android.gms.internal.ads;

import java.util.Collection;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgab {
    static boolean zza(Collection collection, @CheckForNull Object obj) {
        collection.getClass();
        try {
            return collection.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }
}
