package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzamk {
    public final int zza;
    public final int zzb;
    public final int zzc;
    public final int zzd;
    public final int zze;

    private zzamk(int i, int i2, int i3, int i4, int i5) {
        this.zza = i;
        this.zzb = i2;
        this.zzc = i3;
        this.zzd = i4;
        this.zze = i5;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzamk zza(java.lang.String r12) {
        /*
            java.lang.String r0 = "Format:"
            boolean r0 = r12.startsWith(r0)
            com.google.android.gms.internal.ads.zzeq.zzd(r0)
            r0 = 7
            java.lang.String r12 = r12.substring(r0)
            java.lang.String r0 = ","
            java.lang.String[] r12 = android.text.TextUtils.split(r12, r0)
            r0 = 0
            r1 = -1
            r2 = r0
            r4 = r1
            r5 = r4
            r6 = r5
            r7 = r6
        L_0x001b:
            int r8 = r12.length
            if (r2 >= r8) goto L_0x006f
            r3 = r12[r2]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.android.gms.internal.ads.zzfxm.zza(r3)
            int r8 = r3.hashCode()
            r9 = 3
            r10 = 2
            r11 = 1
            switch(r8) {
                case 100571: goto L_0x0051;
                case 3556653: goto L_0x0047;
                case 109757538: goto L_0x003d;
                case 109780401: goto L_0x0033;
                default: goto L_0x0032;
            }
        L_0x0032:
            goto L_0x005b
        L_0x0033:
            java.lang.String r8 = "style"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x005b
            r3 = r10
            goto L_0x005c
        L_0x003d:
            java.lang.String r8 = "start"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x005b
            r3 = r0
            goto L_0x005c
        L_0x0047:
            java.lang.String r8 = "text"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x005b
            r3 = r9
            goto L_0x005c
        L_0x0051:
            java.lang.String r8 = "end"
            boolean r3 = r3.equals(r8)
            if (r3 == 0) goto L_0x005b
            r3 = r11
            goto L_0x005c
        L_0x005b:
            r3 = r1
        L_0x005c:
            if (r3 == 0) goto L_0x006b
            if (r3 == r11) goto L_0x0069
            if (r3 == r10) goto L_0x0067
            if (r3 == r9) goto L_0x0065
            goto L_0x006c
        L_0x0065:
            r7 = r2
            goto L_0x006c
        L_0x0067:
            r6 = r2
            goto L_0x006c
        L_0x0069:
            r5 = r2
            goto L_0x006c
        L_0x006b:
            r4 = r2
        L_0x006c:
            int r2 = r2 + 1
            goto L_0x001b
        L_0x006f:
            if (r4 == r1) goto L_0x007c
            if (r5 == r1) goto L_0x007c
            if (r7 == r1) goto L_0x007c
            com.google.android.gms.internal.ads.zzamk r12 = new com.google.android.gms.internal.ads.zzamk
            r3 = r12
            r3.<init>(r4, r5, r6, r7, r8)
            return r12
        L_0x007c:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamk.zza(java.lang.String):com.google.android.gms.internal.ads.zzamk");
    }
}
