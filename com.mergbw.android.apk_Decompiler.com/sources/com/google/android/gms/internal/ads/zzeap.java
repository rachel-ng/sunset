package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.ConnectionResult;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzeap extends zzeas {
    private final Context zzg;
    private final Executor zzh;

    public zzeap(Context context, Executor executor) {
        this.zzg = context;
        this.zzh = executor;
        this.zzf = new zzbwr(context, zzu.zzt().zzb(), this, this);
    }

    public final void onConnected(Bundle bundle) {
        synchronized (this.zzb) {
            if (!this.zzd) {
                this.zzd = true;
                try {
                    this.zzf.zzp().zzg(this.zze, new zzear(this));
                } catch (RemoteException | IllegalArgumentException unused) {
                    this.zza.zzd(new zzebh(1));
                } catch (Throwable th) {
                    zzu.zzo().zzw(th, "RemoteAdRequestClientTask.onConnected");
                    this.zza.zzd(new zzebh(1));
                }
            }
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzm.zze("Cannot connect to remote service, fallback to local instance.");
        this.zza.zzd(new zzebh(1));
    }

    public final ListenableFuture zza(zzbxu zzbxu) {
        synchronized (this.zzb) {
            if (this.zzc) {
                zzccn zzccn = this.zza;
                return zzccn;
            }
            this.zzc = true;
            this.zze = zzbxu;
            this.zzf.checkAvailabilityAndConnect();
            this.zza.addListener(new zzeao(this), zzcci.zzf);
            zzc(this.zzg, this.zza, this.zzh);
            zzccn zzccn2 = this.zza;
            return zzccn2;
        }
    }
}
