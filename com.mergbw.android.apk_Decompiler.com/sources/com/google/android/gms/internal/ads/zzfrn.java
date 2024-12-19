package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfrn implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    protected final zzfsp zza;
    private final String zzb;
    private final String zzc;
    private final zzazw zzd;
    private final LinkedBlockingQueue zze;
    private final HandlerThread zzf;
    private final zzfre zzg;
    private final long zzh = System.currentTimeMillis();

    public zzfrn(Context context, int i, zzazw zzazw, String str, String str2, String str3, zzfre zzfre) {
        this.zzb = str;
        this.zzd = zzazw;
        this.zzc = str2;
        this.zzg = zzfre;
        HandlerThread handlerThread = new HandlerThread("GassDGClient");
        this.zzf = handlerThread;
        handlerThread.start();
        zzfsp zzfsp = new zzfsp(context, handlerThread.getLooper(), this, this, 19621000);
        this.zza = zzfsp;
        this.zze = new LinkedBlockingQueue();
        zzfsp.checkAvailabilityAndConnect();
    }

    static zzftb zza() {
        return new zzftb((byte[]) null, 1);
    }

    private final void zze(int i, long j, Exception exc) {
        this.zzg.zzc(i, System.currentTimeMillis() - j, exc);
    }

    public final void onConnected(Bundle bundle) {
        zzfsu zzd2 = zzd();
        if (zzd2 != null) {
            try {
                zzftb zzf2 = zzd2.zzf(new zzfsz(1, this.zzd, this.zzb, this.zzc));
                zze(5011, this.zzh, (Exception) null);
                this.zze.put(zzf2);
            } catch (Throwable th) {
                zzc();
                this.zzf.quit();
                throw th;
            }
            zzc();
            this.zzf.quit();
        }
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        try {
            zze(4012, this.zzh, (Exception) null);
            this.zze.put(zza());
        } catch (InterruptedException unused) {
        }
    }

    public final void onConnectionSuspended(int i) {
        try {
            zze(4011, this.zzh, (Exception) null);
            this.zze.put(zza());
        } catch (InterruptedException unused) {
        }
    }

    public final zzftb zzb(int i) {
        zzftb zzftb;
        try {
            zzftb = (zzftb) this.zze.poll(50000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            zze(2009, this.zzh, e);
            zzftb = null;
        }
        zze(PlaybackException.ERROR_CODE_PARSING_MANIFEST_UNSUPPORTED, this.zzh, (Exception) null);
        if (zzftb != null) {
            if (zzftb.zzc == 7) {
                zzfre.zzg(zzatc.DISABLED);
            } else {
                zzfre.zzg(zzatc.ENABLED);
            }
        }
        return zzftb == null ? zza() : zzftb;
    }

    public final void zzc() {
        zzfsp zzfsp = this.zza;
        if (zzfsp == null) {
            return;
        }
        if (zzfsp.isConnected() || this.zza.isConnecting()) {
            this.zza.disconnect();
        }
    }

    /* access modifiers changed from: protected */
    public final zzfsu zzd() {
        try {
            return this.zza.zzp();
        } catch (DeadObjectException | IllegalStateException unused) {
            return null;
        }
    }
}
