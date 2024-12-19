package com.google.android.gms.internal.ads;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzagq {
    private static final String[] zza = {"Camera:MotionPhoto", "GCamera:MotionPhoto", "Camera:MicroVideo", "GCamera:MicroVideo"};
    private static final String[] zzb = {"Camera:MotionPhotoPresentationTimestampUs", "GCamera:MotionPhotoPresentationTimestampUs", "Camera:MicroVideoPresentationTimestampUs", "GCamera:MicroVideoPresentationTimestampUs"};
    private static final String[] zzc = {"Camera:MicroVideoOffset", "GCamera:MicroVideoOffset"};

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        r7 = -9223372036854775807L;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzagm zza(java.lang.String r22) throws java.io.IOException {
        /*
            java.lang.String r0 = "x:xmpmeta"
            r1 = 0
            org.xmlpull.v1.XmlPullParserFactory r2 = org.xmlpull.v1.XmlPullParserFactory.newInstance()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            org.xmlpull.v1.XmlPullParser r2 = r2.newPullParser()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.io.StringReader r3 = new java.io.StringReader     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r4 = r22
            r3.<init>(r4)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r2.setInput(r3)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r2.next()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            boolean r3 = com.google.android.gms.internal.ads.zzge.zzc(r2, r0)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r3 == 0) goto L_0x00d9
            com.google.android.gms.internal.ads.zzgbc r3 = com.google.android.gms.internal.ads.zzgbc.zzm()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r4 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r6 = r4
        L_0x0028:
            r2.next()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r8 = "rdf:Description"
            boolean r8 = com.google.android.gms.internal.ads.zzge.zzc(r2, r8)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r8 != 0) goto L_0x0057
            java.lang.String r8 = "Container:Directory"
            boolean r8 = com.google.android.gms.internal.ads.zzge.zzc(r2, r8)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r8 == 0) goto L_0x0045
            java.lang.String r3 = "Container"
            java.lang.String r8 = "Item"
            com.google.android.gms.internal.ads.zzgbc r3 = zzb(r2, r3, r8)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            goto L_0x00c1
        L_0x0045:
            java.lang.String r8 = "GContainer:Directory"
            boolean r8 = com.google.android.gms.internal.ads.zzge.zzc(r2, r8)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r8 == 0) goto L_0x00c1
            java.lang.String r3 = "GContainer"
            java.lang.String r8 = "GContainerItem"
            com.google.android.gms.internal.ads.zzgbc r3 = zzb(r2, r3, r8)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            goto L_0x00c1
        L_0x0057:
            java.lang.String[] r3 = zza     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r6 = 0
            r7 = r6
        L_0x005b:
            r8 = 4
            if (r7 >= r8) goto L_0x00d8
            r9 = r3[r7]     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r9 = com.google.android.gms.internal.ads.zzge.zza(r2, r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r9 == 0) goto L_0x00d5
            int r3 = java.lang.Integer.parseInt(r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r7 = 1
            if (r3 != r7) goto L_0x00d8
            java.lang.String[] r3 = zzb     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r7 = r6
        L_0x0070:
            if (r7 >= r8) goto L_0x0088
            r9 = r3[r7]     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r9 = com.google.android.gms.internal.ads.zzge.zza(r2, r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r9 == 0) goto L_0x0085
            long r7 = java.lang.Long.parseLong(r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r9 = -1
            int r3 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r3 != 0) goto L_0x0089
            goto L_0x0088
        L_0x0085:
            int r7 = r7 + 1
            goto L_0x0070
        L_0x0088:
            r7 = r4
        L_0x0089:
            java.lang.String[] r3 = zzc     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
        L_0x008b:
            r9 = 2
            if (r6 >= r9) goto L_0x00bc
            r9 = r3[r6]     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r9 = com.google.android.gms.internal.ads.zzge.zza(r2, r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r9 == 0) goto L_0x00b9
            long r13 = java.lang.Long.parseLong(r9)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            com.google.android.gms.internal.ads.zzagl r3 = new com.google.android.gms.internal.ads.zzagl     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r16 = "image/jpeg"
            java.lang.String r17 = "Primary"
            r18 = 0
            r20 = 0
            r15 = r3
            r15.<init>(r16, r17, r18, r20)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            com.google.android.gms.internal.ads.zzagl r6 = new com.google.android.gms.internal.ads.zzagl     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            java.lang.String r11 = "video/mp4"
            java.lang.String r12 = "MotionPhoto"
            r15 = 0
            r10 = r6
            r10.<init>(r11, r12, r13, r15)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            com.google.android.gms.internal.ads.zzgbc r3 = com.google.android.gms.internal.ads.zzgbc.zzo(r3, r6)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            goto L_0x00c0
        L_0x00b9:
            int r6 = r6 + 1
            goto L_0x008b
        L_0x00bc:
            com.google.android.gms.internal.ads.zzgbc r3 = com.google.android.gms.internal.ads.zzgbc.zzm()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
        L_0x00c0:
            r6 = r7
        L_0x00c1:
            boolean r8 = com.google.android.gms.internal.ads.zzge.zzb(r2, r0)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r8 == 0) goto L_0x0028
            boolean r0 = r3.isEmpty()     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            if (r0 == 0) goto L_0x00ce
            goto L_0x00d8
        L_0x00ce:
            com.google.android.gms.internal.ads.zzagm r0 = new com.google.android.gms.internal.ads.zzagm     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r0.<init>(r6, r3)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            r1 = r0
            goto L_0x00d8
        L_0x00d5:
            int r7 = r7 + 1
            goto L_0x005b
        L_0x00d8:
            return r1
        L_0x00d9:
            java.lang.String r0 = "Couldn't find xmp metadata"
            com.google.android.gms.internal.ads.zzch r0 = com.google.android.gms.internal.ads.zzch.zza(r0, r1)     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
            throw r0     // Catch:{ zzch | NumberFormatException | XmlPullParserException -> 0x00e0 }
        L_0x00e0:
            java.lang.String r0 = "MotionPhotoXmpParser"
            java.lang.String r2 = "Ignoring unexpected XMP metadata"
            com.google.android.gms.internal.ads.zzfk.zzf(r0, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzagq.zza(java.lang.String):com.google.android.gms.internal.ads.zzagm");
    }

    private static zzgbc zzb(XmlPullParser xmlPullParser, String str, String str2) throws XmlPullParserException, IOException {
        zzgaz zzgaz = new zzgaz();
        do {
            String concat = str.concat(":Item");
            xmlPullParser.next();
            if (zzge.zzc(xmlPullParser, concat)) {
                String concat2 = str2.concat(":Mime");
                String concat3 = str2.concat(":Semantic");
                String concat4 = str2.concat(":Length");
                String concat5 = str2.concat(":Padding");
                String zza2 = zzge.zza(xmlPullParser, concat2);
                String zza3 = zzge.zza(xmlPullParser, concat3);
                String zza4 = zzge.zza(xmlPullParser, concat4);
                String zza5 = zzge.zza(xmlPullParser, concat5);
                if (zza2 == null || zza3 == null) {
                    return zzgbc.zzm();
                }
                zzgaz.zzf(new zzagl(zza2, zza3, zza4 != null ? Long.parseLong(zza4) : 0, zza5 != null ? Long.parseLong(zza5) : 0));
            }
        } while (!zzge.zzb(xmlPullParser, str.concat(":Directory")));
        return zzgaz.zzi();
    }
}
