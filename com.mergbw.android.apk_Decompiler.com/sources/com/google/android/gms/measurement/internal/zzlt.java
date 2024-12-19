package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
final class zzlt implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ zzo zze;
    private final /* synthetic */ boolean zzf;
    private final /* synthetic */ zzkx zzg;

    zzlt(zzkx zzkx, AtomicReference atomicReference, String str, String str2, String str3, zzo zzo, boolean z) {
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = zzo;
        this.zzf = z;
        this.zzg = zzkx;
    }

    public final void run() {
        synchronized (this.zza) {
            try {
                zzfl zza2 = this.zzg.zzb;
                if (zza2 == null) {
                    this.zzg.zzj().zzg().zza("(legacy) Failed to get user properties; not connected to service", zzfw.zza(this.zzb), this.zzc, this.zzd);
                    this.zza.set(Collections.emptyList());
                    this.zza.notify();
                    return;
                }
                if (TextUtils.isEmpty(this.zzb)) {
                    Preconditions.checkNotNull(this.zze);
                    this.zza.set(zza2.zza(this.zzc, this.zzd, this.zzf, this.zze));
                } else {
                    this.zza.set(zza2.zza(this.zzb, this.zzc, this.zzd, this.zzf));
                }
                this.zzg.zzaq();
                this.zza.notify();
            } catch (RemoteException e) {
                try {
                    this.zzg.zzj().zzg().zza("(legacy) Failed to get user properties; remote exception", zzfw.zza(this.zzb), this.zzc, e);
                    this.zza.set(Collections.emptyList());
                    this.zza.notify();
                } catch (Throwable th) {
                    this.zza.notify();
                    throw th;
                }
            }
        }
    }
}
