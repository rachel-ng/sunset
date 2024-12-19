package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioDeviceInfo;
import android.media.AudioTrack;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import com.google.android.exoplayer2.util.MimeTypes;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.concurrent.ExecutorService;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzrz implements zzqv {
    private static final Object zza = new Object();
    private static ExecutorService zzb;
    private static int zzc;
    private zzrp zzA;
    private zzcl zzB;
    private boolean zzC;
    private long zzD;
    private long zzE;
    private long zzF;
    private long zzG;
    private int zzH;
    private boolean zzI;
    private boolean zzJ;
    private long zzK;
    private float zzL;
    private ByteBuffer zzM;
    private int zzN;
    private ByteBuffer zzO;
    private boolean zzP;
    private boolean zzQ;
    /* access modifiers changed from: private */
    public boolean zzR;
    /* access modifiers changed from: private */
    public boolean zzS;
    private int zzT;
    private zzl zzU;
    private zzpx zzV;
    /* access modifiers changed from: private */
    public long zzW;
    private boolean zzX;
    private boolean zzY;
    private Looper zzZ;
    private long zzaa;
    private long zzab;
    private Handler zzac;
    private final zzrn zzad;
    private final zzrd zzae;
    private final Context zzd;
    private final zzra zze;
    private final zzsj zzf;
    private final zzgbc zzg;
    private final zzgbc zzh;
    private final zzeu zzi;
    private final zzqz zzj;
    private final ArrayDeque zzk;
    private zzrx zzl;
    private final zzrs zzm;
    private final zzrs zzn;
    private final zzrk zzo;
    private zzpj zzp;
    /* access modifiers changed from: private */
    public zzqs zzq;
    private zzrm zzr;
    private zzrm zzs;
    private zzdw zzt;
    /* access modifiers changed from: private */
    public AudioTrack zzu;
    private zzpp zzv;
    private zzpw zzw;
    private zzrr zzx;
    private zzk zzy;
    private zzrp zzz;

    /* synthetic */ zzrz(zzrl zzrl, zzry zzry) {
        zzpp zzpp;
        Context zza2 = zzrl.zza;
        this.zzd = zza2;
        zzk zzk2 = zzk.zza;
        this.zzy = zzk2;
        if (zza2 != null) {
            zzpp zzpp2 = zzpp.zza;
            int i = zzgd.zza;
            zzpp = zzpp.zzc(zza2, zzk2, (zzpx) null);
        } else {
            zzpp = zzrl.zzb;
        }
        this.zzv = zzpp;
        this.zzad = zzrl.zze;
        int i2 = zzgd.zza;
        this.zzo = zzrl.zzd;
        zzrd zzf2 = zzrl.zzf;
        zzf2.getClass();
        this.zzae = zzf2;
        zzeu zzeu = new zzeu(zzer.zza);
        this.zzi = zzeu;
        zzeu.zze();
        this.zzj = new zzqz(new zzru(this, (zzrt) null));
        zzra zzra = new zzra();
        this.zze = zzra;
        zzsj zzsj = new zzsj();
        this.zzf = zzsj;
        this.zzg = zzgbc.zzp(new zzed(), zzra, zzsj);
        this.zzh = zzgbc.zzn(new zzsi());
        this.zzL = 1.0f;
        this.zzT = 0;
        this.zzU = new zzl(0, 0.0f);
        this.zzA = new zzrp(zzcl.zza, 0, 0, (zzro) null);
        this.zzB = zzcl.zza;
        this.zzC = false;
        this.zzk = new ArrayDeque();
        this.zzm = new zzrs(100);
        this.zzn = new zzrs(100);
    }

    public static /* synthetic */ void zzG(zzrz zzrz) {
        if (zzrz.zzab >= 300000) {
            ((zzse) zzrz.zzq).zza.zzn = true;
            zzrz.zzab = 0;
        }
    }

    static /* synthetic */ void zzI(AudioTrack audioTrack, zzqs zzqs, Handler handler, zzqp zzqp, zzeu zzeu) {
        try {
            audioTrack.flush();
            audioTrack.release();
            if (zzqs != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new zzrf(zzqs, zzqp));
            }
            zzeu.zze();
            synchronized (zza) {
                int i = zzc - 1;
                zzc = i;
                if (i == 0) {
                    zzb.shutdown();
                    zzb = null;
                }
            }
        } catch (Throwable th) {
            if (zzqs != null && handler.getLooper().getThread().isAlive()) {
                handler.post(new zzrf(zzqs, zzqp));
            }
            zzeu.zze();
            synchronized (zza) {
                int i2 = zzc - 1;
                zzc = i2;
                if (i2 == 0) {
                    zzb.shutdown();
                    zzb = null;
                }
                throw th;
            }
        }
    }

    /* access modifiers changed from: private */
    public final long zzL() {
        zzrm zzrm = this.zzs;
        return zzrm.zzc == 0 ? this.zzD / ((long) zzrm.zzb) : this.zzE;
    }

    /* access modifiers changed from: private */
    public final long zzM() {
        zzrm zzrm = this.zzs;
        if (zzrm.zzc != 0) {
            return this.zzG;
        }
        long j = this.zzF;
        long j2 = (long) zzrm.zzd;
        int i = zzgd.zza;
        return ((j + j2) - 1) / j2;
    }

    private final AudioTrack zzN(zzrm zzrm) throws zzqr {
        try {
            return zzrm.zza(this.zzy, this.zzT);
        } catch (zzqr e) {
            zzqs zzqs = this.zzq;
            if (zzqs != null) {
                zzqs.zza(e);
            }
            throw e;
        }
    }

    private final void zzO(long j) {
        zzcl zzcl;
        boolean z;
        if (zzaa()) {
            zzrn zzrn = this.zzad;
            zzcl = this.zzB;
            zzrn.zzc(zzcl);
        } else {
            zzcl = zzcl.zza;
        }
        zzcl zzcl2 = zzcl;
        this.zzB = zzcl2;
        if (zzaa()) {
            zzrn zzrn2 = this.zzad;
            z = this.zzC;
            zzrn2.zzd(z);
        } else {
            z = false;
        }
        this.zzC = z;
        this.zzk.add(new zzrp(zzcl2, Math.max(0, j), zzgd.zzs(zzM(), this.zzs.zze), (zzro) null));
        zzV();
        zzqs zzqs = this.zzq;
        if (zzqs != null) {
            ((zzse) zzqs).zza.zzc.zzw(this.zzC);
        }
    }

    private final void zzP() {
        if (this.zzs.zzc()) {
            this.zzX = true;
        }
    }

    private final void zzQ() {
        if (this.zzw == null && this.zzd != null) {
            this.zzZ = Looper.myLooper();
            zzpw zzpw = new zzpw(this.zzd, new zzrh(this), this.zzy, this.zzV);
            this.zzw = zzpw;
            this.zzv = zzpw.zzc();
        }
    }

    private final void zzR() {
        if (!this.zzQ) {
            this.zzQ = true;
            this.zzj.zzb(zzM());
            this.zzu.stop();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        if (r0.hasRemaining() == false) goto L_0x0038;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0030, code lost:
        r2.zzt.zze(r2.zzM);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0026, code lost:
        r0 = r2.zzM;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        if (r0 == null) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzS(long r3) throws com.google.android.gms.internal.ads.zzqu {
        /*
            r2 = this;
            com.google.android.gms.internal.ads.zzdw r0 = r2.zzt
            boolean r0 = r0.zzh()
            if (r0 == 0) goto L_0x0039
        L_0x0008:
            com.google.android.gms.internal.ads.zzdw r0 = r2.zzt
            boolean r0 = r0.zzg()
            if (r0 != 0) goto L_0x0038
        L_0x0010:
            com.google.android.gms.internal.ads.zzdw r0 = r2.zzt
            java.nio.ByteBuffer r0 = r0.zzb()
            boolean r1 = r0.hasRemaining()
            if (r1 == 0) goto L_0x0026
            r2.zzW(r0, r3)
            boolean r0 = r0.hasRemaining()
            if (r0 == 0) goto L_0x0010
            goto L_0x0038
        L_0x0026:
            java.nio.ByteBuffer r0 = r2.zzM
            if (r0 == 0) goto L_0x0038
            boolean r0 = r0.hasRemaining()
            if (r0 == 0) goto L_0x0038
            com.google.android.gms.internal.ads.zzdw r0 = r2.zzt
            java.nio.ByteBuffer r1 = r2.zzM
            r0.zze(r1)
            goto L_0x0008
        L_0x0038:
            return
        L_0x0039:
            java.nio.ByteBuffer r0 = r2.zzM
            if (r0 != 0) goto L_0x003f
            java.nio.ByteBuffer r0 = com.google.android.gms.internal.ads.zzdz.zza
        L_0x003f:
            r2.zzW(r0, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrz.zzS(long):void");
    }

    private final void zzT(zzcl zzcl) {
        zzrp zzrp = new zzrp(zzcl, C.TIME_UNSET, C.TIME_UNSET, (zzro) null);
        if (zzY()) {
            this.zzz = zzrp;
        } else {
            this.zzA = zzrp;
        }
    }

    private final void zzU() {
        if (zzY()) {
            int i = zzgd.zza;
            this.zzu.setVolume(this.zzL);
        }
    }

    private final void zzV() {
        zzdw zzdw = this.zzs.zzi;
        this.zzt = zzdw;
        zzdw.zzc();
    }

    private final void zzW(ByteBuffer byteBuffer, long j) throws zzqu {
        zzqs zzqs;
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.zzO;
            boolean z = false;
            if (byteBuffer2 != null) {
                zzeq.zzd(byteBuffer2 == byteBuffer);
            } else {
                this.zzO = byteBuffer;
                int i = zzgd.zza;
            }
            int remaining = byteBuffer.remaining();
            int i2 = zzgd.zza;
            int write = this.zzu.write(byteBuffer, remaining, 1);
            this.zzW = SystemClock.elapsedRealtime();
            if (write < 0) {
                if ((zzgd.zza >= 24 && write == -6) || write == -32) {
                    if (zzM() <= 0) {
                        if (zzZ(this.zzu)) {
                            zzP();
                        }
                    }
                    z = true;
                }
                zzqu zzqu = new zzqu(write, this.zzs.zza, z);
                zzqs zzqs2 = this.zzq;
                if (zzqs2 != null) {
                    zzqs2.zza(zzqu);
                }
                if (!zzqu.zzb) {
                    this.zzn.zzb(zzqu);
                } else {
                    this.zzv = zzpp.zza;
                    throw zzqu;
                }
            } else {
                this.zzn.zza();
                if (zzZ(this.zzu)) {
                    if (this.zzG > 0) {
                        this.zzY = false;
                    }
                    if (this.zzS && (zzqs = this.zzq) != null && write < remaining) {
                        zzsf zzsf = ((zzse) zzqs).zza;
                        if (zzsf.zzm != null) {
                            zzsf.zzm.zza();
                        }
                    }
                }
                int i3 = this.zzs.zzc;
                if (i3 == 0) {
                    this.zzF += (long) write;
                }
                if (write == remaining) {
                    if (i3 != 0) {
                        if (byteBuffer == this.zzM) {
                            z = true;
                        }
                        zzeq.zzf(z);
                        this.zzG += ((long) this.zzH) * ((long) this.zzN);
                    }
                    this.zzO = null;
                }
            }
        }
    }

    private final boolean zzX() throws zzqu {
        if (!this.zzt.zzh()) {
            ByteBuffer byteBuffer = this.zzO;
            if (byteBuffer == null) {
                return true;
            }
            zzW(byteBuffer, Long.MIN_VALUE);
            return this.zzO == null;
        }
        this.zzt.zzd();
        zzS(Long.MIN_VALUE);
        if (!this.zzt.zzg()) {
            return false;
        }
        ByteBuffer byteBuffer2 = this.zzO;
        if (byteBuffer2 != null) {
            return !byteBuffer2.hasRemaining();
        }
        return true;
    }

    private final boolean zzY() {
        return this.zzu != null;
    }

    private static boolean zzZ(AudioTrack audioTrack) {
        return zzgd.zza >= 29 && FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(audioTrack);
    }

    private final boolean zzaa() {
        zzrm zzrm = this.zzs;
        if (zzrm.zzc != 0) {
            return false;
        }
        int i = zzrm.zza.zzC;
        return true;
    }

    public final boolean zzA(zzan zzan) {
        return zza(zzan) != 0;
    }

    public final void zzJ(zzpp zzpp) {
        zzeq.zzf(this.zzZ == Looper.myLooper());
        if (!zzpp.equals(this.zzv)) {
            this.zzv = zzpp;
            zzqs zzqs = this.zzq;
            if (zzqs != null) {
                ((zzse) zzqs).zza.zzB();
            }
        }
    }

    public final int zza(zzan zzan) {
        zzQ();
        if (!MimeTypes.AUDIO_RAW.equals(zzan.zzn)) {
            return this.zzv.zzb(zzan, this.zzy) != null ? 2 : 0;
        }
        if (!zzgd.zzK(zzan.zzC)) {
            int i = zzan.zzC;
            zzfk.zzf("DefaultAudioSink", "Invalid PCM encoding: " + i);
            return 0;
        } else if (zzan.zzC != 2) {
            return 1;
        } else {
            return 2;
        }
    }

    public final long zzb(boolean z) {
        long j;
        if (!zzY() || this.zzJ) {
            return Long.MIN_VALUE;
        }
        long min = Math.min(this.zzj.zza(z), zzgd.zzs(zzM(), this.zzs.zze));
        while (!this.zzk.isEmpty() && min >= ((zzrp) this.zzk.getFirst()).zzc) {
            this.zzA = (zzrp) this.zzk.remove();
        }
        zzrp zzrp = this.zzA;
        long j2 = min - zzrp.zzc;
        if (zzrp.zza.equals(zzcl.zza)) {
            j = this.zzA.zzb + j2;
        } else if (this.zzk.isEmpty()) {
            j = this.zzad.zza(j2) + this.zzA.zzb;
        } else {
            zzrp zzrp2 = (zzrp) this.zzk.getFirst();
            j = zzrp2.zzb - zzgd.zzp(zzrp2.zzc - min, this.zzA.zza.zzc);
        }
        long zzb2 = this.zzad.zzb();
        long zzs2 = j + zzgd.zzs(zzb2, this.zzs.zze);
        long j3 = this.zzaa;
        if (zzb2 > j3) {
            long zzs3 = zzgd.zzs(zzb2 - j3, this.zzs.zze);
            this.zzaa = zzb2;
            this.zzab += zzs3;
            if (this.zzac == null) {
                this.zzac = new Handler(Looper.myLooper());
            }
            this.zzac.removeCallbacksAndMessages((Object) null);
            this.zzac.postDelayed(new zzrg(this), 100);
        }
        return zzs2;
    }

    public final zzcl zzc() {
        return this.zzB;
    }

    public final zzqa zzd(zzan zzan) {
        if (this.zzX) {
            return zzqa.zza;
        }
        return this.zzae.zza(zzan, this.zzy);
    }

    public final void zze(zzan zzan, int i, int[] iArr) throws zzqq {
        zzdw zzdw;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        zzan zzan2 = zzan;
        zzQ();
        if (MimeTypes.AUDIO_RAW.equals(zzan2.zzn)) {
            zzeq.zzd(zzgd.zzK(zzan2.zzC));
            i7 = zzgd.zzm(zzan2.zzC, zzan2.zzA);
            zzgaz zzgaz = new zzgaz();
            int i11 = zzan2.zzC;
            zzgaz.zzh(this.zzg);
            zzgaz.zzg(this.zzad.zze());
            zzdw zzdw2 = new zzdw(zzgaz.zzi());
            if (zzdw2.equals(this.zzt)) {
                zzdw2 = this.zzt;
            }
            this.zzf.zzq(zzan2.zzD, zzan2.zzE);
            this.zze.zzo(iArr);
            try {
                zzdx zza2 = zzdw2.zza(new zzdx(zzan2.zzB, zzan2.zzA, zzan2.zzC));
                i4 = zza2.zzd;
                i3 = zza2.zzb;
                int i12 = zza2.zzc;
                i2 = zzgd.zzh(i12);
                zzdw = zzdw2;
                i5 = zzgd.zzm(i4, i12);
                i6 = 0;
            } catch (zzdy e) {
                throw new zzqq((Throwable) e, zzan2);
            }
        } else {
            zzdw zzdw3 = new zzdw(zzgbc.zzm());
            int i13 = zzan2.zzB;
            zzqa zzqa = zzqa.zza;
            Pair zzb2 = this.zzv.zzb(zzan2, this.zzy);
            if (zzb2 != null) {
                i4 = ((Integer) zzb2.first).intValue();
                zzdw = zzdw3;
                i7 = -1;
                i2 = ((Integer) zzb2.second).intValue();
                i5 = -1;
                i3 = i13;
                i6 = 2;
            } else {
                throw new zzqq("Unable to configure passthrough for: ".concat(String.valueOf(String.valueOf(zzan))), zzan2);
            }
        }
        if (i4 == 0) {
            throw new zzqq("Invalid output encoding (mode=" + i6 + ") for: " + String.valueOf(zzan), zzan2);
        } else if (i2 != 0) {
            int i14 = zzan2.zzj;
            if (MimeTypes.AUDIO_DTS_EXPRESS.equals(zzan2.zzn) && i14 == -1) {
                i14 = Ac3Util.E_AC3_MAX_RATE_BYTES_PER_SECOND;
            }
            int minBufferSize = AudioTrack.getMinBufferSize(i3, i2, i4);
            zzeq.zzf(minBufferSize != -2);
            int i15 = i5 != -1 ? i5 : 1;
            int i16 = 250000;
            if (i6 == 0) {
                i8 = i6;
                i9 = Math.max(zzsb.zza(250000, i3, i15), Math.min(minBufferSize * 4, zzsb.zza(750000, i3, i15)));
            } else if (i6 != 1) {
                if (i4 == 5) {
                    i16 = 500000;
                } else if (i4 == 8) {
                    i16 = 1000000;
                    i4 = 8;
                }
                if (i14 != -1) {
                    i10 = zzgds.zza(i14, 8, RoundingMode.CEILING);
                } else {
                    i10 = zzsb.zzb(i4);
                }
                i8 = i6;
                i9 = zzgea.zzb((((long) i16) * ((long) i10)) / 1000000);
            } else {
                i8 = i6;
                i9 = zzgea.zzb((((long) zzsb.zzb(i4)) * 50000000) / 1000000);
            }
            this.zzX = false;
            zzrm zzrm = new zzrm(zzan, i7, i8, i5, i3, i2, i4, (((Math.max(minBufferSize, (int) ((double) i9)) + i15) - 1) / i15) * i15, zzdw, false, false, false);
            if (zzY()) {
                this.zzr = zzrm;
            } else {
                this.zzs = zzrm;
            }
        } else {
            throw new zzqq("Invalid output channel config (mode=" + i6 + ") for: " + String.valueOf(zzan), zzan2);
        }
    }

    public final void zzf() {
        zzrr zzrr;
        if (zzY()) {
            this.zzD = 0;
            this.zzE = 0;
            this.zzF = 0;
            this.zzG = 0;
            this.zzY = false;
            this.zzH = 0;
            this.zzA = new zzrp(this.zzB, 0, 0, (zzro) null);
            this.zzK = 0;
            this.zzz = null;
            this.zzk.clear();
            this.zzM = null;
            this.zzN = 0;
            this.zzO = null;
            this.zzQ = false;
            this.zzP = false;
            this.zzR = false;
            this.zzf.zzp();
            zzV();
            if (this.zzj.zzh()) {
                this.zzu.pause();
            }
            if (zzZ(this.zzu)) {
                zzrx zzrx = this.zzl;
                zzrx.getClass();
                zzrx.zzb(this.zzu);
            }
            int i = zzgd.zza;
            zzqp zzb2 = this.zzs.zzb();
            zzrm zzrm = this.zzr;
            if (zzrm != null) {
                this.zzs = zzrm;
                this.zzr = null;
            }
            this.zzj.zzc();
            if (zzgd.zza >= 24 && (zzrr = this.zzx) != null) {
                zzrr.zzb();
                this.zzx = null;
            }
            AudioTrack audioTrack = this.zzu;
            zzeu zzeu = this.zzi;
            zzqs zzqs = this.zzq;
            zzeu.zzc();
            Handler handler = new Handler(Looper.myLooper());
            synchronized (zza) {
                if (zzb == null) {
                    zzb = zzgd.zzE("ExoPlayer:AudioTrackReleaseThread");
                }
                zzc++;
                zzb.execute(new zzre(audioTrack, zzqs, handler, zzb2, zzeu));
            }
            this.zzu = null;
        }
        this.zzn.zza();
        this.zzm.zza();
        this.zzaa = 0;
        this.zzab = 0;
        Handler handler2 = this.zzac;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages((Object) null);
        }
    }

    public final void zzg() {
        this.zzI = true;
    }

    public final void zzh() {
        this.zzS = false;
        if (!zzY()) {
            return;
        }
        if (this.zzj.zzk() || zzZ(this.zzu)) {
            this.zzu.pause();
        }
    }

    public final void zzi() {
        this.zzS = true;
        if (zzY()) {
            this.zzj.zzf();
            this.zzu.play();
        }
    }

    public final void zzj() throws zzqu {
        if (!this.zzP && zzY() && zzX()) {
            zzR();
            this.zzP = true;
        }
    }

    public final void zzk() {
        zzpw zzpw = this.zzw;
        if (zzpw != null) {
            zzpw.zzi();
        }
    }

    public final void zzl() {
        zzf();
        zzgbc zzgbc = this.zzg;
        int size = zzgbc.size();
        for (int i = 0; i < size; i++) {
            ((zzdz) zzgbc.get(i)).zzf();
        }
        zzgbc zzgbc2 = this.zzh;
        int size2 = zzgbc2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((zzdz) zzgbc2.get(i2)).zzf();
        }
        zzdw zzdw = this.zzt;
        if (zzdw != null) {
            zzdw.zzf();
        }
        this.zzS = false;
        this.zzX = false;
    }

    public final void zzm(zzk zzk2) {
        if (!this.zzy.equals(zzk2)) {
            this.zzy = zzk2;
            zzpw zzpw = this.zzw;
            if (zzpw != null) {
                zzpw.zzg(zzk2);
            }
            zzf();
        }
    }

    public final void zzn(int i) {
        if (this.zzT != i) {
            this.zzT = i;
            zzf();
        }
    }

    public final void zzo(zzl zzl2) {
        if (!this.zzU.equals(zzl2)) {
            if (this.zzu != null) {
                int i = this.zzU.zza;
            }
            this.zzU = zzl2;
        }
    }

    public final void zzp(zzer zzer) {
        this.zzj.zze(zzer);
    }

    public final void zzq(zzqs zzqs) {
        this.zzq = zzqs;
    }

    public final void zzr(int i, int i2) {
        AudioTrack audioTrack = this.zzu;
        if (audioTrack != null) {
            zzZ(audioTrack);
        }
    }

    public final void zzs(zzcl zzcl) {
        this.zzB = new zzcl(Math.max(0.1f, Math.min(zzcl.zzc, 8.0f)), Math.max(0.1f, Math.min(zzcl.zzd, 8.0f)));
        zzT(zzcl);
    }

    public final void zzt(zzpj zzpj) {
        this.zzp = zzpj;
    }

    public final void zzv(boolean z) {
        this.zzC = z;
        zzT(this.zzB);
    }

    public final void zzw(float f) {
        if (this.zzL != f) {
            this.zzL = f;
            zzU();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0251, code lost:
        r0 = 1024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0261, code lost:
        r11 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x02fa, code lost:
        r1.zzH = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x02fc, code lost:
        if (r0 == 0) goto L_0x02ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x02ff, code lost:
        return r11;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzx(java.nio.ByteBuffer r28, long r29, int r31) throws com.google.android.gms.internal.ads.zzqr, com.google.android.gms.internal.ads.zzqu {
        /*
            r27 = this;
            r1 = r27
            r2 = r28
            r3 = r29
            r5 = r31
            java.nio.ByteBuffer r0 = r1.zzM
            r7 = 0
            if (r0 == 0) goto L_0x0012
            if (r2 != r0) goto L_0x0010
            goto L_0x0012
        L_0x0010:
            r0 = r7
            goto L_0x0013
        L_0x0012:
            r0 = 1
        L_0x0013:
            com.google.android.gms.internal.ads.zzeq.zzd(r0)
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzr
            r8 = 0
            if (r0 == 0) goto L_0x006f
            boolean r0 = r27.zzX()
            if (r0 != 0) goto L_0x0022
            return r7
        L_0x0022:
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzr
            com.google.android.gms.internal.ads.zzrm r9 = r1.zzs
            int r10 = r9.zzc
            int r11 = r0.zzc
            if (r10 != r11) goto L_0x005f
            int r10 = r9.zzg
            int r11 = r0.zzg
            if (r10 != r11) goto L_0x005f
            int r10 = r9.zze
            int r11 = r0.zze
            if (r10 != r11) goto L_0x005f
            int r10 = r9.zzf
            int r11 = r0.zzf
            if (r10 != r11) goto L_0x005f
            int r10 = r9.zzd
            int r11 = r0.zzd
            if (r10 != r11) goto L_0x005f
            boolean r10 = r9.zzj
            boolean r10 = r0.zzj
            boolean r9 = r9.zzk
            boolean r9 = r0.zzk
            r1.zzs = r0
            r1.zzr = r8
            android.media.AudioTrack r0 = r1.zzu
            if (r0 == 0) goto L_0x006c
            boolean r0 = zzZ(r0)
            if (r0 == 0) goto L_0x006c
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs
            boolean r0 = r0.zzk
            goto L_0x006c
        L_0x005f:
            r27.zzR()
            boolean r0 = r27.zzy()
            if (r0 == 0) goto L_0x0069
            return r7
        L_0x0069:
            r27.zzf()
        L_0x006c:
            r1.zzO(r3)
        L_0x006f:
            boolean r0 = r27.zzY()
            if (r0 == 0) goto L_0x0077
            goto L_0x016a
        L_0x0077:
            com.google.android.gms.internal.ads.zzeu r0 = r1.zzi     // Catch:{ zzqr -> 0x03c5 }
            boolean r0 = r0.zzd()     // Catch:{ zzqr -> 0x03c5 }
            if (r0 != 0) goto L_0x0080
            return r7
        L_0x0080:
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs     // Catch:{ zzqr -> 0x008a }
            if (r0 == 0) goto L_0x0089
            android.media.AudioTrack r0 = r1.zzN(r0)     // Catch:{ zzqr -> 0x008a }
            goto L_0x00d2
        L_0x0089:
            throw r8     // Catch:{ zzqr -> 0x008a }
        L_0x008a:
            r0 = move-exception
            r11 = r0
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs     // Catch:{ zzqr -> 0x03c5 }
            int r12 = r0.zzh     // Catch:{ zzqr -> 0x03c5 }
            r13 = 1000000(0xf4240, float:1.401298E-39)
            if (r12 <= r13) goto L_0x03c1
            com.google.android.gms.internal.ads.zzrm r12 = new com.google.android.gms.internal.ads.zzrm     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzan r15 = r0.zza     // Catch:{ zzqr -> 0x03c5 }
            int r13 = r0.zzb     // Catch:{ zzqr -> 0x03c5 }
            int r14 = r0.zzc     // Catch:{ zzqr -> 0x03c5 }
            int r8 = r0.zzd     // Catch:{ zzqr -> 0x03c5 }
            int r7 = r0.zze     // Catch:{ zzqr -> 0x03c5 }
            int r6 = r0.zzf     // Catch:{ zzqr -> 0x03c5 }
            int r10 = r0.zzg     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzdw r9 = r0.zzi     // Catch:{ zzqr -> 0x03c5 }
            r16 = r14
            boolean r14 = r0.zzj     // Catch:{ zzqr -> 0x03c5 }
            boolean r14 = r0.zzk     // Catch:{ zzqr -> 0x03c5 }
            boolean r0 = r0.zzl     // Catch:{ zzqr -> 0x03c5 }
            r25 = 0
            r26 = 0
            r22 = 1000000(0xf4240, float:1.401298E-39)
            r24 = 0
            r0 = r16
            r14 = r12
            r16 = r13
            r17 = r0
            r18 = r8
            r19 = r7
            r20 = r6
            r21 = r10
            r23 = r9
            r14.<init>(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26)     // Catch:{ zzqr -> 0x03c5 }
            android.media.AudioTrack r0 = r1.zzN(r12)     // Catch:{ zzqr -> 0x03bd }
            r1.zzs = r12     // Catch:{ zzqr -> 0x03bd }
        L_0x00d2:
            r1.zzu = r0     // Catch:{ zzqr -> 0x03c5 }
            boolean r0 = zzZ(r0)     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x00f0
            android.media.AudioTrack r0 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzrx r6 = r1.zzl     // Catch:{ zzqr -> 0x03c5 }
            if (r6 != 0) goto L_0x00e7
            com.google.android.gms.internal.ads.zzrx r6 = new com.google.android.gms.internal.ads.zzrx     // Catch:{ zzqr -> 0x03c5 }
            r6.<init>(r1)     // Catch:{ zzqr -> 0x03c5 }
            r1.zzl = r6     // Catch:{ zzqr -> 0x03c5 }
        L_0x00e7:
            com.google.android.gms.internal.ads.zzrx r6 = r1.zzl     // Catch:{ zzqr -> 0x03c5 }
            r6.zza(r0)     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs     // Catch:{ zzqr -> 0x03c5 }
            boolean r0 = r0.zzk     // Catch:{ zzqr -> 0x03c5 }
        L_0x00f0:
            int r0 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ zzqr -> 0x03c5 }
            r6 = 31
            if (r0 < r6) goto L_0x00ff
            com.google.android.gms.internal.ads.zzpj r0 = r1.zzp     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x00ff
            android.media.AudioTrack r6 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzrj.zza(r6, r0)     // Catch:{ zzqr -> 0x03c5 }
        L_0x00ff:
            android.media.AudioTrack r0 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            int r0 = r0.getAudioSessionId()     // Catch:{ zzqr -> 0x03c5 }
            r1.zzT = r0     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzqz r6 = r1.zzj     // Catch:{ zzqr -> 0x03c5 }
            android.media.AudioTrack r7 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs     // Catch:{ zzqr -> 0x03c5 }
            int r8 = r0.zzc     // Catch:{ zzqr -> 0x03c5 }
            r9 = 2
            if (r8 != r9) goto L_0x0114
            r8 = 1
            goto L_0x0115
        L_0x0114:
            r8 = 0
        L_0x0115:
            int r9 = r0.zzg     // Catch:{ zzqr -> 0x03c5 }
            int r10 = r0.zzd     // Catch:{ zzqr -> 0x03c5 }
            int r11 = r0.zzh     // Catch:{ zzqr -> 0x03c5 }
            r6.zzd(r7, r8, r9, r10, r11)     // Catch:{ zzqr -> 0x03c5 }
            r27.zzU()     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzl r0 = r1.zzU     // Catch:{ zzqr -> 0x03c5 }
            int r0 = r0.zza     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzpx r0 = r1.zzV     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x013f
            int r6 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ zzqr -> 0x03c5 }
            r7 = 23
            if (r6 < r7) goto L_0x013f
            android.media.AudioTrack r6 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzri.zza(r6, r0)     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzpw r0 = r1.zzw     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x013f
            com.google.android.gms.internal.ads.zzpx r6 = r1.zzV     // Catch:{ zzqr -> 0x03c5 }
            android.media.AudioDeviceInfo r6 = r6.zza     // Catch:{ zzqr -> 0x03c5 }
            r0.zzh(r6)     // Catch:{ zzqr -> 0x03c5 }
        L_0x013f:
            int r0 = com.google.android.gms.internal.ads.zzgd.zza     // Catch:{ zzqr -> 0x03c5 }
            r6 = 24
            if (r0 < r6) goto L_0x0152
            com.google.android.gms.internal.ads.zzpw r0 = r1.zzw     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x0152
            com.google.android.gms.internal.ads.zzrr r6 = new com.google.android.gms.internal.ads.zzrr     // Catch:{ zzqr -> 0x03c5 }
            android.media.AudioTrack r7 = r1.zzu     // Catch:{ zzqr -> 0x03c5 }
            r6.<init>(r7, r0)     // Catch:{ zzqr -> 0x03c5 }
            r1.zzx = r6     // Catch:{ zzqr -> 0x03c5 }
        L_0x0152:
            r6 = 1
            r1.zzJ = r6     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzqs r0 = r1.zzq     // Catch:{ zzqr -> 0x03c5 }
            if (r0 == 0) goto L_0x016a
            com.google.android.gms.internal.ads.zzrm r6 = r1.zzs     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzqp r6 = r6.zzb()     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzse r0 = (com.google.android.gms.internal.ads.zzse) r0     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzsf r0 = r0.zza     // Catch:{ zzqr -> 0x03c5 }
            com.google.android.gms.internal.ads.zzqn r0 = r0.zzc     // Catch:{ zzqr -> 0x03c5 }
            r0.zzc(r6)     // Catch:{ zzqr -> 0x03c5 }
        L_0x016a:
            com.google.android.gms.internal.ads.zzrs r0 = r1.zzm
            r0.zza()
            boolean r0 = r1.zzJ
            r6 = 0
            if (r0 == 0) goto L_0x018a
            long r8 = java.lang.Math.max(r6, r3)
            r1.zzK = r8
            r8 = 0
            r1.zzI = r8
            r1.zzJ = r8
            r1.zzO(r3)
            boolean r0 = r1.zzS
            if (r0 == 0) goto L_0x018a
            r27.zzi()
        L_0x018a:
            com.google.android.gms.internal.ads.zzqz r0 = r1.zzj
            long r8 = r27.zzM()
            boolean r0 = r0.zzj(r8)
            if (r0 != 0) goto L_0x0198
            r8 = 0
            return r8
        L_0x0198:
            java.nio.ByteBuffer r0 = r1.zzM
            if (r0 != 0) goto L_0x0390
            java.nio.ByteOrder r0 = r28.order()
            java.nio.ByteOrder r8 = java.nio.ByteOrder.LITTLE_ENDIAN
            if (r0 != r8) goto L_0x01a6
            r0 = 1
            goto L_0x01a7
        L_0x01a6:
            r0 = 0
        L_0x01a7:
            com.google.android.gms.internal.ads.zzeq.zzd(r0)
            boolean r0 = r28.hasRemaining()
            if (r0 != 0) goto L_0x01b2
            r8 = 1
            return r8
        L_0x01b2:
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs
            int r8 = r0.zzc
            if (r8 == 0) goto L_0x0300
            int r8 = r1.zzH
            if (r8 != 0) goto L_0x0300
            int r0 = r0.zzg
            r8 = -2
            r9 = 16
            r10 = 1024(0x400, float:1.435E-42)
            r11 = -1
            switch(r0) {
                case 5: goto L_0x02f5;
                case 6: goto L_0x02f5;
                case 7: goto L_0x026a;
                case 8: goto L_0x026a;
                case 9: goto L_0x0253;
                case 10: goto L_0x0251;
                case 11: goto L_0x024e;
                case 12: goto L_0x024e;
                case 13: goto L_0x01c7;
                case 14: goto L_0x01ff;
                case 15: goto L_0x01fc;
                case 16: goto L_0x0251;
                case 17: goto L_0x01e1;
                case 18: goto L_0x02f5;
                case 19: goto L_0x01c7;
                case 20: goto L_0x01db;
                default: goto L_0x01c7;
            }
        L_0x01c7:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Unexpected audio encoding: "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r2.<init>(r0)
            throw r2
        L_0x01db:
            int r0 = com.google.android.gms.internal.ads.zzaep.zzb(r28)
            goto L_0x0261
        L_0x01e1:
            int r0 = com.google.android.gms.internal.ads.zzacw.zza
            byte[] r0 = new byte[r9]
            int r8 = r28.position()
            r2.get(r0)
            r2.position(r8)
            com.google.android.gms.internal.ads.zzft r8 = new com.google.android.gms.internal.ads.zzft
            r8.<init>(r0, r9)
            com.google.android.gms.internal.ads.zzacv r0 = com.google.android.gms.internal.ads.zzacw.zza(r8)
            int r0 = r0.zzc
            goto L_0x0261
        L_0x01fc:
            r0 = 512(0x200, float:7.175E-43)
            goto L_0x0261
        L_0x01ff:
            int r0 = com.google.android.gms.internal.ads.zzact.zza
            int r0 = r28.position()
            int r10 = r28.limit()
            int r10 = r10 + -10
            r12 = r0
        L_0x020c:
            if (r12 > r10) goto L_0x021f
            int r13 = r12 + 4
            int r13 = com.google.android.gms.internal.ads.zzgd.zzi(r2, r13)
            r13 = r13 & r8
            r14 = -126718022(0xfffffffff8726fba, float:-1.966878E34)
            if (r13 != r14) goto L_0x021c
            int r12 = r12 - r0
            goto L_0x0220
        L_0x021c:
            int r12 = r12 + 1
            goto L_0x020c
        L_0x021f:
            r12 = r11
        L_0x0220:
            if (r12 != r11) goto L_0x0224
            r0 = 0
            goto L_0x0261
        L_0x0224:
            int r0 = r28.position()
            int r0 = r0 + r12
            int r0 = r0 + 7
            byte r0 = r2.get(r0)
            r0 = r0 & 255(0xff, float:3.57E-43)
            int r8 = r28.position()
            int r8 = r8 + r12
            r10 = 187(0xbb, float:2.62E-43)
            if (r0 != r10) goto L_0x023d
            r0 = 9
            goto L_0x023f
        L_0x023d:
            r0 = 8
        L_0x023f:
            int r8 = r8 + r0
            byte r0 = r2.get(r8)
            int r0 = r0 >> 4
            r0 = r0 & 7
            r8 = 40
            int r0 = r8 << r0
            int r0 = r0 * r9
            goto L_0x0261
        L_0x024e:
            r0 = 2048(0x800, float:2.87E-42)
            goto L_0x0261
        L_0x0251:
            r0 = r10
            goto L_0x0261
        L_0x0253:
            int r0 = r28.position()
            int r0 = com.google.android.gms.internal.ads.zzgd.zzi(r2, r0)
            int r0 = com.google.android.gms.internal.ads.zzaeo.zzc(r0)
            if (r0 == r11) goto L_0x0264
        L_0x0261:
            r11 = 1
            goto L_0x02fa
        L_0x0264:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>()
            throw r0
        L_0x026a:
            int r0 = com.google.android.gms.internal.ads.zzadr.zza
            r9 = 0
            int r0 = r2.getInt(r9)
            r12 = -233094848(0xfffffffff21b4140, float:-3.0751398E30)
            if (r0 == r12) goto L_0x02f2
            int r0 = r2.getInt(r9)
            r12 = -398277519(0xffffffffe842c471, float:-3.6790512E24)
            if (r0 != r12) goto L_0x0280
            goto L_0x0251
        L_0x0280:
            int r0 = r2.getInt(r9)
            r9 = 622876772(0x25205864, float:1.3907736E-16)
            if (r0 != r9) goto L_0x028c
            r0 = 4096(0x1000, float:5.74E-42)
            goto L_0x0261
        L_0x028c:
            int r0 = r28.position()
            byte r9 = r2.get(r0)
            if (r9 == r8) goto L_0x02d9
            if (r9 == r11) goto L_0x02c2
            r8 = 31
            if (r9 == r8) goto L_0x02b0
            int r8 = r0 + 4
            int r0 = r0 + 5
            byte r8 = r2.get(r8)
            r9 = 1
            r8 = r8 & r9
            int r8 = r8 << 6
            byte r0 = r2.get(r0)
            r0 = r0 & 252(0xfc, float:3.53E-43)
            r9 = 2
            goto L_0x02d5
        L_0x02b0:
            r9 = 2
            int r8 = r0 + 5
            byte r8 = r2.get(r8)
            r8 = r8 & 7
            int r8 = r8 << 4
            int r0 = r0 + 6
            byte r0 = r2.get(r0)
            goto L_0x02d3
        L_0x02c2:
            r9 = 2
            int r8 = r0 + 4
            byte r8 = r2.get(r8)
            r8 = r8 & 7
            int r8 = r8 << 4
            int r0 = r0 + 7
            byte r0 = r2.get(r0)
        L_0x02d3:
            r0 = r0 & 60
        L_0x02d5:
            int r0 = r0 >> r9
            r0 = r0 | r8
            r11 = 1
            goto L_0x02ee
        L_0x02d9:
            r9 = 2
            int r8 = r0 + 4
            int r0 = r0 + 5
            byte r0 = r2.get(r0)
            r11 = 1
            r0 = r0 & r11
            int r0 = r0 << 6
            byte r8 = r2.get(r8)
            r8 = r8 & 252(0xfc, float:3.53E-43)
            int r8 = r8 >> r9
            r0 = r0 | r8
        L_0x02ee:
            int r0 = r0 + r11
            int r0 = r0 * 32
            goto L_0x02fa
        L_0x02f2:
            r11 = 1
            r0 = r10
            goto L_0x02fa
        L_0x02f5:
            r11 = 1
            int r0 = com.google.android.gms.internal.ads.zzact.zza(r28)
        L_0x02fa:
            r1.zzH = r0
            if (r0 == 0) goto L_0x02ff
            goto L_0x0300
        L_0x02ff:
            return r11
        L_0x0300:
            com.google.android.gms.internal.ads.zzrp r0 = r1.zzz
            if (r0 == 0) goto L_0x0312
            boolean r0 = r27.zzX()
            if (r0 != 0) goto L_0x030c
            r8 = 0
            return r8
        L_0x030c:
            r1.zzO(r3)
            r8 = 0
            r1.zzz = r8
        L_0x0312:
            long r8 = r1.zzK
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs
            long r10 = r27.zzL()
            com.google.android.gms.internal.ads.zzsj r12 = r1.zzf
            long r12 = r12.zzo()
            long r10 = r10 - r12
            com.google.android.gms.internal.ads.zzan r0 = r0.zza
            int r0 = r0.zzB
            long r10 = com.google.android.gms.internal.ads.zzgd.zzs(r10, r0)
            long r8 = r8 + r10
            boolean r0 = r1.zzI
            if (r0 != 0) goto L_0x034a
            long r10 = r8 - r3
            long r10 = java.lang.Math.abs(r10)
            r12 = 200000(0x30d40, double:9.8813E-319)
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 <= 0) goto L_0x034a
            com.google.android.gms.internal.ads.zzqs r0 = r1.zzq
            if (r0 == 0) goto L_0x0347
            com.google.android.gms.internal.ads.zzqt r10 = new com.google.android.gms.internal.ads.zzqt
            r10.<init>(r3, r8)
            r0.zza(r10)
        L_0x0347:
            r10 = 1
            r1.zzI = r10
        L_0x034a:
            boolean r0 = r1.zzI
            if (r0 == 0) goto L_0x0371
            boolean r0 = r27.zzX()
            r10 = 0
            if (r0 != 0) goto L_0x0356
            return r10
        L_0x0356:
            long r8 = r3 - r8
            long r11 = r1.zzK
            long r11 = r11 + r8
            r1.zzK = r11
            r1.zzI = r10
            r1.zzO(r3)
            com.google.android.gms.internal.ads.zzqs r0 = r1.zzq
            if (r0 == 0) goto L_0x0371
            int r6 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r6 == 0) goto L_0x0371
            com.google.android.gms.internal.ads.zzse r0 = (com.google.android.gms.internal.ads.zzse) r0
            com.google.android.gms.internal.ads.zzsf r0 = r0.zza
            r0.zzao()
        L_0x0371:
            com.google.android.gms.internal.ads.zzrm r0 = r1.zzs
            int r0 = r0.zzc
            if (r0 != 0) goto L_0x0382
            long r6 = r1.zzD
            int r0 = r28.remaining()
            long r8 = (long) r0
            long r6 = r6 + r8
            r1.zzD = r6
            goto L_0x038c
        L_0x0382:
            long r6 = r1.zzE
            int r0 = r1.zzH
            long r8 = (long) r0
            long r10 = (long) r5
            long r8 = r8 * r10
            long r6 = r6 + r8
            r1.zzE = r6
        L_0x038c:
            r1.zzM = r2
            r1.zzN = r5
        L_0x0390:
            r1.zzS(r3)
            java.nio.ByteBuffer r0 = r1.zzM
            boolean r0 = r0.hasRemaining()
            if (r0 != 0) goto L_0x03a3
            r2 = 0
            r1.zzM = r2
            r2 = 0
            r1.zzN = r2
            r3 = 1
            return r3
        L_0x03a3:
            r2 = 0
            r3 = 1
            com.google.android.gms.internal.ads.zzqz r0 = r1.zzj
            long r4 = r27.zzM()
            boolean r0 = r0.zzi(r4)
            if (r0 == 0) goto L_0x03bc
            java.lang.String r0 = "DefaultAudioSink"
            java.lang.String r2 = "Resetting stalled audio track"
            com.google.android.gms.internal.ads.zzfk.zzf(r0, r2)
            r27.zzf()
            return r3
        L_0x03bc:
            return r2
        L_0x03bd:
            r0 = move-exception
            r11.addSuppressed(r0)     // Catch:{ zzqr -> 0x03c5 }
        L_0x03c1:
            r27.zzP()     // Catch:{ zzqr -> 0x03c5 }
            throw r11     // Catch:{ zzqr -> 0x03c5 }
        L_0x03c5:
            r0 = move-exception
            boolean r2 = r0.zzb
            if (r2 != 0) goto L_0x03d1
            com.google.android.gms.internal.ads.zzrs r2 = r1.zzm
            r2.zzb(r0)
            r2 = 0
            return r2
        L_0x03d1:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzrz.zzx(java.nio.ByteBuffer, long, int):boolean");
    }

    public final boolean zzy() {
        if (zzY()) {
            return (zzgd.zza < 29 || !FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(this.zzu) || !this.zzR) && this.zzj.zzg(zzM());
        }
        return false;
    }

    public final boolean zzz() {
        if (!zzY()) {
            return true;
        }
        if (this.zzP) {
            return !zzy();
        }
        return false;
    }

    public final void zzu(AudioDeviceInfo audioDeviceInfo) {
        zzpx zzpx;
        if (audioDeviceInfo == null) {
            zzpx = null;
        } else {
            zzpx = new zzpx(audioDeviceInfo);
        }
        this.zzV = zzpx;
        zzpw zzpw = this.zzw;
        if (zzpw != null) {
            zzpw.zzh(audioDeviceInfo);
        }
        AudioTrack audioTrack = this.zzu;
        if (audioTrack != null) {
            zzri.zza(audioTrack, this.zzV);
        }
    }
}
