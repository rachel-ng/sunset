package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.gms.ads.internal.client.zzba;
import com.google.android.gms.ads.internal.util.client.zzm;
import com.google.android.gms.ads.internal.util.zze;
import com.google.android.gms.ads.internal.util.zzt;
import com.google.android.gms.ads.internal.zzu;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzcdi extends zzcdk implements TextureView.SurfaceTextureListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener {
    private static final Map zzc;
    private final zzcee zzd;
    private final zzcef zze;
    private final boolean zzf;
    private int zzg = 0;
    private int zzh = 0;
    private MediaPlayer zzi;
    private Uri zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private zzcec zzn;
    private final boolean zzo;
    private int zzp;
    /* access modifiers changed from: private */
    public zzcdj zzq;
    /* access modifiers changed from: private */
    public boolean zzr = false;
    private Integer zzs = null;

    static {
        HashMap hashMap = new HashMap();
        zzc = hashMap;
        hashMap.put(-1004, "MEDIA_ERROR_IO");
        hashMap.put(-1007, "MEDIA_ERROR_MALFORMED");
        hashMap.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
        hashMap.put(-110, "MEDIA_ERROR_TIMED_OUT");
        hashMap.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        hashMap.put(100, "MEDIA_ERROR_SERVER_DIED");
        hashMap.put(1, "MEDIA_ERROR_UNKNOWN");
        hashMap.put(1, "MEDIA_INFO_UNKNOWN");
        hashMap.put(Integer.valueOf(TypedValues.TransitionType.TYPE_DURATION), "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        hashMap.put(Integer.valueOf(TypedValues.TransitionType.TYPE_FROM), "MEDIA_INFO_BUFFERING_START");
        hashMap.put(Integer.valueOf(TypedValues.TransitionType.TYPE_TO), "MEDIA_INFO_BUFFERING_END");
        hashMap.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        hashMap.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        hashMap.put(802, "MEDIA_INFO_METADATA_UPDATE");
        hashMap.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
        hashMap.put(Integer.valueOf(TypedValues.Custom.TYPE_COLOR), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
    }

    public zzcdi(Context context, zzcee zzcee, boolean z, boolean z2, zzced zzced, zzcef zzcef) {
        super(context);
        setSurfaceTextureListener(this);
        this.zzd = zzcee;
        this.zze = zzcef;
        this.zzo = z;
        this.zzf = z2;
        zzcef.zza(this);
    }

    private final void zzD() {
        zze.zza("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzj != null && surfaceTexture != null) {
            zzE(false);
            try {
                zzu.zzk();
                MediaPlayer mediaPlayer = new MediaPlayer();
                this.zzi = mediaPlayer;
                mediaPlayer.setOnBufferingUpdateListener(this);
                this.zzi.setOnCompletionListener(this);
                this.zzi.setOnErrorListener(this);
                this.zzi.setOnInfoListener(this);
                this.zzi.setOnPreparedListener(this);
                this.zzi.setOnVideoSizeChangedListener(this);
                this.zzm = 0;
                if (this.zzo) {
                    zzcec zzcec = new zzcec(getContext());
                    this.zzn = zzcec;
                    zzcec.zzd(surfaceTexture, getWidth(), getHeight());
                    this.zzn.start();
                    SurfaceTexture zzb = this.zzn.zzb();
                    if (zzb != null) {
                        surfaceTexture = zzb;
                    } else {
                        this.zzn.zze();
                        this.zzn = null;
                    }
                }
                this.zzi.setDataSource(getContext(), this.zzj);
                zzu.zzl();
                this.zzi.setSurface(new Surface(surfaceTexture));
                this.zzi.setAudioStreamType(3);
                this.zzi.setScreenOnWhilePlaying(true);
                this.zzi.prepareAsync();
                zzF(1);
            } catch (IOException | IllegalArgumentException | IllegalStateException e) {
                zzm.zzk("Failed to initialize MediaPlayer at ".concat(String.valueOf(String.valueOf(this.zzj))), e);
                onError(this.zzi, 1, 0);
            }
        }
    }

    private final void zzE(boolean z) {
        zze.zza("AdMediaPlayerView release");
        zzcec zzcec = this.zzn;
        if (zzcec != null) {
            zzcec.zze();
            this.zzn = null;
        }
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzi.release();
            this.zzi = null;
            zzF(0);
            if (z) {
                this.zzh = 0;
            }
        }
    }

    private final void zzF(int i) {
        if (i == 3) {
            this.zze.zzc();
            this.zzb.zzb();
        } else if (this.zzg == 3) {
            this.zze.zze();
            this.zzb.zzc();
        }
        this.zzg = i;
    }

    private final void zzG(float f) {
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzm.zzj("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r2.zzg;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzH() {
        /*
            r2 = this;
            android.media.MediaPlayer r0 = r2.zzi
            if (r0 == 0) goto L_0x000f
            int r0 = r2.zzg
            r1 = -1
            if (r0 == r1) goto L_0x000f
            if (r0 == 0) goto L_0x000f
            r1 = 1
            if (r0 == r1) goto L_0x000f
            return r1
        L_0x000f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdi.zzH():boolean");
    }

    static /* bridge */ /* synthetic */ void zzl(zzcdi zzcdi, MediaPlayer mediaPlayer) {
        MediaFormat format;
        if (((Boolean) zzba.zzc().zza(zzbep.zzbR)).booleanValue() && zzcdi.zzd != null && mediaPlayer != null) {
            try {
                MediaPlayer.TrackInfo[] trackInfo = mediaPlayer.getTrackInfo();
                if (trackInfo != null) {
                    HashMap hashMap = new HashMap();
                    for (MediaPlayer.TrackInfo trackInfo2 : trackInfo) {
                        if (trackInfo2 != null) {
                            int trackType = trackInfo2.getTrackType();
                            if (trackType == 1) {
                                MediaFormat format2 = trackInfo2.getFormat();
                                if (format2 != null) {
                                    if (format2.containsKey("frame-rate")) {
                                        try {
                                            hashMap.put("frameRate", String.valueOf(format2.getFloat("frame-rate")));
                                        } catch (ClassCastException unused) {
                                            hashMap.put("frameRate", String.valueOf(format2.getInteger("frame-rate")));
                                        }
                                    }
                                    if (format2.containsKey("bitrate")) {
                                        Integer valueOf = Integer.valueOf(format2.getInteger("bitrate"));
                                        zzcdi.zzs = valueOf;
                                        hashMap.put("bitRate", String.valueOf(valueOf));
                                    }
                                    if (format2.containsKey("width") && format2.containsKey("height")) {
                                        hashMap.put("resolution", format2.getInteger("width") + "x" + format2.getInteger("height"));
                                    }
                                    if (format2.containsKey("mime")) {
                                        hashMap.put("videoMime", format2.getString("mime"));
                                    }
                                    if (Build.VERSION.SDK_INT >= 30 && format2.containsKey("codecs-string")) {
                                        hashMap.put("videoCodec", format2.getString("codecs-string"));
                                    }
                                }
                            } else if (trackType == 2 && (format = trackInfo2.getFormat()) != null) {
                                if (format.containsKey("mime")) {
                                    hashMap.put("audioMime", format.getString("mime"));
                                }
                                if (Build.VERSION.SDK_INT >= 30 && format.containsKey("codecs-string")) {
                                    hashMap.put("audioCodec", format.getString("codecs-string"));
                                }
                            }
                        }
                    }
                    if (!hashMap.isEmpty()) {
                        zzcdi.zzd.zzd("onMetadataEvent", hashMap);
                    }
                }
            } catch (RuntimeException e) {
                zzu.zzo().zzw(e, "AdMediaPlayerView.reportMetadata");
            }
        }
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzm = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        zze.zza("AdMediaPlayerView completion");
        zzF(5);
        this.zzh = 5;
        zzt.zza.post(new zzcdb(this));
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Map map = zzc;
        String str = (String) map.get(Integer.valueOf(i));
        String str2 = (String) map.get(Integer.valueOf(i2));
        zzm.zzj("AdMediaPlayerView MediaPlayer error: " + str + ":" + str2);
        zzF(-1);
        this.zzh = -1;
        zzt.zza.post(new zzcdc(this, str, str2));
        return true;
    }

    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        Map map = zzc;
        zze.zza("AdMediaPlayerView MediaPlayer info: " + ((String) map.get(Integer.valueOf(i))) + ":" + ((String) map.get(Integer.valueOf(i2))));
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
        if (r1 > r6) goto L_0x0060;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onMeasure(int r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.zzk
            int r0 = getDefaultSize(r0, r6)
            int r1 = r5.zzl
            int r1 = getDefaultSize(r1, r7)
            int r2 = r5.zzk
            if (r2 <= 0) goto L_0x007a
            int r2 = r5.zzl
            if (r2 <= 0) goto L_0x007a
            com.google.android.gms.internal.ads.zzcec r2 = r5.zzn
            if (r2 != 0) goto L_0x007a
            int r0 = android.view.View.MeasureSpec.getMode(r6)
            int r6 = android.view.View.MeasureSpec.getSize(r6)
            int r1 = android.view.View.MeasureSpec.getMode(r7)
            int r7 = android.view.View.MeasureSpec.getSize(r7)
            r2 = 1073741824(0x40000000, float:2.0)
            if (r0 != r2) goto L_0x0042
            if (r1 != r2) goto L_0x0041
            int r0 = r5.zzk
            int r1 = r0 * r7
            int r2 = r5.zzl
            int r3 = r6 * r2
            if (r1 >= r3) goto L_0x003c
            int r0 = r1 / r2
        L_0x003a:
            r1 = r7
            goto L_0x007a
        L_0x003c:
            if (r1 <= r3) goto L_0x0060
            int r1 = r3 / r0
            goto L_0x0052
        L_0x0041:
            r0 = r2
        L_0x0042:
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 != r2) goto L_0x0054
            int r0 = r5.zzl
            int r0 = r0 * r6
            int r2 = r5.zzk
            int r0 = r0 / r2
            if (r1 != r3) goto L_0x0051
            if (r0 <= r7) goto L_0x0051
            goto L_0x0060
        L_0x0051:
            r1 = r0
        L_0x0052:
            r0 = r6
            goto L_0x007a
        L_0x0054:
            if (r1 != r2) goto L_0x0064
            int r1 = r5.zzk
            int r1 = r1 * r7
            int r2 = r5.zzl
            int r1 = r1 / r2
            if (r0 != r3) goto L_0x0062
            if (r1 <= r6) goto L_0x0062
        L_0x0060:
            r0 = r6
            goto L_0x003a
        L_0x0062:
            r0 = r1
            goto L_0x003a
        L_0x0064:
            int r2 = r5.zzk
            int r4 = r5.zzl
            if (r1 != r3) goto L_0x0070
            if (r4 <= r7) goto L_0x0070
            int r1 = r7 * r2
            int r1 = r1 / r4
            goto L_0x0072
        L_0x0070:
            r1 = r2
            r7 = r4
        L_0x0072:
            if (r0 != r3) goto L_0x0062
            if (r1 <= r6) goto L_0x0062
            int r4 = r4 * r6
            int r1 = r4 / r2
            goto L_0x0052
        L_0x007a:
            r5.setMeasuredDimension(r0, r1)
            com.google.android.gms.internal.ads.zzcec r6 = r5.zzn
            if (r6 == 0) goto L_0x0084
            r6.zzc(r0, r1)
        L_0x0084:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdi.onMeasure(int, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x005f A[LOOP:0: B:12:0x005f->B:17:0x007a, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPrepared(android.media.MediaPlayer r8) {
        /*
            r7 = this;
            java.lang.String r0 = "AdMediaPlayerView prepared"
            com.google.android.gms.ads.internal.util.zze.zza(r0)
            r0 = 2
            r7.zzF(r0)
            com.google.android.gms.internal.ads.zzcef r0 = r7.zze
            r0.zzb()
            com.google.android.gms.internal.ads.zzfuv r0 = com.google.android.gms.ads.internal.util.zzt.zza
            com.google.android.gms.internal.ads.zzcda r1 = new com.google.android.gms.internal.ads.zzcda
            r1.<init>(r7, r8)
            r0.post(r1)
            int r0 = r8.getVideoWidth()
            r7.zzk = r0
            int r8 = r8.getVideoHeight()
            r7.zzl = r8
            int r8 = r7.zzp
            if (r8 == 0) goto L_0x002b
            r7.zzq(r8)
        L_0x002b:
            boolean r8 = r7.zzf
            r0 = 3
            if (r8 != 0) goto L_0x0031
            goto L_0x0084
        L_0x0031:
            boolean r8 = r7.zzH()
            if (r8 == 0) goto L_0x0084
            android.media.MediaPlayer r8 = r7.zzi
            int r8 = r8.getCurrentPosition()
            if (r8 <= 0) goto L_0x0084
            int r8 = r7.zzh
            if (r8 == r0) goto L_0x0084
            java.lang.String r8 = "AdMediaPlayerView nudging MediaPlayer"
            com.google.android.gms.ads.internal.util.zze.zza(r8)
            r8 = 0
            r7.zzG(r8)
            android.media.MediaPlayer r8 = r7.zzi
            r8.start()
            android.media.MediaPlayer r8 = r7.zzi
            int r8 = r8.getCurrentPosition()
            com.google.android.gms.common.util.Clock r1 = com.google.android.gms.ads.internal.zzu.zzB()
            long r1 = r1.currentTimeMillis()
        L_0x005f:
            boolean r3 = r7.zzH()
            if (r3 == 0) goto L_0x007c
            android.media.MediaPlayer r3 = r7.zzi
            int r3 = r3.getCurrentPosition()
            if (r3 != r8) goto L_0x007c
            com.google.android.gms.common.util.Clock r3 = com.google.android.gms.ads.internal.zzu.zzB()
            long r3 = r3.currentTimeMillis()
            long r3 = r3 - r1
            r5 = 250(0xfa, double:1.235E-321)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x005f
        L_0x007c:
            android.media.MediaPlayer r8 = r7.zzi
            r8.pause()
            r7.zzn()
        L_0x0084:
            int r8 = r7.zzk
            int r1 = r7.zzl
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "AdMediaPlayerView stream dimensions: "
            r2.<init>(r3)
            r2.append(r8)
            java.lang.String r8 = " x "
            r2.append(r8)
            r2.append(r1)
            java.lang.String r8 = r2.toString()
            com.google.android.gms.ads.internal.util.client.zzm.zzi(r8)
            int r8 = r7.zzh
            if (r8 != r0) goto L_0x00a8
            r7.zzp()
        L_0x00a8:
            r7.zzn()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzcdi.onPrepared(android.media.MediaPlayer):void");
    }

    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zze.zza("AdMediaPlayerView surface created");
        zzD();
        zzt.zza.post(new zzcdd(this));
    }

    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zze.zza("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null && this.zzp == 0) {
            this.zzp = mediaPlayer.getCurrentPosition();
        }
        zzcec zzcec = this.zzn;
        if (zzcec != null) {
            zzcec.zze();
        }
        zzt.zza.post(new zzcdf(this));
        zzE(true);
        return true;
    }

    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zze.zza("AdMediaPlayerView surface changed");
        int i3 = this.zzh;
        boolean z = false;
        if (this.zzk == i && this.zzl == i2) {
            z = true;
        }
        if (this.zzi != null && i3 == 3 && z) {
            int i4 = this.zzp;
            if (i4 != 0) {
                zzq(i4);
            }
            zzp();
        }
        zzcec zzcec = this.zzn;
        if (zzcec != null) {
            zzcec.zzc(i, i2);
        }
        zzt.zza.post(new zzcde(this, i, i2));
    }

    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zze.zzf(this);
        this.zza.zza(surfaceTexture, this.zzq);
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        zze.zza("AdMediaPlayerView size changed: " + i + " x " + i2);
        this.zzk = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.zzl = videoHeight;
        if (this.zzk != 0 && videoHeight != 0) {
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i) {
        zze.zza("AdMediaPlayerView window visibility changed to " + i);
        zzt.zza.post(new zzccz(this, i));
        super.onWindowVisibilityChanged(i);
    }

    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        return name + "@" + hexString;
    }

    public final int zza() {
        if (zzH()) {
            return this.zzi.getCurrentPosition();
        }
        return 0;
    }

    public final int zzb() {
        if (Build.VERSION.SDK_INT < 26 || !zzH()) {
            return -1;
        }
        return this.zzi.getMetrics().getInt("android.media.mediaplayer.dropped");
    }

    public final int zzc() {
        if (zzH()) {
            return this.zzi.getDuration();
        }
        return -1;
    }

    public final int zzd() {
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public final int zze() {
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public final long zzf() {
        return 0;
    }

    public final long zzg() {
        if (this.zzs != null) {
            return (zzh() * ((long) this.zzm)) / 100;
        }
        return -1;
    }

    public final long zzh() {
        if (this.zzs != null) {
            return ((long) zzc()) * ((long) this.zzs.intValue());
        }
        return -1;
    }

    public final String zzj() {
        return "MediaPlayer".concat(true != this.zzo ? "" : " spherical");
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void zzm(int i) {
        zzcdj zzcdj = this.zzq;
        if (zzcdj != null) {
            zzcdj.onWindowVisibilityChanged(i);
        }
    }

    public final void zzn() {
        zzG(this.zzb.zza());
    }

    public final void zzo() {
        zze.zza("AdMediaPlayerView pause");
        if (zzH() && this.zzi.isPlaying()) {
            this.zzi.pause();
            zzF(4);
            zzt.zza.post(new zzcdh(this));
        }
        this.zzh = 4;
    }

    public final void zzp() {
        zze.zza("AdMediaPlayerView play");
        if (zzH()) {
            this.zzi.start();
            zzF(3);
            this.zza.zzb();
            zzt.zza.post(new zzcdg(this));
        }
        this.zzh = 3;
    }

    public final void zzq(int i) {
        zze.zza("AdMediaPlayerView seek " + i);
        if (zzH()) {
            this.zzi.seekTo(i);
            this.zzp = 0;
            return;
        }
        this.zzp = i;
    }

    public final void zzr(zzcdj zzcdj) {
        this.zzq = zzcdj;
    }

    public final void zzs(String str) {
        Uri parse = Uri.parse(str);
        zzbcy zza = zzbcy.zza(parse);
        if (zza == null || zza.zza != null) {
            if (zza != null) {
                parse = Uri.parse(zza.zza);
            }
            this.zzj = parse;
            this.zzp = 0;
            zzD();
            requestLayout();
            invalidate();
        }
    }

    public final void zzt() {
        zze.zza("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzi;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzi.release();
            this.zzi = null;
            zzF(0);
            this.zzh = 0;
        }
        this.zze.zzd();
    }

    public final void zzu(float f, float f2) {
        zzcec zzcec = this.zzn;
        if (zzcec != null) {
            zzcec.zzf(f, f2);
        }
    }
}
