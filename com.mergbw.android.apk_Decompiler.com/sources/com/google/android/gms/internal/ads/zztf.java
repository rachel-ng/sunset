package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zztf implements zztn {
    private static final ArrayDeque zza = new ArrayDeque();
    private static final Object zzb = new Object();
    private final MediaCodec zzc;
    private final HandlerThread zzd;
    private Handler zze;
    private final AtomicReference zzf = new AtomicReference();
    private final zzeu zzg;
    private boolean zzh;

    public zztf(MediaCodec mediaCodec, HandlerThread handlerThread) {
        zzeu zzeu = new zzeu(zzer.zza);
        this.zzc = mediaCodec;
        this.zzd = handlerThread;
        this.zzg = zzeu;
    }

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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    static /* bridge */ /* synthetic */ void zza(com.google.android.gms.internal.ads.zztf r9, android.os.Message r10) {
        /*
            int r0 = r10.what
            r1 = 0
            if (r0 == 0) goto L_0x0059
            r2 = 1
            if (r0 == r2) goto L_0x0036
            r2 = 2
            if (r0 == r2) goto L_0x0030
            r2 = 3
            if (r0 == r2) goto L_0x001f
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            int r10 = r10.what
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r0.<init>(r10)
            com.google.android.gms.internal.ads.zztc.zza(r9, r1, r0)
            goto L_0x0075
        L_0x001f:
            java.lang.Object r10 = r10.obj
            android.os.Bundle r10 = (android.os.Bundle) r10
            android.media.MediaCodec r0 = r9.zzc     // Catch:{ RuntimeException -> 0x0029 }
            r0.setParameters(r10)     // Catch:{ RuntimeException -> 0x0029 }
            goto L_0x0075
        L_0x0029:
            r10 = move-exception
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            com.google.android.gms.internal.ads.zztc.zza(r9, r1, r10)
            goto L_0x0075
        L_0x0030:
            com.google.android.gms.internal.ads.zzeu r9 = r9.zzg
            r9.zze()
            goto L_0x0075
        L_0x0036:
            java.lang.Object r10 = r10.obj
            com.google.android.gms.internal.ads.zzte r10 = (com.google.android.gms.internal.ads.zzte) r10
            int r3 = r10.zza
            int r0 = r10.zzb
            android.media.MediaCodec$CryptoInfo r5 = r10.zzd
            long r6 = r10.zze
            int r8 = r10.zzf
            java.lang.Object r0 = zzb     // Catch:{ RuntimeException -> 0x0052 }
            monitor-enter(r0)     // Catch:{ RuntimeException -> 0x0052 }
            android.media.MediaCodec r2 = r9.zzc     // Catch:{ all -> 0x004f }
            r4 = 0
            r2.queueSecureInputBuffer(r3, r4, r5, r6, r8)     // Catch:{ all -> 0x004f }
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            goto L_0x0074
        L_0x004f:
            r2 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004f }
            throw r2     // Catch:{ RuntimeException -> 0x0052 }
        L_0x0052:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            com.google.android.gms.internal.ads.zztc.zza(r9, r1, r0)
            goto L_0x0074
        L_0x0059:
            java.lang.Object r10 = r10.obj
            com.google.android.gms.internal.ads.zzte r10 = (com.google.android.gms.internal.ads.zzte) r10
            int r3 = r10.zza
            int r0 = r10.zzb
            int r5 = r10.zzc
            long r6 = r10.zze
            int r8 = r10.zzf
            android.media.MediaCodec r2 = r9.zzc     // Catch:{ RuntimeException -> 0x006e }
            r4 = 0
            r2.queueInputBuffer(r3, r4, r5, r6, r8)     // Catch:{ RuntimeException -> 0x006e }
            goto L_0x0074
        L_0x006e:
            r0 = move-exception
            java.util.concurrent.atomic.AtomicReference r9 = r9.zzf
            com.google.android.gms.internal.ads.zztc.zza(r9, r1, r0)
        L_0x0074:
            r1 = r10
        L_0x0075:
            if (r1 == 0) goto L_0x0082
            java.util.ArrayDeque r9 = zza
            monitor-enter(r9)
            r9.add(r1)     // Catch:{ all -> 0x007f }
            monitor-exit(r9)     // Catch:{ all -> 0x007f }
            return
        L_0x007f:
            r10 = move-exception
            monitor-exit(r9)     // Catch:{ all -> 0x007f }
            throw r10
        L_0x0082:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztf.zza(com.google.android.gms.internal.ads.zztf, android.os.Message):void");
    }

    private static zzte zzi() {
        ArrayDeque arrayDeque = zza;
        synchronized (arrayDeque) {
            if (arrayDeque.isEmpty()) {
                zzte zzte = new zzte();
                return zzte;
            }
            zzte zzte2 = (zzte) arrayDeque.removeFirst();
            return zzte2;
        }
    }

    public final void zzb() {
        if (this.zzh) {
            try {
                Handler handler = this.zze;
                if (handler != null) {
                    handler.removeCallbacksAndMessages((Object) null);
                    this.zzg.zzc();
                    Handler handler2 = this.zze;
                    if (handler2 != null) {
                        handler2.obtainMessage(2).sendToTarget();
                        this.zzg.zza();
                        return;
                    }
                    throw null;
                }
                throw null;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException(e);
            }
        }
    }

    public final void zzc() {
        RuntimeException runtimeException = (RuntimeException) this.zzf.getAndSet((Object) null);
        if (runtimeException != null) {
            throw runtimeException;
        }
    }

    public final void zzd(int i, int i2, int i3, long j, int i4) {
        zzc();
        zzte zzi = zzi();
        zzi.zza(i, 0, i3, j, i4);
        Handler handler = this.zze;
        int i5 = zzgd.zza;
        handler.obtainMessage(0, zzi).sendToTarget();
    }

    public final void zze(int i, int i2, zzik zzik, long j, int i3) {
        zzc();
        zzte zzi = zzi();
        zzi.zza(i, 0, 0, j, 0);
        MediaCodec.CryptoInfo cryptoInfo = zzi.zzd;
        cryptoInfo.numSubSamples = zzik.zzf;
        cryptoInfo.numBytesOfClearData = zzk(zzik.zzd, cryptoInfo.numBytesOfClearData);
        cryptoInfo.numBytesOfEncryptedData = zzk(zzik.zze, cryptoInfo.numBytesOfEncryptedData);
        byte[] zzj = zzj(zzik.zzb, cryptoInfo.key);
        zzj.getClass();
        cryptoInfo.key = zzj;
        byte[] zzj2 = zzj(zzik.zza, cryptoInfo.iv);
        zzj2.getClass();
        cryptoInfo.iv = zzj2;
        cryptoInfo.mode = zzik.zzc;
        if (zzgd.zza >= 24) {
            FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m();
            cryptoInfo.setPattern(FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(zzik.zzg, zzik.zzh));
        }
        this.zze.obtainMessage(1, zzi).sendToTarget();
    }

    public final void zzf(Bundle bundle) {
        zzc();
        Handler handler = this.zze;
        int i = zzgd.zza;
        handler.obtainMessage(3, bundle).sendToTarget();
    }

    public final void zzg() {
        if (this.zzh) {
            zzb();
            this.zzd.quit();
        }
        this.zzh = false;
    }

    public final void zzh() {
        if (!this.zzh) {
            this.zzd.start();
            this.zze = new zztd(this, this.zzd.getLooper());
            this.zzh = true;
        }
    }

    private static byte[] zzj(byte[] bArr, byte[] bArr2) {
        int length;
        if (bArr == null) {
            return bArr2;
        }
        if (bArr2 == null || bArr2.length < (length = bArr.length)) {
            return Arrays.copyOf(bArr, bArr.length);
        }
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    private static int[] zzk(int[] iArr, int[] iArr2) {
        int length;
        if (iArr == null) {
            return iArr2;
        }
        if (iArr2 == null || iArr2.length < (length = iArr.length)) {
            return Arrays.copyOf(iArr, iArr.length);
        }
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }
}
