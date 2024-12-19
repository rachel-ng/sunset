package com.google.android.gms.internal.ads;

import android.os.Build;
import com.google.common.base.Ascii;
import com.mergbw.core.ble.CommandList;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import kotlin.io.encoding.Base64;
import okio.Utf8;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfqx {
    protected static final byte[] zza = {Base64.padSymbol, 122, Ascii.DC2, 35, 1, -102, -93, -99, -98, -96, -29, 67, 106, -73, -64, -119, 107, -5, 79, -74, 121, CommandList.CMD_CONFIG_RGB_ORDER_REQ, -34, 95, -25, -62, Utf8.REPLACEMENT_BYTE, 50, 108, -113, -103, 74};
    protected static final byte[] zzb = {-110, CommandList.CMD_CONFIG_INFO_REQ, -34, 70, -83, 43, 97, Ascii.NAK, -44, 16, -54, -125, -28, -57, -125, -127, -7, 17, 102, -69, 116, -121, -79, 43, CommandList.CMD_CONFIG_INFO_REQ, 120, 58, 55, -29, -108, 95, 83};
    private final byte[] zzc = zzb;
    private final byte[] zzd = zza;

    public final boolean zza(File file) throws GeneralSecurityException {
        try {
            X509Certificate[][] zza2 = zzaqk.zza(file.getAbsolutePath());
            if (zza2.length == 1) {
                byte[] digest = MessageDigest.getInstance("SHA-256").digest(zza2[0][0].getEncoded());
                if (Arrays.equals(this.zzd, digest)) {
                    return true;
                }
                if (!"user".equals(Build.TYPE)) {
                    return Arrays.equals(this.zzc, digest);
                }
                return false;
            }
            throw new GeneralSecurityException("APK has more than one signature.");
        } catch (zzaqh e) {
            throw new GeneralSecurityException("Package is not signed", e);
        } catch (IOException | RuntimeException e2) {
            throw new GeneralSecurityException("Failed to verify signatures", e2);
        }
    }
}
