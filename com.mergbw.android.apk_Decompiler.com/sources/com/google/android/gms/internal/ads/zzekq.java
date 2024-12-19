package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzekq {
    private final Map zza = new HashMap();
    private final List zzb = new ArrayList();
    private final zzggm zzc;
    private final List zzd = new ArrayList();
    private final Set zze = new HashSet();
    private zzelg zzf;
    private int zzg = Integer.MAX_VALUE;
    private final String zzh;
    private final int zzi;
    private final zzelf zzj;
    private zzfgt zzk;
    private boolean zzl;

    zzekq(zzfhf zzfhf, zzelf zzelf, zzggm zzggm) {
        this.zzl = false;
        this.zzi = zzfhf.zzb.zzb.zzq;
        this.zzj = zzelf;
        this.zzc = zzggm;
        this.zzh = zzelm.zzc(zzfhf);
        List list = zzfhf.zzb.zza;
        for (int i = 0; i < list.size(); i++) {
            this.zza.put((zzfgt) list.get(i), Integer.valueOf(i));
        }
        this.zzb.addAll(list);
    }

    private final synchronized void zze() {
        this.zzj.zzi(this.zzk);
        zzelg zzelg = this.zzf;
        if (zzelg != null) {
            this.zzc.zzc(zzelg);
        } else {
            this.zzc.zzd(new zzelj(3, this.zzh));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzf(boolean r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.List r0 = r5.zzb     // Catch:{ all -> 0x0049 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0049 }
        L_0x0007:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0049 }
            if (r1 == 0) goto L_0x0046
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0049 }
            com.google.android.gms.internal.ads.zzfgt r1 = (com.google.android.gms.internal.ads.zzfgt) r1     // Catch:{ all -> 0x0049 }
            java.util.Map r2 = r5.zza     // Catch:{ all -> 0x0049 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0049 }
            java.lang.Integer r2 = (java.lang.Integer) r2     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0022
            int r2 = r2.intValue()     // Catch:{ all -> 0x0049 }
            goto L_0x0025
        L_0x0022:
            r2 = 2147483647(0x7fffffff, float:NaN)
        L_0x0025:
            java.lang.Integer r3 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0049 }
            if (r6 != 0) goto L_0x0035
            java.util.Set r4 = r5.zze     // Catch:{ all -> 0x0049 }
            java.lang.String r1 = r1.zzau     // Catch:{ all -> 0x0049 }
            boolean r1 = r4.contains(r1)     // Catch:{ all -> 0x0049 }
            if (r1 != 0) goto L_0x0007
        L_0x0035:
            r3.getClass()     // Catch:{ all -> 0x0049 }
            int r1 = r5.zzg     // Catch:{ all -> 0x0049 }
            if (r2 >= r1) goto L_0x003f
            monitor-exit(r5)
            r6 = 1
            return r6
        L_0x003f:
            r3.getClass()     // Catch:{ all -> 0x0049 }
            int r1 = r5.zzg     // Catch:{ all -> 0x0049 }
            if (r2 <= r1) goto L_0x0007
        L_0x0046:
            monitor-exit(r5)
            r6 = 0
            return r6
        L_0x0049:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0049 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekq.zzf(boolean):boolean");
    }

    private final synchronized boolean zzg() {
        for (zzfgt zzfgt : this.zzd) {
            Integer num = (Integer) this.zza.get(zzfgt);
            int intValue = num != null ? num.intValue() : Integer.MAX_VALUE;
            Integer.valueOf(intValue).getClass();
            if (intValue < this.zzg) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0013, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzh() {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 1
            boolean r1 = r2.zzf(r0)     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0012
            boolean r1 = r2.zzg()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            monitor-exit(r2)
            r0 = 0
            return r0
        L_0x0012:
            monitor-exit(r2)
            return r0
        L_0x0014:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0014 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekq.zzh():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0041, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean zzi() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzl     // Catch:{ all -> 0x0042 }
            r1 = 0
            if (r0 == 0) goto L_0x0008
            monitor-exit(r3)
            return r1
        L_0x0008:
            java.util.List r0 = r3.zzb     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r0 != 0) goto L_0x0027
            java.util.List r0 = r3.zzb     // Catch:{ all -> 0x0042 }
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0042 }
            com.google.android.gms.internal.ads.zzfgt r0 = (com.google.android.gms.internal.ads.zzfgt) r0     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.zzaw     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0027
            java.util.List r0 = r3.zzd     // Catch:{ all -> 0x0042 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0025
            goto L_0x0027
        L_0x0025:
            monitor-exit(r3)
            return r1
        L_0x0027:
            boolean r0 = r3.zzd()     // Catch:{ all -> 0x0042 }
            if (r0 != 0) goto L_0x0040
            java.util.List r0 = r3.zzd     // Catch:{ all -> 0x0042 }
            int r2 = r3.zzi     // Catch:{ all -> 0x0042 }
            int r0 = r0.size()     // Catch:{ all -> 0x0042 }
            if (r0 >= r2) goto L_0x0040
            boolean r0 = r3.zzf(r1)     // Catch:{ all -> 0x0042 }
            if (r0 == 0) goto L_0x0040
            monitor-exit(r3)
            r0 = 1
            return r0
        L_0x0040:
            monitor-exit(r3)
            return r1
        L_0x0042:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0042 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekq.zzi():boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0047, code lost:
        return null;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.android.gms.internal.ads.zzfgt zza() {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = r4.zzi()     // Catch:{ all -> 0x0049 }
            if (r0 == 0) goto L_0x0046
            r0 = 0
        L_0x0008:
            java.util.List r1 = r4.zzb     // Catch:{ all -> 0x0049 }
            int r1 = r1.size()     // Catch:{ all -> 0x0049 }
            if (r0 >= r1) goto L_0x0046
            java.util.List r1 = r4.zzb     // Catch:{ all -> 0x0049 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0049 }
            com.google.android.gms.internal.ads.zzfgt r1 = (com.google.android.gms.internal.ads.zzfgt) r1     // Catch:{ all -> 0x0049 }
            java.lang.String r2 = r1.zzau     // Catch:{ all -> 0x0049 }
            java.util.Set r3 = r4.zze     // Catch:{ all -> 0x0049 }
            boolean r3 = r3.contains(r2)     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x0025
            int r0 = r0 + 1
            goto L_0x0008
        L_0x0025:
            boolean r3 = r1.zzaw     // Catch:{ all -> 0x0049 }
            if (r3 == 0) goto L_0x002c
            r3 = 1
            r4.zzl = r3     // Catch:{ all -> 0x0049 }
        L_0x002c:
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ all -> 0x0049 }
            if (r3 != 0) goto L_0x0037
            java.util.Set r3 = r4.zze     // Catch:{ all -> 0x0049 }
            r3.add(r2)     // Catch:{ all -> 0x0049 }
        L_0x0037:
            java.util.List r2 = r4.zzd     // Catch:{ all -> 0x0049 }
            r2.add(r1)     // Catch:{ all -> 0x0049 }
            java.util.List r1 = r4.zzb     // Catch:{ all -> 0x0049 }
            java.lang.Object r0 = r1.remove(r0)     // Catch:{ all -> 0x0049 }
            com.google.android.gms.internal.ads.zzfgt r0 = (com.google.android.gms.internal.ads.zzfgt) r0     // Catch:{ all -> 0x0049 }
            monitor-exit(r4)
            return r0
        L_0x0046:
            monitor-exit(r4)
            r0 = 0
            return r0
        L_0x0049:
            r0 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0049 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekq.zza():com.google.android.gms.internal.ads.zzfgt");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzb(java.lang.Throwable r1, com.google.android.gms.internal.ads.zzfgt r2) {
        /*
            r0 = this;
            monitor-enter(r0)
            r1 = 0
            r0.zzl = r1     // Catch:{ all -> 0x0023 }
            java.util.List r1 = r0.zzd     // Catch:{ all -> 0x0023 }
            r1.remove(r2)     // Catch:{ all -> 0x0023 }
            java.util.Set r1 = r0.zze     // Catch:{ all -> 0x0023 }
            java.lang.String r2 = r2.zzau     // Catch:{ all -> 0x0023 }
            r1.remove(r2)     // Catch:{ all -> 0x0023 }
            boolean r1 = r0.zzd()     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0021
            boolean r1 = r0.zzh()     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x0021
            r0.zze()     // Catch:{ all -> 0x0023 }
            monitor-exit(r0)
            return
        L_0x0021:
            monitor-exit(r0)
            return
        L_0x0023:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0023 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzekq.zzb(java.lang.Throwable, com.google.android.gms.internal.ads.zzfgt):void");
    }

    /* access modifiers changed from: package-private */
    public final synchronized void zzc(zzelg zzelg, zzfgt zzfgt) {
        this.zzl = false;
        this.zzd.remove(zzfgt);
        if (zzd()) {
            zzelg.zzq();
            return;
        }
        Integer num = (Integer) this.zza.get(zzfgt);
        int intValue = num != null ? num.intValue() : Integer.MAX_VALUE;
        Integer valueOf = Integer.valueOf(intValue);
        valueOf.getClass();
        if (intValue > this.zzg) {
            this.zzj.zzm(zzfgt);
            return;
        }
        if (this.zzf != null) {
            this.zzj.zzm(this.zzk);
        }
        valueOf.getClass();
        this.zzg = intValue;
        this.zzf = zzelg;
        this.zzk = zzfgt;
        if (!zzh()) {
            zze();
        }
    }

    /* access modifiers changed from: package-private */
    public final synchronized boolean zzd() {
        return this.zzc.isDone();
    }
}
