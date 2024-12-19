package com.google.android.gms.internal.consent_sdk;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzcl {
    private static String zza;

    public static synchronized String zza(Context context) {
        String str;
        String str2;
        synchronized (zzcl.class) {
            if (zza == null) {
                ContentResolver contentResolver = context.getContentResolver();
                if (contentResolver == null) {
                    str2 = null;
                } else {
                    str2 = Settings.Secure.getString(contentResolver, "android_id");
                }
                if (str2 == null || zzct.zza(true)) {
                    str2 = "emulator";
                }
                zza = zzb(str2);
            }
            str = zza;
        }
        return str;
    }

    private static String zzb(String str) {
        int i = 0;
        while (i < 3) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(str.getBytes());
                return String.format("%032X", new Object[]{new BigInteger(1, instance.digest())});
            } catch (NoSuchAlgorithmException unused) {
                i++;
            } catch (ArithmeticException unused2) {
                return "";
            }
        }
        return "";
    }
}
