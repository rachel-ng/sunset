package com.google.android.gms.internal.ads;

import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzdjw implements zzcus {
    private final Map zza;
    private final Map zzb;
    private final Map zzc;
    private final zzhlg zzd;
    private final zzdme zze;

    zzdjw(Map map, Map map2, Map map3, zzhlg zzhlg, zzdme zzdme) {
        this.zza = map;
        this.zzb = map2;
        this.zzc = map3;
        this.zzd = zzhlg;
        this.zze = zzdme;
    }

    public final zzehl zza(int i, String str) {
        zzehl zzehl;
        zzehl zzehl2 = (zzehl) this.zza.get(str);
        if (zzehl2 != null) {
            return zzehl2;
        }
        if (i != 1) {
            if (i != 4) {
                return null;
            }
            zzejz zzejz = (zzejz) this.zzc.get(str);
            if (zzejz != null) {
                return new zzehm(zzejz, new zzcuu());
            }
            zzehl = (zzehl) this.zzb.get(str);
            if (zzehl == null) {
                return null;
            }
        } else if (this.zze.zze() == null || (zzehl = ((zzcus) this.zzd.zzb()).zza(i, str)) == null) {
            return null;
        }
        return new zzehm(zzehl, new zzcuv());
    }
}
