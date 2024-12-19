package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import javax.crypto.AEADBadTagException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgnp {
    private final zzgnn zza;
    private final zzgnn zzb;

    public zzgnp(byte[] bArr) throws GeneralSecurityException {
        if (zzgoc.zza(1)) {
            this.zza = zza(bArr, 1);
            this.zzb = zza(bArr, 0);
            return;
        }
        throw new GeneralSecurityException("Can not use ChaCha20Poly1305 in FIPS-mode.");
    }

    /* access modifiers changed from: package-private */
    public abstract zzgnn zza(byte[] bArr, int i) throws InvalidKeyException;

    public final byte[] zzb(ByteBuffer byteBuffer, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (byteBuffer.remaining() >= 16) {
            int position = byteBuffer.position();
            byte[] bArr3 = new byte[16];
            byteBuffer.position(byteBuffer.limit() - 16);
            byteBuffer.get(bArr3);
            byteBuffer.position(position);
            byteBuffer.limit(byteBuffer.limit() - 16);
            if (bArr2 == null) {
                bArr2 = new byte[0];
            }
            try {
                byte[] bArr4 = new byte[32];
                this.zzb.zzc(bArr, 0).get(bArr4);
                int length = bArr2.length;
                int i = length & 15;
                int i2 = i == 0 ? length : (length + 16) - i;
                int remaining = byteBuffer.remaining();
                int i3 = remaining % 16;
                int i4 = (i3 == 0 ? remaining : (remaining + 16) - i3) + i2;
                ByteBuffer order = ByteBuffer.allocate(i4 + 16).order(ByteOrder.LITTLE_ENDIAN);
                order.put(bArr2);
                order.position(i2);
                order.put(byteBuffer);
                order.position(i4);
                order.putLong((long) length);
                order.putLong((long) remaining);
                if (MessageDigest.isEqual(zzgnt.zza(bArr4, order.array()), bArr3)) {
                    byteBuffer.position(position);
                    return this.zza.zzd(bArr, byteBuffer);
                }
                throw new GeneralSecurityException("invalid MAC");
            } catch (GeneralSecurityException e) {
                throw new AEADBadTagException(e.toString());
            }
        } else {
            throw new GeneralSecurityException("ciphertext too short");
        }
    }
}
