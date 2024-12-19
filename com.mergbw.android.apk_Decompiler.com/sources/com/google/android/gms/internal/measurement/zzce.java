package com.google.android.gms.internal.measurement;

import org.apache.commons.math3.geometry.VectorFormat;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzce extends zzcj {
    private final String zzb;
    private final boolean zzc;
    private final boolean zzd;
    private final zzcc zze;
    private final zzcb zzf;
    private final zzcl zzg;

    /* synthetic */ zzce(String str, boolean z, boolean z2, zzcc zzcc, zzcb zzcb, zzcl zzcl, zzcg zzcg) {
        this(str, false, false, (zzcc) null, (zzcb) null, zzcl);
    }

    public final int hashCode() {
        int i = 1231;
        int hashCode = (((this.zzb.hashCode() ^ 1000003) * 1000003) ^ (this.zzc ? 1231 : 1237)) * 1000003;
        if (!this.zzd) {
            i = 1237;
        }
        int i2 = (hashCode ^ i) * 1000003;
        zzcc zzcc = this.zze;
        int i3 = 0;
        int hashCode2 = (i2 ^ (zzcc == null ? 0 : zzcc.hashCode())) * 1000003;
        zzcb zzcb = this.zzf;
        if (zzcb != null) {
            i3 = zzcb.hashCode();
        }
        return ((hashCode2 ^ i3) * 1000003) ^ this.zzg.hashCode();
    }

    public final zzcc zza() {
        return this.zze;
    }

    public final zzcb zzb() {
        return this.zzf;
    }

    public final zzcl zzc() {
        return this.zzg;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final String toString() {
        String str = this.zzb;
        boolean z = this.zzc;
        boolean z2 = this.zzd;
        String valueOf = String.valueOf(this.zze);
        String valueOf2 = String.valueOf(this.zzf);
        String valueOf3 = String.valueOf(this.zzg);
        return "FileComplianceOptions{fileOwner=" + str + ", hasDifferentDmaOwner=" + z + ", skipChecks=" + z2 + ", dataForwardingNotAllowedResolver=" + valueOf + ", multipleProductIdGroupsResolver=" + valueOf2 + ", filePurpose=" + valueOf3 + VectorFormat.DEFAULT_SUFFIX;
    }

    private zzce(String str, boolean z, boolean z2, zzcc zzcc, zzcb zzcb, zzcl zzcl) {
        this.zzb = str;
        this.zzc = z;
        this.zzd = z2;
        this.zze = null;
        this.zzf = null;
        this.zzg = zzcl;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        r1 = r4.zze;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003c, code lost:
        r1 = r4.zzf;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            r0 = 1
            if (r5 != r4) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r5 instanceof com.google.android.gms.internal.measurement.zzcj
            r2 = 0
            if (r1 == 0) goto L_0x005e
            com.google.android.gms.internal.measurement.zzcj r5 = (com.google.android.gms.internal.measurement.zzcj) r5
            java.lang.String r1 = r4.zzb
            java.lang.String r3 = r5.zzd()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
            boolean r1 = r4.zzc
            boolean r3 = r5.zze()
            if (r1 != r3) goto L_0x005e
            boolean r1 = r4.zzd
            boolean r3 = r5.zzf()
            if (r1 != r3) goto L_0x005e
            com.google.android.gms.internal.measurement.zzcc r1 = r4.zze
            if (r1 != 0) goto L_0x0032
            com.google.android.gms.internal.measurement.zzcc r1 = r5.zza()
            if (r1 != 0) goto L_0x005e
            goto L_0x003c
        L_0x0032:
            com.google.android.gms.internal.measurement.zzcc r3 = r5.zza()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
        L_0x003c:
            com.google.android.gms.internal.measurement.zzcb r1 = r4.zzf
            if (r1 != 0) goto L_0x0047
            com.google.android.gms.internal.measurement.zzcb r1 = r5.zzb()
            if (r1 != 0) goto L_0x005e
            goto L_0x0051
        L_0x0047:
            com.google.android.gms.internal.measurement.zzcb r3 = r5.zzb()
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x005e
        L_0x0051:
            com.google.android.gms.internal.measurement.zzcl r1 = r4.zzg
            com.google.android.gms.internal.measurement.zzcl r5 = r5.zzc()
            boolean r5 = r1.equals(r5)
            if (r5 == 0) goto L_0x005e
            return r0
        L_0x005e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzce.equals(java.lang.Object):boolean");
    }

    public final boolean zze() {
        return this.zzc;
    }

    public final boolean zzf() {
        return this.zzd;
    }
}
