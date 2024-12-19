package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzas implements zzaq, Iterable<zzaq> {
    /* access modifiers changed from: private */
    public final String zza;

    public final int hashCode() {
        return this.zza.hashCode();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x01b7, code lost:
        if (r24.size() > 0) goto L_0x01c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x01b9, code lost:
        r4 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
        r5 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x01c2, code lost:
        r5 = r23;
        r4 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x01d8, code lost:
        if (r24.size() >= 2) goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x01db, code lost:
        r0 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0201, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r3.indexOf(r4, (int) com.google.android.gms.internal.measurement.zzg.zza(r0))));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0202, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("replace", 2, r2);
        r0 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0218, code lost:
        if (r24.isEmpty() != false) goto L_0x023a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x021a, code lost:
        r0 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x022e, code lost:
        if (r24.size() <= 1) goto L_0x023a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0230, code lost:
        r1 = r5.zza(r2.get(1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x023a, code lost:
        r2 = r6.zza;
        r3 = r2.indexOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0240, code lost:
        if (r3 >= 0) goto L_0x0243;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0242, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x0245, code lost:
        if ((r1 instanceof com.google.android.gms.internal.measurement.zzal) == false) goto L_0x026c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0247, code lost:
        r1 = ((com.google.android.gms.internal.measurement.zzal) r1).zza(r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) java.util.Arrays.asList(new com.google.android.gms.internal.measurement.zzaq[]{new com.google.android.gms.internal.measurement.zzas(r0), new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r3)), r6}));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0295, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r2.substring(0, r3) + r1.zzf() + r2.substring(r3 + r0.length()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0296, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("substring", 2, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x02a6, code lost:
        if (r24.isEmpty() != false) goto L_0x02c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02a8, code lost:
        r1 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02c1, code lost:
        r1 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x02c7, code lost:
        if (r24.size() <= 1) goto L_0x02e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x02c9, code lost:
        r2 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02e1, code lost:
        r2 = r0.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02e5, code lost:
        r1 = java.lang.Math.min(java.lang.Math.max(r1, 0), r0.length());
        r2 = java.lang.Math.min(java.lang.Math.max(r2, 0), r0.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x030f, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r0.substring(java.lang.Math.min(r1, r2), java.lang.Math.max(r1, r2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0310, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("split", 2, r2);
        r0 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0320, code lost:
        if (r0.length() != 0) goto L_0x032e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x032d, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x032e, code lost:
        r1 = new java.util.ArrayList();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0338, code lost:
        if (r24.isEmpty() == false) goto L_0x033f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x033a, code lost:
        r1.add(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x033f, code lost:
        r3 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0352, code lost:
        if (r24.size() <= 1) goto L_0x036b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0354, code lost:
        r4 = com.google.android.gms.internal.measurement.zzg.zzc(r5.zza(r2.get(1)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x036b, code lost:
        r4 = 2147483647L;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0372, code lost:
        if (r4 != 0) goto L_0x037a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0379, code lost:
        return new com.google.android.gms.internal.measurement.zzaf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x037a, code lost:
        r0 = r0.split(java.util.regex.Pattern.quote(r3), ((int) r4) + 1);
        r2 = r0.length;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x038a, code lost:
        if (r3.isEmpty() == false) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x038d, code lost:
        if (r0.length <= 0) goto L_0x03a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x038f, code lost:
        r14 = r0[0].isEmpty();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x039e, code lost:
        if (r0[r0.length - 1].isEmpty() == false) goto L_0x03a4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03a0, code lost:
        r2 = r0.length - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03a3, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x03a8, code lost:
        if (((long) r0.length) <= r4) goto L_0x03ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x03aa, code lost:
        r2 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x03ac, code lost:
        if (r14 >= r2) goto L_0x03bb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03ae, code lost:
        r1.add(new com.google.android.gms.internal.measurement.zzas(r0[r14]));
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03c0, code lost:
        return new com.google.android.gms.internal.measurement.zzaf((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x03c1, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("slice", 2, r2);
        r3 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03d1, code lost:
        if (r24.isEmpty() != false) goto L_0x03e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03d3, code lost:
        r7 = r5.zza(r2.get(0)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03e7, code lost:
        r7 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03e8, code lost:
        r7 = com.google.android.gms.internal.measurement.zzg.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03ee, code lost:
        if (r7 >= 0.0d) goto L_0x03fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03f0, code lost:
        r7 = java.lang.Math.max(((double) r3.length()) + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03fb, code lost:
        r7 = java.lang.Math.min(r7, (double) r3.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0404, code lost:
        r4 = (int) r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x040a, code lost:
        if (r24.size() <= 1) goto L_0x041f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x040c, code lost:
        r7 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x041f, code lost:
        r7 = (double) r3.length();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0424, code lost:
        r7 = com.google.android.gms.internal.measurement.zzg.zza(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x042a, code lost:
        if (r7 >= 0.0d) goto L_0x0437;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x042c, code lost:
        r0 = java.lang.Math.max(((double) r3.length()) + r7, 0.0d);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0437, code lost:
        r0 = java.lang.Math.min(r7, (double) r3.length());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0451, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r3.substring(r4, java.lang.Math.max(0, ((int) r0) - r4) + r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0452, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("match", 1, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0462, code lost:
        if (r24.size() > 0) goto L_0x0467;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0464, code lost:
        r1 = "";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0467, code lost:
        r1 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0476, code lost:
        r0 = java.util.regex.Pattern.compile(r1).matcher(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x0482, code lost:
        if (r0.find() == false) goto L_0x0499;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x0498, code lost:
        return new com.google.android.gms.internal.measurement.zzaf(new com.google.android.gms.internal.measurement.zzas(r0.group()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x049b, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x049c, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r5, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x04af, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.trim());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x04b0, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r5, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x04c5, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x04c6, code lost:
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("lastIndexOf", 2, r2);
        r0 = r21.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x04d6, code lost:
        if (r24.size() > 0) goto L_0x04df;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x04d8, code lost:
        r1 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x04df, code lost:
        r1 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x04f3, code lost:
        if (r24.size() >= 2) goto L_0x04f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x04f5, code lost:
        r2 = Double.NaN;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x04f8, code lost:
        r2 = r5.zza(r2.get(1)).zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x050f, code lost:
        if (java.lang.Double.isNaN(r2) == false) goto L_0x0514;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0511, code lost:
        r2 = Double.POSITIVE_INFINITY;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0514, code lost:
        r2 = com.google.android.gms.internal.measurement.zzg.zza(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0527, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0.lastIndexOf(r1, (int) r2)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0528, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r14, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x053b, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toUpperCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x053c, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(com.google.firebase.analytics.FirebaseAnalytics.Event.SEARCH, 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x054b, code lost:
        if (r24.isEmpty() != false) goto L_0x055c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x054d, code lost:
        r0 = r5.zza(r2.get(0)).zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:212:0x055c, code lost:
        r0 = com.google.android.gms.internal.measurement.zzaq.zzc.zzf();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x0562, code lost:
        r0 = java.util.regex.Pattern.compile(r0).matcher(r6.zza);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0570, code lost:
        if (r0.find() == false) goto L_0x0581;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:216:0x0580, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf((double) r0.start()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:0x058c, code lost:
        return new com.google.android.gms.internal.measurement.zzai(java.lang.Double.valueOf(-1.0d));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x058d, code lost:
        com.google.android.gms.internal.measurement.zzg.zza("toLowerCase", 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x05a3, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase(java.util.Locale.ENGLISH));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x05a4, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x05ae, code lost:
        if (r24.isEmpty() == false) goto L_0x05b1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x05b0, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x05b1, code lost:
        r0 = new java.lang.StringBuilder(r6.zza);
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x05bd, code lost:
        if (r14 >= r24.size()) goto L_0x05d3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x05bf, code lost:
        r0.append(r5.zza(r2.get(r14)).zzf());
        r14 = r14 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x05dc, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r0.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:230:0x05dd, code lost:
        r6 = r21;
        r5 = r23;
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc(r4, 1, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:0x05eb, code lost:
        if (r24.isEmpty() != false) goto L_0x0606;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:232:0x05ed, code lost:
        r14 = (int) com.google.android.gms.internal.measurement.zzg.zza(r5.zza(r2.get(0)).zze().doubleValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x0606, code lost:
        r14 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x0607, code lost:
        r0 = r6.zza;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x0609, code lost:
        if (r14 < 0) goto L_0x0620;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x060f, code lost:
        if (r14 < r0.length()) goto L_0x0612;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x061f, code lost:
        return new com.google.android.gms.internal.measurement.zzas(java.lang.String.valueOf(r0.charAt(r14)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x0622, code lost:
        return com.google.android.gms.internal.measurement.zzaq.zzj;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x0623, code lost:
        com.google.android.gms.internal.measurement.zzg.zza(r17, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x0638, code lost:
        return new com.google.android.gms.internal.measurement.zzas(r21.zza.toLowerCase());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x0639, code lost:
        r0 = r6;
        r6 = r21;
        com.google.android.gms.internal.measurement.zzg.zza(r0, 0, r24);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x0642, code lost:
        return r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x0643, code lost:
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zza(r3, 1, r2);
        r0 = r21.zza;
        r1 = r23.zza(r2.get(0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x0664, code lost:
        if (com.google.android.exoplayer2.source.rtsp.SessionDescription.ATTR_LENGTH.equals(r1.zzf()) == false) goto L_0x0669;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x0668, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0669, code lost:
        r1 = r1.zze().doubleValue();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0677, code lost:
        if (r1 != java.lang.Math.floor(r1)) goto L_0x0685;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:252:0x0679, code lost:
        r1 = (int) r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:253:0x067a, code lost:
        if (r1 < 0) goto L_0x0685;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x0680, code lost:
        if (r1 >= r0.length()) goto L_0x0685;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x0684, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzh;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:259:0x0687, code lost:
        return com.google.android.gms.internal.measurement.zzag.zzi;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c3, code lost:
        r4 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c5, code lost:
        r3 = r17;
        r6 = r19;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c9, code lost:
        r17 = "toLocaleLowerCase";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0152, code lost:
        r20 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x019c, code lost:
        r0 = 0.0d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x019e, code lost:
        switch(r20) {
            case 0: goto L_0x0643;
            case 1: goto L_0x0639;
            case 2: goto L_0x0623;
            case 3: goto L_0x05dd;
            case 4: goto L_0x05a4;
            case 5: goto L_0x058d;
            case 6: goto L_0x053c;
            case 7: goto L_0x0528;
            case 8: goto L_0x04c6;
            case 9: goto L_0x04b0;
            case 10: goto L_0x049c;
            case 11: goto L_0x0452;
            case 12: goto L_0x03c1;
            case 13: goto L_0x0310;
            case 14: goto L_0x0296;
            case 15: goto L_0x0202;
            case 16: goto L_0x01a9;
            default: goto L_0x01a1;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x01a8, code lost:
        throw new java.lang.IllegalArgumentException("Command not supported");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x01a9, code lost:
        r2 = r24;
        com.google.android.gms.internal.measurement.zzg.zzc("indexOf", 2, r2);
        r3 = r21.zza;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x018b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            java.lang.String r4 = "charAt"
            boolean r5 = r4.equals(r1)
            java.lang.String r6 = "concat"
            java.lang.String r7 = "indexOf"
            java.lang.String r8 = "replace"
            java.lang.String r9 = "substring"
            java.lang.String r10 = "split"
            java.lang.String r11 = "slice"
            java.lang.String r12 = "match"
            java.lang.String r13 = "lastIndexOf"
            java.lang.String r14 = "toLocaleUpperCase"
            java.lang.String r15 = "search"
            java.lang.String r2 = "toLowerCase"
            java.lang.String r0 = "toLocaleLowerCase"
            java.lang.String r3 = "toString"
            r16 = r4
            java.lang.String r4 = "hasOwnProperty"
            r17 = r14
            java.lang.String r14 = "toUpperCase"
            r18 = r14
            if (r5 != 0) goto L_0x00af
            boolean r5 = r6.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r4.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r7.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r13.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r12.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r8.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r15.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r11.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r10.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r9.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r2.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r0.equals(r1)
            if (r5 != 0) goto L_0x00af
            boolean r5 = r3.equals(r1)
            if (r5 != 0) goto L_0x00af
            r5 = r18
            boolean r18 = r5.equals(r1)
            r14 = r17
            if (r18 != 0) goto L_0x00b3
            boolean r17 = r14.equals(r1)
            if (r17 != 0) goto L_0x00b3
            r17 = r4
            java.lang.String r4 = "trim"
            boolean r4 = r4.equals(r1)
            if (r4 == 0) goto L_0x009d
            goto L_0x00b5
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r3 = 0
            r2[r3] = r1
            java.lang.String r1 = "%s is not a String function"
            java.lang.String r1 = java.lang.String.format(r1, r2)
            r0.<init>(r1)
            throw r0
        L_0x00af:
            r14 = r17
            r5 = r18
        L_0x00b3:
            r17 = r4
        L_0x00b5:
            r22.hashCode()
            int r4 = r22.hashCode()
            r19 = r3
            r20 = -1
            switch(r4) {
                case -1789698943: goto L_0x018b;
                case -1776922004: goto L_0x0179;
                case -1464939364: goto L_0x0167;
                case -1361633751: goto L_0x0156;
                case -1354795244: goto L_0x0149;
                case -1137582698: goto L_0x013f;
                case -906336856: goto L_0x0136;
                case -726908483: goto L_0x012d;
                case -467511597: goto L_0x0123;
                case -399551817: goto L_0x0119;
                case 3568674: goto L_0x010d;
                case 103668165: goto L_0x0103;
                case 109526418: goto L_0x00f9;
                case 109648666: goto L_0x00ee;
                case 530542161: goto L_0x00e3;
                case 1094496948: goto L_0x00d8;
                case 1943291465: goto L_0x00cd;
                default: goto L_0x00c3;
            }
        L_0x00c3:
            r4 = r16
        L_0x00c5:
            r3 = r17
            r6 = r19
        L_0x00c9:
            r17 = r0
            goto L_0x019c
        L_0x00cd:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L_0x00d4
            goto L_0x00c3
        L_0x00d4:
            r1 = 16
            goto L_0x0152
        L_0x00d8:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L_0x00df
            goto L_0x00c3
        L_0x00df:
            r1 = 15
            goto L_0x0152
        L_0x00e3:
            boolean r1 = r1.equals(r9)
            if (r1 != 0) goto L_0x00ea
            goto L_0x00c3
        L_0x00ea:
            r1 = 14
            goto L_0x0152
        L_0x00ee:
            boolean r1 = r1.equals(r10)
            if (r1 != 0) goto L_0x00f5
            goto L_0x00c3
        L_0x00f5:
            r1 = 13
            goto L_0x0152
        L_0x00f9:
            boolean r1 = r1.equals(r11)
            if (r1 != 0) goto L_0x0100
            goto L_0x00c3
        L_0x0100:
            r1 = 12
            goto L_0x0152
        L_0x0103:
            boolean r1 = r1.equals(r12)
            if (r1 != 0) goto L_0x010a
            goto L_0x00c3
        L_0x010a:
            r1 = 11
            goto L_0x0152
        L_0x010d:
            java.lang.String r4 = "trim"
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x0116
            goto L_0x00c3
        L_0x0116:
            r1 = 10
            goto L_0x0152
        L_0x0119:
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L_0x0120
            goto L_0x00c3
        L_0x0120:
            r1 = 9
            goto L_0x0152
        L_0x0123:
            boolean r1 = r1.equals(r13)
            if (r1 != 0) goto L_0x012a
            goto L_0x00c3
        L_0x012a:
            r1 = 8
            goto L_0x0152
        L_0x012d:
            boolean r1 = r1.equals(r14)
            if (r1 != 0) goto L_0x0134
            goto L_0x00c3
        L_0x0134:
            r1 = 7
            goto L_0x0152
        L_0x0136:
            boolean r1 = r1.equals(r15)
            if (r1 != 0) goto L_0x013d
            goto L_0x00c3
        L_0x013d:
            r1 = 6
            goto L_0x0152
        L_0x013f:
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L_0x0147
            goto L_0x00c3
        L_0x0147:
            r1 = 5
            goto L_0x0152
        L_0x0149:
            boolean r1 = r1.equals(r6)
            if (r1 != 0) goto L_0x0151
            goto L_0x00c3
        L_0x0151:
            r1 = 4
        L_0x0152:
            r20 = r1
            goto L_0x00c3
        L_0x0156:
            r4 = r16
            boolean r1 = r1.equals(r4)
            if (r1 != 0) goto L_0x015f
            goto L_0x016f
        L_0x015f:
            r3 = r17
            r6 = r19
            r20 = 3
            goto L_0x00c9
        L_0x0167:
            r4 = r16
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x0171
        L_0x016f:
            goto L_0x00c5
        L_0x0171:
            r3 = r17
            r6 = r19
            r20 = 2
            goto L_0x00c9
        L_0x0179:
            r4 = r16
            r6 = r19
            boolean r1 = r1.equals(r6)
            r3 = r17
            if (r1 != 0) goto L_0x0187
            goto L_0x00c9
        L_0x0187:
            r20 = 1
            goto L_0x00c9
        L_0x018b:
            r4 = r16
            r3 = r17
            r6 = r19
            boolean r1 = r1.equals(r3)
            r17 = r0
            if (r1 != 0) goto L_0x019a
            goto L_0x019c
        L_0x019a:
            r20 = 0
        L_0x019c:
            r0 = 0
            switch(r20) {
                case 0: goto L_0x0643;
                case 1: goto L_0x0639;
                case 2: goto L_0x0623;
                case 3: goto L_0x05dd;
                case 4: goto L_0x05a4;
                case 5: goto L_0x058d;
                case 6: goto L_0x053c;
                case 7: goto L_0x0528;
                case 8: goto L_0x04c6;
                case 9: goto L_0x04b0;
                case 10: goto L_0x049c;
                case 11: goto L_0x0452;
                case 12: goto L_0x03c1;
                case 13: goto L_0x0310;
                case 14: goto L_0x0296;
                case 15: goto L_0x0202;
                case 16: goto L_0x01a9;
                default: goto L_0x01a1;
            }
        L_0x01a1:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x01a9:
            r2 = r24
            r3 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r7, r3, r2)
            r6 = r21
            java.lang.String r3 = r6.zza
            int r4 = r24.size()
            if (r4 > 0) goto L_0x01c2
            com.google.android.gms.internal.measurement.zzaq r4 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r4 = r4.zzf()
            r5 = r23
            goto L_0x01d3
        L_0x01c2:
            r4 = 0
            java.lang.Object r4 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            r5 = r23
            com.google.android.gms.internal.measurement.zzaq r4 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.String r4 = r4.zzf()
        L_0x01d3:
            int r7 = r24.size()
            r8 = 2
            if (r7 >= r8) goto L_0x01db
            goto L_0x01ee
        L_0x01db:
            r0 = 1
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
        L_0x01ee:
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            com.google.android.gms.internal.measurement.zzai r2 = new com.google.android.gms.internal.measurement.zzai
            int r0 = (int) r0
            int r0 = r3.indexOf(r4, r0)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r2.<init>(r0)
            return r2
        L_0x0202:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r8, r0, r2)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r0 = r0.zzf()
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r3 = r24.isEmpty()
            if (r3 != 0) goto L_0x023a
            r3 = 0
            java.lang.Object r0 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
            int r3 = r24.size()
            r4 = 1
            if (r3 <= r4) goto L_0x023a
            java.lang.Object r1 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
        L_0x023a:
            java.lang.String r2 = r6.zza
            int r3 = r2.indexOf(r0)
            if (r3 >= 0) goto L_0x0243
            return r6
        L_0x0243:
            boolean r4 = r1 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x026c
            com.google.android.gms.internal.measurement.zzal r1 = (com.google.android.gms.internal.measurement.zzal) r1
            r4 = 3
            com.google.android.gms.internal.measurement.zzaq[] r4 = new com.google.android.gms.internal.measurement.zzaq[r4]
            com.google.android.gms.internal.measurement.zzas r7 = new com.google.android.gms.internal.measurement.zzas
            r7.<init>(r0)
            r8 = 0
            r4[r8] = r7
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r3
            java.lang.Double r8 = java.lang.Double.valueOf(r8)
            r7.<init>(r8)
            r8 = 1
            r4[r8] = r7
            r7 = 2
            r4[r7] = r6
            java.util.List r4 = java.util.Arrays.asList(r4)
            com.google.android.gms.internal.measurement.zzaq r1 = r1.zza((com.google.android.gms.internal.measurement.zzh) r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r4)
        L_0x026c:
            com.google.android.gms.internal.measurement.zzas r4 = new com.google.android.gms.internal.measurement.zzas
            r5 = 0
            java.lang.String r5 = r2.substring(r5, r3)
            java.lang.String r1 = r1.zzf()
            int r0 = r0.length()
            int r3 = r3 + r0
            java.lang.String r0 = r2.substring(r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r5)
            r2.append(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r4.<init>(r0)
            return r4
        L_0x0296:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r9, r0, r2)
            java.lang.String r0 = r6.zza
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x02c1
            r1 = 0
            java.lang.Object r3 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r3 = (com.google.android.gms.internal.measurement.zzaq) r3
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            java.lang.Double r1 = r1.zze()
            double r3 = r1.doubleValue()
            double r3 = com.google.android.gms.internal.measurement.zzg.zza((double) r3)
            int r1 = (int) r3
            goto L_0x02c2
        L_0x02c1:
            r1 = 0
        L_0x02c2:
            int r3 = r24.size()
            r4 = 1
            if (r3 <= r4) goto L_0x02e1
            java.lang.Object r2 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r2 = r2.doubleValue()
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
            int r2 = (int) r2
            goto L_0x02e5
        L_0x02e1:
            int r2 = r0.length()
        L_0x02e5:
            r3 = 0
            int r1 = java.lang.Math.max(r1, r3)
            int r4 = r0.length()
            int r1 = java.lang.Math.min(r1, r4)
            int r2 = java.lang.Math.max(r2, r3)
            int r3 = r0.length()
            int r2 = java.lang.Math.min(r2, r3)
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            int r4 = java.lang.Math.min(r1, r2)
            int r1 = java.lang.Math.max(r1, r2)
            java.lang.String r0 = r0.substring(r4, r1)
            r3.<init>(r0)
            return r3
        L_0x0310:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r10, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r0.length()
            if (r1 != 0) goto L_0x032e
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r1 = 1
            com.google.android.gms.internal.measurement.zzaq[] r1 = new com.google.android.gms.internal.measurement.zzaq[r1]
            r3 = 0
            r1[r3] = r6
            r0.<init>((com.google.android.gms.internal.measurement.zzaq[]) r1)
            return r0
        L_0x032e:
            r3 = 0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r4 = r24.isEmpty()
            if (r4 == 0) goto L_0x033f
            r1.add(r6)
            goto L_0x03bb
        L_0x033f:
            java.lang.Object r4 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r3 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            java.lang.String r3 = r3.zzf()
            int r4 = r24.size()
            r7 = 1
            if (r4 <= r7) goto L_0x036b
            java.lang.Object r2 = r2.get(r7)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r4 = r2.doubleValue()
            long r4 = com.google.android.gms.internal.measurement.zzg.zzc(r4)
            goto L_0x036e
        L_0x036b:
            r4 = 2147483647(0x7fffffff, double:1.060997895E-314)
        L_0x036e:
            r7 = 0
            int r2 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r2 != 0) goto L_0x037a
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x037a:
            java.lang.String r2 = java.util.regex.Pattern.quote(r3)
            int r7 = (int) r4
            r8 = 1
            int r7 = r7 + r8
            java.lang.String[] r0 = r0.split(r2, r7)
            int r2 = r0.length
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x03a3
            int r3 = r0.length
            if (r3 <= 0) goto L_0x03a3
            r3 = 0
            r3 = r0[r3]
            boolean r14 = r3.isEmpty()
            int r3 = r0.length
            int r3 = r3 - r8
            r3 = r0[r3]
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L_0x03a4
            int r2 = r0.length
            int r2 = r2 - r8
            goto L_0x03a4
        L_0x03a3:
            r14 = 0
        L_0x03a4:
            int r3 = r0.length
            long r7 = (long) r3
            int r3 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x03ac
            int r2 = r2 + -1
        L_0x03ac:
            if (r14 >= r2) goto L_0x03bb
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            r4 = r0[r14]
            r3.<init>(r4)
            r1.add(r3)
            int r14 = r14 + 1
            goto L_0x03ac
        L_0x03bb:
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>((java.util.List<com.google.android.gms.internal.measurement.zzaq>) r1)
            return r0
        L_0x03c1:
            r6 = r21
            r5 = r23
            r2 = r24
            r3 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r11, r3, r2)
            java.lang.String r3 = r6.zza
            boolean r4 = r24.isEmpty()
            if (r4 != 0) goto L_0x03e7
            r4 = 0
            java.lang.Object r7 = r2.get(r4)
            com.google.android.gms.internal.measurement.zzaq r7 = (com.google.android.gms.internal.measurement.zzaq) r7
            com.google.android.gms.internal.measurement.zzaq r4 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r7)
            java.lang.Double r4 = r4.zze()
            double r7 = r4.doubleValue()
            goto L_0x03e8
        L_0x03e7:
            r7 = r0
        L_0x03e8:
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r4 >= 0) goto L_0x03fb
            int r4 = r3.length()
            double r9 = (double) r4
            double r9 = r9 + r7
            double r7 = java.lang.Math.max(r9, r0)
            goto L_0x0404
        L_0x03fb:
            int r4 = r3.length()
            double r9 = (double) r4
            double r7 = java.lang.Math.min(r7, r9)
        L_0x0404:
            int r4 = (int) r7
            int r7 = r24.size()
            r8 = 1
            if (r7 <= r8) goto L_0x041f
            java.lang.Object r2 = r2.get(r8)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r7 = r2.doubleValue()
            goto L_0x0424
        L_0x041f:
            int r2 = r3.length()
            double r7 = (double) r2
        L_0x0424:
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r2 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r2 >= 0) goto L_0x0437
            int r2 = r3.length()
            double r9 = (double) r2
            double r9 = r9 + r7
            double r0 = java.lang.Math.max(r9, r0)
            goto L_0x0440
        L_0x0437:
            int r0 = r3.length()
            double r0 = (double) r0
            double r0 = java.lang.Math.min(r7, r0)
        L_0x0440:
            int r0 = (int) r0
            int r0 = r0 - r4
            r1 = 0
            int r0 = java.lang.Math.max(r1, r0)
            int r0 = r0 + r4
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r3.substring(r4, r0)
            r1.<init>(r0)
            return r1
        L_0x0452:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r12, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r24.size()
            if (r1 > 0) goto L_0x0467
            java.lang.String r1 = ""
            goto L_0x0476
        L_0x0467:
            r1 = 0
            java.lang.Object r2 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.String r1 = r1.zzf()
        L_0x0476:
            java.util.regex.Pattern r1 = java.util.regex.Pattern.compile(r1)
            java.util.regex.Matcher r0 = r1.matcher(r0)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0499
            com.google.android.gms.internal.measurement.zzaf r1 = new com.google.android.gms.internal.measurement.zzaf
            r2 = 1
            com.google.android.gms.internal.measurement.zzaq[] r2 = new com.google.android.gms.internal.measurement.zzaq[r2]
            com.google.android.gms.internal.measurement.zzas r3 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.group()
            r3.<init>(r0)
            r0 = 0
            r2[r0] = r3
            r1.<init>((com.google.android.gms.internal.measurement.zzaq[]) r2)
            return r1
        L_0x0499:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzd
            return r0
        L_0x049c:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.trim()
            r1.<init>(r0)
            return r1
        L_0x04b0:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toUpperCase(r2)
            r1.<init>(r0)
            return r1
        L_0x04c6:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r13, r0, r2)
            java.lang.String r0 = r6.zza
            int r1 = r24.size()
            if (r1 > 0) goto L_0x04df
            com.google.android.gms.internal.measurement.zzaq r1 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r1 = r1.zzf()
            goto L_0x04ee
        L_0x04df:
            r1 = 0
            java.lang.Object r1 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
        L_0x04ee:
            int r3 = r24.size()
            r4 = 2
            if (r3 >= r4) goto L_0x04f8
            r2 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            goto L_0x050b
        L_0x04f8:
            r3 = 1
            java.lang.Object r2 = r2.get(r3)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r2 = r2.doubleValue()
        L_0x050b:
            boolean r4 = java.lang.Double.isNaN(r2)
            if (r4 == 0) goto L_0x0514
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x0518
        L_0x0514:
            double r2 = com.google.android.gms.internal.measurement.zzg.zza((double) r2)
        L_0x0518:
            com.google.android.gms.internal.measurement.zzai r4 = new com.google.android.gms.internal.measurement.zzai
            int r2 = (int) r2
            int r0 = r0.lastIndexOf(r1, r2)
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r4.<init>(r0)
            return r4
        L_0x0528:
            r0 = 0
            r6 = r21
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r14, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toUpperCase()
            r1.<init>(r0)
            return r1
        L_0x053c:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 0
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r15, r1, r2)
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x055c
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.String r0 = r0.zzf()
            goto L_0x0562
        L_0x055c:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            java.lang.String r0 = r0.zzf()
        L_0x0562:
            java.lang.String r1 = r6.zza
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            java.util.regex.Matcher r0 = r0.matcher(r1)
            boolean r1 = r0.find()
            if (r1 == 0) goto L_0x0581
            com.google.android.gms.internal.measurement.zzai r1 = new com.google.android.gms.internal.measurement.zzai
            int r0 = r0.start()
            double r2 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r2)
            r1.<init>(r0)
            return r1
        L_0x0581:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            r1 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x058d:
            r1 = 0
            r6 = r21
            r0 = r2
            r2 = r24
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.toLowerCase(r2)
            r1.<init>(r0)
            return r1
        L_0x05a4:
            r6 = r21
            r5 = r23
            r2 = r24
            boolean r0 = r24.isEmpty()
            if (r0 == 0) goto L_0x05b1
            return r6
        L_0x05b1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = r6.zza
            r0.<init>(r1)
            r14 = 0
        L_0x05b9:
            int r1 = r24.size()
            if (r14 >= r1) goto L_0x05d3
            java.lang.Object r1 = r2.get(r14)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r1 = r1.zzf()
            r0.append(r1)
            int r14 = r14 + 1
            goto L_0x05b9
        L_0x05d3:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            return r1
        L_0x05dd:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r4, r0, r2)
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x0606
            r0 = 0
            java.lang.Object r0 = r2.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            int r14 = (int) r0
            goto L_0x0607
        L_0x0606:
            r14 = 0
        L_0x0607:
            java.lang.String r0 = r6.zza
            if (r14 < 0) goto L_0x0620
            int r1 = r0.length()
            if (r14 < r1) goto L_0x0612
            goto L_0x0620
        L_0x0612:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            char r0 = r0.charAt(r14)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.<init>(r0)
            return r1
        L_0x0620:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzj
            return r0
        L_0x0623:
            r1 = 0
            r6 = r21
            r2 = r24
            r0 = r17
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r0.toLowerCase()
            r1.<init>(r0)
            return r1
        L_0x0639:
            r1 = 0
            r2 = r24
            r0 = r6
            r6 = r21
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            return r6
        L_0x0643:
            r6 = r21
            r5 = r23
            r2 = r24
            r0 = 1
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r3, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r2)
            java.lang.String r0 = r6.zza
            java.lang.Object r1 = r2.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.String r2 = "length"
            java.lang.String r3 = r1.zzf()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0669
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzh
            return r0
        L_0x0669:
            java.lang.Double r1 = r1.zze()
            double r1 = r1.doubleValue()
            double r3 = java.lang.Math.floor(r1)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 != 0) goto L_0x0685
            int r1 = (int) r1
            if (r1 < 0) goto L_0x0685
            int r0 = r0.length()
            if (r1 >= r0) goto L_0x0685
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzh
            return r0
        L_0x0685:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzag.zzi
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzas.zza(java.lang.String, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }

    public final zzaq zzc() {
        return new zzas(this.zza);
    }

    public final Boolean zzd() {
        return Boolean.valueOf(!this.zza.isEmpty());
    }

    public final Double zze() {
        if (this.zza.isEmpty()) {
            return Double.valueOf(0.0d);
        }
        try {
            return Double.valueOf(this.zza);
        } catch (NumberFormatException unused) {
            return Double.valueOf(Double.NaN);
        }
    }

    public final String zzf() {
        return this.zza;
    }

    public final String toString() {
        String str = this.zza;
        return "\"" + str + "\"";
    }

    public final Iterator<zzaq> zzh() {
        return new zzav(this);
    }

    public final Iterator<zzaq> iterator() {
        return new zzau(this);
    }

    public zzas(String str) {
        if (str != null) {
            this.zza = str;
            return;
        }
        throw new IllegalArgumentException("StringValue cannot be null.");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzas)) {
            return false;
        }
        return this.zza.equals(((zzas) obj).zza);
    }
}
