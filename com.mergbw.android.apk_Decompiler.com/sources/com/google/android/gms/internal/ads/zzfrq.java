package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Binder;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzfrq implements BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
    private final zzfsp zza;
    private final zzfsj zzb;
    private final Object zzc = new Object();
    private boolean zzd = false;
    private boolean zze = false;

    zzfrq(Context context, Looper looper, zzfsj zzfsj) {
        this.zzb = zzfsj;
        this.zza = new zzfsp(context, looper, this, this, 12800000);
    }

    private final void zzb() {
        synchronized (this.zzc) {
            if (this.zza.isConnected() || this.zza.isConnecting()) {
                this.zza.disconnect();
            }
            Binder.flushPendingCommands();
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0020 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onConnected(android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.Object r4 = r3.zzc
            monitor-enter(r4)
            boolean r0 = r3.zze     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            return
        L_0x0009:
            r0 = 1
            r3.zze = r0     // Catch:{ all -> 0x002b }
            com.google.android.gms.internal.ads.zzfsp r0 = r3.zza     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            com.google.android.gms.internal.ads.zzfsu r0 = r0.zzp()     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            com.google.android.gms.internal.ads.zzfsn r1 = new com.google.android.gms.internal.ads.zzfsn     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            com.google.android.gms.internal.ads.zzfsj r2 = r3.zzb     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            byte[] r2 = r2.zzaV()     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            r1.<init>(r2)     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
            r0.zzg(r1)     // Catch:{ Exception -> 0x0020, all -> 0x0024 }
        L_0x0020:
            r3.zzb()     // Catch:{ all -> 0x002b }
            goto L_0x0029
        L_0x0024:
            r0 = move-exception
            r3.zzb()     // Catch:{ all -> 0x002b }
            throw r0     // Catch:{ all -> 0x002b }
        L_0x0029:
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            return
        L_0x002b:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x002b }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfrq.onConnected(android.os.Bundle):void");
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
    }

    public final void onConnectionSuspended(int i) {
    }

    /* access modifiers changed from: package-private */
    public final void zza() {
        synchronized (this.zzc) {
            if (!this.zzd) {
                this.zzd = true;
                this.zza.checkAvailabilityAndConnect();
            }
        }
    }
}
