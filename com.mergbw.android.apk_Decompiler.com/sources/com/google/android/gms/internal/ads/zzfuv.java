package com.google.android.gms.internal.ads;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: com.google.android.gms:play-services-ads-lite@@23.2.0 */
public class zzfuv extends Handler {
    private final Looper zza = Looper.getMainLooper();

    public zzfuv() {
    }

    public final void dispatchMessage(Message message) {
        zza(message);
    }

    /* access modifiers changed from: protected */
    public void zza(Message message) {
        super.dispatchMessage(message);
    }

    public zzfuv(Looper looper) {
        super(looper);
    }
}
