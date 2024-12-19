package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Arrays;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgil implements zzggy {
    private final zzgqk zza;
    private final zzgtl zzb;
    private final zzgtl zzc;

    /* synthetic */ zzgil(zzgqk zzgqk, zzgik zzgik) {
        zzgtl zzgtl;
        this.zza = zzgqk;
        if (zzgqk.zzg()) {
            zzgtm zzb2 = zzgpg.zza().zzb();
            zzgtr zza2 = zzgoy.zza(zzgqk);
            this.zzb = zzb2.zza(zza2, "aead", "encrypt");
            zzgtl = zzb2.zza(zza2, "aead", "decrypt");
        } else {
            zzgtl = zzgoy.zza;
            this.zzb = zzgtl;
        }
        this.zzc = zzgtl;
    }

    public final byte[] zza(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 5) {
            for (zzgqi zzgqi : this.zza.zzf(Arrays.copyOf(bArr, 5))) {
                try {
                    byte[] zza2 = ((zzggy) zzgqi.zze()).zza(bArr, bArr2);
                    zzgqi.zza();
                    int length = bArr.length;
                    return zza2;
                } catch (GeneralSecurityException unused) {
                }
            }
        }
        for (zzgqi zzgqi2 : this.zza.zzf(zzghd.zza)) {
            try {
                byte[] zza3 = ((zzggy) zzgqi2.zze()).zza(bArr, bArr2);
                zzgqi2.zza();
                int length2 = bArr.length;
                return zza3;
            } catch (GeneralSecurityException unused2) {
            }
        }
        throw new GeneralSecurityException("decryption failed");
    }
}
