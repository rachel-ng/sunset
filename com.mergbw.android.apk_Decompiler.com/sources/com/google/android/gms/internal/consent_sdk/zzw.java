package com.google.android.gms.internal.consent_sdk;

import com.google.android.ump.ConsentInformation;

/* compiled from: com.google.android.ump:user-messaging-platform@@2.2.0 */
final class zzw {
    private final zzx zza;
    private final zzck zzb;
    private int zzc = 0;
    private ConsentInformation.PrivacyOptionsRequirementStatus zzd = ConsentInformation.PrivacyOptionsRequirementStatus.UNKNOWN;

    zzw(zzx zzx, zzck zzck) {
        this.zza = zzx;
        this.zzb = zzck;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0083 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.consent_sdk.zzz zza() throws com.google.android.gms.internal.consent_sdk.zzg {
        /*
            r11 = this;
            com.google.android.gms.internal.consent_sdk.zzck r0 = r11.zzb
            int r1 = r0.zzf
            int r2 = r1 + -1
            r3 = 0
            if (r1 == 0) goto L_0x00c6
            java.lang.String r1 = "Invalid response from server."
            r4 = 2
            r5 = 3
            r6 = 1
            switch(r2) {
                case 1: goto L_0x0041;
                case 2: goto L_0x0041;
                case 3: goto L_0x0041;
                case 4: goto L_0x003e;
                case 5: goto L_0x003b;
                case 6: goto L_0x0029;
                case 7: goto L_0x0017;
                default: goto L_0x0011;
            }
        L_0x0011:
            com.google.android.gms.internal.consent_sdk.zzg r0 = new com.google.android.gms.internal.consent_sdk.zzg
            r0.<init>(r6, r1)
            throw r0
        L_0x0017:
            com.google.android.gms.internal.consent_sdk.zzg r1 = new com.google.android.gms.internal.consent_sdk.zzg
            java.lang.String r0 = r0.zzc
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "Publisher misconfiguration: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r5, r0)
            throw r1
        L_0x0029:
            com.google.android.gms.internal.consent_sdk.zzg r1 = new com.google.android.gms.internal.consent_sdk.zzg
            java.lang.String r0 = r0.zzc
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r2 = "Invalid response from server: "
            java.lang.String r0 = r2.concat(r0)
            r1.<init>(r6, r0)
            throw r1
        L_0x003b:
            r11.zzc = r6
            goto L_0x0043
        L_0x003e:
            r11.zzc = r4
            goto L_0x0043
        L_0x0041:
            r11.zzc = r5
        L_0x0043:
            int r0 = r0.zzg
            int r2 = r0 + -1
            if (r0 == 0) goto L_0x00c5
            if (r2 == r6) goto L_0x0058
            if (r2 != r4) goto L_0x0052
            com.google.android.ump.ConsentInformation$PrivacyOptionsRequirementStatus r0 = com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus.NOT_REQUIRED
            r11.zzd = r0
            goto L_0x005c
        L_0x0052:
            com.google.android.gms.internal.consent_sdk.zzg r0 = new com.google.android.gms.internal.consent_sdk.zzg
            r0.<init>(r6, r1)
            throw r0
        L_0x0058:
            com.google.android.ump.ConsentInformation$PrivacyOptionsRequirementStatus r0 = com.google.android.ump.ConsentInformation.PrivacyOptionsRequirementStatus.REQUIRED
            r11.zzd = r0
        L_0x005c:
            com.google.android.gms.internal.consent_sdk.zzck r0 = r11.zzb
            java.lang.String r1 = r0.zza
            if (r1 != 0) goto L_0x0064
            r2 = r3
            goto L_0x006b
        L_0x0064:
            com.google.android.gms.internal.consent_sdk.zzbp r2 = new com.google.android.gms.internal.consent_sdk.zzbp
            java.lang.String r5 = r0.zzb
            r2.<init>(r5, r1)
        L_0x006b:
            com.google.android.gms.internal.consent_sdk.zzx r1 = r11.zza
            com.google.android.gms.internal.consent_sdk.zzap r1 = r1.zzc
            java.util.HashSet r5 = new java.util.HashSet
            java.util.List r0 = r0.zzd
            r5.<init>(r0)
            r1.zzi(r5)
            com.google.android.gms.internal.consent_sdk.zzck r0 = r11.zzb
            java.util.List r0 = r0.zze
            java.util.Iterator r0 = r0.iterator()
        L_0x0083:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00bb
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.consent_sdk.zzcj r1 = (com.google.android.gms.internal.consent_sdk.zzcj) r1
            int r5 = r1.zzb
            int r7 = r5 + -1
            if (r5 == 0) goto L_0x00ba
            if (r7 == 0) goto L_0x00a2
            if (r7 == r6) goto L_0x009f
            if (r7 == r4) goto L_0x009c
            goto L_0x00a2
        L_0x009c:
            java.lang.String r5 = "clear"
            goto L_0x00a3
        L_0x009f:
            java.lang.String r5 = "write"
            goto L_0x00a3
        L_0x00a2:
            r5 = r3
        L_0x00a3:
            if (r5 == 0) goto L_0x0083
            com.google.android.gms.internal.consent_sdk.zzx r7 = r11.zza
            com.google.android.gms.internal.consent_sdk.zze r8 = r7.zza
            java.lang.String r1 = r1.zza
            com.google.android.gms.internal.consent_sdk.zzd[] r9 = new com.google.android.gms.internal.consent_sdk.zzd[r6]
            r10 = 0
            com.google.android.gms.internal.consent_sdk.zzan r7 = r7.zzb
            r9[r10] = r7
            r8.zzb(r5, r1, r9)
            goto L_0x0083
        L_0x00ba:
            throw r3
        L_0x00bb:
            com.google.android.gms.internal.consent_sdk.zzz r0 = new com.google.android.gms.internal.consent_sdk.zzz
            int r1 = r11.zzc
            com.google.android.ump.ConsentInformation$PrivacyOptionsRequirementStatus r4 = r11.zzd
            r0.<init>(r1, r4, r2, r3)
            return r0
        L_0x00c5:
            throw r3
        L_0x00c6:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.consent_sdk.zzw.zza():com.google.android.gms.internal.consent_sdk.zzz");
    }
}
