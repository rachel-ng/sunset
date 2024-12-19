package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgep extends zzgfj implements Runnable {
    public static final /* synthetic */ int zzc = 0;
    @CheckForNull
    ListenableFuture zza;
    @CheckForNull
    Object zzb;

    zzgep(ListenableFuture listenableFuture, Object obj) {
        listenableFuture.getClass();
        this.zza = listenableFuture;
        this.zzb = obj;
    }

    public final void run() {
        ListenableFuture listenableFuture = this.zza;
        Object obj = this.zzb;
        boolean z = true;
        boolean isCancelled = isCancelled() | (listenableFuture == null);
        if (obj != null) {
            z = false;
        }
        if (!isCancelled && !z) {
            this.zza = null;
            if (listenableFuture.isCancelled()) {
                zzs(listenableFuture);
                return;
            }
            try {
                try {
                    Object zze = zze(obj, zzgft.zzp(listenableFuture));
                    this.zzb = null;
                    zzf(zze);
                } catch (Throwable th) {
                    this.zzb = null;
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e) {
                zzd(e.getCause());
            } catch (Exception e2) {
                zzd(e2);
            } catch (Error e3) {
                zzd(e3);
            }
        }
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        String str;
        ListenableFuture listenableFuture = this.zza;
        Object obj = this.zzb;
        String zza2 = super.zza();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture.toString() + "], ";
        } else {
            str = "";
        }
        if (obj != null) {
            return str + "function=[" + obj.toString() + "]";
        } else if (zza2 != null) {
            return str.concat(zza2);
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzb() {
        zzr(this.zza);
        this.zza = null;
        this.zzb = null;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zze(Object obj, Object obj2) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj);
}
