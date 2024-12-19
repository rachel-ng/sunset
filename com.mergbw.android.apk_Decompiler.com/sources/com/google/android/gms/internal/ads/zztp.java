package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.media.MediaCodecInfo;
import android.util.Pair;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zztp {
    public final String zza;
    public final String zzb;
    public final String zzc;
    public final MediaCodecInfo.CodecCapabilities zzd;
    public final boolean zze;
    public final boolean zzf;
    public final boolean zzg;
    private final boolean zzh;

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        if (r15.isFeatureSupported("secure-playback") != false) goto L_0x005e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.ads.zztp zzc(java.lang.String r12, java.lang.String r13, java.lang.String r14, android.media.MediaCodecInfo.CodecCapabilities r15, boolean r16, boolean r17, boolean r18, boolean r19, boolean r20) {
        /*
            r1 = r12
            r4 = r15
            com.google.android.gms.internal.ads.zztp r11 = new com.google.android.gms.internal.ads.zztp
            r0 = 1
            r2 = 0
            if (r4 == 0) goto L_0x003d
            java.lang.String r3 = "adaptive-playback"
            boolean r3 = r15.isFeatureSupported(r3)
            if (r3 == 0) goto L_0x003d
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            r5 = 22
            if (r3 > r5) goto L_0x003b
            java.lang.String r3 = "ODROID-XU3"
            java.lang.String r5 = com.google.android.gms.internal.ads.zzgd.zzd
            boolean r3 = r3.equals(r5)
            if (r3 != 0) goto L_0x002a
            java.lang.String r3 = "Nexus 10"
            java.lang.String r5 = com.google.android.gms.internal.ads.zzgd.zzd
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x003b
        L_0x002a:
            java.lang.String r3 = "OMX.Exynos.AVC.Decoder"
            boolean r3 = r3.equals(r12)
            if (r3 != 0) goto L_0x003d
            java.lang.String r3 = "OMX.Exynos.AVC.Decoder.secure"
            boolean r3 = r3.equals(r12)
            if (r3 == 0) goto L_0x003b
            goto L_0x003d
        L_0x003b:
            r8 = r0
            goto L_0x003e
        L_0x003d:
            r8 = r2
        L_0x003e:
            if (r4 == 0) goto L_0x004c
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            java.lang.String r3 = "tunneled-playback"
            boolean r3 = r15.isFeatureSupported(r3)
            if (r3 == 0) goto L_0x004c
            r9 = r0
            goto L_0x004d
        L_0x004c:
            r9 = r2
        L_0x004d:
            if (r20 != 0) goto L_0x005e
            if (r4 == 0) goto L_0x005c
            int r3 = com.google.android.gms.internal.ads.zzgd.zza
            java.lang.String r3 = "secure-playback"
            boolean r3 = r15.isFeatureSupported(r3)
            if (r3 == 0) goto L_0x005c
            goto L_0x005e
        L_0x005c:
            r10 = r2
            goto L_0x005f
        L_0x005e:
            r10 = r0
        L_0x005f:
            r0 = r11
            r1 = r12
            r2 = r13
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztp.zzc(java.lang.String, java.lang.String, java.lang.String, android.media.MediaCodecInfo$CodecCapabilities, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.ads.zztp");
    }

    private static Point zzi(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2) {
        int widthAlignment = videoCapabilities.getWidthAlignment();
        int heightAlignment = videoCapabilities.getHeightAlignment();
        int i3 = zzgd.zza;
        return new Point((((i + widthAlignment) - 1) / widthAlignment) * widthAlignment, (((i2 + heightAlignment) - 1) / heightAlignment) * heightAlignment);
    }

    private final void zzj(String str) {
        String str2 = zzgd.zze;
        zzfk.zzb(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "NoSupport [" + str + "] [" + this.zza + ", " + this.zzb + "] [" + str2 + "]");
    }

    private static boolean zzk(MediaCodecInfo.VideoCapabilities videoCapabilities, int i, int i2, double d) {
        Point zzi = zzi(videoCapabilities, i, i2);
        int i3 = zzi.x;
        int i4 = zzi.y;
        if (d == -1.0d || d < 1.0d) {
            return videoCapabilities.isSizeSupported(i3, i4);
        }
        return videoCapabilities.areSizeAndRateSupported(i3, i4, Math.floor(d));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0064, code lost:
        r3 = r3.getVideoCapabilities();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzl(com.google.android.gms.internal.ads.zzan r12, boolean r13) {
        /*
            r11 = this;
            android.util.Pair r0 = com.google.android.gms.internal.ads.zzuj.zza(r12)
            r1 = 1
            if (r0 != 0) goto L_0x0009
            goto L_0x0104
        L_0x0009:
            java.lang.Object r2 = r0.first
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.second
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.String r3 = r12.zzn
            java.lang.String r4 = "video/dolby-vision"
            boolean r3 = r4.equals(r3)
            r4 = 8
            java.lang.String r5 = "video/hevc"
            r6 = 2
            r7 = 0
            if (r3 == 0) goto L_0x0040
            java.lang.String r3 = r11.zzb
            java.lang.String r8 = "video/avc"
            boolean r3 = r8.equals(r3)
            if (r3 == 0) goto L_0x0036
            r2 = r4
        L_0x0034:
            r0 = r7
            goto L_0x0040
        L_0x0036:
            java.lang.String r3 = r11.zzb
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L_0x0040
            r2 = r6
            goto L_0x0034
        L_0x0040:
            boolean r3 = r11.zzh
            if (r3 != 0) goto L_0x0049
            r3 = 42
            if (r2 != r3) goto L_0x0104
            r2 = r3
        L_0x0049:
            android.media.MediaCodecInfo$CodecProfileLevel[] r3 = r11.zzh()
            int r8 = com.google.android.gms.internal.ads.zzgd.zza
            r9 = 23
            if (r8 > r9) goto L_0x00d5
            java.lang.String r8 = r11.zzb
            java.lang.String r9 = "video/x-vnd.on2.vp9"
            boolean r8 = r9.equals(r8)
            if (r8 == 0) goto L_0x00d5
            int r8 = r3.length
            if (r8 != 0) goto L_0x00d5
            android.media.MediaCodecInfo$CodecCapabilities r3 = r11.zzd
            if (r3 == 0) goto L_0x0079
            android.media.MediaCodecInfo$VideoCapabilities r3 = r3.getVideoCapabilities()
            if (r3 == 0) goto L_0x0079
            android.util.Range r3 = r3.getBitrateRange()
            java.lang.Comparable r3 = r3.getUpper()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            goto L_0x007a
        L_0x0079:
            r3 = r7
        L_0x007a:
            r8 = 180000000(0xaba9500, float:1.7967196E-32)
            if (r3 < r8) goto L_0x0082
            r4 = 1024(0x400, float:1.435E-42)
            goto L_0x00c7
        L_0x0082:
            r8 = 120000000(0x7270e00, float:1.2567798E-34)
            if (r3 < r8) goto L_0x008a
            r4 = 512(0x200, float:7.175E-43)
            goto L_0x00c7
        L_0x008a:
            r8 = 60000000(0x3938700, float:8.670878E-37)
            if (r3 < r8) goto L_0x0092
            r4 = 256(0x100, float:3.59E-43)
            goto L_0x00c7
        L_0x0092:
            r8 = 30000000(0x1c9c380, float:7.411627E-38)
            if (r3 < r8) goto L_0x009a
            r4 = 128(0x80, float:1.794E-43)
            goto L_0x00c7
        L_0x009a:
            r8 = 18000000(0x112a880, float:2.6936858E-38)
            if (r3 < r8) goto L_0x00a2
            r4 = 64
            goto L_0x00c7
        L_0x00a2:
            r8 = 12000000(0xb71b00, float:1.6815582E-38)
            if (r3 < r8) goto L_0x00aa
            r4 = 32
            goto L_0x00c7
        L_0x00aa:
            r8 = 7200000(0x6ddd00, float:1.0089349E-38)
            if (r3 < r8) goto L_0x00b2
            r4 = 16
            goto L_0x00c7
        L_0x00b2:
            r8 = 3600000(0x36ee80, float:5.044674E-39)
            if (r3 < r8) goto L_0x00b8
            goto L_0x00c7
        L_0x00b8:
            r4 = 1800000(0x1b7740, float:2.522337E-39)
            if (r3 < r4) goto L_0x00bf
            r4 = 4
            goto L_0x00c7
        L_0x00bf:
            r4 = 800000(0xc3500, float:1.121039E-39)
            if (r3 < r4) goto L_0x00c6
            r4 = r6
            goto L_0x00c7
        L_0x00c6:
            r4 = r1
        L_0x00c7:
            android.media.MediaCodecInfo$CodecProfileLevel r3 = new android.media.MediaCodecInfo$CodecProfileLevel
            r3.<init>()
            r3.profile = r1
            r3.level = r4
            android.media.MediaCodecInfo$CodecProfileLevel[] r4 = new android.media.MediaCodecInfo.CodecProfileLevel[r1]
            r4[r7] = r3
            r3 = r4
        L_0x00d5:
            int r4 = r3.length
            r8 = r7
        L_0x00d7:
            if (r8 >= r4) goto L_0x0108
            r9 = r3[r8]
            int r10 = r9.profile
            if (r10 != r2) goto L_0x0105
            int r9 = r9.level
            if (r9 >= r0) goto L_0x00e5
            if (r13 != 0) goto L_0x0105
        L_0x00e5:
            java.lang.String r9 = r11.zzb
            boolean r9 = r5.equals(r9)
            if (r9 == 0) goto L_0x0104
            if (r2 != r6) goto L_0x0104
            java.lang.String r9 = "sailfish"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb
            boolean r9 = r9.equals(r10)
            if (r9 != 0) goto L_0x0105
            java.lang.String r9 = "marlin"
            java.lang.String r10 = com.google.android.gms.internal.ads.zzgd.zzb
            boolean r9 = r9.equals(r10)
            if (r9 == 0) goto L_0x0104
            goto L_0x0105
        L_0x0104:
            return r1
        L_0x0105:
            int r8 = r8 + 1
            goto L_0x00d7
        L_0x0108:
            java.lang.String r12 = r12.zzk
            java.lang.String r13 = r11.zzc
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "codec.profileLevel, "
            r0.<init>(r1)
            r0.append(r12)
            java.lang.String r12 = ", "
            r0.append(r12)
            r0.append(r13)
            java.lang.String r12 = r0.toString()
            r11.zzj(r12)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztp.zzl(com.google.android.gms.internal.ads.zzan, boolean):boolean");
    }

    private final boolean zzm(zzan zzan) {
        return this.zzb.equals(zzan.zzn) || this.zzb.equals(zzuj.zzc(zzan));
    }

    public final String toString() {
        return this.zza;
    }

    public final Point zza(int i, int i2) {
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        if (codecCapabilities == null || (videoCapabilities = codecCapabilities.getVideoCapabilities()) == null) {
            return null;
        }
        return zzi(videoCapabilities, i, i2);
    }

    public final zziy zzb(zzan zzan, zzan zzan2) {
        int i = true != zzgd.zzG(zzan.zzn, zzan2.zzn) ? 8 : 0;
        if (this.zzh) {
            if (zzan.zzv != zzan2.zzv) {
                i |= 1024;
            }
            if (!this.zze && !(zzan.zzs == zzan2.zzs && zzan.zzt == zzan2.zzt)) {
                i |= 512;
            }
            if ((!zzt.zzg(zzan.zzz) || !zzt.zzg(zzan2.zzz)) && !zzgd.zzG(zzan.zzz, zzan2.zzz)) {
                i |= 2048;
            }
            String str = this.zza;
            if (zzgd.zzd.startsWith("SM-T230") && "OMX.MARVELL.VIDEO.HW.CODA7542DECODER".equals(str) && !zzan.zzd(zzan2)) {
                i |= 2;
            }
            if (i == 0) {
                return new zziy(this.zza, zzan, zzan2, true != zzan.zzd(zzan2) ? 2 : 3, 0);
            }
        } else {
            if (zzan.zzA != zzan2.zzA) {
                i |= 4096;
            }
            if (zzan.zzB != zzan2.zzB) {
                i |= 8192;
            }
            if (zzan.zzC != zzan2.zzC) {
                i |= 16384;
            }
            if (i == 0 && MimeTypes.AUDIO_AAC.equals(this.zzb)) {
                Pair zza2 = zzuj.zza(zzan);
                Pair zza3 = zzuj.zza(zzan2);
                if (!(zza2 == null || zza3 == null)) {
                    int intValue = ((Integer) zza2.first).intValue();
                    int intValue2 = ((Integer) zza3.first).intValue();
                    if (intValue == 42 && intValue2 == 42) {
                        return new zziy(this.zza, zzan, zzan2, 3, 0);
                    }
                }
            }
            if (!zzan.zzd(zzan2)) {
                i |= 32;
            }
            if (MimeTypes.AUDIO_OPUS.equals(this.zzb)) {
                i |= 2;
            }
            if (i == 0) {
                return new zziy(this.zza, zzan, zzan2, 1, 0);
            }
        }
        return new zziy(this.zza, zzan, zzan2, 0, i);
    }

    public final boolean zzd(zzan zzan) {
        if (!zzm(zzan) || !zzl(zzan, false)) {
            return false;
        }
        return true;
    }

    public final boolean zze(zzan zzan) throws zzud {
        int i;
        if (!zzm(zzan) || !zzl(zzan, true)) {
            return false;
        }
        if (!this.zzh) {
            int i2 = zzgd.zza;
            int i3 = zzan.zzB;
            if (i3 != -1) {
                MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
                if (codecCapabilities == null) {
                    zzj("sampleRate.caps");
                    return false;
                }
                MediaCodecInfo.AudioCapabilities audioCapabilities = codecCapabilities.getAudioCapabilities();
                if (audioCapabilities == null) {
                    zzj("sampleRate.aCaps");
                    return false;
                } else if (!audioCapabilities.isSampleRateSupported(i3)) {
                    zzj("sampleRate.support, " + i3);
                    return false;
                }
            }
            int i4 = zzan.zzA;
            if (i4 != -1) {
                MediaCodecInfo.CodecCapabilities codecCapabilities2 = this.zzd;
                if (codecCapabilities2 == null) {
                    zzj("channelCount.caps");
                } else {
                    MediaCodecInfo.AudioCapabilities audioCapabilities2 = codecCapabilities2.getAudioCapabilities();
                    if (audioCapabilities2 == null) {
                        zzj("channelCount.aCaps");
                    } else {
                        String str = this.zza;
                        String str2 = this.zzb;
                        int maxInputChannelCount = audioCapabilities2.getMaxInputChannelCount();
                        if (maxInputChannelCount <= 1 && ((zzgd.zza < 26 || maxInputChannelCount <= 0) && !MimeTypes.AUDIO_MPEG.equals(str2) && !MimeTypes.AUDIO_AMR_NB.equals(str2) && !MimeTypes.AUDIO_AMR_WB.equals(str2) && !MimeTypes.AUDIO_AAC.equals(str2) && !MimeTypes.AUDIO_VORBIS.equals(str2) && !MimeTypes.AUDIO_OPUS.equals(str2) && !MimeTypes.AUDIO_RAW.equals(str2) && !MimeTypes.AUDIO_FLAC.equals(str2) && !MimeTypes.AUDIO_ALAW.equals(str2) && !MimeTypes.AUDIO_MLAW.equals(str2) && !MimeTypes.AUDIO_MSGSM.equals(str2))) {
                            if (MimeTypes.AUDIO_AC3.equals(str2)) {
                                i = 6;
                            } else {
                                i = MimeTypes.AUDIO_E_AC3.equals(str2) ? 16 : 30;
                            }
                            zzfk.zzf(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "AssumedMaxChannelAdjustment: " + str + ", [" + maxInputChannelCount + " to " + i + "]");
                            maxInputChannelCount = i;
                        }
                        if (maxInputChannelCount < i4) {
                            zzj("channelCount.support, " + i4);
                        }
                    }
                }
                return false;
            }
            return true;
        } else if (zzan.zzs <= 0 || zzan.zzt <= 0) {
            return true;
        } else {
            int i5 = zzgd.zza;
            return zzg(zzan.zzs, zzan.zzt, (double) zzan.zzu);
        }
    }

    public final boolean zzf(zzan zzan) {
        if (this.zzh) {
            return this.zze;
        }
        Pair zza2 = zzuj.zza(zzan);
        return zza2 != null && ((Integer) zza2.first).intValue() == 42;
    }

    public final boolean zzg(int i, int i2, double d) {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        if (codecCapabilities == null) {
            zzj("sizeAndRate.caps");
            return false;
        }
        MediaCodecInfo.VideoCapabilities videoCapabilities = codecCapabilities.getVideoCapabilities();
        if (videoCapabilities == null) {
            zzj("sizeAndRate.vCaps");
            return false;
        }
        if (zzgd.zza >= 29) {
            int zza2 = zztr.zza(videoCapabilities, i, i2, d);
            if (zza2 != 2) {
                if (zza2 == 1) {
                    zzj("sizeAndRate.cover, " + i + "x" + i2 + "@" + d);
                    return false;
                }
            }
            return true;
        }
        if (!zzk(videoCapabilities, i, i2, d)) {
            if (i >= i2 || (("OMX.MTK.VIDEO.DECODER.HEVC".equals(this.zza) && "mcv5a".equals(zzgd.zzb)) || !zzk(videoCapabilities, i2, i, d))) {
                zzj("sizeAndRate.support, " + i + "x" + i2 + "@" + d);
                return false;
            }
            zzfk.zzb(com.google.android.exoplayer2.mediacodec.MediaCodecInfo.TAG, "AssumedSupport [" + ("sizeAndRate.rotated, " + i + "x" + i2 + "@" + d) + "] [" + this.zza + ", " + this.zzb + "] [" + zzgd.zze + "]");
        }
        return true;
    }

    public final MediaCodecInfo.CodecProfileLevel[] zzh() {
        MediaCodecInfo.CodecCapabilities codecCapabilities = this.zzd;
        if (codecCapabilities == null || codecCapabilities.profileLevels == null) {
            return new MediaCodecInfo.CodecProfileLevel[0];
        }
        return this.zzd.profileLevels;
    }

    zztp(String str, String str2, String str3, MediaCodecInfo.CodecCapabilities codecCapabilities, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        str.getClass();
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = codecCapabilities;
        this.zzg = z;
        this.zze = z4;
        this.zzf = z6;
        this.zzh = zzcg.zzh(str2);
    }
}
