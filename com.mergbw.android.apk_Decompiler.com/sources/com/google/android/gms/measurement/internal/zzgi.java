package com.google.android.gms.measurement.internal;

import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzgi {
    private final zzim zza;

    static zzgi zza(String str) {
        zzim zzim;
        if (TextUtils.isEmpty(str) || str.length() > 1) {
            zzim = zzim.UNINITIALIZED;
        } else {
            zzim = zzin.zza(str.charAt(0));
        }
        return new zzgi(zzim);
    }

    /* access modifiers changed from: package-private */
    public final zzim zza() {
        return this.zza;
    }

    /* access modifiers changed from: package-private */
    public final String zzb() {
        return String.valueOf(zzin.zza(this.zza));
    }

    zzgi(zzim zzim) {
        this.zza = zzim;
    }
}
