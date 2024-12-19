package com.google.android.gms.internal.measurement;

import com.google.common.base.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzhk {
    private final boolean zza;

    public zzhk(zzhn zzhn) {
        Preconditions.checkNotNull(zzhn, "BuildInfo must be non-null");
        this.zza = !zzhn.zza();
    }

    public final boolean zza(String str) {
        Preconditions.checkNotNull(str, "flagName must not be null");
        if (!this.zza) {
            return true;
        }
        return zzhm.zza.get().containsValue(str);
    }
}
