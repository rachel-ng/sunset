package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.AEADBadTagException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgyb implements zzggy {
    private static final ThreadLocal zza = new zzgxz();
    private static final ThreadLocal zzb = new zzgya();
    private final byte[] zzc;
    private final byte[] zzd;
    private final byte[] zze;
    private final SecretKeySpec zzf;
    private final int zzg;

    private zzgyb(byte[] bArr, int i, byte[] bArr2) throws GeneralSecurityException {
        if (!zzgoc.zza(1)) {
            throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
        } else if (i == 12 || i == 16) {
            this.zzg = i;
            zzgzc.zza(bArr.length);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            this.zzf = secretKeySpec;
            Cipher cipher = (Cipher) zza.get();
            cipher.init(1, secretKeySpec);
            byte[] zzc2 = zzc(cipher.doFinal(new byte[16]));
            this.zzc = zzc2;
            this.zzd = zzc(zzc2);
            this.zze = bArr2;
        } else {
            throw new IllegalArgumentException("IV size should be either 12 or 16 bytes");
        }
    }

    public static zzggy zzb(zzgje zzgje) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            return new zzgyb(zzgje.zzd().zzd(zzghh.zza()), zzgje.zzb().zzb(), zzgje.zzc().zzc());
        }
        throw new GeneralSecurityException("Can not use AES-EAX in FIPS-mode.");
    }

    private static byte[] zzc(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        int i = 0;
        while (i < 15) {
            byte b2 = bArr[i];
            int i2 = i + 1;
            bArr2[i] = (byte) (((b2 + b2) ^ ((bArr[i2] & 255) >>> 7)) & 255);
            i = i2;
        }
        byte b3 = bArr[15];
        bArr2[15] = (byte) (((bArr[0] >> 7) & TsExtractor.TS_STREAM_TYPE_E_AC3) ^ (b3 + b3));
        return bArr2;
    }

    private final byte[] zzd(Cipher cipher, int i, byte[] bArr, int i2, int i3) throws IllegalBlockSizeException, BadPaddingException {
        byte[] bArr2;
        int length;
        byte[] bArr3 = new byte[16];
        bArr3[15] = (byte) i;
        if (i3 == 0) {
            return cipher.doFinal(zzf(bArr3, this.zzc));
        }
        byte[] doFinal = cipher.doFinal(bArr3);
        int i4 = 0;
        int i5 = 0;
        while (i3 - i5 > 16) {
            for (int i6 = 0; i6 < 16; i6++) {
                doFinal[i6] = (byte) (bArr[(i2 + i5) + i6] ^ doFinal[i6]);
            }
            doFinal = cipher.doFinal(doFinal);
            i5 += 16;
        }
        byte[] copyOfRange = Arrays.copyOfRange(bArr, i5 + i2, i2 + i3);
        if (copyOfRange.length == 16) {
            bArr2 = zzf(copyOfRange, this.zzc);
        } else {
            byte[] copyOf = Arrays.copyOf(this.zzd, 16);
            while (true) {
                length = copyOfRange.length;
                if (i4 >= length) {
                    break;
                }
                copyOf[i4] = (byte) (copyOf[i4] ^ copyOfRange[i4]);
                i4++;
            }
            copyOf[length] = (byte) (copyOf[length] ^ 128);
            bArr2 = copyOf;
        }
        return cipher.doFinal(zzf(doFinal, bArr2));
    }

    private final byte[] zze(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int length = bArr.length;
        int i = (length - this.zzg) - 16;
        if (i >= 0) {
            Cipher cipher = (Cipher) zza.get();
            cipher.init(1, this.zzf);
            byte[] zzd2 = zzd(cipher, 0, bArr, 0, this.zzg);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] bArr3 = bArr2;
            byte[] zzd3 = zzd(cipher, 1, bArr3, 0, bArr3.length);
            byte[] zzd4 = zzd(cipher, 2, bArr, this.zzg, i);
            int i2 = length - 16;
            byte b2 = 0;
            for (int i3 = 0; i3 < 16; i3++) {
                b2 = (byte) (b2 | (((bArr[i2 + i3] ^ zzd3[i3]) ^ zzd2[i3]) ^ zzd4[i3]));
            }
            if (b2 == 0) {
                Cipher cipher2 = (Cipher) zzb.get();
                cipher2.init(1, this.zzf, new IvParameterSpec(zzd2));
                return cipher2.doFinal(bArr, this.zzg, i);
            }
            throw new AEADBadTagException("tag mismatch");
        }
        throw new GeneralSecurityException("ciphertext too short");
    }

    private static byte[] zzf(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
        return bArr3;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zze;
        if (bArr3.length == 0) {
            return zze(bArr, bArr2);
        }
        if (zzgra.zzc(bArr3, bArr)) {
            byte[] bArr4 = this.zze;
            return zze(Arrays.copyOfRange(bArr, bArr4.length, bArr.length), bArr2);
        }
        throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
    }
}
