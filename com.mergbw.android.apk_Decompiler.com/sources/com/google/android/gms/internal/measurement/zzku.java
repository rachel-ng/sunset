package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
final class zzku {
    private static final char[] zza;

    static String zza(zzkt zzkt, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zza(zzkt, sb, 0);
        return sb.toString();
    }

    static {
        char[] cArr = new char[80];
        zza = cArr;
        Arrays.fill(cArr, ' ');
    }

    private static void zza(int i, StringBuilder sb) {
        while (i > 0) {
            char[] cArr = zza;
            int length = i > cArr.length ? cArr.length : i;
            sb.append(cArr, 0, length);
            i -= length;
        }
    }

    static void zza(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object zza2 : (List) obj) {
                zza(sb, i, str, zza2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry zza3 : ((Map) obj).entrySet()) {
                zza(sb, i, str, zza3);
            }
        } else {
            sb.append(10);
            zza(i, sb);
            if (!str.isEmpty()) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(Character.toLowerCase(str.charAt(0)));
                for (int i2 = 1; i2 < str.length(); i2++) {
                    char charAt = str.charAt(i2);
                    if (Character.isUpperCase(charAt)) {
                        sb2.append("_");
                    }
                    sb2.append(Character.toLowerCase(charAt));
                }
                str = sb2.toString();
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                sb.append(zzmb.zza(zzia.zza((String) obj)));
                sb.append(Typography.quote);
            } else if (obj instanceof zzia) {
                sb.append(": \"");
                sb.append(zzmb.zza((zzia) obj));
                sb.append(Typography.quote);
            } else if (obj instanceof zzjk) {
                sb.append(" {");
                zza((zzjk) obj, sb, i + 2);
                sb.append("\n");
                zza(i, sb);
                sb.append(VectorFormat.DEFAULT_SUFFIX);
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry = (Map.Entry) obj;
                int i3 = i + 2;
                zza(sb, i3, "key", entry.getKey());
                zza(sb, i3, "value", entry.getValue());
                sb.append("\n");
                zza(i, sb);
                sb.append(VectorFormat.DEFAULT_SUFFIX);
            } else {
                sb.append(": ");
                sb.append(obj);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0163, code lost:
        if (r5.containsKey("get" + r9.substring(0, r9.length() - 5)) == false) goto L_0x0165;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0193, code lost:
        if (((java.lang.Boolean) r7).booleanValue() == false) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01a6, code lost:
        if (((java.lang.Integer) r7).intValue() == 0) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01b8, code lost:
        if (java.lang.Float.floatToRawIntBits(((java.lang.Float) r7).floatValue()) == 0) goto L_0x0195;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ce, code lost:
        if (java.lang.Double.doubleToRawLongBits(((java.lang.Double) r7).doubleValue()) == 0) goto L_0x0195;
     */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0206  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void zza(com.google.android.gms.internal.measurement.zzkt r20, java.lang.StringBuilder r21, int r22) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.TreeMap r5 = new java.util.TreeMap
            r5.<init>()
            java.lang.Class r6 = r20.getClass()
            java.lang.reflect.Method[] r6 = r6.getDeclaredMethods()
            int r7 = r6.length
            r8 = 0
            r9 = r8
        L_0x0020:
            java.lang.String r10 = "get"
            java.lang.String r11 = "has"
            java.lang.String r12 = "set"
            r13 = 3
            if (r9 >= r7) goto L_0x0088
            r14 = r6[r9]
            int r15 = r14.getModifiers()
            boolean r15 = java.lang.reflect.Modifier.isStatic(r15)
            if (r15 != 0) goto L_0x0085
            java.lang.String r15 = r14.getName()
            int r15 = r15.length()
            if (r15 < r13) goto L_0x0085
            java.lang.String r13 = r14.getName()
            boolean r12 = r13.startsWith(r12)
            if (r12 == 0) goto L_0x0051
            java.lang.String r10 = r14.getName()
            r3.add(r10)
            goto L_0x0085
        L_0x0051:
            int r12 = r14.getModifiers()
            boolean r12 = java.lang.reflect.Modifier.isPublic(r12)
            if (r12 == 0) goto L_0x0085
            java.lang.Class[] r12 = r14.getParameterTypes()
            int r12 = r12.length
            if (r12 != 0) goto L_0x0085
            java.lang.String r12 = r14.getName()
            boolean r11 = r12.startsWith(r11)
            if (r11 == 0) goto L_0x0074
            java.lang.String r10 = r14.getName()
            r4.put(r10, r14)
            goto L_0x0085
        L_0x0074:
            java.lang.String r11 = r14.getName()
            boolean r10 = r11.startsWith(r10)
            if (r10 == 0) goto L_0x0085
            java.lang.String r10 = r14.getName()
            r5.put(r10, r14)
        L_0x0085:
            int r9 = r9 + 1
            goto L_0x0020
        L_0x0088:
            java.util.Set r6 = r5.entrySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0090:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x021c
            java.lang.Object r7 = r6.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r9 = r7.getKey()
            java.lang.String r9 = (java.lang.String) r9
            java.lang.String r9 = r9.substring(r13)
            java.lang.String r14 = "List"
            boolean r15 = r9.endsWith(r14)
            if (r15 == 0) goto L_0x00e5
            java.lang.String r15 = "OrBuilderList"
            boolean r15 = r9.endsWith(r15)
            if (r15 != 0) goto L_0x00e5
            boolean r14 = r9.equals(r14)
            if (r14 != 0) goto L_0x00e5
            java.lang.Object r14 = r7.getValue()
            java.lang.reflect.Method r14 = (java.lang.reflect.Method) r14
            if (r14 == 0) goto L_0x00e5
            java.lang.Class r15 = r14.getReturnType()
            java.lang.Class<java.util.List> r13 = java.util.List.class
            boolean r13 = r15.equals(r13)
            if (r13 == 0) goto L_0x00e5
            int r7 = r9.length()
            int r7 = r7 + -4
            java.lang.String r7 = r9.substring(r8, r7)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzjk.zza((java.lang.reflect.Method) r14, (java.lang.Object) r0, (java.lang.Object[]) r9)
            zza(r1, r2, r7, r9)
            r13 = 3
            goto L_0x0090
        L_0x00e5:
            java.lang.String r13 = "Map"
            boolean r14 = r9.endsWith(r13)
            if (r14 == 0) goto L_0x012e
            boolean r13 = r9.equals(r13)
            if (r13 != 0) goto L_0x012e
            java.lang.Object r13 = r7.getValue()
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r13 == 0) goto L_0x012e
            java.lang.Class r14 = r13.getReturnType()
            java.lang.Class<java.util.Map> r15 = java.util.Map.class
            boolean r14 = r14.equals(r15)
            if (r14 == 0) goto L_0x012e
            java.lang.Class<java.lang.Deprecated> r14 = java.lang.Deprecated.class
            boolean r14 = r13.isAnnotationPresent(r14)
            if (r14 != 0) goto L_0x012e
            int r14 = r13.getModifiers()
            boolean r14 = java.lang.reflect.Modifier.isPublic(r14)
            if (r14 == 0) goto L_0x012e
            int r7 = r9.length()
            r14 = 3
            int r7 = r7 - r14
            java.lang.String r7 = r9.substring(r8, r7)
            java.lang.Object[] r9 = new java.lang.Object[r8]
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzjk.zza((java.lang.reflect.Method) r13, (java.lang.Object) r0, (java.lang.Object[]) r9)
            zza(r1, r2, r7, r9)
            goto L_0x0219
        L_0x012e:
            r14 = 3
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r12)
            r13.append(r9)
            java.lang.String r13 = r13.toString()
            boolean r13 = r3.contains(r13)
            if (r13 == 0) goto L_0x0219
            java.lang.String r13 = "Bytes"
            boolean r13 = r9.endsWith(r13)
            if (r13 == 0) goto L_0x0165
            int r13 = r9.length()
            int r13 = r13 + -5
            java.lang.String r13 = r9.substring(r8, r13)
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r10)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            boolean r13 = r5.containsKey(r13)
            if (r13 != 0) goto L_0x0219
        L_0x0165:
            java.lang.Object r7 = r7.getValue()
            java.lang.reflect.Method r7 = (java.lang.reflect.Method) r7
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>(r11)
            r13.append(r9)
            java.lang.String r13 = r13.toString()
            java.lang.Object r13 = r4.get(r13)
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13
            if (r7 == 0) goto L_0x0219
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzjk.zza((java.lang.reflect.Method) r7, (java.lang.Object) r0, (java.lang.Object[]) r15)
            if (r13 != 0) goto L_0x0208
            boolean r13 = r7 instanceof java.lang.Boolean
            r15 = 1
            if (r13 == 0) goto L_0x019b
            r13 = r7
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 != 0) goto L_0x0198
        L_0x0195:
            r13 = r15
            goto L_0x0203
        L_0x0198:
            r13 = r8
            goto L_0x0203
        L_0x019b:
            boolean r13 = r7 instanceof java.lang.Integer
            if (r13 == 0) goto L_0x01a9
            r13 = r7
            java.lang.Integer r13 = (java.lang.Integer) r13
            int r13 = r13.intValue()
            if (r13 != 0) goto L_0x0198
            goto L_0x0195
        L_0x01a9:
            boolean r13 = r7 instanceof java.lang.Float
            if (r13 == 0) goto L_0x01bb
            r13 = r7
            java.lang.Float r13 = (java.lang.Float) r13
            float r13 = r13.floatValue()
            int r13 = java.lang.Float.floatToRawIntBits(r13)
            if (r13 != 0) goto L_0x0198
            goto L_0x0195
        L_0x01bb:
            boolean r13 = r7 instanceof java.lang.Double
            if (r13 == 0) goto L_0x01d1
            r13 = r7
            java.lang.Double r13 = (java.lang.Double) r13
            double r16 = r13.doubleValue()
            long r16 = java.lang.Double.doubleToRawLongBits(r16)
            r18 = 0
            int r13 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r13 != 0) goto L_0x0198
            goto L_0x0195
        L_0x01d1:
            boolean r13 = r7 instanceof java.lang.String
            if (r13 == 0) goto L_0x01dc
            java.lang.String r13 = ""
            boolean r13 = r7.equals(r13)
            goto L_0x0203
        L_0x01dc:
            boolean r13 = r7 instanceof com.google.android.gms.internal.measurement.zzia
            if (r13 == 0) goto L_0x01e7
            com.google.android.gms.internal.measurement.zzia r13 = com.google.android.gms.internal.measurement.zzia.zza
            boolean r13 = r7.equals(r13)
            goto L_0x0203
        L_0x01e7:
            boolean r13 = r7 instanceof com.google.android.gms.internal.measurement.zzkt
            if (r13 == 0) goto L_0x01f5
            r13 = r7
            com.google.android.gms.internal.measurement.zzkt r13 = (com.google.android.gms.internal.measurement.zzkt) r13
            com.google.android.gms.internal.measurement.zzkt r13 = r13.zzcj()
            if (r7 != r13) goto L_0x0198
            goto L_0x0195
        L_0x01f5:
            boolean r13 = r7 instanceof java.lang.Enum
            if (r13 == 0) goto L_0x0198
            r13 = r7
            java.lang.Enum r13 = (java.lang.Enum) r13
            int r13 = r13.ordinal()
            if (r13 != 0) goto L_0x0198
            goto L_0x0195
        L_0x0203:
            if (r13 != 0) goto L_0x0206
            goto L_0x0214
        L_0x0206:
            r15 = r8
            goto L_0x0214
        L_0x0208:
            java.lang.Object[] r15 = new java.lang.Object[r8]
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzjk.zza((java.lang.reflect.Method) r13, (java.lang.Object) r0, (java.lang.Object[]) r15)
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r15 = r13.booleanValue()
        L_0x0214:
            if (r15 == 0) goto L_0x0219
            zza(r1, r2, r9, r7)
        L_0x0219:
            r13 = r14
            goto L_0x0090
        L_0x021c:
            boolean r3 = r0 instanceof com.google.android.gms.internal.measurement.zzjk.zzd
            if (r3 == 0) goto L_0x0242
            r3 = r0
            com.google.android.gms.internal.measurement.zzjk$zzd r3 = (com.google.android.gms.internal.measurement.zzjk.zzd) r3
            com.google.android.gms.internal.measurement.zzjd<com.google.android.gms.internal.measurement.zzjk$zzc> r3 = r3.zzc
            java.util.Iterator r3 = r3.zzd()
            boolean r4 = r3.hasNext()
            if (r4 != 0) goto L_0x0230
            goto L_0x0242
        L_0x0230:
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r0 = r0.getKey()
            com.google.android.gms.internal.measurement.zzjk$zzc r0 = (com.google.android.gms.internal.measurement.zzjk.zzc) r0
            java.lang.NoSuchMethodError r0 = new java.lang.NoSuchMethodError
            r0.<init>()
            throw r0
        L_0x0242:
            com.google.android.gms.internal.measurement.zzjk r0 = (com.google.android.gms.internal.measurement.zzjk) r0
            com.google.android.gms.internal.measurement.zzme r3 = r0.zzb
            if (r3 == 0) goto L_0x024d
            com.google.android.gms.internal.measurement.zzme r0 = r0.zzb
            r0.zza((java.lang.StringBuilder) r1, (int) r2)
        L_0x024d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzku.zza(com.google.android.gms.internal.measurement.zzkt, java.lang.StringBuilder, int):void");
    }
}
