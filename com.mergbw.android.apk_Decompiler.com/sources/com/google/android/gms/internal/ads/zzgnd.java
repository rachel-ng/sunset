package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import javax.annotation.Nullable;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgnd implements zzggy {
    private static final byte[] zza = zzgyw.zza("808182838485868788898a8b8c8d8e8f909192939495969798999a9b9c9d9e9f");
    private static final byte[] zzb = zzgyw.zza("070000004041424344454647");
    private static final byte[] zzc = zzgyw.zza("a0784d7a4716f3feb4f64e7f4b39bf04");
    private static final ThreadLocal zzd = new zzgnc();
    private final SecretKey zze;
    private final byte[] zzf;

    private zzgnd(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!zzgoc.zza(1)) {
            throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
        } else if (!zze()) {
            throw new GeneralSecurityException("JCE does not support algorithm: ChaCha20-Poly1305");
        } else if (bArr.length == 32) {
            this.zze = new SecretKeySpec(bArr, "ChaCha20");
            this.zzf = bArr2;
        } else {
            throw new InvalidKeyException("The key length in bytes must be 32.");
        }
    }

    public static zzggy zzb(zzgkn zzgkn) throws GeneralSecurityException {
        return new zzgnd(zzgkn.zzd().zzd(zzghh.zza()), zzgkn.zzc().zzc());
    }

    @Nullable
    static Cipher zzc() {
        return (Cipher) zzd.get();
    }

    public static boolean zze() {
        return zzd.get() != null;
    }

    /* access modifiers changed from: private */
    public static boolean zzf(Cipher cipher) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(zzb);
            byte[] bArr = zza;
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            byte[] bArr2 = zzc;
            if (cipher.doFinal(bArr2).length != 0) {
                return false;
            }
            cipher.init(2, new SecretKeySpec(bArr, "ChaCha20"), ivParameterSpec);
            if (cipher.doFinal(bArr2).length != 0) {
                return false;
            }
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr != null) {
            byte[] bArr3 = this.zzf;
            int length = bArr.length;
            if (length < bArr3.length + 28) {
                throw new GeneralSecurityException("ciphertext too short");
            } else if (zzgra.zzc(bArr3, bArr)) {
                byte[] bArr4 = new byte[12];
                System.arraycopy(bArr, this.zzf.length, bArr4, 0, 12);
                IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
                Cipher cipher = (Cipher) zzd.get();
                cipher.init(2, this.zze, ivParameterSpec);
                if (!(bArr2 == null || bArr2.length == 0)) {
                    cipher.updateAAD(bArr2);
                }
                int length2 = this.zzf.length;
                return cipher.doFinal(bArr, length2 + 12, (length - length2) - 12);
            } else {
                throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
            }
        } else {
            throw new NullPointerException("ciphertext is null");
        }
    }
}
