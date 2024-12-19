package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public final class zzfxv {
    static final CharSequence zza(@CheckForNull Object obj) {
        Objects.requireNonNull(obj);
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }

    public static final StringBuilder zzb(StringBuilder sb, Iterable iterable, String str) {
        Iterator it = iterable.iterator();
        try {
            if (it.hasNext()) {
                sb.append(zza(it.next()));
                while (it.hasNext()) {
                    sb.append(str);
                    sb.append(zza(it.next()));
                }
            }
            return sb;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
