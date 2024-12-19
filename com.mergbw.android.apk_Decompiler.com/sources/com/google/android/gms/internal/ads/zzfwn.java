package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwn {
    /* access modifiers changed from: private */
    public static final zzfwv zzb = new zzfwv("OverlayDisplayService");
    private static final Intent zzc = new Intent("com.google.android.play.core.lmd.BIND_OVERLAY_DISPLAY_SERVICE").setPackage("com.android.vending");
    final zzfxg zza;
    /* access modifiers changed from: private */
    public final String zzd;

    zzfwn(Context context) {
        if (zzfxj.zza(context)) {
            this.zza = new zzfxg(context.getApplicationContext(), zzb, "OverlayDisplayService", zzc, new zzfwi(), (zzfxb) null);
        } else {
            this.zza = null;
        }
        this.zzd = context.getPackageName();
    }

    /* access modifiers changed from: package-private */
    public final void zzc() {
        if (this.zza != null) {
            zzb.zzc("unbind LMD display overlay service", new Object[0]);
            this.zza.zzu();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzd(zzfwe zzfwe, zzfws zzfws) {
        if (this.zza == null) {
            zzb.zza("error: %s", "Play Store not found.");
            return;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzs(new zzfwk(this, taskCompletionSource, zzfwe, zzfws, taskCompletionSource), taskCompletionSource);
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzfwp zzfwp, zzfws zzfws) {
        if (this.zza == null) {
            zzb.zza("error: %s", "Play Store not found.");
        } else if (zzfwp.zzh() == null) {
            zzb.zza("Failed to convert OverlayDisplayShowRequest when to create a new session: appId cannot be null.", new Object[0]);
            zzfwq zzc2 = zzfwr.zzc();
            zzc2.zzb(8160);
            zzfws.zza(zzc2.zzc());
        } else {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.zza.zzs(new zzfwj(this, taskCompletionSource, zzfwp, zzfws, taskCompletionSource), taskCompletionSource);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzf(zzfwu zzfwu, zzfws zzfws, int i) {
        if (this.zza == null) {
            zzb.zza("error: %s", "Play Store not found.");
            return;
        }
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zza.zzs(new zzfwl(this, taskCompletionSource, zzfwu, i, zzfws, taskCompletionSource), taskCompletionSource);
    }
}
