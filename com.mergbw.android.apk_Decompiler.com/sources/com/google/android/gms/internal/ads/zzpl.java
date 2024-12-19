package com.google.android.gms.internal.ads;

import android.media.AudioFormat;
import com.google.android.exoplayer2.audio.OpusUtil;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzpl {
    public static int zza(int i, int i2, zzk zzk) {
        for (int i3 = 10; i3 > 0; i3--) {
            int zzh = zzgd.zzh(i3);
            if (zzh != 0 && FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(new AudioFormat.Builder().setEncoding(i).setSampleRate(i2).setChannelMask(zzh).build(), zzk.zza().zza)) {
                return i3;
            }
        }
        return 0;
    }

    public static zzgbc<Integer> zzb(zzk zzk) {
        zzgaz zzgaz = new zzgaz();
        zzgdi zze = zzpp.zzb.keySet().iterator();
        while (zze.hasNext()) {
            Integer num = (Integer) zze.next();
            int intValue = num.intValue();
            if (zzgd.zza >= zzgd.zzg(intValue) && FrameworkMediaDrm$$ExternalSyntheticApiModelOutline0.m(new AudioFormat.Builder().setChannelMask(12).setEncoding(intValue).setSampleRate(OpusUtil.SAMPLE_RATE).build(), zzk.zza().zza)) {
                zzgaz.zzf(num);
            }
        }
        zzgaz.zzf(2);
        return zzgaz.zzi();
    }
}
