package com.google.android.gms.internal.consent_sdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.sessions.settings.RemoteSettings;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzco {
    public static zzcm zza(Context context, String str) {
        String str2;
        String str3;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(RemoteSettings.FORWARD_SLASH_STRING, -1);
        int length = split.length;
        if (length == 1) {
            String valueOf = String.valueOf(context.getPackageName());
            str2 = split[0];
            str3 = valueOf.concat("_preferences");
        } else if (length != 2) {
            return null;
        } else {
            str3 = split[0];
            str2 = split[1];
        }
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return new zzcm(str3, str2);
    }

    public static void zzb(Context context, Set set) {
        zzcn zzcn = new zzcn(context);
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            zzcm zza = zza(context, str);
            if (zza == null) {
                Log.d("UserMessagingPlatform", "clearKeys: unable to process key: ".concat(String.valueOf(str)));
            } else {
                zzcn.zzd(zza.zza).remove(zza.zzb);
            }
        }
        zzcn.zzb();
    }
}
