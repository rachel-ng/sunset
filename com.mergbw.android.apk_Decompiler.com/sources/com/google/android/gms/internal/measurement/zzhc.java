package com.google.android.gms.internal.measurement;

import android.util.Log;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzhc extends zzgz<Boolean> {
    /* access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object zza(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (zzfy.zzc.matcher(str).matches()) {
                return true;
            }
            if (zzfy.zzd.matcher(str).matches()) {
                return false;
            }
        }
        String zzb = super.zzb();
        String valueOf = String.valueOf(obj);
        Log.e("PhenotypeFlag", "Invalid boolean value for " + zzb + ": " + valueOf);
        return null;
    }

    zzhc(zzhh zzhh, String str, Boolean bool, boolean z) {
        super(zzhh, str, bool);
    }
}
