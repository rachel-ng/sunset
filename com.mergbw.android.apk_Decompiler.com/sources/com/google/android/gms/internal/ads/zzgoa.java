package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgoa extends ThreadLocal {
    zzgoa() {
    }

    protected static final Cipher zza() {
        try {
            Cipher cipher = (Cipher) zzgyn.zza.zza("AES/GCM-SIV/NoPadding");
            if (!zzgob.zze(cipher)) {
                return null;
            }
            return cipher;
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object initialValue() {
        return zza();
    }
}
