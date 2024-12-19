package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.client.zze;
import java.util.LinkedHashMap;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzeld implements zzgfp {
    final /* synthetic */ long zza;
    final /* synthetic */ zzfgw zzb;
    final /* synthetic */ zzfgt zzc;
    final /* synthetic */ String zzd;
    final /* synthetic */ zzfoa zze;
    final /* synthetic */ zzfhf zzf;
    final /* synthetic */ zzelf zzg;

    zzeld(zzelf zzelf, long j, zzfgw zzfgw, zzfgt zzfgt, String str, zzfoa zzfoa, zzfhf zzfhf) {
        this.zza = j;
        this.zzb = zzfgw;
        this.zzc = zzfgt;
        this.zzd = str;
        this.zze = zzfoa;
        this.zzf = zzfhf;
        this.zzg = zzelf;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public final void zza(java.lang.Throwable r17) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            com.google.android.gms.internal.ads.zzelf r2 = r1.zzg
            com.google.android.gms.common.util.Clock r2 = r2.zza
            long r2 = r2.elapsedRealtime()
            long r4 = r1.zza
            long r2 = r2 - r4
            boolean r4 = r0 instanceof java.util.concurrent.TimeoutException
            r5 = 3
            r6 = 0
            if (r4 == 0) goto L_0x001a
            r4 = 2
        L_0x0018:
            r13 = r6
            goto L_0x0063
        L_0x001a:
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzekn
            if (r4 == 0) goto L_0x0020
            r4 = r5
            goto L_0x0018
        L_0x0020:
            boolean r4 = r0 instanceof java.util.concurrent.CancellationException
            if (r4 == 0) goto L_0x0026
            r4 = 4
            goto L_0x0018
        L_0x0026:
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzfhv
            if (r4 == 0) goto L_0x002c
            r4 = 5
            goto L_0x0018
        L_0x002c:
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzdzd
            r7 = 6
            if (r4 == 0) goto L_0x0061
            com.google.android.gms.ads.internal.client.zze r4 = com.google.android.gms.internal.ads.zzfiq.zza(r17)
            int r4 = r4.zza
            if (r4 != r5) goto L_0x003a
            r7 = 1
        L_0x003a:
            com.google.android.gms.internal.ads.zzbeg r4 = com.google.android.gms.internal.ads.zzbep.zzbD
            com.google.android.gms.internal.ads.zzben r8 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r4 = r8.zza(r4)
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x0061
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzehp
            if (r4 == 0) goto L_0x0061
            r4 = r0
            com.google.android.gms.internal.ads.zzehp r4 = (com.google.android.gms.internal.ads.zzehp) r4
            com.google.android.gms.ads.internal.client.zze r4 = r4.zzb()
            if (r4 == 0) goto L_0x0061
            int r4 = r4.zza
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            r13 = r4
            goto L_0x0062
        L_0x0061:
            r13 = r6
        L_0x0062:
            r4 = r7
        L_0x0063:
            com.google.android.gms.internal.ads.zzelf r14 = r1.zzg
            monitor-enter(r14)
            com.google.android.gms.internal.ads.zzelf r7 = r1.zzg     // Catch:{ all -> 0x0102 }
            boolean r8 = r7.zze     // Catch:{ all -> 0x0102 }
            if (r8 == 0) goto L_0x0086
            com.google.android.gms.internal.ads.zzelh r7 = r7.zzb     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfgw r8 = r1.zzb     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfgt r9 = r1.zzc     // Catch:{ all -> 0x0102 }
            boolean r10 = r0 instanceof com.google.android.gms.internal.ads.zzehp     // Catch:{ all -> 0x0102 }
            if (r10 == 0) goto L_0x007d
            r6 = r0
            com.google.android.gms.internal.ads.zzehp r6 = (com.google.android.gms.internal.ads.zzehp) r6     // Catch:{ all -> 0x0102 }
        L_0x007d:
            r10 = r6
            r6 = r7
            r7 = r8
            r8 = r9
            r9 = r4
            r11 = r2
            r6.zza(r7, r8, r9, r10, r11)     // Catch:{ all -> 0x0102 }
        L_0x0086:
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zziu     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzben r7 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ all -> 0x0102 }
            java.lang.Object r6 = r7.zza(r6)     // Catch:{ all -> 0x0102 }
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch:{ all -> 0x0102 }
            boolean r6 = r6.booleanValue()     // Catch:{ all -> 0x0102 }
            if (r6 == 0) goto L_0x00ad
            com.google.android.gms.internal.ads.zzelf r6 = r1.zzg     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfoe r6 = r6.zzc     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfoa r7 = r1.zze     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfhf r8 = r1.zzf     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfgt r9 = r1.zzc     // Catch:{ all -> 0x0102 }
            java.util.List r10 = r9.zzo     // Catch:{ all -> 0x0102 }
            java.util.List r7 = r7.zzc(r8, r9, r10)     // Catch:{ all -> 0x0102 }
            r6.zzd(r7)     // Catch:{ all -> 0x0102 }
        L_0x00ad:
            com.google.android.gms.internal.ads.zzelf r6 = r1.zzg     // Catch:{ all -> 0x0102 }
            boolean r7 = r6.zzg     // Catch:{ all -> 0x0102 }
            if (r7 == 0) goto L_0x00b7
            monitor-exit(r14)     // Catch:{ all -> 0x0102 }
            return
        L_0x00b7:
            java.util.LinkedHashMap r15 = r6.zzd     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfgt r12 = r1.zzc     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzele r10 = new com.google.android.gms.internal.ads.zzele     // Catch:{ all -> 0x0102 }
            java.lang.String r7 = r1.zzd     // Catch:{ all -> 0x0102 }
            java.lang.String r8 = r12.zzag     // Catch:{ all -> 0x0102 }
            r6 = r10
            r9 = r4
            r4 = r10
            r10 = r2
            r5 = r12
            r12 = r13
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x0102 }
            r15.put(r5, r4)     // Catch:{ all -> 0x0102 }
            com.google.android.gms.ads.internal.client.zze r0 = com.google.android.gms.internal.ads.zzfiq.zza(r17)     // Catch:{ all -> 0x0102 }
            int r4 = r0.zza     // Catch:{ all -> 0x0102 }
            r5 = 3
            if (r4 == r5) goto L_0x00da
            if (r4 != 0) goto L_0x00f5
        L_0x00da:
            com.google.android.gms.ads.internal.client.zze r4 = r0.zzd     // Catch:{ all -> 0x0102 }
            if (r4 == 0) goto L_0x00f5
            java.lang.String r4 = r4.zzc     // Catch:{ all -> 0x0102 }
            java.lang.String r5 = "com.google.android.gms.ads"
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x0102 }
            if (r4 != 0) goto L_0x00f5
            com.google.android.gms.internal.ads.zzehp r4 = new com.google.android.gms.internal.ads.zzehp     // Catch:{ all -> 0x0102 }
            com.google.android.gms.ads.internal.client.zze r0 = r0.zzd     // Catch:{ all -> 0x0102 }
            r5 = 13
            r4.<init>(r5, r0)     // Catch:{ all -> 0x0102 }
            com.google.android.gms.ads.internal.client.zze r0 = com.google.android.gms.internal.ads.zzfiq.zza(r4)     // Catch:{ all -> 0x0102 }
        L_0x00f5:
            com.google.android.gms.internal.ads.zzelf r4 = r1.zzg     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzehq r4 = r4.zzf     // Catch:{ all -> 0x0102 }
            com.google.android.gms.internal.ads.zzfgt r5 = r1.zzc     // Catch:{ all -> 0x0102 }
            r4.zzf(r5, r2, r0)     // Catch:{ all -> 0x0102 }
            monitor-exit(r14)     // Catch:{ all -> 0x0102 }
            return
        L_0x0102:
            r0 = move-exception
            monitor-exit(r14)     // Catch:{ all -> 0x0102 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzeld.zza(java.lang.Throwable):void");
    }

    public final void zzb(Object obj) {
        long elapsedRealtime = this.zzg.zza.elapsedRealtime() - this.zza;
        synchronized (this.zzg) {
            zzelf zzelf = this.zzg;
            if (zzelf.zze) {
                zzelf.zzb.zza(this.zzb, this.zzc, 0, (zzehp) null, elapsedRealtime);
            }
            zzelf zzelf2 = this.zzg;
            if (!zzelf2.zzg) {
                if (zzelf2.zzq(this.zzc)) {
                    ((zzele) this.zzg.zzd.get(this.zzc)).zzd = elapsedRealtime;
                } else {
                    LinkedHashMap zzh = this.zzg.zzd;
                    zzfgt zzfgt = this.zzc;
                    zzh.put(zzfgt, new zzele(this.zzd, zzfgt.zzag, 0, elapsedRealtime, (Integer) null));
                }
                this.zzg.zzf.zzg(this.zzc, elapsedRealtime, (zze) null);
            }
        }
    }
}
