package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import org.checkerframework.dataflow.qual.Pure;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@22.0.2 */
public final class zzhc extends zzii {
    /* access modifiers changed from: private */
    public static final AtomicLong zza = new AtomicLong(Long.MIN_VALUE);
    /* access modifiers changed from: private */
    public zzhg zzb;
    /* access modifiers changed from: private */
    public zzhg zzc;
    private final PriorityBlockingQueue<zzhh<?>> zzd = new PriorityBlockingQueue<>();
    private final BlockingQueue<zzhh<?>> zze = new LinkedBlockingQueue();
    private final Thread.UncaughtExceptionHandler zzf = new zzhe(this, "Thread death: Uncaught exception on worker thread");
    private final Thread.UncaughtExceptionHandler zzg = new zzhe(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzh = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzi = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzj;

    @Pure
    public final /* bridge */ /* synthetic */ Context zza() {
        return super.zza();
    }

    /* access modifiers changed from: protected */
    public final boolean zzo() {
        return false;
    }

    @Pure
    public final /* bridge */ /* synthetic */ Clock zzb() {
        return super.zzb();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzab zzd() {
        return super.zzd();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzag zze() {
        return super.zze();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzax zzf() {
        return super.zzf();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfr zzi() {
        return super.zzi();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzfw zzj() {
        return super.zzj();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzgh zzk() {
        return super.zzk();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zzhc zzl() {
        return super.zzl();
    }

    @Pure
    public final /* bridge */ /* synthetic */ zznp zzq() {
        return super.zzq();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:11|12|13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r3 = zzj().zzu();
        r3.zza("Interrupted waiting for " + r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        r2 = r2.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        if (r2 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        r3 = zzj().zzu();
        r3.zza("Timed out waiting for " + r5);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T zza(java.util.concurrent.atomic.AtomicReference<T> r2, long r3, java.lang.String r5, java.lang.Runnable r6) {
        /*
            r1 = this;
            monitor-enter(r2)
            com.google.android.gms.measurement.internal.zzhc r0 = r1.zzl()     // Catch:{ all -> 0x0048 }
            r0.zzb((java.lang.Runnable) r6)     // Catch:{ all -> 0x0048 }
            r2.wait(r3)     // Catch:{ InterruptedException -> 0x002c }
            monitor-exit(r2)     // Catch:{ all -> 0x0048 }
            java.lang.Object r2 = r2.get()
            if (r2 != 0) goto L_0x002b
            com.google.android.gms.measurement.internal.zzfw r3 = r1.zzj()
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r6 = "Timed out waiting for "
            r4.<init>(r6)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.zza(r4)
        L_0x002b:
            return r2
        L_0x002c:
            com.google.android.gms.measurement.internal.zzfw r3 = r1.zzj()     // Catch:{ all -> 0x0048 }
            com.google.android.gms.measurement.internal.zzfy r3 = r3.zzu()     // Catch:{ all -> 0x0048 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0048 }
            java.lang.String r6 = "Interrupted waiting for "
            r4.<init>(r6)     // Catch:{ all -> 0x0048 }
            r4.append(r5)     // Catch:{ all -> 0x0048 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0048 }
            r3.zza(r4)     // Catch:{ all -> 0x0048 }
            monitor-exit(r2)     // Catch:{ all -> 0x0048 }
            r2 = 0
            return r2
        L_0x0048:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0048 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzhc.zza(java.util.concurrent.atomic.AtomicReference, long, java.lang.String, java.lang.Runnable):java.lang.Object");
    }

    public final <V> Future<V> zza(Callable<V> callable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(callable);
        zzhh zzhh = new zzhh(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            if (!this.zzd.isEmpty()) {
                zzj().zzu().zza("Callable skipped the worker queue.");
            }
            zzhh.run();
        } else {
            zza((zzhh<?>) zzhh);
        }
        return zzhh;
    }

    public final <V> Future<V> zzb(Callable<V> callable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(callable);
        zzhh zzhh = new zzhh(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzb) {
            zzhh.run();
        } else {
            zza((zzhh<?>) zzhh);
        }
        return zzhh;
    }

    zzhc(zzhj zzhj) {
        super(zzhj);
    }

    public final void zzr() {
        if (Thread.currentThread() != this.zzc) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ void zzs() {
        super.zzs();
    }

    public final void zzt() {
        if (Thread.currentThread() != this.zzb) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    private final void zza(zzhh<?> zzhh) {
        synchronized (this.zzh) {
            this.zzd.add(zzhh);
            zzhg zzhg = this.zzb;
            if (zzhg == null) {
                zzhg zzhg2 = new zzhg(this, "Measurement Worker", this.zzd);
                this.zzb = zzhg2;
                zzhg2.setUncaughtExceptionHandler(this.zzf);
                this.zzb.start();
            } else {
                zzhg.zza();
            }
        }
    }

    public final void zza(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zzhh zzhh = new zzhh(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzh) {
            this.zze.add(zzhh);
            zzhg zzhg = this.zzc;
            if (zzhg == null) {
                zzhg zzhg2 = new zzhg(this, "Measurement Network", this.zze);
                this.zzc = zzhg2;
                zzhg2.setUncaughtExceptionHandler(this.zzg);
                this.zzc.start();
            } else {
                zzhg.zza();
            }
        }
    }

    public final void zzb(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zza((zzhh<?>) new zzhh(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzc(Runnable runnable) throws IllegalStateException {
        zzac();
        Preconditions.checkNotNull(runnable);
        zza((zzhh<?>) new zzhh(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean zzg() {
        return Thread.currentThread() == this.zzb;
    }
}
