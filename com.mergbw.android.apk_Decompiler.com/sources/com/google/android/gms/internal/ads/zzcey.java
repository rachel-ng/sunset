package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcey implements zzhb {
    private final Context zza;
    private final zzhb zzb;
    private final String zzc;
    private final int zzd;
    private final boolean zze = ((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue();
    private InputStream zzf;
    private boolean zzg;
    private Uri zzh;
    private volatile zzbcy zzi;
    private boolean zzj = false;
    private boolean zzk = false;
    private final AtomicLong zzl = new AtomicLong(-1);
    private zzhh zzm;

    public zzcey(Context context, zzhb zzhb, String str, int i, zzie zzie, zzcex zzcex) {
        this.zza = context;
        this.zzb = zzhb;
        this.zzc = str;
        this.zzd = i;
    }

    private final boolean zzg() {
        if (!this.zze) {
            return false;
        }
        if (((Boolean) zzba.zzc().zza(zzbep.zzer)).booleanValue() && !this.zzj) {
            return true;
        }
        return ((Boolean) zzba.zzc().zza(zzbep.zzes)).booleanValue() && !this.zzk;
    }

    public final int zza(byte[] bArr, int i, int i2) throws IOException {
        if (this.zzg) {
            InputStream inputStream = this.zzf;
            if (inputStream != null) {
                return inputStream.read(bArr, i, i2);
            }
            return this.zzb.zza(bArr, i, i2);
        }
        throw new IOException("Attempt to read closed CacheDataSource.");
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x009f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzb(com.google.android.gms.internal.ads.zzhh r6) throws java.io.IOException {
        /*
            r5 = this;
            boolean r0 = r5.zzg
            if (r0 != 0) goto L_0x011e
            r0 = 1
            r5.zzg = r0
            android.net.Uri r0 = r6.zza
            r5.zzh = r0
            r5.zzm = r6
            android.net.Uri r0 = r6.zza
            com.google.android.gms.internal.ads.zzbcy r0 = com.google.android.gms.internal.ads.zzbcy.zza(r0)
            r5.zzi = r0
            com.google.android.gms.internal.ads.zzbeg r0 = com.google.android.gms.internal.ads.zzbep.zzeo
            com.google.android.gms.internal.ads.zzben r1 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r0 = r1.zza(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            r1 = 0
            if (r0 == 0) goto L_0x00b5
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            if (r0 == 0) goto L_0x00fc
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            long r2 = r6.zze
            r0.zzh = r2
            com.google.android.gms.internal.ads.zzbcy r6 = r5.zzi
            java.lang.String r0 = r5.zzc
            java.lang.String r0 = com.google.android.gms.internal.ads.zzfyv.zzc(r0)
            r6.zzi = r0
            com.google.android.gms.internal.ads.zzbcy r6 = r5.zzi
            int r0 = r5.zzd
            r6.zzj = r0
            com.google.android.gms.internal.ads.zzbcy r6 = r5.zzi
            boolean r6 = r6.zzg
            if (r6 == 0) goto L_0x0055
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzeq
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r0.zza(r6)
            java.lang.Long r6 = (java.lang.Long) r6
            goto L_0x0061
        L_0x0055:
            com.google.android.gms.internal.ads.zzbeg r6 = com.google.android.gms.internal.ads.zzbep.zzep
            com.google.android.gms.internal.ads.zzben r0 = com.google.android.gms.ads.internal.client.zzba.zzc()
            java.lang.Object r6 = r0.zza(r6)
            java.lang.Long r6 = (java.lang.Long) r6
        L_0x0061:
            long r2 = r6.longValue()
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzu.zzB()
            r6.elapsedRealtime()
            com.google.android.gms.ads.internal.zzu.zzd()
            android.content.Context r6 = r5.zza
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            java.util.concurrent.Future r6 = com.google.android.gms.internal.ads.zzbdj.zza(r6, r0)
            r0 = 0
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            java.lang.Object r2 = r6.get(r2, r4)     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            com.google.android.gms.internal.ads.zzbdk r2 = (com.google.android.gms.internal.ads.zzbdk) r2     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            r2.zzd()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            boolean r3 = r2.zzf()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            r5.zzj = r3     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            boolean r3 = r2.zze()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            r5.zzk = r3     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            r2.zza()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            boolean r3 = r5.zzg()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            if (r3 != 0) goto L_0x00ad
            java.io.InputStream r2 = r2.zzc()     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            r5.zzf = r2     // Catch:{ ExecutionException | TimeoutException -> 0x00aa, InterruptedException -> 0x009f }
            goto L_0x00ad
        L_0x009f:
            r6.cancel(r0)     // Catch:{ all -> 0x00ad }
            java.lang.Thread r6 = java.lang.Thread.currentThread()     // Catch:{ all -> 0x00ad }
            r6.interrupt()     // Catch:{ all -> 0x00ad }
            goto L_0x00ad
        L_0x00aa:
            r6.cancel(r0)     // Catch:{ all -> 0x00ad }
        L_0x00ad:
            com.google.android.gms.common.util.Clock r6 = com.google.android.gms.ads.internal.zzu.zzB()
            r6.elapsedRealtime()
            throw r1
        L_0x00b5:
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            if (r0 == 0) goto L_0x00d9
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            long r1 = r6.zze
            r0.zzh = r1
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            java.lang.String r1 = r5.zzc
            java.lang.String r1 = com.google.android.gms.internal.ads.zzfyv.zzc(r1)
            r0.zzi = r1
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            int r1 = r5.zzd
            r0.zzj = r1
            com.google.android.gms.internal.ads.zzbcu r0 = com.google.android.gms.ads.internal.zzu.zzc()
            com.google.android.gms.internal.ads.zzbcy r1 = r5.zzi
            com.google.android.gms.internal.ads.zzbcv r1 = r0.zzb(r1)
        L_0x00d9:
            if (r1 == 0) goto L_0x00fc
            boolean r0 = r1.zze()
            if (r0 == 0) goto L_0x00fc
            boolean r0 = r1.zzg()
            r5.zzj = r0
            boolean r0 = r1.zzf()
            r5.zzk = r0
            boolean r0 = r5.zzg()
            if (r0 != 0) goto L_0x00fc
            java.io.InputStream r6 = r1.zzc()
            r5.zzf = r6
            r0 = -1
            return r0
        L_0x00fc:
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            if (r0 == 0) goto L_0x0115
            com.google.android.gms.internal.ads.zzhf r6 = r6.zza()
            com.google.android.gms.internal.ads.zzbcy r0 = r5.zzi
            java.lang.String r0 = r0.zza
            android.net.Uri r0 = android.net.Uri.parse(r0)
            r6.zzd(r0)
            com.google.android.gms.internal.ads.zzhh r6 = r6.zze()
            r5.zzm = r6
        L_0x0115:
            com.google.android.gms.internal.ads.zzhb r6 = r5.zzb
            com.google.android.gms.internal.ads.zzhh r0 = r5.zzm
            long r0 = r6.zzb(r0)
            return r0
        L_0x011e:
            java.io.IOException r6 = new java.io.IOException
            java.lang.String r0 = "Attempt to open an already open CacheDataSource."
            r6.<init>(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcey.zzb(com.google.android.gms.internal.ads.zzhh):long");
    }

    public final Uri zzc() {
        return this.zzh;
    }

    public final void zzd() throws IOException {
        if (this.zzg) {
            this.zzg = false;
            this.zzh = null;
            InputStream inputStream = this.zzf;
            if (inputStream != null) {
                IOUtils.closeQuietly((Closeable) inputStream);
                this.zzf = null;
                return;
            }
            this.zzb.zzd();
            return;
        }
        throw new IOException("Attempt to close an already closed CacheDataSource.");
    }

    public final /* synthetic */ Map zze() {
        return Collections.emptyMap();
    }

    public final void zzf(zzie zzie) {
    }
}
