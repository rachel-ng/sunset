package com.google.android.gms.measurement.internal;

import com.google.android.gms.measurement.internal.zzin;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public enum zzio {
    STORAGE(zzin.zza.AD_STORAGE, zzin.zza.ANALYTICS_STORAGE),
    DMA(zzin.zza.AD_USER_DATA);
    
    /* access modifiers changed from: private */
    public final zzin.zza[] zzd;

    private zzio(zzin.zza... zzaArr) {
        this.zzd = zzaArr;
    }

    public final zzin.zza[] zza() {
        return this.zzd;
    }
}
