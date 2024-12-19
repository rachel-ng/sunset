package com.google.android.gms.measurement.internal;

import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final /* synthetic */ class zzs implements Runnable {
    private /* synthetic */ zzhj zza;

    public /* synthetic */ zzs(zzhj zzhj) {
        this.zza = zzhj;
    }

    public final void run() {
        zzhj zzhj = this.zza;
        if (!zzhj.zzt().zzw()) {
            zzhj.zzj().zzu().zza("registerTrigger called but app not eligible");
            return;
        }
        zziv zzp = zzhj.zzp();
        Objects.requireNonNull(zzp);
        new Thread(new zzp(zzp)).start();
    }
}
