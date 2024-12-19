package com.google.android.gms.internal.consent_sdk;

import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
public final class zzck {
    public String zza;
    public String zzb;
    public String zzc;
    public List zzd = Collections.emptyList();
    public List zze = Collections.emptyList();
    public int zzf = 1;
    public int zzg = 1;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x01fa, code lost:
        if (r1.equals("CONSENT_SIGNAL_UNKNOWN") != false) goto L_0x0212;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008e, code lost:
        if (r1.equals("UNKNOWN") == false) goto L_0x00a5;
     */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.consent_sdk.zzck zza(android.util.JsonReader r12) throws java.io.IOException {
        /*
            com.google.android.gms.internal.consent_sdk.zzck r0 = new com.google.android.gms.internal.consent_sdk.zzck
            r0.<init>()
            r12.beginObject()
        L_0x0008:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0237
            java.lang.String r1 = r12.nextName()
            int r2 = r1.hashCode()
            r3 = 4
            r4 = 5
            r5 = 6
            r6 = 3
            r7 = 0
            r8 = -1
            r9 = 2
            r10 = 1
            switch(r2) {
                case -2001388947: goto L_0x005e;
                case -1938755376: goto L_0x0054;
                case -1851537225: goto L_0x004a;
                case -1324537865: goto L_0x0040;
                case -1161803523: goto L_0x0036;
                case -986806987: goto L_0x002c;
                case -790907624: goto L_0x0022;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x0068
        L_0x0022:
            java.lang.String r2 = "consent_form_payload"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r10
            goto L_0x0069
        L_0x002c:
            java.lang.String r2 = "request_info_keys"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r3
            goto L_0x0069
        L_0x0036:
            java.lang.String r2 = "actions"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r4
            goto L_0x0069
        L_0x0040:
            java.lang.String r2 = "privacy_options_required"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r5
            goto L_0x0069
        L_0x004a:
            java.lang.String r2 = "consent_form_base_url"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r9
            goto L_0x0069
        L_0x0054:
            java.lang.String r2 = "error_message"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r6
            goto L_0x0069
        L_0x005e:
            java.lang.String r2 = "consent_signal"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0068
            r1 = r7
            goto L_0x0069
        L_0x0068:
            r1 = r8
        L_0x0069:
            switch(r1) {
                case 0: goto L_0x01b5;
                case 1: goto L_0x01ad;
                case 2: goto L_0x01a5;
                case 3: goto L_0x019d;
                case 4: goto L_0x017e;
                case 5: goto L_0x00c4;
                case 6: goto L_0x0070;
                default: goto L_0x006c;
            }
        L_0x006c:
            r12.skipValue()
            goto L_0x0008
        L_0x0070:
            java.lang.String r1 = r12.nextString()
            int r2 = r1.hashCode()
            r3 = -1888946261(0xffffffff8f68f7ab, float:-1.1486182E-29)
            if (r2 == r3) goto L_0x009b
            r3 = 389487519(0x17371b9f, float:5.916535E-25)
            if (r2 == r3) goto L_0x0091
            r3 = 433141802(0x19d1382a, float:2.1632778E-23)
            if (r2 == r3) goto L_0x0088
            goto L_0x00a5
        L_0x0088:
            java.lang.String r2 = "UNKNOWN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00a5
            goto L_0x00a6
        L_0x0091:
            java.lang.String r2 = "REQUIRED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00a5
            r7 = r10
            goto L_0x00a6
        L_0x009b:
            java.lang.String r2 = "NOT_REQUIRED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x00a5
            r7 = r9
            goto L_0x00a6
        L_0x00a5:
            r7 = r8
        L_0x00a6:
            if (r7 == 0) goto L_0x00bf
            if (r7 == r10) goto L_0x00bd
            if (r7 != r9) goto L_0x00ad
            goto L_0x00c0
        L_0x00ad:
            java.lang.String r12 = java.lang.String.valueOf(r1)
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to parse contentads.contributor.direct.serving.appswitchboard.proto.ApplicationGdprResponse.PrivacyOptionsRequirementStatusfrom: "
            java.lang.String r12 = r1.concat(r12)
            r0.<init>(r12)
            throw r0
        L_0x00bd:
            r6 = r9
            goto L_0x00c0
        L_0x00bf:
            r6 = r10
        L_0x00c0:
            r0.zzg = r6
            goto L_0x0008
        L_0x00c4:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.zze = r1
            r12.beginArray()
        L_0x00ce:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0179
            com.google.android.gms.internal.consent_sdk.zzcj r1 = new com.google.android.gms.internal.consent_sdk.zzcj
            r1.<init>()
            r12.beginObject()
        L_0x00dc:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x016f
            java.lang.String r2 = r12.nextName()
            int r3 = r2.hashCode()
            r4 = -2105551094(0xffffffff827fd70a, float:-1.8796154E-37)
            if (r3 == r4) goto L_0x00ff
            r4 = 1583758243(0x5e663ba3, float:4.14750822E18)
            if (r3 == r4) goto L_0x00f5
            goto L_0x0109
        L_0x00f5:
            java.lang.String r3 = "action_type"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0109
            r2 = r7
            goto L_0x010a
        L_0x00ff:
            java.lang.String r3 = "args_json"
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0109
            r2 = r10
            goto L_0x010a
        L_0x0109:
            r2 = r8
        L_0x010a:
            if (r2 == 0) goto L_0x0119
            if (r2 == r10) goto L_0x0112
            r12.skipValue()
            goto L_0x00dc
        L_0x0112:
            java.lang.String r2 = r12.nextString()
            r1.zza = r2
            goto L_0x00dc
        L_0x0119:
            java.lang.String r2 = r12.nextString()
            int r3 = r2.hashCode()
            r4 = 64208429(0x3d3be2d, float:1.2445128E-36)
            if (r3 == r4) goto L_0x0145
            r4 = 82862015(0x4f05fbf, float:5.6511658E-36)
            if (r3 == r4) goto L_0x013b
            r4 = 1856333582(0x6ea5670e, float:2.5594806E28)
            if (r3 == r4) goto L_0x0131
            goto L_0x014f
        L_0x0131:
            java.lang.String r3 = "UNKNOWN_ACTION_TYPE"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x014f
            r3 = r7
            goto L_0x0150
        L_0x013b:
            java.lang.String r3 = "WRITE"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x014f
            r3 = r10
            goto L_0x0150
        L_0x0145:
            java.lang.String r3 = "CLEAR"
            boolean r3 = r2.equals(r3)
            if (r3 == 0) goto L_0x014f
            r3 = r9
            goto L_0x0150
        L_0x014f:
            r3 = r8
        L_0x0150:
            if (r3 == 0) goto L_0x016a
            if (r3 == r10) goto L_0x0168
            if (r3 != r9) goto L_0x0158
            r2 = r6
            goto L_0x016b
        L_0x0158:
            java.lang.String r12 = java.lang.String.valueOf(r2)
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to parse contentads.contributor.direct.serving.appswitchboard.proto.ApplicationGdprResponse.Action.ActionTypefrom: "
            java.lang.String r12 = r1.concat(r12)
            r0.<init>(r12)
            throw r0
        L_0x0168:
            r2 = r9
            goto L_0x016b
        L_0x016a:
            r2 = r10
        L_0x016b:
            r1.zzb = r2
            goto L_0x00dc
        L_0x016f:
            r12.endObject()
            java.util.List r2 = r0.zze
            r2.add(r1)
            goto L_0x00ce
        L_0x0179:
            r12.endArray()
            goto L_0x0008
        L_0x017e:
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r0.zzd = r1
            r12.beginArray()
        L_0x0188:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L_0x0198
            java.lang.String r1 = r12.nextString()
            java.util.List r2 = r0.zzd
            r2.add(r1)
            goto L_0x0188
        L_0x0198:
            r12.endArray()
            goto L_0x0008
        L_0x019d:
            java.lang.String r1 = r12.nextString()
            r0.zzc = r1
            goto L_0x0008
        L_0x01a5:
            java.lang.String r1 = r12.nextString()
            r0.zzb = r1
            goto L_0x0008
        L_0x01ad:
            java.lang.String r1 = r12.nextString()
            r0.zza = r1
            goto L_0x0008
        L_0x01b5:
            java.lang.String r1 = r12.nextString()
            int r2 = r1.hashCode()
            r11 = 7
            switch(r2) {
                case -2058725357: goto L_0x0207;
                case -1969035850: goto L_0x01fd;
                case -1263695752: goto L_0x01f4;
                case -954325659: goto L_0x01ea;
                case -918677260: goto L_0x01e0;
                case 429411856: goto L_0x01d6;
                case 467888915: goto L_0x01cc;
                case 1725474845: goto L_0x01c2;
                default: goto L_0x01c1;
            }
        L_0x01c1:
            goto L_0x0211
        L_0x01c2:
            java.lang.String r2 = "CONSENT_SIGNAL_NOT_REQUIRED"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r4
            goto L_0x0212
        L_0x01cc:
            java.lang.String r2 = "CONSENT_SIGNAL_PERSONALIZED_ADS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r10
            goto L_0x0212
        L_0x01d6:
            java.lang.String r2 = "CONSENT_SIGNAL_SUFFICIENT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r6
            goto L_0x0212
        L_0x01e0:
            java.lang.String r2 = "CONSENT_SIGNAL_PUBLISHER_MISCONFIGURATION"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r11
            goto L_0x0212
        L_0x01ea:
            java.lang.String r2 = "CONSENT_SIGNAL_NON_PERSONALIZED_ADS"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r9
            goto L_0x0212
        L_0x01f4:
            java.lang.String r2 = "CONSENT_SIGNAL_UNKNOWN"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            goto L_0x0212
        L_0x01fd:
            java.lang.String r2 = "CONSENT_SIGNAL_ERROR"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r5
            goto L_0x0212
        L_0x0207:
            java.lang.String r2 = "CONSENT_SIGNAL_COLLECT_CONSENT"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0211
            r7 = r3
            goto L_0x0212
        L_0x0211:
            r7 = r8
        L_0x0212:
            switch(r7) {
                case 0: goto L_0x0232;
                case 1: goto L_0x0230;
                case 2: goto L_0x022e;
                case 3: goto L_0x0233;
                case 4: goto L_0x022c;
                case 5: goto L_0x022a;
                case 6: goto L_0x0228;
                case 7: goto L_0x0225;
                default: goto L_0x0215;
            }
        L_0x0215:
            java.lang.String r12 = java.lang.String.valueOf(r1)
            java.io.IOException r0 = new java.io.IOException
            java.lang.String r1 = "Failed to parse contentads.contributor.direct.serving.appswitchboard.proto.ApplicationGdprResponse.ConsentSignalfrom: "
            java.lang.String r12 = r1.concat(r12)
            r0.<init>(r12)
            throw r0
        L_0x0225:
            r3 = 8
            goto L_0x0233
        L_0x0228:
            r3 = r11
            goto L_0x0233
        L_0x022a:
            r3 = r5
            goto L_0x0233
        L_0x022c:
            r3 = r4
            goto L_0x0233
        L_0x022e:
            r3 = r6
            goto L_0x0233
        L_0x0230:
            r3 = r9
            goto L_0x0233
        L_0x0232:
            r3 = r10
        L_0x0233:
            r0.zzf = r3
            goto L_0x0008
        L_0x0237:
            r12.endObject()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.consent_sdk.zzck.zza(android.util.JsonReader):com.google.android.gms.internal.consent_sdk.zzck");
    }
}
