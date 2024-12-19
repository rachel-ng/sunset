package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdo;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzgr {
    private final zza zza;

    /* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
    public interface zza {
        void doStartService(Context context, Intent intent);
    }

    public zzgr(zza zza2) {
        Preconditions.checkNotNull(zza2);
        this.zza = zza2;
    }

    public final void zza(Context context, Intent intent) {
        zzfw zzj = zzhj.zza(context, (zzdo) null, (Long) null).zzj();
        if (intent == null) {
            zzj.zzu().zza("Receiver called with null intent");
            return;
        }
        String action = intent.getAction();
        zzj.zzp().zza("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzj.zzp().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
        } else if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            zzj.zzu().zza("Install Referrer Broadcasts are deprecated");
        }
    }
}
