package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzhd extends zzgz<Long> {
    /* access modifiers changed from: private */
    @Nullable
    /* renamed from: zzb */
    public final Long zza(Object obj) {
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            try {
                return Long.valueOf(Long.parseLong((String) obj));
            } catch (NumberFormatException unused) {
            }
        }
        String zzb = super.zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", "Invalid long value for " + zzb + ": " + valueOf);
        return null;
    }

    zzhd(zzhh zzhh, String str, Long l, boolean z) {
        super(zzhh, str, l);
    }
}
