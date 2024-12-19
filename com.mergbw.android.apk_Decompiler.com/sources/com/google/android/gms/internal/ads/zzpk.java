package com.google.android.gms.internal.ads;

import android.media.AudioDeviceInfo;
import android.media.AudioManager;

/* compiled from: com.google.android.gms:play-services-ads@@23.2.0 */
final class zzpk {
    private static zzgbh<Integer> zzb() {
        zzgbg zzgbg = new zzgbg();
        zzgbg.zzg(8, 7);
        if (zzgd.zza >= 31) {
            zzgbg.zzg(26, 27);
        }
        if (zzgd.zza >= 33) {
            zzgbg.zzf(30);
        }
        return zzgbg.zzi();
    }

    public static boolean zza(AudioManager audioManager, zzpx zzpx) {
        AudioDeviceInfo[] audioDeviceInfoArr;
        if (zzpx == null) {
            audioManager.getClass();
            audioDeviceInfoArr = audioManager.getDevices(2);
        } else {
            audioDeviceInfoArr = new AudioDeviceInfo[]{zzpx.zza};
        }
        zzgbh<Integer> zzb = zzb();
        for (AudioDeviceInfo type : audioDeviceInfoArr) {
            if (zzb.contains(Integer.valueOf(type.getType()))) {
                return true;
            }
        }
        return false;
    }
}
