package com.google.android.gms.internal.ads;

import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzamv implements zzalv {
    static final Pattern zza = Pattern.compile("^([-+]?\\d+\\.?\\d*?)%$");
    static final Pattern zzb = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern zzc = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern zzd = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern zze = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern zzf = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern zzg = Pattern.compile("^(\\d+) (\\d+)$");
    private static final zzamt zzh = new zzamt(30.0f, 1, 1);
    private final XmlPullParserFactory zzi;

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c7, code lost:
        if (r13.equals("s") != false) goto L_0x00f3;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0110  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long zzc(java.lang.String r13, com.google.android.gms.internal.ads.zzamt r14) throws com.google.android.gms.internal.ads.zzalr {
        /*
            java.util.regex.Pattern r0 = zzc
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r1 == 0) goto L_0x007c
            java.lang.String r13 = r0.group(r8)
            r13.getClass()
            long r8 = java.lang.Long.parseLong(r13)
            r10 = 3600(0xe10, double:1.7786E-320)
            long r8 = r8 * r10
            java.lang.String r13 = r0.group(r7)
            r13.getClass()
            double r7 = (double) r8
            long r9 = java.lang.Long.parseLong(r13)
            r11 = 60
            long r9 = r9 * r11
            java.lang.String r13 = r0.group(r6)
            r13.getClass()
            double r9 = (double) r9
            double r7 = r7 + r9
            long r9 = java.lang.Long.parseLong(r13)
            double r9 = (double) r9
            java.lang.String r13 = r0.group(r5)
            r5 = 0
            if (r13 == 0) goto L_0x004e
            double r11 = java.lang.Double.parseDouble(r13)
            goto L_0x004f
        L_0x004e:
            r11 = r5
        L_0x004f:
            double r7 = r7 + r9
            java.lang.String r13 = r0.group(r4)
            if (r13 == 0) goto L_0x0060
            long r9 = java.lang.Long.parseLong(r13)
            float r13 = (float) r9
            float r1 = r14.zza
            float r13 = r13 / r1
            double r9 = (double) r13
            goto L_0x0061
        L_0x0060:
            r9 = r5
        L_0x0061:
            double r7 = r7 + r11
            r13 = 6
            java.lang.String r13 = r0.group(r13)
            if (r13 == 0) goto L_0x0077
            long r0 = java.lang.Long.parseLong(r13)
            double r0 = (double) r0
            int r13 = r14.zzb
            double r4 = (double) r13
            float r13 = r14.zza
            double r13 = (double) r13
            double r0 = r0 / r4
            double r5 = r0 / r13
        L_0x0077:
            double r7 = r7 + r9
            double r7 = r7 + r5
            double r7 = r7 * r2
            long r13 = (long) r7
            return r13
        L_0x007c:
            java.util.regex.Pattern r0 = zzd
            java.util.regex.Matcher r0 = r0.matcher(r13)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x0119
            java.lang.String r13 = r0.group(r8)
            r13.getClass()
            double r9 = java.lang.Double.parseDouble(r13)
            java.lang.String r13 = r0.group(r7)
            r13.getClass()
            int r0 = r13.hashCode()
            r1 = 102(0x66, float:1.43E-43)
            if (r0 == r1) goto L_0x00e8
            r1 = 104(0x68, float:1.46E-43)
            if (r0 == r1) goto L_0x00de
            r1 = 109(0x6d, float:1.53E-43)
            if (r0 == r1) goto L_0x00d4
            r1 = 3494(0xda6, float:4.896E-42)
            if (r0 == r1) goto L_0x00ca
            r1 = 115(0x73, float:1.61E-43)
            if (r0 == r1) goto L_0x00c1
            r1 = 116(0x74, float:1.63E-43)
            if (r0 == r1) goto L_0x00b7
            goto L_0x00f2
        L_0x00b7:
            java.lang.String r0 = "t"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            r7 = r4
            goto L_0x00f3
        L_0x00c1:
            java.lang.String r0 = "s"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            goto L_0x00f3
        L_0x00ca:
            java.lang.String r0 = "ms"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            r7 = r6
            goto L_0x00f3
        L_0x00d4:
            java.lang.String r0 = "m"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            r7 = r8
            goto L_0x00f3
        L_0x00de:
            java.lang.String r0 = "h"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            r7 = 0
            goto L_0x00f3
        L_0x00e8:
            java.lang.String r0 = "f"
            boolean r13 = r13.equals(r0)
            if (r13 == 0) goto L_0x00f2
            r7 = r5
            goto L_0x00f3
        L_0x00f2:
            r7 = -1
        L_0x00f3:
            if (r7 == 0) goto L_0x0110
            if (r7 == r8) goto L_0x010d
            if (r7 == r6) goto L_0x0106
            if (r7 == r5) goto L_0x0102
            if (r7 == r4) goto L_0x00fe
            goto L_0x0116
        L_0x00fe:
            int r13 = r14.zzc
            double r13 = (double) r13
            goto L_0x010b
        L_0x0102:
            float r13 = r14.zza
            double r13 = (double) r13
            goto L_0x010b
        L_0x0106:
            r13 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x010b:
            double r9 = r9 / r13
            goto L_0x0116
        L_0x010d:
            r13 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0115
        L_0x0110:
            r13 = 4660134898793709568(0x40ac200000000000, double:3600.0)
        L_0x0115:
            double r9 = r9 * r13
        L_0x0116:
            double r9 = r9 * r2
            long r13 = (long) r9
            return r13
        L_0x0119:
            java.lang.String r13 = java.lang.String.valueOf(r13)
            com.google.android.gms.internal.ads.zzalr r14 = new com.google.android.gms.internal.ads.zzalr
            java.lang.String r0 = "Malformed time expression: "
            java.lang.String r13 = r0.concat(r13)
            r14.<init>(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamv.zzc(java.lang.String, com.google.android.gms.internal.ads.zzamt):long");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.text.Layout.Alignment zzd(java.lang.String r5) {
        /*
            java.lang.String r5 = com.google.android.gms.internal.ads.zzfxm.zza(r5)
            int r0 = r5.hashCode()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1364013995: goto L_0x0038;
                case 100571: goto L_0x002e;
                case 3317767: goto L_0x0024;
                case 108511772: goto L_0x001a;
                case 109757538: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0042
        L_0x0010:
            java.lang.String r0 = "start"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0042
            r5 = r4
            goto L_0x0043
        L_0x001a:
            java.lang.String r0 = "right"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0042
            r5 = r3
            goto L_0x0043
        L_0x0024:
            java.lang.String r0 = "left"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0042
            r5 = 0
            goto L_0x0043
        L_0x002e:
            java.lang.String r0 = "end"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0042
            r5 = r2
            goto L_0x0043
        L_0x0038:
            java.lang.String r0 = "center"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x0042
            r5 = r1
            goto L_0x0043
        L_0x0042:
            r5 = -1
        L_0x0043:
            if (r5 == 0) goto L_0x0055
            if (r5 == r4) goto L_0x0055
            if (r5 == r3) goto L_0x0052
            if (r5 == r2) goto L_0x0052
            if (r5 == r1) goto L_0x004f
            r5 = 0
            return r5
        L_0x004f:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_CENTER
            return r5
        L_0x0052:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            return r5
        L_0x0055:
            android.text.Layout$Alignment r5 = android.text.Layout.Alignment.ALIGN_NORMAL
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamv.zzd(java.lang.String):android.text.Layout$Alignment");
    }

    private static zzamy zze(zzamy zzamy) {
        return zzamy == null ? new zzamy() : zzamy;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.ads.zzamy zzf(org.xmlpull.v1.XmlPullParser r16, com.google.android.gms.internal.ads.zzamy r17) {
        /*
            r1 = r16
            int r2 = r16.getAttributeCount()
            r3 = 0
            r0 = r17
            r4 = r3
        L_0x000a:
            if (r4 >= r2) goto L_0x03ec
            java.lang.String r5 = r1.getAttributeValue(r4)
            java.lang.String r6 = r1.getAttributeName(r4)
            int r7 = r6.hashCode()
            r8 = 5
            r9 = 4
            r10 = -1
            r11 = 3
            r12 = 2
            r13 = 1
            switch(r7) {
                case -1550943582: goto L_0x00bc;
                case -1224696685: goto L_0x00b2;
                case -1065511464: goto L_0x00a8;
                case -879295043: goto L_0x009d;
                case -734428249: goto L_0x0093;
                case 3355: goto L_0x0089;
                case 3511770: goto L_0x007e;
                case 94842723: goto L_0x0074;
                case 109403361: goto L_0x0069;
                case 110138194: goto L_0x005d;
                case 365601008: goto L_0x0052;
                case 921125321: goto L_0x0046;
                case 1115953443: goto L_0x003a;
                case 1287124693: goto L_0x002f;
                case 1754920356: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x00c6
        L_0x0023:
            java.lang.String r7 = "multiRowAlign"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 8
            goto L_0x00c7
        L_0x002f:
            java.lang.String r7 = "backgroundColor"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r13
            goto L_0x00c7
        L_0x003a:
            java.lang.String r7 = "rubyPosition"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 11
            goto L_0x00c7
        L_0x0046:
            java.lang.String r7 = "textEmphasis"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 13
            goto L_0x00c7
        L_0x0052:
            java.lang.String r7 = "fontSize"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r9
            goto L_0x00c7
        L_0x005d:
            java.lang.String r7 = "textCombine"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 9
            goto L_0x00c7
        L_0x0069:
            java.lang.String r7 = "shear"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 14
            goto L_0x00c7
        L_0x0074:
            java.lang.String r7 = "color"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r12
            goto L_0x00c7
        L_0x007e:
            java.lang.String r7 = "ruby"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 10
            goto L_0x00c7
        L_0x0089:
            java.lang.String r7 = "id"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r3
            goto L_0x00c7
        L_0x0093:
            java.lang.String r7 = "fontWeight"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r8
            goto L_0x00c7
        L_0x009d:
            java.lang.String r7 = "textDecoration"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 12
            goto L_0x00c7
        L_0x00a8:
            java.lang.String r7 = "textAlign"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 7
            goto L_0x00c7
        L_0x00b2:
            java.lang.String r7 = "fontFamily"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = r11
            goto L_0x00c7
        L_0x00bc:
            java.lang.String r7 = "fontStyle"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x00c6
            r6 = 6
            goto L_0x00c7
        L_0x00c6:
            r6 = r10
        L_0x00c7:
            r7 = 0
            java.lang.String r14 = "TtmlParser"
            switch(r6) {
                case 0: goto L_0x03d4;
                case 1: goto L_0x03ba;
                case 2: goto L_0x03a0;
                case 3: goto L_0x0398;
                case 4: goto L_0x02b7;
                case 5: goto L_0x02a8;
                case 6: goto L_0x0299;
                case 7: goto L_0x028c;
                case 8: goto L_0x027f;
                case 9: goto L_0x0241;
                case 10: goto L_0x01c8;
                case 11: goto L_0x018a;
                case 12: goto L_0x0129;
                case 13: goto L_0x011c;
                case 14: goto L_0x00cf;
                default: goto L_0x00cd;
            }
        L_0x00cd:
            goto L_0x03e7
        L_0x00cf:
            com.google.android.gms.internal.ads.zzamy r6 = zze(r0)
            java.util.regex.Pattern r0 = zza
            java.util.regex.Matcher r0 = r0.matcher(r5)
            boolean r8 = r0.matches()
            r9 = 2139095039(0x7f7fffff, float:3.4028235E38)
            if (r8 != 0) goto L_0x00f0
            java.lang.String r0 = java.lang.String.valueOf(r5)
            java.lang.String r5 = "Invalid value for shear: "
            java.lang.String r0 = r5.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r14, r0)
            goto L_0x0116
        L_0x00f0:
            java.lang.String r0 = r0.group(r13)     // Catch:{ NumberFormatException -> 0x0108 }
            if (r0 == 0) goto L_0x0107
            float r0 = java.lang.Float.parseFloat(r0)     // Catch:{ NumberFormatException -> 0x0108 }
            r7 = -1027080192(0xffffffffc2c80000, float:-100.0)
            float r0 = java.lang.Math.max(r7, r0)     // Catch:{ NumberFormatException -> 0x0108 }
            r7 = 1120403456(0x42c80000, float:100.0)
            float r9 = java.lang.Math.min(r7, r0)     // Catch:{ NumberFormatException -> 0x0108 }
            goto L_0x0116
        L_0x0107:
            throw r7     // Catch:{ NumberFormatException -> 0x0108 }
        L_0x0108:
            r0 = move-exception
            java.lang.String r5 = java.lang.String.valueOf(r5)
            java.lang.String r7 = "Failed to parse shear: "
            java.lang.String r5 = r7.concat(r5)
            com.google.android.gms.internal.ads.zzfk.zzg(r14, r5, r0)
        L_0x0116:
            r6.zzy(r9)
            r0 = r6
            goto L_0x03e7
        L_0x011c:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            com.google.android.gms.internal.ads.zzamr r5 = com.google.android.gms.internal.ads.zzamr.zza(r5)
            r0.zzB(r5)
            goto L_0x03e7
        L_0x0129:
            java.lang.String r5 = com.google.android.gms.internal.ads.zzfxm.zza(r5)
            int r6 = r5.hashCode()
            switch(r6) {
                case -1461280213: goto L_0x0153;
                case -1026963764: goto L_0x0149;
                case 913457136: goto L_0x013f;
                case 1679736913: goto L_0x0135;
                default: goto L_0x0134;
            }
        L_0x0134:
            goto L_0x015c
        L_0x0135:
            java.lang.String r6 = "linethrough"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x015c
            r10 = r3
            goto L_0x015c
        L_0x013f:
            java.lang.String r6 = "nolinethrough"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x015c
            r10 = r13
            goto L_0x015c
        L_0x0149:
            java.lang.String r6 = "underline"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x015c
            r10 = r12
            goto L_0x015c
        L_0x0153:
            java.lang.String r6 = "nounderline"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x015c
            r10 = r11
        L_0x015c:
            if (r10 == 0) goto L_0x0181
            if (r10 == r13) goto L_0x0178
            if (r10 == r12) goto L_0x016f
            if (r10 == r11) goto L_0x0166
            goto L_0x03e7
        L_0x0166:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzC(r3)
            goto L_0x03e7
        L_0x016f:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzC(r13)
            goto L_0x03e7
        L_0x0178:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzu(r3)
            goto L_0x03e7
        L_0x0181:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzu(r13)
            goto L_0x03e7
        L_0x018a:
            java.lang.String r5 = com.google.android.gms.internal.ads.zzfxm.zza(r5)
            int r6 = r5.hashCode()
            r7 = -1392885889(0xffffffffacfa3f7f, float:-7.112477E-12)
            if (r6 == r7) goto L_0x01a7
            r7 = 92734940(0x58705dc, float:1.2697491E-35)
            if (r6 == r7) goto L_0x019d
            goto L_0x01b0
        L_0x019d:
            java.lang.String r6 = "after"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01b0
            r10 = r13
            goto L_0x01b0
        L_0x01a7:
            java.lang.String r6 = "before"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x01b0
            r10 = r3
        L_0x01b0:
            if (r10 == 0) goto L_0x01bf
            if (r10 == r13) goto L_0x01b6
            goto L_0x03e7
        L_0x01b6:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzw(r12)
            goto L_0x03e7
        L_0x01bf:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzw(r13)
            goto L_0x03e7
        L_0x01c8:
            java.lang.String r5 = com.google.android.gms.internal.ads.zzfxm.zza(r5)
            int r6 = r5.hashCode()
            switch(r6) {
                case -618561360: goto L_0x0206;
                case -410956671: goto L_0x01fc;
                case -250518009: goto L_0x01f2;
                case -136074796: goto L_0x01e8;
                case 3016401: goto L_0x01de;
                case 3556653: goto L_0x01d4;
                default: goto L_0x01d3;
            }
        L_0x01d3:
            goto L_0x020f
        L_0x01d4:
            java.lang.String r6 = "text"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r11
            goto L_0x020f
        L_0x01de:
            java.lang.String r6 = "base"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r13
            goto L_0x020f
        L_0x01e8:
            java.lang.String r6 = "textContainer"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r9
            goto L_0x020f
        L_0x01f2:
            java.lang.String r6 = "delimiter"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r8
            goto L_0x020f
        L_0x01fc:
            java.lang.String r6 = "container"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r3
            goto L_0x020f
        L_0x0206:
            java.lang.String r6 = "baseContainer"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x020f
            r10 = r12
        L_0x020f:
            if (r10 == 0) goto L_0x0238
            if (r10 == r13) goto L_0x022f
            if (r10 == r12) goto L_0x022f
            if (r10 == r11) goto L_0x0226
            if (r10 == r9) goto L_0x0226
            if (r10 == r8) goto L_0x021d
            goto L_0x03e7
        L_0x021d:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzx(r9)
            goto L_0x03e7
        L_0x0226:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzx(r11)
            goto L_0x03e7
        L_0x022f:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzx(r12)
            goto L_0x03e7
        L_0x0238:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzx(r13)
            goto L_0x03e7
        L_0x0241:
            java.lang.String r5 = com.google.android.gms.internal.ads.zzfxm.zza(r5)
            int r6 = r5.hashCode()
            r7 = 96673(0x179a1, float:1.35468E-40)
            if (r6 == r7) goto L_0x025e
            r7 = 3387192(0x33af38, float:4.746467E-39)
            if (r6 == r7) goto L_0x0254
            goto L_0x0267
        L_0x0254:
            java.lang.String r6 = "none"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0267
            r10 = r3
            goto L_0x0267
        L_0x025e:
            java.lang.String r6 = "all"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L_0x0267
            r10 = r13
        L_0x0267:
            if (r10 == 0) goto L_0x0276
            if (r10 == r13) goto L_0x026d
            goto L_0x03e7
        L_0x026d:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzA(r13)
            goto L_0x03e7
        L_0x0276:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzA(r3)
            goto L_0x03e7
        L_0x027f:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            android.text.Layout$Alignment r5 = zzd(r5)
            r0.zzv(r5)
            goto L_0x03e7
        L_0x028c:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            android.text.Layout$Alignment r5 = zzd(r5)
            r0.zzz(r5)
            goto L_0x03e7
        L_0x0299:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            java.lang.String r6 = "italic"
            boolean r5 = r6.equalsIgnoreCase(r5)
            r0.zzt(r5)
            goto L_0x03e7
        L_0x02a8:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            java.lang.String r6 = "bold"
            boolean r5 = r6.equalsIgnoreCase(r5)
            r0.zzn(r5)
            goto L_0x03e7
        L_0x02b7:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r6 = "\\s+"
            int r8 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ zzalr -> 0x038a }
            java.lang.String[] r6 = r5.split(r6, r10)     // Catch:{ zzalr -> 0x038a }
            int r8 = r6.length     // Catch:{ zzalr -> 0x038a }
            if (r8 != r13) goto L_0x02cd
            java.util.regex.Pattern r6 = zze     // Catch:{ zzalr -> 0x038a }
            java.util.regex.Matcher r6 = r6.matcher(r5)     // Catch:{ zzalr -> 0x038a }
            goto L_0x02dc
        L_0x02cd:
            if (r8 != r12) goto L_0x036e
            java.util.regex.Pattern r8 = zze     // Catch:{ zzalr -> 0x038a }
            r6 = r6[r13]     // Catch:{ zzalr -> 0x038a }
            java.util.regex.Matcher r6 = r8.matcher(r6)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r8 = "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first."
            com.google.android.gms.internal.ads.zzfk.zzf(r14, r8)     // Catch:{ zzalr -> 0x038a }
        L_0x02dc:
            boolean r8 = r6.matches()     // Catch:{ zzalr -> 0x038a }
            java.lang.String r9 = "'."
            if (r8 == 0) goto L_0x0354
            java.lang.String r8 = r6.group(r11)     // Catch:{ zzalr -> 0x038a }
            if (r8 == 0) goto L_0x0353
            int r15 = r8.hashCode()     // Catch:{ zzalr -> 0x038a }
            r3 = 37
            if (r15 == r3) goto L_0x030f
            r3 = 3240(0xca8, float:4.54E-42)
            if (r15 == r3) goto L_0x0305
            r3 = 3592(0xe08, float:5.033E-42)
            if (r15 == r3) goto L_0x02fb
            goto L_0x0318
        L_0x02fb:
            java.lang.String r3 = "px"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x0318
            r10 = 0
            goto L_0x0318
        L_0x0305:
            java.lang.String r3 = "em"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x0318
            r10 = r13
            goto L_0x0318
        L_0x030f:
            java.lang.String r3 = "%"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x0318
            r10 = r12
        L_0x0318:
            if (r10 == 0) goto L_0x0340
            if (r10 == r13) goto L_0x033c
            if (r10 != r12) goto L_0x0322
            r0.zzr(r11)     // Catch:{ zzalr -> 0x038a }
            goto L_0x0343
        L_0x0322:
            com.google.android.gms.internal.ads.zzalr r3 = new com.google.android.gms.internal.ads.zzalr     // Catch:{ zzalr -> 0x038a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ zzalr -> 0x038a }
            r6.<init>()     // Catch:{ zzalr -> 0x038a }
            java.lang.String r7 = "Invalid unit for fontSize: '"
            r6.append(r7)     // Catch:{ zzalr -> 0x038a }
            r6.append(r8)     // Catch:{ zzalr -> 0x038a }
            r6.append(r9)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r6 = r6.toString()     // Catch:{ zzalr -> 0x038a }
            r3.<init>(r6)     // Catch:{ zzalr -> 0x038a }
            throw r3     // Catch:{ zzalr -> 0x038a }
        L_0x033c:
            r0.zzr(r12)     // Catch:{ zzalr -> 0x038a }
            goto L_0x0343
        L_0x0340:
            r0.zzr(r13)     // Catch:{ zzalr -> 0x038a }
        L_0x0343:
            java.lang.String r3 = r6.group(r13)     // Catch:{ zzalr -> 0x038a }
            if (r3 == 0) goto L_0x0352
            float r3 = java.lang.Float.parseFloat(r3)     // Catch:{ zzalr -> 0x038a }
            r0.zzq(r3)     // Catch:{ zzalr -> 0x038a }
            goto L_0x03e7
        L_0x0352:
            throw r7     // Catch:{ zzalr -> 0x038a }
        L_0x0353:
            throw r7     // Catch:{ zzalr -> 0x038a }
        L_0x0354:
            com.google.android.gms.internal.ads.zzalr r3 = new com.google.android.gms.internal.ads.zzalr     // Catch:{ zzalr -> 0x038a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ zzalr -> 0x038a }
            r6.<init>()     // Catch:{ zzalr -> 0x038a }
            java.lang.String r7 = "Invalid expression for fontSize: '"
            r6.append(r7)     // Catch:{ zzalr -> 0x038a }
            r6.append(r5)     // Catch:{ zzalr -> 0x038a }
            r6.append(r9)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r6 = r6.toString()     // Catch:{ zzalr -> 0x038a }
            r3.<init>(r6)     // Catch:{ zzalr -> 0x038a }
            throw r3     // Catch:{ zzalr -> 0x038a }
        L_0x036e:
            com.google.android.gms.internal.ads.zzalr r3 = new com.google.android.gms.internal.ads.zzalr     // Catch:{ zzalr -> 0x038a }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ zzalr -> 0x038a }
            r6.<init>()     // Catch:{ zzalr -> 0x038a }
            java.lang.String r7 = "Invalid number of entries for fontSize: "
            r6.append(r7)     // Catch:{ zzalr -> 0x038a }
            r6.append(r8)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r7 = "."
            r6.append(r7)     // Catch:{ zzalr -> 0x038a }
            java.lang.String r6 = r6.toString()     // Catch:{ zzalr -> 0x038a }
            r3.<init>(r6)     // Catch:{ zzalr -> 0x038a }
            throw r3     // Catch:{ zzalr -> 0x038a }
        L_0x038a:
            java.lang.String r3 = java.lang.String.valueOf(r5)
            java.lang.String r5 = "Failed parsing fontSize value: "
            java.lang.String r3 = r5.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r14, r3)
            goto L_0x03e7
        L_0x0398:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzp(r5)
            goto L_0x03e7
        L_0x03a0:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            int r3 = com.google.android.gms.internal.ads.zzet.zzb(r5)     // Catch:{ IllegalArgumentException -> 0x03ac }
            r0.zzo(r3)     // Catch:{ IllegalArgumentException -> 0x03ac }
            goto L_0x03e7
        L_0x03ac:
            java.lang.String r3 = java.lang.String.valueOf(r5)
            java.lang.String r5 = "Failed parsing color value: "
            java.lang.String r3 = r5.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r14, r3)
            goto L_0x03e7
        L_0x03ba:
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            int r3 = com.google.android.gms.internal.ads.zzet.zzb(r5)     // Catch:{ IllegalArgumentException -> 0x03c6 }
            r0.zzm(r3)     // Catch:{ IllegalArgumentException -> 0x03c6 }
            goto L_0x03e7
        L_0x03c6:
            java.lang.String r3 = java.lang.String.valueOf(r5)
            java.lang.String r5 = "Failed parsing background value: "
            java.lang.String r3 = r5.concat(r3)
            com.google.android.gms.internal.ads.zzfk.zzf(r14, r3)
            goto L_0x03e7
        L_0x03d4:
            java.lang.String r3 = r16.getName()
            java.lang.String r6 = "style"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L_0x03e7
            com.google.android.gms.internal.ads.zzamy r0 = zze(r0)
            r0.zzs(r5)
        L_0x03e7:
            int r4 = r4 + 1
            r3 = 0
            goto L_0x000a
        L_0x03ec:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamv.zzf(org.xmlpull.v1.XmlPullParser, com.google.android.gms.internal.ads.zzamy):com.google.android.gms.internal.ads.zzamy");
    }

    private static String[] zzg(String str) {
        String trim = str.trim();
        if (trim.isEmpty()) {
            return new String[0];
        }
        int i = zzgd.zza;
        return trim.split("\\s+", -1);
    }

    public final void zza(byte[] bArr, int i, int i2, zzalu zzalu, zzev zzev) {
        zzalp.zza(zzb(bArr, i, i2), zzalu, zzev);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v8, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v17, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v18, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v19, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v20, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v23, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v26, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v27, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v64, resolved type: int} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v65, resolved type: int} */
    /* JADX WARNING: type inference failed for: r5v14 */
    /* JADX WARNING: type inference failed for: r5v15 */
    /* JADX WARNING: type inference failed for: r5v16 */
    /* JADX WARNING: type inference failed for: r5v59 */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01e8 A[SYNTHETIC, Splitter:B:100:0x01e8] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x027b A[Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }, LOOP:1: B:133:0x027b->B:308:0x04fd, LOOP_START, PHI: r2 r5 r11 
      PHI: (r2v25 java.lang.String) = (r2v10 java.lang.String), (r2v27 java.lang.String) binds: [B:132:0x0279, B:308:0x04fd] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r5v41 java.util.HashMap) = (r5v1 java.util.HashMap), (r5v42 java.util.HashMap) binds: [B:132:0x0279, B:308:0x04fd] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r11v33 com.google.android.gms.internal.ads.zzamt) = (r11v5 com.google.android.gms.internal.ads.zzamt), (r11v34 com.google.android.gms.internal.ads.zzamt) binds: [B:132:0x0279, B:308:0x04fd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x042b  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0436  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x044f A[Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x048a  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x04ec A[Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:308:0x04fd A[LOOP:1: B:133:0x027b->B:308:0x04fd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0503  */
    /* JADX WARNING: Removed duplicated region for block: B:478:0x04f9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x018b A[Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zzalq zzb(byte[] r42, int r43, int r44) {
        /*
            r41 = this;
            java.lang.String r1 = ""
            java.lang.String r2 = "http://www.w3.org/ns/ttml#parameter"
            r3 = r41
            org.xmlpull.v1.XmlPullParserFactory r4 = r3.zzi     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            org.xmlpull.v1.XmlPullParser r4 = r4.newPullParser()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r5.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.HashMap r6 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r6.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r7.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzamw r15 = new com.google.android.gms.internal.ads.zzamw     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r9 = ""
            r17 = -8388609(0xffffffffff7fffff, float:-3.4028235E38)
            r18 = -2147483648(0xffffffff80000000, float:-0.0)
            r8 = r15
            r10 = r17
            r11 = r17
            r12 = r18
            r13 = r18
            r14 = r17
            r19 = r15
            r15 = r17
            r16 = r18
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r8 = r19
            r6.put(r1, r8)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.io.ByteArrayInputStream r8 = new java.io.ByteArrayInputStream     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9 = r42
            r10 = r43
            r11 = r44
            r8.<init>(r9, r10, r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9 = 0
            r4.setInput(r8, r9)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.ArrayDeque r8 = new java.util.ArrayDeque     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r8.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r10 = r4.getEventType()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzamt r11 = zzh     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r14 = r9
            r16 = r14
            r15 = 0
            r17 = 15
        L_0x005d:
            r12 = 1
            if (r10 == r12) goto L_0x06c9
            java.lang.Object r18 = r8.peek()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9 = r18
            com.google.android.gms.internal.ads.zzams r9 = (com.google.android.gms.internal.ads.zzams) r9     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r12 = 2
            if (r15 != 0) goto L_0x069b
            java.lang.String r13 = r4.getName()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r20 = r1
            java.lang.String r1 = "tt"
            if (r10 != r12) goto L_0x0655
            boolean r10 = r1.equals(r13)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r21 = 1065353216(0x3f800000, float:1.0)
            java.lang.String r12 = "TtmlParser"
            if (r10 == 0) goto L_0x01ce
            java.lang.String r10 = "frameRate"
            java.lang.String r10 = r4.getAttributeValue(r2, r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r10 == 0) goto L_0x008c
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x008e
        L_0x008c:
            r10 = 30
        L_0x008e:
            java.lang.String r11 = "frameRateMultiplier"
            java.lang.String r11 = r4.getAttributeValue(r2, r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r3 = " "
            if (r11 == 0) goto L_0x00c2
            int r16 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r23 = r14
            r14 = -1
            java.lang.String[] r11 = r11.split(r3, r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r14 = r11.length     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r24 = r15
            r15 = 2
            if (r14 != r15) goto L_0x00a9
            r14 = 1
            goto L_0x00aa
        L_0x00a9:
            r14 = 0
        L_0x00aa:
            java.lang.String r15 = "frameRateMultiplier doesn't have 2 parts"
            com.google.android.gms.internal.ads.zzeq.zze(r14, r15)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r14 = 0
            r15 = r11[r14]     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r15 = java.lang.Integer.parseInt(r15)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            float r15 = (float) r15     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r16 = 1
            r11 = r11[r16]     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            float r11 = (float) r11     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            float r15 = r15 / r11
            goto L_0x00c9
        L_0x00c2:
            r23 = r14
            r24 = r15
            r14 = 0
            r15 = r21
        L_0x00c9:
            com.google.android.gms.internal.ads.zzamt r11 = zzh     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r14 = r11.zzb     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r16 = r14
            java.lang.String r14 = "subFrameRate"
            java.lang.String r14 = r4.getAttributeValue(r2, r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r14 == 0) goto L_0x00dc
            int r14 = java.lang.Integer.parseInt(r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x00de
        L_0x00dc:
            r14 = r16
        L_0x00de:
            int r11 = r11.zzc     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r16 = r11
            java.lang.String r11 = "tickRate"
            java.lang.String r11 = r4.getAttributeValue(r2, r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r11 == 0) goto L_0x00f1
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r25 = r8
            goto L_0x00f5
        L_0x00f1:
            r25 = r8
            r11 = r16
        L_0x00f5:
            com.google.android.gms.internal.ads.zzamt r8 = new com.google.android.gms.internal.ads.zzamt     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            float r10 = (float) r10     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            float r10 = r10 * r15
            r8.<init>(r10, r14, r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r10 = "cellResolution"
            java.lang.String r10 = r4.getAttributeValue(r2, r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r10 != 0) goto L_0x010c
        L_0x0104:
            r26 = r2
            r16 = r8
        L_0x0108:
            r15 = 15
            goto L_0x0181
        L_0x010c:
            java.util.regex.Pattern r11 = zzg     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.regex.Matcher r11 = r11.matcher(r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r14 = r11.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r14 != 0) goto L_0x0122
            java.lang.String r3 = "Ignoring malformed cell resolution: "
            java.lang.String r3 = r3.concat(r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r3)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0104
        L_0x0122:
            r14 = 1
            java.lang.String r15 = r11.group(r14)     // Catch:{ NumberFormatException -> 0x0173 }
            if (r15 == 0) goto L_0x016d
            int r14 = java.lang.Integer.parseInt(r15)     // Catch:{ NumberFormatException -> 0x0173 }
            r15 = 2
            java.lang.String r11 = r11.group(r15)     // Catch:{ NumberFormatException -> 0x0173 }
            if (r11 == 0) goto L_0x0167
            int r11 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x0173 }
            if (r14 == 0) goto L_0x0146
            if (r11 == 0) goto L_0x0141
            r26 = r2
            r15 = r11
            r11 = 1
            goto L_0x014a
        L_0x0141:
            r26 = r2
            r11 = 0
            r15 = 0
            goto L_0x014a
        L_0x0146:
            r26 = r2
            r15 = r11
            r11 = 0
        L_0x014a:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x0175 }
            r2.<init>()     // Catch:{ NumberFormatException -> 0x0175 }
            r16 = r8
            java.lang.String r8 = "Invalid cell resolution "
            r2.append(r8)     // Catch:{ NumberFormatException -> 0x0177 }
            r2.append(r14)     // Catch:{ NumberFormatException -> 0x0177 }
            r2.append(r3)     // Catch:{ NumberFormatException -> 0x0177 }
            r2.append(r15)     // Catch:{ NumberFormatException -> 0x0177 }
            java.lang.String r2 = r2.toString()     // Catch:{ NumberFormatException -> 0x0177 }
            com.google.android.gms.internal.ads.zzeq.zze(r11, r2)     // Catch:{ NumberFormatException -> 0x0177 }
            goto L_0x0181
        L_0x0167:
            r26 = r2
            r16 = r8
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x0177 }
        L_0x016d:
            r26 = r2
            r16 = r8
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x0177 }
        L_0x0173:
            r26 = r2
        L_0x0175:
            r16 = r8
        L_0x0177:
            java.lang.String r2 = "Ignoring malformed cell resolution: "
            java.lang.String r2 = r2.concat(r10)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0108
        L_0x0181:
            java.lang.String r2 = "extent"
            java.lang.String r2 = com.google.android.gms.internal.ads.zzge.zza(r4, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r2 != 0) goto L_0x018b
        L_0x0189:
            r10 = 0
            goto L_0x01cb
        L_0x018b:
            java.util.regex.Pattern r3 = zzf     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.regex.Matcher r3 = r3.matcher(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r8 = r3.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r8 != 0) goto L_0x01a1
            java.lang.String r3 = "Ignoring non-pixel tts extent: "
            java.lang.String r2 = r3.concat(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0189
        L_0x01a1:
            r8 = 1
            java.lang.String r10 = r3.group(r8)     // Catch:{ NumberFormatException -> 0x01c1 }
            if (r10 == 0) goto L_0x01bf
            int r8 = java.lang.Integer.parseInt(r10)     // Catch:{ NumberFormatException -> 0x01c1 }
            r10 = 2
            java.lang.String r3 = r3.group(r10)     // Catch:{ NumberFormatException -> 0x01c1 }
            if (r3 == 0) goto L_0x01bd
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x01c1 }
            com.google.android.gms.internal.ads.zzamu r10 = new com.google.android.gms.internal.ads.zzamu     // Catch:{ NumberFormatException -> 0x01c1 }
            r10.<init>(r8, r3)     // Catch:{ NumberFormatException -> 0x01c1 }
            goto L_0x01cb
        L_0x01bd:
            r3 = 0
            throw r3     // Catch:{ NumberFormatException -> 0x01c1 }
        L_0x01bf:
            r3 = 0
            throw r3     // Catch:{ NumberFormatException -> 0x01c1 }
        L_0x01c1:
            java.lang.String r3 = "Ignoring malformed tts extent: "
            java.lang.String r2 = r3.concat(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0189
        L_0x01cb:
            r11 = r16
            goto L_0x01da
        L_0x01ce:
            r26 = r2
            r25 = r8
            r23 = r14
            r24 = r15
            r10 = r16
            r15 = r17
        L_0x01da:
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r2 = "metadata"
            java.lang.String r3 = "region"
            java.lang.String r8 = "head"
            java.lang.String r14 = "style"
            if (r1 != 0) goto L_0x0275
            boolean r1 = r13.equals(r8)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "body"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "div"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "p"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "span"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "br"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            boolean r1 = r13.equals(r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "styling"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "layout"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            boolean r1 = r13.equals(r3)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            boolean r1 = r13.equals(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "image"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "data"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 != 0) goto L_0x0275
            java.lang.String r1 = "information"
            boolean r1 = r13.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x0251
            goto L_0x0275
        L_0x0251:
            java.lang.String r1 = r4.getName()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r2.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r3 = "Ignoring unsupported tag: "
            r2.append(r3)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r2.append(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r1 = r2.toString()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zze(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r3 = r5
            r16 = r10
            r17 = r15
            r14 = r23
            r2 = r25
            r15 = 1
            goto L_0x06b7
        L_0x0275:
            boolean r1 = r8.equals(r13)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x0503
        L_0x027b:
            r4.next()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzc(r4, r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02bb
            java.lang.String r1 = com.google.android.gms.internal.ads.zzge.zza(r4, r14)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzamy r9 = new com.google.android.gms.internal.ads.zzamy     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9.<init>()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzamy r9 = zzf(r4, r9)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02af
            java.lang.String[] r1 = zzg(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r13 = r1.length     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r16 = r11
            r11 = 0
        L_0x029b:
            if (r11 >= r13) goto L_0x02b1
            r17 = r13
            r13 = r1[r11]     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.Object r13 = r5.get(r13)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzamy r13 = (com.google.android.gms.internal.ads.zzamy) r13     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9.zzl(r13)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r11 = r11 + 1
            r13 = r17
            goto L_0x029b
        L_0x02af:
            r16 = r11
        L_0x02b1:
            java.lang.String r1 = r9.zzE()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02e9
            r5.put(r1, r9)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02e9
        L_0x02bb:
            r16 = r11
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzc(r4, r3)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r9 = "id"
            if (r1 != 0) goto L_0x02ef
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzc(r4, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02e9
        L_0x02cb:
            r4.next()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.String r1 = "image"
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzc(r4, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02e3
            java.lang.String r1 = com.google.android.gms.internal.ads.zzge.zza(r4, r9)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02e3
            java.lang.String r11 = r4.nextText()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r7.put(r1, r11)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
        L_0x02e3:
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzb(r4, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x02cb
        L_0x02e9:
            r17 = r2
            r38 = r5
            goto L_0x04f1
        L_0x02ef:
            java.lang.String r28 = com.google.android.gms.internal.ads.zzge.zza(r4, r9)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r28 != 0) goto L_0x02fc
            r17 = r2
            r38 = r5
        L_0x02f9:
            r1 = 0
            goto L_0x04ea
        L_0x02fc:
            java.lang.String r1 = "origin"
            java.lang.String r1 = com.google.android.gms.internal.ads.zzge.zza(r4, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x04df
            java.util.regex.Pattern r9 = zzb     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.regex.Matcher r11 = r9.matcher(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.regex.Pattern r13 = zzf     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r17 = r2
            java.util.regex.Matcher r2 = r13.matcher(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r18 = r11.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r22 = 1120403456(0x42c80000, float:100.0)
            if (r18 == 0) goto L_0x0347
            r38 = r5
            r5 = 1
            java.lang.String r2 = r11.group(r5)     // Catch:{ NumberFormatException -> 0x033d }
            if (r2 == 0) goto L_0x033b
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x033d }
            float r2 = r2 / r22
            r5 = 2
            java.lang.String r11 = r11.group(r5)     // Catch:{ NumberFormatException -> 0x033d }
            if (r11 == 0) goto L_0x0339
            float r5 = java.lang.Float.parseFloat(r11)     // Catch:{ NumberFormatException -> 0x033d }
            float r5 = r5 / r22
            r29 = r2
            goto L_0x037e
        L_0x0339:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x033d }
        L_0x033b:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x033d }
        L_0x033d:
            java.lang.String r2 = "Ignoring region with malformed origin: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x0347:
            r38 = r5
            boolean r5 = r2.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r5 == 0) goto L_0x04d4
            if (r10 != 0) goto L_0x035b
            java.lang.String r2 = "Ignoring region with missing tts:extent: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x035b:
            r5 = 1
            java.lang.String r11 = r2.group(r5)     // Catch:{ NumberFormatException -> 0x04c9 }
            if (r11 == 0) goto L_0x04c7
            int r5 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x04c9 }
            r11 = 2
            java.lang.String r2 = r2.group(r11)     // Catch:{ NumberFormatException -> 0x04c9 }
            if (r2 == 0) goto L_0x04c5
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x04c9 }
            float r5 = (float) r5     // Catch:{ NumberFormatException -> 0x04c9 }
            int r11 = r10.zza     // Catch:{ NumberFormatException -> 0x04c9 }
            float r11 = (float) r11     // Catch:{ NumberFormatException -> 0x04c9 }
            float r5 = r5 / r11
            float r2 = (float) r2     // Catch:{ NumberFormatException -> 0x04c9 }
            int r11 = r10.zzb     // Catch:{ NumberFormatException -> 0x04c9 }
            float r11 = (float) r11
            float r2 = r2 / r11
            r29 = r5
            r5 = r2
        L_0x037e:
            java.lang.String r2 = "extent"
            java.lang.String r2 = com.google.android.gms.internal.ads.zzge.zza(r4, r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r2 == 0) goto L_0x04be
            java.util.regex.Matcher r9 = r9.matcher(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.util.regex.Matcher r2 = r13.matcher(r2)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r11 = r9.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r11 == 0) goto L_0x03c2
            r11 = 1
            java.lang.String r2 = r9.group(r11)     // Catch:{ NumberFormatException -> 0x03b7 }
            if (r2 == 0) goto L_0x03b5
            float r2 = java.lang.Float.parseFloat(r2)     // Catch:{ NumberFormatException -> 0x03b7 }
            float r2 = r2 / r22
            r11 = 2
            java.lang.String r9 = r9.group(r11)     // Catch:{ NumberFormatException -> 0x03b7 }
            if (r9 == 0) goto L_0x03b3
            float r1 = java.lang.Float.parseFloat(r9)     // Catch:{ NumberFormatException -> 0x03b7 }
            float r1 = r1 / r22
            r34 = r1
            r33 = r2
            goto L_0x03f9
        L_0x03b3:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x03b7 }
        L_0x03b5:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x03b7 }
        L_0x03b7:
            java.lang.String r2 = "Ignoring region with malformed extent: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x03c2:
            boolean r9 = r2.matches()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r9 == 0) goto L_0x04b3
            if (r10 != 0) goto L_0x03d5
            java.lang.String r2 = "Ignoring region with missing tts:extent: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x03d5:
            r9 = 1
            java.lang.String r11 = r2.group(r9)     // Catch:{ NumberFormatException -> 0x04a8 }
            if (r11 == 0) goto L_0x04a6
            int r9 = java.lang.Integer.parseInt(r11)     // Catch:{ NumberFormatException -> 0x04a8 }
            r11 = 2
            java.lang.String r2 = r2.group(r11)     // Catch:{ NumberFormatException -> 0x04a8 }
            if (r2 == 0) goto L_0x04a4
            int r2 = java.lang.Integer.parseInt(r2)     // Catch:{ NumberFormatException -> 0x04a8 }
            float r9 = (float) r9     // Catch:{ NumberFormatException -> 0x04a8 }
            int r11 = r10.zza     // Catch:{ NumberFormatException -> 0x04a8 }
            float r11 = (float) r11     // Catch:{ NumberFormatException -> 0x04a8 }
            float r9 = r9 / r11
            float r2 = (float) r2     // Catch:{ NumberFormatException -> 0x04a8 }
            int r1 = r10.zzb     // Catch:{ NumberFormatException -> 0x04a8 }
            float r1 = (float) r1
            float r2 = r2 / r1
            r34 = r2
            r33 = r9
        L_0x03f9:
            java.lang.String r1 = "displayAlign"
            java.lang.String r1 = com.google.android.gms.internal.ads.zzge.zza(r4, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x0440
            java.lang.String r1 = com.google.android.gms.internal.ads.zzfxm.zza(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r2 = r1.hashCode()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9 = -1364013995(0xffffffffaeb2cc55, float:-8.1307995E-11)
            if (r2 == r9) goto L_0x041e
            r9 = 92734940(0x58705dc, float:1.2697491E-35)
            if (r2 == r9) goto L_0x0414
            goto L_0x0428
        L_0x0414:
            java.lang.String r2 = "after"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0428
            r1 = 1
            goto L_0x0429
        L_0x041e:
            java.lang.String r2 = "center"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0428
            r1 = 0
            goto L_0x0429
        L_0x0428:
            r1 = -1
        L_0x0429:
            if (r1 == 0) goto L_0x0436
            r2 = 1
            if (r1 == r2) goto L_0x042f
            goto L_0x0440
        L_0x042f:
            float r5 = r5 + r34
            r30 = r5
            r32 = 2
            goto L_0x0444
        L_0x0436:
            r1 = 1073741824(0x40000000, float:2.0)
            float r1 = r34 / r1
            float r5 = r5 + r1
            r30 = r5
            r32 = 1
            goto L_0x0444
        L_0x0440:
            r30 = r5
            r32 = 0
        L_0x0444:
            float r1 = (float) r15
            float r36 = r21 / r1
            java.lang.String r1 = "writingMode"
            java.lang.String r1 = com.google.android.gms.internal.ads.zzge.zza(r4, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x0494
            java.lang.String r1 = com.google.android.gms.internal.ads.zzfxm.zza(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r2 = r1.hashCode()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r5 = 3694(0xe6e, float:5.176E-42)
            if (r2 == r5) goto L_0x047a
            r5 = 3553396(0x363874, float:4.979368E-39)
            if (r2 == r5) goto L_0x0470
            r5 = 3553576(0x363928, float:4.97962E-39)
            if (r2 == r5) goto L_0x0466
            goto L_0x0484
        L_0x0466:
            java.lang.String r2 = "tbrl"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0484
            r1 = 2
            goto L_0x0485
        L_0x0470:
            java.lang.String r2 = "tblr"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0484
            r1 = 1
            goto L_0x0485
        L_0x047a:
            java.lang.String r2 = "tb"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0484
            r1 = 0
            goto L_0x0485
        L_0x0484:
            r1 = -1
        L_0x0485:
            if (r1 == 0) goto L_0x0491
            r2 = 1
            if (r1 == r2) goto L_0x0491
            r2 = 2
            if (r1 == r2) goto L_0x048e
            goto L_0x0494
        L_0x048e:
            r37 = 1
            goto L_0x0498
        L_0x0491:
            r37 = 2
            goto L_0x0498
        L_0x0494:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r37 = r1
        L_0x0498:
            com.google.android.gms.internal.ads.zzamw r1 = new com.google.android.gms.internal.ads.zzamw     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r31 = 0
            r35 = 1
            r27 = r1
            r27.<init>(r28, r29, r30, r31, r32, r33, r34, r35, r36, r37)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x04ea
        L_0x04a4:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x04a8 }
        L_0x04a6:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x04a8 }
        L_0x04a8:
            java.lang.String r2 = "Ignoring region with malformed extent: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04b3:
            java.lang.String r2 = "Ignoring region with unsupported extent: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04be:
            java.lang.String r1 = "Ignoring region without an extent"
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04c5:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x04c9 }
        L_0x04c7:
            r2 = 0
            throw r2     // Catch:{ NumberFormatException -> 0x04c9 }
        L_0x04c9:
            java.lang.String r2 = "Ignoring region with malformed origin: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04d4:
            java.lang.String r2 = "Ignoring region with unsupported origin: "
            java.lang.String r1 = r2.concat(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04df:
            r17 = r2
            r38 = r5
            java.lang.String r1 = "Ignoring region without an origin"
            com.google.android.gms.internal.ads.zzfk.zzf(r12, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x02f9
        L_0x04ea:
            if (r1 == 0) goto L_0x04f1
            java.lang.String r2 = r1.zza     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r6.put(r2, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
        L_0x04f1:
            boolean r1 = com.google.android.gms.internal.ads.zzge.zzb(r4, r8)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r11 = r16
            if (r1 == 0) goto L_0x04fd
            r2 = r25
            goto L_0x062c
        L_0x04fd:
            r2 = r17
            r5 = r38
            goto L_0x027b
        L_0x0503:
            r38 = r5
            r16 = r11
            int r1 = r4.getAttributeCount()     // Catch:{ zzalr -> 0x063e }
            r2 = 0
            com.google.android.gms.internal.ads.zzamy r32 = zzf(r4, r2)     // Catch:{ zzalr -> 0x063e }
            r27 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r34 = r20
            r29 = r27
            r36 = r29
            r39 = r36
            r2 = 0
            r33 = 0
            r35 = 0
        L_0x0522:
            if (r2 >= r1) goto L_0x05d5
            java.lang.String r5 = r4.getAttributeName(r2)     // Catch:{ zzalr -> 0x05cc }
            java.lang.String r8 = r4.getAttributeValue(r2)     // Catch:{ zzalr -> 0x05cc }
            int r11 = r5.hashCode()     // Catch:{ zzalr -> 0x05cc }
            r13 = 5
            switch(r11) {
                case -934795532: goto L_0x0565;
                case 99841: goto L_0x055b;
                case 100571: goto L_0x0551;
                case 93616297: goto L_0x0547;
                case 109780401: goto L_0x053f;
                case 1292595405: goto L_0x0535;
                default: goto L_0x0534;
            }
        L_0x0534:
            goto L_0x056d
        L_0x0535:
            java.lang.String r11 = "backgroundImage"
            boolean r5 = r5.equals(r11)
            if (r5 == 0) goto L_0x056d
            r5 = r13
            goto L_0x056e
        L_0x053f:
            boolean r5 = r5.equals(r14)
            if (r5 == 0) goto L_0x056d
            r5 = 3
            goto L_0x056e
        L_0x0547:
            java.lang.String r11 = "begin"
            boolean r5 = r5.equals(r11)
            if (r5 == 0) goto L_0x056d
            r5 = 0
            goto L_0x056e
        L_0x0551:
            java.lang.String r11 = "end"
            boolean r5 = r5.equals(r11)
            if (r5 == 0) goto L_0x056d
            r5 = 1
            goto L_0x056e
        L_0x055b:
            java.lang.String r11 = "dur"
            boolean r5 = r5.equals(r11)
            if (r5 == 0) goto L_0x056d
            r5 = 2
            goto L_0x056e
        L_0x0565:
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x056d
            r5 = 4
            goto L_0x056e
        L_0x056d:
            r5 = -1
        L_0x056e:
            if (r5 == 0) goto L_0x05bf
            r11 = 1
            if (r5 == r11) goto L_0x05b7
            r11 = 2
            if (r5 == r11) goto L_0x05af
            r11 = 3
            if (r5 == r11) goto L_0x059d
            r11 = 4
            if (r5 == r11) goto L_0x0593
            if (r5 == r13) goto L_0x0580
        L_0x057e:
            r5 = 1
            goto L_0x05a7
        L_0x0580:
            java.lang.String r5 = "#"
            boolean r5 = r8.startsWith(r5)     // Catch:{ zzalr -> 0x0590 }
            if (r5 == 0) goto L_0x057e
            r5 = 1
            java.lang.String r8 = r8.substring(r5)     // Catch:{ zzalr -> 0x05aa }
            r35 = r8
            goto L_0x05a7
        L_0x0590:
            r0 = move-exception
            r5 = 1
            goto L_0x05ab
        L_0x0593:
            r5 = 1
            boolean r11 = r6.containsKey(r8)     // Catch:{ zzalr -> 0x05aa }
            if (r11 == 0) goto L_0x05a7
            r34 = r8
            goto L_0x05a7
        L_0x059d:
            r5 = 1
            java.lang.String[] r8 = zzg(r8)     // Catch:{ zzalr -> 0x05aa }
            int r11 = r8.length     // Catch:{ zzalr -> 0x05aa }
            if (r11 <= 0) goto L_0x05a7
            r33 = r8
        L_0x05a7:
            r11 = r16
            goto L_0x05c6
        L_0x05aa:
            r0 = move-exception
        L_0x05ab:
            r1 = r0
            r11 = r16
            goto L_0x05d1
        L_0x05af:
            r11 = r16
            r5 = 1
            long r39 = zzc(r8, r11)     // Catch:{ zzalr -> 0x05f6 }
            goto L_0x05c6
        L_0x05b7:
            r5 = r11
            r11 = r16
            long r29 = zzc(r8, r11)     // Catch:{ zzalr -> 0x05f6 }
            goto L_0x05c6
        L_0x05bf:
            r11 = r16
            r5 = 1
            long r36 = zzc(r8, r11)     // Catch:{ zzalr -> 0x05f6 }
        L_0x05c6:
            int r2 = r2 + 1
            r16 = r11
            goto L_0x0522
        L_0x05cc:
            r0 = move-exception
            r11 = r16
            r5 = 1
        L_0x05d0:
            r1 = r0
        L_0x05d1:
            r2 = r25
            goto L_0x0645
        L_0x05d5:
            r11 = r16
            r5 = 1
            if (r9 == 0) goto L_0x05f8
            long r1 = r9.zzd     // Catch:{ zzalr -> 0x05f6 }
            int r3 = (r1 > r27 ? 1 : (r1 == r27 ? 0 : -1))
            if (r3 == 0) goto L_0x05f4
            int r3 = (r36 > r27 ? 1 : (r36 == r27 ? 0 : -1))
            if (r3 == 0) goto L_0x05e7
            long r36 = r36 + r1
            goto L_0x05e9
        L_0x05e7:
            r36 = r27
        L_0x05e9:
            int r3 = (r29 > r27 ? 1 : (r29 == r27 ? 0 : -1))
            if (r3 == 0) goto L_0x05f0
            long r29 = r29 + r1
            goto L_0x05f4
        L_0x05f0:
            r1 = r9
            r29 = r27
            goto L_0x05f9
        L_0x05f4:
            r1 = r9
            goto L_0x05f9
        L_0x05f6:
            r0 = move-exception
            goto L_0x05d0
        L_0x05f8:
            r1 = 0
        L_0x05f9:
            int r2 = (r29 > r27 ? 1 : (r29 == r27 ? 0 : -1))
            if (r2 != 0) goto L_0x0614
            int r2 = (r39 > r27 ? 1 : (r39 == r27 ? 0 : -1))
            if (r2 == 0) goto L_0x0606
            long r39 = r36 + r39
            r30 = r39
            goto L_0x0616
        L_0x0606:
            if (r1 == 0) goto L_0x0611
            long r2 = r1.zze     // Catch:{ zzalr -> 0x05f6 }
            int r8 = (r2 > r27 ? 1 : (r2 == r27 ? 0 : -1))
            if (r8 == 0) goto L_0x0611
            r30 = r2
            goto L_0x0616
        L_0x0611:
            r30 = r27
            goto L_0x0616
        L_0x0614:
            r30 = r29
        L_0x0616:
            java.lang.String r27 = r4.getName()     // Catch:{ zzalr -> 0x063a }
            r28 = r36
            r36 = r1
            com.google.android.gms.internal.ads.zzams r1 = com.google.android.gms.internal.ads.zzams.zzb(r27, r28, r30, r32, r33, r34, r35, r36)     // Catch:{ zzalr -> 0x063a }
            r2 = r25
            r2.push(r1)     // Catch:{ zzalr -> 0x0638 }
            if (r9 == 0) goto L_0x062c
            r9.zzf(r1)     // Catch:{ zzalr -> 0x0638 }
        L_0x062c:
            r16 = r10
            r17 = r15
            r14 = r23
            r15 = r24
            r3 = r38
            goto L_0x06b7
        L_0x0638:
            r0 = move-exception
            goto L_0x0644
        L_0x063a:
            r0 = move-exception
            r2 = r25
            goto L_0x0644
        L_0x063e:
            r0 = move-exception
            r11 = r16
            r2 = r25
            r5 = 1
        L_0x0644:
            r1 = r0
        L_0x0645:
            java.lang.String r3 = "Suppressing parser error"
            com.google.android.gms.internal.ads.zzfk.zzg(r12, r3, r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r16 = r10
            r17 = r15
            r14 = r23
            r3 = r38
            r15 = r5
            goto L_0x06b7
        L_0x0655:
            r26 = r2
            r38 = r5
            r2 = r8
            r23 = r14
            r24 = r15
            r3 = 4
            if (r10 != r3) goto L_0x0671
            if (r9 == 0) goto L_0x066f
            java.lang.String r1 = r4.getText()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzams r1 = com.google.android.gms.internal.ads.zzams.zzc(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r9.zzf(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0698
        L_0x066f:
            r1 = 0
            throw r1     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
        L_0x0671:
            r3 = 3
            if (r10 != r3) goto L_0x0698
            java.lang.String r3 = r4.getName()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            boolean r1 = r3.equals(r1)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x0690
            com.google.android.gms.internal.ads.zzamz r14 = new com.google.android.gms.internal.ads.zzamz     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            java.lang.Object r1 = r2.peek()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            com.google.android.gms.internal.ads.zzams r1 = (com.google.android.gms.internal.ads.zzams) r1     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            if (r1 == 0) goto L_0x068e
            r3 = r38
            r14.<init>(r1, r3, r6, r7)     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x0694
        L_0x068e:
            r1 = 0
            throw r1     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
        L_0x0690:
            r3 = r38
            r14 = r23
        L_0x0694:
            r2.pop()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            goto L_0x06b5
        L_0x0698:
            r3 = r38
            goto L_0x06b3
        L_0x069b:
            r20 = r1
            r26 = r2
            r3 = r5
            r2 = r8
            r1 = r12
            r23 = r14
            r24 = r15
            if (r10 != r1) goto L_0x06ad
            int r15 = r24 + 1
        L_0x06aa:
            r14 = r23
            goto L_0x06b7
        L_0x06ad:
            r1 = 3
            if (r10 != r1) goto L_0x06b3
            int r15 = r24 + -1
            goto L_0x06aa
        L_0x06b3:
            r14 = r23
        L_0x06b5:
            r15 = r24
        L_0x06b7:
            r4.next()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            int r10 = r4.getEventType()     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
            r8 = r2
            r5 = r3
            r1 = r20
            r2 = r26
            r9 = 0
            r3 = r41
            goto L_0x005d
        L_0x06c9:
            r23 = r14
            if (r23 == 0) goto L_0x06ce
            return r23
        L_0x06ce:
            r1 = 0
            throw r1     // Catch:{ XmlPullParserException -> 0x06da, IOException -> 0x06d0 }
        L_0x06d0:
            r0 = move-exception
            r1 = r0
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "Unexpected error when reading input."
            r2.<init>(r3, r1)
            throw r2
        L_0x06da:
            r0 = move-exception
            r1 = r0
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r3 = "Unable to decode source"
            r2.<init>(r3, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzamv.zzb(byte[], int, int):com.google.android.gms.internal.ads.zzalq");
    }

    public zzamv() {
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.zzi = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }
}
