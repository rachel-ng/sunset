package com.google.android.gms.internal.ads;

import android.util.Base64;
import com.google.android.exoplayer2.metadata.icy.IcyHeaders;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.zzu;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfid {
    public zzfid() {
        try {
            zzgig.zza();
        } catch (GeneralSecurityException e) {
            zze.zza("Failed to Configure Aead. ".concat(e.toString()));
            zzu.zzo().zzw(e, "CryptoUtils.registerAead");
        }
    }

    public static final String zza() {
        zzgzz zzt = zzhac.zzt();
        try {
            zzghb.zzb(zzght.zzb(zzghl.zza(zzgph.zzb().zza("AES128_GCM"))), zzgha.zzb(zzt));
        } catch (IOException | GeneralSecurityException e) {
            zze.zza("Failed to generate key".concat(e.toString()));
            zzu.zzo().zzw(e, "CryptoUtils.generateKey");
        }
        String encodeToString = Base64.encodeToString(zzt.zzb().zzB(), 11);
        zzt.zzc();
        return encodeToString;
    }

    @Nullable
    public static final String zzb(byte[] bArr, byte[] bArr2, String str, zzdux zzdux) {
        zzght zzc = zzc(str);
        if (zzc == null) {
            return null;
        }
        try {
            byte[] zza = ((zzggy) zzc.zzd(zzgqp.zza(), zzggy.class)).zza(bArr, bArr2);
            zzdux.zzb().put("ds", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            return new String(zza, "UTF-8");
        } catch (UnsupportedEncodingException | UnsupportedOperationException | GeneralSecurityException e) {
            zze.zza("Failed to decrypt ".concat(e.toString()));
            zzu.zzo().zzw(e, "CryptoUtils.decrypt");
            zzdux.zzb().put("dsf", e.toString());
            return null;
        }
    }

    @Nullable
    private static final zzght zzc(String str) {
        try {
            return zzghb.zza(zzggz.zzb(Base64.decode(str, 11)));
        } catch (IOException | GeneralSecurityException e) {
            zze.zza("Failed to get keysethandle".concat(e.toString()));
            zzu.zzo().zzw(e, "CryptoUtils.getHandle");
            return null;
        }
    }
}
