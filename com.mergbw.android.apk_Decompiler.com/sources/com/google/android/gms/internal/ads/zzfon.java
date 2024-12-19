package com.google.android.gms.internal.ads;

import android.view.View;
import java.util.Collection;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfon extends zzfoj {
    private final zzfol zza;
    private final zzfok zzb;
    private final zzfpi zzc = new zzfpi();
    private zzfqv zzd;
    private zzfps zze;
    private boolean zzf = false;
    private boolean zzg = false;
    private final String zzh;

    zzfon(zzfok zzfok, zzfol zzfol, String str) {
        this.zzb = zzfok;
        this.zza = zzfol;
        this.zzh = str;
        zzk((View) null);
        if (zzfol.zzd() == zzfom.HTML || zzfol.zzd() == zzfom.JAVASCRIPT) {
            this.zze = new zzfpt(str, zzfol.zza());
        } else {
            this.zze = new zzfpw(str, zzfol.zzi(), (String) null);
        }
        this.zze.zzn();
        zzfpe.zza().zzd(this);
        this.zze.zzf(zzfok);
    }

    private final void zzk(View view) {
        this.zzd = new zzfqv(view);
    }

    public final void zzb(View view, zzfoq zzfoq, String str) {
        if (!this.zzg) {
            this.zzc.zzb(view, zzfoq, "Ad overlay");
        }
    }

    public final void zzc() {
        if (!this.zzg) {
            this.zzd.clear();
            if (!this.zzg) {
                this.zzc.zzc();
            }
            this.zzg = true;
            this.zze.zze();
            zzfpe.zza().zze(this);
            this.zze.zzc();
            this.zze = null;
        }
    }

    public final void zzd(View view) {
        if (!this.zzg && zzf() != view) {
            zzk(view);
            this.zze.zzb();
            Collection<zzfon> zzc2 = zzfpe.zza().zzc();
            if (zzc2 != null && !zzc2.isEmpty()) {
                for (zzfon zzfon : zzc2) {
                    if (zzfon != this && zzfon.zzf() == view) {
                        zzfon.zzd.clear();
                    }
                }
            }
        }
    }

    public final void zze() {
        if (!this.zzf) {
            this.zzf = true;
            zzfpe.zza().zzf(this);
            this.zze.zzl(zzfpm.zzb().zza());
            this.zze.zzg(zzfpc.zza().zzb());
            this.zze.zzi(this, this.zza);
        }
    }

    public final View zzf() {
        return (View) this.zzd.get();
    }

    public final zzfps zzg() {
        return this.zze;
    }

    public final String zzh() {
        return this.zzh;
    }

    public final List zzi() {
        return this.zzc.zza();
    }

    public final boolean zzj() {
        return this.zzf && !this.zzg;
    }
}
