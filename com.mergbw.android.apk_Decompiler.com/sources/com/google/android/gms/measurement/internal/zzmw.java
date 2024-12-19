package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.internal.measurement.zzcn;
import com.google.android.gms.internal.measurement.zzco;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final class zzmw extends zzmx {
    private final AlarmManager zza = ((AlarmManager) zza().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private zzat zzb;
    private Integer zzc;

    private final int zzv() {
        if (this.zzc == null) {
            String packageName = zza().getPackageName();
            this.zzc = Integer.valueOf(("measurement" + packageName).hashCode());
        }
        return this.zzc.intValue();
    }

    private final PendingIntent zzw() {
        Context zza2 = zza();
        return zzco.zza(zza2, 0, new Intent().setClassName(zza2, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), zzco.zza);
    }

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    public final /* bridge */ /* synthetic */ zzu zzg() {
        return super.zzg();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    public final /* bridge */ /* synthetic */ zzal zzh() {
        return super.zzh();
    }

    private final zzat zzx() {
        if (this.zzb == null) {
            this.zzb = new zzmv(this, this.zzf.zzk());
        }
        return this.zzb;
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    public final /* bridge */ /* synthetic */ zzgt zzm() {
        return super.zzm();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    public final /* bridge */ /* synthetic */ zzmc zzn() {
        return super.zzn();
    }

    public final /* bridge */ /* synthetic */ zzna zzo() {
        return super.zzo();
    }

    public final /* bridge */ /* synthetic */ zznl g_() {
        return super.g_();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    protected zzmw(zznc zznc) {
        super(zznc);
    }

    public final void zzu() {
        zzal();
        zzj().zzp().zza("Unscheduling upload");
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        zzx().zza();
        if (Build.VERSION.SDK_INT >= 24) {
            zzy();
        }
    }

    private final void zzy() {
        JobScheduler jobScheduler = (JobScheduler) zza().getSystemService("jobscheduler");
        if (jobScheduler != null) {
            jobScheduler.cancel(zzv());
        }
    }

    public final /* bridge */ /* synthetic */ void zzr() {
        super.zzr();
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final /* bridge */ /* synthetic */ void zzt() {
        super.zzt();
    }

    public final void zza(long j) {
        zzal();
        Context zza2 = zza();
        if (!zznp.zza(zza2)) {
            zzj().zzc().zza("Receiver not registered/enabled");
        }
        if (!zznp.zza(zza2, false)) {
            zzj().zzc().zza("Service not registered/enabled");
        }
        zzu();
        zzj().zzp().zza("Scheduling upload, millis", Long.valueOf(j));
        long elapsedRealtime = zzb().elapsedRealtime() + j;
        if (j < Math.max(0, zzbf.zzx.zza(null).longValue()) && !zzx().zzc()) {
            zzx().zza(j);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            Context zza3 = zza();
            ComponentName componentName = new ComponentName(zza3, "com.google.android.gms.measurement.AppMeasurementJobService");
            int zzv = zzv();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            zzcn.zza(zza3, new JobInfo.Builder(zzv, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build(), "com.google.android.gms", "UploadAlarm");
            return;
        }
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.setInexactRepeating(2, elapsedRealtime, Math.max(zzbf.zzs.zza(null).longValue(), j), zzw());
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzc() {
        AlarmManager alarmManager = this.zza;
        if (alarmManager != null) {
            alarmManager.cancel(zzw());
        }
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        zzy();
        return false;
    }
}
