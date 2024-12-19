package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzuj {
    public static final /* synthetic */ int zza = 0;
    private static final Pattern zzb = Pattern.compile("^\\D?(\\d+)$");
    private static final HashMap zzc = new HashMap();

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:284:0x04e4, code lost:
        if (r1.equals("L90") != false) goto L_0x053d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:461:?, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:462:?, code lost:
        return r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0115  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair zza(com.google.android.gms.internal.ads.zzan r21) {
        /*
            r0 = r21
            java.lang.String r1 = r0.zzk
            if (r1 != 0) goto L_0x0009
        L_0x0006:
            r2 = 0
            goto L_0x07fb
        L_0x0009:
            java.lang.String r3 = "\\."
            java.lang.String[] r1 = r1.split(r3)
            java.lang.String r3 = r0.zzn
            java.lang.String r4 = "video/dolby-vision"
            boolean r3 = r4.equals(r3)
            r5 = 1024(0x400, float:1.435E-42)
            r6 = 512(0x200, float:7.175E-43)
            r7 = 256(0x100, float:3.59E-43)
            r8 = 128(0x80, float:1.794E-43)
            r9 = 64
            r10 = 32
            r12 = 8
            r13 = 3
            r14 = 16
            r15 = 4
            r2 = 2
            java.lang.String r4 = "MediaCodecUtil"
            r11 = 1
            if (r3 == 0) goto L_0x01f0
            java.lang.String r0 = r0.zzk
            int r3 = r1.length
            if (r3 >= r13) goto L_0x0042
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed Dolby Vision codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0042:
            java.util.regex.Pattern r3 = zzb
            r13 = r1[r11]
            java.util.regex.Matcher r3 = r3.matcher(r13)
            boolean r13 = r3.matches()
            if (r13 != 0) goto L_0x005e
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed Dolby Vision codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x005e:
            java.lang.String r0 = r3.group(r11)
            if (r0 != 0) goto L_0x0067
        L_0x0064:
            r3 = 0
            goto L_0x0104
        L_0x0067:
            int r3 = r0.hashCode()
            r13 = 1567(0x61f, float:2.196E-42)
            if (r3 == r13) goto L_0x00f8
            switch(r3) {
                case 1536: goto L_0x00eb;
                case 1537: goto L_0x00de;
                case 1538: goto L_0x00d1;
                case 1539: goto L_0x00c4;
                case 1540: goto L_0x00b7;
                case 1541: goto L_0x00aa;
                case 1542: goto L_0x009d;
                case 1543: goto L_0x008f;
                case 1544: goto L_0x0081;
                case 1545: goto L_0x0073;
                default: goto L_0x0072;
            }
        L_0x0072:
            goto L_0x0064
        L_0x0073:
            java.lang.String r3 = "09"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r6)
            goto L_0x0104
        L_0x0081:
            java.lang.String r3 = "08"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            goto L_0x0104
        L_0x008f:
            java.lang.String r3 = "07"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r8)
            goto L_0x0104
        L_0x009d:
            java.lang.String r3 = "06"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r9)
            goto L_0x0104
        L_0x00aa:
            java.lang.String r3 = "05"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r10)
            goto L_0x0104
        L_0x00b7:
            java.lang.String r3 = "04"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            goto L_0x0104
        L_0x00c4:
            java.lang.String r3 = "03"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r12)
            goto L_0x0104
        L_0x00d1:
            java.lang.String r3 = "02"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r15)
            goto L_0x0104
        L_0x00de:
            java.lang.String r3 = "01"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)
            goto L_0x0104
        L_0x00eb:
            java.lang.String r3 = "00"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)
            goto L_0x0104
        L_0x00f8:
            java.lang.String r3 = "10"
            boolean r3 = r0.equals(r3)
            if (r3 == 0) goto L_0x0064
            java.lang.Integer r3 = java.lang.Integer.valueOf(r5)
        L_0x0104:
            if (r3 != 0) goto L_0x0115
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Unknown Dolby Vision profile string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0115:
            r0 = r1[r2]
            if (r0 != 0) goto L_0x011c
        L_0x0119:
            r1 = 0
            goto L_0x01d8
        L_0x011c:
            int r1 = r0.hashCode()
            switch(r1) {
                case 1537: goto L_0x01cc;
                case 1538: goto L_0x01bf;
                case 1539: goto L_0x01b2;
                case 1540: goto L_0x01a5;
                case 1541: goto L_0x0198;
                case 1542: goto L_0x018b;
                case 1543: goto L_0x017e;
                case 1544: goto L_0x0171;
                case 1545: goto L_0x0163;
                default: goto L_0x0123;
            }
        L_0x0123:
            switch(r1) {
                case 1567: goto L_0x0155;
                case 1568: goto L_0x0147;
                case 1569: goto L_0x0137;
                case 1570: goto L_0x0127;
                default: goto L_0x0126;
            }
        L_0x0126:
            goto L_0x0119
        L_0x0127:
            java.lang.String r1 = "13"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            r1 = 4096(0x1000, float:5.74E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x01d8
        L_0x0137:
            java.lang.String r1 = "12"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            r1 = 2048(0x800, float:2.87E-42)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            goto L_0x01d8
        L_0x0147:
            java.lang.String r1 = "11"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            goto L_0x01d8
        L_0x0155:
            java.lang.String r1 = "10"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)
            goto L_0x01d8
        L_0x0163:
            java.lang.String r1 = "09"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            goto L_0x01d8
        L_0x0171:
            java.lang.String r1 = "08"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)
            goto L_0x01d8
        L_0x017e:
            java.lang.String r1 = "07"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r9)
            goto L_0x01d8
        L_0x018b:
            java.lang.String r1 = "06"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r10)
            goto L_0x01d8
        L_0x0198:
            java.lang.String r1 = "05"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r14)
            goto L_0x01d8
        L_0x01a5:
            java.lang.String r1 = "04"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r12)
            goto L_0x01d8
        L_0x01b2:
            java.lang.String r1 = "03"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r15)
            goto L_0x01d8
        L_0x01bf:
            java.lang.String r1 = "02"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            goto L_0x01d8
        L_0x01cc:
            java.lang.String r1 = "01"
            boolean r1 = r0.equals(r1)
            if (r1 == 0) goto L_0x0119
            java.lang.Integer r1 = java.lang.Integer.valueOf(r11)
        L_0x01d8:
            if (r1 != 0) goto L_0x01e9
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Unknown Dolby Vision level string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x01e9:
            android.util.Pair r2 = new android.util.Pair
            r2.<init>(r3, r1)
            goto L_0x07fb
        L_0x01f0:
            r3 = 0
            r9 = r1[r3]
            int r19 = r9.hashCode()
            r7 = 6
            r5 = -1
            switch(r19) {
                case 3004662: goto L_0x0239;
                case 3006243: goto L_0x022f;
                case 3006244: goto L_0x0225;
                case 3199032: goto L_0x021b;
                case 3214780: goto L_0x0211;
                case 3356560: goto L_0x0207;
                case 3624515: goto L_0x01fd;
                default: goto L_0x01fc;
            }
        L_0x01fc:
            goto L_0x0243
        L_0x01fd:
            java.lang.String r10 = "vp09"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r2
            goto L_0x0244
        L_0x0207:
            java.lang.String r10 = "mp4a"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r7
            goto L_0x0244
        L_0x0211:
            java.lang.String r10 = "hvc1"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r15
            goto L_0x0244
        L_0x021b:
            java.lang.String r10 = "hev1"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r13
            goto L_0x0244
        L_0x0225:
            java.lang.String r10 = "avc2"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r11
            goto L_0x0244
        L_0x022f:
            java.lang.String r10 = "avc1"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = r3
            goto L_0x0244
        L_0x0239:
            java.lang.String r10 = "av01"
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0243
            r9 = 5
            goto L_0x0244
        L_0x0243:
            r9 = r5
        L_0x0244:
            r10 = 8192(0x2000, float:1.14794E-41)
            r8 = 20
            switch(r9) {
                case 0: goto L_0x06ee;
                case 1: goto L_0x06ee;
                case 2: goto L_0x0622;
                case 3: goto L_0x03b5;
                case 4: goto L_0x03b5;
                case 5: goto L_0x02ce;
                case 6: goto L_0x024d;
                default: goto L_0x024b;
            }
        L_0x024b:
            goto L_0x0006
        L_0x024d:
            java.lang.String r0 = r0.zzk
            int r6 = r1.length
            if (r6 == r13) goto L_0x0261
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed MP4A codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0261:
            r6 = r1[r11]     // Catch:{ NumberFormatException -> 0x02bf }
            int r6 = java.lang.Integer.parseInt(r6, r14)     // Catch:{ NumberFormatException -> 0x02bf }
            java.lang.String r6 = com.google.android.gms.internal.ads.zzcg.zzd(r6)     // Catch:{ NumberFormatException -> 0x02bf }
            java.lang.String r9 = "audio/mp4a-latm"
            boolean r6 = r9.equals(r6)     // Catch:{ NumberFormatException -> 0x02bf }
            if (r6 == 0) goto L_0x0006
            r1 = r1[r2]     // Catch:{ NumberFormatException -> 0x02bf }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x02bf }
            r6 = 17
            if (r1 == r6) goto L_0x02ac
            if (r1 == r8) goto L_0x02aa
            r6 = 23
            if (r1 == r6) goto L_0x02a7
            r6 = 29
            if (r1 == r6) goto L_0x02a4
            r6 = 39
            if (r1 == r6) goto L_0x02a1
            r6 = 42
            if (r1 == r6) goto L_0x029e
            switch(r1) {
                case 1: goto L_0x029c;
                case 2: goto L_0x029a;
                case 3: goto L_0x02ae;
                case 4: goto L_0x0298;
                case 5: goto L_0x0296;
                case 6: goto L_0x0294;
                default: goto L_0x0292;
            }     // Catch:{ NumberFormatException -> 0x02bf }
        L_0x0292:
            r13 = r5
            goto L_0x02ae
        L_0x0294:
            r13 = r7
            goto L_0x02ae
        L_0x0296:
            r13 = 5
            goto L_0x02ae
        L_0x0298:
            r13 = r15
            goto L_0x02ae
        L_0x029a:
            r13 = r2
            goto L_0x02ae
        L_0x029c:
            r13 = r11
            goto L_0x02ae
        L_0x029e:
            r13 = 42
            goto L_0x02ae
        L_0x02a1:
            r13 = 39
            goto L_0x02ae
        L_0x02a4:
            r13 = 29
            goto L_0x02ae
        L_0x02a7:
            r13 = 23
            goto L_0x02ae
        L_0x02aa:
            r13 = r8
            goto L_0x02ae
        L_0x02ac:
            r13 = 17
        L_0x02ae:
            if (r13 == r5) goto L_0x0006
            android.util.Pair r1 = new android.util.Pair     // Catch:{ NumberFormatException -> 0x02bf }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r13)     // Catch:{ NumberFormatException -> 0x02bf }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ NumberFormatException -> 0x02bf }
            r1.<init>(r2, r3)     // Catch:{ NumberFormatException -> 0x02bf }
            goto L_0x0610
        L_0x02bf:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed MP4A codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x02ce:
            java.lang.String r8 = r0.zzk
            com.google.android.gms.internal.ads.zzt r0 = r0.zzz
            int r9 = r1.length
            if (r9 >= r15) goto L_0x02e4
            java.lang.String r0 = java.lang.String.valueOf(r8)
            java.lang.String r1 = "Ignoring malformed AV1 codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x02e4:
            r9 = r1[r11]     // Catch:{ NumberFormatException -> 0x03a6 }
            int r9 = java.lang.Integer.parseInt(r9)     // Catch:{ NumberFormatException -> 0x03a6 }
            r14 = r1[r2]     // Catch:{ NumberFormatException -> 0x03a6 }
            java.lang.String r3 = r14.substring(r3, r2)     // Catch:{ NumberFormatException -> 0x03a6 }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x03a6 }
            r1 = r1[r13]     // Catch:{ NumberFormatException -> 0x03a6 }
            int r1 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x03a6 }
            if (r9 == 0) goto L_0x030f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown AV1 profile: "
            r0.<init>(r1)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x030f:
            if (r1 == r12) goto L_0x033a
            r8 = 10
            if (r1 == r8) goto L_0x0328
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown AV1 bit depth: "
            r0.<init>(r2)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0328:
            if (r0 == 0) goto L_0x0338
            byte[] r1 = r0.zzg
            if (r1 != 0) goto L_0x0335
            int r0 = r0.zzf
            r1 = 7
            if (r0 == r1) goto L_0x0335
            if (r0 != r7) goto L_0x0338
        L_0x0335:
            r1 = 4096(0x1000, float:5.74E-42)
            goto L_0x033b
        L_0x0338:
            r1 = r2
            goto L_0x033b
        L_0x033a:
            r1 = r11
        L_0x033b:
            switch(r3) {
                case 0: goto L_0x0381;
                case 1: goto L_0x037f;
                case 2: goto L_0x037d;
                case 3: goto L_0x037b;
                case 4: goto L_0x0378;
                case 5: goto L_0x0375;
                case 6: goto L_0x0372;
                case 7: goto L_0x036f;
                case 8: goto L_0x036c;
                case 9: goto L_0x036a;
                case 10: goto L_0x0367;
                case 11: goto L_0x0364;
                case 12: goto L_0x0361;
                case 13: goto L_0x035f;
                case 14: goto L_0x035c;
                case 15: goto L_0x0358;
                case 16: goto L_0x0355;
                case 17: goto L_0x0352;
                case 18: goto L_0x034f;
                case 19: goto L_0x034c;
                case 20: goto L_0x0349;
                case 21: goto L_0x0346;
                case 22: goto L_0x0343;
                case 23: goto L_0x0340;
                default: goto L_0x033e;
            }
        L_0x033e:
            r0 = r5
            goto L_0x0382
        L_0x0340:
            r0 = 8388608(0x800000, float:1.17549435E-38)
            goto L_0x0382
        L_0x0343:
            r0 = 4194304(0x400000, float:5.877472E-39)
            goto L_0x0382
        L_0x0346:
            r0 = 2097152(0x200000, float:2.938736E-39)
            goto L_0x0382
        L_0x0349:
            r0 = 1048576(0x100000, float:1.469368E-39)
            goto L_0x0382
        L_0x034c:
            r0 = 524288(0x80000, float:7.34684E-40)
            goto L_0x0382
        L_0x034f:
            r0 = 262144(0x40000, float:3.67342E-40)
            goto L_0x0382
        L_0x0352:
            r0 = 131072(0x20000, float:1.83671E-40)
            goto L_0x0382
        L_0x0355:
            r0 = 65536(0x10000, float:9.18355E-41)
            goto L_0x0382
        L_0x0358:
            r0 = 32768(0x8000, float:4.5918E-41)
            goto L_0x0382
        L_0x035c:
            r0 = 16384(0x4000, float:2.2959E-41)
            goto L_0x0382
        L_0x035f:
            r0 = r10
            goto L_0x0382
        L_0x0361:
            r0 = 4096(0x1000, float:5.74E-42)
            goto L_0x0382
        L_0x0364:
            r0 = 2048(0x800, float:2.87E-42)
            goto L_0x0382
        L_0x0367:
            r0 = 1024(0x400, float:1.435E-42)
            goto L_0x0382
        L_0x036a:
            r0 = r6
            goto L_0x0382
        L_0x036c:
            r0 = 256(0x100, float:3.59E-43)
            goto L_0x0382
        L_0x036f:
            r0 = 128(0x80, float:1.794E-43)
            goto L_0x0382
        L_0x0372:
            r0 = 64
            goto L_0x0382
        L_0x0375:
            r0 = 32
            goto L_0x0382
        L_0x0378:
            r0 = 16
            goto L_0x0382
        L_0x037b:
            r0 = r12
            goto L_0x0382
        L_0x037d:
            r0 = r15
            goto L_0x0382
        L_0x037f:
            r0 = r2
            goto L_0x0382
        L_0x0381:
            r0 = r11
        L_0x0382:
            if (r0 != r5) goto L_0x0397
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown AV1 level: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0397:
            android.util.Pair r2 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2.<init>(r1, r0)
            goto L_0x07fb
        L_0x03a6:
            java.lang.String r0 = java.lang.String.valueOf(r8)
            java.lang.String r1 = "Ignoring malformed AV1 codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x03b5:
            java.lang.String r9 = r0.zzk
            com.google.android.gms.internal.ads.zzt r0 = r0.zzz
            int r14 = r1.length
            if (r14 >= r15) goto L_0x03cb
            java.lang.String r0 = java.lang.String.valueOf(r9)
            java.lang.String r1 = "Ignoring malformed HEVC codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x03cb:
            java.util.regex.Pattern r14 = zzb
            r3 = r1[r11]
            java.util.regex.Matcher r3 = r14.matcher(r3)
            boolean r14 = r3.matches()
            if (r14 != 0) goto L_0x03e8
            java.lang.String r0 = java.lang.String.valueOf(r9)
            java.lang.String r1 = "Ignoring malformed HEVC codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x03e8:
            java.lang.String r3 = r3.group(r11)
            java.lang.String r9 = "1"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x03f6
            r0 = r11
            goto L_0x0408
        L_0x03f6:
            java.lang.String r9 = "2"
            boolean r9 = r9.equals(r3)
            if (r9 == 0) goto L_0x0613
            if (r0 == 0) goto L_0x0407
            int r0 = r0.zzf
            if (r0 != r7) goto L_0x0407
            r0 = 4096(0x1000, float:5.74E-42)
            goto L_0x0408
        L_0x0407:
            r0 = r2
        L_0x0408:
            r1 = r1[r13]
            if (r1 != 0) goto L_0x040f
        L_0x040c:
            r2 = 0
            goto L_0x05f6
        L_0x040f:
            int r3 = r1.hashCode()
            switch(r3) {
                case 70821: goto L_0x0531;
                case 70914: goto L_0x0526;
                case 70917: goto L_0x051b;
                case 71007: goto L_0x0510;
                case 71010: goto L_0x0505;
                case 74665: goto L_0x04fb;
                case 74758: goto L_0x04f1;
                case 74761: goto L_0x04e7;
                case 74851: goto L_0x04de;
                case 74854: goto L_0x04d3;
                case 2193639: goto L_0x04c7;
                case 2193642: goto L_0x04bb;
                case 2193732: goto L_0x04b0;
                case 2193735: goto L_0x04a4;
                case 2193738: goto L_0x0498;
                case 2193825: goto L_0x048c;
                case 2193828: goto L_0x0480;
                case 2193831: goto L_0x0474;
                case 2312803: goto L_0x0469;
                case 2312806: goto L_0x045e;
                case 2312896: goto L_0x0453;
                case 2312899: goto L_0x0448;
                case 2312902: goto L_0x043c;
                case 2312989: goto L_0x0430;
                case 2312992: goto L_0x0424;
                case 2312995: goto L_0x0418;
                default: goto L_0x0416;
            }
        L_0x0416:
            goto L_0x053c
        L_0x0418:
            java.lang.String r3 = "L186"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 12
            goto L_0x053d
        L_0x0424:
            java.lang.String r3 = "L183"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 11
            goto L_0x053d
        L_0x0430:
            java.lang.String r3 = "L180"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 10
            goto L_0x053d
        L_0x043c:
            java.lang.String r3 = "L156"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 9
            goto L_0x053d
        L_0x0448:
            java.lang.String r3 = "L153"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r12
            goto L_0x053d
        L_0x0453:
            java.lang.String r3 = "L150"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 7
            goto L_0x053d
        L_0x045e:
            java.lang.String r3 = "L123"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r7
            goto L_0x053d
        L_0x0469:
            java.lang.String r3 = "L120"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 5
            goto L_0x053d
        L_0x0474:
            java.lang.String r3 = "H186"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 25
            goto L_0x053d
        L_0x0480:
            java.lang.String r3 = "H183"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 24
            goto L_0x053d
        L_0x048c:
            java.lang.String r3 = "H180"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 23
            goto L_0x053d
        L_0x0498:
            java.lang.String r3 = "H156"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 22
            goto L_0x053d
        L_0x04a4:
            java.lang.String r3 = "H153"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 21
            goto L_0x053d
        L_0x04b0:
            java.lang.String r3 = "H150"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r8
            goto L_0x053d
        L_0x04bb:
            java.lang.String r3 = "H123"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 19
            goto L_0x053d
        L_0x04c7:
            java.lang.String r3 = "H120"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 18
            goto L_0x053d
        L_0x04d3:
            java.lang.String r3 = "L93"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r15
            goto L_0x053d
        L_0x04de:
            java.lang.String r3 = "L90"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            goto L_0x053d
        L_0x04e7:
            java.lang.String r3 = "L63"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r2
            goto L_0x053d
        L_0x04f1:
            java.lang.String r3 = "L60"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = r11
            goto L_0x053d
        L_0x04fb:
            java.lang.String r3 = "L30"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 0
            goto L_0x053d
        L_0x0505:
            java.lang.String r3 = "H93"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 17
            goto L_0x053d
        L_0x0510:
            java.lang.String r3 = "H90"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 16
            goto L_0x053d
        L_0x051b:
            java.lang.String r3 = "H63"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 15
            goto L_0x053d
        L_0x0526:
            java.lang.String r3 = "H60"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 14
            goto L_0x053d
        L_0x0531:
            java.lang.String r3 = "H30"
            boolean r3 = r1.equals(r3)
            if (r3 == 0) goto L_0x053c
            r13 = 13
            goto L_0x053d
        L_0x053c:
            r13 = r5
        L_0x053d:
            switch(r13) {
                case 0: goto L_0x05f2;
                case 1: goto L_0x05ed;
                case 2: goto L_0x05e6;
                case 3: goto L_0x05df;
                case 4: goto L_0x05d8;
                case 5: goto L_0x05d1;
                case 6: goto L_0x05ca;
                case 7: goto L_0x05c3;
                case 8: goto L_0x05bc;
                case 9: goto L_0x05b5;
                case 10: goto L_0x05ae;
                case 11: goto L_0x05a7;
                case 12: goto L_0x05a0;
                case 13: goto L_0x059b;
                case 14: goto L_0x0596;
                case 15: goto L_0x058f;
                case 16: goto L_0x0587;
                case 17: goto L_0x0581;
                case 18: goto L_0x0579;
                case 19: goto L_0x0573;
                case 20: goto L_0x056a;
                case 21: goto L_0x0562;
                case 22: goto L_0x055a;
                case 23: goto L_0x0552;
                case 24: goto L_0x054a;
                case 25: goto L_0x0542;
                default: goto L_0x0540;
            }
        L_0x0540:
            goto L_0x040c
        L_0x0542:
            r2 = 33554432(0x2000000, float:9.403955E-38)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x054a:
            r2 = 8388608(0x800000, float:1.17549435E-38)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x0552:
            r2 = 2097152(0x200000, float:2.938736E-39)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x055a:
            r2 = 524288(0x80000, float:7.34684E-40)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x0562:
            r2 = 131072(0x20000, float:1.83671E-40)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x056a:
            r2 = 32768(0x8000, float:4.5918E-41)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x0573:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r10)
            goto L_0x05f6
        L_0x0579:
            r3 = 2048(0x800, float:2.87E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            goto L_0x05f6
        L_0x0581:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r6)
            goto L_0x05f6
        L_0x0587:
            r9 = 128(0x80, float:1.794E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r9)
            goto L_0x05f6
        L_0x058f:
            r14 = 32
            java.lang.Integer r2 = java.lang.Integer.valueOf(r14)
            goto L_0x05f6
        L_0x0596:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r12)
            goto L_0x05f6
        L_0x059b:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05a0:
            r2 = 16777216(0x1000000, float:2.3509887E-38)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05a7:
            r2 = 4194304(0x400000, float:5.877472E-39)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05ae:
            r2 = 1048576(0x100000, float:1.469368E-39)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05b5:
            r2 = 262144(0x40000, float:3.67342E-40)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05bc:
            r2 = 65536(0x10000, float:9.18355E-41)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05c3:
            r2 = 16384(0x4000, float:2.2959E-41)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05ca:
            r16 = 4096(0x1000, float:5.74E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r16)
            goto L_0x05f6
        L_0x05d1:
            r8 = 1024(0x400, float:1.435E-42)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r8)
            goto L_0x05f6
        L_0x05d8:
            r17 = 256(0x100, float:3.59E-43)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r17)
            goto L_0x05f6
        L_0x05df:
            r18 = 64
            java.lang.Integer r2 = java.lang.Integer.valueOf(r18)
            goto L_0x05f6
        L_0x05e6:
            r2 = 16
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            goto L_0x05f6
        L_0x05ed:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r15)
            goto L_0x05f6
        L_0x05f2:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
        L_0x05f6:
            if (r2 != 0) goto L_0x0607
            java.lang.String r0 = java.lang.String.valueOf(r1)
            java.lang.String r1 = "Unknown HEVC level string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0607:
            android.util.Pair r1 = new android.util.Pair
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.<init>(r0, r2)
        L_0x0610:
            r2 = r1
            goto L_0x07fb
        L_0x0613:
            java.lang.String r0 = java.lang.String.valueOf(r3)
            java.lang.String r1 = "Unknown HEVC profile string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0622:
            r3 = 2048(0x800, float:2.87E-42)
            r9 = 128(0x80, float:1.794E-43)
            r14 = 32
            r16 = 4096(0x1000, float:5.74E-42)
            r17 = 256(0x100, float:3.59E-43)
            r18 = 64
            java.lang.String r0 = r0.zzk
            int r7 = r1.length
            if (r7 >= r13) goto L_0x0642
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed VP9 codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0642:
            r7 = r1[r11]     // Catch:{ NumberFormatException -> 0x06df }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ NumberFormatException -> 0x06df }
            r1 = r1[r2]     // Catch:{ NumberFormatException -> 0x06df }
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x06df }
            if (r7 == 0) goto L_0x065e
            if (r7 == r11) goto L_0x065c
            if (r7 == r2) goto L_0x065a
            if (r7 == r13) goto L_0x0658
            r1 = r5
            goto L_0x065f
        L_0x0658:
            r1 = r12
            goto L_0x065f
        L_0x065a:
            r1 = r15
            goto L_0x065f
        L_0x065c:
            r1 = r2
            goto L_0x065f
        L_0x065e:
            r1 = r11
        L_0x065f:
            if (r1 != r5) goto L_0x0674
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown VP9 profile: "
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0674:
            r7 = 10
            if (r0 == r7) goto L_0x06b9
            r7 = 11
            if (r0 == r7) goto L_0x06ba
            if (r0 == r8) goto L_0x06b7
            r2 = 21
            if (r0 == r2) goto L_0x06b5
            r2 = 30
            if (r0 == r2) goto L_0x06b2
            r2 = 31
            if (r0 == r2) goto L_0x06b0
            r2 = 40
            if (r0 == r2) goto L_0x06ad
            r2 = 41
            if (r0 == r2) goto L_0x06ab
            r2 = 50
            if (r0 == r2) goto L_0x06a8
            r2 = 51
            if (r0 == r2) goto L_0x06a6
            switch(r0) {
                case 60: goto L_0x06a4;
                case 61: goto L_0x06a1;
                case 62: goto L_0x069f;
                default: goto L_0x069d;
            }
        L_0x069d:
            r2 = r5
            goto L_0x06ba
        L_0x069f:
            r2 = r10
            goto L_0x06ba
        L_0x06a1:
            r2 = r16
            goto L_0x06ba
        L_0x06a4:
            r2 = r3
            goto L_0x06ba
        L_0x06a6:
            r2 = r6
            goto L_0x06ba
        L_0x06a8:
            r2 = r17
            goto L_0x06ba
        L_0x06ab:
            r2 = r9
            goto L_0x06ba
        L_0x06ad:
            r2 = r18
            goto L_0x06ba
        L_0x06b0:
            r2 = r14
            goto L_0x06ba
        L_0x06b2:
            r2 = 16
            goto L_0x06ba
        L_0x06b5:
            r2 = r12
            goto L_0x06ba
        L_0x06b7:
            r2 = r15
            goto L_0x06ba
        L_0x06b9:
            r2 = r11
        L_0x06ba:
            if (r2 != r5) goto L_0x06cf
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown VP9 level: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x06cf:
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.<init>(r1, r2)
        L_0x06dc:
            r2 = r0
            goto L_0x07fb
        L_0x06df:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r1 = "Ignoring malformed VP9 codec string: "
            java.lang.String r0 = r1.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x06ee:
            r3 = 2048(0x800, float:2.87E-42)
            r8 = 1024(0x400, float:1.435E-42)
            r9 = 128(0x80, float:1.794E-43)
            r14 = 32
            r16 = 4096(0x1000, float:5.74E-42)
            r17 = 256(0x100, float:3.59E-43)
            r18 = 64
            java.lang.String r0 = r0.zzk
            int r3 = r1.length
            java.lang.String r6 = "Ignoring malformed AVC codec string: "
            if (r3 >= r2) goto L_0x0710
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r0 = r6.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x0710:
            r20 = r1[r11]     // Catch:{ NumberFormatException -> 0x07ee }
            int r8 = r20.length()     // Catch:{ NumberFormatException -> 0x07ee }
            if (r8 != r7) goto L_0x0730
            r3 = r1[r11]     // Catch:{ NumberFormatException -> 0x07ee }
            r7 = 0
            java.lang.String r3 = r3.substring(r7, r2)     // Catch:{ NumberFormatException -> 0x07ee }
            r7 = 16
            int r3 = java.lang.Integer.parseInt(r3, r7)     // Catch:{ NumberFormatException -> 0x07ee }
            r1 = r1[r11]     // Catch:{ NumberFormatException -> 0x07ee }
            java.lang.String r1 = r1.substring(r15)     // Catch:{ NumberFormatException -> 0x07ee }
            int r0 = java.lang.Integer.parseInt(r1, r7)     // Catch:{ NumberFormatException -> 0x07ee }
            goto L_0x0740
        L_0x0730:
            r7 = 16
            if (r3 < r13) goto L_0x07dd
            r3 = r1[r11]     // Catch:{ NumberFormatException -> 0x07ee }
            int r3 = java.lang.Integer.parseInt(r3)     // Catch:{ NumberFormatException -> 0x07ee }
            r1 = r1[r2]     // Catch:{ NumberFormatException -> 0x07ee }
            int r0 = java.lang.Integer.parseInt(r1)     // Catch:{ NumberFormatException -> 0x07ee }
        L_0x0740:
            r1 = 66
            if (r3 == r1) goto L_0x0769
            r1 = 77
            if (r3 == r1) goto L_0x076a
            r1 = 88
            if (r3 == r1) goto L_0x0767
            r1 = 100
            if (r3 == r1) goto L_0x0765
            r1 = 110(0x6e, float:1.54E-43)
            if (r3 == r1) goto L_0x0763
            r1 = 122(0x7a, float:1.71E-43)
            if (r3 == r1) goto L_0x0761
            r1 = 244(0xf4, float:3.42E-43)
            if (r3 == r1) goto L_0x075e
            r2 = r5
            goto L_0x076a
        L_0x075e:
            r2 = r18
            goto L_0x076a
        L_0x0761:
            r2 = r14
            goto L_0x076a
        L_0x0763:
            r2 = r7
            goto L_0x076a
        L_0x0765:
            r2 = r12
            goto L_0x076a
        L_0x0767:
            r2 = r15
            goto L_0x076a
        L_0x0769:
            r2 = r11
        L_0x076a:
            if (r2 != r5) goto L_0x077f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown AVC profile: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r0 = r0.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x077f:
            switch(r0) {
                case 10: goto L_0x07b8;
                case 11: goto L_0x07b6;
                case 12: goto L_0x07b4;
                case 13: goto L_0x07b2;
                default: goto L_0x0782;
            }
        L_0x0782:
            switch(r0) {
                case 20: goto L_0x07b0;
                case 21: goto L_0x07ad;
                case 22: goto L_0x07ab;
                default: goto L_0x0785;
            }
        L_0x0785:
            switch(r0) {
                case 30: goto L_0x07a8;
                case 31: goto L_0x07a5;
                case 32: goto L_0x07a2;
                default: goto L_0x0788;
            }
        L_0x0788:
            switch(r0) {
                case 40: goto L_0x079f;
                case 41: goto L_0x079c;
                case 42: goto L_0x079a;
                default: goto L_0x078b;
            }
        L_0x078b:
            switch(r0) {
                case 50: goto L_0x0797;
                case 51: goto L_0x0793;
                case 52: goto L_0x0790;
                default: goto L_0x078e;
            }
        L_0x078e:
            r1 = r5
            goto L_0x07b9
        L_0x0790:
            r1 = 65536(0x10000, float:9.18355E-41)
            goto L_0x07b9
        L_0x0793:
            r1 = 32768(0x8000, float:4.5918E-41)
            goto L_0x07b9
        L_0x0797:
            r1 = 16384(0x4000, float:2.2959E-41)
            goto L_0x07b9
        L_0x079a:
            r1 = r10
            goto L_0x07b9
        L_0x079c:
            r1 = r16
            goto L_0x07b9
        L_0x079f:
            r1 = 2048(0x800, float:2.87E-42)
            goto L_0x07b9
        L_0x07a2:
            r1 = 1024(0x400, float:1.435E-42)
            goto L_0x07b9
        L_0x07a5:
            r1 = 512(0x200, float:7.175E-43)
            goto L_0x07b9
        L_0x07a8:
            r1 = r17
            goto L_0x07b9
        L_0x07ab:
            r1 = r9
            goto L_0x07b9
        L_0x07ad:
            r1 = r18
            goto L_0x07b9
        L_0x07b0:
            r1 = r14
            goto L_0x07b9
        L_0x07b2:
            r1 = r7
            goto L_0x07b9
        L_0x07b4:
            r1 = r12
            goto L_0x07b9
        L_0x07b6:
            r1 = r15
            goto L_0x07b9
        L_0x07b8:
            r1 = r11
        L_0x07b9:
            if (r1 != r5) goto L_0x07ce
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown AVC level: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x07ce:
            android.util.Pair r0 = new android.util.Pair
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r0.<init>(r2, r1)
            goto L_0x06dc
        L_0x07dd:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ NumberFormatException -> 0x07ee }
            r1.<init>(r6)     // Catch:{ NumberFormatException -> 0x07ee }
            r1.append(r0)     // Catch:{ NumberFormatException -> 0x07ee }
            java.lang.String r1 = r1.toString()     // Catch:{ NumberFormatException -> 0x07ee }
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r1)     // Catch:{ NumberFormatException -> 0x07ee }
            goto L_0x0006
        L_0x07ee:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            java.lang.String r0 = r6.concat(r0)
            com.google.android.gms.internal.ads.zzfk.zzf(r4, r0)
            goto L_0x0006
        L_0x07fb:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzuj.zza(com.google.android.gms.internal.ads.zzan):android.util.Pair");
    }

    public static zztp zzb() throws zzud {
        List zze = zze(MimeTypes.AUDIO_RAW, false, false);
        if (zze.isEmpty()) {
            return null;
        }
        return (zztp) zze.get(0);
    }

    public static String zzc(zzan zzan) {
        Pair zza2;
        if (MimeTypes.AUDIO_E_AC3_JOC.equals(zzan.zzn)) {
            return MimeTypes.AUDIO_E_AC3;
        }
        if (!MimeTypes.VIDEO_DOLBY_VISION.equals(zzan.zzn) || (zza2 = zza(zzan)) == null) {
            return null;
        }
        int intValue = ((Integer) zza2.first).intValue();
        if (intValue == 16 || intValue == 256) {
            return MimeTypes.VIDEO_H265;
        }
        if (intValue == 512) {
            return MimeTypes.VIDEO_H264;
        }
        return null;
    }

    public static List zzd(zztx zztx, zzan zzan, boolean z, boolean z2) throws zzud {
        String zzc2 = zzc(zzan);
        if (zzc2 == null) {
            return zzgbc.zzm();
        }
        return zze(zzc2, z, z2);
    }

    public static synchronized List zze(String str, boolean z, boolean z2) throws zzud {
        String str2 = str;
        boolean z3 = z;
        boolean z4 = z2;
        synchronized (zzuj.class) {
            zzub zzub = new zzub(str2, z3, z4);
            HashMap hashMap = zzc;
            List list = (List) hashMap.get(zzub);
            if (list != null) {
                return list;
            }
            int i = zzgd.zza;
            ArrayList zzh = zzh(zzub, new zzuh(z3, z4));
            if (z3 && zzh.isEmpty() && zzgd.zza <= 23) {
                zzh = zzh(zzub, new zzug((zzuf) null));
                if (!zzh.isEmpty()) {
                    String str3 = ((zztp) zzh.get(0)).zza;
                    zzfk.zzf("MediaCodecUtil", "MediaCodecList API didn't list secure decoder for: " + str2 + ". Assuming: " + str3);
                }
            }
            if (MimeTypes.AUDIO_RAW.equals(str2)) {
                if (zzgd.zza < 26 && zzgd.zzb.equals("R9") && zzh.size() == 1 && ((zztp) zzh.get(0)).zza.equals("OMX.MTK.AUDIO.DECODER.RAW")) {
                    zzh.add(zztp.zzc("OMX.google.raw.decoder", MimeTypes.AUDIO_RAW, MimeTypes.AUDIO_RAW, (MediaCodecInfo.CodecCapabilities) null, false, true, false, false, false));
                }
                zzi(zzh, new zztz());
            }
            if (zzgd.zza < 32 && zzh.size() > 1 && "OMX.qti.audio.decoder.flac".equals(((zztp) zzh.get(0)).zza)) {
                zzh.add((zztp) zzh.remove(0));
            }
            zzgbc zzk = zzgbc.zzk(zzh);
            hashMap.put(zzub, zzk);
            return zzk;
        }
    }

    @RequiresNonNull({"#2.sampleMimeType"})
    public static List zzf(zztx zztx, zzan zzan, boolean z, boolean z2) throws zzud {
        List zze = zze(zzan.zzn, z, z2);
        List zzd = zzd(zztx, zzan, z, z2);
        zzgaz zzgaz = new zzgaz();
        zzgaz.zzh(zze);
        zzgaz.zzh(zzd);
        return zzgaz.zzi();
    }

    public static List zzg(List list, zzan zzan) {
        ArrayList arrayList = new ArrayList(list);
        zzi(arrayList, new zzua(zzan));
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v21, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v27, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v28, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v30, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v31, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v14 */
    /* JADX WARNING: type inference failed for: r2v29 */
    /* JADX WARNING: type inference failed for: r2v32 */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01c0, code lost:
        if (r2 != r8) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01c6, code lost:
        if (r1.zzb == false) goto L_0x01c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01c8, code lost:
        r22 = r12;
        r18 = r13;
        r20 = r14;
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r6.add(com.google.android.gms.internal.ads.zztp.zzc(r12, r15, r5, r10, r11, r21, r0, false, false));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01e4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        if (r12.endsWith(".secure") == false) goto L_0x0048;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x0234 A[SYNTHETIC, Splitter:B:144:0x0234] */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0254 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList zzh(com.google.android.gms.internal.ads.zzub r23, com.google.android.gms.internal.ads.zzue r24) throws com.google.android.gms.internal.ads.zzud {
        /*
            r1 = r23
            r2 = r24
            java.lang.String r3 = "secure-playback"
            java.lang.String r4 = "tunneled-playback"
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ Exception -> 0x0282 }
            r6.<init>()     // Catch:{ Exception -> 0x0282 }
            java.lang.String r15 = r1.zza     // Catch:{ Exception -> 0x0282 }
            int r14 = r24.zza()     // Catch:{ Exception -> 0x0282 }
            boolean r16 = r24.zze()     // Catch:{ Exception -> 0x0282 }
            r17 = 0
            r13 = r17
        L_0x001b:
            if (r13 >= r14) goto L_0x0281
            android.media.MediaCodecInfo r0 = r2.zzb(r13)     // Catch:{ Exception -> 0x0282 }
            int r7 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0282 }
            r8 = 29
            if (r7 < r8) goto L_0x0034
            boolean r7 = r0.isAlias()     // Catch:{ Exception -> 0x0282 }
            if (r7 == 0) goto L_0x0034
        L_0x002d:
            r18 = r13
            r20 = r14
            r2 = r15
            goto L_0x0276
        L_0x0034:
            java.lang.String r12 = r0.getName()     // Catch:{ Exception -> 0x0282 }
            boolean r7 = r0.isEncoder()     // Catch:{ Exception -> 0x0282 }
            if (r7 != 0) goto L_0x002d
            java.lang.String r7 = ".secure"
            if (r16 != 0) goto L_0x0048
            boolean r9 = r12.endsWith(r7)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
        L_0x0048:
            int r9 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0282 }
            r10 = 24
            if (r9 >= r10) goto L_0x00b8
            java.lang.String r9 = "OMX.SEC.aac.dec"
            boolean r9 = r9.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x005e
            java.lang.String r9 = "OMX.Exynos.AAC.Decoder"
            boolean r9 = r9.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r9 == 0) goto L_0x00b8
        L_0x005e:
            java.lang.String r9 = "samsung"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzc     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 == 0) goto L_0x00b8
            java.lang.String r9 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            java.lang.String r10 = "zeroflte"
            boolean r9 = r9.startsWith(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            java.lang.String r10 = "zerolte"
            boolean r9 = r9.startsWith(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            java.lang.String r10 = "zenlte"
            boolean r9 = r9.startsWith(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = "SC-05G"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = "marinelteatt"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = "404SC"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = "SC-04G"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
            java.lang.String r9 = "SCV31"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb     // Catch:{ Exception -> 0x0282 }
            boolean r9 = r9.equals(r10)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
        L_0x00b8:
            int r9 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0282 }
            r11 = 23
            if (r9 > r11) goto L_0x00ce
            java.lang.String r9 = "audio/eac3-joc"
            boolean r9 = r9.equals(r15)     // Catch:{ Exception -> 0x0282 }
            if (r9 == 0) goto L_0x00ce
            java.lang.String r9 = "OMX.MTK.AUDIO.DECODER.DSPAC3"
            boolean r9 = r9.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r9 != 0) goto L_0x002d
        L_0x00ce:
            java.lang.String[] r9 = r0.getSupportedTypes()     // Catch:{ Exception -> 0x0282 }
            int r10 = r9.length     // Catch:{ Exception -> 0x0282 }
            r11 = r17
        L_0x00d5:
            if (r11 >= r10) goto L_0x00e3
            r5 = r9[r11]     // Catch:{ Exception -> 0x0282 }
            boolean r19 = r5.equalsIgnoreCase(r15)     // Catch:{ Exception -> 0x0282 }
            if (r19 == 0) goto L_0x00e0
            goto L_0x0143
        L_0x00e0:
            int r11 = r11 + 1
            goto L_0x00d5
        L_0x00e3:
            java.lang.String r5 = "video/dolby-vision"
            boolean r5 = r15.equals(r5)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x0109
            java.lang.String r5 = "OMX.MS.HEVCDV.Decoder"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x00f6
            java.lang.String r5 = "video/hevcdv"
            goto L_0x0143
        L_0x00f6:
            java.lang.String r5 = "OMX.RTK.video.decoder"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 != 0) goto L_0x0106
            java.lang.String r5 = "OMX.realtek.video.decoder.tunneled"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x0142
        L_0x0106:
            java.lang.String r5 = "video/dv_hevc"
            goto L_0x0143
        L_0x0109:
            java.lang.String r5 = "audio/alac"
            boolean r5 = r15.equals(r5)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x011c
            java.lang.String r5 = "OMX.lge.alac.decoder"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x011c
            java.lang.String r5 = "audio/x-lg-alac"
            goto L_0x0143
        L_0x011c:
            java.lang.String r5 = "audio/flac"
            boolean r5 = r15.equals(r5)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x012f
            java.lang.String r5 = "OMX.lge.flac.decoder"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x012f
            java.lang.String r5 = "audio/x-lg-flac"
            goto L_0x0143
        L_0x012f:
            java.lang.String r5 = "audio/ac3"
            boolean r5 = r15.equals(r5)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x0142
            java.lang.String r5 = "OMX.lge.ac3.decoder"
            boolean r5 = r5.equals(r12)     // Catch:{ Exception -> 0x0282 }
            if (r5 == 0) goto L_0x0142
            java.lang.String r5 = "audio/lg-ac3"
            goto L_0x0143
        L_0x0142:
            r5 = 0
        L_0x0143:
            if (r5 == 0) goto L_0x002d
            android.media.MediaCodecInfo$CodecCapabilities r10 = r0.getCapabilitiesForType(r5)     // Catch:{ Exception -> 0x0225 }
            boolean r9 = r2.zzd(r4, r5, r10)     // Catch:{ Exception -> 0x0225 }
            boolean r11 = r2.zzc(r4, r5, r10)     // Catch:{ Exception -> 0x0225 }
            boolean r8 = r1.zzc     // Catch:{ Exception -> 0x0225 }
            if (r8 != 0) goto L_0x0158
            if (r11 != 0) goto L_0x002d
            goto L_0x015c
        L_0x0158:
            if (r9 != 0) goto L_0x015c
            goto L_0x002d
        L_0x015c:
            boolean r8 = r2.zzd(r3, r5, r10)     // Catch:{ Exception -> 0x0225 }
            boolean r9 = r2.zzc(r3, r5, r10)     // Catch:{ Exception -> 0x0225 }
            boolean r11 = r1.zzb     // Catch:{ Exception -> 0x0225 }
            r20 = 1
            if (r11 != 0) goto L_0x016d
            if (r9 != 0) goto L_0x002d
            goto L_0x0171
        L_0x016d:
            if (r8 == 0) goto L_0x002d
            r8 = r20
        L_0x0171:
            int r9 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0225 }
            r11 = 29
            if (r9 < r11) goto L_0x017d
            boolean r9 = r0.isHardwareAccelerated()     // Catch:{ Exception -> 0x0225 }
            r11 = r9
            goto L_0x0188
        L_0x017d:
            boolean r9 = zzj(r0, r15)     // Catch:{ Exception -> 0x0225 }
            if (r9 != 0) goto L_0x0186
            r11 = r20
            goto L_0x0188
        L_0x0186:
            r11 = r17
        L_0x0188:
            boolean r21 = zzj(r0, r15)     // Catch:{ Exception -> 0x0225 }
            int r9 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0225 }
            r2 = 29
            if (r9 < r2) goto L_0x0197
            boolean r0 = r0.isVendor()     // Catch:{ Exception -> 0x0225 }
            goto L_0x01bc
        L_0x0197:
            java.lang.String r0 = r0.getName()     // Catch:{ Exception -> 0x0225 }
            java.lang.String r0 = com.google.android.gms.internal.ads.zzfxm.zza(r0)     // Catch:{ Exception -> 0x0225 }
            java.lang.String r2 = "omx.google."
            boolean r2 = r0.startsWith(r2)     // Catch:{ Exception -> 0x0225 }
            if (r2 != 0) goto L_0x01ba
            java.lang.String r2 = "c2.android."
            boolean r2 = r0.startsWith(r2)     // Catch:{ Exception -> 0x0225 }
            if (r2 != 0) goto L_0x01ba
            java.lang.String r2 = "c2.google."
            boolean r0 = r0.startsWith(r2)     // Catch:{ Exception -> 0x0225 }
            if (r0 != 0) goto L_0x01ba
            r0 = r20
            goto L_0x01bc
        L_0x01ba:
            r0 = r17
        L_0x01bc:
            if (r16 == 0) goto L_0x01c2
            boolean r2 = r1.zzb     // Catch:{ Exception -> 0x0225 }
            if (r2 == r8) goto L_0x01c8
        L_0x01c2:
            if (r16 != 0) goto L_0x01f1
            boolean r2 = r1.zzb     // Catch:{ Exception -> 0x01e6 }
            if (r2 != 0) goto L_0x01f1
        L_0x01c8:
            r2 = 0
            r19 = 0
            r7 = r12
            r8 = r15
            r9 = r5
            r22 = r12
            r12 = r21
            r18 = r13
            r13 = r0
            r20 = r14
            r14 = r2
            r2 = r15
            r15 = r19
            com.google.android.gms.internal.ads.zztp r0 = com.google.android.gms.internal.ads.zztp.zzc(r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x01e4 }
            r6.add(r0)     // Catch:{ Exception -> 0x01e4 }
            goto L_0x0276
        L_0x01e4:
            r0 = move-exception
            goto L_0x01ee
        L_0x01e6:
            r0 = move-exception
            r22 = r12
            r18 = r13
            r20 = r14
            r2 = r15
        L_0x01ee:
            r1 = r22
            goto L_0x022c
        L_0x01f1:
            r22 = r12
            r18 = r13
            r20 = r14
            r2 = r15
            if (r16 != 0) goto L_0x0276
            if (r8 == 0) goto L_0x0276
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01e4 }
            r8.<init>()     // Catch:{ Exception -> 0x01e4 }
            r15 = r22
            r8.append(r15)     // Catch:{ Exception -> 0x0222 }
            r8.append(r7)     // Catch:{ Exception -> 0x0222 }
            java.lang.String r7 = r8.toString()     // Catch:{ Exception -> 0x0222 }
            r14 = 0
            r19 = 1
            r8 = r2
            r9 = r5
            r12 = r21
            r13 = r0
            r1 = r15
            r15 = r19
            com.google.android.gms.internal.ads.zztp r0 = com.google.android.gms.internal.ads.zztp.zzc(r7, r8, r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0220 }
            r6.add(r0)     // Catch:{ Exception -> 0x0220 }
            goto L_0x0281
        L_0x0220:
            r0 = move-exception
            goto L_0x022c
        L_0x0222:
            r0 = move-exception
            r1 = r15
            goto L_0x022c
        L_0x0225:
            r0 = move-exception
            r1 = r12
            r18 = r13
            r20 = r14
            r2 = r15
        L_0x022c:
            int r7 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0282 }
            java.lang.String r8 = "MediaCodecUtil"
            r9 = 23
            if (r7 > r9) goto L_0x0254
            boolean r7 = r6.isEmpty()     // Catch:{ Exception -> 0x0282 }
            if (r7 != 0) goto L_0x0254
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0282 }
            r0.<init>()     // Catch:{ Exception -> 0x0282 }
            java.lang.String r5 = "Skipping codec "
            r0.append(r5)     // Catch:{ Exception -> 0x0282 }
            r0.append(r1)     // Catch:{ Exception -> 0x0282 }
            java.lang.String r1 = " (failed to query capabilities)"
            r0.append(r1)     // Catch:{ Exception -> 0x0282 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0282 }
            com.google.android.gms.internal.ads.zzfk.zzc(r8, r0)     // Catch:{ Exception -> 0x0282 }
            goto L_0x0276
        L_0x0254:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0282 }
            r2.<init>()     // Catch:{ Exception -> 0x0282 }
            java.lang.String r3 = "Failed to query codec "
            r2.append(r3)     // Catch:{ Exception -> 0x0282 }
            r2.append(r1)     // Catch:{ Exception -> 0x0282 }
            java.lang.String r1 = " ("
            r2.append(r1)     // Catch:{ Exception -> 0x0282 }
            r2.append(r5)     // Catch:{ Exception -> 0x0282 }
            java.lang.String r1 = ")"
            r2.append(r1)     // Catch:{ Exception -> 0x0282 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x0282 }
            com.google.android.gms.internal.ads.zzfk.zzc(r8, r1)     // Catch:{ Exception -> 0x0282 }
            throw r0     // Catch:{ Exception -> 0x0282 }
        L_0x0276:
            int r13 = r18 + 1
            r1 = r23
            r15 = r2
            r14 = r20
            r2 = r24
            goto L_0x001b
        L_0x0281:
            return r6
        L_0x0282:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzud r1 = new com.google.android.gms.internal.ads.zzud
            r2 = 0
            r1.<init>(r0, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzuj.zzh(com.google.android.gms.internal.ads.zzub, com.google.android.gms.internal.ads.zzue):java.util.ArrayList");
    }

    private static void zzi(List list, zzui zzui) {
        Collections.sort(list, new zzty(zzui));
    }

    private static boolean zzj(MediaCodecInfo mediaCodecInfo, String str) {
        if (zzgd.zza >= 29) {
            return mediaCodecInfo.isSoftwareOnly();
        }
        if (zzcg.zzg(str)) {
            return true;
        }
        String zza2 = zzfxm.zza(mediaCodecInfo.getName());
        if (zza2.startsWith("arc.")) {
            return false;
        }
        if (zza2.startsWith("omx.google.") || zza2.startsWith("omx.ffmpeg.")) {
            return true;
        }
        if ((zza2.startsWith("omx.sec.") && zza2.contains(".sw.")) || zza2.equals("omx.qcom.video.decoder.hevcswvdec") || zza2.startsWith("c2.android.") || zza2.startsWith("c2.google.")) {
            return true;
        }
        if (zza2.startsWith("omx.")) {
            return false;
        }
        if (!zza2.startsWith("c2.")) {
            return true;
        }
        return false;
    }
}
