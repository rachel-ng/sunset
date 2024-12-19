package com.google.android.gms.internal.ads;

import com.google.android.exoplayer2.RendererCapabilities;
import com.google.android.exoplayer2.audio.AacUtil;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.audio.DtsUtil;
import com.google.android.exoplayer2.audio.MpegAudioUtil;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.util.MimeTypes;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
public final class zzaeo {
    /* access modifiers changed from: private */
    public static final String[] zza = {MimeTypes.AUDIO_MPEG_L1, MimeTypes.AUDIO_MPEG_L2, MimeTypes.AUDIO_MPEG};
    /* access modifiers changed from: private */
    public static final int[] zzb = {44100, OpusUtil.SAMPLE_RATE, 32000};
    /* access modifiers changed from: private */
    public static final int[] zzc = {32000, 64000, 96000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 288000, 320000, 352000, 384000, 416000, 448000};
    /* access modifiers changed from: private */
    public static final int[] zzd = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000, 176000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND};
    /* access modifiers changed from: private */
    public static final int[] zze = {32000, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000, 384000};
    /* access modifiers changed from: private */
    public static final int[] zzf = {32000, MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 160000, DtsUtil.DTS_MAX_RATE_BYTES_PER_SECOND, 224000, AacUtil.AAC_XHE_MAX_RATE_BYTES_PER_SECOND, 320000};
    /* access modifiers changed from: private */
    public static final int[] zzg = {8000, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND, 24000, 32000, MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND, OpusUtil.SAMPLE_RATE, 56000, 64000, Ac3Util.AC3_MAX_RATE_BYTES_PER_SECOND, 96000, 112000, 128000, 144000, 160000};

    public static int zzc(int i) {
        int i2;
        int i3;
        if (!zzm(i) || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0) {
            return -1;
        }
        int i4 = i >>> 12;
        int i5 = (i >>> 10) & 3;
        int i6 = i4 & 15;
        if (i6 == 0 || i6 == 15 || i5 == 3) {
            return -1;
        }
        return zzl(i2, i3);
    }

    /* access modifiers changed from: private */
    public static int zzl(int i, int i2) {
        if (i2 == 1) {
            return i == 3 ? 1152 : 576;
        }
        if (i2 != 2) {
            return RendererCapabilities.MODE_SUPPORT_MASK;
        }
        return 1152;
    }

    /* access modifiers changed from: private */
    public static boolean zzm(int i) {
        return (i & -2097152) == -2097152;
    }

    public static int zzb(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (!zzm(i) || (i2 = (i >>> 19) & 3) == 1 || (i3 = (i >>> 17) & 3) == 0 || (i4 = (i >>> 12) & 15) == 0 || i4 == 15 || (i5 = (i >>> 10) & 3) == 3) {
            return -1;
        }
        int i7 = i4 - 1;
        int i8 = zzb[i5];
        if (i2 == 2) {
            i8 /= 2;
        } else if (i2 == 0) {
            i8 /= 4;
        }
        int i9 = (i >>> 9) & 1;
        if (i3 == 3) {
            return ((((i2 == 3 ? zzc[i7] : zzd[i7]) * 12) / i8) + i9) * 4;
        }
        if (i2 == 3) {
            i6 = i3 == 2 ? zze[i7] : zzf[i7];
        } else {
            i6 = zzg[i7];
        }
        int i10 = 144;
        if (i2 == 3) {
            return ((i6 * 144) / i8) + i9;
        }
        if (i3 == 1) {
            i10 = 72;
        }
        return ((i10 * i6) / i8) + i9;
    }
}
