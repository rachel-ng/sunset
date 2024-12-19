package com.google.android.gms.internal.ads;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaye {
    private static final String zzd = "zzaye";
    protected final Context zza;
    protected boolean zzb;
    protected boolean zzc;
    private ExecutorService zze;
    private DexClassLoader zzf;
    private zzaxj zzg;
    private byte[] zzh;
    private volatile AdvertisingIdClient zzi = null;
    private volatile boolean zzj;
    private Future zzk;
    private final boolean zzl;
    /* access modifiers changed from: private */
    public volatile zzaus zzm;
    private Future zzn;
    private zzawy zzo;
    private final Map zzp;
    private boolean zzq;
    private zzaxx zzr;

    private zzaye(Context context) {
        boolean z = false;
        this.zzj = false;
        this.zzk = null;
        this.zzm = null;
        this.zzn = null;
        this.zzb = false;
        this.zzc = false;
        this.zzq = false;
        Context applicationContext = context.getApplicationContext();
        this.zzl = applicationContext != null ? true : z;
        context = applicationContext != null ? applicationContext : context;
        this.zza = context;
        this.zzp = new HashMap();
        if (this.zzr == null) {
            this.zzr = new zzaxx(context);
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(17:1|2|(1:4)|5|6|7|8|(1:10)(1:11)|12|(1:14)(1:15)|16|17|18|(2:20|(1:22)(2:23|24))|25|26|(3:27|28|(17:30|(2:32|33)|34|35|36|37|(2:39|(1:41)(2:42|43))|44|(3:46|(1:48)|49)|50|51|52|53|54|55|56|89)(3:78|79|80))) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058 A[Catch:{ zzaxu -> 0x0176 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0086 A[Catch:{ IllegalArgumentException -> 0x0168, zzaxi -> 0x016f }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0162 A[SYNTHETIC, Splitter:B:78:0x0162] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zzaye zzg(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12) {
        /*
            java.lang.String r10 = "%s/%s.dex"
            java.lang.String r11 = "1708042440713"
            com.google.android.gms.internal.ads.zzaye r0 = new com.google.android.gms.internal.ads.zzaye
            r0.<init>(r9)
            com.google.android.gms.internal.ads.zzaya r9 = new com.google.android.gms.internal.ads.zzaya     // Catch:{ zzaxu -> 0x0176 }
            r9.<init>()     // Catch:{ zzaxu -> 0x0176 }
            java.util.concurrent.ExecutorService r9 = java.util.concurrent.Executors.newCachedThreadPool(r9)     // Catch:{ zzaxu -> 0x0176 }
            r0.zze = r9     // Catch:{ zzaxu -> 0x0176 }
            r0.zzj = r12     // Catch:{ zzaxu -> 0x0176 }
            if (r12 == 0) goto L_0x0025
            java.util.concurrent.ExecutorService r9 = r0.zze     // Catch:{ zzaxu -> 0x0176 }
            com.google.android.gms.internal.ads.zzayb r12 = new com.google.android.gms.internal.ads.zzayb     // Catch:{ zzaxu -> 0x0176 }
            r12.<init>(r0)     // Catch:{ zzaxu -> 0x0176 }
            java.util.concurrent.Future r9 = r9.submit(r12)     // Catch:{ zzaxu -> 0x0176 }
            r0.zzk = r9     // Catch:{ zzaxu -> 0x0176 }
        L_0x0025:
            java.util.concurrent.ExecutorService r9 = r0.zze     // Catch:{ zzaxu -> 0x0176 }
            com.google.android.gms.internal.ads.zzayd r12 = new com.google.android.gms.internal.ads.zzayd     // Catch:{ zzaxu -> 0x0176 }
            r12.<init>(r0)     // Catch:{ zzaxu -> 0x0176 }
            r9.execute(r12)     // Catch:{ zzaxu -> 0x0176 }
            r9 = 1
            r12 = 0
            com.google.android.gms.common.GoogleApiAvailabilityLight r1 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()     // Catch:{ all -> 0x004f }
            android.content.Context r2 = r0.zza     // Catch:{ all -> 0x004f }
            int r2 = r1.getApkVersion(r2)     // Catch:{ all -> 0x004f }
            if (r2 <= 0) goto L_0x003f
            r2 = r9
            goto L_0x0040
        L_0x003f:
            r2 = r12
        L_0x0040:
            r0.zzb = r2     // Catch:{ all -> 0x004f }
            android.content.Context r2 = r0.zza     // Catch:{ all -> 0x004f }
            int r1 = r1.isGooglePlayServicesAvailable(r2)     // Catch:{ all -> 0x004f }
            if (r1 != 0) goto L_0x004c
            r1 = r9
            goto L_0x004d
        L_0x004c:
            r1 = r12
        L_0x004d:
            r0.zzc = r1     // Catch:{ all -> 0x004f }
        L_0x004f:
            r0.zzo(r12, r9)     // Catch:{ zzaxu -> 0x0176 }
            boolean r1 = com.google.android.gms.internal.ads.zzayh.zzc()     // Catch:{ zzaxu -> 0x0176 }
            if (r1 == 0) goto L_0x0073
            com.google.android.gms.internal.ads.zzbeg r1 = com.google.android.gms.internal.ads.zzbep.zzdm     // Catch:{ zzaxu -> 0x0176 }
            com.google.android.gms.internal.ads.zzben r2 = com.google.android.gms.ads.internal.client.zzba.zzc()     // Catch:{ zzaxu -> 0x0176 }
            java.lang.Object r1 = r2.zza(r1)     // Catch:{ zzaxu -> 0x0176 }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ zzaxu -> 0x0176 }
            boolean r1 = r1.booleanValue()     // Catch:{ zzaxu -> 0x0176 }
            if (r1 != 0) goto L_0x006b
            goto L_0x0073
        L_0x006b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ zzaxu -> 0x0176 }
            java.lang.String r10 = "Task Context initialization must not be called from the UI thread."
            r9.<init>(r10)     // Catch:{ zzaxu -> 0x0176 }
            throw r9     // Catch:{ zzaxu -> 0x0176 }
        L_0x0073:
            com.google.android.gms.internal.ads.zzaxj r1 = new com.google.android.gms.internal.ads.zzaxj     // Catch:{ zzaxu -> 0x0176 }
            r2 = 0
            r1.<init>(r2)     // Catch:{ zzaxu -> 0x0176 }
            r0.zzg = r1     // Catch:{ zzaxu -> 0x0176 }
            java.lang.String r3 = "M2RhhRYJhjrQUa7n9jg23IBcTQvCkUFLA/9ZbQYvHFo="
            byte[] r3 = com.google.android.gms.internal.ads.zzavo.zzb(r3, r12)     // Catch:{ IllegalArgumentException -> 0x0168 }
            int r4 = r3.length     // Catch:{ IllegalArgumentException -> 0x0168 }
            r5 = 32
            if (r4 != r5) goto L_0x0162
            r4 = 4
            r5 = 16
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3, r4, r5)     // Catch:{ IllegalArgumentException -> 0x0168 }
            byte[] r4 = new byte[r5]     // Catch:{ IllegalArgumentException -> 0x0168 }
            r3.get(r4)     // Catch:{ IllegalArgumentException -> 0x0168 }
            r3 = r12
        L_0x0093:
            if (r3 >= r5) goto L_0x009f
            byte r6 = r4[r3]     // Catch:{ IllegalArgumentException -> 0x0168 }
            r6 = r6 ^ 68
            byte r6 = (byte) r6     // Catch:{ IllegalArgumentException -> 0x0168 }
            r4[r3] = r6     // Catch:{ IllegalArgumentException -> 0x0168 }
            int r3 = r3 + 1
            goto L_0x0093
        L_0x009f:
            r0.zzh = r4     // Catch:{ zzaxi -> 0x016f }
            android.content.Context r1 = r0.zza     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.io.File r1 = r1.getCacheDir()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            if (r1 != 0) goto L_0x00ba
            android.content.Context r1 = r0.zza     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.String r3 = "dex"
            java.io.File r1 = r1.getDir(r3, r12)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            if (r1 == 0) goto L_0x00b4
            goto L_0x00ba
        L_0x00b4:
            com.google.android.gms.internal.ads.zzaxu r9 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r9.<init>()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            throw r9     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
        L_0x00ba:
            java.lang.String r3 = "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"
            java.io.File r4 = new java.io.File     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.String r5 = "%s/%s.jar"
            r6 = 2
            java.lang.Object[] r7 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r7[r12] = r1     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r7[r9] = r11     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.String r5 = java.lang.String.format(r5, r7)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            boolean r5 = r4.exists()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            if (r5 != 0) goto L_0x00f4
            com.google.android.gms.internal.ads.zzaxj r5 = r0.zzg     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            byte[] r7 = r0.zzh     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            byte[] r3 = r5.zzb(r7, r3)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r4.createNewFile()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r5.<init>(r4)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r8 = 33
            if (r7 < r8) goto L_0x00ed
            r4.setReadOnly()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
        L_0x00ed:
            int r7 = r3.length     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r5.write(r3, r12, r7)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r5.close()     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
        L_0x00f4:
            r0.zzx(r1, r11)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            dalvik.system.DexClassLoader r3 = new dalvik.system.DexClassLoader     // Catch:{ SecurityException -> 0x012b }
            java.lang.String r5 = r4.getAbsolutePath()     // Catch:{ SecurityException -> 0x012b }
            java.lang.String r7 = r1.getAbsolutePath()     // Catch:{ SecurityException -> 0x012b }
            android.content.Context r8 = r0.zza     // Catch:{ SecurityException -> 0x012b }
            java.lang.ClassLoader r8 = r8.getClassLoader()     // Catch:{ SecurityException -> 0x012b }
            r3.<init>(r5, r7, r2, r8)     // Catch:{ SecurityException -> 0x012b }
            r0.zzf = r3     // Catch:{ SecurityException -> 0x012b }
            zzy(r4)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r0.zzw(r1, r11)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.Object[] r2 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r2[r12] = r1     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r2[r9] = r11     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.String r10 = java.lang.String.format(r10, r2)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            zzz(r10)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            com.google.android.gms.internal.ads.zzawy r10 = new com.google.android.gms.internal.ads.zzawy     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r0)     // Catch:{ zzaxu -> 0x0176 }
            r0.zzo = r10     // Catch:{ zzaxu -> 0x0176 }
            r0.zzq = r9     // Catch:{ zzaxu -> 0x0176 }
            goto L_0x0176
        L_0x0129:
            r2 = move-exception
            goto L_0x0132
        L_0x012b:
            r2 = move-exception
            com.google.android.gms.internal.ads.zzaxu r3 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ all -> 0x0129 }
            r3.<init>(r2)     // Catch:{ all -> 0x0129 }
            throw r3     // Catch:{ all -> 0x0129 }
        L_0x0132:
            zzy(r4)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r0.zzw(r1, r11)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.Object[] r3 = new java.lang.Object[r6]     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r3[r12] = r1     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            r3[r9] = r11     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            java.lang.String r9 = java.lang.String.format(r10, r3)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            zzz(r9)     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
            throw r2     // Catch:{ FileNotFoundException -> 0x015b, IOException -> 0x0154, zzaxi -> 0x014d, NullPointerException -> 0x0146 }
        L_0x0146:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxu r10 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r9)     // Catch:{ zzaxu -> 0x0176 }
            throw r10     // Catch:{ zzaxu -> 0x0176 }
        L_0x014d:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxu r10 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r9)     // Catch:{ zzaxu -> 0x0176 }
            throw r10     // Catch:{ zzaxu -> 0x0176 }
        L_0x0154:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxu r10 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r9)     // Catch:{ zzaxu -> 0x0176 }
            throw r10     // Catch:{ zzaxu -> 0x0176 }
        L_0x015b:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxu r10 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r9)     // Catch:{ zzaxu -> 0x0176 }
            throw r10     // Catch:{ zzaxu -> 0x0176 }
        L_0x0162:
            com.google.android.gms.internal.ads.zzaxi r9 = new com.google.android.gms.internal.ads.zzaxi     // Catch:{ IllegalArgumentException -> 0x0168 }
            r9.<init>(r1)     // Catch:{ IllegalArgumentException -> 0x0168 }
            throw r9     // Catch:{ IllegalArgumentException -> 0x0168 }
        L_0x0168:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxi r10 = new com.google.android.gms.internal.ads.zzaxi     // Catch:{ zzaxi -> 0x016f }
            r10.<init>(r1, r9)     // Catch:{ zzaxi -> 0x016f }
            throw r10     // Catch:{ zzaxi -> 0x016f }
        L_0x016f:
            r9 = move-exception
            com.google.android.gms.internal.ads.zzaxu r10 = new com.google.android.gms.internal.ads.zzaxu     // Catch:{ zzaxu -> 0x0176 }
            r10.<init>(r9)     // Catch:{ zzaxu -> 0x0176 }
            throw r10     // Catch:{ zzaxu -> 0x0176 }
        L_0x0176:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaye.zzg(android.content.Context, java.lang.String, java.lang.String, boolean):com.google.android.gms.internal.ads.zzaye");
    }

    /* access modifiers changed from: private */
    public final void zzv() {
        try {
            if (this.zzi == null && this.zzl) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zza);
                advertisingIdClient.start();
                this.zzi = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzi = null;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(10:17|18|19|20|21|22|23|24|25|27) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00c2 */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A[SYNTHETIC, Splitter:B:39:0x00d8] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00dd A[SYNTHETIC, Splitter:B:43:0x00dd] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e7 A[SYNTHETIC, Splitter:B:51:0x00e7] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00ec A[SYNTHETIC, Splitter:B:55:0x00ec] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzw(java.io.File r11, java.lang.String r12) {
        /*
            r10 = this;
            java.lang.String r12 = "test"
            java.io.File r0 = new java.io.File
            r1 = 2
            java.lang.Object[] r2 = new java.lang.Object[r1]
            r3 = 0
            r2[r3] = r11
            r4 = 1
            java.lang.String r5 = "1708042440713"
            r2[r4] = r5
            java.lang.String r6 = "%s/%s.tmp"
            java.lang.String r2 = java.lang.String.format(r6, r2)
            r0.<init>(r2)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x0020
            goto L_0x00f2
        L_0x0020:
            java.io.File r2 = new java.io.File
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r1[r3] = r11
            r1[r4] = r5
            java.lang.String r11 = "%s/%s.dex"
            java.lang.String r11 = java.lang.String.format(r11, r1)
            r2.<init>(r11)
            boolean r11 = r2.exists()
            if (r11 == 0) goto L_0x00f2
            long r6 = r2.length()
            r8 = 0
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 <= 0) goto L_0x00f2
            int r11 = (int) r6
            byte[] r11 = new byte[r11]
            r1 = 0
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e4, all -> 0x00d3 }
            r4.<init>(r2)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e4, all -> 0x00d3 }
            int r6 = r4.read(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            if (r6 > 0) goto L_0x0057
            r4.close()     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            zzy(r2)
            return
        L_0x0057:
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r6.print(r12)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r6.print(r12)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            java.io.PrintStream r6 = java.lang.System.out     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r6.print(r12)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzauv r12 = com.google.android.gms.internal.ads.zzauw.zza()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            byte[] r6 = r6.getBytes()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhac r7 = com.google.android.gms.internal.ads.zzhac.zzb     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            int r7 = r6.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhac r6 = com.google.android.gms.internal.ads.zzhac.zzv(r6, r3, r7)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r12.zzc(r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            byte[] r5 = r5.getBytes()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            int r6 = r5.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhac r5 = com.google.android.gms.internal.ads.zzhac.zzv(r5, r3, r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r12.zzd(r5)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzaxj r5 = r10.zzg     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            byte[] r6 = r10.zzh     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            java.lang.String r11 = r5.zza(r6, r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            byte[] r11 = r11.getBytes()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            int r5 = r11.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhac r5 = com.google.android.gms.internal.ads.zzhac.zzv(r11, r3, r5)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r12.zza(r5)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            byte[] r11 = com.google.android.gms.internal.ads.zzavs.zzf(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            int r5 = r11.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhac r11 = com.google.android.gms.internal.ads.zzhac.zzv(r11, r3, r5)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r12.zzb(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r0.createNewFile()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            java.io.FileOutputStream r11 = new java.io.FileOutputStream     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            r11.<init>(r0)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d0, all -> 0x00cb }
            com.google.android.gms.internal.ads.zzhbo r12 = r12.zzbr()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            com.google.android.gms.internal.ads.zzauw r12 = (com.google.android.gms.internal.ads.zzauw) r12     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            byte[] r12 = r12.zzaV()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            int r0 = r12.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            r11.write(r12, r3, r0)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            r11.close()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00d1, all -> 0x00c9 }
            r4.close()     // Catch:{ IOException -> 0x00c2 }
        L_0x00c2:
            r11.close()     // Catch:{ IOException -> 0x00c5 }
        L_0x00c5:
            zzy(r2)
            return
        L_0x00c9:
            r12 = move-exception
            goto L_0x00ce
        L_0x00cb:
            r11 = move-exception
            r12 = r11
            r11 = r1
        L_0x00ce:
            r1 = r4
            goto L_0x00d6
        L_0x00d0:
            r11 = r1
        L_0x00d1:
            r1 = r4
            goto L_0x00e5
        L_0x00d3:
            r11 = move-exception
            r12 = r11
            r11 = r1
        L_0x00d6:
            if (r1 == 0) goto L_0x00db
            r1.close()     // Catch:{ IOException -> 0x00db }
        L_0x00db:
            if (r11 == 0) goto L_0x00e0
            r11.close()     // Catch:{ IOException -> 0x00e0 }
        L_0x00e0:
            zzy(r2)
            throw r12
        L_0x00e4:
            r11 = r1
        L_0x00e5:
            if (r1 == 0) goto L_0x00ea
            r1.close()     // Catch:{ IOException -> 0x00ea }
        L_0x00ea:
            if (r11 == 0) goto L_0x00ef
            r11.close()     // Catch:{ IOException -> 0x00ef }
        L_0x00ef:
            zzy(r2)
        L_0x00f2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaye.zzw(java.io.File, java.lang.String):void");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:31|32|33|34|35|36|37) */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00df, code lost:
        r11 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e0, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00e3, code lost:
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x00cd */
    /* JADX WARNING: Missing exception handler attribute for start block: B:47:0x00db */
    /* JADX WARNING: Removed duplicated region for block: B:55:? A[ExcHandler: zzaxi | IOException | NoSuchAlgorithmException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:12:0x004b] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ea A[SYNTHETIC, Splitter:B:61:0x00ea] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00ef A[SYNTHETIC, Splitter:B:65:0x00ef] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00f6 A[SYNTHETIC, Splitter:B:72:0x00f6] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00fb A[SYNTHETIC, Splitter:B:76:0x00fb] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzx(java.io.File r10, java.lang.String r11) {
        /*
            r9 = this;
            java.io.File r11 = new java.io.File
            r0 = 2
            java.lang.Object[] r1 = new java.lang.Object[r0]
            r2 = 0
            r1[r2] = r10
            r3 = 1
            java.lang.String r4 = "1708042440713"
            r1[r3] = r4
            java.lang.String r5 = "%s/%s.tmp"
            java.lang.String r1 = java.lang.String.format(r5, r1)
            r11.<init>(r1)
            boolean r1 = r11.exists()
            if (r1 != 0) goto L_0x001d
            return r2
        L_0x001d:
            java.io.File r1 = new java.io.File
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r2] = r10
            r0[r3] = r4
            java.lang.String r10 = "%s/%s.dex"
            java.lang.String r10 = java.lang.String.format(r10, r0)
            r1.<init>(r10)
            boolean r10 = r1.exists()
            if (r10 != 0) goto L_0x00fe
            r10 = 0
            long r5 = r11.length()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 > 0) goto L_0x0043
            zzy(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            return r2
        L_0x0043:
            int r0 = (int) r5     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            byte[] r0 = new byte[r0]     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            r5.<init>(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00f3, all -> 0x00e6 }
            int r6 = r5.read(r0)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            if (r6 > 0) goto L_0x005f
            java.lang.String r0 = zzd     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            java.lang.String r1 = "Cannot read the cache data."
            android.util.Log.d(r0, r1)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            zzy(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r5.close()     // Catch:{ IOException -> 0x005e }
        L_0x005e:
            return r2
        L_0x005f:
            com.google.android.gms.internal.ads.zzhay r6 = com.google.android.gms.internal.ads.zzhay.zza()     // Catch:{ NullPointerException -> 0x00db, zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3 }
            com.google.android.gms.internal.ads.zzauw r0 = com.google.android.gms.internal.ads.zzauw.zzd(r0, r6)     // Catch:{ NullPointerException -> 0x00db, zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3 }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            com.google.android.gms.internal.ads.zzhac r7 = r0.zzh()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r7 = r7.zzB()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r6.<init>(r7)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            boolean r4 = r4.equals(r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            if (r4 == 0) goto L_0x00d4
            com.google.android.gms.internal.ads.zzhac r4 = r0.zzf()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r4 = r4.zzB()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            com.google.android.gms.internal.ads.zzhac r6 = r0.zze()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r6 = r6.zzB()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r6 = com.google.android.gms.internal.ads.zzavs.zzf(r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            boolean r4 = java.util.Arrays.equals(r4, r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            if (r4 == 0) goto L_0x00d4
            com.google.android.gms.internal.ads.zzhac r4 = r0.zzg()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r4 = r4.zzB()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            java.lang.String r6 = android.os.Build.VERSION.SDK     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r6 = r6.getBytes()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            boolean r4 = java.util.Arrays.equals(r4, r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            if (r4 != 0) goto L_0x00a9
            goto L_0x00d4
        L_0x00a9:
            com.google.android.gms.internal.ads.zzaxj r11 = r9.zzg     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r4 = r9.zzh     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            java.lang.String r6 = new java.lang.String     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            com.google.android.gms.internal.ads.zzhac r0 = r0.zze()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r0 = r0.zzB()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r6.<init>(r0)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            byte[] r11 = r11.zzb(r4, r6)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r1.createNewFile()     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r0.<init>(r1)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            int r10 = r11.length     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e4, all -> 0x00d1 }
            r0.write(r11, r2, r10)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e4, all -> 0x00d1 }
            r5.close()     // Catch:{ IOException -> 0x00cd }
        L_0x00cd:
            r0.close()     // Catch:{ IOException -> 0x00d0 }
        L_0x00d0:
            return r3
        L_0x00d1:
            r10 = move-exception
            r11 = r10
            goto L_0x00e1
        L_0x00d4:
            zzy(r11)     // Catch:{ zzaxi | IOException | NoSuchAlgorithmException -> 0x00e3, all -> 0x00df }
            r5.close()     // Catch:{ IOException -> 0x00da }
        L_0x00da:
            return r2
        L_0x00db:
            r5.close()     // Catch:{ IOException -> 0x00de }
        L_0x00de:
            return r2
        L_0x00df:
            r11 = move-exception
            r0 = r10
        L_0x00e1:
            r10 = r5
            goto L_0x00e8
        L_0x00e3:
            r0 = r10
        L_0x00e4:
            r10 = r5
            goto L_0x00f4
        L_0x00e6:
            r11 = move-exception
            r0 = r10
        L_0x00e8:
            if (r10 == 0) goto L_0x00ed
            r10.close()     // Catch:{ IOException -> 0x00ed }
        L_0x00ed:
            if (r0 == 0) goto L_0x00f2
            r0.close()     // Catch:{ IOException -> 0x00f2 }
        L_0x00f2:
            throw r11
        L_0x00f3:
            r0 = r10
        L_0x00f4:
            if (r10 == 0) goto L_0x00f9
            r10.close()     // Catch:{ IOException -> 0x00f9 }
        L_0x00f9:
            if (r0 == 0) goto L_0x00fe
            r0.close()     // Catch:{ IOException -> 0x00fe }
        L_0x00fe:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaye.zzx(java.io.File, java.lang.String):boolean");
    }

    private static final void zzy(File file) {
        if (!file.exists()) {
            Log.d(zzd, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
            return;
        }
        file.delete();
    }

    private static final void zzz(String str) {
        zzy(new File(str));
    }

    public final int zza() {
        if (this.zzo != null) {
            return zzawy.zzd();
        }
        return Integer.MIN_VALUE;
    }

    public final Context zzb() {
        return this.zza;
    }

    public final zzaus zzc() {
        return this.zzm;
    }

    public final zzawy zzd() {
        return this.zzo;
    }

    public final zzaxj zze() {
        return this.zzg;
    }

    /* access modifiers changed from: package-private */
    public final zzaxx zzf() {
        return this.zzr;
    }

    public final AdvertisingIdClient zzh() {
        Future future;
        if (!this.zzj) {
            return null;
        }
        if (this.zzi == null && (future = this.zzk) != null) {
            try {
                future.get(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
                this.zzk = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzk.cancel(true);
            }
        }
        return this.zzi;
    }

    public final DexClassLoader zzi() {
        return this.zzf;
    }

    public final Method zzj(String str, String str2) {
        zzazr zzazr = (zzazr) this.zzp.get(new Pair(str, str2));
        if (zzazr == null) {
            return null;
        }
        return zzazr.zza();
    }

    public final ExecutorService zzk() {
        return this.zze;
    }

    public final Future zzl() {
        return this.zzn;
    }

    /* access modifiers changed from: package-private */
    public final void zzo(int i, boolean z) {
        if (this.zzc) {
            Future<?> submit = this.zze.submit(new zzayc(this, i, true));
            if (i == 0) {
                this.zzn = submit;
            }
        }
    }

    public final boolean zzp() {
        return this.zzc;
    }

    public final boolean zzq() {
        return this.zzb;
    }

    public final boolean zzr() {
        return this.zzq;
    }

    public final boolean zzs() {
        return this.zzr.zza();
    }

    public final boolean zzt(String str, String str2, Class... clsArr) {
        Pair pair = new Pair(str, str2);
        if (this.zzp.containsKey(pair)) {
            return false;
        }
        this.zzp.put(pair, new zzazr(this, str, str2, clsArr));
        return true;
    }

    public final byte[] zzu() {
        return this.zzh;
    }
}
