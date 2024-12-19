package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.remoteconfig.RemoteConfigConstants;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwj extends zzfww {
    final /* synthetic */ zzfwp zza;
    final /* synthetic */ zzfws zzb;
    final /* synthetic */ TaskCompletionSource zzc;
    final /* synthetic */ zzfwn zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfwj(zzfwn zzfwn, TaskCompletionSource taskCompletionSource, zzfwp zzfwp, zzfws zzfws, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zza = zzfwp;
        this.zzb = zzfws;
        this.zzc = taskCompletionSource2;
        this.zzd = zzfwn;
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [com.google.android.gms.internal.ads.zzfvo, android.os.IInterface] */
    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ? zze = this.zzd.zza.zze();
            if (zze != 0) {
                zzfwn zzfwn = this.zzd;
                String zzb2 = zzfwn.zzd;
                zzfwp zzfwp = this.zza;
                String zzb3 = zzfwn.zzd;
                Bundle bundle = new Bundle();
                bundle.putBinder("windowToken", zzfwp.zzf());
                bundle.putString("adFieldEnifd", zzfwp.zzg());
                bundle.putInt("layoutGravity", zzfwp.zzc());
                bundle.putFloat("layoutVerticalMargin", zzfwp.zza());
                bundle.putInt("displayMode", 0);
                bundle.putInt("triggerMode", 0);
                bundle.putInt("windowWidthPx", zzfwp.zze());
                bundle.putString("deeplinkUrl", (String) null);
                bundle.putBoolean("stableSessionToken", true);
                bundle.putString("callerPackage", zzb3);
                if (zzfwp.zzh() != null) {
                    bundle.putString(RemoteConfigConstants.RequestFieldKey.APP_ID, zzfwp.zzh());
                }
                zze.zzf(zzb2, bundle, new zzfwm(this.zzd, this.zzb));
            }
        } catch (RemoteException e) {
            zzfwn.zzb.zzb(e, "show overlay display from: %s", this.zzd.zzd);
            this.zzc.trySetException(new RuntimeException(e));
        }
    }
}
