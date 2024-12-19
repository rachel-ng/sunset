package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzbe {
    private static zzaf zza(zzaf zzaf, zzh zzh, zzal zzal, Boolean bool, Boolean bool2) {
        zzaf zzaf2 = new zzaf();
        Iterator<Integer> zzg = zzaf.zzg();
        while (zzg.hasNext()) {
            int intValue = zzg.next().intValue();
            if (zzaf.zzc(intValue)) {
                zzaq zza = zzal.zza(zzh, (List<zzaq>) Arrays.asList(new zzaq[]{zzaf.zza(intValue), new zzai(Double.valueOf((double) intValue)), zzaf}));
                if (zza.zzd().equals(bool)) {
                    return zzaf2;
                }
                if (bool2 == null || zza.zzd().equals(bool2)) {
                    zzaf2.zzb(intValue, zza);
                }
            }
        }
        return zzaf2;
    }

    private static zzaf zza(zzaf zzaf, zzh zzh, zzal zzal) {
        return zza(zzaf, zzh, zzal, (Boolean) null, (Boolean) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ea, code lost:
        r17 = r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzaq zza(java.lang.String r21, com.google.android.gms.internal.measurement.zzaf r22, com.google.android.gms.internal.measurement.zzh r23, java.util.List<com.google.android.gms.internal.measurement.zzaq> r24) {
        /*
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r21.hashCode()
            int r4 = r21.hashCode()
            java.lang.String r5 = "reverse"
            java.lang.String r6 = "slice"
            java.lang.String r7 = "shift"
            java.lang.String r8 = "every"
            java.lang.String r9 = "sort"
            java.lang.String r10 = "some"
            java.lang.String r11 = "join"
            java.lang.String r12 = "pop"
            java.lang.String r13 = "map"
            java.lang.String r14 = "lastIndexOf"
            java.lang.String r15 = "forEach"
            java.lang.String r1 = "filter"
            java.lang.String r2 = "toString"
            r3 = 1
            r16 = r2
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r3)
            r17 = -1
            switch(r4) {
                case -1776922004: goto L_0x0118;
                case -1354795244: goto L_0x010a;
                case -1274492040: goto L_0x00fd;
                case -934873754: goto L_0x00ee;
                case -895859076: goto L_0x00df;
                case -678635926: goto L_0x00d5;
                case -467511597: goto L_0x00cb;
                case -277637751: goto L_0x00bf;
                case 107868: goto L_0x00b4;
                case 111185: goto L_0x00aa;
                case 3267882: goto L_0x00a0;
                case 3452698: goto L_0x0094;
                case 3536116: goto L_0x008a;
                case 3536286: goto L_0x007f;
                case 96891675: goto L_0x0074;
                case 109407362: goto L_0x0069;
                case 109526418: goto L_0x005e;
                case 965561430: goto L_0x0051;
                case 1099846370: goto L_0x0046;
                case 1943291465: goto L_0x0039;
                default: goto L_0x0035;
            }
        L_0x0035:
            r4 = r16
            goto L_0x0123
        L_0x0039:
            java.lang.String r4 = "indexOf"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0042
            goto L_0x0035
        L_0x0042:
            r0 = 19
            goto L_0x00ea
        L_0x0046:
            boolean r0 = r0.equals(r5)
            if (r0 != 0) goto L_0x004d
            goto L_0x0035
        L_0x004d:
            r0 = 18
            goto L_0x00ea
        L_0x0051:
            java.lang.String r4 = "reduceRight"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x005a
            goto L_0x0035
        L_0x005a:
            r0 = 17
            goto L_0x00ea
        L_0x005e:
            boolean r0 = r0.equals(r6)
            if (r0 != 0) goto L_0x0065
            goto L_0x0035
        L_0x0065:
            r0 = 16
            goto L_0x00ea
        L_0x0069:
            boolean r0 = r0.equals(r7)
            if (r0 != 0) goto L_0x0070
            goto L_0x0035
        L_0x0070:
            r0 = 15
            goto L_0x00ea
        L_0x0074:
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x007b
            goto L_0x0035
        L_0x007b:
            r0 = 14
            goto L_0x00ea
        L_0x007f:
            boolean r0 = r0.equals(r9)
            if (r0 != 0) goto L_0x0086
            goto L_0x0035
        L_0x0086:
            r0 = 13
            goto L_0x00ea
        L_0x008a:
            boolean r0 = r0.equals(r10)
            if (r0 != 0) goto L_0x0091
            goto L_0x0035
        L_0x0091:
            r0 = 12
            goto L_0x00ea
        L_0x0094:
            java.lang.String r4 = "push"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x009d
            goto L_0x0035
        L_0x009d:
            r0 = 11
            goto L_0x00ea
        L_0x00a0:
            boolean r0 = r0.equals(r11)
            if (r0 != 0) goto L_0x00a7
            goto L_0x0035
        L_0x00a7:
            r0 = 10
            goto L_0x00ea
        L_0x00aa:
            boolean r0 = r0.equals(r12)
            if (r0 != 0) goto L_0x00b1
            goto L_0x0035
        L_0x00b1:
            r0 = 9
            goto L_0x00ea
        L_0x00b4:
            boolean r0 = r0.equals(r13)
            if (r0 != 0) goto L_0x00bc
            goto L_0x0035
        L_0x00bc:
            r0 = 8
            goto L_0x00ea
        L_0x00bf:
            java.lang.String r4 = "unshift"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x00c9
            goto L_0x0035
        L_0x00c9:
            r0 = 7
            goto L_0x00ea
        L_0x00cb:
            boolean r0 = r0.equals(r14)
            if (r0 != 0) goto L_0x00d3
            goto L_0x0035
        L_0x00d3:
            r0 = 6
            goto L_0x00ea
        L_0x00d5:
            boolean r0 = r0.equals(r15)
            if (r0 != 0) goto L_0x00dd
            goto L_0x0035
        L_0x00dd:
            r0 = 5
            goto L_0x00ea
        L_0x00df:
            java.lang.String r4 = "splice"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x00e9
            goto L_0x0035
        L_0x00e9:
            r0 = 4
        L_0x00ea:
            r17 = r0
            goto L_0x0035
        L_0x00ee:
            java.lang.String r4 = "reduce"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x00f8
            goto L_0x0035
        L_0x00f8:
            r4 = r16
            r17 = 3
            goto L_0x0123
        L_0x00fd:
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0105
            goto L_0x0035
        L_0x0105:
            r4 = r16
            r17 = 2
            goto L_0x0123
        L_0x010a:
            java.lang.String r4 = "concat"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0114
            goto L_0x0035
        L_0x0114:
            r17 = r3
            goto L_0x0035
        L_0x0118:
            r4 = r16
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L_0x0121
            goto L_0x0123
        L_0x0121:
            r17 = 0
        L_0x0123:
            r18 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            java.lang.String r0 = "Callback should be a method"
            r20 = r4
            r3 = 0
            switch(r17) {
                case 0: goto L_0x075e;
                case 1: goto L_0x06fc;
                case 2: goto L_0x06a2;
                case 3: goto L_0x0696;
                case 4: goto L_0x05d3;
                case 5: goto L_0x05a3;
                case 6: goto L_0x0501;
                case 7: goto L_0x0475;
                case 8: goto L_0x0443;
                case 9: goto L_0x0428;
                case 10: goto L_0x03e7;
                case 11: goto L_0x03b4;
                case 12: goto L_0x0341;
                case 13: goto L_0x02e7;
                case 14: goto L_0x02a8;
                case 15: goto L_0x028f;
                case 16: goto L_0x020f;
                case 17: goto L_0x0203;
                case 18: goto L_0x01cb;
                case 19: goto L_0x0136;
                default: goto L_0x012e;
            }
        L_0x012e:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Command not supported"
            r0.<init>(r1)
            throw r0
        L_0x0136:
            java.lang.String r0 = "indexOf"
            r15 = r24
            r1 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r0, r1, r15)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x0154
            r1 = 0
            java.lang.Object r0 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            r5 = r23
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            goto L_0x0156
        L_0x0154:
            r5 = r23
        L_0x0156:
            int r1 = r24.size()
            r2 = 1
            if (r1 <= r2) goto L_0x0192
            java.lang.Object r1 = r15.get(r2)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.Double r1 = r1.zze()
            double r1 = r1.doubleValue()
            double r1 = com.google.android.gms.internal.measurement.zzg.zza((double) r1)
            int r5 = r22.zzb()
            double r5 = (double) r5
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x0186
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r18)
            r0.<init>(r1)
            return r0
        L_0x0186:
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 >= 0) goto L_0x0191
            int r3 = r22.zzb()
            double r3 = (double) r3
            double r3 = r3 + r1
            goto L_0x0192
        L_0x0191:
            r3 = r1
        L_0x0192:
            java.util.Iterator r1 = r22.zzg()
        L_0x0196:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01c1
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            double r5 = (double) r2
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            r14 = r22
            if (r7 < 0) goto L_0x0196
            com.google.android.gms.internal.measurement.zzaq r2 = r14.zza((int) r2)
            boolean r2 = com.google.android.gms.internal.measurement.zzg.zza(r2, r0)
            if (r2 == 0) goto L_0x0196
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r5)
            r0.<init>(r1)
            return r0
        L_0x01c1:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r18)
            r0.<init>(r1)
            return r0
        L_0x01cb:
            r14 = r22
            r15 = r24
            r0 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r5, (int) r0, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            int r0 = r22.zzb()
            if (r0 == 0) goto L_0x0202
            r3 = 0
        L_0x01da:
            int r1 = r0 / 2
            if (r3 >= r1) goto L_0x0202
            boolean r1 = r14.zzc((int) r3)
            if (r1 == 0) goto L_0x01ff
            com.google.android.gms.internal.measurement.zzaq r1 = r14.zza((int) r3)
            r2 = 0
            r14.zzb(r3, r2)
            int r2 = r0 + -1
            int r2 = r2 - r3
            boolean r4 = r14.zzc((int) r2)
            if (r4 == 0) goto L_0x01fc
            com.google.android.gms.internal.measurement.zzaq r4 = r14.zza((int) r2)
            r14.zzb(r3, r4)
        L_0x01fc:
            r14.zzb(r2, r1)
        L_0x01ff:
            int r3 = r3 + 1
            goto L_0x01da
        L_0x0202:
            return r14
        L_0x0203:
            r14 = r22
            r5 = r23
            r15 = r24
            r0 = 0
            com.google.android.gms.internal.measurement.zzaq r0 = zza((com.google.android.gms.internal.measurement.zzaf) r14, (com.google.android.gms.internal.measurement.zzh) r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15, (boolean) r0)
            return r0
        L_0x020f:
            r14 = r22
            r5 = r23
            r15 = r24
            r0 = 0
            r1 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r6, r1, r15)
            boolean r2 = r24.isEmpty()
            if (r2 == 0) goto L_0x0225
            com.google.android.gms.internal.measurement.zzaq r0 = r22.zzc()
            return r0
        L_0x0225:
            int r2 = r22.zzb()
            double r6 = (double) r2
            java.lang.Object r0 = r15.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r8 = r0.doubleValue()
            double r8 = com.google.android.gms.internal.measurement.zzg.zza((double) r8)
            int r0 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r0 >= 0) goto L_0x024a
            double r8 = r8 + r6
            double r8 = java.lang.Math.max(r8, r3)
            goto L_0x024e
        L_0x024a:
            double r8 = java.lang.Math.min(r8, r6)
        L_0x024e:
            int r0 = r24.size()
            if (r0 != r1) goto L_0x0279
            r0 = 1
            java.lang.Object r0 = r15.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            java.lang.Double r0 = r0.zze()
            double r0 = r0.doubleValue()
            double r0 = com.google.android.gms.internal.measurement.zzg.zza((double) r0)
            int r2 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r2 >= 0) goto L_0x0275
            double r6 = r6 + r0
            double r6 = java.lang.Math.max(r6, r3)
            goto L_0x0279
        L_0x0275:
            double r6 = java.lang.Math.min(r6, r0)
        L_0x0279:
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            int r1 = (int) r8
        L_0x027f:
            double r2 = (double) r1
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 >= 0) goto L_0x028e
            com.google.android.gms.internal.measurement.zzaq r2 = r14.zza((int) r1)
            r0.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            int r1 = r1 + 1
            goto L_0x027f
        L_0x028e:
            return r0
        L_0x028f:
            r14 = r22
            r15 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r7, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x02a0
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x02a0:
            com.google.android.gms.internal.measurement.zzaq r0 = r14.zza((int) r1)
            r14.zzb((int) r1)
            return r0
        L_0x02a8:
            r14 = r22
            r5 = r23
            r15 = r24
            r1 = 0
            r3 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r8, (int) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            java.lang.Object r3 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzaq r3 = (com.google.android.gms.internal.measurement.zzaq) r3
            com.google.android.gms.internal.measurement.zzaq r3 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzar
            if (r4 == 0) goto L_0x02e1
            int r0 = r22.zzb()
            if (r0 == 0) goto L_0x02de
            com.google.android.gms.internal.measurement.zzar r3 = (com.google.android.gms.internal.measurement.zzar) r3
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            com.google.android.gms.internal.measurement.zzaf r0 = zza(r14, r5, r3, r0, r2)
            int r0 = r0.zzb()
            int r1 = r22.zzb()
            if (r0 == r1) goto L_0x02de
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzi
            return r0
        L_0x02de:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x02e1:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x02e7:
            r14 = r22
            r5 = r23
            r15 = r24
            r0 = 1
            r1 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r9, r0, r15)
            int r0 = r22.zzb()
            if (r0 < r1) goto L_0x0340
            java.util.List r0 = r22.zzi()
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x031c
            r1 = 0
            java.lang.Object r2 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzal
            if (r2 == 0) goto L_0x0314
            com.google.android.gms.internal.measurement.zzal r1 = (com.google.android.gms.internal.measurement.zzal) r1
            goto L_0x031d
        L_0x0314:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Comparator should be a method"
            r0.<init>(r1)
            throw r0
        L_0x031c:
            r1 = 0
        L_0x031d:
            com.google.android.gms.internal.measurement.zzbh r2 = new com.google.android.gms.internal.measurement.zzbh
            r2.<init>(r1, r5)
            java.util.Collections.sort(r0, r2)
            r22.zzj()
            java.util.Iterator r0 = r0.iterator()
            r3 = 0
        L_0x032d:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0340
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            int r2 = r3 + 1
            r14.zzb(r3, r1)
            r3 = r2
            goto L_0x032d
        L_0x0340:
            return r14
        L_0x0341:
            r14 = r22
            r5 = r23
            r15 = r24
            r1 = 2
            r2 = 3
            r3 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r10, (int) r3, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            r3 = 0
            java.lang.Object r4 = r15.get(r3)
            com.google.android.gms.internal.measurement.zzaq r4 = (com.google.android.gms.internal.measurement.zzaq) r4
            com.google.android.gms.internal.measurement.zzaq r3 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r4)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x03ae
            int r0 = r22.zzb()
            if (r0 == 0) goto L_0x03ab
            com.google.android.gms.internal.measurement.zzal r3 = (com.google.android.gms.internal.measurement.zzal) r3
            java.util.Iterator r0 = r22.zzg()
        L_0x0368:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x03ab
            java.lang.Object r4 = r0.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            boolean r6 = r14.zzc((int) r4)
            if (r6 == 0) goto L_0x0368
            com.google.android.gms.internal.measurement.zzaq[] r6 = new com.google.android.gms.internal.measurement.zzaq[r2]
            com.google.android.gms.internal.measurement.zzaq r7 = r14.zza((int) r4)
            r8 = 0
            r6[r8] = r7
            com.google.android.gms.internal.measurement.zzai r7 = new com.google.android.gms.internal.measurement.zzai
            double r8 = (double) r4
            java.lang.Double r4 = java.lang.Double.valueOf(r8)
            r7.<init>(r4)
            r4 = 1
            r6[r4] = r7
            r6[r1] = r14
            java.util.List r4 = java.util.Arrays.asList(r6)
            com.google.android.gms.internal.measurement.zzaq r4 = r3.zza((com.google.android.gms.internal.measurement.zzh) r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r4)
            java.lang.Boolean r4 = r4.zzd()
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0368
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzh
            return r0
        L_0x03ab:
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzi
            return r0
        L_0x03ae:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x03b4:
            r14 = r22
            r5 = r23
            r15 = r24
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x03d8
            java.util.Iterator r0 = r24.iterator()
        L_0x03c4:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x03d8
            java.lang.Object r1 = r0.next()
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            r14.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            goto L_0x03c4
        L_0x03d8:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r1 = r22.zzb()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x03e7:
            r14 = r22
            r5 = r23
            r15 = r24
            r0 = 1
            com.google.android.gms.internal.measurement.zzg.zzc(r11, r0, r15)
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x03fa
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzj
            return r0
        L_0x03fa:
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x041c
            r0 = 0
            java.lang.Object r0 = r15.get(r0)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzao
            if (r1 != 0) goto L_0x0419
            boolean r1 = r0 instanceof com.google.android.gms.internal.measurement.zzax
            if (r1 == 0) goto L_0x0414
            goto L_0x0419
        L_0x0414:
            java.lang.String r0 = r0.zzf()
            goto L_0x041e
        L_0x0419:
            java.lang.String r0 = ""
            goto L_0x041e
        L_0x041c:
            java.lang.String r0 = ","
        L_0x041e:
            com.google.android.gms.internal.measurement.zzas r1 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r0 = r14.zzb((java.lang.String) r0)
            r1.<init>(r0)
            return r1
        L_0x0428:
            r14 = r22
            r15 = r24
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r12, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x0439
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x0439:
            r2 = 1
            int r0 = r0 - r2
            com.google.android.gms.internal.measurement.zzaq r1 = r14.zza((int) r0)
            r14.zzb((int) r0)
            return r1
        L_0x0443:
            r14 = r22
            r5 = r23
            r15 = r24
            r1 = 0
            r2 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r13, (int) r2, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r15)
            java.lang.Object r1 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzar
            if (r2 == 0) goto L_0x046f
            int r0 = r22.zzb()
            if (r0 != 0) goto L_0x0468
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x0468:
            com.google.android.gms.internal.measurement.zzar r1 = (com.google.android.gms.internal.measurement.zzar) r1
            com.google.android.gms.internal.measurement.zzaf r0 = zza(r14, r5, r1)
            return r0
        L_0x046f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x0475:
            r14 = r22
            r5 = r23
            r15 = r24
            boolean r0 = r24.isEmpty()
            if (r0 != 0) goto L_0x04f2
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            java.util.Iterator r1 = r24.iterator()
        L_0x048a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x04aa
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r3 != 0) goto L_0x04a2
            r0.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            goto L_0x048a
        L_0x04a2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Argument evaluation failed"
            r0.<init>(r1)
            throw r0
        L_0x04aa:
            int r1 = r0.zzb()
            java.util.Iterator r2 = r22.zzg()
        L_0x04b2:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x04cf
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r4 = r3.intValue()
            int r4 = r4 + r1
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzaq r3 = r14.zza((int) r3)
            r0.zzb(r4, r3)
            goto L_0x04b2
        L_0x04cf:
            r22.zzj()
            java.util.Iterator r1 = r0.zzg()
        L_0x04d6:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x04f2
            java.lang.Object r2 = r1.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r3 = r2.intValue()
            int r2 = r2.intValue()
            com.google.android.gms.internal.measurement.zzaq r2 = r0.zza((int) r2)
            r14.zzb(r3, r2)
            goto L_0x04d6
        L_0x04f2:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            int r1 = r22.zzb()
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0501:
            r6 = r22
            r5 = r23
            r15 = r24
            r1 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r14, r1, r15)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x051e
            r1 = 0
            java.lang.Object r0 = r15.get(r1)
            com.google.android.gms.internal.measurement.zzaq r0 = (com.google.android.gms.internal.measurement.zzaq) r0
            com.google.android.gms.internal.measurement.zzaq r0 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r0)
        L_0x051e:
            int r1 = r22.zzb()
            r2 = 1
            int r1 = r1 - r2
            double r7 = (double) r1
            int r1 = r24.size()
            if (r1 <= r2) goto L_0x0561
            java.lang.Object r1 = r15.get(r2)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            java.lang.Double r5 = r1.zze()
            double r7 = r5.doubleValue()
            boolean r5 = java.lang.Double.isNaN(r7)
            if (r5 == 0) goto L_0x054a
            int r1 = r22.zzb()
            int r1 = r1 - r2
            double r1 = (double) r1
            goto L_0x0556
        L_0x054a:
            java.lang.Double r1 = r1.zze()
            double r1 = r1.doubleValue()
            double r1 = com.google.android.gms.internal.measurement.zzg.zza((double) r1)
        L_0x0556:
            r7 = r1
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0561
            int r1 = r22.zzb()
            double r1 = (double) r1
            double r7 = r7 + r1
        L_0x0561:
            int r1 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x056f
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r18)
            r0.<init>(r1)
            return r0
        L_0x056f:
            int r1 = r22.zzb()
            double r1 = (double) r1
            double r1 = java.lang.Math.min(r1, r7)
            int r1 = (int) r1
        L_0x0579:
            if (r1 < 0) goto L_0x0599
            boolean r2 = r6.zzc((int) r1)
            if (r2 == 0) goto L_0x0596
            com.google.android.gms.internal.measurement.zzaq r2 = r6.zza((int) r1)
            boolean r2 = com.google.android.gms.internal.measurement.zzg.zza(r2, r0)
            if (r2 == 0) goto L_0x0596
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            double r1 = (double) r1
            java.lang.Double r1 = java.lang.Double.valueOf(r1)
            r0.<init>(r1)
            return r0
        L_0x0596:
            int r1 = r1 + -1
            goto L_0x0579
        L_0x0599:
            com.google.android.gms.internal.measurement.zzai r0 = new com.google.android.gms.internal.measurement.zzai
            java.lang.Double r1 = java.lang.Double.valueOf(r18)
            r0.<init>(r1)
            return r0
        L_0x05a3:
            r6 = r22
            r5 = r23
            r3 = r24
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r15, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r3)
            r1 = 0
            java.lang.Object r1 = r3.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            boolean r2 = r1 instanceof com.google.android.gms.internal.measurement.zzar
            if (r2 == 0) goto L_0x05cd
            int r0 = r22.zza()
            if (r0 != 0) goto L_0x05c5
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x05c5:
            com.google.android.gms.internal.measurement.zzar r1 = (com.google.android.gms.internal.measurement.zzar) r1
            zza(r6, r5, r1)
            com.google.android.gms.internal.measurement.zzaq r0 = com.google.android.gms.internal.measurement.zzaq.zzc
            return r0
        L_0x05cd:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x05d3:
            r6 = r22
            r5 = r23
            r3 = r24
            r1 = 2
            boolean r0 = r24.isEmpty()
            if (r0 == 0) goto L_0x05e6
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x05e6:
            r0 = 0
            java.lang.Object r2 = r3.get(r0)
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            java.lang.Double r2 = r2.zze()
            double r7 = r2.doubleValue()
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r2 = (int) r7
            if (r2 >= 0) goto L_0x060a
            int r4 = r22.zzb()
            int r2 = r2 + r4
            int r2 = java.lang.Math.max(r0, r2)
            goto L_0x0614
        L_0x060a:
            int r0 = r22.zzb()
            if (r2 <= r0) goto L_0x0614
            int r2 = r22.zzb()
        L_0x0614:
            int r0 = r22.zzb()
            com.google.android.gms.internal.measurement.zzaf r4 = new com.google.android.gms.internal.measurement.zzaf
            r4.<init>()
            int r7 = r24.size()
            r8 = 1
            if (r7 <= r8) goto L_0x0685
            java.lang.Object r7 = r3.get(r8)
            com.google.android.gms.internal.measurement.zzaq r7 = (com.google.android.gms.internal.measurement.zzaq) r7
            com.google.android.gms.internal.measurement.zzaq r7 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r7)
            java.lang.Double r7 = r7.zze()
            double r7 = r7.doubleValue()
            double r7 = com.google.android.gms.internal.measurement.zzg.zza((double) r7)
            int r7 = (int) r7
            r8 = 0
            int r7 = java.lang.Math.max(r8, r7)
            if (r7 <= 0) goto L_0x0658
            r8 = r2
        L_0x0643:
            int r9 = r2 + r7
            int r9 = java.lang.Math.min(r0, r9)
            if (r8 >= r9) goto L_0x0658
            com.google.android.gms.internal.measurement.zzaq r9 = r6.zza((int) r2)
            r4.zza((com.google.android.gms.internal.measurement.zzaq) r9)
            r6.zzb((int) r2)
            int r8 = r8 + 1
            goto L_0x0643
        L_0x0658:
            int r0 = r24.size()
            if (r0 <= r1) goto L_0x0684
            r0 = r1
        L_0x065f:
            int r7 = r24.size()
            if (r0 >= r7) goto L_0x0684
            java.lang.Object r7 = r3.get(r0)
            com.google.android.gms.internal.measurement.zzaq r7 = (com.google.android.gms.internal.measurement.zzaq) r7
            com.google.android.gms.internal.measurement.zzaq r7 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r7)
            boolean r8 = r7 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r8 != 0) goto L_0x067c
            int r8 = r2 + r0
            int r8 = r8 - r1
            r6.zza((int) r8, (com.google.android.gms.internal.measurement.zzaq) r7)
            int r0 = r0 + 1
            goto L_0x065f
        L_0x067c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed to parse elements to add"
            r0.<init>(r1)
            throw r0
        L_0x0684:
            return r4
        L_0x0685:
            if (r2 >= r0) goto L_0x0695
            com.google.android.gms.internal.measurement.zzaq r1 = r6.zza((int) r2)
            r4.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            r1 = 0
            r6.zzb(r2, r1)
            int r2 = r2 + 1
            goto L_0x0685
        L_0x0695:
            return r4
        L_0x0696:
            r6 = r22
            r5 = r23
            r3 = r24
            r4 = 1
            com.google.android.gms.internal.measurement.zzaq r0 = zza((com.google.android.gms.internal.measurement.zzaf) r6, (com.google.android.gms.internal.measurement.zzh) r5, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r3, (boolean) r4)
            return r0
        L_0x06a2:
            r6 = r22
            r5 = r23
            r3 = r24
            r4 = 1
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r1, (int) r4, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r3)
            r1 = 0
            java.lang.Object r1 = r3.get(r1)
            com.google.android.gms.internal.measurement.zzaq r1 = (com.google.android.gms.internal.measurement.zzaq) r1
            com.google.android.gms.internal.measurement.zzaq r1 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r1)
            boolean r3 = r1 instanceof com.google.android.gms.internal.measurement.zzar
            if (r3 == 0) goto L_0x06f6
            int r0 = r22.zza()
            if (r0 != 0) goto L_0x06c7
            com.google.android.gms.internal.measurement.zzaf r0 = new com.google.android.gms.internal.measurement.zzaf
            r0.<init>()
            return r0
        L_0x06c7:
            com.google.android.gms.internal.measurement.zzaq r0 = r22.zzc()
            com.google.android.gms.internal.measurement.zzaf r0 = (com.google.android.gms.internal.measurement.zzaf) r0
            com.google.android.gms.internal.measurement.zzar r1 = (com.google.android.gms.internal.measurement.zzar) r1
            r3 = 0
            com.google.android.gms.internal.measurement.zzaf r1 = zza(r6, r5, r1, r3, r2)
            com.google.android.gms.internal.measurement.zzaf r2 = new com.google.android.gms.internal.measurement.zzaf
            r2.<init>()
            java.util.Iterator r1 = r1.zzg()
        L_0x06dd:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L_0x06f5
            java.lang.Object r3 = r1.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.google.android.gms.internal.measurement.zzaq r3 = r0.zza((int) r3)
            r2.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            goto L_0x06dd
        L_0x06f5:
            return r2
        L_0x06f6:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            r1.<init>(r0)
            throw r1
        L_0x06fc:
            r6 = r22
            r5 = r23
            r3 = r24
            com.google.android.gms.internal.measurement.zzaq r0 = r22.zzc()
            com.google.android.gms.internal.measurement.zzaf r0 = (com.google.android.gms.internal.measurement.zzaf) r0
            boolean r1 = r24.isEmpty()
            if (r1 != 0) goto L_0x075d
            java.util.Iterator r1 = r24.iterator()
        L_0x0712:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x075d
            java.lang.Object r2 = r1.next()
            com.google.android.gms.internal.measurement.zzaq r2 = (com.google.android.gms.internal.measurement.zzaq) r2
            com.google.android.gms.internal.measurement.zzaq r2 = r5.zza((com.google.android.gms.internal.measurement.zzaq) r2)
            boolean r3 = r2 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r3 != 0) goto L_0x0755
            int r3 = r0.zzb()
            boolean r4 = r2 instanceof com.google.android.gms.internal.measurement.zzaf
            if (r4 == 0) goto L_0x0751
            com.google.android.gms.internal.measurement.zzaf r2 = (com.google.android.gms.internal.measurement.zzaf) r2
            java.util.Iterator r4 = r2.zzg()
        L_0x0734:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x0712
            java.lang.Object r6 = r4.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r7 = r6.intValue()
            int r7 = r7 + r3
            int r6 = r6.intValue()
            com.google.android.gms.internal.measurement.zzaq r6 = r2.zza((int) r6)
            r0.zzb(r7, r6)
            goto L_0x0734
        L_0x0751:
            r0.zzb(r3, r2)
            goto L_0x0712
        L_0x0755:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Failed evaluation of arguments"
            r0.<init>(r1)
            throw r0
        L_0x075d:
            return r0
        L_0x075e:
            r6 = r22
            r3 = r24
            r0 = r20
            r1 = 0
            com.google.android.gms.internal.measurement.zzg.zza((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r3)
            com.google.android.gms.internal.measurement.zzas r0 = new com.google.android.gms.internal.measurement.zzas
            java.lang.String r1 = r22.toString()
            r0.<init>(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbe.zza(java.lang.String, com.google.android.gms.internal.measurement.zzaf, com.google.android.gms.internal.measurement.zzh, java.util.List):com.google.android.gms.internal.measurement.zzaq");
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.internal.measurement.zzaq zza(com.google.android.gms.internal.measurement.zzaf r9, com.google.android.gms.internal.measurement.zzh r10, java.util.List<com.google.android.gms.internal.measurement.zzaq> r11, boolean r12) {
        /*
            java.lang.String r0 = "reduce"
            r1 = 1
            com.google.android.gms.internal.measurement.zzg.zzb((java.lang.String) r0, (int) r1, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r11)
            r2 = 2
            com.google.android.gms.internal.measurement.zzg.zzc(r0, r2, r11)
            r0 = 0
            java.lang.Object r3 = r11.get(r0)
            com.google.android.gms.internal.measurement.zzaq r3 = (com.google.android.gms.internal.measurement.zzaq) r3
            com.google.android.gms.internal.measurement.zzaq r3 = r10.zza((com.google.android.gms.internal.measurement.zzaq) r3)
            boolean r4 = r3 instanceof com.google.android.gms.internal.measurement.zzal
            if (r4 == 0) goto L_0x009f
            int r4 = r11.size()
            if (r4 != r2) goto L_0x0036
            java.lang.Object r11 = r11.get(r1)
            com.google.android.gms.internal.measurement.zzaq r11 = (com.google.android.gms.internal.measurement.zzaq) r11
            com.google.android.gms.internal.measurement.zzaq r11 = r10.zza((com.google.android.gms.internal.measurement.zzaq) r11)
            boolean r4 = r11 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r4 != 0) goto L_0x002e
            goto L_0x003d
        L_0x002e:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Failed to parse initial value"
            r9.<init>(r10)
            throw r9
        L_0x0036:
            int r11 = r9.zzb()
            if (r11 == 0) goto L_0x0097
            r11 = 0
        L_0x003d:
            com.google.android.gms.internal.measurement.zzal r3 = (com.google.android.gms.internal.measurement.zzal) r3
            int r4 = r9.zzb()
            if (r12 == 0) goto L_0x0047
            r5 = r0
            goto L_0x0049
        L_0x0047:
            int r5 = r4 + -1
        L_0x0049:
            if (r12 == 0) goto L_0x004d
            int r4 = r4 - r1
            goto L_0x004e
        L_0x004d:
            r4 = r0
        L_0x004e:
            if (r12 == 0) goto L_0x0052
            r12 = r1
            goto L_0x0053
        L_0x0052:
            r12 = -1
        L_0x0053:
            if (r11 != 0) goto L_0x005a
            com.google.android.gms.internal.measurement.zzaq r11 = r9.zza((int) r5)
            goto L_0x0094
        L_0x005a:
            int r6 = r4 - r5
            int r6 = r6 * r12
            if (r6 < 0) goto L_0x0096
            boolean r6 = r9.zzc((int) r5)
            if (r6 == 0) goto L_0x0094
            r6 = 4
            com.google.android.gms.internal.measurement.zzaq[] r6 = new com.google.android.gms.internal.measurement.zzaq[r6]
            r6[r0] = r11
            com.google.android.gms.internal.measurement.zzaq r11 = r9.zza((int) r5)
            r6[r1] = r11
            com.google.android.gms.internal.measurement.zzai r11 = new com.google.android.gms.internal.measurement.zzai
            double r7 = (double) r5
            java.lang.Double r7 = java.lang.Double.valueOf(r7)
            r11.<init>(r7)
            r6[r2] = r11
            r11 = 3
            r6[r11] = r9
            java.util.List r11 = java.util.Arrays.asList(r6)
            com.google.android.gms.internal.measurement.zzaq r11 = r3.zza((com.google.android.gms.internal.measurement.zzh) r10, (java.util.List<com.google.android.gms.internal.measurement.zzaq>) r11)
            boolean r6 = r11 instanceof com.google.android.gms.internal.measurement.zzaj
            if (r6 != 0) goto L_0x008c
            goto L_0x0094
        L_0x008c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Reduce operation failed"
            r9.<init>(r10)
            throw r9
        L_0x0094:
            int r5 = r5 + r12
            goto L_0x005a
        L_0x0096:
            return r11
        L_0x0097:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "Empty array with no initial value error"
            r9.<init>(r10)
            throw r9
        L_0x009f:
            java.lang.IllegalArgumentException r9 = new java.lang.IllegalArgumentException
            java.lang.String r10 = "Callback should be a method"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbe.zza(com.google.android.gms.internal.measurement.zzaf, com.google.android.gms.internal.measurement.zzh, java.util.List, boolean):com.google.android.gms.internal.measurement.zzaq");
    }
}
