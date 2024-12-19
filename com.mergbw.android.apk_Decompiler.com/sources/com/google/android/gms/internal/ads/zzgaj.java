package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzgaj extends zzfzw {
    final /* synthetic */ zzgal zza;
    private final Object zzb;
    private int zzc;

    zzgaj(zzgal zzgal, int i) {
        this.zza = zzgal;
        this.zzb = zzgal.zzg(zzgal, i);
        this.zzc = i;
    }

    private final void zza() {
        int i = this.zzc;
        if (i == -1 || i >= this.zza.size() || !zzfya.zza(this.zzb, zzgal.zzg(this.zza, this.zzc))) {
            this.zzc = this.zza.zzw(this.zzb);
        }
    }

    public final Object getKey() {
        return this.zzb;
    }

    public final Object getValue() {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.get(this.zzb);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            return null;
        }
        return zzgal.zzj(this.zza, i);
    }

    public final Object setValue(Object obj) {
        Map zzl = this.zza.zzl();
        if (zzl != null) {
            return zzl.put(this.zzb, obj);
        }
        zza();
        int i = this.zzc;
        if (i == -1) {
            this.zza.put(this.zzb, obj);
            return null;
        }
        zzgal zzgal = this.zza;
        Object zzj = zzgal.zzj(zzgal, i);
        zzgal.zzn(zzgal, this.zzc, obj);
        return zzj;
    }
}
