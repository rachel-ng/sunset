package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmn {
    protected long zza;
    final /* synthetic */ zzmh zzb;
    private long zzc;
    private final zzat zzd;

    /* access modifiers changed from: package-private */
    public final long zza(long j) {
        long j2 = j - this.zza;
        this.zza = j;
        return j2;
    }

    static /* synthetic */ void zza(zzmn zzmn) {
        zzmn.zzb.zzt();
        zzmn.zza(false, false, zzmn.zzb.zzb().elapsedRealtime());
        zzmn.zzb.zzc().zza(zzmn.zzb.zzb().elapsedRealtime());
    }

    public zzmn(zzmh zzmh) {
        this.zzb = zzmh;
        this.zzd = new zzmq(this, zzmh.zzu);
        long elapsedRealtime = zzmh.zzb().elapsedRealtime();
        this.zzc = elapsedRealtime;
        this.zza = elapsedRealtime;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zzd.zza();
        if (this.zzb.zze().zza(zzbf.zzcy)) {
            this.zzc = this.zzb.zzb().elapsedRealtime();
        } else {
            this.zzc = 0;
        }
        this.zza = this.zzc;
    }

    /* access modifiers changed from: package-private */
    public final void zzb(long j) {
        this.zzd.zza();
    }

    /* access modifiers changed from: package-private */
    public final void zzc(long j) {
        this.zzb.zzt();
        this.zzd.zza();
        this.zzc = j;
        this.zza = j;
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzb.zzt();
        this.zzb.zzu();
        if (this.zzb.zzu.zzac()) {
            this.zzb.zzk().zzk.zza(this.zzb.zzb().currentTimeMillis());
        }
        long j2 = j - this.zzc;
        if (z || j2 >= 1000) {
            if (!z2) {
                j2 = zza(j);
            }
            this.zzb.zzj().zzp().zza("Recording user engagement, ms", Long.valueOf(j2));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j2);
            zznp.zza(this.zzb.zzn().zza(!this.zzb.zze().zzv()), bundle, true);
            if (!z2) {
                this.zzb.zzm().zzc("auto", "_e", bundle);
            }
            this.zzc = j;
            this.zzd.zza();
            this.zzd.zza(zzbf.zzba.zza(null).longValue());
            return true;
        }
        this.zzb.zzj().zzp().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
        return false;
    }
}
