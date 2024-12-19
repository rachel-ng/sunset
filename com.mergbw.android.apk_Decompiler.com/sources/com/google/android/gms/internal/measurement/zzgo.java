package com.google.android.gms.internal.measurement;

import androidx.collection.SimpleArrayMap;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzgo implements zzgt {
    private final SimpleArrayMap<String, SimpleArrayMap<String, String>> zza;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        if (r3 != null) goto L_0x000a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0019 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(android.net.Uri r2, java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            r1 = this;
            r0 = 0
            if (r2 == 0) goto L_0x0008
            java.lang.String r3 = r2.toString()
            goto L_0x000a
        L_0x0008:
            if (r3 == 0) goto L_0x0016
        L_0x000a:
            androidx.collection.SimpleArrayMap<java.lang.String, androidx.collection.SimpleArrayMap<java.lang.String, java.lang.String>> r2 = r1.zza
            if (r2 != 0) goto L_0x000f
            goto L_0x0016
        L_0x000f:
            java.lang.Object r2 = r2.get(r3)
            androidx.collection.SimpleArrayMap r2 = (androidx.collection.SimpleArrayMap) r2
            goto L_0x0017
        L_0x0016:
            r2 = r0
        L_0x0017:
            if (r2 != 0) goto L_0x001a
            return r0
        L_0x001a:
            if (r4 == 0) goto L_0x002b
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r4)
            r3.append(r5)
            java.lang.String r5 = r3.toString()
        L_0x002b:
            java.lang.Object r2 = r2.get(r5)
            java.lang.String r2 = (java.lang.String) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzgo.zza(android.net.Uri, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    zzgo(SimpleArrayMap<String, SimpleArrayMap<String, String>> simpleArrayMap) {
        this.zza = simpleArrayMap;
    }
}
