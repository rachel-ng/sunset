package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzafr implements zzafj {
    public final zzgbc zza;
    private final int zzb;

    private zzafr(int i, zzgbc zzgbc) {
        this.zzb = i;
        this.zza = zzgbc;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzafr zzc(int r16, com.google.android.gms.internal.ads.zzfu r17) {
        /*
            r0 = r17
            com.google.android.gms.internal.ads.zzgaz r1 = new com.google.android.gms.internal.ads.zzgaz
            r1.<init>()
            int r2 = r17.zze()
            r3 = -2
        L_0x000c:
            int r4 = r17.zzb()
            r5 = 8
            if (r4 <= r5) goto L_0x0186
            int r4 = r17.zzi()
            int r5 = r17.zzi()
            int r6 = r17.zzd()
            int r6 = r6 + r5
            r0.zzJ(r6)
            r5 = 1414744396(0x5453494c, float:3.62987127E12)
            r7 = 2
            r8 = 1
            if (r4 != r5) goto L_0x0035
            int r4 = r17.zzi()
            com.google.android.gms.internal.ads.zzafr r4 = zzc(r4, r0)
            goto L_0x0142
        L_0x0035:
            r5 = 0
            switch(r4) {
                case 1718776947: goto L_0x004e;
                case 1751742049: goto L_0x0048;
                case 1752331379: goto L_0x0042;
                case 1852994675: goto L_0x003c;
                default: goto L_0x0039;
            }
        L_0x0039:
            r4 = r5
            goto L_0x0142
        L_0x003c:
            com.google.android.gms.internal.ads.zzaft r4 = com.google.android.gms.internal.ads.zzaft.zzb(r17)
            goto L_0x0142
        L_0x0042:
            com.google.android.gms.internal.ads.zzafp r4 = com.google.android.gms.internal.ads.zzafp.zzb(r17)
            goto L_0x0142
        L_0x0048:
            com.google.android.gms.internal.ads.zzafo r4 = com.google.android.gms.internal.ads.zzafo.zzb(r17)
            goto L_0x0142
        L_0x004e:
            java.lang.String r4 = "StreamFormatChunk"
            if (r3 != r7) goto L_0x00a4
            r9 = 4
            r0.zzL(r9)
            int r10 = r17.zzi()
            int r11 = r17.zzi()
            r0.zzL(r9)
            int r9 = r17.zzi()
            switch(r9) {
                case 808802372: goto L_0x0076;
                case 826496577: goto L_0x0073;
                case 828601953: goto L_0x0073;
                case 842289229: goto L_0x0070;
                case 859066445: goto L_0x006d;
                case 875967048: goto L_0x0073;
                case 877677894: goto L_0x0076;
                case 1145656883: goto L_0x0076;
                case 1145656920: goto L_0x0076;
                case 1196444237: goto L_0x006a;
                case 1482049860: goto L_0x0076;
                case 1684633208: goto L_0x0076;
                case 1735420525: goto L_0x006a;
                case 2021026148: goto L_0x0076;
                default: goto L_0x0068;
            }
        L_0x0068:
            r12 = r5
            goto L_0x0078
        L_0x006a:
            java.lang.String r12 = "video/mjpeg"
            goto L_0x0078
        L_0x006d:
            java.lang.String r12 = "video/mp43"
            goto L_0x0078
        L_0x0070:
            java.lang.String r12 = "video/mp42"
            goto L_0x0078
        L_0x0073:
            java.lang.String r12 = "video/avc"
            goto L_0x0078
        L_0x0076:
            java.lang.String r12 = "video/mp4v-es"
        L_0x0078:
            if (r12 != 0) goto L_0x008c
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Ignoring track with unsupported compression "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r9)
            goto L_0x0039
        L_0x008c:
            com.google.android.gms.internal.ads.zzal r4 = new com.google.android.gms.internal.ads.zzal
            r4.<init>()
            r4.zzac(r10)
            r4.zzI(r11)
            r4.zzX(r12)
            com.google.android.gms.internal.ads.zzafs r5 = new com.google.android.gms.internal.ads.zzafs
            com.google.android.gms.internal.ads.zzan r4 = r4.zzad()
            r5.<init>(r4)
            goto L_0x0039
        L_0x00a4:
            if (r3 != r8) goto L_0x0133
            int r9 = r17.zzk()
            java.lang.String r10 = "audio/mp4a-latm"
            java.lang.String r11 = "audio/raw"
            if (r9 == r8) goto L_0x00cd
            r12 = 85
            if (r9 == r12) goto L_0x00ca
            r12 = 255(0xff, float:3.57E-43)
            if (r9 == r12) goto L_0x00c8
            r12 = 8192(0x2000, float:1.14794E-41)
            if (r9 == r12) goto L_0x00c5
            r12 = 8193(0x2001, float:1.1481E-41)
            if (r9 == r12) goto L_0x00c2
            r12 = r5
            goto L_0x00ce
        L_0x00c2:
            java.lang.String r12 = "audio/vnd.dts"
            goto L_0x00ce
        L_0x00c5:
            java.lang.String r12 = "audio/ac3"
            goto L_0x00ce
        L_0x00c8:
            r12 = r10
            goto L_0x00ce
        L_0x00ca:
            java.lang.String r12 = "audio/mpeg"
            goto L_0x00ce
        L_0x00cd:
            r12 = r11
        L_0x00ce:
            if (r12 != 0) goto L_0x00e3
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Ignoring track with unsupported format tag "
            r10.<init>(r11)
            r10.append(r9)
            java.lang.String r9 = r10.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r9)
            goto L_0x0039
        L_0x00e3:
            int r4 = r17.zzk()
            int r5 = r17.zzi()
            r9 = 6
            r0.zzL(r9)
            int r9 = r17.zzq()
            int r9 = com.google.android.gms.internal.ads.zzgd.zzl(r9)
            int r13 = r17.zzk()
            byte[] r14 = new byte[r13]
            r15 = 0
            r0.zzG(r14, r15, r13)
            com.google.android.gms.internal.ads.zzal r15 = new com.google.android.gms.internal.ads.zzal
            r15.<init>()
            r15.zzX(r12)
            r15.zzy(r4)
            r15.zzY(r5)
            boolean r4 = r11.equals(r12)
            if (r4 == 0) goto L_0x011a
            if (r9 == 0) goto L_0x011a
            r15.zzR(r9)
        L_0x011a:
            boolean r4 = r10.equals(r12)
            if (r4 == 0) goto L_0x0129
            if (r13 <= 0) goto L_0x0129
            com.google.android.gms.internal.ads.zzgbc r4 = com.google.android.gms.internal.ads.zzgbc.zzn(r14)
            r15.zzL(r4)
        L_0x0129:
            com.google.android.gms.internal.ads.zzafs r4 = new com.google.android.gms.internal.ads.zzafs
            com.google.android.gms.internal.ads.zzan r5 = r15.zzad()
            r4.<init>(r5)
            goto L_0x0142
        L_0x0133:
            java.lang.String r9 = "Ignoring strf box for unsupported track type: "
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzC(r3)
            java.lang.String r9 = r9.concat(r10)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r9)
            goto L_0x0039
        L_0x0142:
            if (r4 == 0) goto L_0x017e
            int r5 = r4.zza()
            r9 = 1752331379(0x68727473, float:4.5798432E24)
            if (r5 != r9) goto L_0x017b
            r3 = r4
            com.google.android.gms.internal.ads.zzafp r3 = (com.google.android.gms.internal.ads.zzafp) r3
            int r3 = r3.zza
            r5 = 1935960438(0x73646976, float:1.809666E31)
            if (r3 == r5) goto L_0x017a
            r5 = 1935963489(0x73647561, float:1.8100348E31)
            if (r3 == r5) goto L_0x0178
            r5 = 1937012852(0x73747874, float:1.936895E31)
            if (r3 == r5) goto L_0x0176
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            java.lang.String r3 = java.lang.String.valueOf(r3)
            java.lang.String r5 = "AviStreamHeaderChunk"
            java.lang.String r7 = "Found unsupported streamType fourCC: "
            java.lang.String r3 = r7.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r5, r3)
            r3 = -1
            goto L_0x017b
        L_0x0176:
            r3 = 3
            goto L_0x017b
        L_0x0178:
            r3 = r8
            goto L_0x017b
        L_0x017a:
            r3 = r7
        L_0x017b:
            r1.zzf(r4)
        L_0x017e:
            r0.zzK(r6)
            r0.zzJ(r2)
            goto L_0x000c
        L_0x0186:
            com.google.android.gms.internal.ads.zzafr r0 = new com.google.android.gms.internal.ads.zzafr
            com.google.android.gms.internal.ads.zzgbc r1 = r1.zzi()
            r2 = r16
            r0.<init>(r2, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzafr.zzc(int, com.google.android.gms.internal.ads.zzfu):com.google.android.gms.internal.ads.zzafr");
    }

    public final int zza() {
        return this.zzb;
    }

    public final zzafj zzb(Class cls) {
        zzgbc zzgbc = this.zza;
        int size = zzgbc.size();
        int i = 0;
        while (i < size) {
            zzafj zzafj = (zzafj) zzgbc.get(i);
            i++;
            if (zzafj.getClass() == cls) {
                return zzafj;
            }
        }
        return null;
    }
}
