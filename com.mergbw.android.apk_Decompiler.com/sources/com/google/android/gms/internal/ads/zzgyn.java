package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgyn {
    public static final zzgyn zza = new zzgyn(new zzgyo());
    public static final zzgyn zzb = new zzgyn(new zzgys());
    public static final zzgyn zzc = new zzgyn(new zzgyu());
    public static final zzgyn zzd = new zzgyn(new zzgyt());
    public static final zzgyn zze = new zzgyn(new zzgyp());
    public static final zzgyn zzf = new zzgyn(new zzgyr());
    public static final zzgyn zzg = new zzgyn(new zzgyq());
    private final zzgym zzh;

    public zzgyn(zzgyv zzgyv) {
        this.zzh = !zzgod.zzb() ? "The Android Project".equals(System.getProperty("java.vendor")) ? new zzgyh(zzgyv, (zzgyg) null) : new zzgyj(zzgyv, (zzgyi) null) : new zzgyl(zzgyv, (zzgyk) null);
    }

    public static List zzb(String... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String provider : strArr) {
            Provider provider2 = Security.getProvider(provider);
            if (provider2 != null) {
                arrayList.add(provider2);
            }
        }
        return arrayList;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        return this.zzh.zza(str);
    }
}
