package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzzd extends zzza {
    private final boolean zze;
    private final zzys zzf;
    private final boolean zzg;
    private final boolean zzh;
    private final boolean zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final int zzm;
    private final boolean zzn;
    private final int zzo;
    private final boolean zzp;
    private final boolean zzq;
    private final int zzr;

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0031, code lost:
        if (r2 <= 2.14748365E9f) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0048, code lost:
        if (r1 >= 0) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0050, code lost:
        if (r1 >= 0) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x005d, code lost:
        if (r1 >= 0.0f) goto L_0x005f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0065, code lost:
        if (r11 >= 0) goto L_0x0067;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0147  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x00c9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzzd(int r5, com.google.android.gms.internal.ads.zzde r6, int r7, com.google.android.gms.internal.ads.zzys r8, int r9, int r10, boolean r11) {
        /*
            r4 = this;
            r4.<init>(r5, r6, r7)
            r4.zzf = r8
            boolean r5 = r8.zzK
            r6 = 1
            if (r6 == r5) goto L_0x000d
            r5 = 16
            goto L_0x000f
        L_0x000d:
            r5 = 24
        L_0x000f:
            boolean r7 = r8.zzJ
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            r10 = -1
            r0 = 0
            if (r11 == 0) goto L_0x003b
            com.google.android.gms.internal.ads.zzan r1 = r4.zzd
            int r2 = r1.zzs
            if (r2 == r10) goto L_0x001f
            int r2 = r8.zzd
        L_0x001f:
            int r2 = r1.zzt
            if (r2 == r10) goto L_0x0025
            int r2 = r8.zze
        L_0x0025:
            float r2 = r1.zzu
            int r3 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x0033
            int r3 = r8.zzf
            r3 = 1325400064(0x4f000000, float:2.14748365E9)
            int r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r2 > 0) goto L_0x003b
        L_0x0033:
            int r1 = r1.zzj
            if (r1 == r10) goto L_0x0039
            int r1 = r8.zzg
        L_0x0039:
            r1 = r6
            goto L_0x003c
        L_0x003b:
            r1 = r0
        L_0x003c:
            r4.zze = r1
            if (r11 == 0) goto L_0x0069
            com.google.android.gms.internal.ads.zzan r11 = r4.zzd
            int r1 = r11.zzs
            if (r1 == r10) goto L_0x004a
            int r2 = r8.zzh
            if (r1 < 0) goto L_0x0069
        L_0x004a:
            int r1 = r11.zzt
            if (r1 == r10) goto L_0x0052
            int r2 = r8.zzi
            if (r1 < 0) goto L_0x0069
        L_0x0052:
            float r1 = r11.zzu
            int r2 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r2 == 0) goto L_0x005f
            int r2 = r8.zzj
            r2 = 0
            int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r1 < 0) goto L_0x0069
        L_0x005f:
            int r11 = r11.zzj
            if (r11 == r10) goto L_0x0067
            int r1 = r8.zzk
            if (r11 < 0) goto L_0x0069
        L_0x0067:
            r11 = r6
            goto L_0x006a
        L_0x0069:
            r11 = r0
        L_0x006a:
            r4.zzg = r11
            boolean r11 = com.google.android.gms.internal.ads.zzze.zzo(r9, r0)
            r4.zzh = r11
            com.google.android.gms.internal.ads.zzan r11 = r4.zzd
            float r1 = r11.zzu
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0082
            r7 = 1092616192(0x41200000, float:10.0)
            int r7 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r7 < 0) goto L_0x0082
            r7 = r6
            goto L_0x0083
        L_0x0082:
            r7 = r0
        L_0x0083:
            r4.zzi = r7
            int r7 = r11.zzj
            r4.zzj = r7
            int r7 = r11.zza()
            r4.zzk = r7
            com.google.android.gms.internal.ads.zzan r7 = r4.zzd
            int r7 = r7.zzg
            int r11 = r8.zzp
            int r7 = com.google.android.gms.internal.ads.zzze.zzb(r7, r0)
            r4.zzm = r7
            com.google.android.gms.internal.ads.zzan r7 = r4.zzd
            int r7 = r7.zzg
            if (r7 == 0) goto L_0x00a7
            r7 = r7 & r6
            if (r7 == 0) goto L_0x00a5
            goto L_0x00a7
        L_0x00a5:
            r7 = r0
            goto L_0x00a8
        L_0x00a7:
            r7 = r6
        L_0x00a8:
            r4.zzn = r7
            r7 = r0
        L_0x00ab:
            com.google.android.gms.internal.ads.zzgbc r11 = r8.zzo
            int r11 = r11.size()
            if (r7 >= r11) goto L_0x00c9
            com.google.android.gms.internal.ads.zzan r11 = r4.zzd
            java.lang.String r11 = r11.zzn
            if (r11 == 0) goto L_0x00c6
            com.google.android.gms.internal.ads.zzgbc r1 = r8.zzo
            java.lang.Object r1 = r1.get(r7)
            boolean r11 = r11.equals(r1)
            if (r11 == 0) goto L_0x00c6
            goto L_0x00cc
        L_0x00c6:
            int r7 = r7 + 1
            goto L_0x00ab
        L_0x00c9:
            r7 = 2147483647(0x7fffffff, float:NaN)
        L_0x00cc:
            r4.zzl = r7
            r7 = r9 & 384(0x180, float:5.38E-43)
            r8 = 128(0x80, float:1.794E-43)
            if (r7 != r8) goto L_0x00d6
            r7 = r6
            goto L_0x00d7
        L_0x00d6:
            r7 = r0
        L_0x00d7:
            r4.zzp = r7
            r7 = r9 & 64
            r8 = 64
            if (r7 != r8) goto L_0x00e1
            r7 = r6
            goto L_0x00e2
        L_0x00e1:
            r7 = r0
        L_0x00e2:
            r4.zzq = r7
            com.google.android.gms.internal.ads.zzan r7 = r4.zzd
            java.lang.String r8 = r7.zzn
            r11 = 2
            if (r8 != 0) goto L_0x00ee
        L_0x00eb:
            r2 = r0
            goto L_0x013d
        L_0x00ee:
            int r1 = r8.hashCode()
            r2 = 4
            r3 = 3
            switch(r1) {
                case -1851077871: goto L_0x0120;
                case -1662735862: goto L_0x0116;
                case -1662541442: goto L_0x010c;
                case 1331836730: goto L_0x0102;
                case 1599127257: goto L_0x00f8;
                default: goto L_0x00f7;
            }
        L_0x00f7:
            goto L_0x012a
        L_0x00f8:
            java.lang.String r1 = "video/x-vnd.on2.vp9"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x012a
            r8 = r3
            goto L_0x012b
        L_0x0102:
            java.lang.String r1 = "video/avc"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x012a
            r8 = r2
            goto L_0x012b
        L_0x010c:
            java.lang.String r1 = "video/hevc"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x012a
            r8 = r11
            goto L_0x012b
        L_0x0116:
            java.lang.String r1 = "video/av01"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x012a
            r8 = r6
            goto L_0x012b
        L_0x0120:
            java.lang.String r1 = "video/dolby-vision"
            boolean r8 = r8.equals(r1)
            if (r8 == 0) goto L_0x012a
            r8 = r0
            goto L_0x012b
        L_0x012a:
            r8 = r10
        L_0x012b:
            if (r8 == 0) goto L_0x013c
            if (r8 == r6) goto L_0x013d
            if (r8 == r11) goto L_0x013a
            if (r8 == r3) goto L_0x0138
            if (r8 == r2) goto L_0x0136
            goto L_0x00eb
        L_0x0136:
            r2 = r6
            goto L_0x013d
        L_0x0138:
            r2 = r11
            goto L_0x013d
        L_0x013a:
            r2 = r3
            goto L_0x013d
        L_0x013c:
            r2 = 5
        L_0x013d:
            r4.zzr = r2
            int r8 = r7.zzg
            r8 = r8 & 16384(0x4000, float:2.2959E-41)
            if (r8 == 0) goto L_0x0147
        L_0x0145:
            r6 = r0
            goto L_0x0173
        L_0x0147:
            com.google.android.gms.internal.ads.zzys r8 = r4.zzf
            boolean r1 = r8.zzT
            boolean r1 = com.google.android.gms.internal.ads.zzze.zzo(r9, r1)
            if (r1 != 0) goto L_0x0152
            goto L_0x0145
        L_0x0152:
            boolean r1 = r4.zze
            if (r1 != 0) goto L_0x015b
            boolean r2 = r8.zzI
            if (r2 != 0) goto L_0x015b
            goto L_0x0145
        L_0x015b:
            boolean r0 = com.google.android.gms.internal.ads.zzze.zzo(r9, r0)
            if (r0 == 0) goto L_0x0173
            boolean r0 = r4.zzg
            if (r0 == 0) goto L_0x0173
            if (r1 == 0) goto L_0x0173
            int r7 = r7.zzj
            if (r7 == r10) goto L_0x0173
            boolean r7 = r8.zzC
            boolean r7 = r8.zzB
            r5 = r5 & r9
            if (r5 == 0) goto L_0x0173
            r6 = r11
        L_0x0173:
            r4.zzo = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzzd.<init>(int, com.google.android.gms.internal.ads.zzde, int, com.google.android.gms.internal.ads.zzys, int, int, boolean):void");
    }

    public static /* synthetic */ int zza(zzzd zzzd, zzzd zzzd2) {
        zzgcn zzgcn;
        if (!zzzd.zze || !zzzd.zzh) {
            zzgcn = zzze.zzb.zza();
        } else {
            zzgcn = zzze.zzb;
        }
        zzgar zzk2 = zzgar.zzk();
        boolean z = zzzd.zzf.zzB;
        return zzk2.zzd(Integer.valueOf(zzzd.zzk), Integer.valueOf(zzzd2.zzk), zzgcn).zzd(Integer.valueOf(zzzd.zzj), Integer.valueOf(zzzd2.zzj), zzgcn).zza();
    }

    public static /* synthetic */ int zzd(zzzd zzzd, zzzd zzzd2) {
        zzgar zzd = zzgar.zzk().zze(zzzd.zzh, zzzd2.zzh).zzb(zzzd.zzm, zzzd2.zzm).zze(zzzd.zzn, zzzd2.zzn).zze(zzzd.zzi, zzzd2.zzi).zze(zzzd.zze, zzzd2.zze).zze(zzzd.zzg, zzzd2.zzg).zzd(Integer.valueOf(zzzd.zzl), Integer.valueOf(zzzd2.zzl), zzgcn.zzc().zza());
        boolean z = zzzd.zzp;
        zzgar zze2 = zzd.zze(z, zzzd2.zzp);
        boolean z2 = zzzd.zzq;
        zzgar zze3 = zze2.zze(z2, zzzd2.zzq);
        if (z && z2) {
            zze3 = zze3.zzb(zzzd.zzr, zzzd2.zzr);
        }
        return zze3.zza();
    }

    public final int zzb() {
        return this.zzo;
    }

    public final /* bridge */ /* synthetic */ boolean zzc(zzza zzza) {
        zzzd zzzd = (zzzd) zzza;
        if (!zzgd.zzG(this.zzd.zzn, zzzd.zzd.zzn)) {
            return false;
        }
        boolean z = this.zzf.zzL;
        return this.zzp == zzzd.zzp && this.zzq == zzzd.zzq;
    }
}
