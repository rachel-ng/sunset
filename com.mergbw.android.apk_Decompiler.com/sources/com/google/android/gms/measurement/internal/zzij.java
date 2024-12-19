package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
class zzij implements zzil {
    protected final zzhj zzu;

    @Pure
    public Context zza() {
        return this.zzu.zza();
    }

    @Pure
    public Clock zzb() {
        return this.zzu.zzb();
    }

    @Pure
    public zzab zzd() {
        return this.zzu.zzd();
    }

    @Pure
    public zzag zze() {
        return this.zzu.zzf();
    }

    @Pure
    public zzax zzf() {
        return this.zzu.zzg();
    }

    @Pure
    public zzfr zzi() {
        return this.zzu.zzk();
    }

    @Pure
    public zzfw zzj() {
        return this.zzu.zzj();
    }

    @Pure
    public zzgh zzk() {
        return this.zzu.zzn();
    }

    @Pure
    public zzhc zzl() {
        return this.zzu.zzl();
    }

    @Pure
    public zznp zzq() {
        return this.zzu.zzt();
    }

    zzij(zzhj zzhj) {
        Preconditions.checkNotNull(zzhj);
        this.zzu = zzhj;
    }

    public void zzr() {
        this.zzu.zzl().zzr();
    }

    public void zzs() {
        this.zzu.zzy();
    }

    public void zzt() {
        this.zzu.zzl().zzt();
    }
}
