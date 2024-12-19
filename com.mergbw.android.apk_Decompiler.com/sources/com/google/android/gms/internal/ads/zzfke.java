package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzfke {
    private final zzfjx zza;
    private final ListenableFuture zzb;
    private boolean zzc = false;
    private boolean zzd = false;

    public zzfke(zzfjc zzfjc, zzfjw zzfjw, zzfjx zzfjx) {
        this.zza = zzfjx;
        this.zzb = zzgft.zzf(zzgft.zzn(zzfjw.zza(zzfjx), new zzfkc(this, zzfjw, zzfjc, zzfjx), zzfjx.zzb()), Exception.class, new zzfkd(this, zzfjw), zzfjx.zzb());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0030, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized com.google.common.util.concurrent.ListenableFuture zza(com.google.android.gms.internal.ads.zzfjx r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.zzd     // Catch:{ all -> 0x0032 }
            if (r0 != 0) goto L_0x002f
            boolean r0 = r1.zzc     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x000a
            goto L_0x002f
        L_0x000a:
            com.google.android.gms.internal.ads.zzfjx r0 = r1.zza     // Catch:{ all -> 0x0032 }
            com.google.android.gms.internal.ads.zzfjm r0 = r0.zza()     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x002f
            com.google.android.gms.internal.ads.zzfjm r0 = r2.zza()     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x002f
            com.google.android.gms.internal.ads.zzfjx r0 = r1.zza     // Catch:{ all -> 0x0032 }
            com.google.android.gms.internal.ads.zzfjm r0 = r0.zza()     // Catch:{ all -> 0x0032 }
            com.google.android.gms.internal.ads.zzfjm r2 = r2.zza()     // Catch:{ all -> 0x0032 }
            boolean r2 = r0.equals(r2)     // Catch:{ all -> 0x0032 }
            if (r2 == 0) goto L_0x002f
            r2 = 1
            r1.zzc = r2     // Catch:{ all -> 0x0032 }
            com.google.common.util.concurrent.ListenableFuture r2 = r1.zzb     // Catch:{ all -> 0x0032 }
            monitor-exit(r1)
            return r2
        L_0x002f:
            monitor-exit(r1)
            r2 = 0
            return r2
        L_0x0032:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0032 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzfke.zza(com.google.android.gms.internal.ads.zzfjx):com.google.common.util.concurrent.ListenableFuture");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ListenableFuture zzb(zzfjw zzfjw, zzfjc zzfjc, zzfjx zzfjx, zzfjl zzfjl) throws Exception {
        synchronized (this) {
            this.zzd = true;
            zzfjw.zzb(zzfjl);
            if (!this.zzc) {
                zzfjc.zzd(zzfjx.zza(), zzfjl);
                ListenableFuture zzh = zzgft.zzh((Object) null);
                return zzh;
            }
            ListenableFuture zzh2 = zzgft.zzh(new zzfjv(zzfjl, zzfjx));
            return zzh2;
        }
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ ListenableFuture zzc(zzfjw zzfjw, Exception exc) throws Exception {
        synchronized (this) {
            this.zzd = true;
            throw exc;
        }
    }

    public final synchronized void zzd(zzgfp zzgfp) {
        zzgft.zzr(zzgft.zzn(this.zzb, new zzfkb(), this.zza.zzb()), zzgfp, this.zza.zzb());
    }
}
