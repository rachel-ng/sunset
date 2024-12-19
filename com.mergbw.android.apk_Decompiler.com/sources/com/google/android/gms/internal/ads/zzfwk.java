package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.remoteconfig.RemoteConfigConstants;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwk extends zzfww {
    final /* synthetic */ zzfwe zza;
    final /* synthetic */ zzfws zzb;
    final /* synthetic */ TaskCompletionSource zzc;
    final /* synthetic */ zzfwn zzd;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfwk(zzfwn zzfwn, TaskCompletionSource taskCompletionSource, zzfwe zzfwe, zzfws zzfws, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zza = zzfwe;
        this.zzb = zzfws;
        this.zzc = taskCompletionSource2;
        this.zzd = zzfwn;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.ads.zzfvo, android.os.IInterface] */
    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ? zze = this.zzd.zza.zze();
            if (zze != 0) {
                zzfwe zzfwe = this.zza;
                String zzb2 = this.zzd.zzd;
                Bundle bundle = new Bundle();
                bundle.putString("sessionToken", zzfwe.zzb());
                bundle.putString("callerPackage", zzb2);
                bundle.putString(RemoteConfigConstants.RequestFieldKey.APP_ID, zzfwe.zza());
                zze.zze(bundle, new zzfwm(this.zzd, this.zzb));
            }
        } catch (RemoteException e) {
            zzfwn zzfwn = this.zzd;
            zzfwn.zzb.zzb(e, "dismiss overlay display from: %s", zzfwn.zzd);
            this.zzc.trySetException(new RuntimeException(e));
        }
    }
}
