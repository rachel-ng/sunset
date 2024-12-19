package com.google.android.gms.internal.ads;

import android.graphics.Color;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzanl {
    public static final Pattern zza = Pattern.compile("^(\\S+)\\s+-->\\s+(\\S+)(.*)?$");
    private static final Pattern zzb = Pattern.compile("(\\S+?):(\\S+)");
    private static final Map zzc;
    private static final Map zzd;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("white", Integer.valueOf(Color.rgb(255, 255, 255)));
        hashMap.put("lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        hashMap.put("cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        hashMap.put("red", Integer.valueOf(Color.rgb(255, 0, 0)));
        hashMap.put("yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        hashMap.put("magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        hashMap.put("blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        hashMap.put("black", Integer.valueOf(Color.rgb(0, 0, 0)));
        zzc = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("bg_white", Integer.valueOf(Color.rgb(255, 255, 255)));
        hashMap2.put("bg_lime", Integer.valueOf(Color.rgb(0, 255, 0)));
        hashMap2.put("bg_cyan", Integer.valueOf(Color.rgb(0, 255, 255)));
        hashMap2.put("bg_red", Integer.valueOf(Color.rgb(255, 0, 0)));
        hashMap2.put("bg_yellow", Integer.valueOf(Color.rgb(255, 255, 0)));
        hashMap2.put("bg_magenta", Integer.valueOf(Color.rgb(255, 0, 255)));
        hashMap2.put("bg_blue", Integer.valueOf(Color.rgb(0, 0, 255)));
        hashMap2.put("bg_black", Integer.valueOf(Color.rgb(0, 0, 0)));
        zzd = Collections.unmodifiableMap(hashMap2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:111:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x012d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static android.text.SpannedString zza(java.lang.String r17, java.lang.String r18, java.util.List r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            android.text.SpannableStringBuilder r3 = new android.text.SpannableStringBuilder
            r3.<init>()
            java.util.ArrayDeque r4 = new java.util.ArrayDeque
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r6 = 0
            r7 = r6
        L_0x0017:
            int r8 = r18.length()
            if (r7 < r8) goto L_0x003d
        L_0x001d:
            boolean r1 = r4.isEmpty()
            if (r1 != 0) goto L_0x002d
            java.lang.Object r1 = r4.pop()
            com.google.android.gms.internal.ads.zzani r1 = (com.google.android.gms.internal.ads.zzani) r1
            zzg(r0, r1, r5, r3, r2)
            goto L_0x001d
        L_0x002d:
            com.google.android.gms.internal.ads.zzani r1 = com.google.android.gms.internal.ads.zzani.zzb()
            java.util.List r4 = java.util.Collections.emptyList()
            zzg(r0, r1, r4, r3, r2)
            android.text.SpannedString r0 = android.text.SpannedString.valueOf(r3)
            return r0
        L_0x003d:
            int r8 = r7 + 1
            char r9 = r1.charAt(r7)
            r10 = 62
            r11 = 60
            r12 = 38
            r14 = 2
            r15 = -1
            r13 = 1
            if (r9 == r12) goto L_0x0170
            if (r9 == r11) goto L_0x0055
            r3.append(r9)
            goto L_0x020b
        L_0x0055:
            int r9 = r18.length()
            if (r8 < r9) goto L_0x005d
            goto L_0x020b
        L_0x005d:
            char r9 = r1.charAt(r8)
            int r8 = r1.indexOf(r10, r8)
            if (r8 != r15) goto L_0x006c
            int r8 = r18.length()
            goto L_0x006e
        L_0x006c:
            int r8 = r8 + 1
        L_0x006e:
            int r10 = r8 + -2
            char r11 = r1.charAt(r10)
            r12 = 47
            if (r11 != r12) goto L_0x007a
            r11 = r13
            goto L_0x007b
        L_0x007a:
            r11 = r6
        L_0x007b:
            if (r9 != r12) goto L_0x0080
            r16 = r14
            goto L_0x0082
        L_0x0080:
            r16 = r13
        L_0x0082:
            int r7 = r7 + r16
            if (r11 == 0) goto L_0x0087
            goto L_0x0089
        L_0x0087:
            int r10 = r8 + -1
        L_0x0089:
            java.lang.String r7 = r1.substring(r7, r10)
            java.lang.String r10 = r7.trim()
            boolean r10 = r10.isEmpty()
            if (r10 == 0) goto L_0x0099
            goto L_0x020b
        L_0x0099:
            java.lang.String r10 = r7.trim()
            boolean r16 = r10.isEmpty()
            r16 = r16 ^ 1
            com.google.android.gms.internal.ads.zzeq.zzd(r16)
            int r16 = com.google.android.gms.internal.ads.zzgd.zza
            java.lang.String r13 = "[ \\.]"
            java.lang.String[] r10 = r10.split(r13, r14)
            r10 = r10[r6]
            int r13 = r10.hashCode()
            r6 = 98
            if (r13 == r6) goto L_0x011d
            r6 = 99
            if (r13 == r6) goto L_0x0113
            r6 = 105(0x69, float:1.47E-43)
            if (r13 == r6) goto L_0x0109
            r6 = 3650(0xe42, float:5.115E-42)
            if (r13 == r6) goto L_0x00ff
            r6 = 3314158(0x3291ee, float:4.644125E-39)
            if (r13 == r6) goto L_0x00f5
            r6 = 3511770(0x3595da, float:4.921038E-39)
            if (r13 == r6) goto L_0x00eb
            r6 = 117(0x75, float:1.64E-43)
            if (r13 == r6) goto L_0x00e1
            r6 = 118(0x76, float:1.65E-43)
            if (r13 == r6) goto L_0x00d7
            goto L_0x0127
        L_0x00d7:
            java.lang.String r6 = "v"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 7
            goto L_0x0128
        L_0x00e1:
            java.lang.String r6 = "u"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 6
            goto L_0x0128
        L_0x00eb:
            java.lang.String r6 = "ruby"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 4
            goto L_0x0128
        L_0x00f5:
            java.lang.String r6 = "lang"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 3
            goto L_0x0128
        L_0x00ff:
            java.lang.String r6 = "rt"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 5
            goto L_0x0128
        L_0x0109:
            java.lang.String r6 = "i"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = r14
            goto L_0x0128
        L_0x0113:
            java.lang.String r6 = "c"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 1
            goto L_0x0128
        L_0x011d:
            java.lang.String r6 = "b"
            boolean r6 = r10.equals(r6)
            if (r6 == 0) goto L_0x0127
            r13 = 0
            goto L_0x0128
        L_0x0127:
            r13 = r15
        L_0x0128:
            switch(r13) {
                case 0: goto L_0x012d;
                case 1: goto L_0x012d;
                case 2: goto L_0x012d;
                case 3: goto L_0x012d;
                case 4: goto L_0x012d;
                case 5: goto L_0x012d;
                case 6: goto L_0x012d;
                case 7: goto L_0x012d;
                default: goto L_0x012b;
            }
        L_0x012b:
            goto L_0x020b
        L_0x012d:
            if (r9 != r12) goto L_0x0161
        L_0x012f:
            boolean r6 = r4.isEmpty()
            if (r6 == 0) goto L_0x0137
            goto L_0x020b
        L_0x0137:
            java.lang.Object r6 = r4.pop()
            com.google.android.gms.internal.ads.zzani r6 = (com.google.android.gms.internal.ads.zzani) r6
            zzg(r0, r6, r5, r3, r2)
            boolean r7 = r4.isEmpty()
            if (r7 != 0) goto L_0x0154
            com.google.android.gms.internal.ads.zzanh r7 = new com.google.android.gms.internal.ads.zzanh
            int r9 = r3.length()
            r11 = 0
            r7.<init>(r6, r9, r11)
            r5.add(r7)
            goto L_0x0157
        L_0x0154:
            r5.clear()
        L_0x0157:
            java.lang.String r6 = r6.zza
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x012f
            goto L_0x020b
        L_0x0161:
            if (r11 != 0) goto L_0x020b
            int r6 = r3.length()
            com.google.android.gms.internal.ads.zzani r6 = com.google.android.gms.internal.ads.zzani.zza(r7, r6)
            r4.push(r6)
            goto L_0x020b
        L_0x0170:
            r6 = 59
            int r6 = r1.indexOf(r6, r8)
            r7 = 32
            int r13 = r1.indexOf(r7, r8)
            if (r6 != r15) goto L_0x0180
            r6 = r13
            goto L_0x0186
        L_0x0180:
            if (r13 == r15) goto L_0x0186
            int r6 = java.lang.Math.min(r6, r13)
        L_0x0186:
            if (r6 == r15) goto L_0x0208
            java.lang.String r8 = r1.substring(r8, r6)
            int r9 = r8.hashCode()
            r15 = 3309(0xced, float:4.637E-42)
            if (r9 == r15) goto L_0x01c1
            r15 = 3464(0xd88, float:4.854E-42)
            if (r9 == r15) goto L_0x01b7
            r15 = 96708(0x179c4, float:1.35517E-40)
            if (r9 == r15) goto L_0x01ad
            r15 = 3374865(0x337f11, float:4.729193E-39)
            if (r9 == r15) goto L_0x01a3
            goto L_0x01cb
        L_0x01a3:
            java.lang.String r9 = "nbsp"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01cb
            r15 = r14
            goto L_0x01cc
        L_0x01ad:
            java.lang.String r9 = "amp"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01cb
            r15 = 3
            goto L_0x01cc
        L_0x01b7:
            java.lang.String r9 = "lt"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01cb
            r15 = 0
            goto L_0x01cc
        L_0x01c1:
            java.lang.String r9 = "gt"
            boolean r9 = r8.equals(r9)
            if (r9 == 0) goto L_0x01cb
            r15 = 1
            goto L_0x01cc
        L_0x01cb:
            r15 = -1
        L_0x01cc:
            if (r15 == 0) goto L_0x01fb
            r9 = 1
            if (r15 == r9) goto L_0x01f7
            if (r15 == r14) goto L_0x01f3
            r9 = 3
            if (r15 == r9) goto L_0x01ef
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r9 = "ignoring unsupported entity: '&"
            r7.<init>(r9)
            r7.append(r8)
            java.lang.String r8 = ";'"
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "WebvttCueParser"
            com.google.android.gms.internal.ads.zzfk.zzf(r8, r7)
            goto L_0x01fe
        L_0x01ef:
            r3.append(r12)
            goto L_0x01fe
        L_0x01f3:
            r3.append(r7)
            goto L_0x01fe
        L_0x01f7:
            r3.append(r10)
            goto L_0x01fe
        L_0x01fb:
            r3.append(r11)
        L_0x01fe:
            if (r6 != r13) goto L_0x0205
            java.lang.String r7 = " "
            r3.append(r7)
        L_0x0205:
            int r7 = r6 + 1
            goto L_0x020c
        L_0x0208:
            r3.append(r9)
        L_0x020b:
            r7 = r8
        L_0x020c:
            r6 = 0
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanl.zza(java.lang.String, java.lang.String, java.util.List):android.text.SpannedString");
    }

    static zzeg zzb(String str) {
        zzank zzank = new zzank();
        zzh(str, zzank);
        return zzank.zza();
    }

    public static zzane zzc(zzfu zzfu, List list) {
        String zzy = zzfu.zzy(zzfxs.zzc);
        if (zzy != null) {
            Pattern pattern = zza;
            Matcher matcher = pattern.matcher(zzy);
            if (matcher.matches()) {
                return zze((String) null, matcher, zzfu, list);
            }
            String zzy2 = zzfu.zzy(zzfxs.zzc);
            if (zzy2 != null) {
                Matcher matcher2 = pattern.matcher(zzy2);
                if (matcher2.matches()) {
                    return zze(zzy.trim(), matcher2, zzfu, list);
                }
            }
        }
        return null;
    }

    private static int zzd(List list, String str, zzani zzani) {
        List zzf = zzf(list, str, zzani);
        for (int i = 0; i < zzf.size(); i++) {
            zzand zzand = ((zzanj) zzf.get(i)).zzb;
            if (zzand.zze() != -1) {
                return zzand.zze();
            }
        }
        return -1;
    }

    private static zzane zze(String str, Matcher matcher, zzfu zzfu, List list) {
        zzank zzank = new zzank();
        try {
            String group = matcher.group(1);
            if (group != null) {
                zzank.zza = zzann.zzb(group);
                String group2 = matcher.group(2);
                if (group2 != null) {
                    zzank.zzb = zzann.zzb(group2);
                    String group3 = matcher.group(3);
                    group3.getClass();
                    zzh(group3, zzank);
                    StringBuilder sb = new StringBuilder();
                    String zzy = zzfu.zzy(zzfxs.zzc);
                    while (!TextUtils.isEmpty(zzy)) {
                        if (sb.length() > 0) {
                            sb.append("\n");
                        }
                        sb.append(zzy.trim());
                        zzy = zzfu.zzy(zzfxs.zzc);
                    }
                    zzank.zzc = zza(str, sb.toString(), list);
                    return new zzane(zzank.zza().zzp(), zzank.zza, zzank.zzb);
                }
                throw null;
            }
            throw null;
        } catch (NumberFormatException unused) {
            zzfk.zzf("WebvttCueParser", "Skipping cue with bad header: ".concat(String.valueOf(matcher.group())));
            return null;
        }
    }

    private static List zzf(List list, String str, zzani zzani) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzand zzand = (zzand) list.get(i);
            int zzf = zzand.zzf(str, zzani.zza, zzani.zzd, zzani.zzc);
            if (zzf > 0) {
                arrayList.add(new zzanj(zzf, zzand));
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:113:? A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x015b  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0178  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zzg(java.lang.String r18, com.google.android.gms.internal.ads.zzani r19, java.util.List r20, android.text.SpannableStringBuilder r21, java.util.List r22) {
        /*
            r0 = r18
            r1 = r19
            r2 = r21
            r3 = r22
            int r4 = r1.zzb
            int r5 = r21.length()
            java.lang.String r6 = r1.zza
            int r7 = r6.hashCode()
            r9 = 2
            r11 = -1
            if (r7 == 0) goto L_0x007d
            r13 = 105(0x69, float:1.47E-43)
            if (r7 == r13) goto L_0x0073
            r13 = 3314158(0x3291ee, float:4.644125E-39)
            if (r7 == r13) goto L_0x0069
            r13 = 3511770(0x3595da, float:4.921038E-39)
            if (r7 == r13) goto L_0x005f
            r13 = 98
            if (r7 == r13) goto L_0x0055
            r13 = 99
            if (r7 == r13) goto L_0x004b
            r13 = 117(0x75, float:1.64E-43)
            if (r7 == r13) goto L_0x0041
            r13 = 118(0x76, float:1.65E-43)
            if (r7 == r13) goto L_0x0037
            goto L_0x0087
        L_0x0037:
            java.lang.String r7 = "v"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 6
            goto L_0x0088
        L_0x0041:
            java.lang.String r7 = "u"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 3
            goto L_0x0088
        L_0x004b:
            java.lang.String r7 = "c"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 4
            goto L_0x0088
        L_0x0055:
            java.lang.String r7 = "b"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 0
            goto L_0x0088
        L_0x005f:
            java.lang.String r7 = "ruby"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = r9
            goto L_0x0088
        L_0x0069:
            java.lang.String r7 = "lang"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 5
            goto L_0x0088
        L_0x0073:
            java.lang.String r7 = "i"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 1
            goto L_0x0088
        L_0x007d:
            java.lang.String r7 = ""
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0087
            r6 = 7
            goto L_0x0088
        L_0x0087:
            r6 = r11
        L_0x0088:
            r7 = 33
            switch(r6) {
                case 0: goto L_0x0164;
                case 1: goto L_0x015b;
                case 2: goto L_0x00e1;
                case 3: goto L_0x00d7;
                case 4: goto L_0x008f;
                case 5: goto L_0x016d;
                case 6: goto L_0x016d;
                case 7: goto L_0x016d;
                default: goto L_0x008d;
            }
        L_0x008d:
            goto L_0x022b
        L_0x008f:
            java.util.Set r6 = r1.zzd
            java.util.Iterator r6 = r6.iterator()
        L_0x0095:
            boolean r13 = r6.hasNext()
            if (r13 == 0) goto L_0x016d
            java.lang.Object r13 = r6.next()
            java.lang.String r13 = (java.lang.String) r13
            java.util.Map r14 = zzc
            boolean r15 = r14.containsKey(r13)
            if (r15 == 0) goto L_0x00bc
            java.lang.Object r13 = r14.get(r13)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            android.text.style.ForegroundColorSpan r14 = new android.text.style.ForegroundColorSpan
            r14.<init>(r13)
            r2.setSpan(r14, r4, r5, r7)
            goto L_0x0095
        L_0x00bc:
            java.util.Map r14 = zzd
            boolean r15 = r14.containsKey(r13)
            if (r15 == 0) goto L_0x0095
            java.lang.Object r13 = r14.get(r13)
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            android.text.style.BackgroundColorSpan r14 = new android.text.style.BackgroundColorSpan
            r14.<init>(r13)
            r2.setSpan(r14, r4, r5, r7)
            goto L_0x0095
        L_0x00d7:
            android.text.style.UnderlineSpan r6 = new android.text.style.UnderlineSpan
            r6.<init>()
            r2.setSpan(r6, r4, r5, r7)
            goto L_0x016d
        L_0x00e1:
            int r6 = zzd(r3, r0, r1)
            java.util.ArrayList r13 = new java.util.ArrayList
            int r14 = r20.size()
            r13.<init>(r14)
            r14 = r20
            r13.addAll(r14)
            java.util.Comparator r14 = com.google.android.gms.internal.ads.zzanh.zza
            java.util.Collections.sort(r13, r14)
            int r14 = r1.zzb
            r15 = 0
            r16 = 0
        L_0x00ff:
            int r10 = r13.size()
            if (r15 >= r10) goto L_0x016d
            java.lang.Object r10 = r13.get(r15)
            com.google.android.gms.internal.ads.zzanh r10 = (com.google.android.gms.internal.ads.zzanh) r10
            com.google.android.gms.internal.ads.zzani r10 = r10.zzb
            java.lang.String r10 = r10.zza
            java.lang.String r8 = "rt"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0157
            java.lang.Object r8 = r13.get(r15)
            com.google.android.gms.internal.ads.zzanh r8 = (com.google.android.gms.internal.ads.zzanh) r8
            com.google.android.gms.internal.ads.zzani r10 = r8.zzb
            int r10 = zzd(r3, r0, r10)
            if (r10 == r11) goto L_0x012a
            goto L_0x012f
        L_0x012a:
            if (r6 == r11) goto L_0x012e
            r10 = r6
            goto L_0x012f
        L_0x012e:
            r10 = 1
        L_0x012f:
            com.google.android.gms.internal.ads.zzani r11 = r8.zzb
            int r11 = r11.zzb
            int r11 = r11 - r16
            int r8 = r8.zzc
            int r8 = r8 - r16
            java.lang.CharSequence r17 = r2.subSequence(r11, r8)
            r2.delete(r11, r8)
            com.google.android.gms.internal.ads.zzen r8 = new com.google.android.gms.internal.ads.zzen
            java.lang.String r12 = r17.toString()
            r8.<init>(r12, r10)
            r2.setSpan(r8, r14, r11, r7)
            int r8 = r17.length()
            int r16 = r16 + r8
            r14 = r11
        L_0x0157:
            int r15 = r15 + 1
            r11 = -1
            goto L_0x00ff
        L_0x015b:
            android.text.style.StyleSpan r6 = new android.text.style.StyleSpan
            r6.<init>(r9)
            r2.setSpan(r6, r4, r5, r7)
            goto L_0x016d
        L_0x0164:
            android.text.style.StyleSpan r6 = new android.text.style.StyleSpan
            r8 = 1
            r6.<init>(r8)
            r2.setSpan(r6, r4, r5, r7)
        L_0x016d:
            java.util.List r0 = zzf(r3, r0, r1)
            r10 = 0
        L_0x0172:
            int r1 = r0.size()
            if (r10 >= r1) goto L_0x022b
            java.lang.Object r1 = r0.get(r10)
            com.google.android.gms.internal.ads.zzanj r1 = (com.google.android.gms.internal.ads.zzanj) r1
            com.google.android.gms.internal.ads.zzand r1 = r1.zzb
            if (r1 != 0) goto L_0x0187
            r6 = -1
            r8 = 3
            r12 = 1
            goto L_0x0227
        L_0x0187:
            int r3 = r1.zzg()
            r6 = -1
            if (r3 == r6) goto L_0x019a
            android.text.style.StyleSpan r3 = new android.text.style.StyleSpan
            int r8 = r1.zzg()
            r3.<init>(r8)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
        L_0x019a:
            boolean r3 = r1.zzz()
            if (r3 == 0) goto L_0x01a8
            android.text.style.UnderlineSpan r3 = new android.text.style.UnderlineSpan
            r3.<init>()
            r2.setSpan(r3, r4, r5, r7)
        L_0x01a8:
            boolean r3 = r1.zzy()
            if (r3 == 0) goto L_0x01ba
            android.text.style.ForegroundColorSpan r3 = new android.text.style.ForegroundColorSpan
            int r8 = r1.zzc()
            r3.<init>(r8)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
        L_0x01ba:
            boolean r3 = r1.zzx()
            if (r3 == 0) goto L_0x01cc
            android.text.style.BackgroundColorSpan r3 = new android.text.style.BackgroundColorSpan
            int r8 = r1.zzb()
            r3.<init>(r8)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
        L_0x01cc:
            java.lang.String r3 = r1.zzr()
            if (r3 == 0) goto L_0x01de
            android.text.style.TypefaceSpan r3 = new android.text.style.TypefaceSpan
            java.lang.String r8 = r1.zzr()
            r3.<init>(r8)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
        L_0x01de:
            int r3 = r1.zzd()
            r8 = 1
            if (r3 == r8) goto L_0x020a
            if (r3 == r9) goto L_0x01fc
            r8 = 3
            if (r3 == r8) goto L_0x01ec
        L_0x01ea:
            r12 = 1
            goto L_0x0219
        L_0x01ec:
            android.text.style.RelativeSizeSpan r3 = new android.text.style.RelativeSizeSpan
            float r11 = r1.zza()
            r12 = 1120403456(0x42c80000, float:100.0)
            float r11 = r11 / r12
            r3.<init>(r11)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
            goto L_0x01ea
        L_0x01fc:
            r8 = 3
            android.text.style.RelativeSizeSpan r3 = new android.text.style.RelativeSizeSpan
            float r11 = r1.zza()
            r3.<init>(r11)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
            goto L_0x01ea
        L_0x020a:
            r8 = 3
            android.text.style.AbsoluteSizeSpan r3 = new android.text.style.AbsoluteSizeSpan
            float r11 = r1.zza()
            int r11 = (int) r11
            r12 = 1
            r3.<init>(r11, r12)
            com.google.android.gms.internal.ads.zzeo.zza(r2, r3, r4, r5, r7)
        L_0x0219:
            boolean r1 = r1.zzw()
            if (r1 == 0) goto L_0x0227
            com.google.android.gms.internal.ads.zzem r1 = new com.google.android.gms.internal.ads.zzem
            r1.<init>()
            r2.setSpan(r1, r4, r5, r7)
        L_0x0227:
            int r10 = r10 + 1
            goto L_0x0172
        L_0x022b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzanl.zzg(java.lang.String, com.google.android.gms.internal.ads.zzani, java.util.List, android.text.SpannableStringBuilder, java.util.List):void");
    }

    private static void zzh(String str, zzank zzank) {
        zzank zzank2 = zzank;
        Matcher matcher = zzb.matcher(str);
        while (matcher.find()) {
            int i = 1;
            String group = matcher.group(1);
            group.getClass();
            int i2 = 2;
            String group2 = matcher.group(2);
            group2.getClass();
            try {
                char c2 = 65535;
                if ("line".equals(group)) {
                    int indexOf = group2.indexOf(44);
                    if (indexOf != -1) {
                        String substring = group2.substring(indexOf + 1);
                        switch (substring.hashCode()) {
                            case -1364013995:
                                if (substring.equals(TtmlNode.CENTER)) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                            case -1074341483:
                                if (substring.equals("middle")) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case 100571:
                                if (substring.equals(TtmlNode.END)) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 109757538:
                                if (substring.equals(TtmlNode.START)) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                        }
                        if (c2 == 0) {
                            i2 = 0;
                        } else if (c2 == 1 || c2 == 2) {
                            i2 = 1;
                        } else if (c2 != 3) {
                            zzfk.zzf("WebvttCueParser", "Invalid anchor value: ".concat(String.valueOf(substring)));
                            i2 = Integer.MIN_VALUE;
                        }
                        zzank2.zzg = i2;
                        group2 = group2.substring(0, indexOf);
                    }
                    if (group2.endsWith("%")) {
                        zzank2.zze = zzann.zza(group2);
                        zzank2.zzf = 0;
                    } else {
                        zzank2.zze = (float) Integer.parseInt(group2);
                        zzank2.zzf = 1;
                    }
                } else if ("align".equals(group)) {
                    switch (group2.hashCode()) {
                        case -1364013995:
                            if (group2.equals(TtmlNode.CENTER)) {
                                c2 = 2;
                                break;
                            }
                            break;
                        case -1074341483:
                            if (group2.equals("middle")) {
                                c2 = 3;
                                break;
                            }
                            break;
                        case 100571:
                            if (group2.equals(TtmlNode.END)) {
                                c2 = 4;
                                break;
                            }
                            break;
                        case 3317767:
                            if (group2.equals(TtmlNode.LEFT)) {
                                c2 = 1;
                                break;
                            }
                            break;
                        case 108511772:
                            if (group2.equals(TtmlNode.RIGHT)) {
                                c2 = 5;
                                break;
                            }
                            break;
                        case 109757538:
                            if (group2.equals(TtmlNode.START)) {
                                c2 = 0;
                                break;
                            }
                            break;
                    }
                    if (c2 != 0) {
                        if (c2 != 1) {
                            if (!(c2 == 2 || c2 == 3)) {
                                if (c2 != 4) {
                                    i = 5;
                                    if (c2 != 5) {
                                        zzfk.zzf("WebvttCueParser", "Invalid alignment value: ".concat(group2));
                                    }
                                } else {
                                    i = 3;
                                }
                            }
                            i = 2;
                        } else {
                            i = 4;
                        }
                    }
                    zzank2.zzd = i;
                } else if ("position".equals(group)) {
                    int indexOf2 = group2.indexOf(44);
                    if (indexOf2 != -1) {
                        String substring2 = group2.substring(indexOf2 + 1);
                        switch (substring2.hashCode()) {
                            case -1842484672:
                                if (substring2.equals("line-left")) {
                                    c2 = 0;
                                    break;
                                }
                                break;
                            case -1364013995:
                                if (substring2.equals(TtmlNode.CENTER)) {
                                    c2 = 2;
                                    break;
                                }
                                break;
                            case -1276788989:
                                if (substring2.equals("line-right")) {
                                    c2 = 4;
                                    break;
                                }
                                break;
                            case -1074341483:
                                if (substring2.equals("middle")) {
                                    c2 = 3;
                                    break;
                                }
                                break;
                            case 100571:
                                if (substring2.equals(TtmlNode.END)) {
                                    c2 = 5;
                                    break;
                                }
                                break;
                            case 109757538:
                                if (substring2.equals(TtmlNode.START)) {
                                    c2 = 1;
                                    break;
                                }
                                break;
                        }
                        if (c2 == 0 || c2 == 1) {
                            i = 0;
                        } else if (!(c2 == 2 || c2 == 3)) {
                            if (c2 == 4 || c2 == 5) {
                                i = 2;
                            } else {
                                zzfk.zzf("WebvttCueParser", "Invalid anchor value: ".concat(String.valueOf(substring2)));
                                i = Integer.MIN_VALUE;
                            }
                        }
                        zzank2.zzi = i;
                        group2 = group2.substring(0, indexOf2);
                    }
                    zzank2.zzh = zzann.zza(group2);
                } else if ("size".equals(group)) {
                    zzank2.zzj = zzann.zza(group2);
                } else if (!"vertical".equals(group)) {
                    zzfk.zzf("WebvttCueParser", "Unknown cue setting " + group + ":" + group2);
                } else {
                    int hashCode = group2.hashCode();
                    if (hashCode != 3462) {
                        if (hashCode == 3642 && group2.equals("rl")) {
                            c2 = 0;
                        }
                    } else if (group2.equals("lr")) {
                        c2 = 1;
                    }
                    if (c2 != 0) {
                        if (c2 != 1) {
                            zzfk.zzf("WebvttCueParser", "Invalid 'vertical' value: ".concat(group2));
                            i = Integer.MIN_VALUE;
                        } else {
                            i = 2;
                        }
                    }
                    zzank2.zzk = i;
                }
            } catch (NumberFormatException unused) {
                zzfk.zzf("WebvttCueParser", "Skipping bad cue setting: ".concat(String.valueOf(matcher.group())));
            }
        }
    }
}
