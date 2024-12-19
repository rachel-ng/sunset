package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzghv {
    private static final CopyOnWriteArrayList zza = new CopyOnWriteArrayList();

    public static zzghu zza(String str) throws GeneralSecurityException {
        Iterator it = zza.iterator();
        while (it.hasNext()) {
            zzghu zzghu = (zzghu) it.next();
            if (zzghu.zza()) {
                return zzghu;
            }
        }
        throw new GeneralSecurityException("No KMS client does support: ".concat(String.valueOf(str)));
    }
}
