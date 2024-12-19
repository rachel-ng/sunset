package com.google.android.gms.internal.ads;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgkw implements zzggy {
    public static final /* synthetic */ int zza = 0;
    private static final byte[] zzb = new byte[0];
    private static final Set zzc;
    private final String zzd;
    private final zzghx zze;
    private final zzggy zzf;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.XChaCha20Poly1305Key");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesGcmSivKey");
        hashSet.add("type.googleapis.com/google.crypto.tink.AesEaxKey");
        zzc = Collections.unmodifiableSet(hashSet);
    }

    @Deprecated
    zzgkw(zzgwm zzgwm, zzggy zzggy) throws GeneralSecurityException {
        if (zzc.contains(zzgwm.zzi())) {
            this.zzd = zzgwm.zzi();
            zzgwl zzc2 = zzgwm.zzc(zzgwm);
            zzc2.zza(zzgxn.RAW);
            this.zze = zzgie.zza(((zzgwm) zzc2.zzbr()).zzaV());
            this.zzf = zzggy;
            return;
        }
        String zzi = zzgwm.zzi();
        throw new IllegalArgumentException("Unsupported DEK key type: " + zzi + ". Only Tink AEAD key types are supported.");
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        try {
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            int i = wrap.getInt();
            if (i <= 0 || i > bArr.length - 4) {
                throw new GeneralSecurityException("invalid ciphertext");
            }
            byte[] bArr3 = new byte[i];
            wrap.get(bArr3, 0, i);
            byte[] bArr4 = new byte[wrap.remaining()];
            wrap.get(bArr4, 0, wrap.remaining());
            byte[] zza2 = this.zzf.zza(bArr3, zzb);
            String str = this.zzd;
            zzhac zzhac = zzhac.zzb;
            return ((zzggy) zzgpi.zza().zzc(zzgpl.zzc().zza(zzgql.zza(str, zzhac.zzv(zza2, 0, zza2.length), zzgwg.SYMMETRIC, zzgxn.RAW, (Integer) null), zzghh.zza()), zzggy.class)).zza(bArr4, bArr2);
        } catch (IndexOutOfBoundsException | NegativeArraySizeException | BufferUnderflowException e) {
            throw new GeneralSecurityException("invalid ciphertext", e);
        }
    }
}
