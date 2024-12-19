package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;
import java.util.Objects;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzsf extends zztv implements zzlp {
    private final Context zzb;
    /* access modifiers changed from: private */
    public final zzqn zzc;
    private final zzqv zzd;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private zzan zzh;
    private zzan zzi;
    private long zzj;
    private boolean zzk;
    private boolean zzl;
    /* access modifiers changed from: private */
    public zzmm zzm;
    /* access modifiers changed from: private */
    public boolean zzn;

    public zzsf(Context context, zztl zztl, zztx zztx, boolean z, Handler handler, zzqo zzqo, zzqv zzqv) {
        super(1, zztl, zztx, false, 44100.0f);
        this.zzb = context.getApplicationContext();
        this.zzd = zzqv;
        this.zzc = new zzqn(handler, zzqo);
        zzqv.zzq(new zzse(this, (zzsd) null));
    }

    private final int zzaM(zztp zztp, zzan zzan) {
        if (!"OMX.google.raw.decoder".equals(zztp.zza) || zzgd.zza >= 24 || (zzgd.zza == 23 && zzgd.zzN(this.zzb))) {
            return zzan.zzo;
        }
        return -1;
    }

    private static List zzaN(zztx zztx, zzan zzan, boolean z, zzqv zzqv) throws zzud {
        zztp zzb2;
        if (zzan.zzn == null) {
            return zzgbc.zzm();
        }
        if (!zzqv.zzA(zzan) || (zzb2 = zzuj.zzb()) == null) {
            return zzuj.zzf(zztx, zzan, false, false);
        }
        return zzgbc.zzn(zzb2);
    }

    private final void zzaO() {
        long zzb2 = this.zzd.zzb(zzW());
        if (zzb2 != Long.MIN_VALUE) {
            if (!this.zzk) {
                zzb2 = Math.max(this.zzj, zzb2);
            }
            this.zzj = zzb2;
            this.zzk = false;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzA() {
        this.zzd.zzk();
    }

    /* access modifiers changed from: protected */
    public final void zzC() {
        this.zzn = false;
        try {
            super.zzC();
            if (this.zzl) {
                this.zzl = false;
                this.zzd.zzl();
            }
        } catch (Throwable th) {
            if (this.zzl) {
                this.zzl = false;
                this.zzd.zzl();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public final void zzD() {
        this.zzd.zzi();
    }

    /* access modifiers changed from: protected */
    public final void zzE() {
        zzaO();
        this.zzd.zzh();
    }

    public final String zzU() {
        return "MediaCodecAudioRenderer";
    }

    public final boolean zzW() {
        return super.zzW() && this.zzd.zzz();
    }

    public final boolean zzX() {
        return this.zzd.zzy() || super.zzX();
    }

    /* access modifiers changed from: protected */
    public final float zzZ(float f, zzan zzan, zzan[] zzanArr) {
        int i = -1;
        for (zzan zzan2 : zzanArr) {
            int i2 = zzan2.zzB;
            if (i2 != -1) {
                i = Math.max(i, i2);
            }
        }
        if (i == -1) {
            return -1.0f;
        }
        return ((float) i) * f;
    }

    public final long zza() {
        if (zzcU() == 2) {
            zzaO();
        }
        return this.zzj;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzaa(com.google.android.gms.internal.ads.zztx r10, com.google.android.gms.internal.ads.zzan r11) throws com.google.android.gms.internal.ads.zzud {
        /*
            r9 = this;
            java.lang.String r0 = r11.zzn
            boolean r0 = com.google.android.gms.internal.ads.zzcg.zzg(r0)
            r1 = 128(0x80, float:1.794E-43)
            if (r0 != 0) goto L_0x000b
            return r1
        L_0x000b:
            int r0 = com.google.android.gms.internal.ads.zzgd.zza
            int r0 = r11.zzH
            boolean r2 = zzaL(r11)
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0046
            if (r0 == 0) goto L_0x001f
            com.google.android.gms.internal.ads.zztp r0 = com.google.android.gms.internal.ads.zzuj.zzb()
            if (r0 == 0) goto L_0x0046
        L_0x001f:
            com.google.android.gms.internal.ads.zzqv r0 = r9.zzd
            com.google.android.gms.internal.ads.zzqa r0 = r0.zzd(r11)
            boolean r5 = r0.zzb
            if (r5 != 0) goto L_0x002b
            r5 = r3
            goto L_0x003a
        L_0x002b:
            boolean r5 = r0.zzc
            if (r4 == r5) goto L_0x0032
            r5 = 512(0x200, float:7.175E-43)
            goto L_0x0034
        L_0x0032:
            r5 = 1536(0x600, float:2.152E-42)
        L_0x0034:
            boolean r0 = r0.zzd
            if (r0 == 0) goto L_0x003a
            r5 = r5 | 2048(0x800, float:2.87E-42)
        L_0x003a:
            com.google.android.gms.internal.ads.zzqv r0 = r9.zzd
            boolean r0 = r0.zzA(r11)
            if (r0 != 0) goto L_0x0043
            goto L_0x0047
        L_0x0043:
            r10 = r5 | 172(0xac, float:2.41E-43)
            return r10
        L_0x0046:
            r5 = r3
        L_0x0047:
            java.lang.String r0 = r11.zzn
            java.lang.String r6 = "audio/raw"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x005a
            com.google.android.gms.internal.ads.zzqv r0 = r9.zzd
            boolean r0 = r0.zzA(r11)
            if (r0 != 0) goto L_0x005a
            goto L_0x007c
        L_0x005a:
            com.google.android.gms.internal.ads.zzqv r0 = r9.zzd
            int r6 = r11.zzA
            int r7 = r11.zzB
            r8 = 2
            com.google.android.gms.internal.ads.zzan r6 = com.google.android.gms.internal.ads.zzgd.zzz(r8, r6, r7)
            boolean r0 = r0.zzA(r6)
            if (r0 != 0) goto L_0x006c
            goto L_0x007c
        L_0x006c:
            com.google.android.gms.internal.ads.zzqv r0 = r9.zzd
            java.util.List r10 = zzaN(r10, r11, r3, r0)
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L_0x0079
            goto L_0x007c
        L_0x0079:
            if (r2 != 0) goto L_0x007f
            r4 = r8
        L_0x007c:
            r10 = r4 | 128(0x80, float:1.794E-43)
            return r10
        L_0x007f:
            java.lang.Object r0 = r10.get(r3)
            com.google.android.gms.internal.ads.zztp r0 = (com.google.android.gms.internal.ads.zztp) r0
            boolean r2 = r0.zze(r11)
            if (r2 != 0) goto L_0x00a5
            r6 = r4
        L_0x008c:
            int r7 = r10.size()
            if (r6 >= r7) goto L_0x00a5
            java.lang.Object r7 = r10.get(r6)
            com.google.android.gms.internal.ads.zztp r7 = (com.google.android.gms.internal.ads.zztp) r7
            boolean r8 = r7.zze(r11)
            if (r8 == 0) goto L_0x00a2
            r10 = r3
            r2 = r4
            r0 = r7
            goto L_0x00a6
        L_0x00a2:
            int r6 = r6 + 1
            goto L_0x008c
        L_0x00a5:
            r10 = r4
        L_0x00a6:
            if (r4 == r2) goto L_0x00aa
            r6 = 3
            goto L_0x00ab
        L_0x00aa:
            r6 = 4
        L_0x00ab:
            r7 = 8
            if (r2 == 0) goto L_0x00b7
            boolean r11 = r0.zzf(r11)
            if (r11 == 0) goto L_0x00b7
            r7 = 16
        L_0x00b7:
            boolean r11 = r0.zzg
            if (r4 == r11) goto L_0x00bd
            r11 = r3
            goto L_0x00bf
        L_0x00bd:
            r11 = 64
        L_0x00bf:
            if (r4 == r10) goto L_0x00c2
            r1 = r3
        L_0x00c2:
            r10 = r6 | r7
            r10 = r10 | 32
            r10 = r10 | r11
            r10 = r10 | r1
            r10 = r10 | r5
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzsf.zzaa(com.google.android.gms.internal.ads.zztx, com.google.android.gms.internal.ads.zzan):int");
    }

    /* access modifiers changed from: protected */
    public final zziy zzab(zztp zztp, zzan zzan, zzan zzan2) {
        int i;
        int i2;
        zziy zzb2 = zztp.zzb(zzan, zzan2);
        int i3 = zzb2.zze;
        if (zzaJ(zzan2)) {
            i3 |= 32768;
        }
        if (zzaM(zztp, zzan2) > this.zze) {
            i3 |= 64;
        }
        String str = zztp.zza;
        if (i3 != 0) {
            i = i3;
            i2 = 0;
        } else {
            i2 = zzb2.zzd;
            i = 0;
        }
        return new zziy(str, zzan, zzan2, i2, i);
    }

    /* access modifiers changed from: protected */
    public final zztk zzaf(zztp zztp, zzan zzan, MediaCrypto mediaCrypto, float f) {
        int zzaM = zzaM(zztp, zzan);
        if (r0 != 1) {
            for (zzan zzan2 : zzT()) {
                if (zztp.zzb(zzan, zzan2).zzd != 0) {
                    zzaM = Math.max(zzaM, zzaM(zztp, zzan2));
                }
            }
        }
        this.zze = zzaM;
        this.zzf = zzgd.zza < 24 && "OMX.SEC.aac.dec".equals(zztp.zza) && "samsung".equals(zzgd.zzc) && (zzgd.zzb.startsWith("zeroflte") || zzgd.zzb.startsWith("herolte") || zzgd.zzb.startsWith("heroqlte"));
        String str = zztp.zza;
        this.zzg = str.equals("OMX.google.opus.decoder") || str.equals("c2.android.opus.decoder") || str.equals("OMX.google.vorbis.decoder") || str.equals("c2.android.vorbis.decoder");
        String str2 = zztp.zzc;
        int i = this.zze;
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", str2);
        mediaFormat.setInteger("channel-count", zzan.zzA);
        mediaFormat.setInteger("sample-rate", zzan.zzB);
        zzfn.zzb(mediaFormat, zzan.zzp);
        zzfn.zza(mediaFormat, "max-input-size", i);
        if (zzgd.zza >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f && (zzgd.zza != 23 || (!"ZTE B2017G".equals(zzgd.zzd) && !"AXON 7 mini".equals(zzgd.zzd)))) {
                mediaFormat.setFloat("operating-rate", f);
            }
        }
        if (zzgd.zza <= 28 && MimeTypes.AUDIO_AC4.equals(zzan.zzn)) {
            mediaFormat.setInteger("ac4-is-sync", 1);
        }
        if (zzgd.zza >= 24 && this.zzd.zza(zzgd.zzz(4, zzan.zzA, zzan.zzB)) == 2) {
            mediaFormat.setInteger("pcm-encoding", 4);
        }
        if (zzgd.zza >= 32) {
            mediaFormat.setInteger("max-output-channel-count", 99);
        }
        this.zzi = (!MimeTypes.AUDIO_RAW.equals(zztp.zzb) || MimeTypes.AUDIO_RAW.equals(zzan.zzn)) ? null : zzan;
        return zztk.zza(zztp, mediaFormat, zzan, (MediaCrypto) null);
    }

    /* access modifiers changed from: protected */
    public final List zzag(zztx zztx, zzan zzan, boolean z) throws zzud {
        return zzuj.zzg(zzaN(zztx, zzan, false, this.zzd), zzan);
    }

    /* access modifiers changed from: protected */
    public final void zzaj(zzin zzin) {
        zzan zzan;
        if (zzgd.zza >= 29 && (zzan = zzin.zza) != null && Objects.equals(zzan.zzn, MimeTypes.AUDIO_OPUS) && zzaI()) {
            ByteBuffer byteBuffer = zzin.zzf;
            byteBuffer.getClass();
            zzan zzan2 = zzin.zza;
            zzan2.getClass();
            if (byteBuffer.remaining() == 8) {
                this.zzd.zzr(zzan2.zzD, (int) ((byteBuffer.order(ByteOrder.LITTLE_ENDIAN).getLong() * 48000) / C.NANOS_PER_SECOND));
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzak(Exception exc) {
        zzfk.zzd("MediaCodecAudioRenderer", "Audio codec error", exc);
        this.zzc.zza(exc);
    }

    /* access modifiers changed from: protected */
    public final void zzal(String str, zztk zztk, long j, long j2) {
        this.zzc.zze(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public final void zzam(String str) {
        this.zzc.zzf(str);
    }

    /* access modifiers changed from: protected */
    public final void zzao() {
        this.zzk = true;
    }

    /* access modifiers changed from: protected */
    public final void zzap() {
        this.zzd.zzg();
    }

    /* access modifiers changed from: protected */
    public final void zzaq() throws zzjh {
        try {
            this.zzd.zzj();
        } catch (zzqu e) {
            throw zzi(e, e.zzc, e.zzb, true != zzaI() ? PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED : 5003);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean zzas(zzan zzan) {
        zzm();
        return this.zzd.zzA(zzan);
    }

    public final zzcl zzc() {
        return this.zzd.zzc();
    }

    public final void zzg(zzcl zzcl) {
        this.zzd.zzs(zzcl);
    }

    public final boolean zzj() {
        boolean z = this.zzn;
        this.zzn = false;
        return z;
    }

    public final zzlp zzk() {
        return this;
    }

    public final void zzt(int i, Object obj) throws zzjh {
        if (i == 2) {
            zzqv zzqv = this.zzd;
            obj.getClass();
            zzqv.zzw(((Float) obj).floatValue());
        } else if (i == 3) {
            zzk zzk2 = (zzk) obj;
            zzqv zzqv2 = this.zzd;
            zzk2.getClass();
            zzqv2.zzm(zzk2);
        } else if (i != 6) {
            switch (i) {
                case 9:
                    zzqv zzqv3 = this.zzd;
                    obj.getClass();
                    zzqv3.zzv(((Boolean) obj).booleanValue());
                    return;
                case 10:
                    zzqv zzqv4 = this.zzd;
                    obj.getClass();
                    zzqv4.zzn(((Integer) obj).intValue());
                    return;
                case 11:
                    this.zzm = (zzmm) obj;
                    return;
                case 12:
                    if (zzgd.zza >= 23) {
                        zzsc.zza(this.zzd, obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            zzl zzl2 = (zzl) obj;
            zzqv zzqv5 = this.zzd;
            zzl2.getClass();
            zzqv5.zzo(zzl2);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzw() {
        this.zzl = true;
        this.zzh = null;
        try {
            this.zzd.zzf();
            super.zzw();
        } catch (Throwable th) {
            super.zzw();
            throw th;
        } finally {
            this.zzc.zzg(this.zza);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzx(boolean z, boolean z2) throws zzjh {
        super.zzx(z, z2);
        this.zzc.zzh(this.zza);
        zzm();
        this.zzd.zzt(zzn());
        this.zzd.zzp(zzh());
    }

    /* access modifiers changed from: protected */
    public final void zzz(long j, boolean z) throws zzjh {
        super.zzz(j, z);
        this.zzd.zzf();
        this.zzj = j;
        this.zzn = false;
        this.zzk = true;
    }

    /* access modifiers changed from: protected */
    public final zziy zzac(zzlj zzlj) throws zzjh {
        zzan zzan = zzlj.zza;
        zzan.getClass();
        this.zzh = zzan;
        zziy zzac = super.zzac(zzlj);
        this.zzc.zzi(zzan, zzac);
        return zzac;
    }

    /* access modifiers changed from: protected */
    public final boolean zzar(long j, long j2, zztm zztm, ByteBuffer byteBuffer, int i, int i2, int i3, long j3, boolean z, boolean z2, zzan zzan) throws zzjh {
        byteBuffer.getClass();
        if (this.zzi != null && (i2 & 2) != 0) {
            zztm.getClass();
            zztm.zzn(i, false);
            return true;
        } else if (z) {
            if (zztm != null) {
                zztm.zzn(i, false);
            }
            this.zza.zzf += i3;
            this.zzd.zzg();
            return true;
        } else {
            try {
                if (!this.zzd.zzx(byteBuffer, j3, i3)) {
                    return false;
                }
                if (zztm != null) {
                    zztm.zzn(i, false);
                }
                this.zza.zze += i3;
                return true;
            } catch (zzqr e) {
                zzan zzan2 = this.zzh;
                if (zzaI()) {
                    zzm();
                }
                throw zzi(e, zzan2, e.zzb, PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED);
            } catch (zzqu e2) {
                if (zzaI()) {
                    zzm();
                }
                throw zzi(e2, zzan, e2.zzb, PlaybackException.ERROR_CODE_AUDIO_TRACK_WRITE_FAILED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void zzan(zzan zzan, MediaFormat mediaFormat) throws zzjh {
        int i;
        int[] iArr;
        int i2;
        zzan zzan2 = this.zzi;
        int[] iArr2 = null;
        boolean z = true;
        if (zzan2 != null) {
            zzan = zzan2;
        } else if (zzaw() != null) {
            mediaFormat.getClass();
            if (MimeTypes.AUDIO_RAW.equals(zzan.zzn)) {
                i = zzan.zzC;
            } else if (zzgd.zza < 24 || !mediaFormat.containsKey("pcm-encoding")) {
                i = mediaFormat.containsKey("v-bits-per-sample") ? zzgd.zzl(mediaFormat.getInteger("v-bits-per-sample")) : 2;
            } else {
                i = mediaFormat.getInteger("pcm-encoding");
            }
            zzal zzal = new zzal();
            zzal.zzX(MimeTypes.AUDIO_RAW);
            zzal.zzR(i);
            zzal.zzF(zzan.zzD);
            zzal.zzG(zzan.zzE);
            zzal.zzQ(zzan.zzl);
            zzal.zzK(zzan.zzb);
            zzal.zzM(zzan.zzc);
            zzal.zzN(zzan.zzd);
            zzal.zzO(zzan.zze);
            zzal.zzZ(zzan.zzf);
            zzal.zzV(zzan.zzg);
            zzal.zzy(mediaFormat.getInteger("channel-count"));
            zzal.zzY(mediaFormat.getInteger("sample-rate"));
            zzan zzad = zzal.zzad();
            if (this.zzf && zzad.zzA == 6 && (i2 = zzan.zzA) < 6) {
                iArr2 = new int[i2];
                for (int i3 = 0; i3 < zzan.zzA; i3++) {
                    iArr2[i3] = i3;
                }
            } else if (this.zzg) {
                int i4 = zzad.zzA;
                if (i4 == 3) {
                    iArr = new int[]{0, 2, 1};
                } else if (i4 == 5) {
                    iArr = new int[]{0, 2, 1, 3, 4};
                } else if (i4 == 6) {
                    iArr = new int[]{0, 2, 1, 5, 3, 4};
                } else if (i4 == 7) {
                    iArr = new int[]{0, 2, 1, 6, 5, 3, 4};
                } else if (i4 == 8) {
                    iArr = new int[]{0, 2, 1, 7, 5, 6, 3, 4};
                }
                iArr2 = iArr;
            }
            zzan = zzad;
        }
        try {
            if (zzgd.zza >= 29) {
                if (zzaI()) {
                    zzm();
                }
                if (zzgd.zza < 29) {
                    z = false;
                }
                zzeq.zzf(z);
            }
            this.zzd.zze(zzan, 0, iArr2);
        } catch (zzqq e) {
            throw zzi(e, e.zza, false, PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED);
        }
    }
}
