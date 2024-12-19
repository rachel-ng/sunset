package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfpm implements zzfpf {
    private static zzfpm zza;
    private float zzb = 0.0f;
    private final zzfpb zzc;
    private final zzfoz zzd;
    private zzfpa zze;
    private zzfpe zzf;

    public zzfpm(zzfpb zzfpb, zzfoz zzfoz) {
        this.zzc = zzfpb;
        this.zzd = zzfoz;
    }

    public static zzfpm zzb() {
        if (zza == null) {
            zza = new zzfpm(new zzfpb(), new zzfoz());
        }
        return zza;
    }

    public final float zza() {
        return this.zzb;
    }

    public final void zzc(boolean z) {
        if (z) {
            zzfqo.zzd().zzi();
        } else {
            zzfqo.zzd().zzh();
        }
    }

    public final void zzd(Context context) {
        this.zze = new zzfpa(new Handler(), context, new zzfoy(), this);
    }

    public final void zze(float f) {
        this.zzb = f;
        if (this.zzf == null) {
            this.zzf = zzfpe.zza();
        }
        for (zzfon zzg : this.zzf.zzb()) {
            zzg.zzg().zzl(f);
        }
    }

    public final void zzf() {
        zzfpd.zza().zze(this);
        zzfpd.zza().zzf();
        zzfqo.zzd().zzi();
        this.zze.zza();
    }

    public final void zzg() {
        zzfqo.zzd().zzj();
        zzfpd.zza().zzg();
        this.zze.zzb();
    }
}
