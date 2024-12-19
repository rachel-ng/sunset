package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.util.Log;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzftj {
    final /* synthetic */ zzftk zza;
    private final byte[] zzb;
    private int zzc;
    private int zzd;

    /* synthetic */ zzftj(zzftk zzftk, byte[] bArr, zzfti zzfti) {
        this.zza = zzftk;
        this.zzb = bArr;
    }

    public final zzftj zza(int i) {
        this.zzd = i;
        return this;
    }

    public final zzftj zzb(int i) {
        this.zzc = i;
        return this;
    }

    public final synchronized void zzc() {
        try {
            zzftk zzftk = this.zza;
            if (zzftk.zzb) {
                zzftk.zza.zzj(this.zzb);
                this.zza.zza.zzi(this.zzc);
                this.zza.zza.zzg(this.zzd);
                this.zza.zza.zzh((int[]) null);
                this.zza.zza.zzf();
            }
        } catch (RemoteException e) {
            Log.d("GASS", "Clearcut log failed", e);
        }
    }
}
