package com.google.android.gms.internal.ads;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzarr implements zzard {
    private final Map zza = new HashMap();
    private final zzaqq zzb;
    private final BlockingQueue zzc;
    private final zzaqv zzd;

    zzarr(zzaqq zzaqq, BlockingQueue blockingQueue, zzaqv zzaqv) {
        this.zzd = zzaqv;
        this.zzb = zzaqq;
        this.zzc = blockingQueue;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0061, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zza(com.google.android.gms.internal.ads.zzare r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Map r0 = r5.zza     // Catch:{ all -> 0x0062 }
            java.lang.String r6 = r6.zzj()     // Catch:{ all -> 0x0062 }
            java.lang.Object r0 = r0.remove(r6)     // Catch:{ all -> 0x0062 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0062 }
            if (r0 == 0) goto L_0x0060
            boolean r1 = r0.isEmpty()     // Catch:{ all -> 0x0062 }
            if (r1 != 0) goto L_0x0060
            boolean r1 = com.google.android.gms.internal.ads.zzarq.zzb     // Catch:{ all -> 0x0062 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x002f
            int r1 = r0.size()     // Catch:{ all -> 0x0062 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0062 }
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ all -> 0x0062 }
            r4[r3] = r1     // Catch:{ all -> 0x0062 }
            r4[r2] = r6     // Catch:{ all -> 0x0062 }
            java.lang.String r1 = "%d waiting requests for cacheKey=%s; resend to network"
            com.google.android.gms.internal.ads.zzarq.zzd(r1, r4)     // Catch:{ all -> 0x0062 }
        L_0x002f:
            java.lang.Object r1 = r0.remove(r3)     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzare r1 = (com.google.android.gms.internal.ads.zzare) r1     // Catch:{ all -> 0x0062 }
            java.util.Map r4 = r5.zza     // Catch:{ all -> 0x0062 }
            r4.put(r6, r0)     // Catch:{ all -> 0x0062 }
            r1.zzu(r5)     // Catch:{ all -> 0x0062 }
            java.util.concurrent.BlockingQueue r6 = r5.zzc     // Catch:{ InterruptedException -> 0x0044 }
            r6.put(r1)     // Catch:{ InterruptedException -> 0x0044 }
            monitor-exit(r5)
            return
        L_0x0044:
            r6 = move-exception
            java.lang.String r6 = r6.toString()     // Catch:{ all -> 0x0062 }
            java.lang.Object[] r0 = new java.lang.Object[r2]     // Catch:{ all -> 0x0062 }
            r0[r3] = r6     // Catch:{ all -> 0x0062 }
            java.lang.String r6 = "Couldn't add request to queue. %s"
            com.google.android.gms.internal.ads.zzarq.zzb(r6, r0)     // Catch:{ all -> 0x0062 }
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x0062 }
            r6.interrupt()     // Catch:{ all -> 0x0062 }
            com.google.android.gms.internal.ads.zzaqq r6 = r5.zzb     // Catch:{ all -> 0x0062 }
            r6.zzb()     // Catch:{ all -> 0x0062 }
            monitor-exit(r5)
            return
        L_0x0060:
            monitor-exit(r5)
            return
        L_0x0062:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0062 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarr.zza(com.google.android.gms.internal.ads.zzare):void");
    }

    public final void zzb(zzare zzare, zzark zzark) {
        List<zzare> list;
        zzaqn zzaqn = zzark.zzb;
        if (zzaqn == null || zzaqn.zza(System.currentTimeMillis())) {
            zza(zzare);
            return;
        }
        String zzj = zzare.zzj();
        synchronized (this) {
            list = (List) this.zza.remove(zzj);
        }
        if (list != null) {
            if (zzarq.zzb) {
                zzarq.zzd("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(list.size()), zzj);
            }
            for (zzare zzb2 : list) {
                this.zzd.zzb(zzb2, zzark, (Runnable) null);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean zzc(com.google.android.gms.internal.ads.zzare r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.Map r0 = r5.zza     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = r6.zzj()     // Catch:{ all -> 0x0052 }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x0052 }
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L_0x003a
            java.util.Map r0 = r5.zza     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0052 }
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x001e
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
            r0.<init>()     // Catch:{ all -> 0x0052 }
        L_0x001e:
            java.lang.String r4 = "waiting-for-response"
            r6.zzm(r4)     // Catch:{ all -> 0x0052 }
            r0.add(r6)     // Catch:{ all -> 0x0052 }
            java.util.Map r6 = r5.zza     // Catch:{ all -> 0x0052 }
            r6.put(r1, r0)     // Catch:{ all -> 0x0052 }
            boolean r6 = com.google.android.gms.internal.ads.zzarq.zzb     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0038
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            r6[r3] = r1     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = "Request for cacheKey=%s is in flight, putting on hold."
            com.google.android.gms.internal.ads.zzarq.zza(r0, r6)     // Catch:{ all -> 0x0052 }
        L_0x0038:
            monitor-exit(r5)
            return r2
        L_0x003a:
            java.util.Map r0 = r5.zza     // Catch:{ all -> 0x0052 }
            r4 = 0
            r0.put(r1, r4)     // Catch:{ all -> 0x0052 }
            r6.zzu(r5)     // Catch:{ all -> 0x0052 }
            boolean r6 = com.google.android.gms.internal.ads.zzarq.zzb     // Catch:{ all -> 0x0052 }
            if (r6 == 0) goto L_0x0050
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
            r6[r3] = r1     // Catch:{ all -> 0x0052 }
            java.lang.String r0 = "new request, sending to network %s"
            com.google.android.gms.internal.ads.zzarq.zza(r0, r6)     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r5)
            return r3
        L_0x0052:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0052 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzarr.zzc(com.google.android.gms.internal.ads.zzare):boolean");
    }
}
