package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.remoteconfig.RemoteConfigConstants;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfwl extends zzfww {
    final /* synthetic */ zzfwu zza;
    final /* synthetic */ int zzb;
    final /* synthetic */ zzfws zzc;
    final /* synthetic */ TaskCompletionSource zzd;
    final /* synthetic */ zzfwn zze;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzfwl(zzfwn zzfwn, TaskCompletionSource taskCompletionSource, zzfwu zzfwu, int i, zzfws zzfws, TaskCompletionSource taskCompletionSource2) {
        super(taskCompletionSource);
        this.zza = zzfwu;
        this.zzb = i;
        this.zzc = zzfws;
        this.zzd = taskCompletionSource2;
        this.zze = zzfwn;
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.gms.internal.ads.zzfvo, android.os.IInterface] */
    /* access modifiers changed from: protected */
    public final void zza() {
        try {
            ? zze2 = this.zze.zza.zze();
            if (zze2 != 0) {
                zzfwu zzfwu = this.zza;
                String zzb2 = this.zze.zzd;
                int i = this.zzb;
                Bundle bundle = new Bundle();
                bundle.putString("sessionToken", zzfwu.zzb());
                bundle.putInt("displayMode", i);
                bundle.putString("callerPackage", zzb2);
                bundle.putString(RemoteConfigConstants.RequestFieldKey.APP_ID, zzfwu.zza());
                zze2.zzg(bundle, new zzfwm(this.zze, this.zzc));
            }
        } catch (RemoteException e) {
            int i2 = this.zzb;
            zzfwn.zzb.zzb(e, "switchDisplayMode overlay display to %d from: %s", Integer.valueOf(i2), this.zze.zzd);
            this.zzd.trySetException(new RuntimeException(e));
        }
    }
}
