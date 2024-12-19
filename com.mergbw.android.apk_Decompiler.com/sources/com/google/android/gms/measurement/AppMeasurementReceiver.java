package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzgr;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzgr.zza {
    private zzgr zza;

    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }

    public final void doStartService(Context context, Intent intent) {
        startWakefulService(context, intent);
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zza == null) {
            this.zza = new zzgr(this);
        }
        this.zza.zza(context, intent);
    }
}
