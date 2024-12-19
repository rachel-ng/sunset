package com.google.android.gms.internal.consent_sdk;

import android.app.Application;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzan implements zzd {
    private final Application zza;
    private final zzap zzb;
    private final Executor zzc;

    public zzan(Application application, zzap zzap, Executor executor) {
        this.zza = application;
        this.zzb = zzap;
        this.zzc = executor;
    }

    public final Executor zza() {
        return this.zzc;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzb(java.lang.String r9, org.json.JSONObject r10) {
        /*
            r8 = this;
            int r0 = r9.hashCode()
            r1 = 94746189(0x5a5b64d, float:1.5583492E-35)
            r2 = 0
            r3 = 1
            if (r0 == r1) goto L_0x001b
            r1 = 113399775(0x6c257df, float:7.3103804E-35)
            if (r0 == r1) goto L_0x0011
            goto L_0x0025
        L_0x0011:
            java.lang.String r0 = "write"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0025
            r9 = r2
            goto L_0x0026
        L_0x001b:
            java.lang.String r0 = "clear"
            boolean r9 = r9.equals(r0)
            if (r9 == 0) goto L_0x0025
            r9 = r3
            goto L_0x0026
        L_0x0025:
            r9 = -1
        L_0x0026:
            java.lang.String r0 = "UserMessagingPlatform"
            if (r9 == 0) goto L_0x0081
            if (r9 == r3) goto L_0x002d
            return r2
        L_0x002d:
            java.lang.String r9 = "keys"
            org.json.JSONArray r9 = r10.optJSONArray(r9)
            if (r9 == 0) goto L_0x006f
            int r1 = r9.length()
            if (r1 != 0) goto L_0x003c
            goto L_0x006f
        L_0x003c:
            java.util.HashSet r10 = new java.util.HashSet
            r10.<init>()
            int r1 = r9.length()
        L_0x0045:
            if (r2 >= r1) goto L_0x0069
            java.lang.String r4 = r9.optString(r2)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0063
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Action[clear]: empty key at index: "
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r0, r4)
            goto L_0x0066
        L_0x0063:
            r10.add(r4)
        L_0x0066:
            int r2 = r2 + 1
            goto L_0x0045
        L_0x0069:
            android.app.Application r9 = r8.zza
            com.google.android.gms.internal.consent_sdk.zzco.zzb(r9, r10)
            goto L_0x0080
        L_0x006f:
            java.lang.String r9 = r10.toString()
            java.lang.String r9 = java.lang.String.valueOf(r9)
            java.lang.String r10 = "Action[clear]: wrong args."
            java.lang.String r9 = r10.concat(r9)
            android.util.Log.d(r0, r9)
        L_0x0080:
            return r3
        L_0x0081:
            android.app.Application r9 = r8.zza
            com.google.android.gms.internal.consent_sdk.zzcn r1 = new com.google.android.gms.internal.consent_sdk.zzcn
            r1.<init>(r9)
            java.util.Iterator r9 = r10.keys()
        L_0x008c:
            boolean r2 = r9.hasNext()
            if (r2 == 0) goto L_0x00d7
            java.lang.Object r2 = r9.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r10.opt(r2)
            java.lang.String r5 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Writing to storage: ["
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r7 = "] "
            r6.append(r7)
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            android.util.Log.d(r0, r5)
            boolean r4 = r1.zzc(r2, r4)
            if (r4 == 0) goto L_0x00c9
            com.google.android.gms.internal.consent_sdk.zzap r4 = r8.zzb
            java.util.Set r4 = r4.zzd()
            r4.add(r2)
            goto L_0x008c
        L_0x00c9:
            java.lang.String r2 = java.lang.String.valueOf(r2)
            java.lang.String r4 = "Failed writing key: "
            java.lang.String r2 = r4.concat(r2)
            android.util.Log.d(r0, r2)
            goto L_0x008c
        L_0x00d7:
            com.google.android.gms.internal.consent_sdk.zzap r9 = r8.zzb
            r9.zzf()
            r1.zzb()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.consent_sdk.zzan.zzb(java.lang.String, org.json.JSONObject):boolean");
    }
}
