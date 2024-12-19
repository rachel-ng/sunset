package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzmp {
    final /* synthetic */ zzmh zza;

    zzmp(zzmh zzmh) {
        this.zza = zzmh;
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzt();
        if (this.zza.zzk().zza(this.zza.zzb().currentTimeMillis())) {
            this.zza.zzk().zzg.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzj().zzp().zza("Detected application was in foreground");
                zzb(this.zza.zzb().currentTimeMillis(), false);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(long j, boolean z) {
        this.zza.zzt();
        this.zza.zzab();
        if (this.zza.zzk().zza(j)) {
            this.zza.zzk().zzg.zza(true);
            this.zza.zzg().zzag();
        }
        this.zza.zzk().zzk.zza(j);
        if (this.zza.zzk().zzg.zza()) {
            zzb(j, z);
        }
    }

    private final void zzb(long j, boolean z) {
        this.zza.zzt();
        if (this.zza.zzu.zzac()) {
            this.zza.zzk().zzk.zza(j);
            this.zza.zzj().zzp().zza("Session started, time", Long.valueOf(this.zza.zzb().elapsedRealtime()));
            long j2 = j / 1000;
            Long valueOf = Long.valueOf(j2);
            this.zza.zzm().zza("auto", "_sid", (Object) valueOf, j);
            zzgm zzgm = this.zza.zzk().zzl;
            valueOf.getClass();
            zzgm.zza(j2);
            this.zza.zzk().zzg.zza(false);
            Bundle bundle = new Bundle();
            valueOf.getClass();
            bundle.putLong("_sid", j2);
            this.zza.zzm().zza("auto", "_s", j, bundle);
            String zza2 = this.zza.zzk().zzq.zza();
            if (!TextUtils.isEmpty(zza2)) {
                Bundle bundle2 = new Bundle();
                bundle2.putString("_ffr", zza2);
                this.zza.zzm().zza("auto", "_ssr", j, bundle2);
            }
        }
    }
}
