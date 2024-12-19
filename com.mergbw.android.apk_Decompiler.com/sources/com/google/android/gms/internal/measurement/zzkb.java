package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public class zzkb {
    private volatile zzkt zza;
    private volatile zzia zzb;

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzb != null) {
            return this.zzb.zzb();
        }
        if (this.zza != null) {
            return this.zza.zzca();
        }
        return 0;
    }

    public final zzia zzb() {
        if (this.zzb != null) {
            return this.zzb;
        }
        synchronized (this) {
            if (this.zzb != null) {
                zzia zzia = this.zzb;
                return zzia;
            }
            if (this.zza == null) {
                this.zzb = zzia.zza;
            } else {
                this.zzb = this.zza.zzby();
            }
            zzia zzia2 = this.zzb;
            return zzia2;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.measurement.zzkt zzb(com.google.android.gms.internal.measurement.zzkt r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.measurement.zzkt r0 = r1.zza
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.measurement.zzkt r0 = r1.zza     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zza = r2     // Catch:{ zzjs -> 0x0012 }
            com.google.android.gms.internal.measurement.zzia r0 = com.google.android.gms.internal.measurement.zzia.zza     // Catch:{ zzjs -> 0x0012 }
            r1.zzb = r0     // Catch:{ zzjs -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zza = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.measurement.zzia r2 = com.google.android.gms.internal.measurement.zzia.zza     // Catch:{ all -> 0x001a }
            r1.zzb = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.measurement.zzkt r2 = r1.zza
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzkb.zzb(com.google.android.gms.internal.measurement.zzkt):com.google.android.gms.internal.measurement.zzkt");
    }

    public final zzkt zza(zzkt zzkt) {
        zzkt zzkt2 = this.zza;
        this.zzb = null;
        this.zza = zzkt;
        return zzkt2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzkb)) {
            return false;
        }
        zzkb zzkb = (zzkb) obj;
        zzkt zzkt = this.zza;
        zzkt zzkt2 = zzkb.zza;
        if (zzkt == null && zzkt2 == null) {
            return zzb().equals(zzkb.zzb());
        }
        if (zzkt != null && zzkt2 != null) {
            return zzkt.equals(zzkt2);
        }
        if (zzkt != null) {
            return zzkt.equals(zzkb.zzb(zzkt.zzcj()));
        }
        return zzb(zzkt2.zzcj()).equals(zzkt2);
    }
}
