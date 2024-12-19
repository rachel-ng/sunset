package com.google.android.gms.internal.ads;

import com.google.android.gms.security.ProviderInstaller;
import java.lang.reflect.InvocationTargetException;
import java.security.GeneralSecurityException;
import java.security.Provider;
import java.security.SecureRandom;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgqo {
    private static final ThreadLocal zza = new zzgqn();

    static /* synthetic */ SecureRandom zza() {
        SecureRandom zzc = zzc();
        zzc.nextLong();
        return zzc;
    }

    public static byte[] zzb(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) zza.get()).nextBytes(bArr);
        return bArr;
    }

    private static SecureRandom zzc() {
        try {
            return SecureRandom.getInstance("SHA1PRNG", ProviderInstaller.PROVIDER_NAME);
        } catch (GeneralSecurityException unused) {
            try {
                return SecureRandom.getInstance("SHA1PRNG", "AndroidOpenSSL");
            } catch (GeneralSecurityException unused2) {
                try {
                    return SecureRandom.getInstance("SHA1PRNG", "Conscrypt");
                } catch (GeneralSecurityException unused3) {
                    try {
                        try {
                            return SecureRandom.getInstance("SHA1PRNG", (Provider) Class.forName("org.conscrypt.Conscrypt").getMethod("newProvider", (Class[]) null).invoke((Object) null, (Object[]) null));
                        } catch (GeneralSecurityException unused4) {
                            return new SecureRandom();
                        }
                    } catch (ClassNotFoundException e) {
                        e = e;
                        throw new GeneralSecurityException("Failed to get Conscrypt provider", e);
                    } catch (NoSuchMethodException e2) {
                        e = e2;
                        throw new GeneralSecurityException("Failed to get Conscrypt provider", e);
                    } catch (IllegalArgumentException e3) {
                        e = e3;
                        throw new GeneralSecurityException("Failed to get Conscrypt provider", e);
                    } catch (InvocationTargetException e4) {
                        e = e4;
                        throw new GeneralSecurityException("Failed to get Conscrypt provider", e);
                    } catch (IllegalAccessException e5) {
                        e = e5;
                        throw new GeneralSecurityException("Failed to get Conscrypt provider", e);
                    }
                }
            }
        }
    }
}
