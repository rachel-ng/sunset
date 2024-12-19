package com.google.android.gms.internal.ads;

import android.os.HandlerThread;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzsz implements zztl {
    private final zzfyw zzb;
    private final zzfyw zzc;
    private boolean zzd = true;

    public zzsz(int i) {
        zzsx zzsx = new zzsx(i);
        zzsy zzsy = new zzsy(i);
        this.zzb = zzsx;
        this.zzc = zzsy;
    }

    static /* synthetic */ HandlerThread zza(int i) {
        return new HandlerThread(zztb.zzr(i, "ExoPlayer:MediaCodecAsyncAdapter:"));
    }

    static /* synthetic */ HandlerThread zzb(int i) {
        return new HandlerThread(zztb.zzr(i, "ExoPlayer:MediaCodecQueueingThread:"));
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.ads.zztb zzc(com.google.android.gms.internal.ads.zztk r7) throws java.io.IOException {
        /*
            r6 = this;
            java.lang.String r0 = "createCodec:"
            com.google.android.gms.internal.ads.zztp r1 = r7.zza
            java.lang.String r1 = r1.zza
            r2 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x006b }
            r3.<init>(r0)     // Catch:{ Exception -> 0x006b }
            r3.append(r1)     // Catch:{ Exception -> 0x006b }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x006b }
            android.os.Trace.beginSection(r0)     // Catch:{ Exception -> 0x006b }
            android.media.MediaCodec r0 = android.media.MediaCodec.createByCodecName(r1)     // Catch:{ Exception -> 0x006b }
            boolean r1 = r6.zzd     // Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x003c
            com.google.android.gms.internal.ads.zzan r1 = r7.zzc     // Catch:{ Exception -> 0x0069 }
            int r3 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0069 }
            r4 = 34
            if (r3 >= r4) goto L_0x0027
            goto L_0x003c
        L_0x0027:
            int r3 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ Exception -> 0x0069 }
            r4 = 35
            if (r3 >= r4) goto L_0x0035
            java.lang.String r1 = r1.zzn     // Catch:{ Exception -> 0x0069 }
            boolean r1 = com.google.android.gms.internal.ads.zzcg.zzh(r1)     // Catch:{ Exception -> 0x0069 }
            if (r1 == 0) goto L_0x003c
        L_0x0035:
            com.google.android.gms.internal.ads.zzum r1 = new com.google.android.gms.internal.ads.zzum     // Catch:{ Exception -> 0x0069 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0069 }
            r3 = 4
            goto L_0x004c
        L_0x003c:
            com.google.android.gms.internal.ads.zztf r1 = new com.google.android.gms.internal.ads.zztf     // Catch:{ Exception -> 0x0069 }
            com.google.android.gms.internal.ads.zzfyw r3 = r6.zzc     // Catch:{ Exception -> 0x0069 }
            com.google.android.gms.internal.ads.zzsy r3 = (com.google.android.gms.internal.ads.zzsy) r3     // Catch:{ Exception -> 0x0069 }
            int r3 = r3.zza     // Catch:{ Exception -> 0x0069 }
            android.os.HandlerThread r3 = zzb(r3)     // Catch:{ Exception -> 0x0069 }
            r1.<init>(r0, r3)     // Catch:{ Exception -> 0x0069 }
            r3 = 0
        L_0x004c:
            com.google.android.gms.internal.ads.zztb r4 = new com.google.android.gms.internal.ads.zztb     // Catch:{ Exception -> 0x0069 }
            com.google.android.gms.internal.ads.zzfyw r5 = r6.zzb     // Catch:{ Exception -> 0x0069 }
            com.google.android.gms.internal.ads.zzsx r5 = (com.google.android.gms.internal.ads.zzsx) r5     // Catch:{ Exception -> 0x0069 }
            int r5 = r5.zza     // Catch:{ Exception -> 0x0069 }
            android.os.HandlerThread r5 = zza(r5)     // Catch:{ Exception -> 0x0069 }
            r4.<init>(r0, r5, r1, r2)     // Catch:{ Exception -> 0x0069 }
            android.os.Trace.endSection()     // Catch:{ Exception -> 0x0066 }
            android.media.MediaFormat r1 = r7.zzb     // Catch:{ Exception -> 0x0066 }
            android.view.Surface r7 = r7.zzd     // Catch:{ Exception -> 0x0066 }
            com.google.android.gms.internal.ads.zztb.zzh(r4, r1, r7, r2, r3)     // Catch:{ Exception -> 0x0066 }
            return r4
        L_0x0066:
            r7 = move-exception
            r2 = r4
            goto L_0x006d
        L_0x0069:
            r7 = move-exception
            goto L_0x006d
        L_0x006b:
            r7 = move-exception
            r0 = r2
        L_0x006d:
            if (r2 != 0) goto L_0x0075
            if (r0 == 0) goto L_0x0078
            r0.release()
            goto L_0x0078
        L_0x0075:
            r2.zzl()
        L_0x0078:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsz.zzc(com.google.android.gms.internal.ads.zztk):com.google.android.gms.internal.ads.zztb");
    }

    public final /* bridge */ /* synthetic */ zztm zzd(zztk zztk) throws IOException {
        throw null;
    }

    public final void zze(boolean z) {
        this.zzd = true;
    }
}
