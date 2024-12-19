package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzank {
    public long zza = 0;
    public long zzb = 0;
    public CharSequence zzc;
    public int zzd = 2;
    public float zze = -3.4028235E38f;
    public int zzf = 1;
    public int zzg = 0;
    public float zzh = -3.4028235E38f;
    public int zzi = Integer.MIN_VALUE;
    public float zzj = 1.0f;
    public int zzk = Integer.MIN_VALUE;

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x007b, code lost:
        if (r6 == 0) goto L_0x007d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007f  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzeg zza() {
        /*
            r13 = this;
            float r0 = r13.zzh
            r1 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            r3 = 1056964608(0x3f000000, float:0.5)
            r4 = 0
            r5 = 5
            r6 = 4
            r7 = 1065353216(0x3f800000, float:1.0)
            if (r2 == 0) goto L_0x0011
            goto L_0x001c
        L_0x0011:
            int r0 = r13.zzd
            if (r0 == r6) goto L_0x001b
            if (r0 == r5) goto L_0x0019
            r0 = r3
            goto L_0x001c
        L_0x0019:
            r0 = r7
            goto L_0x001c
        L_0x001b:
            r0 = r4
        L_0x001c:
            int r2 = r13.zzi
            r8 = -2147483648(0xffffffff80000000, float:-0.0)
            r9 = 3
            r10 = 2
            r11 = 1
            if (r2 == r8) goto L_0x0026
            goto L_0x0035
        L_0x0026:
            int r2 = r13.zzd
            if (r2 == r11) goto L_0x0034
            if (r2 == r9) goto L_0x0032
            if (r2 == r6) goto L_0x0034
            if (r2 == r5) goto L_0x0032
            r2 = r11
            goto L_0x0035
        L_0x0032:
            r2 = r10
            goto L_0x0035
        L_0x0034:
            r2 = 0
        L_0x0035:
            com.google.android.gms.internal.ads.zzeg r8 = new com.google.android.gms.internal.ads.zzeg
            r8.<init>()
            int r12 = r13.zzd
            if (r12 == r11) goto L_0x0061
            if (r12 == r10) goto L_0x005e
            if (r12 == r9) goto L_0x005b
            if (r12 == r6) goto L_0x0061
            if (r12 == r5) goto L_0x005b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Unknown textAlignment: "
            r5.<init>(r6)
            r5.append(r12)
            java.lang.String r5 = r5.toString()
            java.lang.String r6 = "WebvttCueParser"
            com.google.android.gms.internal.ads.zzfk.zzf(r6, r5)
            r5 = 0
            goto L_0x0063
        L_0x005b:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            goto L_0x0063
        L_0x005e:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_CENTER
            goto L_0x0063
        L_0x0061:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_NORMAL
        L_0x0063:
            r8.zzm(r5)
            float r5 = r13.zze
            int r6 = r13.zzf
            int r9 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r9 == 0) goto L_0x0079
            if (r6 != 0) goto L_0x0079
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 < 0) goto L_0x007d
            int r4 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r4 <= 0) goto L_0x0079
            goto L_0x007d
        L_0x0079:
            if (r9 != 0) goto L_0x007f
            if (r6 != 0) goto L_0x0080
        L_0x007d:
            r1 = r7
            goto L_0x0080
        L_0x007f:
            r1 = r5
        L_0x0080:
            r8.zze(r1, r6)
            int r1 = r13.zzg
            r8.zzf(r1)
            r8.zzh(r0)
            r8.zzi(r2)
            float r1 = r13.zzj
            if (r2 == 0) goto L_0x00ab
            if (r2 == r11) goto L_0x00a1
            if (r2 != r10) goto L_0x0097
            goto L_0x00ad
        L_0x0097:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = java.lang.String.valueOf(r2)
            r0.<init>(r1)
            throw r0
        L_0x00a1:
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x00a7
            float r0 = r0 + r0
            goto L_0x00ad
        L_0x00a7:
            float r7 = r7 - r0
            float r0 = r7 + r7
            goto L_0x00ad
        L_0x00ab:
            float r0 = r7 - r0
        L_0x00ad:
            float r0 = java.lang.Math.min(r1, r0)
            r8.zzk(r0)
            int r0 = r13.zzk
            r8.zzo(r0)
            java.lang.CharSequence r0 = r13.zzc
            if (r0 == 0) goto L_0x00c0
            r8.zzl(r0)
        L_0x00c0:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzank.zza():com.google.android.gms.internal.ads.zzeg");
    }
}
