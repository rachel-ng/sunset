package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzgb extends ContentObserver {
    private final /* synthetic */ zzfz zza;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzgb(zzfz zzfz, Handler handler) {
        super((Handler) null);
        this.zza = zzfz;
    }

    public final void onChange(boolean z) {
        this.zza.zza.set(true);
    }
}
