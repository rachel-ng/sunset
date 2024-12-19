package com.google.android.gms.internal.ads;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Trace;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public abstract class zztv extends zziw {
    private static final byte[] zzb = {0, 0, 1, 103, 66, -64, 11, -38, 37, -112, 0, 0, 1, 104, -50, 15, 19, 32, 0, 0, 1, 101, -120, -124, 13, -50, 113, Ascii.CAN, -96, 0, 47, -65, Ascii.FS, 49, -61, 39, 93, 120};
    private boolean zzA;
    private boolean zzB;
    private boolean zzC;
    private boolean zzD;
    private boolean zzE;
    private long zzF;
    private int zzG;
    private int zzH;
    private ByteBuffer zzI;
    private boolean zzJ;
    private boolean zzK;
    private boolean zzL;
    private boolean zzM;
    private boolean zzN;
    private boolean zzO;
    private int zzP;
    private int zzQ;
    private int zzR;
    private boolean zzS;
    private boolean zzT;
    private boolean zzU;
    private long zzV;
    private long zzW;
    private boolean zzX;
    private boolean zzY;
    private boolean zzZ;
    protected zzix zza;
    private zztu zzaa;
    private long zzab;
    private boolean zzac;
    private zzsu zzad;
    private zzsu zzae;
    private final zztl zzc;
    private final zztx zzd;
    private final float zze;
    private final zzin zzf = new zzin(0, 0);
    private final zzin zzg = new zzin(0, 0);
    private final zzin zzh = new zzin(2, 0);
    private final zzti zzi;
    private final MediaCodec.BufferInfo zzj;
    private final ArrayDeque zzk;
    private final zzsg zzl;
    private zzan zzm;
    private zzan zzn;
    private MediaCrypto zzo;
    private float zzp;
    private zztm zzq;
    private zzan zzr;
    private MediaFormat zzs;
    private boolean zzt;
    private float zzu;
    private ArrayDeque zzv;
    private zztt zzw;
    private zztp zzx;
    private int zzy;
    private boolean zzz;

    public zztv(int i, zztl zztl, zztx zztx, boolean z, float f) {
        super(i);
        this.zzc = zztl;
        this.zzd = zztx;
        this.zze = f;
        zzti zzti = new zzti();
        this.zzi = zzti;
        this.zzj = new MediaCodec.BufferInfo();
        this.zzp = 1.0f;
        this.zzk = new ArrayDeque();
        this.zzaa = zztu.zza;
        zzti.zzi(0);
        zzti.zzc.order(ByteOrder.nativeOrder());
        this.zzl = new zzsg();
        this.zzu = -1.0f;
        this.zzy = 0;
        this.zzP = 0;
        this.zzG = -1;
        this.zzH = -1;
        this.zzF = C.TIME_UNSET;
        this.zzV = C.TIME_UNSET;
        this.zzW = C.TIME_UNSET;
        this.zzab = C.TIME_UNSET;
        this.zzQ = 0;
        this.zzR = 0;
        this.zza = new zzix();
    }

    protected static boolean zzaL(zzan zzan) {
        return zzan.zzH == 0;
    }

    private final void zzaM() {
        this.zzG = -1;
        this.zzg.zzc = null;
    }

    private final void zzaN() {
        this.zzH = -1;
        this.zzI = null;
    }

    private final void zzaO(zztu zztu) {
        this.zzaa = zztu;
        if (zztu.zzd != C.TIME_UNSET) {
            this.zzac = true;
        }
    }

    private final void zzaP() throws zzjh {
        zzsu zzsu = this.zzae;
        zzsu.getClass();
        this.zzad = zzsu;
        this.zzQ = 0;
        this.zzR = 0;
    }

    private final boolean zzaQ() throws zzjh {
        if (this.zzS) {
            this.zzQ = 1;
            if (this.zzA) {
                this.zzR = 3;
                return false;
            }
            this.zzR = 2;
        } else {
            zzaP();
        }
        return true;
    }

    private final boolean zzaR() throws zzjh {
        zztm zztm = this.zzq;
        if (zztm == null || this.zzQ == 2 || this.zzX) {
            return false;
        }
        if (this.zzG < 0) {
            int zza2 = zztm.zza();
            this.zzG = zza2;
            if (zza2 < 0) {
                return false;
            }
            this.zzg.zzc = zztm.zzf(zza2);
            this.zzg.zzb();
        }
        if (this.zzQ == 1) {
            if (!this.zzE) {
                this.zzT = true;
                zztm.zzj(this.zzG, 0, 0, 0, 4);
                zzaM();
            }
            this.zzQ = 2;
            return false;
        } else if (this.zzC) {
            this.zzC = false;
            ByteBuffer byteBuffer = this.zzg.zzc;
            byteBuffer.getClass();
            byteBuffer.put(zzb);
            zztm.zzj(this.zzG, 0, 38, 0, 0);
            zzaM();
            this.zzS = true;
            return true;
        } else {
            if (this.zzP == 1) {
                int i = 0;
                while (true) {
                    zzan zzan = this.zzr;
                    zzan.getClass();
                    if (i >= zzan.zzp.size()) {
                        break;
                    }
                    ByteBuffer byteBuffer2 = this.zzg.zzc;
                    byteBuffer2.getClass();
                    byteBuffer2.put((byte[]) this.zzr.zzp.get(i));
                    i++;
                }
                this.zzP = 2;
            }
            ByteBuffer byteBuffer3 = this.zzg.zzc;
            byteBuffer3.getClass();
            int position = byteBuffer3.position();
            zzlj zzcX = zzcX();
            try {
                int zzcV = zzcV(zzcX, this.zzg, 0);
                if (zzcV == -3) {
                    if (zzQ()) {
                        this.zzW = this.zzV;
                    }
                    return false;
                } else if (zzcV == -5) {
                    if (this.zzP == 2) {
                        this.zzg.zzb();
                        this.zzP = 1;
                    }
                    zzac(zzcX);
                    return true;
                } else {
                    zzin zzin = this.zzg;
                    if (zzin.zzf()) {
                        this.zzW = this.zzV;
                        if (this.zzP == 2) {
                            zzin.zzb();
                            this.zzP = 1;
                        }
                        this.zzX = true;
                        if (!this.zzS) {
                            zzao();
                            return false;
                        }
                        try {
                            if (!this.zzE) {
                                this.zzT = true;
                                zztm.zzj(this.zzG, 0, 0, 0, 4);
                                zzaM();
                            }
                            return false;
                        } catch (MediaCodec.CryptoException e) {
                            throw zzi(e, this.zzm, false, zzgd.zzj(e.getErrorCode()));
                        }
                    } else if (this.zzS || zzin.zzg()) {
                        boolean zzk2 = zzin.zzk();
                        if (zzk2) {
                            zzin.zzb.zzb(position);
                        }
                        long j = this.zzg.zze;
                        if (this.zzZ) {
                            if (!this.zzk.isEmpty()) {
                                zzga zzga = ((zztu) this.zzk.peekLast()).zze;
                                zzan zzan2 = this.zzm;
                                zzan2.getClass();
                                zzga.zzd(j, zzan2);
                            } else {
                                zzga zzga2 = this.zzaa.zze;
                                zzan zzan3 = this.zzm;
                                zzan3.getClass();
                                zzga2.zzd(j, zzan3);
                            }
                            this.zzZ = false;
                        }
                        long max = Math.max(this.zzV, j);
                        this.zzV = max;
                        if (zzQ() || this.zzg.zzh()) {
                            this.zzW = max;
                        }
                        this.zzg.zzj();
                        zzin zzin2 = this.zzg;
                        if (zzin2.zze()) {
                            zzaj(zzin2);
                        }
                        zzaB(this.zzg);
                        zzat(this.zzg);
                        if (zzk2) {
                            try {
                                zztm.zzk(this.zzG, 0, this.zzg.zzb, j, 0);
                            } catch (MediaCodec.CryptoException e2) {
                                throw zzi(e2, this.zzm, false, zzgd.zzj(e2.getErrorCode()));
                            }
                        } else {
                            int i2 = this.zzG;
                            ByteBuffer byteBuffer4 = this.zzg.zzc;
                            if (byteBuffer4 != null) {
                                zztm.zzj(i2, 0, byteBuffer4.limit(), j, 0);
                            } else {
                                throw null;
                            }
                        }
                        zzaM();
                        this.zzS = true;
                        this.zzP = 0;
                        this.zza.zzc++;
                        return true;
                    } else {
                        zzin.zzb();
                        if (this.zzP == 2) {
                            this.zzP = 1;
                        }
                        return true;
                    }
                }
            } catch (zzim e3) {
                zzak(e3);
                zzaU(0);
                zzah();
                return true;
            }
        }
    }

    private final boolean zzaS() {
        return this.zzH >= 0;
    }

    private final boolean zzaT(long j, long j2) {
        if (j2 >= j) {
            return false;
        }
        zzan zzan = this.zzn;
        if (zzan == null || !Objects.equals(zzan.zzn, MimeTypes.AUDIO_OPUS)) {
            return true;
        }
        return !zzaep.zzf(j, j2);
    }

    private final boolean zzaU(int i) throws zzjh {
        zzin zzin = this.zzf;
        zzlj zzcX = zzcX();
        zzin.zzb();
        int zzcV = zzcV(zzcX, this.zzf, i | 4);
        if (zzcV == -5) {
            zzac(zzcX);
            return true;
        } else if (zzcV != -4 || !this.zzf.zzf()) {
            return false;
        } else {
            this.zzX = true;
            zzao();
            return false;
        }
    }

    private final boolean zzaV(zzan zzan) throws zzjh {
        if (!(zzgd.zza < 23 || this.zzq == null || this.zzR == 3 || zzcU() == 0)) {
            float f = this.zzp;
            zzan.getClass();
            float zzZ2 = zzZ(f, zzan, zzT());
            float f2 = this.zzu;
            if (f2 != zzZ2) {
                if (zzZ2 == -1.0f) {
                    zzae();
                    return false;
                } else if (f2 != -1.0f || zzZ2 > this.zze) {
                    Bundle bundle = new Bundle();
                    bundle.putFloat("operating-rate", zzZ2);
                    zztm zztm = this.zzq;
                    zztm.getClass();
                    zztm.zzp(bundle);
                    this.zzu = zzZ2;
                }
            }
        }
        return true;
    }

    private final void zzad() {
        this.zzN = false;
        this.zzi.zzb();
        this.zzh.zzb();
        this.zzM = false;
        this.zzL = false;
        this.zzl.zzb();
    }

    private final void zzae() throws zzjh {
        if (this.zzS) {
            this.zzQ = 1;
            this.zzR = 3;
            return;
        }
        zzaD();
        zzaz();
    }

    private final void zzah() {
        try {
            zztm zztm = this.zzq;
            zzeq.zzb(zztm);
            zztm.zzi();
        } finally {
            zzaE();
        }
    }

    /* JADX INFO: finally extract failed */
    private final void zzai(zztp zztp, MediaCrypto mediaCrypto) throws Exception {
        float f;
        zztk zztk;
        boolean z;
        int i;
        boolean z2;
        zztk zztk2;
        zztp zztp2 = zztp;
        zzan zzan = this.zzm;
        zzan.getClass();
        String str = zztp2.zza;
        if (zzgd.zza < 23) {
            f = -1.0f;
        } else {
            f = zzZ(this.zzp, zzan, zzT());
        }
        if (f <= this.zze) {
            f = -1.0f;
        }
        zzaC(zzan);
        zzh();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        zztk zzaf = zzaf(zztp2, zzan, (MediaCrypto) null, f);
        if (zzgd.zza >= 31) {
            zzts.zza(zzaf, zzn());
        }
        try {
            Trace.beginSection("createCodec:" + str);
            this.zzq = this.zzc.zzd(zzaf);
            Trace.endSection();
            zzh();
            long elapsedRealtime2 = SystemClock.elapsedRealtime();
            if (!zztp2.zze(zzan)) {
                StringBuilder sb = new StringBuilder();
                sb.append("id=");
                sb.append(zzan.zzb);
                sb.append(", mimeType=");
                sb.append(zzan.zzn);
                if (zzan.zzm != null) {
                    sb.append(", container=");
                    sb.append(zzan.zzm);
                }
                if (zzan.zzj != -1) {
                    sb.append(", bitrate=");
                    sb.append(zzan.zzj);
                }
                if (zzan.zzk != null) {
                    sb.append(", codecs=");
                    sb.append(zzan.zzk);
                }
                if (zzan.zzq != null) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    int i2 = 0;
                    while (true) {
                        zzae zzae2 = zzan.zzq;
                        if (i2 >= zzae2.zzb) {
                            break;
                        }
                        UUID uuid = zzae2.zza(i2).zza;
                        if (uuid.equals(zzo.zzb)) {
                            linkedHashSet.add(C.CENC_TYPE_cenc);
                        } else if (uuid.equals(zzo.zzc)) {
                            linkedHashSet.add("clearkey");
                        } else if (uuid.equals(zzo.zze)) {
                            linkedHashSet.add("playready");
                        } else if (uuid.equals(zzo.zzd)) {
                            linkedHashSet.add("widevine");
                        } else if (uuid.equals(zzo.zza)) {
                            linkedHashSet.add("universal");
                        } else {
                            zztk2 = zzaf;
                            linkedHashSet.add("unknown (" + uuid.toString() + ")");
                            i2++;
                            zzaf = zztk2;
                        }
                        zztk2 = zzaf;
                        i2++;
                        zzaf = zztk2;
                    }
                    zztk = zzaf;
                    sb.append(", drm=[");
                    zzfxv.zzb(sb, linkedHashSet, ",");
                    sb.append(']');
                } else {
                    zztk = zzaf;
                }
                if (!(zzan.zzs == -1 || zzan.zzt == -1)) {
                    sb.append(", res=");
                    sb.append(zzan.zzs);
                    sb.append("x");
                    sb.append(zzan.zzt);
                }
                zzt zzt2 = zzan.zzz;
                if (zzt2 != null && (zzt2.zze() || zzt2.zzf())) {
                    sb.append(", color=");
                    sb.append(zzan.zzz.zzd());
                }
                if (zzan.zzu != -1.0f) {
                    sb.append(", fps=");
                    sb.append(zzan.zzu);
                }
                if (zzan.zzA != -1) {
                    sb.append(", channels=");
                    sb.append(zzan.zzA);
                }
                if (zzan.zzB != -1) {
                    sb.append(", sample_rate=");
                    sb.append(zzan.zzB);
                }
                if (zzan.zze != null) {
                    sb.append(", language=");
                    sb.append(zzan.zze);
                }
                if (!zzan.zzd.isEmpty()) {
                    sb.append(", labels=[");
                    zzfxv.zzb(sb, zzan.zzd, ",");
                    sb.append("]");
                }
                if (zzan.zzf != 0) {
                    sb.append(", selectionFlags=[");
                    int i3 = zzan.zzf;
                    ArrayList arrayList = new ArrayList();
                    if ((i3 & 1) != 0) {
                        arrayList.add("default");
                    }
                    if ((i3 & 2) != 0) {
                        arrayList.add("forced");
                    }
                    zzfxv.zzb(sb, arrayList, ",");
                    sb.append("]");
                }
                if (zzan.zzg != 0) {
                    sb.append(", roleFlags=[");
                    int i4 = zzan.zzg;
                    ArrayList arrayList2 = new ArrayList();
                    if ((i4 & 1) != 0) {
                        arrayList2.add("main");
                    }
                    if ((i4 & 2) != 0) {
                        arrayList2.add("alt");
                    }
                    if ((i4 & 4) != 0) {
                        arrayList2.add("supplementary");
                    }
                    if ((i4 & 8) != 0) {
                        arrayList2.add("commentary");
                    }
                    if ((i4 & 16) != 0) {
                        arrayList2.add("dub");
                    }
                    if ((i4 & 32) != 0) {
                        arrayList2.add("emergency");
                    }
                    if ((i4 & 64) != 0) {
                        arrayList2.add("caption");
                    }
                    if ((i4 & 128) != 0) {
                        arrayList2.add("subtitle");
                    }
                    if ((i4 & 256) != 0) {
                        arrayList2.add("sign");
                    }
                    if ((i4 & 512) != 0) {
                        arrayList2.add("describes-video");
                    }
                    if ((i4 & 1024) != 0) {
                        arrayList2.add("describes-music");
                    }
                    if ((i4 & 2048) != 0) {
                        arrayList2.add("enhanced-intelligibility");
                    }
                    if ((i4 & 4096) != 0) {
                        arrayList2.add("transcribes-dialog");
                    }
                    if ((i4 & 8192) != 0) {
                        arrayList2.add("easy-read");
                    }
                    if ((i4 & 16384) != 0) {
                        arrayList2.add("trick-play");
                    }
                    zzfxv.zzb(sb, arrayList2, ",");
                    sb.append("]");
                }
                z = false;
                zzfk.zzf("MediaCodecRenderer", String.format(Locale.US, "Format exceeds selected codec's capabilities [%s, %s]", new Object[]{sb.toString(), str}));
            } else {
                zztk = zzaf;
                z = false;
            }
            this.zzx = zztp2;
            this.zzu = f;
            this.zzr = zzan;
            if (zzgd.zza > 25 || !"OMX.Exynos.avc.dec.secure".equals(str) || (!zzgd.zzd.startsWith("SM-T585") && !zzgd.zzd.startsWith("SM-A510") && !zzgd.zzd.startsWith("SM-A520") && !zzgd.zzd.startsWith("SM-J700"))) {
                i = (zzgd.zza >= 24 || (!"OMX.Nvidia.h264.decode".equals(str) && !"OMX.Nvidia.h264.decode.secure".equals(str)) || (!"flounder".equals(zzgd.zzb) && !"flounder_lte".equals(zzgd.zzb) && !"grouper".equals(zzgd.zzb) && !"tilapia".equals(zzgd.zzb))) ? z : 1;
            } else {
                i = 2;
            }
            this.zzy = i;
            this.zzr.getClass();
            this.zzz = (zzgd.zza != 29 || !"c2.android.aac.decoder".equals(str)) ? z : true;
            this.zzA = (zzgd.zza > 23 || !"OMX.google.vorbis.decoder".equals(str)) ? z : true;
            this.zzB = (zzgd.zza != 21 || !"OMX.google.aac.decoder".equals(str)) ? z : true;
            String str2 = zztp2.zza;
            if ((zzgd.zza > 25 || !"OMX.rk.video_decoder.avc".equals(str2)) && ((zzgd.zza > 29 || (!"OMX.broadcom.video_decoder.tunnel".equals(str2) && !"OMX.broadcom.video_decoder.tunnel.secure".equals(str2) && !"OMX.bcm.vdec.avc.tunnel".equals(str2) && !"OMX.bcm.vdec.avc.tunnel.secure".equals(str2) && !"OMX.bcm.vdec.hevc.tunnel".equals(str2) && !"OMX.bcm.vdec.hevc.tunnel.secure".equals(str2))) && (!"Amazon".equals(zzgd.zzc) || !"AFTS".equals(zzgd.zzd) || !zztp2.zzf))) {
                z2 = z;
            } else {
                z2 = true;
            }
            this.zzE = z2;
            this.zzq.getClass();
            if (zzcU() == 2) {
                zzh();
                this.zzF = SystemClock.elapsedRealtime() + 1000;
            }
            this.zza.zza++;
            zzal(str, zztk, elapsedRealtime2, elapsedRealtime2 - elapsedRealtime);
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void zzC() {
        try {
            zzad();
            zzaD();
        } finally {
            this.zzae = null;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0036, code lost:
        if (r5 >= r1) goto L_0x0038;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzF(com.google.android.gms.internal.ads.zzan[] r13, long r14, long r16, com.google.android.gms.internal.ads.zzvo r18) throws com.google.android.gms.internal.ads.zzjh {
        /*
            r12 = this;
            r0 = r12
            com.google.android.gms.internal.ads.zztu r1 = r0.zzaa
            long r1 = r1.zzd
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0020
            com.google.android.gms.internal.ads.zztu r1 = new com.google.android.gms.internal.ads.zztu
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.zzaO(r1)
            return
        L_0x0020:
            java.util.ArrayDeque r1 = r0.zzk
            boolean r1 = r1.isEmpty()
            if (r1 == 0) goto L_0x0055
            long r1 = r0.zzV
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 == 0) goto L_0x0038
            long r5 = r0.zzab
            int r7 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0055
            int r1 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
            if (r1 < 0) goto L_0x0055
        L_0x0038:
            com.google.android.gms.internal.ads.zztu r1 = new com.google.android.gms.internal.ads.zztu
            r6 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r5 = r1
            r8 = r14
            r10 = r16
            r5.<init>(r6, r8, r10)
            r12.zzaO(r1)
            com.google.android.gms.internal.ads.zztu r1 = r0.zzaa
            long r1 = r1.zzd
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L_0x0054
            r12.zzap()
        L_0x0054:
            return
        L_0x0055:
            java.util.ArrayDeque r1 = r0.zzk
            com.google.android.gms.internal.ads.zztu r9 = new com.google.android.gms.internal.ads.zztu
            long r3 = r0.zzV
            r2 = r9
            r5 = r14
            r7 = r16
            r2.<init>(r3, r5, r7)
            r1.add(r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztv.zzF(com.google.android.gms.internal.ads.zzan[], long, long, com.google.android.gms.internal.ads.zzvo):void");
    }

    public void zzM(float f, float f2) throws zzjh {
        this.zzp = f2;
        zzaV(this.zzr);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v25, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v28, resolved type: android.media.MediaCodec$BufferInfo} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v21, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v88, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v89, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v41, resolved type: com.google.android.gms.internal.ads.zzan} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v44, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v45, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v58, resolved type: com.google.android.gms.internal.ads.zzan} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v59, resolved type: com.google.android.gms.internal.ads.zztv} */
    /* JADX WARNING: type inference failed for: r18v0 */
    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r18v2 */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: type inference failed for: r18v19 */
    /* JADX WARNING: Can't wrap try/catch for region: R(3:136|137|(1:139)(0)) */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x019d, code lost:
        r15.zzM = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01fc, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:?, code lost:
        zzao();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0201, code lost:
        if (r15.zzY != false) goto L_0x0203;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0203, code lost:
        zzaD();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x02e1, code lost:
        if (r15.zzn != null) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0336, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:218:?, code lost:
        zzao();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x0339, code lost:
        r15 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x033b, code lost:
        r18 = r18;
        r1 = r1;
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:?, code lost:
        r0 = r15.zzY;
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:222:0x033d, code lost:
        if (r0 != false) goto L_0x033f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x033f, code lost:
        zzaD();
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0344, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0345, code lost:
        r15 = r22;
        r18 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:246:0x03a8, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x03a9, code lost:
        r18 = r18;
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0090, code lost:
        r14 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0091, code lost:
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r15.zzY = true;
        r15 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0093, code lost:
        r13 = false;
        r15 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:136:0x01fc */
    /* JADX WARNING: Missing exception handler attribute for start block: B:217:0x0336 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:269:0x03e2  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x0405  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x0407  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x040b  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zzV(long r23, long r25) throws com.google.android.gms.internal.ads.zzjh {
        /*
            r22 = this;
            r15 = r22
            r14 = 1
            boolean r0 = r15.zzY     // Catch:{ IllegalStateException -> 0x03d6 }
            if (r0 == 0) goto L_0x000b
            r22.zzaq()     // Catch:{ IllegalStateException -> 0x03d6 }
            return
        L_0x000b:
            com.google.android.gms.internal.ads.zzan r0 = r15.zzm     // Catch:{ IllegalStateException -> 0x03d6 }
            r11 = 2
            if (r0 != 0) goto L_0x0018
            boolean r0 = r15.zzaU(r11)     // Catch:{ IllegalStateException -> 0x03d6 }
            if (r0 == 0) goto L_0x0017
            goto L_0x0018
        L_0x0017:
            return
        L_0x0018:
            r22.zzaz()     // Catch:{ IllegalStateException -> 0x03d6 }
            boolean r0 = r15.zzL     // Catch:{ IllegalStateException -> 0x03d6 }
            if (r0 == 0) goto L_0x01d2
            java.lang.String r0 = "bypassRender"
            android.os.Trace.beginSection(r0)     // Catch:{ IllegalStateException -> 0x01ce }
        L_0x0024:
            boolean r0 = r15.zzY     // Catch:{ IllegalStateException -> 0x01ce }
            r0 = r0 ^ r14
            com.google.android.gms.internal.ads.zzeq.zzf(r0)     // Catch:{ IllegalStateException -> 0x01ce }
            com.google.android.gms.internal.ads.zzti r0 = r15.zzi     // Catch:{ IllegalStateException -> 0x01ce }
            boolean r1 = r0.zzp()     // Catch:{ IllegalStateException -> 0x01ce }
            if (r1 == 0) goto L_0x008b
            java.nio.ByteBuffer r7 = r0.zzc     // Catch:{ IllegalStateException -> 0x0086 }
            int r8 = r15.zzH     // Catch:{ IllegalStateException -> 0x0086 }
            int r10 = r0.zzl()     // Catch:{ IllegalStateException -> 0x0086 }
            long r4 = r0.zze     // Catch:{ IllegalStateException -> 0x0086 }
            long r1 = r22.zzf()     // Catch:{ IllegalStateException -> 0x0086 }
            long r12 = r0.zzm()     // Catch:{ IllegalStateException -> 0x0086 }
            boolean r13 = r15.zzaT(r1, r12)     // Catch:{ IllegalStateException -> 0x0086 }
            com.google.android.gms.internal.ads.zzti r0 = r15.zzi     // Catch:{ IllegalStateException -> 0x0086 }
            boolean r0 = r0.zzf()     // Catch:{ IllegalStateException -> 0x0086 }
            com.google.android.gms.internal.ads.zzan r11 = r15.zzn     // Catch:{ IllegalStateException -> 0x0086 }
            if (r11 == 0) goto L_0x0084
            r6 = 0
            r9 = 0
            r1 = r22
            r2 = r23
            r18 = r4
            r4 = r25
            r17 = r11
            r11 = r18
            r14 = r0
            r15 = r17
            boolean r0 = r1.zzar(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)     // Catch:{ IllegalStateException -> 0x0080 }
            if (r0 == 0) goto L_0x007a
            r15 = r22
            com.google.android.gms.internal.ads.zzti r0 = r15.zzi     // Catch:{ IllegalStateException -> 0x0086 }
            long r0 = r0.zzm()     // Catch:{ IllegalStateException -> 0x0086 }
            r15.zzaA(r0)     // Catch:{ IllegalStateException -> 0x0086 }
            com.google.android.gms.internal.ads.zzti r0 = r15.zzi     // Catch:{ IllegalStateException -> 0x0086 }
            r0.zzb()     // Catch:{ IllegalStateException -> 0x0086 }
            goto L_0x008b
        L_0x007a:
            r15 = r22
            r13 = 0
            r14 = 1
            goto L_0x01c0
        L_0x0080:
            r0 = move-exception
            r15 = r22
            goto L_0x0087
        L_0x0084:
            r0 = 0
            throw r0     // Catch:{ IllegalStateException -> 0x0086 }
        L_0x0086:
            r0 = move-exception
        L_0x0087:
            r1 = r15
            r2 = 1
            goto L_0x03d9
        L_0x008b:
            r0 = 0
            boolean r1 = r15.zzX     // Catch:{ IllegalStateException -> 0x01c9 }
            if (r1 == 0) goto L_0x0096
            r14 = 1
            r15.zzY = r14     // Catch:{ IllegalStateException -> 0x03d6 }
            r13 = 0
            goto L_0x01c0
        L_0x0096:
            r14 = 1
            boolean r1 = r15.zzM     // Catch:{ IllegalStateException -> 0x01ce }
            if (r1 == 0) goto L_0x00aa
            com.google.android.gms.internal.ads.zzti r1 = r15.zzi     // Catch:{ IllegalStateException -> 0x01ce }
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x01ce }
            boolean r1 = r1.zzo(r2)     // Catch:{ IllegalStateException -> 0x01ce }
            com.google.android.gms.internal.ads.zzeq.zzf(r1)     // Catch:{ IllegalStateException -> 0x01ce }
            r13 = 0
            r15.zzM = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x00ab
        L_0x00aa:
            r13 = 0
        L_0x00ab:
            boolean r1 = r15.zzN     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x00c3
            com.google.android.gms.internal.ads.zzti r1 = r15.zzi     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r1 = r1.zzp()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 != 0) goto L_0x0024
            r22.zzad()     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzN = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            r22.zzaz()     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r1 = r15.zzL     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x01c0
        L_0x00c3:
            boolean r1 = r15.zzX     // Catch:{ IllegalStateException -> 0x03d0 }
            r1 = r1 ^ r14
            com.google.android.gms.internal.ads.zzeq.zzf(r1)     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzlj r1 = r22.zzcX()     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            r2.zzb()     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x00d2:
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            r2.zzb()     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r15.zzcV(r1, r2, r13)     // Catch:{ IllegalStateException -> 0x03d0 }
            r3 = -5
            if (r2 == r3) goto L_0x01a0
            r3 = -4
            if (r2 == r3) goto L_0x00e5
            goto L_0x01a3
        L_0x00e5:
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = r2.zzf()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x00f1
            r15.zzX = r14     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x01a3
        L_0x00f1:
            boolean r2 = r15.zzZ     // Catch:{ IllegalStateException -> 0x03d0 }
            java.lang.String r3 = "audio/opus"
            if (r2 == 0) goto L_0x0139
            com.google.android.gms.internal.ads.zzan r2 = r15.zzm     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0138
            r15.zzn = r2     // Catch:{ IllegalStateException -> 0x03d0 }
            java.lang.String r2 = r2.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = java.util.Objects.equals(r2, r3)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0130
            com.google.android.gms.internal.ads.zzan r2 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            java.util.List r2 = r2.zzp     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = r2.isEmpty()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 != 0) goto L_0x0130
            com.google.android.gms.internal.ads.zzan r2 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            java.util.List r2 = r2.zzp     // Catch:{ IllegalStateException -> 0x03d0 }
            java.lang.Object r2 = r2.get(r13)     // Catch:{ IllegalStateException -> 0x03d0 }
            byte[] r2 = (byte[]) r2     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = com.google.android.gms.internal.ads.zzaep.zza(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r4 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r4 == 0) goto L_0x012f
            com.google.android.gms.internal.ads.zzal r4 = r4.zzb()     // Catch:{ IllegalStateException -> 0x03d0 }
            r4.zzF(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r2 = r4.zzad()     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzn = r2     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x0130
        L_0x012f:
            throw r0     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x0130:
            com.google.android.gms.internal.ads.zzan r2 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzan(r2, r0)     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzZ = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x0139
        L_0x0138:
            throw r0     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x0139:
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            r2.zzj()     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r2 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0174
            java.lang.String r2 = r2.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = java.util.Objects.equals(r2, r3)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0174
            com.google.android.gms.internal.ads.zzin r2 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r3 = r2.zze()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r3 == 0) goto L_0x0159
            com.google.android.gms.internal.ads.zzan r3 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            r2.zza = r3     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzaj(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x0159:
            long r2 = r22.zzf()     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzin r4 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            long r5 = r4.zze     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = com.google.android.gms.internal.ads.zzaep.zzf(r2, r5)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0174
            com.google.android.gms.internal.ads.zzsg r2 = r15.zzl     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r3 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r3 == 0) goto L_0x0173
            java.util.List r3 = r3.zzp     // Catch:{ IllegalStateException -> 0x03d0 }
            r2.zza(r4, r3)     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x0174
        L_0x0173:
            throw r0     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x0174:
            com.google.android.gms.internal.ads.zzti r2 = r15.zzi     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r3 = r2.zzp()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r3 != 0) goto L_0x017d
            goto L_0x0193
        L_0x017d:
            long r3 = r22.zzf()     // Catch:{ IllegalStateException -> 0x03d0 }
            long r5 = r2.zzm()     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = r15.zzaT(r3, r5)     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzin r5 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            long r5 = r5.zze     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r3 = r15.zzaT(r3, r5)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 != r3) goto L_0x019d
        L_0x0193:
            com.google.android.gms.internal.ads.zzti r2 = r15.zzi     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzin r3 = r15.zzh     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = r2.zzo(r3)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 != 0) goto L_0x00d2
        L_0x019d:
            r15.zzM = r14     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x01a3
        L_0x01a0:
            r15.zzac(r1)     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x01a3:
            com.google.android.gms.internal.ads.zzti r1 = r15.zzi     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r2 = r1.zzp()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x01ae
            r1.zzj()     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x01ae:
            com.google.android.gms.internal.ads.zzti r1 = r15.zzi     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r1 = r1.zzp()     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 != 0) goto L_0x0024
            boolean r1 = r15.zzX     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 != 0) goto L_0x0024
            boolean r1 = r15.zzN     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x01c0
            goto L_0x0024
        L_0x01c0:
            android.os.Trace.endSection()     // Catch:{ IllegalStateException -> 0x03d0 }
            r18 = r13
            r2 = r14
            r1 = r15
            goto L_0x03c5
        L_0x01c9:
            r0 = move-exception
            r13 = 0
            r14 = 1
            goto L_0x03d1
        L_0x01ce:
            r0 = move-exception
            r13 = 0
            goto L_0x03d1
        L_0x01d2:
            r0 = 0
            r13 = 0
            com.google.android.gms.internal.ads.zztm r1 = r15.zzq     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x03b3
            r22.zzh()     // Catch:{ IllegalStateException -> 0x03ae }
            android.os.SystemClock.elapsedRealtime()     // Catch:{ IllegalStateException -> 0x03ae }
            java.lang.String r1 = "drainAndFeed"
            android.os.Trace.beginSection(r1)     // Catch:{ IllegalStateException -> 0x03ae }
        L_0x01e3:
            com.google.android.gms.internal.ads.zztm r6 = r15.zzq     // Catch:{ IllegalStateException -> 0x03ae }
            if (r6 == 0) goto L_0x03aa
            boolean r1 = r22.zzaS()     // Catch:{ IllegalStateException -> 0x03ae }
            if (r1 != 0) goto L_0x02f2
            boolean r1 = r15.zzB     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x0207
            boolean r1 = r15.zzT     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x0207
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x01fc }
            int r1 = r6.zzb(r1)     // Catch:{ IllegalStateException -> 0x01fc }
            goto L_0x020d
        L_0x01fc:
            r22.zzao()     // Catch:{ IllegalStateException -> 0x03d0 }
            boolean r0 = r15.zzY     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r0 == 0) goto L_0x023f
            r22.zzaD()     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x023f
        L_0x0207:
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r1 = r6.zzb(r1)     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x020d:
            if (r1 >= 0) goto L_0x0250
            r2 = -2
            if (r1 != r2) goto L_0x023b
            r15.zzU = r14     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zztm r1 = r15.zzq     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x023a
            android.media.MediaFormat r1 = r1.zzc()     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r15.zzy     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x0235
            java.lang.String r2 = "width"
            int r2 = r1.getInteger(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            r3 = 32
            if (r2 != r3) goto L_0x0235
            java.lang.String r2 = "height"
            int r2 = r1.getInteger(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 != r3) goto L_0x0235
            r15.zzD = r14     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x01e3
        L_0x0235:
            r15.zzs = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzt = r14     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x01e3
        L_0x023a:
            throw r0     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x023b:
            boolean r0 = r15.zzE     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r0 != 0) goto L_0x0244
        L_0x023f:
            r18 = r13
        L_0x0241:
            r1 = r15
            goto L_0x0397
        L_0x0244:
            boolean r0 = r15.zzX     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r0 != 0) goto L_0x024c
            int r0 = r15.zzQ     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r0 != r11) goto L_0x023f
        L_0x024c:
            r22.zzao()     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x023f
        L_0x0250:
            boolean r2 = r15.zzD     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x025a
            r15.zzD = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            r6.zzn(r1, r13)     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x01e3
        L_0x025a:
            android.media.MediaCodec$BufferInfo r2 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r2.size     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 != 0) goto L_0x026c
            android.media.MediaCodec$BufferInfo r2 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r2.flags     // Catch:{ IllegalStateException -> 0x03d0 }
            r2 = r2 & 4
            if (r2 == 0) goto L_0x026c
            r22.zzao()     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x023f
        L_0x026c:
            r15.zzH = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            java.nio.ByteBuffer r1 = r6.zzg(r1)     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzI = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x028b
            android.media.MediaCodec$BufferInfo r2 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r2.offset     // Catch:{ IllegalStateException -> 0x03d0 }
            r1.position(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            java.nio.ByteBuffer r1 = r15.zzI     // Catch:{ IllegalStateException -> 0x03d0 }
            android.media.MediaCodec$BufferInfo r2 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r2.offset     // Catch:{ IllegalStateException -> 0x03d0 }
            android.media.MediaCodec$BufferInfo r3 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            int r3 = r3.size     // Catch:{ IllegalStateException -> 0x03d0 }
            int r2 = r2 + r3
            r1.limit(r2)     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x028b:
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            long r1 = r1.presentationTimeUs     // Catch:{ IllegalStateException -> 0x03d0 }
            long r3 = r22.zzf()     // Catch:{ IllegalStateException -> 0x03d0 }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 >= 0) goto L_0x0299
            r1 = r14
            goto L_0x029a
        L_0x0299:
            r1 = r13
        L_0x029a:
            r15.zzJ = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            long r1 = r15.zzW     // Catch:{ IllegalStateException -> 0x03d0 }
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x02b1
            android.media.MediaCodec$BufferInfo r3 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            long r3 = r3.presentationTimeUs     // Catch:{ IllegalStateException -> 0x03d0 }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x02b1
            r1 = r14
            goto L_0x02b2
        L_0x02b1:
            r1 = r13
        L_0x02b2:
            r15.zzK = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x03d0 }
            long r1 = r1.presentationTimeUs     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zztu r3 = r15.zzaa     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzga r3 = r3.zze     // Catch:{ IllegalStateException -> 0x03d0 }
            java.lang.Object r1 = r3.zzc(r1)     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r1 = (com.google.android.gms.internal.ads.zzan) r1     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 != 0) goto L_0x02d6
            boolean r2 = r15.zzac     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x02d6
            android.media.MediaFormat r2 = r15.zzs     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r2 == 0) goto L_0x02d6
            com.google.android.gms.internal.ads.zztu r1 = r15.zzaa     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzga r1 = r1.zze     // Catch:{ IllegalStateException -> 0x03d0 }
            java.lang.Object r1 = r1.zzb()     // Catch:{ IllegalStateException -> 0x03d0 }
            com.google.android.gms.internal.ads.zzan r1 = (com.google.android.gms.internal.ads.zzan) r1     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x02d6:
            if (r1 == 0) goto L_0x02db
            r15.zzn = r1     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x02e3
        L_0x02db:
            boolean r1 = r15.zzt     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x02f2
            com.google.android.gms.internal.ads.zzan r1 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x02f2
        L_0x02e3:
            com.google.android.gms.internal.ads.zzan r1 = r15.zzn     // Catch:{ IllegalStateException -> 0x03d0 }
            if (r1 == 0) goto L_0x02f1
            android.media.MediaFormat r2 = r15.zzs     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzan(r1, r2)     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzt = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            r15.zzac = r13     // Catch:{ IllegalStateException -> 0x03d0 }
            goto L_0x02f2
        L_0x02f1:
            throw r0     // Catch:{ IllegalStateException -> 0x03d0 }
        L_0x02f2:
            boolean r1 = r15.zzB     // Catch:{ IllegalStateException -> 0x03ae }
            if (r1 == 0) goto L_0x0349
            boolean r1 = r15.zzT     // Catch:{ IllegalStateException -> 0x03ae }
            if (r1 == 0) goto L_0x0349
            java.nio.ByteBuffer r7 = r15.zzI     // Catch:{ IllegalStateException -> 0x0334 }
            int r8 = r15.zzH     // Catch:{ IllegalStateException -> 0x0334 }
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x0334 }
            int r9 = r1.flags     // Catch:{ IllegalStateException -> 0x0334 }
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x0334 }
            long r4 = r1.presentationTimeUs     // Catch:{ IllegalStateException -> 0x0334 }
            boolean r12 = r15.zzJ     // Catch:{ IllegalStateException -> 0x0334 }
            boolean r10 = r15.zzK     // Catch:{ IllegalStateException -> 0x0334 }
            com.google.android.gms.internal.ads.zzan r2 = r15.zzn     // Catch:{ IllegalStateException -> 0x0334 }
            if (r2 == 0) goto L_0x0331
            r16 = 1
            r1 = r22
            r17 = r2
            r2 = r23
            r18 = r4
            r4 = r25
            r20 = r10
            r10 = r16
            r21 = r11
            r16 = r12
            r11 = r18
            r18 = r13
            r13 = r16
            r14 = r20
            r15 = r17
            boolean r1 = r1.zzar(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)     // Catch:{ IllegalStateException -> 0x0336 }
            goto L_0x0373
        L_0x0331:
            r18 = r13
            throw r0     // Catch:{ IllegalStateException -> 0x0336 }
        L_0x0334:
            r18 = r13
        L_0x0336:
            r22.zzao()     // Catch:{ IllegalStateException -> 0x0344 }
            r15 = r22
            boolean r0 = r15.zzY     // Catch:{ IllegalStateException -> 0x03a8 }
            if (r0 == 0) goto L_0x0241
            r22.zzaD()     // Catch:{ IllegalStateException -> 0x03a8 }
            goto L_0x0241
        L_0x0344:
            r0 = move-exception
            r15 = r22
            goto L_0x03b1
        L_0x0349:
            r21 = r11
            r18 = r13
            java.nio.ByteBuffer r7 = r15.zzI     // Catch:{ IllegalStateException -> 0x03a8 }
            int r8 = r15.zzH     // Catch:{ IllegalStateException -> 0x03a8 }
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x03a8 }
            int r9 = r1.flags     // Catch:{ IllegalStateException -> 0x03a8 }
            android.media.MediaCodec$BufferInfo r1 = r15.zzj     // Catch:{ IllegalStateException -> 0x03a8 }
            long r11 = r1.presentationTimeUs     // Catch:{ IllegalStateException -> 0x03a8 }
            boolean r13 = r15.zzJ     // Catch:{ IllegalStateException -> 0x03a8 }
            boolean r14 = r15.zzK     // Catch:{ IllegalStateException -> 0x03a8 }
            com.google.android.gms.internal.ads.zzan r10 = r15.zzn     // Catch:{ IllegalStateException -> 0x03a8 }
            if (r10 == 0) goto L_0x03a6
            r16 = 1
            r1 = r22
            r2 = r23
            r4 = r25
            r17 = r10
            r10 = r16
            r15 = r17
            boolean r1 = r1.zzar(r2, r4, r6, r7, r8, r9, r10, r11, r13, r14, r15)     // Catch:{ IllegalStateException -> 0x03a2 }
        L_0x0373:
            if (r1 == 0) goto L_0x0395
            r1 = r22
            android.media.MediaCodec$BufferInfo r2 = r1.zzj     // Catch:{ IllegalStateException -> 0x03cd }
            long r2 = r2.presentationTimeUs     // Catch:{ IllegalStateException -> 0x03cd }
            r1.zzaA(r2)     // Catch:{ IllegalStateException -> 0x03cd }
            android.media.MediaCodec$BufferInfo r2 = r1.zzj     // Catch:{ IllegalStateException -> 0x03cd }
            int r2 = r2.flags     // Catch:{ IllegalStateException -> 0x03cd }
            r2 = r2 & 4
            r22.zzaN()     // Catch:{ IllegalStateException -> 0x03cd }
            if (r2 == 0) goto L_0x038d
            r22.zzao()     // Catch:{ IllegalStateException -> 0x03cd }
            goto L_0x0397
        L_0x038d:
            r15 = r1
            r13 = r18
            r11 = r21
            r14 = 1
            goto L_0x01e3
        L_0x0395:
            r1 = r22
        L_0x0397:
            boolean r0 = r22.zzaR()     // Catch:{ IllegalStateException -> 0x03cd }
            if (r0 != 0) goto L_0x0397
            android.os.Trace.endSection()     // Catch:{ IllegalStateException -> 0x03cd }
            r2 = 1
            goto L_0x03c5
        L_0x03a2:
            r0 = move-exception
            r1 = r22
            goto L_0x03ce
        L_0x03a6:
            r1 = r15
            throw r0     // Catch:{ IllegalStateException -> 0x03cd }
        L_0x03a8:
            r0 = move-exception
            goto L_0x03b1
        L_0x03aa:
            r18 = r13
            r1 = r15
            throw r0     // Catch:{ IllegalStateException -> 0x03cd }
        L_0x03ae:
            r0 = move-exception
            r18 = r13
        L_0x03b1:
            r1 = r15
            goto L_0x03ce
        L_0x03b3:
            r18 = r13
            r1 = r15
            com.google.android.gms.internal.ads.zzix r0 = r1.zza     // Catch:{ IllegalStateException -> 0x03cd }
            int r2 = r0.zzd     // Catch:{ IllegalStateException -> 0x03cd }
            int r3 = r22.zzd(r23)     // Catch:{ IllegalStateException -> 0x03cd }
            int r2 = r2 + r3
            r0.zzd = r2     // Catch:{ IllegalStateException -> 0x03cd }
            r2 = 1
            r1.zzaU(r2)     // Catch:{ IllegalStateException -> 0x03cb }
        L_0x03c5:
            com.google.android.gms.internal.ads.zzix r0 = r1.zza     // Catch:{ IllegalStateException -> 0x03cb }
            r0.zza()     // Catch:{ IllegalStateException -> 0x03cb }
            return
        L_0x03cb:
            r0 = move-exception
            goto L_0x03db
        L_0x03cd:
            r0 = move-exception
        L_0x03ce:
            r2 = 1
            goto L_0x03db
        L_0x03d0:
            r0 = move-exception
        L_0x03d1:
            r18 = r13
            r2 = r14
            r1 = r15
            goto L_0x03db
        L_0x03d6:
            r0 = move-exception
            r2 = r14
            r1 = r15
        L_0x03d9:
            r18 = 0
        L_0x03db:
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            boolean r3 = r0 instanceof android.media.MediaCodec.CodecException
            if (r3 == 0) goto L_0x03e2
            goto L_0x03f7
        L_0x03e2:
            java.lang.StackTraceElement[] r4 = r0.getStackTrace()
            int r5 = r4.length
            if (r5 <= 0) goto L_0x041d
            r4 = r4[r18]
            java.lang.String r4 = r4.getClassName()
            java.lang.String r5 = "android.media.MediaCodec"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x041d
        L_0x03f7:
            r1.zzak(r0)
            if (r3 == 0) goto L_0x0407
            r3 = r0
            android.media.MediaCodec$CodecException r3 = (android.media.MediaCodec.CodecException) r3
            boolean r3 = r3.isRecoverable()
            if (r3 == 0) goto L_0x0407
            r14 = r2
            goto L_0x0409
        L_0x0407:
            r14 = r18
        L_0x0409:
            if (r14 == 0) goto L_0x040e
            r22.zzaD()
        L_0x040e:
            com.google.android.gms.internal.ads.zztp r2 = r1.zzx
            com.google.android.gms.internal.ads.zzto r0 = r1.zzax(r0, r2)
            com.google.android.gms.internal.ads.zzan r2 = r1.zzm
            r3 = 4003(0xfa3, float:5.61E-42)
            com.google.android.gms.internal.ads.zzjh r0 = r1.zzi(r0, r2, r14, r3)
            throw r0
        L_0x041d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztv.zzV(long, long):void");
    }

    public boolean zzW() {
        return this.zzY;
    }

    public boolean zzX() {
        if (this.zzm == null) {
            return false;
        }
        if (zzS() || zzaS()) {
            return true;
        }
        if (this.zzF == C.TIME_UNSET) {
            return false;
        }
        zzh();
        return SystemClock.elapsedRealtime() < this.zzF;
    }

    public final int zzY(zzan zzan) throws zzjh {
        try {
            return zzaa(this.zzd, zzan);
        } catch (zzud e) {
            throw zzi(e, zzan, false, PlaybackException.ERROR_CODE_DECODER_QUERY_FAILED);
        }
    }

    /* access modifiers changed from: protected */
    public float zzZ(float f, zzan zzan, zzan[] zzanArr) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzaA(long j) {
        this.zzab = j;
        while (!this.zzk.isEmpty() && j >= ((zztu) this.zzk.peek()).zzb) {
            zztu zztu = (zztu) this.zzk.poll();
            zztu.getClass();
            zzaO(zztu);
            zzap();
        }
    }

    /* access modifiers changed from: protected */
    public void zzaB(zzin zzin) throws zzjh {
    }

    /* access modifiers changed from: protected */
    public void zzaC(zzan zzan) throws zzjh {
    }

    /* access modifiers changed from: protected */
    public final void zzaD() {
        try {
            zztm zztm = this.zzq;
            if (zztm != null) {
                zztm.zzl();
                this.zza.zzb++;
                zztp zztp = this.zzx;
                if (zztp != null) {
                    zzam(zztp.zza);
                } else {
                    throw null;
                }
            }
        } finally {
            this.zzq = null;
            this.zzo = null;
            this.zzad = null;
            zzaF();
        }
    }

    /* access modifiers changed from: protected */
    public void zzaE() {
        zzaM();
        zzaN();
        this.zzF = C.TIME_UNSET;
        this.zzT = false;
        this.zzS = false;
        this.zzC = false;
        this.zzD = false;
        this.zzJ = false;
        this.zzK = false;
        this.zzV = C.TIME_UNSET;
        this.zzW = C.TIME_UNSET;
        this.zzab = C.TIME_UNSET;
        this.zzQ = 0;
        this.zzR = 0;
        this.zzP = this.zzO ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    public final void zzaF() {
        zzaE();
        this.zzv = null;
        this.zzx = null;
        this.zzr = null;
        this.zzs = null;
        this.zzt = false;
        this.zzU = false;
        this.zzu = -1.0f;
        this.zzy = 0;
        this.zzz = false;
        this.zzA = false;
        this.zzB = false;
        this.zzE = false;
        this.zzO = false;
        this.zzP = 0;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaG() throws zzjh {
        boolean zzaH = zzaH();
        if (zzaH) {
            zzaz();
        }
        return zzaH;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaH() {
        if (this.zzq == null) {
            return false;
        }
        int i = this.zzR;
        if (i == 3 || ((this.zzz && !this.zzU) || (this.zzA && this.zzT))) {
            zzaD();
            return true;
        }
        if (i == 2) {
            zzeq.zzf(zzgd.zza >= 23);
            if (zzgd.zza >= 23) {
                try {
                    zzaP();
                } catch (zzjh e) {
                    zzfk.zzg("MediaCodecRenderer", "Failed to update the DRM session, releasing the codec instead.", e);
                    zzaD();
                    return true;
                }
            }
        }
        zzah();
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaI() {
        return this.zzL;
    }

    /* access modifiers changed from: protected */
    public final boolean zzaJ(zzan zzan) {
        return this.zzae == null && zzas(zzan);
    }

    /* access modifiers changed from: protected */
    public boolean zzaK(zztp zztp) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract int zzaa(zztx zztx, zzan zzan) throws zzud;

    /* access modifiers changed from: protected */
    public zziy zzab(zztp zztp, zzan zzan, zzan zzan2) {
        throw null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0068, code lost:
        if (zzaQ() == false) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0094, code lost:
        if (zzaQ() == false) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a7, code lost:
        if (zzaQ() == false) goto L_0x00be;
     */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00d5 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zziy zzac(com.google.android.gms.internal.ads.zzlj r12) throws com.google.android.gms.internal.ads.zzjh {
        /*
            r11 = this;
            r0 = 1
            r11.zzZ = r0
            com.google.android.gms.internal.ads.zzan r4 = r12.zza
            r4.getClass()
            java.lang.String r1 = r4.zzn
            r2 = 0
            if (r1 == 0) goto L_0x00e5
            com.google.android.gms.internal.ads.zzsu r12 = r12.zzb
            r11.zzae = r12
            r11.zzm = r4
            boolean r12 = r11.zzL
            r1 = 0
            if (r12 == 0) goto L_0x001b
            r11.zzN = r0
            return r1
        L_0x001b:
            com.google.android.gms.internal.ads.zztm r12 = r11.zzq
            if (r12 != 0) goto L_0x0025
            r11.zzv = r1
            r11.zzaz()
            return r1
        L_0x0025:
            com.google.android.gms.internal.ads.zztp r1 = r11.zzx
            r1.getClass()
            com.google.android.gms.internal.ads.zzan r3 = r11.zzr
            r3.getClass()
            com.google.android.gms.internal.ads.zzsu r5 = r11.zzad
            com.google.android.gms.internal.ads.zzsu r6 = r11.zzae
            if (r5 != r6) goto L_0x00d6
            if (r6 == r5) goto L_0x0039
            r5 = r0
            goto L_0x003a
        L_0x0039:
            r5 = r2
        L_0x003a:
            if (r5 == 0) goto L_0x0045
            int r6 = com.google.android.gms.internal.ads.zzgd.zza
            r7 = 23
            if (r6 < r7) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r6 = r2
            goto L_0x0046
        L_0x0045:
            r6 = r0
        L_0x0046:
            com.google.android.gms.internal.ads.zzeq.zzf(r6)
            com.google.android.gms.internal.ads.zziy r6 = r11.zzab(r1, r3, r4)
            int r7 = r6.zzd
            r8 = 3
            if (r7 == 0) goto L_0x00ba
            r9 = 16
            r10 = 2
            if (r7 == r0) goto L_0x0097
            if (r7 == r10) goto L_0x006b
            boolean r0 = r11.zzaV(r4)
            if (r0 != 0) goto L_0x0060
            goto L_0x009d
        L_0x0060:
            r11.zzr = r4
            if (r5 == 0) goto L_0x00bd
            boolean r0 = r11.zzaQ()
            if (r0 != 0) goto L_0x00bd
            goto L_0x00be
        L_0x006b:
            boolean r7 = r11.zzaV(r4)
            if (r7 != 0) goto L_0x0072
            goto L_0x009d
        L_0x0072:
            r11.zzO = r0
            r11.zzP = r0
            int r7 = r11.zzy
            if (r7 == r10) goto L_0x008a
            if (r7 != r0) goto L_0x0089
            int r7 = r4.zzs
            int r9 = r3.zzs
            if (r7 != r9) goto L_0x0089
            int r7 = r4.zzt
            int r9 = r3.zzt
            if (r7 != r9) goto L_0x0089
            goto L_0x008a
        L_0x0089:
            r0 = r2
        L_0x008a:
            r11.zzC = r0
            r11.zzr = r4
            if (r5 == 0) goto L_0x00bd
            boolean r0 = r11.zzaQ()
            if (r0 != 0) goto L_0x00bd
            goto L_0x00be
        L_0x0097:
            boolean r7 = r11.zzaV(r4)
            if (r7 != 0) goto L_0x009f
        L_0x009d:
            r10 = r9
            goto L_0x00be
        L_0x009f:
            r11.zzr = r4
            if (r5 == 0) goto L_0x00aa
            boolean r0 = r11.zzaQ()
            if (r0 != 0) goto L_0x00bd
            goto L_0x00be
        L_0x00aa:
            boolean r5 = r11.zzS
            if (r5 == 0) goto L_0x00bd
            r11.zzQ = r0
            boolean r5 = r11.zzA
            if (r5 == 0) goto L_0x00b7
            r11.zzR = r8
            goto L_0x00be
        L_0x00b7:
            r11.zzR = r0
            goto L_0x00bd
        L_0x00ba:
            r11.zzae()
        L_0x00bd:
            r10 = r2
        L_0x00be:
            int r0 = r6.zzd
            if (r0 == 0) goto L_0x00d5
            com.google.android.gms.internal.ads.zztm r0 = r11.zzq
            if (r0 != r12) goto L_0x00ca
            int r12 = r11.zzR
            if (r12 != r8) goto L_0x00d5
        L_0x00ca:
            java.lang.String r2 = r1.zza
            com.google.android.gms.internal.ads.zziy r12 = new com.google.android.gms.internal.ads.zziy
            r5 = 0
            r1 = r12
            r6 = r10
            r1.<init>(r2, r3, r4, r5, r6)
            return r12
        L_0x00d5:
            return r6
        L_0x00d6:
            r11.zzae()
            java.lang.String r2 = r1.zza
            com.google.android.gms.internal.ads.zziy r12 = new com.google.android.gms.internal.ads.zziy
            r5 = 0
            r6 = 128(0x80, float:1.794E-43)
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6)
            return r12
        L_0x00e5:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Sample MIME type is null."
            r12.<init>(r0)
            r0 = 4005(0xfa5, float:5.612E-42)
            com.google.android.gms.internal.ads.zzjh r12 = r11.zzi(r12, r4, r2, r0)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztv.zzac(com.google.android.gms.internal.ads.zzlj):com.google.android.gms.internal.ads.zziy");
    }

    /* access modifiers changed from: protected */
    public abstract zztk zzaf(zztp zztp, zzan zzan, MediaCrypto mediaCrypto, float f);

    /* access modifiers changed from: protected */
    public abstract List zzag(zztx zztx, zzan zzan, boolean z) throws zzud;

    /* access modifiers changed from: protected */
    public void zzaj(zzin zzin) throws zzjh {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzak(Exception exc) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzal(String str, zztk zztk, long j, long j2) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzam(String str) {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzan(zzan zzan, MediaFormat mediaFormat) throws zzjh {
        throw null;
    }

    /* access modifiers changed from: protected */
    public void zzap() {
    }

    /* access modifiers changed from: protected */
    public void zzaq() throws zzjh {
    }

    /* access modifiers changed from: protected */
    public abstract boolean zzar(long j, long j2, zztm zztm, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, zzan zzan) throws zzjh;

    /* access modifiers changed from: protected */
    public boolean zzas(zzan zzan) {
        return false;
    }

    /* access modifiers changed from: protected */
    public int zzat(zzin zzin) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public final long zzau() {
        return this.zzaa.zzd;
    }

    /* access modifiers changed from: protected */
    public final long zzav() {
        return this.zzaa.zzc;
    }

    /* access modifiers changed from: protected */
    public final zztm zzaw() {
        return this.zzq;
    }

    /* access modifiers changed from: protected */
    public zzto zzax(Throwable th, zztp zztp) {
        return new zzto(th, zztp);
    }

    /* access modifiers changed from: protected */
    public final zztp zzay() {
        return this.zzx;
    }

    /* access modifiers changed from: protected */
    public final void zzaz() throws zzjh {
        zzan zzan;
        zzan zzan2;
        if (this.zzq == null && !this.zzL && (zzan = this.zzm) != null) {
            if (zzaJ(zzan)) {
                zzad();
                String str = zzan.zzn;
                if (MimeTypes.AUDIO_AAC.equals(str) || MimeTypes.AUDIO_MPEG.equals(str) || MimeTypes.AUDIO_OPUS.equals(str)) {
                    this.zzi.zzn(32);
                } else {
                    this.zzi.zzn(1);
                }
                this.zzL = true;
                return;
            }
            zzsu zzsu = this.zzae;
            this.zzad = zzsu;
            if (zzsu != null) {
                zzeq.zzf(true);
                zzsu zzsu2 = this.zzad;
                boolean z = zzsv.zza;
                zzsu2.zza();
            }
            try {
                if (this.zzad != null) {
                    zzeq.zzb(zzan.zzn);
                }
                zzan2 = this.zzm;
                if (zzan2 != null) {
                    if (this.zzv == null) {
                        List zzag = zzag(this.zzd, zzan2, false);
                        zzag.isEmpty();
                        this.zzv = new ArrayDeque();
                        if (!zzag.isEmpty()) {
                            this.zzv.add((zztp) zzag.get(0));
                        }
                        this.zzw = null;
                    }
                    if (!this.zzv.isEmpty()) {
                        ArrayDeque arrayDeque = this.zzv;
                        if (arrayDeque != null) {
                            zztp zztp = (zztp) arrayDeque.peekFirst();
                            while (this.zzq == null) {
                                zztp zztp2 = (zztp) arrayDeque.peekFirst();
                                if (zztp2 == null) {
                                    throw null;
                                } else if (zzaK(zztp2)) {
                                    try {
                                        zzai(zztp2, (MediaCrypto) null);
                                    } catch (Exception e) {
                                        if (zztp2 == zztp) {
                                            zzfk.zzf("MediaCodecRenderer", "Preferred decoder instantiation failed. Sleeping for 50ms then retrying.");
                                            Thread.sleep(50);
                                            zzai(zztp2, (MediaCrypto) null);
                                        } else {
                                            throw e;
                                        }
                                    } catch (Exception e2) {
                                        zzfk.zzg("MediaCodecRenderer", "Failed to initialize decoder: ".concat(zztp2.zza), e2);
                                        arrayDeque.removeFirst();
                                        zztt zztt = new zztt(zzan2, (Throwable) e2, false, zztp2);
                                        zzak(zztt);
                                        zztt zztt2 = this.zzw;
                                        if (zztt2 == null) {
                                            this.zzw = zztt;
                                        } else {
                                            this.zzw = zztt.zza(zztt2, zztt);
                                        }
                                        if (arrayDeque.isEmpty()) {
                                            throw this.zzw;
                                        }
                                    }
                                } else {
                                    return;
                                }
                            }
                            this.zzv = null;
                            return;
                        }
                        throw null;
                    }
                    throw new zztt(zzan2, (Throwable) null, false, -49999);
                }
                throw null;
            } catch (zzud e3) {
                throw new zztt(zzan2, (Throwable) e3, false, -49998);
            } catch (zztt e4) {
                throw zzi(e4, zzan, false, PlaybackException.ERROR_CODE_DECODER_INIT_FAILED);
            }
        }
    }

    public final int zze() {
        return 8;
    }

    /* access modifiers changed from: protected */
    public void zzw() {
        this.zzm = null;
        zzaO(zztu.zza);
        this.zzk.clear();
        zzaH();
    }

    /* access modifiers changed from: protected */
    public void zzx(boolean z, boolean z2) throws zzjh {
        this.zza = new zzix();
    }

    /* access modifiers changed from: protected */
    public void zzz(long j, boolean z) throws zzjh {
        this.zzX = false;
        this.zzY = false;
        if (this.zzL) {
            this.zzi.zzb();
            this.zzh.zzb();
            this.zzM = false;
            this.zzl.zzb();
        } else {
            zzaG();
        }
        zzga zzga = this.zzaa.zze;
        if (zzga.zza() > 0) {
            this.zzZ = true;
        }
        zzga.zze();
        this.zzk.clear();
    }

    private final void zzao() throws zzjh {
        int i = this.zzR;
        if (i == 1) {
            zzah();
        } else if (i == 2) {
            zzah();
            zzaP();
        } else if (i != 3) {
            this.zzY = true;
            zzaq();
        } else {
            zzaD();
            zzaz();
        }
    }
}
