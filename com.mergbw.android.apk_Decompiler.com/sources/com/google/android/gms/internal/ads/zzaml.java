package com.google.android.gms.internal.ads;

import androidx.work.WorkRequest;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaml implements zzalv {
    private static final Pattern zza = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+)[:.](\\d+)");
    private final boolean zzb;
    private final zzamk zzc;
    private final zzfu zzd;
    private Map zze;
    private float zzf;
    private float zzg;

    public zzaml() {
        this((List) null);
    }

    private static float zzb(int i) {
        if (i == 0) {
            return 0.05f;
        }
        if (i != 1) {
            return i != 2 ? -3.4028235E38f : 0.95f;
        }
        return 0.5f;
    }

    private static int zzc(long j, List list, List list2) {
        int i;
        int size = list.size();
        while (true) {
            size--;
            if (size >= 0) {
                if (((Long) list.get(size)).longValue() != j) {
                    if (((Long) list.get(size)).longValue() < j) {
                        i = size + 1;
                        break;
                    }
                } else {
                    return size;
                }
            } else {
                i = 0;
                break;
            }
        }
        list.add(i, Long.valueOf(j));
        list2.add(i, i == 0 ? new ArrayList() : new ArrayList((Collection) list2.get(i - 1)));
        return i;
    }

    private static long zzd(String str) {
        Matcher matcher = zza.matcher(str.trim());
        if (!matcher.matches()) {
            return C.TIME_UNSET;
        }
        String group = matcher.group(1);
        int i = zzgd.zza;
        return (Long.parseLong(group) * 3600000000L) + (Long.parseLong(matcher.group(2)) * 60000000) + (Long.parseLong(matcher.group(3)) * 1000000) + (Long.parseLong(matcher.group(4)) * WorkRequest.MIN_BACKOFF_MILLIS);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0050, code lost:
        if (r3.equals("playresx") != false) goto L_0x0054;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zze(com.google.android.gms.internal.ads.zzfu r7, java.nio.charset.Charset r8) {
        /*
            r6 = this;
        L_0x0000:
            java.lang.String r0 = r7.zzy(r8)
            if (r0 == 0) goto L_0x00dd
            java.lang.String r1 = "[Script Info]"
            boolean r1 = r1.equalsIgnoreCase(r0)
            r2 = 91
            if (r1 == 0) goto L_0x0073
        L_0x0010:
            java.lang.String r0 = r7.zzy(r8)
            if (r0 == 0) goto L_0x0000
            int r1 = r7.zzb()
            if (r1 == 0) goto L_0x0022
            char r1 = r7.zza(r8)
            if (r1 == r2) goto L_0x0000
        L_0x0022:
            java.lang.String r1 = ":"
            java.lang.String[] r0 = r0.split(r1)
            int r1 = r0.length
            r3 = 2
            if (r1 != r3) goto L_0x0010
            r1 = 0
            r3 = r0[r1]
            java.lang.String r3 = r3.trim()
            java.lang.String r3 = com.google.android.gms.internal.ads.zzfxm.zza(r3)
            int r4 = r3.hashCode()
            r5 = 1
            switch(r4) {
                case 1879649548: goto L_0x004a;
                case 1879649549: goto L_0x0040;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x0053
        L_0x0040:
            java.lang.String r1 = "playresy"
            boolean r1 = r3.equals(r1)
            if (r1 == 0) goto L_0x0053
            r1 = r5
            goto L_0x0054
        L_0x004a:
            java.lang.String r4 = "playresx"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r1 = -1
        L_0x0054:
            if (r1 == 0) goto L_0x0066
            if (r1 == r5) goto L_0x0059
            goto L_0x0010
        L_0x0059:
            r0 = r0[r5]     // Catch:{ NumberFormatException -> 0x0010 }
            java.lang.String r0 = r0.trim()     // Catch:{ NumberFormatException -> 0x0010 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0010 }
            r6.zzg = r0     // Catch:{ NumberFormatException -> 0x0010 }
            goto L_0x0010
        L_0x0066:
            r0 = r0[r5]     // Catch:{ NumberFormatException -> 0x0010 }
            java.lang.String r0 = r0.trim()     // Catch:{ NumberFormatException -> 0x0010 }
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0010 }
            r6.zzf = r0     // Catch:{ NumberFormatException -> 0x0010 }
            goto L_0x0010
        L_0x0073:
            java.lang.String r1 = "[V4+ Styles]"
            boolean r1 = r1.equalsIgnoreCase(r0)
            java.lang.String r3 = "SsaParser"
            if (r1 == 0) goto L_0x00c6
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            r1 = 0
        L_0x0083:
            java.lang.String r4 = r7.zzy(r8)
            if (r4 == 0) goto L_0x00c2
            int r5 = r7.zzb()
            if (r5 == 0) goto L_0x0095
            char r5 = r7.zza(r8)
            if (r5 == r2) goto L_0x00c2
        L_0x0095:
            java.lang.String r5 = "Format:"
            boolean r5 = r4.startsWith(r5)
            if (r5 == 0) goto L_0x00a2
            com.google.android.gms.internal.ads.zzamm r1 = com.google.android.gms.internal.ads.zzamm.zza(r4)
            goto L_0x0083
        L_0x00a2:
            java.lang.String r5 = "Style:"
            boolean r5 = r4.startsWith(r5)
            if (r5 == 0) goto L_0x0083
            if (r1 != 0) goto L_0x00b6
            java.lang.String r5 = "Skipping 'Style:' line before 'Format:' line: "
            java.lang.String r4 = r5.concat(r4)
            com.google.android.gms.internal.ads.zzfk.zzf(r3, r4)
            goto L_0x0083
        L_0x00b6:
            com.google.android.gms.internal.ads.zzamo r4 = com.google.android.gms.internal.ads.zzamo.zzb(r4, r1)
            if (r4 == 0) goto L_0x0083
            java.lang.String r5 = r4.zza
            r0.put(r5, r4)
            goto L_0x0083
        L_0x00c2:
            r6.zze = r0
            goto L_0x0000
        L_0x00c6:
            java.lang.String r1 = "[V4 Styles]"
            boolean r1 = r1.equalsIgnoreCase(r0)
            if (r1 == 0) goto L_0x00d5
            java.lang.String r0 = "[V4 Styles] are not supported"
            com.google.android.gms.internal.ads.zzfk.zze(r3, r0)
            goto L_0x0000
        L_0x00d5:
            java.lang.String r1 = "[Events]"
            boolean r0 = r1.equalsIgnoreCase(r0)
            if (r0 == 0) goto L_0x0000
        L_0x00dd:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaml.zze(com.google.android.gms.internal.ads.zzfu, java.nio.charset.Charset):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0244, code lost:
        r7.zzh(zzb(r7.zzb()));
        r7.zze(zzb(r7.zza()), 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x025b, code lost:
        r0 = r7.zzp();
        r1 = zzc(r13, r3, r2);
        r4 = zzc(r20, r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0269, code lost:
        if (r1 >= r4) goto L_0x027d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x026b, code lost:
        ((java.util.List) r2.get(r1)).add(r0);
        r1 = r1 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01ea, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01eb, code lost:
        r7.zzm(r5);
        r10 = Integer.MIN_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01f0, code lost:
        switch(r0) {
            case -1: goto L_0x0209;
            case 0: goto L_0x01f3;
            case 1: goto L_0x0207;
            case 2: goto L_0x0205;
            case 3: goto L_0x0203;
            case 4: goto L_0x0207;
            case 5: goto L_0x0205;
            case 6: goto L_0x0203;
            case 7: goto L_0x0207;
            case 8: goto L_0x0205;
            case 9: goto L_0x0203;
            default: goto L_0x01f3;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01f3, code lost:
        com.google.android.gms.internal.ads.zzfk.zzf("SsaParser", "Unknown alignment: " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0203, code lost:
        r5 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0205, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0207, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0209, code lost:
        r5 = Integer.MIN_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x020a, code lost:
        r7.zzi(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x020d, code lost:
        switch(r0) {
            case -1: goto L_0x0225;
            case 0: goto L_0x0210;
            case 1: goto L_0x0224;
            case 2: goto L_0x0224;
            case 3: goto L_0x0224;
            case 4: goto L_0x0222;
            case 5: goto L_0x0222;
            case 6: goto L_0x0222;
            case 7: goto L_0x0220;
            case 8: goto L_0x0220;
            case 9: goto L_0x0220;
            default: goto L_0x0210;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0210, code lost:
        com.google.android.gms.internal.ads.zzfk.zzf("SsaParser", "Unknown alignment: " + r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0220, code lost:
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0222, code lost:
        r10 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0224, code lost:
        r10 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0225, code lost:
        r7.zzf(r10);
        r0 = r9.zzb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x022a, code lost:
        if (r0 == null) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x022e, code lost:
        if (r15 == -3.4028235E38f) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0232, code lost:
        if (r8 == -3.4028235E38f) goto L_0x0244;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0234, code lost:
        r7.zzh(r0.x / r8);
        r7.zze(r9.zzb.y / r15, 0);
     */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(byte[] r23, int r24, int r25, com.google.android.gms.internal.ads.zzalu r26, com.google.android.gms.internal.ads.zzev r27) {
        /*
            r22 = this;
            r0 = r22
            r1 = r24
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r4 = r1 + r25
            com.google.android.gms.internal.ads.zzfu r5 = r0.zzd
            r6 = r23
            r5.zzI(r6, r4)
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzd
            r4.zzK(r1)
            com.google.android.gms.internal.ads.zzfu r1 = r0.zzd
            java.nio.charset.Charset r1 = r1.zzB()
            if (r1 != 0) goto L_0x0026
            java.nio.charset.Charset r1 = com.google.android.gms.internal.ads.zzfxs.zzc
        L_0x0026:
            boolean r4 = r0.zzb
            if (r4 != 0) goto L_0x002f
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzd
            r0.zze(r4, r1)
        L_0x002f:
            com.google.android.gms.internal.ads.zzfu r4 = r0.zzd
            boolean r5 = r0.zzb
            if (r5 == 0) goto L_0x0038
            com.google.android.gms.internal.ads.zzamk r5 = r0.zzc
            goto L_0x0039
        L_0x0038:
            r5 = 0
        L_0x0039:
            java.lang.String r7 = r4.zzy(r1)
            r8 = -1
            if (r7 == 0) goto L_0x0287
            java.lang.String r11 = "Format:"
            boolean r11 = r7.startsWith(r11)
            if (r11 == 0) goto L_0x004d
            com.google.android.gms.internal.ads.zzamk r5 = com.google.android.gms.internal.ads.zzamk.zza(r7)
            goto L_0x0039
        L_0x004d:
            java.lang.String r11 = "Dialogue:"
            boolean r12 = r7.startsWith(r11)
            if (r12 == 0) goto L_0x0277
            java.lang.String r12 = "SsaParser"
            if (r5 != 0) goto L_0x0064
            java.lang.String r8 = "Skipping dialogue line before complete format: "
            java.lang.String r7 = r8.concat(r7)
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r7)
            goto L_0x0277
        L_0x0064:
            boolean r11 = r7.startsWith(r11)
            com.google.android.gms.internal.ads.zzeq.zzd(r11)
            r11 = 9
            java.lang.String r11 = r7.substring(r11)
            java.lang.String r13 = ","
            int r14 = r5.zze
            java.lang.String[] r11 = r11.split(r13, r14)
            int r13 = r11.length
            int r14 = r5.zze
            if (r13 == r14) goto L_0x0089
            java.lang.String r8 = "Skipping dialogue line with fewer columns than format: "
            java.lang.String r7 = r8.concat(r7)
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r7)
            goto L_0x0277
        L_0x0089:
            int r13 = r5.zza
            r13 = r11[r13]
            long r13 = zzd(r13)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            java.lang.String r6 = "Skipping invalid timing: "
            if (r17 != 0) goto L_0x00a5
            java.lang.String r6 = r6.concat(r7)
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r6)
            goto L_0x0277
        L_0x00a5:
            int r9 = r5.zzb
            r9 = r11[r9]
            r26 = r11
            long r10 = zzd(r9)
            int r9 = (r10 > r15 ? 1 : (r10 == r15 ? 0 : -1))
            if (r9 != 0) goto L_0x00bc
            java.lang.String r6 = r6.concat(r7)
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r6)
            goto L_0x0277
        L_0x00bc:
            java.util.Map r6 = r0.zze
            if (r6 == 0) goto L_0x00d1
            int r7 = r5.zzc
            if (r7 == r8) goto L_0x00d1
            r7 = r26[r7]
            java.lang.String r7 = r7.trim()
            java.lang.Object r6 = r6.get(r7)
            com.google.android.gms.internal.ads.zzamo r6 = (com.google.android.gms.internal.ads.zzamo) r6
            goto L_0x00d2
        L_0x00d1:
            r6 = 0
        L_0x00d2:
            int r7 = r5.zzd
            r7 = r26[r7]
            com.google.android.gms.internal.ads.zzamn r9 = com.google.android.gms.internal.ads.zzamn.zza(r7)
            java.lang.String r7 = com.google.android.gms.internal.ads.zzamn.zzb(r7)
            java.lang.String r15 = "\\N"
            java.lang.String r8 = "\n"
            java.lang.String r7 = r7.replace(r15, r8)
            java.lang.String r15 = "\\n"
            java.lang.String r7 = r7.replace(r15, r8)
            java.lang.String r8 = "\\h"
            java.lang.String r15 = " "
            java.lang.String r7 = r7.replace(r8, r15)
            float r8 = r0.zzf
            float r15 = r0.zzg
            android.text.SpannableString r0 = new android.text.SpannableString
            r0.<init>(r7)
            com.google.android.gms.internal.ads.zzeg r7 = new com.google.android.gms.internal.ads.zzeg
            r7.<init>()
            r7.zzl(r0)
            r16 = r1
            r17 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            if (r6 == 0) goto L_0x01b9
            java.lang.Integer r1 = r6.zzc
            r18 = r4
            if (r1 == 0) goto L_0x012a
            android.text.style.ForegroundColorSpan r4 = new android.text.style.ForegroundColorSpan
            int r1 = r1.intValue()
            r4.<init>(r1)
            int r1 = r0.length()
            r19 = r5
            r20 = r10
            r5 = 33
            r10 = 0
            r0.setSpan(r4, r10, r1, r5)
            goto L_0x012e
        L_0x012a:
            r19 = r5
            r20 = r10
        L_0x012e:
            int r1 = r6.zzj
            r4 = 3
            if (r1 != r4) goto L_0x014a
            java.lang.Integer r1 = r6.zzd
            if (r1 == 0) goto L_0x014a
            android.text.style.BackgroundColorSpan r5 = new android.text.style.BackgroundColorSpan
            int r1 = r1.intValue()
            r5.<init>(r1)
            int r1 = r0.length()
            r10 = 33
            r11 = 0
            r0.setSpan(r5, r11, r1, r10)
        L_0x014a:
            float r1 = r6.zze
            int r5 = (r1 > r17 ? 1 : (r1 == r17 ? 0 : -1))
            if (r5 == 0) goto L_0x0159
            int r5 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r5 == 0) goto L_0x0159
            float r1 = r1 / r15
            r5 = 1
            r7.zzn(r1, r5)
        L_0x0159:
            boolean r1 = r6.zzf
            if (r1 == 0) goto L_0x0182
            boolean r1 = r6.zzg
            if (r1 == 0) goto L_0x0171
            android.text.style.StyleSpan r1 = new android.text.style.StyleSpan
            r1.<init>(r4)
            int r4 = r0.length()
            r5 = 33
            r10 = 0
            r0.setSpan(r1, r10, r4, r5)
            goto L_0x0197
        L_0x0171:
            r5 = 33
            r10 = 0
            android.text.style.StyleSpan r1 = new android.text.style.StyleSpan
            r4 = 1
            r1.<init>(r4)
            int r4 = r0.length()
            r0.setSpan(r1, r10, r4, r5)
            goto L_0x0197
        L_0x0182:
            r5 = 33
            r10 = 0
            boolean r1 = r6.zzg
            if (r1 == 0) goto L_0x0197
            android.text.style.StyleSpan r1 = new android.text.style.StyleSpan
            r4 = 2
            r1.<init>(r4)
            int r11 = r0.length()
            r0.setSpan(r1, r10, r11, r5)
            goto L_0x0198
        L_0x0197:
            r4 = 2
        L_0x0198:
            boolean r1 = r6.zzh
            if (r1 == 0) goto L_0x01a8
            android.text.style.UnderlineSpan r1 = new android.text.style.UnderlineSpan
            r1.<init>()
            int r11 = r0.length()
            r0.setSpan(r1, r10, r11, r5)
        L_0x01a8:
            boolean r1 = r6.zzi
            if (r1 == 0) goto L_0x01c0
            android.text.style.StrikethroughSpan r1 = new android.text.style.StrikethroughSpan
            r1.<init>()
            int r11 = r0.length()
            r0.setSpan(r1, r10, r11, r5)
            goto L_0x01c0
        L_0x01b9:
            r18 = r4
            r19 = r5
            r20 = r10
            r4 = 2
        L_0x01c0:
            int r0 = r9.zza
            r1 = -1
            if (r0 == r1) goto L_0x01c6
            goto L_0x01cc
        L_0x01c6:
            if (r6 == 0) goto L_0x01cb
            int r0 = r6.zzb
            goto L_0x01cc
        L_0x01cb:
            r0 = -1
        L_0x01cc:
            java.lang.String r1 = "Unknown alignment: "
            switch(r0) {
                case -1: goto L_0x01ea;
                case 0: goto L_0x01d1;
                case 1: goto L_0x01e7;
                case 2: goto L_0x01e4;
                case 3: goto L_0x01e1;
                case 4: goto L_0x01e7;
                case 5: goto L_0x01e4;
                case 6: goto L_0x01e1;
                case 7: goto L_0x01e7;
                case 8: goto L_0x01e4;
                case 9: goto L_0x01e1;
                default: goto L_0x01d1;
            }
        L_0x01d1:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r5)
            goto L_0x01ea
        L_0x01e1:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            goto L_0x01eb
        L_0x01e4:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_CENTER
            goto L_0x01eb
        L_0x01e7:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_NORMAL
            goto L_0x01eb
        L_0x01ea:
            r5 = 0
        L_0x01eb:
            r7.zzm(r5)
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            switch(r0) {
                case -1: goto L_0x0209;
                case 0: goto L_0x01f3;
                case 1: goto L_0x0207;
                case 2: goto L_0x0205;
                case 3: goto L_0x0203;
                case 4: goto L_0x0207;
                case 5: goto L_0x0205;
                case 6: goto L_0x0203;
                case 7: goto L_0x0207;
                case 8: goto L_0x0205;
                case 9: goto L_0x0203;
                default: goto L_0x01f3;
            }
        L_0x01f3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r1)
            r5.append(r0)
            java.lang.String r5 = r5.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r5)
            goto L_0x0209
        L_0x0203:
            r5 = r4
            goto L_0x020a
        L_0x0205:
            r5 = 1
            goto L_0x020a
        L_0x0207:
            r5 = 0
            goto L_0x020a
        L_0x0209:
            r5 = r10
        L_0x020a:
            r7.zzi(r5)
            switch(r0) {
                case -1: goto L_0x0225;
                case 0: goto L_0x0210;
                case 1: goto L_0x0224;
                case 2: goto L_0x0224;
                case 3: goto L_0x0224;
                case 4: goto L_0x0222;
                case 5: goto L_0x0222;
                case 6: goto L_0x0222;
                case 7: goto L_0x0220;
                case 8: goto L_0x0220;
                case 9: goto L_0x0220;
                default: goto L_0x0210;
            }
        L_0x0210:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r1)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r0)
            goto L_0x0225
        L_0x0220:
            r10 = 0
            goto L_0x0225
        L_0x0222:
            r10 = 1
            goto L_0x0225
        L_0x0224:
            r10 = r4
        L_0x0225:
            r7.zzf(r10)
            android.graphics.PointF r0 = r9.zzb
            if (r0 == 0) goto L_0x0244
            int r1 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r1 == 0) goto L_0x0244
            int r1 = (r8 > r17 ? 1 : (r8 == r17 ? 0 : -1))
            if (r1 == 0) goto L_0x0244
            float r0 = r0.x
            float r0 = r0 / r8
            r7.zzh(r0)
            android.graphics.PointF r0 = r9.zzb
            float r0 = r0.y
            float r0 = r0 / r15
            r10 = 0
            r7.zze(r0, r10)
            goto L_0x025b
        L_0x0244:
            r10 = 0
            int r0 = r7.zzb()
            float r0 = zzb(r0)
            r7.zzh(r0)
            int r0 = r7.zza()
            float r0 = zzb(r0)
            r7.zze(r0, r10)
        L_0x025b:
            com.google.android.gms.internal.ads.zzei r0 = r7.zzp()
            int r1 = zzc(r13, r3, r2)
            r4 = r20
            int r4 = zzc(r4, r3, r2)
        L_0x0269:
            if (r1 >= r4) goto L_0x027d
            java.lang.Object r5 = r2.get(r1)
            java.util.List r5 = (java.util.List) r5
            r5.add(r0)
            int r1 = r1 + 1
            goto L_0x0269
        L_0x0277:
            r16 = r1
            r18 = r4
            r19 = r5
        L_0x027d:
            r0 = r22
            r1 = r16
            r4 = r18
            r5 = r19
            goto L_0x0039
        L_0x0287:
            r10 = 0
            r0 = r10
        L_0x0289:
            int r1 = r2.size()
            if (r0 >= r1) goto L_0x02e1
            java.lang.Object r1 = r2.get(r0)
            r5 = r1
            java.util.List r5 = (java.util.List) r5
            boolean r1 = r5.isEmpty()
            if (r1 == 0) goto L_0x02a4
            if (r0 == 0) goto L_0x02a3
            r4 = r27
            r1 = 1
            r11 = -1
            goto L_0x02d9
        L_0x02a3:
            r0 = r10
        L_0x02a4:
            int r1 = r2.size()
            r11 = -1
            int r1 = r1 + r11
            if (r0 == r1) goto L_0x02db
            java.lang.Object r1 = r3.get(r0)
            java.lang.Long r1 = (java.lang.Long) r1
            long r6 = r1.longValue()
            int r1 = r0 + 1
            java.lang.Object r1 = r3.get(r1)
            java.lang.Long r1 = (java.lang.Long) r1
            long r8 = r1.longValue()
            java.lang.Object r1 = r3.get(r0)
            java.lang.Long r1 = (java.lang.Long) r1
            long r12 = r1.longValue()
            long r8 = r8 - r12
            com.google.android.gms.internal.ads.zzaln r1 = new com.google.android.gms.internal.ads.zzaln
            r4 = r1
            r4.<init>(r5, r6, r8)
            r4 = r27
            r4.zza(r1)
            r1 = 1
        L_0x02d9:
            int r0 = r0 + r1
            goto L_0x0289
        L_0x02db:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x02e1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaml.zza(byte[], int, int, com.google.android.gms.internal.ads.zzalu, com.google.android.gms.internal.ads.zzev):void");
    }

    public zzaml(List list) {
        this.zzf = -3.4028235E38f;
        this.zzg = -3.4028235E38f;
        this.zzd = new zzfu();
        if (list == null || list.isEmpty()) {
            this.zzb = false;
            this.zzc = null;
            return;
        }
        this.zzb = true;
        String zzA = zzgd.zzA((byte[]) list.get(0));
        zzeq.zzd(zzA.startsWith("Format:"));
        zzamk zza2 = zzamk.zza(zzA);
        zza2.getClass();
        this.zzc = zza2;
        zze(new zzfu((byte[]) list.get(1)), zzfxs.zzc);
    }
}
