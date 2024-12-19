package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;

/* compiled from: com.google.android.gms:play-services-measurement@@22.0.2 */
public final /* synthetic */ class zzmd implements Runnable {
    private /* synthetic */ zzme zza;
    private /* synthetic */ zzfw zzb;
    private /* synthetic */ JobParameters zzc;

    public /* synthetic */ zzmd(zzme zzme, zzfw zzfw, JobParameters jobParameters) {
        this.zza = zzme;
        this.zzb = zzfw;
        this.zzc = jobParameters;
    }

    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}
