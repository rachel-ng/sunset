package com.google.android.gms.ads.internal.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.ads.zzbep;
import com.google.android.gms.internal.ads.zzfvg;
import com.google.android.gms.internal.ads.zzfvh;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzad {
    public static Bundle zza(Context context, String str, SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        if (TextUtils.isEmpty(str)) {
            return Bundle.EMPTY;
        }
        PreferenceManager.getDefaultSharedPreferences(context).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
        return zzb(context, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0018  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.os.Bundle zzb(android.content.Context r10, java.lang.String r11) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r11)
            r1 = 0
            if (r0 == 0) goto L_0x0009
        L_0x0007:
            r0 = r1
            goto L_0x0016
        L_0x0009:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch:{ JSONException -> 0x000f }
            r0.<init>(r11)     // Catch:{ JSONException -> 0x000f }
            goto L_0x0016
        L_0x000f:
            r11 = move-exception
            java.lang.String r0 = "JSON parsing error"
            com.google.android.gms.ads.internal.util.client.zzm.zzf(r0, r11)
            goto L_0x0007
        L_0x0016:
            if (r0 != 0) goto L_0x001b
            android.os.Bundle r10 = android.os.Bundle.EMPTY
            return r10
        L_0x001b:
            android.os.Bundle r11 = new android.os.Bundle
            r11.<init>()
            r2 = 0
            r3 = r2
        L_0x0022:
            int r4 = r0.length()
            if (r3 >= r4) goto L_0x00f1
            org.json.JSONObject r4 = r0.optJSONObject(r3)
            java.lang.String r5 = "bk"
            java.lang.String r5 = r4.optString(r5)
            java.lang.String r6 = "sk"
            java.lang.String r6 = r4.optString(r6)
            java.lang.String r7 = "type"
            r8 = -1
            int r4 = r4.optInt(r7, r8)
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x004d
            if (r4 == r8) goto L_0x004b
            if (r4 == r7) goto L_0x0049
            r4 = r2
            goto L_0x004e
        L_0x0049:
            r4 = 3
            goto L_0x004e
        L_0x004b:
            r4 = r7
            goto L_0x004e
        L_0x004d:
            r4 = r8
        L_0x004e:
            boolean r9 = android.text.TextUtils.isEmpty(r5)
            if (r9 != 0) goto L_0x00ed
            boolean r9 = android.text.TextUtils.isEmpty(r6)
            if (r9 != 0) goto L_0x00ed
            if (r4 != 0) goto L_0x005e
            goto L_0x00ed
        L_0x005e:
            r9 = 47
            com.google.android.gms.internal.ads.zzfxr r9 = com.google.android.gms.internal.ads.zzfxr.zzc(r9)
            com.google.android.gms.internal.ads.zzfyt r9 = com.google.android.gms.internal.ads.zzfyt.zzc(r9)
            java.util.List r6 = r9.zzf(r6)
            int r9 = r6.size()
            if (r9 > r7) goto L_0x00a3
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L_0x0079
            goto L_0x00a3
        L_0x0079:
            int r7 = r6.size()
            if (r7 != r8) goto L_0x008a
            android.content.SharedPreferences r7 = android.preference.PreferenceManager.getDefaultSharedPreferences(r10)
            java.lang.Object r6 = r6.get(r2)
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x009a
        L_0x008a:
            java.lang.Object r7 = r6.get(r2)
            java.lang.String r7 = (java.lang.String) r7
            android.content.SharedPreferences r7 = r10.getSharedPreferences(r7, r2)
            java.lang.Object r6 = r6.get(r8)
            java.lang.String r6 = (java.lang.String) r6
        L_0x009a:
            java.util.Map r7 = r7.getAll()
            java.lang.Object r6 = r7.get(r6)
            goto L_0x00a4
        L_0x00a3:
            r6 = r1
        L_0x00a4:
            if (r6 == 0) goto L_0x00ed
            int r4 = r4 + -1
            if (r4 == 0) goto L_0x00e4
            if (r4 == r8) goto L_0x00ba
            boolean r4 = r6 instanceof java.lang.Boolean
            if (r4 == 0) goto L_0x00ed
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r4 = r6.booleanValue()
            r11.putBoolean(r5, r4)
            goto L_0x00ed
        L_0x00ba:
            boolean r4 = r6 instanceof java.lang.Integer
            if (r4 == 0) goto L_0x00c8
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r4 = r6.intValue()
            r11.putInt(r5, r4)
            goto L_0x00ed
        L_0x00c8:
            boolean r4 = r6 instanceof java.lang.Long
            if (r4 == 0) goto L_0x00d6
            java.lang.Long r6 = (java.lang.Long) r6
            long r6 = r6.longValue()
            r11.putLong(r5, r6)
            goto L_0x00ed
        L_0x00d6:
            boolean r4 = r6 instanceof java.lang.Float
            if (r4 == 0) goto L_0x00ed
            java.lang.Float r6 = (java.lang.Float) r6
            float r4 = r6.floatValue()
            r11.putFloat(r5, r4)
            goto L_0x00ed
        L_0x00e4:
            boolean r4 = r6 instanceof java.lang.String
            if (r4 == 0) goto L_0x00ed
            java.lang.String r6 = (java.lang.String) r6
            r11.putString(r5, r6)
        L_0x00ed:
            int r3 = r3 + 1
            goto L_0x0022
        L_0x00f1:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzad.zzb(android.content.Context, java.lang.String):android.os.Bundle");
    }

    public static void zzc(Context context) {
        if (((Boolean) zzba.zzc().zza(zzbep.zzgs)).booleanValue() && context != null) {
            context.deleteDatabase("OfflineUpload.db");
        }
        try {
            zzfvg zzj = zzfvg.zzj(context);
            zzfvh zzi = zzfvh.zzi(context);
            zzj.zzk();
            zzj.zzl();
            zzi.zzj();
            if (((Boolean) zzba.zzc().zza(zzbep.zzcX)).booleanValue()) {
                zzi.zzk();
            }
            if (((Boolean) zzba.zzc().zza(zzbep.zzcY)).booleanValue()) {
                zzi.zzl();
            }
        } catch (IOException e) {
            zzu.zzo().zzw(e, "clearStorageOnIdlessMode");
        }
    }
}
