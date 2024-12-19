package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzgu extends ContentObserver {
    zzgu(zzgs zzgs, Handler handler) {
        super((Handler) null);
    }

    public final void onChange(boolean z) {
        zzgz.zzc();
    }
}
