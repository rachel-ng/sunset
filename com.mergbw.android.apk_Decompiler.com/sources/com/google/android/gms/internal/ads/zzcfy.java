package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.ads.internal.util.client.zzf;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.sessions.settings.RemoteSettings;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcfy extends zzcfp implements zzcdu {
    public static final /* synthetic */ int zzd = 0;
    private zzcdv zze;
    private String zzf;
    private boolean zzg;
    private boolean zzh;
    private zzcfh zzi;
    private long zzj;
    private long zzk;

    public zzcfy(zzcee zzcee, zzced zzced) {
        super(zzcee);
        zzcgq zzcgq = new zzcgq(zzcee.getContext(), zzced, (zzcee) this.zzc.get(), (Integer) null);
        zzm.zzi("ExoPlayerAdapter initialized.");
        this.zze = zzcgq;
        zzcgq.zzL(this);
    }

    protected static final String zzc(String str) {
        return "cache:".concat(String.valueOf(zzf.zzf(str)));
    }

    private static String zzd(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        return str + RemoteSettings.FORWARD_SLASH_STRING + canonicalName + ":" + message;
    }

    private final void zzx(long j) {
        zzt.zza.postDelayed(new zzcfx(this), j);
    }

    public final void release() {
        zzcdv zzcdv = this.zze;
        if (zzcdv != null) {
            zzcdv.zzL((zzcdu) null);
            this.zze.zzH();
        }
    }

    public final void zzD(int i, int i2) {
    }

    public final zzcdv zza() {
        synchronized (this) {
            this.zzh = true;
            notify();
        }
        this.zze.zzL((zzcdu) null);
        zzcdv zzcdv = this.zze;
        this.zze = null;
        return zzcdv;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v24, resolved type: com.google.android.gms.internal.ads.zzcfy} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v30, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v16, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v17, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v21, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v32, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v33, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v22, resolved type: long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v34, resolved type: com.google.android.gms.internal.ads.zzcfy} */
    /* JADX WARNING: type inference failed for: r3v11, types: [boolean] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0059, code lost:
        r3 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x010d, code lost:
        r3.zzx(((java.lang.Long) com.google.android.gms.ads.internal.client.zzba.zzc().zza(com.google.android.gms.internal.ads.zzbep.zzy)).longValue());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0120, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zzb() {
        /*
            r31 = this;
            r15 = r31
            java.lang.String r0 = "Timeout reached. Limit: "
            java.lang.String r1 = r15.zzf
            java.lang.String r13 = zzc(r1)
            java.lang.String r17 = "error"
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzx     // Catch:{ Exception -> 0x0165 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0165 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ Exception -> 0x0165 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x0165 }
            long r1 = r1.longValue()     // Catch:{ Exception -> 0x0165 }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r3
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzw     // Catch:{ Exception -> 0x0165 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0165 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ Exception -> 0x0165 }
            java.lang.Integer r3 = (java.lang.Integer) r3     // Catch:{ Exception -> 0x0165 }
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x0165 }
            long r11 = (long) r3     // Catch:{ Exception -> 0x0165 }
            com.google.android.gms.internal.ads.zzbeg r3 = com.google.android.gms.internal.ads.zzbep.zzbR     // Catch:{ Exception -> 0x0165 }
            com.google.android.gms.internal.ads.zzben r4 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x0165 }
            java.lang.Object r3 = r4.zza(r3)     // Catch:{ Exception -> 0x0165 }
            java.lang.Boolean r3 = (java.lang.Boolean) r3     // Catch:{ Exception -> 0x0165 }
            boolean r3 = r3.booleanValue()     // Catch:{ Exception -> 0x0165 }
            monitor-enter(r31)     // Catch:{ Exception -> 0x0165 }
            com.google.android.gms.common.util.Clock r4 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ all -> 0x015e }
            long r4 = r4.currentTimeMillis()     // Catch:{ all -> 0x015e }
            long r6 = r15.zzj     // Catch:{ all -> 0x015e }
            long r4 = r4 - r6
            int r4 = (r4 > r1 ? 1 : (r4 == r1 ? 0 : -1))
            if (r4 > 0) goto L_0x013d
            boolean r0 = r15.zzg     // Catch:{ all -> 0x015e }
            if (r0 != 0) goto L_0x012d
            boolean r0 = r15.zzh     // Catch:{ all -> 0x015e }
            if (r0 == 0) goto L_0x005c
            monitor-exit(r31)     // Catch:{ all -> 0x015e }
            r3 = r15
            goto L_0x019e
        L_0x005c:
            com.google.android.gms.internal.ads.zzcdv r0 = r15.zze     // Catch:{ all -> 0x015e }
            boolean r0 = r0.zzV()     // Catch:{ all -> 0x015e }
            if (r0 == 0) goto L_0x0121
            com.google.android.gms.internal.ads.zzcdv r0 = r15.zze     // Catch:{ all -> 0x015e }
            long r9 = r0.zzz()     // Catch:{ all -> 0x015e }
            r18 = 0
            int r0 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r0 <= 0) goto L_0x010a
            com.google.android.gms.internal.ads.zzcdv r0 = r15.zze     // Catch:{ all -> 0x015e }
            long r6 = r0.zzv()     // Catch:{ all -> 0x015e }
            long r0 = r15.zzk     // Catch:{ all -> 0x015e }
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 == 0) goto L_0x00dd
            int r0 = (r6 > r18 ? 1 : (r6 == r18 ? 0 : -1))
            if (r0 <= 0) goto L_0x0082
            r0 = 1
            goto L_0x0083
        L_0x0082:
            r0 = 0
        L_0x0083:
            r8 = r0
            java.lang.String r2 = r15.zzf     // Catch:{ all -> 0x00d8 }
            r0 = -1
            if (r3 == 0) goto L_0x0093
            com.google.android.gms.internal.ads.zzcdv r4 = r15.zze     // Catch:{ all -> 0x015e }
            long r4 = r4.zzA()     // Catch:{ all -> 0x015e }
            r20 = r4
            goto L_0x0095
        L_0x0093:
            r20 = r0
        L_0x0095:
            if (r3 == 0) goto L_0x00a0
            com.google.android.gms.internal.ads.zzcdv r4 = r15.zze     // Catch:{ all -> 0x015e }
            long r4 = r4.zzx()     // Catch:{ all -> 0x015e }
            r22 = r4
            goto L_0x00a2
        L_0x00a0:
            r22 = r0
        L_0x00a2:
            if (r3 == 0) goto L_0x00aa
            com.google.android.gms.internal.ads.zzcdv r0 = r15.zze     // Catch:{ all -> 0x015e }
            long r0 = r0.zzB()     // Catch:{ all -> 0x015e }
        L_0x00aa:
            r24 = r0
            int r0 = com.google.android.gms.internal.ads.zzcdv.zzs()     // Catch:{ all -> 0x00d8 }
            int r16 = com.google.android.gms.internal.ads.zzcdv.zzu()     // Catch:{ all -> 0x00d8 }
            r1 = r31
            r3 = r13
            r4 = r6
            r26 = r6
            r6 = r9
            r28 = r9
            r9 = r20
            r20 = r11
            r11 = r22
            r30 = r13
            r13 = r24
            r15 = r0
            r1.zzo(r2, r3, r4, r6, r8, r9, r11, r13, r15, r16)     // Catch:{ all -> 0x00d4 }
            r3 = r31
            r0 = r26
            r3.zzk = r0     // Catch:{ all -> 0x00f2 }
            r4 = r28
            goto L_0x00e4
        L_0x00d4:
            r0 = move-exception
            r3 = r31
            goto L_0x00f3
        L_0x00d8:
            r0 = move-exception
            r30 = r13
            r3 = r15
            goto L_0x00f3
        L_0x00dd:
            r0 = r6
            r20 = r11
            r30 = r13
            r3 = r15
            r4 = r9
        L_0x00e4:
            int r2 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r2 < 0) goto L_0x00f7
            java.lang.String r0 = r3.zzf     // Catch:{ all -> 0x00f2 }
            r6 = r30
            r3.zzj(r0, r6, r4)     // Catch:{ all -> 0x015c }
            monitor-exit(r31)     // Catch:{ all -> 0x015c }
            goto L_0x019e
        L_0x00f2:
            r0 = move-exception
        L_0x00f3:
            r6 = r30
            goto L_0x0161
        L_0x00f7:
            r6 = r30
            com.google.android.gms.internal.ads.zzcdv r2 = r3.zze     // Catch:{ all -> 0x015c }
            long r4 = r2.zzw()     // Catch:{ all -> 0x015c }
            int r2 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r2 < 0) goto L_0x010c
            int r0 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r0 <= 0) goto L_0x010c
            monitor-exit(r31)     // Catch:{ all -> 0x015c }
            goto L_0x019e
        L_0x010a:
            r6 = r13
            r3 = r15
        L_0x010c:
            monitor-exit(r31)     // Catch:{ all -> 0x015c }
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzy
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r0 = r1.zza(r0)
            java.lang.Long r0 = (java.lang.Long) r0
            long r0 = r0.longValue()
            r3.zzx(r0)
            return
        L_0x0121:
            r6 = r13
            r3 = r15
            java.lang.String r1 = "exoPlayerReleased"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0139 }
            java.lang.String r2 = "ExoPlayer was released during preloading."
            r0.<init>(r2)     // Catch:{ all -> 0x0139 }
            throw r0     // Catch:{ all -> 0x0139 }
        L_0x012d:
            r6 = r13
            r3 = r15
            java.lang.String r1 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0139 }
            java.lang.String r2 = "Abort requested before buffering finished. "
            r0.<init>(r2)     // Catch:{ all -> 0x0139 }
            throw r0     // Catch:{ all -> 0x0139 }
        L_0x0139:
            r0 = move-exception
            r17 = r1
            goto L_0x0161
        L_0x013d:
            r6 = r13
            r3 = r15
            java.lang.String r4 = "downloadTimeout"
            java.io.IOException r5 = new java.io.IOException     // Catch:{ all -> 0x0158 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x0158 }
            r7.<init>(r0)     // Catch:{ all -> 0x0158 }
            r7.append(r1)     // Catch:{ all -> 0x0158 }
            java.lang.String r0 = " ms"
            r7.append(r0)     // Catch:{ all -> 0x0158 }
            java.lang.String r0 = r7.toString()     // Catch:{ all -> 0x0158 }
            r5.<init>(r0)     // Catch:{ all -> 0x0158 }
            throw r5     // Catch:{ all -> 0x0158 }
        L_0x0158:
            r0 = move-exception
            r17 = r4
            goto L_0x0161
        L_0x015c:
            r0 = move-exception
            goto L_0x0161
        L_0x015e:
            r0 = move-exception
            r6 = r13
            r3 = r15
        L_0x0161:
            monitor-exit(r31)     // Catch:{ all -> 0x015c }
            throw r0     // Catch:{ Exception -> 0x0163 }
        L_0x0163:
            r0 = move-exception
            goto L_0x0168
        L_0x0165:
            r0 = move-exception
            r6 = r13
            r3 = r15
        L_0x0168:
            r1 = r17
            java.lang.String r2 = r3.zzf
            java.lang.String r4 = r0.getMessage()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r7 = "Failed to preload url "
            r5.<init>(r7)
            r5.append(r2)
            java.lang.String r2 = " Exception: "
            r5.append(r2)
            r5.append(r4)
            java.lang.String r2 = r5.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r2)
            java.lang.String r2 = "VideoStreamExoPlayerCache.preload"
            com.google.android.gms.internal.ads.zzcby r4 = com.google.android.gms.ads.internal.zzu.zzo()
            r4.zzv(r0, r2)
            r31.release()
            java.lang.String r0 = zzd(r1, r0)
            java.lang.String r2 = r3.zzf
            r3.zzg(r2, r6, r1, r0)
        L_0x019e:
            com.google.android.gms.internal.ads.zzcfi r0 = com.google.android.gms.ads.internal.zzu.zzy()
            com.google.android.gms.internal.ads.zzcfh r1 = r3.zzi
            r0.zzc(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfy.zzb():void");
    }

    public final void zzf() {
        synchronized (this) {
            this.zzg = true;
            notify();
            release();
        }
        String str = this.zzf;
        if (str != null) {
            zzg(this.zzf, zzc(str), "externalAbort", "Programmatic precache abort.");
        }
    }

    public final void zzi(boolean z, long j) {
        zzcee zzcee = (zzcee) this.zzc.get();
        if (zzcee != null) {
            zzcci.zze.execute(new zzcfw(zzcee, z, j));
        }
    }

    public final void zzk(String str, Exception exc) {
        zzm.zzk("Precache error", exc);
        zzu.zzo().zzv(exc, "VideoStreamExoPlayerCache.onError");
    }

    public final void zzl(String str, Exception exc) {
        zzm.zzk("Precache exception", exc);
        zzu.zzo().zzv(exc, "VideoStreamExoPlayerCache.onException");
    }

    public final void zzm(int i) {
    }

    public final void zzp(int i) {
        this.zze.zzJ(i);
    }

    public final void zzq(int i) {
        this.zze.zzK(i);
    }

    public final void zzr(int i) {
        this.zze.zzM(i);
    }

    public final void zzs(int i) {
        this.zze.zzN(i);
    }

    public final boolean zzt(String str) {
        return zzu(str, new String[]{str});
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x009e, code lost:
        r5 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0136, code lost:
        r5 = r45;
        r6 = r46;
        r7 = r44;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r5.zzj(r6, r7, r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzu(java.lang.String r46, java.lang.String[] r47) {
        /*
            r45 = this;
            r15 = r45
            r13 = r46
            r0 = r47
            r15.zzf = r13
            java.lang.String r17 = "error"
            java.lang.String r14 = zzc(r46)
            r18 = 0
            int r1 = r0.length     // Catch:{ Exception -> 0x01c7 }
            android.net.Uri[] r1 = new android.net.Uri[r1]     // Catch:{ Exception -> 0x01c7 }
            r2 = r18
        L_0x0015:
            int r3 = r0.length     // Catch:{ Exception -> 0x01c7 }
            if (r2 >= r3) goto L_0x0023
            r3 = r0[r2]     // Catch:{ Exception -> 0x01c7 }
            android.net.Uri r3 = android.net.Uri.parse(r3)     // Catch:{ Exception -> 0x01c7 }
            r1[r2] = r3     // Catch:{ Exception -> 0x01c7 }
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0023:
            com.google.android.gms.internal.ads.zzcdv r0 = r15.zze     // Catch:{ Exception -> 0x01c7 }
            java.lang.String r2 = r15.zzb     // Catch:{ Exception -> 0x01c7 }
            r0.zzF(r1, r2)     // Catch:{ Exception -> 0x01c7 }
            java.lang.ref.WeakReference r0 = r15.zzc     // Catch:{ Exception -> 0x01c7 }
            java.lang.Object r0 = r0.get()     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzcee r0 = (com.google.android.gms.internal.ads.zzcee) r0     // Catch:{ Exception -> 0x01c7 }
            if (r0 == 0) goto L_0x0037
            r0.zzt(r14, r15)     // Catch:{ Exception -> 0x01c7 }
        L_0x0037:
            com.google.android.gms.common.util.Clock r0 = com.google.android.gms.ads.internal.zzu.zzB()     // Catch:{ Exception -> 0x01c7 }
            long r19 = r0.currentTimeMillis()     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzy     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x01c7 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ Exception -> 0x01c7 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x01c7 }
            long r11 = r1.longValue()     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzx     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x01c7 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ Exception -> 0x01c7 }
            java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ Exception -> 0x01c7 }
            long r1 = r1.longValue()     // Catch:{ Exception -> 0x01c7 }
            r3 = 1000(0x3e8, double:4.94E-321)
            long r9 = r1 * r3
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzw     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x01c7 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ Exception -> 0x01c7 }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ Exception -> 0x01c7 }
            int r1 = r1.intValue()     // Catch:{ Exception -> 0x01c7 }
            long r6 = (long) r1     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzbR     // Catch:{ Exception -> 0x01c7 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ Exception -> 0x01c7 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ Exception -> 0x01c7 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ Exception -> 0x01c7 }
            boolean r21 = r1.booleanValue()     // Catch:{ Exception -> 0x01c7 }
            r22 = -1
            r1 = r22
        L_0x0088:
            monitor-enter(r45)     // Catch:{ Exception -> 0x01c7 }
            long r3 = r0.currentTimeMillis()     // Catch:{ all -> 0x01bf }
            long r3 = r3 - r19
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x0194
            boolean r3 = r15.zzg     // Catch:{ all -> 0x01bf }
            if (r3 != 0) goto L_0x0187
            boolean r3 = r15.zzh     // Catch:{ all -> 0x01bf }
            r24 = 1
            if (r3 == 0) goto L_0x00a1
            monitor-exit(r45)     // Catch:{ all -> 0x01bf }
            r5 = r15
            goto L_0x0156
        L_0x00a1:
            com.google.android.gms.internal.ads.zzcdv r3 = r15.zze     // Catch:{ all -> 0x01bf }
            boolean r3 = r3.zzV()     // Catch:{ all -> 0x01bf }
            if (r3 == 0) goto L_0x017a
            com.google.android.gms.internal.ads.zzcdv r3 = r15.zze     // Catch:{ all -> 0x01bf }
            long r4 = r3.zzz()     // Catch:{ all -> 0x01bf }
            r25 = 0
            int r3 = (r4 > r25 ? 1 : (r4 == r25 ? 0 : -1))
            if (r3 <= 0) goto L_0x015a
            com.google.android.gms.internal.ads.zzcdv r3 = r15.zze     // Catch:{ all -> 0x01bf }
            long r27 = r3.zzv()     // Catch:{ all -> 0x01bf }
            int r3 = (r27 > r1 ? 1 : (r27 == r1 ? 0 : -1))
            if (r3 == 0) goto L_0x0129
            int r1 = (r27 > r25 ? 1 : (r27 == r25 ? 0 : -1))
            if (r1 <= 0) goto L_0x00c6
            r8 = r24
            goto L_0x00c8
        L_0x00c6:
            r8 = r18
        L_0x00c8:
            if (r21 == 0) goto L_0x00d3
            com.google.android.gms.internal.ads.zzcdv r1 = r15.zze     // Catch:{ all -> 0x01bf }
            long r1 = r1.zzA()     // Catch:{ all -> 0x01bf }
            r29 = r1
            goto L_0x00d5
        L_0x00d3:
            r29 = r22
        L_0x00d5:
            if (r21 == 0) goto L_0x00e0
            com.google.android.gms.internal.ads.zzcdv r1 = r15.zze     // Catch:{ all -> 0x01bf }
            long r1 = r1.zzx()     // Catch:{ all -> 0x01bf }
            r31 = r1
            goto L_0x00e2
        L_0x00e0:
            r31 = r22
        L_0x00e2:
            if (r21 == 0) goto L_0x00ed
            com.google.android.gms.internal.ads.zzcdv r1 = r15.zze     // Catch:{ all -> 0x01bf }
            long r1 = r1.zzB()     // Catch:{ all -> 0x01bf }
            r33 = r1
            goto L_0x00ef
        L_0x00ed:
            r33 = r22
        L_0x00ef:
            int r16 = com.google.android.gms.internal.ads.zzcdv.zzs()     // Catch:{ all -> 0x011e }
            int r35 = com.google.android.gms.internal.ads.zzcdv.zzu()     // Catch:{ all -> 0x011e }
            r1 = r45
            r2 = r46
            r3 = r14
            r36 = r4
            r4 = r27
            r38 = r6
            r6 = r36
            r40 = r9
            r9 = r29
            r42 = r11
            r11 = r31
            r44 = r14
            r13 = r33
            r15 = r16
            r16 = r35
            r1.zzo(r2, r3, r4, r6, r8, r9, r11, r13, r15, r16)     // Catch:{ all -> 0x011c }
            r1 = r27
            r3 = r36
            goto L_0x0132
        L_0x011c:
            r0 = move-exception
            goto L_0x0121
        L_0x011e:
            r0 = move-exception
            r44 = r14
        L_0x0121:
            r5 = r45
            r6 = r46
            r7 = r44
            goto L_0x01c3
        L_0x0129:
            r38 = r6
            r40 = r9
            r42 = r11
            r44 = r14
            r3 = r4
        L_0x0132:
            int r5 = (r27 > r3 ? 1 : (r27 == r3 ? 0 : -1))
            if (r5 < 0) goto L_0x0141
            r5 = r45
            r6 = r46
            r7 = r44
            r5.zzj(r6, r7, r3)     // Catch:{ all -> 0x01bd }
            monitor-exit(r45)     // Catch:{ all -> 0x01bd }
            goto L_0x0156
        L_0x0141:
            r5 = r45
            r6 = r46
            r7 = r44
            com.google.android.gms.internal.ads.zzcdv r3 = r5.zze     // Catch:{ all -> 0x01bd }
            long r3 = r3.zzw()     // Catch:{ all -> 0x01bd }
            int r3 = (r3 > r38 ? 1 : (r3 == r38 ? 0 : -1))
            if (r3 < 0) goto L_0x0157
            int r3 = (r27 > r25 ? 1 : (r27 == r25 ? 0 : -1))
            if (r3 <= 0) goto L_0x0157
            monitor-exit(r45)     // Catch:{ all -> 0x01bd }
        L_0x0156:
            return r24
        L_0x0157:
            r3 = r42
            goto L_0x0162
        L_0x015a:
            r38 = r6
            r40 = r9
            r6 = r13
            r7 = r14
            r5 = r15
            r3 = r11
        L_0x0162:
            r5.wait(r3)     // Catch:{ InterruptedException -> 0x0170 }
            monitor-exit(r45)     // Catch:{ all -> 0x01bd }
            r11 = r3
            r15 = r5
            r13 = r6
            r14 = r7
            r6 = r38
            r9 = r40
            goto L_0x0088
        L_0x0170:
            java.lang.String r1 = "interrupted"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = "Wait interrupted."
            r0.<init>(r2)     // Catch:{ all -> 0x01b9 }
            throw r0     // Catch:{ all -> 0x01b9 }
        L_0x017a:
            r6 = r13
            r7 = r14
            r5 = r15
            java.lang.String r1 = "exoPlayerReleased"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = "ExoPlayer was released during preloading."
            r0.<init>(r2)     // Catch:{ all -> 0x01b9 }
            throw r0     // Catch:{ all -> 0x01b9 }
        L_0x0187:
            r6 = r13
            r7 = r14
            r5 = r15
            java.lang.String r1 = "externalAbort"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = "Abort requested before buffering finished. "
            r0.<init>(r2)     // Catch:{ all -> 0x01b9 }
            throw r0     // Catch:{ all -> 0x01b9 }
        L_0x0194:
            r40 = r9
            r6 = r13
            r7 = r14
            r5 = r15
            java.lang.String r1 = "downloadTimeout"
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01b9 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x01b9 }
            r2.<init>()     // Catch:{ all -> 0x01b9 }
            java.lang.String r3 = "Timeout reached. Limit: "
            r2.append(r3)     // Catch:{ all -> 0x01b9 }
            r3 = r40
            r2.append(r3)     // Catch:{ all -> 0x01b9 }
            java.lang.String r3 = " ms"
            r2.append(r3)     // Catch:{ all -> 0x01b9 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x01b9 }
            r0.<init>(r2)     // Catch:{ all -> 0x01b9 }
            throw r0     // Catch:{ all -> 0x01b9 }
        L_0x01b9:
            r0 = move-exception
            r17 = r1
            goto L_0x01c3
        L_0x01bd:
            r0 = move-exception
            goto L_0x01c3
        L_0x01bf:
            r0 = move-exception
            r6 = r13
            r7 = r14
            r5 = r15
        L_0x01c3:
            monitor-exit(r45)     // Catch:{ all -> 0x01bd }
            throw r0     // Catch:{ Exception -> 0x01c5 }
        L_0x01c5:
            r0 = move-exception
            goto L_0x01cb
        L_0x01c7:
            r0 = move-exception
            r6 = r13
            r7 = r14
            r5 = r15
        L_0x01cb:
            r1 = r17
            java.lang.String r2 = r0.getMessage()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to preload url "
            r3.<init>(r4)
            r3.append(r6)
            java.lang.String r4 = " Exception: "
            r3.append(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzj(r2)
            java.lang.String r2 = "VideoStreamExoPlayerCache.preload"
            com.google.android.gms.internal.ads.zzcby r3 = com.google.android.gms.ads.internal.zzu.zzo()
            r3.zzv(r0, r2)
            r45.release()
            java.lang.String r0 = zzd(r1, r0)
            r5.zzg(r6, r7, r1, r0)
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcfy.zzu(java.lang.String, java.lang.String[]):boolean");
    }

    public final void zzv() {
        zzm.zzj("Precache onRenderedFirstFrame");
    }

    public final boolean zzw(String str, String[] strArr, zzcfh zzcfh) {
        this.zzf = str;
        this.zzi = zzcfh;
        String zzc = zzc(str);
        try {
            Uri[] uriArr = new Uri[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                uriArr[i] = Uri.parse(strArr[i]);
            }
            this.zze.zzF(uriArr, this.zzb);
            zzcee zzcee = (zzcee) this.zzc.get();
            if (zzcee != null) {
                zzcee.zzt(zzc, this);
            }
            this.zzj = zzu.zzB().currentTimeMillis();
            this.zzk = -1;
            zzx(0);
            return true;
        } catch (Exception e) {
            zzm.zzj("Failed to preload url " + str + " Exception: " + e.getMessage());
            zzu.zzo().zzv(e, "VideoStreamExoPlayerCache.preload");
            release();
            zzg(str, zzc, "error", zzd("error", e));
            return false;
        }
    }
}
