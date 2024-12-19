package com.google.android.gms.internal.ads;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfvz extends zzfwr {
    private final int zza;
    private final String zzb;

    /* synthetic */ zzfvz(int i, String str, zzfvy zzfvy) {
        this.zza = i;
        this.zzb = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0013, code lost:
        r1 = r4.zzb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.ads.zzfwr
            r2 = 0
            if (r1 == 0) goto L_0x002a
            com.google.android.gms.internal.ads.zzfwr r5 = (com.google.android.gms.internal.ads.zzfwr) r5
            int r1 = r4.zza
            int r3 = r5.zza()
            if (r1 != r3) goto L_0x002a
            java.lang.String r1 = r4.zzb
            if (r1 != 0) goto L_0x001e
            java.lang.String r5 = r5.zzb()
            if (r5 != 0) goto L_0x002a
            goto L_0x0029
        L_0x001e:
            java.lang.String r5 = r5.zzb()
            boolean r5 = r1.equals(r5)
            if (r5 != 0) goto L_0x0029
            goto L_0x002a
        L_0x0029:
            return r0
        L_0x002a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfvz.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        String str = this.zzb;
        return (str == null ? 0 : str.hashCode()) ^ ((this.zza ^ 1000003) * 1000003);
    }

    public final String toString() {
        return "OverlayDisplayState{statusCode=" + this.zza + ", sessionToken=" + this.zzb + VectorFormat.DEFAULT_SUFFIX;
    }

    public final int zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }
}
