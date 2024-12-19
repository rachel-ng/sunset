package com.google.android.gms.internal.ads;

import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.Provider;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgyh implements zzgym {
    private final zzgyv zza;

    /* synthetic */ zzgyh(zzgyv zzgyv, zzgyg zzgyg) {
        this.zza = zzgyv;
    }

    public final Object zza(String str) throws GeneralSecurityException {
        for (Provider zza2 : zzgyn.zzb(ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL")) {
            try {
                return this.zza.zza(str, zza2);
            } catch (Exception unused) {
            }
        }
        return this.zza.zza(str, (Provider) null);
    }
}
