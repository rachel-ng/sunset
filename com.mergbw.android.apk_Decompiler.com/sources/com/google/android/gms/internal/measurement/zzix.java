package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzjk;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
public class zzix {
    static final zzix zza = new zzix(true);
    private static volatile boolean zzb = false;
    private static volatile zzix zzc;
    private final Map<zza, zzjk.zzf<?, ?>> zzd;

    /* compiled from: com.google.android.gms:play-services-measurement-base@@22.0.2 */
    private static final class zza {
        private final Object zza;
        private final int zzb;

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zza2 = (zza) obj;
            if (this.zza == zza2.zza && this.zzb == zza2.zzb) {
                return true;
            }
            return false;
        }
    }

    public static zzix zza() {
        zzix zzix = zzc;
        if (zzix != null) {
            return zzix;
        }
        synchronized (zzix.class) {
            zzix zzix2 = zzc;
            if (zzix2 != null) {
                return zzix2;
            }
            zzix zza2 = zzji.zza(zzix.class);
            zzc = zza2;
            return zza2;
        }
    }

    public final <ContainingType extends zzkt> zzjk.zzf<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return this.zzd.get(new zza(containingtype, i));
    }

    zzix() {
        this.zzd = new HashMap();
    }

    private zzix(boolean z) {
        this.zzd = Collections.emptyMap();
    }
}
