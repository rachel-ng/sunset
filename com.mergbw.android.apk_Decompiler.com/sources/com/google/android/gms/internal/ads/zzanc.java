package com.google.android.gms.internal.ads;

import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzanc {
    private static final Pattern zza = Pattern.compile("\\[voice=\"([^\"]*)\"\\]");
    private static final Pattern zzb = Pattern.compile("^((?:[0-9]*\\.)?[0-9]+)(px|em|%)$");
    private final zzfu zzc = new zzfu();
    private final StringBuilder zzd = new StringBuilder();

    static String zza(zzfu zzfu, StringBuilder sb) {
        zzc(zzfu);
        if (zzfu.zzb() == 0) {
            return null;
        }
        String zzd2 = zzd(zzfu, sb);
        if (!"".equals(zzd2)) {
            return zzd2;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((char) zzfu.zzm());
        return sb2.toString();
    }

    static void zzc(zzfu zzfu) {
        while (true) {
            boolean z = true;
            while (zzfu.zzb() > 0 && z) {
                char c2 = (char) zzfu.zzM()[zzfu.zzd()];
                if (c2 == 9 || c2 == 10 || c2 == 12 || c2 == 13 || c2 == ' ') {
                    zzfu.zzL(1);
                } else {
                    int zzd2 = zzfu.zzd();
                    int zze = zzfu.zze();
                    byte[] zzM = zzfu.zzM();
                    if (zzd2 + 2 <= zze) {
                        int i = zzd2 + 1;
                        if (zzM[zzd2] == 47) {
                            int i2 = zzd2 + 2;
                            if (zzM[i] == 42) {
                                while (true) {
                                    int i3 = i2 + 1;
                                    if (i3 >= zze) {
                                        break;
                                    } else if (((char) zzM[i2]) == '*' && ((char) zzM[i3]) == '/') {
                                        zze = i2 + 2;
                                        i2 = zze;
                                    } else {
                                        i2 = i3;
                                    }
                                }
                                zzfu.zzL(zze - zzfu.zzd());
                            }
                        } else {
                            continue;
                        }
                    }
                    z = false;
                }
            }
            return;
        }
    }

    private static String zzd(zzfu zzfu, StringBuilder sb) {
        sb.setLength(0);
        int zzd2 = zzfu.zzd();
        int zze = zzfu.zze();
        loop0:
        while (true) {
            boolean z = false;
            while (zzd2 < zze && !z) {
                char c2 = (char) zzfu.zzM()[zzd2];
                if ((c2 < 'A' || c2 > 'Z') && ((c2 < 'a' || c2 > 'z') && !((c2 >= '0' && c2 <= '9') || c2 == '#' || c2 == '-' || c2 == '.' || c2 == '_'))) {
                    z = true;
                } else {
                    sb.append(c2);
                    zzd2++;
                }
            }
        }
        zzfu.zzL(zzd2 - zzfu.zzd());
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00b0, code lost:
        if (")".equals(zza(r3, r4)) == false) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x02e3  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x02f6  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0317 A[EDGE_INSN: B:160:0x0317->B:158:0x0317 ?: BREAK  
    EDGE_INSN: B:161:0x0317->B:158:0x0317 ?: BREAK  , RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List zzb(com.google.android.gms.internal.ads.zzfu r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.StringBuilder r1 = r0.zzd
            r2 = 0
            r1.setLength(r2)
            int r1 = r18.zzd()
        L_0x000c:
            java.nio.charset.Charset r3 = com.google.android.gms.internal.ads.zzfxs.zzc
            r4 = r18
            java.lang.String r3 = r4.zzy(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x000c
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzc
            byte[] r5 = r18.zzM()
            int r4 = r18.zzd()
            r3.zzI(r5, r4)
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzc
            r3.zzK(r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L_0x0031:
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzc
            java.lang.StringBuilder r4 = r0.zzd
            zzc(r3)
            int r5 = r3.zzb()
            java.lang.String r6 = "{"
            r7 = 5
            java.lang.String r8 = ""
            r10 = 1
            if (r5 >= r7) goto L_0x0047
        L_0x0044:
            r5 = 0
            goto L_0x00b3
        L_0x0047:
            java.nio.charset.Charset r5 = com.google.android.gms.internal.ads.zzfxs.zzc
            java.lang.String r5 = r3.zzA(r7, r5)
            java.lang.String r7 = "::cue"
            boolean r5 = r7.equals(r5)
            if (r5 != 0) goto L_0x0056
            goto L_0x0044
        L_0x0056:
            int r5 = r3.zzd()
            java.lang.String r7 = zza(r3, r4)
            if (r7 != 0) goto L_0x0061
            goto L_0x0044
        L_0x0061:
            boolean r11 = r6.equals(r7)
            if (r11 == 0) goto L_0x006c
            r3.zzK(r5)
            r5 = r8
            goto L_0x00b3
        L_0x006c:
            java.lang.String r5 = "("
            boolean r5 = r5.equals(r7)
            if (r5 == 0) goto L_0x00a5
            int r5 = r3.zzd()
            int r7 = r3.zze()
            r11 = r2
        L_0x007d:
            if (r5 >= r7) goto L_0x0093
            if (r11 != 0) goto L_0x0093
            byte[] r11 = r3.zzM()
            int r12 = r5 + 1
            byte r5 = r11[r5]
            char r5 = (char) r5
            r11 = 41
            if (r5 != r11) goto L_0x0090
            r11 = r10
            goto L_0x0091
        L_0x0090:
            r11 = r2
        L_0x0091:
            r5 = r12
            goto L_0x007d
        L_0x0093:
            int r5 = r5 + -1
            int r7 = r3.zzd()
            int r5 = r5 - r7
            java.nio.charset.Charset r7 = com.google.android.gms.internal.ads.zzfxs.zzc
            java.lang.String r5 = r3.zzA(r5, r7)
            java.lang.String r5 = r5.trim()
            goto L_0x00a6
        L_0x00a5:
            r5 = 0
        L_0x00a6:
            java.lang.String r3 = zza(r3, r4)
            java.lang.String r4 = ")"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L_0x00b3
            goto L_0x0044
        L_0x00b3:
            if (r5 == 0) goto L_0x0317
            com.google.android.gms.internal.ads.zzfu r3 = r0.zzc
            java.lang.StringBuilder r4 = r0.zzd
            java.lang.String r3 = zza(r3, r4)
            boolean r3 = r6.equals(r3)
            if (r3 != 0) goto L_0x00c5
            goto L_0x0317
        L_0x00c5:
            com.google.android.gms.internal.ads.zzand r3 = new com.google.android.gms.internal.ads.zzand
            r3.<init>()
            boolean r4 = r8.equals(r5)
            r6 = -1
            if (r4 == 0) goto L_0x00d4
        L_0x00d1:
            r4 = r2
            r5 = 0
            goto L_0x012d
        L_0x00d4:
            r4 = 91
            int r4 = r5.indexOf(r4)
            if (r4 == r6) goto L_0x00fa
            java.util.regex.Pattern r7 = zza
            java.lang.String r11 = r5.substring(r4)
            java.util.regex.Matcher r7 = r7.matcher(r11)
            boolean r11 = r7.matches()
            if (r11 == 0) goto L_0x00f6
            java.lang.String r7 = r7.group(r10)
            r7.getClass()
            r3.zzv(r7)
        L_0x00f6:
            java.lang.String r5 = r5.substring(r2, r4)
        L_0x00fa:
            int r4 = com.google.android.gms.internal.ads.zzgd.zza
            java.lang.String r4 = "\\."
            java.lang.String[] r4 = r5.split(r4, r6)
            r5 = r4[r2]
            r7 = 35
            int r7 = r5.indexOf(r7)
            if (r7 == r6) goto L_0x011d
            java.lang.String r11 = r5.substring(r2, r7)
            r3.zzu(r11)
            int r7 = r7 + 1
            java.lang.String r5 = r5.substring(r7)
            r3.zzt(r5)
            goto L_0x0120
        L_0x011d:
            r3.zzu(r5)
        L_0x0120:
            int r5 = r4.length
            if (r5 <= r10) goto L_0x00d1
            java.lang.Object[] r4 = java.util.Arrays.copyOfRange(r4, r10, r5)
            java.lang.String[] r4 = (java.lang.String[]) r4
            r3.zzs(r4)
            goto L_0x00d1
        L_0x012d:
            java.lang.String r7 = "}"
            if (r4 != 0) goto L_0x030b
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzc
            java.lang.StringBuilder r5 = r0.zzd
            int r11 = r4.zzd()
            java.lang.String r5 = zza(r4, r5)
            if (r5 == 0) goto L_0x0148
            boolean r4 = r7.equals(r5)
            if (r4 == 0) goto L_0x0146
            goto L_0x0148
        L_0x0146:
            r4 = r2
            goto L_0x0149
        L_0x0148:
            r4 = r10
        L_0x0149:
            if (r4 != 0) goto L_0x0307
            com.google.android.gms.internal.ads.zzfu r12 = r0.zzc
            r12.zzK(r11)
            com.google.android.gms.internal.ads.zzfu r11 = r0.zzc
            java.lang.StringBuilder r12 = r0.zzd
            zzc(r11)
            java.lang.String r13 = zzd(r11, r12)
            boolean r14 = r8.equals(r13)
            if (r14 == 0) goto L_0x0163
            goto L_0x0307
        L_0x0163:
            java.lang.String r14 = zza(r11, r12)
            java.lang.String r15 = ":"
            boolean r14 = r15.equals(r14)
            if (r14 != 0) goto L_0x0171
            goto L_0x0307
        L_0x0171:
            zzc(r11)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            r15 = r2
        L_0x017a:
            java.lang.String r2 = ";"
            if (r15 != 0) goto L_0x01a1
            int r6 = r11.zzd()
            java.lang.String r9 = zza(r11, r12)
            if (r9 != 0) goto L_0x018a
            r6 = 0
            goto L_0x01a5
        L_0x018a:
            boolean r16 = r7.equals(r9)
            if (r16 != 0) goto L_0x019b
            boolean r2 = r2.equals(r9)
            if (r2 == 0) goto L_0x0197
            goto L_0x019b
        L_0x0197:
            r14.append(r9)
            goto L_0x019f
        L_0x019b:
            r11.zzK(r6)
            r15 = r10
        L_0x019f:
            r6 = -1
            goto L_0x017a
        L_0x01a1:
            java.lang.String r6 = r14.toString()
        L_0x01a5:
            if (r6 == 0) goto L_0x0307
            boolean r9 = r8.equals(r6)
            if (r9 == 0) goto L_0x01af
            goto L_0x0307
        L_0x01af:
            int r9 = r11.zzd()
            java.lang.String r12 = zza(r11, r12)
            boolean r2 = r2.equals(r12)
            if (r2 == 0) goto L_0x01be
            goto L_0x01c7
        L_0x01be:
            boolean r2 = r7.equals(r12)
            if (r2 == 0) goto L_0x0307
            r11.zzK(r9)
        L_0x01c7:
            java.lang.String r2 = "color"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x01d8
            int r2 = com.google.android.gms.internal.ads.zzet.zza(r6)
            r3.zzk(r2)
            goto L_0x0307
        L_0x01d8:
            java.lang.String r2 = "background-color"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x01e9
            int r2 = com.google.android.gms.internal.ads.zzet.zza(r6)
            r3.zzh(r2)
            goto L_0x0307
        L_0x01e9:
            java.lang.String r2 = "ruby-position"
            boolean r2 = r2.equals(r13)
            r7 = 2
            if (r2 == 0) goto L_0x020c
            java.lang.String r2 = "over"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x01ff
            r3.zzp(r10)
            goto L_0x0307
        L_0x01ff:
            java.lang.String r2 = "under"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0307
            r3.zzp(r7)
            goto L_0x0307
        L_0x020c:
            java.lang.String r2 = "text-combine-upright"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x022d
            java.lang.String r2 = "all"
            boolean r2 = r2.equals(r6)
            if (r2 != 0) goto L_0x0227
            java.lang.String r2 = "digits"
            boolean r2 = r6.startsWith(r2)
            if (r2 == 0) goto L_0x0225
            goto L_0x0227
        L_0x0225:
            r2 = 0
            goto L_0x0228
        L_0x0227:
            r2 = r10
        L_0x0228:
            r3.zzj(r2)
            goto L_0x0307
        L_0x022d:
            java.lang.String r2 = "text-decoration"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x0242
            java.lang.String r2 = "underline"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0307
            r3.zzq(r10)
            goto L_0x0307
        L_0x0242:
            java.lang.String r2 = "font-family"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x024f
            r3.zzl(r6)
            goto L_0x0307
        L_0x024f:
            java.lang.String r2 = "font-weight"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x0264
            java.lang.String r2 = "bold"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0307
            r3.zzi(r10)
            goto L_0x0307
        L_0x0264:
            java.lang.String r2 = "font-style"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x0279
            java.lang.String r2 = "italic"
            boolean r2 = r2.equals(r6)
            if (r2 == 0) goto L_0x0307
            r3.zzo(r10)
            goto L_0x0307
        L_0x0279:
            java.lang.String r2 = "font-size"
            boolean r2 = r2.equals(r13)
            if (r2 == 0) goto L_0x0307
            java.util.regex.Pattern r2 = zzb
            java.lang.String r9 = com.google.android.gms.internal.ads.zzfxm.zza(r6)
            java.util.regex.Matcher r2 = r2.matcher(r9)
            boolean r9 = r2.matches()
            if (r9 != 0) goto L_0x02aa
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r7 = "Invalid font-size: '"
            r2.<init>(r7)
            r2.append(r6)
            java.lang.String r6 = "'."
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            java.lang.String r6 = "WebvttCssParser"
            com.google.android.gms.internal.ads.zzfk.zzf(r6, r2)
            goto L_0x0307
        L_0x02aa:
            java.lang.String r6 = r2.group(r7)
            r6.getClass()
            int r9 = r6.hashCode()
            r11 = 37
            if (r9 == r11) goto L_0x02d6
            r11 = 3240(0xca8, float:4.54E-42)
            if (r9 == r11) goto L_0x02cc
            r11 = 3592(0xe08, float:5.033E-42)
            if (r9 == r11) goto L_0x02c2
            goto L_0x02e0
        L_0x02c2:
            java.lang.String r9 = "px"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x02e0
            r6 = 0
            goto L_0x02e1
        L_0x02cc:
            java.lang.String r9 = "em"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x02e0
            r6 = r10
            goto L_0x02e1
        L_0x02d6:
            java.lang.String r9 = "%"
            boolean r6 = r6.equals(r9)
            if (r6 == 0) goto L_0x02e0
            r6 = r7
            goto L_0x02e1
        L_0x02e0:
            r6 = -1
        L_0x02e1:
            if (r6 == 0) goto L_0x02f6
            if (r6 == r10) goto L_0x02f2
            if (r6 != r7) goto L_0x02ec
            r6 = 3
            r3.zzn(r6)
            goto L_0x02f9
        L_0x02ec:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            r1.<init>()
            throw r1
        L_0x02f2:
            r3.zzn(r7)
            goto L_0x02f9
        L_0x02f6:
            r3.zzn(r10)
        L_0x02f9:
            java.lang.String r2 = r2.group(r10)
            r2.getClass()
            float r2 = java.lang.Float.parseFloat(r2)
            r3.zzm(r2)
        L_0x0307:
            r2 = 0
            r6 = -1
            goto L_0x012d
        L_0x030b:
            boolean r2 = r7.equals(r5)
            if (r2 == 0) goto L_0x0314
            r1.add(r3)
        L_0x0314:
            r2 = 0
            goto L_0x0031
        L_0x0317:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanc.zzb(com.google.android.gms.internal.ads.zzfu):java.util.List");
    }
}
