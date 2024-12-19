package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public class zzfpg implements Application.ActivityLifecycleCallbacks {
    protected boolean zza;
    private boolean zzb;
    private zzfpf zzc;

    private final void zza(boolean z) {
        if (this.zza != z) {
            this.zza = z;
            if (this.zzb) {
                zzb(z);
                zzfpf zzfpf = this.zzc;
                if (zzfpf != null) {
                    zzfpf.zzc(z);
                }
            }
        }
    }

    private final boolean zzh() {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        return runningAppProcessInfo.importance == 100 || zzc();
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
        zza(true);
    }

    public final void onActivityStopped(Activity activity) {
        zza(zzh());
    }

    /* access modifiers changed from: protected */
    public void zzb(boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean zzc() {
        return false;
    }

    public final void zzd(Context context) {
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(this);
        }
    }

    public final void zze(zzfpf zzfpf) {
        this.zzc = zzfpf;
    }

    public final void zzf() {
        this.zzb = true;
        boolean zzh = zzh();
        this.zza = zzh;
        zzb(zzh);
    }

    public final void zzg() {
        this.zzb = false;
        this.zzc = null;
    }
}
