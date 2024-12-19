package com.google.android.gms.ads.internal.util;

import android.util.Range;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcn {
    private static final Map zza = new HashMap();
    private static List zzb;
    private static final Object zzc = new Object();

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
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static java.util.List zza(java.lang.String r13) {
        /*
            java.lang.Object r0 = zzc
            monitor-enter(r0)
            java.util.Map r1 = zza     // Catch:{ all -> 0x0126 }
            boolean r2 = r1.containsKey(r13)     // Catch:{ all -> 0x0126 }
            if (r2 == 0) goto L_0x0013
            java.lang.Object r13 = r1.get(r13)     // Catch:{ all -> 0x0126 }
            java.util.List r13 = (java.util.List) r13     // Catch:{ all -> 0x0126 }
            monitor-exit(r0)     // Catch:{ all -> 0x0126 }
            return r13
        L_0x0013:
            monitor-enter(r0)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.util.List r1 = zzb     // Catch:{ all -> 0x00ff }
            r2 = 0
            if (r1 == 0) goto L_0x001b
            monitor-exit(r0)     // Catch:{ all -> 0x00ff }
            goto L_0x002b
        L_0x001b:
            android.media.MediaCodecList r1 = new android.media.MediaCodecList     // Catch:{ all -> 0x00ff }
            r1.<init>(r2)     // Catch:{ all -> 0x00ff }
            android.media.MediaCodecInfo[] r1 = r1.getCodecInfos()     // Catch:{ all -> 0x00ff }
            java.util.List r1 = java.util.Arrays.asList(r1)     // Catch:{ all -> 0x00ff }
            zzb = r1     // Catch:{ all -> 0x00ff }
            monitor-exit(r0)     // Catch:{ all -> 0x00ff }
        L_0x002b:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r1.<init>()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.util.List r3 = zzb     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
        L_0x0036:
            boolean r4 = r3.hasNext()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            if (r4 == 0) goto L_0x00f8
            java.lang.Object r4 = r3.next()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            android.media.MediaCodecInfo r4 = (android.media.MediaCodecInfo) r4     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            boolean r5 = r4.isEncoder()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            if (r5 != 0) goto L_0x0036
            java.lang.String[] r5 = r4.getSupportedTypes()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.util.List r5 = java.util.Arrays.asList(r5)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            boolean r5 = r5.contains(r13)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            if (r5 == 0) goto L_0x0036
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.<init>()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r6 = "codecName"
            java.lang.String r7 = r4.getName()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r6, r7)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            android.media.MediaCodecInfo$CodecCapabilities r4 = r4.getCapabilitiesForType(r13)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r6.<init>()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            android.media.MediaCodecInfo$CodecProfileLevel[] r7 = r4.profileLevels     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            int r8 = r7.length     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r9 = r2
        L_0x0071:
            if (r9 >= r8) goto L_0x008f
            r10 = r7[r9]     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            int r11 = r10.profile     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            int r10 = r10.level     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r12 = 2
            java.lang.Integer[] r12 = new java.lang.Integer[r12]     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r12[r2] = r11     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r11 = 1
            r12[r11] = r10     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r6.add(r12)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            int r9 = r9 + 1
            goto L_0x0071
        L_0x008f:
            java.lang.String r7 = "profileLevels"
            r5.put(r7, r6)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            android.media.MediaCodecInfo$VideoCapabilities r6 = r4.getVideoCapabilities()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "bitRatesBps"
            android.util.Range r8 = r6.getBitrateRange()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer[] r8 = zzb(r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "widthAlignment"
            int r8 = r6.getWidthAlignment()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "heightAlignment"
            int r8 = r6.getHeightAlignment()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "frameRates"
            android.util.Range r8 = r6.getSupportedFrameRates()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer[] r8 = zzb(r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "widths"
            android.util.Range r8 = r6.getSupportedWidths()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer[] r8 = zzb(r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r8)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r7 = "heights"
            android.util.Range r6 = r6.getSupportedHeights()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer[] r6 = zzb(r6)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r7, r6)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.String r6 = "instancesLimit"
            int r4 = r4.getMaxSupportedInstances()     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r5.put(r6, r4)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r1.add(r5)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            goto L_0x0036
        L_0x00f8:
            java.util.Map r2 = zza     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            r2.put(r13, r1)     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
            monitor-exit(r0)     // Catch:{ all -> 0x0126 }
            return r1
        L_0x00ff:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ff }
            throw r1     // Catch:{ RuntimeException -> 0x0104, LinkageError -> 0x0102 }
        L_0x0102:
            r1 = move-exception
            goto L_0x0105
        L_0x0104:
            r1 = move-exception
        L_0x0105:
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x0126 }
            r2.<init>()     // Catch:{ all -> 0x0126 }
            java.lang.String r3 = "error"
            java.lang.Class r1 = r1.getClass()     // Catch:{ all -> 0x0126 }
            java.lang.String r1 = r1.getSimpleName()     // Catch:{ all -> 0x0126 }
            r2.put(r3, r1)     // Catch:{ all -> 0x0126 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0126 }
            r1.<init>()     // Catch:{ all -> 0x0126 }
            r1.add(r2)     // Catch:{ all -> 0x0126 }
            java.util.Map r2 = zza     // Catch:{ all -> 0x0126 }
            r2.put(r13, r1)     // Catch:{ all -> 0x0126 }
            monitor-exit(r0)     // Catch:{ all -> 0x0126 }
            return r1
        L_0x0126:
            r13 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0126 }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.util.zzcn.zza(java.lang.String):java.util.List");
    }

    private static Integer[] zzb(Range range) {
        return new Integer[]{(Integer) range.getLower(), (Integer) range.getUpper()};
    }
}
