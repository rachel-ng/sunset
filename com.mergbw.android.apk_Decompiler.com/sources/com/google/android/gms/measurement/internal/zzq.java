package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.measurement.zzpg;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzq extends BroadcastReceiver {
    private final zzhj zza;

    public zzq(zzhj zzhj) {
        this.zza = zzhj;
    }

    public final void onReceive(Context context, Intent intent) {
        if (intent == null) {
            this.zza.zzj().zzu().zza("App receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        if (action == null) {
            this.zza.zzj().zzu().zza("App receiver called with null action");
            return;
        }
        action.hashCode();
        if (!action.equals("com.google.android.gms.measurement.TRIGGERS_AVAILABLE")) {
            this.zza.zzj().zzu().zza("App receiver called with unknown action");
            return;
        }
        zzhj zzhj = this.zza;
        if (zzpg.zza() && zzhj.zzf().zzf((String) null, zzbf.zzca)) {
            zzhj.zzj().zzp().zza("App receiver notified triggers are available");
            zzhj.zzl().zzb((Runnable) new zzs(zzhj));
        }
    }
}
