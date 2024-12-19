package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.metrics.LogSessionId;
import android.media.metrics.MediaMetricsManager;
import android.media.metrics.PlaybackMetrics;
import android.media.metrics.PlaybackSession;
import android.media.metrics.TrackChangeEvent;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import java.io.IOException;
import java.util.HashMap;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzpf implements zzna, zzpg {
    private final Context zza;
    private final zzph zzb;
    private final PlaybackSession zzc;
    private final long zzd = SystemClock.elapsedRealtime();
    private final zzdb zze = new zzdb();
    private final zzcz zzf = new zzcz();
    private final HashMap zzg = new HashMap();
    private final HashMap zzh = new HashMap();
    private String zzi;
    private PlaybackMetrics.Builder zzj;
    private int zzk;
    private int zzl = 0;
    private int zzm = 0;
    private zzcj zzn;
    private zzpe zzo;
    private zzpe zzp;
    private zzpe zzq;
    private zzan zzr;
    private zzan zzs;
    private zzan zzt;
    private boolean zzu;
    private boolean zzv;
    private int zzw;
    private int zzx;
    private int zzy;
    private boolean zzz;

    private zzpf(Context context, PlaybackSession playbackSession) {
        this.zza = context.getApplicationContext();
        this.zzc = playbackSession;
        zzpd zzpd = new zzpd(zzpd.zza);
        this.zzb = zzpd;
        zzpd.zzh(this);
    }

    public static zzpf zzb(Context context) {
        MediaMetricsManager m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(context.getSystemService("media_metrics"));
        if (m == null) {
            return null;
        }
        return new zzpf(context, m.createPlaybackSession());
    }

    private static int zzr(int i) {
        switch (zzgd.zzj(i)) {
            case PlaybackException.ERROR_CODE_DRM_PROVISIONING_FAILED:
                return 24;
            case PlaybackException.ERROR_CODE_DRM_CONTENT_ERROR:
                return 28;
            case PlaybackException.ERROR_CODE_DRM_LICENSE_ACQUISITION_FAILED:
                return 25;
            case PlaybackException.ERROR_CODE_DRM_DISALLOWED_OPERATION:
                return 26;
            default:
                return 27;
        }
    }

    private final void zzs() {
        long j;
        long j2;
        PlaybackMetrics.Builder builder = this.zzj;
        if (builder != null && this.zzz) {
            PlaybackMetrics.Builder unused = builder.setAudioUnderrunCount(this.zzy);
            PlaybackMetrics.Builder unused2 = this.zzj.setVideoFramesDropped(this.zzw);
            PlaybackMetrics.Builder unused3 = this.zzj.setVideoFramesPlayed(this.zzx);
            Long l = (Long) this.zzg.get(this.zzi);
            PlaybackMetrics.Builder builder2 = this.zzj;
            if (l == null) {
                j = 0;
            } else {
                j = l.longValue();
            }
            PlaybackMetrics.Builder unused4 = builder2.setNetworkTransferDurationMillis(j);
            Long l2 = (Long) this.zzh.get(this.zzi);
            PlaybackMetrics.Builder builder3 = this.zzj;
            if (l2 == null) {
                j2 = 0;
            } else {
                j2 = l2.longValue();
            }
            PlaybackMetrics.Builder unused5 = builder3.setNetworkBytesRead(j2);
            PlaybackMetrics.Builder unused6 = this.zzj.setStreamSource((l2 == null || l2.longValue() <= 0) ? 0 : 1);
            this.zzc.reportPlaybackMetrics(this.zzj.build());
        }
        this.zzj = null;
        this.zzi = null;
        this.zzy = 0;
        this.zzw = 0;
        this.zzx = 0;
        this.zzr = null;
        this.zzs = null;
        this.zzt = null;
        this.zzz = false;
    }

    private final void zzt(long j, zzan zzan, int i) {
        if (!zzgd.zzG(this.zzs, zzan)) {
            int i2 = this.zzs == null ? 1 : 0;
            this.zzs = zzan;
            zzx(0, j, zzan, i2);
        }
    }

    private final void zzu(long j, zzan zzan, int i) {
        if (!zzgd.zzG(this.zzt, zzan)) {
            int i2 = this.zzt == null ? 1 : 0;
            this.zzt = zzan;
            zzx(2, j, zzan, i2);
        }
    }

    @RequiresNonNull({"metricsBuilder"})
    private final void zzv(zzdc zzdc, zzvo zzvo) {
        int zza2;
        PlaybackMetrics.Builder builder = this.zzj;
        if (zzvo != null && (zza2 = zzdc.zza(zzvo.zza)) != -1) {
            int i = 0;
            zzdc.zzd(zza2, this.zzf, false);
            zzdc.zze(this.zzf.zzd, this.zze, 0);
            zzbn zzbn = this.zze.zze.zzd;
            int i2 = 2;
            if (zzbn != null) {
                int zzn2 = zzgd.zzn(zzbn.zzb);
                i = zzn2 != 0 ? zzn2 != 1 ? zzn2 != 2 ? 1 : 4 : 5 : 3;
            }
            PlaybackMetrics.Builder unused = builder.setStreamType(i);
            zzdb zzdb = this.zze;
            if (zzdb.zzo != C.TIME_UNSET && !zzdb.zzm && !zzdb.zzj && !zzdb.zzb()) {
                PlaybackMetrics.Builder unused2 = builder.setMediaDurationMillis(zzgd.zzu(this.zze.zzo));
            }
            if (true != this.zze.zzb()) {
                i2 = 1;
            }
            PlaybackMetrics.Builder unused3 = builder.setPlaybackType(i2);
            this.zzz = true;
        }
    }

    private final void zzw(long j, zzan zzan, int i) {
        if (!zzgd.zzG(this.zzr, zzan)) {
            int i2 = this.zzr == null ? 1 : 0;
            this.zzr = zzan;
            zzx(1, j, zzan, i2);
        }
    }

    private final void zzx(int i, long j, zzan zzan, int i2) {
        TrackChangeEvent.Builder m = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(i).setTimeSinceCreatedMillis(j - this.zzd);
        if (zzan != null) {
            TrackChangeEvent.Builder unused = m.setTrackState(1);
            TrackChangeEvent.Builder unused2 = m.setTrackChangeReason(i2 != 1 ? 1 : 2);
            String str = zzan.zzm;
            if (str != null) {
                TrackChangeEvent.Builder unused3 = m.setContainerMimeType(str);
            }
            String str2 = zzan.zzn;
            if (str2 != null) {
                TrackChangeEvent.Builder unused4 = m.setSampleMimeType(str2);
            }
            String str3 = zzan.zzk;
            if (str3 != null) {
                TrackChangeEvent.Builder unused5 = m.setCodecName(str3);
            }
            int i3 = zzan.zzj;
            if (i3 != -1) {
                TrackChangeEvent.Builder unused6 = m.setBitrate(i3);
            }
            int i4 = zzan.zzs;
            if (i4 != -1) {
                TrackChangeEvent.Builder unused7 = m.setWidth(i4);
            }
            int i5 = zzan.zzt;
            if (i5 != -1) {
                TrackChangeEvent.Builder unused8 = m.setHeight(i5);
            }
            int i6 = zzan.zzA;
            if (i6 != -1) {
                TrackChangeEvent.Builder unused9 = m.setChannelCount(i6);
            }
            int i7 = zzan.zzB;
            if (i7 != -1) {
                TrackChangeEvent.Builder unused10 = m.setAudioSampleRate(i7);
            }
            String str4 = zzan.zze;
            if (str4 != null) {
                int i8 = zzgd.zza;
                String[] split = str4.split("-", -1);
                Pair create = Pair.create(split[0], split.length >= 2 ? split[1] : null);
                TrackChangeEvent.Builder unused11 = m.setLanguage((String) create.first);
                if (create.second != null) {
                    TrackChangeEvent.Builder unused12 = m.setLanguageRegion((String) create.second);
                }
            }
            float f = zzan.zzu;
            if (f != -1.0f) {
                TrackChangeEvent.Builder unused13 = m.setVideoFrameRate(f);
            }
        } else {
            TrackChangeEvent.Builder unused14 = m.setTrackState(0);
        }
        this.zzz = true;
        this.zzc.reportTrackChangeEvent(m.build());
    }

    @EnsuresNonNullIf(expression = {"#1"}, result = true)
    private final boolean zzy(zzpe zzpe) {
        if (zzpe == null) {
            return false;
        }
        return zzpe.zzc.equals(this.zzb.zze());
    }

    public final LogSessionId zza() {
        return this.zzc.getSessionId();
    }

    public final void zzc(zzmy zzmy, String str) {
        zzvo zzvo = zzmy.zzd;
        if (zzvo == null || !zzvo.zzb()) {
            zzs();
            this.zzi = str;
            this.zzj = FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m().setPlayerName("AndroidXMedia3").setPlayerVersion("1.4.0-alpha01");
            zzv(zzmy.zzb, zzmy.zzd);
        }
    }

    public final void zzd(zzmy zzmy, String str, boolean z) {
        zzvo zzvo = zzmy.zzd;
        if ((zzvo == null || !zzvo.zzb()) && str.equals(this.zzi)) {
            zzs();
        }
        this.zzg.remove(str);
        this.zzh.remove(str);
    }

    public final /* synthetic */ void zze(zzmy zzmy, zzan zzan, zziy zziy) {
    }

    public final void zzf(zzmy zzmy, int i, long j, long j2) {
        long j3;
        zzvo zzvo = zzmy.zzd;
        if (zzvo != null) {
            zzph zzph = this.zzb;
            zzdc zzdc = zzmy.zzb;
            HashMap hashMap = this.zzh;
            String zzf2 = zzph.zzf(zzdc, zzvo);
            Long l = (Long) hashMap.get(zzf2);
            Long l2 = (Long) this.zzg.get(zzf2);
            HashMap hashMap2 = this.zzh;
            long j4 = 0;
            if (l == null) {
                j3 = 0;
            } else {
                j3 = l.longValue();
            }
            hashMap2.put(zzf2, Long.valueOf(j3 + j));
            HashMap hashMap3 = this.zzg;
            if (l2 != null) {
                j4 = l2.longValue();
            }
            hashMap3.put(zzf2, Long.valueOf(j4 + ((long) i)));
        }
    }

    public final void zzg(zzmy zzmy, zzvk zzvk) {
        zzvo zzvo = zzmy.zzd;
        if (zzvo != null) {
            zzan zzan = zzvk.zzb;
            zzan.getClass();
            zzpe zzpe = new zzpe(zzan, 0, this.zzb.zzf(zzmy.zzb, zzvo));
            int i = zzvk.zza;
            if (i != 0) {
                if (i == 1) {
                    this.zzp = zzpe;
                    return;
                } else if (i != 2) {
                    if (i == 3) {
                        this.zzq = zzpe;
                        return;
                    }
                    return;
                }
            }
            this.zzo = zzpe;
        }
    }

    public final /* synthetic */ void zzh(zzmy zzmy, int i, long j) {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:129:0x01eb, code lost:
        if (r8 != 1) goto L_0x01ef;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzi(com.google.android.gms.internal.ads.zzct r19, com.google.android.gms.internal.ads.zzmz r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            int r2 = r20.zzb()
            if (r2 != 0) goto L_0x000c
            goto L_0x03ca
        L_0x000c:
            r2 = 0
            r3 = r2
        L_0x000e:
            int r4 = r20.zzb()
            r5 = 11
            if (r3 >= r4) goto L_0x0038
            int r4 = r1.zza(r3)
            com.google.android.gms.internal.ads.zzmy r6 = r1.zzc(r4)
            if (r4 != 0) goto L_0x0026
            com.google.android.gms.internal.ads.zzph r4 = r0.zzb
            r4.zzk(r6)
            goto L_0x0035
        L_0x0026:
            if (r4 != r5) goto L_0x0030
            com.google.android.gms.internal.ads.zzph r4 = r0.zzb
            int r5 = r0.zzk
            r4.zzj(r6, r5)
            goto L_0x0035
        L_0x0030:
            com.google.android.gms.internal.ads.zzph r4 = r0.zzb
            r4.zzi(r6)
        L_0x0035:
            int r3 = r3 + 1
            goto L_0x000e
        L_0x0038:
            long r3 = android.os.SystemClock.elapsedRealtime()
            boolean r6 = r1.zzd(r2)
            if (r6 == 0) goto L_0x0051
            com.google.android.gms.internal.ads.zzmy r6 = r1.zzc(r2)
            android.media.metrics.PlaybackMetrics$Builder r7 = r0.zzj
            if (r7 == 0) goto L_0x0051
            com.google.android.gms.internal.ads.zzdc r7 = r6.zzb
            com.google.android.gms.internal.ads.zzvo r6 = r6.zzd
            r0.zzv(r7, r6)
        L_0x0051:
            r6 = 2
            boolean r7 = r1.zzd(r6)
            r9 = 3
            r10 = 0
            r11 = 1
            if (r7 == 0) goto L_0x00c9
            android.media.metrics.PlaybackMetrics$Builder r7 = r0.zzj
            if (r7 == 0) goto L_0x00c9
            com.google.android.gms.internal.ads.zzdp r7 = r19.zzo()
            com.google.android.gms.internal.ads.zzgbc r7 = r7.zza()
            int r12 = r7.size()
            r13 = r2
        L_0x006c:
            if (r13 >= r12) goto L_0x0092
            java.lang.Object r14 = r7.get(r13)
            com.google.android.gms.internal.ads.zzdo r14 = (com.google.android.gms.internal.ads.zzdo) r14
            r15 = r2
        L_0x0075:
            int r5 = r14.zzb
            int r16 = r13 + 1
            if (r15 >= r5) goto L_0x008d
            boolean r5 = r14.zzd(r15)
            if (r5 == 0) goto L_0x008a
            com.google.android.gms.internal.ads.zzan r5 = r14.zzb(r15)
            com.google.android.gms.internal.ads.zzae r5 = r5.zzq
            if (r5 == 0) goto L_0x008a
            goto L_0x0093
        L_0x008a:
            int r15 = r15 + 1
            goto L_0x0075
        L_0x008d:
            r13 = r16
            r5 = 11
            goto L_0x006c
        L_0x0092:
            r5 = r10
        L_0x0093:
            if (r5 == 0) goto L_0x00c9
            android.media.metrics.PlaybackMetrics$Builder r7 = r0.zzj
            int r12 = com.google.android.gms.internal.ads.zzgd.zza
            r12 = r2
        L_0x009a:
            int r13 = r5.zzb
            if (r12 >= r13) goto L_0x00c5
            com.google.android.gms.internal.ads.zzad r13 = r5.zza(r12)
            java.util.UUID r13 = r13.zza
            java.util.UUID r14 = com.google.android.gms.internal.ads.zzo.zzd
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x00ae
            r5 = r9
            goto L_0x00c6
        L_0x00ae:
            java.util.UUID r14 = com.google.android.gms.internal.ads.zzo.zze
            boolean r14 = r13.equals(r14)
            if (r14 == 0) goto L_0x00b8
            r5 = r6
            goto L_0x00c6
        L_0x00b8:
            java.util.UUID r14 = com.google.android.gms.internal.ads.zzo.zzc
            boolean r13 = r13.equals(r14)
            if (r13 == 0) goto L_0x00c2
            r5 = 6
            goto L_0x00c6
        L_0x00c2:
            int r12 = r12 + 1
            goto L_0x009a
        L_0x00c5:
            r5 = r11
        L_0x00c6:
            android.media.metrics.PlaybackMetrics.Builder unused = r7.setDrmType(r5)
        L_0x00c9:
            r5 = 1011(0x3f3, float:1.417E-42)
            boolean r5 = r1.zzd(r5)
            if (r5 == 0) goto L_0x00d6
            int r5 = r0.zzy
            int r5 = r5 + r11
            r0.zzy = r5
        L_0x00d6:
            com.google.android.gms.internal.ads.zzcj r5 = r0.zzn
            r16 = 9
            if (r5 != 0) goto L_0x00de
            goto L_0x0277
        L_0x00de:
            android.content.Context r7 = r0.zza
            int r8 = r5.zzb
            r12 = 1001(0x3e9, float:1.403E-42)
            if (r8 != r12) goto L_0x00eb
            r7 = 20
        L_0x00e8:
            r8 = r2
            goto L_0x0252
        L_0x00eb:
            r8 = r5
            com.google.android.gms.internal.ads.zzjh r8 = (com.google.android.gms.internal.ads.zzjh) r8
            int r12 = r8.zze
            if (r12 != r11) goto L_0x00f4
            r12 = r11
            goto L_0x00f5
        L_0x00f4:
            r12 = r2
        L_0x00f5:
            int r8 = r8.zzi
            java.lang.Throwable r13 = r5.getCause()
            r13.getClass()
            boolean r14 = r13 instanceof java.io.IOException
            r15 = 23
            if (r14 == 0) goto L_0x01e5
            boolean r8 = r13 instanceof com.google.android.gms.internal.ads.zzhx
            if (r8 == 0) goto L_0x0110
            com.google.android.gms.internal.ads.zzhx r13 = (com.google.android.gms.internal.ads.zzhx) r13
            int r7 = r13.zzd
            r8 = r7
            r7 = 5
            goto L_0x0252
        L_0x0110:
            boolean r8 = r13 instanceof com.google.android.gms.internal.ads.zzhw
            if (r8 != 0) goto L_0x01e0
            boolean r8 = r13 instanceof com.google.android.gms.internal.ads.zzch
            if (r8 == 0) goto L_0x011a
            goto L_0x01e0
        L_0x011a:
            boolean r8 = r13 instanceof com.google.android.gms.internal.ads.zzhv
            if (r8 != 0) goto L_0x01ad
            boolean r12 = r13 instanceof com.google.android.gms.internal.ads.zzif
            if (r12 == 0) goto L_0x0124
            goto L_0x01ad
        L_0x0124:
            int r7 = r5.zzb
            r8 = 1002(0x3ea, float:1.404E-42)
            if (r7 != r8) goto L_0x012d
            r7 = 21
            goto L_0x00e8
        L_0x012d:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zzsm
            if (r7 == 0) goto L_0x0179
            java.lang.Throwable r7 = r13.getCause()
            r7.getClass()
            int r8 = com.google.android.gms.internal.ads.zzgd.zza
            boolean r8 = r7 instanceof android.media.MediaDrm.MediaDrmStateException
            if (r8 == 0) goto L_0x014e
            android.media.MediaDrm$MediaDrmStateException r7 = (android.media.MediaDrm.MediaDrmStateException) r7
            java.lang.String r7 = r7.getDiagnosticInfo()
            int r7 = com.google.android.gms.internal.ads.zzgd.zzk(r7)
            int r8 = zzr(r7)
            goto L_0x021c
        L_0x014e:
            int r8 = com.google.android.gms.internal.ads.zzgd.zza
            if (r8 < r15) goto L_0x0159
            boolean r8 = r7 instanceof android.media.MediaDrmResetException
            if (r8 == 0) goto L_0x0159
            r7 = 27
            goto L_0x00e8
        L_0x0159:
            boolean r8 = r7 instanceof android.media.NotProvisionedException
            if (r8 == 0) goto L_0x0160
            r7 = 24
            goto L_0x00e8
        L_0x0160:
            boolean r8 = r7 instanceof android.media.DeniedByServerException
            if (r8 == 0) goto L_0x0167
            r7 = 29
            goto L_0x00e8
        L_0x0167:
            boolean r8 = r7 instanceof com.google.android.gms.internal.ads.zzsw
            if (r8 == 0) goto L_0x016d
            goto L_0x01fb
        L_0x016d:
            boolean r7 = r7 instanceof com.google.android.gms.internal.ads.zzsk
            if (r7 == 0) goto L_0x0175
            r7 = 28
            goto L_0x00e8
        L_0x0175:
            r7 = 30
            goto L_0x00e8
        L_0x0179:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zzhr
            if (r7 == 0) goto L_0x01a8
            java.lang.Throwable r7 = r13.getCause()
            boolean r7 = r7 instanceof java.io.FileNotFoundException
            if (r7 == 0) goto L_0x01a8
            java.lang.Throwable r7 = r13.getCause()
            r7.getClass()
            java.lang.Throwable r7 = r7.getCause()
            int r8 = com.google.android.gms.internal.ads.zzgd.zza
            boolean r8 = r7 instanceof android.system.ErrnoException
            r12 = 31
            if (r8 == 0) goto L_0x01a4
            android.system.ErrnoException r7 = (android.system.ErrnoException) r7
            int r7 = r7.errno
            int r8 = android.system.OsConstants.EACCES
            if (r7 != r8) goto L_0x01a4
            r7 = 32
            goto L_0x00e8
        L_0x01a4:
            r8 = r2
            r7 = r12
            goto L_0x0252
        L_0x01a8:
            r8 = r2
            r7 = r16
            goto L_0x0252
        L_0x01ad:
            com.google.android.gms.internal.ads.zzfs r7 = com.google.android.gms.internal.ads.zzfs.zzb(r7)
            int r7 = r7.zza()
            if (r7 != r11) goto L_0x01bb
            r8 = r2
            r7 = r9
            goto L_0x0252
        L_0x01bb:
            java.lang.Throwable r7 = r13.getCause()
            boolean r12 = r7 instanceof java.net.UnknownHostException
            if (r12 == 0) goto L_0x01c7
            r8 = r2
            r7 = 6
            goto L_0x0252
        L_0x01c7:
            boolean r7 = r7 instanceof java.net.SocketTimeoutException
            if (r7 == 0) goto L_0x01cf
            r8 = r2
            r7 = 7
            goto L_0x0252
        L_0x01cf:
            if (r8 == 0) goto L_0x01db
            com.google.android.gms.internal.ads.zzhv r13 = (com.google.android.gms.internal.ads.zzhv) r13
            int r7 = r13.zzc
            if (r7 != r11) goto L_0x01db
            r8 = r2
            r7 = 4
            goto L_0x0252
        L_0x01db:
            r8 = r2
            r7 = 8
            goto L_0x0252
        L_0x01e0:
            r8 = r2
            r7 = 11
            goto L_0x0252
        L_0x01e5:
            if (r12 == 0) goto L_0x01ef
            r7 = 35
            if (r8 == 0) goto L_0x00e8
            if (r8 != r11) goto L_0x01ef
            goto L_0x00e8
        L_0x01ef:
            if (r12 == 0) goto L_0x01f7
            if (r8 != r9) goto L_0x01f7
            r7 = 15
            goto L_0x00e8
        L_0x01f7:
            if (r12 == 0) goto L_0x01fe
            if (r8 != r6) goto L_0x01fe
        L_0x01fb:
            r8 = r2
            r7 = r15
            goto L_0x0252
        L_0x01fe:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zztt
            if (r7 == 0) goto L_0x020e
            com.google.android.gms.internal.ads.zztt r13 = (com.google.android.gms.internal.ads.zztt) r13
            java.lang.String r7 = r13.zzd
            int r7 = com.google.android.gms.internal.ads.zzgd.zzk(r7)
            r8 = r7
            r7 = 13
            goto L_0x0252
        L_0x020e:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zzto
            r8 = 14
            if (r7 == 0) goto L_0x0222
            com.google.android.gms.internal.ads.zzto r13 = (com.google.android.gms.internal.ads.zzto) r13
            java.lang.String r7 = r13.zzb
            int r7 = com.google.android.gms.internal.ads.zzgd.zzk(r7)
        L_0x021c:
            r17 = r8
            r8 = r7
            r7 = r17
            goto L_0x0252
        L_0x0222:
            boolean r7 = r13 instanceof java.lang.OutOfMemoryError
            if (r7 == 0) goto L_0x0229
            r7 = r8
            goto L_0x00e8
        L_0x0229:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zzqr
            if (r7 == 0) goto L_0x0234
            com.google.android.gms.internal.ads.zzqr r13 = (com.google.android.gms.internal.ads.zzqr) r13
            int r7 = r13.zza
            r8 = 17
            goto L_0x021c
        L_0x0234:
            boolean r7 = r13 instanceof com.google.android.gms.internal.ads.zzqu
            if (r7 == 0) goto L_0x023f
            com.google.android.gms.internal.ads.zzqu r13 = (com.google.android.gms.internal.ads.zzqu) r13
            int r7 = r13.zza
            r8 = 18
            goto L_0x021c
        L_0x023f:
            boolean r7 = r13 instanceof android.media.MediaCodec.CryptoException
            if (r7 == 0) goto L_0x024e
            android.media.MediaCodec$CryptoException r13 = (android.media.MediaCodec.CryptoException) r13
            int r7 = r13.getErrorCode()
            int r8 = zzr(r7)
            goto L_0x021c
        L_0x024e:
            r7 = 22
            goto L_0x00e8
        L_0x0252:
            android.media.metrics.PlaybackSession r12 = r0.zzc
            android.media.metrics.PlaybackErrorEvent$Builder r13 = com.bumptech.glide.disklrucache.DiskLruCache$$ExternalSyntheticApiModelOutline0.m()
            long r14 = r0.zzd
            long r14 = r3 - r14
            android.media.metrics.PlaybackErrorEvent$Builder r13 = r13.setTimeSinceCreatedMillis(r14)
            android.media.metrics.PlaybackErrorEvent$Builder r7 = r13.setErrorCode(r7)
            android.media.metrics.PlaybackErrorEvent$Builder r7 = r7.setSubErrorCode(r8)
            android.media.metrics.PlaybackErrorEvent$Builder r5 = r7.setException(r5)
            android.media.metrics.PlaybackErrorEvent r5 = r5.build()
            r12.reportPlaybackErrorEvent(r5)
            r0.zzz = r11
            r0.zzn = r10
        L_0x0277:
            boolean r5 = r1.zzd(r6)
            if (r5 == 0) goto L_0x02a3
            com.google.android.gms.internal.ads.zzdp r5 = r19.zzo()
            boolean r7 = r5.zzb(r6)
            boolean r8 = r5.zzb(r11)
            boolean r5 = r5.zzb(r9)
            if (r7 != 0) goto L_0x0294
            if (r8 != 0) goto L_0x0294
            if (r5 == 0) goto L_0x02a3
            r5 = r11
        L_0x0294:
            if (r7 != 0) goto L_0x0299
            r0.zzw(r3, r10, r2)
        L_0x0299:
            if (r8 != 0) goto L_0x029e
            r0.zzt(r3, r10, r2)
        L_0x029e:
            if (r5 != 0) goto L_0x02a3
            r0.zzu(r3, r10, r2)
        L_0x02a3:
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzo
            boolean r5 = r0.zzy(r5)
            if (r5 == 0) goto L_0x02bb
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzo
            com.google.android.gms.internal.ads.zzan r7 = r5.zza
            int r8 = r7.zzt
            r12 = -1
            if (r8 == r12) goto L_0x02bb
            int r5 = r5.zzb
            r0.zzw(r3, r7, r2)
            r0.zzo = r10
        L_0x02bb:
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzp
            boolean r5 = r0.zzy(r5)
            if (r5 == 0) goto L_0x02ce
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzp
            com.google.android.gms.internal.ads.zzan r7 = r5.zza
            int r5 = r5.zzb
            r0.zzt(r3, r7, r2)
            r0.zzp = r10
        L_0x02ce:
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzq
            boolean r5 = r0.zzy(r5)
            if (r5 == 0) goto L_0x02e1
            com.google.android.gms.internal.ads.zzpe r5 = r0.zzq
            com.google.android.gms.internal.ads.zzan r7 = r5.zza
            int r5 = r5.zzb
            r0.zzu(r3, r7, r2)
            r0.zzq = r10
        L_0x02e1:
            android.content.Context r5 = r0.zza
            com.google.android.gms.internal.ads.zzfs r5 = com.google.android.gms.internal.ads.zzfs.zzb(r5)
            int r5 = r5.zza()
            switch(r5) {
                case 0: goto L_0x0302;
                case 1: goto L_0x02ff;
                case 2: goto L_0x02fd;
                case 3: goto L_0x02fb;
                case 4: goto L_0x02f9;
                case 5: goto L_0x02f7;
                case 6: goto L_0x02ee;
                case 7: goto L_0x02f5;
                case 8: goto L_0x02ee;
                case 9: goto L_0x02f2;
                case 10: goto L_0x02f0;
                default: goto L_0x02ee;
            }
        L_0x02ee:
            r13 = r11
            goto L_0x0303
        L_0x02f0:
            r13 = 7
            goto L_0x0303
        L_0x02f2:
            r13 = 8
            goto L_0x0303
        L_0x02f5:
            r13 = r9
            goto L_0x0303
        L_0x02f7:
            r13 = 6
            goto L_0x0303
        L_0x02f9:
            r13 = 5
            goto L_0x0303
        L_0x02fb:
            r13 = 4
            goto L_0x0303
        L_0x02fd:
            r13 = r6
            goto L_0x0303
        L_0x02ff:
            r13 = r16
            goto L_0x0303
        L_0x0302:
            r13 = r2
        L_0x0303:
            int r5 = r0.zzm
            if (r13 == r5) goto L_0x0322
            r0.zzm = r13
            android.media.metrics.PlaybackSession r5 = r0.zzc
            android.media.metrics.NetworkEvent$Builder r7 = com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m()
            android.media.metrics.NetworkEvent$Builder r7 = r7.setNetworkType(r13)
            long r12 = r0.zzd
            long r12 = r3 - r12
            android.media.metrics.NetworkEvent$Builder r7 = r7.setTimeSinceCreatedMillis(r12)
            android.media.metrics.NetworkEvent r7 = r7.build()
            r5.reportNetworkEvent(r7)
        L_0x0322:
            int r5 = r19.zzf()
            if (r5 == r6) goto L_0x032a
            r0.zzu = r2
        L_0x032a:
            r5 = r19
            com.google.android.gms.internal.ads.zzmt r5 = (com.google.android.gms.internal.ads.zzmt) r5
            com.google.android.gms.internal.ads.zzjh r5 = r5.zzC()
            r7 = 10
            if (r5 != 0) goto L_0x0339
            r0.zzv = r2
            goto L_0x0341
        L_0x0339:
            boolean r2 = r1.zzd(r7)
            if (r2 == 0) goto L_0x0341
            r0.zzv = r11
        L_0x0341:
            int r2 = r19.zzf()
            boolean r5 = r0.zzu
            if (r5 == 0) goto L_0x034b
            r5 = 5
            goto L_0x0397
        L_0x034b:
            boolean r5 = r0.zzv
            if (r5 == 0) goto L_0x0352
            r5 = 13
            goto L_0x0397
        L_0x0352:
            r5 = 4
            if (r2 != r5) goto L_0x0358
            r5 = 11
            goto L_0x0397
        L_0x0358:
            r8 = 12
            if (r2 != r6) goto L_0x0379
            int r2 = r0.zzl
            if (r2 == 0) goto L_0x0377
            if (r2 == r6) goto L_0x0377
            if (r2 != r8) goto L_0x0365
            goto L_0x0377
        L_0x0365:
            boolean r2 = r19.zzv()
            if (r2 != 0) goto L_0x036d
            r5 = 7
            goto L_0x0397
        L_0x036d:
            int r2 = r19.zzg()
            if (r2 == 0) goto L_0x0375
            r5 = r7
            goto L_0x0397
        L_0x0375:
            r5 = 6
            goto L_0x0397
        L_0x0377:
            r5 = r6
            goto L_0x0397
        L_0x0379:
            if (r2 != r9) goto L_0x038d
            boolean r2 = r19.zzv()
            if (r2 != 0) goto L_0x0382
            goto L_0x0397
        L_0x0382:
            int r2 = r19.zzg()
            if (r2 == 0) goto L_0x038b
            r5 = r16
            goto L_0x0397
        L_0x038b:
            r5 = r9
            goto L_0x0397
        L_0x038d:
            if (r2 != r11) goto L_0x0395
            int r2 = r0.zzl
            if (r2 == 0) goto L_0x0395
            r5 = r8
            goto L_0x0397
        L_0x0395:
            int r5 = r0.zzl
        L_0x0397:
            int r2 = r0.zzl
            if (r2 == r5) goto L_0x03b9
            r0.zzl = r5
            r0.zzz = r11
            android.media.metrics.PlaybackSession r2 = r0.zzc
            android.media.metrics.PlaybackStateEvent$Builder r5 = com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m()
            int r6 = r0.zzl
            android.media.metrics.PlaybackStateEvent$Builder r5 = r5.setState(r6)
            long r6 = r0.zzd
            long r3 = r3 - r6
            android.media.metrics.PlaybackStateEvent$Builder r3 = r5.setTimeSinceCreatedMillis(r3)
            android.media.metrics.PlaybackStateEvent r3 = r3.build()
            r2.reportPlaybackStateEvent(r3)
        L_0x03b9:
            r2 = 1028(0x404, float:1.44E-42)
            boolean r3 = r1.zzd(r2)
            if (r3 == 0) goto L_0x03ca
            com.google.android.gms.internal.ads.zzph r3 = r0.zzb
            com.google.android.gms.internal.ads.zzmy r1 = r1.zzc(r2)
            r3.zzg(r1)
        L_0x03ca:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzpf.zzi(com.google.android.gms.internal.ads.zzct, com.google.android.gms.internal.ads.zzmz):void");
    }

    public final void zzj(zzmy zzmy, zzvf zzvf, zzvk zzvk, IOException iOException, boolean z) {
    }

    public final /* synthetic */ void zzk(zzmy zzmy, int i) {
    }

    public final void zzl(zzmy zzmy, zzcj zzcj) {
        this.zzn = zzcj;
    }

    public final void zzm(zzmy zzmy, zzcs zzcs, zzcs zzcs2, int i) {
        if (i == 1) {
            this.zzu = true;
            i = 1;
        }
        this.zzk = i;
    }

    public final /* synthetic */ void zzn(zzmy zzmy, Object obj, long j) {
    }

    public final void zzo(zzmy zzmy, zzix zzix) {
        this.zzw += zzix.zzg;
        this.zzx += zzix.zze;
    }

    public final /* synthetic */ void zzp(zzmy zzmy, zzan zzan, zziy zziy) {
    }

    public final void zzq(zzmy zzmy, zzdv zzdv) {
        zzpe zzpe = this.zzo;
        if (zzpe != null) {
            zzan zzan = zzpe.zza;
            if (zzan.zzt == -1) {
                zzal zzb2 = zzan.zzb();
                zzb2.zzac(zzdv.zzc);
                zzb2.zzI(zzdv.zzd);
                this.zzo = new zzpe(zzb2.zzad(), 0, zzpe.zzc);
            }
        }
    }
}
