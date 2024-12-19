package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzfz implements zzga {
    /* access modifiers changed from: private */
    public final AtomicBoolean zza = new AtomicBoolean();
    private HashMap<String, String> zzb = null;
    private final HashMap<String, Boolean> zzc = new HashMap<>(16, 1.0f);
    private final HashMap<String, Integer> zzd = new HashMap<>(16, 1.0f);
    private final HashMap<String, Long> zze = new HashMap<>(16, 1.0f);
    private final HashMap<String, Float> zzf = new HashMap<>(16, 1.0f);
    private Object zzg = null;
    private boolean zzh = false;
    private String[] zzi = new String[0];
    private final zzgg zzj = new zzge();

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x006c, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e7, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00e9, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r7 = r6.zzj.zza(r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f4, code lost:
        if (r7 == null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00fa, code lost:
        if (r7.equals((java.lang.Object) null) == false) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fc, code lost:
        r7 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00fd, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0100, code lost:
        if (r9 != r6.zzg) goto L_0x0107;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0102, code lost:
        r6.zzb.put(r8, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0107, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0108, code lost:
        if (r7 == null) goto L_0x010b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x010a, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x010b, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x010f, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zza(android.content.ContentResolver r7, java.lang.String r8, java.lang.String r9) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x0113
            monitor-enter(r6)
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r6.zzb     // Catch:{ all -> 0x0110 }
            r0 = 1
            r1 = 0
            r2 = 0
            if (r9 != 0) goto L_0x002c
            java.util.concurrent.atomic.AtomicBoolean r9 = r6.zza     // Catch:{ all -> 0x0110 }
            r9.set(r1)     // Catch:{ all -> 0x0110 }
            java.util.HashMap r9 = new java.util.HashMap     // Catch:{ all -> 0x0110 }
            r3 = 16
            r4 = 1065353216(0x3f800000, float:1.0)
            r9.<init>(r3, r4)     // Catch:{ all -> 0x0110 }
            r6.zzb = r9     // Catch:{ all -> 0x0110 }
            java.lang.Object r9 = new java.lang.Object     // Catch:{ all -> 0x0110 }
            r9.<init>()     // Catch:{ all -> 0x0110 }
            r6.zzg = r9     // Catch:{ all -> 0x0110 }
            android.net.Uri r9 = com.google.android.gms.internal.measurement.zzfy.zza     // Catch:{ all -> 0x0110 }
            com.google.android.gms.internal.measurement.zzgb r3 = new com.google.android.gms.internal.measurement.zzgb     // Catch:{ all -> 0x0110 }
            r3.<init>(r6, r2)     // Catch:{ all -> 0x0110 }
            r7.registerContentObserver(r9, r0, r3)     // Catch:{ all -> 0x0110 }
            goto L_0x0056
        L_0x002c:
            java.util.concurrent.atomic.AtomicBoolean r9 = r6.zza     // Catch:{ all -> 0x0110 }
            boolean r9 = r9.getAndSet(r1)     // Catch:{ all -> 0x0110 }
            if (r9 == 0) goto L_0x0056
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r6.zzb     // Catch:{ all -> 0x0110 }
            r9.clear()     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Boolean> r9 = r6.zzc     // Catch:{ all -> 0x0110 }
            r9.clear()     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Integer> r9 = r6.zzd     // Catch:{ all -> 0x0110 }
            r9.clear()     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Long> r9 = r6.zze     // Catch:{ all -> 0x0110 }
            r9.clear()     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Float> r9 = r6.zzf     // Catch:{ all -> 0x0110 }
            r9.clear()     // Catch:{ all -> 0x0110 }
            java.lang.Object r9 = new java.lang.Object     // Catch:{ all -> 0x0110 }
            r9.<init>()     // Catch:{ all -> 0x0110 }
            r6.zzg = r9     // Catch:{ all -> 0x0110 }
            r6.zzh = r1     // Catch:{ all -> 0x0110 }
        L_0x0056:
            java.lang.Object r9 = r6.zzg     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.String> r3 = r6.zzb     // Catch:{ all -> 0x0110 }
            boolean r3 = r3.containsKey(r8)     // Catch:{ all -> 0x0110 }
            if (r3 == 0) goto L_0x006d
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r6.zzb     // Catch:{ all -> 0x0110 }
            java.lang.Object r7 = r7.get(r8)     // Catch:{ all -> 0x0110 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0110 }
            if (r7 == 0) goto L_0x006b
            r2 = r7
        L_0x006b:
            monitor-exit(r6)     // Catch:{ all -> 0x0110 }
            return r2
        L_0x006d:
            java.lang.String[] r3 = r6.zzi     // Catch:{ all -> 0x0110 }
            int r4 = r3.length     // Catch:{ all -> 0x0110 }
        L_0x0070:
            if (r1 >= r4) goto L_0x00ed
            r5 = r3[r1]     // Catch:{ all -> 0x0110 }
            boolean r5 = r8.startsWith(r5)     // Catch:{ all -> 0x0110 }
            if (r5 == 0) goto L_0x00ea
            boolean r9 = r6.zzh     // Catch:{ all -> 0x0110 }
            if (r9 != 0) goto L_0x00e8
            java.lang.String[] r9 = r6.zzi     // Catch:{ all -> 0x0110 }
            com.google.android.gms.internal.measurement.zzgg r1 = r6.zzj     // Catch:{ zzgf -> 0x00d3 }
            com.google.android.gms.internal.measurement.zzgc r3 = new com.google.android.gms.internal.measurement.zzgc     // Catch:{ zzgf -> 0x00d3 }
            r3.<init>()     // Catch:{ zzgf -> 0x00d3 }
            java.util.Map r7 = r1.zza(r7, r9, r3)     // Catch:{ zzgf -> 0x00d3 }
            java.util.HashMap r7 = (java.util.HashMap) r7     // Catch:{ zzgf -> 0x00d3 }
            boolean r9 = r7.isEmpty()     // Catch:{ all -> 0x0110 }
            if (r9 != 0) goto L_0x00bb
            java.util.Set r9 = r7.keySet()     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Boolean> r1 = r6.zzc     // Catch:{ all -> 0x0110 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0110 }
            r9.removeAll(r1)     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Integer> r1 = r6.zzd     // Catch:{ all -> 0x0110 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0110 }
            r9.removeAll(r1)     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Long> r1 = r6.zze     // Catch:{ all -> 0x0110 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0110 }
            r9.removeAll(r1)     // Catch:{ all -> 0x0110 }
            java.util.HashMap<java.lang.String, java.lang.Float> r1 = r6.zzf     // Catch:{ all -> 0x0110 }
            java.util.Set r1 = r1.keySet()     // Catch:{ all -> 0x0110 }
            r9.removeAll(r1)     // Catch:{ all -> 0x0110 }
        L_0x00bb:
            boolean r9 = r7.isEmpty()     // Catch:{ all -> 0x0110 }
            if (r9 != 0) goto L_0x00d1
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r6.zzb     // Catch:{ all -> 0x0110 }
            boolean r9 = r9.isEmpty()     // Catch:{ all -> 0x0110 }
            if (r9 == 0) goto L_0x00cc
            r6.zzb = r7     // Catch:{ all -> 0x0110 }
            goto L_0x00d1
        L_0x00cc:
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r6.zzb     // Catch:{ all -> 0x0110 }
            r9.putAll(r7)     // Catch:{ all -> 0x0110 }
        L_0x00d1:
            r6.zzh = r0     // Catch:{ all -> 0x0110 }
        L_0x00d3:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r6.zzb     // Catch:{ all -> 0x0110 }
            boolean r7 = r7.containsKey(r8)     // Catch:{ all -> 0x0110 }
            if (r7 == 0) goto L_0x00e8
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r6.zzb     // Catch:{ all -> 0x0110 }
            java.lang.Object r7 = r7.get(r8)     // Catch:{ all -> 0x0110 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x0110 }
            if (r7 == 0) goto L_0x00e6
            r2 = r7
        L_0x00e6:
            monitor-exit(r6)     // Catch:{ all -> 0x0110 }
            return r2
        L_0x00e8:
            monitor-exit(r6)     // Catch:{ all -> 0x0110 }
            return r2
        L_0x00ea:
            int r1 = r1 + 1
            goto L_0x0070
        L_0x00ed:
            monitor-exit(r6)     // Catch:{ all -> 0x0110 }
            com.google.android.gms.internal.measurement.zzgg r0 = r6.zzj     // Catch:{ zzgf -> 0x010f }
            java.lang.String r7 = r0.zza(r7, r8)     // Catch:{ zzgf -> 0x010f }
            if (r7 == 0) goto L_0x00fd
            boolean r0 = r7.equals(r2)
            if (r0 == 0) goto L_0x00fd
            r7 = r2
        L_0x00fd:
            monitor-enter(r6)
            java.lang.Object r0 = r6.zzg     // Catch:{ all -> 0x010c }
            if (r9 != r0) goto L_0x0107
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r6.zzb     // Catch:{ all -> 0x010c }
            r9.put(r8, r7)     // Catch:{ all -> 0x010c }
        L_0x0107:
            monitor-exit(r6)     // Catch:{ all -> 0x010c }
            if (r7 == 0) goto L_0x010b
            return r7
        L_0x010b:
            return r2
        L_0x010c:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x010c }
            throw r7
        L_0x010f:
            return r2
        L_0x0110:
            r7 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0110 }
            throw r7
        L_0x0113:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "ContentResolver needed with GservicesDelegateSupplier.init()"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfz.zza(android.content.ContentResolver, java.lang.String, java.lang.String):java.lang.String");
    }
}
