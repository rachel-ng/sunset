package com.google.android.gms.internal.ads;

import com.google.common.util.concurrent.ListenableFuture;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
abstract class zzgef extends zzgfj implements Runnable {
    @CheckForNull
    ListenableFuture zza;
    @CheckForNull
    Class zzb;
    @CheckForNull
    Object zzc;

    zzgef(ListenableFuture listenableFuture, Class cls, Object obj) {
        listenableFuture.getClass();
        this.zza = listenableFuture;
        this.zzb = cls;
        this.zzc = obj;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x007b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r9 = this;
            com.google.common.util.concurrent.ListenableFuture r0 = r9.zza
            java.lang.Class r1 = r9.zzb
            java.lang.Object r2 = r9.zzc
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x000c
            r5 = r3
            goto L_0x000d
        L_0x000c:
            r5 = r4
        L_0x000d:
            if (r1 != 0) goto L_0x0011
            r6 = r3
            goto L_0x0012
        L_0x0011:
            r6 = r4
        L_0x0012:
            r5 = r5 | r6
            if (r2 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r3 = r4
        L_0x0017:
            r3 = r3 | r5
            if (r3 != 0) goto L_0x00a2
            boolean r3 = r9.isCancelled()
            if (r3 == 0) goto L_0x0022
            goto L_0x00a2
        L_0x0022:
            r3 = 0
            r9.zza = r3
            boolean r4 = r0 instanceof com.google.android.gms.internal.ads.zzggw     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            if (r4 == 0) goto L_0x0031
            r4 = r0
            com.google.android.gms.internal.ads.zzggw r4 = (com.google.android.gms.internal.ads.zzggw) r4     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            java.lang.Throwable r4 = r4.zzl()     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            goto L_0x0032
        L_0x0031:
            r4 = r3
        L_0x0032:
            if (r4 != 0) goto L_0x003a
            java.lang.Object r5 = com.google.android.gms.internal.ads.zzgft.zzp(r0)     // Catch:{ ExecutionException -> 0x003c, all -> 0x0039 }
            goto L_0x0075
        L_0x0039:
            r4 = move-exception
        L_0x003a:
            r5 = r3
            goto L_0x0075
        L_0x003c:
            r4 = move-exception
            java.lang.Throwable r5 = r4.getCause()
            if (r5 != 0) goto L_0x0073
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.Class r6 = r0.getClass()
            java.lang.String r6 = java.lang.String.valueOf(r6)
            java.lang.Class r4 = r4.getClass()
            java.lang.String r4 = java.lang.String.valueOf(r4)
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "Future type "
            r7.<init>(r8)
            r7.append(r6)
            java.lang.String r6 = " threw "
            r7.append(r6)
            r7.append(r4)
            java.lang.String r4 = " without a cause"
            r7.append(r4)
            java.lang.String r4 = r7.toString()
            r5.<init>(r4)
        L_0x0073:
            r4 = r5
            goto L_0x003a
        L_0x0075:
            if (r4 != 0) goto L_0x007b
            r9.zzc(r5)
            return
        L_0x007b:
            boolean r1 = r1.isInstance(r4)
            if (r1 == 0) goto L_0x009f
            java.lang.Object r0 = r9.zze(r2, r4)     // Catch:{ all -> 0x008d }
            r9.zzb = r3
            r9.zzc = r3
            r9.zzf(r0)
            return
        L_0x008d:
            r0 = move-exception
            com.google.android.gms.internal.ads.zzggl.zza(r0)     // Catch:{ all -> 0x0099 }
            r9.zzd(r0)     // Catch:{ all -> 0x0099 }
            r9.zzb = r3
            r9.zzc = r3
            return
        L_0x0099:
            r0 = move-exception
            r9.zzb = r3
            r9.zzc = r3
            throw r0
        L_0x009f:
            r9.zzs(r0)
        L_0x00a2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgef.run():void");
    }

    /* access modifiers changed from: protected */
    @CheckForNull
    public final String zza() {
        String str;
        ListenableFuture listenableFuture = this.zza;
        Class cls = this.zzb;
        Object obj = this.zzc;
        String zza2 = super.zza();
        if (listenableFuture != null) {
            str = "inputFuture=[" + listenableFuture.toString() + "], ";
        } else {
            str = "";
        }
        if (cls != null && obj != null) {
            return str + "exceptionType=[" + cls.toString() + "], fallback=[" + obj.toString() + "]";
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
        this.zzc = null;
    }

    /* access modifiers changed from: package-private */
    public abstract Object zze(Object obj, Throwable th) throws Exception;

    /* access modifiers changed from: package-private */
    public abstract void zzf(Object obj);
}
