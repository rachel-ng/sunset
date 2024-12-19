package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzgyf implements zzggy {
    private final zzgyx zza;
    private final zzghw zzb;
    private final int zzc;
    private final byte[] zzd;

    private zzgyf(zzgyx zzgyx, zzghw zzghw, int i, byte[] bArr) {
        this.zza = zzgyx;
        this.zzb = zzghw;
        this.zzc = i;
        this.zzd = bArr;
    }

    public static zzggy zzb(zzgiq zzgiq) throws GeneralSecurityException {
        zzgxy zzgxy = new zzgxy(zzgiq.zzd().zzd(zzghh.zza()), zzgiq.zzb().zzd());
        String valueOf = String.valueOf(String.valueOf(zzgiq.zzb().zzg()));
        return new zzgyf(zzgxy, new zzgzb(new zzgza("HMAC".concat(valueOf), new SecretKeySpec(zzgiq.zze().zzd(zzghh.zza()), "HMAC")), zzgiq.zzb().zze()), zzgiq.zzb().zze(), zzgiq.zzc().zzc());
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] bArr3 = this.zzd;
        int i = this.zzc;
        int length = bArr3.length;
        int length2 = bArr.length;
        if (length2 < i + length) {
            throw new GeneralSecurityException("Decryption failed (ciphertext too short).");
        } else if (zzgra.zzc(bArr3, bArr)) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, this.zzd.length, length2 - this.zzc);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, length2 - this.zzc, length2);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            byte[] copyOf = Arrays.copyOf(ByteBuffer.allocate(8).putLong(((long) bArr2.length) * 8).array(), 8);
            byte[][] bArr4 = {bArr2, copyOfRange, copyOf};
            if (MessageDigest.isEqual(((zzgzb) this.zzb).zzc(zzgyd.zzb(bArr4)), copyOfRange2)) {
                return this.zza.zza(copyOfRange);
            }
            throw new GeneralSecurityException("invalid MAC");
        } else {
            throw new GeneralSecurityException("Decryption failed (OutputPrefix mismatch).");
        }
    }
}
