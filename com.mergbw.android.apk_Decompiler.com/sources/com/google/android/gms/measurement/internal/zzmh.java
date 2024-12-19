package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzcz;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzmh extends zze {
    protected final zzmp zza = new zzmp(this);
    protected final zzmn zzb = new zzmn(this);
    /* access modifiers changed from: private */
    public Handler zzc;
    private boolean zzd = true;
    private final zzmm zze = new zzmm(this);

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzz() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzb zzc() {
        return super.zzc();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    public final /* bridge */ /* synthetic */ zzfq zzg() {
        return super.zzg();
    }

    public final /* bridge */ /* synthetic */ zzfp zzh() {
        return super.zzh();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zziv zzm() {
        return super.zzm();
    }

    public final /* bridge */ /* synthetic */ zzks zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzkx zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zzmh zzp() {
        return super.zzp();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    static /* synthetic */ void zza(zzmh zzmh, long j) {
        zzmh.zzt();
        zzmh.zzab();
        zzmh.zzj().zzp().zza("Activity paused, time", Long.valueOf(j));
        zzmh.zze.zza(j);
        if (zzmh.zze().zzv()) {
            zzmh.zzb.zzb(j);
        }
    }

    static /* synthetic */ void zzb(zzmh zzmh, long j) {
        zzmh.zzt();
        zzmh.zzab();
        zzmh.zzj().zzp().zza("Activity resumed, time", Long.valueOf(j));
        if (zzmh.zze().zza(zzbf.zzcg)) {
            if (zzmh.zze().zzv() || zzmh.zzd) {
                zzmh.zzb.zzc(j);
            }
        } else if (zzmh.zze().zzv() || zzmh.zzk().zzn.zza()) {
            zzmh.zzb.zzc(j);
        }
        zzmh.zze.zza();
        zzmp zzmp = zzmh.zza;
        zzmp.zza.zzt();
        if (zzmp.zza.zzu.zzac()) {
            zzmp.zza(zzmp.zza.zzb().currentTimeMillis(), false);
        }
    }

    zzmh(zzhj zzhj) {
        super(zzhj);
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    /* access modifiers changed from: private */
    public final void zzab() {
        zzt();
        if (this.zzc == null) {
            this.zzc = new zzcz(Looper.getMainLooper());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(boolean z) {
        zzt();
        this.zzd = z;
    }

    /* access modifiers changed from: package-private */
    public final boolean zzaa() {
        zzt();
        return this.zzd;
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        return this.zzb.zza(z, z2, j);
    }
}
