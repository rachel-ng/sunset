package com.google.android.gms.internal.measurement;

import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzw extends zzal {
    private zzaa zzk;

    public final zzaq zza(zzh zzh, List<zzaq> list) {
        zzg.zza(this.zza, 3, list);
        String zzf = zzh.zza(list.get(0)).zzf();
        zzaq zza = zzh.zza(list.get(1));
        if (zza instanceof zzar) {
            zzaq zza2 = zzh.zza(list.get(2));
            if (zza2 instanceof zzap) {
                zzap zzap = (zzap) zza2;
                if (zzap.zzc(SessionDescription.ATTR_TYPE)) {
                    this.zzk.zza(zzf, zzap.zzc("priority") ? zzg.zzb(zzap.zza("priority").zze().doubleValue()) : 1000, (zzar) zza, zzap.zza(SessionDescription.ATTR_TYPE).zzf());
                    return zzaq.zzc;
                }
                throw new IllegalArgumentException("Undefined rule type");
            }
            throw new IllegalArgumentException("Invalid callback params");
        }
        throw new IllegalArgumentException("Invalid callback type");
    }

    public zzw(zzaa zzaa) {
        super("internal.registerCallback");
        this.zzk = zzaa;
    }
}
