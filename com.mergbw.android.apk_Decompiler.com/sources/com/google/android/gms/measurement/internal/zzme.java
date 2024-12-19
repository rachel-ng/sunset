package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzdo;
import com.google.android.gms.measurement.internal.zzmi;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzme<T extends Context & zzmi> {
    private final T zza;

    public final int zza(Intent intent, int i, int i2) {
        zzfw zzj = zzhj.zza(this.zza, (zzdo) null, (Long) null).zzj();
        if (intent == null) {
            zzj.zzu().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzj.zzp().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza((Runnable) new zzmg(this, i2, zzj, intent));
        }
        return 2;
    }

    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzhn(zznc.zza((Context) this.zza));
        }
        zzc().zzu().zza("onBind received unknown action", action);
        return null;
    }

    private final zzfw zzc() {
        return zzhj.zza(this.zza, (zzdo) null, (Long) null).zzj();
    }

    public zzme(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(int i, zzfw zzfw, Intent intent) {
        if (((zzmi) this.zza).zza(i)) {
            zzfw.zzp().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzc().zzp().zza("Completed wakeful intent.");
            ((zzmi) this.zza).zza(intent);
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zza(zzfw zzfw, JobParameters jobParameters) {
        zzfw.zzp().zza("AppMeasurementJobService processed last upload request.");
        ((zzmi) this.zza).zza(jobParameters, false);
    }

    public final void zza() {
        zzhj.zza(this.zza, (zzdo) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is starting up");
    }

    public final void zzb() {
        zzhj.zza(this.zza, (zzdo) null, (Long) null).zzj().zzp().zza("Local AppMeasurementService is shutting down");
    }

    public final void zzb(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onRebind called with null intent");
            return;
        }
        zzc().zzp().zza("onRebind called. action", intent.getAction());
    }

    private final void zza(Runnable runnable) {
        zznc zza2 = zznc.zza((Context) this.zza);
        zza2.zzl().zzb((Runnable) new zzmf(this, zza2, runnable));
    }

    public final boolean zza(JobParameters jobParameters) {
        zzfw zzj = zzhj.zza(this.zza, (zzdo) null, (Long) null).zzj();
        String string = jobParameters.getExtras().getString("action");
        zzj.zzp().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza((Runnable) new zzmd(this, zzj, jobParameters));
        return true;
    }

    public final boolean zzc(Intent intent) {
        if (intent == null) {
            zzc().zzg().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzp().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }
}
