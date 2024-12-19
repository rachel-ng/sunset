package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzamp implements zzalv {
    private static final Pattern zza = Pattern.compile("\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*-->\\s*((?:(\\d+):)?(\\d+):(\\d+)(?:,(\\d+))?)\\s*");
    private static final Pattern zzb = Pattern.compile("\\{\\\\.*?\\}");
    private final StringBuilder zzc = new StringBuilder();
    private final ArrayList zzd = new ArrayList();
    private final zzfu zze = new zzfu();

    public static float zzb(int i) {
        if (i == 0) {
            return 0.08f;
        }
        if (i == 1) {
            return 0.5f;
        }
        if (i == 2) {
            return 0.92f;
        }
        throw new IllegalArgumentException();
    }

    private static long zzc(Matcher matcher, int i) {
        String group = matcher.group(i + 1);
        long parseLong = group != null ? Long.parseLong(group) * 3600000 : 0;
        String group2 = matcher.group(i + 2);
        group2.getClass();
        long parseLong2 = parseLong + (Long.parseLong(group2) * 60000);
        String group3 = matcher.group(i + 3);
        group3.getClass();
        long parseLong3 = parseLong2 + (Long.parseLong(group3) * 1000);
        String group4 = matcher.group(i + 4);
        if (group4 != null) {
            parseLong3 += Long.parseLong(group4);
        }
        return parseLong3 * 1000;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(byte[] r17, int r18, int r19, com.google.android.gms.internal.ads.zzalu r20, com.google.android.gms.internal.ads.zzev r21) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            java.lang.String r2 = "SubripParser"
            int r3 = r1 + r19
            com.google.android.gms.internal.ads.zzfu r4 = r0.zze
            r5 = r17
            r4.zzI(r5, r3)
            com.google.android.gms.internal.ads.zzfu r3 = r0.zze
            r3.zzK(r1)
            com.google.android.gms.internal.ads.zzfu r1 = r0.zze
            java.nio.charset.Charset r1 = r1.zzB()
            if (r1 != 0) goto L_0x001e
            java.nio.charset.Charset r1 = com.google.android.gms.internal.ads.zzfxs.zzc
        L_0x001e:
            com.google.android.gms.internal.ads.zzfu r3 = r0.zze
            java.lang.String r3 = r3.zzy(r1)
            if (r3 == 0) goto L_0x0201
            int r4 = r3.length()
            if (r4 == 0) goto L_0x01fb
            java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x01ef }
            com.google.android.gms.internal.ads.zzfu r3 = r0.zze
            java.lang.String r3 = r3.zzy(r1)
            if (r3 != 0) goto L_0x003d
            java.lang.String r1 = "Unexpected end"
            com.google.android.gms.internal.ads.zzfk.zzf(r2, r1)
            return
        L_0x003d:
            java.util.regex.Pattern r4 = zza
            java.util.regex.Matcher r4 = r4.matcher(r3)
            boolean r5 = r4.matches()
            if (r5 == 0) goto L_0x01e3
            r3 = 1
            long r7 = zzc(r4, r3)
            r5 = 6
            long r4 = zzc(r4, r5)
            java.lang.StringBuilder r6 = r0.zzc
            r9 = 0
            r6.setLength(r9)
            java.util.ArrayList r6 = r0.zzd
            r6.clear()
            com.google.android.gms.internal.ads.zzfu r6 = r0.zze
            java.lang.String r6 = r6.zzy(r1)
        L_0x0064:
            boolean r10 = android.text.TextUtils.isEmpty(r6)
            if (r10 != 0) goto L_0x00be
            java.lang.StringBuilder r10 = r0.zzc
            int r10 = r10.length()
            if (r10 <= 0) goto L_0x0079
            java.lang.StringBuilder r10 = r0.zzc
            java.lang.String r11 = "<br>"
            r10.append(r11)
        L_0x0079:
            java.lang.StringBuilder r10 = r0.zzc
            java.util.ArrayList r11 = r0.zzd
            java.lang.String r6 = r6.trim()
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>(r6)
            java.util.regex.Pattern r13 = zzb
            java.util.regex.Matcher r6 = r13.matcher(r6)
            r13 = r9
        L_0x008d:
            boolean r14 = r6.find()
            if (r14 == 0) goto L_0x00ae
            java.lang.String r14 = r6.group()
            r11.add(r14)
            int r15 = r6.start()
            int r15 = r15 - r13
            int r14 = r14.length()
            int r9 = r15 + r14
            java.lang.String r3 = ""
            r12.replace(r15, r9, r3)
            int r13 = r13 + r14
            r3 = 1
            r9 = 0
            goto L_0x008d
        L_0x00ae:
            java.lang.String r3 = r12.toString()
            r10.append(r3)
            com.google.android.gms.internal.ads.zzfu r3 = r0.zze
            java.lang.String r6 = r3.zzy(r1)
            r3 = 1
            r9 = 0
            goto L_0x0064
        L_0x00be:
            java.lang.StringBuilder r3 = r0.zzc
            java.lang.String r3 = r3.toString()
            android.text.Spanned r3 = android.text.Html.fromHtml(r3)
            r6 = 0
        L_0x00c9:
            java.util.ArrayList r9 = r0.zzd
            int r9 = r9.size()
            if (r6 >= r9) goto L_0x00e5
            java.util.ArrayList r9 = r0.zzd
            java.lang.Object r9 = r9.get(r6)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r10 = "\\{\\\\an[1-9]\\}"
            boolean r10 = r9.matches(r10)
            if (r10 == 0) goto L_0x00e2
            goto L_0x00e6
        L_0x00e2:
            int r6 = r6 + 1
            goto L_0x00c9
        L_0x00e5:
            r9 = 0
        L_0x00e6:
            com.google.android.gms.internal.ads.zzaln r11 = new com.google.android.gms.internal.ads.zzaln
            com.google.android.gms.internal.ads.zzeg r6 = new com.google.android.gms.internal.ads.zzeg
            r6.<init>()
            r6.zzl(r3)
            if (r9 != 0) goto L_0x00f8
            com.google.android.gms.internal.ads.zzei r3 = r6.zzp()
            goto L_0x01d3
        L_0x00f8:
            int r3 = r9.hashCode()
            java.lang.String r10 = "{\\an9}"
            java.lang.String r12 = "{\\an7}"
            java.lang.String r13 = "{\\an3}"
            java.lang.String r14 = "{\\an1}"
            r15 = 2
            switch(r3) {
                case -685620710: goto L_0x0135;
                case -685620648: goto L_0x012d;
                case -685620617: goto L_0x0123;
                case -685620555: goto L_0x0119;
                case -685620524: goto L_0x0111;
                case -685620462: goto L_0x0109;
                default: goto L_0x0108;
            }
        L_0x0108:
            goto L_0x013d
        L_0x0109:
            boolean r3 = r9.equals(r10)
            if (r3 == 0) goto L_0x013d
            r3 = 5
            goto L_0x013e
        L_0x0111:
            boolean r3 = r9.equals(r12)
            if (r3 == 0) goto L_0x013d
            r3 = r15
            goto L_0x013e
        L_0x0119:
            java.lang.String r3 = "{\\an6}"
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x013d
            r3 = 4
            goto L_0x013e
        L_0x0123:
            java.lang.String r3 = "{\\an4}"
            boolean r3 = r9.equals(r3)
            if (r3 == 0) goto L_0x013d
            r3 = 1
            goto L_0x013e
        L_0x012d:
            boolean r3 = r9.equals(r13)
            if (r3 == 0) goto L_0x013d
            r3 = 3
            goto L_0x013e
        L_0x0135:
            boolean r3 = r9.equals(r14)
            if (r3 == 0) goto L_0x013d
            r3 = 0
            goto L_0x013e
        L_0x013d:
            r3 = -1
        L_0x013e:
            if (r3 == 0) goto L_0x0157
            r0 = 1
            if (r3 == r0) goto L_0x0157
            if (r3 == r15) goto L_0x0157
            r15 = 3
            if (r3 == r15) goto L_0x0152
            r15 = 4
            if (r3 == r15) goto L_0x0152
            r15 = 5
            if (r3 == r15) goto L_0x0152
            r6.zzi(r0)
            goto L_0x015b
        L_0x0152:
            r0 = 2
            r6.zzi(r0)
            goto L_0x015b
        L_0x0157:
            r0 = 0
            r6.zzi(r0)
        L_0x015b:
            int r0 = r9.hashCode()
            switch(r0) {
                case -685620710: goto L_0x018f;
                case -685620679: goto L_0x0185;
                case -685620648: goto L_0x017d;
                case -685620524: goto L_0x0175;
                case -685620493: goto L_0x016b;
                case -685620462: goto L_0x0163;
                default: goto L_0x0162;
            }
        L_0x0162:
            goto L_0x0197
        L_0x0163:
            boolean r0 = r9.equals(r10)
            if (r0 == 0) goto L_0x0197
            r0 = 5
            goto L_0x0198
        L_0x016b:
            java.lang.String r0 = "{\\an8}"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x0197
            r0 = 4
            goto L_0x0198
        L_0x0175:
            boolean r0 = r9.equals(r12)
            if (r0 == 0) goto L_0x0197
            r0 = 3
            goto L_0x0198
        L_0x017d:
            boolean r0 = r9.equals(r13)
            if (r0 == 0) goto L_0x0197
            r0 = 2
            goto L_0x0198
        L_0x0185:
            java.lang.String r0 = "{\\an2}"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x0197
            r0 = 1
            goto L_0x0198
        L_0x018f:
            boolean r0 = r9.equals(r14)
            if (r0 == 0) goto L_0x0197
            r0 = 0
            goto L_0x0198
        L_0x0197:
            r0 = -1
        L_0x0198:
            if (r0 == 0) goto L_0x01b4
            r3 = 1
            if (r0 == r3) goto L_0x01b4
            r9 = 2
            if (r0 == r9) goto L_0x01b2
            r9 = 3
            if (r0 == r9) goto L_0x01ad
            r9 = 4
            if (r0 == r9) goto L_0x01ad
            r9 = 5
            if (r0 == r9) goto L_0x01ad
            r6.zzf(r3)
            goto L_0x01b8
        L_0x01ad:
            r0 = 0
            r6.zzf(r0)
            goto L_0x01b8
        L_0x01b2:
            r0 = r9
            goto L_0x01b5
        L_0x01b4:
            r0 = 2
        L_0x01b5:
            r6.zzf(r0)
        L_0x01b8:
            int r0 = r6.zzb()
            float r0 = zzb(r0)
            r6.zzh(r0)
            int r0 = r6.zza()
            float r0 = zzb(r0)
            r3 = 0
            r6.zze(r0, r3)
            com.google.android.gms.internal.ads.zzei r3 = r6.zzp()
        L_0x01d3:
            long r9 = r4 - r7
            com.google.android.gms.internal.ads.zzgbc r6 = com.google.android.gms.internal.ads.zzgbc.zzn(r3)
            r5 = r11
            r5.<init>(r6, r7, r9)
            r0 = r21
            r0.zza(r11)
            goto L_0x01fd
        L_0x01e3:
            r0 = r21
            java.lang.String r4 = "Skipping invalid timing: "
            java.lang.String r3 = r4.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r2, r3)
            goto L_0x01fd
        L_0x01ef:
            r0 = r21
            java.lang.String r4 = "Skipping invalid index: "
            java.lang.String r3 = r4.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r2, r3)
            goto L_0x01fd
        L_0x01fb:
            r0 = r21
        L_0x01fd:
            r0 = r16
            goto L_0x001e
        L_0x0201:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamp.zza(byte[], int, int, com.google.android.gms.internal.ads.zzalu, com.google.android.gms.internal.ads.zzev):void");
    }
}
