package com.google.android.gms.internal.ads;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public class zzhck {
    private static final zzhay zzb = zzhay.zza;
    protected volatile zzhde zza;
    private volatile zzhac zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhck)) {
            return false;
        }
        zzhck zzhck = (zzhck) obj;
        zzhde zzhde = this.zza;
        zzhde zzhde2 = zzhck.zza;
        if (zzhde == null && zzhde2 == null) {
            return zzb().equals(zzhck.zzb());
        }
        if (zzhde != null && zzhde2 != null) {
            return zzhde.equals(zzhde2);
        }
        if (zzhde != null) {
            zzhck.zzd(zzhde.zzbt());
            return zzhde.equals(zzhck.zza);
        }
        zzd(zzhde2.zzbt());
        return this.zza.equals(zzhde2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzgzy) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzaY();
        }
        return 0;
    }

    public final zzhac zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                zzhac zzhac = this.zzc;
                return zzhac;
            }
            if (this.zza == null) {
                this.zzc = zzhac.zzb;
            } else {
                this.zzc = this.zza.zzaN();
            }
            zzhac zzhac2 = this.zzc;
            return zzhac2;
        }
    }

    public final zzhde zzc(zzhde zzhde) {
        zzhde zzhde2 = this.zza;
        this.zzc = null;
        this.zza = zzhde;
        return zzhde2;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0013 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzd(com.google.android.gms.internal.ads.zzhde r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.ads.zzhde r0 = r1.zza
            if (r0 == 0) goto L_0x0005
            return
        L_0x0005:
            monitor-enter(r1)
            com.google.android.gms.internal.ads.zzhde r0 = r1.zza     // Catch:{ all -> 0x001b }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x000c:
            r1.zza = r2     // Catch:{ zzhcd -> 0x0013 }
            com.google.android.gms.internal.ads.zzhac r0 = com.google.android.gms.internal.ads.zzhac.zzb     // Catch:{ zzhcd -> 0x0013 }
            r1.zzc = r0     // Catch:{ zzhcd -> 0x0013 }
            goto L_0x0019
        L_0x0013:
            r1.zza = r2     // Catch:{ all -> 0x001b }
            com.google.android.gms.internal.ads.zzhac r2 = com.google.android.gms.internal.ads.zzhac.zzb     // Catch:{ all -> 0x001b }
            r1.zzc = r2     // Catch:{ all -> 0x001b }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            return
        L_0x001b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhck.zzd(com.google.android.gms.internal.ads.zzhde):void");
    }
}
