package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zztq {
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0089, code lost:
        if (zzb(r6, com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(1280, 720, 60)) == 1) goto L_0x008f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int zza(android.media.MediaCodecInfo.VideoCapabilities r2, int r3, int r4, double r5) {
        /*
            java.util.List r2 = r2.getSupportedPerformancePoints()
            r0 = 0
            if (r2 == 0) goto L_0x00a2
            boolean r1 = r2.isEmpty()
            if (r1 == 0) goto L_0x000f
            goto L_0x00a2
        L_0x000f:
            int r5 = (int) r5
            android.media.MediaCodecInfo$VideoCapabilities$PerformancePoint r3 = com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m((int) r3, (int) r4, (int) r5)
            int r2 = zzb(r2, r3)
            r3 = 1
            if (r2 != r3) goto L_0x00a1
            java.lang.Boolean r4 = com.google.android.gms.internal.ads.zztr.zza
            if (r4 != 0) goto L_0x00a1
            int r4 = com.google.android.gms.internal.ads.zzgd.zza
            r5 = 35
            if (r4 < r5) goto L_0x0029
        L_0x0027:
            r3 = r0
            goto L_0x008f
        L_0x0029:
            com.google.android.gms.internal.ads.zzal r4 = new com.google.android.gms.internal.ads.zzal     // Catch:{ zzud -> 0x008f }
            r4.<init>()     // Catch:{ zzud -> 0x008f }
            java.lang.String r5 = "video/avc"
            r4.zzX(r5)     // Catch:{ zzud -> 0x008f }
            com.google.android.gms.internal.ads.zzan r4 = r4.zzad()     // Catch:{ zzud -> 0x008f }
            java.lang.String r5 = r4.zzn     // Catch:{ zzud -> 0x008f }
            if (r5 == 0) goto L_0x008f
            com.google.android.gms.internal.ads.zztx r5 = com.google.android.gms.internal.ads.zztx.zza     // Catch:{ zzud -> 0x008f }
            java.util.List r4 = com.google.android.gms.internal.ads.zzuj.zzf(r5, r4, r0, r0)     // Catch:{ zzud -> 0x008f }
            r5 = r0
        L_0x0042:
            int r6 = r4.size()     // Catch:{ zzud -> 0x008f }
            if (r5 >= r6) goto L_0x008f
            java.lang.Object r6 = r4.get(r5)     // Catch:{ zzud -> 0x008f }
            com.google.android.gms.internal.ads.zztp r6 = (com.google.android.gms.internal.ads.zztp) r6     // Catch:{ zzud -> 0x008f }
            android.media.MediaCodecInfo$CodecCapabilities r6 = r6.zzd     // Catch:{ zzud -> 0x008f }
            if (r6 == 0) goto L_0x008c
            java.lang.Object r6 = r4.get(r5)     // Catch:{ zzud -> 0x008f }
            com.google.android.gms.internal.ads.zztp r6 = (com.google.android.gms.internal.ads.zztp) r6     // Catch:{ zzud -> 0x008f }
            android.media.MediaCodecInfo$CodecCapabilities r6 = r6.zzd     // Catch:{ zzud -> 0x008f }
            android.media.MediaCodecInfo$VideoCapabilities r6 = r6.getVideoCapabilities()     // Catch:{ zzud -> 0x008f }
            if (r6 == 0) goto L_0x008c
            java.lang.Object r6 = r4.get(r5)     // Catch:{ zzud -> 0x008f }
            com.google.android.gms.internal.ads.zztp r6 = (com.google.android.gms.internal.ads.zztp) r6     // Catch:{ zzud -> 0x008f }
            android.media.MediaCodecInfo$CodecCapabilities r6 = r6.zzd     // Catch:{ zzud -> 0x008f }
            android.media.MediaCodecInfo$VideoCapabilities r6 = r6.getVideoCapabilities()     // Catch:{ zzud -> 0x008f }
            java.util.List r6 = r6.getSupportedPerformancePoints()     // Catch:{ zzud -> 0x008f }
            if (r6 == 0) goto L_0x008c
            boolean r1 = r6.isEmpty()     // Catch:{ zzud -> 0x008f }
            if (r1 != 0) goto L_0x008c
            com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m$1()     // Catch:{ zzud -> 0x008f }
            r4 = 720(0x2d0, float:1.009E-42)
            r5 = 60
            r1 = 1280(0x500, float:1.794E-42)
            android.media.MediaCodecInfo$VideoCapabilities$PerformancePoint r4 = com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m((int) r1, (int) r4, (int) r5)     // Catch:{ zzud -> 0x008f }
            int r4 = zzb(r6, r4)     // Catch:{ zzud -> 0x008f }
            if (r4 != r3) goto L_0x0027
            goto L_0x008f
        L_0x008c:
            int r5 = r5 + 1
            goto L_0x0042
        L_0x008f:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            com.google.android.gms.internal.ads.zztr.zza = r3
            java.lang.Boolean r3 = com.google.android.gms.internal.ads.zztr.zza
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x00a1
            return r0
        L_0x00a1:
            return r2
        L_0x00a2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zztq.zza(android.media.MediaCodecInfo$VideoCapabilities, int, int, double):int");
    }

    private static int zzb(List list, MediaCodecInfo.VideoCapabilities.PerformancePoint performancePoint) {
        for (int i = 0; i < list.size(); i++) {
            if (FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(list.get(i)).covers(performancePoint)) {
                return 2;
            }
        }
        return 1;
    }
}
